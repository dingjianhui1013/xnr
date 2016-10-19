/**
 *2014年9月12日 下午7:06:36
 */
package com.xnradmin.core.test;

import com.cntinker.util.StringHelper;

/**
 * @author: liubin
 *
 */
public class TestString {

	private static void testSplit() {
		String str = "<p><span style=\"font-size:12px;\">的简历附件里的世界覅覅</span></p><p><span style=\"font-size:12px;\">法式风味和<br /></span></p>";
		System.out.println(StringHelper.spiltStr(str, "<span style=", "\">"));

		int start = 0;
		int end = 0;

		String temp = str;
		start = str.indexOf("<p>") + 3;
		end = str.indexOf("</p>");

		int flag = 0;
		StringBuffer sb = new StringBuffer();
		while (temp.indexOf("<p>") > -1 || temp.indexOf("</p>") > -1) {

			if (flag >= str.length())
				break;

			System.out.println("in : " + temp);
			temp = getContent(temp);
			sb.append(temp);
			System.out.println(temp.length());
			System.out.println("getContent : " + temp);
			temp = str.substring(temp.length() + 7);

			flag += temp.length() + 7;
			System.out.println("flag : " + flag + " | len : " + str.length());

			System.out.println("2:" + str.substring(temp.length()));
		}

		System.out.println(StringHelper.removeSpace("<br /  >"));
		System.out.println("final content : "
				+ StringHelper.removeSpace(sb.toString()).replaceAll("<br/>",
						""));

	}

	private static String getContent(String c) {
		int start = 0;
		int end = 0;
		start = c.indexOf("<p>") + 3;
		end = c.indexOf("</p>");
		String temp = c;
		temp = temp.substring(start, end);
		return temp;
	}

	public static void main(String[] args) throws Exception {
		String total = "0.01".trim().replaceAll("\\.", "");
		Integer t = new Integer(total);
		System.out.println(t);
		testSplit();
	}
}
