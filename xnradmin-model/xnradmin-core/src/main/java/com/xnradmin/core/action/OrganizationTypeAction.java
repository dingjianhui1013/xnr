/**
 *2012-5-11 下午4:39:38
 */
package com.xnradmin.core.action;

import java.io.IOException;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.cntinker.util.StringHelper;
import com.xnradmin.core.service.OrganizationTypeService;
import com.xnradmin.constant.AjaxResult;
import com.xnradmin.constant.StrutsResMSG;
import com.xnradmin.po.CommonOrganizationType;

/**
 * @autohr: bin_liu
 * 
 */
@Controller
@Scope("prototype")
@Namespace("/page/orgtype")
@ParentPackage("json-default")
public class OrganizationTypeAction extends ParentAction {

	@Autowired
	private OrganizationTypeService service;

	@Override
	public boolean isPublic() {
		return false;
	};

	static Log log = LogFactory.getLog(OrganizationAction.class);

	/**
	 * 跳转到信息页
	 * 
	 * @return
	 */
	@Action(value = "info", results = {
			@Result(name = StrutsResMSG.SUCCESS, location = "/orgtype/info.jsp"),
			@Result(name = StrutsResMSG.FAILED, location = "/orgtype/info.jsp") })
	public String info() {

		// 设置排序
		setSort();

		this.voList = service.listVO(searchOrgTypeName, getPageNum(),
				getNumPerPage(), orderField, orderDirection);
		this.totalCount = service.getCountByPorgid(searchOrgTypeName);

		return StrutsResMSG.SUCCESS;
	}

	/**
	 * 设置排序相关项
	 */
	private void setSort() {
		HttpServletRequest request = ServletActionContext.getRequest();
		if (!StringHelper.isNull(orderField)) {
			this.idsort = orderDirection;
		} else {
			this.idsort = "asc";
		}
	}

	/**
	 * 带信息到修改页
	 * 
	 * @return String
	 */
	@Action(value = "modifyinfo", results = {
			@Result(name = StrutsResMSG.SUCCESS, location = "/orgtype/modify.jsp"),
			@Result(name = StrutsResMSG.FAILED, location = "/orgtype/modify.jsp") })
	public String modifyinfo() {
		CommonOrganizationType po = service.findByid(orgid);
		this.orgname = po.getOrganizationTypeName();
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

		CommonOrganizationType po = new CommonOrganizationType();
		po.setId(new Integer(orgid));

		po.setOrganizationTypeName(orgname);

		int res = this.service.modify(po);
		if (res == 1) {
			super.error("已存在相同的组织类型名称");
		} else {
			super.success(null, AjaxResult.CALL_BACK_TYPE_CLOSECURRENT,
					"orgType", null);
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

		CommonOrganizationType po = new CommonOrganizationType();
		po.setOrganizationTypeName(orgname);

		int res = service.save(po);
		if (res == 1)
			super.error("组织类型名称已存在");
		else
			super.success(null, AjaxResult.CALL_BACK_TYPE_CLOSECURRENT,
					"orgType", null);

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

	private String searchOrgTypeName;

	private String idsort;

	private List<CommonOrganizationType> voList;

	private String orgid;

	private String orgname;

	public String getOrgname() {
		return orgname;
	}

	public void setOrgname(String orgname) {
		this.orgname = orgname;
	}

	public String getOrgid() {
		return orgid;
	}

	public void setOrgid(String orgid) {
		this.orgid = orgid;
	}

	public List<CommonOrganizationType> getVoList() {
		return voList;
	}

	public void setVoList(List<CommonOrganizationType> voList) {
		this.voList = voList;
	}

	public String getIdsort() {
		return idsort;
	}

	public void setIdsort(String idsort) {
		this.idsort = idsort;
	}

	public String getSearchOrgTypeName() {
		return searchOrgTypeName;
	}

	public void setSearchOrgTypeName(String searchOrgTypeName) {
		this.searchOrgTypeName = searchOrgTypeName;
	}

}
