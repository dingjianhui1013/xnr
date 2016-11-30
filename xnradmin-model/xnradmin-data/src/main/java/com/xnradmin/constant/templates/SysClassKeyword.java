/**
 * 2013-7-22 上午2:49:38
 */
package com.xnradmin.constant.templates;


import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;

import com.cntinker.util.StringHelper;
import com.cntinker.uuid.UIDFactory;

/**
 * @author: bin_liu
 */
public class SysClassKeyword{
	private static Logger log = Logger.getLogger(SysClassKeyword.class);
    public static final String JAVA = "java";

    public static final String GEN = "gen";

    public static final String IF = "if";

    public static final String FOR = "for";

    public static final String END = "end";

    public static Set<String> keywordList;

    static{
        keywordList = new HashSet<String>();
        keywordList.add(JAVA);
        keywordList.add(GEN);
        keywordList.add(IF);
        keywordList.add(FOR);
        keywordList.add(END);
    }

    public static boolean isKeywordStart(String start){
        if(keywordList.contains(start))
            return true;
        
        return false;
    }

    public static void main(String[] args) throws Exception{
        String c = "$[for   BuiltInVar.SYS_ALL_PO_VAR var]";
        log.debug(c.substring(2));

        log.debug(isKeywordStart(c));

    }
}
