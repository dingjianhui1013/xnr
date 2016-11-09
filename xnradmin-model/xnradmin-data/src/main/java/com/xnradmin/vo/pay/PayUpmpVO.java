package com.xnradmin.vo.pay;

public class PayUpmpVO {

	private String payUpmpid;
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
    private String orderId;  //订单号
    private String orderType;  //订单类型ID(包卡订单，按次订单)
    private String orderTypeName;  //订单类型(包卡订单，按次订单)
    private String orderTime; //交易开始日期时间
    private String settleAmount; //清算金额
    private String settleCurrency; //清算币种
    private String settleDate; //清算日期(MMDD)
    private String exchangeRate; //清算汇率
    private String exchangeDate; //兑换日期(MMDD)
    private String merReserved; //商户保留域
    private String reqReserved; //请求方保留域
    private String sysReserved; //系统保留域
    private String receiveTime; //插入时间
    private String modifyTime;  //更新时间
	public String getPayUpmpid() {
		return payUpmpid;
	}
	public void setPayUpmpid(String payUpmpid) {
		this.payUpmpid = payUpmpid;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getCharset() {
		return charset;
	}
	public void setCharset(String charset) {
		this.charset = charset;
	}
	public String getSignMethod() {
		return signMethod;
	}
	public void setSignMethod(String signMethod) {
		this.signMethod = signMethod;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	public String getTransType() {
		return transType;
	}
	public void setTransType(String transType) {
		this.transType = transType;
	}
	public String getMerId() {
		return merId;
	}
	public void setMerId(String merId) {
		this.merId = merId;
	}
	public String getTransStatus() {
		return transStatus;
	}
	public void setTransStatus(String transStatus) {
		this.transStatus = transStatus;
	}
	public String getRespCode() {
		return respCode;
	}
	public void setRespCode(String respCode) {
		this.respCode = respCode;
	}
	public String getRespMsg() {
		return respMsg;
	}
	public void setRespMsg(String respMsg) {
		this.respMsg = respMsg;
	}
	public String getSerNo() {
		return serNo;
	}
	public void setSerNo(String serNo) {
		this.serNo = serNo;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getOrderType() {
		return orderType;
	}
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	public String getOrderTypeName() {
		return orderTypeName;
	}
	public void setOrderTypeName(String orderTypeName) {
		this.orderTypeName = orderTypeName;
	}
	public String getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}
	public String getSettleAmount() {
		return settleAmount;
	}
	public void setSettleAmount(String settleAmount) {
		this.settleAmount = settleAmount;
	}
	public String getSettleCurrency() {
		return settleCurrency;
	}
	public void setSettleCurrency(String settleCurrency) {
		this.settleCurrency = settleCurrency;
	}
	public String getSettleDate() {
		return settleDate;
	}
	public void setSettleDate(String settleDate) {
		this.settleDate = settleDate;
	}
	public String getExchangeRate() {
		return exchangeRate;
	}
	public void setExchangeRate(String exchangeRate) {
		this.exchangeRate = exchangeRate;
	}
	public String getExchangeDate() {
		return exchangeDate;
	}
	public void setExchangeDate(String exchangeDate) {
		this.exchangeDate = exchangeDate;
	}
	public String getMerReserved() {
		return merReserved;
	}
	public void setMerReserved(String merReserved) {
		this.merReserved = merReserved;
	}
	public String getReqReserved() {
		return reqReserved;
	}
	public void setReqReserved(String reqReserved) {
		this.reqReserved = reqReserved;
	}
	public String getSysReserved() {
		return sysReserved;
	}
	public void setSysReserved(String sysReserved) {
		this.sysReserved = sysReserved;
	}
	public String getReceiveTime() {
		return receiveTime;
	}
	public void setReceiveTime(String receiveTime) {
		this.receiveTime = receiveTime;
	}
	public String getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
	}
    
    
}
