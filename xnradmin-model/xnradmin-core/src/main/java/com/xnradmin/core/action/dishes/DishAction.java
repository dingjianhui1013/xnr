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
import com.xnradmin.core.action.ParentAction;
import com.xnradmin.core.service.StaffService;
import com.xnradmin.core.service.common.status.StatusService;
import com.xnradmin.core.service.dishes.CollocationService;
import com.xnradmin.core.service.dishes.DishService;
import com.xnradmin.po.CommonStaff;
import com.xnradmin.po.common.status.Status;
import com.xnradmin.po.dishes.Collocation;
import com.xnradmin.po.dishes.Dish;
import com.xnradmin.po.dishes.RawMaterial;
import com.xnradmin.vo.StaffVO;
import com.xnradmin.vo.dishes.DishesVO;

/**
 * @autohr: xiang_liu
 * 
 */
@Controller
@Scope("prototype")
@Namespace("/page/dishes/dish")
@ParentPackage("json-default")
public class DishAction extends ParentAction {

	@Autowired
	private CollocationService collocationService;

	@Autowired
	private DishService dishService;

	@Autowired
	private StatusService statusService;

	@Autowired
	private StaffService staffService;
	
	private String collocationId;
	private String dishId;
	private String dishName;
	private String rawMaterialId;
	private String rawMaterialName;
	private String weightId;
	private String number;
	private String createStartTime;
	private String createEndTime;
	private String createStaffId;
	private String createStaffName;
	private String modifyStartTime;
	private String modifyEndTime;
	private String modifyStaffId;
	private String modifyStaffName;
	private String introduction;
	private String cookingMethod;
	private String picUrl;
	private String dishTypeId;
	private String dishTypeName;
	private List<Status> materialTypeList;
	private List<Status> dishTypeList;
	private List<Status> weightList;
	private List<StaffVO> staffList;
	private Map<String,DishesVO> rawMaterialList;
	private List<DishesVO> dvList;
	private Status dishType;
	private Status weight;
	private CommonStaff currentStaff;
	private List<DishesVO> voList;
	private DishesVO dishVo;
	private Dish dish;
	private Status status;
	public StatusService getStatusService() {
		return statusService;
	}

	public void setStatusService(StatusService statusService) {
		this.statusService = statusService;
	}

	public String getCollocationId() {
		return collocationId;
	}

	public void setCollocationId(String collocationId) {
		this.collocationId = collocationId;
	}

	public String getDishId() {
		return dishId;
	}

	public void setDishId(String dishId) {
		this.dishId = dishId;
	}

	public String getDishName() {
		return dishName;
	}

	public void setDishName(String dishName) {
		this.dishName = dishName;
	}

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

	public String getWeightId() {
		return weightId;
	}

	public void setWeightId(String weightId) {
		this.weightId = weightId;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
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

	public List<Status> getDishTypeList() {
		return dishTypeList;
	}

	public void setDishTypeList(List<Status> dishTypeList) {
		this.dishTypeList = dishTypeList;
	}

	public Status getDishType() {
		return dishType;
	}

	public void setDishType(Status dishType) {
		this.dishType = dishType;
	}

	public Status getWeight() {
		return weight;
	}

	public void setWeight(Status weight) {
		this.weight = weight;
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

	public Map<String,DishesVO> getRawMaterialList() {
		return rawMaterialList;
	}

	public void setRawMaterialList(Map<String,DishesVO> rawMaterialList) {
		this.rawMaterialList = rawMaterialList;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public String getCookingMethod() {
		return cookingMethod;
	}

	public void setCookingMethod(String cookingMethod) {
		this.cookingMethod = cookingMethod;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public String getDishTypeId() {
		return dishTypeId;
	}

	public void setDishTypeId(String dishTypeId) {
		this.dishTypeId = dishTypeId;
	}

	public DishesVO getDishVo() {
		return dishVo;
	}

	public void setDishVo(DishesVO dishVo) {
		this.dishVo = dishVo;
	}

	public Dish getDish() {
		return dish;
	}

	public void setDish(Dish dish) {
		this.dish = dish;
	}

	public List<Status> getWeightList() {
		return weightList;
	}

	public void setWeightList(List<Status> weightList) {
		this.weightList = weightList;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getDishTypeName() {
		return dishTypeName;
	}

	public void setDishTypeName(String dishTypeName) {
		this.dishTypeName = dishTypeName;
	}

	public List<DishesVO> getDvList() {
		return dvList;
	}

	public void setDvList(List<DishesVO> dvList) {
		this.dvList = dvList;
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

	static Log log = LogFactory.getLog(DishAction.class);

	/**
	 * 跳转到信息页。。
	 * 
	 * @return
	 */
	@Action(value = "info", results = {
			@Result(name = StrutsResMSG.SUCCESS, location = "/dishes/dish/info.jsp"),
			@Result(name = StrutsResMSG.FAILED, location = "/dishes/dish/info.jsp") })
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
			@Result(name = StrutsResMSG.SUCCESS, location = "/dishes/dish/lookup.jsp"),
			@Result(name = StrutsResMSG.FAILED, location = "/dishes/dish/lookup.jsp") })
	public String lookup() {
		setPageInfo();
		return StrutsResMSG.SUCCESS;
	}
	
	@Action(value = "dishTypeLookup", results = {
			@Result(name = StrutsResMSG.SUCCESS, location = "/dishes/dish/dishTypeLookup.jsp"),
			@Result(name = StrutsResMSG.FAILED, location = "/dishes/dish/dishTypeLookup.jsp") })
	public String statusLookup() {
		setDishTypeList();
		return StrutsResMSG.SUCCESS;
	}
	
	@Action(value = "weightSelect", results = { @Result(name = StrutsResMSG.SUCCESS, location = "/dishes/dish/weightSelect.jsp") })
	public String weightSelect(){
		setWegihtList();
		return StrutsResMSG.SUCCESS;
	}

	@Action(value = "weightLookup", results = {
			@Result(name = StrutsResMSG.SUCCESS, location = "/dishes/dish/weightLookup.jsp"),
			@Result(name = StrutsResMSG.FAILED, location = "/dishes/dish/weightLookup.jsp") })
	public String productTypeLookup() {
		setWegihtList();
		return StrutsResMSG.SUCCESS;
	}

	private void setPageInfo() {
		// 设置排序
		setDishTypeList();
		setMaterialTypeList();
		setWegihtList();
		setStaffList();
		this.voList = dishService.listVO(dishName, introduction,
				cookingMethod, picUrl, dishTypeId, createStartTime, createEndTime,
				createStaffId, modifyStartTime, modifyEndTime, modifyStaffId,
				getPageNum(), getNumPerPage(), orderField, orderDirection);
		this.totalCount = dishService.getCount(dishName, introduction,
				cookingMethod, picUrl, dishTypeId, createStartTime, createEndTime,
				createStaffId, modifyStartTime, modifyEndTime, modifyStaffId);
	}

	/**
	 * 加载菜品类型
	 */
	private void setDishTypeList() {
		this.dishTypeList = statusService.find(Dish.class, "dishType");
	}

	/**
	 * 加载原料类型
	 */
	private void setMaterialTypeList() {
		this.materialTypeList = statusService.find(RawMaterial.class,
				"materialType");
	}

	/**
	 * 加载重量单位
	 */
	private void setWegihtList() {
		this.weightList = statusService.find(Collocation.class, "weight");
	}
	
	/*
	 * 加载所有用户 
	 * */
	private void setStaffList(){
		this.staffList = staffService.listVO("","","", "", "", "", "", "", "", 0, 0, 0, "", "");
	}

	@Action(value = "addinfo", results = {
			@Result(name = StrutsResMSG.SUCCESS, location = "/dishes/dish/add.jsp"),
			@Result(name = StrutsResMSG.FAILED, location = "/dishes/dish/add.jsp") })
	public String addInfo() {
		// 加载状态与类型
		setDishTypeList();
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
		// 取得当前登录人信息
		currentStaff = super.getCurrentStaff();
		// 批量增加原料
		if (dishService.findByDishName(dishName, "") > 0) {
			super.error("菜品名已存在");
		} else if (rawMaterialList == null) {
			super.error("未填写原料");
		} else {
			log.debug("start:::");
			// 增加新菜品
			Dish dish = new Dish();
			dish.setDishName(dishName);
			if (status.getId()!=null) {
				dish.setDishTypeId(status.getId());
			}
			dish.setIntroduction(introduction);
			dish.setCookingMethod(cookingMethod);
			dish.setPicUrl(picUrl);
			dish.setCreateStaffId(currentStaff.getId());
			dish.setCreateTime(new Timestamp(System.currentTimeMillis()));
			dish.setModifyStaffId(currentStaff.getId());
			dish.setModifyTime(new Timestamp(System.currentTimeMillis()));
			int dishId = dishService.save(dish);
			// 为新菜品添加原料
			Iterator<String> it = rawMaterialList.keySet().iterator();
			while (it.hasNext()) {
				String key = it.next();
				DishesVO dvo = rawMaterialList.get(key);
				Status weightId = dvo.getStatus();
				Collocation ct = new Collocation();
				RawMaterial rawMaterial = dvo.getRawMaterial();
				ct.setDishId(dishId);
				ct.setRawMaterialId(rawMaterial.getId());
				ct.setCreateStaffId(currentStaff.getId());
				ct.setCreateTime(new Timestamp(System.currentTimeMillis()));
				ct.setModifyStaffId(currentStaff.getId());
				ct.setModifyTime(new Timestamp(System.currentTimeMillis()));
				ct.setNumber(Float.valueOf(dvo.getNumber()));
				ct.setWeightId(weightId.getId());
				collocationService.save(ct);
			}
			log.debug("end:::");
		}
		super.success(null, AjaxResult.CALL_BACK_TYPE_CLOSECURRENT, "dish",
				null);
		return null;
	}

	/**
	 * 带信息到修改页
	 * 
	 * @return String
	 */
	@Action(value = "modifyinfo", results = {
			@Result(name = StrutsResMSG.SUCCESS, location = "/dishes/dish/modify.jsp"),
			@Result(name = StrutsResMSG.FAILED, location = "/dishes/dish/modify.jsp") })
	public String modifyinfo() {
		// 加载菜品类型，重量单位
		setDishTypeList();
		setWegihtList();
		// 加载该菜品所有原料
		dvList = collocationService.listVO(dishId,
				rawMaterialId, weightId, number, createStartTime,
				createEndTime, createStaffId, modifyStartTime, modifyEndTime,
				modifyStaffId, getPageNum(), getNumPerPage(), orderField,
				orderDirection);
		// 加载该菜品信息
		dish = dishService.findByid(dishId);
		this.dishName = dish.getDishName();
		this.introduction = dish.getIntroduction();
		this.cookingMethod = dish.getCookingMethod();
		this.picUrl = dish.getPicUrl();
		this.dishTypeId = dish.getDishTypeId().toString();
		Status status = statusService.findByid(dishTypeId);
		this.dishTypeName = (status.getStatusCode());
		this.createStaffId = dish.getCreateStaffId().toString();
		CommonStaff cs = staffService.findByid(createStaffId);
		this.createStaffName = cs.getStaffName();
		this.modifyStaffId = dish.getModifyStaffId().toString();
		CommonStaff ms = staffService.findByid(modifyStaffId);
		this.modifyStaffName = ms.getStaffName();
		this.createStartTime = dish.getCreateTime().toString();
		this.modifyStartTime = dish.getModifyTime().toString();
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
		log.debug("modify dish: " + dishId);
		// 取得当前登录人信息
		currentStaff = super.getCurrentStaff();
		// 批量增加原料
		if (dishService.findByDishName(dish.getDishName(), dishId) > 0) {
			super.error("菜品名已存在");
		} else if (rawMaterialList == null) {
			super.error("未填写原料");
		} else {
			log.debug("start:::");
			// 修改菜品
			log.debug(dishType.getId());
			Dish tempDish = new Dish();
			tempDish.setId(Integer.parseInt(dishId));
			tempDish.setDishName(dish.getDishName());
			if (dishType.getId()!=null) {
				tempDish.setDishTypeId(dishType.getId());
			}
			tempDish.setIntroduction(dish.getIntroduction());
			tempDish.setCookingMethod(dish.getCookingMethod());
			tempDish.setCreateStaffId(currentStaff.getId());
			tempDish.setCreateTime(new Timestamp(System.currentTimeMillis()));
			tempDish.setModifyStaffId(currentStaff.getId());
			tempDish.setModifyTime(new Timestamp(System.currentTimeMillis()));
			tempDish.setPicUrl(dish.getPicUrl());
			dishService.modify(tempDish);
			//删除该菜品所有的原料
			collocationService.removeDishId(Integer.parseInt(dishId));
			// 为新菜品添加原料
			Iterator<String> it = rawMaterialList.keySet().iterator();
			while (it.hasNext()) {
				String key = it.next();
				DishesVO dvo = rawMaterialList.get(key);
				Status weightId = dvo.getStatus();
				Collocation ct = new Collocation();
				RawMaterial rawMaterial = dvo.getRawMaterial();
				ct.setDishId(Integer.parseInt(dishId));
				ct.setRawMaterialId(rawMaterial.getId());
				ct.setCreateStaffId(currentStaff.getId());
				ct.setCreateTime(new Timestamp(System.currentTimeMillis()));
				ct.setModifyStaffId(currentStaff.getId());
				ct.setModifyTime(new Timestamp(System.currentTimeMillis()));
				ct.setNumber(Float.valueOf(dvo.getNumber()));
				ct.setWeightId(weightId.getId());
				collocationService.save(ct);
			}
			log.debug("end:::");
		}
		super.success(null, AjaxResult.CALL_BACK_TYPE_CLOSECURRENT, "dish",
				null);
		return null;
	}

	@Action(value = "del", results = { @Result(name = StrutsResMSG.SUCCESS, type = "plainText") })
	public String del() throws IOException, JSONException {
		dishService.removeDishId(dishId);
		collocationService.removeDishId(Integer.parseInt(dishId));
		super.success(null, AjaxResult.CALL_BACK_TYPE_CLOSECURRENT,
				"dish", null);
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
		super.toJsonArray(dishService.listAll());
		return null;
	}
}
