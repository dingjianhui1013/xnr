package com.cntinker.util.wx.connect;

public class OAuth {

	private Long timep;
	private String group_id;
	private String signature;
	private String signaturet;
	private String noncestr;
	public Long getTimep() {
		return timep;
	}
	public void setTimep(Long timep) {
		this.timep = timep;
	}
	public String getGroup_id() {
		return group_id;
	}
	public void setGroup_id(String group_id) {
		this.group_id = group_id;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	public String getSignaturet() {
		return signaturet;
	}
	public void setSignaturet(String signaturet) {
		this.signaturet = signaturet;
	}
	public String getNoncestr() {
		return noncestr;
	}
	public void setNoncestr(String noncestr) {
		this.noncestr = noncestr;
	}
	
}
