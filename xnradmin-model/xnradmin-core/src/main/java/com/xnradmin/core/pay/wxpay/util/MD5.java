package com.xnradmin.core.pay.wxpay.util;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * User: rizenguo
 * Date: 2014/10/23
 * Time: 15:43
 */
public class MD5 {
    
    public static String MD5Encode(String input) {
      /*  try {
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(input.getBytes());
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            StringBuffer hexString = new StringBuffer();
            // 字节数组转换为 十六进制 数
            for (int i = 0; i < md.length; i++) {
                String shaHex = Integer.toHexString(md[i] & 0xFF);
                if (shaHex.length() < 2) {
                    hexString.append(0);
                }
                hexString.append(shaHex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";*/
    	
    	
    	if (input != null) {
    		try {
    		// 创建具有指定算法名称的信息摘要
    		MessageDigest md = MessageDigest.getInstance("MD5");
    		// 使用指定的字节数组对摘要进行最后的更新，然后完成摘要计算
    		byte[] results = md.digest(input.getBytes("utf-8"));
    		// 将得到的字节数组编程字符窜返回
    		String resultString = bytesToHexString(results);
    		return resultString.toUpperCase();
    		} catch (Exception ex) {
    		ex.printStackTrace();
    		}
    		}
    		return null;
    	
    	
    }
    
    
    
    public static String bytesToHexString(byte[] src){       

        StringBuilder stringBuilder = new StringBuilder();       

        if (src == null || src.length <= 0) {       

            return null;       

        }       

        for (int i = 0; i < src.length; i++) {       

            int v = src[i] & 0xFF;       

            String hv = Integer.toHexString(v);       

            if (hv.length() < 2) {       

                stringBuilder.append(0);       

            }       

            stringBuilder.append(hv);       

        }       

        return stringBuilder.toString();       

    }
    
    
    
    

}
