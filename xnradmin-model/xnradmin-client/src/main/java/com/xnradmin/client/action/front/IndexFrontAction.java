package com.xnradmin.client.action.front;

import java.io.IOException;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.xnradmin.client.service.wx.FarmerService;
import com.xnradmin.constant.AjaxResult;
import com.xnradmin.constant.StrutsResMSG;
import com.xnradmin.core.service.business.commodity.BusinessGoodsService;
import com.xnradmin.po.business.BusinessCategory;
import com.xnradmin.po.business.BusinessGoods;

@Controller
@Scope("prototype")
@Namespace("/front")
@ParentPackage("json-default")
public class IndexFrontAction  {
	
	@Action(value = "index", results = { @Result(name = StrutsResMSG.SUCCESS, location = "/front/index.jsp") })
	public String info() {
		return StrutsResMSG.SUCCESS;
	}
	
}
