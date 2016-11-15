/**
 *2014年11月25日 下午5:27:55
 */
package com.xnradmin.po.business;

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
@Table(name = "business_data")
public class BusinessData implements java.io.Serializable {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	private Long id;

	private String sourceId;
	
	private String sourceName;

	private String sourceWeb;

	private String productName;
	
	private String category;
	
	private String parentCategory;
	
	private String subject;
	
	private String unitdetail;
	
	private String unit;

	private Double price;
	
	private Double singlePrice;

	private Double maxPrice;

	private Double minPrice;

	private Double lastPrice;
	
	private Double lastMaxPrice;
	
	private Double lastMinPrice;

	private Timestamp updateTime;
	
	private String city;
	
	private String market;
	
	private String province;
	
	private String description;
	
	private String imgUrl;
	
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

	public String getSourceName() {
		return sourceName;
	}

	public String getSourceWeb() {
		return sourceWeb;
	}

	public String getProductName() {
		return productName;
	}

	public Double getPrice() {
		return price;
	}

	public Double getMaxPrice() {
		return maxPrice;
	}

	public Double getMinPrice() {
		return minPrice;
	}

	public Double getLastPrice() {
		return lastPrice;
	}

	public Double getLastMaxPrice() {
		return lastMaxPrice;
	}

	public Double getLastMinPrice() {
		return lastMinPrice;
	}

	public Timestamp getUpdateTime() {
		return updateTime;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}

	public void setSourceWeb(String sourceWeb) {
		this.sourceWeb = sourceWeb;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public void setMaxPrice(Double maxPrice) {
		this.maxPrice = maxPrice;
	}

	public void setMinPrice(Double minPrice) {
		this.minPrice = minPrice;
	}

	public void setLastPrice(Double lastPrice) {
		this.lastPrice = lastPrice;
	}

	public void setLastMaxPrice(Double lastMaxPrice) {
		this.lastMaxPrice = lastMaxPrice;
	}

	public void setLastMinPrice(Double lastMinPrice) {
		this.lastMinPrice = lastMinPrice;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

	public String getCity() {
		return city;
	}

	public String getMarket() {
		return market;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setMarket(String market) {
		this.market = market;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCategory() {
		return category;
	}

	public String getSubject() {
		return subject;
	}

	public String getUnit() {
		return unit;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getSourceId() {
		return sourceId;
	}

	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}

	public String getParentCategory() {
		return parentCategory;
	}

	public void setParentCategory(String parentCategory) {
		this.parentCategory = parentCategory;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUnitdetail() {
		return unitdetail;
	}

	public void setUnitdetail(String unitdetail) {
		this.unitdetail = unitdetail;
	}

	public Double getSinglePrice() {
		return singlePrice;
	}

	public void setSinglePrice(Double singlePrice) {
		this.singlePrice = singlePrice;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
}
