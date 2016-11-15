package com.xnradmin.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * CommonStaffRoleRelation entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "common_staff_role_relation")
public class CommonStaffRoleRelation implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer staffId;
	private Integer roleId;

	// Constructors

	/** default constructor */
	public CommonStaffRoleRelation() {
	}

	/** full constructor */
	public CommonStaffRoleRelation(Integer staffId, Integer roleId) {
		this.staffId = staffId;
		this.roleId = roleId;
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

	@Column(name = "STAFF_ID", nullable = false)
	public Integer getStaffId() {
		return this.staffId;
	}

	public void setStaffId(Integer staffId) {
		this.staffId = staffId;
	}

	@Column(name = "ROLE_ID", nullable = false)
	public Integer getRoleId() {
		return this.roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

}