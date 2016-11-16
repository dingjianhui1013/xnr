/**
 * 2013年10月7日 下午4:06:18
 */
package com.xnradmin.constant.templates;


import java.util.HashSet;
import java.util.Set;

/**
 * @author: bin_liu
 */
public class ProjectKeyword{
    public static final String PROJECT = "project";

    public static final String DESC = "desc";

    public static Set<String> keywordList;

    static{
        keywordList = new HashSet<String>();
        keywordList.add(PROJECT);
        keywordList.add(DESC);

    }

    public static boolean isKeywordStart(String start){
        if(keywordList.contains(start))
            return true;

        return false;
    }
}
