package com.xnradmin.client.action.front;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
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
import com.xnradmin.po.business.BusinessCategory;
import com.xnradmin.po.business.BusinessGoods;
import com.xnradmin.po.front.FrontUser;
import com.xnradmin.po.front.ReceiptAddress;
import com.xnradmin.vo.business.BusinessGoodsVO;
import com.xnradmin.vo.front.ProductDetailVo;

@Controller
@Scope("prototype")
@Namespace("/front")
@ParentPackage("json-default")
public class IndexFrontAction  {
	private String productCategoryId;//产品列表，分类id（三级）
	private String first;//产品列表，一级菜单名称
	private String three;//产品列表，三级菜单名称
	private List<BusinessGoodsVO> productList;//产品列表
	private String search;//搜索条件
	private ProductDetailVo productDetailVo;
	private String goodsId;
	private BusinessGoodsVO businessGoodsVO;
	private Map<String, List<BusinessGoods>> related_classification;
	private ReceiptAddress receiptAddress;
	private List<ReceiptAddress> receiptAddressList;
	private String setDefaultId;
	private String flag;
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
	public ReceiptAddress getReceiptAddress() {
		return receiptAddress;
	}
	public void setReceiptAddress(ReceiptAddress receiptAddress) {
		this.receiptAddress = receiptAddress;
	}
	public List<ReceiptAddress> getReceiptAddressList() {
		return receiptAddressList;
	}
	public void setReceiptAddressList(List<ReceiptAddress> receiptAddressList) {
		this.receiptAddressList = receiptAddressList;
	}
	public String getSetDefaultId() {
		return setDefaultId;
	}
	public void setSetDefaultId(String setDefaultId) {
		this.setDefaultId = setDefaultId;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
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
		this.indexGoods = indexFrontService.listBusinessGoodsVO();
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
		productDetailVo.setFirstClassification(businessCategoryService.findByid(businessCategoryF.getCategoryParentId().toString()).getId().toString());
		productDetailVo.setSecoundName(businessCategory.getCategoryName());
		productDetailVo.setFoodName(businessGoodsVO.getBusinessGoods().getGoodsName());
		this.productDetailVo = productDetailVo;
		Map<String, List<BusinessGoods>> related_classification = new HashMap<String, List<BusinessGoods>>();
		List<BusinessGoods> goods = businessGoodsService.getListBycategoryId(businessCategory.getId().toString());
		related_classification.put(businessCategory.getCategoryName(), goods);
		this.related_classification = related_classification;
		this.allBusinessCategorys = indexFrontService.getAllBusinessCategory();
//		this.indexGoods = indexFrontService.listBusinessGoodsVO();
		return StrutsResMSG.SUCCESS;
	}
	/**
	 * 个人中心
	 * @return
	 */
	@Action(value="personalCenter",results = {@Result(name = StrutsResMSG.SUCCESS, location = "/front/personalCenter.jsp"),@Result(name = StrutsResMSG.FAILED, location = "/front/login.jsp")})
	public String personalCenter()
	{	
		HttpSession session = ServletActionContext.getRequest().getSession();
		FrontUser user = (FrontUser)session.getAttribute("user");
		if(null==user){
			return StrutsResMSG.FAILED;
		}
		this.flag = flag;
		this.allBusinessCategorys = indexFrontService.getAllBusinessCategory();
		this.receiptAddressList = indexFrontService.getListAddress(user);
		return StrutsResMSG.SUCCESS;
	}
	/**
	 * 产品列表
	 * @return
	 */
	@Action(value="product",results = {@Result(name = StrutsResMSG.SUCCESS, location = "/front/product.jsp")})
	public String product()
	{
		this.productList = indexFrontService.listBusinessGoodsByCategoryId(productCategoryId);
		this.allBusinessCategorys = indexFrontService.getAllBusinessCategory();
//		this.indexGoods = indexFrontService.listBusinessGoodsVO();
//			this.first = new String(this.first.getBytes("iso-8859-1"),"UTF-8"); 
//			this.three = new String(this.three.getBytes("iso-8859-1"),"UTF-8"); 
		this.first = first;
		this.three = three;
		return StrutsResMSG.SUCCESS;
	}
	/**
	 * 搜索
	 * @return
	 */
	@Action(value="search",results = {@Result(name = StrutsResMSG.FAILED, type="redirect" ,location = "/front/index.action"),@Result(name = StrutsResMSG.SUCCESS, type="redirect" ,location = "/front/productDetail.action?goodsId=${goodsId}")})
	public String search()
	{	
		if(null==this.search||"".equals(this.search)){
			return StrutsResMSG.FAILED;
		}
		BusinessGoods findGoodsByName = indexFrontService.findGoodsByName(this.search);
		if(null == findGoodsByName){
			return StrutsResMSG.FAILED;
		}
		this.goodsId = findGoodsByName.getId().toString();
		return StrutsResMSG.SUCCESS;
	}
	@Action(value="addAddress",results = {@Result(name = StrutsResMSG.SUCCESS, type="redirect",location = "/front/personalCenter.action",params = {"flag", "address" })})
	public String addAddress()
	{
		List<ReceiptAddress> list =  indexFrontService.findAddress(receiptAddress.getFrontUserId());
		if(list.isEmpty())
		{
			receiptAddress.setType("1");
		}else
		{
			receiptAddress.setType("0");
		}
		indexFrontService.saveAddress(receiptAddress);
		return StrutsResMSG.SUCCESS;
	}
	@Action(value="setDefault",results = {@Result(name = StrutsResMSG.SUCCESS,type="redirect", location = "/front/personalCenter.action",params = {"flag", "address" })})
	public String setDefault()
	{
		indexFrontService.changeDefault(receiptAddress);
		indexFrontService.setDefault(setDefaultId);
		return StrutsResMSG.SUCCESS;
	}
	@Action(value="deleteAddress",results = {@Result(name = StrutsResMSG.SUCCESS,type="redirect",location = "/front/personalCenter.action",params = {"flag", "address" })})
	public String deleteAddress()
	{
		indexFrontService.deleteAddress(receiptAddress);
//		this.locationId = "address";
		return StrutsResMSG.SUCCESS;
	}
	@Action(value="modifyAddress",results = {@Result(name = StrutsResMSG.SUCCESS, type="json")})
	public String modifyAddress()
	{
		this.receiptAddress = indexFrontService.getAddress(receiptAddress);
		return StrutsResMSG.SUCCESS;
	}
	@Action(value="saveModifyAddress",results = {@Result(name = StrutsResMSG.SUCCESS,type="redirect", location = "/front/personalCenter.action",params = {"flag", "address" })})
	public String saveModifyAddress()
	{
		indexFrontService.updateAddress(receiptAddress);
		return StrutsResMSG.SUCCESS;
	}
	@Action(value="contact",results = {@Result(name = StrutsResMSG.SUCCESS, location = "/front/contact.jsp")})
	public String contact()
	{
		this.allBusinessCategorys = indexFrontService.getAllBusinessCategory();
		return StrutsResMSG.SUCCESS;
	}
	@Action(value="pay",results = { @Result(name = StrutsResMSG.SUCCESS, location = "/front/pay.jsp") })
	public String pay()
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
	public String getSearch() {
		return search;
	}
	public void setSearch(String search) {
		this.search = search;
	}
	

	
	
}
