package com.xnradmin.client.action.wx;


import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.cntinker.util.wx.connect.WeixinUtil;
import com.xnradmin.client.service.wx.OutPlanService;
import com.xnradmin.client.service.wx.WXGetTokenService;
import com.xnradmin.constant.StrutsResMSG;
import com.xnradmin.po.wx.OutPlan;
import com.xnradmin.po.wx.connect.WXurl;

@Controller
@Scope("prototype")
@Namespace("/page/wx/outplan")
@ParentPackage("json-default")
public class OutPlanAction {
	
	private OutPlan outplan ;
	
	public OutPlan getOutplan() {
		return outplan;
	}
	public void setOutplan(OutPlan outplan) {
		this.outplan = outplan;
	}
	
	@Autowired
	private OutPlanService outPlanService ;
		
	@Action(value = "outplan",results = { @Result(name = StrutsResMSG.SUCCESS, location = "/wx/admin/seting/outplan/outplan.jsp") })
	public String outplan(){
		return StrutsResMSG.SUCCESS;
	}
	
	@Action(value = "save",results = { @Result(name = StrutsResMSG.SUCCESS, type="redirect",location = "/page/wx/personalCenter/list.action") })
	public String save(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String code = request.getParameter("code");
		String access_tokenString = WXGetTokenService.accessTokenIsOvertime();
		JSONObject userId = WeixinUtil.httpRequest(
				WXurl.WX_USERID_URL.replace("ACCESS_TOKEN", access_tokenString)
						.replace("CODE", code), "GET", null);
		outplan.setUserId(userId.getString("UserId"));
		outPlanService.save(outplan);
		return StrutsResMSG.SUCCESS;
	}
}
