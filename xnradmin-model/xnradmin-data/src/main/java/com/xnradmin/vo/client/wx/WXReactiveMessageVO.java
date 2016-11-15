/**
 *2014年9月7日 下午8:12:43
 */
package com.xnradmin.vo.client.wx;

import java.io.Serializable;
import java.util.List;

import com.cntinker.util.ReflectHelper;
import com.xnradmin.po.CommonStaff;
import com.xnradmin.po.wx.WXReactiveMessage;

/**
 * @author: liubin
 *
 */
public class WXReactiveMessageVO implements Serializable {

	private WXReactiveMessage reactiveMessage;

	private WXUserVO wxuservo;

	private CommonStaff createStaff;

	private String wxuserid;

	private String wxuserName;

	private String msgContent;

	private WXMenuVO wxmenu;
	
	private List<WXMessageVO> msgList;

	public String toString() {
		String res = "";
		try {
			res = ReflectHelper.makeToString(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public WXReactiveMessage getReactiveMessage() {
		return reactiveMessage;
	}

	public void setReactiveMessage(WXReactiveMessage reactiveMessage) {
		this.reactiveMessage = reactiveMessage;
	}

	public WXUserVO getWxuservo() {
		return wxuservo;
	}

	public void setWxuservo(WXUserVO wxuservo) {
		this.wxuservo = wxuservo;
	}

	public CommonStaff getCreateStaff() {
		return createStaff;
	}

	public void setCreateStaff(CommonStaff createStaff) {
		this.createStaff = createStaff;
	}

	public String getWxuserid() {
		return wxuserid;
	}

	public String getWxuserName() {
		return wxuserName;
	}

	public void setWxuserid(String wxuserid) {
		this.wxuserid = wxuserid;
	}

	public void setWxuserName(String wxuserName) {
		this.wxuserName = wxuserName;
	}

	public String getMsgContent() {
		return msgContent;
	}

	public void setMsgContent(String msgContent) {
		this.msgContent = msgContent;
	}

	public WXMenuVO getWxmenu() {
		return wxmenu;
	}

	public void setWxmenu(WXMenuVO wxmenu) {
		this.wxmenu = wxmenu;
	}

	public List<WXMessageVO> getMsgList() {
		return msgList;
	}

	public void setMsgList(List<WXMessageVO> msgList) {
		this.msgList = msgList;
	}

	
}
