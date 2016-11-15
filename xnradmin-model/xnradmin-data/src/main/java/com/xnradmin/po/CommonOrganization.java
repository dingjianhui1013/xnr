package com.xnradmin.po;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * CommonOrganization entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "common_organization")
public class CommonOrganization implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer praentOrganizationId;
	private String organizationName;

	private Integer organizationTypeId;

	// Constructors
	@Column(name = "ORGANIZATION_TYPE_ID")
	public Integer getOrganizationTypeId() {
		return organizationTypeId;
	}

	public void setOrganizationTypeId(Integer organizationTypeId) {
		this.organizationTypeId = organizationTypeId;
	}

	/** default constructor */
	public CommonOrganization() {
	}

	/** full constructor */
	public CommonOrganization(Integer praentOrganizationId,
			String organizationName, Integer organizationTypeId) {
		this.praentOrganizationId = praentOrganizationId;
		this.organizationName = organizationName;
		this.organizationTypeId = organizationTypeId;
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

	@Column(name = "PRAENT_ORGANIZATION_ID")
	public Integer getPraentOrganizationId() {
		return this.praentOrganizationId;
	}

	public void setPraentOrganizationId(Integer praentOrganizationId) {
		this.praentOrganizationId = praentOrganizationId;
	}

	@Column(name = "ORGANIZATION_NAME", length = 64)
	public String getOrganizationName() {
		return this.organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

}