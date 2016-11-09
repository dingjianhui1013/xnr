/**
 *2016年11月8日 下午6:27:12
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
 * 仓库盘点表
 * 
 * @author: liubin
 *
 */
@Entity
@Table(name = "inv_checkmain")
public class InvCheckMain implements java.io.Serializable {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;

	private Integer checkTimes;// 盘点次数
	private Integer checkFinish;// 是否完成盘点1：盘点完成 0：盘点中 2:复位
	private Timestamp startDate;// 盘点开始时间
	private Timestamp endDate;// 盘点结束时间
	private Integer storeId;// 盘点仓库
	private Double goodsCount;// 盘点的数量
	private String checkName;// 盘点结束时的联系人
	private String checkNamePhone;// 盘点结束时的电话
	private String resetName;// 复位时的联系人
	private String resetNamePhone;// 复位时的联系人电话
	private Integer type;// 1：审批通过 2：审批拒绝 3盘点中断

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

	public Integer getCheckTimes() {
		return checkTimes;
	}

	public void setCheckTimes(Integer checkTimes) {
		this.checkTimes = checkTimes;
	}

	public Integer getCheckFinish() {
		return checkFinish;
	}

	public void setCheckFinish(Integer checkFinish) {
		this.checkFinish = checkFinish;
	}

	public Timestamp getStartDate() {
		return startDate;
	}

	public void setStartDate(Timestamp startDate) {
		this.startDate = startDate;
	}

	public Timestamp getEndDate() {
		return endDate;
	}

	public void setEndDate(Timestamp endDate) {
		this.endDate = endDate;
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public Double getGoodsCount() {
		return goodsCount;
	}

	public void setGoodsCount(Double goodsCount) {
		this.goodsCount = goodsCount;
	}

	public String getCheckName() {
		return checkName;
	}

	public void setCheckName(String checkName) {
		this.checkName = checkName;
	}

	public String getCheckNamePhone() {
		return checkNamePhone;
	}

	public void setCheckNamePhone(String checkNamePhone) {
		this.checkNamePhone = checkNamePhone;
	}

	public String getResetName() {
		return resetName;
	}

	public void setResetName(String resetName) {
		this.resetName = resetName;
	}

	public String getResetNamePhone() {
		return resetNamePhone;
	}

	public void setResetNamePhone(String resetNamePhone) {
		this.resetNamePhone = resetNamePhone;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
}
