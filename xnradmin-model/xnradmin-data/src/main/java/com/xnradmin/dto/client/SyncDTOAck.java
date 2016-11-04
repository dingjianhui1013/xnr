/**
 * 2014年2月2日 下午5:56:44
 */
package com.xnradmin.dto.client;

import java.io.Serializable;

import com.cntinker.util.ReflectHelper;

/**
 * @author: bin_liu
 */
public class SyncDTOAck implements Serializable {

	private String action = "";

	private String jsonOut = "";

	private String linkid = "";

	private String status = "";

	private String statusDesc = "";

	private String wxOut = "";

	public String toString() {
		String res = "";
		try {
			res = ReflectHelper.makeToString(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getJsonOut() {
		return jsonOut;
	}

	public void setJsonOut(String jsonOut) {
		this.jsonOut = jsonOut;
	}

	public String getLinkid() {
		return linkid;
	}

	public void setLinkid(String linkid) {
		this.linkid = linkid;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatusDesc() {
		return statusDesc;
	}

	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}

	public String getWxOut() {
		return wxOut;
	}

	public void setWxOut(String wxOut) {
		this.wxOut = wxOut;
	}
}
