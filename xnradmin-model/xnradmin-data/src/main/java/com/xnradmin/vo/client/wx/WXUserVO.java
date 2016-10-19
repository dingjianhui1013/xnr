/**
 *2014年8月20日 下午4:00:34
 */
package com.xnradmin.vo.client.wx;

import java.io.Serializable;

import com.cntinker.util.ReflectHelper;
import com.xnradmin.po.CommonStaff;
import com.xnradmin.po.common.status.Status;
import com.xnradmin.po.wx.WXAccessToken;
import com.xnradmin.po.wx.WXUser;

/**
 * @author: liubin
 * 
 */
public class WXUserVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private WXUser wxuser;

	private CommonStaff staff;

	private Status status;
	
	private WXAccessToken wxaccessToken;
	
	private String staffName;
	
	private String wxuserid;
	
	private CommonStaff modifyStaff;
	
	public String toString() {
		String res = "";
		try {
			res = ReflectHelper.makeToString(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public WXUser getWxuser() {
		return wxuser;
	}

	public CommonStaff getStaff() {
		return staff;
	}

	public void setWxuser(WXUser wxuser) {
		this.wxuser = wxuser;
	}

	public void setStaff(CommonStaff staff) {
		this.staff = staff;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public WXAccessToken getWxaccessToken() {
		return wxaccessToken;
	}

	public void setWxaccessToken(WXAccessToken wxaccessToken) {
		this.wxaccessToken = wxaccessToken;
	}

	public String getStaffName() {
		return staffName;
	}

	public String getWxuserid() {
		return wxuserid;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	public void setWxuserid(String wxuserid) {
		this.wxuserid = wxuserid;
	}

	public CommonStaff getModifyStaff() {
		return modifyStaff;
	}

	public void setModifyStaff(CommonStaff modifyStaff) {
		this.modifyStaff = modifyStaff;
	}

}
