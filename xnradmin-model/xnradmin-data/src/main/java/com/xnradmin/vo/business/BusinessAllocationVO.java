package com.xnradmin.vo.business;

import com.xnradmin.po.business.BusinessGoods;
import com.xnradmin.po.business.BusinessOrderGoodsRelation;
import com.xnradmin.po.business.BusinessOrderRecord;

public class BusinessAllocationVO {
	private BusinessOrderRecord businessOrderRecord;
	private BusinessGoods businessGoods;
	private BusinessOrderGoodsRelation businessOrderGoodsRelation;
	private Integer businessGoodsCount;//同一个商品的统计信息
	private String businessOrder;//该分配的产品包含的订单号
	
	public Integer getBusinessGoodsCount() {
		return businessGoodsCount;
	}

	public void setBusinessGoodsCount(Integer businessGoodsCount) {
		this.businessGoodsCount = businessGoodsCount;
	}

	public BusinessOrderRecord getBusinessOrderRecord() {
		return businessOrderRecord;
	}

	public void setBusinessOrderRecord(BusinessOrderRecord businessOrderRecord) {
		this.businessOrderRecord = businessOrderRecord;
	}

	public BusinessGoods getBusinessGoods() {
		return businessGoods;
	}
	
	public void setBusinessGoods(BusinessGoods businessGoods) {
		this.businessGoods = businessGoods;
	}
	public BusinessOrderGoodsRelation getBusinessOrderGoodsRelation() {
		return businessOrderGoodsRelation;
	}
	public void setBusinessOrderGoodsRelation(
			BusinessOrderGoodsRelation businessOrderGoodsRelation) {
		this.businessOrderGoodsRelation = businessOrderGoodsRelation;
	}

	public String getBusinessOrder() {
		return businessOrder;
	}

	public void setBusinessOrder(String businessOrder) {
		this.businessOrder = businessOrder;
	}
	
}
