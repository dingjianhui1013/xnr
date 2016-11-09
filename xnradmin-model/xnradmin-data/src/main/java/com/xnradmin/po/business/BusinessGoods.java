package com.xnradmin.po.business;


import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Id;
import javax.persistence.Table;

import com.cntinker.util.ReflectHelper;

/**
 * select * from phone_local where position(concat(id) in (select fid from test))!=0
 */
@Entity
@Table(name = "business_goods")
public class BusinessGoods implements java.io.Serializable{

    // Fields

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;

    private Integer sortId;  //商品排序Id
    
    private String goodsParentId;  //上级商品Id

    private String goodsCategoryId;  //商品所属分类
    
    private String goodsName;  //商品名称
    
    private String goodsDescription;	//商品描述
    
    private Float goodsOriginalPrice;	//商品原价
    
    private Float goodsPreferentialPrice;	//商品优惠价
    
    private Float goodsSinglePrice; //商品单价
    
    private Float goodsPurchasePrice;	//商品进货价
    
    private Integer goodsBrandId; //商品品牌ID
    
    private Float goodsWeight; //商品重量或数量
    
    private Integer goodsWeightId; //重量或数量单位
    
    private Integer goodsSoldCount; //已售数量
    
    private Integer goodsStock; //商品库存
    
    private Integer goodsStatus;  //商品状态上架下架
    
    private Integer isFreeLogistics; //包邮不包邮
    
    private Integer isNewGoods; //是否新商品
    
    private Integer isDiscountGoods; //是否优惠商品
    
    private Integer isHotSaleGoods; //是否热卖商品
    
    private String goodsLogo; //商品图片
    
    private String goodsBigLogo; //商品大图片
    
    private Integer primaryConfigurationId; //对应商城ID
    
	private Timestamp createTime;  //建立时间
	
	private Integer createStaffId;  //建立人
	
	private Timestamp modifyTime;  //修改时间

	private Integer modifyStaffId;  //修改人
	
	private Integer buyTeamId; //采购分组
	
	private Integer sourceId; //商品来源
	
    
    /** default constructor */
    public BusinessGoods(){
    }
    
    public String toString(){
        String res = "";
        try{
            res = ReflectHelper.makeToString(this);
        }catch(Exception e){
            e.printStackTrace();
        }
        return res;
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

    @Column(name = "SORT_ID")
	public Integer getSortId() {
		return sortId;
	}

    public void setSortId(Integer sortId) {
		this.sortId = sortId;
	}
    
    @Column(name = "GOODS_PARENT_ID")
	public String getGoodsParentId() {
		return goodsParentId;
	}

	public void setGoodsParentId(String goodsParentId) {
		this.goodsParentId = goodsParentId;
	}

	@Column(name = "GOODS_CATEGORY_ID" , length=100)
	public String getGoodsCategoryId() {
		return goodsCategoryId;
	}

	public void setGoodsCategoryId(String goodsCategoryId) {
		this.goodsCategoryId = goodsCategoryId;
	}

	@Column(name = "GOODS_NAME" , length=100)
	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	@Column(name = "GOODS_DESCRIPTION" , length=400)
	public String getGoodsDescription() {
		return goodsDescription;
	}

	public void setGoodsDescription(String goodsDescription) {
		this.goodsDescription = goodsDescription;
	}

	@Column(name = "GOODS_ORIGINAL_PRICE")
	public Float getGoodsOriginalPrice() {
		return goodsOriginalPrice;
	}

	public void setGoodsOriginalPrice(Float goodsOriginalPrice) {
		this.goodsOriginalPrice = goodsOriginalPrice;
	}

	@Column(name = "GOODS_PREFERENTIAL_PRICE")
	public Float getGoodsPreferentialPrice() {
		return goodsPreferentialPrice;
	}

	public void setGoodsPreferentialPrice(Float goodsPreferentialPrice) {
		this.goodsPreferentialPrice = goodsPreferentialPrice;
	}

	@Column(name = "GOODS_BRAND_ID")
	public Integer getGoodsBrandId() {
		return goodsBrandId;
	}

	public void setGoodsBrandId(Integer goodsBrandId) {
		this.goodsBrandId = goodsBrandId;
	}

	@Column(name = "GOODS_WEIGHT")
	public Float getGoodsWeight() {
		return goodsWeight;
	}

	public void setGoodsWeight(Float goodsWeight) {
		this.goodsWeight = goodsWeight;
	}

	@Column(name = "GOODS_STOCK")
	public Integer getGoodsStock() {
		return goodsStock;
	}

	public void setGoodsStock(Integer goodsStock) {
		this.goodsStock = goodsStock;
	}

	@Column(name = "GOODS_STATUS")
	public Integer getGoodsStatus() {
		return goodsStatus;
	}

	public void setGoodsStatus(Integer goodsStatus) {
		this.goodsStatus = goodsStatus;
	}

	@Column(name = "IS_FREELOGISTICS")
	public Integer getIsFreeLogistics() {
		return isFreeLogistics;
	}

	public void setIsFreeLogistics(Integer isFreeLogistics) {
		this.isFreeLogistics = isFreeLogistics;
	}

	@Column(name = "IS_NEW_GOODS")
	public Integer getIsNewGoods() {
		return isNewGoods;
	}

	public void setIsNewGoods(Integer isNewGoods) {
		this.isNewGoods = isNewGoods;
	}

	@Column(name = "IS_DISCOUNT_GOODS")
	public Integer getIsDiscountGoods() {
		return isDiscountGoods;
	}

	public void setIsDiscountGoods(Integer isDiscountGoods) {
		this.isDiscountGoods = isDiscountGoods;
	}

	@Column(name = "IS_HOTSALE_GOODS")
	public Integer getIsHotSaleGoods() {
		return isHotSaleGoods;
	}

	public void setIsHotSaleGoods(Integer isHotSaleGoods) {
		this.isHotSaleGoods = isHotSaleGoods;
	}

	@Column(name = "GOODS_LOGO")
	public String getGoodsLogo() {
		return goodsLogo;
	}

	public void setGoodsLogo(String goodsLogo) {
		this.goodsLogo = goodsLogo;
	}

	@Column(name = "GOODS_BIG_LOGO")
	public String getGoodsBigLogo() {
		return goodsBigLogo;
	}

	public void setGoodsBigLogo(String goodsBigLogo) {
		this.goodsBigLogo = goodsBigLogo;
	}
	
	
	@Column(name = "GOODS_WEIGHT_ID")
	public Integer getGoodsWeightId() {
		return goodsWeightId;
	}

	public void setGoodsWeightId(Integer goodsWeightId) {
		this.goodsWeightId = goodsWeightId;
	}

	@Column(name = "GOODS_SOLD_COUNT")
	public Integer getGoodsSoldCount() {
		return goodsSoldCount;
	}

	public void setGoodsSoldCount(Integer goodsSoldCount) {
		this.goodsSoldCount = goodsSoldCount;
	}

	@Column(name = "CREATE_TIME")
	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	@Column(name = "CREATE_STAFF_ID")
	public Integer getCreateStaffId() {
		return createStaffId;
	}

	public void setCreateStaffId(Integer createStaffId) {
		this.createStaffId = createStaffId;
	}

	@Column(name = "MODIFY_TIME")
	public Timestamp getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Timestamp modifyTime) {
		this.modifyTime = modifyTime;
	}

	@Column(name = "MODIFY_STAFF_ID")
	public Integer getModifyStaffId() {
		return modifyStaffId;
	}

	public void setModifyStaffId(Integer modifyStaffId) {
		this.modifyStaffId = modifyStaffId;
	}

	@Column(name = "PRIMARY_CONFIGURATION_ID")
	public Integer getPrimaryConfigurationId() {
		return primaryConfigurationId;
	}

	public void setPrimaryConfigurationId(Integer primaryConfigurationId) {
		this.primaryConfigurationId = primaryConfigurationId;
	}

	@Column(name = "GOODS_SINGLE_PRICE")
	public Float getGoodsSinglePrice() {
		return goodsSinglePrice;
	}

	public void setGoodsSinglePrice(Float goodsSinglePrice) {
		this.goodsSinglePrice = goodsSinglePrice;
	}

	@Column(name = "GOODS_PURCHASE_PRICE")
	public Float getGoodsPurchasePrice() {
		return goodsPurchasePrice;
	}

	public void setGoodsPurchasePrice(Float goodsPurchasePrice) {
		this.goodsPurchasePrice = goodsPurchasePrice;
	}

	public Integer getBuyTeamId() {
		return buyTeamId;
	}

	public void setBuyTeamId(Integer buyTeamId) {
		this.buyTeamId = buyTeamId;
	}

	public Integer getSourceId() {
		return sourceId;
	}

	public void setSourceId(Integer sourceId) {
		this.sourceId = sourceId;
	}

	
	
	
}