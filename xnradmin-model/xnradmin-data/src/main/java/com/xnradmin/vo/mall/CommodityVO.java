package com.xnradmin.vo.mall;

import com.xnradmin.po.dishes.Dish;
import com.xnradmin.po.mall.commodity.Goods;
import com.xnradmin.po.mall.commodity.GoodsDishRelation;

public class CommodityVO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	//category
	private String categoryId;

    private String categorySortId;  //排序Id

    private String categoryParentId;  //上级分类Id

    private String categoryLevel; //分类级别
    
    private String categoryName;  //分类名称

    private String categoryLogo;	//分类图片
    
    private String categoryHeadLogo;	//分类置顶图片
    
    private String categoryDescription;	//分类描述
    
    private String categoryStatus;  //分类状态
    
    private String categoryStaffId;	//隶属用户Id
    
    private String categoryPrimaryConfigurationId; //对应商城ID
    
	private String categoryCreateTime;  //建立时间
	
	private String categoryCreateStaffId;  //建立人
	
	private String categoryModifyTime;  //修改时间

	private String categoryModifyStaffId;  //修改人

	private String categoryGoodsCount; //当前类型商品总量
	
	//goods
	private String goodsId;

    private String goodsSortId;  //商品排序Id
    
    private String goodsParentId;  //上级商品Id

    private String goodsCategoryId;  //商品所属分类
    
    private String goodsName;  //商品名称
    
    private String goodsDescription;	//商品描述
    
    private String goodsOriginalPrice;	//商品原价
    
    private String goodsPreferentialPrice;	//商品优惠价
    
    private String goodsBrandId; //商品品牌ID
    
    private String goodsWeight; //商品重量
    
    private String goodsStock; //商品库存
    
    private String goodsStatus;  //商品状态上架下架
    
    private String goodsStatusCode; //商品状态上架下架文字说明
    
    private String isFreeLogistics; //包邮不包邮
    
    private String isNewGoods; //是否新商品
    
    private String isDiscountGoods; //是否优惠商品
    
    private String isHotSaleGoods; //是否热卖商品
    
    private String goodsLogo; //商品图片
    
    private String goodsBigLogo; //商品大图片
    
    private String goodsStaffId;	//隶属用户Id
    
    private String goodsPrimaryConfigurationId; //对应商城ID
    
	private String goodsCreateTime;  //建立时间
	
	private String goodsCreateStaffId;  //建立人
	
	private String goodsModifyTime;  //修改时间

	private String goodsModifyStaffId;  //修改人
	
	
	//goodsdishrrelation
	private String goodsDishrelationId;

    private String goodsDishrelationSortId;  //显示排序Id

    private String goodsDishrelationGoodsId;  //商品Id
    
    private String goodsDishrelationDishId;  //菜品Id
    
    private String goodsDishrelationDishCount;  //菜品数量
    
    private String goodsDishrelationStaffId;	//隶属用户Id
    
    private String goodsDishrelationpPrimaryConfigurationId; //对应商城ID
    
	private String goodsDishrelationCreateTime;  //建立时间
	
	private String goodsDishrelationCreateStaffId;  //建立人
	
	private String GoodsDishrelationCreateStaffName;  //建立用户名称
	
	private String goodsDishrelationModifyTime;  //修改时间

	private String goodsDishrelationModifyStaffId;  //修改人
	
	private String goodsDishrelationModifyStaffName;  //修改用户名称
	
	
	//dish
	private Dish dish;	//菜品
	
	//goods
	private Goods goods; //商品
	
	//goodsDishRelation
	private GoodsDishRelation goodsDishRelation; //中间表
	
	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategorySortId() {
		return categorySortId;
	}

	public void setCategorySortId(String categorySortId) {
		this.categorySortId = categorySortId;
	}

	public String getCategoryParentId() {
		return categoryParentId;
	}

	public void setCategoryParentId(String categorySortId) {
		this.categoryParentId = categorySortId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getCategoryLogo() {
		return categoryLogo;
	}

	public void setCategoryLogo(String categoryLogo) {
		this.categoryLogo = categoryLogo;
	}

	public String getCategoryHeadLogo() {
		return categoryHeadLogo;
	}

	public void setCategoryHeadLogo(String categoryHeadLogo) {
		this.categoryHeadLogo = categoryHeadLogo;
	}

	public String getCategoryDescription() {
		return categoryDescription;
	}

	public void setCategoryDescription(String categoryDescription) {
		this.categoryDescription = categoryDescription;
	}

	public String getCategoryStatus() {
		return categoryStatus;
	}

	public void setCategoryStatus(String categoryStatus) {
		this.categoryStatus = categoryStatus;
	}

	public String getCategoryStaffId() {
		return categoryStaffId;
	}

	public void setCategoryStaffId(String categoryStaffId) {
		this.categoryStaffId = categoryStaffId;
	}

	public String getCategoryPrimaryConfigurationId() {
		return categoryPrimaryConfigurationId;
	}

	public void setCategoryPrimaryConfigurationId(
			String categoryPrimaryConfigurationId) {
		this.categoryPrimaryConfigurationId = categoryPrimaryConfigurationId;
	}

	public String getCategoryCreateTime() {
		return categoryCreateTime;
	}

	public void setCategoryCreateTime(String categoryCreateTime) {
		this.categoryCreateTime = categoryCreateTime;
	}

	public String getCategoryCreateStaffId() {
		return categoryCreateStaffId;
	}

	public void setCategoryCreateStaffId(String categoryCreateStaffId) {
		this.categoryCreateStaffId = categoryCreateStaffId;
	}

	public String getCategoryModifyTime() {
		return categoryModifyTime;
	}

	public void setCategoryModifyTime(String categoryModifyTime) {
		this.categoryModifyTime = categoryModifyTime;
	}

	public String getCategoryModifyStaffId() {
		return categoryModifyStaffId;
	}

	public void setCategoryModifyStaffId(String categoryModifyStaffId) {
		this.categoryModifyStaffId = categoryModifyStaffId;
	}

	public String getCategoryLevel() {
		return categoryLevel;
	}

	public void setCategoryLevel(String categoryLevel) {
		this.categoryLevel = categoryLevel;
	}

	public String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	public String getGoodsSortId() {
		return goodsSortId;
	}

	public void setGoodsSortId(String goodsSortId) {
		this.goodsSortId = goodsSortId;
	}

	public String getGoodsParentId() {
		return goodsParentId;
	}

	public void setGoodsParentId(String goodsParentId) {
		this.goodsParentId = goodsParentId;
	}

	public String getGoodsCategoryId() {
		return goodsCategoryId;
	}

	public void setGoodsCategoryId(String goodsCategoryId) {
		this.goodsCategoryId = goodsCategoryId;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getGoodsDescription() {
		return goodsDescription;
	}

	public void setGoodsDescription(String goodsDescription) {
		this.goodsDescription = goodsDescription;
	}

	public String getGoodsOriginalPrice() {
		return goodsOriginalPrice;
	}

	public void setGoodsOriginalPrice(String goodsOriginalPrice) {
		this.goodsOriginalPrice = goodsOriginalPrice;
	}

	public String getGoodsPreferentialPrice() {
		return goodsPreferentialPrice;
	}

	public void setGoodsPreferentialPrice(String goodsPreferentialPrice) {
		this.goodsPreferentialPrice = goodsPreferentialPrice;
	}

	public String getGoodsBrandId() {
		return goodsBrandId;
	}

	public void setGoodsBrandId(String goodsBrandId) {
		this.goodsBrandId = goodsBrandId;
	}

	public String getGoodsWeight() {
		return goodsWeight;
	}

	public void setGoodsWeight(String goodsWeight) {
		this.goodsWeight = goodsWeight;
	}

	public String getGoodsStock() {
		return goodsStock;
	}

	public void setGoodsStock(String goodsStock) {
		this.goodsStock = goodsStock;
	}

	public String getGoodsStatus() {
		return goodsStatus;
	}

	public void setGoodsStatus(String goodsStatus) {
		this.goodsStatus = goodsStatus;
	}

	public String getIsFreeLogistics() {
		return isFreeLogistics;
	}

	public void setIsFreeLogistics(String isFreeLogistics) {
		this.isFreeLogistics = isFreeLogistics;
	}

	public String getIsNewGoods() {
		return isNewGoods;
	}

	public void setIsNewGoods(String isNewGoods) {
		this.isNewGoods = isNewGoods;
	}

	public String getIsDiscountGoods() {
		return isDiscountGoods;
	}

	public void setIsDiscountGoods(String isDiscountGoods) {
		this.isDiscountGoods = isDiscountGoods;
	}

	public String getIsHotSaleGoods() {
		return isHotSaleGoods;
	}

	public void setIsHotSaleGoods(String isHotSaleGoods) {
		this.isHotSaleGoods = isHotSaleGoods;
	}

	public String getGoodsLogo() {
		return goodsLogo;
	}

	public void setGoodsLogo(String goodsLogo) {
		this.goodsLogo = goodsLogo;
	}

	public String getGoodsBigLogo() {
		return goodsBigLogo;
	}

	public void setGoodsBigLogo(String goodsBigLogo) {
		this.goodsBigLogo = goodsBigLogo;
	}

	public String getGoodsStaffId() {
		return goodsStaffId;
	}

	public void setGoodsStaffId(String goodsStaffId) {
		this.goodsStaffId = goodsStaffId;
	}

	public String getGoodsPrimaryConfigurationId() {
		return goodsPrimaryConfigurationId;
	}

	public void setGoodsPrimaryConfigurationId(String goodsPrimaryConfigurationId) {
		this.goodsPrimaryConfigurationId = goodsPrimaryConfigurationId;
	}

	public String getGoodsCreateTime() {
		return goodsCreateTime;
	}

	public void setGoodsCreateTime(String goodsCreateTime) {
		this.goodsCreateTime = goodsCreateTime;
	}

	public String getGoodsCreateStaffId() {
		return goodsCreateStaffId;
	}

	public void setGoodsCreateStaffId(String goodsCreateStaffId) {
		this.goodsCreateStaffId = goodsCreateStaffId;
	}

	public String getGoodsModifyTime() {
		return goodsModifyTime;
	}

	public void setGoodsModifyTime(String goodsModifyTime) {
		this.goodsModifyTime = goodsModifyTime;
	}

	public String getGoodsModifyStaffId() {
		return goodsModifyStaffId;
	}

	public void setGoodsModifyStaffId(String goodsModifyStaffId) {
		this.goodsModifyStaffId = goodsModifyStaffId;
	}

	public String getGoodsDishrelationId() {
		return goodsDishrelationId;
	}

	public void setGoodsDishrelationId(String goodsDishrelationId) {
		this.goodsDishrelationId = goodsDishrelationId;
	}

	public String getGoodsDishrelationSortId() {
		return goodsDishrelationSortId;
	}

	public void setGoodsDishrelationSortId(String goodsDishrelationSortId) {
		this.goodsDishrelationSortId = goodsDishrelationSortId;
	}

	public String getGoodsDishrelationGoodsId() {
		return goodsDishrelationGoodsId;
	}

	public void setGoodsDishrelationGoodsId(String goodsDishrelationGoodsId) {
		this.goodsDishrelationGoodsId = goodsDishrelationGoodsId;
	}

	public String getGoodsDishrelationDishId() {
		return goodsDishrelationDishId;
	}

	public void setGoodsDishrelationDishId(String goodsDishrelationDishId) {
		this.goodsDishrelationDishId = goodsDishrelationDishId;
	}

	public String getGoodsDishrelationStaffId() {
		return goodsDishrelationStaffId;
	}

	public void setGoodsDishrelationStaffId(String goodsDishrelationStaffId) {
		this.goodsDishrelationStaffId = goodsDishrelationStaffId;
	}

	public String getGoodsDishrelationpPrimaryConfigurationId() {
		return goodsDishrelationpPrimaryConfigurationId;
	}

	public void setGoodsDishrelationpPrimaryConfigurationId(
			String goodsDishrelationpPrimaryConfigurationId) {
		this.goodsDishrelationpPrimaryConfigurationId = goodsDishrelationpPrimaryConfigurationId;
	}

	public String getGoodsDishrelationCreateTime() {
		return goodsDishrelationCreateTime;
	}

	public void setGoodsDishrelationCreateTime(String goodsDishrelationCreateTime) {
		this.goodsDishrelationCreateTime = goodsDishrelationCreateTime;
	}

	public String getGoodsDishrelationCreateStaffId() {
		return goodsDishrelationCreateStaffId;
	}

	public void setGoodsDishrelationCreateStaffId(
			String goodsDishrelationCreateStaffId) {
		this.goodsDishrelationCreateStaffId = goodsDishrelationCreateStaffId;
	}

	public String getGoodsDishrelationModifyTime() {
		return goodsDishrelationModifyTime;
	}

	public void setGoodsDishrelationModifyTime(String goodsDishrelationModifyTime) {
		this.goodsDishrelationModifyTime = goodsDishrelationModifyTime;
	}

	public String getGoodsDishrelationModifyStaffId() {
		return goodsDishrelationModifyStaffId;
	}

	public void setGoodsDishrelationModifyStaffId(
			String goodsDishrelationModifyStaffId) {
		this.goodsDishrelationModifyStaffId = goodsDishrelationModifyStaffId;
	}

	public String getGoodsDishrelationDishCount() {
		return goodsDishrelationDishCount;
	}

	public void setGoodsDishrelationDishCount(String goodsDishrelationDishCount) {
		this.goodsDishrelationDishCount = goodsDishrelationDishCount;
	}

	public String getGoodsDishrelationCreateStaffName() {
		return GoodsDishrelationCreateStaffName;
	}

	public void setGoodsDishrelationCreateStaffName(
			String goodsDishrelationCreateStaffName) {
		this.GoodsDishrelationCreateStaffName = goodsDishrelationCreateStaffName;
	}

	public String getGoodsDishrelationModifyStaffName() {
		return goodsDishrelationModifyStaffName;
	}

	public void setGoodsDishrelationModifyStaffName(
			String goodsDishrelationModifyStaffName) {
		this.goodsDishrelationModifyStaffName = goodsDishrelationModifyStaffName;
	}

	public Dish getDish() {
		return dish;
	}

	public void setDish(Dish dish) {
		this.dish = dish;
	}

	public Goods getGoods() {
		return goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}

	public GoodsDishRelation getGoodsDishRelation() {
		return goodsDishRelation;
	}

	public void setGoodsDishRelation(GoodsDishRelation goodsDishRelation) {
		this.goodsDishRelation = goodsDishRelation;
	}

	public String getCategoryGoodsCount() {
		return categoryGoodsCount;
	}

	public void setCategoryGoodsCount(String categoryGoodsCount) {
		this.categoryGoodsCount = categoryGoodsCount;
	}

	public String getGoodsStatusCode() {
		return goodsStatusCode;
	}

	public void setGoodsStatusCode(String goodsStatusCode) {
		this.goodsStatusCode = goodsStatusCode;
	}
	
}
