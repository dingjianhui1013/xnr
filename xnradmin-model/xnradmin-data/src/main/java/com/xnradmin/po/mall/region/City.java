package com.xnradmin.po.mall.region;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Index;

/**
 * CommonMenu entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "city")
public class City implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String city;
	private String country;
	@Index(name = "idx_city_countryId")
	private Integer countryId;
	private String province;
	@Index(name = "idx_city_provinceId")
	private Integer provinceId;
	// Constructors

	/** default constructor */
	public City() {
	    
	}

	/** minimal constructor */
	public City(Integer id) {
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
		return this.provinceId;
	}

	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}

	@Column(name = "PROVINCE", length = 20)
	public String getProvince() {
		return this.province;
	}

	public void setProvince(String province) {
		this.province = province;
	}
	
	@Column(name = "COUNTRY_ID")
	public Integer getCountryId() {
		return this.countryId;
	}

	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}

	@Column(name = "COUNTRY", length = 20)
	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	
	@Column(name = "CITY", length = 20)
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
}