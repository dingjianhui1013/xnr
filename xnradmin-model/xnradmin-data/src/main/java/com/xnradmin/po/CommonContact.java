package com.xnradmin.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * CommonContact entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "common_contact")
public class CommonContact implements java.io.Serializable {

	// Fields

	private Integer id;
	private String contactName;
	private String mobile;
	private String email;
	private String position;
	private String status;

	// Constructors

	/** default constructor */
	public CommonContact() {
	}

	/** full constructor */
	public CommonContact(String contactName, String mobile, String email,
			String position, String status) {
		this.contactName = contactName;
		this.mobile = mobile;
		this.email = email;
		this.position = position;
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

	@Column(name = "CONTACT_NAME", length = 32)
	public String getContactName() {
		return this.contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	@Column(name = "MOBILE", length = 64)
	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Column(name = "EMAIL", length = 128)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "POSITION", length = 128)
	public String getPosition() {
		return this.position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	@Column(name = "STATUS", length = 128)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}