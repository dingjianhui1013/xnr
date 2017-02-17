/**
 *2015年1月20日 下午5:03:26
 */
package com.xnradmin.po.business.wareHouse;

import static javax.persistence.GenerationType.IDENTITY;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.cntinker.util.ReflectHelper;

/**
 * @author: liubin
 *
 */
@Entity
@Table(name = "business_ware_house_inv_rel")
public class BusinessWareHouseInvRel implements java.io.Serializable{

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	private Long id;

	private Long wareHouseId;

	private Long goodsId;
	
	private Integer weightId;
	
	private Timestamp createTime;

	private Integer createStaffId;
	
	private Timestamp modifyTime;

	private Integer modifyStaffId;
	
	private Long count;

	public String toString() {
		String res = "";
		try {
			res = ReflectHelper.makeToString(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}



	public Long getId() {
		return id;
	}



	public Long getWareHouseId() {
		return wareHouseId;
	}



	public Long getGoodsId() {
		return goodsId;
	}



	public Timestamp getCreateTime() {
		return createTime;
	}



	public Integer getCreateStaffId() {
		return createStaffId;
	}



	public Long getCount() {
		return count;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public void setWareHouseId(Long wareHouseId) {
		this.wareHouseId = wareHouseId;
	}



	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}



	public Integer getWeightId() {
		return weightId;
	}



	public void setWeightId(Integer weightId) {
		this.weightId = weightId;
	}



	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}



	public void setCreateStaffId(Integer createStaffId) {
		this.createStaffId = createStaffId;
	}



	public void setCount(Long count) {
		this.count = count;
	}



	public Timestamp getModifyTime() {
		return modifyTime;
	}



	public void setModifyTime(Timestamp modifyTime) {
		this.modifyTime = modifyTime;
	}



	public Integer getModifyStaffId() {
		return modifyStaffId;
	}



	public void setModifyStaffId(Integer modifyStaffId) {
		this.modifyStaffId = modifyStaffId;
	}





}
