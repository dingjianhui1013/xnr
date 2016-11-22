package com.xnradmin.client.action.front;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.xnradmin.constant.StrutsResMSG;
import com.xnradmin.core.service.business.commodity.BusinessCategoryService;
import com.xnradmin.core.service.business.commodity.BusinessGoodsService;
import com.xnradmin.vo.front.ProductDetailVo;

@Controller
@Scope("prototype")
@Namespace("/front")
@ParentPackage("json-default")
public class IndexFrontAction  {
	
	private ProductDetailVo productDetailVo;
	private BusinessCategoryService businessCategoryService;
	private BusinessGoodsService businessGoodsService;
	public ProductDetailVo getProductDetailVo() {
		return productDetailVo;
	}
	public void setProductDetailVo(ProductDetailVo productDetailVo) {
		this.productDetailVo = productDetailVo;
	}
	@Action(value = "index", results = { @Result(name = StrutsResMSG.SUCCESS, location = "/front/index.jsp") })
	public String info() {
		
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
	
}
