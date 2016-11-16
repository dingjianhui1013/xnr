/**
 * 2014年2月2日 下午5:56:37
 */
package com.xnradmin.dto.client;


import java.io.Serializable;

/**
 * @author: bin_liu
 */
public class SyncDTO implements Serializable{
    private String action = "";

    private String jsonSource = "";

    private String sourceIp = "";

    private String imei = "";

    public String getAction(){
        return action;
    }

    public void setAction(String action){
        this.action = action;
    }

    public String getJsonSource(){
        return jsonSource;
    }

    public void setJsonSource(String jsonSource){
        this.jsonSource = jsonSource;
    }

    public String getSourceIp(){
        return sourceIp;
    }

    public void setSourceIp(String sourceIp){
        this.sourceIp = sourceIp;
    }

    public String getImei(){
        return imei;
    }

    public void setImei(String imei){
        this.imei = imei;
    }

}
