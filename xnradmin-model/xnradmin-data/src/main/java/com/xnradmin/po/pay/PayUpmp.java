package com.xnradmin.po.pay;

import static javax.persistence.GenerationType.IDENTITY;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "pay_upmp_log")
public class PayUpmp implements java.io.Serializable{
	
	private Long id;
    private String version; //版本号
    private String charset; //字符编码
    private String signMethod; //签名方法
    private String signature; //签名信息
    private String transType; //交易类型
    private String merId; //商户代码
    private String transStatus; //交易状态
    private String respCode; //响应码
    private String respMsg; //响应信息
    private String serNo; //银行流水号
    private Long orderId;  //订单号
    private Integer orderType;  //订单类型ID(包卡订单，按次订单)
    private String orderTypeName;  //订单类型(包卡订单，按次订单)
    private Timestamp orderTime; //交易开始日期时间
    private String settleAmount; //清算金额
    private String settleCurrency; //清算币种
    private Timestamp settleDate; //清算日期(MMDD)
    private String exchangeRate; //清算汇率
    private Timestamp exchangeDate; //兑换日期(MMDD)
    private String merReserved; //商户保留域
    private String reqReserved; //请求方保留域
    private String sysReserved; //系统保留域
    private Timestamp receivetime; //收到数据时间
    private Timestamp modifytime; //修改数据时间
    
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name = "VERSION", length = 50)
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	
	@Column(name = "CHARSET", length = 50)
	public String getCharset() {
		return charset;
	}
	public void setCharset(String charset) {
		this.charset = charset;
	}
	
	@Column(name = "SIGN_METHOD", length = 20)
	public String getSignMethod() {
		return signMethod;
	}
	public void setSignMethod(String signMethod) {
		this.signMethod = signMethod;
	}
	
	@Column(name = "SIGNATURE", length = 50)
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	
	@Column(name = "TRANS_TYPE", length = 20)
	public String getTransType() {
		return transType;
	}
	public void setTransType(String transType) {
		this.transType = transType;
	}
	
	@Column(name = "MER_ID", length = 30)
	public String getMerId() {
		return merId;
	}
	public void setMerId(String merId) {
		this.merId = merId;
	}
	
	@Column(name = "TRANS_STATUS", length = 20)
	public String getTransStatus() {
		return transStatus;
	}
	public void setTransStatus(String transStatus) {
		this.transStatus = transStatus;
	}
	
	@Column(name = "RESP_CODE", length = 20)
	public String getRespCode() {
		return respCode;
	}
	public void setRespCode(String respCode) {
		this.respCode = respCode;
	}
	
	@Column(name = "RESP_MSG", length = 200)
	public String getRespMsg() {
		return respMsg;
	}
	public void setRespMsg(String respMsg) {
		this.respMsg = respMsg;
	}
	
	@Column(name = "SER_NO")
	public String getSerNo() {
		return serNo;
	}
	public void setSerNo(String serNo) {
		this.serNo = serNo;
	}
	
	@Column(name = "ORDER_ID")
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	
	@Column(name = "ORDER_TYPE" )
	public Integer getOrderType() {
		return orderType;
	}
	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}
	
	@Column(name = "ORDER_TYPE_NAME" )
	public String getOrderTypeName() {
		return orderTypeName;
	}
	public void setOrderTypeName(String orderTypeName) {
		this.orderTypeName = orderTypeName;
	}
	
	@Column(name = "ORDER_TIME")
	public Timestamp getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(Timestamp orderTime) {
		this.orderTime = orderTime;
	}
	
	
	
	@Column(name = "SETTLE_AMOUNT", length = 20)
	public String getSettleAmount() {
		return settleAmount;
	}
	public void setSettleAmount(String settleAmount) {
		this.settleAmount = settleAmount;
	}
	
	@Column(name = "SETTLE_CURRENCY", length = 20)
	public String getSettleCurrency() {
		return settleCurrency;
	}
	public void setSettleCurrency(String settleCurrency) {
		this.settleCurrency = settleCurrency;
	}
	
	@Column(name = "SETTLE_DATE")
	public Timestamp getSettleDate() {
		return settleDate;
	}
	public void setSettleDate(Timestamp settleDate) {
		this.settleDate = settleDate;
	}
	
	@Column(name = "EXCHANGE_RATE", length = 20)
	public String getExchangeRate() {
		return exchangeRate;
	}
	public void setExchangeRate(String exchangeRate) {
		this.exchangeRate = exchangeRate;
	}
	
	@Column(name = "EXCHANGE_DATE", length = 20)
	public Timestamp getExchangeDate() {
		return exchangeDate;
	}
	public void setExchangeDate(Timestamp exchangeDate) {
		this.exchangeDate = exchangeDate;
	}
	
	@Column(name = "MER_RESERVED", length = 200)
	public String getMerReserved() {
		return merReserved;
	}
	public void setMerReserved(String merReserved) {
		this.merReserved = merReserved;
	}
	
	@Column(name = "REQ_RESERVED", length = 200)
	public String getReqReserved() {
		return reqReserved;
	}
	public void setReqReserved(String reqReserved) {
		this.reqReserved = reqReserved;
	}
	
	@Column(name = "SYS_RESERVED", length = 200)
	public String getSysReserved() {
		return sysReserved;
	}
	public void setSysReserved(String sysReserved) {
		this.sysReserved = sysReserved;
	}
	
	@Column(name = "RECEIVETIME")
	public Timestamp getReceivetime() {
		return receivetime;
	}
	public void setReceivetime(Timestamp receivetime) {
		this.receivetime = receivetime;
	}
	
	@Column(name = "MODIFYTIME")
	public Timestamp getModifytime() {
		return modifytime;
	}
	public void setModifytime(Timestamp modifytime) {
		this.modifytime = modifytime;
	}
    
    
}
