/**
 *2014年8月25日 下午5:22:30
 */
package com.xnradmin.po.mall.commodity;

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
@Table(name = "mall_recharge_product")
public class RechargeProduct implements java.io.Serializable {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	private Long id;

	private String productName;

	// 需要支付金额
	private Float amount;

	// 账户变动金额
	private Float changeAmount;

	// 使用折扣字段- 预留
	private Float discount;

	private Timestamp createTime;

	private Integer createStaffId;

	private String createStaffName;

	private Timestamp modifyTime;

	private Integer modifyStaffId;

	private String modifyStaffName;

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

	public String getProductName() {
		return productName;
	}

	public Float getAmount() {
		return amount;
	}

	public Float getChangeAmount() {
		return changeAmount;
	}

	public Float getDiscount() {
		return discount;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public Integer getCreateStaffId() {
		return createStaffId;
	}

	public String getCreateStaffName() {
		return createStaffName;
	}

	public Timestamp getModifyTime() {
		return modifyTime;
	}

	public Integer getModifyStaffId() {
		return modifyStaffId;
	}

	public String getModifyStaffName() {
		return modifyStaffName;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public void setAmount(Float amount) {
		this.amount = amount;
	}

	public void setChangeAmount(Float changeAmount) {
		this.changeAmount = changeAmount;
	}

	public void setDiscount(Float discount) {
		this.discount = discount;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public void setCreateStaffId(Integer createStaffId) {
		this.createStaffId = createStaffId;
	}

	public void setCreateStaffName(String createStaffName) {
		this.createStaffName = createStaffName;
	}

	public void setModifyTime(Timestamp modifyTime) {
		this.modifyTime = modifyTime;
	}

	public void setModifyStaffId(Integer modifyStaffId) {
		this.modifyStaffId = modifyStaffId;
	}

	public void setModifyStaffName(String modifyStaffName) {
		this.modifyStaffName = modifyStaffName;
	}

}
