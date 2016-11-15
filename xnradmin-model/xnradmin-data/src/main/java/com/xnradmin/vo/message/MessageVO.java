/**
 *2012-12-8 下午12:26:46
 */
package com.xnradmin.vo.message;

import com.xnradmin.po.CommonOrganization;
import com.xnradmin.po.CommonStaff;
import com.xnradmin.po.message.MessageMsg;

/**
 * @author: bin_liu
 * 
 */
public class MessageVO {

	private MessageMsg msg;
	private CommonOrganization sendOrg;
	private CommonOrganization recOrg;
	private CommonStaff receiver;
	private CommonStaff sender;
	private boolean sendSms;
	private boolean sendMail;

	private String recStaffIds;
	private String recStaffNames;
	private String recOrgIds;
	private String recOrgNames;

	private String stime;
	private String etime;
	
	private String modelName;
	private Integer modelId;

	public MessageMsg getMsg() {
		return msg;
	}

	public void setMsg(MessageMsg msg) {
		this.msg = msg;
	}

	public CommonOrganization getSendOrg() {
		return sendOrg;
	}

	public void setSendOrg(CommonOrganization sendOrg) {
		this.sendOrg = sendOrg;
	}

	public CommonOrganization getRecOrg() {
		return recOrg;
	}

	public void setRecOrg(CommonOrganization recOrg) {
		this.recOrg = recOrg;
	}

	public CommonStaff getReceiver() {
		return receiver;
	}

	public void setReceiver(CommonStaff receiver) {
		this.receiver = receiver;
	}

	public CommonStaff getSender() {
		return sender;
	}

	public void setSender(CommonStaff sender) {
		this.sender = sender;
	}

	public String getStime() {
		return stime;
	}

	public void setStime(String stime) {
		this.stime = stime;
	}

	public String getEtime() {
		return etime;
	}

	public void setEtime(String etime) {
		this.etime = etime;
	}

	public boolean isSendSms() {
		return sendSms;
	}

	public void setSendSms(boolean sendSms) {
		this.sendSms = sendSms;
	}

	public boolean isSendMail() {
		return sendMail;
	}

	public void setSendMail(boolean sendMail) {
		this.sendMail = sendMail;
	}

	public String getRecStaffIds() {
		return recStaffIds;
	}

	public void setRecStaffIds(String recStaffIds) {
		this.recStaffIds = recStaffIds;
	}

	public String getRecOrgIds() {
		return recOrgIds;
	}

	public void setRecOrgIds(String recOrgIds) {
		this.recOrgIds = recOrgIds;
	}

	public String getRecStaffNames() {
		return recStaffNames;
	}

	public void setRecStaffNames(String recStaffNames) {
		this.recStaffNames = recStaffNames;
	}

	public String getRecOrgNames() {
		return recOrgNames;
	}

	public void setRecOrgNames(String recOrgNames) {
		this.recOrgNames = recOrgNames;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public Integer getModelId() {
		return modelId;
	}

	public void setModelId(Integer modelId) {
		this.modelId = modelId;
	}

}
