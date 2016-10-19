/**
 *2012-5-15 上午12:54:57
 */
package com.xnradmin.core.action;

import java.io.IOException;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.mysql.jdbc.StringUtils;
import com.xnradmin.core.service.PermissionService;
import com.xnradmin.constant.AjaxResult;
import com.xnradmin.constant.StrutsResMSG;
import com.xnradmin.po.CommonPermission;

/**
 * @autohr: bin_liu
 * 
 */
@Controller
@Scope("prototype")
@Namespace("/page/per")
public class PermissionAction extends ParentAction {
	@Autowired
	public PermissionService service;

	private String searchName;
	private List<CommonPermission> voList;
	private String permissionName;
	private String permissionDesc;
	private String permissionCode;
	private Integer queryid;
	private boolean enabled;

	@Override
	public boolean isPublic() {
		// TODO Auto-generated method stub
		return false;
	}

	@Action(value = "info", results = {
			@Result(name = StrutsResMSG.SUCCESS, location = "/permission/info.jsp"),
			@Result(name = StrutsResMSG.FAILED, location = "/permission/info.jsp") })
	public String info() {
		this.voList = service.findValidPermissions();
		return StrutsResMSG.SUCCESS;
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

		CommonPermission po = new CommonPermission();
		po.setEnabled(true);
		po.setPermissionName(permissionName);
		po.setPermissionCode(permissionCode);
		po.setPermissionDesc(permissionDesc);

		int res = this.service.save(po);

		if (res == 1)
			super.error("权限名称或权限代码已存在");
		else
			super.success(null, AjaxResult.CALL_BACK_TYPE_CLOSECURRENT,
					"permission", null);

		return null;
	}

	/**
	 * 禁用对象接口
	 * 
	 * @return String
	 * @throws IOException
	 * @throws JSONException
	 */
	@Action(value = "del", results = { @Result(name = StrutsResMSG.SUCCESS, type = "plainText") })
	public String disable() throws IOException, JSONException {

		CommonPermission po = service.findById(queryid);
		po.setEnabled(false);
		int res = this.service.save(po);

		if (res == 1)
			super.error("权限不存在");
		else
			super.success(null, null, "permission", null);

		return null;
	}

	/**
	 * 带信息到修改页
	 * 
	 * @return String
	 */
	@Action(value = "modifyinfo", results = {
			@Result(name = StrutsResMSG.SUCCESS, location = "/permission/modify.jsp"),
			@Result(name = StrutsResMSG.FAILED, location = "/permission/modify.jsp") })
	public String modifyinfo() {
		CommonPermission po = service.findById(queryid);
		this.permissionName = po.getPermissionName();
		this.permissionCode = po.getPermissionCode();
		this.permissionDesc = po.getPermissionDesc();

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

		CommonPermission po = service.findById(queryid);

		if (!StringUtils.isNullOrEmpty(permissionDesc))
			po.setPermissionDesc(permissionDesc);
		po.setPermissionName(permissionName);
		po.setPermissionCode(StringUtils.isNullOrEmpty(permissionCode) ? ""
				: permissionCode);

		int res = this.service.modify(po);
		if (res == 1) {
			super.error("更改权限信息出错");
		} else {
			super.success(null, AjaxResult.CALL_BACK_TYPE_CLOSECURRENT,
					"permission", null);
		}

		return null;
	}

	public String getSearchName() {
		return searchName;
	}

	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}

	public List<CommonPermission> getVoList() {
		return voList;
	}

	public void setVoList(List<CommonPermission> voList) {
		this.voList = voList;
	}

	public String getPermissionName() {
		return permissionName;
	}

	public void setPermissionName(String permissionName) {
		this.permissionName = permissionName;
	}

	public String getPermissionDesc() {
		return permissionDesc;
	}

	public void setPermissionDesc(String permissionDesc) {
		this.permissionDesc = permissionDesc;
	}

	public String getPermissionCode() {
		return permissionCode;
	}

	public void setPermissionCode(String permissionCode) {
		this.permissionCode = permissionCode;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Integer getQueryid() {
		return queryid;
	}

	public void setQueryid(Integer queryid) {
		this.queryid = queryid;
	}

}
