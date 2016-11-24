package com.xnradmin.po.wx.connect;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="farmer_image_bak")
public class FarmerImageBak {
	private int id;
	private String userId;
	private String picUrl;
	private String typeName;
	private Date createDate;
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
	@Column(name="picurl")
	public String getPicUrl() {
		return picUrl;
	}
	@Column(name="type_name")
	public String getTypeName() {
		return typeName;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

}