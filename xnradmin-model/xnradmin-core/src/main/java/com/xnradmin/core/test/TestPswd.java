/**
 *2016年10月19日 下午6:14:50
 */
package com.xnradmin.core.test;

import com.cntinker.security.MD5Encoder;

/**
 * @author: liubin
 *
 */
public class TestPswd {

	public static void main(String[] args) throws Exception {
		String pswd = "1234";
		String login = "admin";
		String s = MD5Encoder.encode32(pswd + "{" + login + "}");
		System.out.println(s);
	}
}
