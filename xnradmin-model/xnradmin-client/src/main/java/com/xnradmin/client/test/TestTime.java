/**
 *2014年8月22日 下午2:21:46
 */
package com.xnradmin.client.test;

import com.cntinker.util.StringHelper;

/**
 * @author: liubin
 * 
 */
public class TestTime {

	public static void main(String[] args) throws Exception {
		Long e1 = StringHelper.getTime("2014-08-22 14:21:00");
		Long e2 = System.currentTimeMillis();
		Long res = e2-e1;
		Integer max = 72;
		System.out.println(e2 - e1);
		System.out.println((e2 - e1) / 1000);
		System.out.println(((e2 - e1) / 1000)>15);
		System.out.println((res / 1000)>max);
	}
}
