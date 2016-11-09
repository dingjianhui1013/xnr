/**
 *2014年1月19日 下午4:29:04
*/
package com.xnradmin.core.test;

import org.apache.commons.logging.Log;

import com.cntinker.util.StringHelper;
import com.xnradmin.core.util.Log4jUtil;

/**
 * @author: bin_liu
 *
 */
public class TestLog4j{
    
    private static void testadd() throws InterruptedException{
        int flag = 0;
        while(true){

            System.out.println(StringHelper.getSystime());
            Thread.currentThread().sleep(1 * 1000);

            if(flag == 25){
                Log4jUtil.getLog("client").error("bbbb");
            }
            Log log = Log4jUtil.getLog("other");
            log.error("测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试");
            System.out.println(flag + " | test..." + Log4jUtil.getLog4jFile());
            flag ++ ;
            
        }
    }
    
    private static void testChangeConfig(){
    	
    }
    
    public static void main(String[] args) throws Exception{
        testadd();
    }
}
