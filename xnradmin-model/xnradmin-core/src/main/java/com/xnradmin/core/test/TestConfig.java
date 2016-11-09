/**
 *2014年2月2日 下午5:22:06
*/
package com.xnradmin.core.test;

import com.cntinker.util.ConfigHelper;
import com.xnradmin.core.util.SpringBase;

/**
 * @author: bin_liu
 *
 */
public class TestConfig{

    public static void main(String[] args) throws Exception{
        //ConfigHelper c = new ConfigHelper(Test.class);
        String s = SpringBase.getCfg().getValueByName("server.properties","http.disable.dir");
        System.out.println("s: "+s);
        
        String db = SpringBase.getCfg().getValueByName("server.properties","mysql.master.jdbc.driverClassName");
        System.out.println("db: "+db);
    }
}
