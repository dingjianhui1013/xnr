/**
 *2014年11月25日 下午3:49:07
 */
package com.xnradmin.vo.business;

import java.util.List;

import com.cntinker.util.ReflectHelper;
import com.xnradmin.po.CommonStaff;
import com.xnradmin.po.business.AllocationData;
import com.xnradmin.po.business.BusinessCategory;
import com.xnradmin.po.business.BusinessGoods;
import com.xnradmin.po.business.BusinessOrderGoodsRelation;
import com.xnradmin.po.business.BusinessOrderRecord;
import com.xnradmin.po.client.ClientUserInfo;

/**
 * @author: liubin
 * 
 */
public class AllocationVO implements java.io.Serializable, Cloneable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private CommonStaff staff;
	
	private AllocationData allocationData;

	private String createStartTime;

	private String createEndTime;

	private List<BusinessOrderGoodsRelation> businessOrderGoodsRelationList;
	
	private String groupBy;

	private String orderBy;
	
	public String getOrderByField() {
		return orderByField;
	}

	public void setOrderByField(String orderByField) {
		this.orderByField = orderByField;
	}

	public String[] getOrderDesc() {
		return orderDesc;
	}

	public void setOrderDesc(String[] orderDesc) {
		this.orderDesc = orderDesc;
	}

	private String orderByField;

	private String[] orderDesc;
	
	@Override
	public AllocationVO clone() {
		AllocationVO vo = null;
		try {
			vo = (AllocationVO) super.clone();
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

	public List<BusinessOrderGoodsRelation> getBusinessOrderGoodsRelationList() {
		return businessOrderGoodsRelationList;
	}

	public void setBusinessOrderGoodsRelationList(
			List<BusinessOrderGoodsRelation> businessOrderGoodsRelationList) {
		this.businessOrderGoodsRelationList = businessOrderGoodsRelationList;
	}

	public CommonStaff getStaff() {
		return staff;
	}

	public void setStaff(CommonStaff staff) {
		this.staff = staff;
	}

	public String getGroupBy() {
		return groupBy;
	}

	public void setGroupBy(String groupBy) {
		this.groupBy = groupBy;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public AllocationData getAllocationData() {
		return allocationData;
	}

	public void setAllocationData(AllocationData allocationData) {
		this.allocationData = allocationData;
	}

}
