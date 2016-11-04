/**
 *2012-5-10 下午3:52:29
 */
package com.xnradmin.core.action.stat;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.cntinker.util.StringHelper;
import com.xnradmin.constant.StrutsResMSG;
import com.xnradmin.core.action.ParentAction;
import com.xnradmin.core.service.StaffService;
import com.xnradmin.core.service.business.commodity.BusinessCategoryService;
import com.xnradmin.core.service.business.commodity.BusinessGoodsService;
import com.xnradmin.core.service.business.commodity.BusinessWeightService;
import com.xnradmin.core.service.business.wareHouse.BusinessWareHouseInvRelService;
import com.xnradmin.core.service.business.wareHouse.BusinessWareHouseService;
import com.xnradmin.core.service.common.status.StatusService;
import com.xnradmin.core.service.stat.BusinessWareHouseStatService;
import com.xnradmin.po.CommonStaff;
import com.xnradmin.po.business.BusinessCategory;
import com.xnradmin.po.business.BusinessGoods;
import com.xnradmin.po.business.BusinessWeight;
import com.xnradmin.po.business.wareHouse.BusinessWareHouse;
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
@Namespace("/page/stat/business/warehouse")
@ParentPackage("json-default")
public class BusinessWareHouseStatAction extends ParentAction {

	@Autowired
	private BusinessWareHouseStatService businessWareHouseStatService;

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
	private ArrayList<String[]> contentList;
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

	public ArrayList<String[]> getContentList() {
		return contentList;
	}

	public void setContentList(ArrayList<String[]> contentList) {
		this.contentList = contentList;
	}

	@Override
	public boolean isPublic() {
		return false;
	};

	static Log log = LogFactory.getLog(BusinessWareHouseStatAction.class);

	/**
	 * 跳转到信息页。。
	 * 
	 * @return
	 */
	@Action(value = "findWareHouseOperate", results = {
			@Result(name = StrutsResMSG.SUCCESS, location = "/stat/business/warehouse/findWareHouseOperate.jsp"),
			@Result(name = StrutsResMSG.FAILED, location = "/stat/business/warehouse/findWareHouseOperate.jsp") })
	public String findWareHouseOperate() {
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
		this.voList = businessWareHouseStatService.findWareHouseOperate(vo,
				getPageNum(), getNumPerPage(), orderField, orderDirection);
		this.totalCount = voList.size();
		return StrutsResMSG.SUCCESS;
	}

	/**
	 * 跳转到信息页。。
	 * 
	 * @return
	 */
	@Action(value = "findWareHousePrice", results = {
			@Result(name = StrutsResMSG.SUCCESS, location = "/stat/business/warehouse/findWareHousePrice.jsp"),
			@Result(name = StrutsResMSG.FAILED, location = "/stat/business/warehouse/findWareHousePrice.jsp") })
	public String findWareHousePrice() {
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
		List<Object[]> list = businessWareHouseStatService.findWareHousePrice(
				vo, getPageNum(), getNumPerPage());
		contentList = new ArrayList<String[]>();
		for (int i = 0; i < list.size(); i++) {
			String[] content = new String[3];
			Object[] a = list.get(i);
			if (a == null)
				continue;
			content[0] = a[0] == null ? "0" : a[0].toString();
			content[1] = a[1] == null ? "0" : a[1].toString();
			content[2] = a[2] == null ? "0" : a[2].toString();
			System.out.println(content[0]);
			System.out.println(content[1]);
			System.out.println(content[2]);
			contentList.add(content);
		}
		return StrutsResMSG.SUCCESS;
	}

	/**
	 * 跳转到信息页。。
	 * 
	 * @return
	 */
	@Action(value = "findInOrOutWareHouse", results = {
			@Result(name = StrutsResMSG.SUCCESS, location = "/stat/business/warehouse/findInOrOutWareHouse.jsp"),
			@Result(name = StrutsResMSG.FAILED, location = "/stat/business/warehouse/findInOrOutWareHouse.jsp") })
	public String findInOrOutWareHouse() {
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
		List<Object[]> list = businessWareHouseStatService
				.findInOrOutWareHouse(vo, getPageNum(), getNumPerPage());
		contentList = new ArrayList<String[]>();
		for (int i = 0; i < list.size(); i++) {
			String[] content = new String[6];
			Object[] a = list.get(i);
			if (a == null)
				continue;
			content[0] = a[0] == null ? "0" : a[0].toString();
			content[1] = a[1] == null ? "0" : a[1].toString();
			content[2] = a[2] == null ? "0" : a[2].toString();
			content[3] = a[3] == null ? "0" : a[3].toString();
			content[4] = a[4] == null ? "0" : a[4].toString();
			content[5] = a[5] == null ? "0" : a[5].toString();
			contentList.add(content);
		}
		return StrutsResMSG.SUCCESS;
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
		this.operationStatusList = statusService.find(
				BusinessWareHouseOperate.class, "wareHouseOperationStatus");
	}

	/**
	 * 加载仓库操作理由（采购，出售，退货，换货，自用，损耗）
	 */
	private void findReasonStatusList() {
		this.reasonStatusList = statusService.find(
				BusinessWareHouseOperate.class, "wareHouseReasonStatus");
	}

	/**
	 * 加载所有单位
	 */
	private void findBusinessWeightList() {
		this.businessWeightList = businessWeightService.listAll();
	}
}
