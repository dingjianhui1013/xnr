package com.xnradmin.po.pay;

public class Alipay {
	private Integer id;
	private String outTradeNo;//订单号
	private String subject;//商品名称
	private String totalFee;//付款金额
	private String body;//商品描述
	private String userId;//用户Id
	public Integer getId() {
		return id;
	}
	public String getOutTradeNo() {
		return outTradeNo;
	}
	public String getSubject() {
		return subject;
	}
	public String getTotalFee() {
		return totalFee;
	}
	public String getBody() {
		return body;
	}
	public void setTotalFee(String totalFee) {
		this.totalFee = totalFee;
	}
	public String getUserId() {
		return userId;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
}
