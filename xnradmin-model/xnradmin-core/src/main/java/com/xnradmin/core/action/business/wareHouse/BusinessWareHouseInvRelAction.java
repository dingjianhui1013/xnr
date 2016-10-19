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
import com.xnradmin.core.action.business.wareHouse.BusinessWareHouseInvRelAction;
import com.xnradmin.core.service.StaffService;
import com.xnradmin.core.service.business.commodity.BusinessCategoryService;
import com.xnradmin.core.service.business.commodity.BusinessGoodsService;
import com.xnradmin.core.service.business.commodity.BusinessWeightService;
import com.xnradmin.core.service.business.wareHouse.BusinessWareHouseInvRelService;
import com.xnradmin.core.service.business.wareHouse.BusinessWareHouseService;
import com.xnradmin.po.CommonStaff;
import com.xnradmin.po.business.BusinessCategory;
import com.xnradmin.po.business.BusinessGoods;
import com.xnradmin.po.business.BusinessWeight;
import com.xnradmin.po.business.wareHouse.BusinessWareHouse;
import com.xnradmin.po.business.wareHouse.BusinessWareHouseInvRel;
import com.xnradmin.po.common.status.Status;
import com.xnradmin.vo.StaffVO;
import com.xnradmin.vo.business.BusinessWareHouseVO;

/**
 * @autohr: xiang_liu
 * 
 */
@Controller
@Scope("prototype")
@Namespace("/page/business/admin/warehouse/wareHouseInvRel")
@ParentPackage("json-default")
public class BusinessWareHouseInvRelAction extends ParentAction {

	@Autowired
	private BusinessWareHouseInvRelService businessWareHouseInvRelService;
	
	@Autowired
	private BusinessWareHouseService businessWareHouseService;

	@Autowired
	private BusinessCategoryService businessCategoryService;
	
	@Autowired
	private BusinessGoodsService businessGoodsService;

	@Autowired
	private BusinessWeightService businessWeightService;
	
	@Autowired
	private StaffService staffService;

	private String wareHouseInvRelId;
    private String wareHouseInvRelWareHouseId;  //仓库Id
    private String wareHouseInvRelGoodsId;  //商品Id
    private String wareHouseInvRelCount;  //商品数量
    private String wareHouseInvRelGoodsCategoryId;  //商品类型Id
    private String wareHouseInvRelWeightId;  //商品单位Id
	private String createTime; // 建立时间
	private String createStartTime; // 建立开始时间
	private String createEndTime; // 建立结束时间
	private String createStaffId; // 建立人
	private String modifyTime; // 修改时间
	private String modifyStartTime; // 修改开始时间
	private String modifyEndTime; // 修改结束时间
	private String modifyStaffId; // 修改人
	private List<Status> wareHouseInvRelStatusList;
	private List<BusinessWareHouseInvRel> businessWareHouseInvRelList;
	private List<BusinessWareHouse> businessWareHouseList;
	private List<BusinessGoods> businessGoodsList;
	private List<BusinessCategory> businessCategoryList;
	private List<BusinessWeight> businessWeightList;
	private List<StaffVO> staffList;
	private CommonStaff currentStaff;
	private List<BusinessWareHouseVO> voList;
	private BusinessWareHouseVO businessWareHouseVO;
	private BusinessWareHouseInvRel wareHouseInvRel;
	private BusinessGoods goods;

	public String getWareHouseInvRelId() {
		return wareHouseInvRelId;
	}

	public void setWareHouseInvRelId(String wareHouseInvRelId) {
		this.wareHouseInvRelId = wareHouseInvRelId;
	}

	public String getWareHouseInvRelWareHouseId() {
		return wareHouseInvRelWareHouseId;
	}

	public void setWareHouseInvRelWareHouseId(String wareHouseInvRelWareHouseId) {
		this.wareHouseInvRelWareHouseId = wareHouseInvRelWareHouseId;
	}

	public String getWareHouseInvRelGoodsId() {
		return wareHouseInvRelGoodsId;
	}

	public void setWareHouseInvRelGoodsId(String wareHouseInvRelGoodsId) {
		this.wareHouseInvRelGoodsId = wareHouseInvRelGoodsId;
	}

	public String getWareHouseInvRelGoodsCategoryId() {
		return wareHouseInvRelGoodsCategoryId;
	}

	public void setWareHouseInvRelGoodsCategoryId(
			String wareHouseInvRelGoodsCategoryId) {
		this.wareHouseInvRelGoodsCategoryId = wareHouseInvRelGoodsCategoryId;
	}

	public String getWareHouseInvRelCount() {
		return wareHouseInvRelCount;
	}

	public void setWareHouseInvRelCount(String wareHouseInvRelCount) {
		this.wareHouseInvRelCount = wareHouseInvRelCount;
	}

	public BusinessWareHouseVO getBusinessWareHouseVO() {
		return businessWareHouseVO;
	}

	public void setBusinessWareHouseVO(BusinessWareHouseVO businessWareHouseVO) {
		this.businessWareHouseVO = businessWareHouseVO;
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

	public List<Status> getWareHouseInvRelStatusList() {
		return wareHouseInvRelStatusList;
	}

	public void setWareHouseInvRelStatusList(List<Status> wareHouseInvRelStatusList) {
		this.wareHouseInvRelStatusList = wareHouseInvRelStatusList;
	}

	public List<BusinessWareHouseInvRel> getBusinessWareHouseInvRelList() {
		return businessWareHouseInvRelList;
	}

	public void setBusinessWareHouseInvRelList(
			List<BusinessWareHouseInvRel> businessWareHouseInvRelList) {
		this.businessWareHouseInvRelList = businessWareHouseInvRelList;
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

	public BusinessWareHouseInvRel getWareHouseInvRel() {
		return wareHouseInvRel;
	}

	public void setWareHouseInvRel(BusinessWareHouseInvRel wareHouseInvRel) {
		this.wareHouseInvRel = wareHouseInvRel;
	}

	public List<BusinessWareHouse> getBusinessWareHouseList() {
		return businessWareHouseList;
	}

	public void setBusinessWareHouseList(
			List<BusinessWareHouse> businessWareHouseList) {
		this.businessWareHouseList = businessWareHouseList;
	}

	public List<BusinessGoods> getBusinessGoodsList() {
		return businessGoodsList;
	}

	public void setBusinessGoodsList(List<BusinessGoods> businessGoodsList) {
		this.businessGoodsList = businessGoodsList;
	}

	public List<BusinessCategory> getBusinessCategoryList() {
		return businessCategoryList;
	}

	public void setBusinessCategoryList(List<BusinessCategory> businessCategoryList) {
		this.businessCategoryList = businessCategoryList;
	}

	public BusinessGoods getGoods() {
		return goods;
	}

	public void setGoods(BusinessGoods goods) {
		this.goods = goods;
	}

	public String getWareHouseInvRelWeightId() {
		return wareHouseInvRelWeightId;
	}

	public void setWareHouseInvRelWeightId(String wareHouseInvRelWeightId) {
		this.wareHouseInvRelWeightId = wareHouseInvRelWeightId;
	}

	public List<BusinessWeight> getBusinessWeightList() {
		return businessWeightList;
	}

	public void setBusinessWeightList(List<BusinessWeight> businessWeightList) {
		this.businessWeightList = businessWeightList;
	}

	@Override
	public boolean isPublic() {
		return false;
	};

	static Log log = LogFactory.getLog(BusinessWareHouseInvRelAction.class);

	/**
	 * 跳转到信息页。。
	 * 
	 * @return
	 */
	@Action(value = "info", results = {
			@Result(name = StrutsResMSG.SUCCESS, location = "/business/admin/warehouse/wareHouseInvRel/info.jsp"),
			@Result(name = StrutsResMSG.FAILED, location = "/business/admin/warehouse/wareHouseInvRel/info.jsp") })
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
			@Result(name = StrutsResMSG.SUCCESS, location = "/business/admin/warehouse/wareHouseInvRel/lookup.jsp"),
			@Result(name = StrutsResMSG.FAILED, location = "/business/admin/warehouse/wareHouseInvRel/lookup.jsp") })
	public String lookup() {
		setPageInfo();
		return StrutsResMSG.SUCCESS;
	}
	
	private void setPageInfo() {
		// 设置排序
		findStaffList();
		// 加载所有仓库
		findBusinessWareHouse();
		// 加载所有商品
		findBusinessGoodsList();
		// 加载所有商品类型
		findBusinessCategoryList();
		// 加载所有单位
		findBusinessWeightList();
		BusinessWareHouseVO vo = new BusinessWareHouseVO();
		BusinessWareHouseInvRel bwir = new BusinessWareHouseInvRel();
		BusinessGoods goods = new BusinessGoods();
		if(!StringHelper.isNull(wareHouseInvRelId)){
			bwir.setId(Long.valueOf(wareHouseInvRelId));
		}
		if(!StringHelper.isNull(wareHouseInvRelWareHouseId)){
			bwir.setWareHouseId(Long.valueOf(wareHouseInvRelWareHouseId));
		}
		if (goods != null) {
			if (goods.getId() != null) {
				bwir.setGoodsId(Long.valueOf(goods.getId()));
			}
		}
		if(!StringHelper.isNull(wareHouseInvRelGoodsCategoryId)){
			goods.setGoodsCategoryId(wareHouseInvRelGoodsCategoryId);
		}
		if(!StringHelper.isNull(wareHouseInvRelWeightId)){
			goods.setGoodsWeightId(Integer.parseInt(wareHouseInvRelWeightId));
		}
		if(!StringHelper.isNull(wareHouseInvRelCount)){
			bwir.setCount(Long.valueOf(wareHouseInvRelCount));
		}
		if(!StringHelper.isNull(createStaffId)){
			bwir.setCreateStaffId(Integer.parseInt(createStaffId));
		}
		if(!StringHelper.isNull(modifyStaffId)){
			bwir.setModifyStaffId(Integer.parseInt(modifyStaffId));
		}
		vo.setBusinessWareHouseInvRel(bwir);
		vo.setBusinessGoods(goods);
		vo.setCreateStartTime(createStartTime);
		vo.setCreateEndTime(createEndTime);
		vo.setModifyStartTime(modifyStartTime);
		vo.setCreateEndTime(modifyEndTime);
		this.voList = businessWareHouseInvRelService.listVO(vo, getPageNum(), getNumPerPage(), orderField, orderDirection);
		this.totalCount = businessWareHouseInvRelService.getCount(vo);
	}

	/*
	 * 加载所有用户
	 */
	private void findStaffList() {
		this.staffList = staffService.listVO("","","", "", "", "", "", "", "", 0, 0,
				0, "", "");
	}
	
	/**
	 * 加载所有仓库
	 */
	private void findBusinessWareHouse() {
		this.businessWareHouseList = businessWareHouseService.listAll();
	}
	
	/**
	 * 加载所有商品
	 */
	private void findBusinessGoodsList() {
		this.businessGoodsList = businessGoodsService.listAll();
	}
	
	/**
	 * 加载所有商品类型
	 */
	private void findBusinessCategoryList() {
		this.businessCategoryList = businessCategoryService.listAll();
	}
	
	/**
	 * 加载所有单位
	 */
	private void findBusinessWeightList() {
		this.businessWeightList = businessWeightService.listAll();
	}

	@Action(value = "addinfo", results = {
			@Result(name = StrutsResMSG.SUCCESS, location = "/business/admin/warehouse/wareHouseInvRel/add.jsp"),
			@Result(name = StrutsResMSG.FAILED, location = "/business/admin/warehouse/wareHouseInvRel/add.jsp") })
	public String addInfo() {
		//加载所有仓库
		findBusinessWareHouse();
		//加载所有商品
		findBusinessGoodsList();
		//加载所有商品类型
		findBusinessCategoryList();
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
		BusinessWareHouseInvRel po = new BusinessWareHouseInvRel();
		if(!StringHelper.isNull(wareHouseInvRelWareHouseId)){
			po.setWareHouseId(Long.valueOf(wareHouseInvRelWareHouseId));
		}
		if(goods != null){
			if(goods.getId() != null){
				po.setGoodsId(Long.valueOf(goods.getId()));
			}
			if(goods.getGoodsWeightId() != null){
				po.setWeightId(goods.getGoodsWeightId());
			}
		}
		if(!StringHelper.isNull(wareHouseInvRelCount)){
			po.setCount(Long.valueOf(wareHouseInvRelCount));
		}
		
		po.setCreateStaffId(currentStaff.getId());
		po.setCreateTime(new Timestamp(System.currentTimeMillis()));
		po.setModifyStaffId(currentStaff.getId());
		po.setModifyTime(new Timestamp(System.currentTimeMillis()));
		businessWareHouseInvRelService.save(po);
		super.success(null, AjaxResult.CALL_BACK_TYPE_CLOSECURRENT, "businessWareHouseInvRel",
				null);
		return null;
	}

	/**
	 * 带信息到修改页
	 * 
	 * @return String
	 */
	@Action(value = "modifyinfo", results = {
			@Result(name = StrutsResMSG.SUCCESS, location = "/business/admin/warehouse/wareHouseInvRel/modify.jsp"),
			@Result(name = StrutsResMSG.FAILED, location = "/business/admin/warehouse/wareHouseInvRel/modify.jsp") })
	public String modifyinfo() {
		//加载所有仓库
		findBusinessWareHouse();
		//加载所有商品
		findBusinessGoodsList();
		//加载所有商品类型
		findBusinessCategoryList();
		BusinessWareHouseInvRel po = new BusinessWareHouseInvRel();
		po = businessWareHouseInvRelService.findByid(wareHouseInvRelId);
		businessWareHouseVO = new BusinessWareHouseVO();
		businessWareHouseVO.setBusinessWareHouseInvRel(po);
		for(int i = 0 ; businessGoodsList.size() > i ; i++){
			if(businessGoodsList.get(i).getId().toString().equals(po.getGoodsId().toString())){
				goods = businessGoodsList.get(i);
				break;
			}
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
		log.debug("modify wareHouseInvRel: " + wareHouseInvRelId);
		// 取得当前登录人信息
		currentStaff = super.getCurrentStaff();
		BusinessWareHouseInvRel po = businessWareHouseInvRelService
				.findByid(wareHouseInvRelId);
		if(!StringHelper.isNull(wareHouseInvRelWareHouseId)){
			po.setWareHouseId(Long.valueOf(wareHouseInvRelWareHouseId));
		}
		if(goods != null){
			if(goods.getId() != null){
				po.setGoodsId(Long.valueOf(goods.getId()));
			}
			if(goods.getGoodsWeightId() != null){
				po.setWeightId(goods.getGoodsWeightId());
			}
		}
		if(!StringHelper.isNull(wareHouseInvRelCount)){
			po.setCount(Long.valueOf(wareHouseInvRelCount));
		}
		po.setModifyStaffId(currentStaff.getId());
		po.setModifyTime(new Timestamp(System.currentTimeMillis()));
		this.businessWareHouseInvRelService.modify(po);
		super.success(null, AjaxResult.CALL_BACK_TYPE_CLOSECURRENT, "businessWareHouseInvRel",
				null);
		return null;
	}

	@Action(value = "del", results = { @Result(name = StrutsResMSG.SUCCESS, type = "plainText") })
	public String del() throws IOException, JSONException {
		businessWareHouseInvRelService
				.removeBusinessWareHouseInvRelId(wareHouseInvRelId);
		super.success(null, AjaxResult.CALL_BACK_TYPE_CLOSECURRENT, "businessWareHouseInvRel",
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
		super.toJsonArray(businessWareHouseInvRelService.listAll());
		return null;
	}
}
