/**
 * 2012-4-23 上午12:42:24
 */
package com.xnradmin.core.util;


import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.cntinker.util.ConfigHelper;
import com.cntinker.util.FileHelper;
import com.cntinker.util.StringHelper;
import com.xnradmin.core.test.Test;

/**
 * @autohr: bin_liu
 */
public class AjaxUtil{

    private static String resultDIR;

    static{
        ConfigHelper c = new ConfigHelper(new Test());
        // System.out.println("cfgPath:" + c.getCfgPath());
        resultDIR = c.getCfgPath() + "ajaxresult/";
        // System.out.println(resultDIR);
    }

    public static String getTimeout(String msg) throws FileNotFoundException,
            IOException{
        String[] str = FileHelper.getLine(resultDIR + "opearTimeout.ajx");
        StringBuffer sb = new StringBuffer();
        for(String e : str){
            sb.append(e);
        }

        String res = sb.toString();

        if(StringHelper.isNull(msg)){
            res = res.replace("${msg}","会话超时 ");
        }else{
            res = res.replace("${msg}","" + msg + "");
        }

        return res;
    }

    public static String getSuccess(String msg,String callbackType,
            String navTabId,String forwardUrl) throws FileNotFoundException,
            IOException{
        String[] str = FileHelper.getLine(resultDIR + "opearSuccess.ajx");
        StringBuffer sb = new StringBuffer();
        for(String e : str){
            sb.append(e);
        }

        String res = sb.toString();

        if(StringHelper.isNull(msg)){
            res = res.replace("${msg}","操作成功");
        }else{
            res = res.replace("${msg}","" + msg + "");
        }
        if(StringHelper.isNull(navTabId)){
            res = res.replace("${navTabId}","");
        }else{
            res = res.replace("${navTabId}","" + navTabId + "");
        }

        if(StringHelper.isNull(callbackType)){
            res = res.replace("${callbackType}","");
        }else{
            res = res.replace("${callbackType}","" + callbackType + "");
        }

        if(StringHelper.isNull(forwardUrl)){
            res = res.replace("${forwardUrl}","");
        }else{
            res = res.replace("${forwardUrl}","" + forwardUrl + "");
        }

        return res;
    }

    public static String getError(String msg) throws FileNotFoundException,
            IOException{
        String[] str = FileHelper.getLine(resultDIR + "operError.ajx");
        StringBuffer sb = new StringBuffer();
        for(String e : str){
            sb.append(e);
        }

        String res = sb.toString();

        if(StringHelper.isNull(msg)){
            res = res.replace("${msg}","操作失败");
        }else{
            res = res.replace("${msg}","" + msg + "");
        }

        return res;
    }

    public static Object getValueByJsonArrayAjaxRequest(
            String jsonArrayRequest,String parameter) throws JSONException{

        JSONArray array = new JSONArray(jsonArrayRequest);
        for(int i = 0;i < array.length();i ++ ){

            JSONObject obj = array.getJSONObject(i);

            if(parameter.equals(obj.get("name"))){
                return obj.get("value");
            }
        }

        return "";
    }

    public static void main(String[] args) throws Exception{
        System.out.println(AjaxUtil.getSuccess(null,"closeCurrent",null,null));

        System.out.println(AjaxUtil.getError(null));
        String jsonString = "[{\"name\":\"depname\",\"value\":\"高级管理部门\"},{\"name\":\"org1.pdid\",\"value\":\"\"},{\"name\":\"org1.pdname\",\"value\":\"\"},{\"name\":\"level\",\"value\":\"0\"}]";

        System.out.println(AjaxUtil.getValueByJsonArrayAjaxRequest(jsonString,
                "depname"));
    }
}
