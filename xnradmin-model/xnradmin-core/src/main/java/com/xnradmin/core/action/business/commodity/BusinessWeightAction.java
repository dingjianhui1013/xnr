/**
 *2012-5-10 下午3:52:29
 */
package com.xnradmin.core.action.business.commodity;

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
import com.xnradmin.core.service.business.commodity.BusinessCategoryService;
import com.xnradmin.core.service.business.commodity.BusinessWeightService;
import com.xnradmin.core.service.common.status.StatusService;
import com.xnradmin.core.service.mall.seting.PrimaryConfigurationService;
import com.xnradmin.po.CommonStaff;
import com.xnradmin.po.business.BusinessWeight;
import com.xnradmin.po.common.status.Status;
import com.xnradmin.po.mall.seting.PrimaryConfiguration;
import com.xnradmin.vo.StaffVO;
import com.xnradmin.vo.business.BusinessGoodsVO;

/**
 * @autohr: xiang_liu
 * 
 */
@Controller
@Scope("prototype")
@Namespace("/page/business/admin/commodity/weight")
@ParentPackage("json-default")
public class BusinessWeightAction extends ParentAction {

	@Autowired
	private BusinessWeightService businessWeightService;
	
	@Autowired
	private PrimaryConfigurationService primaryConfigurationService;

	@Autowired
	private StatusService statusService;

	@Autowired
	private StaffService staffService;

	private String weightId;
    private String weightName;  //单位名称
    private String weightStatus;  //单位状态
    private String sortId; //单位排序
    private String weightDescription;	//单位描述
    private String primaryConfigurationId; //对应商城ID
	private String createTime; // 建立时间
	private String createStartTime; // 建立开始时间
	private String createEndTime; // 建立结束时间
	private String createStaffId; // 建立人
	private String modifyTime; // 修改时间
	private String modifyStartTime; // 修改开始时间
	private String modifyEndTime; // 修改结束时间
	private String modifyStaffId; // 修改人
	private List<Status> weightStatusList;
	private List<PrimaryConfiguration> primaryConfigurationList;
	private List<BusinessWeight> businessWeightList;
	private List<StaffVO> staffList;
	private CommonStaff currentStaff;
	private List<BusinessGoodsVO> voList;
	private BusinessGoodsVO businessGoodsVO;
	private BusinessWeight weight;
	private PrimaryConfiguration primaryConfiguration;
	private Status status;

	public String getWeightId() {
		return weightId;
	}

	public void setWeightId(String weightId) {
		this.weightId = weightId;
	}

	public String getWeightName() {
		return weightName;
	}

	public void setWeightName(String weightName) {
		this.weightName = weightName;
	}

	public String getWeightStatus() {
		return weightStatus;
	}

	public void setWeightStatus(String weightStatus) {
		this.weightStatus = weightStatus;
	}

	public String getSortId() {
		return sortId;
	}

	public void setSortId(String sortId) {
		this.sortId = sortId;
	}

	public String getWeightDescription() {
		return weightDescription;
	}

	public void setWeightDescription(String weightDescription) {
		this.weightDescription = weightDescription;
	}

	public String getPrimaryConfigurationId() {
		return primaryConfigurationId;
	}

	public void setPrimaryConfigurationId(String primaryConfigurationId) {
		this.primaryConfigurationId = primaryConfigurationId;
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

	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
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

	public String getModifyStaffId() {
		return modifyStaffId;
	}

	public void setModifyStaffId(String modifyStaffId) {
		this.modifyStaffId = modifyStaffId;
	}

	public List<Status> getWeightStatusList() {
		return weightStatusList;
	}

	public void setWeightStatusList(List<Status> weightStatusList) {
		this.weightStatusList = weightStatusList;
	}

	public List<PrimaryConfiguration> getPrimaryConfigurationList() {
		return primaryConfigurationList;
	}

	public void setPrimaryConfigurationList(
			List<PrimaryConfiguration> primaryConfigurationList) {
		this.primaryConfigurationList = primaryConfigurationList;
	}

	public List<BusinessWeight> getBusinessWeightList() {
		return businessWeightList;
	}

	public void setBusinessWeightList(List<BusinessWeight> businessWeightList) {
		this.businessWeightList = businessWeightList;
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

	public List<BusinessGoodsVO> getVoList() {
		return voList;
	}

	public void setVoList(List<BusinessGoodsVO> voList) {
		this.voList = voList;
	}

	public BusinessGoodsVO getBusinessGoodsVO() {
		return businessGoodsVO;
	}

	public void setBusinessGoodsVO(BusinessGoodsVO businessGoodsVO) {
		this.businessGoodsVO = businessGoodsVO;
	}

	public BusinessWeight getWeight() {
		return weight;
	}

	public void setWeight(BusinessWeight weight) {
		this.weight = weight;
	}

	public PrimaryConfiguration getPrimaryConfiguration() {
		return primaryConfiguration;
	}

	public void setPrimaryConfiguration(PrimaryConfiguration primaryConfiguration) {
		this.primaryConfiguration = primaryConfiguration;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@Override
	public boolean isPublic() {
		return false;
	};

	static Log log = LogFactory.getLog(BusinessWeightAction.class);

	/**
	 * 跳转到信息页。。
	 * 
	 * @return
	 */
	@Action(value = "info", results = {
			@Result(name = StrutsResMSG.SUCCESS, location = "/business/admin/commodity/weight/info.jsp"),
			@Result(name = StrutsResMSG.FAILED, location = "/business/admin/commodity/weight/info.jsp") })
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
			@Result(name = StrutsResMSG.SUCCESS, location = "/business/admin/commodity/weight/lookup.jsp"),
			@Result(name = StrutsResMSG.FAILED, location = "/business/admin/commodity/weight/lookup.jsp") })
	public String lookup() {
		setPageInfo();
		return StrutsResMSG.SUCCESS;
	}
	
	private void setPageInfo() {
		// 设置排序
		findBusinessWeightStatusList();
		findStaffList();
		findPrimaryConfigurationList();
		findBusinessWeightList();
		BusinessGoodsVO vo = new BusinessGoodsVO();
		BusinessWeight bc = new BusinessWeight();
		if(!StringHelper.isNull(weightId)){
			bc.setId(Integer.parseInt(weightId));
		}
		bc.setWeightName(weightName);
		if(!StringHelper.isNull(weightStatus)){
			bc.setWeightStatus(Integer.parseInt(weightStatus));
		}
		if(!StringHelper.isNull(primaryConfigurationId)){
			bc.setPrimaryConfigurationId(Integer.parseInt(primaryConfigurationId));
		}
		if(!StringHelper.isNull(sortId)){
			bc.setSortId(Integer.parseInt(sortId));
		}
		bc.setWeightDescription(weightDescription);
		if(!StringHelper.isNull(createStaffId)){
			bc.setCreateStaffId(Integer.parseInt(createStaffId));
		}
		if(!StringHelper.isNull(modifyStaffId)){
			bc.setModifyStaffId(Integer.parseInt(modifyStaffId));
		}
		vo.setBusinessWeight(bc);
		vo.setCreateStartTime(createStartTime);
		vo.setCreateEndTime(createEndTime);
		vo.setModifyStartTime(modifyStartTime);
		vo.setCreateEndTime(modifyEndTime);
		this.voList = businessWeightService.listVO(vo, getPageNum(), getNumPerPage(), orderField, orderDirection);
		this.totalCount = businessWeightService.getCount(vo);
	}

	/**
	 * 加载类型状态
	 */
	private void findBusinessWeightStatusList() {
		this.weightStatusList = statusService.find(BusinessWeight.class,
				"businessWeightStatus");
	}

	/*
	 * 加载所有用户
	 */
	private void findStaffList() {
		this.staffList = staffService.listVO("","","", "", "", "", "", "", "", 0, 0,
				0, "", "");
	}

	/**
	 * 加载所有商城
	 */
	private void findPrimaryConfigurationList() {
		this.primaryConfigurationList = primaryConfigurationService.listAll();
	}
	
	/**
	 * 加载所有数量单位
	 */
	private void findBusinessWeightList() {
		this.businessWeightList = businessWeightService.listAll();
	}
	

	@Action(value = "addinfo", results = {
			@Result(name = StrutsResMSG.SUCCESS, location = "/business/admin/commodity/weight/add.jsp"),
			@Result(name = StrutsResMSG.FAILED, location = "/business/admin/commodity/weight/add.jsp") })
	public String addInfo() {
		// 加载状态与类型
		findBusinessWeightStatusList();
		findPrimaryConfigurationList();
		findBusinessWeightList();
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
		BusinessWeight po = new BusinessWeight();
		if(primaryConfiguration != null){
			po.setPrimaryConfigurationId(primaryConfiguration.getId());
		}
		if(!StringHelper.isNull(sortId)){
			po.setSortId(Integer.parseInt(sortId));
		}
		po.setWeightDescription(weightDescription);
		po.setWeightName(weightName);
		if(!StringHelper.isNull(weightStatus)){
			po.setWeightStatus(Integer.parseInt(weightStatus));
		}
		po.setCreateStaffId(currentStaff.getId());
		po.setCreateTime(new Timestamp(System.currentTimeMillis()));
		po.setModifyStaffId(currentStaff.getId());
		po.setModifyTime(new Timestamp(System.currentTimeMillis()));
		businessWeightService.save(po);
		super.success(null, AjaxResult.CALL_BACK_TYPE_CLOSECURRENT, "businessWeight",
				null);
		return null;
	}

	/**
	 * 带信息到修改页
	 * 
	 * @return String
	 */
	@Action(value = "modifyinfo", results = {
			@Result(name = StrutsResMSG.SUCCESS, location = "/business/admin/commodity/weight/modify.jsp"),
			@Result(name = StrutsResMSG.FAILED, location = "/business/admin/commodity/weight/modify.jsp") })
	public String modifyinfo() {
		findBusinessWeightStatusList();
		findPrimaryConfigurationList();
		findBusinessWeightList();
		BusinessWeight po = new BusinessWeight();
		po = businessWeightService.findByid(weightId);
		
		businessGoodsVO = new BusinessGoodsVO();
		businessGoodsVO.setBusinessWeight(po);
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
		log.debug("modify weight: " + weightId);
		// 取得当前登录人信息
		currentStaff = super.getCurrentStaff();
		BusinessWeight po = businessWeightService
				.findByid(weightId);
		if(primaryConfiguration != null){
			po.setPrimaryConfigurationId(primaryConfiguration.getId());
		}
		if(!StringHelper.isNull(sortId)){
			po.setSortId(Integer.parseInt(sortId));
		}
		po.setWeightDescription(weightDescription);
		po.setWeightName(weightName);
		if(!StringHelper.isNull(weightStatus)){
			po.setWeightStatus(Integer.parseInt(weightStatus));
		}
		po.setModifyStaffId(currentStaff.getId());
		po.setModifyTime(new Timestamp(System.currentTimeMillis()));
		int res = this.businessWeightService.modify(po);
		super.success(null, AjaxResult.CALL_BACK_TYPE_CLOSECURRENT, "businessWeight",
				null);
		return null;
	}

	@Action(value = "del", results = { @Result(name = StrutsResMSG.SUCCESS, type = "plainText") })
	public String del() throws IOException, JSONException {
		businessWeightService
				.removeBusinessWeightId(weightId);
		super.success(null, AjaxResult.CALL_BACK_TYPE_CLOSECURRENT, "businessWeight",
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
		super.toJsonArray(businessWeightService.listAll());
		return null;
	}
}
