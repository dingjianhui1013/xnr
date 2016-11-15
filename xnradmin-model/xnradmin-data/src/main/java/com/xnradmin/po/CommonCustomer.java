package com.xnradmin.po;

import static javax.persistence.GenerationType.IDENTITY;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * CommonCustomer entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "common_customer")
public class CommonCustomer implements java.io.Serializable {

	// Fields

	private Integer id;
	private String customerName;
	private String customerAddress;
	private String province;
	private String city;
	private String extent;
	private String level;
	private String country;
	private Integer createStaffId;
	private String createStaffName;
	private Integer createStaffOrgId;
	private String createStaffOrgName;
	private Timestamp createTime;
	private String telephone;
	private String fax;
	private String postCode;

	// Constructors

	/** default constructor */
	public CommonCustomer() {
	}

	/** full constructor */
	public CommonCustomer(String customerName, String customerAddress,
			String province, String city, String extent, String level,
			String country) {
		this.customerName = customerName;
		this.customerAddress = customerAddress;
		this.province = province;
		this.city = city;
		this.extent = extent;
		this.level = level;
		this.country = country;
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

	@Column(name = "CUSTOMER_NAME", length = 128)
	public String getCustomerName() {
		return this.customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	@Column(name = "CUSTOMER_ADDRESS", length = 512)
	public String getCustomerAddress() {
		return this.customerAddress;
	}

	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}

	@Column(name = "PROVINCE", length = 128)
	public String getProvince() {
		return this.province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	@Column(name = "CITY", length = 128)
	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Column(name = "EXTENT", length = 128)
	public String getExtent() {
		return this.extent;
	}

	public void setExtent(String extent) {
		this.extent = extent;
	}

	@Column(name = "LEVEL", length = 32)
	public String getLevel() {
		return this.level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	@Column(name = "COUNTRY", length = 64)
	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Integer getCreateStaffId() {
		return createStaffId;
	}

	public void setCreateStaffId(Integer createStaffId) {
		this.createStaffId = createStaffId;
	}

	public String getCreateStaffName() {
		return createStaffName;
	}

	public void setCreateStaffName(String createStaffName) {
		this.createStaffName = createStaffName;
	}

	public Integer getCreateStaffOrgId() {
		return createStaffOrgId;
	}

	public void setCreateStaffOrgId(Integer createStaffOrgId) {
		this.createStaffOrgId = createStaffOrgId;
	}

	public String getCreateStaffOrgName() {
		return createStaffOrgName;
	}

	public void setCreateStaffOrgName(String createStaffOrgName) {
		this.createStaffOrgName = createStaffOrgName;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	@Column(name = "TELEPHONE", length = 32)
	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	@Column(name = "FAX", length = 32)
	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}
	@Column(name = "POSTCODE", length = 32)
	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

}