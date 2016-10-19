/**
 *2014年8月15日 下午4:52:04
 */
package com.xnradmin.po.wx;

import static javax.persistence.GenerationType.IDENTITY;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Index;

import com.cntinker.util.ReflectHelper;

/**
 * @author: liubin
 * 
 */
@Entity
@Table(name = "wx_access_token")
public class WXAccessToken implements java.io.Serializable {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	private Long id;

	@Index(name = "idx_access_token_wxuserid")
	private Long wxuserid;

	// 单位：秒
	private Integer expiresin;

	private String grant_type;

	@Column(name = "accessToken", length = 1024)
	private String accessToken;

	private String res;

	private Timestamp lastGenTime;
	
	private String errcode;
	
	private String errmsg;
	
	public String toString() {
		String res = "";
		try {
			res = ReflectHelper.makeToString(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public Long getId() {
		return id;
	}

	public Long getWxuserid() {
		return wxuserid;
	}

	public Integer getExpiresin() {
		return expiresin;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setWxuserid(Long wxuserid) {
		this.wxuserid = wxuserid;
	}

	public void setExpiresin(Integer expiresin) {
		this.expiresin = expiresin;
	}

	public String getGrant_type() {
		return grant_type;
	}

	public void setGrant_type(String grant_type) {
		this.grant_type = grant_type;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getRes() {
		return res;
	}

	public void setRes(String res) {
		this.res = res;
	}

	public Timestamp getLastGenTime() {
		return lastGenTime;
	}

	public void setLastGenTime(Timestamp lastGenTime) {
		this.lastGenTime = lastGenTime;
	}

	public String getErrcode() {
		return errcode;
	}

	public String getErrmsg() {
		return errmsg;
	}

	public void setErrcode(String errcode) {
		this.errcode = errcode;
	}

	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}
}
