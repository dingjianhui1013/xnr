package com.xnradmin.po.front;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="receipt_address")
public class ReceiptAddress {
	private int id;
	private String receiptName;
	private String city;
	private String province;
	private String county;
	private String tel;
	private String detailedAddress;
	private String email;
	private String type;//默认地址
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return id;
	}
	@Column(name="receipt_name")
	public String getReceiptName() {
		return receiptName;
	}
	@Column(name = "city")
	public String getCity() {
		return city;
	}
	@Column(name="province")
	public String getProvince() {
		return province;
	}
	@Column(name="county")
	public String getCounty() {
		return county;
	}
	@Column(name="tel")
	public String getTel() {
		return tel;
	}
	@Column(name="detailed_address")
	public String getDetailedAddress() {
		return detailedAddress;
	}
	@Column(name="email")
	public String getEmail() {
		return email;
	}
	@Column(name="type")
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setReceiptName(String receiptName) {
		this.receiptName = receiptName;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public void setCounty(String county) {
		this.county = county;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public void setDetailedAddress(String detailedAddress) {
		this.detailedAddress = detailedAddress;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
}
