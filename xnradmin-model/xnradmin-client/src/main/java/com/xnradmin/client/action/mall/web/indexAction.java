/**
 *2012-5-10 下午3:52:29
 */
package com.xnradmin.client.action.mall.web;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.cntinker.util.StringHelper;
import com.xnradmin.constant.StrutsResMSG;
import com.xnradmin.core.action.ParentAction;
import com.xnradmin.core.service.StaffService;
import com.xnradmin.core.service.common.status.StatusService;
import com.xnradmin.core.service.mall.clientUser.ClientUserInfoService;
import com.xnradmin.core.service.mall.commodity.CategoryService;
import com.xnradmin.core.service.mall.commodity.GoodsService;
import com.xnradmin.core.service.mall.order.ShoppingCartService;
import com.xnradmin.core.service.mall.seting.PrimaryConfigurationService;
import com.xnradmin.po.client.ClientUserInfo;
import com.xnradmin.po.common.status.Status;
import com.xnradmin.po.mall.commodity.Category;
import com.xnradmin.po.mall.commodity.Goods;
import com.xnradmin.vo.mall.CommodityVO;

;

/**
 * @autohr: xiang_liu
 * 
 */
@Controller
@Scope("prototype")
@Namespace("/page/wx/client/web/index")
@ParentPackage("json-default")
public class indexAction extends ParentAction {

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private GoodsService goodsService;

	@Autowired
	private ShoppingCartService shoppingCartService;
	
	@Autowired
	private ClientUserInfoService clientUserInfoService;

	@Autowired
	private StatusService statusService;

	private String categoryId;
	private String categorySortId; // 排序Id
	private String categoryParentId; // 上级分类Id
	private String categoryLevel; // 分类等级
	private String firstLevel; // 是否是根分类
	private String categoryName; // 分类名称
	private String categoryLogo; // 分类图片
	private File categoryLogoFile; // 分类图片上传文件
	private String categoryLogoFileFileName; // 分类图片名称
	private String categoryLogoFileContentType;
	private String categoryHeadLogo; // 分类置顶图片
	private File categoryHeadLogoFile; // 分类置顶图片
	private String categoryHeadLogoFileFileName; // 分类置顶图片名称
	private String categoryHeadLogoFileContentType;
	private String categoryDescription; // 分类描述
	private String categoryStatus; // 分类状态
	private String goodsName; // 商品名称
	private String isFreeLogistics; // 包邮
	private String isNewGoods; // 新品
	private String isDiscountGoods; // 优惠
	private String isHotSaleGoods; // 热门
	private String uid; // 微信用户的OPENUID
	private int userShoppingCartCount;
	private List<Category> categoryList;
	private List<Goods> goodsList;

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

	public void setCategoryParentId(String categoryParentId) {
		this.categoryParentId = categoryParentId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public File getCategoryLogoFile() {
		return categoryLogoFile;
	}

	public void setCategoryLogoFile(File categoryLogoFile) {
		this.categoryLogoFile = categoryLogoFile;
	}

	public File getCategoryHeadLogoFile() {
		return categoryHeadLogoFile;
	}

	public void setCategoryHeadLogoFile(File categoryHeadLogoFile) {
		this.categoryHeadLogoFile = categoryHeadLogoFile;
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

	public String getCategoryLogoFileFileName() {
		return categoryLogoFileFileName;
	}

	public void setCategoryLogoFileFileName(String categoryLogoFileFileName) {
		this.categoryLogoFileFileName = categoryLogoFileFileName;
	}

	public String getCategoryHeadLogoFileFileName() {
		return categoryHeadLogoFileFileName;
	}

	public void setCategoryHeadLogoFileFileName(
			String categoryHeadLogoFileFileName) {
		this.categoryHeadLogoFileFileName = categoryHeadLogoFileFileName;
	}

	public String getCategoryLevel() {
		return categoryLevel;
	}

	public void setCategoryLevel(String categoryLevel) {
		this.categoryLevel = categoryLevel;
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

	public List<Category> getCategoryList() {
		return categoryList;
	}

	public void setCategoryList(List<Category> categoryList) {
		this.categoryList = categoryList;
	}

	public String getCategoryLogoFileContentType() {
		return categoryLogoFileContentType;
	}

	public void setCategoryLogoFileContentType(
			String categoryLogoFileContentType) {
		this.categoryLogoFileContentType = categoryLogoFileContentType;
	}

	public String getCategoryHeadLogoFileContentType() {
		return categoryHeadLogoFileContentType;
	}

	public void setCategoryHeadLogoFileContentType(
			String categoryHeadLogoFileContentType) {
		this.categoryHeadLogoFileContentType = categoryHeadLogoFileContentType;
	}

	public CategoryService getCategoryService() {
		return categoryService;
	}

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	public String getFirstLevel() {
		return firstLevel;
	}

	public void setFirstLevel(String firstLevel) {
		this.firstLevel = firstLevel;
	}

	public List<Goods> getGoodsList() {
		return goodsList;
	}

	public void setGoodsList(List<Goods> goodsList) {
		this.goodsList = goodsList;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
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

	public int getUserShoppingCartCount() {
		return userShoppingCartCount;
	}

	public void setUserShoppingCartCount(int userShoppingCartCount) {
		this.userShoppingCartCount = userShoppingCartCount;
	}

	@Override
	public boolean isPublic() {
		return true;
	};

	static Log log = LogFactory.getLog(indexAction.class);

	/**
	 * HTML index.html 首页显示
	 * 
	 * @return
	 */
	@Action(value = "index", results = { @Result(name = StrutsResMSG.SUCCESS, type = "json") })
	public String categoryList() throws IOException {
		if (!StringHelper.isNull(uid) && !uid.equals("null")) {
			ClientUserInfo clientUserInfo = clientUserInfoService
					.findByProperty("wxopenuid", uid);
			if (clientUserInfo == null || clientUserInfo.getId() == null) {
				ClientUserInfo po = new ClientUserInfo();
				po.setWxopenuid(uid);
				po.setIswxuser(1);
				po.setStatus(139);
				Status statusCode = statusService.findByid("139");
				po.setStatusName(statusCode.getStatusName());
				po.setType(153);
				Status typeCode = statusService.findByid("153");
				po.setTypeName(typeCode.getStatusName());
				po.setCreateTime(new Timestamp(System.currentTimeMillis()));
				po.setLastLoginTime(new Timestamp(System.currentTimeMillis()));
				po.setModifyTime(new Timestamp(System.currentTimeMillis()));
				clientUserInfoService.saveWx(po);
			}
			this.userShoppingCartCount = shoppingCartService.getCount(clientUserInfo.getId().toString(), "", 
					null, "1", null, null, null, null);
		}
		this.categoryList = categoryService.webIndex("0", categoryName, "0",
				"33", "1", 0, 0, "sortId", "asc");
//		this.goodsList = goodsService.webList("1","0", goodsName, null, "118", "1",
//				isFreeLogistics, isNewGoods, isDiscountGoods, isHotSaleGoods,
//				0, 0, "sortId", "asc");
		return StrutsResMSG.SUCCESS;
	}

	/**
	 * 外部调用，获取所有组织信息
	 * 
	 * @return String
	 * @throws IOException
	 */
	@Action(value = "all", results = { @Result(name = StrutsResMSG.SUCCESS, type = "plainText") })
	public String all() throws IOException {
		super.toJsonArray(categoryService.listAll());
		return null;
	}
}
