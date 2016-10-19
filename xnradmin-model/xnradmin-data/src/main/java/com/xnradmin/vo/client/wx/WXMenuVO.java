/**
 *2014年8月25日 下午1:49:38
 */
package com.xnradmin.vo.client.wx;

import java.io.Serializable;

import com.cntinker.util.ReflectHelper;
import com.xnradmin.po.CommonStaff;
import com.xnradmin.po.common.status.Status;
import com.xnradmin.po.wx.WXMenu;

/**
 * @author: liubin
 * 
 */
public class WXMenuVO implements Serializable {

	private WXMenu menu;

	private WXUserVO wxuservo;

	private CommonStaff createStaff;

	private CommonStaff modifyStaff;
	
	private WXMenu parentMenu;
	
	private CommonStaff staff;
	
	private Status menuType;
	
	private String wxuserid;
	
	private String staffName;

	public String toString() {
		String res = "";
		try {
			res = ReflectHelper.makeToString(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public WXMenu getMenu() {
		return menu;
	}

	public WXUserVO getWxuservo() {
		return wxuservo;
	}

	public CommonStaff getCreateStaff() {
		return createStaff;
	}

	public CommonStaff getModifyStaff() {
		return modifyStaff;
	}

	public void setMenu(WXMenu menu) {
		this.menu = menu;
	}

	public void setWxuservo(WXUserVO wxuservo) {
		this.wxuservo = wxuservo;
	}

	public void setCreateStaff(CommonStaff createStaff) {
		this.createStaff = createStaff;
	}

	public void setModifyStaff(CommonStaff modifyStaff) {
		this.modifyStaff = modifyStaff;
	}

	public WXMenu getParentMenu() {
		return parentMenu;
	}

	public void setParentMenu(WXMenu parentMenu) {
		this.parentMenu = parentMenu;
	}

	public CommonStaff getStaff() {
		return staff;
	}

	public void setStaff(CommonStaff staff) {
		this.staff = staff;
	}

	public Status getMenuType() {
		return menuType;
	}

	public void setMenuType(Status menuType) {
		this.menuType = menuType;
	}

	public String getWxuserid() {
		return wxuserid;
	}

	public String getStaffName() {
		return staffName;
	}

	public void setWxuserid(String wxuserid) {
		this.wxuserid = wxuserid;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

}
