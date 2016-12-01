package com.xnradmin.vo.business;

import com.xnradmin.po.business.BusinessGoods;
import com.xnradmin.po.business.FarmerOrderRecord;
import com.xnradmin.po.wx.OutPlan;
import com.xnradmin.po.wx.connect.Farmer;

public class FarmerOrderVO {

	private FarmerOrderRecord farmerOrder;
	private OutPlan outPlan;
	private BusinessGoods businessGoods;
	
	private Farmer farmer;

	
	
	
	
	
	
	public FarmerOrderVO() {
		super();
	}

	public FarmerOrderRecord getFarmerOrder() {
		return farmerOrder;
	}

	public void setFarmerOrder(FarmerOrderRecord farmerOrder) {
		this.farmerOrder = farmerOrder;
	}

	public OutPlan getOutPlan() {
		return outPlan;
	}

	public void setOutPlan(OutPlan outPlan) {
		this.outPlan = outPlan;
	}

	public BusinessGoods getBusinessGoods() {
		return businessGoods;
	}

	public void setBusinessGoods(BusinessGoods businessGoods) {
		this.businessGoods = businessGoods;
	}

	public Farmer getFarmer() {
		return farmer;
	}

	public void setFarmer(Farmer farmer) {
		this.farmer = farmer;
	}
	
	
	
	
}
