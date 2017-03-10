package com.xnradmin.client.action.front;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.cntinker.util.StringHelper;
import com.xnradmin.client.service.front.ReceiptAddressService;
import com.xnradmin.constant.StrutsResMSG;
import com.xnradmin.core.action.ParentAction;
import com.xnradmin.core.pay.wxpay.util.QRcodeUtils;
import com.xnradmin.core.service.StaffService;
import com.xnradmin.core.service.business.combo.ComboService;
import com.xnradmin.core.service.business.combo.ComboUserService;
import com.xnradmin.core.service.business.commodity.BusinessGoodsService;
import com.xnradmin.core.service.business.order.BusinessOrderGoodsRelationService;
import com.xnradmin.core.service.business.order.BusinessOrderRecordService;
import com.xnradmin.core.service.common.status.StatusService;
import com.xnradmin.core.service.mall.clientUser.ClientUserInfoService;
import com.xnradmin.core.service.mall.clientUser.ClientUserRegionInfoService;
import com.xnradmin.core.service.mall.commodity.GoodsAllocationShowService;
import com.xnradmin.core.service.mall.order.ShoppingCartService;
import com.xnradmin.core.service.mall.region.AreaService;
import com.xnradmin.core.service.mall.seting.LogisticsCompanyService;
import com.xnradmin.core.service.mall.seting.PrimaryConfigurationService;
import com.xnradmin.core.service.pay.AlipayService;
import com.xnradmin.core.service.pay.wx.ScanPayService;
import com.xnradmin.core.util.Log4jUtil;
import com.xnradmin.po.CommonStaff;
import com.xnradmin.po.business.BusinessGoods;
import com.xnradmin.po.business.BusinessOrderGoodsRelation;
import com.xnradmin.po.business.BusinessOrderRecord;
import com.xnradmin.po.business.Combo;
import com.xnradmin.po.business.ComboUser;
import com.xnradmin.po.client.ClientUserInfo;
import com.xnradmin.po.common.status.Status;
import com.xnradmin.po.front.FrontUser;
import com.xnradmin.po.front.ReceiptAddress;
import com.xnradmin.po.mall.commodity.GoodsAllocationShow;
import com.xnradmin.po.mall.order.ShoppingCart;
import com.xnradmin.po.mall.seting.LogisticsCompany;
import com.xnradmin.po.mall.seting.PrimaryConfiguration;
import com.xnradmin.po.pay.Alipay;
import com.xnradmin.po.pay.protocol.pay_protocol.ScanPayReqData;
import com.xnradmin.po.wx.connect.WXfInit;
import com.xnradmin.vo.business.BusinessGoodsVO;
import com.xnradmin.vo.business.BusinessOrderVO;
import com.xnradmin.vo.business.ComboVO;
import com.xnradmin.vo.front.BusinessGoodsCartVo;

/**
 * @autohr: xiang_liu
 * 
 */
@Controller
@Scope("prototype")
@Namespace("/front/orderrecord")
@ParentPackage("json-default")
public class BusinessOrderRecodeAction extends ParentAction {

	@Autowired
	private BusinessOrderRecordService orderRecordService;

	
	@Autowired
	private ReceiptAddressService addressService;
	
	@Autowired
	private ShoppingCartService shoppingCartService;
	
	@Autowired
	private BusinessOrderGoodsRelationService orderGoodsRelationService;

	@Autowired
	private BusinessGoodsService goodsService;

	@Autowired
	private ClientUserInfoService clientUserInfoService;

	@Autowired
	private ClientUserRegionInfoService clientUserRegionInfoService;

	@Autowired
	private LogisticsCompanyService logisticsCompanyService;

	@Autowired
	private PrimaryConfigurationService primaryConfigurationService;

	@Autowired
	private StatusService statusService;

	@Autowired
	private AreaService areaService;

	@Autowired
	private StaffService staffService;
	@Autowired
	private AlipayService alipayService;

	@Autowired
	private GoodsAllocationShowService allocationShowService;
	
	@Autowired
	private ComboService comboService;
	
	@Autowired
	private ComboUserService comboUserService;
	
	private Log exLog = Log4jUtil.getLog("ex");

	private Log coopLog = Log4jUtil.getLog("coopLog");

	private FrontUser user;
	private List<BusinessGoodsCartVo> cartVoList;
	private  List<ReceiptAddress> addrs;
	
	
	private Alipay alipay;
	private String receiptName;
	private String detailedAddress;
	private String telAddress;
	
	private Long businessOrderRecodeId;
	
	private String listJson; // List页面返回的JSON值
	private String goods;
	private String userReal;
	private String clientUserRegionId;
	private String userRealMobile;
	private String userRealAddress;
	private String userRealName;
	private String areaId;
	private String staffId;
	private String payType;
	private String startTime;
	private String endTime;
	private String errorMsg;
	private String orderRecordId;
	private String requiredDeliveryTime;
	private String data;
	private String cpUserId;
	private List<BusinessOrderVO> businessOrderVoList;
	private List<BusinessGoods> goodsList;
	List<BusinessOrderVO> voList;
	private String userDesc;

	
	private String cartids;
	private String totalMoney;
	private Integer receiptAddressId;
	private Integer paymethod;
	private String outTradeNo;
	
	//订单与分配库存数的有关变量
	private String cartidcount;//订单每一项的订单数目
	private Map<String,Integer> goodsCountMap = new HashMap<String,Integer>();//每一个产品的订单数
	private String msg;//库存不足的提示信息
	private List<ComboVO> comboVOs;
	
	
	
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}



	public String getCartids() {
		return cartids;
	}

	public void setCartids(String cartids) {
		this.cartids = cartids;
	}
	
	

	public String getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(String totalMoney) {
		this.totalMoney = totalMoney;
	}

	public String getCartidcount() {
		return cartidcount;
	}

	public void setCartidcount(String cartidcount) {
		this.cartidcount = cartidcount;
	}

	public Map<String, Integer> getGoodsCountMap() {
		return goodsCountMap;
	}

	public void setGoodsCountMap(Map<String, Integer> goodsCountMap) {
		this.goodsCountMap = goodsCountMap;
	}

	public Integer getReceiptAddressId() {
		return receiptAddressId;
	}

	public void setReceiptAddressId(Integer receiptAddressId) {
		this.receiptAddressId = receiptAddressId;
	}

	public String getListJson() {
		return listJson;
	}

	public void setListJson(String listJson) {
		this.listJson = listJson;
	}

	public String getReceiptName() {
		return receiptName;
	}

	public void setReceiptName(String receiptName) {
		this.receiptName = receiptName;
	}

	public String getDetailedAddress() {
		return detailedAddress;
	}

	public void setDetailedAddress(String detailedAddress) {
		this.detailedAddress = detailedAddress;
	}

	public String getTelAddress() {
		return telAddress;
	}

	public void setTelAddress(String telAddress) {
		this.telAddress = telAddress;
	}

	public String getGoods() {
		return goods;
	}

	public void setGoods(String goods) {
		this.goods = goods;
	}

	public String getUserReal() {
		return userReal;
	}

	public void setUserReal(String userReal) {
		this.userReal = userReal;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getClientUserRegionId() {
		return clientUserRegionId;
	}

	public void setClientUserRegionId(String clientUserRegionId) {
		this.clientUserRegionId = clientUserRegionId;
	}

	public String getUserRealMobile() {
		return userRealMobile;
	}

	public void setUserRealMobile(String userRealMobile) {
		this.userRealMobile = userRealMobile;
	}

	public String getUserRealAddress() {
		return userRealAddress;
	}

	public void setUserRealAddress(String userRealAddress) {
		this.userRealAddress = userRealAddress;
	}

	public String getUserRealName() {
		return userRealName;
	}

	public void setUserRealName(String userRealName) {
		this.userRealName = userRealName;
	}

	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	public String getStaffId() {
		return staffId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

	public String getCpUserId() {
		return cpUserId;
	}

	public void setCpUserId(String cpUserId) {
		this.cpUserId = cpUserId;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public String getOrderRecordId() {
		return orderRecordId;
	}

	public void setOrderRecordId(String orderRecordId) {
		this.orderRecordId = orderRecordId;
	}

	public List<BusinessOrderVO> getBusinessOrderVoList() {
		return businessOrderVoList;
	}

	public void setBusinessOrderVoList(List<BusinessOrderVO> businessOrderVoList) {
		this.businessOrderVoList = businessOrderVoList;
	}

	public List<BusinessGoods> getGoodsList() {
		return goodsList;
	}

	public void setGoodsList(List<BusinessGoods> goodsList) {
		this.goodsList = goodsList;
	}

	public List<BusinessOrderVO> getVoList() {
		return voList;
	}

	public void setVoList(List<BusinessOrderVO> voList) {
		this.voList = voList;
	}

	public String getRequiredDeliveryTime() {
		return requiredDeliveryTime;
	}

	public void setRequiredDeliveryTime(String requiredDeliveryTime) {
		this.requiredDeliveryTime = requiredDeliveryTime;
	}
	
	
	
	

	public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public Alipay getAlipay() {
		return alipay;
	}

	public void setAlipay(Alipay alipay) {
		this.alipay = alipay;
	}

	public Integer getPaymethod() {
		return paymethod;
	}

	public void setPaymethod(Integer paymethod) {
		this.paymethod = paymethod;
	}

	public List<ReceiptAddress> getAddrs() {
		return addrs;
	}

	public void setAddrs(List<ReceiptAddress> addrs) {
		this.addrs = addrs;
	}

	public FrontUser getUser() {
		return user;
	}

	public void setUser(FrontUser user) {
		this.user = user;
	}

	public List<BusinessGoodsCartVo> getCartVoList() {
		return cartVoList;
	}

	public void setCartVoList(List<BusinessGoodsCartVo> cartVoList) {
		this.cartVoList = cartVoList;
	}

	public List<ComboVO> getComboVOs() {
		return comboVOs;
	}

	public void setComboVOs(List<ComboVO> comboVOs) {
		this.comboVOs = comboVOs;
	}


	@Override
	public boolean isPublic() {
		return true;
	};

	static Log log = LogFactory.getLog(BusinessOrderRecodeAction.class);

	/**
	 * 跳转到订单结算界面
	 * @return
	 */
	@Action(value = "businessConfirm",results = { @Result(name = StrutsResMSG.SUCCESS, location = "/front/businessConfirm.jsp"),
			@Result(name = StrutsResMSG.FAILED, location = "/front/register.jsp"),@Result(name = StrutsResMSG.ERROR, type = "json") })
    public String businessConfirm() {
		log.debug("总价格"+totalMoney);
		 user = (FrontUser)ServletActionContext.getRequest().getSession().getAttribute("user");
		 addrs = addressService.findListByUserId(user.getId());
		 
		 
		 
		
		 
         if(cartids.equals("all")){
        	 comboVOs = shoppingCartService.findByUserIdAndComboId(Integer.parseInt(user.getId().toString()));
        	 cartVoList = shoppingCartService.findByUserId(Integer.parseInt(user.getId().toString()));
         }else{
        	 
        	 cartVoList = new ArrayList<BusinessGoodsCartVo>();
        	 List<ComboVO> comboVOlist = new ArrayList<ComboVO>();
        	 String[] cartIdArray = cartids.split(",");
        	 for(int i=0;i<cartIdArray.length;i++){
        		 ShoppingCart cart = shoppingCartService.findByid(cartIdArray[i]);
        		 if(cart.getGoodsId()!=null)
        		 {
        			 BusinessGoods goods = goodsService.findByid(cart.getGoodsId().toString());
        			 if(goodsCountMap.get(goods.getId()+"")==null){
        				 goodsCountMap.put(goods.getId()+"", cart.getGoodsCount());        			 
        			 }else{
        				 goodsCountMap.put(goods.getId()+"", goodsCountMap.get(goods.getId()+"")+cart.getGoodsCount());
        			 }
        			 BusinessGoodsCartVo vo = new BusinessGoodsCartVo();
        			 vo.setCart(cart);
        			 vo.setGoods(goods);
        			 cartVoList.add(vo);
        		 }else {
        			 Combo combo  = comboService.findByCombo(cart.getComboId().toString());
					if(goodsCountMap.get(combo.getId()+"")==null)
					{
						goodsCountMap.put(combo.getId()+"", cart.getGoodsCount());
					}else
					{
						goodsCountMap.put(combo.getId()+"", goodsCountMap.get(combo.getId()+"")+cart.getGoodsCount());
					}
					    ComboVO comboVO = new ComboVO();
					    comboVO.setShoppingCart(cart);
					    comboVO.setCombo(combo);
					    comboVOlist.add(comboVO);
				}
        	 }
        	 comboVOs = comboVOlist;
         }
		
		 return StrutsResMSG.SUCCESS;
		
    }
	
	/**
	 * 跳转到订单前检测库存
	 * @return
	 */
	@Action(value = "businessConfirmCheck",results = { @Result(name = StrutsResMSG.SUCCESS, type = "json")})
    public String businessConfirmCheck() {
		log.debug("总价格"+totalMoney);
		user = (FrontUser)ServletActionContext.getRequest().getSession().getAttribute("user");
		
		 List<BusinessGoodsCartVo> newcartVoList=null;
         if(cartids.equals("all")){
        	 newcartVoList = shoppingCartService.findByUserId(Integer.parseInt(user.getId().toString()));
        	 for(int i=0;i<newcartVoList.size();i++){
        		 BusinessGoodsCartVo cartVo = newcartVoList.get(i);
        		 ShoppingCart cart = cartVo.getCart();
        		 BusinessGoods goods = cartVo.getGoods();
        		 if(goodsCountMap.get(cartVo.getGoods().getId()+"")==null){
        			 goodsCountMap.put(goods.getId()+"", cart.getGoodsCount());        			 
        		 }else{
        			 goodsCountMap.put(goods.getId()+"", goodsCountMap.get(goods.getId()+"")+cart.getGoodsCount());
        		 }
        	 }
         }else{
        	 newcartVoList = new ArrayList<BusinessGoodsCartVo>();
        	 String[] cartIdArray = cartids.split(",");
        	 for(int i=0;i<cartIdArray.length;i++){
        		 ShoppingCart cart = shoppingCartService.findByid(cartIdArray[i]);
        		 if(cart.getGoodsId()!=null)
        		 {
        			 BusinessGoods goods = goodsService.findByid(cart.getGoodsId().toString());
        			 if(goodsCountMap.get(goods.getId()+"")==null){
        				 goodsCountMap.put(goods.getId()+"", cart.getGoodsCount());        			 
        			 }else{
        				 goodsCountMap.put(goods.getId()+"", goodsCountMap.get(goods.getId()+"")+cart.getGoodsCount());
        			 }
        		 }
        	 }
         }
         for(String skey:goodsCountMap.keySet()){
        	 GoodsAllocationShow allocationshow = allocationShowService.findByGoodsidToday(skey);
        	 if(goodsCountMap.get(skey)>allocationshow.getSurplusCount()){
        		 BusinessGoods goods = goodsService.findByid(skey);
        		 msg = goods.getGoodsName() +"库存不足，无法下订单";
        		 return StrutsResMSG.SUCCESS;
        	 }else{
        		 msg="";
        	 }
         }
         if(goodsCountMap.isEmpty())
         {
        	 msg="";
         }
         return StrutsResMSG.SUCCESS;
		
    }

	/**
	 * 跳转到信息页。。
	 * 
	 * @return
	 */
	@Action(value = "businessWaitting", results = { @Result(name = StrutsResMSG.SUCCESS, type = "json") })
	public String businessWaitting() {
		if (!StringHelper.isNull(staffId)) {
			// 处理订单列表
			BusinessOrderVO vo = new BusinessOrderVO();
			BusinessOrderRecord po = new BusinessOrderRecord();
			po.setStaffId(staffId);
			vo.setBusinessOrderRecord(po);
			voList = new ArrayList<BusinessOrderVO>();
			this.voList = orderRecordService.webList(vo, 0, 0, "", "");

		}
		return StrutsResMSG.SUCCESS;
	}

	/**
	 * 加载所有商品
	 */
	private void findBusinessGoodsList() {
		this.goodsList = goodsService.listAll();
	}

	/**
	 * 保存对象接口
	 * 
	 * @return String
	 * @throws JSONException
	 * @throws Exception
	 */
	@Action(value = "add",results = { @Result(name = StrutsResMSG.SUCCESS, location = "/front/businessConfirm.jsp"),@Result(name = StrutsResMSG.FAILED, location = "/front/register.jsp"),@Result(name = StrutsResMSG.ALIPAY, location = "/pay/alipay/alipayapi.jsp"),@Result(name = StrutsResMSG.WEIXIN, location = "/pay/wxpay/subSuccess.jsp") })
	public String add() throws JSONException {
		
		 	user =  (FrontUser)ServletActionContext.getRequest().getSession().getAttribute("user");
		   
		 	ReceiptAddress address = null;
		 	
		 	if(receiptAddressId!=null){
		 		address = addressService.findByid(receiptAddressId.toString());
		 	}else{
		 		address = addressService.findByUserId(user.getId());
		 	}	
		 	
		 	
		 	
			BusinessOrderRecord orderRecord = new BusinessOrderRecord();
			if (user != null) {
				orderRecord.setClientUserId(Integer.parseInt(user.getId().toString()));
				
				
				/**
				 * 现在不太清楚为什么要设置这个字段
				 * 
				 * */
				
				orderRecord.setStaffId("2");
			}
			outTradeNo = StringHelper.getSystime("yyyyMMddHHmmss")
					+ StringHelper.getRandom(5);
			orderRecord.setOrderNo(outTradeNo);
			orderRecord.setClientUserName(address.getReceiptName());
			orderRecord.setClientUserMobile(address.getTel());
			orderRecord.setClientUserEmail(address.getEmail());
		
			orderRecord.setUserRealMobile(address.getTel());
			orderRecord.setCountryId(null);
			orderRecord.setCountryName(address.getCounty());
			orderRecord.setProvinceId(null);
			orderRecord.setProvinceName(address.getProvince());
			orderRecord.setCityId(null);
			orderRecord.setCityName(address.getCity());
			orderRecord.setAreaId(null);
			orderRecord.setAreaName(address.getDetailedAddress());
			orderRecord.setUserRealAddress(address.getDetailedAddress());
			orderRecord.setUserRealPlane(null);
			orderRecord.setUserRealName(address.getReceiptName());
			orderRecord.setUserRealPostcode(null);
			orderRecord.setTheEarliestTime(null);
			orderRecord.setTheLatestTime(null);
			orderRecord.setBillTime(null);
			orderRecord.setBillTimeName(null);
			
			String StrutsMessage = "";
			Status statusCode = new Status();
			if(paymethod==0){
				statusCode = statusService.findByStatusNameAndReadme("微信支付","商户订单支付方式");
				orderRecord.setPaymentProvider(statusCode.getId());
				orderRecord.setPaymentProviderName(statusCode.getStatusName());
				StrutsMessage = StrutsResMSG.WEIXIN;
			}else if(paymethod==1){
				statusCode = statusService.findByStatusNameAndReadme("支付宝支付","商户订单支付方式");
				orderRecord.setPaymentProvider(statusCode.getId());	
				orderRecord.setPaymentProviderName(statusCode.getStatusName());
				StrutsMessage = StrutsResMSG.ALIPAY;
			}
			
			// 状态为处理中
			orderRecord.setOperateStatus(204);
			statusCode = statusService.findByid(orderRecord.getOperateStatus()
					.toString());
			orderRecord.setOperateStatusName(statusCode.getStatusName());
			orderRecord.setLogisticsCompanyId(1);
			LogisticsCompany logisticsCompanyCode = logisticsCompanyService
					.findByid("1");
			orderRecord.setLogisticsCompanyName(logisticsCompanyCode
					.getCompanyName());
			orderRecord.setPaymentStatus(201);
			statusCode = statusService.findByid(orderRecord.getPaymentStatus()
					.toString());
			orderRecord.setPaymentStatusName(statusCode.getStatusName());
			orderRecord
					.setOperateTime(new Timestamp(System.currentTimeMillis()));
			orderRecord
					.setCreateTime(new Timestamp(System.currentTimeMillis()));
			
			
			orderRecord.setOriginalPrice(Float.parseFloat(totalMoney));
			orderRecord.setTotalPrice(Float.parseFloat(totalMoney));
			orderRecord.setPurchasePrice(Float.parseFloat(totalMoney));
			orderRecord.setDeliveryStatus(207);
			statusCode = statusService.findByid(orderRecord.getDeliveryStatus()
					.toString());
			orderRecord.setDeliveryStatusName(statusCode.getStatusName());
			orderRecord.setPrimaryConfigurationId(1);
			PrimaryConfiguration primaryConfiguration = primaryConfigurationService
					.findByid("1");
			orderRecord.setPrimaryConfigurationName(primaryConfiguration
					.getMallName());
			orderRecord.setUserRealDescription(userDesc);
			Long newOrderRecordId = orderRecordService.save(orderRecord);
			orderRecordId = String.valueOf(newOrderRecordId);
			Long newComboRecordId = 0L;
			Integer totalCount = 0;
			String goodDetail = "";
			
			 if(cartids.equals("all")){
	        	 
				 cartVoList = shoppingCartService.findByUserId(Integer.parseInt(user.getId().toString()));
				 comboVOs = shoppingCartService.findByUserIdAndComboId(Integer.parseInt(user.getId().toString()));
				 for(BusinessGoodsCartVo vo:cartVoList){
					 ShoppingCart cart = vo.getCart();
	        		 BusinessGoods goods = vo.getGoods();
	        		 
	        		 goodDetail += goods.getGoodsName()+" ";
	        		 
	        		 BusinessOrderGoodsRelation relation = new BusinessOrderGoodsRelation();
	        		 relation.setClientUserId(Integer.parseInt(user.getId().toString()));
	        		 relation.setCurrentPrice(cart.getCurrentPrice());
	        		 relation.setCurrentPriceType(cart.getCurrentPriceType());
	        		 relation.setGoodsCount(cart.getGoodsCount());
	        		 relation.setGoodsId(cart.getGoodsId());
	        		 relation.setGoodsWeightId(goods.getGoodsWeightId());
	        		 relation.setOrderGoodsRelationTime(new Timestamp(new Date().getTime()));
	        		 relation.setOrderRecordId(orderRecord.getId());
	        		 relation.setOriginalPrice(Float.parseFloat(totalMoney));
	        		 relation.setPurchasePrice(Float.parseFloat(totalMoney));
	        		 
	        		 totalCount += cart.getGoodsCount();
	        		 
	        		 orderGoodsRelationService.save(relation);
	        		 shoppingCartService.del(cart.getId().toString());
				 }
				
				 for (ComboVO cvos : comboVOs) {
					 
					 ShoppingCart cart = cvos.getShoppingCart();
					 
					 Combo combo  = cvos.getCombo();
        			
        			 goodDetail += combo.getComboName()+" ";
        			 
        			 BusinessOrderGoodsRelation relation = new BusinessOrderGoodsRelation();
        			 relation.setClientUserId(Integer.parseInt(user.getId().toString()));
        			 relation.setCurrentPrice(cart.getCurrentPrice());
        			 relation.setCurrentPriceType(cart.getCurrentPriceType());
        			 relation.setGoodsCount(cart.getGoodsCount());
        			 relation.setComboId(cart.getComboId());
//        			 relation.setGoodsWeightId(goods.getGoodsWeightId());
        			 relation.setOrderGoodsRelationTime(new Timestamp(new Date().getTime()));
        			 relation.setOrderRecordId(orderRecord.getId());
        			 relation.setOriginalPrice(Float.parseFloat(totalMoney));
        			 relation.setPurchasePrice(Float.parseFloat(totalMoney));
        			 
        			 
        			 totalCount += cart.getGoodsCount();
        			 
        			 ComboUser comboUser = new ComboUser();
        			 comboUser.setUserId(Integer.parseInt(user.getId().toString()));
        			 comboUser.setComboId(cart.getComboId().toString());
        			 comboUser.setOrderId(newOrderRecordId);
        			 comboUser.setComboUserStatus(0);
        			 comboUser.setUsingMoney(0F);
        			 comboUser.setUsingTimes(0);
        			 Date nowDate = new Date();
        			 String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(nowDate.getTime());
        			 comboUser.setCreateTime(Timestamp.valueOf(time));
        			 Calendar calendar = Calendar.getInstance();
        			 calendar.setTime(nowDate);
        			 calendar.add(Calendar.DATE,1);
        			 calendar.set(Calendar.HOUR_OF_DAY, 0);
        			 calendar.set(Calendar.SECOND,0);
        			 calendar.set(Calendar.MINUTE,0);
        			 Timestamp startTimestamp = new Timestamp(calendar.getTime().getTime());
        			 Calendar endTIme = Calendar.getInstance();
        			 endTIme.setTime(nowDate);
        			 if(combo.getComboCycleStatus().equals("228"))
        			 {
        				 endTIme.add(Calendar.DATE,30);
        			 }else if (combo.getComboCycleStatus().equals("229")) {
        				 endTIme.add(Calendar.DATE,60);
					}else if (combo.getComboCycleStatus().equals("230")) {
       				 endTIme.add(Calendar.DATE,120);
					}else if (combo.getComboCycleStatus().equals("231")) {
       				 endTIme.add(Calendar.DATE,240);
					}else if (combo.getComboCycleStatus().equals("232")) {
       				 endTIme.add(Calendar.DATE,365);
					}
        			 endTIme.set(Calendar.HOUR_OF_DAY, 23);
        			 endTIme.set(Calendar.SECOND,59);
        			 endTIme.set(Calendar.MINUTE,59);
        			 Timestamp endTimeTamp = new Timestamp(endTIme.getTime().getTime());
        			 comboUser.setComboStartTime(startTimestamp);
        			 comboUser.setComboEndTime(endTimeTamp);
        			 comboUser.setFirstDay(startTimestamp);
        			 comboUser.setFirstMonth(startTimestamp);
        			 comboUser.setFirstWeek(startTimestamp);
        			 comboUser.setFirstYear(startTimestamp);
        			 comboUserService.save(comboUser);
        			 orderGoodsRelationService.save(relation);
        			 shoppingCartService.del(cart.getId().toString());
				}
				 
	         }else{
	        	 
	        	 cartVoList = new ArrayList<BusinessGoodsCartVo>();
	        	 
	        	 String[] cartIdArray = cartids.split(",");
	        	 
	        	 for(int i=0;i<cartIdArray.length;i++){
	        		 ShoppingCart cart = shoppingCartService.findByid(cartIdArray[i]);
	        		 if(cart.getGoodsId()!=null)
	        		 {
	        			 
	        			 BusinessGoods goods = goodsService.findByid(cart.getGoodsId().toString());
	        			 
	        			 goodDetail += goods.getGoodsName()+" ";
	        			 
	        			 BusinessOrderGoodsRelation relation = new BusinessOrderGoodsRelation();
	        			 relation.setClientUserId(Integer.parseInt(user.getId().toString()));
	        			 relation.setCurrentPrice(cart.getCurrentPrice());
	        			 relation.setCurrentPriceType(cart.getCurrentPriceType());
	        			 relation.setGoodsCount(cart.getGoodsCount());
	        			 relation.setGoodsId(cart.getGoodsId());
	        			 relation.setGoodsWeightId(goods.getGoodsWeightId());
	        			 relation.setOrderGoodsRelationTime(new Timestamp(new Date().getTime()));
	        			 relation.setOrderRecordId(orderRecord.getId());
	        			 relation.setOriginalPrice(Float.parseFloat(totalMoney));
	        			 relation.setPurchasePrice(Float.parseFloat(totalMoney));
	        			 
	        			 
	        			 totalCount += cart.getGoodsCount();
	        			 
	        			 orderGoodsRelationService.save(relation);
	        			 shoppingCartService.del(cart.getId().toString());
	        		 }
	        		 if(cart.getComboId()!=null)
	        		 {
//	        			 BusinessGoods goods = goodsService.findByid(cart.getGoodsId().toString());
	        			 Combo combo  = comboService.findByCombo(cart.getComboId().toString());
	        			 
	        			 goodDetail += combo.getComboName()+" ";
	        			 
	        			 BusinessOrderGoodsRelation relation = new BusinessOrderGoodsRelation();
	        			 relation.setClientUserId(Integer.parseInt(user.getId().toString()));
	        			 relation.setCurrentPrice(cart.getCurrentPrice());
	        			 relation.setCurrentPriceType(cart.getCurrentPriceType());
	        			 relation.setGoodsCount(cart.getGoodsCount());
	        			 relation.setComboId(cart.getComboId());
//	        			 relation.setGoodsWeightId(goods.getGoodsWeightId());
	        			 relation.setOrderGoodsRelationTime(new Timestamp(new Date().getTime()));
	        			 relation.setOrderRecordId(orderRecord.getId());
	        			 relation.setOriginalPrice(Float.parseFloat(totalMoney));
	        			 relation.setPurchasePrice(Float.parseFloat(totalMoney));
	        			 
	        			 
	        			 totalCount += cart.getGoodsCount();
	        			 
	        			 ComboUser comboUser = new ComboUser();
	        			 comboUser.setUserId(Integer.parseInt(user.getId().toString()));
	        			 comboUser.setComboId(cart.getComboId().toString());
	        			 comboUser.setOrderId(newOrderRecordId);
	        			 comboUser.setComboUserStatus(0);
	        			 comboUser.setUsingMoney(0F);
	        			 comboUser.setUsingTimes(0);
	        			 String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date().getTime());
	        			 comboUser.setCreateTime(Timestamp.valueOf(time));
	        			 comboUserService.save(comboUser);
	        			 orderGoodsRelationService.save(relation);
	        			 shoppingCartService.del(cart.getId().toString());
	        		 }
	        	 }
	        	 
	         }
			 
			 
			 orderRecord.setTotalCount(totalCount);
			 orderRecordService.modify(orderRecord);
			 
			 
			 
			if(paymethod==1)
      {//跳转去支付。。。。。
				this.alipay = alipayService.payInfo(outTradeNo, newOrderRecordId,totalMoney);
          }else if(paymethod==0){
              boolean wxFlag = this.wxPayInfo(outTradeNo,totalMoney,goodDetail);
              if(wxFlag){
                  StrutsMessage = StrutsResMSG.WEIXIN;
                  return StrutsMessage;
              }else{
                  StrutsMessage = StrutsResMSG.FAILED;
                  return StrutsMessage;
              }
          }
		return StrutsMessage;
	}
	
	
	
	
	
	 
	@Action(value = "wxPayAgain",results = { @Result(name = StrutsResMSG.SUCCESS, location = "/pay/wxpay/subSuccess.jsp"),
			@Result(name = StrutsResMSG.FAILED, location = "/front/register.jsp"),@Result(name = StrutsResMSG.ERROR, type = "json") })	
	public String wxPayAgain() {
		
		BusinessOrderRecord orderRecord = orderRecordService.findByid(businessOrderRecodeId.toString());
		
		this.outTradeNo = orderRecord.getOrderNo();
		this.totalMoney = orderRecord.getTotalPrice().toString();
		
		String goodDetail = "";
		
		List<BusinessOrderGoodsRelation> relations = orderGoodsRelationService.findByOrderId(businessOrderRecodeId);
		
		for(BusinessOrderGoodsRelation relation:relations){
			BusinessGoods good = new BusinessGoods();
			Combo combo = new Combo();
			try {
				if(relation.getGoodsId()!=null||!relation.getGoodsId().equals("null"))
				{
					good = goodsService.findByid(relation.getGoodsId().toString());
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				if (relation.getComboId()!=null||!relation.getComboId().equals("null")) {
					combo = comboService.findByCombo(relation.getComboId().toString());
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			goodDetail += good.getGoodsName()+" "+combo.getComboName();
			
		}
		
		 boolean wxFlag = this.wxPayInfo(outTradeNo,totalMoney,goodDetail);
         if(wxFlag){
        	 return  StrutsResMSG.SUCCESS;
              
         }else{
        	 return  StrutsResMSG.FAILED;
              
         }
	}
	
	
	
	
	private boolean wxPayInfo(String outTradeNo, String  totalMoney ,String goodDetail){
	   String  body = goodDetail;
       String  detail = goodDetail;

//       int   total_fee = 1;
    int   total_fee = (int)(Float.parseFloat(totalMoney)*100);
    String out_trade_no = outTradeNo;
    String spbill_create_ip = WXfInit.getIP();
    String trade_type = WXfInit.getTradeType();
    // 获取二维码
    ScanPayReqData unifiedOrderReqData = new ScanPayReqData(body, detail, out_trade_no,
            total_fee, spbill_create_ip, trade_type, "", "", "");
    try {
        ScanPayService service = new ScanPayService();
        String res = service.getCallOrderInfo(unifiedOrderReqData);
        log.info("res:"+res);
        JSONObject json = new JSONObject(res);
        if (json.has("return_code")) {
            if (json.get("return_code").equals("SUCCESS")) {
                if (json.has("return_msg")) {
                    if (json.get("return_msg").equals("OK")) {
                        if (json.has("code_url")) {
                            String codeUrl = json.getString("code_url");
                            boolean flag = QRcodeUtils.getQRCode(codeUrl, "getWxImage");
                            return flag;
                        }
                    }
                }
            }
        }
    } catch (Exception e) {
        log.error(e.getMessage());
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
    return false;
	}

	/**
	 * 保存对象接口
	 * 
	 * @return String
	 * @throws JSONException
	 * @throws Exception
	 */
	@Action(value = "putOrder", results = { @Result(name = StrutsResMSG.SUCCESS, type = "json") })
	public String putOrder() {
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpServletResponse response = ServletActionContext.getResponse();

			Enumeration eh = request.getHeaderNames();
			while (eh.hasMoreElements()) {
				String h = (String) eh.nextElement();
				coopLog.debug("headr: " + h + " | " + request.getHeader(h));

			}

			Enumeration e = request.getParameterNames();
			while (e.hasMoreElements()) {
				String k = e.nextElement().toString();
				String v = request.getParameter(k);
				coopLog.debug("key: " + k + " | value: " + v);
			}

			BufferedReader br = new BufferedReader(new InputStreamReader(
					request.getInputStream()));

			StringBuffer sb = new StringBuffer();
			String currentLine = "";
			while ((currentLine = br.readLine()) != null) {
				sb.append(currentLine);
			}

			String source = sb.toString();

			coopLog.debug("source:" + source);

			// 取得传输JSON数据
			JSONArray goodsArray = new JSONArray(new JSONObject(source).get(
					"goods").toString());
			JSONArray addresArray = new JSONArray(new JSONObject(source).get(
					"userReal").toString());

			// 获取送货信息
			for (int i = 0; i < addresArray.length(); i++) {
				// 获取每一个JsonObject对象
				JSONObject tempAddres = addresArray.getJSONObject(i);
				// 获取每一个对象中的值
				userRealMobile = tempAddres.getString("userRealMobile");
				userRealAddress = tempAddres.getString("userRealAddress");
				userRealName = tempAddres.getString("userRealName");
				staffId = String.valueOf(tempAddres.getInt("coopid"));
				payType = tempAddres.getString("payType");
				requiredDeliveryTime = tempAddres
						.getString("requiredDeliveryTime");
				cpUserId = tempAddres.getString("userid");
			}
			if (StringHelper.isNull(staffId)) {
				response.getWriter().println("Error: coopid is null");
				return null;
			}
			// 获取用户信息
			CommonStaff staff = staffService.findByid(staffId);
			ClientUserInfo clientUserInfo = clientUserInfoService
					.findByProperty("staffId", Integer.parseInt(staffId));

			if (staff == null || StringHelper.isNull(staff.getIp())
					|| !super.getRemoteIp().equals(staff.getIp())) {
				response.getWriter()
						.println("Error: ip is null or ip is block");
				return null;
			}

			// 获取下单商品及数量
			findBusinessGoodsList();
			businessOrderVoList = new ArrayList<BusinessOrderVO>();
			for (int i = 0; i < goodsArray.length(); i++) {
				BusinessOrderVO vo = new BusinessOrderVO();
				boolean flag = false;
				BusinessGoodsVO goodsVo = new BusinessGoodsVO();
				// 获取每一个JsonObject对象
				JSONObject tempGoods = goodsArray.getJSONObject(i);
				// 获取每一个对象中的值
				String goodsId = tempGoods.getString("goodsId");
				String goodsCount = tempGoods.getString("goodsCount");
				for (int j = 0; j < goodsList.size(); j++) {
					if (goodsList.get(j).getId().toString().equals(goodsId)
							&& !StringHelper.isNull(goodsCount)) {
						goodsVo.setBusinessGoods(goodsList.get(j));
						flag = true;
					}
				}
				if (flag) {
					int gc = Integer.parseInt(goodsCount);
					if (gc <= 0)
						break;
					vo.setBusinessGoodsVO(goodsVo);
					BusinessOrderGoodsRelation po = new BusinessOrderGoodsRelation();
					po.setGoodsCount(gc);
					vo.setBusinessOrderGoodsRelation(po);
					businessOrderVoList.add(vo);
				}
			}

			// 批量增加商品
			if (businessOrderVoList == null) {
				response.getWriter().println("Error: not found goods");
				return null;
			} else if (staff != null && !StringHelper.isNull(userRealMobile)
					&& !StringHelper.isNull(userRealAddress)
					&& !StringHelper.isNull(userRealName)) {
				// 增加新订单
				BusinessOrderRecord orderRecord = new BusinessOrderRecord();
				if (clientUserInfo != null) {
					orderRecord.setClientUserId(clientUserInfo.getId());
				}
				String outTradeNo = StringHelper.getSystime("yyyyMMddHHmmss")
						+ StringHelper.getRandom(5);
				orderRecord.setOrderNo(outTradeNo);
				orderRecord.setClientUserName(staff.getStaffName());
				orderRecord.setClientUserMobile(staff.getMobile());
				orderRecord.setClientUserEmail(staff.getEmail());
				orderRecord.setStaffId(staffId);
				orderRecord.setUserRealMobile(userRealMobile);
				orderRecord.setUserRealAddress(userRealAddress);
				orderRecord.setUserRealName(userRealName);
				orderRecord.setTheEarliestTime(staff.getTheEarliestTime());
				orderRecord.setTheLatestTime(staff.getTheLatestTime());
				orderRecord.setBillTime(staff.getBillTime());
				orderRecord.setBillTimeName(staff.getBillTimeName());
				orderRecord.setRequiredDeliveryTime(Timestamp
						.valueOf(requiredDeliveryTime + " 00:00:00"));
				orderRecord.setPaymentProvider(197);
				Status statusCode = statusService.findByid("197");
				orderRecord.setPaymentProviderName(statusCode.getStatusName());
				// 状态为处理中
				orderRecord.setOperateStatus(204);
				statusCode = statusService.findByid(orderRecord
						.getOperateStatus().toString());
				orderRecord.setOperateStatusName(statusCode.getStatusName());
				orderRecord.setLogisticsCompanyId(1);
				LogisticsCompany logisticsCompanyCode = logisticsCompanyService
						.findByid("1");
				orderRecord.setLogisticsCompanyName(logisticsCompanyCode
						.getCompanyName());
				orderRecord.setPaymentStatus(201);
				statusCode = statusService.findByid(orderRecord
						.getPaymentStatus().toString());
				orderRecord.setPaymentStatusName(statusCode.getStatusName());
				orderRecord.setOperateTime(new Timestamp(System
						.currentTimeMillis()));
				orderRecord.setCreateTime(new Timestamp(System
						.currentTimeMillis()));
				// 累积订单价格
				Float tempOriginalPrice = 0f;
				Float tempTotalPrice = 0f;
				Float tempPurchasePrice = 0f;
				for (int i = 0; businessOrderVoList.size() > i; i++) {
					BusinessOrderVO dvo = businessOrderVoList.get(i);
					BusinessGoods goods = dvo.getBusinessGoodsVO()
							.getBusinessGoods();
					// 取得商品数量
					BigDecimal d1 = new BigDecimal(dvo
							.getBusinessOrderGoodsRelation().getGoodsCount());
					BigDecimal d2;
					// 计算原价
					d2 = new BigDecimal(goods.getGoodsOriginalPrice()
							* staff.getViewDiscount());
					BigDecimal o1 = new BigDecimal(d1.multiply(d2).floatValue());
					BigDecimal o2 = new BigDecimal(tempOriginalPrice);
					tempOriginalPrice = o1.add(o2).floatValue();

					// 计算进货价
					if (goods.getGoodsPurchasePrice() != null) {
						d2 = new BigDecimal(goods.getGoodsPurchasePrice());
					} else {
						d2 = new BigDecimal(0);
					}
					BigDecimal p1 = new BigDecimal(d1.multiply(d2).floatValue());
					BigDecimal p2 = new BigDecimal(tempPurchasePrice);
					tempPurchasePrice = p1.add(p2).floatValue();

					// 折扣计算(当折扣小于1的时候，则进行折扣计算)
					if (staff.getDiscount() != null && staff.getDiscount() != 1
							&& staff.getDiscount() > 0) {
						BigDecimal t1 = new BigDecimal(staff.getDiscount());
						BigDecimal t2 = new BigDecimal(tempOriginalPrice);
						tempTotalPrice = t1.multiply(t2).floatValue();
						orderRecord.setDiscount(staff.getDiscount());
					} else {
						tempTotalPrice = tempOriginalPrice;
						orderRecord.setDiscount(1f);
					}
				}
				orderRecord.setOriginalPrice(tempOriginalPrice);
				orderRecord.setTotalPrice(tempTotalPrice);
				orderRecord.setPurchasePrice(tempPurchasePrice);
				orderRecord.setDeliveryStatus(207);
				statusCode = statusService.findByid(orderRecord
						.getDeliveryStatus().toString());
				orderRecord.setDeliveryStatusName(statusCode.getStatusName());
				orderRecord.setPrimaryConfigurationId(1);
				PrimaryConfiguration primaryConfiguration = primaryConfigurationService
						.findByid("1");
				orderRecord.setPrimaryConfigurationName(primaryConfiguration
						.getMallName());
				orderRecord.setUserRealDescription(userDesc);
				Long newOrderRecordId = orderRecordService.save(orderRecord);
				orderRecordId = String.valueOf(newOrderRecordId);
				// 为新订单添加商品
				for (int i = 0; businessOrderVoList.size() > i; i++) {
					BusinessOrderVO dvo = businessOrderVoList.get(i);
					BusinessOrderGoodsRelation ogr = new BusinessOrderGoodsRelation();
					BusinessGoods goods = dvo.getBusinessGoodsVO()
							.getBusinessGoods();
					ogr.setOrderRecordId(newOrderRecordId);
					BusinessGoods p = goodsService.findByid(goods.getId()
							.toString());
					ogr.setGoodsWeightId(p.getGoodsWeightId());
					if (clientUserInfo != null) {
						ogr.setClientUserId(clientUserInfo.getId());
					}
					ogr.setGoodsId(goods.getId());
					ogr.setGoodsCount(dvo.getBusinessOrderGoodsRelation()
							.getGoodsCount());
					ogr.setOriginalPrice(goods.getGoodsOriginalPrice()
							* staff.getViewDiscount());
					ogr.setPurchasePrice(goods.getGoodsPurchasePrice());
					// 计算折扣后价格
					if (staff.getDiscount() != null && staff.getDiscount() != 1
							&& staff.getDiscount() > 0) {
						BigDecimal r1 = new BigDecimal(staff.getDiscount());
						BigDecimal r2 = new BigDecimal(
								goods.getGoodsOriginalPrice()
										* staff.getViewDiscount());
						ogr.setDiscount(staff.getDiscount());
						ogr.setCurrentPrice(r1.multiply(r2).floatValue());
						ogr.setCurrentPriceType(216);
					} else {
						ogr.setDiscount(1f);
						ogr.setCurrentPrice(goods.getGoodsOriginalPrice()
								* staff.getViewDiscount());
						ogr.setCurrentPriceType(215);
					}
					ogr.setStaffId(staffId);
					ogr.setPrimaryConfigurationId(1);
					ogr.setOrderGoodsRelationTime(new Timestamp(System
							.currentTimeMillis()));
					orderGoodsRelationService.save(ogr);
				}
				JSONObject endJson = new JSONObject();
				endJson.put("orderRecordId", orderRecordId);
				endJson.put("coopid", staffId);
				endJson.put("userid", cpUserId);
				endJson.put("status", "ok");
				DecimalFormat df = new DecimalFormat("#.00");
				endJson.put("price", df.format(tempTotalPrice));
				response.getWriter().println(endJson.toString());
			} else {
				response.getWriter().println(
						"Error: not found coop or parameter error");
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			exLog.error(StringHelper.getStackInfo(e));
		}
		return null;
	}

	public String getUserDesc() {
		return userDesc;
	}

	public void setUserDesc(String userDesc) {
		this.userDesc = userDesc;
	}

	public Long getBusinessOrderRecodeId() {
		return businessOrderRecodeId;
	}

	public void setBusinessOrderRecodeId(Long businessOrderRecodeId) {
		this.businessOrderRecodeId = businessOrderRecodeId;
	}
	
	
	
	
}
