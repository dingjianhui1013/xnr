/**
 * 2013-5-29 下午5:08:50
 */
package com.xnradmin.po.sms;

import static javax.persistence.GenerationType.IDENTITY;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.cntinker.util.ReflectHelper;

/**
 * @autohr: bin_liu
 */
@Entity
@Table(name = "sms_record")
public class SmsRecord implements java.io.Serializable {

	private Long id;

	// local uuid
	private Integer uid;// 用户uid

	private String phone; // 用户手机号码

	private String content; // 发送内容

	private Timestamp mtTime; // 发送时间

	private String msgID;// 下发唯一id

	private Timestamp statusTime; // 发送时间

	private String status; // 状态

	private Integer customerId;// 合作方id

	private String customerName;// 合作方名称

	public String toString() {
		String res = "";
		try {
			res = ReflectHelper.makeToString(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "UID")
	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	@Column(name = "PHONE", length = 11)
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "CONTENT", length = 70)
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "MT_TIME")
	public Timestamp getMtTime() {
		return mtTime;
	}

	public void setMtTime(Timestamp mtTime) {
		this.mtTime = mtTime;
	}

	@Column(name = "MSGID")
	public String getMsgID() {
		return msgID;
	}

	public void setMsgID(String msgID) {
		this.msgID = msgID;
	}

	@Column(name = "STATUS_TIME")
	public Timestamp getStatusTime() {
		return statusTime;
	}

	public void setStatusTime(Timestamp statusTime) {
		this.statusTime = statusTime;
	}

	@Column(name = "STATUS")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "CUSTOMER_ID")
	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	@Column(name = "CUSTOMER_NAME")
	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
}
