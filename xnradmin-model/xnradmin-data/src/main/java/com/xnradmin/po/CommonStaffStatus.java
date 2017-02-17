package com.xnradmin.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * CommonStaffStatus entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "common_staff_status")
public class CommonStaffStatus implements java.io.Serializable {

	// Fields

	private Integer id;
	private String statusName;

	// Constructors

	/** default constructor */
	public CommonStaffStatus() {
	}

	/** full constructor */
	public CommonStaffStatus(String statusName) {
		this.statusName = statusName;
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

	@Column(name = "STATUS_NAME", length = 64)
	public String getStatusName() {
		return this.statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

}