/**
 *2014年11月14日 下午3:24:26
 */
package com.xnradmin.vo.stat;

import java.io.Serializable;

import com.cntinker.util.ReflectHelper;
import com.xnradmin.po.client.ClientUserInfo;
import com.xnradmin.po.mall.order.OrderGoodsRelation;
import com.xnradmin.po.mall.order.OrderRecord;

/**
 * @author: liubin
 *
 */
public class OrderRecordVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private OrderRecord orderRecord;

	private ClientUserInfo clientUserInfo;

	private OrderGoodsRelation orderGoodsRelation;
	
	private String createTime;

	private String userRealStartTime;

	private String userRealEndTime;

	private String paymentStartTime;

	private String paymentEndTime;

	private String operateStartTime;

	private String operateEndTime;

	private String createStartTime;

	private String createEndTime;

	private String deliveryStartStartTime;

	private String deliveryStartEndTime;

	private String deliveryEndStartTime;

	private String deliveryEndEndTime;

	public String toString() {
		String res = "";
		try {
			res = ReflectHelper.makeToString(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public OrderRecord getOrderRecord() {
		return orderRecord;
	}

	public ClientUserInfo getClientUserInfo() {
		return clientUserInfo;
	}

	public OrderGoodsRelation getOrderGoodsRelation() {
		return orderGoodsRelation;
	}

	public void setOrderRecord(OrderRecord orderRecord) {
		this.orderRecord = orderRecord;
	}

	public void setClientUserInfo(ClientUserInfo clientUserInfo) {
		this.clientUserInfo = clientUserInfo;
	}

	public void setOrderGoodsRelation(OrderGoodsRelation orderGoodsRelation) {
		this.orderGoodsRelation = orderGoodsRelation;
	}

	public String getUserRealStartTime() {
		return userRealStartTime;
	}

	public String getUserRealEndTime() {
		return userRealEndTime;
	}

	public void setUserRealStartTime(String userRealStartTime) {
		this.userRealStartTime = userRealStartTime;
	}

	public void setUserRealEndTime(String userRealEndTime) {
		this.userRealEndTime = userRealEndTime;
	}

	public String getPaymentStartTime() {
		return paymentStartTime;
	}

	public String getPaymentEndTime() {
		return paymentEndTime;
	}

	public void setPaymentStartTime(String paymentStartTime) {
		this.paymentStartTime = paymentStartTime;
	}

	public void setPaymentEndTime(String paymentEndTime) {
		this.paymentEndTime = paymentEndTime;
	}

	public String getOperateStartTime() {
		return operateStartTime;
	}

	public String getOperateEndTime() {
		return operateEndTime;
	}

	public String getCreateStartTime() {
		return createStartTime;
	}

	public String getCreateEndTime() {
		return createEndTime;
	}

	public String getDeliveryStartStartTime() {
		return deliveryStartStartTime;
	}

	public String getDeliveryStartEndTime() {
		return deliveryStartEndTime;
	}

	public String getDeliveryEndStartTime() {
		return deliveryEndStartTime;
	}

	public String getDeliveryEndEndTime() {
		return deliveryEndEndTime;
	}

	public void setOperateStartTime(String operateStartTime) {
		this.operateStartTime = operateStartTime;
	}

	public void setOperateEndTime(String operateEndTime) {
		this.operateEndTime = operateEndTime;
	}

	public void setCreateStartTime(String createStartTime) {
		this.createStartTime = createStartTime;
	}

	public void setCreateEndTime(String createEndTime) {
		this.createEndTime = createEndTime;
	}

	public void setDeliveryStartStartTime(String deliveryStartStartTime) {
		this.deliveryStartStartTime = deliveryStartStartTime;
	}

	public void setDeliveryStartEndTime(String deliveryStartEndTime) {
		this.deliveryStartEndTime = deliveryStartEndTime;
	}

	public void setDeliveryEndStartTime(String deliveryEndStartTime) {
		this.deliveryEndStartTime = deliveryEndStartTime;
	}

	public void setDeliveryEndEndTime(String deliveryEndEndTime) {
		this.deliveryEndEndTime = deliveryEndEndTime;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
}
