package com.xnradmin.vo.business;

import com.xnradmin.po.business.BusinessCategory;
import com.xnradmin.po.business.BusinessGoods;
import com.xnradmin.po.business.BusinessWeight;
import com.xnradmin.po.wx.OutPlan;
import com.xnradmin.po.wx.connect.Farmer;

public class OutPlanVO {
	private OutPlan outPlan;
	private Farmer farmer;
	private BusinessGoods businessGood;
	private BusinessWeight businessWeight;
	private BusinessCategory businessCategory;
	
	public OutPlan getOutPlan() {
		return outPlan;
	}
	public void setOutPlan(OutPlan outPlan) {
		this.outPlan = outPlan;
	}
	public Farmer getFarmer() {
		return farmer;
	}
	public void setFarmer(Farmer farmer) {
		this.farmer = farmer;
	}
	public BusinessGoods getBusinessGood() {
		return businessGood;
	}
	public void setBusinessGood(BusinessGoods businessGood) {
		this.businessGood = businessGood;
	}
	public BusinessWeight getBusinessWeight() {
		return businessWeight;
	}
	public void setBusinessWeight(BusinessWeight businessWeight) {
		this.businessWeight = businessWeight;
	}
	public BusinessCategory getBusinessCategory() {
		return businessCategory;
	}
	public void setBusinessCategory(BusinessCategory businessCategory) {
		this.businessCategory = businessCategory;
	}
	
} 
