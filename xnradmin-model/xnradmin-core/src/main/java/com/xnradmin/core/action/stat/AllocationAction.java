/**
 *2012-5-10 下午3:52:29
 */
package com.xnradmin.core.action.stat;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import com.xnradmin.client.service.wx.OutPlanService;
import com.xnradmin.constant.AjaxResult;
import com.xnradmin.constant.StrutsResMSG;
import com.xnradmin.constant.ViewConstant;
import com.xnradmin.core.action.ParentAction;
import com.xnradmin.core.service.business.order.AllocationService;
import com.xnradmin.core.service.business.order.BusinessOrderGoodsRelationService;
import com.xnradmin.core.service.business.order.BusinessOrderRecordService;
import com.xnradmin.core.service.business.order.FarmerOrderRecordService;
import com.xnradmin.core.service.common.status.StatusService;
import com.xnradmin.core.service.mall.order.OrderGoodsRelationService;
import com.xnradmin.core.service.mall.order.OrderRecordService;
import com.xnradmin.core.service.mall.seting.LogisticsCompanyService;
import com.xnradmin.po.CommonStaff;
import com.xnradmin.po.business.AllocationData;
import com.xnradmin.po.business.BusinessOrderGoodsRelation;
import com.xnradmin.po.business.BusinessOrderRecord;
import com.xnradmin.po.business.FarmerOrderRecord;
import com.xnradmin.po.common.status.Status;
import com.xnradmin.po.mall.order.OrderRecord;
import com.xnradmin.po.mall.seting.LogisticsCompany;
import com.xnradmin.po.wx.OutPlan;
import com.xnradmin.vo.business.AllocationVO;
import com.xnradmin.vo.business.BusinessAllocationVO;
import com.xnradmin.vo.business.FarmerOrderVO;
import com.xnradmin.vo.mall.OrderVO;

/**
 * @autohr: xiang_liu
 * 
 */
@Controller
@Scope("prototype")
@Namespace("/page/business/admin/allocation")
@ParentPackage("json-default")
public class AllocationAction extends ParentAction {

	@Autowired
	private OrderGoodsRelationService orderGoodsRelationService;
	
	@Autowired
	private BusinessOrderGoodsRelationService businessOrderGoodsRelationService;
	
	@Autowired
	private OrderRecordService orderRecordService;
	
	@Autowired
	private BusinessOrderRecordService businessOrderRecordService;
	
	@Autowired
	private AllocationService allocationService;
	
	@Autowired
	private OutPlanService planService;
	
	@Autowired
	private LogisticsCompanyService logisticsCompanyService;
	
	@Autowired
	private FarmerOrderRecordService farmerOrderService;
	
	@Autowired
	private StatusService statusService;
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
	
	private String allocationStatus; // 订单分配状态（已分配，未分配）

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

	private String orderGoodsRelationOrderRecordId; // 订单ID

	private String orderNo; // 自定义订单编号

	private String orderGoodsRelationClientUserId; // 用户ID

	private String orderGoodsRelationStaffId; // 隶属用户Id

	private String orderGoodsRelationPrimaryConfigurationId; // 对应商城ID

	private List<Status> paymentTypeList;

	private List<Status> paymentStatusList;

	private List<Status> paymentProviderList;

	private List<Status> operateStatusList;

	private List<Status> deliveryStatusList;
	
	private List<Status> allocationStatusList;

	private List<LogisticsCompany> logisticsCompanyList;

	private List<OrderVO> voList;
	
	private List<AllocationVO> allocationVo;

	private List<OrderVO> excelVoList;

	private List<OrderVO> dvList;
	
	private List<BusinessAllocationVO> allocationList;
	
	private List<String[]> orderGoodsCountList;
	
	private List<FarmerOrderVO> farmerOrderList;

	private OrderRecord orderRecord;
	
	private CommonStaff currentStaff;//登录用户信息
	
	private String allocationId;//分配id
	
	private String goodsIdstr;
	
	private String pageType;//页面类型 查看1 编辑2 新增3
	
	private Map<String, FarmerOrderRecord> items;

	public String getAllocationId() {
		return allocationId;
	}

	public void setAllocationId(String allocationId) {
		this.allocationId = allocationId;
	}

	public String getGoodsIdstr() {
		return goodsIdstr;
	}

	public void setGoodsIdstr(String goodsIdstr) {
		this.goodsIdstr = goodsIdstr;
	}

	public String getPageType() {
		return pageType;
	}

	public void setPageType(String pageType) {
		this.pageType = pageType;
	}

	public String getOrderRecordId() {
		return orderRecordId;
	}

	public List<FarmerOrderVO> getFarmerOrderList() {
		return farmerOrderList;
	}

	public CommonStaff getCurrentStaff() {
		return currentStaff;
	}

	public void setCurrentStaff(CommonStaff currentStaff) {
		this.currentStaff = currentStaff;
	}

	public void setFarmerOrderList(List<FarmerOrderVO> farmerOrderList) {
		this.farmerOrderList = farmerOrderList;
	}

	public void setOrderRecordId(String orderRecordId) {
		this.orderRecordId = orderRecordId;
	}

	public String getClientUserId() {
		return clientUserId;
	}

	public Map<String, FarmerOrderRecord> getItems() {
		return items;
	}

	public void setItems(Map<String, FarmerOrderRecord> items) {
		this.items = items;
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
	
	public String getAllocationStatus() {
		return allocationStatus;
	}

	public void setAllocationStatus(String allocationStatus) {
		this.allocationStatus = allocationStatus;
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

	public List<BusinessAllocationVO> getAllocationList() {
		return allocationList;
	}

	public void setAllocationList(List<BusinessAllocationVO> allocationList) {
		this.allocationList = allocationList;
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

	public List<OrderVO> getVoList() {
		return voList;
	}

	public void setVoList(List<OrderVO> voList) {
		this.voList = voList;
	}

	public List<OrderVO> getExcelVoList() {
		return excelVoList;
	}

	public void setExcelVoList(List<OrderVO> excelVoList) {
		this.excelVoList = excelVoList;
	}

	public List<String[]> getOrderGoodsCountList() {
		return orderGoodsCountList;
	}

	public void setOrderGoodsCountList(List<String[]> orderGoodsCountList) {
		this.orderGoodsCountList = orderGoodsCountList;
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

	public String getUserRealDescription() {
		return userRealDescription;
	}

	public void setUserRealDescription(String userRealDescription) {
		this.userRealDescription = userRealDescription;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public List<LogisticsCompany> getLogisticsCompanyList() {
		return logisticsCompanyList;
	}

	public void setLogisticsCompanyList(
			List<LogisticsCompany> logisticsCompanyList) {
		this.logisticsCompanyList = logisticsCompanyList;
	}

	public List<OrderVO> getDvList() {
		return dvList;
	}

	public void setDvList(List<OrderVO> dvList) {
		this.dvList = dvList;
	}

	public OrderRecord getOrderRecord() {
		return orderRecord;
	}

	public void setOrderRecord(OrderRecord orderRecord) {
		this.orderRecord = orderRecord;
	}

	public List<Status> getAllocationStatusList() {
		return allocationStatusList;
	}

	public void setAllocationStatusList(List<Status> allocationStatusList) {
		this.allocationStatusList = allocationStatusList;
	}
	
	public List<AllocationVO> getAllocationVo() {
		return allocationVo;
	}

	public void setAllocationVo(List<AllocationVO> allocationVo) {
		this.allocationVo = allocationVo;
	}

	@Override
	public boolean isPublic() {
		return false;
	};

	static Log log = LogFactory.getLog(AllocationAction.class);

	/**
	 * 带信息到修改页
	 * 
	 * @return String
	 */
	@Action(value = "toAllocation", results = {
			@Result(name = StrutsResMSG.SUCCESS, location = "/business/admin/allocation/modify.jsp"),
			@Result(name = StrutsResMSG.FAILED, location = "/business/admin/allocation/modify.jsp" )})
	public String toAllocation() {
		findDateTime();
		findPaymentStatusList();
		findPaymentProviderList();
		findOperateStatusList();
		findDeliveryStatus();
		findPaymentTypeList();
		findAllocationStatus();
		// 处理订单列表 已支付  未派送  待处理 的未分配的订单
		allocationList = orderRecordService.orderGoodsCountDetail(orderNo,
				orderRecordId, clientUserId, clientUserName, clientUserMobile,
				clientUserEmail, clientUserSex, clientUserType,
				clientUserTypeName, wxOpenId, orderGoodsRelationStaffId,
				userRealMobile, userRealPlane, userRealName, countryId,
				countryName, provinceId, provinceName, cityId, cityName,
				areaId, areaName, userRealAddress, userRealPostcode,
				userRealStartTime, userRealEndTime, paymentType,
				paymentTypeName, "200", paymentStatusName,
				paymentProvider, paymentProviderName, paymentStartTime,
				paymentEndTime, operateStartTime, operateEndTime,
				"203", operateStatusName, createStartTime,
				createEndTime, originalPrice, totalPrice, logisticsCompanyId,
				logisticsCompanyName, deliveryStaffId, deliveryStaffName,
				deliveryStaffMobile, deliveryStartStartTime,
				deliveryStartEndTime, deliveryEndStartTime, deliveryEndEndTime,
				"207", deliveryStatusName, sourceId, sourceName,
				sourceType, sourceTypeName, serNo, sellerId, sellerName, cusId,
				cusName, orderGoodsRelationPrimaryConfigurationId,"227",null,
				primaryConfigurationName, 0, 0, orderField, orderDirection);
		goodsIdstr="";
		if(allocationList!=null&&allocationList.size()>0){
			for(BusinessAllocationVO ba:allocationList){
				if("".equals(goodsIdstr)){
					goodsIdstr=ba.getBusinessGoods().getId()+"";
				}else{
					goodsIdstr+=","+ba.getBusinessGoods().getId();
				}
			}
		}
		//FarmerOrderVO farmerOrderVO = new FarmerOrderVO();
		
//		FarmerOrderRecord farmerOrder = new FarmerOrderRecord();
//		
//		farmerOrder.setOrderRecordId(Long.parseLong(orderRecordId));
//		
//		farmerOrderVO.setFarmerOrder(farmerOrder);
		
		//farmerOrderList = farmerOrderService.listVO(farmerOrderVO,0,0,null,null);
		
		return StrutsResMSG.SUCCESS;
	}
	
	/**
	 * 列表页面
	 * 
	 * @return String
	 */
	@Action(value = "info", results = {
			@Result(name = StrutsResMSG.SUCCESS, location = "/business/admin/allocation/info.jsp")})
	public String info() {
		setPageInfo();
		return StrutsResMSG.SUCCESS;
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
	}
	
	private void setPageInfo() {
		// 设置排序
		findDateTime();
		AllocationVO vo = new AllocationVO();
		if(allocationId!=null){
			vo.setCreateStartTime(createStartTime);
			vo.setCreateEndTime(createEndTime);			
		}
		
		if(currentStaff!=null){
			vo.setStaff(currentStaff);
		}
		
		vo.setGroupBy("a.id");
		vo.setOrderByField("desc");
		this.allocationVo = allocationService.listVO(vo, getPageNum(),
				getNumPerPage(), orderField, orderDirection);
		vo.setGroupBy("");
		String select = "select count(distinct a.id) ";
		this.totalCount = allocationService.getCount(select, vo);
	}
	
	/**
	 * 跳转到修改界面
	 * 
	 * @return String
	 * @throws Exception
	 */
	@Action(value = "modifyInfo", results = { @Result(name = StrutsResMSG.SUCCESS, location = "/business/admin/allocation/modify.jsp") })
	public String modifyInfo() throws Exception {
		// 取得当前登录人信息
		currentStaff = super.getCurrentStaff();
		AllocationData allocationData = allocationService.findByid(allocationId);
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		this.createStartTime = sdf.format(new Date(allocationData.getStartTimeCondition().getTime()));
		this.createEndTime = sdf.format(new Date(allocationData.getEndTimeCondition().getTime()));
		findPaymentStatusList();
		findPaymentProviderList();
		findOperateStatusList();
		findDeliveryStatus();
		findPaymentTypeList();
		findAllocationStatus();
		// 处理订单列表 已支付  未派送  待处理 的未分配的订单
		allocationList = orderRecordService.orderGoodsCountDetail(orderNo,
				orderRecordId, clientUserId, clientUserName, clientUserMobile,
				clientUserEmail, clientUserSex, clientUserType,
				clientUserTypeName, wxOpenId, orderGoodsRelationStaffId,
				userRealMobile, userRealPlane, userRealName, countryId,
				countryName, provinceId, provinceName, cityId, cityName,
				areaId, areaName, userRealAddress, userRealPostcode,
				userRealStartTime, userRealEndTime, paymentType,
				paymentTypeName, paymentStatus, paymentStatusName,
				paymentProvider, paymentProviderName, paymentStartTime,
				paymentEndTime, operateStartTime, operateEndTime,
				operateStatus, operateStatusName, createStartTime,
				createEndTime, originalPrice, totalPrice, logisticsCompanyId,
				logisticsCompanyName, deliveryStaffId, deliveryStaffName,
				deliveryStaffMobile, deliveryStartStartTime,
				deliveryStartEndTime, deliveryEndStartTime, deliveryEndEndTime,
				deliveryStatus, deliveryStatusName, sourceId, sourceName,
				sourceType, sourceTypeName, serNo, sellerId, sellerName, cusId,
				cusName, orderGoodsRelationPrimaryConfigurationId,"227",allocationId,
				primaryConfigurationName, 0, 0, orderField, orderDirection);
		goodsIdstr="";
		if(allocationList!=null&&allocationList.size()>0){
			for(BusinessAllocationVO ba:allocationList){
				if("".equals(goodsIdstr)){
					goodsIdstr=ba.getBusinessGoods().getId()+"";
				}else{
					goodsIdstr+=","+ba.getBusinessGoods().getId();
				}
			}
		}
		
		FarmerOrderVO farmerOrderVO = new FarmerOrderVO();
		
		FarmerOrderRecord farmerOrder = new FarmerOrderRecord();
		
		farmerOrder.setOrderRecordId(Long.parseLong(allocationId));
		
		farmerOrderVO.setFarmerOrder(farmerOrder);
		
		farmerOrderList = farmerOrderService.listVO(farmerOrderVO,0,0,null,null);
		
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
		log.debug("modify allocationId: " + allocationId);
		// 取得当前登录人信息
		currentStaff = super.getCurrentStaff();
		AllocationData ad = new AllocationData();
		if(allocationList!=null&&allocationList.size()>0){
			if(allocationId==null||"".equals(allocationId)){
				ad.setOrderRecord(allocationList.get(0).getBusinessOrderRecordStr());
				ad.setAllocationUser(currentStaff.getId()+"");
				ad.setAllocationTime(new Timestamp(System.currentTimeMillis()));
				ad.setStartTimeCondition(Timestamp.valueOf(createStartTime));
				ad.setEndTimeCondition(Timestamp.valueOf(createEndTime));
				ad.setAllocationStatus(0);
				allocationService.save(ad);
			}else{
				ad = allocationService.findByid(allocationId);
			}
			// 批量增加菜品
			log.debug("start:::");
			//原来的状态取消
			if(allocationId!=null&&!"".equals(allocationId)){
				businessOrderGoodsRelationService.removeAllocation(Integer.parseInt(allocationId));	
			}
			// 将ordergoodsrelation 置为分配状态 delflag=1 allocationId 为分配的id 
			for(BusinessAllocationVO ba:allocationList){
				String[] orderRelations = ba.getBusinessOrder().split(",");
				for(String s:orderRelations){
					orderGoodsRelationService.removeOrderRecordById(Long.parseLong(s),ad.getId());
				}
			}
			log.debug("end:::");
			
			//保存分配信息  先删除在添加
			if(allocationId!=null&&!"".equals(allocationId)){			
				List<FarmerOrderRecord> listFarmer = farmerOrderService.listByOrderId(Long.parseLong(allocationId));
				if(listFarmer!=null&&listFarmer.size()>0){
					for(FarmerOrderRecord farmerOrder:listFarmer){
						OutPlan plan = planService.findById(farmerOrder.getOutPlanId().toString());
						farmerOrder.setFarmerUserId(plan.getUserId());
						farmerOrder.setGoodsId(Integer.parseInt(plan.getGoodsId()));
						farmerOrder.setOrderRecordId(Long.valueOf(ad.getId()));
						farmerOrder.setCreateTime(new Timestamp(new Date().getTime()));
						farmerOrder.setStaffId(super.getCurrentStaff().getId());
						plan.setOccupyAmount(plan.getOccupyAmount()-farmerOrder.getGoodsCount());
						plan.setValidAmount(plan.getValidAmount()+farmerOrder.getGoodsCount());
						plan.setSendoutAmount(plan.getSendoutAmount()+farmerOrder.getGoodsCount());
						planService.modify(plan);
						farmerOrderService.del(farmerOrder.getId()+"");				
					}	
				}
			}
			
			if(items!=null){
				Iterator<String> item = items.keySet().iterator();
				
				while (item.hasNext()) {
					String key = item.next();
					FarmerOrderRecord farmerOrder = items.get(key);
					
					OutPlan plan = planService.findById(farmerOrder.getOutPlanId().toString());
					
					farmerOrder.setFarmerUserId(plan.getUserId());
					farmerOrder.setGoodsId(Integer.parseInt(plan.getGoodsId()));
					farmerOrder.setOrderRecordId(Long.valueOf(ad.getId()));
					farmerOrder.setCreateTime(new Timestamp(new Date().getTime()));
					farmerOrder.setStaffId(this.getCurrentStaff().getId());
					plan.setOccupyAmount(plan.getOccupyAmount()+farmerOrder.getGoodsCount());
					plan.setValidAmount(plan.getValidAmount()-farmerOrder.getGoodsCount());
					plan.setSendoutAmount(plan.getSendoutAmount()+farmerOrder.getGoodsCount());
					planService.modify(plan);
					farmerOrderService.save(farmerOrder);				
				}	
			}
			//修改分配的订单状态 为 订单处理中
			String[] orderArray = ad.getOrderRecord().split(",");
			for(String s:orderArray){
				BusinessOrderRecord bor = businessOrderRecordService.findByid(s);
				bor.setOperateStatus(204);
				bor.setOperateStatusName("处理中");
				businessOrderRecordService.modify(bor);
			}
		}
		
		if(allocationId==null||"".equals(allocationId)){			
			super.success(null, AjaxResult.CALL_BACK_TYPE_CLOSECURRENT,
					"allocationManager", null);
		}
		super.success(null, AjaxResult.CALL_BACK_TYPE_CLOSECURRENT,
				"allocationManager", null);
		
		return null;
	}
	
	/**
	 * 配送接口
	 * 
	 * @return String
	 * @throws Exception
	 */
	@Action(value = "send", results = { @Result(name = StrutsResMSG.SUCCESS, type = "plainText") })
	public String send() throws Exception {
		log.debug("send action!");
		log.debug("send allocationId: " + allocationId);
		AllocationData ad = allocationService.findByid(allocationId);

		//修改分配的订单状态 为 订单配送中
		String[] orderArray = ad.getOrderRecord().split(",");
		for(String s:orderArray){
			BusinessOrderRecord bor = businessOrderRecordService.findByid(s);
			bor.setDeliveryStatus(208);
			bor.setDeliveryStatusName("配送中");
			bor.setDeliveryStartTime(new Timestamp(new Date().getTime()));
			businessOrderRecordService.modify(bor);
		}
		//分配状态改为不可修改
		ad.setAllocationStatus(1);
		allocationService.modify(ad);
		super.success(null, null,
				"allocationManager", null);
		
		return null;
	}
	
	/**
	 * 更新对象接口
	 * 
	 * @return String
	 * @throws Exception
	 */
	@Action(value = "delInfo", results = { @Result(name = StrutsResMSG.SUCCESS, type = "plainText") })
	public String delInfo() throws Exception {
		log.debug("delInfo action!");
		log.debug("delInfo allocationId: " + allocationId);

		AllocationData ad = allocationService.findByid(allocationId);
		// 批量增加菜品
		businessOrderGoodsRelationService.removeAllocation(Integer.parseInt(allocationId));	
		
		//恢复分配信息 
		List<FarmerOrderRecord> listFarmer = farmerOrderService.listByOrderId(Long.parseLong(allocationId));
		if(listFarmer!=null&&listFarmer.size()>0){
			for(FarmerOrderRecord farmerOrder:listFarmer){
				OutPlan plan = planService.findById(farmerOrder.getOutPlanId().toString());
				farmerOrder.setFarmerUserId(plan.getUserId());
				farmerOrder.setGoodsId(Integer.parseInt(plan.getGoodsId()));
				farmerOrder.setOrderRecordId(Long.valueOf(ad.getId()));
				farmerOrder.setCreateTime(new Timestamp(new Date().getTime()));
				farmerOrder.setStaffId(super.getCurrentStaff().getId());
				plan.setOccupyAmount(plan.getOccupyAmount()-farmerOrder.getGoodsCount());
				plan.setValidAmount(plan.getValidAmount()+farmerOrder.getGoodsCount());
				plan.setSendoutAmount(plan.getSendoutAmount()+farmerOrder.getGoodsCount());
				planService.modify(plan);
				farmerOrderService.del(farmerOrder.getId()+"");				
			}	
		}
		//修改分配的订单状态 为 订单待处理
		String[] orderArray = ad.getOrderRecord().split(",");
		for(String s:orderArray){
			BusinessOrderRecord bor = businessOrderRecordService.findByid(s);
			bor.setOperateStatus(203);
			bor.setOperateStatusName("待处理");
			businessOrderRecordService.modify(bor);
		}
		
		allocationService.del(allocationId);
		super.success(null, null,
				"allocationManager", null);
		return null;
	}
	
	private void findPageInfo() {
		// 设置排序
		findDateTime();
		findPaymentStatusList();
		findPaymentProviderList();
		findOperateStatusList();
		findDeliveryStatus();
		findPaymentTypeList();
		this.voList = orderRecordService.listVO(orderNo, orderRecordId,
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

		this.totalCount = orderRecordService.getCount(orderNo, orderRecordId,
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

	private void findLogisticsCompanyList() {
		this.logisticsCompanyList = logisticsCompanyService.listAll();
	}

	/**
	 * 加载所有支付状态
	 */
	private void findPaymentStatusList() {
		this.paymentStatusList = statusService.find(BusinessOrderRecord.class,
				"businessPaymentStatus");
	}

	/**
	 * 加载所有支付提供者类型
	 */
	private void findPaymentProviderList() {
		this.paymentProviderList = statusService.find(BusinessOrderRecord.class,
				"businessPaymentProvider");
	}

	/**
	 * 加载所有订单处理状态
	 */
	private void findOperateStatusList() {
		this.operateStatusList = statusService.find(BusinessOrderRecord.class,
				"businessOperateStatus");
	}

	/**
	 * 加载所有订单派送状态
	 */
	private void findDeliveryStatus() {
		this.deliveryStatusList = statusService.find(BusinessOrderRecord.class,
				"businessDeliveryStatus");
	}

	/**
	 * 加载所有支付类型
	 */
	private void findPaymentTypeList() {
		this.paymentTypeList = statusService.find(BusinessOrderRecord.class,
				"businessPaymentType");
	}
	
	/**
	 * 加载所有分配状态
	 */
	private void findAllocationStatus() {
		this.allocationStatusList = statusService.find(BusinessOrderRecord.class,
				"businessAllocationStatus");
	}

	private void findDateTime() {
		// 设置默认时间
		if (StringHelper.isNull(createStartTime)
				&& StringHelper.isNull(createEndTime)) {
			this.createStartTime = StringHelper.getSystime("yyyy-MM-dd");
			createStartTime = createStartTime + " 00:00:00";
			this.createEndTime = StringHelper.getSystime("yyyy-MM-dd");
			createEndTime = createEndTime + " 23:59:59";
		}
		if (StringHelper.isNull(operateStatus)) {
			this.operateStatus = "204";
		}
	}

}