/**
 *2016年11月8日 下午6:09:10
 */
package com.xnradmin.po.inv;

import static javax.persistence.GenerationType.IDENTITY;

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
@Table(name = "inv_input")
public class InvInput implements java.io.Serializable {
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;

	private Integer inputOrderId; // 供货订单id,这里对应的表是com.xnradmin.po.wx.outplan
	private Double inNum; // 实际进货数量/重量
	private Double buyNum;// 合同(或协议采购)数量/重量
	private Integer goodsId; // 商品id
	private String goodsName; // 商品名称
	private Integer storeId; // 仓库id
	private String storeName; // 仓库名称
	private Double singlePrice; // 入库时的商品单价
	private Double totalPrice; // 入库总价
	private Double priceNow; // 加权平均价
	private Double storeTotalNow; // 在入库时的库存总量，为了以后核对加权平均价用
	private String logName;// 物流人员姓名
	private String logPhone;// 物流人员手机
	private String logCard;// 车牌号
	private Integer type;// 盘点时进行入库：1-正常 0-异常
	private Double avaNum; // 此单剩余总量/重量
	private String checkMainId;// 盘点异常入库会记录盘点的id

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

	public Integer getInputOrderId() {
		return inputOrderId;
	}

	public void setInputOrderId(Integer inputOrderId) {
		this.inputOrderId = inputOrderId;
	}

	public Double getInNum() {
		return inNum;
	}

	public void setInNum(Double inNum) {
		this.inNum = inNum;
	}

	public Double getBuyNum() {
		return buyNum;
	}

	public void setBuyNum(Double buyNum) {
		this.buyNum = buyNum;
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

	public Double getSinglePrice() {
		return singlePrice;
	}

	public void setSinglePrice(Double singlePrice) {
		this.singlePrice = singlePrice;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Double getPriceNow() {
		return priceNow;
	}

	public void setPriceNow(Double priceNow) {
		this.priceNow = priceNow;
	}

	public Double getStoreTotalNow() {
		return storeTotalNow;
	}

	public void setStoreTotalNow(Double storeTotalNow) {
		this.storeTotalNow = storeTotalNow;
	}

	public String getLogName() {
		return logName;
	}

	public void setLogName(String logName) {
		this.logName = logName;
	}

	public String getLogPhone() {
		return logPhone;
	}

	public void setLogPhone(String logPhone) {
		this.logPhone = logPhone;
	}

	public String getLogCard() {
		return logCard;
	}

	public void setLogCard(String logCard) {
		this.logCard = logCard;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Double getAvaNum() {
		return avaNum;
	}

	public void setAvaNum(Double avaNum) {
		this.avaNum = avaNum;
	}

	public String getCheckMainId() {
		return checkMainId;
	}

	public void setCheckMainId(String checkMainId) {
		this.checkMainId = checkMainId;
	}
}
