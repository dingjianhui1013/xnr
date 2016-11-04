package com.xnradmin.po.client;

import static javax.persistence.GenerationType.IDENTITY;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Index;

/**
 * CommonMenu entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "client_user_info")
public class ClientUserInfo implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String nickName;
	private String email;
	private String mobile;
	private String loginPassWord;
	private String paymentPassword;
	private Integer status;
	private String statusName;
	private Integer type;
	private String typeName;
	private String uuid;
	private Timestamp createTime;
	private Timestamp lastLoginTime;
	private Timestamp modifyTime;
	private Integer sourceId; // 该订单用户推广来源ID（有可能是用户推广，代理商推广，线上推广）
	private String sourceName; // 该订单用户推广来源名称
	private Integer sourceType; // 该订单用户推广来源类型 （有可能是用户推广，代理商推广，线上推广）
	private String sourceTypeName; // 该订单用户推广来源类型名称
	private Float discount; // 当前用户充值后使用产品给合作方结算的折扣
	@Index(name = "idx_clientuserinfo_wxfromusername")
	private String wxfromusername;
	private String wxtousername;
	private Timestamp wxsubtime;
	private Timestamp wxunsubtime;
	private Timestamp wxlastActvieTime;
	private String wxmsgtype;
	private String wxevent;
	private String wxnetworktype;
	@Index(name = "idx_clientuserinfo_wxopenuid")
	private String wxopenuid;	
	private String wxnickname;
	private String wxsex;
	private String wxlanguage;
	private Integer wxstatusid;
	private String wxstatusName;
	private String wxcity;
	private String wxprovince;
	private String wxcountry;
	private String wxheadimgurl;
	@Index(name = "idx_clientuserinfo_wxunionid")
	private String wxunionid;
	private Integer staffId; //对应staff表的ID
	
	//判断用户是否符合以下条件用，1为true,0为false
	private Integer isappuser;
	private Integer iswxuser;
	private Integer ismobileuser;
	private Integer isemailuser;
	private Integer iswebsiteuser;
	// Constructors

	/** default constructor */
	public ClientUserInfo() {

	}

	/** minimal constructor */
	public ClientUserInfo(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public ClientUserInfo(Integer id, String nickName, String email,
			String mobile, String loginPassWord, String paymentPassword,
			Integer status, String statusName, Integer type, String typeName,
			String uuid) {
		this.id = id;
		this.nickName = nickName;
		this.email = email;
		this.mobile = mobile;
		this.loginPassWord = loginPassWord;
		this.paymentPassword = paymentPassword;
		this.status = status;
		this.statusName = statusName;
		this.type = type;
		this.typeName = typeName;
		this.uuid = uuid;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "NICK_NAME", length = 20)
	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	@Column(name = "EMAIL", length = 50)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "MOBILE", length = 20)
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Column(name = "LOGIN_PASSWORD", length = 50)
	public String getLoginPassWord() {
		return loginPassWord;
	}

	public void setLoginPassWord(String loginPassWord) {
		this.loginPassWord = loginPassWord;
	}

	@Column(name = "PAYMENT_PASSWORD", length = 50)
	public String getPaymentPassword() {
		return paymentPassword;
	}

	public void setPaymentPassword(String paymentPassword) {
		this.paymentPassword = paymentPassword;
	}

	@Column(name = "STATUS", length = 10)
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(name = "STATUS_NAME", length = 50)
	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	@Column(name = "TYPE", length = 10)
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	@Column(name = "TYPE_NAME", length = 50)
	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	@Column(name = "UUID", length = 50)
	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	@Column(name = "CREATE_TIME")
	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	@Column(name = "LAST_LOGIN_TIME")
	public Timestamp getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Timestamp lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	@Column(name = "MODIFY_TIME")
	public Timestamp getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Timestamp modifyTime) {
		this.modifyTime = modifyTime;
	}

	@Column(name = "SOURCE_ID", length = 11)
	public Integer getSourceId() {
		return sourceId;
	}

	public void setSourceId(Integer sourceId) {
		this.sourceId = sourceId;
	}

	@Column(name = "SOURCE_NAME", length = 50)
	public String getSourceName() {
		return sourceName;
	}

	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}

	@Column(name = "SOURCE_TYPE", length = 11)
	public Integer getSourceType() {
		return sourceType;
	}

	public void setSourceType(Integer sourceType) {
		this.sourceType = sourceType;
	}

	@Column(name = "SOURCE_TYPE_NAME", length = 50)
	public String getSourceTypeName() {
		return sourceTypeName;
	}

	public void setSourceTypeName(String sourceTypeName) {
		this.sourceTypeName = sourceTypeName;
	}

	@Column(name = "DISCOUNT")
	public Float getDiscount() {
		return discount;
	}

	public void setDiscount(Float discount) {
		this.discount = discount;
	}

	@Column(name = "WX_FROM_USER_NAME", length = 50)
	public String getWxfromusername() {
		return wxfromusername;
	}

	public void setWxfromusername(String wxfromusername) {
		this.wxfromusername = wxfromusername;
	}

	@Column(name = "WX_TO_USER_NAME", length = 50)
	public String getWxtousername() {
		return wxtousername;
	}

	public void setWxtousername(String wxtousername) {
		this.wxtousername = wxtousername;
	}

	@Column(name = "WX_SUB_TIME")
	public Timestamp getWxsubtime() {
		return wxsubtime;
	}

	public void setWxsubtime(Timestamp wxsubtime) {
		this.wxsubtime = wxsubtime;
	}

	@Column(name = "WX_UNSUB_TIME")
	public Timestamp getWxunsubtime() {
		return wxunsubtime;
	}

	public void setWxunsubtime(Timestamp wxunsubtime) {
		this.wxunsubtime = wxunsubtime;
	}

	@Column(name = "WX_LAST_ACTVIE_TIME")
	public Timestamp getWxlastActvieTime() {
		return wxlastActvieTime;
	}

	public void setWxlastActvieTime(Timestamp wxlastActvieTime) {
		this.wxlastActvieTime = wxlastActvieTime;
	}

	@Column(name = "WX_MSG_TYPE")
	public String getWxmsgtype() {
		return wxmsgtype;
	}

	public void setWxmsgtype(String wxmsgtype) {
		this.wxmsgtype = wxmsgtype;
	}

	@Column(name = "WX_EVENT")
	public String getWxevent() {
		return wxevent;
	}

	public void setWxevent(String wxevent) {
		this.wxevent = wxevent;
	}

	@Column(name = "WX_NET_WORK_TYPE")
	public String getWxnetworktype() {
		return wxnetworktype;
	}

	public void setWxnetworktype(String wxnetworktype) {
		this.wxnetworktype = wxnetworktype;
	}

	@Column(name = "WX_OPENU_ID")
	public String getWxopenuid() {
		return wxopenuid;
	}

	public void setWxopenuid(String wxopenuid) {
		this.wxopenuid = wxopenuid;
	}

	@Column(name = "WX_NICK_NAME")
	public String getWxnickname() {
		return wxnickname;
	}

	public void setWxnickname(String wxnickname) {
		this.wxnickname = wxnickname;
	}

	@Column(name = "WX_SEX")
	public String getWxsex() {
		return wxsex;
	}

	public void setWxsex(String wxsex) {
		this.wxsex = wxsex;
	}

	@Column(name = "WX_LANGUAGE")
	public String getWxlanguage() {
		return wxlanguage;
	}

	public void setWxlanguage(String wxlanguage) {
		this.wxlanguage = wxlanguage;
	}

	@Column(name = "WX_STATUS_ID")
	public Integer getWxstatusid() {
		return wxstatusid;
	}

	public void setWxstatusid(Integer wxstatusid) {
		this.wxstatusid = wxstatusid;
	}

	@Column(name = "WX_STATUS_NAME", length=50)
	public String getWxstatusName() {
		return wxstatusName;
	}

	public void setWxstatusName(String wxstatusName) {
		this.wxstatusName = wxstatusName;
	}

	@Column(name = "WX_CITY", length=50)
	public String getWxcity() {
		return wxcity;
	}

	public void setWxcity(String wxcity) {
		this.wxcity = wxcity;
	}

	@Column(name = "WX_PROVINCE", length=50)
	public String getWxprovince() {
		return wxprovince;
	}

	public void setWxprovince(String wxprovince) {
		this.wxprovince = wxprovince;
	}

	@Column(name = "WX_COUNTRY", length=50)
	public String getWxcountry() {
		return wxcountry;
	}

	public void setWxcountry(String wxcountry) {
		this.wxcountry = wxcountry;
	}

	@Column(name = "WX_HEADIMG_URL")
	public String getWxheadimgurl() {
		return wxheadimgurl;
	}

	public void setWxheadimgurl(String wxheadimgurl) {
		this.wxheadimgurl = wxheadimgurl;
	}

	@Column(name = "WX_UNION_ID")
	public String getWxunionid() {
		return wxunionid;
	}

	public void setWxunionid(String wxunionid) {
		this.wxunionid = wxunionid;
	}

	@Column(name = "ISAPPUSER")
	public Integer getIsappuser() {
		return isappuser;
	}

	public void setIsappuser(Integer isappuser) {
		this.isappuser = isappuser;
	}

	@Column(name = "ISWXUSER")
	public Integer getIswxuser() {
		return iswxuser;
	}

	public void setIswxuser(Integer iswxuser) {
		this.iswxuser = iswxuser;
	}

	@Column(name = "ISMOBILEUSER")
	public Integer getIsmobileuser() {
		return ismobileuser;
	}

	public void setIsmobileuser(Integer ismobileuser) {
		this.ismobileuser = ismobileuser;
	}

	@Column(name = "ISEMAILUSER")
	public Integer getIsemailuser() {
		return isemailuser;
	}

	public void setIsemailuser(Integer isemailuser) {
		this.isemailuser = isemailuser;
	}

	@Column(name = "ISWEBSITEUSER")
	public Integer getIswebsiteuser() {
		return iswebsiteuser;
	}

	public void setIswebsiteuser(Integer iswebsiteuser) {
		this.iswebsiteuser = iswebsiteuser;
	}

	@Column(name = "STAFF_ID")
	public Integer getStaffId() {
		return staffId;
	}

	public void setStaffId(Integer staffId) {
		this.staffId = staffId;
	}

	

}