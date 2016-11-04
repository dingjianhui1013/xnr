/**
 *2012-5-10 下午3:52:29
 */
package com.xnradmin.stat.action.business;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
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
import com.xnradmin.constant.StrutsResMSG;
import com.xnradmin.core.action.ParentAction;
import com.xnradmin.core.service.StaffService;
import com.xnradmin.core.service.business.commodity.BusinessGoodsService;
import com.xnradmin.core.service.business.commodity.BusinessWeightService;
import com.xnradmin.core.service.business.order.BusinessOrderGoodsRelationService;
import com.xnradmin.core.service.common.status.StatusService;
import com.xnradmin.core.service.mall.region.AreaService;
import com.xnradmin.core.service.mall.seting.LogisticsCompanyService;
import com.xnradmin.core.service.mall.seting.PrimaryConfigurationService;
import com.xnradmin.po.CommonStaff;
import com.xnradmin.po.business.BusinessGoods;
import com.xnradmin.po.business.BusinessOrderRecord;
import com.xnradmin.po.business.BusinessWeight;
import com.xnradmin.po.client.ClientUserRegionInfo;
import com.xnradmin.po.common.status.Status;
import com.xnradmin.po.mall.region.Area;
import com.xnradmin.po.mall.region.City;
import com.xnradmin.po.mall.region.Country;
import com.xnradmin.po.mall.region.Province;
import com.xnradmin.po.mall.seting.LogisticsCompany;
import com.xnradmin.stat.service.business.AccountManagerStatService;
import com.xnradmin.vo.StaffVO;
import com.xnradmin.vo.business.BusinessOrderVO;

/**
 * @autohr: xiang_liu
 * 
 */
@Controller
@Scope("prototype")
@Namespace("/page/stat/business")
@ParentPackage("json-default")
public class AccountManagerStatAction extends ParentAction {

	@Autowired
	private AccountManagerStatService accountManagerStatService;

	@Autowired
	private BusinessOrderGoodsRelationService orderGoodsRelationService;

	@Autowired
	private BusinessGoodsService goodsService;

	@Autowired
	private ClientUserInfoService clientUserInfoService;

	@Autowired
	private BusinessWeightService weightService;

	@Autowired
	private ClientUserRegionInfoService clientUserRegionInfoService;

	@Autowired
	private PrimaryConfigurationService primaryConfigurationService;

	@Autowired
	private LogisticsCompanyService logisticsCompanyService;
	@Autowired
	private AreaService areaService;
	@Autowired
	private StatusService statusService;

	@Autowired
	private StaffService staffService;

	private CommonStaff staff;

	private CommonStaff leaderStaff;

	@Override
	public boolean isPublic() {
		return false;
	};

	static Log log = LogFactory.getLog(AccountManagerStatAction.class);

	/**
	 * 跳转到信息页。。
	 * 
	 * @return
	 */
	@Action(value = "accountManager", results = {
			@Result(name = StrutsResMSG.SUCCESS, location = "/stat/business/accountManager.jsp"),
			@Result(name = StrutsResMSG.FAILED, location = "/stat/business/accountManager.jsp") })
	public String accountManager() {
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
		findStaffList();
		BusinessOrderVO vo = new BusinessOrderVO();
		BusinessOrderRecord po = new BusinessOrderRecord();
		if (!StringHelper.isNull(orderGoodsRelationClientUserId)) {
			po.setClientUserId(Integer.parseInt(orderGoodsRelationClientUserId));
		}
		po.setOrderNo(orderNo);
		if (!StringHelper.isNull(orderRecordId)) {
			po.setId(Long.valueOf(orderRecordId));
		}
		po.setClientUserName(clientUserName);
		po.setClientUserEmail(clientUserEmail);
		po.setClientUserMobile(clientUserMobile);
		po.setClientUserSex(clientUserSex);
		if (!StringHelper.isNull(clientUserType)) {
			po.setClientUserType(Integer.parseInt(clientUserType));
		}
		po.setClientUserTypeName(clientUserTypeName);
		po.setWxOpenId(wxOpenId);
		po.setStaffId(orderGoodsRelationStaffId);
		po.setUserRealMobile(userRealMobile);
		po.setUserRealPlane(userRealPlane);
		po.setUserRealName(userRealName);
		if (!StringHelper.isNull(countryId)) {
			po.setCountryId(Integer.parseInt(countryId));
		}
		po.setCountryName(countryName);
		if (!StringHelper.isNull(provinceId)) {
			po.setProvinceId(Integer.parseInt(provinceId));
		}
		po.setProvinceName(provinceName);
		if (!StringHelper.isNull(cityId)) {
			po.setCityId(Integer.parseInt(cityId));
		}
		po.setCityName(cityName);
		if (!StringHelper.isNull(areaId)) {
			po.setAreaId(Integer.parseInt(areaId));
		}
		po.setAreaName(areaName);
		po.setUserRealAddress(userRealAddress);
		po.setUserRealPostcode(userRealPostcode);
		if (!StringHelper.isNull(paymentType)) {
			po.setPaymentType(Integer.parseInt(paymentType));
		}
		po.setPaymentTypeName(paymentTypeName);
		if (!StringHelper.isNull(paymentStatus)) {
			po.setPaymentStatus(Integer.parseInt(paymentStatus));
		}
		po.setPaymentStatusName(paymentStatusName);
		if (!StringHelper.isNull(paymentProvider)) {
			po.setPaymentProvider(Integer.parseInt(paymentProvider));
		}
		po.setPaymentProviderName(paymentProviderName);
		if (!StringHelper.isNull(operateStatus)) {
			if (!operateStatus.equals("0")) {
				po.setOperateStatus(Integer.parseInt(operateStatus));
			}
		}
		po.setOperateStatusName(operateStatusName);
		if (!StringHelper.isNull(originalPrice)) {
			po.setOriginalPrice(Float.valueOf(originalPrice));
		}
		if (!StringHelper.isNull(totalPrice)) {
			po.setTotalPrice(Float.valueOf(totalPrice));
		}
		if (!StringHelper.isNull(purchasePrice)) {
			po.setPurchasePrice(Float.valueOf(purchasePrice));
		}
		if (!StringHelper.isNull(discount)) {
			po.setDiscount(Float.valueOf(discount));
		}
		if (!StringHelper.isNull(logisticsCompanyId)) {
			po.setLogisticsCompanyId(Integer.parseInt(logisticsCompanyId));
		}
		po.setLogisticsCompanyName(logisticsCompanyName);
		po.setDeliveryStaffId(deliveryStaffId);
		po.setDeliveryStaffName(deliveryStaffName);
		po.setDeliveryStaffMobile(deliveryStaffMobile);
		if (!StringHelper.isNull(deliveryStatus)) {
			po.setDeliveryStatus(Integer.parseInt(deliveryStatus));
		}
		po.setDeliveryStatusName(deliveryStatusName);
		if (!StringHelper.isNull(sourceId)) {
			po.setSourceId(Integer.parseInt(sourceId));
		}
		po.setSourceName(sourceName);
		if (!StringHelper.isNull(sourceType)) {
			po.setSourceType(Integer.parseInt(sourceType));
		}
		po.setSourceTypeName(sourceTypeName);
		po.setSerNo(serNo);
		if (!StringHelper.isNull(sellerId)) {
			po.setSellerId(Integer.parseInt(sellerId));
		}
		po.setSellerName(sellerName);
		if (!StringHelper.isNull(cusId)) {
			po.setCusId(Integer.parseInt(cusId));
		}
		po.setCusName(cusName);
		if (!StringHelper.isNull(orderGoodsRelationPrimaryConfigurationId)) {
			po.setPrimaryConfigurationId(Integer
					.parseInt(orderGoodsRelationPrimaryConfigurationId));
		}
		po.setPrimaryConfigurationName(primaryConfigurationName);
		vo.setBusinessOrderRecord(po);
		vo.setUserRealStartTime(userRealStartTime);
		vo.setUserRealEndTime(userRealEndTime);
		vo.setPaymentStartTime(paymentStartTime);
		vo.setPaymentEndTime(paymentEndTime);
		vo.setOperateStartTime(operateStartTime);
		vo.setOperateEndTime(operateEndTime);
		vo.setCreateStartTime(createStartTime);
		vo.setCreateEndTime(createEndTime);
		vo.setDeliveryStartStartTime(deliveryStartStartTime);
		vo.setDeliveryStartEndTime(deliveryStartEndTime);
		vo.setDeliveryEndStartTime(deliveryEndStartTime);
		vo.setDeliveryEndEndTime(deliveryEndEndTime);
		vo.setRequiredDeliveryStartTime(requiredDeliveryStartTime);
		vo.setRequiredDeliveryEndTime(requiredDeliveryEndTime);
		vo.setFinalDeliveryStartTime(finalDeliveryStartTime);
		vo.setFinalDeliveryEndTime(finalDeliveryEndTime);
		vo.setLeaderStaff(leaderStaff);
		vo.setStaffCreateEndTime(staffCreateEndTime);
		vo.setStaffCreateStartTime(staffCreateStartTime);
		List<Object[]> list = accountManagerStatService.findAccountManagerStat(vo, getPageNum(), getNumPerPage());
		this.totalCount = list.size();
		List<Object[]> leadStaffList = accountManagerStatService.findLeadStaffCount(vo);
		HashMap map = new HashMap();
		for (int i = 0; i < leadStaffList.size(); i++) {
			String[] content = new String[2];
			Object[] a = leadStaffList.get(i);
			if (a == null)
				continue;
			content[0] = a[0] == null ? "0" : a[0].toString();
			content[1] = a[1] == null ? "0" : a[1].toString();
			map.put(content[0], content[1]);
		}
		priceList = new ArrayList<String[]>();
		for (int i = 0; i < list.size(); i++) {
			String[] content = new String[5];
			Object[] a = list.get(i);
			if (a == null)
				continue;
			content[0] = a[0] == null ? "0" : a[0].toString();
			content[1] = a[1] == null ? "0" : a[1].toString();
			content[2] = a[2] == null ? "0" : a[2].toString();
			content[3] = a[3] == null ? "0" : a[3].toString();
			BigDecimal b = new BigDecimal(content[3]);
			content[3] = b.setScale(2, java.math.BigDecimal.ROUND_HALF_UP).toString();
			if(map != null && map.get(content[0]) != null){
				content[4] = map.get(content[0]).toString();
			}
			priceList.add(content);
		}
		
	}

	/**
	 * 加载所有支付状态
	 */
	private void findPaymentStatusList() {
		this.paymentStatusList = statusService.find(BusinessOrderRecord.class,
				"BusinessPaymentStatus");
	}

	/**
	 * 加载所有支付提供者类型
	 */
	private void findPaymentProviderList() {
		this.paymentProviderList = statusService.find(
				BusinessOrderRecord.class, "BusinessPaymentProvider");
	}

	/**
	 * 加载所有订单处理状态
	 */
	private void findOperateStatusList() {
		this.operateStatusList = statusService.find(BusinessOrderRecord.class,
				"BusinessOperateStatus");
	}

	/**
	 * 加载所有订单派送状态
	 */
	private void findDeliveryStatus() {
		this.deliveryStatusList = statusService.find(BusinessOrderRecord.class,
				"BusinessDeliveryStatus");
	}

	/**
	 * 加载所有支付类型
	 */
	private void findPaymentTypeList() {
		this.paymentTypeList = statusService.find(BusinessOrderRecord.class,
				"BusinessPaymentType");
	}

	/*
	 * 加载所有商务部门用户
	 */
	private void findStaffList() {
		this.staffList = staffService.listVO("", "", "", "16", "", "", "", "",
				"", 0, 0, 0, "", "");
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
			this.operateStatus = "204";
		}

	}

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
	private List<BusinessGoods> allGoodsList;
	private List<ClientUserRegionInfo> clientUserRegionInfoList;
	private Map<String, BusinessOrderVO> goodsList;
	private List<BusinessOrderVO> dvList;
	private List<LogisticsCompany> logisticsCompanyList;
	private CommonStaff currentStaff;
	private CommonStaff deliveryStaff;
	private List<BusinessOrderVO> voList;
	private List<String[]> priceList;
	private BusinessOrderVO businessOrderVO;
	private BusinessOrderRecord orderRecord;
	private BusinessGoods goods;
	private Status status;
	private Status isDiscount;
	private Country countryPO;
	private Province provincePO;
	private City cityPO;
	private Area areaPO;

	private String divid;

	// orderRecord
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

	private String purchasePrice; // 进货价格

	private String discount; // 当前用户折扣

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

	private String requiredDeliveryTime; // 用户要求送达时间

	private String requiredDeliveryStartTime; // 用户要求送达查询起始时间

	private String requiredDeliveryEndTime; // 用户要求送达查询结束时间

	private String finalDeliveryTime; // 最终送达时间

	private String finalDeliveryStartTime; // 最终送达查询起始时间

	private String finalDeliveryEndTime; // 最终送达查询结束时间

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

	private String staffCreateStartTime; //用户注册起始时间
	
	private String staffCreateEndTime; //用户注册结束时间
	
	private String leadStaffId; //上级领导Id
	
	private String leadStaffName; //上级领导名称
	
	private List<BusinessWeight> weightList;

	private List<Status> billList;

	private List<Status> buyTeamList;

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

	public String getUserRealDescription() {
		return userRealDescription;
	}

	public void setUserRealDescription(String userRealDescription) {
		this.userRealDescription = userRealDescription;
	}

	public String getUserRealTime() {
		return userRealTime;
	}

	public void setUserRealTime(String userRealTime) {
		this.userRealTime = userRealTime;
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

	public String getOperateTime() {
		return operateTime;
	}

	public void setOperateTime(String operateTime) {
		this.operateTime = operateTime;
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

	public String getRequiredDeliveryTime() {
		return requiredDeliveryTime;
	}

	public void setRequiredDeliveryTime(String requiredDeliveryTime) {
		this.requiredDeliveryTime = requiredDeliveryTime;
	}

	public String getFinalDeliveryTime() {
		return finalDeliveryTime;
	}

	public void setFinalDeliveryTime(String finalDeliveryTime) {
		this.finalDeliveryTime = finalDeliveryTime;
	}

	public String getRequiredDeliveryStartTime() {
		return requiredDeliveryStartTime;
	}

	public void setRequiredDeliveryStartTime(String requiredDeliveryStartTime) {
		this.requiredDeliveryStartTime = requiredDeliveryStartTime;
	}

	public String getRequiredDeliveryEndTime() {
		return requiredDeliveryEndTime;
	}

	public void setRequiredDeliveryEndTime(String requiredDeliveryEndTime) {
		this.requiredDeliveryEndTime = requiredDeliveryEndTime;
	}

	public String getFinalDeliveryStartTime() {
		return finalDeliveryStartTime;
	}

	public void setFinalDeliveryStartTime(String finalDeliveryStartTime) {
		this.finalDeliveryStartTime = finalDeliveryStartTime;
	}

	public String getFinalDeliveryEndTime() {
		return finalDeliveryEndTime;
	}

	public void setFinalDeliveryEndTime(String finalDeliveryEndTime) {
		this.finalDeliveryEndTime = finalDeliveryEndTime;
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

	public String getDeliveryEndTime() {
		return deliveryEndTime;
	}

	public void setDeliveryEndTime(String deliveryEndTime) {
		this.deliveryEndTime = deliveryEndTime;
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

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
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

	public String getClientUserRegionInfoId() {
		return clientUserRegionInfoId;
	}

	public void setClientUserRegionInfoId(String clientUserRegionInfoId) {
		this.clientUserRegionInfoId = clientUserRegionInfoId;
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

	public List<StaffVO> getStaffList() {
		return staffList;
	}

	public void setStaffList(List<StaffVO> staffList) {
		this.staffList = staffList;
	}

	public List<BusinessGoods> getAllGoodsList() {
		return allGoodsList;
	}

	public void setAllGoodsList(List<BusinessGoods> allGoodsList) {
		this.allGoodsList = allGoodsList;
	}

	public List<ClientUserRegionInfo> getClientUserRegionInfoList() {
		return clientUserRegionInfoList;
	}

	public void setClientUserRegionInfoList(
			List<ClientUserRegionInfo> clientUserRegionInfoList) {
		this.clientUserRegionInfoList = clientUserRegionInfoList;
	}

	public Map<String, BusinessOrderVO> getGoodsList() {
		return goodsList;
	}

	public void setGoodsList(Map<String, BusinessOrderVO> goodsList) {
		this.goodsList = goodsList;
	}

	public List<BusinessOrderVO> getDvList() {
		return dvList;
	}

	public void setDvList(List<BusinessOrderVO> dvList) {
		this.dvList = dvList;
	}

	public List<LogisticsCompany> getLogisticsCompanyList() {
		return logisticsCompanyList;
	}

	public void setLogisticsCompanyList(
			List<LogisticsCompany> logisticsCompanyList) {
		this.logisticsCompanyList = logisticsCompanyList;
	}

	public CommonStaff getCurrentStaff() {
		return currentStaff;
	}

	public void setCurrentStaff(CommonStaff currentStaff) {
		this.currentStaff = currentStaff;
	}

	public CommonStaff getDeliveryStaff() {
		return deliveryStaff;
	}

	public void setDeliveryStaff(CommonStaff deliveryStaff) {
		this.deliveryStaff = deliveryStaff;
	}

	public List<BusinessOrderVO> getVoList() {
		return voList;
	}

	public void setVoList(List<BusinessOrderVO> voList) {
		this.voList = voList;
	}

	public List<String[]> getPriceList() {
		return priceList;
	}

	public void setPriceList(List<String[]> priceList) {
		this.priceList = priceList;
	}

	public BusinessOrderVO getBusinessOrderVO() {
		return businessOrderVO;
	}

	public void setBusinessOrderVO(BusinessOrderVO businessOrderVO) {
		this.businessOrderVO = businessOrderVO;
	}

	public BusinessOrderRecord getOrderRecord() {
		return orderRecord;
	}

	public void setOrderRecord(BusinessOrderRecord orderRecord) {
		this.orderRecord = orderRecord;
	}

	public BusinessGoods getGoods() {
		return goods;
	}

	public void setGoods(BusinessGoods goods) {
		this.goods = goods;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getPurchasePrice() {
		return purchasePrice;
	}

	public void setPurchasePrice(String purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	public String getDiscount() {
		return discount;
	}

	public void setDiscount(String discount) {
		this.discount = discount;
	}

	public Status getIsDiscount() {
		return isDiscount;
	}

	public void setIsDiscount(Status isDiscount) {
		this.isDiscount = isDiscount;
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

	public String getDivid() {
		return divid;
	}

	public void setDivid(String divid) {
		this.divid = divid;
	}

	public List<BusinessWeight> getWeightList() {
		return weightList;
	}

	public void setWeightList(List<BusinessWeight> weightList) {
		this.weightList = weightList;
	}

	public CommonStaff getStaff() {
		return staff;
	}

	public void setStaff(CommonStaff staff) {
		this.staff = staff;
	}

	public CommonStaff getLeaderStaff() {
		return leaderStaff;
	}

	public void setLeaderStaff(CommonStaff leaderStaff) {
		this.leaderStaff = leaderStaff;
	}

	public List<Status> getBillList() {
		return billList;
	}

	public void setBillList(List<Status> billList) {
		this.billList = billList;
	}

	public List<Status> getBuyTeamList() {
		return buyTeamList;
	}

	public void setBuyTeamList(List<Status> buyTeamList) {
		this.buyTeamList = buyTeamList;
	}

	public String getStaffCreateStartTime() {
		return staffCreateStartTime;
	}

	public void setStaffCreateStartTime(String staffCreateStartTime) {
		this.staffCreateStartTime = staffCreateStartTime;
	}

	public String getStaffCreateEndTime() {
		return staffCreateEndTime;
	}

	public void setStaffCreateEndTime(String staffCreateEndTime) {
		this.staffCreateEndTime = staffCreateEndTime;
	}

	public String getLeadStaffId() {
		return leadStaffId;
	}

	public void setLeadStaffId(String leadStaffId) {
		this.leadStaffId = leadStaffId;
	}

	public String getLeadStaffName() {
		return leadStaffName;
	}

	public void setLeadStaffName(String leadStaffName) {
		this.leadStaffName = leadStaffName;
	}
	
	
}
