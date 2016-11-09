/**
 *2012-5-10 下午3:52:29
 */
package com.xnradmin.core.action.mall.seting;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.cntinker.util.StringHelper;
import com.xnradmin.constant.AjaxResult;
import com.xnradmin.constant.StrutsResMSG;
import com.xnradmin.core.action.ParentAction;
import com.xnradmin.core.service.StaffService;
import com.xnradmin.core.service.common.status.StatusService;
import com.xnradmin.core.service.mall.seting.LogisticsCompanyService;
import com.xnradmin.core.service.mall.seting.PrimaryConfigurationService;
import com.xnradmin.po.CommonStaff;
import com.xnradmin.po.mall.seting.LogisticsCompany;
import com.xnradmin.po.mall.seting.PrimaryConfiguration;
import com.xnradmin.vo.StaffVO;
import com.xnradmin.vo.mall.SetingVO;

/**
 * @autohr: xiang_liu
 * 
 */
@Controller
@Scope("prototype")
@Namespace("/page/wx/admin/seting/logisticsCompany")
@ParentPackage("json-default")
public class LogisticsCompanyAction extends ParentAction {

	@Autowired
	private LogisticsCompanyService logisticsCompanyService;

	@Autowired
	private PrimaryConfigurationService primaryConfigurationService;
	
	@Autowired
	private StatusService statusService;

	@Autowired
	private StaffService staffService;

	private String logisticsCompanyId;
	private String sortId; // 排序Id
	private String companyName; // 公司名称
	private String companyUrl; // 公司网址
	private String primaryConfigurationId; // 对应商城ID
	private String staffId; // 隶属用户ID
	private String createTime; // 建立时间
	private String createStartTime; // 建立开始时间
	private String createEndTime; // 建立结束时间
	private String createStaffId; // 建立人
	private String modifyTime; // 修改时间
	private String modifyStartTime; // 修改开始时间
	private String modifyEndTime; // 修改结束时间
	private String modifyStaffId; // 修改人
	private CommonStaff currentStaff;
	private List<SetingVO> voList;
	private List<PrimaryConfiguration> primaryConfigurationList;
	private List<StaffVO> staffList;
	private SetingVO setingVO;
	private LogisticsCompany logisticsCompany;
	private PrimaryConfiguration primaryConfiguration;
	
	public String getLogisticsCompanyId() {
		return logisticsCompanyId;
	}

	public void setLogisticsCompanyId(String logisticsCompanyId) {
		this.logisticsCompanyId = logisticsCompanyId;
	}

	public String getSortId() {
		return sortId;
	}

	public void setSortId(String sortId) {
		this.sortId = sortId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyUrl() {
		return companyUrl;
	}

	public void setCompanynUrl(String companyUrl) {
		this.companyUrl = companyUrl;
	}

	public String getPrimaryConfigurationId() {
		return primaryConfigurationId;
	}

	public void setPrimaryConfigurationId(String primaryConfigurationId) {
		this.primaryConfigurationId = primaryConfigurationId;
	}

	public String getStaffId() {
		return staffId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
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

	public String getCreateStaffId() {
		return createStaffId;
	}

	public void setCreateStaffId(String createStaffId) {
		this.createStaffId = createStaffId;
	}

	public String getModifyTime() {
		return modifyTime;
	}

	public String getModifyStartTime() {
		return modifyStartTime;
	}

	public void setModifyStartTime(String modifyStartTime) {
		this.modifyStartTime = modifyStartTime;
	}

	public String getModifyEndTime() {
		return modifyEndTime;
	}

	public void setModifyEndTime(String modifyEndTime) {
		this.modifyEndTime = modifyEndTime;
	}

	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
	}

	public String getModifyStaffId() {
		return modifyStaffId;
	}

	public void setModifyStaffId(String modifyStaffId) {
		this.modifyStaffId = modifyStaffId;
	}

	public CommonStaff getCurrentStaff() {
		return currentStaff;
	}

	public void setCurrentStaff(CommonStaff currentStaff) {
		this.currentStaff = currentStaff;
	}

	public List<SetingVO> getVoList() {
		return voList;
	}

	public void setVoList(List<SetingVO> voList) {
		this.voList = voList;
	}

	public SetingVO getSetingVO() {
		return setingVO;
	}

	public void setSetingVO(SetingVO setingVO) {
		this.setingVO = setingVO;
	}

	public LogisticsCompany getLogisticsCompany() {
		return logisticsCompany;
	}

	public void setLogisticsCompany(
			LogisticsCompany logisticsCompany) {
		this.logisticsCompany = logisticsCompany;
	}

	public PrimaryConfiguration getPrimaryConfiguration() {
		return primaryConfiguration;
	}

	public void setPrimaryConfiguration(PrimaryConfiguration primaryConfiguration) {
		this.primaryConfiguration = primaryConfiguration;
	}

	public void setCompanyUrl(String companyUrl) {
		this.companyUrl = companyUrl;
	}

	public List<PrimaryConfiguration> getPrimaryConfigurationList() {
		return primaryConfigurationList;
	}

	public void setPrimaryConfigurationList(
			List<PrimaryConfiguration> primaryConfigurationList) {
		this.primaryConfigurationList = primaryConfigurationList;
	}

	public List<StaffVO> getStaffList() {
		return staffList;
	}

	public void setStaffList(List<StaffVO> staffList) {
		this.staffList = staffList;
	}

	@Override
	public boolean isPublic() {
		return false;
	};

	static Log log = LogFactory.getLog(LogisticsCompanyAction.class);

	/**
	 * 跳转到信息页。。
	 * 
	 * @return
	 */
	@Action(value = "info", results = {
			@Result(name = StrutsResMSG.SUCCESS, location = "/wx/admin/seting/logisticsCompany/info.jsp"),
			@Result(name = StrutsResMSG.FAILED, location = "/wx/admin/seting/logisticsCompany/info.jsp") })
	public String info() {
		setPageInfo();
		return StrutsResMSG.SUCCESS;
	}

	private void setPageInfo() {
		// 设置排序
		setPrimaryConfigurationList();
		setStaffList();
		this.voList = logisticsCompanyService.listVO(companyName, staffId,
				primaryConfigurationId, createStartTime, createEndTime, createStaffId,
				modifyStartTime, modifyEndTime, modifyStaffId, getPageNum(),
				getNumPerPage(), orderField, orderDirection);

		this.totalCount = logisticsCompanyService.getCount(companyName,
				staffId, primaryConfigurationId, createStartTime, createEndTime,
				createStaffId, modifyStartTime, modifyEndTime, modifyStaffId);
	}


	/**
	 * 加载所有商城
	 */
	private void setPrimaryConfigurationList() {
		this.primaryConfigurationList = primaryConfigurationService.listAll();
	}
	
	/*
	 * 加载所有用户
	 */
	private void setStaffList() {
		this.staffList = staffService.listVO("","","", "", "", "", "", "", "", 0, 0,
				0, "", "");
	}
	
	@Action(value = "addinfo", results = {
			@Result(name = StrutsResMSG.SUCCESS, location = "/wx/admin/seting/logisticsCompany/add.jsp"),
			@Result(name = StrutsResMSG.FAILED, location = "/wx/admin/seting/logisticsCompany/add.jsp") })
	public String addInfo() {
		// 加载状态与类型
		currentStaff = super.getCurrentStaff();
		return StrutsResMSG.SUCCESS;
	}

	/**
	 * 保存对象接口
	 * 
	 * @return String
	 * @throws Exception
	 */
	@Action(value = "add", results = { @Result(name = StrutsResMSG.SUCCESS, type = "plainText") })
	public String add() throws Exception {
		// 取得当前登录人信息
		currentStaff = super.getCurrentStaff();
		LogisticsCompany logistics = new LogisticsCompany();
		logistics.setCompanyName(companyName);
		logistics.setCompanyUrl(companyUrl);
		logistics.setSortId(sortId);
		if (primaryConfiguration != null) {
			logistics.setPrimaryConfigurationId(primaryConfiguration.getId());
		}
		logistics.setStaffId(staffId);
		logistics.setCreateStaffId(currentStaff.getId());
		logistics.setCreateTime(new Timestamp(System.currentTimeMillis()));
		logistics.setModifyStaffId(currentStaff.getId());
		logistics.setModifyTime(new Timestamp(System.currentTimeMillis()));
		logisticsCompanyService.save(logistics);
		super.success(null, AjaxResult.CALL_BACK_TYPE_CLOSECURRENT, "logisticsCompany",
				null);
		return null;
	}

	/**
	 * 带信息到修改页
	 * 
	 * @return String
	 */
	@Action(value = "modifyinfo", results = {
			@Result(name = StrutsResMSG.SUCCESS, location = "/wx/admin/seting/logisticsCompany/modify.jsp"),
			@Result(name = StrutsResMSG.FAILED, location = "/wx/admin/seting/logisticsCompany/modify.jsp") })
	public String modifyinfo() {
		setPrimaryConfigurationList();
		setStaffList();
		LogisticsCompany logistics = new LogisticsCompany();
		logistics = logisticsCompanyService.findByid(logisticsCompanyId);
		this.companyName = logistics.getCompanyName();
		this.companyUrl = logistics.getCompanyUrl();
		this.sortId = logistics.getSortId();
		if (logistics.getPrimaryConfigurationId() != null) {
			this.primaryConfigurationId = logistics.getPrimaryConfigurationId()
					.toString();
		}
		this.staffId = logistics.getStaffId();
		this.createStaffId = logistics.getCreateStaffId().toString();
		this.modifyStaffId = logistics.getModifyStaffId().toString();
		if (logistics.getCreateTime() != null) {
			this.createStartTime = logistics.getCreateTime().toString();
		}
		if (logistics.getModifyTime() != null) {
			this.modifyStartTime = logistics.getModifyTime().toString();
		}
		return StrutsResMSG.SUCCESS;
	}

	/**
	 * 更新对象接口
	 * 
	 * @return String
	 * @throws Exception
	 */
	@Action(value = "modify", results = { @Result(name = StrutsResMSG.SUCCESS, type = "plainText") })
	public String modify() throws Exception {
		log.debug("modif action!");
		log.debug("modify logisticsCompany: " + logisticsCompanyId);
		// 取得当前登录人信息
		currentStaff = super.getCurrentStaff();
		LogisticsCompany oldpo = logisticsCompanyService
				.findByid(logisticsCompanyId);
		LogisticsCompany po = new LogisticsCompany();
		po.setId(Integer.parseInt(logisticsCompanyId));
		if (!StringHelper.isNull(companyName)) {
			po.setCompanyName(companyName);
		} else {
			po.setCompanyName(oldpo.getCompanyName());
		}
		if (!StringHelper.isNull(companyUrl)) {
			po.setCompanyUrl(companyUrl);
		} else {
			po.setCompanyUrl(oldpo.getCompanyUrl());
		}
		if (!StringHelper.isNull(sortId)) {
			po.setSortId(sortId);
		} else {
			po.setSortId(oldpo.getSortId());
		}
		if (primaryConfiguration!=null) {
			po.setPrimaryConfigurationId(primaryConfiguration.getId());
		} else {
			po.setPrimaryConfigurationId(oldpo.getPrimaryConfigurationId());
		}
		if (!StringHelper.isNull(staffId)) {
			po.setStaffId(staffId);
		} else {
			po.setStaffId(oldpo.getStaffId());
		}
		po.setModifyTime(new Timestamp(System.currentTimeMillis()));
		po.setModifyStaffId(currentStaff.getId());
		po.setCreateStaffId(oldpo.getCreateStaffId());
		po.setCreateTime(oldpo.getCreateTime());
		int res = this.logisticsCompanyService.modify(po);
		super.success(null, AjaxResult.CALL_BACK_TYPE_CLOSECURRENT, "logisticsCompany",
				null);
		return null;
	}

	@Action(value = "del", results = { @Result(name = StrutsResMSG.SUCCESS, type = "plainText") })
	public String del() throws IOException, JSONException {
		logisticsCompanyService
				.removeLogisticsCompanyId(logisticsCompanyId);
		super.success(null, AjaxResult.CALL_BACK_TYPE_CLOSECURRENT, "logisticsCompany",
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
		super.toJsonArray(logisticsCompanyService.listAll());
		return null;
	}
}
