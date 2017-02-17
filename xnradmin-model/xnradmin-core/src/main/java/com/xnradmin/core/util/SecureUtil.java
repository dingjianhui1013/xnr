/**
 * 2012-5-14 上午10:54:34
 */
package com.xnradmin.core.util;


import com.cntinker.security.MD5Encoder;
import com.xnradmin.po.CommonStaff;
import com.xnradmin.po.client.ClientUserInfo;

/**
 * @autohr: bin_liu
 */
public class SecureUtil{

    /**
     * 返回用户密码
     * 
     * @param pswd
     * @return String
     */
    public static synchronized String getEncodePswd(CommonStaff user){
        // String str = "sec.hein.com";
        // BASE64Encoder encode = new BASE64Encoder();
        String str = user.getPassword() + "{" + user.getLoginId() + "}";
        return MD5Encoder.encode32(str);
    }

    /**
     * 返回客户端用户密码
     * 
     * @param pswd
     * @return String
     */
    public static synchronized String getClientUserEncodePswd(ClientUserInfo user, String type){
        // String str = "sec.hein.com";
        // BASE64Encoder encode = new BASE64Encoder();
    	String str = "";
    	if(user.getLoginPassWord()!=null && type!=null && type.equalsIgnoreCase("1")){
    		str = user.getLoginPassWord() + "[" + user.getMobile() + "]";
    	}
    	else if(user.getPaymentPassword()!=null && type!=null && type.equalsIgnoreCase("2")){
    		str = user.getPaymentPassword() + "[" + user.getMobile() + "]";
    	}
        return MD5Encoder.encode32(str);
    }

    
    // 应用方式测试
    public static void main(String[] args) throws Exception{
        // log.debug(MD5Encoder.encode32( "1234"+"{"+"test"+"}"));
        // log.debug(MD5Encoder.encode32( "1234"+"{"+"1004"+"}"));
    }
}
