package com.xnradmin.po;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * CommonPermission entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "common_permission")
public class CommonPermission implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 6049553301044992649L;
	private Integer id;
	private String permissionName;
	private String permissionDesc;
	private String permissionCode;
	private boolean enabled;


	// Constructors

	/** default constructor */
	public CommonPermission() {
	}

	/** full constructor */
	public CommonPermission(String permissionName) {
		this.permissionName = permissionName;
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

	@Column(name = "PERMISSION_NAME", length = 64)
	public String getPermissionName() {
		return this.permissionName;
	}

	public void setPermissionName(String permissionName) {
		this.permissionName = permissionName;
	}
	@Column(name = "PERMISSIONDESC",length = 255)
	public String getPermissionDesc() {
		return permissionDesc;
	}

	public void setPermissionDesc(String permissionDesc) {
		this.permissionDesc = permissionDesc;
	}
	@Column(name = "PERMISSIONCODE", unique = true, nullable = false)
	public String getPermissionCode() {
		return permissionCode;
	}

	public void setPermissionCode(String permissionCode) {
		this.permissionCode = permissionCode;
	}
	@Column(name = "ENABLED")
	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	

}