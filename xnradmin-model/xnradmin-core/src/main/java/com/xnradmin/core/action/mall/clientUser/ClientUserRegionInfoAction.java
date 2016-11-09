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
import com.xnradmin.core.service.mall.clientUser.ClientUserRegionInfoService;
import com.xnradmin.constant.AjaxResult;
import com.xnradmin.constant.StrutsResMSG;
import com.xnradmin.core.action.ParentAction;
import com.xnradmin.po.client.ClientUserRegionInfo;
import com.xnradmin.vo.client.ClientUserVO;

/**
 * @autohr: xiang_liu
 * 
 */
@Controller
@Scope("prototype")
@Namespace("/page/client/clientuserregioninfo")
@ParentPackage("json-default")
public class ClientUserRegionInfoAction extends ParentAction {

	@Autowired
	private ClientUserRegionInfoService service;
	
	private String clientUserRegionInfoId;
	private String countryId;
	private String countryName;
	private String provinceId;
	private String provinceName;
	private String cityId;
	private String cityName;
	private String areaId;
	private String areaName;
	private String userRealAddress;
	private String clientUserInfoId;
	private String userRealMobile; //收货人用户手机号
	private String userRealPlane; //收货人用户座机
	private String userRealName; //收货人名称
	private String userRealPostcode; //邮政编码
	private String createTime;
	private String modifyTime;
	private String userMobile;
	private String wxnickname;
	private String wxopenuid;
	private String email;
	private String sourceType;
	private String createStartTime;
	private String createEndTime;
	private String staffId;
	private List<ClientUserVO> voList;
	private List<ClientUserRegionInfo> poList;
	private ClientUserRegionInfo cuci;
	
	public String getClientUserRegionInfoId() {
		return clientUserRegionInfoId;
	}

	public void setClientUserRegionInfoId(String clientUserRegionInfoId) {
		this.clientUserRegionInfoId = clientUserRegionInfoId;
	}

	public String getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}

	public String getCityId() {
		return cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	public ClientUserRegionInfoService getService() {
		return service;
	}

	public void setService(ClientUserRegionInfoService service) {
		this.service = service;
	}

	public String getCountryId() {
		return countryId;
	}

	public void setCountryId(String countryId) {
		this.countryId = countryId;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getUserRealAddress() {
		return userRealAddress;
	}

	public void setUserRealAddress(String userRealAddress) {
		this.userRealAddress = userRealAddress;
	}

	public String getUserMobile() {
		return userMobile;
	}

	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}

	public String getWxnickname() {
		return wxnickname;
	}

	public void setWxnickname(String wxnickname) {
		this.wxnickname = wxnickname;
	}

	public String getWxopenuid() {
		return wxopenuid;
	}

	public void setWxopenuid(String wxopenuid) {
		this.wxopenuid = wxopenuid;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSourceType() {
		return sourceType;
	}

	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
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

	public String getClientUserInfoId() {
		return clientUserInfoId;
	}

	public void setClientUserInfoId(String clientUserInfoId) {
		this.clientUserInfoId = clientUserInfoId;
	}

	public String getUserRealMobile() {
		return userRealMobile;
	}

	public void setUserRealMobile(String userRealMobile) {
		this.userRealMobile = userRealMobile;
	}

	public String getUserRealPlane() {
		return userRealPlane;
	}

	public void setUserRealPlane(String userRealPlane) {
		this.userRealPlane = userRealPlane;
	}

	public String getUserRealName() {
		return userRealName;
	}

	public void setUserRealName(String userRealName) {
		this.userRealName = userRealName;
	}

	public String getUserRealPostcode() {
		return userRealPostcode;
	}

	public void setUserRealPostcode(String userRealPostcode) {
		this.userRealPostcode = userRealPostcode;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
	}

	public String getStaffId() {
		return staffId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

	public List<ClientUserVO> getVoList() {
		return voList;
	}

	public void setVoList(List<ClientUserVO> voList) {
		this.voList = voList;
	}

	public List<ClientUserRegionInfo> getPoList() {
		return poList;
	}

	public void setPoList(List<ClientUserRegionInfo> poList) {
		this.poList = poList;
	}

	public ClientUserRegionInfo getCuci() {
		return cuci;
	}

	public void setCuci(ClientUserRegionInfo cuci) {
		this.cuci = cuci;
	}

	@Override
	public boolean isPublic() {
		return false;
	};

	static Log log = LogFactory.getLog(ClientUserRegionInfoAction.class);

	/**
	 * 跳转到信息页。。
	 * 
	 * @return
	 */
	@Action(value = "info", results = {
			@Result(name = StrutsResMSG.SUCCESS, location = "/client/clientuserregioninfo/info.jsp"),
			@Result(name = StrutsResMSG.FAILED, location = "/client/clientuserregioninfo/info.jsp") })
	public String info() {
		setPageInfo();
		return StrutsResMSG.SUCCESS;
	}

	private void setPageInfo() {
		// 设置排序
		this.voList = service.listVO(clientUserInfoId, userMobile, wxnickname, wxopenuid, email, 
				countryName, provinceName, cityName, areaName, userRealMobile, userRealPlane, 
				userRealName, sourceType, createStartTime, createEndTime, getPageNum(),
				getNumPerPage(), orderField, orderDirection);
		
		this.totalCount = service.getCount(clientUserInfoId, userMobile, wxnickname, wxopenuid, 
				email, countryName, provinceName, cityName, areaName, userRealMobile, userRealPlane, 
				userRealName, sourceType, createStartTime, createEndTime);
	}


	/**
	 * 带信息到修改页
	 * 
	 * @return String
	 */
	@Action(value = "modifyinfo", results = {
			@Result(name = StrutsResMSG.SUCCESS, location = "/client/clientuserregioninfo/modify.jsp"),
			@Result(name = StrutsResMSG.FAILED, location = "/client/clientuserregioninfo/modify.jsp") })
	public String modifyinfo() {
		ClientUserRegionInfo po = service.findByid(clientUserRegionInfoId);
		if(po.getId()!=null){
			this.clientUserRegionInfoId = po.getId().toString();
		}
		if(po.getProvinceId()!=null){
			this.countryId = po.getCountryId().toString();
			this.countryName = po.getCountryName();
		}
		if(po.getProvinceId()!=null){
			this.provinceId = po.getProvinceId().toString();
			this.provinceName = po.getProvinceName();
		}
		if(po.getCityId()!=null){
			this.cityId = po.getCityId().toString();
			this.cityName = po.getCityName();
		}
		if(po.getAreaId()!=null){
			this.areaId = po.getAreaId().toString();
			this.areaName = po.getAreaName();
		}
		this.userRealAddress = po.getUserRealAddress();
		this.userRealMobile = po.getUserRealMobile();
		this.userRealName = po.getUserRealName();
		this.userRealPlane = po.getUserRealPlane();
		this.userRealPostcode = po.getUserRealPostcode();
		if(po.getCreateTime()!=null){
			this.createTime = po.getCreateTime().toString();
		}
		if(po.getModifyTime()!=null){
			this.modifyTime = po.getModifyTime().toString();
		}
		if(po.getClientUserInfoId()!=null){
			this.clientUserInfoId = po.getClientUserInfoId().toString();
		}
		if(po.getStaffId()!=null){
			this.staffId = po.getStaffId().toString();
		}
		return StrutsResMSG.SUCCESS;
	}

	@Action(value = "addinfo", results = {
			@Result(name = StrutsResMSG.SUCCESS, location = "/client/clientuserregioninfo/add.jsp"),
			@Result(name = StrutsResMSG.FAILED, location = "/client/clientuserregioninfo/add.jsp") })
	public String addInfo() {
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
        log.debug("modify clientUserRegionInfo: " + clientUserRegionInfoId);
        ClientUserRegionInfo oldpo = service.findByid(clientUserRegionInfoId);
        oldpo.setId(new Integer(clientUserRegionInfoId));
		if(!StringHelper.isNull(countryId)){
			oldpo.setCountryId(Integer.parseInt(countryId));
		}
		if(!StringHelper.isNull(countryName)){
			oldpo.setCountryName(countryName);
		}
		if(!StringHelper.isNull(provinceId)){
			oldpo.setProvinceId(Integer.parseInt(provinceId));
		}
		if(!StringHelper.isNull(provinceName)){
			oldpo.setProvinceName(provinceName);
		}
		if(!StringHelper.isNull(cityId)){
			oldpo.setCityId(Integer.parseInt(cityId));
		}
		if(!StringHelper.isNull(cityName)){
			oldpo.setCityName(cityName);
		}
		if(!StringHelper.isNull(areaId)){
			oldpo.setAreaId(Integer.parseInt(areaId));
		}
		if(!StringHelper.isNull(areaName)){
			oldpo.setAreaName(areaName);
		}
		if(!StringHelper.isNull(userRealAddress)){
			oldpo.setUserRealAddress(userRealAddress);
		}
		if(!StringHelper.isNull(clientUserInfoId)){
			oldpo.setClientUserInfoId(Integer.parseInt(clientUserInfoId));
		}
		if(!StringHelper.isNull(userRealMobile)){
			oldpo.setUserRealMobile(userRealMobile);
		}
		if(!StringHelper.isNull(userRealPlane)){
			oldpo.setUserRealPlane(userRealPlane);
		}
		if(!StringHelper.isNull(userRealName)){
			oldpo.setUserRealName(userRealName);
		}
		if(!StringHelper.isNull(userRealPostcode)){
			oldpo.setUserRealPostcode(userRealPostcode);
		}
		if(!StringHelper.isNull(staffId)){
			oldpo.setStaffId(Integer.parseInt(staffId));
		}
		oldpo.setModifyTime(new Timestamp(System.currentTimeMillis()));
		int res = this.service.modify(oldpo);
		if (res == 1) {
			super.error("已存在相同的数据");
		} else {
			super.success(null, AjaxResult.CALL_BACK_TYPE_CLOSECURRENT, "clientUserRegionInfo",
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
		ClientUserRegionInfo po = new ClientUserRegionInfo();
		if(!StringHelper.isNull(countryId)){
			po.setCountryId(Integer.parseInt(countryId));
		}
		if(!StringHelper.isNull(countryName)){
			po.setCountryName(countryName);
		}
		if(!StringHelper.isNull(provinceId)){
			po.setProvinceId(Integer.parseInt(provinceId));
		}
		if(!StringHelper.isNull(provinceName)){
			po.setProvinceName(provinceName);
		}
		if(!StringHelper.isNull(cityId)){
			po.setCityId(Integer.parseInt(cityId));
		}
		if(!StringHelper.isNull(cityName)){
			po.setCityName(cityName);
		}
		if(!StringHelper.isNull(areaId)){
			po.setAreaId(Integer.parseInt(areaId));
		}
		if(!StringHelper.isNull(areaName)){
			po.setAreaName(areaName);
		}
		if(!StringHelper.isNull(userRealAddress)){
			po.setUserRealAddress(userRealAddress);
		}
		if(!StringHelper.isNull(clientUserInfoId)){
			po.setClientUserInfoId(Integer.parseInt(clientUserInfoId));
		}
		if(!StringHelper.isNull(userRealMobile)){
			po.setUserRealMobile(userRealMobile);
		}
		if(!StringHelper.isNull(userRealPlane)){
			po.setUserRealPlane(userRealPlane);
		}
		if(!StringHelper.isNull(userRealName)){
			po.setUserRealName(userRealName);
		}
		if(!StringHelper.isNull(userRealPostcode)){
			po.setUserRealPostcode(userRealPostcode);
		}
		if(!StringHelper.isNull(staffId)){
			po.setStaffId(Integer.parseInt(staffId));
		}
		po.setCreateTime(new Timestamp(System.currentTimeMillis()));
		po.setModifyTime(new Timestamp(System.currentTimeMillis()));
		int res = service.save(po);
		if (res == 1)
			super.error("已存在");
		else
			super.success(null, AjaxResult.CALL_BACK_TYPE_CLOSECURRENT, "clientUserRegionInfo",
					null);

		return null;
	}

	@Action(value = "del", results = {@Result(name = StrutsResMSG.SUCCESS, type = "plainText")})
    public String del() throws IOException,JSONException{
        String clientUserRegionInfoId = ServletActionContext.getRequest()
                .getParameter("clientUserRegionInfoId");
        service.del(clientUserRegionInfoId);
        super.success(null,null,"clientUserRegionInfo",null);
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
