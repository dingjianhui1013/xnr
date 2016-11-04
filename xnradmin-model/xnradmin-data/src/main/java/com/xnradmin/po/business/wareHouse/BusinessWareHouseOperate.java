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
 * @author: liu_xiang
 *
 */
@Entity
@Table(name = "business_ware_house_operate")
public class BusinessWareHouseOperate implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	private Long id;

	private Long wareHouseId;//仓库ID

	private Long goodsId;//商品ID
	
	private String categoryId;//商品类型ID
	
	private Integer operationStatus;//操作状态（入库，出库）
	
	private Integer reasonStatus;//操作理由（采购，出售，退货，换货，自用，损耗）
	
	private Float price;//出入库价格
	
	private Long count;//出入库数量
	
	private Integer weightId;//数量单位（与商品表数量单位统一）
	
	private Integer supplierStaffId;//供货商ID
	
	private Integer purchaserStaffId;//采购商ID
	
	private String remark;//备注
	
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

	public void setId(Long id) {
		this.id = id;
	}

	public Long getWareHouseId() {
		return wareHouseId;
	}

	public void setWareHouseId(Long wareHouseId) {
		this.wareHouseId = wareHouseId;
	}

	public Long getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}

	public Integer getOperationStatus() {
		return operationStatus;
	}

	public void setOperationStatus(Integer operationStatus) {
		this.operationStatus = operationStatus;
	}

	public Integer getReasonStatus() {
		return reasonStatus;
	}

	public void setReasonStatus(Integer reasonStatus) {
		this.reasonStatus = reasonStatus;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

	public Integer getWeightId() {
		return weightId;
	}

	public void setWeightId(Integer weightId) {
		this.weightId = weightId;
	}

	public Integer getSupplierStaffId() {
		return supplierStaffId;
	}

	public void setSupplierStaffId(Integer supplierStaffId) {
		this.supplierStaffId = supplierStaffId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Integer getCreateStaffId() {
		return createStaffId;
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

	public Integer getPurchaserStaffId() {
		return purchaserStaffId;
	}

	public void setPurchaserStaffId(Integer purchaserStaffId) {
		this.purchaserStaffId = purchaserStaffId;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	
	
}
