/**
 *2014年9月20日 下午5:05:26
 */
package com.xnradmin.vo.client.wx;

import java.io.Serializable;

import com.cntinker.util.ReflectHelper;
import com.xnradmin.po.CommonAttach;
import com.xnradmin.po.CommonStaff;
import com.xnradmin.po.wx.WXQRcode;

/**
 * @author: liubin
 *
 */
public class WXQrcodeVO implements Serializable {

	private WXQRcode wxqrcode;

	private CommonAttach attach;

	private WXUserVO wxuservo;
	
	private CommonStaff staff;

	public String toString() {
		String res = "";
		try {
			res = ReflectHelper.makeToString(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public WXQRcode getWxqrcode() {
		return wxqrcode;
	}

	public CommonAttach getAttach() {
		return attach;
	}

	public WXUserVO getWxuservo() {
		return wxuservo;
	}

	public void setWxqrcode(WXQRcode wxqrcode) {
		this.wxqrcode = wxqrcode;
	}

	public void setAttach(CommonAttach attach) {
		this.attach = attach;
	}

	public void setWxuservo(WXUserVO wxuservo) {
		this.wxuservo = wxuservo;
	}

	public CommonStaff getStaff() {
		return staff;
	}

	public void setStaff(CommonStaff staff) {
		this.staff = staff;
	}
}
