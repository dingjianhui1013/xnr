/**
 *2012-5-10 下午3:52:29
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
import com.xnradmin.core.service.OrganizationService;
import com.xnradmin.core.service.OrganizationTypeService;
import com.xnradmin.constant.AjaxResult;
import com.xnradmin.constant.StrutsResMSG;
import com.xnradmin.po.CommonOrganization;
import com.xnradmin.po.CommonOrganizationType;
import com.xnradmin.vo.GetOrgListVO;

/**
 * @autohr: bin_liu
 * 
 */
@Controller
@Scope("prototype")
@Namespace("/page/org")
@ParentPackage("json-default")
public class OrganizationAction extends ParentAction {

	@Autowired
	private OrganizationService service;

	@Autowired
	private OrganizationTypeService typeService;

	@Override
	public boolean isPublic() {
		return false;
	}

	static Log log = LogFactory.getLog(OrganizationAction.class);

	/**
	 * 跳转到信息页。。
	 * 
	 * @return
	 */
	@Action(value = "info", results = {
			@Result(name = StrutsResMSG.SUCCESS, location = "/org/info.jsp"),
			@Result(name = StrutsResMSG.FAILED, location = "/org/info.jsp") })
	public String info() {

		setPageInfo();
		return StrutsResMSG.SUCCESS;
	}

	@Action(value = "lookup", results = {
			@Result(name = StrutsResMSG.SUCCESS, location = "/org/lookup.jsp"),
			@Result(name = StrutsResMSG.FAILED, location = "/org/lookup.jsp") })
	public String lookup() {
		setPageInfo();
		return StrutsResMSG.SUCCESS;
	}
	
	@Action(value = "orgTree", results = {
			@Result(name = StrutsResMSG.SUCCESS, location = "/org/orgTree.jsp"),
			@Result(name = StrutsResMSG.FAILED, location = "/org/orgTree.jsp") })
	public String orgTree() {
		this.orgTree = service.getOrgTree();
		return StrutsResMSG.SUCCESS;
	}

	private void setPageInfo() {

		// 设置排序
		setSort(orderField, orderDirection);
		String orgid = "";
		if (org1 != null && org1.getId() != null) {
			orgid = org1.getId().toString();
		}

		this.voList = service.listVO(orgid, orgName, getPageNum(),
				getNumPerPage(), orderField, orderDirection);
		this.totalCount = service.getCountByPorgid(orgid, orgName);

		// 设置类型选项
		setTypeList();
	}

	private void setTypeList() {
		this.typeList = typeService.listAll();
	}

	/**
	 * 设置排序相关项
	 */
	private void setSort(String orderField, String orderDirection) {
		HttpServletRequest request = ServletActionContext.getRequest();
		if (!StringHelper.isNull(orderField)) {
			// 部门编号
			if (orderField.equals("id")) {
				this.idsort = orderDirection;
				// 分页用
				this.orderField = "id";
				this.orderDirection = orderDirection;
			} else if (orderField.equals("departmentlevel")) {
				this.levelsort = orderDirection;
				this.idsort = "asc";
			}
		} else {
			request.setAttribute("idsort", "asc");
			request.setAttribute("levelsort", "asc");
		}
	}

	/**
	 * 带信息到修改页
	 * 
	 * @return String
	 */
	@Action(value = "modifyinfo", results = {
			@Result(name = StrutsResMSG.SUCCESS, location = "/org/modify.jsp"),
			@Result(name = StrutsResMSG.FAILED, location = "/org/modify.jsp") })
	public String modifyinfo() {

		CommonOrganization po = service.findByid(orgid);
		this.orgname = po.getOrganizationName();
		log.debug("porgid::: " + po.getPraentOrganizationId());
		if (po.getPraentOrganizationId() != null
				&& po.getPraentOrganizationId() != 0) {
			CommonOrganization parent = service.findByid(po
					.getPraentOrganizationId().toString());
			log.info("porgid::: " + po.getPraentOrganizationId());
			if (parent != null) {
				this.porgid = parent.getId().toString();
				this.porgname = parent.getOrganizationName();
			}

		}
		this.typeId = po.getOrganizationTypeId().toString();

		// 设置类型选项
		setTypeList();
		return StrutsResMSG.SUCCESS;
	}

	@Action(value = "addinfo", results = {
			@Result(name = StrutsResMSG.SUCCESS, location = "/org/add.jsp"),
			@Result(name = StrutsResMSG.FAILED, location = "/org/add.jsp") })
	public String addInfo() {
		// 设置类型选项
		setTypeList();
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

		log.info("type: " + type);
		CommonOrganization po = new CommonOrganization();
		po.setId(new Integer(orgid));
		if (org1 != null && org1.getId() != null)
			po.setPraentOrganizationId(org1.getId());
		po.setOrganizationName(orgname);

		po.setOrganizationTypeId(new Integer(this.orgType));
		int res = this.service.modify(po);
		if (res == 1) {
			super.error("已存在相同的组织名称");
		} else {
			super.success(null, AjaxResult.CALL_BACK_TYPE_CLOSECURRENT, "org",
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

		CommonOrganization po = new CommonOrganization();
		po.setOrganizationName(orgname);
		if (org1 != null && org1.getId() != null)
			po.setPraentOrganizationId(org1.getId());

		log.debug("orgType:::::::" + orgType);

		po.setOrganizationTypeId(new Integer(orgType));
		int res = service.save(po);
		if (res == 1)
			super.error("组织名称已存在");
		else
			super.success(null, AjaxResult.CALL_BACK_TYPE_CLOSECURRENT, "org",
					null);

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

	private String porgid;
	private String orgName_save;
	private String idsort;
	private String levelsort;
	private String orgname;
	private String porgname;
	private String typeId;
	private String orgName;
	private String orgid;
	
	private String orgTree;

	private List<GetOrgListVO> voList;
	private List<CommonOrganizationType> typeList;
	private CommonOrganization org1;

	private String type;
	private String orgType;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getOrgType() {
		return orgType;
	}

	public void setOrgType(String orgType) {
		this.orgType = orgType;
	}

	public String getOrgid() {
		return orgid;
	}

	public void setOrgid(String orgid) {
		this.orgid = orgid;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public OrganizationTypeService getTypeService() {
		return typeService;
	}

	public void setTypeService(OrganizationTypeService typeService) {
		this.typeService = typeService;
	}

	public String getPorgid() {
		return porgid;
	}

	public void setPorgid(String porgid) {
		this.porgid = porgid;
	}

	public String getOrgName_save() {
		return orgName_save;
	}

	public void setOrgName_save(String orgName_save) {
		this.orgName_save = orgName_save;
	}

	public String getIdsort() {
		return idsort;
	}

	public void setIdsort(String idsort) {
		this.idsort = idsort;
	}

	public String getLevelsort() {
		return levelsort;
	}

	public void setLevelsort(String levelsort) {
		this.levelsort = levelsort;
	}

	public String getOrgname() {
		return orgname;
	}

	public void setOrgname(String orgname) {
		this.orgname = orgname;
	}

	public String getPorgname() {
		return porgname;
	}

	public void setPorgname(String porgname) {
		this.porgname = porgname;
	}

	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	public List<GetOrgListVO> getVoList() {
		return voList;
	}

	public void setVoList(List<GetOrgListVO> voList) {
		this.voList = voList;
	}

	public List<CommonOrganizationType> getTypeList() {
		return typeList;
	}

	public void setTypeList(List<CommonOrganizationType> typeList) {
		this.typeList = typeList;
	}

	public CommonOrganization getOrg1() {
		return org1;
	}

	public void setOrg1(CommonOrganization org1) {
		this.org1 = org1;
	}

	public String getOrgTree() {
		return orgTree;
	}

	public void setOrgTree(String orgTree) {
		this.orgTree = orgTree;
	}

}
