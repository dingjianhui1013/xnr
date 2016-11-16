/**
 *2012-5-10 下午3:52:29
 */
package com.xnradmin.core.action.business.wareHouse;

import java.io.IOException;
import java.math.BigDecimal;
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
import com.xnradmin.core.action.business.wareHouse.BusinessWareHouseOperateAction;
import com.xnradmin.core.service.StaffService;
import com.xnradmin.core.service.business.commodity.BusinessCategoryService;
import com.xnradmin.core.service.business.commodity.BusinessGoodsService;
import com.xnradmin.core.service.business.commodity.BusinessWeightService;
import com.xnradmin.core.service.business.wareHouse.BusinessWareHouseInvRelService;
import com.xnradmin.core.service.business.wareHouse.BusinessWareHouseOperateService;
import com.xnradmin.core.service.business.wareHouse.BusinessWareHouseService;
import com.xnradmin.core.service.common.status.StatusService;
import com.xnradmin.po.CommonStaff;
import com.xnradmin.po.business.BusinessCategory;
import com.xnradmin.po.business.BusinessGoods;
import com.xnradmin.po.business.BusinessOrderRecord;
import com.xnradmin.po.business.BusinessWeight;
import com.xnradmin.po.business.wareHouse.BusinessWareHouse;
import com.xnradmin.po.business.wareHouse.BusinessWareHouseInvRel;
import com.xnradmin.po.business.wareHouse.BusinessWareHouseOperate;
import com.xnradmin.po.common.status.Status;
import com.xnradmin.vo.StaffVO;
import com.xnradmin.vo.business.BusinessWareHouseVO;

/**
 * @autohr: xiang_liu
 * 
 */
@Controller
@Scope("prototype")
@Namespace("/page/business/admin/warehouse/wareHouseOperate")
@ParentPackage("json-default")
public class BusinessWareHouseOperateAction extends ParentAction {

	@Autowired
	private BusinessWareHouseOperateService businessWareHouseOperateService;

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
	private StatusService statusService;

	@Autowired
	private StaffService staffService;
	
	private String wareHouseOperateId;
	private String wareHouseId;// 仓库ID
	private String goodsId;// 商品ID
	private String categoryId;// 商品类型ID
	private String operationStatus;// 操作状态（入库，出库）
	private String reasonStatus;// 操作理由（采购，出售，退货，换货，自用，损耗）
	private String price;// 出入库价格
	private String count;// 出入库数量
	private String weightId;// 数量单位（与商品表数量单位统一）
	private String supplierStaffId;// 供货商ID
	private String purchaserStaffId;// 采购商ID
	private String remark;// 备注
	private String createTime; // 建立时间
	private String createStartTime; // 建立开始时间
	private String createEndTime; // 建立结束时间
	private String createStaffId; // 建立人
	private String modifyTime; // 修改时间
	private String modifyStartTime; // 修改开始时间
	private String modifyEndTime; // 修改结束时间
	private String modifyStaffId; // 修改人

	private List<Status> reasonStatusList;
	private List<BusinessWareHouseOperate> businessWareHouseOperateList;
	private List<BusinessWareHouse> businessWareHouseList;
	private List<BusinessGoods> businessGoodsList;
	private List<BusinessCategory> businessCategoryList;
	private List<BusinessWeight> businessWeightList;
	private List<Status> operationStatusList;
	private List<StaffVO> staffList;
	private List<StaffVO> supplierStaffList;
	private List<StaffVO> purchaserStaffList;
	private CommonStaff currentStaff;
	private CommonStaff supplierStaff;
	private CommonStaff purchaserStaff;
	private List<BusinessWareHouseVO> voList;
	private BusinessWareHouseVO businessWareHouseVO;
	private BusinessWareHouseOperate wareHouseOperate;
	private BusinessGoods goods;

	public String getWareHouseOperateId() {
		return wareHouseOperateId;
	}

	public void setWareHouseOperateId(String wareHouseOperateId) {
		this.wareHouseOperateId = wareHouseOperateId;
	}

	public String getWareHouseId() {
		return wareHouseId;
	}

	public void setWareHouseId(String wareHouseId) {
		this.wareHouseId = wareHouseId;
	}

	public String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getOperationStatus() {
		return operationStatus;
	}

	public void setOperationStatus(String operationStatus) {
		this.operationStatus = operationStatus;
	}

	public String getReasonStatus() {
		return reasonStatus;
	}

	public void setReasonStatus(String reasonStatus) {
		this.reasonStatus = reasonStatus;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public String getWeightId() {
		return weightId;
	}

	public void setWeightId(String weightId) {
		this.weightId = weightId;
	}

	public String getSupplierStaffId() {
		return supplierStaffId;
	}

	public void setSupplierStaffId(String supplierStaffId) {
		this.supplierStaffId = supplierStaffId;
	}

	public String getPurchaserStaffId() {
		return purchaserStaffId;
	}

	public void setPurchaserStaffId(String purchaserStaffId) {
		this.purchaserStaffId = purchaserStaffId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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

	public List<Status> getReasonStatusList() {
		return reasonStatusList;
	}

	public void setReasonStatusList(List<Status> reasonStatusList) {
		this.reasonStatusList = reasonStatusList;
	}

	public List<BusinessWareHouseOperate> getBusinessWareHouseOperateList() {
		return businessWareHouseOperateList;
	}

	public void setBusinessWareHouseOperateList(
			List<BusinessWareHouseOperate> businessWareHouseOperateList) {
		this.businessWareHouseOperateList = businessWareHouseOperateList;
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

	public void setBusinessCategoryList(
			List<BusinessCategory> businessCategoryList) {
		this.businessCategoryList = businessCategoryList;
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

	public BusinessWareHouseOperate getWareHouseOperate() {
		return wareHouseOperate;
	}

	public void setWareHouseOperate(BusinessWareHouseOperate wareHouseOperate) {
		this.wareHouseOperate = wareHouseOperate;
	}

	public BusinessGoods getGoods() {
		return goods;
	}

	public void setGoods(BusinessGoods goods) {
		this.goods = goods;
	}

	public List<Status> getOperationStatusList() {
		return operationStatusList;
	}

	public void setOperationStatusList(List<Status> operationStatusList) {
		this.operationStatusList = operationStatusList;
	}

	public CommonStaff getSupplierStaff() {
		return supplierStaff;
	}

	public void setSupplierStaff(CommonStaff supplierStaff) {
		this.supplierStaff = supplierStaff;
	}

	public CommonStaff getPurchaserStaff() {
		return purchaserStaff;
	}

	public void setPurchaserStaff(CommonStaff purchaserStaff) {
		this.purchaserStaff = purchaserStaff;
	}

	public List<StaffVO> getSupplierStaffList() {
		return supplierStaffList;
	}

	public void setSupplierStaffList(List<StaffVO> supplierStaffList) {
		this.supplierStaffList = supplierStaffList;
	}

	public List<StaffVO> getPurchaserStaffList() {
		return purchaserStaffList;
	}

	public void setPurchaserStaffList(List<StaffVO> purchaserStaffList) {
		this.purchaserStaffList = purchaserStaffList;
	}

	@Override
	public boolean isPublic() {
		return false;
	};

	static Log log = LogFactory.getLog(BusinessWareHouseOperateAction.class);

	/**
	 * 跳转到信息页。。
	 * 
	 * @return
	 */
	@Action(value = "info", results = {
			@Result(name = StrutsResMSG.SUCCESS, location = "/business/admin/warehouse/wareHouseOperate/info.jsp"),
			@Result(name = StrutsResMSG.FAILED, location = "/business/admin/warehouse/wareHouseOperate/info.jsp") })
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
			@Result(name = StrutsResMSG.SUCCESS, location = "/business/admin/warehouse/wareHouseOperate/lookup.jsp"),
			@Result(name = StrutsResMSG.FAILED, location = "/business/admin/warehouse/wareHouseOperate/lookup.jsp") })
	public String lookup() {
		setPageInfo();
		return StrutsResMSG.SUCCESS;
	}

	private void setPageInfo() {
		// 设置排序
		findStaffList();
		// 加载仓库
		findBusinessWareHouse();
		// 加载商品分类
		findBusinessCategoryList();
		// 加载商品
		findBusinessGoodsList();
		// 加载操作类型
		findOperationStatusList();
		// 加载操作理由
		findReasonStatusList();
		// 加载商品单位
		findBusinessWeightList();
		
		BusinessWareHouseVO vo = new BusinessWareHouseVO();
		BusinessWareHouseOperate bwho = new BusinessWareHouseOperate();
		if (!StringHelper.isNull(wareHouseOperateId)) {
			bwho.setId(Long.valueOf(wareHouseOperateId));
		}
		if (!StringHelper.isNull(wareHouseId)) {
			bwho.setWareHouseId(Long.valueOf(wareHouseId));
		}
		if (goods != null) {
			if (goods.getId() != null) {
				bwho.setGoodsId(Long.valueOf(goods.getId()));
			}
		}
		if (!StringHelper.isNull(categoryId)) {
			bwho.setCategoryId(categoryId);
		}
		if (!StringHelper.isNull(weightId)) {
			bwho.setWeightId(Integer.parseInt(weightId));
		}
		if (!StringHelper.isNull(price)) {
			bwho.setPrice(Float.valueOf(price));
		}
		if (!StringHelper.isNull(count)) {
			bwho.setCount(Long.valueOf(count));
		}
		if (!StringHelper.isNull(operationStatus)) {
			bwho.setOperationStatus(Integer.parseInt(operationStatus));
		}
		if (!StringHelper.isNull(reasonStatus)) {
			bwho.setReasonStatus(Integer.parseInt(reasonStatus));
		}
		if (supplierStaff != null) {
			bwho.setSupplierStaffId(supplierStaff.getId());
		}
		if (purchaserStaff != null) {
			bwho.setPurchaserStaffId(purchaserStaff.getId());
		}
		if (!StringHelper.isNull(remark)) {
			bwho.setRemark(remark);
		}
		if (!StringHelper.isNull(createStaffId)) {
			bwho.setCreateStaffId(Integer.parseInt(createStaffId));
		}
		if (!StringHelper.isNull(modifyStaffId)) {
			bwho.setModifyStaffId(Integer.parseInt(modifyStaffId));
		}
		vo.setBusinessWareHouseOperate(bwho);
		vo.setCreateStartTime(createStartTime);
		vo.setCreateEndTime(createEndTime);
		vo.setModifyStartTime(modifyStartTime);
		vo.setCreateEndTime(modifyEndTime);
		this.voList = businessWareHouseOperateService.listVO(vo, getPageNum(),
				getNumPerPage(), orderField, orderDirection);
		this.totalCount = businessWareHouseOperateService.getCount(vo);
	}

	/*
	 * 加载所有用户
	 */
	private void findStaffList() {
		this.staffList = staffService.listVO("", "", "", "", "", "", "", "",
				"", 0, 0, 0, "", "");
	}

	/**
	 * 加载所有仓库
	 */
	private void findBusinessWareHouse() {
		this.businessWareHouseList = businessWareHouseService.listAll();
	}

	/**
	 * 加载所有商品分类
	 */
	private void findBusinessCategoryList() {
		this.businessCategoryList = businessCategoryService.listAll();
	}
	
	/**
	 * 加载所有商品
	 */
	private void findBusinessGoodsList() {
		this.businessGoodsList = businessGoodsService.listAll();
	}

	/**
	 * 加载仓库操作（入库出库）
	 */
	private void findOperationStatusList() {
		this.operationStatusList = statusService.find(BusinessWareHouseOperate.class,
				"wareHouseOperationStatus");
	}
	
	/**
	 * 加载仓库操作理由（采购，出售，退货，换货，自用，损耗）
	 */
	private void findReasonStatusList() {
		this.reasonStatusList = statusService.find(BusinessWareHouseOperate.class,
				"wareHouseReasonStatus");
	}
	
	/**
	 * 加载所有单位
	 */
	private void findBusinessWeightList() {
		this.businessWeightList = businessWeightService.listAll();
	}
	/**
	 * 跳转到信息页。。
	 * 
	 * @return
	 */
	@Action(value = "supplierStaffLookUp", results = {
			@Result(name = StrutsResMSG.SUCCESS, location = "/business/admin/warehouse/wareHouseOperate/supplierStaffLookUp.jsp"),
			@Result(name = StrutsResMSG.FAILED, location = "/business/admin/warehouse/wareHouseOperate/supplierStaffLookUp.jsp") })
	public String supplierStaffLookUp() {
		this.supplierStaffList = staffService.listVO(null, null, null, "18", null, null, null, null, null, null, getPageNum(),
				getNumPerPage(), orderField, orderDirection);
		this.totalCount = staffService.getCount(null, null, null, "18", null, null, null, null, null, null, null, null);
		return StrutsResMSG.SUCCESS;
	}
	
	/**
	 * 跳转到信息页。。
	 * 
	 * @return
	 */
	@Action(value = "purchaserStaffLookUp", results = {
			@Result(name = StrutsResMSG.SUCCESS, location = "/business/admin/warehouse/wareHouseOperate/purchaserStaffLookUp.jsp"),
			@Result(name = StrutsResMSG.FAILED, location = "/business/admin/warehouse/wareHouseOperate/purchaserStaffLookUp.jsp") })
	public String purchaserStaffLookUp() {
		this.purchaserStaffList = staffService.listVO(null, null, null, "15", null, null, null, null, null, null, getPageNum(),
				getNumPerPage(), orderField, orderDirection);
		this.totalCount = staffService.getCount(null, null, null, "15", null, null, null, null, null, null, null, null);
		return StrutsResMSG.SUCCESS;
	}
	
	
	@Action(value = "addinfo", results = {
			@Result(name = StrutsResMSG.SUCCESS, location = "/business/admin/warehouse/wareHouseOperate/add.jsp"),
			@Result(name = StrutsResMSG.FAILED, location = "/business/admin/warehouse/wareHouseOperate/add.jsp") })
	public String addInfo() {
		// 加载所有仓库
		findBusinessWareHouse();
		// 加载所有商品
		findBusinessGoodsList();
		findReasonStatusList();
		findOperationStatusList();
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
		BusinessWareHouseOperate bwho = new BusinessWareHouseOperate();

		if (!StringHelper.isNull(wareHouseOperateId)) {
			bwho.setId(Long.valueOf(wareHouseOperateId));
		}
		if (!StringHelper.isNull(wareHouseId)) {
			bwho.setWareHouseId(Long.valueOf(wareHouseId));
		}
		if (goods != null) {
			if (goods.getId() != null) {
				bwho.setGoodsId(Long.valueOf(goods.getId()));
			}
			if (goods.getGoodsCategoryId() != null) {
				bwho.setCategoryId(goods.getGoodsCategoryId());
			}
			if (goods.getGoodsWeightId() != null) {
				bwho.setWeightId(goods.getGoodsWeightId());
			}
		}
		if (!StringHelper.isNull(price)) {
			bwho.setPrice(Float.valueOf(price));
		}
		if (!StringHelper.isNull(count)) {
			bwho.setCount(Long.valueOf(count));
		}
		if (!StringHelper.isNull(operationStatus)) {
			bwho.setOperationStatus(Integer.parseInt(operationStatus));
		}
		if (!StringHelper.isNull(reasonStatus)) {
			bwho.setReasonStatus(Integer.parseInt(reasonStatus));
		}
		if (supplierStaff != null) {
			bwho.setSupplierStaffId(supplierStaff.getId());
		}
		if (purchaserStaff != null) {
			bwho.setPurchaserStaffId(purchaserStaff.getId());
		}
		if (!StringHelper.isNull(remark)) {
			bwho.setRemark(remark);
		}
		bwho.setCreateStaffId(currentStaff.getId());
		bwho.setCreateTime(new Timestamp(System.currentTimeMillis()));
		bwho.setModifyStaffId(currentStaff.getId());
		bwho.setModifyTime(new Timestamp(System.currentTimeMillis()));
		Long flag = businessWareHouseOperateService.save(bwho);
		if (flag != null) {
			List<BusinessWareHouseInvRel> bwhir = businessWareHouseInvRelService
					.findByGoodsId(bwho.getGoodsId().toString());
			// 如果是商品出库，直接更新仓库总表
			if (bwho.getOperationStatus() == 225) {// 出库{
				BusinessWareHouseInvRel temp = bwhir.get(0);
				BigDecimal r1 = new BigDecimal(temp.getCount());
				BigDecimal r2 = new BigDecimal(bwho.getCount());
				temp.setCount(r1.subtract(r2).longValue());
				temp.setWeightId(bwho.getWeightId());
				businessWareHouseInvRelService.modify(temp);
			} 
			//如果是商品入库则判定是否总表已经存在该商品
			else if (bwho.getOperationStatus() == 226) {// 入库
				//如果存在该商品则更新总表
				if (bwhir != null && bwhir.size() > 0) {
					BusinessWareHouseInvRel temp = bwhir.get(0);
					BigDecimal r1 = new BigDecimal(temp.getCount());
					BigDecimal r2 = new BigDecimal(bwho.getCount());
					temp.setCount(r1.add(r2).longValue());
					temp.setWeightId(bwho.getWeightId());
					businessWareHouseInvRelService.modify(temp);
				}
				//如果不存在该商品则插入总表
				else{
					BusinessWareHouseInvRel temp = new BusinessWareHouseInvRel();
					temp.setCount(bwho.getCount());
					temp.setGoodsId(bwho.getGoodsId());
					temp.setWareHouseId(bwho.getWareHouseId());
					temp.setWeightId(bwho.getWeightId());
					temp.setCreateStaffId(currentStaff.getId());
					temp.setCreateTime(new Timestamp(System.currentTimeMillis()));
					temp.setModifyStaffId(currentStaff.getId());
					temp.setModifyTime(new Timestamp(System.currentTimeMillis()));
					businessWareHouseInvRelService.save(temp);
				}
			}
		}
		super.success(null, AjaxResult.CALL_BACK_TYPE_CLOSECURRENT,
				"businessWareHouseOperate", null);
		return null;
	}

	/**
	 * 带信息到修改页
	 * 
	 * @return String
	 */
	@Action(value = "modifyinfo", results = {
			@Result(name = StrutsResMSG.SUCCESS, location = "/business/admin/warehouse/wareHouseOperate/modify.jsp"),
			@Result(name = StrutsResMSG.FAILED, location = "/business/admin/warehouse/wareHouseOperate/modify.jsp") })
	public String modifyinfo() {
		// 加载所有仓库
		findBusinessWareHouse();
		// 加载所有商品
		findBusinessGoodsList();
		BusinessWareHouseOperate po = new BusinessWareHouseOperate();
		po = businessWareHouseOperateService.findByid(wareHouseOperateId);
		businessWareHouseVO = new BusinessWareHouseVO();
		businessWareHouseVO.setBusinessWareHouseOperate(po);
		for (int i = 0; businessGoodsList.size() > i; i++) {
			if (businessGoodsList.get(i).getId().toString()
					.equals(po.getGoodsId().toString())) {
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
		log.debug("modify wareHouseOperate: " + wareHouseOperateId);
		// 取得当前登录人信息
		currentStaff = super.getCurrentStaff();
		BusinessWareHouseOperate bwho = businessWareHouseOperateService
				.findByid(wareHouseOperateId);
		if (!StringHelper.isNull(wareHouseOperateId)) {
			bwho.setId(Long.valueOf(wareHouseOperateId));
		}
		if (!StringHelper.isNull(wareHouseId)) {
			bwho.setWareHouseId(Long.valueOf(wareHouseId));
		}
		if (goods != null) {
			if (goods.getId() != null) {
				bwho.setGoodsId(Long.valueOf(goods.getId()));
			}
			if (goods.getGoodsCategoryId() != null) {
				bwho.setCategoryId(goods.getGoodsCategoryId());
			}
			if (goods.getGoodsWeightId() != null) {
				bwho.setWeightId(goods.getGoodsWeightId());
			}
		}
		if (!StringHelper.isNull(price)) {
			bwho.setPrice(Float.valueOf(price));
		}
		if (!StringHelper.isNull(count)) {
			bwho.setCount(Long.valueOf(count));
		}
		if (!StringHelper.isNull(operationStatus)) {
			bwho.setOperationStatus(Integer.parseInt(operationStatus));
		}
		if (!StringHelper.isNull(reasonStatus)) {
			bwho.setReasonStatus(Integer.parseInt(reasonStatus));
		}
		if (supplierStaff != null) {
			bwho.setSupplierStaffId(supplierStaff.getId());
		}
		if (purchaserStaff != null) {
			bwho.setPurchaserStaffId(purchaserStaff.getId());
		}
		if (!StringHelper.isNull(remark)) {
			bwho.setRemark(remark);
		}
		bwho.setModifyStaffId(currentStaff.getId());
		bwho.setModifyTime(new Timestamp(System.currentTimeMillis()));
		this.businessWareHouseOperateService.modify(bwho);
		super.success(null, AjaxResult.CALL_BACK_TYPE_CLOSECURRENT,
				"businessWareHouseOperate", null);
		return null;
	}

	@Action(value = "del", results = { @Result(name = StrutsResMSG.SUCCESS, type = "plainText") })
	public String del() throws IOException, JSONException {
		businessWareHouseOperateService
				.removeBusinessWareHouseOperateId(wareHouseOperateId);
		super.success(null, AjaxResult.CALL_BACK_TYPE_CLOSECURRENT,
				"businessWareHouseOperate", null);
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
		super.toJsonArray(businessWareHouseOperateService.listAll());
		return null;
	}
}
