package com.xnradmin.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * CommonPermissionMenuRelation entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "common_permission_menu_relation")
public class CommonPermissionMenuRelation implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 6696666858055518603L;
	private Integer id;
	private Integer permissionId;
	private Integer menuId;

	// Constructors

	/** default constructor */
	public CommonPermissionMenuRelation() {
	}

	/** full constructor */
	public CommonPermissionMenuRelation(Integer permissionId, Integer menuId) {
		this.permissionId = permissionId;
		this.menuId = menuId;
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

	@Column(name = "MENU_ID", nullable = false, precision = 11, scale = 0)
	public Integer getMenuId() {
		return this.menuId;
	}

	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}

}