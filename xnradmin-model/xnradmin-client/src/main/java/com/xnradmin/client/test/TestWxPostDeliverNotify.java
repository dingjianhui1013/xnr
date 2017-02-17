/**
 *2014年8月30日 下午2:03:00
 */
package com.xnradmin.client.test;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.log4j.Logger;
import org.dom4j.DocumentException;
import org.json.JSONException;
import org.json.JSONObject;

import com.cntinker.util.HttpHelper;
import com.cntinker.util.SHA1;
import com.cntinker.util.StringHelper;
import com.xnradmin.client.service.wx.SendNotifyService;
import com.xnradmin.client.service.wx.WXAccessTokenService;
import com.xnradmin.client.service.wx.WXPayInfoService;
import com.xnradmin.core.pay.wxpay.xnrutil.SDKRuntimeException;
import com.xnradmin.core.util.SpringBase;
import com.xnradmin.core.util.WxUtil;
import com.xnradmin.po.wx.WXAccessToken;
import com.xnradmin.vo.client.wx.WXPayInfoVO;

/**
 * @author: liubin
 * 
 */
public class TestWxPostDeliverNotify {
	private static Logger log = Logger.getLogger(TestWxPostDeliverNotify.class);
	private static void sendPost() throws JSONException, IOException {
		String appid = "wxaeabc3bd7fd54699";
		String openid = "ohmUCjzaoZzX6gTX3CxDO2B-LfE4";
		String appkey = "zzybkXgAi2InoZX49yKFhBIn3vBUixHFLjztqOjkM5i0rJzQ2y9fYPAVpPAKXbrNcsn55vfBXE5J51tZ12stVpW5WAw4vaE6QNkkiGYyXM5xwzJOXvhXpbsb3A5vRsE5";
		String transid = StringHelper.getRandomStr(16);
		String out_trade_no = StringHelper.getRandomStr(11);
		String deliver_timestamp = StringHelper.toUnixTimestamp(
				System.currentTimeMillis()).toString();
		String deliver_status = "1";
		String deliver_msg = "ok";
		Map<String, String> map = new HashMap<String, String>();
		map.put("out_trade_no", out_trade_no);
		map.put("transid", transid);
		map.put("appid", appid);
		map.put("appkey", appkey);
		map.put("openid", openid);
		map.put("deliver_timestamp", deliver_timestamp);
		map.put("deliver_status", deliver_status);
		map.put("deliver_status", deliver_status);

		String app_signature = getSignature(map);

		JSONObject obj = new JSONObject();
		obj.put("appid", appid);
		obj.put("openid", openid);
		obj.put("transid", transid);
		obj.put("out_trade_no", out_trade_no);
		obj.put("deliver_timestamp", deliver_timestamp);
		obj.put("deliver_status", deliver_status);
		obj.put("deliver_msg", deliver_msg);
		obj.put("app_signature", app_signature);
		obj.put("sign_method", "sha1");

		log.debug("json: " + obj.toString());

		String url = "https://api.weixin.qq.com/pay/delivernotify?access_token=";
		String accessToken = "di8wmtSA7t0dNZHvR_2ztfhqf254J4_HRgc--7RJuKX2f2rl4YBUrzMoeAi6XSIErbEcn1C-68MbcyeirUncjg";
		url += accessToken;
		log.debug("url:" + url);
		String res = HttpHelper.postXml(url, obj.toString());
		log.debug(res);

	}

	private static String getSignature(Map<String, String> map) {
		String[] str = new String[map.size()];
		boolean isAnd = false;

		int key = 0;
		Iterator<String> it = map.keySet().iterator();
		while (it.hasNext()) {
			str[key] = it.next();
			key++;
		}
		Arrays.sort(str); // 字典序排序
		StringBuffer bigStr = new StringBuffer();
		for (int i = 0; i < str.length; i++) {
			if (isAnd)
				bigStr.append("&");
			bigStr.append(str[i]).append("=")
					.append(map.get(str[i]).toString());
			isAnd = true;
		}
		log.debug("str: " + bigStr.toString().toLowerCase());
		// SHA1加密
		String digest = new SHA1().getDigestOfString(
				bigStr.toString().getBytes()).toLowerCase();

		return digest;
	}

	private static void testSend1() throws IllegalAccessException,
			DocumentException, IOException, SDKRuntimeException {
		String source = "<xml><OpenId><![CDATA[ohmUCj1104_W4N7h3c1EcJ3Qlsf0]]></OpenId><AppId><![CDATA[wxaeabc3bd7fd54699]]></AppId><IsSubscribe>1</IsSubscribe><TimeStamp>1410429584</TimeStamp><NonceStr><![CDATA[EWQqefiDUYppJXDu]]></NonceStr><AppSignature><![CDATA[a65fd437ad5e8d304d5e02967a9587378f08f797]]></AppSignature><SignMethod><![CDATA[sha1]]></SignMethod></xml>";
		WXPayInfoService payService = (WXPayInfoService) SpringBase.getCtx()
				.getBean("WXPayInfoService");
		SendNotifyService notifyService = (SendNotifyService) SpringBase
				.getCtx().getBean("SendNotifyService");
		WXPayInfoVO v = payService.findByWxUserid(1l);
		

		String tokenId = "K0XPtkStmZvJysoR3xp4JjT34EsA-btw4Jvro_1d4lmcz994mmCV_LjlJYv4rh9w8uwudc9JUIZLSMY_g2iQGQ";

		String notifyUrl = "https://api.weixin.qq.com/pay/delivernotify?access_token="
				+ tokenId;

		WxUtil wx = new WxUtil(source);

		String appid = wx.getDataFromKey("AppId");
		String userOpenid = wx.getDataFromKey("OpenId");
		String transaction_id = "1220311401201409113181785836";
		String out_trade_no = "201409111758395510";
		String curTime = StringHelper.toUnixTimestamp(
				System.currentTimeMillis()).toString();

		v.setDeliver_msg("ok");
		v.setDeliver_status("1");
		v.setOpenuid(userOpenid);
		v.setTransid(transaction_id);
		v.setOutTradeNo(out_trade_no);
		v.setOldTimestamp(curTime);

		String sign = notifyService.getSignOut(v);

		log.debug("sign:" + sign);

		StringBuffer o = new StringBuffer();
		// 公众平台账户的AppId；
		o.append("{");
		o.append("\"appid\":\"").append(appid).append("\"");
		// 贩买用户的OpenId，这个已经放在最终支付结果通知的PostData里了；
		o.append(",\"openid\":\"").append(userOpenid).append("\"");
		// 交易单号；
		o.append(",\"transid\":\"").append(transaction_id).append("\"");
		// 第三方订单号；
		o.append(",\"out_trade_no\":\"").append(out_trade_no).append("\"");
		// 发货时间戳，这里指的是Linux时间戳；
		o.append(",\"deliver_timestamp\":\"").append(curTime).append("\"");
		// 发货状态，1表明成功，0表明失败，失败时需要在deliver_msg填上失败原因；
		o.append(",\"deliver_status\":\"").append("1").append("\"");
		// 发货状态信息，失败时可以填上UTF8编码的错诨提示信息，比如“该商品已退款”；
		o.append(",\"deliver_msg\":\"").append("ok").append("\"");
		// 根据支付签名（paySign）生成方法中所讲的签名方式生成的，参加签名字段为：appid、appkey、openid、transid、out_trade_no、deliver_timestamp、deliver_status、deliver_msg；
		o.append(",\"app_signature\":\"").append(sign).append("\"");
		// 签名方法（不计入签名生成）；
		o.append(",\"sign_method\":\"").append("sha1").append("\"");
		o.append("}");

		log.debug("notifyUrl:" + notifyUrl);
		log.debug("body:" + o.toString());
		String res = HttpHelper.postXml(notifyUrl, o.toString(), "UTF-8");
		log.debug("sign res: " + res);
	}

	public static void main(String[] args) throws Exception {
		testSend1(); 
	}
}
