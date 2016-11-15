/**
 *2012-5-10 下午3:52:29
 */
package com.xnradmin.core.action.stat;

import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.cntinker.util.StringHelper;
import com.xnradmin.constant.StrutsResMSG;
import com.xnradmin.constant.ViewConstant;
import com.xnradmin.core.action.ParentAction;
import com.xnradmin.core.service.common.status.StatusService;
import com.xnradmin.core.service.mall.order.OrderGoodsRelationService;
import com.xnradmin.core.service.mall.order.OrderRecordService;
import com.xnradmin.core.service.mall.seting.LogisticsCompanyService;
import com.xnradmin.po.common.status.Status;
import com.xnradmin.po.mall.commodity.Goods;
import com.xnradmin.po.mall.order.OrderGoodsRelation;
import com.xnradmin.po.mall.order.OrderRecord;
import com.xnradmin.po.mall.seting.LogisticsCompany;
import com.xnradmin.vo.mall.OrderVO;

/**
 * @autohr: xiang_liu
 * 
 */
@Controller
@Scope("prototype")
@Namespace("/page/stat/order")
@ParentPackage("json-default")
public class OrderRecordStatAction extends ParentAction {

	@Autowired
	private OrderRecordService orderRecordService;

	@Autowired
	private StatusService statusService;

	@Autowired
	private OrderGoodsRelationService orderGoodsRelationService;

	@Autowired
	private LogisticsCompanyService logisticsCompanyService;

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

	private List<LogisticsCompany> logisticsCompanyList;

	private List<OrderVO> voList;

	private List<OrderVO> excelVoList;

	private List<OrderVO> dvList;

	private List<String[]> orderGoodsCountList;

	private OrderRecord orderRecord;

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

	@Override
	public boolean isPublic() {
		return false;
	};

	static Log log = LogFactory.getLog(OrderRecordStatAction.class);

	/**
	 * 跳转到信息页。。
	 * 
	 * @return
	 */
	@Action(value = "orderListStat", results = {
			@Result(name = StrutsResMSG.SUCCESS, location = "/stat/order/orderListStat.jsp"),
			@Result(name = StrutsResMSG.FAILED, location = "/stat/order/orderListStat.jsp") })
	public String info() {
		findPageInfo();
		findPageExcel();
		createExcel();
		createExcel2();
		return StrutsResMSG.SUCCESS;
	}

	/**
	 * 跳转到信息页。。
	 * 
	 * @return
	 */
	@Action(value = "orderGoodsCount", results = {
			@Result(name = StrutsResMSG.SUCCESS, location = "/stat/order/orderGoodsCount.jsp"),
			@Result(name = StrutsResMSG.FAILED, location = "/stat/order/orderGoodsCount.jsp") })
	public String orderGoodsCount() {
		findDateTime();
		findPaymentStatusList();
		findPaymentProviderList();
		findOperateStatusList();
		findDeliveryStatus();
		findPaymentTypeList();
		// 处理订单列表
		List<Object[]> list = orderRecordService.orderGoodsCount(orderNo,
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
				cusName, orderGoodsRelationPrimaryConfigurationId,
				primaryConfigurationName, 0, 0, orderField, orderDirection);
		this.totalCount = list.size();
		orderGoodsCountList = new ArrayList<String[]>();
		for (int i = 0; i < list.size(); i++) {
			String[] content = new String[2];
			Object[] a = list.get(i);
			if (a == null)
				continue;
			content[0] = a[0] == null ? "0" : a[0].toString();
			content[1] = a[1] == null ? "0" : a[1].toString();
			orderGoodsCountList.add(content);
		}
		return StrutsResMSG.SUCCESS;
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

	private void findPageExcel() {
		// 设置排序
		findDateTime();
		findPaymentStatusList();
		findPaymentProviderList();
		findOperateStatusList();
		findDeliveryStatus();
		findPaymentTypeList();
		// 处理订单列表
		this.excelVoList = orderRecordService.orderListExcel(orderNo,
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
				cusName, orderGoodsRelationPrimaryConfigurationId,
				primaryConfigurationName, 0, 0, orderField, orderDirection);
	}

	/**
	 * 带信息到修改页
	 * 
	 * @return String
	 */
	@Action(value = "detailed", results = {
			@Result(name = StrutsResMSG.SUCCESS, location = "/stat/order/detailed.jsp"),
			@Result(name = StrutsResMSG.FAILED, location = "/stat/order/detailed.jsp") })
	public String modifyinfo() {
		findPaymentStatusList();
		findPaymentProviderList();
		findLogisticsCompanyList();
		findOperateStatusList();
		findDeliveryStatus();
		findPaymentTypeList();
		// 加载该订单所有商品
		this.dvList = orderGoodsRelationService.listVO(orderRecordId,
				clientUserId, "", "", orderGoodsRelationPrimaryConfigurationId,
				deliveryStaffId, "", createStartTime, createEndTime,
				getPageNum(), getNumPerPage(), orderField, orderDirection);
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
			this.userRealTime = orderRecord.getUserRealTime().toString();
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
		if (orderRecord.getLogisticsCompanyId() != null) {
			this.logisticsCompanyId = orderRecord.getLogisticsCompanyId()
					.toString();
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

	private void findLogisticsCompanyList() {
		this.logisticsCompanyList = logisticsCompanyService.listAll();
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
			this.operateStatus = "169";
		}
	}

	/**
	 * 生成EXCEL
	 */
	public void createExcel() {
		// 第一步，创建一个webbook，对应一个Excel文件
		HSSFWorkbook wb = new HSSFWorkbook();
		// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
		HSSFSheet sheet = wb.createSheet("派送单");
		// 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
		HSSFRow row = sheet.createRow((int) 0);
		// 第四步，创建单元格，并设置值表头 设置表头居中
		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
		HSSFCell cell = row.createCell(0);
		cell.setCellValue("下单日期");
		cell.setCellStyle(style);
		cell = row.createCell(1);
		cell.setCellValue("送货日期");
		cell.setCellStyle(style);
		cell = row.createCell(2);
		cell.setCellValue("客户姓名");
		cell.setCellStyle(style);
		cell = row.createCell(3);
		cell.setCellValue("电话号码");
		cell.setCellStyle(style);
		cell = row.createCell(4);
		cell.setCellValue("送货地址");
		cell.setCellStyle(style);
		cell = row.createCell(5);
		cell.setCellValue("送货时间");
		cell.setCellStyle(style);
		cell = row.createCell(6);
		cell.setCellValue("订单商品");
		cell.setCellStyle(style);
		cell = row.createCell(7);
		cell.setCellValue("支付状态");
		cell.setCellStyle(style);
		cell = row.createCell(8);
		cell.setCellValue("支付金额");
		cell.setCellStyle(style);
		cell = row.createCell(9);
		cell.setCellValue("备注");
		cell.setCellStyle(style);
		// 第五步，写入实体数据 实际应用中这些数据从数据库得到，
		for (int i = 0; i < excelVoList.size(); i++) {
			row = sheet.createRow((int) i + 1);
			OrderVO vo = (OrderVO) excelVoList.get(i);
			// 第四步，创建单元格，并设置值
			row.createCell(0).setCellValue(vo.getOrderRecordCreateTime());
			row.createCell(1)
					.setCellValue(vo.getOrderRecordDeliveryStartTime());
			row.createCell(2).setCellValue(vo.getOrderRecordUserRealName());
			row.createCell(3).setCellValue(vo.getOrderRecordUserRealMobile());
			row.createCell(4).setCellValue(
					vo.getOrderRecordProvinceName()
							+ vo.getOrderRecordCityName()
							+ vo.getOrderRecordAreaName()
							+ vo.getOrderRecordUserRealAddress());
			row.createCell(5).setCellValue(vo.getOrderRecordUserRealTime());
			String tempGoods = "";
			List<Goods> tempGoodsList = excelVoList.get(i).getGoodsList();
			List<OrderGoodsRelation> tempGoodsRelationList = excelVoList.get(i)
					.getOrderGoodsRelationList();
			for (int j = 0; j < tempGoodsList.size(); j++) {
				Goods gd = tempGoodsList.get(j);
				OrderGoodsRelation ogr = tempGoodsRelationList.get(j);
				tempGoods = tempGoods + gd.getGoodsName() + "X"
						+ ogr.getGoodsCount() + ",";
			}
			row.createCell(6).setCellValue(tempGoods);
			row.createCell(7).setCellValue(
					vo.getOrderRecordPaymentProviderName());
			row.createCell(8).setCellValue(vo.getOrderRecordTotalPrice());
			row.createCell(9).setCellValue(
					vo.getOrderRecordUserRealDescription());
		}
		// 第六步，将文件存到指定位置
		try {
			FileOutputStream fout = new FileOutputStream(ServletActionContext
					.getServletContext().getRealPath("")
					+ "/themes/mall/excel/orderListExcel.xls");
			wb.write(fout);
			fout.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void createExcel2() {
		// 第一步，创建一个webbook，对应一个Excel文件
		HSSFWorkbook wb = new HSSFWorkbook();
		// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
		HSSFSheet sheet = wb.createSheet("派送单");
		// 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
		HSSFRow row = sheet.createRow((int) 0);
		// 第四步，创建单元格，并设置值表头 设置表头居中
		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
		HSSFCell cell = row.createCell(0);
		cell = row.createCell(1);
		cell.setCellValue("菜品名称");
		cell.setCellStyle(style);
		cell = row.createCell(2);
		cell.setCellValue("客户姓名");
		cell.setCellStyle(style);
		cell = row.createCell(3);
		cell.setCellValue("电话号码");
		cell.setCellStyle(style);
		cell = row.createCell(4);
		cell.setCellValue("送货地址");
		cell.setCellStyle(style);
		cell = row.createCell(5);
		cell.setCellValue("支付状态");
		cell.setCellStyle(style);
		cell = row.createCell(6);
		cell.setCellValue("送货时间");
		cell.setCellStyle(style);
		cell = row.createCell(7);
		cell.setCellValue("支付金额");
		cell.setCellStyle(style);
		// 第五步，写入实体数据 实际应用中这些数据从数据库得到，
		for (int i = 0; i < excelVoList.size(); i++) {
			row = sheet.createRow((int) i + 1);
			OrderVO vo = (OrderVO) excelVoList.get(i);
			// 第四步，创建单元格，并设置值
			String tempGoods = "";
			List<Goods> tempGoodsList = excelVoList.get(i).getGoodsList();
			List<OrderGoodsRelation> tempGoodsRelationList = excelVoList.get(i)
					.getOrderGoodsRelationList();
			for (int j = 0; j < tempGoodsList.size(); j++) {
				Goods gd = tempGoodsList.get(j);
				OrderGoodsRelation ogr = tempGoodsRelationList.get(j);
				tempGoods = tempGoods + gd.getGoodsName() + "X"
						+ ogr.getGoodsCount() + ",";
			}
			row.createCell(1).setCellValue(tempGoods);
			row.createCell(2).setCellValue(vo.getOrderRecordUserRealName());
			row.createCell(3).setCellValue(vo.getOrderRecordUserRealMobile());
			row.createCell(4).setCellValue(
					vo.getOrderRecordProvinceName()
							+ vo.getOrderRecordCityName()
							+ vo.getOrderRecordAreaName()
							+ vo.getOrderRecordUserRealAddress());
			row.createCell(5).setCellValue(
					vo.getOrderRecordPaymentProviderName());
			row.createCell(6).setCellValue(vo.getOrderRecordUserRealTime());
			row.createCell(7).setCellValue(vo.getOrderRecordTotalPrice());
		}
		// 第六步，将文件存到指定位置
		try {
			FileOutputStream fout = new FileOutputStream(ServletActionContext
					.getServletContext().getRealPath("")
					+ "/themes/mall/excel/orderListExcel2.xls");
			wb.write(fout);
			fout.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}