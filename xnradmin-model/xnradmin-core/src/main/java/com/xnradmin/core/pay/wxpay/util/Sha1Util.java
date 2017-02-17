package com.xnradmin.core.pay.wxpay.util;

import java.net.URLDecoder;
import java.security.MessageDigest;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.SortedMap;

import org.apache.log4j.Logger;

import com.xnradmin.core.pay.upmp.service.UpmpService;

/*
'============================================================================
'api璇存槑锛�
'createSHA1Sign鍒涘缓绛惧悕SHA1
'getSha1()Sha1绛惧悕
'============================================================================
'*/
public class Sha1Util {
    private static Logger log = Logger.getLogger(Sha1Util.class);
    public static String getNonceStr() {
        Random random = new Random();
        return MD5.MD5Encode(String.valueOf(random.nextInt(10000)));
    }
    public static String getTimeStamp() {
        return String.valueOf(System.currentTimeMillis() / 1000);
    }
    
   //鍒涘缓绛惧悕SHA1
    public static String createSHA1Sign(SortedMap<String, String> signParams) throws Exception {
        StringBuffer sb = new StringBuffer();
        Set es = signParams.entrySet();
        Iterator it = es.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            String k = (String) entry.getKey();
            String v = (String) entry.getValue();
            sb.append(k + "=" + v + "&");
            //瑕侀噰鐢║RLENCODER鐨勫師濮嬪�硷紒
        }
        String params = sb.substring(0, sb.lastIndexOf("&"));
        log.debug("sha1 sb:" + params);
        return getSha1(params);
    }
    //Sha1绛惧悕
    public static String getSha1(String str) {
        if (str == null || str.length() == 0) {
            return null;
        }
        char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'a', 'b', 'c', 'd', 'e', 'f' };

        try {
            MessageDigest mdTemp = MessageDigest.getInstance("SHA1");
            mdTemp.update(str.getBytes("GBK"));

            byte[] md = mdTemp.digest();
            int j = md.length;
            char buf[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                buf[k++] = hexDigits[byte0 >>> 4 & 0xf];
                buf[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(buf);
        } catch (Exception e) {
            return null;
        }
    }
}
