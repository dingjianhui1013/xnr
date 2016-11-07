package com.xnradmin.po.wx.connect;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Index;

@Entity
@Table(name = "farmer",uniqueConstraints = {@UniqueConstraint(columnNames={"user_id"})})
public class Farmer implements Serializable{
	private Integer id;
	private String userName;
	private String userId;
	private String headPortrait;
	private String types;
	@Id
	@GeneratedValue(strategy = IDENTITY)
    @Column(name = "ID", unique = true, nullable = false) 
	public Integer getId() {
		return id;
	}
	@Column(name="user_name")
	public String getUserName() {
		return userName;
	}
	@Column(name="user_id")
	public String getUserId() {
		return userId;
	}
	@Column(name="head_portrait")
	public String getHeadPortrait() {
		return headPortrait;
	}
	public void setHeadPortrait(String headPortrait) {
		this.headPortrait = headPortrait;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	@Column(name="types")
	public String getTypes() {
		return types;
	}
	public void setTypes(String types) {
		this.types = types;
	}
	
	
}
