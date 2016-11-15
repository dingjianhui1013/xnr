/**
 *2012-5-15 下午3:37:11
 */
package com.xnradmin.vo;

import java.io.Serializable;

/**
 * @author: bin_liu
 * 
 */
public class ContactVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String contactid;
	private String contactName;
	private String mobile;
	private String email;
	private String position;
	private String contactstatus;

	private String customerid;
	private String customerName;
	private String customerAddress;
	private String province;
	private String city;
	private String extent;
	private String level;
	private String country;

	public String getContactid() {
		return contactid;
	}

	public void setContactid(String contactid) {
		this.contactid = contactid;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getCustomerid() {
		return customerid;
	}

	public void setCustomerid(String customerid) {
		this.customerid = customerid;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getExtent() {
		return extent;
	}

	public void setExtent(String extent) {
		this.extent = extent;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getContactstatus() {
		return contactstatus;
	}

	public void setContactstatus(String contactstatus) {
		this.contactstatus = contactstatus;
	}
}
