/**
 *2014年9月20日 下午4:59:44
 */
package com.xnradmin.client.action.wx;

import java.io.IOException;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.xnradmin.client.service.wx.WXQRcodeService;
import com.xnradmin.constant.StrutsResMSG;
import com.xnradmin.core.action.ParentAction;
import com.xnradmin.vo.client.wx.WXQrcodeVO;

/**
 * @author: liubin
 *
 */
@Controller
@Scope("prototype")
@Namespace("/page/wx/wxqrcode")
@ParentPackage("json-default")
public class WXQrcodeAction extends ParentAction {

	@Override
	public boolean isPublic() {
		// TODO Auto-generated method stub
		return false;
	}

	@Autowired
	private WXQRcodeService wxqrcodeService;

	@Action(value = "view", results = { @Result(name = StrutsResMSG.SUCCESS, location = "/staff/wxqrcode.jsp") })
	public String view() throws IOException {
		// this.query =
		// this.wxMessageService.findByIdVO(this.query.getWxmessage().getId().toString());
		if (query == null || query.getStaff() == null
				|| query.getStaff().getId() == null) {
			super.error("缺少参数，指定用户不能位空");
			return StrutsResMSG.SUCCESS;
		}
		this.query = wxqrcodeService.findByStaffid(this.query.getStaff()
				.getId());

		return StrutsResMSG.SUCCESS;
	}

	@Action(value = "genWxqrcode", results = { @Result(name = StrutsResMSG.SUCCESS, type = "plainText") })
	public String genWxqrcode() throws IOException {
		//wxqrcodeService.genNoLimitQRcode(null, null);
		//wxqrcodeService.genTempQRcode(wxuserid, scene_id);
		return StrutsResMSG.SUCCESS;
	}

	private WXQrcodeVO query;

	public WXQrcodeVO getQuery() {
		return query;
	}

	public void setQuery(WXQrcodeVO query) {
		this.query = query;
	}

}
