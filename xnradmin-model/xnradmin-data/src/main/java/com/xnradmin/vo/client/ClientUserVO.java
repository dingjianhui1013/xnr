package com.xnradmin.vo.client;


import java.io.Serializable;

public class ClientUserVO implements Serializable{
    private static final long serialVersionUID = 1L;

    //clientUserInfo
    private String clientUserInfoId;
	private String nickName;
	private String email;
	private String mobile;
	private String loginPassWord;
	private String paymentPassword;
	private String status;
	private String statusName;
	private String type;
	private String typeName;
	private String uuid;
	private String clientUserInfoCreateTime;
	private String lastLoginTime;
	private String clientUserInfoModifyTime;
	private String sourceId; // 该订单用户推广来源ID（有可能是用户推广，代理商推广，线上推广）
	private String sourceName; // 该订单用户推广来源名称
	private String sourceType; // 该订单用户推广来源类型 （有可能是用户推广，代理商推广，线上推广）
	private String sourceTypeName; // 该订单用户推广来源类型名称
	private String discount; // 当前用户充值后使用产品给合作方结算的折扣
	private String regionDescriptionId;// 地区描述ID
	private String wxfromusername;
	private String wxtousername;
	private String wxsubtime;
	private String wxunsubtime;
	private String wxlastActvieTime;
	private String wxmsgtype;
	private String wxevent;
	private String wxnetworktype;
	private String wxopenuid;	
	private String wxnickname;
	private String wxsex;
	private String wxlanguage;
	private String wxstatusid;
	private String wxstatusName;
	private String wxcity;
	private String wxprovince;
	private String wxcountry;
	private String wxheadimgurl;
	private String wxunionid;
	private String isappuser;
	private String iswxuser;
	private String ismobileuser;
	private String isemailuser;
	private String iswebsiteuser;
	
	//ClientUserRegionInfo
	private String clientUserRegionInfoId;
	private String countryId;
	private String countryName;
	private String provinceId;
	private String provinceName;
	private String cityId;
	private String cityName;
	private String areaId;
	private String areaName;
	private String userRealAddress;
	private String clientUserRegionInfoClientUserInfoId;
	private String userRealMobile; //收货人用户手机号
    private String userRealPlane; //收货人用户座机
    private String userRealName; //收货人名称
    private String userRealPostcode; //邮政编码
	private String clientUserRegionInfoCreateTime;
	private String clientUserRegionInfoModifyTime;
	public String getClientUserInfoId() {
		return clientUserInfoId;
	}
	public void setClientUserInfoId(String clientUserInfoId) {
		this.clientUserInfoId = clientUserInfoId;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getLoginPassWord() {
		return loginPassWord;
	}
	public void setLoginPassWord(String loginPassWord) {
		this.loginPassWord = loginPassWord;
	}
	public String getPaymentPassword() {
		return paymentPassword;
	}
	public void setPaymentPassword(String paymentPassword) {
		this.paymentPassword = paymentPassword;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getStatusName() {
		return statusName;
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getClientUserInfoCreateTime() {
		return clientUserInfoCreateTime;
	}
	public void setClientUserInfoCreateTime(String clientUserInfoCreateTime) {
		this.clientUserInfoCreateTime = clientUserInfoCreateTime;
	}
	public String getLastLoginTime() {
		return lastLoginTime;
	}
	public void setLastLoginTime(String lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	public String getClientUserInfoModifyTime() {
		return clientUserInfoModifyTime;
	}
	public void setClientUserInfoModifyTime(String clientUserInfoModifyTime) {
		this.clientUserInfoModifyTime = clientUserInfoModifyTime;
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
	public String getDiscount() {
		return discount;
	}
	public void setDiscount(String discount) {
		this.discount = discount;
	}
	public String getRegionDescriptionId() {
		return regionDescriptionId;
	}
	public void setRegionDescriptionId(String regionDescriptionId) {
		this.regionDescriptionId = regionDescriptionId;
	}
	public String getWxfromusername() {
		return wxfromusername;
	}
	public void setWxfromusername(String wxfromusername) {
		this.wxfromusername = wxfromusername;
	}
	public String getWxtousername() {
		return wxtousername;
	}
	public void setWxtousername(String wxtousername) {
		this.wxtousername = wxtousername;
	}
	public String getWxsubtime() {
		return wxsubtime;
	}
	public void setWxsubtime(String wxsubtime) {
		this.wxsubtime = wxsubtime;
	}
	public String getWxunsubtime() {
		return wxunsubtime;
	}
	public void setWxunsubtime(String wxunsubtime) {
		this.wxunsubtime = wxunsubtime;
	}
	public String getWxlastActvieTime() {
		return wxlastActvieTime;
	}
	public void setWxlastActvieTime(String wxlastActvieTime) {
		this.wxlastActvieTime = wxlastActvieTime;
	}
	public String getWxmsgtype() {
		return wxmsgtype;
	}
	public void setWxmsgtype(String wxmsgtype) {
		this.wxmsgtype = wxmsgtype;
	}
	public String getWxevent() {
		return wxevent;
	}
	public void setWxevent(String wxevent) {
		this.wxevent = wxevent;
	}
	public String getWxnetworktype() {
		return wxnetworktype;
	}
	public void setWxnetworktype(String wxnetworktype) {
		this.wxnetworktype = wxnetworktype;
	}
	public String getWxopenuid() {
		return wxopenuid;
	}
	public void setWxopenuid(String wxopenuid) {
		this.wxopenuid = wxopenuid;
	}
	public String getWxnickname() {
		return wxnickname;
	}
	public void setWxnickname(String wxnickname) {
		this.wxnickname = wxnickname;
	}
	public String getWxsex() {
		return wxsex;
	}
	public void setWxsex(String wxsex) {
		this.wxsex = wxsex;
	}
	public String getWxlanguage() {
		return wxlanguage;
	}
	public void setWxlanguage(String wxlanguage) {
		this.wxlanguage = wxlanguage;
	}
	public String getWxstatusid() {
		return wxstatusid;
	}
	public void setWxstatusid(String wxstatusid) {
		this.wxstatusid = wxstatusid;
	}
	public String getWxstatusName() {
		return wxstatusName;
	}
	public void setWxstatusName(String wxstatusName) {
		this.wxstatusName = wxstatusName;
	}
	public String getWxcity() {
		return wxcity;
	}
	public void setWxcity(String wxcity) {
		this.wxcity = wxcity;
	}
	public String getWxprovince() {
		return wxprovince;
	}
	public void setWxprovince(String wxprovince) {
		this.wxprovince = wxprovince;
	}
	public String getWxcountry() {
		return wxcountry;
	}
	public void setWxcountry(String wxcountry) {
		this.wxcountry = wxcountry;
	}
	public String getWxheadimgurl() {
		return wxheadimgurl;
	}
	public void setWxheadimgurl(String wxheadimgurl) {
		this.wxheadimgurl = wxheadimgurl;
	}
	public String getWxunionid() {
		return wxunionid;
	}
	public void setWxunionid(String wxunionid) {
		this.wxunionid = wxunionid;
	}
	public String getIsappuser() {
		return isappuser;
	}
	public void setIsappuser(String isappuser) {
		this.isappuser = isappuser;
	}
	public String getIswxuser() {
		return iswxuser;
	}
	public void setIswxuser(String iswxuser) {
		this.iswxuser = iswxuser;
	}
	public String getIsmobileuser() {
		return ismobileuser;
	}
	public void setIsmobileuser(String ismobileuser) {
		this.ismobileuser = ismobileuser;
	}
	public String getIsemailuser() {
		return isemailuser;
	}
	public void setIsemailuser(String isemailuser) {
		this.isemailuser = isemailuser;
	}
	public String getIswebsiteuser() {
		return iswebsiteuser;
	}
	public void setIswebsiteuser(String iswebsiteuser) {
		this.iswebsiteuser = iswebsiteuser;
	}
	public String getClientUserRegionInfoId() {
		return clientUserRegionInfoId;
	}
	public void setClientUserRegionInfoId(String clientUserRegionInfoId) {
		this.clientUserRegionInfoId = clientUserRegionInfoId;
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
	public String getClientUserRegionInfoClientUserInfoId() {
		return clientUserRegionInfoClientUserInfoId;
	}
	public void setClientUserRegionInfoClientUserInfoId(
			String clientUserRegionInfoClientUserInfoId) {
		this.clientUserRegionInfoClientUserInfoId = clientUserRegionInfoClientUserInfoId;
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
	public String getUserRealPostcode() {
		return userRealPostcode;
	}
	public void setUserRealPostcode(String userRealPostcode) {
		this.userRealPostcode = userRealPostcode;
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
	
	
}
