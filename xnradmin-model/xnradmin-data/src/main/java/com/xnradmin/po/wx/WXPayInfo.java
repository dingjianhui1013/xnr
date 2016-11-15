/**
 *2014年9月11日 上午11:41:23
 */
package com.xnradmin.po.wx;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Index;

import com.cntinker.util.ReflectHelper;

/**
 * @author: liubin
 *
 */
@Entity
@Table(name = "wx_payinfo")
public class WXPayInfo implements java.io.Serializable {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	private Long id;
	@Index(name = "idx_wxpayInfo_wxuserid")
	private Long wxuserid;

	// 财付通商户号
	private String partner;

	// 财付通密钥
	private String partnerKey;

	// paysignkey 128位字符串(非appkey)
	private String appKey;

	// 支付完成后的回调处理页面,*替换成notify_url.asp所在路径
	private String notify_url;

	// 日志保存路径
	private String loging_dir;
	
	private String bankType = "WX";
	
	//字符集
	private String inputCharset = "UTF-8";

	public String toString() {
		String res = "";
		try {
			res = ReflectHelper.makeToString(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public Long getId() {
		return id;
	}

	public Long getWxuserid() {
		return wxuserid;
	}

	public String getPartner() {
		return partner;
	}

	public String getPartnerKey() {
		return partnerKey;
	}

	public String getAppKey() {
		return appKey;
	}

	public String getNotify_url() {
		return notify_url;
	}

	public String getLoging_dir() {
		return loging_dir;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setWxuserid(Long wxuserid) {
		this.wxuserid = wxuserid;
	}

	public void setPartner(String partner) {
		this.partner = partner;
	}

	public void setPartnerKey(String partnerKey) {
		this.partnerKey = partnerKey;
	}

	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}

	public void setNotify_url(String notify_url) {
		this.notify_url = notify_url;
	}

	public void setLoging_dir(String loging_dir) {
		this.loging_dir = loging_dir;
	}

	public String getBankType() {
		return bankType;
	}

	public String getInputCharset() {
		return inputCharset;
	}

	public void setBankType(String bankType) {
		this.bankType = bankType;
	}

	public void setInputCharset(String inputCharset) {
		this.inputCharset = inputCharset;
	}
}
