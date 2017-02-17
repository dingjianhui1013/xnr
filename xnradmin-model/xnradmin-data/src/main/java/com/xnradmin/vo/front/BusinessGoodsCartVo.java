package com.xnradmin.vo.front;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import com.xnradmin.po.business.BusinessGoods;
import com.xnradmin.po.mall.order.ShoppingCart;

public class BusinessGoodsCartVo {

	
	private ShoppingCart cart;
	
	private BusinessGoods goods;
	
	
	
	
	

	public ShoppingCart getCart() {
		return cart;
	}

	public void setCart(ShoppingCart cart) {
		this.cart = cart;
	}

	public BusinessGoods getGoods() {
		return goods;
	}

	public void setGoods(BusinessGoods goods) {
		DecimalFormat df = new DecimalFormat("#.00");
		goods.setGoodsOriginalPriceStr(df.format(goods.getGoodsOriginalPrice()));
		BigDecimal   b  =   new  BigDecimal(goods.getGoodsOriginalPrice());
		goods.setGoodsOriginalPrice(b.setScale(3,  BigDecimal.ROUND_HALF_UP).floatValue());
		this.goods = goods;
	}
	
	
	
	
	
	
	
	
}
