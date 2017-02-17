package com.xnradmin.vo;

import com.xnradmin.po.business.BusinessGoods;
import com.xnradmin.po.wx.connect.Farmer;
import com.xnradmin.po.wx.connect.FarmerQrCode;

public class FarmerQrCodeVo {
	private FarmerQrCode farmerQrCode;
	private Farmer farmer;
	private BusinessGoods businessGoods;
	public FarmerQrCode getFarmerQrCode() {
		return farmerQrCode;
	}
	public Farmer getFarmer() {
		return farmer;
	}
	public BusinessGoods getBusinessGoods() {
		return businessGoods;
	}
	public void setFarmerQrCode(FarmerQrCode farmerQrCode) {
		this.farmerQrCode = farmerQrCode;
	}
	public void setFarmer(Farmer farmer) {
		this.farmer = farmer;
	}
	public void setBusinessGoods(BusinessGoods businessGoods) {
		this.businessGoods = businessGoods;
	}

	
}
