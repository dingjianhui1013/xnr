package com.xnradmin.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * CommonRolePermissionRelation entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "common_role_permission_relation")
public class CommonRolePermissionRelation implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -2341278724747082179L;
	private Integer id;
	private Integer permissionId;
	private Integer roleId;

	// Constructors

	/** default constructor */
	public CommonRolePermissionRelation() {
	}

	/** full constructor */
	public CommonRolePermissionRelation(Integer permissionId, Integer roleId) {
		this.permissionId = permissionId;
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

	@Column(name = "PERMISSION_ID", nullable = false)
	public Integer getPermissionId() {
		return this.permissionId;
	}

	public void setPermissionId(Integer permissionId) {
		this.permissionId = permissionId;
	}

	@Column(name = "ROLE_ID", nullable = false)
	public Integer getRoleId() {
		return this.roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

}