/**
 *2012-5-10 下午3:52:29
 */
package com.xnradmin.core.action.business.wareHouse;


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
import com.xnradmin.core.action.business.wareHouse.BusinessWareHouseAction;
import com.xnradmin.core.service.StaffService;
import com.xnradmin.core.service.business.wareHouse.BusinessWareHouseService;
import com.xnradmin.core.service.common.status.StatusService;
import com.xnradmin.po.CommonStaff;
import com.xnradmin.po.business.wareHouse.BusinessWareHouse;
import com.xnradmin.po.common.status.Status;
import com.xnradmin.vo.StaffVO;
import com.xnradmin.vo.business.BusinessWareHouseVO;

/**
 * @autohr: xiang_liu
 * 
 */
@Controller
@Scope("prototype")
@Namespace("/page/business/admin/warehouse/wareHouse")
@ParentPackage("json-default")
public class BusinessWareHouseAction extends ParentAction {

	@Autowired
	private BusinessWareHouseService businessWareHouseService;
	
	@Autowired
	private StatusService statusService;

	@Autowired
	private StaffService staffService;

	private String wareHouseId;
    private String wareHouseName;  //单位名称
    private String wareHouseStatus;  //单位状态
    private String wareHouseAddress;  //单位状态
    private String wareHouseSerno;  //单位状态
	private String createTime; // 建立时间
	private String createStartTime; // 建立开始时间
	private String createEndTime; // 建立结束时间
	private String createStaffId; // 建立人
	private String modifyTime; // 修改时间
	private String modifyStartTime; // 修改开始时间
	private String modifyEndTime; // 修改结束时间
	private String modifyStaffId; // 修改人
	private List<Status> wareHouseStatusList;
	private List<BusinessWareHouse> businessWareHouseList;
	private List<StaffVO> staffList;
	private CommonStaff currentStaff;
	private List<BusinessWareHouseVO> voList;
	private BusinessWareHouseVO businessWareHouseVO;
	private BusinessWareHouse wareHouse;
	private Status status;

	public String getWareHouseId() {
		return wareHouseId;
	}

	public void setWareHouseId(String wareHouseId) {
		this.wareHouseId = wareHouseId;
	}

	public String getWareHouseName() {
		return wareHouseName;
	}

	public void setWareHouseName(String wareHouseName) {
		this.wareHouseName = wareHouseName;
	}

	public String getWareHouseStatus() {
		return wareHouseStatus;
	}

	public void setWareHouseStatus(String wareHouseStatus) {
		this.wareHouseStatus = wareHouseStatus;
	}

	public String getWareHouseAddress() {
		return wareHouseAddress;
	}

	public void setWareHouseAddress(String wareHouseAddress) {
		this.wareHouseAddress = wareHouseAddress;
	}

	public String getWareHouseSerno() {
		return wareHouseSerno;
	}

	public void setWareHouseSerno(String wareHouseSerno) {
		this.wareHouseSerno = wareHouseSerno;
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

	public List<Status> getWareHouseStatusList() {
		return wareHouseStatusList;
	}

	public void setWareHouseStatusList(List<Status> wareHouseStatusList) {
		this.wareHouseStatusList = wareHouseStatusList;
	}

	public List<BusinessWareHouse> getBusinessWareHouseList() {
		return businessWareHouseList;
	}

	public void setBusinessWareHouseList(
			List<BusinessWareHouse> businessWareHouseList) {
		this.businessWareHouseList = businessWareHouseList;
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

	public List<BusinessWareHouseVO> getVoList() {
		return voList;
	}

	public void setVoList(List<BusinessWareHouseVO> voList) {
		this.voList = voList;
	}

	public BusinessWareHouseVO getBusinessWareHouseVO() {
		return businessWareHouseVO;
	}

	public void setBusinessWareHouseVO(BusinessWareHouseVO businessWareHouseVO) {
		this.businessWareHouseVO = businessWareHouseVO;
	}

	public BusinessWareHouse getWareHouse() {
		return wareHouse;
	}

	public void setWareHouse(BusinessWareHouse wareHouse) {
		this.wareHouse = wareHouse;
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

	static Log log = LogFactory.getLog(BusinessWareHouseAction.class);

	/**
	 * 跳转到信息页。。
	 * 
	 * @return
	 */
	@Action(value = "info", results = {
			@Result(name = StrutsResMSG.SUCCESS, location = "/business/admin/warehouse/wareHouse/info.jsp"),
			@Result(name = StrutsResMSG.FAILED, location = "/business/admin/warehouse/wareHouse/info.jsp") })
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
			@Result(name = StrutsResMSG.SUCCESS, location = "/business/admin/warehouse/wareHouse/lookup.jsp"),
			@Result(name = StrutsResMSG.FAILED, location = "/business/admin/warehouse/wareHouse/lookup.jsp") })
	public String lookup() {
		setPageInfo();
		return StrutsResMSG.SUCCESS;
	}
	
	private void setPageInfo() {
		// 设置排序
		findBusinessWareHouseStatusList();
		findStaffList();
		findBusinessWareHouseList();
		BusinessWareHouseVO vo = new BusinessWareHouseVO();
		BusinessWareHouse bw = new BusinessWareHouse();
		if(!StringHelper.isNull(wareHouseId)){
			bw.setId(Long.valueOf(wareHouseId));
		}
		bw.setWareHouseName(wareHouseName);
		if(!StringHelper.isNull(wareHouseStatus)){
			bw.setWareHouseStatus(Integer.parseInt(wareHouseStatus));
		}
		if(!StringHelper.isNull(wareHouseSerno)){
			bw.setWareHouseSerno(wareHouseSerno);
		}
		if(!StringHelper.isNull(createStaffId)){
			bw.setCreateStaffId(Integer.parseInt(createStaffId));
		}
		if(!StringHelper.isNull(modifyStaffId)){
			bw.setModifyStaffId(Integer.parseInt(modifyStaffId));
		}
		vo.setBusinessWareHouse(bw);
		vo.setCreateStartTime(createStartTime);
		vo.setCreateEndTime(createEndTime);
		vo.setModifyStartTime(modifyStartTime);
		vo.setCreateEndTime(modifyEndTime);
		this.voList = businessWareHouseService.listVO(vo, getPageNum(), getNumPerPage(), orderField, orderDirection);
		this.totalCount = businessWareHouseService.getCount(vo);
	}

	/**
	 * 加载类型状态
	 */
	private void findBusinessWareHouseStatusList() {
		this.wareHouseStatusList = statusService.find(BusinessWareHouse.class,
				"businessWareHouseStatus");
	}

	/*
	 * 加载所有用户
	 */
	private void findStaffList() {
		this.staffList = staffService.listVO("","","", "", "", "", "", "", "", 0, 0,
				0, "", "");
	}
	
	/**
	 * 加载所有数量单位
	 */
	private void findBusinessWareHouseList() {
		this.businessWareHouseList = businessWareHouseService.listAll();
	}
	

	@Action(value = "addinfo", results = {
			@Result(name = StrutsResMSG.SUCCESS, location = "/business/admin/warehouse/wareHouse/add.jsp"),
			@Result(name = StrutsResMSG.FAILED, location = "/business/admin/warehouse/wareHouse/add.jsp") })
	public String addInfo() {
		// 加载状态与类型
		findBusinessWareHouseStatusList();
		findBusinessWareHouseList();
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
		BusinessWareHouse po = new BusinessWareHouse();
		po.setWareHouseSerno(wareHouseSerno);
		po.setWareHouseName(wareHouseName);
		po.setWareHouseAddress(wareHouseAddress);
		if(!StringHelper.isNull(wareHouseStatus)){
			po.setWareHouseStatus(Integer.parseInt(wareHouseStatus));
		}
		po.setCreateStaffId(currentStaff.getId());
		po.setCreateTime(new Timestamp(System.currentTimeMillis()));
		po.setModifyStaffId(currentStaff.getId());
		po.setModifyTime(new Timestamp(System.currentTimeMillis()));
		businessWareHouseService.save(po);
		super.success(null, AjaxResult.CALL_BACK_TYPE_CLOSECURRENT, "businessWareHouse",
				null);
		return null;
	}

	/**
	 * 带信息到修改页
	 * 
	 * @return String
	 */
	@Action(value = "modifyinfo", results = {
			@Result(name = StrutsResMSG.SUCCESS, location = "/business/admin/warehouse/wareHouse/modify.jsp"),
			@Result(name = StrutsResMSG.FAILED, location = "/business/admin/warehouse/wareHouse/modify.jsp") })
	public String modifyinfo() {
		findBusinessWareHouseStatusList();
		findBusinessWareHouseList();
		BusinessWareHouse po = new BusinessWareHouse();
		po = businessWareHouseService.findByid(wareHouseId);
		businessWareHouseVO = new BusinessWareHouseVO();
		businessWareHouseVO.setBusinessWareHouse(po);
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
		log.debug("modify wareHouse: " + wareHouseId);
		// 取得当前登录人信息
		currentStaff = super.getCurrentStaff();
		BusinessWareHouse po = businessWareHouseService
				.findByid(wareHouseId);
		po.setWareHouseSerno(wareHouseSerno);
		po.setWareHouseName(wareHouseName);
		po.setWareHouseAddress(wareHouseAddress);
		if(!StringHelper.isNull(wareHouseStatus)){
			po.setWareHouseStatus(Integer.parseInt(wareHouseStatus));
		}
		po.setModifyStaffId(currentStaff.getId());
		po.setModifyTime(new Timestamp(System.currentTimeMillis()));
		this.businessWareHouseService.modify(po);
		super.success(null, AjaxResult.CALL_BACK_TYPE_CLOSECURRENT, "businessWareHouse",
				null);
		return null;
	}

	@Action(value = "del", results = { @Result(name = StrutsResMSG.SUCCESS, type = "plainText") })
	public String del() throws IOException, JSONException {
		businessWareHouseService
				.removeBusinessWareHouseId(wareHouseId);
		super.success(null, AjaxResult.CALL_BACK_TYPE_CLOSECURRENT, "businessWareHouse",
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
		super.toJsonArray(businessWareHouseService.listAll());
		return null;
	}
}
