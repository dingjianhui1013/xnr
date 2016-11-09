package com.xnradmin.vo.mall;

import java.sql.Timestamp;

public class SetingVO {

	//DeliverMode
	private String deliverModeId;

    private String deliverModeSortId;  //排序Id

    private String deliveryModeName;  //配送方式名称

    private String deliverModeLogisticsCompanyId;  //默认物流公司Id

    private String firstWeight;	//首重单位
    
    private String firstPrice;	//首重价格
    
    private String continuedHeavyWeight;  //续重单位
    
    private String continuedHeavyPrice;	//续重价格
    
    private String deliverModeStaffId;	//隶属用户Id
    
    private String deliverModePrimaryConfigurationId;	//商城ID
    
	private String deliverModeCreateTime;  //建立时间
	
	private String deliverModeCreateStaffId;  //建立人
	
	private String deliverModeModifyTime;  //修改时间

	private String deliverModeModifyStaffId;  //修改人
	
	
	//LogisticsCompany
	private String logisticsCompanyId;

    private String logisticsCompanySortId;  //排序Id

    private String companyName;  //公司名称

    private String companyUrl;  //公司网址

    private String logisticsCompanyStaffId;	//隶属用户Id
    
    private String logisticsCompanyPrimaryConfigurationId;	//商城ID
    
	private String logisticsCompanyCreateTime;  //建立时间
	
	private String logisticsCompanyCreateStaffId;  //建立人
	
	private String logisticsCompanyModifyTime;  //修改时间

	private String logisticsCompanyModifyStaffId;  //修改人
	
	
	//PayMentMode
	private String payMentModeId;

    private String payMentModeSortId;  //排序Id

    private String paymentTypeId;  //支付类型ID

    private String paymentName;  //支付类型名称

    private String payMentModeStaffId;	//隶属用户Id
    
    private String payMentModePrimaryConfigurationId;	//商城ID
    
	private String payMentModeCreateTime;  //建立时间
	
	private String payMentModeCreateStaffId;  //建立人
	
	private String payMentModeModifyTime;  //修改时间

	private String payMentModeModifyStaffId;  //修改人
	
	
	//PrimaryConfiguration
	private String PrimaryConfigurationId;

    private String mallDemoUrl;  //商城预览地址

    private String mallName;  //商城名称

    private String mallLogo;  //商城logo图

    private String mallBackground;  //商城背景

    private String mallBackgroundStatus;  //是否开启背景
    
    private String address;  //联系地址

    private String mobile;  //联系电话

    private String code;  //邮编

    private String email;  //邮箱地址
    
    private String mallIntroduction;  //商城介绍
    
    private String mallStatus; //商城状态
    
    private String primaryConfigurationStaffId; //隶属用户ID
    
	private String primaryConfigurationCreateTime;  //建立时间
	
	private String primaryConfigurationCreateStaffId;  //建立人
	
	private String primaryConfigurationModifyTime;  //修改时间

	private String primaryConfigurationModifyStaffId;  //修改人
	
	
	//slide
	private String slideId;

    private String slideSortId;  //排序Id

    private String title;  //标题

    private String picPath;  //本地图片位置

    private String picUrl;  //外链图片URL

    private String showStatus;  //是否显示
    
    private String showPosition;  //显示位置

    private String slideStaffId;	//隶属用户Id
    
    private String slidePrimaryConfigurationId;	//商城ID
    
    private String picType;	//图片类型（LOGO，背景，幻灯片）
    
	private String slideCreateTime;  //建立时间
	
	private String slideCreateStaffId;  //建立人
	
	private String slideModifyTime;  //修改时间

	private String slideModifyStaffId;  //修改人

	public String getDeliverModeId() {
		return deliverModeId;
	}

	public void setDeliverModeId(String deliverModeId) {
		this.deliverModeId = deliverModeId;
	}

	public String getDeliverModeSortId() {
		return deliverModeSortId;
	}

	public void setDeliverModeSortId(String deliverModeSortId) {
		this.deliverModeSortId = deliverModeSortId;
	}

	public String getDeliveryModeName() {
		return deliveryModeName;
	}

	public void setDeliveryModeName(String deliveryModeName) {
		this.deliveryModeName = deliveryModeName;
	}

	public String getDeliverModeLogisticsCompanyId() {
		return deliverModeLogisticsCompanyId;
	}

	public void setDeliverModeLogisticsCompanyId(
			String deliverModeLogisticsCompanyId) {
		this.deliverModeLogisticsCompanyId = deliverModeLogisticsCompanyId;
	}

	public String getFirstWeight() {
		return firstWeight;
	}

	public void setFirstWeight(String firstWeight) {
		this.firstWeight = firstWeight;
	}

	public String getFirstPrice() {
		return firstPrice;
	}

	public void setFirstPrice(String firstPrice) {
		this.firstPrice = firstPrice;
	}

	public String getContinuedHeavyWeight() {
		return continuedHeavyWeight;
	}

	public void setContinuedHeavyWeight(String continuedHeavyWeight) {
		this.continuedHeavyWeight = continuedHeavyWeight;
	}

	public String getContinuedHeavyPrice() {
		return continuedHeavyPrice;
	}

	public void setContinuedHeavyPrice(String continuedHeavyPrice) {
		this.continuedHeavyPrice = continuedHeavyPrice;
	}

	public String getDeliverModeStaffId() {
		return deliverModeStaffId;
	}

	public void setDeliverModeStaffId(String deliverModeStaffId) {
		this.deliverModeStaffId = deliverModeStaffId;
	}

	public String getDeliverModeCreateTime() {
		return deliverModeCreateTime;
	}

	public void setDeliverModeCreateTime(String deliverModeCreateTime) {
		this.deliverModeCreateTime = deliverModeCreateTime;
	}

	public String getDeliverModeCreateStaffId() {
		return deliverModeCreateStaffId;
	}

	public void setDeliverModeCreateStaffId(String deliverModeCreateStaffId) {
		this.deliverModeCreateStaffId = deliverModeCreateStaffId;
	}

	public String getDeliverModeModifyTime() {
		return deliverModeModifyTime;
	}

	public void setDeliverModeModifyTime(String deliverModeModifyTime) {
		this.deliverModeModifyTime = deliverModeModifyTime;
	}

	public String getDeliverModeModifyStaffId() {
		return deliverModeModifyStaffId;
	}

	public void setDeliverModeModifyStaffId(String deliverModeModifyStaffId) {
		this.deliverModeModifyStaffId = deliverModeModifyStaffId;
	}

	public String getLogisticsCompanyId() {
		return logisticsCompanyId;
	}

	public void setLogisticsCompanyId(String logisticsCompanyId) {
		this.logisticsCompanyId = logisticsCompanyId;
	}

	public String getLogisticsCompanySortId() {
		return logisticsCompanySortId;
	}

	public void setLogisticsCompanySortId(String logisticsCompanySortId) {
		this.logisticsCompanySortId = logisticsCompanySortId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyUrl() {
		return companyUrl;
	}

	public void setCompanyUrl(String companyUrl) {
		this.companyUrl = companyUrl;
	}

	public String getLogisticsCompanyStaffId() {
		return logisticsCompanyStaffId;
	}

	public void setLogisticsCompanyStaffId(String logisticsCompanyStaffId) {
		this.logisticsCompanyStaffId = logisticsCompanyStaffId;
	}

	public String getLogisticsCompanyCreateTime() {
		return logisticsCompanyCreateTime;
	}

	public void setLogisticsCompanyCreateTime(String logisticsCompanyCreateTime) {
		this.logisticsCompanyCreateTime = logisticsCompanyCreateTime;
	}

	public String getLogisticsCompanyCreateStaffId() {
		return logisticsCompanyCreateStaffId;
	}

	public void setLogisticsCompanyCreateStaffId(
			String logisticsCompanyCreateStaffId) {
		this.logisticsCompanyCreateStaffId = logisticsCompanyCreateStaffId;
	}

	public String getLogisticsCompanyModifyTime() {
		return logisticsCompanyModifyTime;
	}

	public void setLogisticsCompanyModifyTime(String logisticsCompanyModifyTime) {
		this.logisticsCompanyModifyTime = logisticsCompanyModifyTime;
	}

	public String getLogisticsCompanyModifyStaffId() {
		return logisticsCompanyModifyStaffId;
	}

	public void setLogisticsCompanyModifyStaffId(
			String logisticsCompanyModifyStaffId) {
		this.logisticsCompanyModifyStaffId = logisticsCompanyModifyStaffId;
	}

	public String getPayMentModeId() {
		return payMentModeId;
	}

	public void setPayMentModeId(String payMentModeId) {
		this.payMentModeId = payMentModeId;
	}

	public String getPayMentModeSortId() {
		return payMentModeSortId;
	}

	public void setPayMentModeSortId(String payMentModeSortId) {
		this.payMentModeSortId = payMentModeSortId;
	}

	public String getPaymentTypeId() {
		return paymentTypeId;
	}

	public void setPaymentTypeId(String paymentTypeId) {
		this.paymentTypeId = paymentTypeId;
	}

	public String getPaymentName() {
		return paymentName;
	}

	public void setPaymentName(String paymentName) {
		this.paymentName = paymentName;
	}

	public String getPayMentModeStaffId() {
		return payMentModeStaffId;
	}

	public void setPayMentModeStaffId(String payMentModeStaffId) {
		this.payMentModeStaffId = payMentModeStaffId;
	}

	public String getPayMentModeCreateTime() {
		return payMentModeCreateTime;
	}

	public void setPayMentModeCreateTime(String payMentModeCreateTime) {
		this.payMentModeCreateTime = payMentModeCreateTime;
	}

	public String getPayMentModeCreateStaffId() {
		return payMentModeCreateStaffId;
	}

	public void setPayMentModeCreateStaffId(String payMentModeCreateStaffId) {
		this.payMentModeCreateStaffId = payMentModeCreateStaffId;
	}

	public String getPayMentModeModifyTime() {
		return payMentModeModifyTime;
	}

	public void setPayMentModeModifyTime(String payMentModeModifyTime) {
		this.payMentModeModifyTime = payMentModeModifyTime;
	}

	public String getPayMentModeModifyStaffId() {
		return payMentModeModifyStaffId;
	}

	public void setPayMentModeModifyStaffId(String payMentModeModifyStaffId) {
		this.payMentModeModifyStaffId = payMentModeModifyStaffId;
	}

	public String getPrimaryConfigurationId() {
		return PrimaryConfigurationId;
	}

	public void setPrimaryConfigurationId(String PrimaryConfigurationId) {
		this.PrimaryConfigurationId = PrimaryConfigurationId;
	}

	public String getMallDemoUrl() {
		return mallDemoUrl;
	}

	public void setMallDemoUrl(String mallDemoUrl) {
		this.mallDemoUrl = mallDemoUrl;
	}

	public String getMallName() {
		return mallName;
	}

	public void setMallName(String mallName) {
		this.mallName = mallName;
	}

	public String getMallLogo() {
		return mallLogo;
	}

	public void setMallLogo(String mallLogo) {
		this.mallLogo = mallLogo;
	}

	public String getMallBackground() {
		return mallBackground;
	}

	public void setMallBackground(String mallBackground) {
		this.mallBackground = mallBackground;
	}

	public String getMallBackgroundStatus() {
		return mallBackgroundStatus;
	}

	public void setMallBackgroundStatus(String mallBackgroundStatus) {
		this.mallBackgroundStatus = mallBackgroundStatus;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMallIntroduction() {
		return mallIntroduction;
	}

	public void setMallIntroduction(String mallIntroduction) {
		this.mallIntroduction = mallIntroduction;
	}
	
	public String getMallStatus() {
		return mallStatus;
	}

	public void setMallStatus(String mallStatus) {
		this.mallStatus = mallStatus;
	}

	public String getPrimaryConfigurationStaffId() {
		return primaryConfigurationStaffId;
	}

	public void setPrimaryConfigurationStaffId(String primaryConfigurationStaffId) {
		this.primaryConfigurationStaffId = primaryConfigurationStaffId;
	}

	public String getPrimaryConfigurationCreateTime() {
		return primaryConfigurationCreateTime;
	}

	public void setPrimaryConfigurationCreateTime(String primaryConfigurationCreateTime) {
		this.primaryConfigurationCreateTime = primaryConfigurationCreateTime;
	}

	public String getPrimaryConfigurationCreateStaffId() {
		return primaryConfigurationCreateStaffId;
	}

	public void setPrimaryConfigurationCreateStaffId(String primaryConfigurationCreateStaffId) {
		this.primaryConfigurationCreateStaffId = primaryConfigurationCreateStaffId;
	}

	public String getPrimaryConfigurationModifyTime() {
		return primaryConfigurationModifyTime;
	}

	public void setPrimaryConfigurationModifyTime(String primaryConfigurationModifyTime) {
		this.primaryConfigurationModifyTime = primaryConfigurationModifyTime;
	}

	public String getPrimaryConfigurationModifyStaffId() {
		return primaryConfigurationModifyStaffId;
	}

	public void setPrimaryConfigurationModifyStaffId(String primaryConfigurationModifyStaffId) {
		this.primaryConfigurationModifyStaffId = primaryConfigurationModifyStaffId;
	}

	public String getSlideId() {
		return slideId;
	}

	public void setSlideId(String slideId) {
		this.slideId = slideId;
	}

	public String getSlideSortId() {
		return slideSortId;
	}

	public void setSlideSortId(String slideSortId) {
		this.slideSortId = slideSortId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPicPath() {
		return picPath;
	}

	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public String getShowStatus() {
		return showStatus;
	}

	public void setShowStatus(String showStatus) {
		this.showStatus = showStatus;
	}

	public String getShowPosition() {
		return showPosition;
	}

	public void setShowPosition(String showPosition) {
		this.showPosition = showPosition;
	}

	public String getSlideStaffId() {
		return slideStaffId;
	}

	public void setSlideStaffId(String slideStaffId) {
		this.slideStaffId = slideStaffId;
	}

	public String getSlideCreateTime() {
		return slideCreateTime;
	}

	public void setSlideCreateTime(String slideCreateTime) {
		this.slideCreateTime = slideCreateTime;
	}

	public String getSlideCreateStaffId() {
		return slideCreateStaffId;
	}

	public void setSlideCreateStaffId(String slideCreateStaffId) {
		this.slideCreateStaffId = slideCreateStaffId;
	}

	public String getSlideModifyTime() {
		return slideModifyTime;
	}

	public void setSlideModifyTime(String slideModifyTime) {
		this.slideModifyTime = slideModifyTime;
	}

	public String getSlideModifyStaffId() {
		return slideModifyStaffId;
	}

	public void setSlideModifyStaffId(String slideModifyStaffId) {
		this.slideModifyStaffId = slideModifyStaffId;
	}

	public String getDeliverModePrimaryConfigurationId() {
		return deliverModePrimaryConfigurationId;
	}

	public void setDeliverModePrimaryConfigurationId(String deliverModePrimaryConfigurationId) {
		this.deliverModePrimaryConfigurationId = deliverModePrimaryConfigurationId;
	}

	public String getLogisticsCompanyPrimaryConfigurationId() {
		return logisticsCompanyPrimaryConfigurationId;
	}

	public void setLogisticsCompanyPrimaryConfigurationId(String logisticsCompanyPrimaryConfigurationId) {
		this.logisticsCompanyPrimaryConfigurationId = logisticsCompanyPrimaryConfigurationId;
	}

	public String getPayMentModePrimaryConfigurationId() {
		return payMentModePrimaryConfigurationId;
	}

	public void setPayMentModePrimaryConfigurationId(String payMentModePrimaryConfigurationId) {
		this.payMentModePrimaryConfigurationId = payMentModePrimaryConfigurationId;
	}

	public String getSlidePrimaryConfigurationId() {
		return slidePrimaryConfigurationId;
	}

	public void setSlidePrimaryConfigurationId(String slidePrimaryConfigurationId) {
		this.slidePrimaryConfigurationId = slidePrimaryConfigurationId;
	}

	public String getPicType() {
		return picType;
	}

	public void setPicType(String picType) {
		this.picType = picType;
	}

}
