package com.xnradmin.po;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * CommonStaff entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "common_staff")
public class CommonStaff implements java.io.Serializable {

    // Fields

    private Integer id;

    private String loginId;

    private String staffName;

    private String staffNo;

    private String positionName;

    private String password;

    private Timestamp createTime;

    private String mobile;

    private String email;

    private Integer organizationId;

    private Integer statusId;

    private Timestamp lastactive;

    private String idcard;

    private Integer workinglife;

    private Timestamp workingtime;

    private Boolean manager;

    private String edu;

    private Integer gender;

    private Timestamp birthday;

    private Integer leadStaffId;

    private String leadStaffName;

    private Integer leadStaffOrgId;

    private String leadStaffOrgName;

    private String bankInformation;
    /**
     * 部门总监：0=否,1=是
     */
    private Integer director;
    
    @Column(name = "descript", length = 4096)
    private String descript;

    private String theEarliestTime;
    
    private String theLatestTime;
    
    private Integer billTime;
    
    private String billTimeName;
    
    //折扣
    private Float discount;
    // Constructors
    
    private Float viewDiscount;
    
    private String ip;
    
    private String userDesc;

    /** default constructor */
    public CommonStaff(){
    }

    /** minimal constructor */
    public CommonStaff(Timestamp createTime,Integer organizationId,
            Integer statusId){
        this.createTime = createTime;
        this.organizationId = organizationId;
        this.statusId = statusId;
    }

    /** full constructor */
    public CommonStaff(String loginId,String staffName,String staffNo,
            String positionName,String password,Timestamp createTime,
            String mobile,String email,Integer organizationId,Integer statusId,
            Timestamp lastactive,String idcard,Integer workinglife,
            Timestamp workingtime,Boolean manager,String edu,String bankInformation){
        this.loginId = loginId;
        this.staffName = staffName;
        this.staffNo = staffNo;
        this.positionName = positionName;
        this.password = password;
        this.createTime = createTime;
        this.mobile = mobile;
        this.email = email;
        this.organizationId = organizationId;
        this.statusId = statusId;
        this.lastactive = lastactive;
        this.idcard = idcard;
        this.workinglife = workinglife;
        this.workingtime = workingtime;
        this.manager = manager;
        this.edu = edu;
        this.bankInformation = bankInformation;
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

    @Column(name = "LOGIN_ID", length = 32)
    public String getLoginId(){
        return this.loginId;
    }

    public void setLoginId(String loginId){
        this.loginId = loginId;
    }

    @Column(name = "STAFF_NAME", length = 32)
    public String getStaffName(){
        return this.staffName;
    }

    public void setStaffName(String staffName){
        this.staffName = staffName;
    }

    @Column(name = "STAFF_NO", length = 64)
    public String getStaffNo(){
        return this.staffNo;
    }

    public void setStaffNo(String staffNo){
        this.staffNo = staffNo;
    }

    @Column(name = "POSITION_NAME", length = 64)
    public String getPositionName(){
        return this.positionName;
    }

    public void setPositionName(String positionName){
        this.positionName = positionName;
    }

    @Column(name = "PASSWORD", length = 64)
    public String getPassword(){
        return this.password;
    }

    public void setPassword(String password){
        this.password = password;
    }

    @Column(name = "CREATE_TIME", nullable = false, length = 19)
    public Timestamp getCreateTime(){
        return this.createTime;
    }

    public void setCreateTime(Timestamp createTime){
        this.createTime = createTime;
    }

    @Column(name = "MOBILE", length = 32)
    public String getMobile(){
        return this.mobile;
    }

    public void setMobile(String mobile){
        this.mobile = mobile;
    }

    @Column(name = "EMAIL", length = 128)
    public String getEmail(){
        return this.email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    @Column(name = "ORGANIZATION_ID", nullable = false)
    public Integer getOrganizationId(){
        return this.organizationId;
    }

    public void setOrganizationId(Integer organizationId){
        this.organizationId = organizationId;
    }

    @Column(name = "STATUS_ID", nullable = false)
    public Integer getStatusId(){
        return this.statusId;
    }

    public void setStatusId(Integer statusId){
        this.statusId = statusId;
    }

    @Column(name = "LASTACTIVE", length = 19)
    public Timestamp getLastactive(){
        return this.lastactive;
    }

    public void setLastactive(Timestamp lastactive){
        this.lastactive = lastactive;
    }

    @Column(name = "IDCARD")
    public String getIdcard(){
        return this.idcard;
    }

    public void setIdcard(String idcard){
        this.idcard = idcard;
    }

    @Column(name = "WORKINGLIFE")
    public Integer getWorkinglife(){
        return this.workinglife;
    }

    public void setWorkinglife(Integer workinglife){
        this.workinglife = workinglife;
    }

    @Column(name = "WORKINGTIME", length = 19)
    public Timestamp getWorkingtime(){
        return this.workingtime;
    }

    public void setWorkingtime(Timestamp workingtime){
        this.workingtime = workingtime;
    }

    @Column(name = "MANAGER")
    public Boolean getManager(){
        return this.manager;
    }

    public void setManager(Boolean manager){
        this.manager = manager;
    }

    @Column(name = "EDU", length = 64)
    public String getEdu(){
        return this.edu;
    }

    public void setEdu(String edu){
        this.edu = edu;
    }

    @Column(name = "GENDER", length = 4)
    public Integer getGender(){
        return gender;
    }

    public void setGender(Integer gender){
        this.gender = gender;
    }

    @Column(name = "BIRTHDAY", length = 19)
    public Timestamp getBirthday(){
        return birthday;
    }

    public void setBirthday(Timestamp birthday){
        this.birthday = birthday;
    }

    @Column(name = "BANK_INFORMATION", length = 400)
    public String getBankInformation() {
		return bankInformation;
	}

	public void setBankInformation(String bankInformation) {
		this.bankInformation = bankInformation;
	}

	@Column(name = "THE_EARLIEST_TIME", length = 50)
    public String getTheEarliestTime() {
		return theEarliestTime;
	}

	public void setTheEarliestTime(String theEarliestTime) {
		this.theEarliestTime = theEarliestTime;
	}
	
	@Column(name = "THE_LATEST_TIME", length = 50)
    public String getTheLatestTime() {
		return theLatestTime;
	}

	public void setTheLatestTime(String theLatestTime) {
		this.theLatestTime = theLatestTime;
	}
	
	public Integer getLeadStaffId(){
        return leadStaffId;
    }

    public void setLeadStaffId(Integer leadStaffId){
        this.leadStaffId = leadStaffId;
    }

    public String getLeadStaffName(){
        return leadStaffName;
    }

    public void setLeadStaffName(String leadStaffName){
        this.leadStaffName = leadStaffName;
    }

    public Integer getLeadStaffOrgId(){
        return leadStaffOrgId;
    }

    public void setLeadStaffOrgId(Integer leadStaffOrgId){
        this.leadStaffOrgId = leadStaffOrgId;
    }

    public String getLeadStaffOrgName(){
        return leadStaffOrgName;
    }

    public void setLeadStaffOrgName(String leadStaffOrgName){
        this.leadStaffOrgName = leadStaffOrgName;
    }

    public Integer getDirector(){
        return director;
    }

    public void setDirector(Integer director){
        this.director = director;
    }

	public String getDescript() {
		return descript;
	}

	public void setDescript(String descript) {
		this.descript = descript;
	}

	public Integer getBillTime() {
		return billTime;
	}

	public String getBillTimeName() {
		return billTimeName;
	}

	public void setBillTime(Integer billTime) {
		this.billTime = billTime;
	}

	public void setBillTimeName(String billTimeName) {
		this.billTimeName = billTimeName;
	}

	public Float getDiscount() {
		return discount;
	}

	public void setDiscount(Float discount) {
		this.discount = discount;
	}

	public Float getViewDiscount() {
		return viewDiscount;
	}

	public void setViewDiscount(Float viewDiscount) {
		this.viewDiscount = viewDiscount;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getUserDesc() {
		return userDesc;
	}

	public void setUserDesc(String userDesc) {
		this.userDesc = userDesc;
	}

}