/**
 *2012-5-10 下午3:52:29
 */
package com.xnradmin.client.action.mall.web;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.cntinker.util.StringHelper;
import com.xnradmin.constant.StrutsResMSG;
import com.xnradmin.core.action.ParentAction;
import com.xnradmin.core.service.common.status.StatusService;
import com.xnradmin.core.service.mall.clientUser.ClientUserInfoService;
import com.xnradmin.core.service.mall.clientUser.ClientUserRegionInfoService;
import com.xnradmin.core.service.mall.commodity.GoodsService;
import com.xnradmin.core.service.mall.order.OrderRecordService;
import com.xnradmin.core.service.mall.order.ShoppingCartService;
import com.xnradmin.core.service.mall.region.AreaService;
import com.xnradmin.core.service.mall.region.CityService;
import com.xnradmin.core.service.mall.region.ProvinceService;
import com.xnradmin.core.service.mall.seting.LogisticsCompanyService;
import com.xnradmin.po.client.ClientUserInfo;
import com.xnradmin.po.client.ClientUserRegionInfo;
import com.xnradmin.po.common.status.Status;
import com.xnradmin.po.mall.commodity.Goods;
import com.xnradmin.po.mall.order.OrderRecord;
import com.xnradmin.po.mall.order.ShoppingCart;
import com.xnradmin.po.mall.region.Area;
import com.xnradmin.po.mall.region.City;
import com.xnradmin.po.mall.region.Province;
import com.xnradmin.po.mall.seting.LogisticsCompany;
import com.xnradmin.vo.mall.OrderVO;
/**
 * @autohr: xiang_liu
 * 
 */
@Controller
@Scope("prototype")
@Namespace("/page/wx/client/web/bill")
@ParentPackage("json-default")
public class billAction extends ParentAction {

	@Autowired
	private ShoppingCartService shoppingCartService;
	
	@Autowired
	private OrderRecordService orderRecordService;
	
	@Autowired
	private GoodsService goodsService;

	@Autowired
	private ClientUserInfoService clientUserInfoService;
	
	@Autowired
	private ClientUserRegionInfoService clientUserRegionInfoService;
	
	@Autowired
	private LogisticsCompanyService logisticsCompanyService;
	
	@Autowired
	private ProvinceService provinceService;
	
	@Autowired
	private CityService cityService;
	
	@Autowired
	private AreaService areaService;
	
	@Autowired
	private StatusService statusService;


	private String shoppingCartId;
	private String clientUserId; //用户ID
	private String goodsId; //商品Id
    private String goodsCount; //商品数量
    private Float currentPrice; //当前单价
    private String currentPriceType; //当前价格类型（原价，优惠价）
    private Float totalPrice; //当前总价
    private String staffId;	//隶属用户Id
    private String primaryConfigurationId; //对应商城ID
    private String shoppingCartTime; //生成时间 
    private String uid;
    private String countryId;
    private String provinceId;
    private String cityId;
    private String areaId;
    private String clientUserRegionInfoId; 
    private String isSubGoods; //是否有子产品未选择主产品
    private List<Goods> subGoodsList; //为选择主产品的子产品
	private List<OrderVO> voList;
	private List<Goods> goodsList;
	private List<Status> paymentProviderList;
	private ShoppingCart shoppingCart;
	private List<ClientUserRegionInfo> clientUserRegionInfoList;
	private List<Province> provinceList;
	private List<City> cityList;
	private List<Area> areaList;
	private List<LogisticsCompany> logisticsCompanyList;
	private Status status;
	private Goods goods;
	private List<Goods> freeGoodsList;
	private List<Goods> oneGoodsList;
	private List<String> dateList;
	public ShoppingCartService getShoppingCartService() {
		return shoppingCartService;
	}

	public void setShoppingCartService(ShoppingCartService shoppingCartService) {
		this.shoppingCartService = shoppingCartService;
	}

	public StatusService getStatusService() {
		return statusService;
	}

	public void setStatusService(StatusService statusService) {
		this.statusService = statusService;
	}

	public String getShoppingCartId() {
		return shoppingCartId;
	}

	public void setShoppingCartId(String shoppingCartId) {
		this.shoppingCartId = shoppingCartId;
	}

	public String getClientUserId() {
		return clientUserId;
	}

	public void setClientUserId(String clientUserId) {
		this.clientUserId = clientUserId;
	}

	public String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	public String getGoodsCount() {
		return goodsCount;
	}

	public void setGoodsCount(String goodsCount) {
		this.goodsCount = goodsCount;
	}

	public Float getCurrentPrice() {
		return currentPrice;
	}

	public void setCurrentPrice(Float currentPrice) {
		this.currentPrice = currentPrice;
	}

	public String getCurrentPriceType() {
		return currentPriceType;
	}

	public void setCurrentPriceType(String currentPriceType) {
		this.currentPriceType = currentPriceType;
	}

	public String getStaffId() {
		return staffId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

	public String getPrimaryConfigurationId() {
		return primaryConfigurationId;
	}

	public void setPrimaryConfigurationId(String primaryConfigurationId) {
		this.primaryConfigurationId = primaryConfigurationId;
	}

	public String getShoppingCartTime() {
		return shoppingCartTime;
	}

	public void setShoppingCartTime(String shoppingCartTime) {
		this.shoppingCartTime = shoppingCartTime;
	}

	public String getClientUserRegionInfoId() {
		return clientUserRegionInfoId;
	}

	public void setClientUserRegionInfoId(String clientUserRegionInfoId) {
		this.clientUserRegionInfoId = clientUserRegionInfoId;
	}

	public List<OrderVO> getVoList() {
		return voList;
	}

	public void setVoList(List<OrderVO> voList) {
		this.voList = voList;
	}

	public ShoppingCart getShoppingCart() {
		return shoppingCart;
	}

	public void setShoppingCart(ShoppingCart shoppingCart) {
		this.shoppingCart = shoppingCart;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Goods getGoods() {
		return goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}

	public GoodsService getGoodsService() {
		return goodsService;
	}

	public void setGoodsService(GoodsService goodsService) {
		this.goodsService = goodsService;
	}

	public List<Goods> getGoodsList() {
		return goodsList;
	}

	public void setGoodsList(List<Goods> goodsList) {
		this.goodsList = goodsList;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public Float getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Float totalPrice) {
		this.totalPrice = totalPrice;
	}

	public List<ClientUserRegionInfo> getClientUserRegionInfoList() {
		return clientUserRegionInfoList;
	}

	public void setClientUserRegionInfoList(
			List<ClientUserRegionInfo> clientUserRegionInfoList) {
		this.clientUserRegionInfoList = clientUserRegionInfoList;
	}

	public List<Province> getProvinceList() {
		return provinceList;
	}

	public void setProvinceList(List<Province> provinceList) {
		this.provinceList = provinceList;
	}

	public List<City> getCityList() {
		return cityList;
	}

	public void setCityList(List<City> cityList) {
		this.cityList = cityList;
	}

	public List<Area> getAreaList() {
		return areaList;
	}

	public void setAreaList(List<Area> areaList) {
		this.areaList = areaList;
	}

	public String getCountryId() {
		return countryId;
	}

	public void setCountryId(String countryId) {
		this.countryId = countryId;
	}

	public String getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}

	public String getCityId() {
		return cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	public List<Status> getPaymentProviderList() {
		return paymentProviderList;
	}

	public void setPaymentProviderList(List<Status> paymentProviderList) {
		this.paymentProviderList = paymentProviderList;
	}

	public List<LogisticsCompany> getLogisticsCompanyList() {
		return logisticsCompanyList;
	}

	public void setLogisticsCompanyList(List<LogisticsCompany> logisticsCompanyList) {
		this.logisticsCompanyList = logisticsCompanyList;
	}

	public List<Goods> getFreeGoodsList() {
		return freeGoodsList;
	}

	public void setFreeGoodsList(List<Goods> freeGoodsList) {
		this.freeGoodsList = freeGoodsList;
	}

	public List<Goods> getOneGoodsList() {
		return oneGoodsList;
	}

	public void setOneGoodsList(List<Goods> oneGoodsList) {
		this.oneGoodsList = oneGoodsList;
	}

	public String getIsSubGoods() {
		return isSubGoods;
	}

	public void setIsSubGoods(String isSubGoods) {
		this.isSubGoods = isSubGoods;
	}

	public List<Goods> getSubGoodsList() {
		return subGoodsList;
	}

	public void setSubGoodsList(List<Goods> subGoodsList) {
		this.subGoodsList = subGoodsList;
	}

	public List<String> getDateList() {
		return dateList;
	}

	public void setDateList(List<String> dateList) {
		this.dateList = dateList;
	}

	@Override
	public boolean isPublic() {
		return true;
	};

	static Log log = LogFactory.getLog(ShoppingCart.class);

	/**
	 * 跳转到信息页。。
	 * 
	 * @return
	 */
	@Action(value = "checkGoods", results = { @Result(name = StrutsResMSG.SUCCESS, type = "json") })
	public String checkGoods() {
		if(!StringHelper.isNull(uid)){
			ClientUserInfo clientUserInfo = clientUserInfoService.findByProperty("wxopenuid",uid);
			if(clientUserInfo!=null && clientUserInfo.getId()!=null){
				this.voList = shoppingCartService.listVO(clientUserInfo.getId().toString(), goodsId, 
						goodsCount, primaryConfigurationId, staffId, currentPriceType, null, null, 
						0, 0, "a.id", "asc");
				isSubGoods = "1";
				subGoodsList = new ArrayList<Goods>();
				for(int i = 0 ; voList.size() > i ; i++){
					if(voList.get(i)!=null){
						Goods goods = voList.get(i).getGoods();
						//判定如果有附属商品的话，购物车中是否有主商品
						if(goods.getGoodsParentId()!=null 
								&& !goods.getGoodsParentId().equals("")
								&& !goods.getGoodsParentId().equals("0")){
							isSubGoods = "0";
							String tempParentId = goods.getGoodsParentId();
							for(int j = 0 ; voList.size() > j ; j++){
								Goods ParenGoods = voList.get(j).getGoods();
								if(ParenGoods.getId()==Integer.parseInt(tempParentId)){
									isSubGoods = "1";
								}
							}
							if(isSubGoods.equals("0")){
								subGoodsList.add(goods);
							}
						}
					}
				}
			}
		}
		return StrutsResMSG.SUCCESS;
	}
	
	
	/**
	 * 跳转到信息页。。
	 * 
	 * @return
	 */
	@Action(value = "webList", results = { @Result(name = StrutsResMSG.SUCCESS, type = "json") })
	public String webList() {
		if(!StringHelper.isNull(uid)){
			ClientUserInfo clientUserInfo = clientUserInfoService.findByProperty("wxopenuid",uid);
			if(clientUserInfo!=null && clientUserInfo.getId()!=null){
				this.voList = shoppingCartService.listVO(clientUserInfo.getId().toString(), goodsId, 
						goodsCount, primaryConfigurationId, staffId, currentPriceType, null, null, 
						0, 0, "a.id", "asc");
				totalPrice = 0f;
				for(int i = 0 ; voList.size() > i ; i++){
					BigDecimal a1 = new BigDecimal(totalPrice);
					BigDecimal a2 = new BigDecimal(voList.get(i).getShoppingCartTotalPrice());
					totalPrice = a1.add(a2).floatValue();
				}
				//取得用户所有派送地址
				clientUserRegionInfoList = clientUserRegionInfoService.findByProperty("clientUserInfoId",clientUserInfo.getId());
				paymentProviderList = statusService.find(OrderRecord.class,"paymentProvider");
				logisticsCompanyList = logisticsCompanyService.listAll();
				//判定是否用户购买5个商品送1个商品（购物车中包含原价产品就送）
				Boolean fiveflag = true; //五送一状态
				Boolean firstflag = false; //首次下单状态
				int count = 0;
				for(int i = 0 ; voList.size() > i ; i++){
					if(voList.get(i)!=null){
						Goods goods = voList.get(i).getGoods();
//						判定是否有原价产品
						if(goods.getIsDiscountGoods()==121){
							firstflag = true;
//							fiveflag = false;
						}
//						判定是否套包产品
//						else if(goods.getGoodsCategoryId().equals("10")){
//							firstflag = true;
//							fiveflag = false;
//						}
//						else{
							count = count+Integer.parseInt(voList.get(i).getShoppingCartGoodsCount());
//						}
					}
				}
				//增加赠送菜品
				if(fiveflag&&count>=5){
					List<Status> freeStatusList = statusService.find(Goods.class,
							"freeGoods");
					freeGoodsList = new ArrayList<Goods>();
					if(freeStatusList!=null && freeStatusList.size()>0){
						for(int i=0 ; freeStatusList.size()>i ; i++){
							String freeGoodsId = freeStatusList.get(i).getStatusCode();
							Goods freeGoods = goodsService.findByid(freeGoodsId);
							freeGoodsList.add(freeGoods);
						}
					}
				}
				
				//判定用户是否首单，如果是，就送一个菜
				int userOrderCount = orderRecordService.getUserOrderCount(clientUserInfo.getId().toString());
				if(firstflag&&userOrderCount<=0){
					List<Status> freeOneStatusList = statusService.find(Goods.class,
							"freeOneGoods");
					oneGoodsList = new ArrayList<Goods>();
					if(freeOneStatusList!=null && freeOneStatusList.size()>0){
						for(int i=0 ; freeOneStatusList.size()>i ; i++){
							String freeGoodsId = freeOneStatusList.get(i).getStatusCode();
							Goods freeGoods = goodsService.findByid(freeGoodsId);
							oneGoodsList.add(freeGoods);
						}
					}
				}
			}
			//取得未来3天的日期时间
			Calendar cal=Calendar.getInstance();
			SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd");
			cal.add(Calendar.DATE,1);
			String a = f.format(cal.getTime());
			cal.add(Calendar.DATE,1);
			String b = f.format(cal.getTime());
			cal.add(Calendar.DATE,1);
			String c = f.format(cal.getTime());
			dateList = new ArrayList<String>();
			dateList.add(a);
			dateList.add(b);
			dateList.add(c);
		}

		return StrutsResMSG.SUCCESS;
	}
	
	
	/**
	 * 跳转到信息页。。
	 * 
	 * @return
	 */
	@Action(value = "findProvince", results = { @Result(name = StrutsResMSG.SUCCESS, type = "json") })
	public String findProvince() {
		provinceList = provinceService.listPO(countryId, null, 0, 0, "id", "asc");
		return StrutsResMSG.SUCCESS;
	}
	
	/**
	 * 跳转到信息页。。
	 * 
	 * @return
	 */
	@Action(value = "findCity", results = { @Result(name = StrutsResMSG.SUCCESS, type = "json") })
	public String findCity() {
		cityList = cityService.listPO(countryId, provinceId, null, 0, 0, "id", "asc");
		return StrutsResMSG.SUCCESS;
	}
	
	
	/**
	 * 跳转到信息页。。
	 * 
	 * @return
	 */
	@Action(value = "findArea", results = { @Result(name = StrutsResMSG.SUCCESS, type = "json") })
	public String findArea() {
		areaList = areaService.listPO(countryId, provinceId, cityId, null, 0, 0, "id", "asc");
		return StrutsResMSG.SUCCESS;
	}
	
	
	
	@Action(value = "del", results = { @Result(name = StrutsResMSG.SUCCESS, type = "json") })
    public String del() {
        clientUserRegionInfoService.del(clientUserRegionInfoId);
        return StrutsResMSG.SUCCESS;
    }
}
