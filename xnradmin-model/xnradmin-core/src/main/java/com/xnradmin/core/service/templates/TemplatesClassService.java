/**
 * 2013-7-22 上午4:09:10
 */
package com.xnradmin.core.service.templates;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Vector;

import com.cntinker.util.FileHelper;
import com.cntinker.util.StringHelper;
import com.xnradmin.constant.templates.SysClassKeyword;
import com.xnradmin.dto.TemplateClass;
import com.xnradmin.po.system.SysClass;

/**
 * @autohr: bin_liu
 */
public class TemplatesClassService{

    private String[] tempContent;

    private TemplateClass sysClass;

    public TemplatesClassService(TemplateClass sysClass)
            throws IllegalArgumentException,FileNotFoundException,IOException{
        this.sysClass = sysClass;
        if(sysClass.getSyscls() == null
                || StringHelper.isNull(sysClass.getSyscls().getTempFile()))
            throw new IllegalArgumentException(
                    "class or template file can't be null");
        this.tempContent = FileHelper.getLine(sysClass.getSyscls()
                .getTempFile());
    }

    private boolean hasExe(String line){
        if(line.indexOf("$[") > -1 && line.indexOf("]") > -1){
            return true;
        }
        return false;
    }

    private String getTempCommand(String line){
        line = line.trim();
        String start = line.substring(line.indexOf("$[") + 2,line.indexOf("]"))
                .trim();
        return start;
    }

    /**
     * 得到根据模板转换后的源文件
     * 
     * @return String[]
     */
    public String[] getJavaSource(){
        List result = new Vector();
        for(String e : tempContent){
            String c = "";
            if(hasExe(e)){
                c = processCommand(e);
            }
            result.add(c);
        }
        return (String[]) result.toArray(new String[0]);
    }

    public String processCommand(String line){
        System.out.println(getTempCommand(line));
        String command = getTempCommand(line);
        if(command.equals(SysClassKeyword.GEN)){
            TemplatesGen gen = new TemplatesGen(this,command);
            
        }

        return "";
    }

    public String getStartKeyword(String line){
        String start = line.trim().substring(2).trim();
        start = StringHelper.toUnicode(start);
        String end = "";

        if(start.indexOf("\\u0020") > -1)
            end = start.substring(0,start.indexOf("\\u0020"));
        if(start.indexOf("\\u3000") > -1)
            end = start.substring(0,start.indexOf("\\u3000"));
        end = StringHelper.fromUnicode(end).trim();
        return end;
    }
    
    public static void main(String[] args) throws Exception{
        SysClass cls = new SysClass();
        cls.setPackageName("com.smsplatform.po");
        cls.setClassName("Test");
        cls.setItisPO(1);
        cls.setTempFile("d:/temp/PO.java.tpl");

        TemplateClass c = new TemplateClass();
        c.setSyscls(cls);
        
        TemplatesClassService t = new TemplatesClassService(c);

        t.getJavaSource();
    }

    public String[] getTempContent(){
        return tempContent;
    }

    public void setTempContent(String[] tempContent){
        this.tempContent = tempContent;
    }

    public TemplateClass getTemplateClass(){
        return sysClass;
    }
}
