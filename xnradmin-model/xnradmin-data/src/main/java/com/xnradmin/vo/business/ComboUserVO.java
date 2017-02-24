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
 * @author: liubin
 *
 */
public class ComboUserVO implements java.io.Serializable, Cloneable{

	private static final long serialVersionUID = 1L;

	private Combo combo;
	
	private ComboUser comboUser;
	
	private FrontUser frontUser;
	
	private String comboStartTime;
	
	private String comboEndTime;
	
	private PseudoOrders pseudoOrders;//一条伪订单
	
	@Override
	public ComboUserVO clone() {
		ComboUserVO vo = null;
		try {
			vo = (ComboUserVO) super.clone();
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

	public PseudoOrders getPseudoOrders() {
		return pseudoOrders;
	}

	public void setPseudoOrders(PseudoOrders pseudoOrders) {
		this.pseudoOrders = pseudoOrders;
	}
	
}
