package com.xnradmin.po.mall.region;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * CommonMenu entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "country")
public class Country implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String country;
	// Constructors

	/** default constructor */
	public Country() {
	    
	}

	/** minimal constructor */
	public Country(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public Country(Integer id, String country) {
		this.id = id;
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

	@Column(name = "COUNTRY", length = 20)
	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	
}