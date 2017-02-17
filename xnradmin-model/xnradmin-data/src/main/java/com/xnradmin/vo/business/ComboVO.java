/**
 *2014年11月25日 下午3:44:55
*/
package com.xnradmin.vo.business;

import java.util.List;

import com.cntinker.util.ReflectHelper;
import com.xnradmin.po.business.Combo;
import com.xnradmin.po.business.ComboUser;
import com.xnradmin.po.common.status.Status;
import com.xnradmin.po.mall.order.ShoppingCart;

/**
 * @author: liubin
 *
 */
public class ComboVO implements java.io.Serializable{

	private static final long serialVersionUID = 1L;

	private Combo combo;
	
	private List<ComboGoodsVO> comboGoodsList;
	
	private List<ComboPlanVO> comboPlanList;
	
	private ComboUser comboUser;
	
	private Status status;
	
	private ShoppingCart shoppingCart;
	public String toString() {
		String res = "";
		try {
			res = ReflectHelper.makeToString(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public Combo getCombo() {
		return combo;
	}

	public void setCombo(Combo combo) {
		this.combo = combo;
	}

	public List<ComboGoodsVO> getComboGoodsList() {
		return comboGoodsList;
	}

	public void setComboGoodsList(List<ComboGoodsVO> comboGoodsList) {
		this.comboGoodsList = comboGoodsList;
	}

	public List<ComboPlanVO> getComboPlanList() {
		return comboPlanList;
	}

	public void setComboPlanList(List<ComboPlanVO> comboPlanList) {
		this.comboPlanList = comboPlanList;
	}

	public ComboUser getComboUser() {
		return comboUser;
	}

	public void setComboUser(ComboUser comboUser) {
		this.comboUser = comboUser;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public ShoppingCart getShoppingCart() {
		return shoppingCart;
	}

	public void setShoppingCart(ShoppingCart shoppingCart) {
		this.shoppingCart = shoppingCart;
	}
}
