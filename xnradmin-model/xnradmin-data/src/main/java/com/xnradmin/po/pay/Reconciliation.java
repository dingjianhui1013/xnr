package com.xnradmin.po.pay;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "reconciliation")
public class Reconciliation {

	private Integer id;
	private Long oprderRecordId;//订单id
	private String alipayTradNo;//支付宝交易号
	private String buyerMail;//买家支付宝号
	private String buyerId;//买家支付宝账号
	private String tradeStatus;//支付状态
	private String isRefund;
	@Id
	@GeneratedValue(strategy = IDENTITY)
    @Column(name = "ID", unique = true, nullable = false) 
	public Integer getId() {
		return id;
	}
	public Long getOprderRecordId() {
		return oprderRecordId;
	}
	public String getAlipayTradNo() {
		return alipayTradNo;
	}
	public String getBuyerMail() {
		return buyerMail;
	}
	public String getBuyerId() {
		return buyerId;
	}
	public String getTradeStatus() {
		return tradeStatus;
	}
	public String getIsRefund() {
		return isRefund;
	}
	public void setIsRefund(String isRefund) {
		this.isRefund = isRefund;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setOprderRecordId(Long oprderRecordId) {
		this.oprderRecordId = oprderRecordId;
	}
	public void setAlipayTradNo(String alipayTradNo) {
		this.alipayTradNo = alipayTradNo;
	}
	public void setBuyerMail(String buyerMail) {
		this.buyerMail = buyerMail;
	}
	public void setBuyerId(String buyerId) {
		this.buyerId = buyerId;
	}
	public void setTradeStatus(String tradeStatus) {
		this.tradeStatus = tradeStatus;
	}
	
	
	
	
	
}
