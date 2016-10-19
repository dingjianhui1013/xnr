/**
 * 2012-5-11 下午4:31:42
 */
package com.xnradmin.core.action;

import java.io.IOException;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.cntinker.security.MD5Encoder;
import com.cntinker.util.StringHelper;
import com.xnradmin.core.service.RoleService;
import com.xnradmin.core.service.StaffService;
import com.xnradmin.core.service.StaffStatusService;
import com.xnradmin.core.service.common.status.StatusService;
import com.xnradmin.core.service.mall.clientUser.ClientUserInfoService;
import com.xnradmin.constant.AjaxResult;
import com.xnradmin.constant.StrutsResMSG;
import com.xnradmin.po.CommonOrganization;
import com.xnradmin.po.CommonRole;
import com.xnradmin.po.CommonStaff;
import com.xnradmin.po.CommonStaffRoleRelation;
import com.xnradmin.po.CommonStaffStatus;
import com.xnradmin.po.client.ClientUserInfo;
import com.xnradmin.po.common.status.Status;
import com.xnradmin.po.wx.WXMenu;
import com.xnradmin.vo.StaffVO;

/**
 * @autohr: bin_liu
 */
@Controller
@Scope("prototype")
@Namespace("/page/staff")
// @ParentPackage("json-default")
public class StaffAction extends ParentAction {

	@Autowired
	private StaffService service;

	@Autowired
	private StaffStatusService staffStatusService;

	@Autowired
	private StatusService statusService;

	@Autowired
	private ClientUserInfoService clientUserInfoService;

	@Autowired
	private RoleService roleService;

	private String queryid;

	private String staffname;

	private String sTime;

	private String eTime;

	private List<StaffVO> voList;

	private String staffStatus;

	private StaffVO vo;

	private List<CommonStaffStatus> statusList;

	private CommonOrganization org1;

	private String orgname;

	private String login_id;

	private String staffid;

	private String mobile;

	private String email;

	private String pswd;

	private String staffno;

	private String idcard;

	private String worklife;

	private Timestamp workingtime;

	private String queryOrgid;

	private List<CommonRole> roles;

	private List<Integer> myRoleCodes;

	private List<Status> billList;

	private String oldpwd;

	private String newpwd;

	private String manager;

	private CommonStaff staff;

	private CommonOrganization queryOrg1;

	private String queryStaffStatus;

	private String queryStaffname;

	private String queryStime;

	private String queryEtime;

	private Integer director;

	private String bankInformation;
	private CommonStaff currentStaff;

	private String discount;

	private String divid;
	
	private String viewDiscount;
	
	private CommonStaff leaderStaff;
	
	private String ip;

	// @Override
	public boolean isPublic() {
		return false;
	}

	static Log log = LogFactory.getLog(StaffAction.class);

	/**
	 * 跳转到信息页
	 * 
	 * @return
	 */
	@Action(value = "info", results = {
			@Result(name = StrutsResMSG.SUCCESS, location = "/staff/info.jsp"),
			@Result(name = StrutsResMSG.FAILED, location = "/staff/info.jsp") })
	public String info() {
		// 设置状态列表
		setStatusList();
		// 设置页面信息
		setPageInfo();
		return StrutsResMSG.SUCCESS;
	}

	/**
	 * 跳转到信息页
	 * 
	 * @return
	 */
	@Action(value = "businessInfo", results = {
			@Result(name = StrutsResMSG.SUCCESS, location = "/staff/businessInfo.jsp"),
			@Result(name = StrutsResMSG.FAILED, location = "/staff/businessInfo.jsp") })
	public String businessInfo() {
		// 设置状态列表
		setStatusList();
		// 设置页面信息
		setPageInfo();
		return StrutsResMSG.SUCCESS;
	}
	
	/**
	 * 跳转到信息页
	 * 
	 * @return
	 */
	@Action(value = "supplierInfo", results = {
			@Result(name = StrutsResMSG.SUCCESS, location = "/staff/supplierInfo.jsp"),
			@Result(name = StrutsResMSG.FAILED, location = "/staff/supplierInfo.jsp") })
	public String supplierInfo() {
		// 设置状态列表
		setStatusList();
		// 设置页面信息
		setPageInfo();
		return StrutsResMSG.SUCCESS;
	}

	@Action(value = "multLookup", results = {
			@Result(name = StrutsResMSG.SUCCESS, location = "/staff/multLookup.jsp"),
			@Result(name = StrutsResMSG.FAILED, location = "/staff/multLookup.jsp") })
	public String multLookup() {
		// 设置状态列表
		setStatusList();
		// 设置页面信息
		setPageInfo();
		return StrutsResMSG.SUCCESS;
	}

	@Action(value = "lookup", results = {
			@Result(name = StrutsResMSG.SUCCESS, location = "/staff/lookup.jsp"),
			@Result(name = StrutsResMSG.FAILED, location = "/staff/lookup.jsp") })
	public String lookup() {
		// 设置状态列表
		setStatusList();
		// 设置页面信息
		setPageInfo();
		return StrutsResMSG.SUCCESS;
	}

	@Action(value = "leadStaffLookup", results = {
			@Result(name = StrutsResMSG.SUCCESS, location = "/staff/leadStaffLookup.jsp"),
			@Result(name = StrutsResMSG.FAILED, location = "/staff/leadStaffLookup.jsp") })
	public String leadStaffLookup() {
		// 设置状态列表
		setStatusList();
		// 设置页面信息
		setPageInfo();
		return StrutsResMSG.SUCCESS;
	}

	private void setPageInfo() {
		log.debug("staffAction lookup:: " + queryOrgid);
		// 设置排序
		setSort(orderField, orderDirection);
		currentStaff = super.getCurrentStaff();
		String orgid = "";
		if (this.org1 != null && this.org1.getId() != null) {
			orgid = this.org1.getId().toString();
		}
		if (!StringHelper.isNull(queryOrgid)) {
			orgid = queryOrgid;
		}
		String leaderStaffid = null;
		if(leaderStaff!=null && leaderStaff.getId()!=null)
			leaderStaffid = leaderStaff.getId().toString();
		this.voList = service.listVO(leaderStaffid,login_id, staffname, orgid, orgname,
				sTime, eTime, staffStatus, manager, director, getPageNum(),
				getNumPerPage(), orderField, orderDirection);
		this.totalCount = service.getCount(leaderStaffid,login_id, staffname, orgid, orgname,
				sTime, eTime, staffStatus, manager, director, orderField,
				orderDirection);
		log.debug("staffAction lookup orgid :: " + queryOrgid);

	}

	/**
	 * 设置排序相关项
	 */
	private void setSort(String orderField, String orderDirection) {
		HttpServletRequest request = ServletActionContext.getRequest();
		if (!StringHelper.isNull(orderField)) {
			if (orderField.equals("menu_level")) {
				request.setAttribute("menu_level", orderDirection);
				// 分页用
				request.setAttribute("orderField", "menu_level");
				request.setAttribute("orderDirection", orderDirection);
			}
		} else {
			// request.setAttribute("menu_level", "asc");
			this.orderField = "a.id";
			this.orderDirection = "desc";
		}
	}

	/**
	 * 带信息到修改页
	 * 
	 * @return String
	 */
	@Action(value = "modifyinfo", results = {
			@Result(name = StrutsResMSG.SUCCESS, location = "/staff/modify.jsp"),
			@Result(name = StrutsResMSG.FAILED, location = "/staff/modify.jsp") })
	public String modifyinfo() {

		// log.info("queryid: "+queryid);

		this.vo = service.findVoByid(queryid);
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
	@Action(value = "modify")
	public String modify() throws JSONException, IOException {

		log.debug("modif action!");
		log.debug("modify org1: " + this.org1.getId() + " | "
				+ this.org1.getOrganizationName());
		staffno = login_id;
		CommonStaff old = this.service.findByid(staffid);
		staff.setId(new Integer(staffid));
		staff.setCreateTime(new Timestamp(new Date().getTime()));
		staff.setEmail(email);
		// 登录名和员工编号一致
		staff.setLoginId(login_id);
		staff.setStaffNo(staffno);
		if (!StringHelper.isNull(mobile))
			staff.setMobile(mobile);
		staff.setOrganizationId(org1.getId());

		if (!StringHelper.isNull(worklife))
			staff.setWorkinglife(new Integer(worklife));
		if (workingtime != null)
			staff.setWorkingtime(workingtime);
		if (!StringHelper.isNull(idcard))
			staff.setIdcard(idcard);

		if (StringHelper.isNull(pswd)) {
			staff.setPassword(old.getPassword());
		} else
			staff.setPassword(MD5Encoder.encode32(pswd + "{" + login_id + "}"));

		if (!old.getLoginId().equals(staff.getLoginId())) {
			if (StringHelper.isNull(pswd)) {
				super.error("更改登录名的话必须重新填写密码");
			}
			staff.setPassword(MD5Encoder.encode32(pswd + "{" + login_id + "}"));
		}
		
		staff.setIp(ip);
		staff.setStaffName(staffname);
		staff.setStatusId(new Integer(staffStatus));
		staff.setViewDiscount(new Float(this.viewDiscount));

		if (!StringHelper.isNull(manager) && manager.equals("1")) {
			staff.setManager(true);
		}
		if (director != null && director.intValue() > 0) {
			staff.setDirector(director);
		}
		staff.setBankInformation(bankInformation);
		if (!StringHelper.isNull(discount))
			staff.setDiscount(Float.valueOf(discount));
		if (staff.getBillTime() != null && staff.getBillTime().intValue() > 0) {
			Status b = statusService.findByid(staff.getBillTime().toString());
			staff.setBillTimeName(b.getStatusName());
		}

		int res = this.service.modify(staff);

		if (res == 1) {
			super.error("已存在相同的登录名");
		} else if (res == 2) {
			super.error("该部门已存在部门总监");
		} else {
			String qstaffname = StringHelper.isNull(queryStaffname) ? ""
					: queryStaffname;
			String qstaffStatus = StringHelper.isNull(queryStaffStatus) ? ""
					: queryStaffStatus;
			String qsTime = StringHelper.isNull(queryStime) ? "" : queryStime;
			String qeTime = StringHelper.isNull(queryEtime) ? "" : queryEtime;
			String qorgid = queryOrg1 == null || queryOrg1.getId() == null ? ""
					: queryOrg1.getId().toString();
			String qorgname = queryOrg1 == null
					|| StringHelper.isNull(queryOrg1.getOrganizationName()) ? ""
					: queryOrg1.getOrganizationName();
			String flowUrl = null;

			if (StringHelper.isNull(divid))
				flowUrl = "page/staff/info.action?pageNum="
						+ super.getPageNum() + "&numPerPage="
						+ super.getNumPerPage() + "&staffname=" + qstaffname
						+ "&staffStatus=" + qstaffStatus + "&sTime=" + qsTime
						+ "&eTime=" + qeTime + "&org1.id=" + qorgid
						+ "&org1.organizationName=" + qorgname + "&divid="
						+ divid;
			else
				flowUrl = "page/staff/businessInfo.action?pageNum="
						+ super.getPageNum() + "&numPerPage="
						+ super.getNumPerPage() + "&staffname=" + qstaffname
						+ "&staffStatus=" + qstaffStatus + "&sTime=" + qsTime
						+ "&eTime=" + qeTime + "&org1.id=" + qorgid
						+ "&org1.organizationName=" + qorgname + "&divid="
						+ divid+"&queryOrgid=15";;

			// 如果是餐饮采购商就联动修改用户表
			if (staff.getOrganizationId() == 15) {
				// 修改用户表
				ClientUserInfo po = clientUserInfoService.findByProperty(
						"staffId", staff.getId());
				if (po != null && po.getId() != null) {
					po.setLoginPassWord(staff.getPassword());
					po.setNickName(staff.getStaffName());
					po.setMobile(staff.getMobile());
					po.setModifyTime(new Timestamp(System.currentTimeMillis()));
					po.setDiscount(staff.getDiscount());
					clientUserInfoService.modify(po);
				} else {
					po = new ClientUserInfo();
					po.setStaffId(staff.getId());
					po.setIswebsiteuser(1);
					po.setStatus(139);
					Status statusCode = statusService.findByid("139");
					po.setStatusName(statusCode.getStatusName());
					po.setType(154);
					Status typeCode = statusService.findByid("154");
					po.setTypeName(typeCode.getStatusName());
					po.setMobile(staff.getMobile());
					po.setLoginPassWord(staff.getPassword());
					po.setNickName(staff.getStaffName());
					po.setCreateTime(new Timestamp(System.currentTimeMillis()));
					po.setLastLoginTime(new Timestamp(System
							.currentTimeMillis()));
					po.setModifyTime(new Timestamp(System.currentTimeMillis()));
					po.setDiscount(staff.getDiscount());
					clientUserInfoService.saveBusinessUser(po);
				}
			}
			//super.success(null, AjaxResult.CALL_BACK_TYPE_FORWARD, "", flowUrl);

			if (StringHelper.isNull(divid))
				super.success(null, AjaxResult.CALL_BACK_TYPE_FORWARD, "", flowUrl);
			else 
				
				super.success(null, AjaxResult.CALL_BACK_TYPE_FORWARD, "", flowUrl);
			
		}

		return null;
	}

	@Action(value = "del")
	public String del() throws IOException, JSONException {

		String queryid = ServletActionContext.getRequest().getParameter(
				"queryid");
		service.del(queryid);
		super.success(null, null, "staffInfo", null);
		return null;
	}

	@Action(value = "modifyStatus")
	public String modifyStatus() throws IOException {
		String queryid = ServletActionContext.getRequest().getParameter(
				"queryid");
		service.modifyStatus(queryid, queryStaffStatus);

		if (StringHelper.isNull(divid))
			super.success(null, null, "staffInfo", null);
		else {
			if (divid.equals("businessStaffInfo"))
				super.success(null, null, "businessStaffInfo", null);
		}

		super.success(null, null, "businessStaffInfo", null);
		return null;
	}

	@Action(value = "addinfo", results = {
			@Result(name = StrutsResMSG.SUCCESS, location = "/staff/add_dialog.jsp"),
			@Result(name = StrutsResMSG.FAILED, location = "/staff/add_dialog.jsp") })
	public String addInfo() {
		setStatusList();
		currentStaff = super.getCurrentStaff();
		return StrutsResMSG.SUCCESS;
	}

	/**
	 * 设置状态列表
	 */
	private void setStatusList() {
		this.statusList = staffStatusService.listAll();
		this.billList = statusService.find(CommonStaff.class, "staffBillTime");
	}

	/**
	 * 保存对象接口
	 * 
	 * @return String
	 * @throws IOException
	 * @throws JSONException
	 */
	@Action(value = "add")
	public String save() throws IOException, JSONException {

		staff.setCreateTime(new Timestamp(new Date().getTime()));
		staff.setEmail(email);
		// 登录名和员工编号一致
		staff.setLoginId(staffno);
		staff.setStaffNo(staffno);

		staff.setMobile(mobile);
		staff.setOrganizationId(org1.getId());

		if (!StringHelper.isNull(worklife))
			staff.setWorkinglife(new Integer(worklife));
		staff.setWorkingtime(workingtime);
		staff.setIdcard(idcard);
		staff.setViewDiscount(new Float(viewDiscount));
		
		staff.setIp(ip);
		staff.setStaffName(staffname);
		staff.setStatusId(new Integer(staffStatus));
		staff.setPassword(MD5Encoder.encode32(pswd + "{" + staffno + "}"));
		if (!StringHelper.isNull(manager) && manager.equals("1")) {
			staff.setManager(true);
		}
		if (director != null && director.intValue() > 0) {
			staff.setDirector(director);
		}
		staff.setBankInformation(bankInformation);
		if (!StringHelper.isNull(discount))
			staff.setDiscount(Float.valueOf(discount));

		if (staff.getBillTime() != null && staff.getBillTime().intValue() > 0) {
			Status b = statusService.findByid(staff.getBillTime().toString());
			staff.setBillTimeName(b.getStatusName());
		}

		int res = service.save(staff);
		if (res == 1) {
			super.error("已存在相同的登录名");
		} else if (res == 2) {
			super.error("该部门已存在部门总监");
		} else if (res == 3) {
			super.error("未正确添加人员");
		} else {
			// 如果是餐饮采购商就添加到用户表
			if (staff.getOrganizationId() == 15) {
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
				po.setMobile(staff.getMobile());
				po.setLoginPassWord(staff.getPassword());
				po.setNickName(staff.getStaffName());
				po.setCreateTime(new Timestamp(System.currentTimeMillis()));
				po.setLastLoginTime(new Timestamp(System.currentTimeMillis()));
				po.setModifyTime(new Timestamp(System.currentTimeMillis()));
				po.setDiscount(staff.getDiscount());
				clientUserInfoService.saveBusinessUser(po);
			}
			if (StringHelper.isNull(divid))
				super.success(null, null, "staffInfo", null);
			else {
				if (divid.equals("businessStaffInfo"))
					super.success(null, null, "businessStaffInfo", null);
			}
		}

		return null;
	}

	/**
	 * 带信息到授权页面
	 * 
	 * @return String
	 */
	@Action(value = "anthinfo", results = {
			@Result(name = StrutsResMSG.SUCCESS, location = "/staff/staffrole.jsp"),
			@Result(name = StrutsResMSG.FAILED, location = "/staff/staffrole.jsp") })
	public String anthinfo() {

		roles = roleService.getValidRoles();
		myRoleCodes = roleService.getRoleId4User(new Integer(queryid));

		return StrutsResMSG.SUCCESS;
	}

	/**
	 * 更新对象接口
	 * 
	 * @return String
	 * @throws JSONException
	 * @throws IOException
	 */
	@Action(value = "auth")
	public String auth() throws JSONException, IOException {

		roleService.removeRoles4userId(new Integer(queryid));

		for (Integer authid : myRoleCodes) {
			CommonStaffRoleRelation crp = new CommonStaffRoleRelation();
			crp.setStaffId(new Integer(queryid));
			crp.setRoleId(authid);
			roleService.saveStaffRoleRelation(crp);
		}

		String qstaffname = StringHelper.isNull(queryStaffname) ? ""
				: queryStaffname;
		String qstaffStatus = StringHelper.isNull(queryStaffStatus) ? ""
				: queryStaffStatus;
		String qsTime = StringHelper.isNull(queryStime) ? "" : queryStime;
		String qeTime = StringHelper.isNull(queryEtime) ? "" : queryEtime;
		String qorgid = queryOrg1 == null || queryOrg1.getId() == null ? ""
				: queryOrg1.getId().toString();
		String qorgname = queryOrg1 == null
				|| StringHelper.isNull(queryOrg1.getOrganizationName()) ? ""
				: queryOrg1.getOrganizationName();
		String flowUrl = "page/staff/info.action?pageNum=" + super.getPageNum()
				+ "&numPerPage=" + super.getNumPerPage() + "&staffname="
				+ qstaffname + "&staffStatus=" + qstaffStatus + "&sTime="
				+ qsTime + "&eTime=" + qeTime + "&org1.id=" + qorgid
				+ "&org1.organizationName=" + qorgname;
		super.success(null, AjaxResult.CALL_BACK_TYPE_FORWARD, "", flowUrl);

		// super.success(null,AjaxResult.CALL_BACK_TYPE_CLOSECURRENT,"staffInfo",
		// null);

		return null;
	}

	@Action(value = "changepwd")
	public String changepwd() throws JSONException, IOException {

		CommonStaff currentStaff = super.getCurrentStaff();

		int res = this.service
				.changePwd(currentStaff, this.oldpwd, this.newpwd);
		if (res == 0) {
			super.success(null, AjaxResult.CALL_BACK_TYPE_CLOSECURRENT,
					"staffInfo", null);
		}
		if (res == 1)
			super.error("当前登录用户信息错误");
		if (res == 2)
			super.error("旧密码不正确");

		return null;
	}

	public String getStaffno() {
		return staffno;
	}

	public void setStaffno(String staffno) {
		this.staffno = staffno;
	}

	public StaffStatusService getStaffStatusService() {
		return staffStatusService;
	}

	public void setStaffStatusService(StaffStatusService staffStatusService) {
		this.staffStatusService = staffStatusService;
	}

	public String getLogin_id() {
		return login_id;
	}

	public void setLogin_id(String login_id) {
		this.login_id = login_id;
	}

	public String getStaffid() {
		return staffid;
	}

	public void setStaffid(String staffid) {
		this.staffid = staffid;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPswd() {
		return pswd;
	}

	public void setPswd(String pswd) {
		this.pswd = pswd;
	}

	public CommonOrganization getOrg1() {
		return org1;
	}

	public void setOrg1(CommonOrganization org1) {
		this.org1 = org1;
	}

	public String getQueryid() {
		return queryid;
	}

	public void setQueryid(String queryid) {
		this.queryid = queryid;
	}

	public StaffVO getVo() {
		return vo;
	}

	public void setVo(StaffVO vo) {
		this.vo = vo;
	}

	public List<CommonStaffStatus> getStatusList() {
		return statusList;
	}

	public void setStatusList(List<CommonStaffStatus> statusList) {
		this.statusList = statusList;
	}

	public String getStaffname() {
		return staffname;
	}

	public void setStaffname(String staffname) {
		this.staffname = staffname;
	}

	public String getsTime() {
		return sTime;
	}

	public void setsTime(String sTime) {
		this.sTime = sTime;
	}

	public String geteTime() {
		return eTime;
	}

	public void seteTime(String eTime) {
		this.eTime = eTime;
	}

	public List<StaffVO> getVoList() {
		return voList;
	}

	public void setVoList(List<StaffVO> voList) {
		this.voList = voList;
	}

	public String getStaffStatus() {
		return staffStatus;
	}

	public void setStaffStatus(String staffStatus) {
		this.staffStatus = staffStatus;
	}

	public List<CommonRole> getRoles() {
		return roles;
	}

	public void setRoles(List<CommonRole> roles) {
		this.roles = roles;
	}

	public List<Integer> getMyRoleCodes() {
		return myRoleCodes;
	}

	public void setMyRoleCodes(List<Integer> myRoleCodes) {
		this.myRoleCodes = myRoleCodes;
	}

	public String getNewpwd() {
		return newpwd;
	}

	public void setNewpwd(String newpwd) {
		this.newpwd = newpwd;
	}

	public String getOldpwd() {
		return oldpwd;
	}

	public void setOldpwd(String oldpwd) {
		this.oldpwd = oldpwd;
	}

	public String getIdcard() {
		return idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	public String getWorklife() {
		return worklife;
	}

	public void setWorklife(String worklife) {
		this.worklife = worklife;
	}

	public Timestamp getWorkingtime() {
		return workingtime;
	}

	public void setWorkingtime(Timestamp workingtime) {
		this.workingtime = workingtime;
	}

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	public String getOrgname() {
		return orgname;
	}

	public void setOrgname(String orgname) {
		this.orgname = orgname;
	}

	public CommonStaff getStaff() {
		return staff;
	}

	public void setStaff(CommonStaff staff) {
		this.staff = staff;
	}

	public String getQueryOrgid() {
		return queryOrgid;
	}

	public void setQueryOrgid(String queryOrgid) {
		this.queryOrgid = queryOrgid;
	}

	public CommonOrganization getQueryOrg1() {
		return queryOrg1;
	}

	public void setQueryOrg1(CommonOrganization queryOrg1) {
		this.queryOrg1 = queryOrg1;
	}

	public String getQueryStaffStatus() {
		return queryStaffStatus;
	}

	public void setQueryStaffStatus(String queryStaffStatus) {
		this.queryStaffStatus = queryStaffStatus;
	}

	public String getQueryStaffname() {
		return queryStaffname;
	}

	public void setQueryStaffname(String queryStaffname) {
		this.queryStaffname = queryStaffname;
	}

	public String getQueryStime() {
		return queryStime;
	}

	public void setQueryStime(String queryStime) {
		this.queryStime = queryStime;
	}

	public String getQueryEtime() {
		return queryEtime;
	}

	public void setQueryEtime(String queryEtime) {
		this.queryEtime = queryEtime;
	}

	public Integer getDirector() {
		return director;
	}

	public void setDirector(Integer director) {
		this.director = director;
	}

	public CommonStaff getCurrentStaff() {
		return currentStaff;
	}

	public void setCurrentStaff(CommonStaff currentStaff) {
		this.currentStaff = currentStaff;
	}

	public String getBankInformation() {
		return bankInformation;
	}

	public void setBankInformation(String bankInformation) {
		this.bankInformation = bankInformation;
	}

	public String getDiscount() {
		return discount;
	}

	public void setDiscount(String discount) {
		this.discount = discount;
	}

	public String getDivid() {
		return divid;
	}

	public void setDivid(String divid) {
		this.divid = divid;
	}

	public List<Status> getBillList() {
		return billList;
	}

	public void setBillList(List<Status> billList) {
		this.billList = billList;
	}

	public String getViewDiscount() {
		return viewDiscount;
	}

	public void setViewDiscount(String viewDiscount) {
		this.viewDiscount = viewDiscount;
	}

	public CommonStaff getLeaderStaff() {
		return leaderStaff;
	}

	public void setLeaderStaff(CommonStaff leaderStaff) {
		this.leaderStaff = leaderStaff;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

}
