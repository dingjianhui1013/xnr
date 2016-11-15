/**
 *2014年8月30日 下午5:11:51
 */
package com.xnradmin.client.test;

import java.io.IOException;
import java.net.MalformedURLException;

import org.json.JSONException;

import com.xnradmin.client.service.wx.WXMenuService;
import com.xnradmin.core.dao.CommonDAO;
import com.xnradmin.core.util.SpringBase;

/**
 * @author: liubin
 * 
 */
public class TestMenu {

	private static void testOut() throws MalformedURLException, JSONException,
			IOException {
		WXMenuService service = (WXMenuService) SpringBase.getCtx().getBean(
				"WXMenuService");
		String res = service.reload("1");
		System.out.println(res);
	}

	public static void main(String[] args) throws Exception {
		testOut();
	}
}
