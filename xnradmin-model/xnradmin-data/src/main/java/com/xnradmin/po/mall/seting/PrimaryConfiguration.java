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
@Table(name = "primary_configuration")
public class PrimaryConfiguration implements java.io.Serializable{

    // Fields

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;

    private String mallDemoUrl;  //商城预览地址

    private String mallName;  //商城名称

    private String mallLogo;  //商城logo图

    private String mallBackground;  //商城背景

    private Integer mallBackgroundStatus;  //是否开启背景
    
    private String address;  //联系地址

    private String mobile;  //联系电话

    private String code;  //邮编

    private String email;  //邮箱地址
    
    private String mallIntroduction;  //商城介绍
    
    private Integer mallStatus; //商城状态
    
    private String staffId; //隶属用户ID
    
	private Timestamp createTime;  //建立时间
	
	private Integer createStaffId;  //建立人
	
	private Timestamp modifyTime;  //修改时间

	private Integer modifyStaffId;  //修改人
	
    /** default constructor */
    public PrimaryConfiguration(){
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

    @Column(name = "MALL_DEMO_URL", length = 400)
	public String getMallDemoUrl() {
		return mallDemoUrl;
	}

	public void setMallDemoUrl(String mallDemoUrl) {
		this.mallDemoUrl = mallDemoUrl;
	}
	
	@Column(name = "MALL_NAME", length = 50)
	public String getMallName() {
		return mallName;
	}

	public void setMallName(String mallName) {
		this.mallName = mallName;
	}

	@Column(name = "MALL_LOGO", length = 200)
	public String getMallLogo() {
		return mallLogo;
	}

	public void setMallLogo(String mallLogo) {
		this.mallLogo = mallLogo;
	}

	@Column(name = "MALL_BACKGROUND", length = 200)
	public String getMallBackground() {
		return mallBackground;
	}

	public void setMallBackground(String mallBackground) {
		this.mallBackground = mallBackground;
	}

	@Column(name = "MALL_BACKGROUND_STATUS")
	public Integer getMallBackgroundStatus() {
		return mallBackgroundStatus;
	}

	public void setMallBackgroundStatus(Integer mallBackgroundStatus) {
		this.mallBackgroundStatus = mallBackgroundStatus;
	}
	
	@Column(name = "ADDRESS", length = 400)
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "MOBILE", length = 50)
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Column(name = "CODE", length = 50)
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "EMAIL", length = 200)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "MALL_STATUS")
	public Integer getMallStatus() {
		return mallStatus;
	}

	public void setMallStatus(Integer mallStatus) {
		this.mallStatus = mallStatus;
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

	@Column(name = "MALL_INTRODUCTION", length = 400)
	public String getMallIntroduction() {
		return mallIntroduction;
	}

	public void setMallIntroduction(String mallIntroduction) {
		this.mallIntroduction = mallIntroduction;
	}

	
}