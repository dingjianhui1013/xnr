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
import com.xnradmin.po.front.FrontUser;

/**
 * @author: liubin
 *
 */
public class ComboUserVO implements java.io.Serializable{

	private static final long serialVersionUID = 1L;

	private Combo combo;
	
	private ComboUser comboUser;
	
	private FrontUser frontUser;
	
	private String comboStartTime;
	
	private String comboEndTime;
	
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

	public ComboUser getComboUser() {
		return comboUser;
	}

	public void setComboUser(ComboUser comboUser) {
		this.comboUser = comboUser;
	}

	public FrontUser getFrontUser() {
		return frontUser;
	}

	public void setFrontUser(FrontUser frontUser) {
		this.frontUser = frontUser;
	}

	public String getComboStartTime() {
		return comboStartTime;
	}

	public void setComboStartTime(String comboStartTime) {
		this.comboStartTime = comboStartTime;
	}

	public String getComboEndTime() {
		return comboEndTime;
	}

	public void setComboEndTime(String comboEndTime) {
		this.comboEndTime = comboEndTime;
	}

}
