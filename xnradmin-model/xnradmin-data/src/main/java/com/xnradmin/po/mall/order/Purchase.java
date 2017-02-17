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
@Table(name = "purchase")
public class Purchase implements java.io.Serializable{

	private static final long serialVersionUID = 1L;

	private Long id;
    
	private Integer clientUserId; //用户ID
	
	private Long orderRecordId; //订单ID
	
	private String orderNo; //订单号
	
	private Integer goodsId; //商品ID
	
	private String goodsName; //商品名称
	
	private Integer goodsCategoryId; //商品类型ID
	
	private String goodsCategoryName; //商品类型名称
	
	private Integer goodsCount; //商品数量
	
	private Integer dishId; //菜品ID
	
	private String dishName; //菜品名称
    
	private Integer dishTypeId; //菜品类型ID
	
	private String dishTypeName; //菜品类型名称
	
	private Integer dishCount; //菜品数量
	
	private Integer rawMaterialId; //材料ID
	
	private String rawMaterialName; //材料名称
	
	private Integer rawMaterialTypeId; //材料类型ID
	
	private String rawMaterialTypeName; //材料类型名称
	
	private Integer weightId; //数量单位ID
	
	private String weightName; //数量单位名称
	
	private Float number; //采购数量
	
    private Timestamp createTime; //搜索时间 
    
    /** default constructor */
    public Purchase(){
    }

    // Property accessors
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    public Long getId(){
        return this.id;
    }

    public void setId(Long id){
        this.id = id;
    }

	@Column(name = "CLIENT_USER_ID")
	public Integer getClientUserId() {
		return clientUserId;
	}

	public void setClientUserId(Integer clientUserId) {
		this.clientUserId = clientUserId;
	}

	@Column(name = "ORDER_RECORD_ID" ,length=20)
	public Long getOrderRecordId() {
		return orderRecordId;
	}

	public void setOrderRecordId(Long orderRecordId) {
		this.orderRecordId = orderRecordId;
	}

	@Column(name = "ORDER_NO")
	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	@Column(name = "GOODS_ID")
	public Integer getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}
	
	@Column(name = "GOODS_NAME")
	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	@Column(name = "DISH_ID")
	public Integer getDishId() {
		return dishId;
	}

	public void setDishId(Integer dishId) {
		this.dishId = dishId;
	}

	@Column(name = "DISH_NAME")
	public String getDishName() {
		return dishName;
	}

	public void setDishName(String dishName) {
		this.dishName = dishName;
	}

	@Column(name = "RAW_MATERIAL_ID")
	public Integer getRawMaterialId() {
		return rawMaterialId;
	}

	public void setRawMaterialId(Integer rawMaterialId) {
		this.rawMaterialId = rawMaterialId;
	}

	@Column(name = "RAW_MATERIAL_NAME")
	public String getRawMaterialName() {
		return rawMaterialName;
	}

	public void setRawMaterialName(String rawMaterialName) {
		this.rawMaterialName = rawMaterialName;
	}

	@Column(name = "RAW_MATERIAL_TYPE_ID")
	public Integer getRawMaterialTypeId() {
		return rawMaterialTypeId;
	}

	public void setRawMaterialTypeId(Integer rawMaterialTypeId) {
		this.rawMaterialTypeId = rawMaterialTypeId;
	}

	@Column(name = "RAW_MATERIAL_TYPE_NAME")
	public String getRawMaterialTypeName() {
		return rawMaterialTypeName;
	}

	public void setRawMaterialTypeName(String rawMaterialTypeName) {
		this.rawMaterialTypeName = rawMaterialTypeName;
	}

	@Column(name = "WEIGHT_ID")
	public Integer getWeightId() {
		return weightId;
	}

	public void setWeightId(Integer weightId) {
		this.weightId = weightId;
	}

	@Column(name = "WEIGHT_NAME")
	public String getWeightName() {
		return weightName;
	}

	public void setWeightName(String weightName) {
		this.weightName = weightName;
	}

	@Column(name = "NUMBER")
	public Float getNumber() {
		return number;
	}

	public void setNumber(Float number) {
		this.number = number;
	}

	@Column(name = "CREATE_TIME")
	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	@Column(name = "GOODS_COUNT")
	public Integer getGoodsCount() {
		return goodsCount;
	}

	public void setGoodsCount(Integer goodsCount) {
		this.goodsCount = goodsCount;
	}

	@Column(name = "DISH_COUNT")
	public Integer getDishCount() {
		return dishCount;
	}

	public void setDishCount(Integer dishCount) {
		this.dishCount = dishCount;
	}

	@Column(name = "GOODS_CATEGORY_ID")
	public Integer getGoodsCategoryId() {
		return goodsCategoryId;
	}

	public void setGoodsCategoryId(Integer goodsCategoryId) {
		this.goodsCategoryId = goodsCategoryId;
	}

	@Column(name = "GOODS_CATEGORY_NAME")
	public String getGoodsCategoryName() {
		return goodsCategoryName;
	}

	public void setGoodsCategoryName(String goodsCategoryName) {
		this.goodsCategoryName = goodsCategoryName;
	}

	@Column(name = "DISH_TYPE_ID")
	public Integer getDishTypeId() {
		return dishTypeId;
	}

	public void setDishTypeId(Integer dishTypeId) {
		this.dishTypeId = dishTypeId;
	}

	@Column(name = "DISH_TYPE_NAME")
	public String getDishTypeName() {
		return dishTypeName;
	}

	public void setDishTypeName(String dishTypeName) {
		this.dishTypeName = dishTypeName;
	}

	

	
}