package com.xnradmin.client.action.front;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
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
import com.xnradmin.client.service.front.FrontUserService;
import com.xnradmin.constant.AjaxResult;
import com.xnradmin.constant.StrutsResMSG;
import com.xnradmin.core.action.ParentAction;
import com.xnradmin.core.service.StaffService;
import com.xnradmin.core.service.business.commodity.BusinessGoodsService;
import com.xnradmin.core.service.common.status.StatusService;
import com.xnradmin.core.service.mall.clientUser.ClientUserInfoService;
import com.xnradmin.core.service.mall.commodity.GoodsService;
import com.xnradmin.core.service.mall.order.ShoppingCartService;
import com.xnradmin.core.service.mall.seting.PrimaryConfigurationService;
import com.xnradmin.po.CommonStaff;
import com.xnradmin.po.business.BusinessGoods;
import com.xnradmin.po.client.ClientUserInfo;
import com.xnradmin.po.common.status.Status;
import com.xnradmin.po.front.FrontUser;
import com.xnradmin.po.mall.commodity.Goods;
import com.xnradmin.po.mall.order.ShoppingCart;
import com.xnradmin.po.mall.seting.PrimaryConfiguration;
import com.xnradmin.vo.StaffVO;
import com.xnradmin.vo.front.BusinessGoodsCartVo;
import com.xnradmin.vo.mall.OrderVO;
/**
 * @autohr: xiang_liu
 * 
 */
@Controller
@Scope("prototype")
@Namespace("/front/shopingCart")
@ParentPackage("json-default")
public class CartAction extends ParentAction {

	@Autowired
	private ShoppingCartService shoppingCartService;
	
	@Autowired
	private BusinessGoodsService goodsService;
	
	@Autowired
	private FrontUserService userService;

	
	
	@Autowired
	private StatusService statusService;

    private FrontUser user;
	private String shoppingCartId;
	private String clientUserId; //用户ID
	private String goodsId; //商品Id
    private Integer goodsCount; //商品数量
    private Float currentPrice; //当前单价
    private String currentPriceType; //当前价格类型（原价，优惠价）
    private Float totalPrice; //当前总价
    private String staffId;	//隶属用户Id
    private String primaryConfigurationId; //对应商城ID
    private String shoppingCartTime; //生成时间 
    private String uid;
	private List<OrderVO> voList;
	private List<Goods> goodsList;
	private List<Status> currentPriceTypeList;
	private ShoppingCart shoppingCart;
	private Status status;
	private Goods goods;
	private boolean delStatus;//删除购物车
	private List<BusinessGoodsCartVo> cartVoList;
	

	
	
	

	public boolean isDelStatus() {
		return delStatus;
	}

	public void setDelStatus(boolean delStatus) {
		this.delStatus = delStatus;
	}

	public List<BusinessGoodsCartVo> getCartVoList() {
		return cartVoList;
	}

	public void setCartVoList(List<BusinessGoodsCartVo> cartVoList) {
		this.cartVoList = cartVoList;
	}

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

	public List<Status> getCurrentPriceTypeList() {
		return currentPriceTypeList;
	}

	public void setCurrentPriceTypeList(List<Status> currentPriceTypeList) {
		this.currentPriceTypeList = currentPriceTypeList;
	}

	public BusinessGoodsService getBusinessGoodsService() {
		return goodsService;
	}

	public void setBusinessGoodsService(BusinessGoodsService goodsService) {
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
	
	
	
	

	public FrontUser getUser() {
		return user;
	}

	public void setUser(FrontUser user) {
		this.user = user;
	}

	@Override
	public boolean isPublic() {
		return true;
	};

	static Log log = LogFactory.getLog(ShoppingCart.class);

	
	
	/**
	 * 跳转到购物车页面
	 * @return
	 */
	@Action(value = "shoppingCart",results = { @Result(name = StrutsResMSG.SUCCESS, location = "/front/shoppingCart.jsp"),@Result(name = StrutsResMSG.FAILED, location = "/front/register.jsp") })
    public String shoppingCart() {
		
		 user =  (FrontUser)ServletActionContext.getRequest().getSession().getAttribute("user");
		
		 cartVoList = shoppingCartService.findByUserId(Integer.parseInt(user.getId().toString()));
		
		return StrutsResMSG.SUCCESS;
		
    }
	
	

	/**
	 * 保存对象接口
	 * 
	 * @return String
	 * @throws Exception
	 */
	@Action(value = "add", results = { @Result(name = StrutsResMSG.SUCCESS, type = "json") })
	public String add() throws Exception {
		ShoppingCart po = new ShoppingCart();
		if(!StringHelper.isNull(clientUserId) && !StringHelper.isNull(goodsId) &&!StringHelper.isNull(goodsCount.toString())){
			FrontUser frontUser = userService.findByid(clientUserId);
			BusinessGoods goods = goodsService.findByid(goodsId);
			if(frontUser!=null && goods!=null && frontUser.getId()!=null){
				po.setClientUserId(Integer.parseInt(frontUser.getId().toString()));
				po.setGoodsId(goods.getId());
				po.setGoodsCount(goodsCount);
				
				
				po.setCurrentPrice(goods.getGoodsOriginalPrice());
				po.setTotalPrice(goods.getGoodsOriginalPrice()*goodsCount);
				po.setCurrentPriceType(121);
				
				po.setOriginalPrice(goods.getGoodsOriginalPrice());
				po.setOriginalTotalPrice(goods.getGoodsOriginalPrice()*goodsCount);
				po.setPrimaryConfigurationId(1);
				po.setShoppingCartTime(new Timestamp(System.currentTimeMillis()));
				int res = shoppingCartService.save(po);
				//如果用户已经添加过这个商品，就把这个商品数量加1
				/*if(res==1){
					List<ShoppingCart> poList = shoppingCartService.webList(clientUserInfo.getId().toString(), 
							goodsId, null, "1", null, null, 0, 0, "id", "asc");
					if(poList!=null && poList.size()>0){
						ShoppingCart oldpo = poList.get(0);
						if(oldpo!=null){
							int tempGoodsCount = oldpo.getGoodsCount()+1;
							oldpo.setGoodsCount(tempGoodsCount);
							if(tempGoods.getIsDiscountGoods()==120){
								oldpo.setCurrentPrice(tempGoods.getGoodsPreferentialPrice());
								oldpo.setCurrentPriceType(120);
							}
							else{
								oldpo.setCurrentPrice(tempGoods.getGoodsOriginalPrice());
								oldpo.setCurrentPriceType(121);
							}
							oldpo.setOriginalPrice(tempGoods.getGoodsOriginalPrice());
							BigDecimal b1 = new BigDecimal(oldpo.getCurrentPrice());
							BigDecimal b2 = new BigDecimal(oldpo.getGoodsCount());
							BigDecimal b3 = new BigDecimal(oldpo.getOriginalPrice());
							oldpo.setTotalPrice(b1.multiply(b2).floatValue());
							oldpo.setOriginalTotalPrice(b3.multiply(b2).floatValue());
							shoppingCartService.modify(oldpo);
						}
					}
				}*/
			}
		}
		return StrutsResMSG.SUCCESS;
	}

	/**
	 * 更新对象接口
	 * 
	 * @return String
	 * @throws Exception
	 */
	@Action(value = "modify", results = { @Result(name = StrutsResMSG.SUCCESS, type = "json") })
	public String modify() throws Exception {
		log.debug("modif action!");
		log.debug("modify shoppingCart: " + shoppingCartId);
		// 取得当前登录人信息
		/*if(!StringHelper.isNull(uid) && !StringHelper.isNull(goodsId) && !StringHelper.isNull(shoppingCartId)
				&& !StringHelper.isNull(goodsCount)){
			ClientUserInfo clientUserInfo = clientUserInfoService.findByProperty("wxopenuid",uid);
			Goods tempGoods = goodsService.findByid(goodsId);
			ShoppingCart po = shoppingCartService.findByid(shoppingCartId);
			if(clientUserInfo!=null && tempGoods!=null && po!=null && clientUserInfo.getId()!=null){
				if(tempGoods.getIsDiscountGoods()==120){
					po.setCurrentPrice(tempGoods.getGoodsPreferentialPrice());
					po.setCurrentPriceType(120);
				}
				else{
					po.setCurrentPrice(tempGoods.getGoodsOriginalPrice());
					po.setCurrentPriceType(121);
				}
				po.setOriginalPrice(tempGoods.getGoodsOriginalPrice());
				po.setGoodsCount(Integer.parseInt(goodsCount));
				BigDecimal b1 = new BigDecimal(po.getCurrentPrice());
				BigDecimal b2 = new BigDecimal(po.getGoodsCount());
				BigDecimal b3 = new BigDecimal(po.getOriginalPrice());
				po.setTotalPrice(b1.multiply(b2).floatValue());
				po.setOriginalTotalPrice(b3.multiply(b2).floatValue());
				po.setShoppingCartTime(new Timestamp(System.currentTimeMillis()));
				shoppingCartService.modify(po);
			}
		}*/
		return StrutsResMSG.SUCCESS;
	}

	@Action(value = "del", results = { @Result(name = StrutsResMSG.SUCCESS, type = "json") })
	public String del() throws IOException, JSONException {
		if(null!=ServletActionContext.getRequest().getSession().getAttribute("user")){
			this.delStatus = shoppingCartService.removeShoppingCartById(shoppingCartId);
		}
		return StrutsResMSG.SUCCESS;
	}
}
