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
@Table(name = "slide")
public class Slide implements java.io.Serializable{

    // Fields

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;

    private String sortId;  //排序Id

    private String title;  //标题

    private String picPath;  //本地图片位置

    private String picUrl;  //外链图片URL

    private Integer showStatus;  //是否显示
    
    private String showPosition;  //显示位置

    private String staffId;	//隶属用户Id
    
    private Integer primaryConfigurationId; //对应商城ID
    
    private Integer picType; //图片类型（LOGO，背景，幻灯片）
    
	private Timestamp createTime;  //建立时间
	
	private Integer createStaffId;  //建立人
	
	private Timestamp modifyTime;  //修改时间

	private Integer modifyStaffId;  //修改人
	
    /** default constructor */
    public Slide(){
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

	@Column(name = "TITLE",	length=100)
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "PIC_PATH",	length=100)
	public String getPicPath() {
		return picPath;
	}

	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}

	@Column(name = "PIC_URL",	length=400)
	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	@Column(name = "SHOW_STATUS")
	public Integer getShowStatus() {
		return showStatus;
	}

	public void setShowStatus(Integer showStatus) {
		this.showStatus = showStatus;
	}

	@Column(name = "SHOW_POSITION",	length=400)
	public String getShowPosition() {
		return showPosition;
	}

	public void setShowPosition(String showPosition) {
		this.showPosition = showPosition;
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

	@Column(name = "PIC_TYPE")
	public Integer getPicType() {
		return picType;
	}

	public void setPicType(Integer picType) {
		this.picType = picType;
	}


	
}