/**
 *2014年11月25日 下午3:49:07
 */
package com.xnradmin.vo.business;

import java.util.List;

import com.cntinker.util.ReflectHelper;
import com.xnradmin.po.CommonStaff;
import com.xnradmin.po.business.BusinessCategory;
import com.xnradmin.po.business.BusinessGoods;
import com.xnradmin.po.business.BusinessOrderGoodsRelation;
import com.xnradmin.po.business.BusinessOrderRecord;
import com.xnradmin.po.client.ClientUserInfo;

/**
 * @author: liubin
 * 
 */
public class BusinessOrderVO implements java.io.Serializable, Cloneable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private BusinessGoodsVO businessGoodsVO;

	private BusinessOrderRecord businessOrderRecord;

	private BusinessOrderGoodsRelation businessOrderGoodsRelation;

	private ClientUserInfo clientUserInfo;

	private CommonStaff staff;

	private CommonStaff leaderStaff;

	private BusinessCategory businessCategory;

	private BusinessGoods businessGoods;

	private String createStartTime;

	private String createEndTime;

	private String userRealStartTime;

	private String userRealEndTime;

	private String paymentStartTime;

	private String paymentEndTime;

	private String operateStartTime;

	private String operateEndTime;

	private String deliveryStartStartTime;

	private String deliveryStartEndTime;

	private String deliveryEndStartTime;

	private String deliveryEndEndTime;

	private String requiredDeliveryStartTime; // 用户要求送达时间

	private String requiredDeliveryEndTime; // 用户要求送达时间

	private String finalDeliveryStartTime; // 最终送达时间

	private String finalDeliveryEndTime; // 最终送达时间

	private String staffCreateStartTime; // 用户注册时间

	private String staffCreateEndTime; // 用户注册时间

	private List<BusinessOrderGoodsRelation> businessOrderGoodsRelationList;

	private List<BusinessGoods> businessGoodsList;

	private String[] queryCateList;

	private String groupBy;

	private String orderBy;

	private String orderByField;

	private String[] orderDesc;

	private String totalPrice;

	private String productName;

	private String orderSumPrice;

	private Integer orderCount;
	
	private Integer serno;
	
	@Override
	public BusinessOrderVO clone() {
		BusinessOrderVO vo = null;
		try {
			vo = (BusinessOrderVO) super.clone();
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

	public BusinessGoodsVO getBusinessGoodsVO() {
		return businessGoodsVO;
	}

	public void setBusinessGoodsVO(BusinessGoodsVO businessGoodsVO) {
		this.businessGoodsVO = businessGoodsVO;
	}

	public BusinessOrderRecord getBusinessOrderRecord() {
		return businessOrderRecord;
	}

	public void setBusinessOrderRecord(BusinessOrderRecord businessOrderRecord) {
		this.businessOrderRecord = businessOrderRecord;
	}

	public BusinessOrderGoodsRelation getBusinessOrderGoodsRelation() {
		return businessOrderGoodsRelation;
	}

	public void setBusinessOrderGoodsRelation(
			BusinessOrderGoodsRelation businessOrderGoodsRelation) {
		this.businessOrderGoodsRelation = businessOrderGoodsRelation;
	}

	public ClientUserInfo getClientUserInfo() {
		return clientUserInfo;
	}

	public void setClientUserInfo(ClientUserInfo clientUserInfo) {
		this.clientUserInfo = clientUserInfo;
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

	public String getUserRealStartTime() {
		return userRealStartTime;
	}

	public void setUserRealStartTime(String userRealStartTime) {
		this.userRealStartTime = userRealStartTime;
	}

	public String getUserRealEndTime() {
		return userRealEndTime;
	}

	public void setUserRealEndTime(String userRealEndTime) {
		this.userRealEndTime = userRealEndTime;
	}

	public String getPaymentStartTime() {
		return paymentStartTime;
	}

	public void setPaymentStartTime(String paymentStartTime) {
		this.paymentStartTime = paymentStartTime;
	}

	public String getPaymentEndTime() {
		return paymentEndTime;
	}

	public void setPaymentEndTime(String paymentEndTime) {
		this.paymentEndTime = paymentEndTime;
	}

	public String getOperateStartTime() {
		return operateStartTime;
	}

	public void setOperateStartTime(String operateStartTime) {
		this.operateStartTime = operateStartTime;
	}

	public String getOperateEndTime() {
		return operateEndTime;
	}

	public void setOperateEndTime(String operateEndTime) {
		this.operateEndTime = operateEndTime;
	}

	public String getDeliveryStartStartTime() {
		return deliveryStartStartTime;
	}

	public void setDeliveryStartStartTime(String deliveryStartStartTime) {
		this.deliveryStartStartTime = deliveryStartStartTime;
	}

	public String getDeliveryStartEndTime() {
		return deliveryStartEndTime;
	}

	public void setDeliveryStartEndTime(String deliveryStartEndTime) {
		this.deliveryStartEndTime = deliveryStartEndTime;
	}

	public String getDeliveryEndStartTime() {
		return deliveryEndStartTime;
	}

	public void setDeliveryEndStartTime(String deliveryEndStartTime) {
		this.deliveryEndStartTime = deliveryEndStartTime;
	}

	public String getDeliveryEndEndTime() {
		return deliveryEndEndTime;
	}

	public void setDeliveryEndEndTime(String deliveryEndEndTime) {
		this.deliveryEndEndTime = deliveryEndEndTime;
	}

	public List<BusinessOrderGoodsRelation> getBusinessOrderGoodsRelationList() {
		return businessOrderGoodsRelationList;
	}

	public void setBusinessOrderGoodsRelationList(
			List<BusinessOrderGoodsRelation> businessOrderGoodsRelationList) {
		this.businessOrderGoodsRelationList = businessOrderGoodsRelationList;
	}

	public List<BusinessGoods> getBusinessGoodsList() {
		return businessGoodsList;
	}

	public void setBusinessGoodsList(List<BusinessGoods> businessGoodsList) {
		this.businessGoodsList = businessGoodsList;
	}

	public String getRequiredDeliveryStartTime() {
		return requiredDeliveryStartTime;
	}

	public void setRequiredDeliveryStartTime(String requiredDeliveryStartTime) {
		this.requiredDeliveryStartTime = requiredDeliveryStartTime;
	}

	public String getRequiredDeliveryEndTime() {
		return requiredDeliveryEndTime;
	}

	public void setRequiredDeliveryEndTime(String requiredDeliveryEndTime) {
		this.requiredDeliveryEndTime = requiredDeliveryEndTime;
	}

	public String getFinalDeliveryStartTime() {
		return finalDeliveryStartTime;
	}

	public void setFinalDeliveryStartTime(String finalDeliveryStartTime) {
		this.finalDeliveryStartTime = finalDeliveryStartTime;
	}

	public String getFinalDeliveryEndTime() {
		return finalDeliveryEndTime;
	}

	public void setFinalDeliveryEndTime(String finalDeliveryEndTime) {
		this.finalDeliveryEndTime = finalDeliveryEndTime;
	}

	public CommonStaff getStaff() {
		return staff;
	}

	public void setStaff(CommonStaff staff) {
		this.staff = staff;
	}

	public CommonStaff getLeaderStaff() {
		return leaderStaff;
	}

	public void setLeaderStaff(CommonStaff leaderStaff) {
		this.leaderStaff = leaderStaff;
	}

	public BusinessCategory getBusinessCategory() {
		return businessCategory;
	}

	public void setBusinessCategory(BusinessCategory businessCategory) {
		this.businessCategory = businessCategory;
	}

	public BusinessGoods getBusinessGoods() {
		return businessGoods;
	}

	public void setBusinessGoods(BusinessGoods businessGoods) {
		this.businessGoods = businessGoods;
	}

	public String[] getQueryCateList() {
		return queryCateList;
	}

	public void setQueryCateList(String[] queryCateList) {
		this.queryCateList = queryCateList;
	}

	public String getGroupBy() {
		return groupBy;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public String getOrderByField() {
		return orderByField;
	}

	public void setGroupBy(String groupBy) {
		this.groupBy = groupBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public void setOrderByField(String orderByField) {
		this.orderByField = orderByField;
	}

	public String getStaffCreateStartTime() {
		return staffCreateStartTime;
	}

	public void setStaffCreateStartTime(String staffCreateStartTime) {
		this.staffCreateStartTime = staffCreateStartTime;
	}

	public String getStaffCreateEndTime() {
		return staffCreateEndTime;
	}

	public void setStaffCreateEndTime(String staffCreateEndTime) {
		this.staffCreateEndTime = staffCreateEndTime;
	}

	public String[] getOrderDesc() {
		return orderDesc;
	}

	public void setOrderDesc(String[] orderDesc) {
		this.orderDesc = orderDesc;
	}

	public String getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getOrderSumPrice() {
		return orderSumPrice;
	}

	public void setOrderSumPrice(String orderSumPrice) {
		this.orderSumPrice = orderSumPrice;
	}

	public Integer getOrderCount() {
		return orderCount;
	}

	public void setOrderCount(Integer orderCount) {
		this.orderCount = orderCount;
	}

	public Integer getSerno() {
		return serno;
	}

	public void setSerno(Integer serno) {
		this.serno = serno;
	}

}
