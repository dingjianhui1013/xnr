/**
 *2012-10-10 上午3:21:25
 */
package com.xnradmin.po.message;

import static javax.persistence.GenerationType.IDENTITY;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author: bin_liu
 * 
 */
@Entity
@Table(name = "message_msg")
public class MessageMsg implements java.io.Serializable {

	private Long id;
	private Integer senderId;
	private String senderName;
	private Integer senderOrgid;
	private String sendOrgName;

	private String modelName;

	private Integer receiverId;
	private String receiverName;
	private Integer recOrgid;
	private String recOrgName;
	private String recMobile;
	private String recMail;

	private String recGroupId;
	private Integer status;
	private String title;
	private String content;
	private Timestamp sendTime;
	private Timestamp readTime;
	private String statusName;
	private Integer errorCode;
	private String errorMsg;

	private String msgid;
	private Integer msgType;
	private String msgTypeName;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("MessageMsg : [");
		sb.append(" id<").append(id).append(">");
		sb.append(" senderId<").append(senderId).append(">");
		sb.append(" senderName<").append(this.senderName).append(">");		
		sb.append(" title<").append(this.title).append(">");
		sb.append(" content<").append(this.content).append(">");
		sb.append(" errorMsg<").append(this.errorMsg).append(">");
		sb.append(" modelName<").append(this.modelName).append(">");
		sb.append(" msgid<").append(this.msgid).append(">");
		sb.append(" msgType<").append(this.msgType).append(">");
		sb.append(" msgTypeName<").append(this.msgTypeName).append(">");
		sb.append(" receiverId<").append(this.receiverId).append(">");
		sb.append(" receiverName<").append(this.receiverName).append(">");
		sb.append(" recGroupId<").append(this.recGroupId).append(">");
		sb.append(" recMail<").append(this.recMail).append(">");
		sb.append(" recMobile<").append(this.recMobile).append(">");
		sb.append(" recOrgName<").append(this.recOrgName).append(">");
		sb.append(" recOrgid<").append(this.recOrgid).append(">");
		sb.append(" sendOrgName<").append(this.sendOrgName).append(">");
		sb.append(" status<").append(this.status).append(">");
		sb.append(" statusName<").append(this.statusName).append(">");		
		sb.append(" readTime<").append(this.readTime).append(">");		
		sb.append(" senderId<").append(this.senderId).append(">");
		sb.append(" senderOrgid<").append(this.senderOrgid).append(">");
		sb.append(" sendTime<").append(this.sendTime).append(">");
				
		sb.append("]");
		return sb.toString();
	}

	public Integer getSenderId() {
		return senderId;
	}

	public void setSenderId(Integer senderId) {
		this.senderId = senderId;
	}

	public Integer getReceiverId() {
		return receiverId;
	}

	public void setReceiverId(Integer receiverId) {
		this.receiverId = receiverId;
	}

	public String getRecGroupId() {
		return recGroupId;
	}

	public void setRecGroupId(String recGroupId) {
		this.recGroupId = recGroupId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Timestamp getSendTime() {
		return sendTime;
	}

	public void setSendTime(Timestamp sendTime) {
		this.sendTime = sendTime;
	}

	public Timestamp getReadTime() {
		return readTime;
	}

	public void setReadTime(Timestamp readTime) {
		this.readTime = readTime;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public String getSenderName() {
		return senderName;
	}

	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}

	public Integer getSenderOrgid() {
		return senderOrgid;
	}

	public void setSenderOrgid(Integer senderOrgid) {
		this.senderOrgid = senderOrgid;
	}

	public String getSendOrgName() {
		return sendOrgName;
	}

	public void setSendOrgName(String sendOrgName) {
		this.sendOrgName = sendOrgName;
	}

	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	public Integer getRecOrgid() {
		return recOrgid;
	}

	public void setRecOrgid(Integer recOrgid) {
		this.recOrgid = recOrgid;
	}

	public String getRecOrgName() {
		return recOrgName;
	}

	public void setRecOrgName(String recOrgName) {
		this.recOrgName = recOrgName;
	}

	public Integer getErrorCode() {
		return errorCode;
	}

	public String getRecMobile() {
		return recMobile;
	}

	public void setRecMobile(String recMobile) {
		this.recMobile = recMobile;
	}

	public String getRecMail() {
		return recMail;
	}

	public void setRecMail(String recMail) {
		this.recMail = recMail;
	}

	public void setErrorCode(Integer errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public String getMsgid() {
		return msgid;
	}

	public void setMsgid(String msgid) {
		this.msgid = msgid;
	}

	public Integer getMsgType() {
		return msgType;
	}

	public void setMsgType(Integer msgType) {
		this.msgType = msgType;
	}

	public String getMsgTypeName() {
		return msgTypeName;
	}

	public void setMsgTypeName(String msgTypeName) {
		this.msgTypeName = msgTypeName;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

}
