package com.xnradmin.po.mall.order;


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
@Table(name = "shopping_cart")
public class ShoppingCart implements java.io.Serializable{

    // Fields

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
    
	private Integer clientUserId; //用户ID
	
	private Integer goodsId; //商品Id
    
    private Integer goodsCount; //商品数量
    
    private Float  currentPrice; //当前单价
    
    private Float originalPrice; //原始价格
    
    private Integer  currentPriceType; //当前单价类型（原价，优惠价）
    
    private Float totalPrice; //当前总价
    
    private Float originalTotalPrice; //原始总价
    
    private String staffId;	//隶属用户Id
    
    private Integer primaryConfigurationId; //对应商城ID
	
    private Timestamp shoppingCartTime; //生成时间 
    private String cookieCartId;//cookie
    private Integer comboId;
    
    /** default constructor */
    public ShoppingCart(){
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

	@Column(name = "SHOPPING_CART_TIME")
	public Timestamp getShoppingCartTime() {
		return shoppingCartTime;
	}

	public void setShoppingCartTime(Timestamp shoppingCartTime) {
		this.shoppingCartTime = shoppingCartTime;
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

	@Column(name = "TOTAL_PRICE")
	public Float getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Float totalPrice) {
		this.totalPrice = totalPrice;
	}

	@Column(name = "ORIGINAL_PRICE")
	public Float getOriginalPrice() {
		return originalPrice;
	}

	public void setOriginalPrice(Float originalPrice) {
		this.originalPrice = originalPrice;
	}

	@Column(name = "ORIGINAL_TOTAL_PRICE")
	public Float getOriginalTotalPrice() {
		return originalTotalPrice;
	}

	public void setOriginalTotalPrice(Float originalTotalPrice) {
		this.originalTotalPrice = originalTotalPrice;
	}
	@Column(name = "cookie_cart_id")
	public String getCookieCartId() {
		return cookieCartId;
	}

	public void setCookieCartId(String cookieCartId) {
		this.cookieCartId = cookieCartId;
	}

	@Column(name = "combo_id")
	public Integer getComboId() {
		return comboId;
	}

	public void setComboId(Integer comboId) {
		this.comboId = comboId;
	}
	
	
}