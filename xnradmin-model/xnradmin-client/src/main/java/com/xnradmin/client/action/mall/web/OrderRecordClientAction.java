/**
 *2012-5-10 下午3:52:29
 */
package com.xnradmin.client.action.mall.web;

import java.io.Serializable;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.cntinker.util.StringHelper;
import com.xnradmin.core.service.mall.clientUser.ClientUserInfoService;
import com.xnradmin.core.service.mall.clientUser.ClientUserRegionInfoService;
import com.xnradmin.constant.AjaxResult;
import com.xnradmin.constant.StrutsResMSG;
import com.xnradmin.core.action.ParentAction;
import com.xnradmin.core.service.StaffService;
import com.xnradmin.core.service.common.status.StatusService;
import com.xnradmin.core.service.mall.commodity.GoodsDishRelationService;
import com.xnradmin.core.service.mall.commodity.GoodsService;
import com.xnradmin.core.service.mall.order.OrderGoodsRelationService;
import com.xnradmin.core.service.mall.order.OrderRecordService;
import com.xnradmin.core.service.mall.order.PurchaseService;
import com.xnradmin.core.service.mall.order.ShoppingCartService;
import com.xnradmin.core.service.mall.region.AreaService;
import com.xnradmin.core.service.mall.region.CityService;
import com.xnradmin.core.service.mall.region.ProvinceService;
import com.xnradmin.core.service.mall.seting.LogisticsCompanyService;
import com.xnradmin.core.service.mall.seting.PrimaryConfigurationService;
import com.xnradmin.po.CommonStaff;
import com.xnradmin.po.client.ClientUserInfo;
import com.xnradmin.po.client.ClientUserRegionInfo;
import com.xnradmin.po.common.status.Status;
import com.xnradmin.po.mall.commodity.Goods;
import com.xnradmin.po.mall.order.OrderGoodsRelation;
import com.xnradmin.po.mall.order.OrderRecord;
import com.xnradmin.po.mall.order.ShoppingCart;
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
@Namespace("/page/wx/client/web/orderrecord")
@ParentPackage("json-default")
public class OrderRecordClientAction extends ParentAction {

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
	private ShoppingCartService shoppingCartService;

	@Autowired
	private LogisticsCompanyService logisticsCompanyService;

	@Autowired
	private StatusService statusService;

	@Autowired
	private AreaService areaService;

	@Autowired
	private PurchaseService purchaseService;

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

	private String orderNo; // 自定义订单号，先使用

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

	private String uid;

	private Integer goodsCount; //商品数量
	
	private Float shoppingCartTotalPrice; //总价
	
	private List<ShoppingCart> shoppingCartList;
	private List<Status> paymentTypeList;
	private List<Status> paymentStatusList;
	private List<Status> paymentProviderList;
	private List<Status> operateStatusList;
	private List<Status> deliveryStatusList;
	private List<StaffVO> staffList;
	private List<Goods> allGoodsList;
	private Map<String, OrderVO> goodsList;
	private List<OrderVO> dvList;
	private CommonStaff currentStaff;
	private CommonStaff deliveryStaff;
	private List<OrderVO> voList;
	private CommodityVO commodityVO;
	private OrderRecord orderRecord;
	private Goods goods;
	private Status status;
	private Status isDiscount;

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

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public Integer getGoodsCount() {
		return goodsCount;
	}

	public void setGoodsCount(Integer goodsCount) {
		this.goodsCount = goodsCount;
	}

	public Float getShoppingCartTotalPrice() {
		return shoppingCartTotalPrice;
	}

	public void setShoppingCartTotalPrice(Float shoppingCartTotalPrice) {
		this.shoppingCartTotalPrice = shoppingCartTotalPrice;
	}

	public List<ShoppingCart> getShoppingCartList() {
		return shoppingCartList;
	}

	public void setShoppingCartList(List<ShoppingCart> shoppingCartList) {
		this.shoppingCartList = shoppingCartList;
	}

	@Override
	public boolean isPublic() {
		return true;
	};

	static Log log = LogFactory.getLog(OrderRecordClientAction.class);

	/**
	 * 跳转到信息页。。
	 * 
	 * @return
	 */
	@Action(value = "webList", results = { @Result(name = StrutsResMSG.SUCCESS, type = "json") })
	public String webList() {
		if (!StringHelper.isNull(uid)) {
			ClientUserInfo clientUserInfo = clientUserInfoService
					.findByProperty("wxopenuid", uid);
			if (clientUserInfo != null && clientUserInfo.getId() != null) {
				shoppingCartList = shoppingCartService.webList(clientUserInfo
						.getId().toString(), null, null, "1", null, null, 0, 0,
						"id", "asc");
				shoppingCartTotalPrice = 0f;
				goodsCount = 0;
				if (shoppingCartList != null && shoppingCartList.size() > 0) {
					for (int i = 0; shoppingCartList.size() > i; i++) {
						ShoppingCart tempShoppingCart = new ShoppingCart();
						tempShoppingCart = shoppingCartList.get(i);
						Float tempTotalPrice = tempShoppingCart.getTotalPrice();
						int tempGoodsCount = tempShoppingCart.getGoodsCount();
						BigDecimal b1 = new BigDecimal(tempTotalPrice);
						BigDecimal b2 = new BigDecimal(tempGoodsCount);
						BigDecimal a1 = new BigDecimal(shoppingCartTotalPrice);
						BigDecimal a2 = new BigDecimal(goodsCount);
						// 计算最终商品数量和资费
						shoppingCartTotalPrice = a1.add(b1).floatValue();
						goodsCount = a2.add(b2).intValue();
					}
				}
				//处理订单列表
				this.voList = orderRecordService.webList(orderNo,
						orderRecordId, clientUserInfo.getId()
								.toString(), clientUserName, clientUserMobile,
						clientUserEmail, clientUserSex, clientUserType,
						clientUserTypeName, wxOpenId,
						orderGoodsRelationStaffId, userRealMobile,
						userRealPlane, userRealName, countryId, countryName,
						provinceId, provinceName, cityId, cityName, areaId,
						areaName, userRealAddress, userRealPostcode,
						userRealStartTime, userRealEndTime, paymentType,
						paymentTypeName, paymentStatus, paymentStatusName,
						paymentProvider, paymentProviderName, paymentStartTime,
						paymentEndTime, operateStartTime, operateEndTime,
						operateStatus, operateStatusName, createStartTime,
						createEndTime, originalPrice, totalPrice,
						logisticsCompanyId, logisticsCompanyName,
						deliveryStaffId, deliveryStaffName,
						deliveryStaffMobile, deliveryStartStartTime,
						deliveryStartEndTime, deliveryEndStartTime,
						deliveryEndEndTime, deliveryStatus, deliveryStatusName,
						sourceId, sourceName, sourceType, sourceTypeName,
						serNo, sellerId, sellerName, cusId, cusName,
						orderGoodsRelationPrimaryConfigurationId,
						primaryConfigurationName, getPageNum(),
						getNumPerPage(), orderField, orderDirection);
			}
		}
		return StrutsResMSG.SUCCESS;
	}

	/**
	 * 保存对象接口
	 * 
	 * @return String
	 * @throws Exception
	 */
	@Action(value = "add", results = { @Result(name = StrutsResMSG.SUCCESS, type = "json") })
	public String add() {
		if (!StringHelper.isNull(uid)) {
			ClientUserRegionInfo clientUserRegionInfo = new ClientUserRegionInfo();
			ClientUserInfo clientUserInfo = clientUserInfoService
					.findByProperty("wxopenuid", uid);
			// 判定用户是否为新用户，新用户自动添加
			// if (clientUserInfo == null) {
			// ClientUserInfo po = new ClientUserInfo();
			// po.setWxunionid(uid);
			// po.setMobile(clientUserMobile);
			// po.setStatus(139);
			// po.setStatusName("正常");
			// po.setType(153);
			// po.setTypeName("微信用户");
			// po.setCreateTime(new Timestamp(System.currentTimeMillis()));
			// po.setLastLoginTime(new Timestamp(System.currentTimeMillis()));
			// po.setModifyTime(new Timestamp(System.currentTimeMillis()));
			// if (!StringHelper.isNull(sourceType)) {
			// po.setSourceType(Integer.parseInt(sourceType));
			// Status sourceTypeCode = statusService.findByid(sourceType);
			// po.setSourceTypeName(sourceTypeCode.getStatusName());
			// }
			// // 正常添加用户以后增加用户地址
			// clientUserId = clientUserInfoService.saveWx(po).toString();
			// clientUserInfo = po;
			// ClientUserRegionInfo cur = new ClientUserRegionInfo();
			// Area area = areaService.findByid(areaId);
			// cur.setCountryId(area.getCountryId());
			// cur.setCountryName(area.getCountry());
			// cur.setProvinceId(area.getProvinceId());
			// cur.setProvinceName(area.getProvince());
			// cur.setCityId(area.getCityId());
			// cur.setCityName(area.getCity());
			// cur.setAreaId(area.getId());
			// cur.setAreaName(area.getArea());
			// if (!StringHelper.isNull(userRealAddress)) {
			// cur.setUserRealAddress(userRealAddress);
			// }
			// cur.setClientUserInfoId(Integer.parseInt(clientUserId));
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
			// po.setCreateTime(new Timestamp(System.currentTimeMillis()));
			// po.setModifyTime(new Timestamp(System.currentTimeMillis()));
			// clientUserRegionInfoService.saveWx(cur);
			// } else {
			// 如果用户存在，则查询用户使用的地址是不是新地址，如果是，则添加新地址
			if (!StringHelper.isNull(clientUserRegionInfoId)
					&& clientUserRegionInfoId.equals("new")) {
				if (!StringHelper.isNull(areaId)
						&& !StringHelper.isNull(userRealAddress)
						&& !StringHelper.isNull(userRealName)
						&& !StringHelper.isNull(userRealMobile)) {
					ClientUserRegionInfo cri = new ClientUserRegionInfo();
					Area area = areaService.findByid(areaId);
					cri.setCountryId(area.getCountryId());
					cri.setCountryName(area.getCountry());
					cri.setProvinceId(area.getProvinceId());
					cri.setProvinceName(area.getProvince());
					cri.setCityId(area.getCityId());
					cri.setCityName(area.getCity());
					cri.setAreaId(area.getId());
					cri.setAreaName(area.getArea());
					if (!StringHelper.isNull(userRealAddress)) {
						cri.setUserRealAddress(userRealAddress);
					}
					if (clientUserInfo != null) {
						cri.setClientUserInfoId(clientUserInfo.getId());
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
					clientUserRegionInfoService.saveWx(cri);
					clientUserRegionInfo = cri;
				}
			} else {
				ClientUserRegionInfo cri = clientUserRegionInfoService
						.findByid(clientUserRegionInfoId);
				clientUserRegionInfo = cri;
			}
			// }
			// 增加新订单
			List<OrderVO> shoppingCartList = shoppingCartService.listVO(
					clientUserInfo.getId().toString(), null, null,
					primaryConfigurationId, staffId, null, null, null, 0, 0,
					"a.id", "asc");
			if (shoppingCartList != null && shoppingCartList.size() > 0) {
				OrderRecord orderRecord = new OrderRecord();
				orderRecord.setClientUserId(clientUserInfo.getId());
				orderRecord.setClientUserName(clientUserInfo.getNickName());
				orderRecord.setClientUserMobile(clientUserInfo.getMobile());
				orderRecord.setClientUserEmail(clientUserInfo.getEmail());
				orderRecord.setWxOpenId(uid);
				orderRecord.setStaffId(staffId);
				orderRecord.setUserRealMobile(clientUserRegionInfo
						.getUserRealMobile());
				orderRecord.setCountryId(clientUserRegionInfo.getCountryId());
				orderRecord.setCountryName(clientUserRegionInfo
						.getCountryName());
				orderRecord.setProvinceId(clientUserRegionInfo.getProvinceId());
				orderRecord.setProvinceName(clientUserRegionInfo
						.getProvinceName());
				orderRecord.setCityId(clientUserRegionInfo.getCityId());
				orderRecord.setCityName(clientUserRegionInfo.getCityName());
				orderRecord.setAreaId(clientUserRegionInfo.getAreaId());
				orderRecord.setAreaName(clientUserRegionInfo.getAreaName());
				orderRecord.setUserRealAddress(clientUserRegionInfo
						.getUserRealAddress());
				orderRecord.setUserRealPlane(clientUserRegionInfo
						.getUserRealPlane());
				orderRecord.setUserRealName(clientUserRegionInfo
						.getUserRealName());
				orderRecord.setUserRealPostcode(clientUserRegionInfo
						.getUserRealPostcode());
				System.out.println("userRealTime++"+userRealTime);
				if (!StringHelper.isNull(userRealTime)) {
					userRealTime = userRealTime+" 16:00:00";
					orderRecord
							.setUserRealTime(Timestamp.valueOf(userRealTime));
				}
				if (!StringHelper.isNull(userRealDescription)) {
					orderRecord.setUserRealDescription(userRealDescription);
				}
				if (!StringHelper.isNull(paymentProvider)) {
					orderRecord.setPaymentProvider(Integer
							.parseInt(paymentProvider));
					Status statusCode = statusService.findByid(paymentProvider);
					orderRecord.setPaymentProviderName(statusCode
							.getStatusName());
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
					orderRecord
							.setOperateStatusName(statusCode.getStatusName());
				}
				if (!StringHelper.isNull(paymentType)) {
					orderRecord.setPaymentType(Integer.parseInt(paymentType));
					Status statusCode = statusService.findByid(paymentType);
					orderRecord.setPaymentTypeName(statusCode.getStatusName());
				}
				if (!StringHelper.isNull(logisticsCompanyId)) {
					orderRecord.setLogisticsCompanyId(Integer
							.parseInt(logisticsCompanyId));
					LogisticsCompany logisticsCompanyCode = logisticsCompanyService
							.findByid(logisticsCompanyId);
					orderRecord.setLogisticsCompanyName(logisticsCompanyCode
							.getCompanyName());
				}
				orderRecord.setPaymentStatus(166);
				Status statusCode = statusService.findByid(orderRecord
						.getPaymentStatus().toString());
				orderRecord.setPaymentStatusName(statusCode.getStatusName());
				orderRecord.setOperateTime(new Timestamp(System
						.currentTimeMillis()));
				orderRecord.setCreateTime(new Timestamp(System
						.currentTimeMillis()));
				// 累积订单价格
				Float tempOriginalPrice = 0f;
				Float tempTotalPrice = 0f;
				for (int i = 0; shoppingCartList.size() > i; i++) {
					BigDecimal a1 = new BigDecimal(tempTotalPrice);
					BigDecimal a2 = new BigDecimal(shoppingCartList.get(i)
							.getShoppingCartTotalPrice());
					BigDecimal a3 = new BigDecimal(tempOriginalPrice);
					BigDecimal a4 = new BigDecimal(shoppingCartList.get(i)
							.getShoppingCartOriginalTotalPrice());
					tempTotalPrice = a1.add(a2).floatValue();
					tempOriginalPrice = a3.add(a4).floatValue();
				}
				orderRecord.setOriginalPrice(tempOriginalPrice);
				orderRecord.setTotalPrice(tempTotalPrice);
				orderRecord.setDeliveryStaffId(deliveryStaffId);
				orderRecord.setDeliveryStaffMobile(deliveryStaffMobile);
				orderRecord.setDeliveryStaffName(deliveryStaffName);
				// 派送状态未派送
				orderRecord.setDeliveryStatus(172);
				statusCode = statusService.findByid(orderRecord
						.getDeliveryStatus().toString());
				orderRecord.setDeliveryStatusName(statusCode.getStatusName());
				if (!StringHelper.isNull(primaryConfigurationId)) {
					orderRecord.setPrimaryConfigurationId(1);
					PrimaryConfiguration primaryConfiguration = primaryConfigurationService
							.findByid(primaryConfigurationId);
					orderRecord
							.setPrimaryConfigurationName(primaryConfiguration
									.getMallName());
				}
				String outTradeNo = StringHelper.getSystime("yyyyMMddHHmmss")
						+ StringHelper.getRandom(5);
				orderRecord.setOrderNo(outTradeNo);
				Long newOrderRecordId = orderRecordService.save(orderRecord);
				orderRecordId = String.valueOf(newOrderRecordId);
				// 为新订单添加商品
				Boolean fiveflag = true; //五送一状态
				Boolean firstflag = false; //首次下单状态
				int count = 0;
				for (int i = 0; shoppingCartList.size() > i; i++) {
					OrderVO dvo = shoppingCartList.get(i);
					OrderGoodsRelation ogr = new OrderGoodsRelation();
					Goods goods = dvo.getGoods();
					ogr.setOrderRecordId(newOrderRecordId);
					ogr.setClientUserId(clientUserInfo.getId());
					ogr.setGoodsId(goods.getId());
					ogr.setGoodsCount(Integer.parseInt(dvo
							.getShoppingCartGoodsCount()));
					// 判定价格类型和价格
					if (goods.getIsDiscountGoods() != null
							&& goods.getIsDiscountGoods() == 120) {
						ogr.setCurrentPrice(goods.getGoodsPreferentialPrice());
						ogr.setCurrentPriceType(143);
					} else {
						ogr.setCurrentPrice(goods.getGoodsOriginalPrice());
						ogr.setCurrentPriceType(142);
					}
					// 判定是否用户购买5个商品送1个商品（购物车中包含原价产品就送）
					if (goods.getIsDiscountGoods() == 121) {
						firstflag = true;
					}
					// 判定是否套包产品
//					if (goods.getGoodsCategoryId().equals("10")) {
//						flag = false;
//					} else {
						count = count
								+ Integer.parseInt(dvo
										.getShoppingCartGoodsCount());
//					}
					ogr.setStaffId(staffId);
					ogr.setPrimaryConfigurationId(Integer
							.parseInt(shoppingCartList.get(i)
									.getShoppingCartPrimaryConfigurationId()));
					ogr.setOrderGoodsRelationTime(new Timestamp(System
							.currentTimeMillis()));
					orderGoodsRelationService.save(ogr);
					shoppingCartService.del(shoppingCartList.get(i)
							.getShoppingCartId());
				}
				// 五送一赠送商品
				if (fiveflag && count >= 5) {
					List<Status> freeStatusList = statusService.find(
							Goods.class, "freeGoods");
					if (freeStatusList != null && freeStatusList.size() > 0) {
						for (int i = 0; freeStatusList.size() > i; i++) {
							String freeGoodsId = freeStatusList.get(i)
									.getStatusCode();
							Goods freeGoods = goodsService
									.findByid(freeGoodsId);
							OrderGoodsRelation ogr = new OrderGoodsRelation();
							ogr.setOrderRecordId(newOrderRecordId);
							ogr.setClientUserId(clientUserInfo.getId());
							ogr.setGoodsId(freeGoods.getId());
							ogr.setGoodsCount(1);
							ogr.setCurrentPrice(0f);
							ogr.setCurrentPriceType(143);
							ogr.setStaffId(staffId);
							ogr.setPrimaryConfigurationId(1);
							ogr.setOrderGoodsRelationTime(new Timestamp(System
									.currentTimeMillis()));
							orderGoodsRelationService.save(ogr);
						}
					}
				}
				// 首单赠送商品 
				int userOrderCount = orderRecordService
						.getUserOrderCount(clientUserInfo.getId().toString());
				if (firstflag && userOrderCount <= 1) {
					List<Status> freeOneStatusList = statusService.find(
							Goods.class, "freeOneGoods");
					if (freeOneStatusList != null
							&& freeOneStatusList.size() > 0) {
						for (int i = 0; freeOneStatusList.size() > i; i++) {
							String freeGoodsId = freeOneStatusList.get(i)
									.getStatusCode();
							Goods freeGoods = goodsService
									.findByid(freeGoodsId);
							OrderGoodsRelation ogr = new OrderGoodsRelation();
							ogr.setOrderRecordId(newOrderRecordId);
							ogr.setClientUserId(clientUserInfo.getId());
							ogr.setGoodsId(freeGoods.getId());
							ogr.setGoodsCount(1);
							ogr.setCurrentPrice(0f);
							ogr.setCurrentPriceType(143);
							ogr.setStaffId(staffId);
							ogr.setPrimaryConfigurationId(1);
							ogr.setOrderGoodsRelationTime(new Timestamp(System
									.currentTimeMillis()));
							orderGoodsRelationService.save(ogr);
						}
					}
				}
				// 判定如果是货到付款订单，就直接入材料采购表
				if (orderRecord.getPaymentProvider() == 152) {
					purchaseService.save(orderRecordId);
				}
			}

		}
		return StrutsResMSG.SUCCESS;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
}
