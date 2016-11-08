/**
 *2016年11月8日 下午1:59:34
 */
package com.xnradmin.po.inv;

import static javax.persistence.GenerationType.IDENTITY;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.cntinker.util.ReflectHelper;

/**
 * 
 * 出库表
 * 
 * @author: liubin
 *
 */
@Entity
@Table(name = "inv_out")
public class InvOut implements java.io.Serializable {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;

	private Integer goodsId;// 商品id
	private String goodsName;// 商品名称
	private Integer storeId;// 仓库ID
	private String storeName;// 仓库名称
	private Double outNum; // 出库数量
	private Double outPrice;// 出库价格
	private String logisticsStaffName; // 物流人员姓名
	private String logisticsStaffMobile; // 物流人员电话
	private String logisticsCarNo; // 物流车辆车牌号
	private Timestamp outTime; // 出库时间

	private Integer invInputId;// 入库表ID
	/**
	 * 供货商表的ID,这里对应的是com.xnradmin.po.wx.connect.Farmer表
	 */
	private Integer proviedId;
	private String proviedName;// 供货商名称
	@Column(name = "ack_err_code", columnDefinition = "longtext", length = 4096)
	private String interfaceAck; // 出库接口响应
	private String interfaceAckStatus; // 返回状态码
	private String interfaceAckStatusDesc; // 返回状态描述

	private String orderIds; // 平台订单ID列表，格式为1,2,3
	private String orderNums; // 平台订单ID对应的此商品的出库数量，格式为100,50,23

	private Integer outType; // 出库类型 1:正常 0:异常-此商品
	// ，异常类型是由盘库时产生异常数据时，触发一条出库单，并请求接口
	private String actualString;// 异常原因

	private String checkMainId;// 盘点异常出库会记录盘点的id
	private Integer status; // 出库状态 1:出库成功 0:出库失败

	public String toString() {
		String res = "";
		try {
			res = ReflectHelper.makeToString(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public Double getOutNum() {
		return outNum;
	}

	public void setOutNum(Double outNum) {
		this.outNum = outNum;
	}

	public Double getOutPrice() {
		return outPrice;
	}

	public void setOutPrice(Double outPrice) {
		this.outPrice = outPrice;
	}

	public String getLogisticsStaffName() {
		return logisticsStaffName;
	}

	public void setLogisticsStaffName(String logisticsStaffName) {
		this.logisticsStaffName = logisticsStaffName;
	}

	public String getLogisticsStaffMobile() {
		return logisticsStaffMobile;
	}

	public void setLogisticsStaffMobile(String logisticsStaffMobile) {
		this.logisticsStaffMobile = logisticsStaffMobile;
	}

	public String getLogisticsCarNo() {
		return logisticsCarNo;
	}

	public void setLogisticsCarNo(String logisticsCarNo) {
		this.logisticsCarNo = logisticsCarNo;
	}

	public Timestamp getOutTime() {
		return outTime;
	}

	public void setOutTime(Timestamp outTime) {
		this.outTime = outTime;
	}

	public Integer getInvInputId() {
		return invInputId;
	}

	public void setInvInputId(Integer invInputId) {
		this.invInputId = invInputId;
	}

	public Integer getProviedId() {
		return proviedId;
	}

	public void setProviedId(Integer proviedId) {
		this.proviedId = proviedId;
	}

	public String getProviedName() {
		return proviedName;
	}

	public void setProviedName(String proviedName) {
		this.proviedName = proviedName;
	}

	public String getInterfaceAck() {
		return interfaceAck;
	}

	public void setInterfaceAck(String interfaceAck) {
		this.interfaceAck = interfaceAck;
	}

	public String getInterfaceAckStatus() {
		return interfaceAckStatus;
	}

	public void setInterfaceAckStatus(String interfaceAckStatus) {
		this.interfaceAckStatus = interfaceAckStatus;
	}

	public String getInterfaceAckStatusDesc() {
		return interfaceAckStatusDesc;
	}

	public void setInterfaceAckStatusDesc(String interfaceAckStatusDesc) {
		this.interfaceAckStatusDesc = interfaceAckStatusDesc;
	}

	public String getOrderIds() {
		return orderIds;
	}

	public void setOrderIds(String orderIds) {
		this.orderIds = orderIds;
	}

	public String getOrderNums() {
		return orderNums;
	}

	public void setOrderNums(String orderNums) {
		this.orderNums = orderNums;
	}

	public Integer getOutType() {
		return outType;
	}

	public void setOutType(Integer outType) {
		this.outType = outType;
	}

	public String getActualString() {
		return actualString;
	}

	public void setActualString(String actualString) {
		this.actualString = actualString;
	}

	public String getCheckMainId() {
		return checkMainId;
	}

	public void setCheckMainId(String checkMainId) {
		this.checkMainId = checkMainId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
