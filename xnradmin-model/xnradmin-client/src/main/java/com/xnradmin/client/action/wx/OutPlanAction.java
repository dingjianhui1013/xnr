package com.xnradmin.client.action.wx;


import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.xnradmin.client.service.wx.OutPlanService;
import com.xnradmin.constant.StrutsResMSG;
import com.xnradmin.po.wx.OutPlan;

@Controller
@Scope("prototype")
@Namespace("/page/wx/outplan")
@ParentPackage("json-default")
public class OutPlanAction {
	
	private OutPlan outplan = new OutPlan();
	
	public OutPlan getOutplan() {
		return outplan;
	}
	public void setOutplan(OutPlan outplan) {
		this.outplan = outplan;
	}
	
	//	@Autowired
	private OutPlanService outPlanService = new OutPlanService();
	@Action(value = "outplan",results = { @Result(name = StrutsResMSG.SUCCESS, location = "/wx/admin/seting/outplan/outplan.jsp") })
	public String outplan(){
		return StrutsResMSG.SUCCESS;
	}
	@Action(value = "list",results = { @Result(name = StrutsResMSG.SUCCESS, location = "/wx/admin/seting/outplan/outplanList.jsp") })
	public String outplanlist(){
		return StrutsResMSG.SUCCESS;
	}
	@Action(value = "save")//,results = { @Result(name = StrutsResMSG.SUCCESS, location = "/wx/admin/seting/outplan/list.action") }
	public String save(){
		System.out.println(outplan.getOutput());
		outPlanService.save(outplan);
		return StrutsResMSG.SUCCESS;
	}
}
