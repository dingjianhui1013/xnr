/**
 *2014年9月11日 下午4:23:20
 */
package com.xnradmin.client.service.wx;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xnradmin.core.pay.wxpay.util.Sha1Util;
import com.xnradmin.core.pay.wxpay.xnrutil.SDKRuntimeException;
import com.xnradmin.core.util.Log4jUtil;
import com.xnradmin.vo.client.wx.WXPayInfoVO;
import com.xnradmin.vo.client.wx.WXUserVO;

/**
 * @author: liubin
 *
 */
@Service("SendNotifyService")
public class SendNotifyService {

	static Log log = LogFactory.getLog(SendNotifyService.class);

	private static Log wxlog = Log4jUtil.getLog("wxpay");

	@Autowired
	private WXPayInfoService wxpayInfoService;

	public String getSignOut(WXPayInfoVO vo) throws SDKRuntimeException {
		if (vo.getWxpayInfo() == null
				|| vo.getWxpayInfo().getWxuserid() == null)
			return null;
		WXUserVO wxuservo = vo.getWxuserVO();
		if (wxuservo == null) {
			vo = wxpayInfoService.findByWxUserid(vo.getWxpayInfo()
					.getWxuserid());
			if (vo == null || vo.getWxpayInfo() == null)
				return null;
		}
		return getSign(vo);
	}

	private static String getSign(WXPayInfoVO vo) throws SDKRuntimeException {
		String app_id = vo.getWxuserVO().getWxuser().getAppid();
		String app_key = vo.getWxpayInfo().getAppKey();
		String nonce_str = vo.getOldNonceStr();
		String package_string = vo.getOldPackageString();
		String time_stamp = vo.getOldTimestamp();
		Map<String, String> o = new HashMap<String, String>();
		o.put("appid", app_id);
		o.put("appkey", app_key);
		o.put("openid", vo.getOpenuid());
		o.put("transid", vo.getTransid());
		o.put("out_trade_no", vo.getOutTradeNo());
		o.put("deliver_timestamp", time_stamp);
		o.put("deliver_status", vo.getDeliver_status());
		o.put("deliver_msg", vo.getDeliver_msg());

		String res = WXPayInfoService.formatBizQueryParaMap(o, false);
		wxlog.debug("res:" + res);
		res = Sha1Util.getSha1(res);
		return res;
	}

	public static void main(String[] args) throws Exception {
		System.out.println("");
	}
}
