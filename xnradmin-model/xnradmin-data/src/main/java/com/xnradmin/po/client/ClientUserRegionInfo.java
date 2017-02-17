package com.xnradmin.po.client;

import static javax.persistence.GenerationType.IDENTITY;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * CommonMenu entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "client_user_region_info")
public class ClientUserRegionInfo implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer staffId; //对应staff表的ID
	private Integer countryId;
	private String countryName;
	private Integer provinceId;
	private String provinceName;
	private Integer cityId;
	private String cityName;
	private Integer areaId;
	private String areaName;
	private String userRealAddress;
	private Integer clientUserInfoId;
	private String userRealMobile; //收货人用户手机号
    private String userRealPlane; //收货人用户座机
    private String userRealName; //收货人名称
    private String userRealPostcode; //邮政编码
	private Timestamp createTime;
	private Timestamp modifyTime;
	
	// Constructors

	/** default constructor */
	public ClientUserRegionInfo() {
	    
	}

	/** minimal constructor */
	public ClientUserRegionInfo(Integer id) {
		this.id = id;
	}

	

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name = "PROVINCE_ID")
	public Integer getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}

	@Column(name = "CITY_ID")
	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	@Column(name = "AREA_ID")
	public Integer getAreaId() {
		return areaId;
	}

	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}
	
	@Column(name = "CLIENT_USER_INFO_ID")
	public Integer getClientUserInfoId() {
		return clientUserInfoId;
	}

	public void setClientUserInfoId(Integer clientUserInfoId) {
		this.clientUserInfoId = clientUserInfoId;
	}

	@Column(name = "COUNTRY_ID")
	public Integer getCountryId() {
		return countryId;
	}

	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}

	@Column(name = "COUNTRY_NAME", length = 50)
	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	@Column(name = "PROVINCE_NAME", length = 50)
	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	@Column(name = "CITY_NAME", length = 50)
	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	@Column(name = "AREA_NAME", length = 50)
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

	@Column(name = "USER_REAL_MOBILE", length = 50)
	public String getUserRealMobile() {
		return userRealMobile;
	}

	public void setUserRealMobile(String userRealMobile) {
		this.userRealMobile = userRealMobile;
	}

	@Column(name = "USER_REAL_PLANE", length = 50)
	public String getUserRealPlane() {
		return userRealPlane;
	}

	public void setUserRealPlane(String userRealPlane) {
		this.userRealPlane = userRealPlane;
	}

	@Column(name = "USER_REAL_NAME", length = 100)
	public String getUserRealName() {
		return userRealName;
	}

	public void setUserRealName(String userRealName) {
		this.userRealName = userRealName;
	}

	@Column(name = "USER_REAL_POSTCODE", length = 50)
	public String getUserRealPostcode() {
		return userRealPostcode;
	}

	public void setUserRealPostcode(String userRealPostcode) {
		this.userRealPostcode = userRealPostcode;
	}

	@Column(name = "CREATE_TIME")
	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	@Column(name = "MODIFY_TIME")
	public Timestamp getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Timestamp modifyTime) {
		this.modifyTime = modifyTime;
	}

	@Column(name = "STAFF_ID")
	public Integer getStaffId() {
		return staffId;
	}

	public void setStaffId(Integer staffId) {
		this.staffId = staffId;
	}

	
	
}