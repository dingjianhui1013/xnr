/**
 *2014年8月21日 下午3:06:27
 */
package com.xnradmin.po.pay;

import static javax.persistence.GenerationType.IDENTITY;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.cntinker.util.ReflectHelper;

/**
 * @author: liubin
 * 
 */
@Entity
@Table(name = "pay_info")
public class PayInfo implements java.io.Serializable {
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	private Long id;

	private Integer payTypeid;

	private Integer statusid;

	// 平台内部的用户ID
	private Integer staffid;

	// .......... 微信支付 ............

	// WX支付 - 财付通商户号
	private String wx_partner;

	// WX支付 - 财付通密钥
	private String wx_partnerKey;

	// WX支付 - paysignkey 128位字符串(非appkey)
	private String wx_paysignkey;

	// WX支付 - 支付完成后的回调处理页面
	private String wx_notifyUrl;

	// 平台内部的微信用户ID
	private Long wxuserid;

	// .......... 阿里支付 ............

	// alipay - 商户的私钥
	private String alipay_private_key;

	// alipay - 支付宝的公钥，无需修改该值
	private String alipay_ali_public_key;

	// alipay - 合作身份者ID，以2088开头由16位纯数字组成的字符串
	private String alipay_partner;

	private Timestamp createTime;

	private Timestamp modifyTime;

	private Integer createStaffid;

	private Integer modifyStaffid;

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

	public Integer getPayTypeid() {
		return payTypeid;
	}

	public Integer getStatusid() {
		return statusid;
	}

	public Integer getStaffid() {
		return staffid;
	}

	public String getWx_partner() {
		return wx_partner;
	}

	public String getWx_partnerKey() {
		return wx_partnerKey;
	}

	public String getWx_paysignkey() {
		return wx_paysignkey;
	}

	public String getWx_notifyUrl() {
		return wx_notifyUrl;
	}

	public Long getWxuserid() {
		return wxuserid;
	}

	public String getAlipay_private_key() {
		return alipay_private_key;
	}

	public String getAlipay_ali_public_key() {
		return alipay_ali_public_key;
	}

	public String getAlipay_partner() {
		return alipay_partner;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setPayTypeid(Integer payTypeid) {
		this.payTypeid = payTypeid;
	}

	public void setStatusid(Integer statusid) {
		this.statusid = statusid;
	}

	public void setStaffid(Integer staffid) {
		this.staffid = staffid;
	}

	public void setWx_partner(String wx_partner) {
		this.wx_partner = wx_partner;
	}

	public void setWx_partnerKey(String wx_partnerKey) {
		this.wx_partnerKey = wx_partnerKey;
	}

	public void setWx_paysignkey(String wx_paysignkey) {
		this.wx_paysignkey = wx_paysignkey;
	}

	public void setWx_notifyUrl(String wx_notifyUrl) {
		this.wx_notifyUrl = wx_notifyUrl;
	}

	public void setWxuserid(Long wxuserid) {
		this.wxuserid = wxuserid;
	}

	public void setAlipay_private_key(String alipay_private_key) {
		this.alipay_private_key = alipay_private_key;
	}

	public void setAlipay_ali_public_key(String alipay_ali_public_key) {
		this.alipay_ali_public_key = alipay_ali_public_key;
	}

	public void setAlipay_partner(String alipay_partner) {
		this.alipay_partner = alipay_partner;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public Timestamp getModifyTime() {
		return modifyTime;
	}

	public Integer getCreateStaffid() {
		return createStaffid;
	}

	public Integer getModifyStaffid() {
		return modifyStaffid;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public void setModifyTime(Timestamp modifyTime) {
		this.modifyTime = modifyTime;
	}

	public void setCreateStaffid(Integer createStaffid) {
		this.createStaffid = createStaffid;
	}

	public void setModifyStaffid(Integer modifyStaffid) {
		this.modifyStaffid = modifyStaffid;
	}

}
