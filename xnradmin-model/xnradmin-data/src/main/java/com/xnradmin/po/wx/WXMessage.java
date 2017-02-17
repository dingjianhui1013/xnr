/**
 *2014年8月26日 上午11:39:42
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
@Table(name = "wx_message")
public class WXMessage implements java.io.Serializable {
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	private Long id;

	private Timestamp receiveTime;

	// 返回的消息
	@Column(name = "content", length = 2048)
	private String content;

	// 语音 = amr,文本 = text,图文消息 = news
	private Integer msgTypeId;

	private String msgTitle;

	private String msgDescription;

	// 图片链接，支持JPG、PNG格式，较好的效果为大图360*200，小图200*200
	private String picUrl;

	// 点击图文消息跳转链接
	private String clickUrl;

	private Timestamp createTime;

	private Integer createStaffID;

	private String uploadGroupid;

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

	public Timestamp getReceiveTime() {
		return receiveTime;
	}

	public String getContent() {
		return content;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setReceiveTime(Timestamp receiveTime) {
		this.receiveTime = receiveTime;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getMsgTypeId() {
		return msgTypeId;
	}

	public String getMsgTitle() {
		return msgTitle;
	}

	public String getMsgDescription() {
		return msgDescription;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public String getClickUrl() {
		return clickUrl;
	}

	public void setMsgTypeId(Integer msgTypeId) {
		this.msgTypeId = msgTypeId;
	}

	public void setMsgTitle(String msgTitle) {
		this.msgTitle = msgTitle;
	}

	public void setMsgDescription(String msgDescription) {
		this.msgDescription = msgDescription;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public void setClickUrl(String clickUrl) {
		this.clickUrl = clickUrl;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public Integer getCreateStaffID() {
		return createStaffID;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public void setCreateStaffID(Integer createStaffID) {
		this.createStaffID = createStaffID;
	}

	public String getUploadGroupid() {
		return uploadGroupid;
	}

	public void setUploadGroupid(String uploadGroupid) {
		this.uploadGroupid = uploadGroupid;
	}

}
