/**
 * 2012-5-12 上午9:46:29
 */
package com.xnradmin.core.action;

import java.io.IOException;
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
import com.xnradmin.core.service.MenuService;
import com.xnradmin.core.service.PermissionService;
import com.xnradmin.constant.AjaxResult;
import com.xnradmin.constant.StrutsResMSG;
import com.xnradmin.po.CommonMenu;
import com.xnradmin.po.CommonPermission;
import com.xnradmin.po.CommonPermissionMenuRelation;
import com.xnradmin.vo.MenuVO;

/**
 * @autohr: bin_liu
 */
@Controller
@Scope("prototype")
@Namespace("/page/menu")
@ParentPackage("json-default")
public class MenuAction extends ParentAction {

	@Autowired
	private MenuService service;

	@Autowired
	private PermissionService pservice;

	private String menuName;

	private String search_menuName;

	private Integer menuid;

	private Integer pmenuid;

	private String pmenuname;

	private String menuname;

	private String enmenuname;

	private String menulevel;

	private String menulink;

	private String level;

	private String sortOrder;

	private List<CommonPermission> permissions;

	private List<Integer> myPermissionCodes;

	@Override
	public boolean isPublic() {
		return false;
	};

	static Log log = LogFactory.getLog(MenuAction.class);

	/**
	 * 跳转到信息页
	 * 
	 * @return
	 */
	@Action(value = "info", results = {
			@Result(name = StrutsResMSG.SUCCESS, location = "/menu/info.jsp"),
			@Result(name = StrutsResMSG.FAILED, location = "/menu/info.jsp") })
	public String info() {
		setPageInfo();
		return StrutsResMSG.SUCCESS;
	}

	@Action(value = "lookup", results = {
			@Result(name = StrutsResMSG.SUCCESS, location = "/menu/lookup.jsp"),
			@Result(name = StrutsResMSG.FAILED, location = "/menu/lookup.jsp") })
	public String lookup() {
		setPageInfo();
		return StrutsResMSG.SUCCESS;
	}

	@Action(value = "selectLookup", results = {
			@Result(name = StrutsResMSG.SUCCESS, location = "/menu/selectLookup.jsp"),
			@Result(name = StrutsResMSG.FAILED, location = "/menu/selectLookup.jsp") })
	public String selectLookup() {
		setPageInfo();
		return StrutsResMSG.SUCCESS;
	}

	private void setPageInfo() {

		// 设置排序
		setSort(orderField, orderDirection);

		if (StringHelper.isNull(menuName)) {
			if (!StringHelper.isNull(search_menuName))
				menuName = search_menuName;
		}

		this.voList = service.listVO(menuName, getPageNum(), getNumPerPage(),
				orderField, orderDirection);
		this.setTotalCount(service.getCountByPmenuName(menuName));

	}

	/**
	 * 设置排序相关项
	 */
	private void setSort(String orderField, String orderDirection) {
		if (!StringHelper.isNull(orderField)) {
			if (orderField.equals("menu_level")) {
				// request.setAttribute("menu_level", orderDirection);
				this.menu_level = orderDirection;
				// 分页用
				this.orderField = "menu_level";
				this.orderDirection = orderDirection;
			}
		} else {
			this.menu_level = "asc";
		}
	}

	/**
	 * 带信息到修改页
	 * 
	 * @return String
	 */
	@Action(value = "modifyinfo", results = {
			@Result(name = StrutsResMSG.SUCCESS, location = "/menu/modify.jsp"),
			@Result(name = StrutsResMSG.FAILED, location = "/menu/modify.jsp") })
	public String modifyinfo() {

		CommonMenu po = service.findByid(menuid);

		if (po.getPraentMenuId() != null && po.getPraentMenuId() != 0) {
			CommonMenu parent = service.findByid(po.getPraentMenuId());
			if (parent != null) {
				this.pmenuid = parent.getId();
				this.pmenuname = parent.getMenuName();
			}

		}
		this.menuname = po.getMenuName();
		this.enmenuname = po.getEnName();
		this.menu_level = po.getMenuLevel().toString();
		this.menulevel = po.getMenuLevel().toString();
		this.menulink = po.getMenuLink();
		this.sortOrder = po.getSortOrder() == null ? "0" : po.getSortOrder()
				.toString();

		return StrutsResMSG.SUCCESS;
	}

	/**
	 * 带信息到授权页面
	 * 
	 * @return String
	 */
	@Action(value = "anthinfo", results = {
			@Result(name = StrutsResMSG.SUCCESS, location = "/menu/menuauth.jsp"),
			@Result(name = StrutsResMSG.FAILED, location = "/menu/menuauth.jsp") })
	public String anthinfo() {

		permissions = pservice.findValidPermissions();
		myPermissionCodes = pservice.findAuthIdsByMenuId(menuid);

		return StrutsResMSG.SUCCESS;
	}

	@Action(value = "addinfo", results = {
			@Result(name = StrutsResMSG.SUCCESS, location = "/menu/add.jsp"),
			@Result(name = StrutsResMSG.FAILED, location = "/menu/add.jsp") })
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

		CommonMenu po = new CommonMenu();
		po.setId(menuid);
		if (org1 != null && org1.getId() != null)
			po.setPraentMenuId(org1.getId());
		po.setMenuName(menuname);
		po.setMenuLevel(new Integer(level));
		po.setEnName(enname);
		po.setMenuLink(menulink);
		po.setSortOrder(new Integer(sortOrder));
		po.setStatus(0);

		this.service.modify(po);

		super.success(null, AjaxResult.CALL_BACK_TYPE_CLOSECURRENT, "menuInfo",
				null);

		return null;
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

		pservice.removePermission4MenuId(menuid);

		for (Integer authid : myPermissionCodes) {
			CommonPermissionMenuRelation cpm = new CommonPermissionMenuRelation();
			cpm.setMenuId(menuid);
			cpm.setPermissionId(authid);
			pservice.savePermissionMenuRelation(cpm);
		}

		super.success(null, AjaxResult.CALL_BACK_TYPE_CLOSECURRENT, "menuInfo",
				null);

		return null;
	}

	@Action(value = "del", results = { @Result(name = StrutsResMSG.SUCCESS, type = "plainText") })
	public String del() throws IOException, JSONException {

		String menuid = ServletActionContext.getRequest()
				.getParameter("menuid");
		service.del(menuid);
		super.success(null, null, "menuInfo", null);
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

		CommonMenu po = new CommonMenu();
		po.setEnName(enname);
		po.setMenuLevel(new Integer(level));
		po.setMenuLink(menulink);
		po.setMenuName(menuname);
		if (org1 != null && org1.getId() != null)
			po.setPraentMenuId(org1.getId());
		po.setStatus(0);

		service.save(po);

		super.success(null, AjaxResult.CALL_BACK_TYPE_CLOSECURRENT, "menuInfo",
				null);

		return null;
	}

	/**
	 * 外部调用，获取所有菜单信息
	 * 
	 * @return String
	 * @throws IOException
	 */
	@Action(value = "all", results = { @Result(name = StrutsResMSG.SUCCESS, type = "plainText") })
	public String all() throws IOException {
		super.toJsonArray(service.listAll());
		return null;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	private String enname;

	private CommonMenu org1;

	public CommonMenu getOrg1() {
		return org1;
	}

	public void setOrg1(CommonMenu org1) {
		this.org1 = org1;
	}

	public String getEnname() {
		return enname;
	}

	public void setEnname(String enname) {
		this.enname = enname;
	}

	List<MenuVO> voList;

	private String menu_level;

	public String getMenu_level() {
		return menu_level;
	}

	public void setMenu_level(String menu_level) {
		this.menu_level = menu_level;
	}

	public List<MenuVO> getVoList() {
		return voList;
	}

	public void setVoList(List<MenuVO> voList) {
		this.voList = voList;
	}

	public Integer getMenuid() {
		return menuid;
	}

	public void setMenuid(Integer menuid) {
		this.menuid = menuid;
	}

	public Integer getPmenuid() {
		return pmenuid;
	}

	public void setPmenuid(Integer pmenuid) {
		this.pmenuid = pmenuid;
	}

	public String getPmenuname() {
		return pmenuname;
	}

	public void setPmenuname(String pmenuname) {
		this.pmenuname = pmenuname;
	}

	public String getMenuname() {
		return menuname;
	}

	public void setMenuname(String menuname) {
		this.menuname = menuname;
	}

	public String getEnmenuname() {
		return enmenuname;
	}

	public void setEnmenuname(String enmenuname) {
		this.enmenuname = enmenuname;
	}

	public String getMenulevel() {
		return menulevel;
	}

	public void setMenulevel(String menulevel) {
		this.menulevel = menulevel;
	}

	public String getMenulink() {
		return menulink;
	}

	public void setMenulink(String menulink) {
		this.menulink = menulink;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getSearch_menuName() {
		return search_menuName;
	}

	public void setSearch_menuName(String search_menuName) {
		this.search_menuName = search_menuName;
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

	public String getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}

}
