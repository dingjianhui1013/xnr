package com.xnradmin.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * CommonCustomerContact entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "common_customer_contact")
public class CommonCustomerContact implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer customerId;
	private Integer contactId;

	// Constructors

	/** default constructor */
	public CommonCustomerContact() {
	}

	/** full constructor */
	public CommonCustomerContact(Integer customerId, Integer contactId) {
		this.customerId = customerId;
		this.contactId = contactId;
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

	@Column(name = "CUSTOMER_ID", nullable = false)
	public Integer getCustomerId() {
		return this.customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	@Column(name = "CONTACT_ID", nullable = false)
	public Integer getContactId() {
		return this.contactId;
	}

	public void setContactId(Integer contactId) {
		this.contactId = contactId;
	}

}