/**
 *2014年8月15日 下午4:53:21
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
@Table(name = "wx_user")
public class WXUser implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	private Long id;

	private String token;

	@Index(name = "idx_wx_user_appid")
	private String appid;

	private String appSecret;

	// 微信通信中公众号/服务号的唯一标识
	private String wxuidtouser;

	// 对应公众号/服务号的用户名
	private Integer staffId;

	private Integer status;

	private Integer createStaffId;

	private Integer modifyStaffId;

	private Timestamp createTime;

	private Timestamp modifyTime;
	
	@Column(name = "welcome", length = 2048)
	private String welcome;

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

	public String getToken() {
		return token;
	}

	public String getAppid() {
		return appid;
	}

	public String getAppSecret() {
		return appSecret;
	}

	public Integer getStaffId() {
		return staffId;
	}

	public Integer getStatus() {
		return status;
	}

	public Integer getCreateStaffId() {
		return createStaffId;
	}

	public Integer getModifyStaffId() {
		return modifyStaffId;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public Timestamp getModifyTime() {
		return modifyTime;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
	}

	public void setStaffId(Integer staffId) {
		this.staffId = staffId;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public void setCreateStaffId(Integer createStaffId) {
		this.createStaffId = createStaffId;
	}

	public void setModifyStaffId(Integer modifyStaffId) {
		this.modifyStaffId = modifyStaffId;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public void setModifyTime(Timestamp modifyTime) {
		this.modifyTime = modifyTime;
	}

	public String getWxuidtouser() {
		return wxuidtouser;
	}

	public void setWxuidtouser(String wxuidtouser) {
		this.wxuidtouser = wxuidtouser;
	}

	public String getWelcome() {
		return welcome;
	}

	public void setWelcome(String welcome) {
		this.welcome = welcome;
	}

}
