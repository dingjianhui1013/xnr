/**
 *2012-5-10 下午3:52:29
 */
package com.xnradmin.core.action.mall.order;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.cntinker.util.StringHelper;
import com.xnradmin.core.service.mall.clientUser.ClientUserInfoService;
import com.xnradmin.core.service.mall.clientUser.ClientUserRegionInfoService;
import com.xnradmin.constant.AjaxResult;
import com.xnradmin.constant.StrutsResMSG;
import com.xnradmin.constant.ViewConstant;
import com.xnradmin.core.action.ParentAction;
import com.xnradmin.core.service.StaffService;
import com.xnradmin.core.service.common.status.StatusService;
import com.xnradmin.core.service.mall.commodity.GoodsDishRelationService;
import com.xnradmin.core.service.mall.commodity.GoodsService;
import com.xnradmin.core.service.mall.order.OrderGoodsRelationService;
import com.xnradmin.core.service.mall.order.OrderRecordService;
import com.xnradmin.core.service.mall.order.PurchaseService;
import com.xnradmin.core.service.mall.seting.LogisticsCompanyService;
import com.xnradmin.core.service.mall.seting.PrimaryConfigurationService;
import com.xnradmin.po.CommonStaff;
import com.xnradmin.po.client.ClientUserInfo;
import com.xnradmin.po.client.ClientUserRegionInfo;
import com.xnradmin.po.common.status.Status;
import com.xnradmin.po.mall.commodity.Goods;
import com.xnradmin.po.mall.order.OrderGoodsRelation;
import com.xnradmin.po.mall.order.OrderRecord;
import com.xnradmin.po.mall.region.Area;
import com.xnradmin.po.mall.region.City;
import com.xnradmin.po.mall.region.Country;
import com.xnradmin.po.mall.region.Province;
import com.xnradmin.po.mall.seting.LogisticsCompany;
import com.xnradmin.po.mall.seting.PrimaryConfiguration;
import com.xnradmin.vo.StaffVO;
import com.xnradmin.vo.mall.CommodityVO;
import com.xnradmin.vo.mall.OrderVO;

/**
 * @autohr: xiang_liu
 * 
 */
@Controller
@Scope("prototype")
@Namespace("/page/wx/admin/orderrecord")
@ParentPackage("json-default")
public class OrderRecordAction extends ParentAction {

	@Autowired
	private OrderRecordService orderRecordService;

	@Autowired
	private OrderGoodsRelationService orderGoodsRelationService;

	@Autowired
	private GoodsService goodsService;

	@Autowired
	private GoodsDishRelationService goodsDishRelationService;

	@Autowired
	private ClientUserInfoService clientUserInfoService;

	@Autowired
	private ClientUserRegionInfoService clientUserRegionInfoService;

	@Autowired
	private PrimaryConfigurationService primaryConfigurationService;

	@Autowired
	private LogisticsCompanyService logisticsCompanyService;

	@Autowired
	private PurchaseService purchaseService;
	
	@Autowired
	private StatusService statusService;

	@Autowired
	private StaffService staffService;

	// orderRecford
	private String orderRecordId;

	private String clientUserId; // 用户ID

	private String clientUserName; // 用户昵称

	private String clientUserMobile; // 用户手机号

	private String clientUserEmail; // 用户邮箱

	private String clientUserSex; // 用户性别

	private String clientUserType; // 用户类型（微信，APP，WEB，电话）

	private String clientUserTypeName; // 用户类型名称（微信，APP，WEB，电话）

	private String clientUserInfoStatus; // 用户状态（正常，屏蔽）

	private String clientUserInfoStatusName; // 用户状态（正常，屏蔽）

	private String clientUserInfoType; // 用户类型（客户端用户，WX用户，电话用户）

	private String clientUserInfoTypeName; // 用户类型（客户端用户，WX用户，电话用户）

	private String wxOpenId; // 微信OpenId

	private String staffId; // 隶属用户Id

	private String userRealMobile; // 收货人用户手机号

	private String userRealPlane; // 收货人用户座机

	private String userRealName; // 收货人名称

	private String countryId; // 收货人国家

	private String countryName; // 收货人国家名称

	private String provinceId; // 收货人省份

	private String provinceName; // 收货人省份名称

	private String cityId; // 收货人城市

	private String cityName; // 收货人城市名称

	private String areaId; // 收货人区县

	private String areaName; // 收货人区县名称

	private String userRealAddress; // 收货人地址

	private String userRealPostcode; // 邮政编码

	private String userRealDescription; // 备注

	private String userRealTime; // 用户收货时间

	private String userRealStartTime; // 用户收货开始时间

	private String userRealEndTime; // 用户收货结束时间

	private String paymentType; // 支付类型，充值账户支付，单次支付

	private String paymentTypeName; // 支付类型名称，充值账户支付，单次支付

	private String paymentStatus; // 支付状态，已支付，待支付，退款

	private String paymentStatusName; // 支付状态名称，已支付，待支付，退款

	private String paymentProvider; // 支付提供者，微信，支付宝，银联

	private String paymentProviderName; // 支付提供者名称，微信，支付宝，银联

	private String paymentTime; // 支付时间

	private String paymentStartTime; // 支付开始时间

	private String paymentEndTime; // 支付结束时间

	private String operateTime; // 订单操作时间（待处理，处理中，处理完成，订单退单）

	private String operateStartTime; // 订单操作开始时间（待处理，处理中，处理完成，订单退单）

	private String operateEndTime; // 订单操作结束时间（待处理，处理中，处理完成，订单退单）

	private String operateStatus; // 订单操作状态（待处理，处理中，处理完成，订单退单）

	private String operateStatusName; // 订单操作状态名称（待处理，处理中，处理完成，订单退单）

	private String createTime; // 订单生成时间

	private String createStartTime; // 订单生成开始时间

	private String createEndTime; // 订单生成结束时间

	private String originalPrice; // 原始结算价格

	private String totalPrice; // 最终结算价格

	private String logisticsCompanyId; // 配送公司ID

	private String logisticsCompanyName; // 配送公司名称

	private String deliveryStaffId; // 送货人员ID

	private String deliveryStaffName; // 送货人员姓名

	private String deliveryStaffMobile; // 送货人员手机号

	private String deliveryStartTime; // 送货起始时间

	private String deliveryStartStartTime; // 送货查询起始时间

	private String deliveryStartEndTime; // 送货查询结束时间

	private String deliveryEndTime; // 送货送达时间

	private String deliveryEndStartTime; // 送货查询起始送达时间

	private String deliveryEndEndTime; // 送货查询结束送达时间

	private String deliveryStatus; // 配送状态（未发货，已发货）

	private String deliveryStatusName; // 配送状态名称（未发货，已发货）

	private String sourceId; // 该订单用户推广来源ID（有可能是用户推广，代理商推广，线上推广）

	private String sourceName; // 该订单用户推广来源名称（有可能是用户推广，代理商推广，线上推广）

	private String sourceType; // 该订单用户推广来源类型 （有可能是用户推广，代理商推广，线上推广）

	private String sourceTypeName; // 该订单用户推广来源类型名称 （有可能是用户推广，代理商推广，线上推广）

	private String serNo; // 银行流水号

	private String sellerId; // 代理商ID

	private String sellerName; // 代理商名称

	private String cusId; // 合作方管理

	private String cusName; // 合作方名称

	private String primaryConfigurationId; // 对应商城ID

	private String primaryConfigurationName; // 对应商城名称

	// orderGoodsRelation
	private String orderGoodsRelationId;

	private String orderGoodsRelationOrderRecordId; // 订单ID
	
	private String orderNo; // 自定义订单编号

	private String orderGoodsRelationClientUserId; // 用户ID

	private String orderGoodsRelationGoodsId; // 商品Id

	private String orderGoodsRelationGoodsCount; // 商品数量

	private String orderGoodsRelationCurrentPrice; // 当前价格

	private String orderGoodsRelationCurrentPriceType; // 当前价格类型（原价，优惠价）

	private String orderGoodsRelationStaffId; // 隶属用户Id

	private String orderGoodsRelationPrimaryConfigurationId; // 对应商城ID

	private String orderGoodsRelationTime; // 生成时间

	// clientUserRegionInfo
	private String clientUserRegionInfoId;
	private String clientUserRegionInfoUserRealAddress;
	private String clientUserInfoId;
	private String clientUserRegionInfoUserRealMobile; // 收货人用户手机号
	private String clientUserRegionInfoUserRealPlane; // 收货人用户座机
	private String clientUserRegionInfoUserRealName; // 收货人名称
	private String clientUserRegionInfoUserRealPostcode; // 邮政编码
	private String clientUserRegionInfoCreateTime;
	private String clientUserRegionInfoModifyTime;
	private String clientUserRegionInfoUserMobile;
	private String clientUserRegionInfoWxnickname;
	private String clientUserRegionInfoWxopenuid;
	private String clientUserRegionInfoEmail;
	private String clientUserRegionInfoSourceType;

	private String address;
	private List<Status> paymentTypeList;
	private List<Status> paymentStatusList;
	private List<Status> paymentProviderList;
	private List<Status> operateStatusList;
	private List<Status> deliveryStatusList;
	private List<StaffVO> staffList;
	private List<Goods> allGoodsList;
	private List<ClientUserRegionInfo> clientUserRegionInfoList;
	private Map<String, OrderVO> goodsList;
	private List<OrderVO> dvList;
	private List<LogisticsCompany> logisticsCompanyList;
	private CommonStaff currentStaff;
	private CommonStaff deliveryStaff;
	private List<OrderVO> voList;
	private CommodityVO commodityVO;
	private OrderRecord orderRecord;
	private Goods goods;
	private Status status;
	private Status isDiscount;
	private Country countryPO;
	private Province provincePO;
	private City cityPO;
	private Area areaPO;

	public OrderRecordService getOrderRecordService() {
		return orderRecordService;
	}

	public void setOrderRecordService(OrderRecordService orderRecordService) {
		this.orderRecordService = orderRecordService;
	}

	public OrderGoodsRelationService getOrderGoodsRelationService() {
		return orderGoodsRelationService;
	}

	public void setOrderGoodsRelationService(
			OrderGoodsRelationService orderGoodsRelationService) {
		this.orderGoodsRelationService = orderGoodsRelationService;
	}

	public GoodsService getGoodsService() {
		return goodsService;
	}

	public void setGoodsService(GoodsService goodsService) {
		this.goodsService = goodsService;
	}

	public GoodsDishRelationService getGoodsDishRelationService() {
		return goodsDishRelationService;
	}

	public void setGoodsDishRelationService(
			GoodsDishRelationService goodsDishRelationService) {
		this.goodsDishRelationService = goodsDishRelationService;
	}

	public StatusService getStatusService() {
		return statusService;
	}

	public void setStatusService(StatusService statusService) {
		this.statusService = statusService;
	}

	public StaffService getStaffService() {
		return staffService;
	}

	public void setStaffService(StaffService staffService) {
		this.staffService = staffService;
	}

	public String getOrderRecordId() {
		return orderRecordId;
	}

	public void setOrderRecordId(String orderRecordId) {
		this.orderRecordId = orderRecordId;
	}

	public String getClientUserId() {
		return clientUserId;
	}

	public void setClientUserId(String clientUserId) {
		this.clientUserId = clientUserId;
	}

	public String getClientUserName() {
		return clientUserName;
	}

	public void setClientUserName(String clientUserName) {
		this.clientUserName = clientUserName;
	}

	public String getClientUserMobile() {
		return clientUserMobile;
	}

	public void setClientUserMobile(String clientUserMobile) {
		this.clientUserMobile = clientUserMobile;
	}

	public String getClientUserEmail() {
		return clientUserEmail;
	}

	public void setClientUserEmail(String clientUserEmail) {
		this.clientUserEmail = clientUserEmail;
	}

	public String getClientUserSex() {
		return clientUserSex;
	}

	public void setClientUserSex(String clientUserSex) {
		this.clientUserSex = clientUserSex;
	}

	public String getClientUserType() {
		return clientUserType;
	}

	public void setClientUserType(String clientUserType) {
		this.clientUserType = clientUserType;
	}

	public String getClientUserTypeName() {
		return clientUserTypeName;
	}

	public void setClientUserTypeName(String clientUserTypeName) {
		this.clientUserTypeName = clientUserTypeName;
	}

	public String getWxOpenId() {
		return wxOpenId;
	}

	public void setWxOpenId(String wxOpenId) {
		this.wxOpenId = wxOpenId;
	}

	public String getStaffId() {
		return staffId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

	public String getUserRealMobile() {
		return userRealMobile;
	}

	public void setUserRealMobile(String userRealMobile) {
		this.userRealMobile = userRealMobile;
	}

	public String getUserRealPlane() {
		return userRealPlane;
	}

	public void setUserRealPlane(String userRealPlane) {
		this.userRealPlane = userRealPlane;
	}

	public String getUserRealName() {
		return userRealName;
	}

	public void setUserRealName(String userRealName) {
		this.userRealName = userRealName;
	}

	public String getCountryId() {
		return countryId;
	}

	public void setCountryId(String countryId) {
		this.countryId = countryId;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public String getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public String getCityId() {
		return cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getUserRealAddress() {
		return userRealAddress;
	}

	public void setUserRealAddress(String userRealAddress) {
		this.userRealAddress = userRealAddress;
	}

	public String getUserRealPostcode() {
		return userRealPostcode;
	}

	public void setUserRealPostcode(String userRealPostcode) {
		this.userRealPostcode = userRealPostcode;
	}

	public String getUserRealTime() {
		return userRealTime;
	}

	public void setUserRealTime(String userRealTime) {
		this.userRealTime = userRealTime;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public String getPaymentTypeName() {
		return paymentTypeName;
	}

	public void setPaymentTypeName(String paymentTypeName) {
		this.paymentTypeName = paymentTypeName;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public String getPaymentStatusName() {
		return paymentStatusName;
	}

	public void setPaymentStatusName(String paymentStatusName) {
		this.paymentStatusName = paymentStatusName;
	}

	public String getPaymentProvider() {
		return paymentProvider;
	}

	public void setPaymentProvider(String paymentProvider) {
		this.paymentProvider = paymentProvider;
	}

	public String getPaymentProviderName() {
		return paymentProviderName;
	}

	public void setPaymentProviderName(String paymentProviderName) {
		this.paymentProviderName = paymentProviderName;
	}

	public String getPaymentTime() {
		return paymentTime;
	}

	public void setPaymentTime(String paymentTime) {
		this.paymentTime = paymentTime;
	}

	public String getOperateTime() {
		return operateTime;
	}

	public void setOperateTime(String operateTime) {
		this.operateTime = operateTime;
	}

	public String getOperateStatus() {
		return operateStatus;
	}

	public void setOperateStatus(String operateStatus) {
		this.operateStatus = operateStatus;
	}

	public String getOperateStatusName() {
		return operateStatusName;
	}

	public void setOperateStatusName(String operateStatusName) {
		this.operateStatusName = operateStatusName;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getOriginalPrice() {
		return originalPrice;
	}

	public void setOriginalPrice(String originalPrice) {
		this.originalPrice = originalPrice;
	}

	public String getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getLogisticsCompanyId() {
		return logisticsCompanyId;
	}

	public void setLogisticsCompanyId(String logisticsCompanyId) {
		this.logisticsCompanyId = logisticsCompanyId;
	}

	public String getLogisticsCompanyName() {
		return logisticsCompanyName;
	}

	public void setLogisticsCompanyName(String logisticsCompanyName) {
		this.logisticsCompanyName = logisticsCompanyName;
	}

	public String getDeliveryStaffId() {
		return deliveryStaffId;
	}

	public void setDeliveryStaffId(String deliveryStaffId) {
		this.deliveryStaffId = deliveryStaffId;
	}

	public String getDeliveryStaffName() {
		return deliveryStaffName;
	}

	public void setDeliveryStaffName(String deliveryStaffName) {
		this.deliveryStaffName = deliveryStaffName;
	}

	public String getDeliveryStaffMobile() {
		return deliveryStaffMobile;
	}

	public void setDeliveryStaffMobile(String deliveryStaffMobile) {
		this.deliveryStaffMobile = deliveryStaffMobile;
	}

	public String getDeliveryStartTime() {
		return deliveryStartTime;
	}

	public void setDeliveryStartTime(String deliveryStartTime) {
		this.deliveryStartTime = deliveryStartTime;
	}

	public String getDeliveryEndTime() {
		return deliveryEndTime;
	}

	public void setDeliveryEndTime(String deliveryEndTime) {
		this.deliveryEndTime = deliveryEndTime;
	}

	public String getDeliveryStatus() {
		return deliveryStatus;
	}

	public void setDeliveryStatus(String deliveryStatus) {
		this.deliveryStatus = deliveryStatus;
	}

	public String getDeliveryStatusName() {
		return deliveryStatusName;
	}

	public void setDeliveryStatusName(String deliveryStatusName) {
		this.deliveryStatusName = deliveryStatusName;
	}

	public String getSourceId() {
		return sourceId;
	}

	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}

	public String getSourceName() {
		return sourceName;
	}

	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}

	public String getSourceType() {
		return sourceType;
	}

	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}

	public String getSourceTypeName() {
		return sourceTypeName;
	}

	public void setSourceTypeName(String sourceTypeName) {
		this.sourceTypeName = sourceTypeName;
	}

	public String getSerNo() {
		return serNo;
	}

	public void setSerNo(String serNo) {
		this.serNo = serNo;
	}

	public String getSellerId() {
		return sellerId;
	}

	public void setSellerId(String sellerId) {
		this.sellerId = sellerId;
	}

	public String getSellerName() {
		return sellerName;
	}

	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}

	public String getCusId() {
		return cusId;
	}

	public void setCusId(String cusId) {
		this.cusId = cusId;
	}

	public String getCusName() {
		return cusName;
	}

	public void setCusName(String cusName) {
		this.cusName = cusName;
	}

	public String getPrimaryConfigurationId() {
		return primaryConfigurationId;
	}

	public void setPrimaryConfigurationId(String primaryConfigurationId) {
		this.primaryConfigurationId = primaryConfigurationId;
	}

	public String getPrimaryConfigurationName() {
		return primaryConfigurationName;
	}

	public void setPrimaryConfigurationName(String primaryConfigurationName) {
		this.primaryConfigurationName = primaryConfigurationName;
	}

	public String getOrderGoodsRelationId() {
		return orderGoodsRelationId;
	}

	public void setOrderGoodsRelationId(String orderGoodsRelationId) {
		this.orderGoodsRelationId = orderGoodsRelationId;
	}

	public String getOrderGoodsRelationOrderRecordId() {
		return orderGoodsRelationOrderRecordId;
	}

	public void setOrderGoodsRelationOrderRecordId(
			String orderGoodsRelationOrderRecordId) {
		this.orderGoodsRelationOrderRecordId = orderGoodsRelationOrderRecordId;
	}

	public String getOrderGoodsRelationClientUserId() {
		return orderGoodsRelationClientUserId;
	}

	public void setOrderGoodsRelationClientUserId(
			String orderGoodsRelationClientUserId) {
		this.orderGoodsRelationClientUserId = orderGoodsRelationClientUserId;
	}

	public String getOrderGoodsRelationGoodsId() {
		return orderGoodsRelationGoodsId;
	}

	public void setOrderGoodsRelationGoodsId(String orderGoodsRelationGoodsId) {
		this.orderGoodsRelationGoodsId = orderGoodsRelationGoodsId;
	}

	public String getOrderGoodsRelationGoodsCount() {
		return orderGoodsRelationGoodsCount;
	}

	public void setOrderGoodsRelationGoodsCount(
			String orderGoodsRelationGoodsCount) {
		this.orderGoodsRelationGoodsCount = orderGoodsRelationGoodsCount;
	}

	public String getOrderGoodsRelationCurrentPrice() {
		return orderGoodsRelationCurrentPrice;
	}

	public void setOrderGoodsRelationCurrentPrice(
			String orderGoodsRelationCurrentPrice) {
		this.orderGoodsRelationCurrentPrice = orderGoodsRelationCurrentPrice;
	}

	public String getOrderGoodsRelationCurrentPriceType() {
		return orderGoodsRelationCurrentPriceType;
	}

	public void setOrderGoodsRelationCurrentPriceType(
			String orderGoodsRelationCurrentPriceType) {
		this.orderGoodsRelationCurrentPriceType = orderGoodsRelationCurrentPriceType;
	}

	public String getOrderGoodsRelationStaffId() {
		return orderGoodsRelationStaffId;
	}

	public void setOrderGoodsRelationStaffId(String orderGoodsRelationStaffId) {
		this.orderGoodsRelationStaffId = orderGoodsRelationStaffId;
	}

	public String getOrderGoodsRelationPrimaryConfigurationId() {
		return orderGoodsRelationPrimaryConfigurationId;
	}

	public void setOrderGoodsRelationPrimaryConfigurationId(
			String orderGoodsRelationPrimaryConfigurationId) {
		this.orderGoodsRelationPrimaryConfigurationId = orderGoodsRelationPrimaryConfigurationId;
	}

	public String getOrderGoodsRelationTime() {
		return orderGoodsRelationTime;
	}

	public void setOrderGoodsRelationTime(String orderGoodsRelationTime) {
		this.orderGoodsRelationTime = orderGoodsRelationTime;
	}

	public List<Status> getPaymentTypeList() {
		return paymentTypeList;
	}

	public void setPaymentTypeList(List<Status> paymentTypeList) {
		this.paymentTypeList = paymentTypeList;
	}

	public List<Status> getPaymentStatusList() {
		return paymentStatusList;
	}

	public void setPaymentStatusList(List<Status> paymentStatusList) {
		this.paymentStatusList = paymentStatusList;
	}

	public List<Status> getPaymentProviderList() {
		return paymentProviderList;
	}

	public void setPaymentProviderList(List<Status> paymentProviderList) {
		this.paymentProviderList = paymentProviderList;
	}

	public List<Status> getOperateStatusList() {
		return operateStatusList;
	}

	public void setOperateStatusList(List<Status> operateStatusList) {
		this.operateStatusList = operateStatusList;
	}

	public List<Status> getDeliveryStatusList() {
		return deliveryStatusList;
	}

	public void setDeliveryStatusList(List<Status> deliveryStatusList) {
		this.deliveryStatusList = deliveryStatusList;
	}

	public List<Goods> getAllGoodsList() {
		return allGoodsList;
	}

	public void setAllGoodsList(List<Goods> allGoodsList) {
		this.allGoodsList = allGoodsList;
	}

	public List<StaffVO> getStaffList() {
		return staffList;
	}

	public void setStaffList(List<StaffVO> staffList) {
		this.staffList = staffList;
	}

	public CommonStaff getCurrentStaff() {
		return currentStaff;
	}

	public void setCurrentStaff(CommonStaff currentStaff) {
		this.currentStaff = currentStaff;
	}

	public Map<String, OrderVO> getGoodsList() {
		return goodsList;
	}

	public void setGoodsList(Map<String, OrderVO> goodsList) {
		this.goodsList = goodsList;
	}

	public List<OrderVO> getDvList() {
		return dvList;
	}

	public void setDvList(List<OrderVO> dvList) {
		this.dvList = dvList;
	}

	public List<OrderVO> getVoList() {
		return voList;
	}

	public void setVoList(List<OrderVO> voList) {
		this.voList = voList;
	}

	public CommodityVO getCommodityVO() {
		return commodityVO;
	}

	public void setCommodityVO(CommodityVO commodityVO) {
		this.commodityVO = commodityVO;
	}

	public Goods getGoods() {
		return goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Status getIsDiscount() {
		return isDiscount;
	}

	public void setIsDiscount(Status isDiscount) {
		this.isDiscount = isDiscount;
	}

	public String getUserRealStartTime() {
		return userRealStartTime;
	}

	public void setUserRealStartTime(String userRealStartTime) {
		this.userRealStartTime = userRealStartTime;
	}

	public String getUserRealEndTime() {
		return userRealEndTime;
	}

	public void setUserRealEndTime(String userRealEndTime) {
		this.userRealEndTime = userRealEndTime;
	}

	public String getDeliveryStartStartTime() {
		return deliveryStartStartTime;
	}

	public void setDeliveryStartStartTime(String deliveryStartStartTime) {
		this.deliveryStartStartTime = deliveryStartStartTime;
	}

	public String getDeliveryStartEndTime() {
		return deliveryStartEndTime;
	}

	public void setDeliveryStartEndTime(String deliveryStartEndTime) {
		this.deliveryStartEndTime = deliveryStartEndTime;
	}

	public String getDeliveryEndStartTime() {
		return deliveryEndStartTime;
	}

	public void setDeliveryEndStartTime(String deliveryEndStartTime) {
		this.deliveryEndStartTime = deliveryEndStartTime;
	}

	public String getDeliveryEndEndTime() {
		return deliveryEndEndTime;
	}

	public void setDeliveryEndEndTime(String deliveryEndEndTime) {
		this.deliveryEndEndTime = deliveryEndEndTime;
	}

	public String getPaymentStartTime() {
		return paymentStartTime;
	}

	public void setPaymentStartTime(String paymentStartTime) {
		this.paymentStartTime = paymentStartTime;
	}

	public String getPaymentEndTime() {
		return paymentEndTime;
	}

	public void setPaymentEndTime(String paymentEndTime) {
		this.paymentEndTime = paymentEndTime;
	}

	public String getOperateStartTime() {
		return operateStartTime;
	}

	public void setOperateStartTime(String operateStartTime) {
		this.operateStartTime = operateStartTime;
	}

	public String getOperateEndTime() {
		return operateEndTime;
	}

	public void setOperateEndTime(String operateEndTime) {
		this.operateEndTime = operateEndTime;
	}

	public String getCreateStartTime() {
		return createStartTime;
	}

	public void setCreateStartTime(String createStartTime) {
		this.createStartTime = createStartTime;
	}

	public String getCreateEndTime() {
		return createEndTime;
	}

	public void setCreateEndTime(String createEndTime) {
		this.createEndTime = createEndTime;
	}

	public Country getCountryPO() {
		return countryPO;
	}

	public void setCountryPO(Country countryPO) {
		this.countryPO = countryPO;
	}

	public Province getProvincePO() {
		return provincePO;
	}

	public void setProvincePO(Province provincePO) {
		this.provincePO = provincePO;
	}

	public City getCityPO() {
		return cityPO;
	}

	public void setCityPO(City cityPO) {
		this.cityPO = cityPO;
	}

	public Area getAreaPO() {
		return areaPO;
	}

	public void setAreaPO(Area areaPO) {
		this.areaPO = areaPO;
	}

	public String getClientUserInfoStatus() {
		return clientUserInfoStatus;
	}

	public void setClientUserInfoStatus(String clientUserInfoStatus) {
		this.clientUserInfoStatus = clientUserInfoStatus;
	}

	public String getClientUserInfoStatusName() {
		return clientUserInfoStatusName;
	}

	public void setClientUserInfoStatusName(String clientUserInfoStatusName) {
		this.clientUserInfoStatusName = clientUserInfoStatusName;
	}

	public String getClientUserInfoType() {
		return clientUserInfoType;
	}

	public void setClientUserInfoType(String clientUserInfoType) {
		this.clientUserInfoType = clientUserInfoType;
	}

	public String getClientUserInfoTypeName() {
		return clientUserInfoTypeName;
	}

	public void setClientUserInfoTypeName(String clientUserInfoTypeName) {
		this.clientUserInfoTypeName = clientUserInfoTypeName;
	}

	public CommonStaff getDeliveryStaff() {
		return deliveryStaff;
	}

	public void setDeliveryStaff(CommonStaff deliveryStaff) {
		this.deliveryStaff = deliveryStaff;
	}

	public OrderRecord getOrderRecord() {
		return orderRecord;
	}

	public void setOrderRecord(OrderRecord orderRecord) {
		this.orderRecord = orderRecord;
	}

	public String getClientUserRegionInfoId() {
		return clientUserRegionInfoId;
	}

	public void setClientUserRegionInfoId(String clientUserRegionInfoId) {
		this.clientUserRegionInfoId = clientUserRegionInfoId;
	}

	public String getUserRealDescription() {
		return userRealDescription;
	}

	public void setUserRealDescription(String userRealDescription) {
		this.userRealDescription = userRealDescription;
	}

	public List<ClientUserRegionInfo> getClientUserRegionInfoList() {
		return clientUserRegionInfoList;
	}

	public void setClientUserRegionInfoList(
			List<ClientUserRegionInfo> clientUserRegionInfoList) {
		this.clientUserRegionInfoList = clientUserRegionInfoList;
	}

	public String getClientUserRegionInfoUserRealAddress() {
		return clientUserRegionInfoUserRealAddress;
	}

	public void setClientUserRegionInfoUserRealAddress(
			String clientUserRegionInfoUserRealAddress) {
		this.clientUserRegionInfoUserRealAddress = clientUserRegionInfoUserRealAddress;
	}

	public String getClientUserInfoId() {
		return clientUserInfoId;
	}

	public void setClientUserInfoId(String clientUserInfoId) {
		this.clientUserInfoId = clientUserInfoId;
	}

	public String getClientUserRegionInfoUserRealMobile() {
		return clientUserRegionInfoUserRealMobile;
	}

	public void setClientUserRegionInfoUserRealMobile(
			String clientUserRegionInfoUserRealMobile) {
		this.clientUserRegionInfoUserRealMobile = clientUserRegionInfoUserRealMobile;
	}

	public String getClientUserRegionInfoUserRealPlane() {
		return clientUserRegionInfoUserRealPlane;
	}

	public void setClientUserRegionInfoUserRealPlane(
			String clientUserRegionInfoUserRealPlane) {
		this.clientUserRegionInfoUserRealPlane = clientUserRegionInfoUserRealPlane;
	}

	public String getClientUserRegionInfoUserRealName() {
		return clientUserRegionInfoUserRealName;
	}

	public void setClientUserRegionInfoUserRealName(
			String clientUserRegionInfoUserRealName) {
		this.clientUserRegionInfoUserRealName = clientUserRegionInfoUserRealName;
	}

	public String getClientUserRegionInfoUserRealPostcode() {
		return clientUserRegionInfoUserRealPostcode;
	}

	public void setClientUserRegionInfoUserRealPostcode(
			String clientUserRegionInfoUserRealPostcode) {
		this.clientUserRegionInfoUserRealPostcode = clientUserRegionInfoUserRealPostcode;
	}

	public String getClientUserRegionInfoCreateTime() {
		return clientUserRegionInfoCreateTime;
	}

	public void setClientUserRegionInfoCreateTime(
			String clientUserRegionInfoCreateTime) {
		this.clientUserRegionInfoCreateTime = clientUserRegionInfoCreateTime;
	}

	public String getClientUserRegionInfoModifyTime() {
		return clientUserRegionInfoModifyTime;
	}

	public void setClientUserRegionInfoModifyTime(
			String clientUserRegionInfoModifyTime) {
		this.clientUserRegionInfoModifyTime = clientUserRegionInfoModifyTime;
	}

	public String getClientUserRegionInfoUserMobile() {
		return clientUserRegionInfoUserMobile;
	}

	public void setClientUserRegionInfoUserMobile(
			String clientUserRegionInfoUserMobile) {
		this.clientUserRegionInfoUserMobile = clientUserRegionInfoUserMobile;
	}

	public String getClientUserRegionInfoWxnickname() {
		return clientUserRegionInfoWxnickname;
	}

	public void setClientUserRegionInfoWxnickname(
			String clientUserRegionInfoWxnickname) {
		this.clientUserRegionInfoWxnickname = clientUserRegionInfoWxnickname;
	}

	public String getClientUserRegionInfoWxopenuid() {
		return clientUserRegionInfoWxopenuid;
	}

	public void setClientUserRegionInfoWxopenuid(
			String clientUserRegionInfoWxopenuid) {
		this.clientUserRegionInfoWxopenuid = clientUserRegionInfoWxopenuid;
	}

	public String getClientUserRegionInfoEmail() {
		return clientUserRegionInfoEmail;
	}

	public void setClientUserRegionInfoEmail(String clientUserRegionInfoEmail) {
		this.clientUserRegionInfoEmail = clientUserRegionInfoEmail;
	}

	public String getClientUserRegionInfoSourceType() {
		return clientUserRegionInfoSourceType;
	}

	public void setClientUserRegionInfoSourceType(
			String clientUserRegionInfoSourceType) {
		this.clientUserRegionInfoSourceType = clientUserRegionInfoSourceType;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<LogisticsCompany> getLogisticsCompanyList() {
		return logisticsCompanyList;
	}

	public void setLogisticsCompanyList(
			List<LogisticsCompany> logisticsCompanyList) {
		this.logisticsCompanyList = logisticsCompanyList;
	}

	@Override
	public boolean isPublic() {
		return false;
	};

	static Log log = LogFactory.getLog(OrderRecordAction.class);

	/**
	 * 跳转到信息页。。
	 * 
	 * @return
	 */
	@Action(value = "info", results = {
			@Result(name = StrutsResMSG.SUCCESS, location = "/wx/admin/orderrecord/info.jsp"),
			@Result(name = StrutsResMSG.FAILED, location = "/wx/admin/orderrecord/info.jsp") })
	public String info() {
		setPageInfo();
		return StrutsResMSG.SUCCESS;
	}

	private void setPageInfo() {
		// 设置排序
		setDateTime();
		findPaymentStatusList();
		findPaymentProviderList();
		findOperateStatusList();
		findDeliveryStatus();
		findPaymentTypeList();
		this.voList = orderRecordService.listVO(orderNo,
				orderRecordId,
				orderGoodsRelationClientUserId, clientUserName,
				clientUserMobile, clientUserEmail, clientUserSex,
				clientUserType, clientUserTypeName, wxOpenId,
				orderGoodsRelationStaffId, userRealMobile, userRealPlane,
				userRealName, countryId, countryName, provinceId, provinceName,
				cityId, cityName, areaId, areaName, userRealAddress,
				userRealPostcode, userRealStartTime, userRealEndTime,
				paymentType, paymentTypeName, paymentStatus, paymentStatusName,
				paymentProvider, paymentProviderName, paymentStartTime,
				paymentEndTime, operateStartTime, operateEndTime,
				operateStatus, operateStatusName, createStartTime,
				createEndTime, originalPrice, totalPrice, logisticsCompanyId,
				logisticsCompanyName, deliveryStaffId, deliveryStaffName,
				deliveryStaffMobile, deliveryStartStartTime,
				deliveryStartEndTime, deliveryEndStartTime, deliveryEndEndTime,
				deliveryStatus, deliveryStatusName, sourceId, sourceName,
				sourceType, sourceTypeName, serNo, sellerId, sellerName, cusId,
				cusName, orderGoodsRelationPrimaryConfigurationId,
				primaryConfigurationName, getPageNum(), getNumPerPage(),
				orderField, orderDirection);

		this.totalCount = orderRecordService.getCount(orderNo,
				orderRecordId,
				orderGoodsRelationClientUserId, clientUserName,
				clientUserMobile, clientUserEmail, clientUserSex,
				clientUserType, clientUserTypeName, wxOpenId,
				orderGoodsRelationStaffId, userRealMobile, userRealPlane,
				userRealName, countryId, countryName, provinceId, provinceName,
				cityId, cityName, areaId, areaName, userRealAddress,
				userRealPostcode, userRealStartTime, userRealEndTime,
				paymentType, paymentTypeName, paymentStatus, paymentStatusName,
				paymentProvider, paymentProviderName, paymentStartTime,
				paymentEndTime, operateStartTime, operateEndTime,
				operateStatus, operateStatusName, createStartTime,
				createEndTime, originalPrice, totalPrice, logisticsCompanyId,
				logisticsCompanyName, deliveryStaffId, deliveryStaffName,
				deliveryStaffMobile, deliveryStartStartTime,
				deliveryStartEndTime, deliveryEndStartTime, deliveryEndEndTime,
				deliveryStatus, deliveryStatusName, sourceId, sourceName,
				sourceType, sourceTypeName, serNo, sellerId, sellerName, cusId,
				cusName, orderGoodsRelationPrimaryConfigurationId,
				primaryConfigurationName);
	}

	/**
	 * 加载所有支付状态
	 */
	private void findPaymentStatusList() {
		this.paymentStatusList = statusService.find(OrderRecord.class,
				"paymentStatus");
	}

	/**
	 * 加载所有支付提供者类型
	 */
	private void findPaymentProviderList() {
		this.paymentProviderList = statusService.find(OrderRecord.class,
				"paymentProvider");
	}

	/**
	 * 加载所有订单处理状态
	 */
	private void findOperateStatusList() {
		this.operateStatusList = statusService.find(OrderRecord.class,
				"operateStatus");
	}

	/**
	 * 加载所有订单派送状态
	 */
	private void findDeliveryStatus() {
		this.deliveryStatusList = statusService.find(OrderRecord.class,
				"deliveryStatus");
	}

	/**
	 * 加载所有支付类型
	 */
	private void findPaymentTypeList() {
		this.paymentTypeList = statusService.find(OrderRecord.class,
				"paymentType");
	}

	/*
	 * 加载所有用户
	 */
	private void findStaffList() {
		this.staffList = staffService.listVO("","","", "", "", "", "", "", "", 0, 0,
				0, "", "");
	}

	private void findLogisticsCompanyList() {
		this.logisticsCompanyList = logisticsCompanyService.listAll();
	}

	@Action(value = "addinfo", results = {
			@Result(name = StrutsResMSG.SUCCESS, location = "/wx/admin/orderrecord/add.jsp"),
			@Result(name = StrutsResMSG.FAILED, location = "/wx/admin/orderrecord/add.jsp") })
	public String addInfo() {
		if(!StringHelper.isNull(clientUserId)){
			clientUserRegionInfoList = clientUserRegionInfoService.findByProperty(
				"clientUserInfoId", Integer.parseInt(clientUserId));
		}
		findPaymentProviderList();
		findLogisticsCompanyList();
		currentStaff = super.getCurrentStaff();
		return StrutsResMSG.SUCCESS;
	}

	/**
	 * 保存对象接口
	 * 
	 * @return String
	 * @throws Exception
	 */
	@Action(value = "add", results = { @Result(name = StrutsResMSG.SUCCESS, type = "plainText") })
	public String add() throws Exception {
		// 取得当前登录人信息
		currentStaff = super.getCurrentStaff();
		ClientUserInfo clientUserInfo = clientUserInfoService
				.findByid(clientUserId);
		// 批量增加商品
		if (goodsList == null) {
			super.error("未填写商品");
		} else {
			System.out.println("start:::");
			// 判定用户是否为新用户，新用户自动添加
			// if (StringHelper.isNull(clientUserId)) {
			// if (countryPO != null && provincePO != null && cityPO != null
			// && areaPO != null
			// && !StringHelper.isNull(userRealAddress)
			// && !StringHelper.isNull(userRealName)
			// && !StringHelper.isNull(userRealMobile)) {
			// ClientUserInfo po = new ClientUserInfo();
			// po.setMobile(clientUserMobile);
			// if (!StringHelper.isNull(clientUserInfoStatus)) {
			// po.setStatus(Integer.parseInt(clientUserInfoStatus));
			// Status statusCode = statusService
			// .findByid(clientUserInfoStatus);
			// po.setStatusName(statusCode.getStatusName());
			// this.clientUserInfoStatusName = statusCode
			// .getStatusName();
			// }
			// if (!StringHelper.isNull(clientUserInfoType)) {
			// po.setType(Integer.parseInt(clientUserInfoType));
			// Status typeCode = statusService
			// .findByid(clientUserInfoType);
			// po.setTypeName(typeCode.getStatusName());
			// this.clientUserInfoTypeName = typeCode.getStatusName();
			// }
			// po.setCreateTime(new Timestamp(System.currentTimeMillis()));
			// po.setLastLoginTime(new Timestamp(System
			// .currentTimeMillis()));
			// po.setModifyTime(new Timestamp(System.currentTimeMillis()));
			// if (!StringHelper.isNull(sourceType)) {
			// po.setSourceType(Integer.parseInt(sourceType));
			// Status sourceTypeCode = statusService
			// .findByid(sourceType);
			// po.setSourceTypeName(sourceTypeCode.getStatusName());
			// }
			// Map map = clientUserInfoService.saveRetrunId(po);
			// if (map.get("code").equals("1")) {
			// super.error("用户已存在");
			// } else {
			// // 正常添加用户以后增加用户地址
			// clientUserId = map.get("serializable").toString();
			// ClientUserRegionInfo cur = new ClientUserRegionInfo();
			// if (countryPO != null) {
			// cur.setCountryId(countryPO.getId());
			// cur.setCountryName(countryPO.getCountry());
			// }
			// if (provincePO != null) {
			// cur.setProvinceId(provincePO.getId());
			// cur.setProvinceName(provincePO.getProvince());
			// }
			// if (cityPO != null) {
			// cur.setCityId(cityPO.getId());
			// cur.setCityName(cityPO.getCity());
			// }
			// if (areaPO != null) {
			// cur.setAreaId(areaPO.getId());
			// cur.setAreaName(areaPO.getArea());
			// }
			// if (!StringHelper.isNull(userRealAddress)) {
			// cur.setUserRealAddress(userRealAddress);
			// }
			// if (map.get("serializable") != null) {
			// cur.setClientUserInfoId(Integer
			// .parseInt(clientUserId));
			// }
			// if (!StringHelper.isNull(userRealMobile)) {
			// cur.setUserRealMobile(userRealMobile);
			// }
			// if (!StringHelper.isNull(userRealPlane)) {
			// cur.setUserRealPlane(userRealPlane);
			// }
			// if (!StringHelper.isNull(userRealName)) {
			// cur.setUserRealName(userRealName);
			// }
			// if (!StringHelper.isNull(userRealPostcode)) {
			// cur.setUserRealPostcode(userRealPostcode);
			// }
			// po.setCreateTime(new Timestamp(System
			// .currentTimeMillis()));
			// po.setModifyTime(new Timestamp(System
			// .currentTimeMillis()));
			// int re = clientUserRegionInfoService.save(cur);
			// if (re == 1) {
			// super.error("绑定地区超过20个");
			// }
			// }
			//
			// } else {
			// super.error("订单信息填写不全");
			// }
			// } else
			ClientUserRegionInfo cri = new ClientUserRegionInfo();
			if (!StringHelper.isNull(clientUserRegionInfoId)) {
				if (clientUserRegionInfoId.equals("new") && provincePO != null
						&& cityPO != null && areaPO != null
						&& !StringHelper.isNull(userRealAddress)
						&& !StringHelper.isNull(userRealName)
						&& !StringHelper.isNull(userRealMobile)) {

					cri.setCountryId(1);
					cri.setCountryName("中国");

					if (provincePO != null) {
						cri.setProvinceId(provincePO.getId());
						cri.setProvinceName(provincePO.getProvince());
					}
					if (cityPO != null) {
						cri.setCityId(cityPO.getId());
						cri.setCityName(cityPO.getCity());
					}
					if (areaPO != null) {
						cri.setAreaId(areaPO.getId());
						cri.setAreaName(areaPO.getArea());
					}
					if (!StringHelper.isNull(userRealAddress)) {
						cri.setUserRealAddress(userRealAddress);
					}
					if (!StringHelper.isNull(clientUserId)) {
						cri.setClientUserInfoId(Integer.parseInt(clientUserId));
					}
					if (!StringHelper.isNull(userRealMobile)) {
						cri.setUserRealMobile(userRealMobile);
					}
					if (!StringHelper.isNull(userRealPlane)) {
						cri.setUserRealPlane(userRealPlane);
					}
					if (!StringHelper.isNull(userRealName)) {
						cri.setUserRealName(userRealName);
					}
					if (!StringHelper.isNull(userRealPostcode)) {
						cri.setUserRealPostcode(userRealPostcode);
					}
					cri.setCreateTime(new Timestamp(System.currentTimeMillis()));
					cri.setModifyTime(new Timestamp(System.currentTimeMillis()));
					int re = clientUserRegionInfoService.save(cri);
					if(re==1){
						super.error("该用户地区超过20个。请删除再添加。");
					}
				} else {
					cri = clientUserRegionInfoService
							.findByid(clientUserRegionInfoId);
				}
			}
			// 增加新订单
			OrderRecord orderRecord = new OrderRecord();
			if (!StringHelper.isNull(clientUserId)) {
				orderRecord.setClientUserId(Integer.parseInt(clientUserId));
			}
			String outTradeNo = StringHelper.getSystime("yyyyMMddHHmmss")
					+ StringHelper.getRandom(5);
			orderRecord.setOrderNo(outTradeNo);
			orderRecord.setClientUserId(clientUserInfo.getId());
			orderRecord.setClientUserName(clientUserInfo.getNickName());
			orderRecord.setClientUserMobile(clientUserInfo.getMobile());
			orderRecord.setClientUserEmail(clientUserInfo.getEmail());
			orderRecord.setStaffId(staffId);
			orderRecord.setUserRealMobile(userRealMobile);
			orderRecord.setCountryId(cri.getCountryId());
			orderRecord.setCountryName(cri.getCountryName());
			orderRecord.setProvinceId(cri.getProvinceId());
			orderRecord.setProvinceName(cri.getProvinceName());
			orderRecord.setCityId(cri.getCityId());
			orderRecord.setCityName(cri.getCityName());
			orderRecord.setAreaId(cri.getAreaId());
			orderRecord.setAreaName(cri.getAreaName());
			orderRecord.setUserRealAddress(cri.getUserRealAddress());
			orderRecord.setUserRealPlane(cri.getUserRealPlane());
			orderRecord.setUserRealName(cri.getUserRealName());
			orderRecord.setUserRealPostcode(cri.getUserRealPostcode());
			if (!StringHelper.isNull(userRealTime)) {
				orderRecord.setUserRealTime(Timestamp.valueOf(userRealTime));
			}
			if (!StringHelper.isNull(userRealDescription)) {
				orderRecord.setUserRealDescription(userRealDescription);
			}
			if (!StringHelper.isNull(paymentProvider)) {
				orderRecord.setPaymentProvider(Integer
						.parseInt(paymentProvider));
				Status statusCode = statusService.findByid(paymentProvider);
				orderRecord.setPaymentProviderName(statusCode.getStatusName());
				// 如果是货到付款，状态为处理中
				if (paymentProvider.equals("152")) {
					orderRecord.setOperateStatus(169);
				}
				// 其他支付模式，状态为待处理
				else {
					orderRecord.setOperateStatus(168);
				}
				statusCode = statusService.findByid(orderRecord
						.getOperateStatus().toString());
				orderRecord.setOperateStatusName(statusCode.getStatusName());
			}
			if (!StringHelper.isNull(paymentType)) {
				orderRecord.setPaymentType(Integer.parseInt(paymentType));
				Status statusCode = statusService.findByid(paymentType);
				orderRecord.setPaymentTypeName(statusCode.getStatusName());
			}
			if (!StringHelper.isNull(logisticsCompanyId)) {
				orderRecord.setLogisticsCompanyId(Integer.parseInt(logisticsCompanyId));
				LogisticsCompany logisticsCompanyCode = logisticsCompanyService.findByid(logisticsCompanyId);
				orderRecord.setLogisticsCompanyName(logisticsCompanyCode.getCompanyName());
			}
			orderRecord.setPaymentStatus(166);
			Status statusCode = statusService.findByid(orderRecord
					.getPaymentStatus().toString());
			orderRecord.setPaymentStatusName(statusCode.getStatusName());
			orderRecord
					.setOperateTime(new Timestamp(System.currentTimeMillis()));
			orderRecord
					.setCreateTime(new Timestamp(System.currentTimeMillis()));
			// 累积订单价格
			Iterator<String> it = goodsList.keySet().iterator();
			Float tempOriginalPrice = 0f;
			Float tempTotalPrice = 0f;
			while (it.hasNext()) {
				String key = it.next();
				OrderVO dvo = goodsList.get(key);
				Goods goods = dvo.getGoods();
				//取得商品数量
				BigDecimal d1 = new BigDecimal(dvo.getOrderGoodsRelationGoodsCount());
				BigDecimal d2;
				// 计算原价
				d2 = new BigDecimal(goods.getGoodsOriginalPrice());
				BigDecimal o1 = new BigDecimal(d1.multiply(d2).floatValue());
				BigDecimal o2 = new BigDecimal(tempOriginalPrice);
				tempOriginalPrice = o1.add(o2).floatValue();
				// 计算最终价
				// 是否优惠产品
				if (goods.getIsDiscountGoods() != null
						&& goods.getIsDiscountGoods() == 120) {
					d2 = new BigDecimal(goods.getGoodsPreferentialPrice());
				} else {
					d2 = new BigDecimal(goods.getGoodsOriginalPrice());
				}
				BigDecimal p1 = new BigDecimal(d1.multiply(d2).floatValue());
				BigDecimal p2 = new BigDecimal(tempTotalPrice);
				tempTotalPrice = p1.add(p2).floatValue();
			}
			orderRecord.setOriginalPrice(tempOriginalPrice);
			orderRecord.setTotalPrice(tempTotalPrice);

			// if (deliveryStaff != null) {
			// orderRecord.setDeliveryStaffId(deliveryStaff.getId());
			// orderRecord.setDeliveryStaffMobile(deliveryStaff.getMobile());
			// orderRecord.setDeliveryStaffName(deliveryStaff.getStaffName());
			// }
			orderRecord.setDeliveryStaffId(deliveryStaffId);
			orderRecord.setDeliveryStaffMobile(deliveryStaffMobile);
			orderRecord.setDeliveryStaffName(deliveryStaffName);
			orderRecord.setDeliveryStatus(172);
			statusCode = statusService.findByid(orderRecord.getDeliveryStatus().toString());
			orderRecord.setDeliveryStatusName(statusCode.getStatusName());
			if (!StringHelper.isNull(primaryConfigurationId)) {
				orderRecord.setPrimaryConfigurationId(Integer
						.parseInt(primaryConfigurationId));
				PrimaryConfiguration primaryConfiguration = primaryConfigurationService
						.findByid(primaryConfigurationId);
				orderRecord.setPrimaryConfigurationName(primaryConfiguration
						.getMallName());
			}
			Long newOrderRecordId = orderRecordService.save(orderRecord);
			orderRecordId = String.valueOf(newOrderRecordId);
			// 为新订单添加商品
			it = goodsList.keySet().iterator();
			while (it.hasNext()) {
				String key = it.next();
				OrderVO dvo = goodsList.get(key);
				OrderGoodsRelation ogr = new OrderGoodsRelation();
				Goods goods = dvo.getGoods();
				ogr.setOrderRecordId(newOrderRecordId);
				ogr.setClientUserId(Integer.parseInt(clientUserId));
				ogr.setGoodsId(goods.getId());
				ogr.setGoodsCount(Integer.parseInt(dvo.getOrderGoodsRelationGoodsCount()));
				// 判定价格类型和价格
				if (goods.getIsDiscountGoods() != null
						&& goods.getIsDiscountGoods() == 120) {
					ogr.setCurrentPrice(goods.getGoodsPreferentialPrice());
					ogr.setCurrentPriceType(143);
				} else {
					ogr.setCurrentPrice(goods.getGoodsOriginalPrice());
					ogr.setCurrentPriceType(142);
				}
				ogr.setStaffId(staffId);
				ogr.setPrimaryConfigurationId(1);
				ogr.setOrderGoodsRelationTime(new Timestamp(System
						.currentTimeMillis()));
				orderGoodsRelationService.save(ogr);
			}
			
			//判定如果是货到付款订单，就直接入材料采购表
			if(orderRecord.getPaymentProvider()==152){
				purchaseService.save(orderRecordId);
			}
			
			System.out.println("end:::");
		}
		super.success(null, AjaxResult.CALL_BACK_TYPE_CLOSECURRENT,
				"orderRecrod", null);
		return null;
	}

	/**
	 * 带信息到修改页
	 * 
	 * @return String
	 */
	@Action(value = "modifyinfo", results = {
			@Result(name = StrutsResMSG.SUCCESS, location = "/wx/admin/orderrecord/modify.jsp"),
			@Result(name = StrutsResMSG.FAILED, location = "/wx/admin/orderrecord/modify.jsp") })
	public String modifyinfo() {
		findPaymentStatusList();
		findPaymentProviderList();
		findLogisticsCompanyList();
		findOperateStatusList();
		findDeliveryStatus();
		findPaymentTypeList();
		findStaffList();
		// 加载该订单所有商品
		dvList = orderGoodsRelationService.listVO(orderRecordId, clientUserId,
				orderGoodsRelationGoodsId, orderGoodsRelationGoodsCount,
				orderGoodsRelationPrimaryConfigurationId, deliveryStaffId,
				orderGoodsRelationCurrentPriceType, createStartTime,
				createEndTime, getPageNum(), getNumPerPage(), orderField,
				orderDirection);
		// 加载该订单信息
		orderRecord = orderRecordService.findByid(orderRecordId);
		if (orderRecord.getClientUserId() != null) {
			this.clientUserId = orderRecord.getClientUserId().toString();
		}
		this.clientUserMobile = orderRecord.getClientUserMobile();
		this.clientUserEmail = orderRecord.getClientUserEmail();
		this.clientUserName = orderRecord.getClientUserName();
		this.clientUserSex = orderRecord.getClientUserSex();
		this.wxOpenId = orderRecord.getWxOpenId();
		this.staffId = orderRecord.getStaffId();
		this.userRealMobile = orderRecord.getUserRealMobile();
		this.userRealPlane = orderRecord.getUserRealPlane();
		this.userRealName = orderRecord.getUserRealName();
		this.userRealDescription = orderRecord.getUserRealDescription();
		if (orderRecord.getCountryId() != null) {
			this.countryId = orderRecord.getCountryId().toString();
		}
		this.countryName = orderRecord.getCountryName();
		if (orderRecord.getProvinceId() != null) {
			this.provinceId = orderRecord.getProvinceId().toString();
		}
		this.provinceName = orderRecord.getProvinceName();
		if (orderRecord.getCityId() != null) {
			this.cityId = orderRecord.getCityId().toString();
		}
		this.cityName = orderRecord.getCityName();
		if (orderRecord.getAreaId() != null) {
			this.areaId = orderRecord.getAreaId().toString();
		}
		this.areaName = orderRecord.getAreaName();
		this.userRealAddress = orderRecord.getUserRealAddress();
		this.userRealPostcode = orderRecord.getUserRealPostcode();
		if (orderRecord.getUserRealTime() != null) {
			this.userRealTime = StringHelper.getSystime(
					ViewConstant.PAGE_DATE_FORMAT_SEC, orderRecord
							.getUserRealTime().getTime());
		}
		if (orderRecord.getPaymentType() != null) {
			this.paymentType = orderRecord.getPaymentType().toString();
		}
		this.paymentTypeName = orderRecord.getPaymentTypeName();
		if (orderRecord.getPaymentStatus() != null) {
			this.paymentStatus = orderRecord.getPaymentStatus().toString();
		}
		this.paymentStatusName = orderRecord.getPaymentStatusName();
		if (orderRecord.getPaymentProvider() != null) {
			this.paymentProvider = orderRecord.getPaymentProvider().toString();
		}
		this.paymentProviderName = orderRecord.getPaymentProviderName();
		if (orderRecord.getPaymentTime() != null) {
			this.paymentTime = StringHelper.getSystime(
					ViewConstant.PAGE_DATE_FORMAT_SEC, orderRecord
							.getPaymentTime().getTime());
		}
		if (orderRecord.getOperateTime() != null) {
			this.operateTime = StringHelper.getSystime(
					ViewConstant.PAGE_DATE_FORMAT_SEC, orderRecord
							.getOperateTime().getTime());
		}
		if (orderRecord.getOperateStatus() != null) {
			this.operateStatus = orderRecord.getOperateStatus().toString();
		}
		this.operateStatusName = orderRecord.getOperateStatusName();
		if (orderRecord.getCreateTime() != null) {
			this.createTime = StringHelper.getSystime(
					ViewConstant.PAGE_DATE_FORMAT_SEC, orderRecord
							.getCreateTime().getTime());
		}
		if (orderRecord.getOriginalPrice() != null) {
			this.originalPrice = orderRecord.getOriginalPrice().toString();
		}
		if (orderRecord.getTotalPrice() != null) {
			this.totalPrice = orderRecord.getTotalPrice().toString();
		}
		if(orderRecord.getLogisticsCompanyId() != null){
			this.logisticsCompanyId = orderRecord.getLogisticsCompanyId().toString();
		}
		this.logisticsCompanyName = orderRecord.getLogisticsCompanyName();
		if (orderRecord.getDeliveryStaffId() != null) {
			this.deliveryStaffId = orderRecord.getDeliveryStaffId().toString();
		}
		this.deliveryStaffName = orderRecord.getDeliveryStaffName();
		this.deliveryStaffMobile = orderRecord.getDeliveryStaffMobile();
		if (orderRecord.getDeliveryStartTime() != null) {
			this.deliveryStartTime = StringHelper.getSystime(
					ViewConstant.PAGE_DATE_FORMAT_SEC, orderRecord
							.getDeliveryStartTime().getTime());
		}
		if (orderRecord.getDeliveryEndTime() != null) {
			this.deliveryEndTime = StringHelper.getSystime(
					ViewConstant.PAGE_DATE_FORMAT_SEC, orderRecord
							.getDeliveryEndTime().getTime());
		}
		if (orderRecord.getDeliveryStatus() != null) {
			this.deliveryStatus = orderRecord.getDeliveryStatus().toString();
		}
		this.deliveryStatusName = orderRecord.getDeliveryStatusName();
		if (orderRecord.getSourceId() != null) {
			this.sourceId = orderRecord.getSourceId().toString();
		}
		this.sourceName = orderRecord.getSourceName();
		if (orderRecord.getSourceType() != null) {
			this.sourceType = orderRecord.getSourceType().toString();
		}
		this.sourceTypeName = orderRecord.getSourceTypeName();
		this.serNo = orderRecord.getSerNo();
		if (orderRecord.getSellerId() != null) {
			this.sellerId = orderRecord.getSellerId().toString();
		}
		this.sellerName = orderRecord.getSellerName();
		if (orderRecord.getCusId() != null) {
			this.cusId = orderRecord.getCusId().toString();
		}
		this.cusName = orderRecord.getCusName();
		if (orderRecord.getPrimaryConfigurationId() != null) {
			this.primaryConfigurationId = orderRecord
					.getPrimaryConfigurationId().toString();
		}
		this.primaryConfigurationName = orderRecord
				.getPrimaryConfigurationName();
		return StrutsResMSG.SUCCESS;
	}

	/**
	 * 更新对象接口
	 * 
	 * @return String
	 * @throws Exception
	 */
	@Action(value = "modify", results = { @Result(name = StrutsResMSG.SUCCESS, type = "plainText") })
	public String modify() throws Exception {
		log.debug("modif action!");
		log.debug("modify orderRecord: " + orderRecordId);
		// 取得当前登录人信息
		currentStaff = super.getCurrentStaff();
		// 批量增加菜品
		if (goodsList == null) {
			super.error("未填写商品");
		} else {
			System.out.println("start:::");
			// 判定用户是否为新用户，新用户自动添加
//			if (StringHelper.isNull(clientUserId)) {
//				if (provincePO != null && cityPO != null
//						&& areaPO != null
//						&& !StringHelper.isNull(userRealAddress)
//						&& !StringHelper.isNull(userRealName)
//						&& !StringHelper.isNull(userRealMobile)) {
//					ClientUserInfo po = new ClientUserInfo();
//					po.setMobile(clientUserMobile);
//					if (!StringHelper.isNull(clientUserInfoStatus)) {
//						po.setStatus(Integer.parseInt(clientUserInfoStatus));
//						Status statusCode = statusService
//								.findByid(clientUserInfoStatus);
//						po.setStatusName(statusCode.getStatusName());
//						this.clientUserInfoStatusName = statusCode
//								.getStatusName();
//					}
//					if (!StringHelper.isNull(clientUserInfoType)) {
//						po.setType(Integer.parseInt(clientUserInfoType));
//						Status typeCode = statusService
//								.findByid(clientUserInfoType);
//						po.setTypeName(typeCode.getStatusName());
//						this.clientUserInfoTypeName = typeCode.getStatusName();
//					}
//					po.setCreateTime(new Timestamp(System.currentTimeMillis()));
//					po.setLastLoginTime(new Timestamp(System
//							.currentTimeMillis()));
//					po.setModifyTime(new Timestamp(System.currentTimeMillis()));
//					if (!StringHelper.isNull(sourceType)) {
//						po.setSourceType(Integer.parseInt(sourceType));
//						Status sourceTypeCode = statusService
//								.findByid(sourceType);
//						po.setSourceTypeName(sourceTypeCode.getStatusName());
//					}
//					Map map = clientUserInfoService.saveRetrunId(po);
//					if (map.get("code").equals("1")) {
//						super.error("用户已存在");
//					} else {
//						// 正常添加用户以后增加用户地址
//						clientUserId = map.get("serializable").toString();
//						ClientUserRegionInfo cur = new ClientUserRegionInfo();
//						cur.setCountryId(1);
//						cur.setCountryName("中国");
//						if (provincePO != null) {
//							cur.setProvinceId(provincePO.getId());
//							cur.setProvinceName(provincePO.getProvince());
//						}
//						if (cityPO != null) {
//							cur.setCityId(cityPO.getId());
//							cur.setCityName(cityPO.getCity());
//						}
//						if (areaPO != null) {
//							cur.setAreaId(areaPO.getId());
//							cur.setAreaName(areaPO.getArea());
//						}
//						if (!StringHelper.isNull(userRealAddress)) {
//							cur.setUserRealAddress(userRealAddress);
//						}
//						if (map.get("serializable") != null) {
//							cur.setClientUserInfoId(Integer
//									.parseInt(clientUserId));
//						}
//						if (!StringHelper.isNull(userRealMobile)) {
//							cur.setUserRealMobile(userRealMobile);
//						}
//						if (!StringHelper.isNull(userRealPlane)) {
//							cur.setUserRealPlane(userRealPlane);
//						}
//						if (!StringHelper.isNull(userRealName)) {
//							cur.setUserRealName(userRealName);
//						}
//						if (!StringHelper.isNull(userRealPostcode)) {
//							cur.setUserRealPostcode(userRealPostcode);
//						}
//						cur.setCreateTime(new Timestamp(System
//								.currentTimeMillis()));
//						cur.setModifyTime(new Timestamp(System
//								.currentTimeMillis()));
//						int re = clientUserRegionInfoService.save(cur);
//						if (re == 1) {
//							super.error("绑定地区超过20个");
//						}
//					}
//
//				} else {
//					super.error("订单信息填写不全");
//				}
//			} else 
			if (!StringHelper.isNull(clientUserRegionInfoId)) {
				if (provincePO != null && cityPO != null
						&& areaPO != null
						&& !StringHelper.isNull(userRealAddress)
						&& !StringHelper.isNull(userRealName)
						&& !StringHelper.isNull(userRealMobile)) {
					ClientUserRegionInfo cri = clientUserRegionInfoService
							.findByid(clientUserRegionInfoId);
					cri.setCountryId(1);
					cri.setCountryName("中国");
					if (provincePO != null) {
						cri.setProvinceId(provincePO.getId());
						cri.setProvinceName(provincePO.getProvince());
					}
					if (cityPO != null) {
						cri.setCityId(cityPO.getId());
						cri.setCityName(cityPO.getCity());
					}
					if (areaPO != null) {
						cri.setAreaId(areaPO.getId());
						cri.setAreaName(areaPO.getArea());
					}
					if (!StringHelper.isNull(userRealAddress)) {
						cri.setUserRealAddress(userRealAddress);
					}
					if (!StringHelper.isNull(clientUserId)) {
						cri.setClientUserInfoId(Integer.parseInt(clientUserId));
					}
					if (!StringHelper.isNull(userRealMobile)) {
						cri.setUserRealMobile(userRealMobile);
					}
					if (!StringHelper.isNull(userRealPlane)) {
						cri.setUserRealPlane(userRealPlane);
					}
					if (!StringHelper.isNull(userRealName)) {
						cri.setUserRealName(userRealName);
					}
					if (!StringHelper.isNull(userRealPostcode)) {
						cri.setUserRealPostcode(userRealPostcode);
					}
					cri.setCreateTime(new Timestamp(System.currentTimeMillis()));
					cri.setModifyTime(new Timestamp(System.currentTimeMillis()));
					clientUserRegionInfoService.modify(cri);
				}
			}
			// 修改订单
			OrderRecord oldOrderRecord = new OrderRecord();
			oldOrderRecord = orderRecordService.findByid(orderRecordId);
			if (!StringHelper.isNull(clientUserId)) {
				oldOrderRecord.setClientUserId(Integer.parseInt(clientUserId));
			}
			if (!StringHelper.isNull(clientUserName)) {
				oldOrderRecord.setClientUserName(clientUserName);
			}
			if (!StringHelper.isNull(clientUserMobile)) {
				oldOrderRecord.setClientUserMobile(clientUserMobile);
			}
			if (!StringHelper.isNull(clientUserEmail)) {
				oldOrderRecord.setClientUserEmail(clientUserEmail);
			}
			if (!StringHelper.isNull(clientUserSex)) {
				oldOrderRecord.setClientUserSex(clientUserSex);
			}
			if (!StringHelper.isNull(wxOpenId)) {
				oldOrderRecord.setWxOpenId(wxOpenId);
			}
			if (!StringHelper.isNull(staffId)) {
				oldOrderRecord.setStaffId(staffId);
			}
			if (!StringHelper.isNull(userRealMobile)) {
				oldOrderRecord.setUserRealMobile(userRealMobile);
			}
			if (!StringHelper.isNull(userRealDescription)) {
				oldOrderRecord.setUserRealDescription(userRealDescription);
			}
			if (!StringHelper.isNull(countryId)) {
				oldOrderRecord.setCountryId(Integer.parseInt(countryId));
			}
			if (!StringHelper.isNull(countryName)) {
				oldOrderRecord.setCountryName(countryName);
			}
			if (countryPO != null) {
				oldOrderRecord.setCountryId(countryPO.getId());
				oldOrderRecord.setCountryName(countryPO.getCountry());
			}
			if (provincePO != null) {
				oldOrderRecord.setProvinceId(provincePO.getId());
				oldOrderRecord.setProvinceName(provincePO.getProvince());
			}
			if (cityPO != null) {
				oldOrderRecord.setCityId(cityPO.getId());
				oldOrderRecord.setCityName(cityPO.getCity());
			}
			if (areaPO != null) {
				oldOrderRecord.setAreaId(areaPO.getId());
				oldOrderRecord.setAreaName(areaPO.getArea());
			}
			if (!StringHelper.isNull(userRealAddress)) {
				oldOrderRecord.setUserRealAddress(userRealAddress);
			}
			if (!StringHelper.isNull(userRealPlane)) {
				oldOrderRecord.setUserRealPlane(userRealPlane);
			}
			if (!StringHelper.isNull(userRealName)) {
				oldOrderRecord.setUserRealName(userRealName);
			}
			if (!StringHelper.isNull(userRealPostcode)) {
				oldOrderRecord.setUserRealPostcode(userRealPostcode);
			}
			if (!StringHelper.isNull(userRealTime)) {
				oldOrderRecord.setUserRealTime(Timestamp.valueOf(userRealTime));
			}
			if (!StringHelper.isNull(paymentType)) {
				oldOrderRecord.setPaymentType(Integer.parseInt(paymentType));
				Status statusCode = statusService.findByid(paymentType);
				oldOrderRecord.setPaymentTypeName(statusCode.getStatusName());
			}
			if (!StringHelper.isNull(paymentStatus)) {
				oldOrderRecord
						.setPaymentStatus(Integer.parseInt(paymentStatus));
				Status statusCode = statusService.findByid(paymentStatus);
				oldOrderRecord.setPaymentStatusName(statusCode.getStatusName());
			}
			if (!StringHelper.isNull(paymentProvider)) {
				oldOrderRecord.setPaymentProvider(Integer
						.parseInt(paymentProvider));
				Status statusCode = statusService.findByid(paymentProvider);
				oldOrderRecord.setPaymentProviderName(statusCode
						.getStatusName());
			}
			if (!StringHelper.isNull(logisticsCompanyId)) {
				oldOrderRecord.setLogisticsCompanyId(Integer.parseInt(logisticsCompanyId));
				LogisticsCompany logisticsCompanyCode = logisticsCompanyService.findByid(logisticsCompanyId);
				oldOrderRecord.setLogisticsCompanyName(logisticsCompanyCode.getCompanyName());
			}
			oldOrderRecord.setOperateTime(new Timestamp(System
					.currentTimeMillis()));
			if (!StringHelper.isNull(operateStatus)) {
				oldOrderRecord
						.setOperateStatus(Integer.parseInt(operateStatus));
				Status statusCode = statusService.findByid(operateStatus);
				oldOrderRecord.setOperateStatusName(statusCode.getStatusName());
			}
			// 累积订单价格
			Iterator<String> it = goodsList.keySet().iterator();
			Float tempOriginalPrice = 0f;
			Float tempTotalPrice = 0f;
			while (it.hasNext()) {
				String key = it.next();
				OrderVO dvo = goodsList.get(key);
				Goods goods = dvo.getGoods();
				//取得商品数量
				BigDecimal d1 = new BigDecimal(dvo.getOrderGoodsRelationGoodsCount());
				BigDecimal d2;
				// 计算原价
				d2 = new BigDecimal(goods.getGoodsOriginalPrice());
				BigDecimal o1 = new BigDecimal(d1.multiply(d2).floatValue());
				BigDecimal o2 = new BigDecimal(tempOriginalPrice);
				tempOriginalPrice = o1.add(o2).floatValue();
				// 计算最终价
				// 是否优惠产品
				if (goods.getIsDiscountGoods() != null
						&& goods.getIsDiscountGoods() == 120) {
					d2 = new BigDecimal(goods.getGoodsPreferentialPrice());
				} else {
					d2 = new BigDecimal(goods.getGoodsOriginalPrice());
				}
				BigDecimal p1 = new BigDecimal(d1.multiply(d2).floatValue());
				BigDecimal p2 = new BigDecimal(tempTotalPrice);
				tempTotalPrice = p1.add(p2).floatValue();
			}
			oldOrderRecord.setOriginalPrice(tempOriginalPrice);
			oldOrderRecord.setTotalPrice(tempTotalPrice);
			// if (deliveryStaff != null) {
			// oldOrderRecord.setDeliveryStaffId(deliveryStaff.getId());
			// oldOrderRecord
			// .setDeliveryStaffMobile(deliveryStaff.getMobile());
			// oldOrderRecord.setDeliveryStaffName(deliveryStaff
			// .getStaffName());
			// }
			if (!StringHelper.isNull(deliveryStaffId)) {
				oldOrderRecord.setDeliveryStaffId(deliveryStaffId);
			}
			if (!StringHelper.isNull(deliveryStaffMobile)) {
				oldOrderRecord.setDeliveryStaffMobile(deliveryStaffMobile);
			}
			if (!StringHelper.isNull(deliveryStaffName)) {
				oldOrderRecord.setDeliveryStaffName(deliveryStaffName);
			}
			if (!StringHelper.isNull(deliveryStatus)) {
				oldOrderRecord.setDeliveryStatus(Integer
						.parseInt(deliveryStatus));
				Status statusCode = statusService.findByid(deliveryStatus);
				oldOrderRecord
						.setDeliveryStatusName(statusCode.getStatusName());
			}
			if (!StringHelper.isNull(primaryConfigurationId)) {
				oldOrderRecord.setPrimaryConfigurationId(Integer
						.parseInt(primaryConfigurationId));
				PrimaryConfiguration primaryConfiguration = primaryConfigurationService
						.findByid(primaryConfigurationId);
				oldOrderRecord.setPrimaryConfigurationName(primaryConfiguration
						.getMallName());
			}
			orderRecordService.modify(oldOrderRecord);
			// 删除该订单所有商品
			orderGoodsRelationService.removeOrderRecordId(Long.valueOf(orderRecordId));
			// 为新订单添加商品
			it = goodsList.keySet().iterator();
			while (it.hasNext()) {
				String key = it.next();
				OrderVO dvo = goodsList.get(key);
				OrderGoodsRelation ogr = new OrderGoodsRelation();
				Goods goods = dvo.getGoods();
				ogr.setOrderRecordId(Long.valueOf(orderRecordId));
				ogr.setClientUserId(Integer.parseInt(clientUserId));
				ogr.setGoodsId(goods.getId());
				ogr.setGoodsCount(Integer.parseInt(dvo.getOrderGoodsRelationGoodsCount()));
				// 判定价格类型和价格
				if (goods.getIsDiscountGoods() != null
						&& goods.getIsDiscountGoods() == 120) {
					ogr.setCurrentPrice(goods.getGoodsPreferentialPrice());
					ogr.setCurrentPriceType(143);
				} else {
					ogr.setCurrentPrice(goods.getGoodsOriginalPrice());
					ogr.setCurrentPriceType(142);
				}
				ogr.setStaffId(staffId);
				ogr.setPrimaryConfigurationId(1);
				ogr.setOrderGoodsRelationTime(new Timestamp(System
						.currentTimeMillis()));
				orderGoodsRelationService.save(ogr);
			}
			//判定如果是货到付款订单，就直接入材料采购表
			if(oldOrderRecord!=null){
				if(oldOrderRecord.getOperateStatus()==171){
					purchaseService.removeOrderRecordId(orderRecordId);
				}
				else if(oldOrderRecord.getPaymentProvider()==152){
					purchaseService.removeOrderRecordId(orderRecordId);
					purchaseService.save(orderRecordId);
				}
			}
			System.out.println("end:::");
		}
		super.success(null, AjaxResult.CALL_BACK_TYPE_CLOSECURRENT,
				"orderRecord", null);
		return null;
	}

	@Action(value = "del", results = { @Result(name = StrutsResMSG.SUCCESS, type = "plainText") })
	public String del() throws IOException, JSONException {
		orderRecordService.removeOrderRecordId(orderRecordId);
		orderGoodsRelationService.removeOrderRecordId(Long.valueOf(orderRecordId));
		super.success(null, AjaxResult.CALL_BACK_TYPE_CLOSECURRENT,
				"orderRecord", null);
		return null;
	}

	private void setDateTime() {
		// 设置默认时间
		if (StringHelper.isNull(createStartTime)
				&& StringHelper.isNull(createEndTime)) {
			this.createStartTime = StringHelper.getSystime("yyyy-MM-dd");
			createStartTime = createStartTime + " 00:00:00";
			this.createEndTime = StringHelper.getSystime("yyyy-MM-dd");
			createEndTime = createEndTime + " 23:59:59";
		}
		if (StringHelper.isNull(operateStatus)) {
			this.operateStatus = "169";
		}
		
	}
	
	/**
	 * 外部调用，获取所有组织信息
	 * 
	 * @return String
	 * @throws IOException
	 */
	@Action(value = "all", results = { @Result(name = StrutsResMSG.SUCCESS, type = "plainText") })
	public String all() throws IOException {
		super.toJsonArray(orderRecordService.listAll());
		return null;
	}
}
