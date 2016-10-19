/**
 * 2013年9月29日 下午3:17:47
 */
package com.xnradmin.dto;


import java.io.Serializable;

/**
 * @autohr: bin_liu
 */
public class ScriptResultDTO implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private String res;

    private Integer resCode;

    private String jsonSource;

    public String getRes(){
        return res;
    }

    public void setRes(String res){
        this.res = res;
    }

    public Integer getResCode(){
        return resCode;
    }

    public void setResCode(Integer resCode){
        this.resCode = resCode;
    }

    public String getJsonSource(){
        return jsonSource;
    }

    public void setJsonSource(String jsonSource){
        this.jsonSource = jsonSource;
    }

}
