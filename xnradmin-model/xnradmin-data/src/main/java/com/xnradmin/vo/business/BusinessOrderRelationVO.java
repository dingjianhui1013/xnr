package com.xnradmin.vo.business;

import com.xnradmin.po.business.BusinessGoods;
import com.xnradmin.po.business.BusinessOrderGoodsRelation;
import com.xnradmin.po.business.Combo;

public class BusinessOrderRelationVO {
	private BusinessOrderGoodsRelation orderGoodsRelation;
	private BusinessGoods businessGoods;
	private Combo combo;
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
	public Combo getCombo() {
		return combo;
	}
	public void setCombo(Combo combo) {
		this.combo = combo;
	}
	
}
