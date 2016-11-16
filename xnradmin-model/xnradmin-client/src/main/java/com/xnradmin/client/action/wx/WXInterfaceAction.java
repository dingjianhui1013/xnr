/**
 *2014年8月23日 下午3:07:26
 */
package com.xnradmin.client.action.wx;

import java.io.IOException;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.cntinker.util.HttpHelper;
import com.xnradmin.constant.AjaxResult;
import com.xnradmin.constant.StrutsResMSG;
import com.xnradmin.core.action.ParentAction;

/**
 * @author: liubin
 * 
 */
@Controller
@Scope("prototype")
@Namespace("/page/wx/wxinterface")
@ParentPackage("json-default")
public class WXInterfaceAction extends ParentAction {

	private String resOut;

	private String headerOut;

	private String urlOut;

	@Override
	public boolean isPublic() {
		// TODO Auto-generated method stub
		return false;
	}

	@Action(value = "sendInfo", results = { @Result(name = StrutsResMSG.SUCCESS, location = "/wx/admin/seting/wxinterface/send.jsp") })
	public String sendInfo() {
		return StrutsResMSG.SUCCESS;
	}

	@Action(value = "sendToView", results = { @Result(name = StrutsResMSG.SUCCESS, type = "json") })
	public String sendToView() throws IOException {
		String[] r = HttpHelper.sendGetReturnHeaderRes(urlOut, "UTF-8");
		if (r != null && r.length > 0) {
			this.headerOut = r[0];
			this.resOut = r[1];
		}
		return StrutsResMSG.SUCCESS;
	}

	public String getResOut() {
		return resOut;
	}

	public String getHeaderOut() {
		return headerOut;
	}

	public String getUrlOut() {
		return urlOut;
	}

	public void setResOut(String resOut) {
		this.resOut = resOut;
	}

	public void setHeaderOut(String headerOut) {
		this.headerOut = headerOut;
	}

	public void setUrlOut(String urlOut) {
		this.urlOut = urlOut;
	}

}
