package com.xnradmin.client.action.farmers;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.xnradmin.client.service.wx.FarmerService;
import com.xnradmin.constant.StrutsResMSG;
import com.xnradmin.core.action.ParentAction;
import com.xnradmin.po.wx.WXMenu;
import com.xnradmin.po.wx.connect.Farmer;

@Controller
@Scope("prototype")
@Namespace("/page/wx/farmer")
@ParentPackage("json-default")
public class farmerAction extends ParentAction{
	
	private Farmer query;
	private List<Farmer> voList;
	private List<Farmer> farmerList;
	private Farmer farmer;
	@Autowired
	private FarmerService farmerService;
	
	public Farmer getQuery() {
		return query;
	}


	public void setQuery(Farmer query) {
		this.query = query;
	}


	public List<Farmer> getVoList() {
		return voList;
	}


	public void setVoList(List<Farmer> voList) {
		this.voList = voList;
	}


	public List<Farmer> getFarmerList() {
		return farmerList;
	}


	public void setFarmerList(List<Farmer> farmerList) {
		this.farmerList = farmerList;
	}


	public Farmer getFarmer() {
		return farmer;
	}


	public void setFarmer(Farmer farmer) {
		this.farmer = farmer;
	}


	@Override
	public boolean isPublic() {
		return false;
	}
	
	@Action(value = "info", results = { @Result(name = StrutsResMSG.SUCCESS, location = "/business/admin/farmer/info.jsp") })
	public String info() {
//		setPageInfo();
		return StrutsResMSG.SUCCESS;
	}
	
	private void setPageInfo() {
		this.voList = this.farmerService.getList(query, super.getPageNum(),
				super.getNumPerPage());
//		super.totalCount = this.farmerService.getCount(query);
	}
}
