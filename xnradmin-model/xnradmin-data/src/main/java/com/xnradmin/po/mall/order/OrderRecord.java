/**
 * 2013-5-29 下午5:08:50
 */
package com.xnradmin.po.mall.order;


import static javax.persistence.GenerationType.IDENTITY;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Index;

/**
 * @autohr: bin_liu
 */
@Entity
@Table(name = "order_record")
public class OrderRecord implements java.io.Serializable{

	private static final long serialVersionUID = 1L;

	private Long id;

    private Integer clientUserId; //用户ID
    
    private String clientUserName; //用户昵称 
    
    private String clientUserMobile; //用户手机号
    
    private String clientUserEmail; //用户邮箱
    
    private String clientUserSex; //用户性别
    
    private Integer clientUserType; //用户类型（微信，APP，WEB，电话）
    
    private String clientUserTypeName; //用户类型名称（微信，APP，WEB，电话）
    
    private String wxOpenId; //微信OpenId
    
    private String staffId;	//隶属用户Id
    
    private String userRealMobile; //收货人用户手机号
    
    private String userRealPlane; //收货人用户座机

    private String userRealName; //收货人名称
    
    private String userRealDescription; //收货人备注
    
	private Integer countryId; //收货人国家
	
	private String countryName; //收货人国家名称
	
	private Integer provinceId; //收货人省份
	
	private String provinceName; //收货人省份名称
	
	private Integer cityId; //收货人城市
	
	private String cityName; //收货人城市名称
	
	private Integer areaId; //收货人区县
	
	private String areaName; //收货人区县名称
	
    private String userRealAddress; //收货人地址
    
    private String userRealPostcode; //邮政编码
    
    private Timestamp userRealTime; //用户收货时间
    
    private Integer paymentType; //支付类型，充值账户支付，单次支付
    
    private String paymentTypeName; //支付类型名称，充值账户支付，单次支付

    private Integer paymentStatus; //支付状态，已支付，待支付，退款
    
    private String paymentStatusName; //支付状态名称，已支付，待支付，退款
    
    private Integer paymentProvider; //支付提供者，微信，支付宝，银联
    
    private String paymentProviderName; //支付提供者名称，微信，支付宝，银联
    
    private Timestamp paymentTime; //支付时间

    private Timestamp operateTime; //订单操作时间（待处理，处理中，处理完成，订单退单）
    
    private Integer operateStatus; //订单操作状态（待处理，处理中，处理完成，订单退单）
    
    private String operateStatusName; //订单操作状态名称（待处理，处理中，处理完成，订单退单）
    
    private Timestamp createTime; //订单生成时间

    private Float originalPrice; //原始结算价格
    
    private Float totalPrice; //最终结算价格
    
    private Integer logisticsCompanyId; //配送公司ID
    
    private String logisticsCompanyName; //配送公司名称
    
    private String deliveryStaffId; //送货人员ID
    
    private String deliveryStaffName; //送货人员姓名
    
    private String deliveryStaffMobile; //送货人员手机号
    
    private Timestamp deliveryStartTime; //送货起始时间
    
    private Timestamp deliveryEndTime; //送货送达时间
    
    private Integer deliveryStatus; //配送状态（未发货，已发货）
    
    private String deliveryStatusName; //配送状态名称（未发货，已发货）
    
    private Integer sourceId; //该订单用户推广来源ID（有可能是用户推广，代理商推广，线上推广）
    
    private String sourceName; //该订单用户推广来源名称（有可能是用户推广，代理商推广，线上推广）
    
    private Integer sourceType; //该订单用户来源类型 （有可能是400电话用户，微信用户）
    
    private String sourceTypeName; //该订单用户推广来源类型名称 （有可能是用户推广，代理商推广，线上推广）
    
    private String serNo; //银行流水号
    
    @Index(name = "idx_order_record_orderNo")
    private String orderNo; //自定义的订单流水号 - 生成规则，年月日小时分秒+5位随机数

    private Integer sellerId; //代理商ID
    
    private String sellerName; //代理商名称
    
    private Integer cusId; //合作方管理
    
    private String cusName; //合作方名称
    
    private Integer primaryConfigurationId; //对应商城ID
    
    private String primaryConfigurationName; //对应商城名称

    
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    @Column(name = "CLIENT_USER_ID", length = 11)
    public Integer getClientUserId() {
		return clientUserId;
	}

	public void setClientUserId(Integer clientUserId) {
		this.clientUserId = clientUserId;
	}

	@Column(name = "CLIENT_USER_TYPE", length = 11)
	public Integer getClientUserType() {
		return clientUserType;
	}

	public void setClientUserType(Integer clientUserType) {
		this.clientUserType = clientUserType;
	}

	@Column(name = "CLIENT_USER_TYPE_NAME")
	public String getClientUserTypeName() {
		return clientUserTypeName;
	}

	public void setClientUserTypeName(String clientUserTypeName) {
		this.clientUserTypeName = clientUserTypeName;
	}

	@Column(name = "USER_REAL_MOBILE", length = 20)
	public String getUserRealMobile() {
		return userRealMobile;
	}
	
	public void setUserRealMobile(String userRealMobile) {
		this.userRealMobile = userRealMobile;
	}

	@Column(name = "USER_REAL_PLANE", length = 20)
	public String getUserRealPlane() {
		return userRealPlane;
	}

	public void setUserRealPlane(String userRealPlane) {
		this.userRealPlane = userRealPlane;
	}

	@Column(name = "USER_REAL_NAME", length = 20)
	public String getUserRealName() {
		return userRealName;
	}

	public void setUserRealName(String userRealName) {
		this.userRealName = userRealName;
	}

	@Column(name = "COUNTRY_ID")
	public Integer getCountryId() {
		return countryId;
	}

	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}

	@Column(name = "COUNTRY_NAME", length = 20)
	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	@Column(name = "PROVINCE_ID")
	public Integer getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}

	@Column(name = "PROVINCE_NAME", length=20)
	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	@Column(name = "CITY_ID")
	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	@Column(name = "CITY_NAME",length=20)
	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	@Column(name = "AREA_ID")
	public Integer getAreaId() {
		return areaId;
	}

	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}

	@Column(name = "AREA_NAME",length=20)
	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	@Column(name = "USER_REAL_ADDRESS", length = 400)
	public String getUserRealAddress() {
		return userRealAddress;
	}

	public void setUserRealAddress(String userRealAddress) {
		this.userRealAddress = userRealAddress;
	}

	@Column(name = "USER_REAL_POSTCODE", length = 20)
	public String getUserRealPostcode() {
		return userRealPostcode;
	}

	public void setUserRealPostcode(String userRealPostcode) {
		this.userRealPostcode = userRealPostcode;
	}

	@Column(name = "DELIVERY_STAFF_ID")
	public String getDeliveryStaffId() {
		return deliveryStaffId;
	}

	public void setDeliveryStaffId(String deliveryStaffId) {
		this.deliveryStaffId = deliveryStaffId;
	}

	@Column(name = "DELIVERY_START_TIME")
	public Timestamp getDeliveryStartTime() {
		return deliveryStartTime;
	}

	public void setDeliveryStartTime(Timestamp deliveryStartTime) {
		this.deliveryStartTime = deliveryStartTime;
	}

	@Column(name = "DELIVERY_END_TIME")
	public Timestamp getDeliveryEndTime() {
		return deliveryEndTime;
	}

	public void setDeliveryEndTime(Timestamp deliveryEndTime) {
		this.deliveryEndTime = deliveryEndTime;
	}

	@Column(name = "DELIVERY_STATUS")
	public Integer getDeliveryStatus() {
		return deliveryStatus;
	}

	public void setDeliveryStatus(Integer deliveryStatus) {
		this.deliveryStatus = deliveryStatus;
	}
	
	@Column(name = "SOURCE_NAME", length=50)
	public String getSourceName() {
		return sourceName;
	}

	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}

	@Column(name = "SOURCE_ID")
	public Integer getSourceId() {
		return sourceId;
	}

	public void setSourceId(Integer sourceId) {
		this.sourceId = sourceId;
	}

	@Column(name = "SOURCE_TYPE")
	public Integer getSourceType() {
		return sourceType;
	}

	public void setSourceType(Integer sourceType) {
		this.sourceType = sourceType;
	}

	@Column(name = "SER_NO")
	public String getSerNo() {
		return serNo;
	}

	public void setSerNo(String serNo) {
		this.serNo = serNo;
	}

	@Column(name = "CUS_ID")
	public Integer getCusId() {
		return cusId;
	}

	public void setCusId(Integer cusId) {
		this.cusId = cusId;
	}

    @Column(name = "SELLER_ID", length = 11)
    public Integer getSellerId(){
        return sellerId;
    }

    public void setSellerId(Integer sellerId){
        this.sellerId = sellerId;
    }
	
	@Column(name = "STAFF_ID")
	public String getStaffId() {
		return staffId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

	@Column(name = "USER_REAL_TIME")
	public Timestamp getUserRealTime() {
		return userRealTime;
	}

	public void setUserRealTime(Timestamp userRealTime) {
		this.userRealTime = userRealTime;
	}

	@Column(name = "PRIMARY_CONFIGURATION_ID")
	public Integer getPrimaryConfigurationId() {
		return primaryConfigurationId;
	}

	public void setPrimaryConfigurationId(Integer primaryConfigurationId) {
		this.primaryConfigurationId = primaryConfigurationId;
	}

	@Column(name = "CLIENT_USER_NAME", length=50)
	public String getClientUserName() {
		return clientUserName;
	}

	public void setClientUserName(String clientUserName) {
		this.clientUserName = clientUserName;
	}

	@Column(name = "CLIENT_USER_MOBILE", length=50)
	public String getClientUserMobile() {
		return clientUserMobile;
	}

	public void setClientUserMobile(String clientUserMobile) {
		this.clientUserMobile = clientUserMobile;
	}

	@Column(name = "CLIENT_USER_EMAIL", length=50)
	public String getClientUserEmail() {
		return clientUserEmail;
	}

	public void setClientUserEmail(String clientUserEmail) {
		this.clientUserEmail = clientUserEmail;
	}

	@Column(name = "WX_OPEN_ID", length=50)
	public String getWxOpenId() {
		return wxOpenId;
	}

	public void setWxOpenId(String wxOpenId) {
		this.wxOpenId = wxOpenId;
	}

	@Column(name = "CLIENT_USER_SEX", length=10)
	public String getClientUserSex() {
		return clientUserSex;
	}

	public void setClientUserSex(String clientUserSex) {
		this.clientUserSex = clientUserSex;
	}

	@Column(name = "DELIVERY_STAFF_NAME", length=50)
	public String getDeliveryStaffName() {
		return deliveryStaffName;
	}

	public void setDeliveryStaffName(String deliveryStaffName) {
		this.deliveryStaffName = deliveryStaffName;
	}

	@Column(name = "DELIVERY_STATUS_NAME", length=50)
	public String getDeliveryStatusName() {
		return deliveryStatusName;
	}

	public void setDeliveryStatusName(String deliveryStatusName) {
		this.deliveryStatusName = deliveryStatusName;
	}

	@Column(name = "SOURCE_TYPE_NAME", length=50)
	public String getSourceTypeName() {
		return sourceTypeName;
	}

	public void setSourceTypeName(String sourceTypeName) {
		this.sourceTypeName = sourceTypeName;
	}

	@Column(name = "SELLER_NAME", length=50)
	public String getSellerName() {
		return sellerName;
	}

	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}

	@Column(name = "CUS_NAME", length=50)
	public String getCusName() {
		return cusName;
	}

	public void setCusName(String cusName) {
		this.cusName = cusName;
	}

	@Column(name = "PRIMARY_CONFIGURATION_NAME", length=50)
	public String getPrimaryConfigurationName() {
		return primaryConfigurationName;
	}

	public void setPrimaryConfigurationName(String primaryConfigurationName) {
		this.primaryConfigurationName = primaryConfigurationName;
	}

	@Column(name = "DELIVERY_STAFF_MOBILE", length=50)
	public String getDeliveryStaffMobile() {
		return deliveryStaffMobile;
	}

	public void setDeliveryStaffMobile(String deliveryStaffMobile) {
		this.deliveryStaffMobile = deliveryStaffMobile;
	}

	@Column(name = "PAYMENT_TYPE")
	public Integer getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(Integer paymentType) {
		this.paymentType = paymentType;
	}

	@Column(name = "PAYMENT_TYPE_NAME", length=50)
	public String getPaymentTypeName() {
		return paymentTypeName;
	}

	public void setPaymentTypeName(String paymentTypeName) {
		this.paymentTypeName = paymentTypeName;
	}

	@Column(name = "PAYMENT_STATUS")
	public Integer getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(Integer paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	@Column(name = "PAYMENT_STATUS_NAME", length=50)
	public String getPaymentStatusName() {
		return paymentStatusName;
	}

	public void setPaymentStatusName(String paymentStatusName) {
		this.paymentStatusName = paymentStatusName;
	}

	@Column(name = "PAYMENT_PROVIDER")
	public Integer getPaymentProvider() {
		return paymentProvider;
	}

	public void setPaymentProvider(Integer paymentProvider) {
		this.paymentProvider = paymentProvider;
	}

	@Column(name = "PAYMENT_PROVIDER_NAME", length=50)
	public String getPaymentProviderName() {
		return paymentProviderName;
	}

	public void setPaymentProviderName(String paymentProviderName) {
		this.paymentProviderName = paymentProviderName;
	}

	@Column(name = "PAYMENT_TIME")
	public Timestamp getPaymentTime() {
		return paymentTime;
	}

	public void setPaymentTime(Timestamp paymentTime) {
		this.paymentTime = paymentTime;
	}

	@Column(name = "OPERATE_TIME")
	public Timestamp getOperateTime() {
		return operateTime;
	}

	public void setOperateTime(Timestamp operateTime) {
		this.operateTime = operateTime;
	}
	
	@Column(name = "OPERATE_STATUS")
	public Integer getOperateStatus() {
		return operateStatus;
	}

	public void setOperateStatus(Integer operateStatus) {
		this.operateStatus = operateStatus;
	}

	@Column(name = "OPERATE_STATUS_NAME")
	public String getOperateStatusName() {
		return operateStatusName;
	}

	public void setOperateStatusName(String operateStatusName) {
		this.operateStatusName = operateStatusName;
	}

	@Column(name = "CREATE_TIME")
	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	@Column(name = "ORIGINAL_PRICE")
	public Float getOriginalPrice() {
		return originalPrice;
	}

	public void setOriginalPrice(Float originalPrice) {
		this.originalPrice = originalPrice;
	}

	@Column(name = "TOTAL_PRICE")
	public Float getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Float totalPrice) {
		this.totalPrice = totalPrice;
	}

	@Column(name = "USER_REAL_DESCRIPTION", length=400)
	public String getUserRealDescription() {
		return userRealDescription;
	}

	public void setUserRealDescription(String userRealDescription) {
		this.userRealDescription = userRealDescription;
	}

	@Column(name = "LOGISTICS_COMPANY_ID")
	public Integer getLogisticsCompanyId() {
		return logisticsCompanyId;
	}

	public void setLogisticsCompanyId(Integer logisticsCompanyId) {
		this.logisticsCompanyId = logisticsCompanyId;
	}

	@Column(name = "LOGISTICS_COMPANY_NAME", length=100)
	public String getLogisticsCompanyName() {
		return logisticsCompanyName;
	}

	public void setLogisticsCompanyName(String logisticsCompanyName) {
		this.logisticsCompanyName = logisticsCompanyName;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

}