package com.xnradmin.client.action.front;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.xnradmin.client.service.IndexFrontService;
import com.xnradmin.constant.StrutsResMSG;
import com.xnradmin.core.service.business.commodity.BusinessCategoryService;
import com.xnradmin.core.service.business.commodity.BusinessGoodsService;
import com.xnradmin.vo.front.ProductDetailVo;
import com.xnradmin.po.business.BusinessCategory;
import com.xnradmin.po.business.BusinessGoods;
import com.xnradmin.vo.business.BusinessGoodsVO;

@Controller
@Scope("prototype")
@Namespace("/front")
@ParentPackage("json-default")
public class IndexFrontAction  {
	private String productCategoryId;//产品列表，分类id（三级）
	private String first;//产品列表，一级菜单名称
	private String three;//产品列表，三级菜单名称
	private List<BusinessGoodsVO> productList;//产品列表
	private ProductDetailVo productDetailVo;
	@Autowired
	private BusinessCategoryService businessCategoryService;
	@Autowired
	private BusinessGoodsService businessGoodsService;
	public ProductDetailVo getProductDetailVo() {
		return productDetailVo;
	}
	public void setProductDetailVo(ProductDetailVo productDetailVo) {
		this.productDetailVo = productDetailVo;
	}
	private List<Map<BusinessCategory, List<Map<BusinessCategory, List<BusinessCategory>>>>> allBusinessCategorys;
	private List<BusinessGoodsVO> indexGoods;
	@Autowired IndexFrontService indexFrontService;
	
	@Action(value = "index", results = { @Result(name = StrutsResMSG.SUCCESS, location = "/front/index.jsp") })
	public String info() {
		this.allBusinessCategorys = indexFrontService.getAllBusinessCategory();
		this.indexGoods = indexFrontService.listBusinessGoodsVO(0,8);
		return StrutsResMSG.SUCCESS;
	}
	@Action(value="productDetail",results = {@Result(name = StrutsResMSG.SUCCESS, location = "/front/productDetail.jsp")})
	public String productDetail()
	{
		//此处需要传来三个参数，一级分类id，二级分类id，菜品分类id。
		String firstName = businessCategoryService.findByid(productDetailVo.getFirstClassification()).getCategoryName();
		String secoundName = businessCategoryService.findByid(productDetailVo.getSecoundClassification()).getCategoryName();
		String foodName = businessGoodsService.findByid(productDetailVo.getFirstClassification()).getGoodsName();
		productDetailVo.setFirstName(firstName);
		productDetailVo.setSecoundName(secoundName);
		productDetailVo.setFoodName(foodName);
		return StrutsResMSG.SUCCESS;
	}
	
	@Action(value="product",results = {@Result(name = StrutsResMSG.SUCCESS, location = "/front/product.jsp")})
	public String product()
	{
		this.productList = indexFrontService.listBusinessGoodsByCategoryId(productCategoryId);
		this.allBusinessCategorys = indexFrontService.getAllBusinessCategory();
		this.indexGoods = indexFrontService.listBusinessGoodsVO(0,8);
		try {
			this.first = new String(this.first.getBytes("iso-8859-1"),"UTF-8"); 
			this.three = new String(this.three.getBytes("iso-8859-1"),"UTF-8"); 
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return StrutsResMSG.SUCCESS;
	}
	//getter And setter
	public List<Map<BusinessCategory, List<Map<BusinessCategory, List<BusinessCategory>>>>> getAllBusinessCategorys() {
		return allBusinessCategorys;
	}

	public void setAllBusinessCategorys(
			List<Map<BusinessCategory, List<Map<BusinessCategory, List<BusinessCategory>>>>> allBusinessCategorys) {
		this.allBusinessCategorys = allBusinessCategorys;
	}

	public List<BusinessGoodsVO> getIndexGoods() {
		return indexGoods;
	}

	public void setIndexGoods(List<BusinessGoodsVO> indexGoods) {
		this.indexGoods = indexGoods;
	}
	public String getProductCategoryId() {
		return productCategoryId;
	}
	public void setProductCategoryId(String productCategoryId) {
		this.productCategoryId = productCategoryId;
	}
	public String getFirst() {
		return first;
	}
	public void setFirst(String first) {
		this.first = first;
	}
	public String getThree() {
		return three;
	}
	public void setThree(String three) {
		this.three = three;
	}
	public List<BusinessGoodsVO> getProductList() {
		return productList;
	}
	public void setProductList(List<BusinessGoodsVO> productList) {
		this.productList = productList;
	}
	

	
	
}
