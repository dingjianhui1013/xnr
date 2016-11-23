package com.xnradmin.po.wx.connect;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "farmer_qrcode",uniqueConstraints = {@UniqueConstraint(columnNames={"farmer_id","goods_id"})})
public class FarmerQrCode {
	private int id;
	private String farmerId;
	private String goodsId;
	private String qrCodeUrl;
	private String skipUrl;
	@Id
	@GeneratedValue(strategy = IDENTITY)
    @Column(name = "ID", unique = true, nullable = false) 
	public int getId() {
		return id;
	}
	@Column(name="farmer_id")
	public String getFarmerId() {
		return farmerId;
	}
	@Column(name="goods_id")
	public String getGoodsId() {
		return goodsId;
	}
	@Column(name="qrcode_url")
	public String getQrCodeUrl() {
		return qrCodeUrl;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setFarmerId(String farmerId) {
		this.farmerId = farmerId;
	}
	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}
	public void setQrCodeUrl(String qrCodeUrl) {
		this.qrCodeUrl = qrCodeUrl;
	}
	@Column(name="skip_url")
	public String getSkipUrl() {
		return skipUrl;
	}
	public void setSkipUrl(String skipUrl) {
		this.skipUrl = skipUrl;
	}
}
