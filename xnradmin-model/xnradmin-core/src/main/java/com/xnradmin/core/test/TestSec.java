/**
 * 2012-5-29 上午2:44:39
 */
package com.xnradmin.core.test;


import java.io.IOException;
import java.io.UnsupportedEncodingException;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import com.cntinker.security.MD5Encoder;
import com.cntinker.util.StringHelper;
import com.xnradmin.core.util.SecureUtil;
import com.xnradmin.po.CommonStaff;

/**
 * @autohr: bin_liu
 */
public class TestSec{

    private static boolean isValid(String key,String id) throws IOException{

        String udid = "18878fefsdf8aadfcfe";
        String str = udid.concat("_netu");

        BASE64Encoder encode = new BASE64Encoder();
        String encodedPassword = encode.encode(str.getBytes());

        System.out.println("encodedPassword: " + encodedPassword);

        System.out.println(MD5Encoder.encode32(encodedPassword));
        if(id.equals("1")){
            if(MD5Encoder.encode16(encodedPassword).equals(key))
                return true;
        }else if(id.equals("2")){
            if(MD5Encoder.encode32(encodedPassword).equals(key))
                return true;
        }

        return false;
    }

    private static void testLogin(){
        CommonStaff staff = new CommonStaff();
        staff.setLoginId("admin");
        staff.setPassword("1234");
        System.out.println(SecureUtil.getEncodePswd(staff));
        
      
        System.out.println(StringHelper.getFirstDay().substring(0,
                StringHelper.getFirstDay().indexOf(" ")));
    }

    private static String encode(String content)
            throws UnsupportedEncodingException{
        BASE64Encoder encode = new BASE64Encoder();
        return new BASE64Encoder().encode(encode.encode(
                content.getBytes("UTF-8")).getBytes());
    }

    private static String decode(String content)
            throws UnsupportedEncodingException,IOException{
        BASE64Decoder decode = new BASE64Decoder();
        return new String(new BASE64Decoder().decodeBuffer(new String(decode
                .decodeBuffer(content),"UTF-8")));
    }

    public static void main(String[] args) throws Exception{
        // String c = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
        // c += "<ack>";
        // c += "<action>";
        // c += "GET_FEE";
        // c += "</action>";
        // c += "<serviceid>";
        // c += "77889900";
        // c += "</serviceid>";
        // c += "<fee_serviceid>";
        // c += "66778899";
        // c += "</fee_serviceid>";
        // c += "<spid>";
        // c += "97013";
        // c += "</spid>";
        // c += "<cpid>";
        // c += "15";
        // c += "</cpid> ";
        // c += "</ack>";
        //
        // System.out.println(encode(c));
        //
        // String dstr =
        // "UEQ5NGJXd2dkbVZ5YzJsdmJqMGlNUzR3SWlCbGJtTnZaR2x1WnowaVZWUkdMVGdpUHo0OFlXTnJQanhoWTNScGIyNCtSMFZVWDBaRg0KUlR3dllXTjBhVzl1UGp4elpYSjJhV05sYVdRK056YzRPRGs1TURBOEwzTmxjblpwWTJWcFpENDhabVZsWDNObGNuWnBZMlZwWkQ0Mg0KTmpjM09EZzVPVHd2Wm1WbFgzTmxjblpwWTJWcFpENDhjM0JwWkQ0NU56QXhNend2YzNCcFpENDhZM0JwWkQ0eE5Ud3ZZM0JwWkQ0Zw0KUEM5aFkycys=";
        // System.out.println(decode(dstr));

        testLogin();
    }
}
