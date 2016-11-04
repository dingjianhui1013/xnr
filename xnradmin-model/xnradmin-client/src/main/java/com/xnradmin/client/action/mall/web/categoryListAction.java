/**
 *2012-5-10 下午3:52:29
 */
package com.xnradmin.client.action.mall.web;

import java.io.IOException;
import java.math.BigDecimal;
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
import com.xnradmin.core.service.mall.clientUser.ClientUserInfoService;
import com.xnradmin.core.service.mall.commodity.CategoryService;
import com.xnradmin.core.service.mall.order.ShoppingCartService;
import com.xnradmin.po.client.ClientUserInfo;
import com.xnradmin.po.mall.order.ShoppingCart;
import com.xnradmin.vo.mall.CommodityVO;

/**
 * @autohr: xiang_liu
 * 
 */
@Controller
@Scope("prototype")
@Namespace("/page/wx/client/web/categorylist")
@ParentPackage("json-default")
public class categoryListAction extends ParentAction {
	
	@Autowired
	private CategoryService categoryService;

	@Autowired
	private ShoppingCartService shoppingCartService;

	@Autowired
	private ClientUserInfoService clientUserInfoService;
	
	@Autowired
	private StatusService statusService;
	
	private String categoryId;
	
	private String uid; //微信用户的OPENUID
	
	private String categoryName;
	
    private Integer categoryCount; //类型商品数量
    
    private String categoryParentId; //上级分类ID
	
    private Integer goodsCount; //商品数量
    
    private Float totalPrice; //总价
    
	private List<ShoppingCart> shoppingCartList;
	
	private List<CommodityVO> categoryList;

	
	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public List<ShoppingCart> getShoppingCartList() {
		return shoppingCartList;
	}

	public void setShoppingCartList(List<ShoppingCart> shoppingCartList) {
		this.shoppingCartList = shoppingCartList;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Integer getCategoryCount() {
		return categoryCount;
	}

	public void setCategoryCount(Integer categoryCount) {
		this.categoryCount = categoryCount;
	}

	public List<CommodityVO> getCategoryList() {
		return categoryList;
	}

	public void setCategoryList(List<CommodityVO> categoryList) {
		this.categoryList = categoryList;
	}

	public String getCategoryParentId() {
		return categoryParentId;
	}

	public void setCategoryParentId(String categoryParentId) {
		this.categoryParentId = categoryParentId;
	}

	public Integer getGoodsCount() {
		return goodsCount;
	}

	public void setGoodsCount(Integer goodsCount) {
		this.goodsCount = goodsCount;
	}

	public Float getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Float totalPrice) {
		this.totalPrice = totalPrice;
	}

	@Override
	public boolean isPublic() {
		return true;
	};

	static Log log = LogFactory.getLog(goodsListAction.class);


	/**
	 * HTML list.html
	 * 商品列表页显示
	 * @return
	 */
	@Action(value = "categoryList", results = { @Result(name = StrutsResMSG.SUCCESS, type = "json") })
	public String goodsList() throws IOException {
		categoryList = categoryService.webList(categoryParentId, "", "", "33", 
				"1", 0, 0, "sortId", "asc");
		if(!StringHelper.isNull(uid)){
			ClientUserInfo clientUserInfo = clientUserInfoService.findByProperty("wxopenuid",uid);
			if(clientUserInfo!=null && clientUserInfo.getId()!=null){
				shoppingCartList = shoppingCartService.webList(clientUserInfo.getId().toString(), null, null, 
						"1", null, null, 0, 0, "id", "asc");
				totalPrice = 0f;
				goodsCount = 0;
				if(shoppingCartList!=null && shoppingCartList.size()>0){
					for(int i = 0 ; shoppingCartList.size() > i ; i++){
						ShoppingCart tempShoppingCart = new ShoppingCart();
						tempShoppingCart = shoppingCartList.get(i);
						Float tempTotalPrice = tempShoppingCart.getTotalPrice();
						int tempGoodsCount = tempShoppingCart.getGoodsCount();
						BigDecimal b1 = new BigDecimal(tempTotalPrice);
						BigDecimal b2 = new BigDecimal(tempGoodsCount);
						BigDecimal a1 = new BigDecimal(totalPrice);
						BigDecimal a2 = new BigDecimal(goodsCount);
						//计算最终商品数量和资费
						totalPrice = a1.add(b1).floatValue();
						goodsCount = a2.add(b2).intValue();
					}
				}
			}
		}
		return StrutsResMSG.SUCCESS;
	}
}
