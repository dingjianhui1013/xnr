/**
 *2014年9月5日 下午3:58:00
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
@Table(name = "wx_client_user_message")
public class WXClientUserMessage implements java.io.Serializable {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	private Long id;

	@Column(name = "content", length = 2048)
	private String content;

	// 图片 = image,文本 = text,语音 = voice,视频 = video,地理消息 = location,链接消息 = link
	private Integer userMsgType;

	private String userMsgTypeName;

	private String wxmsgCreateTime;

	private Timestamp receiveTime;

	@Index(name = "idx_wx_client_user_message_wxmsgId")
	private String wxmsgId;

	// 对应API里的FromUserName
	private String fromUserName;

	// 对应API里的开发者微信账号的OPENUID
	private String toUserName;

	// 系统内客户端用户ID
	private Long clientUserInfoId;

	// 系统内微信用户ID
	@Index(name = "idx_wx_client_user_message_wxuserid")
	private Long wxuserId;

	// 如果用户发来的是图片消息，这里存微信的图片链接地址
	@Column(name = "picUrl", length = 1024)
	private String picUrl;

	// 图片,语音消息媒体id，可以调用多媒体文件下载接口拉取数据。
	@Index(name = "idx_wx_client_user_message_mediaid")
	private String mediaId;

	// 语音格式，如amr，speex等
	private String format;

	// 视频消息缩略图的媒体id，可以调用多媒体文件下载接口拉取数据。
	private String thumbMediaId;

	// 地理位置维度
	private String location_X;

	// 地理位置经度
	private String location_Y;

	// 地图缩放大小
	private String scale;

	// 地理位置信息
	private String label;

	// 链接消息的消息标题
	private String title;

	// 链接消息的消息描述
	private String description;

	// 链接消息的消息链接
	private String url;
	
	//调用的被动消息ID
	@Index(name = "idx_wx_client_user_message_reactivemessageid")
	private Long reactiveMessageId;

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

	public String getContent() {
		return content;
	}

	public Integer getUserMsgType() {
		return userMsgType;
	}

	public String getUserMsgTypeName() {
		return userMsgTypeName;
	}

	public String getWxmsgCreateTime() {
		return wxmsgCreateTime;
	}

	public Timestamp getReceiveTime() {
		return receiveTime;
	}

	public String getWxmsgId() {
		return wxmsgId;
	}

	public String getFromUserName() {
		return fromUserName;
	}

	public String getToUserName() {
		return toUserName;
	}

	public Long getClientUserInfoId() {
		return clientUserInfoId;
	}

	public Long getWxuserId() {
		return wxuserId;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public String getMediaId() {
		return mediaId;
	}

	public String getFormat() {
		return format;
	}

	public String getThumbMediaId() {
		return thumbMediaId;
	}

	public String getLocation_X() {
		return location_X;
	}

	public String getLocation_Y() {
		return location_Y;
	}

	public String getScale() {
		return scale;
	}

	public String getLabel() {
		return label;
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public String getUrl() {
		return url;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setUserMsgType(Integer userMsgType) {
		this.userMsgType = userMsgType;
	}

	public void setUserMsgTypeName(String userMsgTypeName) {
		this.userMsgTypeName = userMsgTypeName;
	}

	public void setWxmsgCreateTime(String wxmsgCreateTime) {
		this.wxmsgCreateTime = wxmsgCreateTime;
	}

	public void setReceiveTime(Timestamp receiveTime) {
		this.receiveTime = receiveTime;
	}

	public void setWxmsgId(String wxmsgId) {
		this.wxmsgId = wxmsgId;
	}

	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}

	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}

	public void setClientUserInfoId(Long clientUserInfoId) {
		this.clientUserInfoId = clientUserInfoId;
	}

	public void setWxuserId(Long wxuserId) {
		this.wxuserId = wxuserId;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public void setThumbMediaId(String thumbMediaId) {
		this.thumbMediaId = thumbMediaId;
	}

	public void setLocation_X(String location_X) {
		this.location_X = location_X;
	}

	public void setLocation_Y(String location_Y) {
		this.location_Y = location_Y;
	}

	public void setScale(String scale) {
		this.scale = scale;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Long getReactiveMessageId() {
		return reactiveMessageId;
	}

	public void setReactiveMessageId(Long reactiveMessageId) {
		this.reactiveMessageId = reactiveMessageId;
	}
}
