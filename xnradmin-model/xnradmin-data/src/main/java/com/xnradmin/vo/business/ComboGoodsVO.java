/**
 *2014年11月25日 下午3:44:55
*/
package com.xnradmin.vo.business;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;

import com.cntinker.util.ReflectHelper;
import com.xnradmin.po.business.BusinessCategory;
import com.xnradmin.po.business.BusinessGoods;
import com.xnradmin.po.business.BusinessUserFavorite;
import com.xnradmin.po.business.BusinessWeight;
import com.xnradmin.po.business.Combo;
import com.xnradmin.po.business.ComboGoods;
import com.xnradmin.po.business.ComboPlan;
import com.xnradmin.po.business.ComboUser;

/**
 * @author: liubin
 *
 */
public class ComboGoodsVO implements java.io.Serializable{

	private static final long serialVersionUID = 1L;

	private BusinessGoods businessGoods;
	
	private ComboGoods comboGoods;
	
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

	public ComboGoods getComboGoods() {
		return comboGoods;
	}

	public void setComboGoods(ComboGoods comboGoods) {
		this.comboGoods = comboGoods;
	}

}
