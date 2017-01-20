package com.xnradmin.po.wx.connect;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="farmer_image")
public class FarmerImage implements Serializable{

	private int id;
	private String userId;
	private String userName;
	private String type;
	private String url;
	private String date;
	private String remarks;
	private String newUrl;
	private String modifyDate;
	public FarmerImage() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FarmerImage(int id, String userId, String userName, String type,
			String url, String date, String remarks) {
		super();
		this.id = id;
		this.userId = userId;
		this.userName = userName;
		this.type = type;
		this.url = url;
		this.date = date;
		this.remarks = remarks;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
    @Column(name = "ID", unique = true, nullable = false) 
	public int getId() {
		return id;
	}
	@Column(name="user_id")
	public String getUserId() {
		return userId;
	}
	@Column(name="user_name")
	public String getUserName() {
		return userName;
	}
	@Column(name="type")
	public String getType() {
		return type;
	}
	@Column(name="url")
	public String getUrl() {
		return url;
	}
	@Column(name="date")
	public String getDate() {
		return date;
	}
	@Column(name="remarks")
	public String getRemarks() {
		return remarks;
	}
	@Column(name="new_url")
	public String getNewUrl() {
		return newUrl;
	}
	@Column(name="modify_date")
	public String getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}


	public void setNewUrl(String newUrl) {
		this.newUrl = newUrl;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public void setId(int id) {
		this.id = id;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public void setType(String type) {
		this.type = type;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
}
