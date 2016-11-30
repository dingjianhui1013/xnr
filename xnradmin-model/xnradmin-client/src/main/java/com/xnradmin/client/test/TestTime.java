/**
 *2014年8月22日 下午2:21:46
 */
package com.xnradmin.client.test;

import org.apache.log4j.Logger;

import com.cntinker.util.StringHelper;
import com.xnradmin.client.action.wx.WXConnectAction;

/**
 * @author: liubin
 * 
 */
public class TestTime {
	private static Logger log = Logger.getLogger(TestTime.class);
	public static void main(String[] args) throws Exception {
		Long e1 = StringHelper.getTime("2014-08-22 14:21:00");
		Long e2 = System.currentTimeMillis();
		Long res = e2-e1;
		Integer max = 72;
		log.debug(e2 - e1);
		log.debug((e2 - e1) / 1000);
		log.debug(((e2 - e1) / 1000)>15);
		log.debug((res / 1000)>max);
	}
}
