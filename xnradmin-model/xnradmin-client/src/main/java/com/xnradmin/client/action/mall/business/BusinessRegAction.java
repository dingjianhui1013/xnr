/**
 *2012-5-12 下午1:51:41
 */
package com.xnradmin.client.action.mall.business;

import java.sql.Timestamp;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.cntinker.security.MD5Encoder;
import com.xnradmin.core.action.ParentAction;
import com.xnradmin.core.service.StaffService;
import com.xnradmin.core.service.common.status.StatusService;
import com.xnradmin.core.service.mall.clientUser.ClientUserInfoService;
import com.xnradmin.core.service.mall.clientUser.ClientUserRegionInfoService;
import com.xnradmin.core.service.mall.region.AreaService;
import com.xnradmin.constant.SessionConstant;
import com.xnradmin.constant.StrutsResMSG;
import com.xnradmin.po.CommonStaff;
import com.xnradmin.po.client.ClientUserInfo;
import com.xnradmin.po.client.ClientUserRegionInfo;
import com.xnradmin.po.common.status.Status;
import com.xnradmin.po.mall.region.Area;

/**
 * @autohr: bin_liu
 * 
 */
@Controller
@Scope("prototype")
@Namespace("/page/wx/client/business/reg")
@ParentPackage("json-default")
public class BusinessRegAction extends ParentAction {

	static Log log = LogFactory.getLog(BusinessRegAction.class);

	@Autowired
	private StaffService staffService;
	@Autowired
	private ClientUserInfoService clientUserInfoService;
	@Autowired
	private StatusService statusService;
	@Autowired
	private AreaService areaService;
	@Autowired
	private ClientUserRegionInfoService clientUserRegionInfoService;
	
	private String mobile; // 手机号
	private String staffname; // 用户昵称
	private String errorMsg; // 错误代码
	private String password; // 密码
	private String validateCode; // 验证码
	private String theEarliestTime; // 最早派送时间
	private String theLatestTime; // 最晚派送时间
	private String areaId; //区ID
	private String addres; //送货地区
	private String userInfoJson;
	
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getStaffname() {
		return staffname;
	}

	public void setStaffname(String staffname) {
		this.staffname = staffname;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getValidateCode() {
		return validateCode;
	}

	public void setValidateCode(String validateCode) {
		this.validateCode = validateCode;
	}

	public String getTheEarliestTime() {
		return theEarliestTime;
	}

	public void setTheEarliestTime(String theEarliestTime) {
		this.theEarliestTime = theEarliestTime;
	}

	public String getTheLatestTime() {
		return theLatestTime;
	}

	public void setTheLatestTime(String theLatestTime) {
		this.theLatestTime = theLatestTime;
	}

	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	public String getAddres() {
		return addres;
	}

	public void setAddres(String addres) {
		this.addres = addres;
	}

	public String getUserInfoJson() {
		return userInfoJson;
	}

	public void setUserInfoJson(String userInfoJson) {
		this.userInfoJson = userInfoJson;
	}

	@Action(value = "regAdd", results = { @Result(name = StrutsResMSG.SUCCESS, type = "json") })
	public String add() throws JSONException {
//		String serverVcode = ServletActionContext.getRequest().getSession()
//				.getAttribute(SessionConstant.SESSION_VALIDCODE) == null ? ""
//				: ServletActionContext.getRequest().getSession()
//						.getAttribute(SessionConstant.SESSION_VALIDCODE)
//						.toString();
//		if (StringHelper.isNull(serverVcode)
//				|| StringHelper.isNull(validateCode)) {
//			errorMsg = "登录错误";// 登录错误
//		} else if (!serverVcode.equals(validateCode)) {
//			errorMsg = "验证码错误";// 验证码错误;
//		} else {
			CommonStaff staff = new CommonStaff();
			staff.setCreateTime(new Timestamp(new Date().getTime()));
			// 登录名和员工编号一致
			staff.setLoginId(mobile);
			staff.setStaffNo(mobile);
			staff.setMobile(mobile);
			staff.setDiscount(1f);
			staff.setOrganizationId(15);
			staff.setStaffName(staffname);
			staff.setStatusId(7);
			staff.setPassword(MD5Encoder.encode32(password + "{" + mobile
					+ "}"));
			staff.setGender(0);
			staff.setTheEarliestTime(theEarliestTime);
			staff.setTheLatestTime(theLatestTime);
			staff.setViewDiscount(1.0f);
			int res = staffService.saveBusinessUser(staff);
			JSONObject json = new JSONObject();
			json.put("id", res);
			json.put("name", staff.getStaffName());
			json.put("loginId", staff.getLoginId());
			json.put("mobile", staff.getMobile());
			json.put("password", password);
			json.put("stime", staff.getTheEarliestTime());
			json.put("etime", staff.getTheLatestTime());
			userInfoJson = json.toString();
			if (res == -1) {
				errorMsg = "已存在相同的手机号";// 已存在相同的手机号
			} else {
				// 添加到用户表
				ClientUserInfo po = new ClientUserInfo();
				po.setStaffId(res);
				po.setIswebsiteuser(1);
				po.setStatus(139);
				Status statusCode = statusService.findByid("139");
				po.setStatusName(statusCode.getStatusName());
				po.setType(154);
				Status typeCode = statusService.findByid("154");
				po.setTypeName(typeCode.getStatusName());
				po.setMobile(mobile);
				po.setLoginPassWord(MD5Encoder.encode32(password + "{" + mobile
					+ "}"));
				po.setNickName(staffname);
				po.setCreateTime(new Timestamp(System.currentTimeMillis()));
				po.setLastLoginTime(new Timestamp(System.currentTimeMillis()));
				po.setModifyTime(new Timestamp(System.currentTimeMillis()));
				po.setDiscount(1f);
				int clientUserId = clientUserInfoService.saveBusinessUser(po);

				// 增加用户配送地区
				ClientUserRegionInfo cri = new ClientUserRegionInfo();
				Area area = areaService.findByid(areaId);
				cri.setCountryId(area.getCountryId());
				cri.setCountryName(area.getCountry());
				cri.setProvinceId(area.getProvinceId());
				cri.setProvinceName(area.getProvince());
				cri.setCityId(area.getCityId());
				cri.setCityName(area.getCity());
				cri.setAreaId(area.getId());
				cri.setAreaName(area.getArea());
				cri.setUserRealAddress(addres);
				cri.setClientUserInfoId(clientUserId);
				cri.setUserRealMobile(mobile);
				cri.setUserRealName(staffname);
				cri.setStaffId(res);
				cri.setCreateTime(new Timestamp(System.currentTimeMillis()));
				cri.setModifyTime(new Timestamp(System.currentTimeMillis()));
				clientUserRegionInfoService.saveWx(cri);
				
				
				errorMsg = "0";
			}
//		}
		return StrutsResMSG.SUCCESS;
	}

	@Action(value = "logout", results = {
			@Result(name = StrutsResMSG.SUCCESS, location = "/login.jsp"),
			@Result(name = StrutsResMSG.FAILED, location = "/login.jsp") })
	public String logout() {
		ServletActionContext.getRequest().getSession()
				.removeAttribute(SessionConstant.SESSION_LOGIN_STAFF);
		return StrutsResMSG.SUCCESS;
	}

	@Override
	public boolean isPublic() {
		// TODO Auto-generated method stub
		return true;
	}

}
