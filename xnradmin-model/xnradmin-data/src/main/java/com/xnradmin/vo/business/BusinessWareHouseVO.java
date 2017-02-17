package com.xnradmin.vo.business;

import com.cntinker.util.ReflectHelper;
import com.xnradmin.po.business.BusinessCategory;
import com.xnradmin.po.business.BusinessGoods;
import com.xnradmin.po.business.BusinessWeight;
import com.xnradmin.po.business.wareHouse.BusinessWareHouse;
import com.xnradmin.po.business.wareHouse.BusinessWareHouseInvRel;
import com.xnradmin.po.business.wareHouse.BusinessWareHouseOperate;

public class BusinessWareHouseVO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private BusinessGoods businessGoods;
	
	private BusinessCategory businessCategory;
	
	private BusinessCategory businessParentCategory;
	
	private BusinessWeight businessWeight;
	
	private BusinessWareHouse businessWareHouse;
	
	private BusinessWareHouseInvRel businessWareHouseInvRel;
	
	private BusinessWareHouseOperate businessWareHouseOperate;
	
	private String createStartTime;
	
	private String createEndTime;
	
	private String modifyStartTime;
	
	private String modifyEndTime;
	
	public String toString() {
		String res = "";
		try {
			res = ReflectHelper.makeToString(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public BusinessGoods getBusinessGoods() {
		return businessGoods;
	}

	public void setBusinessGoods(BusinessGoods businessGoods) {
		this.businessGoods = businessGoods;
	}

	public BusinessCategory getBusinessCategory() {
		return businessCategory;
	}

	public void setBusinessCategory(BusinessCategory businessCategory) {
		this.businessCategory = businessCategory;
	}

	public BusinessCategory getBusinessParentCategory() {
		return businessParentCategory;
	}

	public void setBusinessParentCategory(BusinessCategory businessParentCategory) {
		this.businessParentCategory = businessParentCategory;
	}

	public BusinessWeight getBusinessWeight() {
		return businessWeight;
	}

	public void setBusinessWeight(BusinessWeight businessWeight) {
		this.businessWeight = businessWeight;
	}

	public BusinessWareHouse getBusinessWareHouse() {
		return businessWareHouse;
	}

	public void setBusinessWareHouse(BusinessWareHouse businessWareHouse) {
		this.businessWareHouse = businessWareHouse;
	}

	public String getCreateStartTime() {
		return createStartTime;
	}

	public void setCreateStartTime(String createStartTime) {
		this.createStartTime = createStartTime;
	}

	public String getCreateEndTime() {
		return createEndTime;
	}

	public void setCreateEndTime(String createEndTime) {
		this.createEndTime = createEndTime;
	}

	public String getModifyStartTime() {
		return modifyStartTime;
	}

	public void setModifyStartTime(String modifyStartTime) {
		this.modifyStartTime = modifyStartTime;
	}

	public String getModifyEndTime() {
		return modifyEndTime;
	}

	public void setModifyEndTime(String modifyEndTime) {
		this.modifyEndTime = modifyEndTime;
	}

	public BusinessWareHouseInvRel getBusinessWareHouseInvRel() {
		return businessWareHouseInvRel;
	}

	public void setBusinessWareHouseInvRel(
			BusinessWareHouseInvRel businessWareHouseInvRel) {
		this.businessWareHouseInvRel = businessWareHouseInvRel;
	}

	public BusinessWareHouseOperate getBusinessWareHouseOperate() {
		return businessWareHouseOperate;
	}

	public void setBusinessWareHouseOperate(
			BusinessWareHouseOperate businessWareHouseOperate) {
		this.businessWareHouseOperate = businessWareHouseOperate;
	}

}
