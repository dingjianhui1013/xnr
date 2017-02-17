/**
 * 2013年9月8日 下午3:11:28
 */
package com.xnradmin.core.util;


import java.io.UnsupportedEncodingException;
import java.util.Arrays;

import org.apache.log4j.Logger;

/**
 * @autohr: bin_liu
 */
public class EncodeDecodeUtil{
	private static Logger log = Logger.getLogger(EncodeDecodeUtil.class);
    public static String encode(String content){
        byte[] bsrc = null;
        try{
            bsrc = content.getBytes("UTF-8");
        }catch(UnsupportedEncodingException e){
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        String dest = "",str;
        byte bb;
        int num;
        if(bsrc == null)
            return "";
        for(int ii = 0;ii < bsrc.length;ii ++ ){
            bb = bsrc[ii];
            if(bb >= 0)
                num = bb;
            else
                num = ( bb & 0x7F ) + ( 1 << 7 );
            str = Integer.toHexString(num);
            if(str.length() < 2)
                str = "0" + str;
            dest += str.toUpperCase();
        }
        return dest;
    }

    public static String decode(String src){
        if(src.length() < 2)
            return null;
        byte[] dest = new byte[src.length() / 2];
        byte rb;
        String str;
        Arrays.fill(dest,(byte) 0);
        int index = 0;
        for(int ii = 0;ii < src.length() - 1;ii ++ ){
            str = "#" + src.substring(ii,ii + 2);
            rb = (byte) Integer.decode(str).intValue();
            dest[index ++ ] = rb;
            ii ++ ;
        }
        String res = null;
        try{
            res = new String(dest,"UTF-8");
        }catch(UnsupportedEncodingException e){
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return res;
    }

    public static void main(String[] args) throws Exception{
        String content = "{table:phone_guangdong,table:phone_beijing,table:phone_hebei}";
        log.debug(decode("7B7461626C653A70686F6E655F6775616E67646F6E677D"));
        log.debug(encode(content));
    }
}
