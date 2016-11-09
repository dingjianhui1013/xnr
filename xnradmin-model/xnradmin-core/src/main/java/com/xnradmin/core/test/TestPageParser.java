/**
 * 2014年3月15日 上午10:45:43
 */
package com.xnradmin.core.test;


import java.io.FileNotFoundException;
import java.io.IOException;

import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.AndFilter;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.tags.Div;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

import com.cntinker.util.HtmlParse;

/**
 * @author: bin_liu
 */
public class TestPageParser{

    private static void test() throws FileNotFoundException,ParserException,
            IOException{
        String indexUrl = "http://www.autohome.com.cn/a0/";
        HtmlParse f = new HtmlParse(indexUrl,"GBK");        
        NodeFilter nFilter = new AndFilter(new TagNameFilter("div"),
                new HasAttributeFilter("id","tab-content"));
        String str = f.getDivHtml(nFilter);
        System.out.println(str);
        
        Parser p = new Parser(str);
        NodeFilter nF1 = new AndFilter(new TagNameFilter("div"),
                new HasAttributeFilter("class","uibox"));
        System.out.println("------------------------------");

        NodeList nList = (NodeList) p.parse(nF1);
        System.out.println("nList size:"+nList.size());
        for(int i = 0;i < nList.size();i ++ ){
            Div div = (Div) nList.elementAt(i);
            System.out.println(div.toHtml());
            
            Parser pnode = new Parser(div.toHtml());
            NodeFilter pnodeFilter = new AndFilter(new TagNameFilter("div"),
                    new HasAttributeFilter("data","PY_C"));
            NodeList nodeList = (NodeList) pnode.parse(pnodeFilter);
            
            System.out.println("nodeList size:"+nodeList);
            
            System.out.println("------------ node ----------");
        }
    }

    public static void main(String[] args) throws Exception{
        test();
    }
}
