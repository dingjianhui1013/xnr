package com.xnradmin.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * CommonRole entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "common_role")
public class CommonRole implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1148008605825055891L;
	private Integer id;
	private String roleName;
	private String description;
	private String roleCode;// roleCode 唯一，用于权限判断和授予，与spring security 结合
	private boolean enabled;  //有效性标识
	// Constructors

	/** default constructor */
	public CommonRole() {
	}

	/** full constructor */
	public CommonRole(String roleName, String description) {
		this.roleName = roleName;
		this.description = description;
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

	@Column(name = "ROLE_NAME", length = 64)
	public String getRoleName() {
		return this.roleName;
	}

	
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@Column(name = "DESCRIPTION", length = 1024)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	@Column(name = "ROLECODE", unique = true, nullable = false)
	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}
	@Column(name = "ENABLED")
	public boolean isEnabled() {
		return enabled;
	}
	
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}


}