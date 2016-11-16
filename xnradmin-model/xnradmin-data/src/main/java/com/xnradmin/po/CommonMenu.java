package com.xnradmin.po;

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
@Table(name = "common_menu")
public class CommonMenu implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer praentMenuId;
	private String menuName;
	private String enName;
	private Integer menuLevel;
	private String menuLink;
	private Integer status;
	private Integer sortOrder;

	// Constructors

	/** default constructor */
	public CommonMenu() {
	    
	}

	/** minimal constructor */
	public CommonMenu(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public CommonMenu(Integer id, Integer praentMenuId, String menuName,
			String enName, Integer menuLevel, String menuLink, Integer status) {
		this.id = id;
		this.praentMenuId = praentMenuId;
		this.menuName = menuName;
		this.enName = enName;
		this.menuLevel = menuLevel;
		this.menuLink = menuLink;
		this.status = status;
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

	@Column(name = "PRAENT_MENU_ID", precision = 11, scale = 0)
	public Integer getPraentMenuId() {
		return this.praentMenuId;
	}

	public void setPraentMenuId(Integer praentMenuId) {
		this.praentMenuId = praentMenuId;
	}

	@Column(name = "MENU_NAME", length = 150)
	public String getMenuName() {
		return this.menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	@Column(name = "EN_NAME", length = 150)
	public String getEnName() {
		return this.enName;
	}

	public void setEnName(String enName) {
		this.enName = enName;
	}

	@Column(name = "MENU_LEVEL")
	public Integer getMenuLevel() {
		return this.menuLevel;
	}

	public void setMenuLevel(Integer menuLevel) {
		this.menuLevel = menuLevel;
	}

	@Column(name = "MENU_LINK", length = 500)
	public String getMenuLink() {
		return this.menuLink;
	}

	public void setMenuLink(String menuLink) {
		this.menuLink = menuLink;
	}

	@Column(name = "STATUS")
	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(Integer sortOrder) {
		this.sortOrder = sortOrder;
	}

}