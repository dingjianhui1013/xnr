package com.xnradmin.po.business;


import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * select * from phone_local where position(concat(id) in (select fid from test))!=0
 */
@Entity
@Table(name = "business_order_goods_relation")
public class BusinessOrderGoodsRelation implements java.io.Serializable{

    // Fields

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;

	private Long orderRecordId; //订单ID
    
	private Integer clientUserId; //用户ID
	
	private Integer goodsId; //商品Id
	
	private Integer comboId; //套餐商品Id
    
    private Integer goodsCount; //商品数量
    
    private Float originalPrice; //原始结算价格
    
    private Float  currentPrice; //当前价格
    
    private Float purchasePrice; //进货价格
    
    private Float discount; //当前用户折扣
    
    private Integer  currentPriceType; //当前价格类型（原价，优惠价）
    
    private String staffId;	//隶属用户Id
    
    private String delFlag;	//删除标志位 1 
    
    private Integer primaryConfigurationId; //对应商城ID
	
    private Timestamp orderGoodsRelationTime; //生成时间 
    
    private String allocationId;//分配的id
    
    private Integer goodsWeightId; //商品单位
    
    /** default constructor */
    public BusinessOrderGoodsRelation(){
    }

    // Property accessors
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    public Integer getId(){
        return this.id;
    }

    public void setId(Integer id){
        this.id = id;
    }

    @Column(name = "ORDER_RECORD_ID" ,length=20)
	public Long getOrderRecordId() {
		return orderRecordId;
	}

	public void setOrderRecordId(Long orderRecordId) {
		this.orderRecordId = orderRecordId;
	}

	@Column(name = "CLIENT_USER_ID")
	public Integer getClientUserId() {
		return clientUserId;
	}

	public void setClientUserId(Integer clientUserId) {
		this.clientUserId = clientUserId;
	}

	@Column(name = "GOODS_COUNT")
	public Integer getGoodsCount() {
		return goodsCount;
	}

	public void setGoodsCount(Integer goodsCount) {
		this.goodsCount = goodsCount;
	}

	@Column(name = "ORDER_GOODS_RELATION_TIME")
	public Timestamp getOrderGoodsRelationTime() {
		return orderGoodsRelationTime;
	}

	public void setOrderGoodsRelationTime(Timestamp orderGoodsRelationTime) {
		this.orderGoodsRelationTime = orderGoodsRelationTime;
	}

	@Column(name = "GOODS_ID")
	public Integer getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}
	
	@Column(name = "STAFF_ID")
	public String getStaffId() {
		return staffId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

	@Column(name = "PRIMARY_CONFIGURATION_ID")
	public Integer getPrimaryConfigurationId() {
		return primaryConfigurationId;
	}

	public void setPrimaryConfigurationId(Integer primaryConfigurationId) {
		this.primaryConfigurationId = primaryConfigurationId;
	}

	@Column(name = "CURRENT_PRICE")
	public Float getCurrentPrice() {
		return currentPrice;
	}

	public void setCurrentPrice(Float currentPrice) {
		this.currentPrice = currentPrice;
	}

	@Column(name = "CURRENT_PRICE_TYPE")
	public Integer getCurrentPriceType() {
		return currentPriceType;
	}

	public void setCurrentPriceType(Integer currentPriceType) {
		this.currentPriceType = currentPriceType;
	}

	@Column(name = "ORIGINAL_PRICE")
	public Float getOriginalPrice() {
		return originalPrice;
	}

	public void setOriginalPrice(Float originalPrice) {
		this.originalPrice = originalPrice;
	}

	@Column(name = "PURCHASE_PRICE")
	public Float getPurchasePrice() {
		return purchasePrice;
	}

	public void setPurchasePrice(Float purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	@Column(name = "DISCOUNT")
	public Float getDiscount() {
		return discount;
	}

	public void setDiscount(Float discount) {
		this.discount = discount;
	}
	@Column(name = "del_flag")
	public String getDelFlag() {
		return delFlag;
	}
	@Column(name = "allocation_id")
	public String getAllocationId() {
		return allocationId;
	}

	public void setAllocationId(String allocationId) {
		this.allocationId = allocationId;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

	public Integer getGoodsWeightId() {
		return goodsWeightId;
	}

	public void setGoodsWeightId(Integer goodsWeightId) {
		this.goodsWeightId = goodsWeightId;
	}

	public Integer getComboId() {
		return comboId;
	}

	@Column(name="combo_id")
	public void setComboId(Integer comboId) {
		this.comboId = comboId;
	}
	
	
	
}