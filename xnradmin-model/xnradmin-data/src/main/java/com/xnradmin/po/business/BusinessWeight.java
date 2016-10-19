package com.xnradmin.po.business;


import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * select * from phone_local where position(concat(id) in (select fid from test))!=0
 */
@Entity
@Table(name = "business_weight")
public class BusinessWeight implements java.io.Serializable{

    // Fields

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;

    private String weightName;  //单位名称

    private Integer weightStatus;  //单位状态

    private Integer sortId; //单位排序
    
    private String weightDescription;	//单位描述
    
    private Integer primaryConfigurationId; //对应商城ID
    
	private Timestamp createTime;  //建立时间
	
	private Integer createStaffId;  //建立人
	
	private Timestamp modifyTime;  //修改时间

	private Integer modifyStaffId;  //修改人
	
    /** default constructor */
    public BusinessWeight(){
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

    @Column(name = "WEIGHT_NAME")
    public String getWeightName() {
		return weightName;
	}

	public void setWeightName(String weightName) {
		this.weightName = weightName;
	}

	@Column(name = "WEIGHT_STATUS")
	public Integer getWeightStatus() {
		return weightStatus;
	}

	public void setWeightStatus(Integer weightStatus) {
		this.weightStatus = weightStatus;
	}

	@Column(name = "WEIGHT_DESCRIPTION")
	public String getWeightDescription() {
		return weightDescription;
	}

	public void setWeightDescription(String weightDescription) {
		this.weightDescription = weightDescription;
	}

	@Column(name = "SORT_ID")
	public Integer getSortId() {
		return sortId;
	}

	public void setSortId(Integer sortId) {
		this.sortId = sortId;
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