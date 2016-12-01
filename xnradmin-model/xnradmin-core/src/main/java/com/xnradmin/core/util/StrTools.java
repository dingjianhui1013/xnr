/**
 * 2013-8-4 下午2:17:07
 */
package com.xnradmin.core.util;

import org.apache.log4j.Logger;


/**
 * @autohr: bin_liu
 */
public class StrTools{
	private static Logger log = Logger.getLogger(StrTools.class);
    public static int getStrLength(String str){
        try{
            if(str == null || str.trim().length() == 0){
                return 0;
            }
            int iLeng = str.getBytes().length;
            return iLeng;
        }catch(Throwable e){
            return 50000;
        }
    }

    public static boolean isInteger(String str){
        try{
            Integer.parseInt(str);
            return true;
        }catch(Exception e){
            return false;
        }

    }

    public static boolean isNumeric(String str){
        if(str == null){
            return false;
        }
        int dom = 0;
        for(int i = 0;i < str.length();i ++ ){
            if(str.charAt(i) != '.'){
                if(i == 0 && str.substring(0,1).equalsIgnoreCase("-")){
                    continue;
                }
                if(str.charAt(i) > '9' || str.charAt(i) < '0'){
                    return false;
                }

            }else{
                dom ++ ;
                if(dom > 1){
                    return false;
                }
            }
        }
        return true;
    }

    public static String replaceStr(String str,String oldStr,String newStr){
        if(str == null || newStr == null || oldStr == null
                || oldStr.trim().length() == 0){
            return str;
        }
        int iE = str.indexOf(oldStr);
        int iB = 0;
        String temp = "";
        while(iE != -1){
            temp += str.substring(iB,iE) + newStr;
            iB = iE + oldStr.length();
            iE = str.indexOf(oldStr,iB);
        }
        if(iB < str.length()){
            temp += str.substring(iB);
        }
        return temp;
    }

    public static String setScale(String str,int scale){
        if(str == null || str.trim().length() == 0){
            return "";
        }
        try{
            java.math.BigDecimal bd = new java.math.BigDecimal(str);
            java.math.BigDecimal bd1 = bd.setScale(scale,bd.ROUND_HALF_DOWN);
            return bd1.toString();
        }catch(Exception e){
        	log.debug("e =============" + str + " " + scale);
            e.printStackTrace();
            log.debug("e =============" + e.getMessage());
            return "0";
        }
    }

    public static float str2float(String str){
        if(str == null){
            return 0;
        }
        try{
            return Float.valueOf(str).floatValue();
        }catch(Exception e){
            return 0;
        }
    }

    public static int str2int(String str){
        if(str == null){
            return 0;
        }
        try{
            return Integer.parseInt(str);
        }catch(Exception e){
            return 0;
        }
    }

    public static String Unicode2GB(Object obj){
        try{
            if(obj == null){
                return null;
            }
            if(obj.getClass() != String.class){
                return obj + "";
            }
            String str2 = new String(obj.toString().getBytes("gb2312"),"8859_1");
            return str2;
        }catch(Throwable e){
            return null;
        }
    }

    public static String GB2Unicode(String str){

        try{
            if(str == null){
                return null;
            }

            // if(mycrm.system.iServerType == mycrm.system.TOMCAT)
            // {

            String str2 = new String(str.getBytes("iso8859-1"));

            return str2;
            // }
            // return str;

        }catch(Throwable e){
            return null;
        }
    }

    public static int getRandom(int iLength){
        java.util.Random rd = new java.util.Random();
        int ran = 0;

        int iMax = 1;
        while(iLength > 1){
            iMax = iMax * 10;
            iLength -- ;
        }
        int iRan = iMax * 10 - 1;
        log.debug("" + iMax);
        log.debug("" + iRan);

        while(ran < iMax){
            ran = rd.nextInt(iRan);
        }
        return ran;
    }

    public static String toSpace(String str,int iLength){
        while(str.getBytes().length < iLength){
            str = str + " ";
        }
        return str;
    }

    public static String defaultToGBK(String input)
            throws java.io.UnsupportedEncodingException{
        return new String(input.getBytes(),"GBK");
    }

    public static String parseMsgID(byte[] b){
        if(b.length != 8)
            return new String(b);
        String s = "";
        String temp = "";

        for(int i = 0;i < b.length;i ++ ){
            if(b[i] > 0){
                String ff = Integer.toBinaryString(b[i]);
                while(ff.length() < 8){
                    ff = 0 + ff;
                }
                temp = temp + ff;
            }else{
                String ff = Integer.toBinaryString(256 + b[i]);
                while(ff.length() < 8){
                    ff = 0 + ff;
                }
                temp = temp + ff;
            }
        }
        if(Integer.parseInt(temp.substring(0,4),2) < 10)
            s = s + 0;
        s = s + Integer.parseInt(temp.substring(0,4),2);
        if(Integer.parseInt(temp.substring(4,9),2) < 10)
            s = s + 0;
        s = s + Integer.parseInt(temp.substring(4,9),2);
        if(Integer.parseInt(temp.substring(9,14),2) < 10)
            s = s + 0;
        s = s + Integer.parseInt(temp.substring(9,14),2);
        if(Integer.parseInt(temp.substring(14,20),2) < 10)
            s = s + 0;
        s = s + Integer.parseInt(temp.substring(14,20),2);
        if(Integer.parseInt(temp.substring(20,26),2) < 10)
            s = s + 0;
        s = s + Integer.parseInt(temp.substring(20,26),2);
        s = s + Integer.parseInt(temp.substring(26,48),2);
        String kk = Integer.toString(Integer.parseInt(temp.substring(48),2));
        while(kk.length() < 6)
            kk = 0 + kk;
        s = s + kk;
        return s;
    }
}
