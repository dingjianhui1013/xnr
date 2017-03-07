/**
 *2014年11月25日 下午3:44:55
*/
package com.xnradmin.vo.business;

import com.cntinker.util.ReflectHelper;
import com.xnradmin.po.business.Combo;
import com.xnradmin.po.business.ComboUser;
import com.xnradmin.po.business.PseudoOrders;
import com.xnradmin.po.front.FrontUser;

/**
 * @author: hanwei
 *
 */
public class ComboUserGoodsVO implements java.io.Serializable, Cloneable{

	private static final long serialVersionUID = 1L;

	private ComboUser comboUser;
	
	private ComboGoodsVO comboGoodsVO;
	
	//已分配数
	private Integer hasAllocateNumber;
	//已分配价格
	private Float hasAllocatePrice;
	
	@Override
	public ComboUserGoodsVO clone() {
		ComboUserGoodsVO vo = null;
		try {
			vo = (ComboUserGoodsVO) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return vo;
	}

	
	public String toString() {
		String res = "";
		try {
			res = ReflectHelper.makeToString(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public ComboUser getComboUser() {
		return comboUser;
	}


	public void setComboUser(ComboUser comboUser) {
		this.comboUser = comboUser;
	}


	public ComboGoodsVO getComboGoodsVO() {
		return comboGoodsVO;
	}


	public void setComboGoodsVO(ComboGoodsVO comboGoodsVO) {
		this.comboGoodsVO = comboGoodsVO;
	}


	public Integer getHasAllocateNumber() {
		return hasAllocateNumber;
	}


	public void setHasAllocateNumber(Integer hasAllocateNumber) {
		this.hasAllocateNumber = hasAllocateNumber;
	}


	public Float getHasAllocatePrice() {
		return hasAllocatePrice;
	}


	public void setHasAllocatePrice(Float hasAllocatePrice) {
		this.hasAllocatePrice = hasAllocatePrice;
	}
	
}
