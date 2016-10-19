package com.xnradmin.po.mall.seting;


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
@Table(name = "delivery_mode")
public class DeliveryMode implements java.io.Serializable{

    // Fields

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;

    private String sortId;  //排序Id

    private String deliveryModeName;  //配送方式名称

    private Integer logisticsCompanyId;  //默认物流公司Id

    private String firstWeight;	//首重单位
    
    private Float firstPrice;	//首重价格
    
    private String continuedHeavyWeight;  //续重单位
    
    private Float continuedHeavyPrice;	//续重价格
    
    private String staffId;	//隶属用户Id
    
    private Integer primaryConfigurationId; //对应商城ID
    
	private Timestamp createTime;  //建立时间
	
	private Integer createStaffId;  //建立人
	
	private Timestamp modifyTime;  //修改时间

	private Integer modifyStaffId;  //修改人
	
    /** default constructor */
    public DeliveryMode(){
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

    @Column(name = "SORT_ID")
	public String getSortId() {
		return sortId;
	}

	public void setSortId(String sortId) {
		this.sortId = sortId;
	}

	@Column(name = "DELIVERY_MODE_NAME")
	public String getDeliveryModeName() {
		return deliveryModeName;
	}

	public void setDeliveryModeName(String deliveryModeName) {
		this.deliveryModeName = deliveryModeName;
	}

	@Column(name = "LOGISTICS_COMPANY_ID")
	public Integer getLogisticsCompanyId() {
		return logisticsCompanyId;
	}

	public void setLogisticsCompanyId(Integer logisticsCompanyId) {
		this.logisticsCompanyId = logisticsCompanyId;
	}

	@Column(name = "FIRST_WEIGHT")
	public String getFirstWeight() {
		return firstWeight;
	}

	public void setFirstWeight(String firstWeight) {
		this.firstWeight = firstWeight;
	}

	@Column(name = "FIRST_PRICE")
	public Float getFirstPrice() {
		return firstPrice;
	}

	public void setFirstPrice(Float firstPrice) {
		this.firstPrice = firstPrice;
	}

	@Column(name = "CONTINUE_DHEAVY_WEIGHT")
	public String getContinuedHeavyWeight() {
		return continuedHeavyWeight;
	}

	public void setContinuedHeavyWeight(String continuedHeavyWeight) {
		this.continuedHeavyWeight = continuedHeavyWeight;
	}

	@Column(name = "CONTINUE_DHEAVY_PRICE")
	public Float getContinuedHeavyPrice() {
		return continuedHeavyPrice;
	}

	public void setContinuedHeavyPrice(Float continuedHeavyPrice) {
		this.continuedHeavyPrice = continuedHeavyPrice;
	}

	@Column(name = "STAFF_ID")
	public String getStaffId() {
		return staffId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

	@Column(name = "CREATE_TIME")
	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	@Column(name = "CREATE_STAFF_ID")
	public Integer getCreateStaffId() {
		return createStaffId;
	}

	public void setCreateStaffId(Integer createStaffId) {
		this.createStaffId = createStaffId;
	}

	@Column(name = "MODIFY_TIME")
	public Timestamp getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Timestamp modifyTime) {
		this.modifyTime = modifyTime;
	}

	@Column(name = "MODIFY_STAFF_ID")
	public Integer getModifyStaffId() {
		return modifyStaffId;
	}

	public void setModifyStaffId(Integer modifyStaffId) {
		this.modifyStaffId = modifyStaffId;
	}

	@Column(name = "PRIMARY_CONFIGURATION_ID")
	public Integer getPrimaryConfigurationId() {
		return primaryConfigurationId;
	}

	public void setPrimaryConfigurationId(Integer primaryConfigurationId) {
		this.primaryConfigurationId = primaryConfigurationId;
	}
	
}