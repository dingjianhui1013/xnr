/**
 *2016年11月8日 上午11:15:29
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
 * 库存详情表，会实时变动
 * 
 * @author: liubin
 *
 */
@Entity
@Table(name = "inv_store_detail")
public class InvStoreDetail implements java.io.Serializable {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;
	private Integer storeId; // 仓库ID
	private String storeName; // 仓库名称
	private Integer goodsId; // 商品ID
	private String goodsName; // 商品名称
	private Double weight; // 商品重量
	private Integer categoryId; // 分类ID
	private String categoryName; // 分类名称
	private Timestamp lastUpdate; // 最后更新时间
	private Double reservedTotal; // 预占总量
	private Double availableTotal; // 库存余量
	private Double inputTotal; // 入库总量
	private Double outputTotal; // 出库总量
	private Double early;// 预警值
	private Double price; // 加权平均计算出的库存价格

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

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Timestamp getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getInputTotal() {
		return inputTotal;
	}

	public void setInputTotal(Double inputTotal) {
		this.inputTotal = inputTotal;
	}

	public Double getOutputTotal() {
		return outputTotal;
	}

	public void setOutputTotal(Double outputTotal) {
		this.outputTotal = outputTotal;
	}

	public Double getReservedTotal() {
		return reservedTotal;
	}

	public void setReservedTotal(Double reservedTotal) {
		this.reservedTotal = reservedTotal;
	}

	public Double getAvailableTotal() {
		return availableTotal;
	}

	public void setAvailableTotal(Double availableTotal) {
		this.availableTotal = availableTotal;
	}

	public Double getEarly() {
		return early;
	}

	public void setEarly(Double early) {
		this.early = early;
	}
}
