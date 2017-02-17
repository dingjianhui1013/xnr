package com.xnradmin.vo.business;

import com.xnradmin.po.business.BusinessGoods;
import com.xnradmin.po.wx.connect.Farmer;
import com.xnradmin.po.wx.connect.FarmerImage;

public class FarmerImageVO {

	private Farmer farmer;
	private FarmerImage farmerImage;
	private BusinessGoods businessGoods;
	public FarmerImageVO() {
		super();
	}

	public Farmer getFarmer() {
		return farmer;
	}

	public void setFarmer(Farmer farmer) {
		this.farmer = farmer;
	}

	public FarmerImage getFarmerImage() {
		return farmerImage;
	}

	public void setFarmerImage(FarmerImage farmerImage) {
		this.farmerImage = farmerImage;
	}

	public BusinessGoods getBusinessGoods() {
		return businessGoods;
	}

	public void setBusinessGoods(BusinessGoods businessGoods) {
		this.businessGoods = businessGoods;
	}
	
}
