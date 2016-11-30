/**
 *2014年2月2日 下午5:22:06
*/
package com.xnradmin.core.test;

import org.apache.log4j.Logger;

import com.cntinker.util.ConfigHelper;
import com.xnradmin.core.util.SpringBase;

/**
 * @author: bin_liu
 *
 */
public class TestConfig{
	private static Logger log = Logger.getLogger(TestConfig.class);
    public static void main(String[] args) throws Exception{
        //ConfigHelper c = new ConfigHelper(Test.class);
        String s = SpringBase.getCfg().getValueByName("server.properties","http.disable.dir");
        log.debug("s: "+s);
        
        String db = SpringBase.getCfg().getValueByName("server.properties","mysql.master.jdbc.driverClassName");
        log.debug("db: "+db);
    }
}
