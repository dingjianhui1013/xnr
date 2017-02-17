/**
 *2014年10月10日 下午2:40:26
*/
package com.xnradmin.core.test;

import org.apache.log4j.Logger;

import com.cntinker.util.StringHelper;
import com.xnradmin.core.util.SpringBase;

/**
 * @author: liubin
 *
 */
public class TestMobile {
	private static Logger log = Logger.getLogger(TestMobile.class);
	public static void main(String[] args) throws Exception {
		log.debug(StringHelper.isPhone("13811968624"));
		log.debug(StringHelper.isPhone("18688784906"));
		log.debug(StringHelper.isPhone("18910639965"));
	}
}
