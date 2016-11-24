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
import com.xnradmin.core.service.common.status.StatusService;
import com.xnradmin.core.service.mall.seting.PrimaryConfigurationService;
import com.xnradmin.po.CommonStaff;
import com.xnradmin.po.business.BusinessCategory;
import com.xnradmin.po.common.status.Status;
import com.xnradmin.po.mall.seting.PrimaryConfiguration;
import com.xnradmin.vo.StaffVO;
import com.xnradmin.vo.business.BusinessGoodsVO;
import com.xnradmin.vo.business.BusinessOrderVO;

/**
 * @autohr: xiang_liu
 * 
 */
@Controller
@Scope("prototype")
@Namespace("/page/business/admin/commodity/category")
@ParentPackage("json-default")
public class BusinessCategoryAction extends ParentAction {

	@Autowired
	private BusinessCategoryService businessCategoryService;
	
	@Autowired
	private PrimaryConfigurationService primaryConfigurationService;

	@Autowired
	private StatusService statusService;

	@Autowired
	private StaffService staffService;

	private String categoryId;
    private String categorySortId;  //排序Id
    private String categoryParentId;  //上级分类Id
    private String categoryLevel; //分类等级
    private String firstLevel; //是否是根分类
    private String categoryName;  //分类名称
    private String categoryLogo; //分类图片
    private File categoryLogoFile;	//分类图片上传文件
    private String categoryLogoFileFileName; //分类图片名称
    private String categoryLogoFileContentType;
    private String categoryHeadLogo; //分类置顶图片
    private File categoryHeadLogoFile;	//分类置顶图片
    private String categoryHeadLogoFileFileName; //分类置顶图片名称
    private String categoryHeadLogoFileContentType;
    private String categoryDescription;	//分类描述
    private String categoryStatus;  //分类状态
	private String staffId; // 隶属用户ID
	private String primaryConfigurationId; //对应商城ID
	private String createTime; // 建立时间
	private String createStartTime; // 建立开始时间
	private String createEndTime; // 建立结束时间
	private String createStaffId; // 建立人
	private String modifyTime; // 修改时间
	private String modifyStartTime; // 修改开始时间
	private String modifyEndTime; // 修改结束时间
	private String modifyStaffId; // 修改人
	private List<Status> categoryStatusList;
	private List<PrimaryConfiguration> primaryConfigurationList;
	private List<BusinessCategory> businessCategoryList;
	private List<StaffVO> staffList;
	private CommonStaff currentStaff;
	private List<BusinessGoodsVO> voList;
	private BusinessGoodsVO businessGoodsVO;
	private BusinessCategory category;
	private BusinessCategory categoryParent;
	private PrimaryConfiguration primaryConfiguration;
	private Status status;
	private String categoryLogoSavePath = "/themes/business/categoryLogo/";
	private String categoryHeadLogoSavePath = "/themes/business/categoryheadLogo/";

	public String getPrimaryConfigurationId() {
		return primaryConfigurationId;
	}

	public void setPrimaryConfigurationId(String primaryConfigurationId) {
		this.primaryConfigurationId = primaryConfigurationId;
	}

	public PrimaryConfigurationService getPrimaryConfigurationService() {
		return primaryConfigurationService;
	}

	public void setPrimaryConfigurationService(
			PrimaryConfigurationService primaryConfigurationService) {
		this.primaryConfigurationService = primaryConfigurationService;
	}

	public StatusService getStatusService() {
		return statusService;
	}

	public void setStatusService(StatusService statusService) {
		this.statusService = statusService;
	}

	public StaffService getStaffService() {
		return staffService;
	}

	public void setStaffService(StaffService staffService) {
		this.staffService = staffService;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategorySortId() {
		return categorySortId;
	}

	public void setCategorySortId(String categorySortId) {
		this.categorySortId = categorySortId;
	}

	public String getCategoryParentId() {
		return categoryParentId;
	}

	public void setCategoryParentId(String categoryParentId) {
		this.categoryParentId = categoryParentId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public File getCategoryLogoFile() {
		return categoryLogoFile;
	}

	public void setCategoryLogoFile(File categoryLogoFile) {
		this.categoryLogoFile = categoryLogoFile;
	}

	public File getCategoryHeadLogoFile() {
		return categoryHeadLogoFile;
	}

	public void setCategoryHeadLogoFile(File categoryHeadLogoFile) {
		this.categoryHeadLogoFile = categoryHeadLogoFile;
	}

	public String getCategoryDescription() {
		return categoryDescription;
	}

	public void setCategoryDescription(String categoryDescription) {
		this.categoryDescription = categoryDescription;
	}

	public String getCategoryStatus() {
		return categoryStatus;
	}

	public void setCategoryStatus(String categoryStatus) {
		this.categoryStatus = categoryStatus;
	}

	public List<Status> getCategoryStatusList() {
		return categoryStatusList;
	}

	public void setCategoryStatusList(List<Status> categoryStatusList) {
		this.categoryStatusList = categoryStatusList;
	}

	public List<BusinessCategory> getBusinessCategoryList() {
		return businessCategoryList;
	}

	public void setBusinessCategoryList(List<BusinessCategory> businessCategoryList) {
		this.businessCategoryList = businessCategoryList;
	}

	public BusinessGoodsVO getBusinessGoodsVO() {
		return businessGoodsVO;
	}

	public void setBusinessGoodsVO(BusinessGoodsVO businessGoodsVO) {
		this.businessGoodsVO = businessGoodsVO;
	}

	public BusinessCategory getCategory() {
		return category;
	}

	public void setCategory(BusinessCategory category) {
		this.category = category;
	}

	public void setCategoryParent(BusinessCategory categoryParent) {
		this.categoryParent = categoryParent;
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

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
	
	public String getCategoryLogoFileFileName() {
		return categoryLogoFileFileName;
	}

	public void setCategoryLogoFileFileName(String categoryLogoFileFileName) {
		this.categoryLogoFileFileName = categoryLogoFileFileName;
	}

	public String getCategoryHeadLogoFileFileName() {
		return categoryHeadLogoFileFileName;
	}

	public void setCategoryHeadLogoFileFileName(String categoryHeadLogoFileFileName) {
		this.categoryHeadLogoFileFileName = categoryHeadLogoFileFileName;
	}

	public String getCategoryLevel() {
		return categoryLevel;
	}

	public void setCategoryLevel(String categoryLevel) {
		this.categoryLevel = categoryLevel;
	}

	public String getCategoryLogo() {
		return categoryLogo;
	}

	public void setCategoryLogo(String categoryLogo) {
		this.categoryLogo = categoryLogo;
	}

	public String getCategoryHeadLogo() {
		return categoryHeadLogo;
	}

	public void setCategoryHeadLogo(String categoryHeadLogo) {
		this.categoryHeadLogo = categoryHeadLogo;
	}

	public List<PrimaryConfiguration> getPrimaryConfigurationList() {
		return primaryConfigurationList;
	}

	public void setPrimaryConfigurationList(
			List<PrimaryConfiguration> primaryConfigurationList) {
		this.primaryConfigurationList = primaryConfigurationList;
	}

	public BusinessCategory getCategoryParent() {
		return categoryParent;
	}

	public PrimaryConfiguration getPrimaryConfiguration() {
		return primaryConfiguration;
	}

	public void setPrimaryConfiguration(PrimaryConfiguration primaryConfiguration) {
		this.primaryConfiguration = primaryConfiguration;
	}

	public String getCategoryLogoFileContentType() {
		return categoryLogoFileContentType;
	}

	public void setCategoryLogoFileContentType(String categoryLogoFileContentType) {
		this.categoryLogoFileContentType = categoryLogoFileContentType;
	}

	public String getCategoryHeadLogoFileContentType() {
		return categoryHeadLogoFileContentType;
	}

	public void setCategoryHeadLogoFileContentType(
			String categoryHeadLogoFileContentType) {
		this.categoryHeadLogoFileContentType = categoryHeadLogoFileContentType;
	}

	public void setCategoryLogoSavePath(String categoryLogoSavePath) {
		this.categoryLogoSavePath = categoryLogoSavePath;
	}

	public void setCategoryHeadLogoSavePath(String categoryHeadLogoSavePath) {
		this.categoryHeadLogoSavePath = categoryHeadLogoSavePath;
	}

	public String getFirstLevel() {
		return firstLevel;
	}

	public void setFirstLevel(String firstLevel) {
		this.firstLevel = firstLevel;
	}

	public List<BusinessGoodsVO> getVoList() {
		return voList;
	}

	public void setVoList(List<BusinessGoodsVO> voList) {
		this.voList = voList;
	}

	@Override
	public boolean isPublic() {
		return false;
	};

	static Log log = LogFactory.getLog(BusinessCategoryAction.class);

	/**
	 * 跳转到信息页。。
	 * 
	 * @return
	 */
	@Action(value = "info", results = {
			@Result(name = StrutsResMSG.SUCCESS, location = "/business/admin/commodity/category/info.jsp"),
			@Result(name = StrutsResMSG.FAILED, location = "/business/admin/commodity/category/info.jsp") })
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
			@Result(name = StrutsResMSG.SUCCESS, location = "/business/admin/commodity/category/lookup.jsp"),
			@Result(name = StrutsResMSG.FAILED, location = "/business/admin/commodity/category/lookup.jsp") })
	public String lookup() {
		setPageInfo();
		return StrutsResMSG.SUCCESS;
	}
	
	private void setPageInfo() {
		// 设置排序
		findBusinessCategoryStatusList();
		findStaffList();
		findPrimaryConfigurationList();
		findBusinessCategoryList();
		BusinessGoodsVO vo = new BusinessGoodsVO();
		BusinessCategory bc = new BusinessCategory();
		if(!StringHelper.isNull(categoryParentId)){
			bc.setCategoryParentId(Integer.parseInt(categoryParentId));
		}
		bc.setCategoryName(categoryName);
		if(!StringHelper.isNull(categoryLevel)){
			bc.setCategoryLevel(Integer.parseInt(categoryLevel));
		}
		if(!StringHelper.isNull(categoryStatus)){
			bc.setCategoryStatus(Integer.parseInt(categoryStatus));
		}
		if(!StringHelper.isNull(primaryConfigurationId)){
			bc.setPrimaryConfigurationId(Integer.parseInt(primaryConfigurationId));
		}
		if(!StringHelper.isNull(createStaffId)){
			bc.setCreateStaffId(Integer.parseInt(createStaffId));
		}
		if(!StringHelper.isNull(modifyStaffId)){
			bc.setModifyStaffId(Integer.parseInt(modifyStaffId));
		}
		vo.setBusinessCategory(bc);
		vo.setCreateStartTime(createStartTime);
		vo.setCreateEndTime(createEndTime);
		vo.setModifyStartTime(modifyStartTime);
		vo.setCreateEndTime(modifyEndTime);
		this.voList = businessCategoryService.listVO(vo, getPageNum(), getNumPerPage(), orderField, orderDirection);
		this.totalCount = businessCategoryService.getCount(vo);
	}

	/**
	 * 加载类型状态
	 */
	private void findBusinessCategoryStatusList() {
		this.categoryStatusList = statusService.find(BusinessCategory.class,
				"businessCategoryStatus");
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
	 * 加载所有分类
	 */
	private void findBusinessCategoryList() {
		this.businessCategoryList = businessCategoryService.listAll();
	}
	
	/*
	 * 加载分类图片上传路径
	 */
	public String getCategoryLogoSavePath() throws Exception {
//		return "C:/work/eclipse-jee-kepler-R-win32-x86_64/eclipse/javaworkspace/xnradmin/xnradmin-web"
//				+ "/src/main/webapp/themes/business/categoryLogo";
		return ServletActionContext.getServletContext().getRealPath("")
				+ categoryLogoSavePath;
	}
	
	/*
	 * 加载分类置顶图片上传路径
	 */
	public String getCategoryHeadLogoSavePath() throws Exception {
//		return "C:/work/eclipse-jee-kepler-R-win32-x86_64/eclipse/javaworkspace/xnradmin/xnradmin-web"
//				+ "/src/main/webapp/themes/business/categoryheadLogo";
		return ServletActionContext.getServletContext().getRealPath("")
				+ categoryHeadLogoSavePath;
	}

	@Action(value = "addinfo", results = {
			@Result(name = StrutsResMSG.SUCCESS, location = "/business/admin/commodity/category/add.jsp"),
			@Result(name = StrutsResMSG.FAILED, location = "/business/admin/commodity/category/add.jsp") })
	public String addInfo() {
		// 加载状态与类型
		findBusinessCategoryStatusList();
		findPrimaryConfigurationList();
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
		String newCategoryLogo = "";
		if (categoryLogoFile != null) {
			categoryLogoFileFileName = UUID.randomUUID().toString() + categoryLogoFileFileName;
			newCategoryLogo = getCategoryLogoSavePath() + "/" +categoryLogoFileFileName;
			FileOutputStream fos = new FileOutputStream(newCategoryLogo);
			FileInputStream fis = new FileInputStream(categoryLogoFile);
			byte[] buffer = new byte[1024];
			int len = 0;
			while ((len = fis.read(buffer)) > 0) {
				fos.write(buffer, 0, len);
			}
			fos.close();
			fis.close();
		}

		String newCategoryHeadLogo = "";
		if (categoryHeadLogoFile != null) {
			categoryHeadLogoFileFileName = UUID.randomUUID().toString()
					+ categoryHeadLogoFileFileName;
			newCategoryHeadLogo = getCategoryHeadLogoSavePath() + "/" +categoryHeadLogoFileFileName;
			FileOutputStream fos = new FileOutputStream(newCategoryHeadLogo);
			FileInputStream fis = new FileInputStream(categoryHeadLogoFile);
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
		BusinessCategory po = new BusinessCategory();
		if(!StringHelper.isNull(categorySortId)){
			po.setSortId(Integer.parseInt(categorySortId));
		}
		if(categoryParent!=null){
			if(categoryParent.getId()!=null){
				po.setCategoryParentId(categoryParent.getId());
			}
			else{
				po.setCategoryParentId(0);
			}
			if(categoryParent.getCategoryLevel()!=null){
				po.setCategoryLevel(categoryParent.getCategoryLevel()+1);
			}
			else{
				po.setCategoryLevel(0);
			}
			if(categoryParent.getPrimaryConfigurationId()!=null){
				po.setPrimaryConfigurationId(categoryParent.getPrimaryConfigurationId());
			}
			else{
				if(primaryConfiguration != null){
					po.setPrimaryConfigurationId(primaryConfiguration.getId());
				}
			}
		}
		else{
			po.setCategoryParentId(0);
			po.setCategoryLevel(0);
			if(primaryConfigurationId != null){
				po.setPrimaryConfigurationId(Integer.parseInt(primaryConfigurationId));
			}
		}
		po.setCategoryName(categoryName);;
		if(categoryLogoFile != null){
			po.setCategoryLogo(categoryLogoSavePath+categoryLogoFileFileName);
		}
		if(categoryHeadLogoFile != null){
			po.setCategoryHeadLogo(categoryHeadLogoSavePath+categoryHeadLogoFileFileName);
		}
		po.setCategoryDescription(categoryDescription);
		if (categoryStatus != null) {
			po.setCategoryStatus(Integer
					.parseInt(categoryStatus));
		}
		po.setCreateStaffId(currentStaff.getId());
		po.setCreateTime(new Timestamp(System.currentTimeMillis()));
		po.setModifyStaffId(currentStaff.getId());
		po.setModifyTime(new Timestamp(System.currentTimeMillis()));
		businessCategoryService.save(po);
		super.success(null, AjaxResult.CALL_BACK_TYPE_CLOSECURRENT, "businessCategory",
				null);
		return null;
	}

	/**
	 * 带信息到修改页
	 * 
	 * @return String
	 */
	@Action(value = "modifyinfo", results = {
			@Result(name = StrutsResMSG.SUCCESS, location = "/business/admin/commodity/category/modify.jsp"),
			@Result(name = StrutsResMSG.FAILED, location = "/business/admin/commodity/category/modify.jsp") })
	public String modifyinfo() {
		findBusinessCategoryStatusList();
		findPrimaryConfigurationList();
		BusinessCategory po = new BusinessCategory();
		po = businessCategoryService.findByid(categoryId);
		if(po.getCategoryParentId() != null){
			this.categoryParent = businessCategoryService.findByid(po.getCategoryParentId().toString());
		}
		businessGoodsVO = new BusinessGoodsVO();
		businessGoodsVO.setBusinessCategory(po);
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
		log.debug("modify category: " + categoryId);
		String newCategoryLogo = "";
		if (categoryLogoFile != null) {
			categoryLogoFileFileName = UUID.randomUUID().toString() + categoryLogoFileFileName;
			newCategoryLogo = getCategoryLogoSavePath() + "/" +categoryLogoFileFileName;
			FileOutputStream fos = new FileOutputStream(newCategoryLogo);
			FileInputStream fis = new FileInputStream(categoryLogoFile);
			byte[] buffer = new byte[1024];
			int len = 0;
			while ((len = fis.read(buffer)) > 0) {
				fos.write(buffer, 0, len);
			}
			fos.close();
			fis.close();
		}

		String newCategoryHeadLogo = "";
		if (categoryHeadLogoFile != null) {
			categoryHeadLogoFileFileName = UUID.randomUUID().toString()
					+ categoryHeadLogoFileFileName;
			newCategoryHeadLogo = getCategoryHeadLogoSavePath() + "/" +categoryHeadLogoFileFileName;
			FileOutputStream fos = new FileOutputStream(newCategoryHeadLogo);
			FileInputStream fis = new FileInputStream(categoryHeadLogoFile);
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
		BusinessCategory oldpo = businessCategoryService
				.findByid(categoryId);
		if (!StringHelper.isNull(categorySortId)) {
			oldpo.setSortId(Integer.parseInt(categorySortId));
		}
		if (!StringHelper.isNull(categoryName)) {
			oldpo.setCategoryName(categoryName);
		}
		/**
		if(firstLevel!=null && firstLevel.equals("0")){
			oldpo.setCategoryLevel(0);
			oldpo.setCategoryParentId(0);
			if(primaryConfiguration != null){
				oldpo.setPrimaryConfigurationId(primaryConfiguration.getId());
			}
		}
		*/
		if(categoryParent!=null){
			if(categoryParent.getId()!=null && categoryParent.getId()!=oldpo.getId()){
				oldpo.setCategoryParentId(categoryParent.getId());
				BusinessCategory parentpo = businessCategoryService.findByid(categoryParent.getId().toString());
				if(parentpo!=null && parentpo.getCategoryLevel()!=null){
					oldpo.setCategoryLevel(parentpo.getCategoryLevel()+1);
				}
			} else{
				oldpo.setCategoryParentId(oldpo.getCategoryParentId());
				oldpo.setCategoryLevel(oldpo.getCategoryLevel());
			}
			if(categoryParent.getPrimaryConfigurationId()!=null){
				oldpo.setPrimaryConfigurationId(categoryParent.getPrimaryConfigurationId());
			} else{
				if(primaryConfiguration != null){
					oldpo.setPrimaryConfigurationId(primaryConfiguration.getId());
				}
			}
		}
		else{
			oldpo.setCategoryLevel(0);
			oldpo.setCategoryParentId(0);
			if(primaryConfiguration != null){
				oldpo.setPrimaryConfigurationId(primaryConfiguration.getId());
			}
		}
		if(categoryLogoFile != null){
			oldpo.setCategoryLogo(categoryLogoSavePath+categoryLogoFileFileName);
		}
		if(categoryHeadLogoFile != null){
			oldpo.setCategoryHeadLogo(categoryHeadLogoSavePath+categoryHeadLogoFileFileName);
		}
		if (!StringHelper.isNull(categoryDescription)) {
			oldpo.setCategoryDescription(categoryDescription);
		}
		if (!StringHelper.isNull(categoryStatus)) {
			oldpo.setCategoryStatus(Integer.parseInt(categoryStatus));
		}
		oldpo.setModifyTime(new Timestamp(System.currentTimeMillis()));
		oldpo.setModifyStaffId(currentStaff.getId());
		int res = this.businessCategoryService.modify(oldpo);
		super.success(null, AjaxResult.CALL_BACK_TYPE_CLOSECURRENT, "businessCategory",
				null);
		return null;
	}

	@Action(value = "del", results = { @Result(name = StrutsResMSG.SUCCESS, type = "plainText") })
	public String del() throws IOException, JSONException {
		businessCategoryService
				.removeCategoryId(categoryId);
		super.success(null, null, "businessCategory",
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
		super.toJsonArray(businessCategoryService.listAll());
		return null;
	}
}
