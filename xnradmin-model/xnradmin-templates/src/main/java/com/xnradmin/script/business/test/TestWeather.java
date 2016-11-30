/**
 * 2014年5月11日 下午1:40:30
 */
package com.xnradmin.script.business.test;


import java.io.IOException;
import java.net.MalformedURLException;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.xml.sax.SAXException;

import com.cntinker.util.HttpunitHelper;
import com.meterware.httpunit.WebConversation;
import com.meterware.httpunit.WebResponse;

/**
 * @author: bin_liu
 */
public class TestWeather{
	private static Logger log = Logger.getLogger(TestWeather.class);
    private static void test() throws MalformedURLException,IOException,
            SAXException,JSONException{
        String url = "http://m.weather.com.cn/atad/101010100.html";
        HttpunitHelper webLogin = new HttpunitHelper();
        WebConversation wc = webLogin.init("",true);

        WebResponse r = wc.getResponse(url);
        log.debug(r.getText());
        JSONObject obj = new JSONObject(r.getText());
        log.debug("obj:" + obj);
        
        JSONObject weatherinfo = (JSONObject)obj.get("weatherinfo");
        log.debug(weatherinfo.get("city"));
    }

    public static void main(String[] args) throws Exception{
        test();
        log.debug("");
    }
}
