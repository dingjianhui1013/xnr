/**
 * 2013年9月17日 下午6:15:43
 */
package com.xnradmin.core.util;


import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.codehaus.commons.compiler.CompileException;
import org.codehaus.janino.JavaSourceClassLoader;
import org.codehaus.janino.SimpleCompiler;
import org.springframework.stereotype.Service;

import com.cntinker.util.FileHelper;
import com.cntinker.util.ReflectHelper;
import com.cntinker.util.StringHelper;
import com.xnradmin.core.cache.ScriptCacheManager;
import com.xnradmin.constant.templates.JavaClassKeyword;
import com.xnradmin.dto.ScriptDTO;

/**
 * @autohr: bin_liu
 */
@Service("ScriptHelper")
public class ScriptHelper{

    private static Map<String, ScriptDTO> classMap = new Hashtable<String, ScriptDTO>();

    private static final Log log = Log4jUtil.getLog("script");

    public void reload() throws CompileException,ClassNotFoundException,
            IOException{
        List<String> l = ScriptCacheManager.getKeyIndex();
        for(String e : l){
            reload(e);
        }
    }

    /**
     * 加载本地所有脚本
     * 
     * @throws CompileException
     * @throws ClassNotFoundException
     * @throws IOException
     */
    public void reloadByLocal() throws CompileException,ClassNotFoundException,
            IOException{
        File[] fList = FileHelper.getAllFile(SpringBase.getScriptDTO()
                .getScriptDIR(),null);
        for(File f : fList){
            if(!FileHelper.getSuffix(f.getAbsolutePath()).equals("java"))
                continue;
            String className = getIntegralScriptName(f.getAbsolutePath());
            loadByLocal(className);
        }
    }

    public void del(String scriptClassName) throws CompileException,
            ClassNotFoundException,IOException{
        ScriptDTO dto = find(scriptClassName);
        classMap.remove(scriptClassName);
        System.out.println("classMap: " + classMap);
        ScriptCacheManager.remove(dto);
        System.out.println("classMap: " + classMap);
    }

    /**
     * 从本地加载一个脚本编译，添加缓存
     * 
     * @param scriptClassName
     * @return ScriptDTO
     * @throws CompileException
     * @throws ClassNotFoundException
     * @throws IOException
     */
    public ScriptDTO loadByLocal(String scriptClassName)
            throws CompileException,ClassNotFoundException,IOException{

        ScriptDTO dto = new ScriptDTO();
        String file = findScriptFileByName(scriptClassName);
        dto.setScriptDIR(SpringBase.getScriptDTO().getScriptDIR());
        dto.setClassName(getIntegralScriptName(file));
        dto.setSourceFile(file);
        dto.setScriptEncode(SpringBase.getScriptDTO().getScriptEncode());
        dto.setScriptMethods(SpringBase.getScriptDTO().getScriptMethods());
        dto = compiler(dto);
        classMap.put(dto.getClassName(),dto);
        ScriptCacheManager.add(dto);

        return dto;
    }

    public ScriptDTO reload(String scriptClassName) throws CompileException,
            ClassNotFoundException,IOException{

        if(StringHelper.isNull(scriptClassName))
            return null;
        ScriptDTO dto = null;
        if(classMap.containsKey(scriptClassName))
            dto = classMap.remove(scriptClassName);
        if(dto == null)
            dto = loadByLocal(scriptClassName);

        dto = compiler(dto);
        classMap.put(dto.getClassName(),dto);
        ScriptCacheManager.add(dto);

        return dto;
    }

    /**
     * ScriptDTO:<br>
     * lastModifyTime=System.currentTimeMillis=reload class<br>
     * 
     * @param script
     * @return ScriptDTO
     * @throws CompileException
     * @throws ClassNotFoundException
     * @throws IOException
     */
    public ScriptDTO find(String scriptClassName) throws CompileException,
            ClassNotFoundException,IOException{
        System.out.println("classMap size : " + classMap.size());
        ScriptDTO dto = classMap.get(scriptClassName);
        boolean isCompiler = false;
        if(ScriptCacheManager.isCached(scriptClassName)){
            if(dto == null
                    || ( ScriptCacheManager.get(dto.getClassName()).longValue() > dto
                            .getLastModifyTime() ))
                isCompiler = true;
        }else
            isCompiler = true;
        if(isCompiler){
            // if(dto==null)
            dto = loadByLocal(scriptClassName);
            // dto = compiler(dto);
            classMap.put(dto.getClassName(),dto);
            ScriptCacheManager.add(dto);
        }
        // System.out.println(classMap);
        // System.out.println("classMap size : " + classMap.size());
        return dto;
    }

    /**
     * ScriptDTO:<br>
     * lastModifyTime=System.currentTimeMillis=reload class<br>
     * 
     * @param methodName
     * @param script
     * @param parameter
     */
    public Object executeMethod(String methodName,ScriptDTO script,
            Object... parameter){
        Object o = null;
        try{
            ScriptDTO dto = find(script.getClassName());
            if(dto == null)
                throw new IllegalArgumentException("error input script:"
                        + script);
            Object obj = dto.getLoadClass().newInstance();
            Method m = ReflectHelper.getMethod(obj.getClass(),methodName);
            if(m == null)
                throw new IllegalArgumentException("method not found:"
                        + methodName + " in " + script);
            if(parameter == null)
                o = m.invoke(obj,null);
            else{
                Object arrayObj = Array.newInstance(Object.class,
                        parameter.length);
                for(int i = 0;i < parameter.length;i ++ ){
                    Array.set(arrayObj,i,parameter[i]);
                }

                o = m.invoke(obj,arrayObj);
            }
            // m.invoke(dto.getLoadClass().newInstance(),parameter);
        }catch(CompileException e){
            throw new IllegalArgumentException("compileException:" + script
                    + "\r\n" + StringHelper.getStackInfo(e));
        }catch(IOException e){
            throw new IllegalArgumentException(
                    "IOException, source file not found:" + script + "\r\n"
                            + StringHelper.getStackInfo(e));
        }catch(ClassNotFoundException e){
            throw new IllegalArgumentException(
                    "ClassNotFoundException classLoader error:" + script
                            + "\r\n" + StringHelper.getStackInfo(e));
        }catch(Exception e){
            e.printStackTrace();
            throw new IllegalArgumentException("Exception:" + script + "\r\n"
                    + StringHelper.getStackInfo(e));
        }

        return o;
    }

    private synchronized ScriptDTO compiler(ScriptDTO script)
            throws CompileException,IOException,ClassNotFoundException{
        long a = System.currentTimeMillis();
        File f = new File(script.getSourceFile());
        String fName = f.getName().substring(0,f.getName().indexOf("."));
        String clsName = script.getClassName().substring(
                script.getClassName().lastIndexOf(".") + 1);
        if(!fName.equals(clsName)){
            throw new IllegalArgumentException("error : not equal FileName :["
                    + fName + "] and ClassName : [" + clsName + "]");
        }
        SimpleCompiler compiler = new SimpleCompiler();
        JavaSourceClassLoader javaSourceLoader = new JavaSourceClassLoader(
                getClass().getClassLoader(),new File[]{new File(
                        script.getScriptDIR())},script.getScriptEncode());
        javaSourceLoader.setDebuggingInfo(true,true,true);

        compiler.setParentClassLoader(javaSourceLoader);
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(
                script.getSourceFile()));
        compiler.cook(bis,script.getScriptEncode());
        bis.close();

        Class cls = compiler.getClassLoader().loadClass(script.getClassName());

        script.setLoadTime(System.currentTimeMillis());
        script.setLastModifyTime(System.currentTimeMillis());
        script.setLoadClass(cls);

        log.debug("compiler time : " + ( System.currentTimeMillis() - a )
                + "ms,script:" + script);
        return script;
    }

    public String findScriptFileByName(String scriptClassName){
        if(scriptClassName.startsWith("script"))
            scriptClassName = scriptClassName.substring(scriptClassName
                    .indexOf("script") + 7);
        String f = scriptClassName.replaceAll("\\.","/");
        f = SpringBase.getScriptDTO().getScriptDIR() + "/" + f + ".java";
        return f;
    }

    /**
     * 从源文件中取包名+类名的完整名称
     * 
     * @param scriptFile
     * @return String
     * @throws FileNotFoundException
     */
    public String getIntegralScriptName(String scriptFile)
            throws FileNotFoundException{
        return getScriptPackage(scriptFile) + "." + getScriptName(scriptFile);
    }

    /**
     * 从源文件中取类名
     * 
     * @param scriptFile
     * @return String
     * @throws FileNotFoundException
     */
    public String getScriptName(String scriptFile) throws FileNotFoundException{
        try{
            String source = FileHelper.getContent(scriptFile);
            if(source.indexOf(JavaClassKeyword.CLASS) > -1){
                source = StringHelper.splitContentByUnicodeWithUntilSpace(
                        source,source.indexOf(JavaClassKeyword.CLASS) + 5);
                return source;
            }
        }catch(IOException e){
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new FileNotFoundException("script not found: " + scriptFile);
        }

        throw new IllegalArgumentException(
                "Error source ,not found keyword [class]");
    }

    /**
     * 获取源文件中的包名
     * 
     * @param scriptFile
     * @return String
     * @throws FileNotFoundException
     */
    public String getScriptPackage(String scriptFile)
            throws FileNotFoundException{

        try{
            String[] source = FileHelper.getLine(scriptFile);
            for(String e : source){
                if(e.startsWith(JavaClassKeyword.PACKAGE)){
                    e = StringHelper.removeSpace(e);
                    e = e.substring(e.indexOf(JavaClassKeyword.PACKAGE) + 7,
                            e.length() - 1);
                    return e;
                }
            }
        }catch(IOException e){
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new FileNotFoundException("script not found: " + scriptFile);
        }

        throw new IllegalArgumentException(
                "Error source ,not found keyword [package]");
    }

    public static void main(String[] args) throws Exception{

    }
}
