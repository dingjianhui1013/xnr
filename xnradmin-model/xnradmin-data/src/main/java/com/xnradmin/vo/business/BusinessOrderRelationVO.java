package com.xnradmin.vo.business;

import com.xnradmin.po.business.BusinessGoods;
import com.xnradmin.po.business.BusinessOrderGoodsRelation;

public class BusinessOrderRelationVO {
	private BusinessOrderGoodsRelation orderGoodsRelation;
	private BusinessGoods businessGoods;
	public BusinessOrderGoodsRelation getOrderGoodsRelation() {
		return orderGoodsRelation;
	}
	public BusinessGoods getBusinessGoods() {
		return businessGoods;
	}
	public void setOrderGoodsRelation(BusinessOrderGoodsRelation orderGoodsRelation) {
		this.orderGoodsRelation = orderGoodsRelation;
	}
	public void setBusinessGoods(BusinessGoods businessGoods) {
		this.businessGoods = businessGoods;
	}
	
}
