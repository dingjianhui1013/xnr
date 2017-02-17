/**
 *2014年8月21日 下午4:51:41
 */
package com.xnradmin.vo.pay;

import java.io.Serializable;

import com.cntinker.util.ReflectHelper;
import com.xnradmin.po.CommonStaff;
import com.xnradmin.po.pay.PayInfo;
import com.xnradmin.po.wx.WXUser;

/**
 * @author: liubin
 * 
 */
public class PayInfoVO implements Serializable {

	private PayInfo payInfo;

	private CommonStaff staff;

	private WXUser wxuser;

	public String toString() {
		String res = "";
		try {
			res = ReflectHelper.makeToString(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public PayInfo getPayInfo() {
		return payInfo;
	}

	public CommonStaff getStaff() {
		return staff;
	}

	public WXUser getWxuser() {
		return wxuser;
	}

	public void setPayInfo(PayInfo payInfo) {
		this.payInfo = payInfo;
	}

	public void setStaff(CommonStaff staff) {
		this.staff = staff;
	}

	public void setWxuser(WXUser wxuser) {
		this.wxuser = wxuser;
	}
}
