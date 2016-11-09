/**
 * 2013-7-22 上午3:59:28
 */
package com.xnradmin.constant.templates;


import java.util.HashSet;
import java.util.Set;

import com.cntinker.util.StringHelper;

/**
 * @autohr: bin_liu
 */
public class SysGenKeyword{

    public static final String SYSTIME = "systime";

    public static final String SETGET = "setget";

    public static Set<String> keywordList;

    static{
        keywordList = new HashSet<String>();
        keywordList.add(SYSTIME);
        keywordList.add(SETGET);
    }

    public static boolean isKeywordStart(String key){
        if(keywordList.contains(key))
            return true;

        return false;
    }

    public static void main(String[] args) throws Exception{
        System.out.println("");
    }
}
