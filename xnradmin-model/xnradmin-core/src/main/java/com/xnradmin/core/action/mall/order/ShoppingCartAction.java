/**
 *2012-5-10 下午3:52:29
 */
package com.xnradmin.core.action.mall.order;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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
import com.xnradmin.constant.AjaxResult;
import com.xnradmin.constant.StrutsResMSG;
import com.xnradmin.core.action.ParentAction;
import com.xnradmin.core.service.StaffService;
import com.xnradmin.core.service.common.status.StatusService;
import com.xnradmin.core.service.mall.commodity.GoodsService;
import com.xnradmin.core.service.mall.order.ShoppingCartService;
import com.xnradmin.core.service.mall.seting.PrimaryConfigurationService;
import com.xnradmin.po.CommonStaff;
import com.xnradmin.po.common.status.Status;
import com.xnradmin.po.mall.commodity.Goods;
import com.xnradmin.po.mall.order.ShoppingCart;
import com.xnradmin.po.mall.seting.PrimaryConfiguration;
import com.xnradmin.vo.StaffVO;
import com.xnradmin.vo.mall.OrderVO;
/**
 * @autohr: xiang_liu
 * 
 */
@Controller
@Scope("prototype")
@Namespace("/page/wx/admin/order/shoppingCart")
@ParentPackage("json-default")
public class ShoppingCartAction extends ParentAction {

	@Autowired
	private ShoppingCartService shoppingCartService;
	
	@Autowired
	private GoodsService goodsService;
	
	@Autowired
	private PrimaryConfigurationService primaryConfigurationService;

	@Autowired
	private StatusService statusService;

	@Autowired
	private StaffService staffService;

	private String shoppingCartId;
	private String clientUserId; //用户ID
	private String goodsId; //商品Id
    private String goodsCount; //商品数量
    private String currentPrice; //当前价格
    private String currentPriceType; //当前价格类型（原价，优惠价）
    private String staffId;	//隶属用户Id
    private String primaryConfigurationId; //对应商城ID
    private String shoppingCartTime; //生成时间 
    private String shoppingCartStartTime;
    private String shoppingCartEndTime;
	private List<PrimaryConfiguration> primaryConfigurationList;
	private List<ShoppingCart> shoppingCartList;
	private List<Goods> goodsList;
	private List<Status> currentPriceTypeList;
	private List<StaffVO> staffList;
	private CommonStaff currentStaff;
	private List<OrderVO> voList;
	private OrderVO orderVO;
	private ShoppingCart shoppingCart;
	private PrimaryConfiguration primaryConfiguration;
	private Status status;
	private Goods goods;
	private String userId;
	private Map<String,Integer> count_number;
	public ShoppingCartService getShoppingCartService() {
		return shoppingCartService;
	}

	public void setShoppingCartService(ShoppingCartService shoppingCartService) {
		this.shoppingCartService = shoppingCartService;
	}

	public PrimaryConfigurationService getPrimaryConfigurationService() {
		return primaryConfigurationService;
	}

	public void setPrimaryConfigurationService(
			PrimaryConfigurationService primaryConfigurationService) {
		this.primaryConfigurationService = primaryConfigurationService;
	}

	public StatusService getStatusService() {
		return statusService;
	}

	public void setStatusService(StatusService statusService) {
		this.statusService = statusService;
	}

	public StaffService getStaffService() {
		return staffService;
	}

	public void setStaffService(StaffService staffService) {
		this.staffService = staffService;
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

	public String getCurrentPrice() {
		return currentPrice;
	}

	public void setCurrentPrice(String currentPrice) {
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

	public List<PrimaryConfiguration> getPrimaryConfigurationList() {
		return primaryConfigurationList;
	}

	public void setPrimaryConfigurationList(
			List<PrimaryConfiguration> primaryConfigurationList) {
		this.primaryConfigurationList = primaryConfigurationList;
	}

	public List<ShoppingCart> getShoppingCartList() {
		return shoppingCartList;
	}

	public void setShoppingCartList(List<ShoppingCart> shoppingCartList) {
		this.shoppingCartList = shoppingCartList;
	}

	public List<StaffVO> getStaffList() {
		return staffList;
	}

	public void setStaffList(List<StaffVO> staffList) {
		this.staffList = staffList;
	}

	public CommonStaff getCurrentStaff() {
		return currentStaff;
	}

	public void setCurrentStaff(CommonStaff currentStaff) {
		this.currentStaff = currentStaff;
	}

	public List<OrderVO> getVoList() {
		return voList;
	}

	public void setVoList(List<OrderVO> voList) {
		this.voList = voList;
	}

	public OrderVO getOrderVO() {
		return orderVO;
	}

	public void setOrderVO(OrderVO orderVO) {
		this.orderVO = orderVO;
	}

	public ShoppingCart getShoppingCart() {
		return shoppingCart;
	}

	public void setShoppingCart(ShoppingCart shoppingCart) {
		this.shoppingCart = shoppingCart;
	}

	public PrimaryConfiguration getPrimaryConfiguration() {
		return primaryConfiguration;
	}

	public void setPrimaryConfiguration(PrimaryConfiguration primaryConfiguration) {
		this.primaryConfiguration = primaryConfiguration;
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

	public String getShoppingCartStartTime() {
		return shoppingCartStartTime;
	}

	public void setShoppingCartStartTime(String shoppingCartStartTime) {
		this.shoppingCartStartTime = shoppingCartStartTime;
	}

	public String getShoppingCartEndTime() {
		return shoppingCartEndTime;
	}

	public void setShoppingCartEndTime(String shoppingCartEndTime) {
		this.shoppingCartEndTime = shoppingCartEndTime;
	}

	public List<Status> getCurrentPriceTypeList() {
		return currentPriceTypeList;
	}

	public void setCurrentPriceTypeList(List<Status> currentPriceTypeList) {
		this.currentPriceTypeList = currentPriceTypeList;
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

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Map<String, Integer> getCount_number() {
		return count_number;
	}

	public void setCount_number(Map<String, Integer> count_number) {
		this.count_number = count_number;
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
	@Action(value = "info", results = {
			@Result(name = StrutsResMSG.SUCCESS, location = "/wx/admin/order/shoppingCart/info.jsp"),
			@Result(name = StrutsResMSG.FAILED, location = "/wx/admin/order/shoppingCart/info.jsp") })
	public String info() {
		setPageInfo();
		return StrutsResMSG.SUCCESS;
	}

	/**
	 * 跳转到信息页。。
	 * 
	 * @return
	 */
	@Action(value = "lookup", results = {
			@Result(name = StrutsResMSG.SUCCESS, location = "/wx/admin/order/shoppingCart/lookup.jsp"),
			@Result(name = StrutsResMSG.FAILED, location = "/wx/admin/order/shoppingCart/lookup.jsp") })
	public String lookup() {
		setPageInfo();
		return StrutsResMSG.SUCCESS;
	}
	
	private void setPageInfo() {
		// 设置排序
		setCurrentPriceTypeList();
		setStaffList();
		setPrimaryConfigurationList();
		setGoodsList();
		this.voList = shoppingCartService.listVO(clientUserId, goodsId, goodsCount, primaryConfigurationId, 
				staffId, currentPriceType, shoppingCartStartTime, shoppingCartEndTime, 
				getPageNum(), getNumPerPage(), orderField, orderDirection);

		this.totalCount = shoppingCartService.getCount(clientUserId, goodsId, goodsCount, primaryConfigurationId, 
				staffId, currentPriceType, shoppingCartStartTime, shoppingCartEndTime);
	}

	/**
	 * 加载价格类型
	 */
	private void setCurrentPriceTypeList() {
		this.currentPriceTypeList = statusService.find(ShoppingCartAction.class,
				"currentPriceType");
	}

	/*
	 * 加载所有用户
	 */
	private void setStaffList() {
		this.staffList = staffService.listVO("","","", "", "", "", "", "", "", 0, 0,
				0, "", "");
	}

	/**
	 * 加载所有商城
	 */
	private void setPrimaryConfigurationList() {
		this.primaryConfigurationList = primaryConfigurationService.listAll();
	}
	
	/**
	 * 加载所有分类
	 */
	private void setGoodsList() {
		this.goodsList = goodsService.listAll();
	}

	@Action(value = "addinfo", results = {
			@Result(name = StrutsResMSG.SUCCESS, location = "/wx/admin/order/shoppingCart/add.jsp"),
			@Result(name = StrutsResMSG.FAILED, location = "/wx/admin/order/shoppingCart/add.jsp") })
	public String addInfo() {
		// 加载状态与类型
		setCurrentPriceTypeList();
		setStaffList();
		setPrimaryConfigurationList();
		setGoodsList();
		currentStaff = super.getCurrentStaff();
		return StrutsResMSG.SUCCESS;
	}

	/**
	 * 保存对象接口
	 * 
	 * @return String
	 * @throws Exception
	 */
	@Action(value = "add", results = { @Result(name = StrutsResMSG.SUCCESS, type = "plainText") })
	public String add() throws Exception {
		// 取得当前登录人信息
		currentStaff = super.getCurrentStaff();
		ShoppingCart po = new ShoppingCart();
		if(!StringHelper.isNull(shoppingCartId)){
			po.setId(Integer.parseInt(shoppingCartId));
		}
		if(!StringHelper.isNull(clientUserId)){
			po.setClientUserId(Integer.parseInt(clientUserId));
		}
		if(goods!=null){
			if(goods.getId()!=null){
				po.setGoodsId(goods.getId());
			}
			if(goods.getPrimaryConfigurationId()!=null){
				po.setPrimaryConfigurationId(goods.getPrimaryConfigurationId());
			}
			else{
				if(primaryConfiguration != null){
					po.setPrimaryConfigurationId(primaryConfiguration.getId());
				}
			}
		}
		if(goodsCount != null){
			po.setGoodsCount(Integer.parseInt(goodsCount));
		}
		if(currentPrice != null){
			po.setCurrentPrice(Float.valueOf(currentPrice));
		}
		if (currentPriceType != null) {
			po.setCurrentPriceType(Integer.parseInt(currentPriceType));
		}
		po.setStaffId(staffId);
		po.setShoppingCartTime(new Timestamp(System.currentTimeMillis()));
		shoppingCartService.save(po);
		super.success(null, AjaxResult.CALL_BACK_TYPE_CLOSECURRENT, "shoppingCart",
				null);
		return null;
	}

	/**
	 * 带信息到修改页
	 * 
	 * @return String
	 */
	@Action(value = "modifyinfo", results = {
			@Result(name = StrutsResMSG.SUCCESS, location = "/wx/admin/order/shoppingCart/modify.jsp"),
			@Result(name = StrutsResMSG.FAILED, location = "/wx/admin/order/shoppingCart/modify.jsp") })
	public String modifyinfo() {
		setCurrentPriceTypeList();
		setStaffList();
		setPrimaryConfigurationList();
		setGoodsList();
		ShoppingCart po = new ShoppingCart();
		po = shoppingCartService.findByid(shoppingCartId);
		if(po.getClientUserId() != null){
			this.clientUserId = po.getClientUserId().toString();
		}
		if(po.getGoodsId() != null){
			this.goodsId = po.getGoodsId().toString();
		}
		if(po.getGoodsCount() != null){
			this.goodsCount = po.getGoodsCount().toString();
		}
		if (po.getCurrentPrice() != null) {
			this.currentPrice = po.getCurrentPrice().toString();
		}
		if (po.getCurrentPriceType() != null) {
			this.currentPriceType = po.getCurrentPriceType().toString();
		}
		if (po.getPrimaryConfigurationId() != null) {
			this.primaryConfigurationId = po.getPrimaryConfigurationId().toString();
		}
		this.staffId = po.getStaffId();
		if (po.getShoppingCartTime() != null) {
			this.shoppingCartTime = po.getShoppingCartTime().toString();
		}
		return StrutsResMSG.SUCCESS;
	}

	/**
	 * 更新对象接口
	 * 
	 * @return String
	 * @throws Exception
	 */
	@Action(value = "modify", results = { @Result(name = StrutsResMSG.SUCCESS, type = "plainText") })
	public String modify() throws Exception {
		log.debug("modif action!");
		log.debug("modify shoppingCart: " + shoppingCartId);
		// 取得当前登录人信息
		currentStaff = super.getCurrentStaff();
		ShoppingCart oldpo = shoppingCartService.findByid(shoppingCartId);
		ShoppingCart po = new ShoppingCart();
		po.setId(Integer.parseInt(shoppingCartId));
		if (!StringHelper.isNull(clientUserId)) {
			po.setClientUserId(Integer.parseInt(clientUserId));
		} else {
			po.setClientUserId(oldpo.getClientUserId());
		}
		if(goods!=null){
			if(goods.getId()!=null){
				po.setGoodsId(goods.getId());
			} else {
				po.setGoodsId(oldpo.getGoodsId());
			}
			if(goods.getPrimaryConfigurationId()!=null){
				po.setPrimaryConfigurationId(goods.getPrimaryConfigurationId());
			} else {
				po.setPrimaryConfigurationId(oldpo.getPrimaryConfigurationId());
			}
		}
		if (!StringHelper.isNull(goodsCount)) {
			po.setGoodsCount(Integer.parseInt(goodsCount));
		} else {
			po.setGoodsCount(oldpo.getGoodsCount());
		}
		if (!StringHelper.isNull(currentPrice)) {
			po.setCurrentPrice(Float.valueOf(currentPrice));
		} else {
			po.setCurrentPrice(oldpo.getCurrentPrice());
		}
		if (!StringHelper.isNull(currentPriceType)) {
			po.setCurrentPriceType(Integer.parseInt(currentPriceType));
		} else {
			po.setCurrentPriceType(oldpo.getCurrentPriceType());
		}
		po.setStaffId(staffId);
		po.setShoppingCartTime(new Timestamp(System.currentTimeMillis()));
		int res = this.shoppingCartService.modify(po);
		super.success(null, AjaxResult.CALL_BACK_TYPE_CLOSECURRENT, "shoppingCart",
				null);
		return null;
	}

	@Action(value = "del", results = { @Result(name = StrutsResMSG.SUCCESS, type = "plainText") })
	public String del() throws IOException, JSONException {
		shoppingCartService
				.removeShoppingCartId(shoppingCartId);
		super.success(null, AjaxResult.CALL_BACK_TYPE_CLOSECURRENT, "shoppingCart",
				null);
		return null;
	}

	/**
	 * 外部调用，获取所有组织信息
	 * 
	 * @return String
	 * @throws IOException
	 */
	@Action(value = "all", results = { @Result(name = StrutsResMSG.SUCCESS, type = "plainText") })
	public String all() throws IOException {
		super.toJsonArray(shoppingCartService.listAll());
		return null;
	}
	@Action(value="getTotalAndNumber",results = {@Result(name = StrutsResMSG.SUCCESS, type="json")})
	public String getTotalAndNumber()
	{
		count_number = shoppingCartService.getCartMoney(userId);
		return StrutsResMSG.SUCCESS;
	}
}
