package com.xnradmin.client.action.front;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

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
import com.xnradmin.constant.StrutsResMSG;
import com.xnradmin.core.action.ParentAction;
import com.xnradmin.core.service.StaffService;
import com.xnradmin.core.service.business.commodity.BusinessGoodsService;
import com.xnradmin.core.service.business.order.BusinessOrderGoodsRelationService;
import com.xnradmin.core.service.business.order.BusinessOrderRecordService;
import com.xnradmin.core.service.common.status.StatusService;
import com.xnradmin.core.service.mall.clientUser.ClientUserInfoService;
import com.xnradmin.core.service.mall.clientUser.ClientUserRegionInfoService;
import com.xnradmin.core.service.mall.order.ShoppingCartService;
import com.xnradmin.core.service.mall.region.AreaService;
import com.xnradmin.core.service.mall.seting.LogisticsCompanyService;
import com.xnradmin.core.service.mall.seting.PrimaryConfigurationService;
import com.xnradmin.core.util.Log4jUtil;
import com.xnradmin.po.CommonStaff;
import com.xnradmin.po.business.BusinessGoods;
import com.xnradmin.po.business.BusinessOrderGoodsRelation;
import com.xnradmin.po.business.BusinessOrderRecord;
import com.xnradmin.po.client.ClientUserInfo;
import com.xnradmin.po.client.ClientUserRegionInfo;
import com.xnradmin.po.common.status.Status;
import com.xnradmin.po.front.FrontUser;
import com.xnradmin.po.mall.region.Area;
import com.xnradmin.po.mall.seting.LogisticsCompany;
import com.xnradmin.po.mall.seting.PrimaryConfiguration;
import com.xnradmin.vo.business.BusinessGoodsVO;
import com.xnradmin.vo.business.BusinessOrderVO;
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

	private Log exLog = Log4jUtil.getLog("ex");

	private Log coopLog = Log4jUtil.getLog("coopLog");

	private FrontUser user;
	private List<BusinessGoodsCartVo> cartVoList;
	
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

	@Override
	public boolean isPublic() {
		return true;
	};

	static Log log = LogFactory.getLog(BusinessOrderRecodeAction.class);

	/**
	 * 跳转到订单结算界面
	 * @return
	 */
	@Action(value = "businessConfirm",results = { @Result(name = StrutsResMSG.SUCCESS, location = "/front/businessConfirm.jsp"),@Result(name = StrutsResMSG.FAILED, location = "/front/register.jsp") })
    public String businessConfirm() {
		
		 user =  (FrontUser)ServletActionContext.getRequest().getSession().getAttribute("user");
			
		 cartVoList = shoppingCartService.findByUserId(Integer.parseInt(user.getId().toString()));
		
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
	@Action(value = "add", results = { @Result(name = StrutsResMSG.SUCCESS, type = "json") })
	public String add() throws JSONException {
		JSONArray goodsArray = new JSONArray(new JSONObject(goods).get("goods")
				.toString());
		JSONArray addresArray = new JSONArray(new JSONObject(userReal).get(
				"userReal").toString());
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

		// 获取送货信息
		for (int i = 0; i < addresArray.length(); i++) {
			// 获取每一个JsonObject对象
			JSONObject tempAddres = addresArray.getJSONObject(i);
			// 获取每一个对象中的值
			clientUserRegionId = tempAddres.getString("clientUserRegionId");
			userRealMobile = tempAddres.getString("userRealMobile");
			userRealAddress = tempAddres.getString("userRealAddress");
			userRealName = tempAddres.getString("userRealName");
			areaId = tempAddres.getString("areaId");
			staffId = String.valueOf(tempAddres.getInt("staffId"));
			payType = tempAddres.getString("payType");
		}

		// --------------------------------------------------------------------
		// 取得当前登录人信息
		CommonStaff staff = staffService.findByid(staffId);
		ClientUserInfo clientUserInfo = clientUserInfoService.findByProperty(
				"staffId", Integer.parseInt(staffId));

		// 批量增加商品
		if (businessOrderVoList == null) {
			errorMsg = "未选择商品";
		} else if (staff != null) {
			System.out.println("start:::");
			ClientUserRegionInfo cri = new ClientUserRegionInfo();
			if (!StringHelper.isNull(staffId)) {
				if (clientUserRegionId.equals("new")
						&& !StringHelper.isNull(areaId)
						&& !StringHelper.isNull(userRealAddress)
						&& !StringHelper.isNull(userRealName)
						&& !StringHelper.isNull(userRealMobile)) {
					cri.setCountryId(1);
					cri.setCountryName("中国");
					Area area = areaService.findByid(areaId);
					if (area != null) {
						cri.setProvinceId(area.getProvinceId());
						cri.setProvinceName(area.getProvince());
						cri.setCityId(area.getCityId());
						cri.setCityName(area.getCity());
						cri.setAreaId(area.getId());
						cri.setAreaName(area.getArea());
					}
					if (!StringHelper.isNull(userRealAddress)) {
						cri.setUserRealAddress(userRealAddress);
					}
					if (clientUserInfo != null) {
						cri.setClientUserInfoId(clientUserInfo.getId());
					}
					if (!StringHelper.isNull(userRealMobile)) {
						cri.setUserRealMobile(userRealMobile);
					}
					if (!StringHelper.isNull(userRealName)) {
						cri.setUserRealName(userRealName);
					}
					if (!StringHelper.isNull(staffId)) {
						cri.setStaffId(Integer.parseInt(staffId));
					}
					cri.setCreateTime(new Timestamp(System.currentTimeMillis()));
					cri.setModifyTime(new Timestamp(System.currentTimeMillis()));
					clientUserRegionInfoService.saveBusiness(cri);
				} else {
					List<ClientUserRegionInfo> list = clientUserRegionInfoService
							.findByProperty("staffId",
									Integer.parseInt(staffId));
					cri = list.get(0);
				}
			}
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
			orderRecord.setUserRealMobile(cri.getUserRealMobile());
			orderRecord.setCountryId(cri.getCountryId());
			orderRecord.setCountryName(cri.getCountryName());
			orderRecord.setProvinceId(cri.getProvinceId());
			orderRecord.setProvinceName(cri.getProvinceName());
			orderRecord.setCityId(cri.getCityId());
			orderRecord.setCityName(cri.getCityName());
			orderRecord.setAreaId(cri.getAreaId());
			orderRecord.setAreaName(cri.getAreaName());
			orderRecord.setUserRealAddress(cri.getUserRealAddress());
			orderRecord.setUserRealPlane(cri.getUserRealPlane());
			orderRecord.setUserRealName(cri.getUserRealName());
			orderRecord.setUserRealPostcode(cri.getUserRealPostcode());
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
			errorMsg = "0";
			System.out.println("end:::");
		} else {
			errorMsg = "用户不存在";
		}
		// --------------------------------------------------------------------

		return StrutsResMSG.SUCCESS;
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
				System.out.println("end:::");
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
}
