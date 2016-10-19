package com.xnradmin.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * CommonOrganizationType entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "common_organization_type")
public class CommonOrganizationType implements java.io.Serializable {

	// Fields

	private Integer id;
	private String organizationTypeName;

	// Constructors

	/** default constructor */
	public CommonOrganizationType() {
	}

	/** full constructor */
	public CommonOrganizationType(String organizationTypeName) {
		this.organizationTypeName = organizationTypeName;
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

	@Column(name = "ORGANIZATION_TYPE_NAME", length = 32)
	public String getOrganizationTypeName() {
		return this.organizationTypeName;
	}

	public void setOrganizationTypeName(String organizationTypeName) {
		this.organizationTypeName = organizationTypeName;
	}

}