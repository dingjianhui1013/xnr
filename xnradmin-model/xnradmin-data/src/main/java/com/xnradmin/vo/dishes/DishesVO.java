package com.xnradmin.vo.dishes;

import java.sql.Timestamp;

import com.cntinker.util.ReflectHelper;
import com.xnradmin.po.common.status.Status;
import com.xnradmin.po.dishes.Collocation;
import com.xnradmin.po.dishes.Dish;
import com.xnradmin.po.dishes.RawMaterial;

public class DishesVO {

	private String dishId;  		//菜品ID
    private String dishName;  		//菜品名
    private String introduction;  	//菜品介绍
    private String cookingMethod;  	//烹饪方法
    private String picUrl;  		//图片连接
    private String dishTypeId;  	//菜品类型
    private String dishTypeName;    //菜品名称
	private String createTime;  	//建立时间
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
	private String property;		//商品属性
	private String unitPrice;  		//商品单价
	private String dishCount;  		//商品数量
	private String totalPrice;  	//商品总价
	private	String payStatusId;		//支付状态
	private String dispatchStatusId;//派单状态
	private String orderStatusId;	//订单状态
	private String customer;		//收货人
	private String mobile;			//手机号
	private String address;			//地址
	private String code;			//邮编

	
	private Status status;
	private RawMaterial rawMaterial;
	private Collocation collocation;
	private Dish dish;
	
	public String toString() {
		String res = "";
		try {
			res = ReflectHelper.makeToString(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
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
	public String getCollocationId() {
		return collocationId;
	}
	public void setCollocationId(String collocationId) {
		this.collocationId = collocationId;
	}
	public String getDishId() {
		return dishId;
	}
	public void setDishId(String dishId) {
		this.dishId = dishId;
	}
	public String getRawMaterialId() {
		return rawMaterialId;
	}
	public void setRawMaterialId(String rawMaterialId) {
		this.rawMaterialId = rawMaterialId;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getCreateStaffName() {
		return createStaffName;
	}
	public void setCreateStaffName(String createStaffName) {
		this.createStaffName = createStaffName;
	}
	public String getModifyStaffName() {
		return modifyStaffName;
	}
	public void setModifyStaffName(String modifyStaffName) {
		this.modifyStaffName = modifyStaffName;
	}
	public String getWeightName() {
		return weightName;
	}
	public void setWeightName(String weightName) {
		this.weightName = weightName;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public String getDishTypeName() {
		return dishTypeName;
	}
	public void setDishTypeName(String dishTypeName) {
		this.dishTypeName = dishTypeName;
	}
	public RawMaterial getRawMaterial() {
		return rawMaterial;
	}
	public void setRawMaterial(RawMaterial rawMaterial) {
		this.rawMaterial = rawMaterial;
	}
	public Collocation getCollocation() {
		return collocation;
	}
	public void setCollocation(Collocation collocation) {
		this.collocation = collocation;
	}
	public Dish getDish() {
		return dish;
	}
	public void setDish(Dish dish) {
		this.dish = dish;
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
	public String getDishCount() {
		return dishCount;
	}
	public void setDishCount(String dishCount) {
		this.dishCount = dishCount;
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
	public String getUploadOrderId() {
		return uploadOrderId;
	}
	public void setUploadOrderId(String uploadOrderId) {
		this.uploadOrderId = uploadOrderId;
	}
	
}
