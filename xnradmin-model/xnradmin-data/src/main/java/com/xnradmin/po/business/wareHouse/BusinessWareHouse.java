/**
 *2015年1月19日 下午12:27:14
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
@Table(name = "business_ware_house")
public class BusinessWareHouse implements java.io.Serializable{

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	private Long id;

	private String wareHouseName;

	private String wareHouseAddress;

	private String wareHouseSerno;

	private Integer wareHouseStatus;
	
	private Timestamp createTime;

	private Integer createStaffId;
	
	private Timestamp modifyTime;

	private Integer modifyStaffId;
	

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

	public String getWareHouseName() {
		return wareHouseName;
	}

	public String getWareHouseAddress() {
		return wareHouseAddress;
	}

	public String getWareHouseSerno() {
		return wareHouseSerno;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setWareHouseName(String wareHouseName) {
		this.wareHouseName = wareHouseName;
	}

	public void setWareHouseAddress(String wareHouseAddress) {
		this.wareHouseAddress = wareHouseAddress;
	}

	public void setWareHouseSerno(String wareHouseSerno) {
		this.wareHouseSerno = wareHouseSerno;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public Integer getCreateStaffId() {
		return createStaffId;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public void setCreateStaffId(Integer createStaffId) {
		this.createStaffId = createStaffId;
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

	public Integer getWareHouseStatus() {
		return wareHouseStatus;
	}

	public void setWareHouseStatus(Integer wareHouseStatus) {
		this.wareHouseStatus = wareHouseStatus;
	}
}
