/**
 *2014年8月25日 下午5:21:54
 */
package com.xnradmin.po.mall.commodity;

import static javax.persistence.GenerationType.IDENTITY;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Index;

import com.cntinker.util.ReflectHelper;
import com.xnradmin.po.CommonStaff;

/**
 * @author: liubin
 * 
 */
@Entity
@Table(name = "mall_user_recharge")
public class UserRecharge implements java.io.Serializable {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	private Long id;

	@Index(name = "idx_mall_user_recharge_clientuserinfoid")
	private Long clientUserInfoId;

	// 充值产品ID
	private Long RechargeProductId;

	// 订单表ID
	private Long orderId;

	private Timestamp changeTime;

	// 账户现在余额
	private Float balance;

	// 变动金额
	private Float changeAmount;

	// 原金额
	private Float oldAmount;

	// 实际支付金额
	private Float realPayAmount;

	// 使用折扣字段- 预留
	private Float discount;

	// 如果后面有工作人员后台操作账户，则此字段不为空
	private CommonStaff opearStaffId;

	// 操作类型,诸如用户充值，退款等
	private Integer opearTypeId;

	// 操作类型名称
	private String opearTypeName;

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

	public Long getClientUserInfoId() {
		return clientUserInfoId;
	}

	public Long getRechargeProductId() {
		return RechargeProductId;
	}

	public Long getOrderId() {
		return orderId;
	}

	public Timestamp getChangeTime() {
		return changeTime;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setClientUserInfoId(Long clientUserInfoId) {
		this.clientUserInfoId = clientUserInfoId;
	}

	public void setRechargeProductId(Long rechargeProductId) {
		RechargeProductId = rechargeProductId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public void setChangeTime(Timestamp changeTime) {
		this.changeTime = changeTime;
	}

	public Float getBalance() {
		return balance;
	}

	public Float getChangeAmount() {
		return changeAmount;
	}

	public Float getOldAmount() {
		return oldAmount;
	}

	public Float getRealPayAmount() {
		return realPayAmount;
	}

	public Float getDiscount() {
		return discount;
	}

	public void setBalance(Float balance) {
		this.balance = balance;
	}

	public void setChangeAmount(Float changeAmount) {
		this.changeAmount = changeAmount;
	}

	public void setOldAmount(Float oldAmount) {
		this.oldAmount = oldAmount;
	}

	public void setRealPayAmount(Float realPayAmount) {
		this.realPayAmount = realPayAmount;
	}

	public void setDiscount(Float discount) {
		this.discount = discount;
	}

	public CommonStaff getOpearStaffId() {
		return opearStaffId;
	}

	public Integer getOpearTypeId() {
		return opearTypeId;
	}

	public String getOpearTypeName() {
		return opearTypeName;
	}

	public void setOpearStaffId(CommonStaff opearStaffId) {
		this.opearStaffId = opearStaffId;
	}

	public void setOpearTypeId(Integer opearTypeId) {
		this.opearTypeId = opearTypeId;
	}

	public void setOpearTypeName(String opearTypeName) {
		this.opearTypeName = opearTypeName;
	}

}
