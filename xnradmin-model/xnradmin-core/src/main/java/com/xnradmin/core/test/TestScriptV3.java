/**
 * 2014年5月11日 上午10:43:12
 */
package com.xnradmin.core.test;


import java.io.IOException;

import org.codehaus.commons.compiler.CompileException;
import org.json.JSONException;
import org.json.JSONObject;

import com.xnradmin.core.util.ScriptHelper;
import com.xnradmin.core.util.SpringBase;
import com.xnradmin.dto.ScriptDTO;

/**
 * @author: bin_liu
 */
public class TestScriptV3{

    private static void testUserReg() throws JSONException,CompileException,
            ClassNotFoundException,IOException{
        
        String content = "{\"action\":\"weather\",\"city\":\"北京\",\"day\":\"0\"}";
        JSONObject o = new JSONObject(content);

        // ------如果想设断点测试脚本，则在此下注释掉脚本运行，转去直接运行templates下的类
        ScriptHelper scriptHelper = (ScriptHelper) SpringBase.getCtx().getBean(
                "ScriptHelper");
        ScriptDTO dto = scriptHelper
                .find("com.xnradmin.script.business.ScriptWeather");
        Object out = scriptHelper.executeMethod("execute",dto,o);

        // ----------templates类运行测试
        // ScriptUserReg u = new ScriptUserReg();
        // Object out =u.execute(o);
        System.out.println("out: " + out);
    }

    public static void main(String[] args) throws Exception{
        testUserReg();
        System.out.println("");
    }
}
