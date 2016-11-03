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

import com.xnradmin.client.service.wx.OutPlanService;
import com.xnradmin.client.service.wx.WXGetTokenService;
import com.xnradmin.client.service.wx.WeixinUtil;
import com.xnradmin.constant.StrutsResMSG;
import com.xnradmin.po.wx.OutPlan;
import com.xnradmin.po.wx.connect.WXurl;

@Controller
@Scope("prototype")
@Namespace("/page/wx/outplan")
@ParentPackage("json-default")
public class OutPlanAction {
	
	private OutPlan outplan ;
	private String deleteId;
	private String eidtId;
	
	public String getEidtId() {
		return eidtId;
	}
	public void setEidtId(String eidtId) {
		this.eidtId = eidtId;
	}
	public OutPlan getOutplan() {
		return outplan;
	}
	public void setOutplan(OutPlan outplan) {
		this.outplan = outplan;
	}
	private String userId;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getDeleteId() {
		return deleteId;
	}
	public void setDeleteId(String deleteId) {
		this.deleteId = deleteId;
	}
	@Autowired
	private OutPlanService outPlanService ;
		
	@Action(value = "outplan",results = { @Result(name = StrutsResMSG.SUCCESS, location = "/wx/admin/seting/outplan/outplan.jsp") })
	public String outplan(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String code = request.getParameter("code");
		String access_tokenString = WXGetTokenService.accessTokenIsOvertime();
		JSONObject userId = WeixinUtil.httpRequest(
				WXurl.WX_USERID_URL.replace("ACCESS_TOKEN", access_tokenString)
						.replace("CODE", code), "GET", null);
		this.userId = userId.getString("UserId");
		return StrutsResMSG.SUCCESS;
	}
	
	@Action(value = "save",results = { @Result(name = StrutsResMSG.SUCCESS, type="redirect",location = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx9eb4133bf836c7ae&redirect_uri=http%3a%2f%2fweixin.robustsoft.cn%2fxnr%2fpage%2fwx%2fpersonalCenter%2flist.action&response_type=code&scope=SCOPE&state=STATE#wechat_redirect") })
	public String save(){
		outPlanService.save(outplan);
		return StrutsResMSG.SUCCESS;
	}
	@Action(value = "deletePlan",results = { @Result(name = StrutsResMSG.SUCCESS, type="redirect",location = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx9eb4133bf836c7ae&redirect_uri=http%3a%2f%2fweixin.robustsoft.cn%2fxnr%2fpage%2fwx%2fpersonalCenter%2flist.action&response_type=code&scope=SCOPE&state=STATE#wechat_redirect") })
	public String delete(){
		outPlanService.delete(deleteId);
		return StrutsResMSG.SUCCESS;
	}
	@Action(value = "editPlanForm",results = { @Result(name = StrutsResMSG.SUCCESS, type="redirect",location ="/wx/admin/seting/outplan/planEdit.jsp" ) })
	public String eidtForm(){
		OutPlan outPlan = outPlanService.findById(eidtId);
		ServletActionContext.getRequest().setAttribute("outPlan",  outPlan);
		return StrutsResMSG.SUCCESS;
	}
	@Action(value = "saveEdit",results = { @Result(name = StrutsResMSG.SUCCESS, type="redirect",location ="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx9eb4133bf836c7ae&redirect_uri=http%3a%2f%2fweixin.robustsoft.cn%2fxnr%2fpage%2fwx%2fpersonalCenter%2flist.action&response_type=code&scope=SCOPE&state=STATE#wechat_redirect" ) })
	public String saveEdit(){
		outPlanService.saveEdit(outplan);
		return StrutsResMSG.SUCCESS;
	}
}
