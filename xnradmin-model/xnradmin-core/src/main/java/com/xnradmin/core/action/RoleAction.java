/**
 *2012-5-12 上午9:44:12
 */
package com.xnradmin.core.action;

import java.io.IOException;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.cntinker.util.StringHelper;
import com.mysql.jdbc.StringUtils;
import com.xnradmin.core.service.PermissionService;
import com.xnradmin.core.service.RoleService;
import com.xnradmin.constant.AjaxResult;
import com.xnradmin.constant.StrutsResMSG;
import com.xnradmin.po.CommonPermission;
import com.xnradmin.po.CommonRole;
import com.xnradmin.po.CommonRolePermissionRelation;

/**
 * @autohr: bin_liu
 * 
 */
@Controller
@Scope("prototype")
@Namespace("/page/role")
@ParentPackage("json-default")
public class RoleAction extends ParentAction {

	@Autowired
	private RoleService service;

	@Autowired
	private PermissionService pservice;

	private String queryid;
	private String roleName;
	private String roleCode;
	private String desc;
	private String searchName;
	private List<CommonRole> voList;

	private List<CommonPermission> permissions;
	private List<Integer> myPermissionCodes;

	@Override
	public boolean isPublic() {
		return false;
	};

	@Action(value = "info", results = {
			@Result(name = StrutsResMSG.SUCCESS, location = "/role/info.jsp"),
			@Result(name = StrutsResMSG.FAILED, location = "/role/info.jsp") })
	public String info() {
		setPageInfo();
		return StrutsResMSG.SUCCESS;
	}

	@Action(value = "lookup", results = {
			@Result(name = StrutsResMSG.SUCCESS, location = "/role/lookup.jsp"),
			@Result(name = StrutsResMSG.FAILED, location = "/role/lookup.jsp") })
	public String lookup() {
		setPageInfo();
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

		CommonRole po = new CommonRole();
		po.setRoleName(roleName);
		po.setDescription(desc);
		po.setRoleCode(StringUtils.isNullOrEmpty(roleCode) ? "" : roleCode);
		po.setEnabled(true);
		int res = this.service.save(po);

		if (res == 1)
			super.error("角色名称或角色代码已存在");
		else
			super.success(null, AjaxResult.CALL_BACK_TYPE_CLOSECURRENT,
					"roleInfo", null);

		return null;
	}

	/**
	 * 带信息到修改页
	 * 
	 * @return String
	 */
	@Action(value = "modifyinfo", results = {
			@Result(name = StrutsResMSG.SUCCESS, location = "/role/modify.jsp"),
			@Result(name = StrutsResMSG.FAILED, location = "/role/modify.jsp") })
	public String modifyinfo() {		
		CommonRole po = service.findByid(queryid);
		this.roleName = po.getRoleName();
		this.roleCode = po.getRoleCode();
		this.desc = po.getDescription();

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

		CommonRole po = new CommonRole();
		po.setId(new Integer(queryid));
		if (!StringHelper.isNull(desc))
			po.setDescription(desc);
		po.setRoleName(roleName);
		po.setRoleCode(StringUtils.isNullOrEmpty(roleCode) ? "" : roleCode);

		int res = this.service.modify(po);
		if (res == 1) {
			super.error("已存在相同的组织类型名称");
		} else {
			super.success(null, AjaxResult.CALL_BACK_TYPE_CLOSECURRENT,
					"roleInfo", null);
		}

		return null;
	}

	/**
	 * 带信息到授权页面
	 * 
	 * @return String
	 */
	@Action(value = "anthinfo", results = {
			@Result(name = StrutsResMSG.SUCCESS, location = "/role/roleauth.jsp"),
			@Result(name = StrutsResMSG.FAILED, location = "/role/roleauth.jsp") })
	public String anthinfo() {

		permissions = pservice.findValidPermissions();
		myPermissionCodes = pservice.findAuthIdsByRoleId(new Integer(queryid));

		return StrutsResMSG.SUCCESS;
	}

	/**
	 * 更新对象接口
	 * 
	 * @return String
	 * @throws JSONException
	 * @throws IOException
	 */
	@Action(value = "auth", results = { @Result(name = StrutsResMSG.SUCCESS, type = "plainText") })
	public String auth() throws JSONException, IOException {

		pservice.removePermission4RoleId(new Integer(queryid));

		for (Integer authid : myPermissionCodes) {
			CommonRolePermissionRelation crp = new CommonRolePermissionRelation();
			crp.setRoleId(new Integer(queryid));
			crp.setPermissionId(authid);
			pservice.saveRolePermissionRelation(crp);
		}

		super.success(null, AjaxResult.CALL_BACK_TYPE_CLOSECURRENT, "roleInfo",
				null);

		return null;
	}

	private void setPageInfo() {
		this.voList = service.listVO(searchName, getPageNum(), getNumPerPage(),
				null, null);
		this.totalCount = service.getCount(searchName);
	}

	public String getQueryid() {
		return queryid;
	}

	public void setQueryid(String queryid) {
		this.queryid = queryid;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getSearchName() {
		return searchName;
	}

	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}

	public List<CommonRole> getVoList() {
		return voList;
	}

	public void setVoList(List<CommonRole> voList) {
		this.voList = voList;
	}

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public List<CommonPermission> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<CommonPermission> permissions) {
		this.permissions = permissions;
	}

	public List<Integer> getMyPermissionCodes() {
		return myPermissionCodes;
	}

	public void setMyPermissionCodes(List<Integer> myPermissionCodes) {
		this.myPermissionCodes = myPermissionCodes;
	}

}
