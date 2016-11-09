/**
 * 2014年5月11日 下午1:16:17
 */
package com.xnradmin.script.business.test;


import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.wicket.util.crypt.Base64UrlSafe;

/**
 * @author: bin_liu
 */
public class TestSec{
    private static final String HMAC_SHA1 = "HmacSHA1";

    /**
     * 生成签名数据
     * 
     * @param data
     *            待加密的数据
     * @param key
     *            加密使用的key
     * @throws InvalidKeyException
     * @throws NoSuchAlgorithmException
     */
    public static String getSignature(String data,String key) throws Exception{
        byte[] keyBytes = key.getBytes();
        SecretKeySpec signingKey = new SecretKeySpec(keyBytes,HMAC_SHA1);
        Mac mac = Mac.getInstance(HMAC_SHA1);
        mac.init(signingKey);
        byte[] rawHmac = mac.doFinal(data.getBytes());
        StringBuilder sb = new StringBuilder();
        for(byte b : rawHmac){
            sb.append(byteToHexString(b));
        }
        return sb.toString();
    }

    private static String byteToHexString(byte ib){
        char[] Digit = {'0','1','2','3','4','5','6','7','8','9','a','b','c',
                'd','e','f'};
        char[] ob = new char[2];
        ob[0] = Digit[ ( ib >>> 4 ) & 0X0f];
        ob[1] = Digit[ib & 0X0F];
        String s = new String(ob);
        return s;
    }

    private static String appendEqualSign(String s){
        int len = s.length();
        int appendNum = 8 - (int) ( len / 8 );
        for(int n = 0;n < appendNum;n ++ ){
            s += "%3D";
        }
        return s;
    }

    private static void test() throws NoSuchAlgorithmException,
            InvalidKeyException,UnsupportedEncodingException{
        String key = "a";//"http://webapi.weather.com.cn/data/?areaid=101010100&type=forecast&date=201405111123&appid=161063eaaf951c7e";
        String value = "a";//"9fc8fa_SmartWeatherAPI_1c1223d";
        byte[] keyBytes = key.getBytes();
        SecretKeySpec signingKey = new SecretKeySpec(keyBytes,"HmacSHA1");
        Mac mac = Mac.getInstance("HmacSHA1");
        mac.init(signingKey);

        // Compute the hmac on input data bytes
        byte[] rawHmac = mac.doFinal(value.getBytes());

        // Convert raw bytes to Hex
        String hexBytes = byte2hex(rawHmac);
        System.out.println("hexBytes:" + hexBytes);

        String signature;
        signature = new String(Base64UrlSafe.encodeBase64(hexBytes.getBytes()),
                "UTF-8");
        signature = appendEqualSign(signature);
        System.out.print(signature);
    }

    private static String byte2hex(final byte[] b){
        String hs = "";
        String stmp = "";
        for(int n = 0;n < b.length;n ++ ){
            stmp = ( java.lang.Integer.toHexString(b[n] & 0xFF) );
            if(stmp.length() == 1)
                hs = hs + "0" + stmp;
            else
                hs = hs + stmp;
        }
        return hs;
    }

    public static void main(String[] args) throws Exception{
        String key = "a";
        String value = "a";
        test();
    }
}
