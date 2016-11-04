package com.xnradmin.po.dishes;


import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * PhoneLocal entity.
 */
@Entity
@Table(name = "upload_order")
public class UploadOrder implements java.io.Serializable{

    // Fields

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	
	private String orderNumber;			//订单编号

    private Timestamp orderTime;		//下单时间

    private String dishName;			//菜品名称
    
	private String property;			//商品属性
	
	private Float unitPrice;  			//商品单价
	
	private Integer dishCount;  		//商品数量

	private Float totalPrice;  			//商品总价
	
	private	Integer payStatusId;		//支付状态
	
	private Integer dispatchStatusId;	//派单状态
	
	private Integer orderStatusId;		//订单状态
	
	private String customer;			//收货人
	
	private String mobile;				//手机号
	
	private String address;				//地址
	
	private String code;				//邮编
	
	private Integer createStaffId;		//上传人
	
	private Timestamp createTime;		//上传时间
	
    /** default constructor */
    public UploadOrder(){
    }

    // Property accessors
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    public Integer getId(){
        return this.id;
    }

    public void setId(Integer id){
        this.id = id;
    }

    @Column(name = "ORDER_NUMBER")
	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	@Column(name = "ORDER_TIME")
	public Timestamp getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Timestamp orderTime) {
		this.orderTime = orderTime;
	}

	@Column(name = "DISH_NAME")
	public String getDishName() {
		return dishName;
	}

	public void setDishName(String dishName) {
		this.dishName = dishName;
	}

	@Column(name = "PROPERTY")
	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	@Column(name = "UNIT_PRICE")
	public Float getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Float unitPrice) {
		this.unitPrice = unitPrice;
	}

	@Column(name = "DISH_COUNT")
	public Integer getDishCount() {
		return dishCount;
	}

	public void setDishCount(Integer dishCount) {
		this.dishCount = dishCount;
	}

	@Column(name = "TOTAL_PRICE")
	public Float getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Float totalPrice) {
		this.totalPrice = totalPrice;
	}

	@Column(name = "PAY_STATUS")
	public Integer getPayStatusId() {
		return payStatusId;
	}

	public void setPayStatusId(Integer payStatusId) {
		this.payStatusId = payStatusId;
	}
	
	@Column(name = "DISPATCH_STATUS")
	public Integer getDispatchStatusId() {
		return dispatchStatusId;
	}

	public void setDispatchStatusId(Integer dispatchStatusId) {
		this.dispatchStatusId = dispatchStatusId;
	}

	@Column(name = "ORDER_STATUS")
	public Integer getOrderStatusId() {
		return orderStatusId;
	}

	public void setOrderStatusId(Integer orderStatusId) {
		this.orderStatusId = orderStatusId;
	}

	@Column(name = "CUSTOMER")
	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	@Column(name = "MOBILE")
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Column(name = "ADDRESS")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "CODE")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "CREATE_STAFF_ID")
	public Integer getCreateStaffId() {
		return createStaffId;
	}

	public void setCreateStaffId(Integer createStaffId) {
		this.createStaffId = createStaffId;
	}

	@Column(name = "CREATE_TIME")
	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	
}