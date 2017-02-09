package com.xnradmin.client.action.front;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.cntinker.util.StringHelper;
import com.xnradmin.client.service.IndexFrontService;
import com.xnradmin.constant.StrutsResMSG;
import com.xnradmin.core.action.ParentAction;
import com.xnradmin.core.action.alipay.AlipayAction;
import com.xnradmin.core.service.business.commodity.BusinessCategoryService;
import com.xnradmin.core.service.business.commodity.BusinessGoodsService;
import com.xnradmin.core.service.business.order.BusinessOrderGoodsRelationService;
import com.xnradmin.core.service.business.order.BusinessOrderRecordService;
import com.xnradmin.core.service.common.status.StatusService;
import com.xnradmin.core.service.mall.commodity.GoodsAllocationShowService;
import com.xnradmin.po.business.BusinessCategory;
import com.xnradmin.po.business.BusinessGoods;
import com.xnradmin.po.business.BusinessOrderRecord;
import com.xnradmin.po.common.status.Status;
import com.xnradmin.po.front.FrontUser;
import com.xnradmin.po.front.ReceiptAddress;
import com.xnradmin.po.mall.commodity.GoodsAllocationShow;
import com.xnradmin.vo.business.BusinessGoodsVO;
import com.xnradmin.vo.business.BusinessOrderRelationVO;
import com.xnradmin.vo.business.BusinessOrderVO;
import com.xnradmin.vo.front.ProductDetailVo;

@Controller
@Scope("prototype")
@Namespace("/front")
@ParentPackage("json-default")
public class IndexFrontAction  extends ParentAction{
	private static Logger log = Logger.getLogger(IndexFrontAction.class);
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
	private String createStartTime;
	private String createEndTime;
	private String operateStatus;
	private List<Status> paymentTypeList;
	private List<Status> paymentStatusList;
	private List<Status> paymentProviderList;
	private List<Status> operateStatusList;
	private List<Status> deliveryStatusList;
	private List<BusinessOrderVO> voList;
	private Integer clientUserId;
	private Integer RPageNum =0;
	private Integer RTotalPage;
	private String businessOrderRecordId;
	private BusinessOrderVO businessOrderVO;
	private GoodsAllocationShow goodsAllocationShow;//该商品被今天被分配的数量
	private String status;
	public BusinessOrderVO getBusinessOrderVO() {
		return businessOrderVO;
	}
	public void setBusinessOrderVO(BusinessOrderVO businessOrderVO) {
		this.businessOrderVO = businessOrderVO;
	}
	public String getBusinessOrderRecordId() {
		return businessOrderRecordId;
	}
	public void setBusinessOrderRecordId(String businessOrderRecordId) {
		this.businessOrderRecordId = businessOrderRecordId;
	}
	public Integer getRPageNum() {
		if(this.RPageNum <= 0)
            return 1;
        else
            return RPageNum;
	}
	public Integer getRTotalPage() {
		return RTotalPage;
	}
	public void setRPageNum(Integer rPageNum) {
		if(rPageNum <= 0)
            this.RPageNum = 1;
        else
            this.RPageNum = rPageNum;
	}
	public void setRTotalPage(Integer rTotalPage) {
		RTotalPage = rTotalPage;
	}
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
	public String getCreateStartTime() {
		return createStartTime;
	}
	public String getCreateEndTime() {
		return createEndTime;
	}
	public String getOperateStatus() {
		return operateStatus;
	}
	public List<Status> getPaymentTypeList() {
		return paymentTypeList;
	}
	public List<Status> getPaymentStatusList() {
		return paymentStatusList;
	}
	public List<Status> getPaymentProviderList() {
		return paymentProviderList;
	}
	public List<Status> getOperateStatusList() {
		return operateStatusList;
	}
	public List<BusinessOrderVO> getVoList() {
		return voList;
	}
	public Integer getClientUserId() {
		return clientUserId;
	}
	public void setCreateStartTime(String createStartTime) {
		this.createStartTime = createStartTime;
	}
	public void setCreateEndTime(String createEndTime) {
		this.createEndTime = createEndTime;
	}
	public void setOperateStatus(String operateStatus) {
		this.operateStatus = operateStatus;
	}
	public void setPaymentTypeList(List<Status> paymentTypeList) {
		this.paymentTypeList = paymentTypeList;
	}
	public void setPaymentStatusList(List<Status> paymentStatusList) {
		this.paymentStatusList = paymentStatusList;
	}
	public void setPaymentProviderList(List<Status> paymentProviderList) {
		this.paymentProviderList = paymentProviderList;
	}
	public void setOperateStatusList(List<Status> operateStatusList) {
		this.operateStatusList = operateStatusList;
	}
	public void setVoList(List<BusinessOrderVO> voList) {
		this.voList = voList;
	}
	public void setClientUserId(Integer clientUserId) {
		this.clientUserId = clientUserId;
	}
	public List<Status> getDeliveryStatusList() {
		return deliveryStatusList;
	}
	public void setDeliveryStatusList(List<Status> deliveryStatusList) {
		this.deliveryStatusList = deliveryStatusList;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}


	@Autowired
	private BusinessCategoryService businessCategoryService;
	@Autowired
	private BusinessOrderGoodsRelationService businessOrderGoodsRelationService;
	@Autowired
	private BusinessGoodsService businessGoodsService;
	@Autowired
	private StatusService statusService;
	
	@Autowired
	private GoodsAllocationShowService allocationShowService;
	
	@Autowired
	private BusinessOrderRecordService orderRecordService;    
	private List<Map<BusinessCategory, List<Map<BusinessCategory, List<BusinessCategory>>>>> allBusinessCategorys;
	private List<BusinessGoodsVO> indexGoods;
	@Autowired IndexFrontService indexFrontService;
	
	@Action(value = "index", results = { @Result(name = StrutsResMSG.SUCCESS, location = "/front/index.jsp") })
	public String info() {
		log.debug("success==============================");
		HttpSession session = ServletActionContext.getRequest().getSession();
//		FrontUser user = (FrontUser)session.getAttribute("alipayToIndex_user");
//		if(user!=null)
//		{
//			session.setAttribute("user", user);
//		}
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
		//查询该商品的可以购买数量
		this.goodsAllocationShow = allocationShowService.findByGoodsidToday(goodsId);
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
		this.numPerPage = 2;
		this.receiptAddressList = indexFrontService.getListAddress(user,getRPageNum(),numPerPage);
		int count = indexFrontService.getAddressCount(user);
		if(count%numPerPage==0)
		{
			this.RTotalPage = count/numPerPage;
		}else
		{
			this.RTotalPage =count/numPerPage +1 ;
		}
		this.clientUserId = clientUserId = Integer.parseInt(user.getId().toString());
		setFrontPageInfo();
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
	 * 订单详情
	 * @return
	 */
	@Action(value="orderDetail",results = {@Result(name = StrutsResMSG.SUCCESS, location = "/front/orderDetail.jsp")})
	public String orderDetail()
	{
		this.allBusinessCategorys = indexFrontService.getAllBusinessCategory();
		this.indexGoods = indexFrontService.listBusinessGoodsVO();
		BusinessOrderRecord orderRecord = orderRecordService.findByid(businessOrderRecordId);
		List<BusinessOrderRelationVO> bogr = businessOrderGoodsRelationService.findByOrderRecordId(Long.parseLong(businessOrderRecordId));
		BusinessOrderVO orderVOList = new BusinessOrderVO();
		orderVOList.setBusinessOrderRecord(orderRecord);
		orderVOList.setBusinessOrderRelationVO(bogr);
		this.businessOrderVO = orderVOList;
		return StrutsResMSG.SUCCESS;
	}
	/**
	 * 搜索
	 * @return
	 */
	@Action(value="search",results = {@Result(name = StrutsResMSG.FAILED, type="redirect" ,location = "/front/index.action"),@Result(name = StrutsResMSG.SUCCESS ,location = "/front/product.jsp")})
	public String search()
	{	
		if(null==this.search||"".equals(this.search)){
			return StrutsResMSG.FAILED;
		}
		this.productList = indexFrontService.listBusinessGoodsByGoodsName(this.search);
		this.allBusinessCategorys = indexFrontService.getAllBusinessCategory();
		
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
	
	@Action(value="confirmReceipt",results = {@Result(name=StrutsResMSG.SUCCESS,type="json")})
	public String confirmReceipt()
	{
		try {
			BusinessOrderRecord businessOrderRecord = orderRecordService.findByid(businessOrderRecordId);
			businessOrderRecord.setDeliveryStatus(209);
			businessOrderRecord.setDeliveryStatusName("已完成");
			orderRecordService.modify(businessOrderRecord);
			status = "1";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			status = "0";
		}
		return StrutsResMSG.SUCCESS;
	}
	private void setFrontPageInfo()
	{
		setDateTime();
		findPaymentStatusList();
		findPaymentProviderList();
		findOperateStatusList();
		findDeliveryStatus();
		findPaymentTypeList();
		BusinessOrderVO vo = new BusinessOrderVO();
		BusinessOrderRecord po = new BusinessOrderRecord();
		po.setClientUserId(clientUserId);
		vo.setBusinessOrderRecord(po);
		vo.setGroupBy("record.id");
		vo.setOrderBy("record.staffId");
		vo.setOrderByField("desc");
		this.numPerPage=2;
//		voList = orderRecordService.listVO2(vo,getPageNum(),numPerPage, null, null);
		voList = orderRecordService.listVO3(vo,getPageNum(),numPerPage, null, null);
		this.voList = voList;
		vo.setGroupBy("");
		String select = "select count(distinct record.id) ";
		int count = orderRecordService.getCount2(select, vo);
		int totalPage= 0;
		if(count%numPerPage==0)
		{
			totalPage = count/numPerPage;
		}else
		{
			totalPage = count/numPerPage + 1;
		} 
		this.totalCount = totalPage;
		
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
		if (StringHelper.isNull(operateStatus)) {
			this.operateStatus = "204";
		}

	}
	/**
	 * 加载所有支付状态
	 */
	private void findPaymentStatusList() {
		this.paymentStatusList = statusService.find(BusinessOrderRecord.class,
				"BusinessPaymentStatus");
	}

	/**
	 * 加载所有支付提供者类型
	 */
	private void findPaymentProviderList() {
		this.paymentProviderList = statusService.find(
				BusinessOrderRecord.class, "BusinessPaymentProvider");
	}

	/**
	 * 加载所有订单处理状态
	 */
	private void findOperateStatusList() {
		this.operateStatusList = statusService.find(BusinessOrderRecord.class,
				"BusinessOperateStatus");
	}

	/**
	 * 加载所有订单派送状态
	 */
	private void findDeliveryStatus() {
		this.deliveryStatusList = statusService.find(BusinessOrderRecord.class,
				"BusinessDeliveryStatus");
	}

	/**
	 * 加载所有支付类型
	 */
	private void findPaymentTypeList() {
		this.paymentTypeList = statusService.find(BusinessOrderRecord.class,
				"BusinessPaymentType");
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
	@Override
	public boolean isPublic() {
		// TODO Auto-generated method stub
		return true;
	}
	public GoodsAllocationShow getGoodsAllocationShow() {
		return goodsAllocationShow;
	}
	public void setGoodsAllocationShow(GoodsAllocationShow goodsAllocationShow) {
		this.goodsAllocationShow = goodsAllocationShow;
	}
	
}
