/**
 *2014年9月16日 下午3:00:03
 */
package com.xnradmin.client.test;

import java.io.IOException;

import org.apache.log4j.Logger;

import com.xnradmin.client.service.wx.WXQRcodeService;
import com.xnradmin.core.util.SpringBase;
import com.xnradmin.po.wx.WXQRcode;

/**
 * @author: liubin
 *
 */
public class TestWXQrcode {
	private static Logger log = Logger.getLogger(TestWXQrcode.class);
	private static void test() throws IOException {
		WXQRcodeService wxqrcodeService = (WXQRcodeService) SpringBase.getCtx()
				.getBean("WXQRcodeService");
		String wxuserid = "1";
		String sceneid = "2101";
		WXQRcode wxqrcode = wxqrcodeService.genTempQRcode(wxuserid, sceneid);
		log.debug(wxqrcode);
	}
	
	
	public static void main(String[] args) throws Exception {
		log.debug("start");
		test();
		log.debug("end");
	}
}
