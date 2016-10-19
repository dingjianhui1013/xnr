package com.xnradmin.vo.mall;

import java.sql.Timestamp;
import java.util.List;

import com.xnradmin.po.dishes.Dish;
import com.xnradmin.po.mall.commodity.Goods;
import com.xnradmin.po.mall.commodity.GoodsDishRelation;
import com.xnradmin.po.mall.order.OrderGoodsRelation;
import com.xnradmin.po.mall.order.OrderRecord;
import com.xnradmin.po.mall.order.ShoppingCart;

public class OrderVO {

	/**
	 * 
	 */
	private static final Long serialVersionUID = 1L;

	//orderRecord
	private String orderRecordId;

    private String orderRecordClientUserId; //用户ID
    
    private String orderRecordClientUserName; //用户昵称 
    
    private String orderRecordClientUserMobile; //用户手机号
    
    private String orderRecordClientUserEmail; //用户邮箱
    
    private String orderRecordClientUserSex; //用户性别
    
    private String orderRecordClientUserType; //用户类型（微信，APP，WEB，电话）
    
    private String orderRecordClientUserTypeName; //用户类型名称（微信，APP，WEB，电话）
    
    private String orderRecordWxOpenId; //微信OpenId
    
    private String orderRecordStaffId;	//隶属用户Id
    
    private String orderRecordUserRealMobile; //收货人用户手机号
    
    private String orderRecordUserRealPlane; //收货人用户座机

    private String orderRecordUserRealName; //收货人名称
    
    private String orderRecordCountryId; //收货人国家
	
	private String orderRecordCountryName; //收货人国家名称
	
	private String orderRecordProvinceId; //收货人省份
	
	private String orderRecordProvinceName; //收货人省份名称
	
	private String orderRecordCityId; //收货人城市
	
	private String orderRecordCityName; //收货人城市名称
	
	private String orderRecordAreaId; //收货人区县
	
	private String orderRecordAreaName; //收货人区县名称
	
    private String orderRecordUserRealAddress; //收货人地址
    
    private String orderRecordUserRealPostcode; //邮政编码
    
    private String orderRecordUserRealTime; //用户收货时间
    
    private String orderRecordUserRealDescription; //用户收货描述
    
    private String orderRecordPaymentType; //支付类型，充值账户支付，单次支付
    
    private String orderRecordPaymentTypeName; //支付类型名称，充值账户支付，单次支付

    private String orderRecordPaymentStatus; //支付状态，已支付，待支付，退款
    
    private String orderRecordPaymentStatusName; //支付状态名称，已支付，待支付，退款
    
    private String orderRecordPaymentProvider; //支付提供者，微信，支付宝，银联
    
    private String orderRecordPaymentProviderName; //支付提供者名称，微信，支付宝，银联
    
    private String orderRecordPaymentTime; //支付时间

    private String orderRecordOperateTime; //订单操作时间（待处理，处理中，处理完成，订单退单）
    
    private String orderRecordOperateStatus; //订单操作状态（待处理，处理中，处理完成，订单退单）
    
    private String orderRecordOperateStatusName; //订单操作状态名称（待处理，处理中，处理完成，订单退单）
    
    private String orderRecordCreateTime; //订单生成时间

    private String orderRecordOriginalPrice; //原始结算价格
    
    private String orderRecordTotalPrice; //最终结算价格
    
    private String orderRecordLogisticsCompanyId; //配送方ID
    
    private String orderRecordLogisticsCompanyName; //配送方名称
    
    private String orderRecordDliveryStaffId; //送货人员ID
    
    private String orderRecordDeliveryStaffName; //送货人员姓名
    
    private String orderRecordDeliveryStaffMobile; //送货人员手机号
    
    private String orderRecordDeliveryStartTime; //送货起始时间
    
    private String orderRecordDeliveryEndTime; //送货送达时间
    
    private String orderRecordDeliveryStatus; //配送状态（未发货，已发货）
    
    private String orderRecordDeliveryStatusName; //配送状态名称（未发货，已发货）
    
    private String orderRecordSourceId; //该订单用户推广来源ID（有可能是用户推广，代理商推广，线上推广）
    
    private String orderRecordSourceName; //该订单用户推广来源ID（有可能是用户推广，代理商推广，线上推广）
    
    private String orderRecordSourceType; //该订单用户推广来源类型 （有可能是用户推广，代理商推广，线上推广）
    
    private String orderRecordSourceTypeName; //该订单用户推广来源类型名称 （有可能是用户推广，代理商推广，线上推广）
    
    private String orderRecordSerNo; //银行流水号

    private String orderRecordSellerId; //代理商ID
    
    private String orderRecordSellerName; //代理商名称
    
    private String orderRecordCusId; //合作方管理
    
    private String orderRecordCusName; //合作方名称
    
    private String orderRecordPrimaryConfigurationId; //对应商城ID
    
    private String orderRecordPrimaryConfigurationName; //对应商城名称
    
    //orderGoodsRelation
    private String orderGoodsRelationId;

	private String orderGoodsRelationOrderRecordId; //订单ID
    
	private String orderNo;
	
	private String orderGoodsRelationClientUserId; //用户ID
	
	private String orderGoodsRelationGoodsId; //商品Id
    
    private String orderGoodsRelationGoodsCount; //商品数量
    
    private String orderGoodsRelationCurrentPrice; //当前价格
    
    private String orderGoodsRelationCurrentPriceType; //当前价格类型（原价，优惠价）
    
    private String orderGoodsRelationStaffId;	//隶属用户Id
    
    private String orderGoodsRelationPrimaryConfigurationId; //对应商城ID
	
    private String orderGoodsRelationTime; //生成时间 
    
    //shoppingCart
    private String shoppingCartId;
    
	private String shoppingCartClientUserId; //用户ID
	
	private String shoppingCartGoodsId; //商品Id
    
    private String shoppingCartGoodsCount; //商品数量
    
    private String shoppingCartCurrentPrice; //当前价格
    
    private String shoppingCartOriginalPrice; //原始价格
    
    private String shoppingCartCurrentPriceType; //当前价格类型（原价，优惠价）
    
    private String shoppingCartTotalPrice; //当前总价
    
    private String shoppingCartOriginalTotalPrice; //原始总价
    
    private String shoppingCartStaffId;	//隶属用户Id
    
    private String shoppingCartPrimaryConfigurationId; //对应商城ID
	
    private String shoppingCartTime; //生成时间 

    //search
    private String searchId;	//搜索记录表Id
    
    private String searchClientUserInfoId;	//搜索用户ID
    
    private String searchValue; //搜索文字
	
    private String searchTime; //搜索时间 
    
    //purchase
    private Long purchaseId;
    
	private Integer purchaseClientUserId; //用户ID
	
	private Long purchaseOrderRecordId; //订单ID
	
	private String purchaseOrderNo; //订单号
	
	private String purchaseGoodsId; //商品ID
	
	private String purchaseGoodsName; //商品名称
	
	private String purchaseGoodsCategoryId; //商品类型ID
	
	private String purchaseGoodsCategoryName; //商品类型名称
	
	private String purchaseGoodsCount; //商品数量
	
	private String purchaseDishId; //菜品ID
	
	private String purchaseDishName; //菜品名称
	
	private String purchaseDishTypeId; //菜品类型ID
	
	private String purchaseDishTypeName; //菜品类型名称
    
	private String purchaseDishCount; //菜品数量
	
	private String purchaseRawMaterialId; //材料ID
	
	private String purchaseRawMaterialName; //材料名称
	
	private String purchaseRawMaterialTypeId; //材料类型ID
	
	private String purchaseRawMaterialTypeName; //材料类型名称
	
	private String purchaseWeightId; //数量单位ID
	
	private String purchaseWeightName; //数量单位名称
	
	private String purchaseNumber; //采购数量
	
    private Timestamp purchaseCreateTime; //搜索时间 
    
	//dish
	private Dish dish;	//菜品
	
	//goods
	private Goods goods; //商品
	
	//goods
	private List<Goods> goodsList; //商品列表
		
	//goodsDishRelation
	private GoodsDishRelation goodsDishRelation; //中间表
		
	//orderRecord
	private OrderRecord orderRecord;	//订单
	
	//orderGoodsRelation
	private OrderGoodsRelation orderGoodsRelation; //订单商品对应表
	
	//orderGoodsRelation
	private List<OrderGoodsRelation> orderGoodsRelationList; //订单商品对应列表
	
	//shoppingCart
	private ShoppingCart shoppingCart; //购物车表

	public String getOrderRecordId() {
		return orderRecordId;
	}

	public void setOrderRecordId(String orderRecordId) {
		this.orderRecordId = orderRecordId;
	}

	public String getOrderRecordClientUserId() {
		return orderRecordClientUserId;
	}

	public void setOrderRecordClientUserId(String orderRecordClientUserId) {
		this.orderRecordClientUserId = orderRecordClientUserId;
	}

	public String getOrderRecordClientUserName() {
		return orderRecordClientUserName;
	}

	public void setOrderRecordClientUserName(String orderRecordClientUserName) {
		this.orderRecordClientUserName = orderRecordClientUserName;
	}

	public String getOrderRecordClientUserMobile() {
		return orderRecordClientUserMobile;
	}

	public void setOrderRecordClientUserMobile(String orderRecordClientUserMobile) {
		this.orderRecordClientUserMobile = orderRecordClientUserMobile;
	}

	public String getOrderRecordClientUserEmail() {
		return orderRecordClientUserEmail;
	}

	public void setOrderRecordClientUserEmail(String orderRecordClientUserEmail) {
		this.orderRecordClientUserEmail = orderRecordClientUserEmail;
	}

	public String getOrderRecordClientUserSex() {
		return orderRecordClientUserSex;
	}

	public void setOrderRecordClientUserSex(String orderRecordClientUserSex) {
		this.orderRecordClientUserSex = orderRecordClientUserSex;
	}

	public String getOrderRecordClientUserType() {
		return orderRecordClientUserType;
	}

	public void setOrderRecordClientUserType(String orderRecordClientUserType) {
		this.orderRecordClientUserType = orderRecordClientUserType;
	}

	public String getOrderRecordClientUserTypeName() {
		return orderRecordClientUserTypeName;
	}

	public void setOrderRecordClientUserTypeName(
			String orderRecordClientUserTypeName) {
		this.orderRecordClientUserTypeName = orderRecordClientUserTypeName;
	}

	public String getOrderRecordWxOpenId() {
		return orderRecordWxOpenId;
	}

	public void setOrderRecordWxOpenId(String orderRecordWxOpenId) {
		this.orderRecordWxOpenId = orderRecordWxOpenId;
	}

	public String getOrderRecordStaffId() {
		return orderRecordStaffId;
	}

	public void setOrderRecordStaffId(String orderRecordStaffId) {
		this.orderRecordStaffId = orderRecordStaffId;
	}

	public String getOrderRecordUserRealMobile() {
		return orderRecordUserRealMobile;
	}

	public void setOrderRecordUserRealMobile(String orderRecordUserRealMobile) {
		this.orderRecordUserRealMobile = orderRecordUserRealMobile;
	}

	public String getOrderRecordUserRealPlane() {
		return orderRecordUserRealPlane;
	}

	public void setOrderRecordUserRealPlane(String orderRecordUserRealPlane) {
		this.orderRecordUserRealPlane = orderRecordUserRealPlane;
	}

	public String getOrderRecordUserRealName() {
		return orderRecordUserRealName;
	}

	public void setOrderRecordUserRealName(String orderRecordUserRealName) {
		this.orderRecordUserRealName = orderRecordUserRealName;
	}

	public String getOrderRecordCountryId() {
		return orderRecordCountryId;
	}

	public void setOrderRecordCountryId(String orderRecordCountryId) {
		this.orderRecordCountryId = orderRecordCountryId;
	}

	public String getOrderRecordCountryName() {
		return orderRecordCountryName;
	}

	public void setOrderRecordCountryName(String orderRecordCountryName) {
		this.orderRecordCountryName = orderRecordCountryName;
	}

	public String getOrderRecordProvinceId() {
		return orderRecordProvinceId;
	}

	public void setOrderRecordProvinceId(String orderRecordProvinceId) {
		this.orderRecordProvinceId = orderRecordProvinceId;
	}

	public String getOrderRecordProvinceName() {
		return orderRecordProvinceName;
	}

	public void setOrderRecordProvinceName(String orderRecordProvinceName) {
		this.orderRecordProvinceName = orderRecordProvinceName;
	}

	public String getOrderRecordCityId() {
		return orderRecordCityId;
	}

	public void setOrderRecordCityId(String orderRecordCityId) {
		this.orderRecordCityId = orderRecordCityId;
	}

	public String getOrderRecordCityName() {
		return orderRecordCityName;
	}

	public void setOrderRecordCityName(String orderRecordCityName) {
		this.orderRecordCityName = orderRecordCityName;
	}

	public String getOrderRecordAreaId() {
		return orderRecordAreaId;
	}

	public void setOrderRecordAreaId(String orderRecordAreaId) {
		this.orderRecordAreaId = orderRecordAreaId;
	}

	public String getOrderRecordAreaName() {
		return orderRecordAreaName;
	}

	public void setOrderRecordAreaName(String orderRecordAreaName) {
		this.orderRecordAreaName = orderRecordAreaName;
	}

	public String getOrderRecordUserRealAddress() {
		return orderRecordUserRealAddress;
	}

	public void setOrderRecordUserRealAddress(String orderRecordUserRealAddress) {
		this.orderRecordUserRealAddress = orderRecordUserRealAddress;
	}

	public String getOrderRecordUserRealPostcode() {
		return orderRecordUserRealPostcode;
	}

	public void setOrderRecordUserRealPostcode(String orderRecordUserRealPostcode) {
		this.orderRecordUserRealPostcode = orderRecordUserRealPostcode;
	}

	public String getOrderRecordUserRealTime() {
		return orderRecordUserRealTime;
	}

	public void setOrderRecordUserRealTime(String orderRecordUserRealTime) {
		this.orderRecordUserRealTime = orderRecordUserRealTime;
	}

	public String getOrderRecordPaymentType() {
		return orderRecordPaymentType;
	}

	public void setOrderRecordPaymentType(String orderRecordPaymentType) {
		this.orderRecordPaymentType = orderRecordPaymentType;
	}

	public String getOrderRecordPaymentTypeName() {
		return orderRecordPaymentTypeName;
	}

	public void setOrderRecordPaymentTypeName(String orderRecordPaymentTypeName) {
		this.orderRecordPaymentTypeName = orderRecordPaymentTypeName;
	}

	public String getOrderRecordPaymentStatus() {
		return orderRecordPaymentStatus;
	}

	public void setOrderRecordPaymentStatus(String orderRecordPaymentStatus) {
		this.orderRecordPaymentStatus = orderRecordPaymentStatus;
	}

	public String getOrderRecordPaymentStatusName() {
		return orderRecordPaymentStatusName;
	}

	public void setOrderRecordPaymentStatusName(String orderRecordPaymentStatusName) {
		this.orderRecordPaymentStatusName = orderRecordPaymentStatusName;
	}

	public String getOrderRecordPaymentProvider() {
		return orderRecordPaymentProvider;
	}

	public void setOrderRecordPaymentProvider(String orderRecordPaymentProvider) {
		this.orderRecordPaymentProvider = orderRecordPaymentProvider;
	}

	public String getOrderRecordPaymentProviderName() {
		return orderRecordPaymentProviderName;
	}

	public void setOrderRecordPaymentProviderName(
			String orderRecordPaymentProviderName) {
		this.orderRecordPaymentProviderName = orderRecordPaymentProviderName;
	}

	public String getOrderRecordPaymentTime() {
		return orderRecordPaymentTime;
	}

	public void setOrderRecordPaymentTime(String orderRecordPaymentTime) {
		this.orderRecordPaymentTime = orderRecordPaymentTime;
	}

	public String getOrderRecordOperateTime() {
		return orderRecordOperateTime;
	}

	public void setOrderRecordOperateTime(String orderRecordOperateTime) {
		this.orderRecordOperateTime = orderRecordOperateTime;
	}

	public String getOrderRecordOperateStatus() {
		return orderRecordOperateStatus;
	}

	public void setOrderRecordOperateStatus(String orderRecordOperateStatus) {
		this.orderRecordOperateStatus = orderRecordOperateStatus;
	}

	public String getOrderRecordOperateStatusName() {
		return orderRecordOperateStatusName;
	}

	public void setOrderRecordOperateStatusName(String orderRecordOperateStatusName) {
		this.orderRecordOperateStatusName = orderRecordOperateStatusName;
	}

	public String getOrderRecordCreateTime() {
		return orderRecordCreateTime;
	}

	public void setOrderRecordCreateTime(String orderRecordCreateTime) {
		this.orderRecordCreateTime = orderRecordCreateTime;
	}

	public String getOrderRecordOriginalPrice() {
		return orderRecordOriginalPrice;
	}

	public void setOrderRecordOriginalPrice(String orderRecordOriginalPrice) {
		this.orderRecordOriginalPrice = orderRecordOriginalPrice;
	}

	public String getOrderRecordTotalPrice() {
		return orderRecordTotalPrice;
	}

	public void setOrderRecordTotalPrice(String orderRecordTotalPrice) {
		this.orderRecordTotalPrice = orderRecordTotalPrice;
	}

	public String getOrderRecordDliveryStaffId() {
		return orderRecordDliveryStaffId;
	}

	public void setOrderRecordDliveryStaffId(String orderRecordDliveryStaffId) {
		this.orderRecordDliveryStaffId = orderRecordDliveryStaffId;
	}

	public String getOrderRecordDeliveryStaffName() {
		return orderRecordDeliveryStaffName;
	}

	public void setOrderRecordDeliveryStaffName(String orderRecordDeliveryStaffName) {
		this.orderRecordDeliveryStaffName = orderRecordDeliveryStaffName;
	}

	public String getOrderRecordDeliveryStaffMobile() {
		return orderRecordDeliveryStaffMobile;
	}

	public void setOrderRecordDeliveryStaffMobile(
			String orderRecordDeliveryStaffMobile) {
		this.orderRecordDeliveryStaffMobile = orderRecordDeliveryStaffMobile;
	}

	public String getOrderRecordDeliveryStartTime() {
		return orderRecordDeliveryStartTime;
	}

	public void setOrderRecordDeliveryStartTime(String orderRecordDeliveryStartTime) {
		this.orderRecordDeliveryStartTime = orderRecordDeliveryStartTime;
	}

	public String getOrderRecordDeliveryEndTime() {
		return orderRecordDeliveryEndTime;
	}

	public void setOrderRecordDeliveryEndTime(String orderRecordDeliveryEndTime) {
		this.orderRecordDeliveryEndTime = orderRecordDeliveryEndTime;
	}

	public String getOrderRecordDeliveryStatus() {
		return orderRecordDeliveryStatus;
	}

	public void setOrderRecordDeliveryStatus(String orderRecordDeliveryStatus) {
		this.orderRecordDeliveryStatus = orderRecordDeliveryStatus;
	}

	public String getOrderRecordDeliveryStatusName() {
		return orderRecordDeliveryStatusName;
	}

	public void setOrderRecordDeliveryStatusName(
			String orderRecordDeliveryStatusName) {
		this.orderRecordDeliveryStatusName = orderRecordDeliveryStatusName;
	}

	public String getOrderRecordSourceId() {
		return orderRecordSourceId;
	}

	public void setOrderRecordSourceId(String orderRecordSourceId) {
		this.orderRecordSourceId = orderRecordSourceId;
	}

	public String getOrderRecordSourceName() {
		return orderRecordSourceName;
	}

	public void setOrderRecordSourceName(String orderRecordSourceName) {
		this.orderRecordSourceName = orderRecordSourceName;
	}

	public String getOrderRecordSourceType() {
		return orderRecordSourceType;
	}

	public void setOrderRecordSourceType(String orderRecordSourceType) {
		this.orderRecordSourceType = orderRecordSourceType;
	}

	public String getOrderRecordSourceTypeName() {
		return orderRecordSourceTypeName;
	}

	public void setOrderRecordSourceTypeName(String orderRecordSourceTypeName) {
		this.orderRecordSourceTypeName = orderRecordSourceTypeName;
	}

	public String getOrderRecordSerNo() {
		return orderRecordSerNo;
	}

	public void setOrderRecordSerNo(String orderRecordSerNo) {
		this.orderRecordSerNo = orderRecordSerNo;
	}

	public String getOrderRecordSellerId() {
		return orderRecordSellerId;
	}

	public void setOrderRecordSellerId(String orderRecordSellerId) {
		this.orderRecordSellerId = orderRecordSellerId;
	}

	public String getOrderRecordSellerName() {
		return orderRecordSellerName;
	}

	public void setOrderRecordSellerName(String orderRecordSellerName) {
		this.orderRecordSellerName = orderRecordSellerName;
	}

	public String getOrderRecordCusId() {
		return orderRecordCusId;
	}

	public void setOrderRecordCusId(String orderRecordCusId) {
		this.orderRecordCusId = orderRecordCusId;
	}

	public String getOrderRecordCusName() {
		return orderRecordCusName;
	}

	public void setOrderRecordCusName(String orderRecordCusName) {
		this.orderRecordCusName = orderRecordCusName;
	}

	public String getOrderRecordPrimaryConfigurationId() {
		return orderRecordPrimaryConfigurationId;
	}

	public void setOrderRecordPrimaryConfigurationId(
			String orderRecordPrimaryConfigurationId) {
		this.orderRecordPrimaryConfigurationId = orderRecordPrimaryConfigurationId;
	}

	public String getOrderRecordPrimaryConfigurationName() {
		return orderRecordPrimaryConfigurationName;
	}

	public void setOrderRecordPrimaryConfigurationName(
			String orderRecordPrimaryConfigurationName) {
		this.orderRecordPrimaryConfigurationName = orderRecordPrimaryConfigurationName;
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

	public void setOrderGoodsRelationOrderRecordId(String orderGoodsRelationOrderRecordId) {
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

	public void setOrderGoodsRelationGoodsCount(String orderGoodsRelationGoodsCount) {
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

	public String getShoppingCartId() {
		return shoppingCartId;
	}

	public void setShoppingCartId(String shoppingCartId) {
		this.shoppingCartId = shoppingCartId;
	}

	public String getShoppingCartClientUserId() {
		return shoppingCartClientUserId;
	}

	public void setShoppingCartClientUserId(String shoppingCartClientUserId) {
		this.shoppingCartClientUserId = shoppingCartClientUserId;
	}

	public String getShoppingCartGoodsId() {
		return shoppingCartGoodsId;
	}

	public void setShoppingCartGoodsId(String shoppingCartGoodsId) {
		this.shoppingCartGoodsId = shoppingCartGoodsId;
	}

	public String getShoppingCartGoodsCount() {
		return shoppingCartGoodsCount;
	}

	public void setShoppingCartGoodsCount(String shoppingCartGoodsCount) {
		this.shoppingCartGoodsCount = shoppingCartGoodsCount;
	}

	public String getShoppingCartCurrentPrice() {
		return shoppingCartCurrentPrice;
	}

	public void setShoppingCartCurrentPrice(String shoppingCartCurrentPrice) {
		this.shoppingCartCurrentPrice = shoppingCartCurrentPrice;
	}

	public String getShoppingCartCurrentPriceType() {
		return shoppingCartCurrentPriceType;
	}

	public void setShoppingCartCurrentPriceType(String shoppingCartCurrentPriceType) {
		this.shoppingCartCurrentPriceType = shoppingCartCurrentPriceType;
	}

	public String getShoppingCartStaffId() {
		return shoppingCartStaffId;
	}

	public void setShoppingCartStaffId(String shoppingCartStaffId) {
		this.shoppingCartStaffId = shoppingCartStaffId;
	}

	public String getShoppingCartPrimaryConfigurationId() {
		return shoppingCartPrimaryConfigurationId;
	}

	public void setShoppingCartPrimaryConfigurationId(
			String shoppingCartPrimaryConfigurationId) {
		this.shoppingCartPrimaryConfigurationId = shoppingCartPrimaryConfigurationId;
	}

	public String getShoppingCartTime() {
		return shoppingCartTime;
	}

	public void setShoppingCartTime(String shoppingCartTime) {
		this.shoppingCartTime = shoppingCartTime;
	}

	public Dish getDish() {
		return dish;
	}

	public void setDish(Dish dish) {
		this.dish = dish;
	}

	public Goods getGoods() {
		return goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}

	public GoodsDishRelation getGoodsDishRelation() {
		return goodsDishRelation;
	}

	public void setGoodsDishRelation(GoodsDishRelation goodsDishRelation) {
		this.goodsDishRelation = goodsDishRelation;
	}

	public OrderRecord getOrderRecord() {
		return orderRecord;
	}

	public void setOrderRecord(OrderRecord orderRecord) {
		this.orderRecord = orderRecord;
	}

	public OrderGoodsRelation getOrderGoodsRelation() {
		return orderGoodsRelation;
	}

	public void setOrderGoodsRelation(OrderGoodsRelation orderGoodsRelation) {
		this.orderGoodsRelation = orderGoodsRelation;
	}

	public ShoppingCart getShoppingCart() {
		return shoppingCart;
	}

	public void setShoppingCart(ShoppingCart shoppingCart) {
		this.shoppingCart = shoppingCart;
	}

	public String getShoppingCartTotalPrice() {
		return shoppingCartTotalPrice;
	}

	public void setShoppingCartTotalPrice(String shoppingCartTotalPrice) {
		this.shoppingCartTotalPrice = shoppingCartTotalPrice;
	}

	public String getOrderRecordUserRealDescription() {
		return orderRecordUserRealDescription;
	}

	public void setOrderRecordUserRealDescription(
			String orderRecordUserRealDescription) {
		this.orderRecordUserRealDescription = orderRecordUserRealDescription;
	}

	public String getOrderRecordLogisticsCompanyId() {
		return orderRecordLogisticsCompanyId;
	}

	public void setOrderRecordLogisticsCompanyId(
			String orderRecordLogisticsCompanyId) {
		this.orderRecordLogisticsCompanyId = orderRecordLogisticsCompanyId;
	}

	public String getOrderRecordLogisticsCompanyName() {
		return orderRecordLogisticsCompanyName;
	}

	public void setOrderRecordLogisticsCompanyName(
			String orderRecordLogisticsCompanyName) {
		this.orderRecordLogisticsCompanyName = orderRecordLogisticsCompanyName;
	}

	public List<Goods> getGoodsList() {
		return goodsList;
	}

	public void setGoodsList(List<Goods> goodsList) {
		this.goodsList = goodsList;
	}

	public String getShoppingCartOriginalPrice() {
		return shoppingCartOriginalPrice;
	}

	public void setShoppingCartOriginalPrice(String shoppingCartOriginalPrice) {
		this.shoppingCartOriginalPrice = shoppingCartOriginalPrice;
	}

	public String getShoppingCartOriginalTotalPrice() {
		return shoppingCartOriginalTotalPrice;
	}

	public void setShoppingCartOriginalTotalPrice(String shoppingCartOriginalTotalPrice) {
		this.shoppingCartOriginalTotalPrice = shoppingCartOriginalTotalPrice;
	}

	public List<OrderGoodsRelation> getOrderGoodsRelationList() {
		return orderGoodsRelationList;
	}

	public void setOrderGoodsRelationList(
			List<OrderGoodsRelation> orderGoodsRelationList) {
		this.orderGoodsRelationList = orderGoodsRelationList;
	}

	public String getSearchId() {
		return searchId;
	}

	public void setSearchId(String searchId) {
		this.searchId = searchId;
	}

	public String getSearchClientUserInfoId() {
		return searchClientUserInfoId;
	}

	public void setSearchClientUserInfoId(String searchClientUserInfoId) {
		this.searchClientUserInfoId = searchClientUserInfoId;
	}

	public String getSearchValue() {
		return searchValue;
	}

	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}

	public String getSearchTime() {
		return searchTime;
	}

	public void setSearchTime(String searchTime) {
		this.searchTime = searchTime;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public Long getPurchaseId() {
		return purchaseId;
	}

	public void setPurchaseId(Long purchaseId) {
		this.purchaseId = purchaseId;
	}

	public Integer getPurchaseClientUserId() {
		return purchaseClientUserId;
	}

	public void setPurchaseClientUserId(Integer purchaseClientUserId) {
		this.purchaseClientUserId = purchaseClientUserId;
	}

	public Long getPurchaseOrderRecordId() {
		return purchaseOrderRecordId;
	}

	public void setPurchaseOrderRecordId(Long purchaseOrderRecordId) {
		this.purchaseOrderRecordId = purchaseOrderRecordId;
	}

	public String getPurchaseOrderNo() {
		return purchaseOrderNo;
	}

	public void setPurchaseOrderNo(String purchaseOrderNo) {
		this.purchaseOrderNo = purchaseOrderNo;
	}

	public String getPurchaseGoodsId() {
		return purchaseGoodsId;
	}

	public void setPurchaseGoodsId(String purchaseGoodsId) {
		this.purchaseGoodsId = purchaseGoodsId;
	}

	public String getPurchaseGoodsName() {
		return purchaseGoodsName;
	}

	public void setPurchaseGoodsName(String purchaseGoodsName) {
		this.purchaseGoodsName = purchaseGoodsName;
	}

	public String getPurchaseDishId() {
		return purchaseDishId;
	}

	public void setPurchaseDishId(String purchaseDishId) {
		this.purchaseDishId = purchaseDishId;
	}

	public String getPurchaseDishName() {
		return purchaseDishName;
	}

	public void setPurchaseDishName(String purchaseDishName) {
		this.purchaseDishName = purchaseDishName;
	}

	public String getPurchaseRawMaterialId() {
		return purchaseRawMaterialId;
	}

	public void setPurchaseRawMaterialId(String purchaseRawMaterialId) {
		this.purchaseRawMaterialId = purchaseRawMaterialId;
	}

	public String getPurchaseRawMaterialName() {
		return purchaseRawMaterialName;
	}

	public void setPurchaseRawMaterialName(String purchaseRawMaterialName) {
		this.purchaseRawMaterialName = purchaseRawMaterialName;
	}

	public String getPurchaseWeightId() {
		return purchaseWeightId;
	}

	public void setPurchaseWeightId(String purchaseWeightId) {
		this.purchaseWeightId = purchaseWeightId;
	}

	public String getPurchaseWeightName() {
		return purchaseWeightName;
	}

	public void setPurchaseWeightName(String purchaseWeightName) {
		this.purchaseWeightName = purchaseWeightName;
	}

	public String getPurchaseNumber() {
		return purchaseNumber;
	}

	public void setPurchaseNumber(String purchaseNumber) {
		this.purchaseNumber = purchaseNumber;
	}

	public Timestamp getPurchaseCreateTime() {
		return purchaseCreateTime;
	}

	public void setPurchaseCreateTime(Timestamp purchaseCreateTime) {
		this.purchaseCreateTime = purchaseCreateTime;
	}

	public String getPurchaseGoodsCount() {
		return purchaseGoodsCount;
	}

	public void setPurchaseGoodsCount(String purchaseGoodsCount) {
		this.purchaseGoodsCount = purchaseGoodsCount;
	}

	public String getPurchaseDishCount() {
		return purchaseDishCount;
	}

	public void setPurchaseDishCount(String purchaseDishCount) {
		this.purchaseDishCount = purchaseDishCount;
	}

	public String getPurchaseGoodsCategoryId() {
		return purchaseGoodsCategoryId;
	}

	public void setPurchaseGoodsCategoryId(String purchaseGoodsCategoryId) {
		this.purchaseGoodsCategoryId = purchaseGoodsCategoryId;
	}

	public String getPurchaseGoodsCategoryName() {
		return purchaseGoodsCategoryName;
	}

	public void setPurchaseGoodsCategoryName(String purchaseGoodsCategoryName) {
		this.purchaseGoodsCategoryName = purchaseGoodsCategoryName;
	}

	public String getPurchaseDishTypeId() {
		return purchaseDishTypeId;
	}

	public void setPurchaseDishTypeId(String purchaseDishTypeId) {
		this.purchaseDishTypeId = purchaseDishTypeId;
	}

	public String getPurchaseDishTypeName() {
		return purchaseDishTypeName;
	}

	public void setPurchaseDishTypeName(String purchaseDishTypeName) {
		this.purchaseDishTypeName = purchaseDishTypeName;
	}

	public String getPurchaseRawMaterialTypeId() {
		return purchaseRawMaterialTypeId;
	}

	public void setPurchaseRawMaterialTypeId(String purchaseRawMaterialTypeId) {
		this.purchaseRawMaterialTypeId = purchaseRawMaterialTypeId;
	}

	public String getPurchaseRawMaterialTypeName() {
		return purchaseRawMaterialTypeName;
	}

	public void setPurchaseRawMaterialTypeName(String purchaseRawMaterialTypeName) {
		this.purchaseRawMaterialTypeName = purchaseRawMaterialTypeName;
	}
	
	
}
