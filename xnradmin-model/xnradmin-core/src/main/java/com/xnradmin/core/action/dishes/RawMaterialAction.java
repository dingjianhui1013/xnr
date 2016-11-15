/**
 *2012-5-10 下午3:52:29
 */
package com.xnradmin.core.action.dishes;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
import com.xnradmin.constant.ViewConstant;
import com.xnradmin.core.action.ParentAction;
import com.xnradmin.core.service.StaffService;
import com.xnradmin.core.service.common.status.StatusService;
import com.xnradmin.core.service.dishes.CollocationService;
import com.xnradmin.core.service.dishes.RawMaterialService;
import com.xnradmin.po.CommonStaff;
import com.xnradmin.po.common.status.Status;
import com.xnradmin.po.dishes.RawMaterial;
import com.xnradmin.vo.StaffVO;
import com.xnradmin.vo.dishes.DishesVO;

/**
 * @autohr: xiang_liu
 * 
 */
@Controller
@Scope("prototype")
@Namespace("/page/dishes/rawmaterial")
@ParentPackage("json-default")
public class RawMaterialAction extends ParentAction {

	@Autowired
	private RawMaterialService service;

	@Autowired
	private StatusService statusService;

	@Autowired
	private StaffService staffService;

	private String rawMaterialId;
	private String rawMaterialName;
	private String materialTypeId;
	private String materialTypeName;
	private String createTime;
	private String createStartTime;
	private String createEndTime;
	private String createStaffId;
	private String createStaffName;
	private String modifyTime;
	private String modifyStartTime;
	private String modifyEndTime;
	private String modifyStaffId;
	private String modifyStaffName;
	private List<Status> materialTypeList;
	private CommonStaff currentStaff;
	private List<DishesVO> voList;
	private Map<String, DishesVO> rawMaterialList;
	private List<StaffVO> staffList;
	private RawMaterial rawMaterial;
	private Status status;

	public String getRawMaterialId() {
		return rawMaterialId;
	}

	public void setRawMaterialId(String rawMaterialId) {
		this.rawMaterialId = rawMaterialId;
	}

	public String getRawMaterialName() {
		return rawMaterialName;
	}

	public void setRawMaterialName(String rawMaterialName) {
		this.rawMaterialName = rawMaterialName;
	}

	public String getMaterialTypeId() {
		return materialTypeId;
	}

	public void setMaterialTypeId(String materialTypeId) {
		this.materialTypeId = materialTypeId;
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

	public String getCreateStaffName() {
		return createStaffName;
	}

	public void setCreateStaffName(String createStaffName) {
		this.createStaffName = createStaffName;
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

	public String getModifyStaffName() {
		return modifyStaffName;
	}

	public void setModifyStaffName(String modifyStaffName) {
		this.modifyStaffName = modifyStaffName;
	}

	public List<Status> getMaterialTypeList() {
		return materialTypeList;
	}

	public void setMaterialTypeList(List<Status> materialTypeList) {
		this.materialTypeList = materialTypeList;
	}

	public CommonStaff getCurrentStaff() {
		return currentStaff;
	}

	public void setCurrentStaff(CommonStaff currentStaff) {
		this.currentStaff = currentStaff;
	}

	public List<DishesVO> getVoList() {
		return voList;
	}

	public void setVoList(List<DishesVO> voList) {
		this.voList = voList;
	}

	public Map<String, DishesVO> getRawMaterialList() {
		return rawMaterialList;
	}

	public void setRawMaterialList(Map<String, DishesVO> rawMaterialList) {
		this.rawMaterialList = rawMaterialList;
	}

	public RawMaterial getRawMaterial() {
		return rawMaterial;
	}

	public void setRawMaterial(RawMaterial rawMaterial) {
		this.rawMaterial = rawMaterial;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
	}

	public List<StaffVO> getStaffList() {
		return staffList;
	}

	public void setStaffList(List<StaffVO> staffList) {
		this.staffList = staffList;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getMaterialTypeName() {
		return materialTypeName;
	}

	public void setMaterialTypeName(String materialTypeName) {
		this.materialTypeName = materialTypeName;
	}

	@Override
	public boolean isPublic() {
		return false;
	};

	static Log log = LogFactory.getLog(RawMaterialAction.class);

	/**
	 * 跳转到信息页。。
	 * 
	 * @return
	 */
	@Action(value = "info", results = {
			@Result(name = StrutsResMSG.SUCCESS, location = "/dishes/rawmaterial/info.jsp"),
			@Result(name = StrutsResMSG.FAILED, location = "/dishes/rawmaterial/info.jsp") })
	public String info() {
		setPageInfo();
		return StrutsResMSG.SUCCESS;
	}

	@Action(value = "lookup", results = {
			@Result(name = StrutsResMSG.SUCCESS, location = "/dishes/rawmaterial/lookup.jsp"),
			@Result(name = StrutsResMSG.FAILED, location = "/dishes/rawmaterial/lookup.jsp") })
	public String lookup() {
		setPageInfo();
		return StrutsResMSG.SUCCESS;
	}

	@Action(value = "rwMaterialTypeLookup", results = {
			@Result(name = StrutsResMSG.SUCCESS, location = "/dishes/rawmaterial/rwMaterialTypeLookup.jsp"),
			@Result(name = StrutsResMSG.FAILED, location = "/dishes/rawmaterial/rwMaterialTypeLookup.jsp") })
	public String rwMaterialTypeLookup() {
		setMaterialTypeList();
		return StrutsResMSG.SUCCESS;
	}

	private void setPageInfo() {
		// 设置排序
		setMaterialTypeList();
		setStaffList();
		this.voList = service.listVO(rawMaterialName, materialTypeId,
				createStartTime, createEndTime, createStaffId, modifyStartTime,
				modifyEndTime, modifyStaffId, getPageNum(), getNumPerPage(),
				orderField, orderDirection);
		this.totalCount = service.getCount(rawMaterialName, materialTypeId,
				createStartTime, createEndTime, createStaffId, modifyStartTime,
				modifyEndTime, modifyStaffId);
	}

	@Action(value = "select", results = { @Result(name = StrutsResMSG.SUCCESS, location = "/dishes/rawmaterial/select.jsp") })
	public String select() {
		// return StrutsResMSG.SUCCESS;
		this.materialTypeList = statusService.find(RawMaterial.class,
				"materialType");
		return StrutsResMSG.SUCCESS;
	}

	/**
	 * 加载原料类型
	 */
	private void setMaterialTypeList() {
		this.materialTypeList = statusService.find(RawMaterial.class,
				"materialType");
	}

	/*
	 * 加载所有用户
	 */
	private void setStaffList() {
		this.staffList = staffService.listVO("","","", "", "", "", "", "", "", 0, 0,
				0, "", "");
	}

	/**
	 * 带信息到添加页
	 * 
	 * @return String
	 */
	@Action(value = "addinfo", results = {
			@Result(name = StrutsResMSG.SUCCESS, location = "/dishes/rawmaterial/add.jsp"),
			@Result(name = StrutsResMSG.FAILED, location = "/dishes/rawmaterial/add.jsp") })
	public String addInfo() {
		// 加载原料类型
		setMaterialTypeList();
		currentStaff = super.getCurrentStaff();
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
	public String add() throws IOException, JSONException {
		// 加载原料类型
		setMaterialTypeList();
		// 取得当前登录人信息
		currentStaff = super.getCurrentStaff();
		// 批量增加规则
		if (rawMaterialList == null) {
			super.error("未填写新增原料");
		} else {
			Iterator<String> it = rawMaterialList.keySet().iterator();
			while (it.hasNext()) {
				String key = it.next();
				
				DishesVO dvo = rawMaterialList.get(key);
				Status rawMaterialType = dvo.getStatus();
				RawMaterial rml = new RawMaterial();
				rml.setMaterialName(dvo.getMaterialName());
				rml.setMaterialTypeId(rawMaterialType.getId());
				rml.setCreateStaffId(currentStaff.getId());
				rml.setCreateTime(new Timestamp(System.currentTimeMillis()));
				rml.setModifyStaffId(currentStaff.getId());
				rml.setModifyTime(new Timestamp(System.currentTimeMillis()));
				service.save(rml);
			}
		}
		super.success(null, AjaxResult.CALL_BACK_TYPE_CLOSECURRENT,
				"rawMaterial", null);
		return null;
	}

	/**
	 * 带信息到修改页
	 * 
	 * @return String
	 */
	@Action(value = "modifyinfo", results = {
			@Result(name = StrutsResMSG.SUCCESS, location = "/dishes/rawmaterial/modify.jsp"),
			@Result(name = StrutsResMSG.FAILED, location = "/dishes/rawmaterial/modify.jsp") })
	public String modifyinfo() {
		// 加载原料类型
		setMaterialTypeList();
		RawMaterial po = service.findByid(rawMaterialId);
		if (po.getId() != null) {
			this.rawMaterialId = po.getId().toString();
		}
		this.rawMaterialName = po.getMaterialName();
		if (po.getMaterialTypeId() != null) {
			this.materialTypeId = po.getMaterialTypeId().toString();
			Status status = statusService.findByid(materialTypeId);
			this.materialTypeName = status.getStatusCode();
		}
		if (po.getCreateStaffId() != null) {
			this.createStaffId = po.getCreateStaffId().toString();
			CommonStaff cs = staffService.findByid(po.getCreateStaffId()
					.toString());
			this.createStaffName = cs.getStaffName();
		}
		if (po.getModifyStaffId() != null) {
			this.modifyStaffId = po.getModifyStaffId().toString();
			CommonStaff cs = staffService.findByid(po.getModifyStaffId()
					.toString());
			this.modifyStaffName = cs.getStaffName();
		}
		if (po.getCreateTime() != null) {
			this.createTime = StringHelper.getSystime(
					ViewConstant.PAGE_DATE_FORMAT_SEC, po.getCreateTime()
							.getTime());
		}
		if (po.getModifyTime() != null) {
			this.modifyTime = StringHelper.getSystime(
					ViewConstant.PAGE_DATE_FORMAT_SEC, po.getModifyTime()
							.getTime());
		}
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
		log.debug("modif action!");
		log.debug("modify rawMaterial: " + rawMaterialId);
		// 取得当前登录人信息
		currentStaff = super.getCurrentStaff();
		RawMaterial oldpo = service.findByid(rawMaterialId);
		RawMaterial po = new RawMaterial();
		po.setId(Integer.parseInt(rawMaterialId));
		if (!StringHelper.isNull(rawMaterialName)) {
			po.setMaterialName(rawMaterialName);
		} else {
			po.setMaterialName(oldpo.getMaterialName());
		}
		if (status.getId() != null) {
			po.setMaterialTypeId(status.getId());
		} else {
			po.setMaterialTypeId(oldpo.getMaterialTypeId());
		}
		po.setModifyTime(new Timestamp(System.currentTimeMillis()));
		po.setModifyStaffId(currentStaff.getId());
		po.setCreateStaffId(oldpo.getCreateStaffId());
		po.setCreateTime(oldpo.getCreateTime());
		int res = this.service.modify(po);
		if (res == 1) {
			super.error("原料已存在");
		} else {
			super.success(null, AjaxResult.CALL_BACK_TYPE_CLOSECURRENT,
					"rawMaterial", null);
		}
		return null;
	}

	@Action(value = "del", results = { @Result(name = StrutsResMSG.SUCCESS, type = "plainText") })
	public String del() throws IOException, JSONException {
		service.del(rawMaterialId);
		super.success(null, AjaxResult.CALL_BACK_TYPE_CLOSECURRENT,
				"rawMaterial", null);
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
}
