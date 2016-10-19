/**
 *2014年11月25日 下午3:44:55
*/
package com.xnradmin.vo.business;

import java.util.List;

import com.cntinker.util.ReflectHelper;
import com.xnradmin.po.business.BusinessCategory;
import com.xnradmin.po.business.BusinessGoods;
import com.xnradmin.po.business.BusinessUserFavorite;
import com.xnradmin.po.business.BusinessWeight;

/**
 * @author: liubin
 *
 */
public class BusinessGoodsVO implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private BusinessGoods businessGoods;
	
	private BusinessCategory businessCategory;
	
	private BusinessCategory businessParentCategory;
	
	private BusinessWeight businessWeight;
	
	private BusinessUserFavorite businessUserFavorite;
	
	private String createStartTime;
	
	private String createEndTime;
	
	private String modifyStartTime;
	
	private String modifyEndTime;
	
	private String CategoryGoodsCount;
	
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

	public BusinessWeight getBusinessWeight() {
		return businessWeight;
	}

	public void setBusinessWeight(BusinessWeight businessWeight) {
		this.businessWeight = businessWeight;
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

	public String getCategoryGoodsCount() {
		return CategoryGoodsCount;
	}

	public void setCategoryGoodsCount(String categoryGoodsCount) {
		CategoryGoodsCount = categoryGoodsCount;
	}

	public BusinessCategory getBusinessParentCategory() {
		return businessParentCategory;
	}

	public void setBusinessParentCategory(BusinessCategory businessParentCategory) {
		this.businessParentCategory = businessParentCategory;
	}

	public BusinessUserFavorite getBusinessUserFavorite() {
		return businessUserFavorite;
	}

	public void setBusinessUserFavorite(BusinessUserFavorite businessUserFavorite) {
		this.businessUserFavorite = businessUserFavorite;
	}
	
}
