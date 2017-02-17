/**
 *2014年9月11日 下午12:03:22
 */
package com.xnradmin.client.service.wx;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cntinker.util.StringHelper;
import com.xnradmin.core.dao.CommonDAO;
import com.xnradmin.core.pay.wxpay.util.MD5;
import com.xnradmin.core.pay.wxpay.util.Sha1Util;
import com.xnradmin.core.pay.wxpay.xnrutil.SDKRuntimeException;
import com.xnradmin.core.util.Log4jUtil;
import com.xnradmin.core.util.SpringBase;
import com.xnradmin.po.wx.WXPayInfo;
import com.xnradmin.vo.client.wx.WXPayInfoVO;
import com.xnradmin.vo.client.wx.WXUserVO;

/**
 * @author: liubin
 *
 */
@Service("WXPayInfoService")
public class WXPayInfoService {

	static Log log = LogFactory.getLog(WXPayInfoService.class);

	private Log wxlog = Log4jUtil.getLog("wxpay");

	@Autowired
	private CommonDAO dao;
	@Autowired
	private WXUserService wxuserService;

	public String getSignOut(WXPayInfoVO vo) {
		if (vo.getWxpayInfo() == null
				|| vo.getWxpayInfo().getWxuserid() == null)
			return null;
		WXUserVO wxuservo = vo.getWxuserVO();
		if (wxuservo == null) {
			vo = this.findByWxUserid(vo.getWxpayInfo().getWxuserid());
			if (vo == null || vo.getWxpayInfo() == null)
				return null;
		}

		String timestamp = StringHelper.toUnixTimestamp(
				System.currentTimeMillis()).toString();
		String nonceStr = StringHelper.getRandomChar(32);
		vo.setOldTimestamp(timestamp);
		vo.setOldNonceStr(nonceStr);
		vo = getPackage(vo);
		String signType = vo.getSignType();
		String sign = getSign(vo);

		StringBuffer sb = new StringBuffer();
		sb.append("{");
		sb.append("\"appId\":\"")
				.append(vo.getWxuserVO().getWxuser().getAppid()).append("\"");
		sb.append(",\"timeStamp\":\"").append(timestamp).append("\"");
		sb.append(",\"nonceStr\":\"").append(nonceStr).append("\"");
		sb.append(",\"package\":\"").append(vo.getOldPackageString())
				.append("\"");
		sb.append(",\"signType\":\"").append(signType).append("\"");
		sb.append(",\"paySign\":\"").append(sign).append("\"");
		sb.append("}");
		return sb.toString();
	}

	private static WXPayInfoVO getPackage(WXPayInfoVO vo) {

		String banktype = vo.getWxpayInfo().getBankType();
		String productName = vo.getProductName();
		String feeType = vo.getFee_type();
		String inputCharset = vo.getWxpayInfo().getInputCharset();
		String notifyUrl = vo.getWxpayInfo().getNotify_url();
		String outTradeNo = null;
		outTradeNo = StringHelper.isNull(vo.getOutTradeNo()) ? StringHelper
				.getSystime("yyyyMMddHHmmss") + StringHelper.getRandom(5) : vo
				.getOutTradeNo();
		String partner = vo.getWxpayInfo().getPartner();
		String partnerKey = vo.getWxpayInfo().getPartnerKey();
		String ip = vo.getClientIp();
		String totalFee = new Integer(new Integer(vo.getTotalFee())*10).toString();

		// 首先第一步：对原串进行签名，注意这里不要对任何字段进行编码。这里是将参数按照key=value进行字典排序后组成下面的字符串,在这个字符串最后拼接上key=XXXX。由于这里的字段固定，因此只需要按照这个顺序进行排序即可。
		String signString = "bank_type=" + banktype + "&body=" + productName
				+ "&fee_type=" + feeType + "&input_charset=" + inputCharset
				+ "&notify_url=" + notifyUrl + "&out_trade_no=" + outTradeNo
				+ "&partner=" + partner + "&spbill_create_ip=" + ip
				+ "&total_fee=" + totalFee + "&key=" + partnerKey;
		String md5SignValue = MD5.MD5Encode(signString);

		banktype = URLEncoder.encode(banktype);
		productName = URLEncoder.encode(productName);
		feeType = URLEncoder.encode(feeType);
		inputCharset = URLEncoder.encode(inputCharset);

		notifyUrl = URLEncoder.encode(notifyUrl);
		outTradeNo = URLEncoder.encode(outTradeNo);
		partner = URLEncoder.encode(partner);
		ip = URLEncoder.encode(ip);
		totalFee = URLEncoder.encode(totalFee);

		String completeString = "bank_type=" + banktype + "&body="
				+ productName + "&fee_type=" + feeType + "&input_charset="
				+ inputCharset + "&notify_url=" + notifyUrl + "&out_trade_no="
				+ outTradeNo + "&partner=" + partner + "&spbill_create_ip="
				+ ip + "&total_fee=" + totalFee;
		completeString = completeString + "&sign=" + md5SignValue;

		vo.setOldPackageString(completeString);
		vo.setSignString(completeString);
		return vo;
	}

	private static String getSign(WXPayInfoVO vo) {
		String app_id = vo.getWxuserVO().getWxuser().getAppid();
		String app_key = vo.getWxpayInfo().getAppKey();
		String nonce_str = vo.getOldNonceStr();
		String package_string = vo.getOldPackageString();
		String time_stamp = vo.getOldTimestamp();
		// 第一步，对所有需要传入的参数加上appkey作一次key＝value字典序的排序
		String keyvaluestring = "appid=" + app_id + "&appkey=" + app_key
				+ "&noncestr=" + nonce_str + "&package=" + package_string
				+ "&timestamp=" + time_stamp;
		String sign = Sha1Util.getSha1(keyvaluestring);
		return sign;
	}

	public static String formatBizQueryParaMap(Map<String, String> paraMap,
			boolean urlencode) throws SDKRuntimeException {

		String buff = "";
		try {
			List<Map.Entry<String, String>> infoIds = new ArrayList<Map.Entry<String, String>>(
					paraMap.entrySet());

			Collections.sort(infoIds,
					new Comparator<Map.Entry<String, String>>() {
						public int compare(Map.Entry<String, String> o1,
								Map.Entry<String, String> o2) {
							return (o1.getKey()).toString().compareTo(
									o2.getKey());
						}
					});

			for (int i = 0; i < infoIds.size(); i++) {
				Map.Entry<String, String> item = infoIds.get(i);
				if (item.getKey() != "") {

					String key = item.getKey();
					String val = item.getValue();
					if (urlencode) {
						val = URLEncoder.encode(val, "utf-8");

					}
					buff += key.toLowerCase() + "=" + val + "&";

				}
			}

			if (buff.isEmpty() == false) {
				buff = buff.substring(0, buff.length() - 1);
			}
		} catch (Exception e) {
			throw new SDKRuntimeException(e.getMessage());
		}
		return buff;
	}

	public WXPayInfoVO findByWxUserid(Long wxuserid) {
		if (wxuserid == null)
			return null;
		WXPayInfoVO v = new WXPayInfoVO();
		WXPayInfo info = new WXPayInfo();
		info.setWxuserid(wxuserid);

		v.setWxpayInfo(info);
		List<WXPayInfoVO> res = getList(v, 0, 0);
		if (res == null || res.size() <= 0)
			return null;
		return res.get(0);
	}

	public List<WXPayInfoVO> getList(WXPayInfoVO query, int pageNo, int pageSize) {
		List<WXPayInfoVO> res = new LinkedList<WXPayInfoVO>();
		String hql = getHql(query);
		List<WXPayInfo> l = this.dao.getEntitiesByPropertiesWithHql(hql,
				pageNo, pageSize);
		for (WXPayInfo e : l) {
			WXPayInfoVO v = new WXPayInfoVO();
			v.setWxpayInfo(e);
			v.setWxuserVO(wxuserService.findByidVO(e.getWxuserid().toString()));
			res.add(v);
		}
		return res;
	}

	public String getHql(WXPayInfoVO query) {
		StringBuffer sb = new StringBuffer();
		sb.append("from WXPayInfo");
		boolean isWhere = false;
		boolean isAnd = false;
		if (query != null
				&& query.getWxpayInfo() != null
				&& (query.getWxpayInfo().getWxuserid() != null || query
						.getWxpayInfo().getAppKey() != null))
			isWhere = true;
		if (isWhere) {
			sb.append(" where ");
		}
		if (query.getWxpayInfo() != null
				&& query.getWxpayInfo().getWxuserid() != null) {
			if (isAnd)
				sb.append(" and ");
			sb.append(" wxuserid= ").append(query.getWxpayInfo().getWxuserid());
		}
		return sb.toString();
	}

	public static void main(String[] args) throws Exception {
		Map<String, String> m = new HashMap<String, String>();
		m.put("appId", "wxaeabc3bd7fd54699");
		m.put("timeStamp", "1410405894943");
		m.put("nonceStr", "U1QZVB3gE6p8y44KK5LKrUvukT6M52TA");
		m.put("bank_type", "WX");
		m.put("body", "测试商品");

		String res = formatBizQueryParaMap(m, true);
		log.debug(StringHelper.getRandomChar(32));

		String content = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		log.debug(StringHelper.getRandomCharForInput(content, 16));
		WXPayInfoService payService = (WXPayInfoService) SpringBase.getCtx()
				.getBean("WXPayInfoService");
		WXPayInfoVO v = payService.findByWxUserid(1l);
		v.setProductName("测试商品");
		v.setTotalFee("1");
		v.setClientIp("106.120.21.62");
		String rr = payService.getSignOut(v);

	}

}
