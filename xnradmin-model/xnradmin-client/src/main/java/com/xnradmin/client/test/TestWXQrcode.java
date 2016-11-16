/**
 *2014年9月16日 下午3:00:03
 */
package com.xnradmin.client.test;

import java.io.IOException;

import com.xnradmin.client.service.wx.WXQRcodeService;
import com.xnradmin.core.util.SpringBase;
import com.xnradmin.po.wx.WXQRcode;

/**
 * @author: liubin
 *
 */
public class TestWXQrcode {

	private static void test() throws IOException {
		WXQRcodeService wxqrcodeService = (WXQRcodeService) SpringBase.getCtx()
				.getBean("WXQRcodeService");
		String wxuserid = "1";
		String sceneid = "2101";
		WXQRcode wxqrcode = wxqrcodeService.genTempQRcode(wxuserid, sceneid);
		System.out.println(wxqrcode);
	}
	
	
	public static void main(String[] args) throws Exception {
		System.out.println("start");
		test();
		System.out.println("end");
	}
}
