/**
 *2012-5-10 下午3:52:29
 */
package com.xnradmin.core.action.mall.seting;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

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
import com.xnradmin.constant.AjaxResult;
import com.xnradmin.constant.StrutsResMSG;
import com.xnradmin.core.action.ParentAction;
import com.xnradmin.core.service.StaffService;
import com.xnradmin.core.service.common.status.StatusService;
import com.xnradmin.core.service.mall.seting.PrimaryConfigurationService;
import com.xnradmin.po.CommonStaff;
import com.xnradmin.po.common.status.Status;
import com.xnradmin.po.mall.seting.PrimaryConfiguration;
import com.xnradmin.vo.StaffVO;
import com.xnradmin.vo.mall.SetingVO;

/**
 * @autohr: xiang_liu
 * 
 */
@Controller
@Scope("prototype")
@Namespace("/page/wx/admin/seting/primaryConfiguration")
@ParentPackage("json-default")
public class PrimaryConfigurationAction extends ParentAction {

	@Autowired
	private PrimaryConfigurationService primaryConfigurationService;

	@Autowired
	private StatusService statusService;

	@Autowired
	private StaffService staffService;

	private String primaryConfigurationId;
	private String mallDemoUrl; // 商城预览地址
	private String mallName; // 商城名称
	private String mallLogo; // 商城logo图
	private String mallBackground; // 商城背景
	private File mallLogoFile; // 商城logo图
	private String mallLogoFileFileName;
	private String mallLogoFileContentType;
	private File mallBackgroundFile; // 商城背景
	private String mallBackgroundFileFileName;
	private String mallBackgroundFileContentType;
	private String mallBackgroundStatus; // 是否开启背景
	private String address; // 联系地址
	private String mobile; // 联系电话
	private String code; // 邮编
	private String email; // 邮箱地址
	private String mallIntroduction; // 商城介绍
	private String mallStatus; // 商城状态
	private String staffId; // 隶属用户ID
	private String createTime; // 建立时间
	private String createStartTime; // 建立开始时间
	private String createEndTime; // 建立结束时间
	private String createStaffId; // 建立人
	private String modifyTime; // 修改时间
	private String modifyStartTime; // 修改开始时间
	private String modifyEndTime; // 修改结束时间
	private String modifyStaffId; // 修改人
	private List<Status> mallBackgroundStatusList;
	private List<Status> mallStatusList;
	private List<StaffVO> staffList;
	private CommonStaff currentStaff;
	private List<SetingVO> voList;
	private SetingVO setingVO;
	private PrimaryConfiguration primaryConfiguration;
	private Status status;
	private String logoSavePath = "/themes/mall/logo/";
	private String backgroundSavePath = "/themes/mall/background/";

	public String getPrimaryConfigurationId() {
		return primaryConfigurationId;
	}

	public void setPrimaryConfigurationId(String primaryConfigurationId) {
		this.primaryConfigurationId = primaryConfigurationId;
	}

	public String getMallDemoUrl() {
		return mallDemoUrl;
	}

	public void setMallDemoUrl(String mallDemoUrl) {
		this.mallDemoUrl = mallDemoUrl;
	}

	public String getMallName() {
		return mallName;
	}

	public void setMallName(String mallName) {
		this.mallName = mallName;
	}

	public String getMallLogo() {
		return mallLogo;
	}

	public void setMallLogo(String mallLogo) {
		this.mallLogo = mallLogo;
	}

	public String getMallBackground() {
		return mallBackground;
	}

	public void setMallBackground(String mallBackground) {
		this.mallBackground = mallBackground;
	}

	public File getMallLogoFile() {
		return mallLogoFile;
	}

	public void setMallLogoFile(File mallLogoFile) {
		this.mallLogoFile = mallLogoFile;
	}

	public File getMallBackgroundFile() {
		return mallBackgroundFile;
	}

	public void setMallBackgroundFile(File mallBackgroundFile) {
		this.mallBackgroundFile = mallBackgroundFile;
	}

	public String getMallBackgroundStatus() {
		return mallBackgroundStatus;
	}

	public void setMallBackgroundStatus(String mallBackgroundStatus) {
		this.mallBackgroundStatus = mallBackgroundStatus;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMallIntroduction() {
		return mallIntroduction;
	}

	public void setMallIntroduction(String mallIntroduction) {
		this.mallIntroduction = mallIntroduction;
	}

	public String getMallStatus() {
		return mallStatus;
	}

	public void setMallStatus(String mallStatus) {
		this.mallStatus = mallStatus;
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

	public List<Status> getMallBackgroundStatusList() {
		return mallBackgroundStatusList;
	}

	public void setMallBackgroundStatusList(
			List<Status> mallBackgroundStatusList) {
		this.mallBackgroundStatusList = mallBackgroundStatusList;
	}

	public List<Status> getMallStatusList() {
		return mallStatusList;
	}

	public void setMallStatusList(List<Status> mallStatusList) {
		this.mallStatusList = mallStatusList;
	}

	public List<StaffVO> getStaffList() {
		return staffList;
	}

	public void setStaffList(List<StaffVO> staffList) {
		this.staffList = staffList;
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

	public PrimaryConfiguration getPrimaryConfiguration() {
		return primaryConfiguration;
	}

	public void setPrimaryConfiguration(
			PrimaryConfiguration primaryConfiguration) {
		this.primaryConfiguration = primaryConfiguration;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getMallLogoFileFileName() {
		return mallLogoFileFileName;
	}

	public void setMallLogoFileFileName(String mallLogoFileFileName) {
		this.mallLogoFileFileName = mallLogoFileFileName;
	}

	public String getMallLogoFileContentType() {
		return mallLogoFileContentType;
	}

	public void setMallLogoFileContentType(String mallLogoFileContentType) {
		this.mallLogoFileContentType = mallLogoFileContentType;
	}

	public String getMallBackgroundFileFileName() {
		return mallBackgroundFileFileName;
	}

	public void setMallBackgroundFileFileName(String mallBackgroundFileFileName) {
		this.mallBackgroundFileFileName = mallBackgroundFileFileName;
	}

	public String getMallBackgroundFileContentType() {
		return mallBackgroundFileContentType;
	}

	public void setMallBackgroundFileContentType(String mallBackgroundFileContentType) {
		this.mallBackgroundFileContentType = mallBackgroundFileContentType;
	}

	public void setLogoSavePath(String logoSavePath) {
		this.logoSavePath = logoSavePath;
	}

	public void setBackgroundSavePath(String backgroundSavePath) {
		this.backgroundSavePath = backgroundSavePath;
	}

	@Override
	public boolean isPublic() {
		return false;
	};

	static Log log = LogFactory.getLog(PrimaryConfigurationAction.class);

	/**
	 * 跳转到信息页。。
	 * 
	 * @return
	 */
	@Action(value = "info", results = {
			@Result(name = StrutsResMSG.SUCCESS, location = "/wx/admin/seting/primaryConfiguration/info.jsp"),
			@Result(name = StrutsResMSG.FAILED, location = "/wx/admin/seting/primaryConfiguration/info.jsp") })
	public String info() {
		setPageInfo();
		return StrutsResMSG.SUCCESS;
	}

	/**
	 * 跳转到信息页。。
	 * 
	 * @return
	 */
	@Action(value = "lookup", results = {
			@Result(name = StrutsResMSG.SUCCESS, location = "/wx/admin/seting/primaryConfiguration/lookup.jsp"),
			@Result(name = StrutsResMSG.FAILED, location = "/wx/admin/seting/primaryConfiguration/lookup.jsp") })
	public String lookup() {
		setPageInfo();
		return StrutsResMSG.SUCCESS;
	}
	
	private void setPageInfo() {
		// 设置排序
		setMallStatusList();
		setMallBackgroundStatus();
		setStaffList();
		this.voList = primaryConfigurationService.listVO(mallName, staffId,
				mallStatus, createStartTime, createEndTime, createStaffId,
				modifyStartTime, modifyEndTime, modifyStaffId, getPageNum(),
				getNumPerPage(), orderField, orderDirection);

		this.totalCount = primaryConfigurationService.getCount(mallName,
				staffId, mallStatus, createStartTime, createEndTime,
				createStaffId, modifyStartTime, modifyEndTime, modifyStaffId);
	}

	/**
	 * 加载商城状态
	 */
	private void setMallStatusList() {
		this.mallStatusList = statusService.find(PrimaryConfiguration.class,
				"mallStatus");
	}

	/**
	 * 加载背景状态
	 */
	private void setMallBackgroundStatus() {
		this.mallBackgroundStatusList = statusService.find(
				PrimaryConfiguration.class, "mallBackgroundStatus");
	}

	/*
	 * 加载所有用户
	 */
	private void setStaffList() {
		this.staffList = staffService.listVO("","","", "", "", "", "", "", "", 0, 0,
				0, "", "");
	}

	/*
	 * 加载LOGO上传路径
	 */
	public String getLogoSavePath() throws Exception {
//		return "C:/work/eclipse-jee-kepler-R-win32-x86_64/eclipse/javaworkspace/xnradmin/xnradmin-web/src/main/webapp/themes/mall/logo";
		return ServletActionContext.getServletContext().getRealPath("")
				+ logoSavePath;
	}
	
	/*
	 * 加载背景上传路径
	 */
	public String getBackgroundSavePath() throws Exception {
//		return "C:/work/eclipse-jee-kepler-R-win32-x86_64/eclipse/javaworkspace/xnradmin/xnradmin-web/src/main/webapp/themes/mall/background";
		return ServletActionContext.getServletContext().getRealPath("")
				+ backgroundSavePath;
	}

	@Action(value = "addinfo", results = {
			@Result(name = StrutsResMSG.SUCCESS, location = "/wx/admin/seting/primaryConfiguration/add.jsp"),
			@Result(name = StrutsResMSG.FAILED, location = "/wx/admin/seting/primaryConfiguration/add.jsp") })
	public String addInfo() {
		// 加载状态与类型
		setMallStatusList();
		setMallBackgroundStatus();
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
		String newMallLogo = "";
		if (mallLogoFile != null) {
			mallLogoFileFileName = UUID.randomUUID().toString() + mallLogoFileFileName;
			newMallLogo = getLogoSavePath() + "/" +mallLogoFileFileName;
			FileOutputStream fos = new FileOutputStream(newMallLogo);
			FileInputStream fis = new FileInputStream(mallLogoFile);
			byte[] buffer = new byte[1024];
			int len = 0;
			while ((len = fis.read(buffer)) > 0) {
				fos.write(buffer, 0, len);
			}
			fos.close();
			fis.close();
		}

		String newMallBackground = "";
		if (mallBackgroundFile != null) {
			mallBackgroundFileFileName = UUID.randomUUID().toString()
					+ mallBackgroundFileFileName;
			newMallBackground = getBackgroundSavePath() + "/" +mallBackgroundFileFileName;
			FileOutputStream fos = new FileOutputStream(newMallBackground);
			FileInputStream fis = new FileInputStream(mallBackgroundFile);
			byte[] buffer = new byte[1024];
			int len = 0;
			while ((len = fis.read(buffer)) > 0) {
				fos.write(buffer, 0, len);
			}
			fos.close();
			fis.close();
		}

		// 取得当前登录人信息
		currentStaff = super.getCurrentStaff();
		PrimaryConfiguration primary = new PrimaryConfiguration();
		primary.setMallName(mallName);
		primary.setMallDemoUrl(mallDemoUrl);
		if(!StringHelper.isNull(mallLogoFileFileName)){
			primary.setMallLogo(logoSavePath+mallLogoFileFileName);
		}
		if(!StringHelper.isNull(mallBackgroundFileFileName)){
			primary.setMallBackground(backgroundSavePath+mallBackgroundFileFileName);
		}
		if (mallBackgroundStatus != null) {
			primary.setMallBackgroundStatus(Integer
					.parseInt(mallBackgroundStatus));
		}
		primary.setAddress(address);
		primary.setMobile(mobile);
		primary.setCode(code);
		primary.setEmail(email);
		primary.setMallIntroduction(mallIntroduction);
		if (mallStatus != null) {
			primary.setMallStatus(Integer.parseInt(mallStatus));
		}
		primary.setStaffId(staffId);
		primary.setCreateStaffId(currentStaff.getId());
		primary.setCreateTime(new Timestamp(System.currentTimeMillis()));
		primary.setModifyStaffId(currentStaff.getId());
		primary.setModifyTime(new Timestamp(System.currentTimeMillis()));
		primaryConfigurationService.save(primary);
		super.success(null, AjaxResult.CALL_BACK_TYPE_CLOSECURRENT, "primaryConfiguration",
				null);
		return null;
	}

	/**
	 * 带信息到修改页
	 * 
	 * @return String
	 */
	@Action(value = "modifyinfo", results = {
			@Result(name = StrutsResMSG.SUCCESS, location = "/wx/admin/seting/primaryConfiguration/modify.jsp"),
			@Result(name = StrutsResMSG.FAILED, location = "/wx/admin/seting/primaryConfiguration/modify.jsp") })
	public String modifyinfo() {
		setMallStatusList();
		setMallBackgroundStatus();
		PrimaryConfiguration primary = new PrimaryConfiguration();
		primary = primaryConfigurationService.findByid(primaryConfigurationId);
		this.mallDemoUrl = primary.getMallDemoUrl();
		this.mallName = primary.getMallName();
		this.mallLogo = primary.getMallLogo();
		this.mallBackground = primary.getMallBackground();
		if (primary.getMallBackgroundStatus() != null) {
			this.mallBackgroundStatus = primary.getMallBackgroundStatus()
					.toString();
		}
		this.address = primary.getAddress();
		this.mobile = primary.getMobile();
		this.code = primary.getCode();
		this.email = primary.getEmail();
		this.mallIntroduction = primary.getMallIntroduction();
		if (primary.getMallStatus() != null) {
			this.mallStatus = primary.getMallStatus().toString();
		}
		this.staffId = primary.getStaffId();
		this.createStaffId = primary.getCreateStaffId().toString();
		this.modifyStaffId = primary.getModifyStaffId().toString();
		if (primary.getCreateTime() != null) {
			this.createStartTime = primary.getCreateTime().toString();
		}
		if (primary.getModifyTime() != null) {
			this.modifyStartTime = primary.getModifyTime().toString();
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
		log.debug("modify primary: " + primaryConfigurationId);
		String newMallLogo = "";
		if (mallLogoFile != null) {
			mallLogoFileFileName = UUID.randomUUID().toString() + mallLogoFileFileName;
			newMallLogo = getLogoSavePath() + "/" +mallLogoFileFileName;
			FileOutputStream fos = new FileOutputStream(newMallLogo);
			FileInputStream fis = new FileInputStream(mallLogoFile);
			byte[] buffer = new byte[1024];
			int len = 0;
			while ((len = fis.read(buffer)) > 0) {
				fos.write(buffer, 0, len);
			}
			fos.close();
			fis.close();
		}

		String newMallBackground = "";
		if (mallBackgroundFile != null) {
			mallBackgroundFileFileName = UUID.randomUUID().toString()
					+ mallBackgroundFileFileName;
			newMallBackground = getBackgroundSavePath() + "/" +mallBackgroundFileFileName;
			FileOutputStream fos = new FileOutputStream(newMallBackground);
			FileInputStream fis = new FileInputStream(mallBackgroundFile);
			byte[] buffer = new byte[1024];
			int len = 0;
			while ((len = fis.read(buffer)) > 0) {
				fos.write(buffer, 0, len);
			}
			fos.close();
			fis.close();
		}
		// 取得当前登录人信息
		currentStaff = super.getCurrentStaff();
		PrimaryConfiguration oldpo = primaryConfigurationService
				.findByid(primaryConfigurationId);
		PrimaryConfiguration po = new PrimaryConfiguration();
		po.setId(Integer.parseInt(primaryConfigurationId));
		if (!StringHelper.isNull(mallDemoUrl)) {
			po.setMallDemoUrl(mallDemoUrl);
		} else {
			po.setMallDemoUrl(oldpo.getMallDemoUrl());
		}
		if (!StringHelper.isNull(mallName)) {
			po.setMallName(mallName);
		} else {
			po.setMallName(oldpo.getMallName());
		}
		if (!StringHelper.isNull(mallLogoFileFileName)) {
			po.setMallLogo(logoSavePath+mallLogoFileFileName);
		} else {
			po.setMallLogo(oldpo.getMallLogo());
		}
		if (!StringHelper.isNull(mallBackgroundFileFileName)) {
			po.setMallBackground(backgroundSavePath+mallBackgroundFileFileName);
		} else {
			po.setMallBackground(oldpo.getMallBackground());
		}
		if (!StringHelper.isNull(mallBackgroundStatus)) {
			po.setMallBackgroundStatus(Integer.parseInt(mallBackgroundStatus));
		} else {
			po.setMallBackgroundStatus(oldpo.getMallBackgroundStatus());
		}
		if (!StringHelper.isNull(address)) {
			po.setAddress(address);
		} else {
			po.setAddress(oldpo.getAddress());
		}
		if (!StringHelper.isNull(mobile)) {
			po.setMobile(mobile);
		} else {
			po.setMobile(oldpo.getMobile());
		}
		if (!StringHelper.isNull(code)) {
			po.setCode(code);
		} else {
			po.setCode(oldpo.getCode());
		}
		if (!StringHelper.isNull(email)) {
			po.setEmail(email);
		} else {
			po.setEmail(oldpo.getEmail());
		}
		if (!StringHelper.isNull(mallIntroduction)) {
			po.setMallIntroduction(mallIntroduction);
		} else {
			po.setMallIntroduction(oldpo.getMallIntroduction());
		}
		if (!StringHelper.isNull(mallStatus)) {
			po.setMallStatus(Integer.parseInt(mallStatus));
		} else {
			po.setMallStatus(oldpo.getMallStatus());
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
		int res = this.primaryConfigurationService.modify(po);
		super.success(null, AjaxResult.CALL_BACK_TYPE_CLOSECURRENT, "primaryConfiguration",
				null);
		return null;
	}

	@Action(value = "del", results = { @Result(name = StrutsResMSG.SUCCESS, type = "plainText") })
	public String del() throws IOException, JSONException {
		primaryConfigurationService
				.removePrimaryConfigurationId(primaryConfigurationId);
		super.success(null, AjaxResult.CALL_BACK_TYPE_CLOSECURRENT, "primaryConfiguration",
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
		super.toJsonArray(primaryConfigurationService.listAll());
		return null;
	}
}
