/**
 *2016年11月8日 上午11:54:00
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
 * 仓库表
 * 
 * @author: liubin
 *
 */
@Entity
@Table(name = "inv_store")
public class InvStore implements java.io.Serializable {
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;

	private String storeName;// 仓库名
	private String cityCode;// 城市简称
	private String cityName;// 城市名
	private String contactName; // 联系人姓名
	private String contactPhone; // 联系人姓名
	private String contactEmail;// 联系人邮箱
	private String address;// 详细地址
	private Timestamp createTime;// 创建时间
	private Timestamp lastUpdateTime; // 更新时间
	private Integer status;// 状态,-1删除,0暂停使用,1正常
	private Integer bankType;// '银行账号类型1 对公 2对私'

	private Integer checkType;// 盘点的类型
	private String checkMainId;
	private Timestamp lastCheckTime;

	private Double deliveryPrice;// 仓库起送价
	private Double area; // 仓库面积

	// Fields
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

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getContactPhone() {
		return contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

	public String getContactEmail() {
		return contactEmail;
	}

	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Timestamp getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(Timestamp lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getBankType() {
		return bankType;
	}

	public void setBankType(Integer bankType) {
		this.bankType = bankType;
	}

	public Integer getCheckType() {
		return checkType;
	}

	public void setCheckType(Integer checkType) {
		this.checkType = checkType;
	}

	public String getCheckMainId() {
		return checkMainId;
	}

	public void setCheckMainId(String checkMainId) {
		this.checkMainId = checkMainId;
	}

	public Timestamp getLastCheckTime() {
		return lastCheckTime;
	}

	public void setLastCheckTime(Timestamp lastCheckTime) {
		this.lastCheckTime = lastCheckTime;
	}

	public Double getDeliveryPrice() {
		return deliveryPrice;
	}

	public void setDeliveryPrice(Double deliveryPrice) {
		this.deliveryPrice = deliveryPrice;
	}

	public Double getArea() {
		return area;
	}

	public void setArea(Double area) {
		this.area = area;
	}
}
