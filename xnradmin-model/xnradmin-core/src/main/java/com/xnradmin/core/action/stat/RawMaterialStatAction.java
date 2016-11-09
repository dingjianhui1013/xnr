/**
 *2012-5-10 下午3:52:29
 */
package com.xnradmin.core.action.stat;
import java.util.ArrayList;
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
import com.xnradmin.core.service.common.status.StatusService;
import com.xnradmin.core.service.mall.commodity.CategoryService;
import com.xnradmin.core.service.stat.RawMaterialStatService;
import com.xnradmin.po.common.status.Status;
import com.xnradmin.po.dishes.Dish;
import com.xnradmin.po.dishes.RawMaterial;
import com.xnradmin.po.mall.commodity.Category;
import com.xnradmin.po.mall.commodity.Goods;
import com.xnradmin.po.mall.order.Purchase;

/**
 * @autohr: xiang_liu
 * 
 */
@Controller
@Scope("prototype")
@Namespace("/page/stat/rawmaterial")
@ParentPackage("json-default")
public class RawMaterialStatAction extends ParentAction {

	@Autowired
	private RawMaterialStatService rawMaterialStatService;

	@Autowired
	private StatusService statusService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Override
	public boolean isPublic() {
		return false;
	};

	static Log log = LogFactory.getLog(RawMaterialStatAction.class);

	private String purchaseId;
	private String clientUserId; //用户ID
	private String orderRecordId; //订单ID
	private String orderNo; //订单号
	private String goodsId; //商品ID
	private String goodsName; //商品名称
	private String goodsCategoryId; //商品类型ID
	private String goodsCategoryName; //商品类型名称
	private String goodsCount; //商品数量
	private String dishId; //菜品ID
	private String dishName; //菜品名称
	private String dishTypeId; //菜品类型ID
	private String dishTypeName; //菜品类型名称
	private String dishCount; //菜品数量
	private String rawMaterialId; //材料ID
	private String rawMaterialName; //材料名称
	private String rawMaterialTypeId; //材料类型ID
	private String rawMaterialTypeName; //材料类型名称
	private String weightId; //数量单位ID
	private String weightName; //数量单位名称
	private String number; //采购数量
    private String createStartTime; //搜索起始时间
    private String createEndTime; //搜索结束时间
	private List<String[]> contentList;
	private List<Category> goodsCategoryList;
	private List<Status> dishTypeList;
	private List<Status> rawMaterialTypeList;
	
	public String getPurchaseId() {
		return purchaseId;
	}

	public void setPurchaseId(String purchaseId) {
		this.purchaseId = purchaseId;
	}

	public String getClientUserId() {
		return clientUserId;
	}

	public void setClientUserId(String clientUserId) {
		this.clientUserId = clientUserId;
	}

	public String getOrderRecordId() {
		return orderRecordId;
	}

	public void setOrderRecordId(String orderRecordId) {
		this.orderRecordId = orderRecordId;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getGoodsCategoryId() {
		return goodsCategoryId;
	}

	public void setGoodsCategoryId(String goodsCategoryId) {
		this.goodsCategoryId = goodsCategoryId;
	}

	public String getGoodsCategoryName() {
		return goodsCategoryName;
	}

	public void setGoodsCategoryName(String goodsCategoryName) {
		this.goodsCategoryName = goodsCategoryName;
	}

	public String getGoodsCount() {
		return goodsCount;
	}

	public void setGoodsCount(String goodsCount) {
		this.goodsCount = goodsCount;
	}

	public String getDishId() {
		return dishId;
	}

	public void setDishId(String dishId) {
		this.dishId = dishId;
	}

	public String getDishName() {
		return dishName;
	}

	public void setDishName(String dishName) {
		this.dishName = dishName;
	}

	public String getDishTypeId() {
		return dishTypeId;
	}

	public void setDishTypeId(String dishTypeId) {
		this.dishTypeId = dishTypeId;
	}

	public String getDishTypeName() {
		return dishTypeName;
	}

	public void setDishTypeName(String dishTypeName) {
		this.dishTypeName = dishTypeName;
	}

	public String getDishCount() {
		return dishCount;
	}

	public void setDishCount(String dishCount) {
		this.dishCount = dishCount;
	}

	public String getRawMaterialId() {
		return rawMaterialId;
	}

	public void setRawMaterialId(String rawMaterialId) {
		this.rawMaterialId = rawMaterialId;
	}

	public String getRawMaterialName() {
		return rawMaterialName;
	}

	public void setRawMaterialName(String rawMaterialName) {
		this.rawMaterialName = rawMaterialName;
	}

	public String getRawMaterialTypeId() {
		return rawMaterialTypeId;
	}

	public void setRawMaterialTypeId(String rawMaterialTypeId) {
		this.rawMaterialTypeId = rawMaterialTypeId;
	}

	public String getRawMaterialTypeName() {
		return rawMaterialTypeName;
	}

	public void setRawMaterialTypeName(String rawMaterialTypeName) {
		this.rawMaterialTypeName = rawMaterialTypeName;
	}

	public String getWeightId() {
		return weightId;
	}

	public void setWeightId(String weightId) {
		this.weightId = weightId;
	}

	public String getWeightName() {
		return weightName;
	}

	public void setWeightName(String weightName) {
		this.weightName = weightName;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getCreateStartTime() {
		return createStartTime;
	}

	public void setCreateStartTime(String createStartTime) {
		this.createStartTime = createStartTime;
	}

	public String getCreateEndTime() {
		return createEndTime;
	}

	public void setCreateEndTime(String createEndTime) {
		this.createEndTime = createEndTime;
	}

	public List<String[]> getContentList() {
		return contentList;
	}

	public void setContentList(List<String[]> contentList) {
		this.contentList = contentList;
	}

	public List<Category> getGoodsCategoryList() {
		return goodsCategoryList;
	}

	public void setGoodsCategoryList(List<Category> goodsCategoryList) {
		this.goodsCategoryList = goodsCategoryList;
	}

	public List<Status> getRawMaterialTypeList() {
		return rawMaterialTypeList;
	}

	public void setRawMaterialTypeList(List<Status> rawMaterialTypeList) {
		this.rawMaterialTypeList = rawMaterialTypeList;
	}

	public List<Status> getDishTypeList() {
		return dishTypeList;
	}

	public void setDishTypeList(List<Status> dishTypeList) {
		this.dishTypeList = dishTypeList;
	}

	/**
	 * 加载商品类型
	 */
	private void findGoodsCategoryList() {
		this.goodsCategoryList = categoryService.listAll();
	}
	
	/*
	 * 加载菜品类型
	 * */
	private void findDishTypeList(){
		this.dishTypeList = statusService.find(Dish.class,"dishType");
	}
	
	/*
	 * 加载材料类型
	 * */
	private void findRawMaterialTypeList(){
		this.rawMaterialTypeList = statusService.find(RawMaterial.class,"materialType");
	}
	
	/**
	 * 材料清单汇总
	 * 
	 * @return
	 */
	@Action(value = "rawmaterialcount", results = {
			@Result(name = StrutsResMSG.SUCCESS, location = "/stat/rawmaterial/rawmaterialcount.jsp"),
			@Result(name = StrutsResMSG.FAILED, location = "/stat/rawmaterial/rawmaterialcount.jsp") })
	public String getRawMaterialCount() {
		setDateTime();
		findGoodsCategoryList();
		findDishTypeList();
		findRawMaterialTypeList();
		Purchase po = new Purchase();
		if(!StringHelper.isNull(purchaseId)){
			po.setId(Long.valueOf(purchaseId));
		}
		if(!StringHelper.isNull(clientUserId)){
			po.setClientUserId(Integer.parseInt(clientUserId));
		}
		if(!StringHelper.isNull(orderRecordId)){
			po.setOrderRecordId(Long.valueOf(orderRecordId));
		}
		po.setOrderNo(orderNo);
		if(!StringHelper.isNull(goodsId)){
			po.setGoodsId(Integer.parseInt(goodsId));
		}
		po.setGoodsName(goodsName);
		if(!StringHelper.isNull(dishId)){
			po.setDishId(Integer.parseInt(dishId));
		}
		po.setDishName(dishName);
		if(!StringHelper.isNull(rawMaterialId)){
			po.setRawMaterialId(Integer.parseInt(rawMaterialId));
		}
		po.setRawMaterialName(rawMaterialName);
		if(!StringHelper.isNull(rawMaterialTypeId)){
			po.setRawMaterialTypeId(Integer.parseInt(rawMaterialTypeId));
		}
		po.setRawMaterialTypeName(rawMaterialTypeName);
		if(!StringHelper.isNull(weightId)){
			po.setWeightId(Integer.parseInt(weightId));
		}
		po.setWeightName(weightName);
		if(!StringHelper.isNull(number)){
			po.setNumber(Float.valueOf(number));
		}
		if(!StringHelper.isNull(goodsCount)){
			po.setGoodsCount(Integer.parseInt(goodsCount));
		}
		if(!StringHelper.isNull(dishCount)){
			po.setDishCount(Integer.parseInt(dishCount));
		}
		if(!StringHelper.isNull(goodsCategoryId)){
			po.setGoodsCategoryId(Integer.parseInt(goodsCategoryId));
		}
		po.setGoodsCategoryName(goodsCategoryName);
		if(!StringHelper.isNull(dishTypeId)){
			po.setDishTypeId(Integer.parseInt(dishTypeId));
		}
		po.setDishTypeName(dishTypeName);
		
		List<Object[]> list = rawMaterialStatService.findPurchaseData(createStartTime, createEndTime, 
				po, getPageNum(), getNumPerPage(), orderField, orderDirection);
		this.totalCount = list.size();
		contentList = new ArrayList<String[]>();
		for (int i = 0; i < list.size(); i++) {
			String[] content = new String[3];
			Object[] a = list.get(i);
			if (a == null)
				continue;
			content[0] = a[0] == null ? "0" : a[0].toString();
			content[1] = a[1] == null ? "0" : a[1].toString();
			content[2] = a[2] == null ? "0" : a[2].toString();
			contentList.add(content);
		}
		return StrutsResMSG.SUCCESS;
	}

	private void setDateTime() {
		// 设置默认时间
		if (StringHelper.isNull(createStartTime)
				&& StringHelper.isNull(createEndTime)) {
			this.createStartTime = StringHelper.getSystime("yyyy-MM-dd");
			createStartTime = createStartTime + " 00:00:00";
			this.createEndTime = StringHelper.getSystime("yyyy-MM-dd");
			createEndTime = createEndTime + " 23:59:59";
		}
	}

}
