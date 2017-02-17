/**
 * 2014年3月15日 上午10:45:43
 */
package com.xnradmin.core.test;


import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.AndFilter;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.tags.Div;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

import com.cntinker.util.HtmlParse;
import com.xnradmin.po.system.SysMavenPom;

/**
 * @author: bin_liu
 */
public class TestPageParser{
	private static Logger log = Logger.getLogger(TestPageParser.class);
    private static void test() throws FileNotFoundException,ParserException,
            IOException{
        String indexUrl = "http://www.autohome.com.cn/a0/";
        HtmlParse f = new HtmlParse(indexUrl,"GBK");        
        NodeFilter nFilter = new AndFilter(new TagNameFilter("div"),
                new HasAttributeFilter("id","tab-content"));
        String str = f.getDivHtml(nFilter);
        log.debug(str);
        
        Parser p = new Parser(str);
        NodeFilter nF1 = new AndFilter(new TagNameFilter("div"),
                new HasAttributeFilter("class","uibox"));
        log.debug("------------------------------");

        NodeList nList = (NodeList) p.parse(nF1);
        log.debug("nList size:"+nList.size());
        for(int i = 0;i < nList.size();i ++ ){
            Div div = (Div) nList.elementAt(i);
            log.debug(div.toHtml());
            
            Parser pnode = new Parser(div.toHtml());
            NodeFilter pnodeFilter = new AndFilter(new TagNameFilter("div"),
                    new HasAttributeFilter("data","PY_C"));
            NodeList nodeList = (NodeList) pnode.parse(pnodeFilter);
            
            log.debug("nodeList size:"+nodeList);
            
            log.debug("------------ node ----------");
        }
    }

    public static void main(String[] args) throws Exception{
        test();
    }
}
