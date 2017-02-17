/**
 *2012-5-12 下午1:51:41
 */
package com.xnradmin.core.action;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.cntinker.util.StringHelper;
import com.xnradmin.core.service.MenuService;
import com.xnradmin.core.service.RoleService;
import com.xnradmin.core.service.StaffService;
import com.xnradmin.core.util.SecureUtil;
import com.xnradmin.constant.AjaxResult;
import com.xnradmin.constant.SessionConstant;
import com.xnradmin.constant.StrutsResMSG;
import com.xnradmin.po.CommonStaff;

/**
 * @autohr: bin_liu
 * 
 */
@Controller
@Scope("prototype")
@Namespace("/")
@ParentPackage("json-default")
public class LoginAction extends ParentAction{

	static Log log = LogFactory.getLog(LoginAction.class);

	@Autowired
	private MenuService menuService;
	@Autowired
	private StaffService staffService;
	@Autowired
	private RoleService roleService;

	@Action(value = "login", results = {
			@Result(name = StrutsResMSG.SUCCESS, location = "/main.jsp"),
			@Result(name = StrutsResMSG.FAILED, location = "/login.jsp") })
	public String login() {

		String serverVcode = ServletActionContext.getRequest().getSession()
				.getAttribute(SessionConstant.SESSION_VALIDCODE) == null ? ""
				: ServletActionContext.getRequest().getSession()
						.getAttribute(SessionConstant.SESSION_VALIDCODE)
						.toString();

		if (StringHelper.isNull(serverVcode) || StringHelper.isNull(j_password)
				|| StringHelper.isNull(j_username)
				|| StringHelper.isNull(validateCode)) {
			errorMsg = "登录错误";
			return StrutsResMSG.ERROR;
		}

		String loginid = ServletActionContext.getRequest().getSession()
				.getAttribute(SessionConstant.SESSION_LOGIN_STAFF) == null ? ""
				: ServletActionContext.getRequest().getSession()
						.getAttribute(SessionConstant.SESSION_LOGIN_STAFF)
						.toString();

		log.debug("old loginid::: " + loginid);

		CommonStaff staff = staffService.findByLoginId(j_username);
		if (staff == null) {
			errorMsg = "用户不存在";
			return StrutsResMSG.ERROR;
		}

		CommonStaff login = new CommonStaff();
		login.setLoginId(j_username);
		login.setPassword(j_password);

		if (!staff.getPassword().equals(SecureUtil.getEncodePswd(login))) {
			errorMsg = "密码错误";
			return StrutsResMSG.ERROR;
		}
		if (!serverVcode.equals(validateCode)) {
			errorMsg = "验证码错误";
			return StrutsResMSG.ERROR;
		}

		if (staff.getStatusId() == 3) {
			errorMsg = "帐号已删除";
			return StrutsResMSG.ERROR;
		}
		if (staff.getStatusId() == 2) {
			errorMsg = "帐号已冻结";
			return StrutsResMSG.ERROR;
		}
		ServletActionContext.getRequest().getSession()
				.setAttribute(SessionConstant.SESSION_LOGIN_STAFF, j_username);
		ServletActionContext.getRequest().getSession()
				.setAttribute(SessionConstant.SESSION_LOGIN_STAFF_NAME,
						staff.getStaffName());
		
		
		List<Integer> ids = roleService.getRoleId4User(staff.getId());
		log.debug("role id: "+Arrays.deepToString(ids.toArray()));
		// 设置菜单
		menuList = menuService.menuOut(ids);
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
	

	/**
	 * 超时登陆处理
	 * 
	 * @return String
	 * @throws IOException
	 */
	@Action(value = "loginDialog")
	public String loginDialog() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();

		String serverVcode = request.getSession().getAttribute(
				SessionConstant.SESSION_VALIDCODE) == null ? ""
				: ServletActionContext.getRequest().getSession()
						.getAttribute(SessionConstant.SESSION_VALIDCODE)
						.toString();

		if (StringHelper.isNull(serverVcode) || StringHelper.isNull(j_password)
				|| StringHelper.isNull(j_username)
				|| StringHelper.isNull(validateCode)) {
			errorMsg = "登录错误";
			super.error(errorMsg);
			return null;
		}

		String loginid = request.getSession().getAttribute(
				SessionConstant.SESSION_LOGIN_STAFF) == null ? "" : request
				.getSession().getAttribute(SessionConstant.SESSION_LOGIN_STAFF)
				.toString();

		log.debug(" loginid::: " + loginid);

		CommonStaff staff = staffService.findByLoginId(j_username);
		if (staff == null) {
			errorMsg = "用户不存在";
			super.error(errorMsg);
			return null;
		}

		CommonStaff login = new CommonStaff();
		login.setLoginId(j_username);
		login.setPassword(j_password);

		if (!staff.getPassword().equals(SecureUtil.getEncodePswd(login))) {
			errorMsg = "密码错误";
			super.error(errorMsg);
			return null;
		}
		if (!serverVcode.equals(validateCode)) {
			errorMsg = "验证码错误";
			super.error(errorMsg);
			return null;
		}

		if (staff.getStatusId() != 1) {
			errorMsg = "帐号已删除";
			super.error(errorMsg);
			return null;
		}

		ServletActionContext.getRequest().getSession()
				.setAttribute(SessionConstant.SESSION_LOGIN_STAFF, j_username);

		super.success(null, AjaxResult.CALL_BACK_TYPE_CLOSECURRENT, null, null);

		// List<Integer> ids = roleService.getRoleId4User(staff.getId());
		// 设置菜单
		// menuList = menuService.menuOut(ids);
		return null;
	}

	private String menuList;

	private String errorMsg;

	private String j_password;

	private String j_username;

	private String validateCode;

	public String getMenuList() {
		return menuList;
	}

	public void setMenuList(String menuList) {
		this.menuList = menuList;
	}

	public String getJ_password() {
		return j_password;
	}

	public void setJ_password(String j_password) {
		this.j_password = j_password;
	}

	public String getJ_username() {
		return j_username;
	}

	public void setJ_username(String j_username) {
		this.j_username = j_username;
	}

	public String getValidateCode() {
		return validateCode;
	}

	public void setValidateCode(String validateCode) {
		this.validateCode = validateCode;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	
	@Override
	public boolean isPublic() {
		// TODO Auto-generated method stub
		return true;
	}

}
