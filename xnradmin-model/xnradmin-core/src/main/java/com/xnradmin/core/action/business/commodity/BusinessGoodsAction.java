/**
 *2012-5-10 下午3:52:29
 */
package com.xnradmin.core.action.business.commodity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.xml.sax.SAXException;

import com.cntinker.util.StringHelper;
import com.xnradmin.constant.AjaxResult;
import com.xnradmin.constant.StrutsResMSG;
import com.xnradmin.core.action.ParentAction;
import com.xnradmin.core.service.StaffService;
import com.xnradmin.core.service.business.commodity.BusinessCategoryService;
import com.xnradmin.core.service.business.commodity.BusinessGoodsService;
import com.xnradmin.core.service.business.commodity.BusinessWeightService;
import com.xnradmin.core.service.business.data.ProcessOtherExcel;
import com.xnradmin.core.service.common.status.StatusService;
import com.xnradmin.core.util.Log4jUtil;
import com.xnradmin.po.CommonStaff;
import com.xnradmin.po.business.BusinessCategory;
import com.xnradmin.po.business.BusinessGoods;
import com.xnradmin.po.business.BusinessWeight;
import com.xnradmin.po.common.status.Status;
import com.xnradmin.vo.StaffVO;
import com.xnradmin.vo.business.BusinessGoodsVO;

/**
 * @autohr: xiang_liu
 * 
 */
@Controller
@Scope("prototype")
@Namespace("/page/business/admin/commodity/goods")
@ParentPackage("json-default")
public class BusinessGoodsAction extends ParentAction {

	private Log exlog = Log4jUtil.getLog("ex");

	@Autowired
	private BusinessGoodsService goodsService;

	@Autowired
	private BusinessCategoryService categoryService;

	@Autowired
	private BusinessWeightService weightService;

	@Autowired
	private StatusService statusService;

	@Autowired
	private StaffService staffService;

	@Autowired
	private ProcessOtherExcel processOtherExcel;

	private String goodsId;
	private String goodsSortId; // 商品排序Id
	private String goodsParentId; // 上级商品Id
	private String goodsCategoryId; // 商品所属分类
	private String goodsCategoryName; // 商品所属分类名称
	private String goodsName; // 商品名称
	private String goodsDescription; // 商品描述
	private String goodsOriginalPrice; // 商品原价
	private String goodsPreferentialPrice; // 商品优惠价
	private String goodsPurchasePrice; // 商品进货价
	private String goodsBrandId; // 商品品牌ID
	private String goodsWeight; // 商品重量
	private String goodsWeightId; // 重量或数量单位
	private String goodsSoldCount; // 已售数量
	private String goodsStock; // 商品库存
	private String goodsStatus; // 商品状态上架下架
	private String isFreeLogistics; // 包邮不包邮
	private String isNewGoods; // 是否新商品
	private String isDiscountGoods; // 是否优惠商品
	private String isHotSaleGoods; // 是否热卖商品
	private String goodsLogo; // 商品图片
	private String goodsBigLogo; // 商品图片
	private File goodsLogoFile; // 商品图片上传文件
	private File goodsBigLogoFile; // 商品大图片上传文件
	private File goodsUploadFile; // 要更新上传的EXCEL文件
	private String goodsLogoFileFileName; // 商品图片名称
	private String goodsBigLogoFileFileName; // 商品大图片名称
	private String goodsLogoFileContentType;
	private String goodsBigLogoFileContentType;
	private String goodsStaffId; // 隶属用户Id
	private String goodsPrimaryConfigurationId; // 对应商城ID
	private String goodsCreateTime; // 建立时间
	private String goodsCreateStartTime; // 建立开始时间
	private String goodsCreateEndTime; // 建立结束时间
	private String goodsCreateStaffId; // 建立人
	private String goodsModifyTime; // 修改时间
	private String goodsModifyStartTime; // 修改开始时间
	private String goodsModifyEndTime; // 修改结束时间
	private String goodsModifyStaffId; // 修改人
	private String goodsLogoSavePath = "/themes/business/goodsLogo/";
	private String goodsBigLogoSavePath = "/themes/business/goodsBigLogo/";
	// GoodsDishRelation
	private String goodsDishRelationId;
	private String goodsDishRelationSortId; // 显示排序Id
	private String goodsDishRelationGoodsId; // 商品Id
	private String goodsDishRelationDishId; // 菜品Id
	private String goodsDishRelationDishCount; // 菜品数量
	private String goodsDishRelationStaffId; // 隶属用户Id
	private String goodsDishRelationPrimaryConfigurationId; // 对应商城ID
	private String goodsDishRelationCreateTime; // 建立时间
	private String goodsDishRelationCreateStartTime; // 建立开始时间
	private String goodsDishRelationCreateEndTime; // 建立结束时间
	private String goodsDishRelationCreateStaffId; // 建立人
	private String goodsDishRelationModifyTime; // 修改时间
	private String goodsDishRelationModifyStartTime; // 修改开始时间
	private String goodsDishRelationModifyEndTime; // 修改结束时间
	private String goodsDishRelationModifyStaffId; // 修改人

	private List<Status> isDiscountList;
	private List<Status> goodsStatusList;
	private List<StaffVO> staffList;
	private List<BusinessCategory> categoryList;
	private Map<String, BusinessGoodsVO> dishList;
	private List<BusinessGoodsVO> dvList;
	private CommonStaff currentStaff;
	private List<BusinessGoodsVO> voList;
	private List<BusinessGoods> goodsList;
	private List<BusinessWeight> weightList;
	private BusinessGoodsVO businessGoodsVO;
	private BusinessGoods goods;
	private BusinessGoods goodsParent;
	private BusinessCategory category;
	private Status status;
	private Status isDiscount;
	private List<Status> buyTeamList;
	private List<Status> goodsSourceList;

	private Map<String, BusinessGoodsVO> goodsBuyteamList;

	@Override
	public boolean isPublic() {
		return false;
	};

	static Log log = LogFactory.getLog(BusinessGoodsAction.class);

	/**
	 * 跳转到信息页。。
	 * 
	 * @return
	 */
	@Action(value = "info", results = {
			@Result(name = StrutsResMSG.SUCCESS, location = "/business/admin/commodity/goods/info.jsp"),
			@Result(name = StrutsResMSG.FAILED, location = "/business/admin/commodity/goods/info.jsp") })
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
			@Result(name = StrutsResMSG.SUCCESS, location = "/business/admin/commodity/goods/lookup.jsp"),
			@Result(name = StrutsResMSG.FAILED, location = "/business/admin/commodity/goods/lookup.jsp") })
	public String lookup() {
		setPageInfo();
		return StrutsResMSG.SUCCESS;
	}

	@Action(value = "uploadGoodsInfo", results = {
			@Result(name = StrutsResMSG.SUCCESS, location = "/business/admin/commodity/goods/uploadGoodsInfo.jsp"),
			@Result(name = StrutsResMSG.FAILED, location = "/business/admin/commodity/goods/uploadGoodsInfo.jsp") })
	public String uploadGoodsInfo() {
		return StrutsResMSG.SUCCESS;
	}

	@Action(value = "modifyAllGoods", results = { @Result(name = StrutsResMSG.SUCCESS, type = "plainText") })
	public String modifyAllGoods() {
		try {
			CommonStaff staff = super.getCurrentStaff();
			int res = goodsService.modifyAllGoods(this.goodsUploadFile, staff);
			if (res == 0)
				super.success(null, AjaxResult.CALL_BACK_TYPE_CLOSECURRENT,
						"businessGoods", null);
		} catch (Exception e) {
			exlog.error(StringHelper.getStackInfo(e));
		}
		return null;
	}

	@Action(value = "modifyGoodsBuyteam", results = { @Result(name = StrutsResMSG.SUCCESS, type = "plainText") })
	public String modifyGoodsBuyteam() throws IOException {
		Iterator<String> it = this.goodsBuyteamList.keySet().iterator();
		while (it.hasNext()) {
			String k = it.next();
			BusinessGoodsVO v = goodsBuyteamList.get(k);

			if (v == null || v.getBusinessGoods() == null
					|| v.getBusinessGoods().getId() == null)
				continue;

			BusinessGoods g = this.goodsService.findByid(v.getBusinessGoods()
					.getId().toString());
			g.setBuyTeamId(v.getBusinessGoods().getBuyTeamId());

			this.goodsService.modify(g);
		}
		super.success(null, null, "orderGoodsPurchase", null);
		return null;
	}

	private void setPageInfo() {
		// 设置排序
		findBusinessGoodsStatusList();
		findBusinessIsDiscountList();
		findStaffList();
		findBusinessCategoryList();
		findBusinessGoodsList();
		findBusinessWeightList();
		findBusinessGoodsBuyteamList();
		BusinessGoodsVO vo = new BusinessGoodsVO();
		BusinessGoods po = new BusinessGoods();
		if (!StringHelper.isNull(goodsCategoryId)) {
			po.setGoodsCategoryId(goodsCategoryId);
		}
		if (!StringHelper.isNull(goodsParentId)) {
			po.setGoodsParentId(goodsParentId);
		}
		if (!StringHelper.isNull(goodsName)) {
			po.setGoodsName(goodsName);
		}
		if (!StringHelper.isNull(goodsBrandId)) {
			po.setGoodsBrandId(Integer.parseInt(goodsBrandId));
		}
		if (!StringHelper.isNull(goodsStatus)) {
			po.setGoodsStatus(Integer.parseInt(goodsStatus));
		}
		if (!StringHelper.isNull(goodsPreferentialPrice)) {
			po.setGoodsPreferentialPrice(Float.valueOf(goodsPreferentialPrice));
		}
		if (!StringHelper.isNull(goodsPurchasePrice)) {
			po.setGoodsPurchasePrice(Float.valueOf(goodsPurchasePrice));
		}
		if (!StringHelper.isNull(isFreeLogistics)) {
			po.setIsFreeLogistics(Integer.parseInt(isFreeLogistics));
		}
		if (!StringHelper.isNull(isNewGoods)) {
			po.setIsNewGoods(Integer.parseInt(isNewGoods));
		}
		if (!StringHelper.isNull(isDiscountGoods)) {
			po.setIsDiscountGoods(Integer.parseInt(isDiscountGoods));
		}
		if (!StringHelper.isNull(isHotSaleGoods)) {
			po.setIsHotSaleGoods(Integer.parseInt(isHotSaleGoods));
		}
		if (!StringHelper.isNull(goodsCreateStaffId)) {
			po.setCreateStaffId(Integer.parseInt(goodsCreateStaffId));
		}
		if (!StringHelper.isNull(goodsModifyStaffId)) {
			po.setModifyStaffId(Integer.parseInt(goodsModifyStaffId));
		}
		vo.setBusinessGoods(po);
		vo.setCreateStartTime(goodsCreateStartTime);
		vo.setCreateEndTime(goodsCreateEndTime);
		vo.setModifyStartTime(goodsModifyStartTime);
		vo.setModifyEndTime(goodsModifyEndTime);
		
		//设置来源列表
		goodsSourceList = statusService.find(BusinessGoods.class,
				"businessGoodsSource");

		this.voList = goodsService.listVO(vo, getPageNum(), getNumPerPage(),
				orderField, orderDirection);
		this.totalCount = goodsService.getCount(vo);

	}

	@Action(value = "outputYsmc", results = { @Result(name = StrutsResMSG.SUCCESS, type = "plainText") })
	public void outputYsmc() throws IOException, SAXException, JSONException {
		HttpServletResponse response = ServletActionContext.getResponse();
		processOtherExcel.outputYsmcFile(response);
	}

	@Action(value = "outputFdlm", results = { @Result(name = StrutsResMSG.SUCCESS, type = "plainText") })
	public void outputFdlm() throws IOException, SAXException, JSONException {
		HttpServletResponse response = ServletActionContext.getResponse();
		processOtherExcel.outputFdlmFile(response);
	}
	
	@Action(value = "outputLn", results = { @Result(name = StrutsResMSG.SUCCESS, type = "plainText") })
	public void outputLn() throws IOException, SAXException, JSONException {
		HttpServletResponse response = ServletActionContext.getResponse();
		processOtherExcel.outputLnFile(response);
	}

	@Action(value = "createGoodsExcel", results = { @Result(name = StrutsResMSG.SUCCESS, type = "plainText") })
	public void createGoodsExcel() {
		findBusinessGoodsStatusList();
		findStaffList();
		findBusinessCategoryList();
		findBusinessWeightList();
		// 第一步，创建一个webbook，对应一个Excel文件
		HSSFWorkbook wb = new HSSFWorkbook();
		// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
		HSSFSheet sheet = wb.createSheet("小农人商品列表");
		// 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
		HSSFRow row = sheet.createRow((int) 0);
		// 第四步，创建单元格，并设置值表头 设置表头居中
		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式

		HSSFCell cell = row.createCell(0);
		cell.setCellValue("排序");
		cell.setCellStyle(style);

		cell = row.createCell(1);
		cell.setCellValue("商品名称");
		cell.setCellStyle(style);

		cell = row.createCell(2);
		cell.setCellValue("商品重量");
		cell.setCellStyle(style);

		cell = row.createCell(3);
		cell.setCellValue("商品单位");
		cell.setCellStyle(style);

		cell = row.createCell(4);
		cell.setCellValue("商品售价");
		cell.setCellStyle(style);

		cell = row.createCell(5);
		cell.setCellValue("更新售价");
		cell.setCellStyle(style);

		cell = row.createCell(6);
		cell.setCellValue("是否上架");
		cell.setCellStyle(style);
		cell = row.createCell(7);
		cell.setCellValue("商品类型");
		cell.setCellStyle(style);
		cell = row.createCell(8);
		cell.setCellValue("商品进货价");
		cell.setCellStyle(style);

		cell = row.createCell(9);
		cell.setCellValue("更新进货价");
		cell.setCellStyle(style);

		cell = row.createCell(10);
		cell.setCellValue("修改时间");
		cell.setCellStyle(style);

		cell = row.createCell(11);
		cell.setCellValue("修改人员");
		cell.setCellStyle(style);

		cell = row.createCell(12);
		cell.setCellValue("毛利率");
		cell.setCellStyle(style);

		cell = row.createCell(13);
		cell.setCellValue("商品描述");
		cell.setCellStyle(style);

		List<BusinessGoodsVO> vlist = goodsService.listVO(null, 0, 0,
				"goodsName", "desc");

		// 第五步，写入实体数据 实际应用中这些数据从数据库得到，
		int f = 1;
		for (int i = 0; i < vlist.size(); i++) {
			f = i + 1;
			row = sheet.createRow(f);
			BusinessGoodsVO v = vlist.get(i);
			// 第四步，创建单元格，并设置值
			row.createCell(0).setCellValue(v.getBusinessGoods().getSortId());
			row.createCell(1).setCellValue(v.getBusinessGoods().getGoodsName());
			row.createCell(2).setCellValue(
					StringHelper.formartDecimalToStr(v.getBusinessGoods()
							.getGoodsWeight()));

			for (BusinessWeight e : weightList) {
				if (e.getId().intValue() == v.getBusinessGoods()
						.getGoodsWeightId().intValue()) {
					row.createCell(3).setCellValue(e.getWeightName());
					break;
				}
			}

			row.createCell(4).setCellValue(
					StringHelper.formartDecimalToStr(v.getBusinessGoods()
							.getGoodsOriginalPrice()));

			row.createCell(5).setCellValue("");

			for (Status e : goodsStatusList) {
				if (e.getId().intValue() == v.getBusinessGoods()
						.getGoodsStatus().intValue()) {
					row.createCell(6).setCellValue(e.getStatusName());
					break;
				}
			}
			for (BusinessCategory e : categoryList) {
				if (e.getId().intValue() == new Integer(v.getBusinessGoods()
						.getGoodsCategoryId()).intValue()) {
					row.createCell(7).setCellValue(e.getCategoryName());
					break;
				}
			}

			row.createCell(8).setCellValue(
					StringHelper.formartDecimalToStr(v.getBusinessGoods()
							.getGoodsPurchasePrice()));

			row.createCell(9).setCellValue("");

			if (v.getBusinessGoods().getModifyTime() != null) {
				row.createCell(10).setCellValue(
						StringHelper.getSystime("yyyy-MM-dd HH:mm:ss", v
								.getBusinessGoods().getModifyTime().getTime()));
			} else {
				row.createCell(10).setCellValue("");
			}

			for (StaffVO e : staffList) {
				if (e.getStaff().getId().intValue() == v.getBusinessGoods()
						.getModifyStaffId().intValue()) {
					row.createCell(11)
							.setCellValue(e.getStaff().getStaffName());
					break;
				} else {
					row.createCell(11).setCellValue("");
				}
			}

			HSSFCellStyle style11 = wb.createCellStyle();
			style11.setDataFormat(HSSFDataFormat.getBuiltinFormat("0.00%"));

			HSSFCell c11 = row.createCell(12);
			c11.setCellFormula("(f" + (f + 1) + "-j" + (f + 1) + ")/j"
					+ (f + 1));
			c11.setCellStyle(style11);

			row.createCell(13).setCellValue(
					v.getBusinessGoods().getGoodsDescription());
		}
		// 第六步，将文件存到指定位置
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			// FileOutputStream fout = new FileOutputStream(ServletActionContext
			// .getServletContext().getRealPath("")
			// + "/themes/mall/excel/business_goods_list.xls");
			response.setContentType("application/msexcel;charset=GBK");
			response.setHeader("Content-Disposition", "attachment;filename=\""
					+ new String("xnr_business_goods_list.xls".getBytes(),
							"UTF-8") + "\"");
			wb.write(response.getOutputStream());
			// fout.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 是否有效
	 */
	private void findBusinessGoodsBuyteamList() {
		this.buyTeamList = statusService.find(BusinessGoods.class,
				"businessBuyTeam");
		
	}

	/**
	 * 是否有效
	 */
	private void findBusinessGoodsStatusList() {
		this.goodsStatusList = statusService.find(BusinessGoods.class,
				"businessGoodsStatus");
	}

	/**
	 * 是否优惠
	 */
	private void findBusinessIsDiscountList() {
		this.isDiscountList = statusService.find(BusinessGoods.class,
				"businessIsDiscountGoods");
	}

	/**
	 * 加载所有商品
	 */
	private void findBusinessGoodsList() {
		this.goodsList = goodsService.listAll();
	}

	/*
	 * 加载所有用户
	 */
	private void findStaffList() {
		this.staffList = staffService.listVO("", "", "", "", "", "", "", "",
				"", 0, 0, 0, "", "");
	}

	/*
	 * 加载所有类型
	 */
	private void findBusinessCategoryList() {
		this.categoryList = categoryService.findList();
	}

	/*
	 * 加载所有数量单位
	 */
	private void findBusinessWeightList() {
		this.weightList = weightService.listAll();
	}

	/*
	 * 加载商品图片上传路径
	 */
	public String getGoodsLogoSavePath() throws Exception {
		// return
		// "C:/work/eclipse-jee-kepler-R-win32-x86_64/eclipse/javaworkspace/xnradmin/xnradmin-web"
		// + "/src/main/webapp/themes/business/goodsLogo";
		return ServletActionContext.getServletContext().getRealPath("")
				+ goodsLogoSavePath;
	}

	/*
	 * 加载商品大图片上传路径
	 */
	public String getGoodsBigLogoSavePath() throws Exception {
		// return
		// "C:/work/eclipse-jee-kepler-R-win32-x86_64/eclipse/javaworkspace/xnradmin/xnradmin-web"
		// + "/src/main/webapp/themes/business/goodsBigLogo";
		return ServletActionContext.getServletContext().getRealPath("")
				+ goodsBigLogoSavePath;
	}

	@Action(value = "addinfo", results = {
			@Result(name = StrutsResMSG.SUCCESS, location = "/business/admin/commodity/goods/add.jsp"),
			@Result(name = StrutsResMSG.FAILED, location = "/business/admin/commodity/goods/add.jsp") })
	public String addInfo() {
		findBusinessGoodsStatusList();
		findBusinessIsDiscountList();
		findBusinessCategoryList();
		findBusinessGoodsList();
		findBusinessWeightList();
		findBusinessGoodsBuyteamList();
		goodsSourceList = statusService.find(BusinessGoods.class,
				"businessGoodsSource");
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
		String newGoodsLogo = "";
		if (goodsLogoFile != null) {
			goodsLogoFileFileName = UUID.randomUUID().toString()
					+ goodsLogoFileFileName;
			newGoodsLogo = getGoodsLogoSavePath() + "/" + goodsLogoFileFileName;
			FileOutputStream fos = new FileOutputStream(newGoodsLogo);
			FileInputStream fis = new FileInputStream(goodsLogoFile);
			byte[] buffer = new byte[1024];
			int len = 0;
			while ((len = fis.read(buffer)) > 0) {
				fos.write(buffer, 0, len);
			}
			fos.close();
			fis.close();
		}
		String newGoodsBigLogo = "";
		if (goodsBigLogoFile != null) {
			goodsBigLogoFileFileName = UUID.randomUUID().toString()
					+ goodsBigLogoFileFileName;
			newGoodsBigLogo = getGoodsBigLogoSavePath() + "/"
					+ goodsBigLogoFileFileName;
			FileOutputStream fos = new FileOutputStream(newGoodsBigLogo);
			FileInputStream fis = new FileInputStream(goodsBigLogoFile);
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
		// 增加新商品
		if (goods == null)
			goods = new BusinessGoods();
		goods.setGoodsName(goodsName);
		if (goodsStatus != null) {
			goods.setGoodsStatus(Integer.parseInt(goodsStatus));
		}
		if (goodsParent != null && goodsParent.getId() != null) {
			goods.setGoodsParentId(goodsParent.getId().toString());
		}
		if (category.getId() != null) {
			goods.setGoodsCategoryId(category.getId().toString());
		}
		if (category.getPrimaryConfigurationId() != null) {
			goods.setPrimaryConfigurationId(category
					.getPrimaryConfigurationId());
		}
		goods.setGoodsDescription(goodsDescription);
		if (goodsOriginalPrice != null) {
			goods.setGoodsOriginalPrice(Float.valueOf(goodsOriginalPrice));
		}
		if (goodsPreferentialPrice != null) {
			goods.setGoodsPreferentialPrice(Float
					.valueOf(goodsPreferentialPrice));
		}
		if (goodsPurchasePrice != null) {
			goods.setGoodsPurchasePrice(Float.valueOf(goodsPurchasePrice));
		}
		if (goodsBrandId != null) {
			goods.setGoodsBrandId(Integer.parseInt(goodsBrandId));
		}
		if (goodsWeight != null) {
			goods.setGoodsWeight(Float.valueOf(goodsWeight));
		}
		if (goodsStock != null) {
			goods.setGoodsStock(Integer.parseInt(goodsStock));
		}
		if (isFreeLogistics != null) {
			goods.setIsFreeLogistics(Integer.parseInt(isFreeLogistics));
		}
		if (isNewGoods != null) {
			goods.setIsNewGoods(Integer.parseInt(isNewGoods));
		}
		if (isDiscountGoods != null) {
			goods.setIsDiscountGoods(Integer.parseInt(isDiscountGoods));
		} else {
			goods.setIsDiscountGoods(120);
		}
		if (isHotSaleGoods != null) {
			goods.setIsHotSaleGoods(Integer.parseInt(isHotSaleGoods));
		}
		if (goodsLogoFile != null) {
			goods.setGoodsLogo(goodsLogoSavePath + goodsLogoFileFileName);
		}
		if (goodsBigLogoFile != null) {
			goods.setGoodsBigLogo(goodsBigLogoSavePath
					+ goodsBigLogoFileFileName);
		}
		if (goodsSortId != null) {
			goods.setSortId(Integer.parseInt(goodsSortId));
		}
		if (goodsWeightId != null) {
			goods.setGoodsWeightId(Integer.parseInt(goodsWeightId));
		}
		if (goodsSoldCount != null) {
			goods.setGoodsSoldCount(Integer.parseInt(goodsSoldCount));
		}

		goods.setCreateStaffId(currentStaff.getId());
		goods.setCreateTime(new Timestamp(System.currentTimeMillis()));
		goods.setModifyStaffId(currentStaff.getId());
		goods.setModifyTime(new Timestamp(System.currentTimeMillis()));
		goodsService.save(goods);

		super.success(null, AjaxResult.CALL_BACK_TYPE_CLOSECURRENT,
				"businessGoods", null);
		return null;
	}

	/**
	 * 带信息到修改页
	 * 
	 * @return String
	 */
	@Action(value = "modifyinfo", results = {
			@Result(name = StrutsResMSG.SUCCESS, location = "/business/admin/commodity/goods/modify.jsp"),
			@Result(name = StrutsResMSG.FAILED, location = "/business/admin/commodity/goods/modify.jsp") })
	public String modifyinfo() {
		findBusinessGoodsStatusList();
		findBusinessIsDiscountList();
		findBusinessCategoryList();
		findBusinessGoodsList();
		findStaffList();
		findBusinessWeightList();
		findBusinessGoodsBuyteamList();
		goodsSourceList = statusService.find(BusinessGoods.class,
				"businessGoodsSource");
		// 加载该商品信息
		goods = goodsService.findByid(goodsId);
		category = categoryService.findByid(goods.getGoodsCategoryId());
		businessGoodsVO = new BusinessGoodsVO();
		businessGoodsVO.setBusinessGoods(goods);
		// businessGoodsVO.setBusinessCategory(category);
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
		log.debug("modify goods: " + goodsId);
		String newGoodsLogo = "";
		if (goodsLogoFile != null) {
			goodsLogoFileFileName = UUID.randomUUID().toString()
					+ goodsLogoFileFileName;
			newGoodsLogo = getGoodsLogoSavePath() + "/" + goodsLogoFileFileName;
			FileOutputStream fos = new FileOutputStream(newGoodsLogo);
			FileInputStream fis = new FileInputStream(goodsLogoFile);
			byte[] buffer = new byte[1024];
			int len = 0;
			while ((len = fis.read(buffer)) > 0) {
				fos.write(buffer, 0, len);
			}
			fos.close();
			fis.close();
		}
		String newGoodsBigLogo = "";
		if (goodsBigLogoFile != null) {
			goodsBigLogoFileFileName = UUID.randomUUID().toString()
					+ goodsBigLogoFileFileName;
			newGoodsBigLogo = getGoodsBigLogoSavePath() + "/"
					+ goodsBigLogoFileFileName;
			FileOutputStream fos = new FileOutputStream(newGoodsBigLogo);
			FileInputStream fis = new FileInputStream(goodsBigLogoFile);
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
		// 修改商品
		BusinessGoods oldGoods = new BusinessGoods();
		oldGoods = goodsService.findByid(goodsId);
		if (goodsName != null) {
			oldGoods.setGoodsName(goodsName);
		}
		if (goodsStatus != null) {
			oldGoods.setGoodsStatus(Integer.parseInt(goodsStatus));
		}
		if (goodsParent != null && goodsParent.getId() != null) {
			oldGoods.setGoodsParentId(goodsParent.getId().toString());
		}
		if (category.getId() != null) {
			oldGoods.setGoodsCategoryId(category.getId().toString());
		}
		if (category.getPrimaryConfigurationId() != null) {
			oldGoods.setPrimaryConfigurationId(category
					.getPrimaryConfigurationId());
		}
		if (goodsDescription != null) {
			oldGoods.setGoodsDescription(goodsDescription);
		}
		if (goodsOriginalPrice != null) {
			oldGoods.setGoodsOriginalPrice(Float.valueOf(goodsOriginalPrice));
		}
		if (goodsPreferentialPrice != null) {
			oldGoods.setGoodsPreferentialPrice(Float
					.valueOf(goodsPreferentialPrice));
		}
		if (goodsPurchasePrice != null) {
			oldGoods.setGoodsPurchasePrice(Float.valueOf(goodsPurchasePrice));
		}
		if (goodsBrandId != null) {
			oldGoods.setGoodsBrandId(Integer.parseInt(goodsBrandId));
		}
		if (goodsWeight != null) {
			oldGoods.setGoodsWeight(Float.valueOf(goodsWeight));
		}
		if (goodsStock != null) {
			oldGoods.setGoodsStock(Integer.parseInt(goodsStock));
		}
		if (isFreeLogistics != null) {
			oldGoods.setIsFreeLogistics(Integer.parseInt(isFreeLogistics));
		}
		if (isNewGoods != null) {
			oldGoods.setIsNewGoods(Integer.parseInt(isNewGoods));
		}
		if (isDiscountGoods != null) {
			oldGoods.setIsDiscountGoods(Integer.parseInt(isDiscountGoods));
		}
		if (isHotSaleGoods != null) {
			oldGoods.setIsHotSaleGoods(Integer.parseInt(isHotSaleGoods));
		}
		if (goodsLogoFile != null) {
			oldGoods.setGoodsLogo(goodsLogoSavePath + goodsLogoFileFileName);
		}
		if (goodsBigLogoFile != null) {
			oldGoods.setGoodsBigLogo(goodsBigLogoSavePath
					+ goodsBigLogoFileFileName);
		}
		if (goodsSortId != null) {
			oldGoods.setSortId(Integer.parseInt(goodsSortId));
		}
		if (goodsWeightId != null) {
			oldGoods.setGoodsWeightId(Integer.parseInt(goodsWeightId));
		}
		if (goodsSoldCount != null) {
			oldGoods.setGoodsSoldCount(Integer.parseInt(goodsSoldCount));
		}
		if(goods!=null && goods.getSourceId()!=null ){
			oldGoods.setSourceId(goods.getSourceId());
		}
		oldGoods.setModifyStaffId(currentStaff.getId());
		oldGoods.setModifyTime(new Timestamp(System.currentTimeMillis()));
		oldGoods.setBuyTeamId(goods.getBuyTeamId());
		if (StringHelper.isNull(goodsDescription))
			oldGoods.setGoodsDescription("");
		else
			oldGoods.setGoodsDescription(goodsDescription);
		goodsService.modify(oldGoods);

		super.success(null, AjaxResult.CALL_BACK_TYPE_CLOSECURRENT,
				"businessGoods", null);
		return null;
	}

	@Action(value = "del", results = { @Result(name = StrutsResMSG.SUCCESS, type = "plainText") })
	public String del() throws IOException, JSONException {
		goodsService.removeGoodsId(goodsId);
		super.success(null, null, "businessGoods", null);
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
		super.toJsonArray(goodsService.listAll());
		return null;
	}

	public List<Status> getBuyTeamList() {
		return buyTeamList;
	}

	public void setBuyTeamList(List<Status> buyTeamList) {
		this.buyTeamList = buyTeamList;
	}

	public Map<String, BusinessGoodsVO> getGoodsBuyteamList() {
		return goodsBuyteamList;
	}

	public void setGoodsBuyteamList(
			Map<String, BusinessGoodsVO> goodsBuyteamList) {
		this.goodsBuyteamList = goodsBuyteamList;
	}

	public File getGoodsUploadFile() {
		return goodsUploadFile;
	}

	public void setGoodsUploadFile(File goodsUploadFile) {
		this.goodsUploadFile = goodsUploadFile;
	}

	public String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	public String getGoodsSortId() {
		return goodsSortId;
	}

	public void setGoodsSortId(String goodsSortId) {
		this.goodsSortId = goodsSortId;
	}

	public String getGoodsParentId() {
		return goodsParentId;
	}

	public void setGoodsParentId(String goodsParentId) {
		this.goodsParentId = goodsParentId;
	}

	public String getGoodsCategoryId() {
		return goodsCategoryId;
	}

	public void setGoodsCategoryId(String goodsCategoryId) {
		this.goodsCategoryId = goodsCategoryId;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getGoodsDescription() {
		return goodsDescription;
	}

	public void setGoodsDescription(String goodsDescription) {
		this.goodsDescription = goodsDescription;
	}

	public String getGoodsOriginalPrice() {
		return goodsOriginalPrice;
	}

	public void setGoodsOriginalPrice(String goodsOriginalPrice) {
		this.goodsOriginalPrice = goodsOriginalPrice;
	}

	public String getGoodsPreferentialPrice() {
		return goodsPreferentialPrice;
	}

	public void setGoodsPreferentialPrice(String goodsPreferentialPrice) {
		this.goodsPreferentialPrice = goodsPreferentialPrice;
	}

	public String getGoodsBrandId() {
		return goodsBrandId;
	}

	public void setGoodsBrandId(String goodsBrandId) {
		this.goodsBrandId = goodsBrandId;
	}

	public String getGoodsWeight() {
		return goodsWeight;
	}

	public void setGoodsWeight(String goodsWeight) {
		this.goodsWeight = goodsWeight;
	}

	public String getGoodsWeightId() {
		return goodsWeightId;
	}

	public void setGoodsWeightId(String goodsWeightId) {
		this.goodsWeightId = goodsWeightId;
	}

	public String getGoodsSoldCount() {
		return goodsSoldCount;
	}

	public void setGoodsSoldCount(String goodsSoldCount) {
		this.goodsSoldCount = goodsSoldCount;
	}

	public void setGoodsBigLogoSavePath(String goodsBigLogoSavePath) {
		this.goodsBigLogoSavePath = goodsBigLogoSavePath;
	}

	public String getGoodsStock() {
		return goodsStock;
	}

	public void setGoodsStock(String goodsStock) {
		this.goodsStock = goodsStock;
	}

	public String getGoodsStatus() {
		return goodsStatus;
	}

	public void setGoodsStatus(String goodsStatus) {
		this.goodsStatus = goodsStatus;
	}

	public String getIsFreeLogistics() {
		return isFreeLogistics;
	}

	public void setIsFreeLogistics(String isFreeLogistics) {
		this.isFreeLogistics = isFreeLogistics;
	}

	public String getIsNewGoods() {
		return isNewGoods;
	}

	public void setIsNewGoods(String isNewGoods) {
		this.isNewGoods = isNewGoods;
	}

	public String getIsDiscountGoods() {
		return isDiscountGoods;
	}

	public void setIsDiscountGoods(String isDiscountGoods) {
		this.isDiscountGoods = isDiscountGoods;
	}

	public String getIsHotSaleGoods() {
		return isHotSaleGoods;
	}

	public void setIsHotSaleGoods(String isHotSaleGoods) {
		this.isHotSaleGoods = isHotSaleGoods;
	}

	public String getGoodsLogo() {
		return goodsLogo;
	}

	public void setGoodsLogo(String goodsLogo) {
		this.goodsLogo = goodsLogo;
	}

	public String getGoodsStaffId() {
		return goodsStaffId;
	}

	public void setGoodsStaffId(String goodsStaffId) {
		this.goodsStaffId = goodsStaffId;
	}

	public String getGoodsPrimaryConfigurationId() {
		return goodsPrimaryConfigurationId;
	}

	public void setGoodsPrimaryConfigurationId(
			String goodsPrimaryConfigurationId) {
		this.goodsPrimaryConfigurationId = goodsPrimaryConfigurationId;
	}

	public String getGoodsCreateTime() {
		return goodsCreateTime;
	}

	public void setGoodsCreateTime(String goodsCreateTime) {
		this.goodsCreateTime = goodsCreateTime;
	}

	public String getGoodsCreateStaffId() {
		return goodsCreateStaffId;
	}

	public void setGoodsCreateStaffId(String goodsCreateStaffId) {
		this.goodsCreateStaffId = goodsCreateStaffId;
	}

	public String getGoodsModifyTime() {
		return goodsModifyTime;
	}

	public void setGoodsModifyTime(String goodsModifyTime) {
		this.goodsModifyTime = goodsModifyTime;
	}

	public String getGoodsModifyStaffId() {
		return goodsModifyStaffId;
	}

	public void setGoodsModifyStaffId(String goodsModifyStaffId) {
		this.goodsModifyStaffId = goodsModifyStaffId;
	}

	public String getGoodsPurchasePrice() {
		return goodsPurchasePrice;
	}

	public void setGoodsPurchasePrice(String goodsPurchasePrice) {
		this.goodsPurchasePrice = goodsPurchasePrice;
	}

	public List<Status> getIsDiscountList() {
		return isDiscountList;
	}

	public void setIsDiscountList(List<Status> isDiscountList) {
		this.isDiscountList = isDiscountList;
	}

	public List<Status> getGoodsStatusList() {
		return goodsStatusList;
	}

	public void setGoodsStatusList(List<Status> goodsStatusList) {
		this.goodsStatusList = goodsStatusList;
	}

	public List<StaffVO> getStaffList() {
		return staffList;
	}

	public void setStaffList(List<StaffVO> staffList) {
		this.staffList = staffList;
	}

	public List<BusinessWeight> getWeightList() {
		return weightList;
	}

	public void setWeightList(List<BusinessWeight> weightList) {
		this.weightList = weightList;
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

	public String getGoodsCreateStartTime() {
		return goodsCreateStartTime;
	}

	public void setGoodsCreateStartTime(String goodsCreateStartTime) {
		this.goodsCreateStartTime = goodsCreateStartTime;
	}

	public String getGoodsCreateEndTime() {
		return goodsCreateEndTime;
	}

	public void setGoodsCreateEndTime(String goodsCreateEndTime) {
		this.goodsCreateEndTime = goodsCreateEndTime;
	}

	public String getGoodsModifyStartTime() {
		return goodsModifyStartTime;
	}

	public void setGoodsModifyStartTime(String goodsModifyStartTime) {
		this.goodsModifyStartTime = goodsModifyStartTime;
	}

	public String getGoodsModifyEndTime() {
		return goodsModifyEndTime;
	}

	public void setGoodsModifyEndTime(String goodsModifyEndTime) {
		this.goodsModifyEndTime = goodsModifyEndTime;
	}

	public Status getIsDiscount() {
		return isDiscount;
	}

	public void setIsDiscount(Status isDiscount) {
		this.isDiscount = isDiscount;
	}

	public String getGoodsDishRelationId() {
		return goodsDishRelationId;
	}

	public void setGoodsDishRelationId(String goodsDishRelationId) {
		this.goodsDishRelationId = goodsDishRelationId;
	}

	public String getGoodsDishRelationSortId() {
		return goodsDishRelationSortId;
	}

	public void setGoodsDishRelationSortId(String goodsDishRelationSortId) {
		this.goodsDishRelationSortId = goodsDishRelationSortId;
	}

	public String getGoodsDishRelationGoodsId() {
		return goodsDishRelationGoodsId;
	}

	public void setGoodsDishRelationGoodsId(String goodsDishRelationGoodsId) {
		this.goodsDishRelationGoodsId = goodsDishRelationGoodsId;
	}

	public String getGoodsDishRelationDishId() {
		return goodsDishRelationDishId;
	}

	public void setGoodsDishRelationDishId(String goodsDishRelationDishId) {
		this.goodsDishRelationDishId = goodsDishRelationDishId;
	}

	public String getGoodsDishRelationDishCount() {
		return goodsDishRelationDishCount;
	}

	public void setGoodsDishRelationDishCount(String goodsDishRelationDishCount) {
		this.goodsDishRelationDishCount = goodsDishRelationDishCount;
	}

	public String getGoodsDishRelationStaffId() {
		return goodsDishRelationStaffId;
	}

	public void setGoodsDishRelationStaffId(String goodsDishRelationStaffId) {
		this.goodsDishRelationStaffId = goodsDishRelationStaffId;
	}

	public String getGoodsDishRelationPrimaryConfigurationId() {
		return goodsDishRelationPrimaryConfigurationId;
	}

	public void setGoodsDishRelationPrimaryConfigurationId(
			String goodsDishRelationPrimaryConfigurationId) {
		this.goodsDishRelationPrimaryConfigurationId = goodsDishRelationPrimaryConfigurationId;
	}

	public String getGoodsDishRelationCreateTime() {
		return goodsDishRelationCreateTime;
	}

	public void setGoodsDishRelationCreateTime(
			String goodsDishRelationCreateTime) {
		this.goodsDishRelationCreateTime = goodsDishRelationCreateTime;
	}

	public String getGoodsDishRelationCreateStartTime() {
		return goodsDishRelationCreateStartTime;
	}

	public void setGoodsDishRelationCreateStartTime(
			String goodsDishRelationCreateStartTime) {
		this.goodsDishRelationCreateStartTime = goodsDishRelationCreateStartTime;
	}

	public String getGoodsDishRelationCreateEndTime() {
		return goodsDishRelationCreateEndTime;
	}

	public void setGoodsDishRelationCreateEndTime(
			String goodsDishRelationCreateEndTime) {
		this.goodsDishRelationCreateEndTime = goodsDishRelationCreateEndTime;
	}

	public String getGoodsDishRelationCreateStaffId() {
		return goodsDishRelationCreateStaffId;
	}

	public void setGoodsDishRelationCreateStaffId(
			String goodsDishRelationCreateStaffId) {
		this.goodsDishRelationCreateStaffId = goodsDishRelationCreateStaffId;
	}

	public String getGoodsDishRelationModifyTime() {
		return goodsDishRelationModifyTime;
	}

	public void setGoodsDishRelationModifyTime(
			String goodsDishRelationModifyTime) {
		this.goodsDishRelationModifyTime = goodsDishRelationModifyTime;
	}

	public String getGoodsDishRelationModifyStartTime() {
		return goodsDishRelationModifyStartTime;
	}

	public void setGoodsDishRelationModifyStartTime(
			String goodsDishRelationModifyStartTime) {
		this.goodsDishRelationModifyStartTime = goodsDishRelationModifyStartTime;
	}

	public String getGoodsDishRelationModifyEndTime() {
		return goodsDishRelationModifyEndTime;
	}

	public void setGoodsDishRelationModifyEndTime(
			String goodsDishRelationModifyEndTime) {
		this.goodsDishRelationModifyEndTime = goodsDishRelationModifyEndTime;
	}

	public String getGoodsDishRelationModifyStaffId() {
		return goodsDishRelationModifyStaffId;
	}

	public void setGoodsDishRelationModifyStaffId(
			String goodsDishRelationModifyStaffId) {
		this.goodsDishRelationModifyStaffId = goodsDishRelationModifyStaffId;
	}

	public String getGoodsCategoryName() {
		return goodsCategoryName;
	}

	public void setGoodsCategoryName(String goodsCategoryName) {
		this.goodsCategoryName = goodsCategoryName;
	}

	public File getGoodsLogoFile() {
		return goodsLogoFile;
	}

	public void setGoodsLogoFile(File goodsLogoFile) {
		this.goodsLogoFile = goodsLogoFile;
	}

	public String getGoodsLogoFileFileName() {
		return goodsLogoFileFileName;
	}

	public void setGoodsLogoFileFileName(String goodsLogoFileFileName) {
		this.goodsLogoFileFileName = goodsLogoFileFileName;
	}

	public String getGoodsLogoFileContentType() {
		return goodsLogoFileContentType;
	}

	public void setGoodsLogoFileContentType(String goodsLogoFileContentType) {
		this.goodsLogoFileContentType = goodsLogoFileContentType;
	}

	public void setGoodsLogoSavePath(String goodsLogoSavePath) {
		this.goodsLogoSavePath = goodsLogoSavePath;
	}

	public String getGoodsBigLogo() {
		return goodsBigLogo;
	}

	public void setGoodsBigLogo(String goodsBigLogo) {
		this.goodsBigLogo = goodsBigLogo;
	}

	public File getGoodsBigLogoFile() {
		return goodsBigLogoFile;
	}

	public void setGoodsBigLogoFile(File goodsBigLogoFile) {
		this.goodsBigLogoFile = goodsBigLogoFile;
	}

	public String getGoodsBigLogoFileFileName() {
		return goodsBigLogoFileFileName;
	}

	public void setGoodsBigLogoFileFileName(String goodsBigLogoFileFileName) {
		this.goodsBigLogoFileFileName = goodsBigLogoFileFileName;
	}

	public String getGoodsBigLogoFileContentType() {
		return goodsBigLogoFileContentType;
	}

	public void setGoodsBigLogoFileContentType(
			String goodsBigLogoFileContentType) {
		this.goodsBigLogoFileContentType = goodsBigLogoFileContentType;
	}

	public List<BusinessCategory> getCategoryList() {
		return categoryList;
	}

	public void setCategoryList(List<BusinessCategory> categoryList) {
		this.categoryList = categoryList;
	}

	public Map<String, BusinessGoodsVO> getDishList() {
		return dishList;
	}

	public void setDishList(Map<String, BusinessGoodsVO> dishList) {
		this.dishList = dishList;
	}

	public List<BusinessGoodsVO> getDvList() {
		return dvList;
	}

	public void setDvList(List<BusinessGoodsVO> dvList) {
		this.dvList = dvList;
	}

	public List<BusinessGoodsVO> getVoList() {
		return voList;
	}

	public void setVoList(List<BusinessGoodsVO> voList) {
		this.voList = voList;
	}

	public List<BusinessGoods> getGoodsList() {
		return goodsList;
	}

	public void setGoodsList(List<BusinessGoods> goodsList) {
		this.goodsList = goodsList;
	}

	public BusinessGoodsVO getBusinessGoodsVO() {
		return businessGoodsVO;
	}

	public void setBusinessGoodsVO(BusinessGoodsVO businessGoodsVO) {
		this.businessGoodsVO = businessGoodsVO;
	}

	public BusinessGoods getGoods() {
		return goods;
	}

	public void setGoods(BusinessGoods goods) {
		this.goods = goods;
	}

	public BusinessGoods getGoodsParent() {
		return goodsParent;
	}

	public void setGoodsParent(BusinessGoods goodsParent) {
		this.goodsParent = goodsParent;
	}

	public BusinessCategory getCategory() {
		return category;
	}

	public void setCategory(BusinessCategory category) {
		this.category = category;
	}

	public List<Status> getGoodsSourceList() {
		return goodsSourceList;
	}

	public void setGoodsSourceList(List<Status> goodsSourceList) {
		this.goodsSourceList = goodsSourceList;
	}

}
