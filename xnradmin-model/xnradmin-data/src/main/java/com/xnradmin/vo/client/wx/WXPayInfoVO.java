/**
 *2014年9月11日 上午11:45:41
 */
package com.xnradmin.vo.client.wx;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

import com.cntinker.util.ReflectHelper;
import com.xnradmin.po.wx.WXPayInfo;

/**
 * @author: liubin
 *
 */
public class WXPayInfoVO implements Serializable {

	private WXPayInfo wxpayInfo;

	private WXUserVO wxuserVO;

	// 商品名称信息(body)
	private String productName;

	// 费用类型，这里1为默认的人民币
	private String fee_type = "1";

	private String signType = "SHA1";

	// 订单号，商户需要保证该字段对于本商户的唯一性
	private String outTradeNo;

	// 总金额。单位分
	private String totalFee;

	// 首先第一步：对原串进行签名，注意这里不要对任何字段进行编码。这里是将参数按照key=value进行字典排序后组成下面的字符串,在这个字符串最后拼接上key=XXXX。由于这里的字段固定，因此只需要按照这个顺序进行排序即可。
	private String signString;

	// 然后第二步，对每个参数进行url转码，需要使用UrlEncode函数进行编码。
	private String md5SignValue;

	// 记住package，方便最后进行整体签名时取用
	private String oldPackageString;

	// 用户浏览器的ip
	private String clientIp;

	// /记住nonceStr,避免签名时的nonceStr与传入的nonceStr不一致
	private String oldNonceStr;

	// 记住timestamp，避免签名时的timestamp与传入的timestamp时不一致
	private String oldTimestamp;

	//支付应答的微信用户OPENID
	private String openuid;
	
	//
	private String transid;
	
	private String deliver_status;
	
	private String deliver_msg;

	public String toString() {
		String res = "";
		try {
			res = ReflectHelper.makeToString(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public WXPayInfo getWxpayInfo() {
		return wxpayInfo;
	}

	public WXUserVO getWxuserVO() {
		return wxuserVO;
	}

	public void setWxpayInfo(WXPayInfo wxpayInfo) {
		this.wxpayInfo = wxpayInfo;
	}

	public void setWxuserVO(WXUserVO wxuserVO) {
		this.wxuserVO = wxuserVO;
	}

	public String getProductName() {
		return productName;
	}

	public String getFee_type() {
		return fee_type;
	}

	/**
	 * 订单号，商户需要保证该字段对于本商户的唯一性
	 * 
	 * @return String
	 */
	public String getOutTradeNo() {
		return outTradeNo;
	}

	public String getTotalFee() {
		return totalFee;
	}

	/**
	 * 首先第一步：对原串进行签名，注意这里不要对任何字段进行编码。这里是将参数按照key=value进行字典排序后组成下面的字符串,
	 * 在这个字符串最后拼接上key=XXXX。由于这里的字段固定，因此只需要按照这个顺序进行排序即可。
	 * 
	 * @return String
	 * 
	 */
	public String getSignString() {
		return signString;
	}

	/**
	 * 然后第二步，对每个参数进行url转码，需要使用UrlEncode函数进行编码。
	 * 
	 * @return String
	 */
	public String getMd5SignValue() {
		return md5SignValue;
	}

	/**
	 * 记住package，方便最后进行整体签名时取用
	 * 
	 * @return String
	 */
	public String getOldPackageString() {
		return oldPackageString;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public void setFee_type(String fee_type) {
		this.fee_type = fee_type;
	}

	/**
	 * 订单号，商户需要保证该字段对于本商户的唯一性
	 * 
	 * @param outTradeNo
	 */
	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}

	public void setTotalFee(String totalFee) {
		this.totalFee = totalFee;
	}

	/**
	 * 首先第一步：对原串进行签名，注意这里不要对任何字段进行编码。这里是将参数按照key=value进行字典排序后组成下面的字符串,
	 * 在这个字符串最后拼接上key=XXXX。由于这里的字段固定，因此只需要按照这个顺序进行排序即可。
	 * 
	 * @param signString
	 */
	public void setSignString(String signString) {
		this.signString = signString;
	}

	/**
	 * 然后第二步，对每个参数进行url转码，需要使用UrlEncode函数进行编码。
	 * 
	 * @param md5SignValue
	 */
	public void setMd5SignValue(String md5SignValue) {
		this.md5SignValue = md5SignValue;
	}

	/**
	 * 记住package，方便最后进行整体签名时取用
	 * 
	 * @param oldPackageString
	 */
	public void setOldPackageString(String oldPackageString) {
		this.oldPackageString = oldPackageString;
	}

	public String getClientIp() {
		return clientIp;
	}

	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}

	public String getOldNonceStr() {
		return oldNonceStr;
	}

	public void setOldNonceStr(String oldNonceStr) {
		this.oldNonceStr = oldNonceStr;
	}

	public String getOldTimestamp() {
		return oldTimestamp;
	}

	public void setOldTimestamp(String oldTimestamp) {
		this.oldTimestamp = oldTimestamp;
	}

	public String getSignType() {
		return signType;
	}

	public void setSignType(String signType) {
		this.signType = signType;
	}

	public String getOpenuid() {
		return openuid;
	}

	public void setOpenuid(String openuid) {
		this.openuid = openuid;
	}

	public String getTransid() {
		return transid;
	}

	public void setTransid(String transid) {
		this.transid = transid;
	}

	public String getDeliver_status() {
		return deliver_status;
	}

	public String getDeliver_msg() {
		return deliver_msg;
	}

	public void setDeliver_status(String deliver_status) {
		this.deliver_status = deliver_status;
	}

	public void setDeliver_msg(String deliver_msg) {
		this.deliver_msg = deliver_msg;
	}

}
