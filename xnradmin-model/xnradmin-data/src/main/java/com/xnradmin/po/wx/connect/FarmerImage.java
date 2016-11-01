package com.xnradmin.po.wx.connect;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

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
	
	public FarmerImage() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public FarmerImage(int id, String userId, String userName, String type,
			String url) {
		super();
		this.id = id;
		this.userId = userId;
		this.userName = userName;
		this.type = type;
		this.url = url;
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
