package com.xnradmin.client.action.front;

import java.util.HashMap;
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
	
	private ProductDetailVo productDetailVo;
	private String goodsId;
	private BusinessGoodsVO businessGoodsVO;
	private Map<String, List<BusinessGoods>> related_classification;
	public ProductDetailVo getProductDetailVo() {
		return productDetailVo;
	}
	public void setProductDetailVo(ProductDetailVo productDetailVo) {
		this.productDetailVo = productDetailVo;
	}
	public String getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}
	public BusinessGoodsVO getBusinessGoodsVO() {
		return businessGoodsVO;
	}
	public void setBusinessGoodsVO(BusinessGoodsVO businessGoodsVO) {
		this.businessGoodsVO = businessGoodsVO;
	}
	public Map<String, List<BusinessGoods>> getRelated_classification() {
		return related_classification;
	}
	public void setRelated_classification(
			Map<String, List<BusinessGoods>> related_classification) {
		this.related_classification = related_classification;
	}

	@Autowired
	private BusinessCategoryService businessCategoryService;
	@Autowired
	private BusinessGoodsService businessGoodsService;
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
		ProductDetailVo productDetailVo = new ProductDetailVo();
//		businessGoods = businessGoodsService.findByid(goodsId);
		businessGoodsVO = businessGoodsService.getBusinessGoodsAndWeight(goodsId);
		BusinessCategory businessCategory = businessCategoryService.findByid(businessGoodsVO.getBusinessGoods().getGoodsCategoryId());
		BusinessCategory businessCategoryF = businessCategoryService.findByid(businessCategory.getCategoryParentId().toString());
		productDetailVo.setFirstName(businessCategoryService.findByid(businessCategoryF.getCategoryParentId().toString()).getCategoryName());
		productDetailVo.setSecoundName(businessCategory.getCategoryName());
		productDetailVo.setFoodName(businessGoodsVO.getBusinessGoods().getGoodsName());
		this.productDetailVo = productDetailVo;
		Map<String, List<BusinessGoods>> related_classification = new HashMap<String, List<BusinessGoods>>();
		List<BusinessGoods> goods = businessGoodsService.getListBycategoryId(businessCategory.getId().toString());
		related_classification.put(businessCategory.getCategoryName(), goods);
		this.related_classification = related_classification;
		return StrutsResMSG.SUCCESS;
	}
	@Action(value="personalCenter",results = {@Result(name = StrutsResMSG.SUCCESS, location = "/front/personalCenter.jsp")})
	public String personalCenter()
	{
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

	
	
}
