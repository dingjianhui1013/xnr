/**
 * 2013年10月5日 下午4:54:16
 */
package com.xnradmin.constant.templates;


import java.util.HashSet;
import java.util.Set;

/**
 * @autohr: bin_liu
 */
public class JavaClassKeyword{

    public static final String PACKAGE = "package";

    public static final String CLASS = "class";

    public static final String PUBLIC = "if";

    public static final String PRIVATE = "for";

    public static final String PROTECTED = "protected";

    public static final String IMPORT = "Import";

    public static final String STATIC = "static";

    public static Set<String> keywordList;

    static{
        keywordList = new HashSet<String>();
        keywordList.add(PACKAGE);
        keywordList.add(CLASS);
        keywordList.add(PUBLIC);
        keywordList.add(PRIVATE);
        keywordList.add(PROTECTED);        
        keywordList.add(IMPORT);
        keywordList.add(STATIC);
    }

    public static boolean isKeywordStart(String start){
        if(keywordList.contains(start))
            return true;

        return false;
    }

}
