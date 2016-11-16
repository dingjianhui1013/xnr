/**
 *2012-5-12 下午1:51:41
 */
package com.xnradmin.client.action.mall.business;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
import com.cntinker.util.StringHelper;
import com.xnradmin.core.action.ParentAction;
import com.xnradmin.core.service.MenuService;
import com.xnradmin.core.service.RoleService;
import com.xnradmin.core.service.StaffService;
import com.xnradmin.core.service.mall.clientUser.ClientUserInfoService;
import com.xnradmin.core.service.mall.clientUser.ClientUserRegionInfoService;
import com.xnradmin.core.util.SecureUtil;
import com.xnradmin.constant.AjaxResult;
import com.xnradmin.constant.SessionConstant;
import com.xnradmin.constant.StrutsResMSG;
import com.xnradmin.po.CommonStaff;
import com.xnradmin.po.client.ClientUserInfo;

/**
 * @autohr: bin_liu
 * 
 */
@Controller
@Scope("prototype")
@Namespace("/page/wx/client/business/login")
@ParentPackage("json-default")
public class BusinessLoginAction extends ParentAction {

	static Log log = LogFactory.getLog(BusinessLoginAction.class);

	@Autowired
	private StaffService staffService;

	@Autowired
	private ClientUserInfoService clientUserInfoService;
	
	private String loginId;

	private String errorMsg;

	private String password;

	private String validateCode;
	
	private String userInfoJson;

	private String newpassword;
	
	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
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

	public String getUserInfoJson() {
		return userInfoJson;
	}

	public void setUserInfoJson(String userInfoJson) {
		this.userInfoJson = userInfoJson;
	}

	public String getNewpassword() {
		return newpassword;
	}

	public void setNewpassword(String newpassword) {
		this.newpassword = newpassword;
	}

	@Action(value = "login", results = { @Result(name = StrutsResMSG.SUCCESS, type = "json") })
	public String login() throws JSONException {

		// String serverVcode = ServletActionContext.getRequest().getSession()
		// .getAttribute(SessionConstant.SESSION_VALIDCODE) == null ? ""
		// : ServletActionContext.getRequest().getSession()
		// .getAttribute(SessionConstant.SESSION_VALIDCODE)
		// .toString();

		// if (StringHelper.isNull(serverVcode) ||
		// StringHelper.isNull(j_password)
		// || StringHelper.isNull(j_username)
		// || StringHelper.isNull(validateCode)) {
		// errorMsg = "登录错误";
		// }

		CommonStaff staff = staffService.findByLoginId(loginId);
		if (staff == null) {
			errorMsg = "用户不存在";
		} else {
			CommonStaff login = new CommonStaff();
			login.setLoginId(loginId);
			login.setPassword(password);
			if (!staff.getPassword().equals(SecureUtil.getEncodePswd(login))) {
				errorMsg = "密码错误";
			}
			// if (!serverVcode.equals(validateCode)) {
			// errorMsg = "验证码错误";
			// return StrutsResMSG.ERROR;
			// }
			else if (staff.getStatusId() == 3) {
				errorMsg = "帐号已删除";
			} else if (staff.getStatusId() == 2) {
				errorMsg = "帐号已冻结";
			} else if(staff.getStatusId() == 7){
				errorMsg = "账号还在审核中,请您耐心等待，也可拨打客服电话咨询";
			}else {
				errorMsg = "0";
				loginId = staff.getLoginId();
				JSONObject json = new JSONObject();
				json.put("id", staff.getId());
				json.put("name", staff.getStaffName());
				json.put("loginId", staff.getLoginId());
				json.put("mobile", staff.getMobile());
				json.put("password", password);
				json.put("stime", staff.getTheEarliestTime());
				json.put("etime", staff.getTheLatestTime());
				userInfoJson = json.toString();
			}
		}
		return StrutsResMSG.SUCCESS;
	}

	@Action(value = "password", results = { @Result(name = StrutsResMSG.SUCCESS, type = "json") })
	public String password() throws JSONException {
		CommonStaff staff = staffService.findByLoginId(loginId);
		if (staff == null) {
			errorMsg = "用户不存在";
		} else {
			CommonStaff login = new CommonStaff();
			login.setLoginId(loginId);
			login.setPassword(password);
			if (!staff.getPassword().equals(SecureUtil.getEncodePswd(login))) {
				errorMsg = "密码错误";
			}
			else if (staff.getStatusId() == 3) {
				errorMsg = "帐号已删除";
			} else if (staff.getStatusId() == 2) {
				errorMsg = "帐号已冻结";
			} else if(staff.getStatusId() == 7){
				errorMsg = "账号还在审核中,请您耐心等待，也可拨打客服电话咨询";
			}else {
				errorMsg = "0";
				int a = staffService.changePwd(staff, password, newpassword);
				loginId = staff.getLoginId();
				JSONObject json = new JSONObject();
				json.put("id", staff.getId());
				json.put("name", staff.getStaffName());
				json.put("loginId", staff.getLoginId());
				json.put("mobile", staff.getMobile());
				if(a == 0){
					json.put("password", newpassword);
					ClientUserInfo clientUserInfo = clientUserInfoService.findByProperty(
							"staffId", Integer.parseInt(staff.getId().toString()));
					if(clientUserInfo != null){
						clientUserInfo.setLoginPassWord(MD5Encoder.encode32(newpassword + "{" + staff.getLoginId()+ "}"));
						clientUserInfoService.modify(clientUserInfo);
					}
				}else{
					json.put("password", password);
				}
				json.put("stime", staff.getTheEarliestTime());
				json.put("etime", staff.getTheLatestTime());
				userInfoJson = json.toString();
			}
		}
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
