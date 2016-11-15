/**
 *2014年10月10日 下午2:40:26
*/
package com.xnradmin.core.test;

import com.cntinker.util.StringHelper;

/**
 * @author: liubin
 *
 */
public class TestMobile {

	public static void main(String[] args) throws Exception {
		System.out.println(StringHelper.isPhone("13811968624"));
		System.out.println(StringHelper.isPhone("18688784906"));
		System.out.println(StringHelper.isPhone("18910639965"));
	}
}
