/**
 * 2014年2月3日 下午3:59:36
 */
package com.xnradmin.vo.client.script;


import java.io.Serializable;

import com.cntinker.util.ReflectHelper;
import com.xnradmin.dto.ScriptDTO;
import com.xnradmin.po.CommonScript;

/**
 * @author: bin_liu
 */
public class ScriptVO implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private String scriptClassName;

    private ScriptDTO scriptdto;

    private CommonScript scriptpo;
    
    public String toString(){
        String res = "";
        try{
            res = ReflectHelper.makeToString(this);
        }catch(Exception e){
            e.printStackTrace();
        }
        return res;
    }

    public ScriptDTO getScriptdto(){
        return scriptdto;
    }

    public void setScriptdto(ScriptDTO scriptdto){
        this.scriptdto = scriptdto;
    }

    public CommonScript getScriptpo(){
        return scriptpo;
    }

    public void setScriptpo(CommonScript scriptpo){
        this.scriptpo = scriptpo;
    }

    public String getScriptClassName(){
        return scriptClassName;
    }

    public void setScriptClassName(String scriptClassName){
        this.scriptClassName = scriptClassName;
    }
}
