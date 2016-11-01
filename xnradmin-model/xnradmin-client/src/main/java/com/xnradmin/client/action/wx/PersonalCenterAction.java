package com.xnradmin.client.action.wx;

import java.util.List;

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
import com.xnradmin.client.service.wx.FarmerImageService;
import com.xnradmin.client.service.wx.OutPlanService;
import com.xnradmin.client.service.wx.WXGetTokenService;
import com.xnradmin.constant.StrutsResMSG;
import com.xnradmin.po.wx.OutPlan;
import com.xnradmin.po.wx.connect.FarmerImage;
import com.xnradmin.po.wx.connect.WXurl;

@Controller
@Scope("prototype")
@Namespace("/page/wx/personalCenter")
@ParentPackage("json-default")
public class PersonalCenterAction {
	
	@Autowired
	private OutPlanService outPlanService ;
	@Autowired
	private FarmerImageService farmerImageService ;
	
	@Action(value = "list",results = { @Result(name = StrutsResMSG.SUCCESS, location = "/wx/admin/seting/personalCenter/personalCenter.jsp") })
	public String personalCenter(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String code = request.getParameter("code");
		String access_tokenString = WXGetTokenService.accessTokenIsOvertime();
		JSONObject userId = WeixinUtil.httpRequest(
				WXurl.WX_USERID_URL.replace("ACCESS_TOKEN", access_tokenString)
						.replace("CODE", code), "GET", null);
		
		List<OutPlan> outplans = outPlanService.findAll(userId.getString("UserId"));
		List<FarmerImage> farmerImages = farmerImageService.findAll(userId.getString("UserId"));
		List<FarmerImage> imageTypes = farmerImageService.getImageType(userId.getString("UserId"));
		
		ServletActionContext.getRequest().setAttribute("outplans", outplans);
		ServletActionContext.getRequest().setAttribute("farmerImages", farmerImages);
		ServletActionContext.getRequest().setAttribute("imageTypes", imageTypes);
		return StrutsResMSG.SUCCESS;
	}
}
