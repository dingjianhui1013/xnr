/**
 *2012-5-10 下午3:52:29
 */
package com.xnradmin.client.action.mall.web;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
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
import com.xnradmin.core.service.mall.order.SearchService;
import com.xnradmin.core.service.mall.order.ShoppingCartService;
import com.xnradmin.core.service.mall.seting.PrimaryConfigurationService;
import com.xnradmin.core.util.Log4jUtil;
import com.xnradmin.dto.wx.WxUserCookie;
import com.xnradmin.po.client.ClientUserInfo;
import com.xnradmin.po.common.status.Status;
import com.xnradmin.po.mall.commodity.Category;
import com.xnradmin.po.mall.commodity.Goods;
import com.xnradmin.po.mall.order.Search;
import com.xnradmin.po.mall.order.ShoppingCart;
import com.xnradmin.vo.mall.OrderVO;

/**
 * @autohr: xiang_liu
 * 
 */
@Controller
@Scope("prototype")
@Namespace("/page/wx/client/web/goodslist")
@ParentPackage("json-default")
public class goodsListAction extends ParentAction {
	
	@Autowired
	private GoodsService goodsService;

	@Autowired
	private ShoppingCartService shoppingCartService;

	@Autowired
	private ClientUserInfoService clientUserInfoService;
	
	@Autowired
	private SearchService searchService;
	
	@Autowired
	private StatusService statusService;
	
	private String categoryId;
	
	private String uid; //微信用户的OPENUID
	
	private String goodsName;
	
    private Integer goodsCount; //商品数量
    
    private Float  currentPrice; //当前单价
    
    private Float totalPrice; //总价
    
	private List<ShoppingCart> shoppingCartList;
	
	private List<Goods> goodsList;

	public List<Goods> getGoodsList() {
		return goodsList;
	}

	public void setGoodsList(List<Goods> goodsList) {
		this.goodsList = goodsList;
	}

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

	public Integer getGoodsCount() {
		return goodsCount;
	}

	public void setGoodsCount(Integer goodsCount) {
		this.goodsCount = goodsCount;
	}

	public Float getCurrentPrice() {
		return currentPrice;
	}

	public void setCurrentPrice(Float currentPrice) {
		this.currentPrice = currentPrice;
	}

	public List<ShoppingCart> getShoppingCartList() {
		return shoppingCartList;
	}

	public void setShoppingCartList(List<ShoppingCart> shoppingCartList) {
		this.shoppingCartList = shoppingCartList;
	}

	public Float getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Float totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
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
	@Action(value = "goodsList", results = { @Result(name = StrutsResMSG.SUCCESS, type = "json") })
	public String goodsList() throws IOException {
		goodsList = goodsService.webList(categoryId, null, null, null, "118", 
				"1", null, null, null, null, 0, 0, "sortId", "asc");
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
	
	
	/**
	 * HTML list.html
	 * 商品列表页显示
	 * @return
	 */
	@Action(value = "searchList", results = { @Result(name = StrutsResMSG.SUCCESS, type = "json") })
	public String searchList() throws IOException {
		goodsList = goodsService.webList(null, null, goodsName, null, "118", 
				"1", null, null, null, null, 0, 0, "sortId", "asc");
		if(!StringHelper.isNull(uid)){
			ClientUserInfo clientUserInfo = clientUserInfoService.findByProperty("wxopenuid",uid);
			if(clientUserInfo!=null && clientUserInfo.getId()!=null){
				//增加搜索词汇入表
				Search search = new Search();
				search.setClientUserId(clientUserInfo.getId());
				search.setSearchValue(goodsName);
				search.setSearchTime(new Timestamp(System.currentTimeMillis()));
				searchService.save(search);
				
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
