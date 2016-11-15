/**
 * 2013年9月17日 下午5:53:31
 */
package com.xnradmin.dto;


import java.io.Serializable;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.cntinker.util.ReflectHelper;

/**
 * @autohr: bin_liu
 */
@Repository("ScriptDTO")
public class ScriptDTO implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @Value(value = "${platform.script.dir}")
    private String scriptDIR;

    @Value(value = "${platform.script.encode}")
    private String scriptEncode;

    @Value(value = "${platform.script.methods}")
    private String[] scriptMethods;
    
    private String className;
    
    private String sourceFile;
    
    private Boolean laoded;
    
    private Long loadTime = -1L;
    
    private Long lastModifyTime = -1L;
    
    private Class loadClass;

    public boolean methodExist(String methodName){
        for(String e : this.scriptMethods){
            if(methodName.equals(e))
                return true;
        }
        return false;
    }

    public boolean methodStartWith(String methodName){
        for(String e : this.scriptMethods){
            if(methodName.startsWith(e))
                return true;
        }
        return false;
    }

    public String toString(){
        String res = "";
        try{
            res = ReflectHelper.makeToString(this);
        }catch(Exception e){
            e.printStackTrace();
        }
        return res;
    }

    public String getScriptDIR(){
        return scriptDIR;
    }

    public void setScriptDIR(String scriptDIR){
        this.scriptDIR = scriptDIR;
    }

    public String getScriptEncode(){
        return scriptEncode;
    }

    public void setScriptEncode(String scriptEncode){
        this.scriptEncode = scriptEncode;
    }

    public String[] getScriptMethods(){
        return scriptMethods;
    }

    public void setScriptMethods(String[] scriptMethods){
        this.scriptMethods = scriptMethods;
    }

    public String getClassName(){
        return className;
    }

    public void setClassName(String className){
        this.className = className;
    }

    public String getSourceFile(){
        return sourceFile;
    }

    public void setSourceFile(String sourceFile){
        this.sourceFile = sourceFile;
    }

    public Boolean getLaoded(){
        return laoded;
    }

    public void setLaoded(Boolean laoded){
        this.laoded = laoded;
    }

    public Long getLoadTime(){
        return loadTime;
    }

    public void setLoadTime(Long loadTime){
        this.loadTime = loadTime;
    }

    public Long getLastModifyTime(){
        return lastModifyTime;
    }

    public void setLastModifyTime(Long lastModifyTime){
        this.lastModifyTime = lastModifyTime;
    }

    public Class getLoadClass(){
        return loadClass;
    }

    public void setLoadClass(Class loadClass){
        this.loadClass = loadClass;
    }

}
