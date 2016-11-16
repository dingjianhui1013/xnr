/**
 *2014年8月31日 下午4:05:46
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
@Table(name = "wx_reactive_message")
public class WXReactiveMessage implements java.io.Serializable {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	private Long id;

	@Index(name = "idx_wx_message_wxuserid")
	private Long wxuserid;

	// 接收到的消息
	@Column(name = "recContent", length = 2048)
	private String recContent;

	private Integer msgTypeId;

	private Timestamp createTime;

	private Integer createStaff;

	@Column(name = "msgJson", length = 4096)
	private String msgJson;

	private Long wxmenuid;

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

	public String getRecContent() {
		return recContent;
	}

	public Integer getMsgTypeId() {
		return msgTypeId;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setWxuserid(Long wxuserid) {
		this.wxuserid = wxuserid;
	}

	public void setRecContent(String recContent) {
		this.recContent = recContent;
	}

	public void setMsgTypeId(Integer msgTypeId) {
		this.msgTypeId = msgTypeId;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public Integer getCreateStaff() {
		return createStaff;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public void setCreateStaff(Integer createStaff) {
		this.createStaff = createStaff;
	}

	public String getMsgJson() {
		return msgJson;
	}

	public void setMsgJson(String msgJson) {
		this.msgJson = msgJson;
	}

	public Long getWxmenuid() {
		return wxmenuid;
	}

	public void setWxmenuid(Long wxmenuid) {
		this.wxmenuid = wxmenuid;
	}

}
