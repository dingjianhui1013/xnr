/**
 *2012-5-10 下午3:52:29
 */
package com.xnradmin.core.action.mall.commodity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.cntinker.util.StringHelper;
import com.xnradmin.constant.AjaxResult;
import com.xnradmin.constant.StrutsResMSG;
import com.xnradmin.core.action.ParentAction;
import com.xnradmin.core.service.StaffService;
import com.xnradmin.core.service.common.status.StatusService;
import com.xnradmin.core.service.dishes.CollocationService;
import com.xnradmin.core.service.dishes.DishService;
import com.xnradmin.core.service.mall.commodity.CategoryService;
import com.xnradmin.core.service.mall.commodity.GoodsDishRelationService;
import com.xnradmin.core.service.mall.commodity.GoodsService;
import com.xnradmin.po.CommonStaff;
import com.xnradmin.po.common.status.Status;
import com.xnradmin.po.dishes.Collocation;
import com.xnradmin.po.dishes.Dish;
import com.xnradmin.po.dishes.RawMaterial;
import com.xnradmin.po.mall.commodity.Category;
import com.xnradmin.po.mall.commodity.Goods;
import com.xnradmin.po.mall.commodity.GoodsDishRelation;
import com.xnradmin.vo.StaffVO;
import com.xnradmin.vo.dishes.DishesVO;
import com.xnradmin.vo.mall.CommodityVO;

/**
 * @autohr: xiang_liu
 * 
 */
@Controller
@Scope("prototype")
@Namespace("/page/wx/admin/commodity/goods")
@ParentPackage("json-default")
public class GoodsAction extends ParentAction {

	@Autowired
	private GoodsService goodsService;

	@Autowired
	private GoodsDishRelationService goodsDishRelationService;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private StatusService statusService;

	@Autowired
	private StaffService staffService;

	private String goodsId;
	private String goodsSortId; // 商品排序Id
	private String goodsParentId; // 上级商品Id
	private String goodsCategoryId; // 商品所属分类
	private String goodsCategoryName; // 商品所属分类名称
	private String goodsName; // 商品名称
	private String goodsDescription; // 商品描述
	private String goodsOriginalPrice; // 商品原价
	private String goodsPreferentialPrice; // 商品优惠价
	private String goodsBrandId; // 商品品牌ID
	private String goodsWeight; // 商品重量
	private String goodsStock; // 商品库存
	private String goodsStatus; // 商品状态上架下架
	private String isFreeLogistics; // 包邮不包邮
	private String isNewGoods; // 是否新商品
	private String isDiscountGoods; // 是否优惠商品
	private String isHotSaleGoods; // 是否热卖商品
	private String goodsLogo; // 商品图片
	private String goodsBigLogo; // 商品图片
	private File goodsLogoFile; // 商品图片上传文件
	private File goodsBigLogoFile; // 商品大图片上传文件
	private String goodsLogoFileFileName; // 商品图片名称
	private String goodsBigLogoFileFileName; // 商品大图片名称
	private String goodsLogoFileContentType;
	private String goodsBigLogoFileContentType;
	private String goodsStaffId; // 隶属用户Id
	private String goodsPrimaryConfigurationId; // 对应商城ID
	private String goodsCreateTime; // 建立时间
	private String goodsCreateStartTime; // 建立开始时间
	private String goodsCreateEndTime; // 建立结束时间
	private String goodsCreateStaffId; // 建立人
	private String goodsModifyTime; // 修改时间
	private String goodsModifyStartTime; // 修改开始时间
	private String goodsModifyEndTime; // 修改结束时间
	private String goodsModifyStaffId; // 修改人
	private String goodsLogoSavePath = "/themes/mall/goodsLogo/";
	private String goodsBigLogoSavePath = "/themes/mall/goodsBigLogo/";
	// GoodsDishRelation
	private String goodsDishRelationId;
	private String goodsDishRelationSortId; // 显示排序Id
	private String goodsDishRelationGoodsId; // 商品Id
	private String goodsDishRelationDishId; // 菜品Id
	private String goodsDishRelationDishCount; // 菜品数量
	private String goodsDishRelationStaffId; // 隶属用户Id
	private String goodsDishRelationPrimaryConfigurationId; // 对应商城ID
	private String goodsDishRelationCreateTime; // 建立时间
	private String goodsDishRelationCreateStartTime; // 建立开始时间
	private String goodsDishRelationCreateEndTime; // 建立结束时间
	private String goodsDishRelationCreateStaffId; // 建立人
	private String goodsDishRelationModifyTime; // 修改时间
	private String goodsDishRelationModifyStartTime; // 修改开始时间
	private String goodsDishRelationModifyEndTime; // 修改结束时间
	private String goodsDishRelationModifyStaffId; // 修改人

	private List<Status> isDiscountList;
	private List<Status> goodsStatusList;
	private List<StaffVO> staffList;
	private List<Category> categoryList;
	private Map<String, CommodityVO> dishList;
	private List<CommodityVO> dvList;
	private CommonStaff currentStaff;
	private List<CommodityVO> voList;
	private List<Goods> goodsList;
	private CommodityVO commodityVO;
	private Goods goods;
	private Goods goodsParent;
	private Category category;
	private Status status;
	private Status isDiscount;

	public StatusService getStatusService() {
		return statusService;
	}

	public void setStatusService(StatusService statusService) {
		this.statusService = statusService;
	}

	public GoodsService getGoodsService() {
		return goodsService;
	}

	public void setGoodsService(GoodsService goodsService) {
		this.goodsService = goodsService;
	}

	public StaffService getStaffService() {
		return staffService;
	}

	public void setStaffService(StaffService staffService) {
		this.staffService = staffService;
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

	public String getGoodsStaffId() {
		return goodsStaffId;
	}

	public void setGoodsStaffId(String goodsStaffId) {
		this.goodsStaffId = goodsStaffId;
	}

	public String getGoodsPrimaryConfigurationId() {
		return goodsPrimaryConfigurationId;
	}

	public void setGoodsPrimaryConfigurationId(
			String goodsPrimaryConfigurationId) {
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

	public List<Status> getIsDiscountList() {
		return isDiscountList;
	}

	public void setIsDiscountList(List<Status> isDiscountList) {
		this.isDiscountList = isDiscountList;
	}

	public List<Status> getGoodsStatusList() {
		return goodsStatusList;
	}

	public void setGoodsStatusList(List<Status> goodsStatusList) {
		this.goodsStatusList = goodsStatusList;
	}

	public List<StaffVO> getStaffList() {
		return staffList;
	}

	public void setStaffList(List<StaffVO> staffList) {
		this.staffList = staffList;
	}

	public List<Goods> getGoodsList() {
		return goodsList;
	}

	public void setGoodsList(List<Goods> goodsList) {
		this.goodsList = goodsList;
	}

	public Map<String, CommodityVO> getDishList() {
		return dishList;
	}

	public void setDishList(Map<String, CommodityVO> dishList) {
		this.dishList = dishList;
	}

	public List<CommodityVO> getDvList() {
		return dvList;
	}

	public void setDvList(List<CommodityVO> dvList) {
		this.dvList = dvList;
	}

	public CommonStaff getCurrentStaff() {
		return currentStaff;
	}

	public void setCurrentStaff(CommonStaff currentStaff) {
		this.currentStaff = currentStaff;
	}

	public List<CommodityVO> getVoList() {
		return voList;
	}

	public void setVoList(List<CommodityVO> voList) {
		this.voList = voList;
	}

	public CommodityVO getCommodityVO() {
		return commodityVO;
	}

	public void setCommodityVO(CommodityVO commodityVO) {
		this.commodityVO = commodityVO;
	}

	public Goods getGoods() {
		return goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}

	public Goods getGoodsParent() {
		return goodsParent;
	}

	public void setGoodsParent(Goods goodsParent) {
		this.goodsParent = goodsParent;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getGoodsCreateStartTime() {
		return goodsCreateStartTime;
	}

	public void setGoodsCreateStartTime(String goodsCreateStartTime) {
		this.goodsCreateStartTime = goodsCreateStartTime;
	}

	public String getGoodsCreateEndTime() {
		return goodsCreateEndTime;
	}

	public void setGoodsCreateEndTime(String goodsCreateEndTime) {
		this.goodsCreateEndTime = goodsCreateEndTime;
	}

	public String getGoodsModifyStartTime() {
		return goodsModifyStartTime;
	}

	public void setGoodsModifyStartTime(String goodsModifyStartTime) {
		this.goodsModifyStartTime = goodsModifyStartTime;
	}

	public String getGoodsModifyEndTime() {
		return goodsModifyEndTime;
	}

	public void setGoodsModifyEndTime(String goodsModifyEndTime) {
		this.goodsModifyEndTime = goodsModifyEndTime;
	}

	public Status getIsDiscount() {
		return isDiscount;
	}

	public void setIsDiscount(Status isDiscount) {
		this.isDiscount = isDiscount;
	}

	public GoodsDishRelationService getGoodsDishRelationService() {
		return goodsDishRelationService;
	}

	public void setGoodsDishRelationService(
			GoodsDishRelationService goodsDishRelationService) {
		this.goodsDishRelationService = goodsDishRelationService;
	}

	public String getGoodsDishRelationId() {
		return goodsDishRelationId;
	}

	public void setGoodsDishRelationId(String goodsDishRelationId) {
		this.goodsDishRelationId = goodsDishRelationId;
	}

	public String getGoodsDishRelationSortId() {
		return goodsDishRelationSortId;
	}

	public void setGoodsDishRelationSortId(String goodsDishRelationSortId) {
		this.goodsDishRelationSortId = goodsDishRelationSortId;
	}

	public String getGoodsDishRelationGoodsId() {
		return goodsDishRelationGoodsId;
	}

	public void setGoodsDishRelationGoodsId(String goodsDishRelationGoodsId) {
		this.goodsDishRelationGoodsId = goodsDishRelationGoodsId;
	}

	public String getGoodsDishRelationDishId() {
		return goodsDishRelationDishId;
	}

	public void setGoodsDishRelationDishId(String goodsDishRelationDishId) {
		this.goodsDishRelationDishId = goodsDishRelationDishId;
	}

	public String getGoodsDishRelationDishCount() {
		return goodsDishRelationDishCount;
	}

	public void setGoodsDishRelationDishCount(String goodsDishRelationDishCount) {
		this.goodsDishRelationDishCount = goodsDishRelationDishCount;
	}

	public String getGoodsDishRelationStaffId() {
		return goodsDishRelationStaffId;
	}

	public void setGoodsDishRelationStaffId(String goodsDishRelationStaffId) {
		this.goodsDishRelationStaffId = goodsDishRelationStaffId;
	}

	public String getGoodsDishRelationPrimaryConfigurationId() {
		return goodsDishRelationPrimaryConfigurationId;
	}

	public void setGoodsDishRelationPrimaryConfigurationId(
			String goodsDishRelationPrimaryConfigurationId) {
		this.goodsDishRelationPrimaryConfigurationId = goodsDishRelationPrimaryConfigurationId;
	}

	public String getGoodsDishRelationCreateTime() {
		return goodsDishRelationCreateTime;
	}

	public void setGoodsDishRelationCreateTime(
			String goodsDishRelationCreateTime) {
		this.goodsDishRelationCreateTime = goodsDishRelationCreateTime;
	}

	public String getGoodsDishRelationCreateStartTime() {
		return goodsDishRelationCreateStartTime;
	}

	public void setGoodsDishRelationCreateStartTime(
			String goodsDishRelationCreateStartTime) {
		this.goodsDishRelationCreateStartTime = goodsDishRelationCreateStartTime;
	}

	public String getGoodsDishRelationCreateEndTime() {
		return goodsDishRelationCreateEndTime;
	}

	public void setGoodsDishRelationCreateEndTime(
			String goodsDishRelationCreateEndTime) {
		this.goodsDishRelationCreateEndTime = goodsDishRelationCreateEndTime;
	}

	public String getGoodsDishRelationCreateStaffId() {
		return goodsDishRelationCreateStaffId;
	}

	public void setGoodsDishRelationCreateStaffId(
			String goodsDishRelationCreateStaffId) {
		this.goodsDishRelationCreateStaffId = goodsDishRelationCreateStaffId;
	}

	public String getGoodsDishRelationModifyTime() {
		return goodsDishRelationModifyTime;
	}

	public void setGoodsDishRelationModifyTime(
			String goodsDishRelationModifyTime) {
		this.goodsDishRelationModifyTime = goodsDishRelationModifyTime;
	}

	public String getGoodsDishRelationModifyStartTime() {
		return goodsDishRelationModifyStartTime;
	}

	public void setGoodsDishRelationModifyStartTime(
			String goodsDishRelationModifyStartTime) {
		this.goodsDishRelationModifyStartTime = goodsDishRelationModifyStartTime;
	}

	public String getGoodsDishRelationModifyEndTime() {
		return goodsDishRelationModifyEndTime;
	}

	public void setGoodsDishRelationModifyEndTime(
			String goodsDishRelationModifyEndTime) {
		this.goodsDishRelationModifyEndTime = goodsDishRelationModifyEndTime;
	}

	public String getGoodsDishRelationModifyStaffId() {
		return goodsDishRelationModifyStaffId;
	}

	public void setGoodsDishRelationModifyStaffId(
			String goodsDishRelationModifyStaffId) {
		this.goodsDishRelationModifyStaffId = goodsDishRelationModifyStaffId;
	}

	public String getGoodsCategoryName() {
		return goodsCategoryName;
	}

	public void setGoodsCategoryName(String goodsCategoryName) {
		this.goodsCategoryName = goodsCategoryName;
	}

	public File getGoodsLogoFile() {
		return goodsLogoFile;
	}

	public void setGoodsLogoFile(File goodsLogoFile) {
		this.goodsLogoFile = goodsLogoFile;
	}

	public String getGoodsLogoFileFileName() {
		return goodsLogoFileFileName;
	}

	public void setGoodsLogoFileFileName(String goodsLogoFileFileName) {
		this.goodsLogoFileFileName = goodsLogoFileFileName;
	}

	public String getGoodsLogoFileContentType() {
		return goodsLogoFileContentType;
	}

	public void setGoodsLogoFileContentType(String goodsLogoFileContentType) {
		this.goodsLogoFileContentType = goodsLogoFileContentType;
	}

	public void setGoodsLogoSavePath(String goodsLogoSavePath) {
		this.goodsLogoSavePath = goodsLogoSavePath;
	}

	public CategoryService getCategoryService() {
		return categoryService;
	}

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	public List<Category> getCategoryList() {
		return categoryList;
	}

	public void setCategoryList(List<Category> categoryList) {
		this.categoryList = categoryList;
	}

	public String getGoodsBigLogo() {
		return goodsBigLogo;
	}

	public void setGoodsBigLogo(String goodsBigLogo) {
		this.goodsBigLogo = goodsBigLogo;
	}

	public File getGoodsBigLogoFile() {
		return goodsBigLogoFile;
	}

	public void setGoodsBigLogoFile(File goodsBigLogoFile) {
		this.goodsBigLogoFile = goodsBigLogoFile;
	}

	public String getGoodsBigLogoFileFileName() {
		return goodsBigLogoFileFileName;
	}

	public void setGoodsBigLogoFileFileName(String goodsBigLogoFileFileName) {
		this.goodsBigLogoFileFileName = goodsBigLogoFileFileName;
	}

	public String getGoodsBigLogoFileContentType() {
		return goodsBigLogoFileContentType;
	}

	public void setGoodsBigLogoFileContentType(
			String goodsBigLogoFileContentType) {
		this.goodsBigLogoFileContentType = goodsBigLogoFileContentType;
	}

	@Override
	public boolean isPublic() {
		return false;
	};

	static Log log = LogFactory.getLog(GoodsAction.class);

	/**
	 * 跳转到信息页。。
	 * 
	 * @return
	 */
	@Action(value = "info", results = {
			@Result(name = StrutsResMSG.SUCCESS, location = "/wx/admin/commodity/goods/info.jsp"),
			@Result(name = StrutsResMSG.FAILED, location = "/wx/admin/commodity/goods/info.jsp") })
	public String info() {
		setPageInfo();
		return StrutsResMSG.SUCCESS;
	}

	/**
	 * 跳转到信息页。。
	 * 
	 * @return
	 */
	@Action(value = "lookup", results = {
			@Result(name = StrutsResMSG.SUCCESS, location = "/wx/admin/commodity/goods/lookup.jsp"),
			@Result(name = StrutsResMSG.FAILED, location = "/wx/admin/commodity/goods/lookup.jsp") })
	public String lookup() {
		setPageInfo();
		return StrutsResMSG.SUCCESS;
	}

	private void setPageInfo() {
		// 设置排序
		findGoodsStatusList();
		findIsDiscountList();
		findStaffList();
		findCategoryList();
		findGoodsList();
		this.voList = goodsService.listVO(goodsCategoryId, goodsParentId,
				goodsName, goodsBrandId, goodsStatus,
				goodsPrimaryConfigurationId, isFreeLogistics, isNewGoods,
				isDiscountGoods, isHotSaleGoods, goodsStaffId,
				goodsCreateStartTime, goodsCreateEndTime, goodsCreateStaffId,
				goodsModifyStartTime, goodsModifyEndTime, goodsModifyStaffId,
				getPageNum(), getNumPerPage(), orderField, orderDirection);
		this.totalCount = goodsService.getCount(goodsCategoryId, goodsParentId,
				goodsName, goodsBrandId, goodsStatus,
				goodsPrimaryConfigurationId, isFreeLogistics, isNewGoods,
				isDiscountGoods, isHotSaleGoods, goodsStaffId,
				goodsCreateStartTime, goodsCreateEndTime, goodsCreateStaffId,
				goodsModifyStartTime, goodsModifyEndTime, goodsModifyStaffId);
	}

	/**
	 * 是否有效
	 */
	private void findGoodsStatusList() {
		this.goodsStatusList = statusService.find(Goods.class, "goodsStatus");
	}

	/**
	 * 是否优惠
	 */
	private void findIsDiscountList() {
		this.isDiscountList = statusService
				.find(Goods.class, "isDiscountGoods");
	}

	/**
	 * 加载所有商品
	 */
	private void findGoodsList() {
		this.goodsList = goodsService.listAll();
	}

	/*
	 * 加载所有用户
	 */
	private void findStaffList() {
		this.staffList = staffService.listVO("","","", "", "", "", "", "", "", 0, 0,
				0, "", "");
	}

	/*
	 * 加载所有类型
	 */
	private void findCategoryList() {
		this.categoryList = categoryService.listAll();
	}

	/*
	 * 加载商品图片上传路径
	 */
	public String getGoodsLogoSavePath() throws Exception {
		// return
		// "C:/work/eclipse-jee-kepler-R-win32-x86_64/eclipse/javaworkspace/xnradmin/xnradmin-web"
		// + "/src/main/webapp/themes/mall/goodsLogo";
		return ServletActionContext.getServletContext().getRealPath("")
				+ goodsLogoSavePath;
	}

	/*
	 * 加载商品大图片上传路径
	 */
	public String getGoodsBigLogoSavePath() throws Exception {
		// return
		// "C:/work/eclipse-jee-kepler-R-win32-x86_64/eclipse/javaworkspace/xnradmin/xnradmin-web"
		// + "/src/main/webapp/themes/mall/goodsBigLogo";
		return ServletActionContext.getServletContext().getRealPath("")
				+ goodsBigLogoSavePath;
	}

	@Action(value = "addinfo", results = {
			@Result(name = StrutsResMSG.SUCCESS, location = "/wx/admin/commodity/goods/add.jsp"),
			@Result(name = StrutsResMSG.FAILED, location = "/wx/admin/commodity/goods/add.jsp") })
	public String addInfo() {
		findGoodsStatusList();
		findIsDiscountList();
		findCategoryList();
		findGoodsList();
		currentStaff = super.getCurrentStaff();
		return StrutsResMSG.SUCCESS;
	}

	/**
	 * 保存对象接口
	 * 
	 * @return String
	 * @throws Exception
	 */
	@Action(value = "add", results = { @Result(name = StrutsResMSG.SUCCESS, type = "plainText") })
	public String add() throws Exception {
		String newGoodsLogo = "";
		if (goodsLogoFile != null) {
			goodsLogoFileFileName = UUID.randomUUID().toString()
					+ goodsLogoFileFileName;
			newGoodsLogo = getGoodsLogoSavePath() + "/" + goodsLogoFileFileName;
			FileOutputStream fos = new FileOutputStream(newGoodsLogo);
			FileInputStream fis = new FileInputStream(goodsLogoFile);
			byte[] buffer = new byte[1024];
			int len = 0;
			while ((len = fis.read(buffer)) > 0) {
				fos.write(buffer, 0, len);
			}
			fos.close();
			fis.close();
		}
		String newGoodsBigLogo = "";
		if (goodsBigLogoFile != null) {
			goodsBigLogoFileFileName = UUID.randomUUID().toString()
					+ goodsBigLogoFileFileName;
			newGoodsBigLogo = getGoodsBigLogoSavePath() + "/"
					+ goodsBigLogoFileFileName;
			FileOutputStream fos = new FileOutputStream(newGoodsBigLogo);
			FileInputStream fis = new FileInputStream(goodsBigLogoFile);
			byte[] buffer = new byte[1024];
			int len = 0;
			while ((len = fis.read(buffer)) > 0) {
				fos.write(buffer, 0, len);
			}
			fos.close();
			fis.close();
		}
		// 取得当前登录人信息
		currentStaff = super.getCurrentStaff();
		// 批量增加原料
		// if (goodsService.findByGoodsName(goodsName, "") > 0) {
		// super.error("商品名已存在");
		// } else

		if (dishList == null) {
			super.error("未填写菜品");
		} else {
			System.out.println("start:::");
			// 增加新商品
			Goods goods = new Goods();
			goods.setGoodsName(goodsName);
			if (goodsStatus != null) {
				goods.setGoodsStatus(Integer.parseInt(goodsStatus));
			}
			if (goodsParent != null && goodsParent.getId() != null) {
				goods.setGoodsParentId(goodsParent.getId().toString());
			}
			if (category.getId() != null) {
				goods.setGoodsCategoryId(category.getId().toString());
			}
			if (category.getPrimaryConfigurationId() != null) {
				goods.setPrimaryConfigurationId(category
						.getPrimaryConfigurationId());
			}
			goods.setGoodsDescription(goodsDescription);
			if (goodsOriginalPrice != null) {
				goods.setGoodsOriginalPrice(Float.valueOf(goodsOriginalPrice));
			}
			if (goodsPreferentialPrice != null) {
				goods.setGoodsPreferentialPrice(Float
						.valueOf(goodsPreferentialPrice));
			}
			if (goodsBrandId != null) {
				goods.setGoodsBrandId(Integer.parseInt(goodsBrandId));
			}
			if (goodsWeight != null) {
				goods.setGoodsWeight(Float.valueOf(goodsWeight));
			}
			if (goodsStock != null) {
				goods.setGoodsStock(Integer.parseInt(goodsStock));
			}
			if (isFreeLogistics != null) {
				goods.setIsFreeLogistics(Integer.parseInt(isFreeLogistics));
			}
			if (isNewGoods != null) {
				goods.setIsNewGoods(Integer.parseInt(isNewGoods));
			}
			if (isDiscountGoods != null) {
				goods.setIsDiscountGoods(Integer.parseInt(isDiscountGoods));
			}
			if (isHotSaleGoods != null) {
				goods.setIsHotSaleGoods(Integer.parseInt(isHotSaleGoods));
			}
			if (goodsLogoFile != null) {
				goods.setGoodsLogo(goodsLogoSavePath + goodsLogoFileFileName);
			}
			if (goodsBigLogoFile != null) {
				goods.setGoodsBigLogo(goodsBigLogoSavePath
						+ goodsBigLogoFileFileName);
			}
			if (goodsSortId != null) {
				goods.setSortId(Integer.parseInt(goodsSortId));
			}
			goods.setCreateStaffId(currentStaff.getId());
			goods.setCreateTime(new Timestamp(System.currentTimeMillis()));
			goods.setModifyStaffId(currentStaff.getId());
			goods.setModifyTime(new Timestamp(System.currentTimeMillis()));
			int goodsId = goodsService.save(goods);
			// 为新菜品添加原料
			Iterator<String> it = dishList.keySet().iterator();
			while (it.hasNext()) {
				String key = it.next();
				CommodityVO dvo = dishList.get(key);
				GoodsDishRelation cdr = new GoodsDishRelation();
				Dish dish = dvo.getDish();
				GoodsDishRelation gdr = dvo.getGoodsDishRelation();
				cdr.setGoodsId(goodsId);
				cdr.setDishId(dish.getId());
				cdr.setDishCount(gdr.getDishCount());
				cdr.setSortId(gdr.getSortId());
				cdr.setStaffId(goods.getStaffId());
				cdr.setPrimaryConfigurationId(goods.getPrimaryConfigurationId());
				cdr.setCreateStaffId(currentStaff.getId());
				cdr.setCreateTime(new Timestamp(System.currentTimeMillis()));
				cdr.setModifyStaffId(currentStaff.getId());
				cdr.setModifyTime(new Timestamp(System.currentTimeMillis()));
				goodsDishRelationService.save(cdr);
			}
			System.out.println("end:::");
		}
		super.success(null, AjaxResult.CALL_BACK_TYPE_CLOSECURRENT, "goods",
				null);
		return null;
	}

	/**
	 * 带信息到修改页
	 * 
	 * @return String
	 */
	@Action(value = "modifyinfo", results = {
			@Result(name = StrutsResMSG.SUCCESS, location = "/wx/admin/commodity/goods/modify.jsp"),
			@Result(name = StrutsResMSG.FAILED, location = "/wx/admin/commodity/goods/modify.jsp") })
	public String modifyinfo() {
		findGoodsStatusList();
		findIsDiscountList();
		findStaffList();
		findCategoryList();
		findGoodsList();
		// 加载该商品所有菜品
		dvList = goodsDishRelationService.listVO(goodsId,
				goodsDishRelationDishId, goodsDishRelationSortId,
				goodsDishRelationDishCount,
				goodsDishRelationPrimaryConfigurationId,
				goodsDishRelationStaffId, goodsDishRelationCreateStartTime,
				goodsDishRelationCreateEndTime, goodsDishRelationCreateStaffId,
				goodsDishRelationModifyStartTime,
				goodsDishRelationModifyEndTime, goodsDishRelationModifyStaffId,
				getPageNum(), getNumPerPage(), orderField, orderDirection);
		// 加载该商品信息
		goods = goodsService.findByid(goodsId);
		this.goodsName = goods.getGoodsName();
		this.goodsCategoryId = goods.getGoodsCategoryId();
		Category category = categoryService
				.findByid(goods.getGoodsCategoryId());
		this.goodsCategoryName = (category.getCategoryName());
		if (goods.getSortId() != null) {
			this.goodsSortId = goods.getSortId().toString();
		}
		if (goods.getGoodsParentId() != null) {
			this.goodsParentId = goods.getGoodsParentId().toString();
		}
		this.goodsDescription = goods.getGoodsDescription();
		if (goods.getGoodsOriginalPrice() != null) {
			this.goodsOriginalPrice = goods.getGoodsOriginalPrice().toString();
		}
		if (goods.getGoodsPreferentialPrice() != null) {
			this.goodsPreferentialPrice = goods.getGoodsPreferentialPrice()
					.toString();
		}

		if (goods.getGoodsBrandId() != null) {
			this.goodsBrandId = goods.getGoodsBrandId().toString();
		}
		if (goods.getGoodsWeight() != null) {
			this.goodsWeight = goods.getGoodsWeight().toString();
		}
		if (goods.getGoodsStock() != null) {
			this.goodsStock = goods.getGoodsStock().toString();
		}
		if (goods.getGoodsStatus() != null) {
			this.goodsStatus = goods.getGoodsStatus().toString();
		}
		if (goods.getIsFreeLogistics() != null) {
			this.isFreeLogistics = goods.getIsFreeLogistics().toString();
		}
		if (goods.getIsNewGoods() != null) {
			this.isNewGoods = goods.getIsNewGoods().toString();
		}
		if (goods.getIsDiscountGoods() != null) {
			this.isDiscountGoods = goods.getIsDiscountGoods().toString();
		}
		if (goods.getIsHotSaleGoods() != null) {
			this.isHotSaleGoods = goods.getIsHotSaleGoods().toString();
		}
		if (goods.getGoodsLogo() != null) {
			this.goodsLogo = goods.getGoodsLogo().toString();
		}
		if (goods.getGoodsBigLogo() != null) {
			this.goodsBigLogo = goods.getGoodsBigLogo().toString();
		}
		if (goods.getStaffId() != null) {
			this.goodsStaffId = goods.getStaffId().toString();
		}
		if (goods.getPrimaryConfigurationId() != null) {
			this.goodsPrimaryConfigurationId = goods
					.getPrimaryConfigurationId().toString();
		}
		this.goodsCreateStaffId = goods.getCreateStaffId().toString();
		this.goodsModifyStaffId = goods.getModifyStaffId().toString();
		this.goodsCreateStartTime = goods.getCreateTime().toString();
		this.goodsModifyStartTime = goods.getModifyTime().toString();
		return StrutsResMSG.SUCCESS;
	}

	/**
	 * 更新对象接口
	 * 
	 * @return String
	 * @throws Exception
	 */
	@Action(value = "modify", results = { @Result(name = StrutsResMSG.SUCCESS, type = "plainText") })
	public String modify() throws Exception {
		log.debug("modif action!");
		log.debug("modify goods: " + goodsId);
		String newGoodsLogo = "";
		if (goodsLogoFile != null) {
			goodsLogoFileFileName = UUID.randomUUID().toString()
					+ goodsLogoFileFileName;
			newGoodsLogo = getGoodsLogoSavePath() + "/" + goodsLogoFileFileName;
			FileOutputStream fos = new FileOutputStream(newGoodsLogo);
			FileInputStream fis = new FileInputStream(goodsLogoFile);
			byte[] buffer = new byte[1024];
			int len = 0;
			while ((len = fis.read(buffer)) > 0) {
				fos.write(buffer, 0, len);
			}
			fos.close();
			fis.close();
		}
		String newGoodsBigLogo = "";
		if (goodsBigLogoFile != null) {
			goodsBigLogoFileFileName = UUID.randomUUID().toString()
					+ goodsBigLogoFileFileName;
			newGoodsBigLogo = getGoodsBigLogoSavePath() + "/"
					+ goodsBigLogoFileFileName;
			FileOutputStream fos = new FileOutputStream(newGoodsBigLogo);
			FileInputStream fis = new FileInputStream(goodsBigLogoFile);
			byte[] buffer = new byte[1024];
			int len = 0;
			while ((len = fis.read(buffer)) > 0) {
				fos.write(buffer, 0, len);
			}
			fos.close();
			fis.close();
		}
		// 取得当前登录人信息
		currentStaff = super.getCurrentStaff();
		// 批量增加菜品
		// if (goodsService.findByGoodsName(goodsName, goodsId) > 0) {
		// super.error("商品名已存在");
		// } else
		if (dishList == null) {
			super.error("未填写菜品");
		} else {
			System.out.println("start:::");
			// 修改商品
			Goods tempGoods = new Goods();
			Goods oldGoods = new Goods();
			oldGoods = goodsService.findByid(goodsId);
			tempGoods.setId(Integer.parseInt(goodsId));
			tempGoods.setGoodsName(goodsName);
			if (goodsStatus != null) {
				tempGoods.setGoodsStatus(Integer.parseInt(goodsStatus));
			} else {
				tempGoods.setGoodsStatus(oldGoods.getGoodsStatus());
			}
			if (goodsParent != null && goodsParent.getId() != null) {
				tempGoods.setGoodsParentId(goodsParent.getId().toString());
			} else {
				tempGoods.setGoodsParentId(oldGoods.getGoodsParentId());
			}
			if (category.getId() != null) {
				tempGoods.setGoodsCategoryId(category.getId().toString());
			} else {
				tempGoods.setGoodsCategoryId(oldGoods.getGoodsCategoryId());
			}
			if (category.getPrimaryConfigurationId() != null) {
				tempGoods.setPrimaryConfigurationId(category
						.getPrimaryConfigurationId());
			} else {
				tempGoods.setPrimaryConfigurationId(oldGoods
						.getPrimaryConfigurationId());
			}
			if (goodsDescription != null) {
				tempGoods.setGoodsDescription(goodsDescription);
			} else {
				tempGoods.setGoodsDescription(oldGoods.getGoodsDescription());
			}
			if (goodsOriginalPrice != null) {
				tempGoods.setGoodsOriginalPrice(Float
						.valueOf(goodsOriginalPrice));
			} else {
				tempGoods.setGoodsOriginalPrice(oldGoods
						.getGoodsOriginalPrice());
			}
			if (goodsPreferentialPrice != null) {
				tempGoods.setGoodsPreferentialPrice(Float
						.valueOf(goodsPreferentialPrice));
			} else {
				tempGoods.setGoodsPreferentialPrice(oldGoods
						.getGoodsPreferentialPrice());
			}
			if (goodsBrandId != null) {
				tempGoods.setGoodsBrandId(Integer.parseInt(goodsBrandId));
			} else {
				tempGoods.setGoodsBrandId(oldGoods.getGoodsBrandId());
			}
			if (goodsWeight != null) {
				tempGoods.setGoodsWeight(Float.valueOf(goodsWeight));
			} else {
				tempGoods.setGoodsWeight(oldGoods.getGoodsWeight());
			}
			if (goodsStock != null) {
				tempGoods.setGoodsStock(Integer.parseInt(goodsStock));
			} else {
				tempGoods.setGoodsStock(oldGoods.getGoodsStock());
			}
			if (isFreeLogistics != null) {
				tempGoods.setIsFreeLogistics(Integer.parseInt(isFreeLogistics));
			} else {
				tempGoods.setIsFreeLogistics(oldGoods.getIsFreeLogistics());
			}
			if (isNewGoods != null) {
				tempGoods.setIsNewGoods(Integer.parseInt(isNewGoods));
			} else {
				tempGoods.setIsNewGoods(oldGoods.getIsNewGoods());
			}
			if (isDiscountGoods != null) {
				tempGoods.setIsDiscountGoods(Integer.parseInt(isDiscountGoods));
			} else {
				tempGoods.setIsDiscountGoods(oldGoods.getIsDiscountGoods());
			}
			if (isHotSaleGoods != null) {
				tempGoods.setIsHotSaleGoods(Integer.parseInt(isHotSaleGoods));
			} else {
				tempGoods.setIsHotSaleGoods(oldGoods.getIsHotSaleGoods());
			}
			if (goodsLogoFile != null) {
				tempGoods.setGoodsLogo(goodsLogoSavePath
						+ goodsLogoFileFileName);
			} else {
				tempGoods.setGoodsLogo(oldGoods.getGoodsLogo());
			}
			if (goodsBigLogoFile != null) {
				tempGoods.setGoodsBigLogo(goodsBigLogoSavePath
						+ goodsBigLogoFileFileName);
			} else {
				tempGoods.setGoodsBigLogo(oldGoods.getGoodsBigLogo());
			}
			if (goodsSortId != null) {
				tempGoods.setSortId(Integer.parseInt(goodsSortId));
			} else {
				tempGoods.setSortId(oldGoods.getSortId());
			}
			tempGoods.setCreateStaffId(currentStaff.getId());
			tempGoods.setCreateTime(new Timestamp(System.currentTimeMillis()));
			tempGoods.setModifyStaffId(currentStaff.getId());
			tempGoods.setModifyTime(new Timestamp(System.currentTimeMillis()));
			goodsService.modify(tempGoods);
			// 删除该商品所有菜品
			goodsDishRelationService.removeGoodsId(Integer.parseInt(goodsId));
			// 为商品添加菜品
			Iterator<String> it = dishList.keySet().iterator();
			while (it.hasNext()) {
				String key = it.next();
				CommodityVO dvo = dishList.get(key);
				GoodsDishRelation cdr = new GoodsDishRelation();
				Dish dish = dvo.getDish();
				GoodsDishRelation gdr = dvo.getGoodsDishRelation();
				cdr.setGoodsId(Integer.parseInt(goodsId));
				cdr.setDishId(dish.getId());
				cdr.setDishCount(gdr.getDishCount());
				cdr.setSortId(gdr.getSortId());
				if (tempGoods != null && tempGoods.getStaffId() != null) {
					cdr.setStaffId(tempGoods.getStaffId());
				}
				cdr.setPrimaryConfigurationId(tempGoods
						.getPrimaryConfigurationId());
				cdr.setCreateStaffId(currentStaff.getId());
				cdr.setCreateTime(new Timestamp(System.currentTimeMillis()));
				cdr.setModifyStaffId(currentStaff.getId());
				cdr.setModifyTime(new Timestamp(System.currentTimeMillis()));
				goodsDishRelationService.save(cdr);
			}
			System.out.println("end:::");
		}
		super.success(null, AjaxResult.CALL_BACK_TYPE_CLOSECURRENT, "goods",
				null);
		return null;
	}

	@Action(value = "del", results = { @Result(name = StrutsResMSG.SUCCESS, type = "plainText") })
	public String del() throws IOException, JSONException {
		goodsService.removeGoodsId(goodsId);
		goodsDishRelationService.removeGoodsId(Integer.parseInt(goodsId));
		super.success(null, AjaxResult.CALL_BACK_TYPE_CLOSECURRENT, "goods",
				null);
		return null;
	}

	/**
	 * 外部调用，获取所有组织信息
	 * 
	 * @return String
	 * @throws IOException
	 */
	@Action(value = "all", results = { @Result(name = StrutsResMSG.SUCCESS, type = "plainText") })
	public String all() throws IOException {
		super.toJsonArray(goodsService.listAll());
		return null;
	}
}
