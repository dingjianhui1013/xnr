/**
 *2014年9月1日 下午12:43:55
 */
package com.xnradmin.vo.client.wx;

import java.io.Serializable;

import com.cntinker.util.ReflectHelper;
import com.xnradmin.po.CommonAttach;
import com.xnradmin.po.CommonStaff;
import com.xnradmin.po.common.status.Status;
import com.xnradmin.po.wx.WXMessage;

/**
 * @author: liubin
 * 
 */
public class WXMessageVO implements Serializable {

	private WXMessage wxmessage;

	// private WXUserVO wxuservo;

	private CommonStaff createStaff;

	private Status msgType;

	private CommonAttach upload;

	private String msgContent;

	private String id;

	private String sortOrder;
	
	private String msgTitle;
	
	private String loopid;

	public String toString() {
		String res = "";
		try {
			res = ReflectHelper.makeToString(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public WXMessage getWxmessage() {
		return wxmessage;
	}

	public void setWxmessage(WXMessage wxmessage) {
		this.wxmessage = wxmessage;
	}

	public CommonStaff getCreateStaff() {
		return createStaff;
	}

	public void setCreateStaff(CommonStaff createStaff) {
		this.createStaff = createStaff;
	}

	public Status getMsgType() {
		return msgType;
	}

	public void setMsgType(Status msgType) {
		this.msgType = msgType;
	}

	public CommonAttach getUpload() {
		return upload;
	}

	public void setUpload(CommonAttach upload) {
		this.upload = upload;
	}

	public String getMsgContent() {
		return msgContent;
	}

	public void setMsgContent(String msgContent) {
		this.msgContent = msgContent;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}

	public String getMsgTitle() {
		return msgTitle;
	}

	public void setMsgTitle(String msgTitle) {
		this.msgTitle = msgTitle;
	}

	public String getLoopid() {
		return loopid;
	}

	public void setLoopid(String loopid) {
		this.loopid = loopid;
	}

}
