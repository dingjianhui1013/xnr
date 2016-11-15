/**
 *2012-5-10 下午3:52:29
 */
package com.xnradmin.core.action.stat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
import com.xnradmin.core.service.dishes.CollocationService;
import com.xnradmin.core.service.dishes.DishService;
import com.xnradmin.core.service.dishes.UploadOrderService;
import com.xnradmin.po.common.status.Status;
import com.xnradmin.po.dishes.Collocation;
import com.xnradmin.po.dishes.Dish;
import com.xnradmin.po.dishes.RawMaterial;
import com.xnradmin.po.dishes.UploadOrder;
import com.xnradmin.vo.StaffVO;
import com.xnradmin.vo.dishes.DishesVO;

/**
 * @autohr: xiang_liu
 * 
 */
@Controller
@Scope("prototype")
@Namespace("/page/stat/order")
@ParentPackage("json-default")
public class UploadOrderStatAction extends ParentAction {

	@Autowired
	private UploadOrderService service;

	@Autowired
	private StatusService statusService;

	@Autowired
	private StaffService staffService;

	@Override
	public boolean isPublic() {
		return false;
	};

	static Log log = LogFactory.getLog(UploadOrderStatAction.class);

	private String dishId;  		//菜品ID
    private String dishName;  		//菜品名
    private String introduction;  	//菜品介绍
    private String cookingMethod;  	//烹饪方法
    private String picUrl;  		//图片连接
    private String dishTypeId;  	//菜品类型
    private String dishTypeName;    //菜品名称
	private String createTime;  	//建立时间
	private String createStartTime; //查询建立起始时间
	private String createEndTime; 	//查询建立结束时间
	private String createStaffId;  	//建立人ID
	private String createStaffName; //建立人名
	private String modifyTime;  	//修改时间
	private String modifyStaffId;  	//修改人ID
	private String modifyStaffName; //修改人名称
	private String rawMaterialId;  	//原料ID
    private String materialName;  	//原料名
    private String materialTypeId; 	//原料类型
    private String weightId;  		//重量单位ID
    private String weightName;  	//重量单位名
    private String number;  		//单位数量
	private String collocationId;	//配比ID
	//订单上传
	private String uploadOrderId;	//上传订单表ID
	private String orderNumber;		//订单编号
    private String orderTime;		//下单时间
    private String orderStartTime;	//查询下单起始时间
    private String orderEndTime;	//查询下单结束时间
	private String property;		//商品属性
	private String unitPrice;  		//商品单价
	private String count;  			//商品数量
	private String totalPrice;  	//商品总价
	private	String payStatusId;		//支付状态
	private String dispatchStatusId;//派单状态
	private String orderStatusId;	//订单状态
	private String customer;		//收货人
	private String mobile;			//手机号
	private String address;			//地址
	private String code;			//邮编
	private DishesVO dishesVo;
	private UploadOrder uploadOrder;
	private List<DishesVO> voList;
	private List<Status> materialTypeList;
	private List<Status> dishTypeList;
	private List<Status> weightList;
	private List<StaffVO> staffList;
	private List<Status> payStatusList;
	private List<Status> orderStatusList;
	private List<Status> dispatchStatusList;
	private List<String[]> contentList;
	public UploadOrderService getService() {
		return service;
	}


	public void setService(UploadOrderService service) {
		this.service = service;
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


	public String getDishId() {
		return dishId;
	}


	public void setDishId(String dishId) {
		this.dishId = dishId;
	}


	public String getDishName() {
		return dishName;
	}


	public void setDishName(String dishName) {
		this.dishName = dishName;
	}


	public String getIntroduction() {
		return introduction;
	}


	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}


	public String getCookingMethod() {
		return cookingMethod;
	}


	public void setCookingMethod(String cookingMethod) {
		this.cookingMethod = cookingMethod;
	}


	public String getPicUrl() {
		return picUrl;
	}


	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}


	public String getDishTypeId() {
		return dishTypeId;
	}


	public void setDishTypeId(String dishTypeId) {
		this.dishTypeId = dishTypeId;
	}


	public String getDishTypeName() {
		return dishTypeName;
	}


	public void setDishTypeName(String dishTypeName) {
		this.dishTypeName = dishTypeName;
	}


	public String getCreateTime() {
		return createTime;
	}


	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}


	public String getCreateStaffId() {
		return createStaffId;
	}


	public void setCreateStaffId(String createStaffId) {
		this.createStaffId = createStaffId;
	}


	public String getCreateStaffName() {
		return createStaffName;
	}


	public void setCreateStaffName(String createStaffName) {
		this.createStaffName = createStaffName;
	}


	public String getModifyTime() {
		return modifyTime;
	}


	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
	}


	public String getModifyStaffId() {
		return modifyStaffId;
	}


	public void setModifyStaffId(String modifyStaffId) {
		this.modifyStaffId = modifyStaffId;
	}


	public String getModifyStaffName() {
		return modifyStaffName;
	}


	public void setModifyStaffName(String modifyStaffName) {
		this.modifyStaffName = modifyStaffName;
	}


	public String getRawMaterialId() {
		return rawMaterialId;
	}


	public void setRawMaterialId(String rawMaterialId) {
		this.rawMaterialId = rawMaterialId;
	}


	public String getMaterialName() {
		return materialName;
	}


	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}


	public String getMaterialTypeId() {
		return materialTypeId;
	}


	public void setMaterialTypeId(String materialTypeId) {
		this.materialTypeId = materialTypeId;
	}


	public String getWeightId() {
		return weightId;
	}


	public void setWeightId(String weightId) {
		this.weightId = weightId;
	}


	public String getWeightName() {
		return weightName;
	}


	public void setWeightName(String weightName) {
		this.weightName = weightName;
	}


	public String getNumber() {
		return number;
	}


	public void setNumber(String number) {
		this.number = number;
	}


	public String getCollocationId() {
		return collocationId;
	}


	public void setCollocationId(String collocationId) {
		this.collocationId = collocationId;
	}


	public String getUploadOrderId() {
		return uploadOrderId;
	}


	public void setUploadOrderId(String uploadOrderId) {
		this.uploadOrderId = uploadOrderId;
	}


	public String getOrderNumber() {
		return orderNumber;
	}


	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}


	public String getOrderTime() {
		return orderTime;
	}


	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}


	public String getProperty() {
		return property;
	}


	public void setProperty(String property) {
		this.property = property;
	}


	public String getUnitPrice() {
		return unitPrice;
	}


	public void setUnitPrice(String unitPrice) {
		this.unitPrice = unitPrice;
	}


	public String getCount() {
		return count;
	}


	public void setCount(String count) {
		this.count = count;
	}


	public String getTotalPrice() {
		return totalPrice;
	}


	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}


	public String getPayStatusId() {
		return payStatusId;
	}


	public void setPayStatusId(String payStatusId) {
		this.payStatusId = payStatusId;
	}


	public String getDispatchStatusId() {
		return dispatchStatusId;
	}


	public void setDispatchStatusId(String dispatchStatusId) {
		this.dispatchStatusId = dispatchStatusId;
	}


	public String getOrderStatusId() {
		return orderStatusId;
	}


	public void setOrderStatusId(String orderStatusId) {
		this.orderStatusId = orderStatusId;
	}


	public String getCustomer() {
		return customer;
	}


	public void setCustomer(String customer) {
		this.customer = customer;
	}


	public String getMobile() {
		return mobile;
	}


	public void setMobile(String mobile) {
		this.mobile = mobile;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getCode() {
		return code;
	}


	public void setCode(String code) {
		this.code = code;
	}


	public String getCreateStartTime() {
		return createStartTime;
	}


	public void setCreateStartTime(String createStartTime) {
		this.createStartTime = createStartTime;
	}


	public String getCreateEndTime() {
		return createEndTime;
	}


	public void setCreateEndTime(String createEndTime) {
		this.createEndTime = createEndTime;
	}


	public String getOrderStartTime() {
		return orderStartTime;
	}


	public void setOrderStartTime(String orderStartTime) {
		this.orderStartTime = orderStartTime;
	}


	public String getOrderEndTime() {
		return orderEndTime;
	}


	public void setOrderEndTime(String orderEndTime) {
		this.orderEndTime = orderEndTime;
	}


	public DishesVO getDishesVo() {
		return dishesVo;
	}


	public void setDishesVo(DishesVO dishesVo) {
		this.dishesVo = dishesVo;
	}


	public List<DishesVO> getVoList() {
		return voList;
	}


	public void setVoList(List<DishesVO> voList) {
		this.voList = voList;
	}

	
	public List<Status> getMaterialTypeList() {
		return materialTypeList;
	}


	public void setMaterialTypeList(List<Status> materialTypeList) {
		this.materialTypeList = materialTypeList;
	}


	public List<Status> getDishTypeList() {
		return dishTypeList;
	}


	public void setDishTypeList(List<Status> dishTypeList) {
		this.dishTypeList = dishTypeList;
	}


	public List<Status> getWeightList() {
		return weightList;
	}


	public void setWeightList(List<Status> weightList) {
		this.weightList = weightList;
	}


	public List<StaffVO> getStaffList() {
		return staffList;
	}


	public void setStaffList(List<StaffVO> staffList) {
		this.staffList = staffList;
	}


	public List<Status> getPayStatusList() {
		return payStatusList;
	}


	public void setPayStatusList(List<Status> payStatusList) {
		this.payStatusList = payStatusList;
	}


	public List<Status> getOrderStatusList() {
		return orderStatusList;
	}


	public void setOrderStatusList(List<Status> orderStatusList) {
		this.orderStatusList = orderStatusList;
	}


	public List<Status> getDispatchStatusList() {
		return dispatchStatusList;
	}


	public void setDispatchStatusList(List<Status> dispatchStatusList) {
		this.dispatchStatusList = dispatchStatusList;
	}


	public List<String[]> getContentList() {
		return contentList;
	}


	public void setContentList(List<String[]> contentList) {
		this.contentList = contentList;
	}


	public UploadOrder getUploadOrder() {
		return uploadOrder;
	}


	public void setUploadOrder(UploadOrder uploadOrder) {
		this.uploadOrder = uploadOrder;
	}


	/**
	 * 加载菜品类型
	 */
	private void setDishTypeList() {
		this.dishTypeList = statusService.find(Dish.class, "dishType");
	}

	/**
	 * 加载原料类型
	 */
	private void setMaterialTypeList() {
		this.materialTypeList = statusService.find(RawMaterial.class,
				"materialType");
	}

	/**
	 * 加载重量单位
	 */
	private void setWegihtList() {
		this.weightList = statusService.find(Collocation.class, "weight");
	}
	
	/*
	 * 加载所有用户 
	 * */
	private void setStaffList(){
		this.staffList = staffService.listVO("","","", "", "", "", "", "", "", 0, 0, 0, "", "");
	}

	/**
	 * 加载订单状态
	 */
	private void setOrderStatusList() {
		this.orderStatusList = statusService.find(UploadOrder.class, "orderStatus");
	}

	
	/**
	 * 加载配送状态
	 */
	private void setDispatchStatusList() {
		this.dispatchStatusList = statusService.find(UploadOrder.class, "dispatchStatus");
	}

	
	/**
	 * 加载支付状态
	 */
	private void setPayStatusList() {
		this.payStatusList = statusService.find(UploadOrder.class, "payStatus");
	}

	/**
	 * 详细信息
	 * 
	 * @return
	 */
	@Action(value = "info", results = {
			@Result(name = StrutsResMSG.SUCCESS, location = "/stat/order/info.jsp"),
			@Result(name = StrutsResMSG.FAILED, location = "/stat/order/info.jsp") })
	public String info() {
		setPageInfo();
		return StrutsResMSG.SUCCESS;
	}

	
	/**
	 * 菜品汇总
	 * 
	 * @return
	 */
	@Action(value = "dishCount", results = {
			@Result(name = StrutsResMSG.SUCCESS, location = "/stat/order/dishCount.jsp"),
			@Result(name = StrutsResMSG.FAILED, location = "/stat/order/dishCount.jsp") })
	public String orderList() {
		setDateTime();
		setOrderStatusList();
		setDispatchStatusList();
		setPayStatusList();
		List<Object[]> list = service.getDishCount(orderNumber, orderStartTime, orderEndTime, dishName, 
				property, unitPrice, count, totalPrice, payStatusId, dispatchStatusId, 
				orderStatusId, customer, mobile, address, code, createStaffId, createStartTime, 
				createEndTime, getPageNum(), getNumPerPage(), orderField, orderDirection);

		contentList = new ArrayList<String[]>();
		for (int i = 0; i < list.size(); i++) {
			String[] content = new String[2];
			Object[] a = list.get(i);
			if (a == null)
				continue;
			content[0] = a[0] == null ? "0" : a[0].toString();
			content[1] = a[1] == null ? "0" : a[1].toString();
			contentList.add(content);
		}
		return StrutsResMSG.SUCCESS;
	}

	/**
	 * 菜品汇总
	 * 
	 * @return
	 */
	@Action(value = "rawMaterialCount", results = {
			@Result(name = StrutsResMSG.SUCCESS, location = "/stat/order/rawMaterialCount.jsp"),
			@Result(name = StrutsResMSG.FAILED, location = "/stat/order/rawMaterialCount.jsp") })
	public String getRawMaterialCount() {
		setDateTime();
		setOrderStatusList();
		setDispatchStatusList();
		setPayStatusList();
		List<Object[]> list = service.getRawMaterialCount(orderStartTime, orderEndTime,
				dishName, unitPrice, count, totalPrice, payStatusId, dispatchStatusId, 
				orderStatusId, customer, mobile, address, code, createStaffId, createStartTime, 
				createEndTime, materialName, getPageNum(), getNumPerPage(), orderField, orderDirection);

		contentList = new ArrayList<String[]>();
		for (int i = 0; i < list.size(); i++) {
			String[] content = new String[3];
			Object[] a = list.get(i);
			if (a == null)
				continue;
			content[0] = a[0] == null ? "0" : a[0].toString();
			content[1] = a[1] == null ? "0" : a[1].toString();
			content[2] = a[2] == null ? "0" : a[2].toString();
			contentList.add(content);
		}
		return StrutsResMSG.SUCCESS;
	}
	
	@Action(value = "orderDetailed", results = {
			@Result(name = StrutsResMSG.SUCCESS, location = "/stat/order/orderDetailed.jsp"),
			@Result(name = StrutsResMSG.FAILED, location = "/stat/order/orderDetailed.jsp") })
	public String orderDetailed() {
		setDateTime();
		setOrderStatusList();
		setDispatchStatusList();
		setPayStatusList();
		uploadOrder = service.findByid(uploadOrderId);
		return StrutsResMSG.SUCCESS;
	}

	private void setPageInfo() {
		// 设置排序
		setDateTime();
		setDishTypeList();
		setMaterialTypeList();
		setWegihtList();
		setStaffList();
		setOrderStatusList();
		setDispatchStatusList();
		setPayStatusList();
		setSort(orderField, orderDirection);
		this.totalCount = service.getCount(uploadOrderId, orderNumber, orderStartTime, 
				orderEndTime, dishName, property, unitPrice, count, totalPrice, 
				payStatusId, dispatchStatusId, orderStatusId, customer, mobile, 
				address, code, createStaffId, createStartTime, createEndTime);

		this.voList = service.listVO(uploadOrderId, orderNumber, orderStartTime, orderEndTime, 
				dishName, property, unitPrice, count, totalPrice, payStatusId, dispatchStatusId, 
				orderStatusId, customer, mobile, address, code, createStaffId, createStartTime, 
				createEndTime, getPageNum(), getNumPerPage(), orderField, orderDirection);
	}

	private void setDateTime() {
		// 设置默认时间
		if (StringHelper.isNull(orderStartTime)
				&& StringHelper.isNull(orderEndTime)) {
			this.orderStartTime = StringHelper.getSystime("yyyy-MM-dd");
			orderStartTime = orderStartTime + " 00:00:00";
			this.orderEndTime = StringHelper.getSystime("yyyy-MM-dd");
			orderEndTime = orderEndTime + " 23:59:59";
		}
	}

	/**
	 * 设置排序相关项
	 */
	private void setSort(String orderField, String orderDirection) {
		if (!StringHelper.isNull(orderField)) {
			this.orderField = orderField;
			this.orderDirection = orderDirection;
		}
	}

	/**
	 * 外部调用，获取所有组织信息
	 * 
	 * @return String
	 * @throws IOException
	 */
	@Action(value = "all", results = { @Result(name = StrutsResMSG.SUCCESS, type = "plainText") })
	public String all() throws IOException {
		super.toJsonArray(service.listAll());
		return null;
	}

}
