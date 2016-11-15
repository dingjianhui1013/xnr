/**
 *2012-5-10 下午3:52:29
 */
package com.xnradmin.core.action.mall.clientUser;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

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
import com.xnradmin.core.service.mall.clientUser.ClientUserInfoService;
import com.xnradmin.core.service.mall.clientUser.ClientUserRegionInfoService;
import com.xnradmin.constant.AjaxResult;
import com.xnradmin.constant.StrutsResMSG;
import com.xnradmin.core.action.ParentAction;
import com.xnradmin.core.service.common.status.StatusService;
import com.xnradmin.core.util.SecureUtil;
import com.xnradmin.po.client.ClientUserInfo;
import com.xnradmin.po.common.status.Status;
import com.xnradmin.vo.client.ClientUserVO;

/**
 * @autohr: xiang_liu
 * 
 */
@Controller
@Scope("prototype")
@Namespace("/page/client/clientuserinfo")
@ParentPackage("json-default")
public class ClientUserInfoAction extends ParentAction {

	@Autowired
	private ClientUserInfoService service;
	
	@Autowired
	private ClientUserRegionInfoService clientUserRegionInfoService;
	
	@Autowired
    private StatusService statusService;

	
	private String clientuserinfoId;
	private String nickName;
	private String email;
	private String mobile;
	private String loginPassWord;
	private String paymentPassword;
	private String status;
	private String statusName;
	private String type;
	private String typeName;
	private String uuid;
	private String createTime;
	private String createStartTime;
	private String createEndTime;
	private String lastLoginTime;
	private String modifyTime;
	private String sourceId;
	private String sourceName;
	private String sourceType;
	private String sourceTypeName;
	private String discount;
	private String wxfromusername;
	private String wxtousername;
	private String wxsubtime;
	private String wxunsubtime;
	private String wxlastActvieTime;
	private String wxmsgtype;
	private String wxevent;
	private String wxnetworktype;
	private String wxopenuid;	
	private String wxnickname;
	private String wxsex;
	private String wxlanguage;
	private String wxstatusid;
	private String wxstatusName;
	private String wxcity;
	private String wxprovince;
	private String wxcountry;
	private String wxheadimgurl;
	private String wxunionid;
	//判断用户是否符合以下条件用，1为true,0为false
	private String isappuser;
	private String iswxuser;
	private String ismobileuser;
	private String isemailuser;
	private String iswebsiteuser;
	private List<ClientUserVO> voList;
	private List<ClientUserInfo> poList;
	private ClientUserInfo cui;
	private List<Status> statusList;
    private List<Status> typeList;
    private List<Status> sourceTypeList;
    private Status statustype;
    private Status typetype;
	

	public String getClientuserinfoId() {
		return clientuserinfoId;
	}

	public void setClientuserinfoId(String clientuserinfoId) {
		this.clientuserinfoId = clientuserinfoId;
	}

	public ClientUserInfoService getService() {
		return service;
	}

	public void setService(ClientUserInfoService service) {
		this.service = service;
	}

	public StatusService getStatusService() {
		return statusService;
	}

	public void setStatusService(StatusService statusService) {
		this.statusService = statusService;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getLoginPassWord() {
		return loginPassWord;
	}

	public void setLoginPassWord(String loginPassWord) {
		this.loginPassWord = loginPassWord;
	}

	public String getPaymentPassword() {
		return paymentPassword;
	}

	public void setPaymentPassword(String paymentPassword) {
		this.paymentPassword = paymentPassword;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
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

	public String getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(String lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public String getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
	}

	public String getSourceId() {
		return sourceId;
	}

	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}

	public String getSourceName() {
		return sourceName;
	}

	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}

	public String getSourceType() {
		return sourceType;
	}

	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}

	public String getSourceTypeName() {
		return sourceTypeName;
	}

	public void setSourceTypeName(String sourceTypeName) {
		this.sourceTypeName = sourceTypeName;
	}

	public String getDiscount() {
		return discount;
	}

	public void setDiscount(String discount) {
		this.discount = discount;
	}

	public String getWxfromusername() {
		return wxfromusername;
	}

	public void setWxfromusername(String wxfromusername) {
		this.wxfromusername = wxfromusername;
	}

	public String getWxtousername() {
		return wxtousername;
	}

	public void setWxtousername(String wxtousername) {
		this.wxtousername = wxtousername;
	}

	public String getWxsubtime() {
		return wxsubtime;
	}

	public void setWxsubtime(String wxsubtime) {
		this.wxsubtime = wxsubtime;
	}

	public String getWxunsubtime() {
		return wxunsubtime;
	}

	public void setWxunsubtime(String wxunsubtime) {
		this.wxunsubtime = wxunsubtime;
	}

	public String getWxlastActvieTime() {
		return wxlastActvieTime;
	}

	public void setWxlastActvieTime(String wxlastActvieTime) {
		this.wxlastActvieTime = wxlastActvieTime;
	}

	public String getWxmsgtype() {
		return wxmsgtype;
	}

	public void setWxmsgtype(String wxmsgtype) {
		this.wxmsgtype = wxmsgtype;
	}

	public String getWxevent() {
		return wxevent;
	}

	public void setWxevent(String wxevent) {
		this.wxevent = wxevent;
	}

	public String getWxnetworktype() {
		return wxnetworktype;
	}

	public void setWxnetworktype(String wxnetworktype) {
		this.wxnetworktype = wxnetworktype;
	}

	public String getWxopenuid() {
		return wxopenuid;
	}

	public void setWxopenuid(String wxopenuid) {
		this.wxopenuid = wxopenuid;
	}

	public String getWxnickname() {
		return wxnickname;
	}

	public void setWxnickname(String wxnickname) {
		this.wxnickname = wxnickname;
	}

	public String getWxsex() {
		return wxsex;
	}

	public void setWxsex(String wxsex) {
		this.wxsex = wxsex;
	}

	public String getWxlanguage() {
		return wxlanguage;
	}

	public void setWxlanguage(String wxlanguage) {
		this.wxlanguage = wxlanguage;
	}

	public String getWxstatusid() {
		return wxstatusid;
	}

	public void setWxstatusid(String wxstatusid) {
		this.wxstatusid = wxstatusid;
	}

	public String getWxstatusName() {
		return wxstatusName;
	}

	public void setWxstatusName(String wxstatusName) {
		this.wxstatusName = wxstatusName;
	}

	public String getWxcity() {
		return wxcity;
	}

	public void setWxcity(String wxcity) {
		this.wxcity = wxcity;
	}

	public String getWxprovince() {
		return wxprovince;
	}

	public void setWxprovince(String wxprovince) {
		this.wxprovince = wxprovince;
	}

	public String getWxcountry() {
		return wxcountry;
	}

	public void setWxcountry(String wxcountry) {
		this.wxcountry = wxcountry;
	}

	public String getWxheadimgurl() {
		return wxheadimgurl;
	}

	public void setWxheadimgurl(String wxheadimgurl) {
		this.wxheadimgurl = wxheadimgurl;
	}

	public String getWxunionid() {
		return wxunionid;
	}

	public void setWxunionid(String wxunionid) {
		this.wxunionid = wxunionid;
	}

	public String getIsappuser() {
		return isappuser;
	}

	public void setIsappuser(String isappuser) {
		this.isappuser = isappuser;
	}

	public String getIswxuser() {
		return iswxuser;
	}

	public void setIswxuser(String iswxuser) {
		this.iswxuser = iswxuser;
	}

	public String getIsmobileuser() {
		return ismobileuser;
	}

	public void setIsmobileuser(String ismobileuser) {
		this.ismobileuser = ismobileuser;
	}

	public String getIsemailuser() {
		return isemailuser;
	}

	public void setIsemailuser(String isemailuser) {
		this.isemailuser = isemailuser;
	}

	public String getIswebsiteuser() {
		return iswebsiteuser;
	}

	public void setIswebsiteuser(String iswebsiteuser) {
		this.iswebsiteuser = iswebsiteuser;
	}

	public List<ClientUserVO> getVoList() {
		return voList;
	}

	public void setVoList(List<ClientUserVO> voList) {
		this.voList = voList;
	}

	public List<ClientUserInfo> getPoList() {
		return poList;
	}

	public void setPoList(List<ClientUserInfo> poList) {
		this.poList = poList;
	}

	public ClientUserInfo getCui() {
		return cui;
	}

	public void setCui(ClientUserInfo cui) {
		this.cui = cui;
	}

	public List<Status> getStatusList() {
		return statusList;
	}

	public void setStatusList(List<Status> statusList) {
		this.statusList = statusList;
	}

	public List<Status> getTypeList() {
		return typeList;
	}

	public void setTypeList(List<Status> typeList) {
		this.typeList = typeList;
	}

	public Status getStatustype() {
		return statustype;
	}

	public void setStatustype(Status statustype) {
		this.statustype = statustype;
	}

	public Status getTypetype() {
		return typetype;
	}

	public void setTypetype(Status typetype) {
		this.typetype = typetype;
	}

	public List<Status> getSourceTypeList() {
		return sourceTypeList;
	}

	public void setSourceTypeList(List<Status> sourceTypeList) {
		this.sourceTypeList = sourceTypeList;
	}

	@Override
	public boolean isPublic() {
		return false;
	};

	static Log log = LogFactory.getLog(ClientUserInfoAction.class);

	/**
	 * 跳转到信息页。。
	 * 
	 * @return
	 */
	@Action(value = "info", results = {
			@Result(name = StrutsResMSG.SUCCESS, location = "/client/clientuserinfo/info.jsp"),
			@Result(name = StrutsResMSG.FAILED, location = "/client/clientuserinfo/info.jsp") })
	public String info() {
		setPageInfo();
		return StrutsResMSG.SUCCESS;
	}

	private void setPageInfo() {
		//加载状态列表
		setStatusList();
		this.voList = service.listVO(clientuserinfoId, nickName, email, mobile, status, type, uuid, 
				createStartTime, createEndTime, sourceId, sourceType, wxnickname, wxsex, wxcity, 
				wxprovince, wxcountry, isappuser, iswxuser, ismobileuser, isemailuser, iswebsiteuser, 
				getPageNum(), getNumPerPage(), orderField, orderDirection);
		this.totalCount = service.getCount(clientuserinfoId, nickName, email, mobile, status, type, uuid, 
				createStartTime, createEndTime, sourceId, sourceType, wxnickname, wxsex, wxcity, 
				wxprovince, wxcountry, isappuser, iswxuser, ismobileuser, isemailuser, iswebsiteuser);
	}


	private void setStatusList(){
        this.statusList = statusService.find(ClientUserInfo.class,"clientUserInfoStatus");
        this.typeList = statusService.find(ClientUserInfo.class,"clientUserInfoType");
        this.sourceTypeList = statusService.find(ClientUserInfo.class,"clientUserInfoSourceType");
    }

	/**
	 * 带信息到修改页
	 * 
	 * @return String
	 */
	@Action(value = "modifyinfo", results = {
			@Result(name = StrutsResMSG.SUCCESS, location = "/client/clientuserinfo/modify.jsp"),
			@Result(name = StrutsResMSG.FAILED, location = "/client/clientuserinfo/modify.jsp") })
	public String modifyinfo() {
		//加载状态列表
		setStatusList();
		ClientUserInfo po = service.findByid(clientuserinfoId);
		if(po.getId()!=null){
			this.clientuserinfoId = po.getId().toString();
		}
		this.email = po.getEmail();
		this.loginPassWord = po.getLoginPassWord();
		this.mobile = po.getMobile();
		this.nickName = po.getNickName();
		this.paymentPassword = po.getPaymentPassword();
		if(po.getStatus()!=null){
			this.status = po.getStatus().toString();
		}
		if(po.getType()!=null){
			this.type = po.getType().toString();
		}
		this.uuid = po.getUuid();
		if(po.getSourceType()!=null){
			this.sourceType = po.getSourceType().toString();
		}
		return StrutsResMSG.SUCCESS;
	}

	@Action(value = "addinfo", results = {
			@Result(name = StrutsResMSG.SUCCESS, location = "/client/clientuserinfo/add.jsp"),
			@Result(name = StrutsResMSG.FAILED, location = "/client/clientuserinfo/add.jsp") })
	public String addInfo() {
		//加载状态列表
		setStatusList();
		return StrutsResMSG.SUCCESS;
	}

	/**
	 * 更新对象接口
	 * 
	 * @return String
	 * @throws JSONException
	 * @throws IOException
	 */
	@Action(value = "modify", results = { @Result(name = StrutsResMSG.SUCCESS, type = "plainText") })
	public String modify() throws JSONException, IOException {
		log.debug("modif action!");
        log.debug("modify clientuserinfo: " + clientuserinfoId);
		ClientUserInfo oldpo = service.findByid(clientuserinfoId);
		if(!StringHelper.isNull(mobile)){
			oldpo.setMobile(mobile);
		}
//		//加密登录密码
//		if(!StringHelper.isNull(loginPassWord)){
//			po.setLoginPassWord(loginPassWord);
//			po.setLoginPassWord(SecureUtil.getClientUserEncodePswd(po,"1"));
//			
//		}else{
//			po.setLoginPassWord(oldpo.getLoginPassWord());
//		}
//		//加密支付密码
//		if(!StringHelper.isNull(paymentPassword)){
//			po.setPaymentPassword(paymentPassword);
//			po.setPaymentPassword(SecureUtil.getClientUserEncodePswd(po,"2"));
//			
//		}else{
//			po.setPaymentPassword(oldpo.getLoginPassWord());
//		}
		
		if(!StringHelper.isNull(email)){
			oldpo.setEmail(email);
		}
		
		if(!StringHelper.isNull(nickName)){
			oldpo.setNickName(nickName);
		}
		
		if(!StringHelper.isNull(uuid)){
			oldpo.setUuid(uuid);
		}
		if(!StringHelper.isNull(status)){
			oldpo.setStatus(Integer.parseInt(status));
			Status statusCode = statusService.findByid(status);
			oldpo.setStatusName(statusCode.getStatusName());
		}
		if(!StringHelper.isNull(type)){
			oldpo.setType(Integer.parseInt(type));
			Status typeCode = statusService.findByid(type);
			oldpo.setTypeName(typeCode.getStatusName());
		}
		oldpo.setModifyTime(new Timestamp(System.currentTimeMillis()));
		if(!StringHelper.isNull(sourceType)){
			oldpo.setSourceType(Integer.parseInt(sourceType));
			Status sourceTypeCode = statusService.findByid(sourceType);
			oldpo.setSourceTypeName(sourceTypeCode.getStatusName());
		}
		if(!StringHelper.isNull(isappuser)){
			oldpo.setIsappuser(Integer.parseInt(isappuser));
		}
		if(!StringHelper.isNull(isemailuser)){
			oldpo.setIsemailuser(Integer.parseInt(isemailuser));
		}
		if(!StringHelper.isNull(ismobileuser)){
			oldpo.setIsmobileuser(Integer.parseInt(ismobileuser));
		}
		if(!StringHelper.isNull(iswebsiteuser)){
			oldpo.setIswebsiteuser(Integer.parseInt(iswebsiteuser));
		}
		if(!StringHelper.isNull(iswxuser)){
			oldpo.setIswxuser(Integer.parseInt(iswxuser));
		}
		int res = this.service.modify(oldpo);
		if (res == 1) {
			super.error("已存在相同的用户");
		} else {
			super.success(null, AjaxResult.CALL_BACK_TYPE_CLOSECURRENT, "clientUserInfo",
					null);
		}
		return null;
	}

	/**
	 * 保存对象接口
	 * 
	 * @return String
	 * @throws IOException
	 * @throws JSONException
	 */
	@Action(value = "add", results = { @Result(name = StrutsResMSG.SUCCESS, type = "plainText") })
	public String save() throws IOException, JSONException {
		ClientUserInfo po = new ClientUserInfo();
		po.setMobile(mobile);
		po.setLoginPassWord(loginPassWord);
		po.setPaymentPassword(paymentPassword);
		//加密登录密码
		if(!StringHelper.isNull(loginPassWord)){
			po.setLoginPassWord(loginPassWord);
			po.setLoginPassWord(SecureUtil.getClientUserEncodePswd(po,"1"));
		}
		//加密支付密码
		if(!StringHelper.isNull(paymentPassword)){
			po.setPaymentPassword(paymentPassword);
			po.setPaymentPassword(SecureUtil.getClientUserEncodePswd(po,"2"));
		}

		if(!StringHelper.isNull(nickName)){
			po.setNickName(nickName);
		}
		po.setEmail(email);
		if(!StringHelper.isNull(status)){
			po.setStatus(Integer.parseInt(status));
			Status statusCode = statusService.findByid(status);
			po.setStatusName(statusCode.getStatusName());
		}
		if(!StringHelper.isNull(type)){
			po.setType(Integer.parseInt(type));
			Status typeCode = statusService.findByid(type);
			po.setTypeName(typeCode.getStatusName());
		}
		po.setUuid(uuid);
		po.setCreateTime(new Timestamp(System.currentTimeMillis()));
		po.setLastLoginTime(new Timestamp(System.currentTimeMillis()));
		po.setModifyTime(new Timestamp(System.currentTimeMillis()));
		if(!StringHelper.isNull(sourceType)){
			po.setSourceType(Integer.parseInt(sourceType));
			Status sourceTypeCode = statusService.findByid(sourceType);
			po.setSourceTypeName(sourceTypeCode.getStatusName());
		}
		if(!StringHelper.isNull(isappuser)){
			po.setIsappuser(Integer.parseInt(isappuser));
		}
		if(!StringHelper.isNull(isemailuser)){
			po.setIsemailuser(Integer.parseInt(isemailuser));
		}
		if(!StringHelper.isNull(ismobileuser)){
			po.setIsmobileuser(Integer.parseInt(ismobileuser));
		}
		if(!StringHelper.isNull(iswebsiteuser)){
			po.setIswebsiteuser(Integer.parseInt(iswebsiteuser));
		}
		if(!StringHelper.isNull(iswxuser)){
			po.setIswxuser(Integer.parseInt(iswxuser));
		}
		int res = service.save(po);
		if (res == 1)
			super.error("用户已存在");
		else
			super.success(null, AjaxResult.CALL_BACK_TYPE_CLOSECURRENT, "clientUserInfo",
					null);

		return null;
	}

	@Action(value = "del", results = {@Result(name = StrutsResMSG.SUCCESS, type = "plainText")})
    public String del() throws IOException,JSONException{
        service.removeClientUserInfoId(Integer.parseInt(clientuserinfoId));
        clientUserRegionInfoService.removeClientUserInfoId(Integer.parseInt(clientuserinfoId));
        super.success(null,null,"clientUserInfo",null);
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
		super.toJsonArray(service.listAll());
		return null;
	}

}
