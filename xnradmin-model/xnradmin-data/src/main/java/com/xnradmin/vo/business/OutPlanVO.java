package com.xnradmin.vo.business;

import com.xnradmin.po.business.BusinessCategory;
import com.xnradmin.po.business.BusinessGoods;
import com.xnradmin.po.business.BusinessWeight;
import com.xnradmin.po.wx.OutPlan;
import com.xnradmin.po.wx.connect.Farmer;

public class OutPlanVO {
	private OutPlan outPlan;
	private Farmer farmer;
	private BusinessGoods businessGoods;
	private BusinessWeight businessWeight;
//	private BusinessCategory businessCategory;
	
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
	public BusinessGoods getBusinessGoods() {
		return businessGoods;
	}
	public void setBusinessGoods(BusinessGoods businessGood) {
		this.businessGoods = businessGood;
	}
	public BusinessWeight getBusinessWeight() {
		return businessWeight;
	}
	public void setBusinessWeight(BusinessWeight businessWeight) {
		this.businessWeight = businessWeight;
	}
//	public BusinessCategory getBusinessCategory() {
//		return businessCategory;
//	}
//	public void setBusinessCategory(BusinessCategory businessCategory) {
//		this.businessCategory = businessCategory;
//	}
	
} 
