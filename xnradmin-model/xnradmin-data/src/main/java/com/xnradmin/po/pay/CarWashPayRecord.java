/**
 * 2013-5-29 上午10:03:22
 */
package com.xnradmin.po.pay;


import static javax.persistence.GenerationType.IDENTITY;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @autohr: bin_liu
 */
@Entity
@Table(name = "car_wash_pay_record")
public class CarWashPayRecord implements java.io.Serializable{
    private Long id;

    // local uuid
    private String serNo; //银行流水号

    private Long orderId;  //订单ID订单号

    private String orderIp;  //订单发起者IP

    private String payIp;  //用户支付IP 预留
    
    private Double money;  //资费
    
    private String moneyType; //钱币种类

    private String userId;	//用户ID

    private String userName;	//用户名

    private Integer paymentType;	//支付类型，信用卡，储值卡，借记卡（STATUS表）
    
    private Integer bankerId;	//银行ID（STATUS表）
    
    private Integer bankerName; //银行名（STATUS表）

    private Integer paymentProviderId;	//支付提供者ID（STATUS表）

    private String paymentProviderName;	//提供者名称（STATUS表）
    
    private String paymentProviderSn; //支付提供商户号
    
    private String paymentProviderIp; //支付结果通知服务器IP
    
    private String paymentProviderHost; //request.geturi
    
    private String paymentProviderPort; //端口

    private Timestamp orderTime;  //订单时间

    private Timestamp payTime;  //支付时间

    private Integer status;  //订单状态

    private String statusName;  //订单状态名

    private String productId; //产品ID

    private String productName; //产品名称

    private String productType; //产品类型

    private String productTypeName;//产品类型名称
    
    private String productDesc; //产品描述

    private String returnUrl;   //用户支付完跳转链接

    private String reqUrl; //银联通知URL

    private String reqAck; //返回银联状态信息
    
    private String reqAckCode; //信息代码
       
    private Long sellerId; //代理商ID
    
    private String sellerName; //代理商名称
    
    private String paymentProviderUID;  //三方支付返回的额外预留字段

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public Integer getPaymentType(){
        return paymentType;
    }

    public void setPaymentType(Integer paymentType){
        this.paymentType = paymentType;
    }

    public Timestamp getOrderTime(){
        return orderTime;
    }

    public void setOrderTime(Timestamp orderTime){
        this.orderTime = orderTime;
    }

    public Timestamp getPayTime(){
        return payTime;
    }

    public void setPayTime(Timestamp payTime){
        this.payTime = payTime;
    }

    public Integer getPaymentProviderId(){
        return paymentProviderId;
    }

    public void setPaymentProviderId(Integer paymentProviderId){
        this.paymentProviderId = paymentProviderId;
    }

    public String getPaymentProviderName(){
        return paymentProviderName;
    }

    public void setPaymentProviderName(String paymentProviderName){
        this.paymentProviderName = paymentProviderName;
    }

    public String getUserName(){
        return userName;
    }

    public void setUserName(String userName){
        this.userName = userName;
    }

    public Integer getStatus(){
        return status;
    }

    public void setStatus(Integer status){
        this.status = status;
    }

    public String getStatusName(){
        return statusName;
    }

    public void setStatusName(String statusName){
        this.statusName = statusName;
    }

    public String getProductId(){
        return productId;
    }

    public void setProductId(String productId){
        this.productId = productId;
    }

    public String getProductName(){
        return productName;
    }

    public void setProductName(String productName){
        this.productName = productName;
    }

    public String getProductType(){
        return productType;
    }

    public void setProductType(String productType){
        this.productType = productType;
    }

    public String getProductDesc(){
        return productDesc;
    }

    public void setProductDesc(String productDesc){
        this.productDesc = productDesc;
    }

    public String getReturnUrl(){
        return returnUrl;
    }

    public void setReturnUrl(String returnUrl){
        this.returnUrl = returnUrl;
    }

    public String getReqUrl(){
        return reqUrl;
    }

    public void setReqUrl(String reqUrl){
        this.reqUrl = reqUrl;
    }

    public String getReqAck(){
        return reqAck;
    }

    public void setReqAck(String reqAck){
        this.reqAck = reqAck;
    }

    public String getPaymentProviderSn(){
        return paymentProviderSn;
    }

    public void setPaymentProviderSn(String paymentProviderSn){
        this.paymentProviderSn = paymentProviderSn;
    }

    public Long getSellerId(){
        return sellerId;
    }

    public void setSellerId(Long sellerId){
        this.sellerId = sellerId;
    }

    public Double getMoney(){
        return money;
    }

    public void setMoney(Double money){
        this.money = money;
    }

    public String getMoneyType(){
        return moneyType;
    }

    public void setMoneyType(String moneyType){
        this.moneyType = moneyType;
    }

    public String getPaymentProviderUID(){
        return paymentProviderUID;
    }

    public void setPaymentProviderUID(String paymentProviderUID){
        this.paymentProviderUID = paymentProviderUID;
    }

    public String getReqAckCode(){
        return reqAckCode;
    }

    public void setReqAckCode(String reqAckCode){
        this.reqAckCode = reqAckCode;
    }

    public String getPaymentProviderIp(){
        return paymentProviderIp;
    }

    public void setPaymentProviderIp(String paymentProviderIp){
        this.paymentProviderIp = paymentProviderIp;
    }

    public String getPaymentProviderHost(){
        return paymentProviderHost;
    }

    public void setPaymentProviderHost(String paymentProviderHost){
        this.paymentProviderHost = paymentProviderHost;
    }

    public String getPaymentProviderPort(){
        return paymentProviderPort;
    }

    public void setPaymentProviderPort(String paymentProviderPort){
        this.paymentProviderPort = paymentProviderPort;
    }

	public Integer getBankerId() {
		return bankerId;
	}

	public void setBankerId(Integer bankerId) {
		this.bankerId = bankerId;
	}

	public Integer getBankerName() {
		return bankerName;
	}

	public void setBankerName(Integer bankerName) {
		this.bankerName = bankerName;
	}

	public String getSellerName() {
		return sellerName;
	}

	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}

	public String getSerNo() {
		return serNo;
	}

	public void setSerNo(String serNo) {
		this.serNo = serNo;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public String getOrderIp() {
		return orderIp;
	}

	public void setOrderIp(String orderIp) {
		this.orderIp = orderIp;
	}

	public String getPayIp() {
		return payIp;
	}

	public void setPayIp(String payIp) {
		this.payIp = payIp;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getProductTypeName() {
		return productTypeName;
	}

	public void setProductTypeName(String productTypeName) {
		this.productTypeName = productTypeName;
	}
    
}
