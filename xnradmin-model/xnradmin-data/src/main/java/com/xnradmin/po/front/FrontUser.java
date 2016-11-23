package com.xnradmin.po.front;



import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * FrontUser entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "front_user")
public class FrontUser implements java.io.Serializable {

	// Fields

	private Long id;
	private String userName;
	private String password;
	private String phone;
	private String email;
	private Date createDate;
	private Date updateDate;
	private String delFlag;
	private String type;//0未审核，1通过审核，2未通过审核
	// Constructors

	/** default constructor */
	public FrontUser() {
	}

	/** full constructor */
	public FrontUser(String userName, String password, String phone,
			String email, Date createDate, Date updateDate,
			String delFlag) {
		this.userName = userName;
		this.password = password;
		this.phone = phone;
		this.email = email;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.delFlag = delFlag;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "user_name")
	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "password")
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "phone", length = 31)
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "email")
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "create_date", length = 19)
	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Column(name = "update_date", length = 19)
	public Date getUpdateDate() {
		return this.updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	@Column(name = "del_flag")
	public String getDelFlag() {
		return this.delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}
	@Column(name = "type")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}