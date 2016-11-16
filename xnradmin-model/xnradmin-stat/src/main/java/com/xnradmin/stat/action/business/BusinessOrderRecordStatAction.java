/**
 *2014年12月10日 上午11:58:30
 */
package com.xnradmin.stat.action.business;

import java.io.FileOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFHeader;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Header;
import org.apache.struts2.ServletActionContext;
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
import com.xnradmin.core.service.common.status.StatusService;
import com.xnradmin.core.util.Log4jUtil;
import com.xnradmin.po.CommonStaff;
import com.xnradmin.po.business.BusinessCategory;
import com.xnradmin.po.business.BusinessGoods;
import com.xnradmin.po.business.BusinessOrderRecord;
import com.xnradmin.po.common.status.Status;
import com.xnradmin.stat.service.business.BusinessOrderRecordStatService;
import com.xnradmin.vo.business.BusinessGoodsVO;
import com.xnradmin.vo.business.BusinessOrderVO;

/**
 * @author: liubin
 * 
 */
@Controller
@Scope("prototype")
@Namespace("/page/stat/business")
@ParentPackage("json-default")
public class BusinessOrderRecordStatAction extends ParentAction {

	@Autowired
	private BusinessOrderRecordStatService businessOrderRecordStatService;

	@Autowired
	private StatusService statusService;

	@Autowired
	private BusinessCategoryService businessCategoryService;

	@Autowired
	private StaffService staffService;

	private Log errorLog = Log4jUtil.getLog("ex");

	@Action(value = "statOrderRecordInfo", results = { @Result(name = StrutsResMSG.SUCCESS, location = "/stat/business/order/stat.jsp") })
	public String statOrderRecordInfo() {

		if (query == null) {
			query = new BusinessOrderVO();
			query.setCreateStartTime(StringHelper
					.getSystime("yyyy-MM-dd 00:00:00"));
			query.setCreateEndTime(StringHelper
					.getSystime("yyyy-MM-dd 23:59:59"));
		}

		query.setGroupBy("record.staffId");
		query.setOrderBy("record.id");
		query.setOrderByField("desc");

		if (this.leaderStaff != null && this.leaderStaff.getId() != null) {
			query.setLeaderStaff(leaderStaff);
		}

		this.voList = businessOrderRecordStatService.getStatInfoList(
				this.query, super.getPageNum(), super.getNumPerPage());
		this.query.setGroupBy(null);
		super.totalCount = businessOrderRecordStatService
				.getStatInfoCount(query);

		setPage();
		return StrutsResMSG.SUCCESS;
	}

	@Action(value = "outputOrderDetailTotalExcel", results = { @Result(name = StrutsResMSG.SUCCESS, type = "plainText") })
	public String outputOrderDetailTotalExcel() {
		try {
			businessOrderRecordStatService
					.processOutputOrderDetailTotalExcel(query);
		} catch (Exception e) {
			e.printStackTrace();
			errorLog.error(StringHelper.getStackInfo(e));
		}
		return null;
	}

	@Action(value = "detailTotalInfo", results = { @Result(name = StrutsResMSG.SUCCESS, location = "/stat/business/order/detailtotalinfo.jsp") })
	public String detailTotalInfo() throws IllegalAccessException,
			IllegalArgumentException, InvocationTargetException,
			ClassNotFoundException, InstantiationException {

		this.contentList = businessOrderRecordStatService
				.getStatInfoDetail(query);
		query.setGroupBy("record.id");
		List<BusinessOrderVO> v = businessOrderRecordStatService
				.getStatInfoList(query, 0, 0);
		Integer serno = query.getSerno();
		if (v != null && v.size() > 0)
			query = v.get(0);

		CommonStaff s = staffService.findByid(query.getBusinessOrderRecord()
				.getStaffId());
		CommonStaff ls = staffService.findByid(s.getLeadStaffId().toString());
		this.query.setOrderDesc(businessOrderRecordStatService.getOrderDesc(
				query, v));
		this.query.setTotalPrice(businessOrderRecordStatService
				.getTotalPrice(v));
		this.query.setLeaderStaff(ls);
		query.setSerno(serno);
		setPage();

		return StrutsResMSG.SUCCESS;
	}

	private void setPage() {
		// 加载所有订单处理状态
		this.operateStatusList = statusService.find(BusinessOrderRecord.class,
				"BusinessOperateStatus");
		// 加载所有订单派送状态
		this.deliveryStatusList = statusService.find(BusinessOrderRecord.class,
				"BusinessDeliveryStatus");
		// 加载所有支付状态
		this.paymentStatusList = statusService.find(BusinessOrderRecord.class,
				"BusinessPaymentStatus");
		// 加载所有支付提供者类型
		this.paymentProviderList = statusService.find(
				BusinessOrderRecord.class, "BusinessPaymentProvider");
		// 加载所有支付类型
		this.paymentStatusList = statusService.find(BusinessOrderRecord.class,
				"BusinessPaymentStatus");

		this.billList = statusService.find(CommonStaff.class, "staffBillTime");

		findBusinessGoodsCategoryList();
	}

	/**
	 * 是否有效
	 */
	private void findBusinessGoodsBuyteamList() {
		this.buyTeamList = statusService.find(BusinessGoods.class,
				"businessBuyTeam");
	}

	/**
	 * 商品清单汇总
	 * 
	 * @return
	 * @throws InstantiationException
	 * @throws ClassNotFoundException
	 * @throws InvocationTargetException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	@Action(value = "orderGoodsPurchase", results = {
			@Result(name = StrutsResMSG.SUCCESS, location = "/stat/business/orderGoodsPurchase.jsp"),
			@Result(name = StrutsResMSG.FAILED, location = "/stat/business/orderGoodsPurchase.jsp") })
	public String orderGoodsPurchase() throws IllegalAccessException,
			IllegalArgumentException, InvocationTargetException,
			ClassNotFoundException, InstantiationException {
		findBusinessGoodsCategoryList();
		findOperateStatusList();
		setDateTime();
		findBusinessGoodsBuyteamList();
		BusinessOrderVO bovo = new BusinessOrderVO();
		BusinessGoodsVO gbvo = new BusinessGoodsVO();
		BusinessGoods bgpo = new BusinessGoods();
		BusinessCategory bcpo = new BusinessCategory();
		BusinessOrderRecord borpo = new BusinessOrderRecord();
		bgpo.setGoodsName(goodsName);
		if (!StringHelper.isNull(categoryId)) {
			bcpo.setId(Integer.parseInt(categoryId));
		}
		if (!StringHelper.isNull(parentCategoryId)) {
			bcpo.setCategoryParentId(Integer.parseInt(parentCategoryId));
		}

		if (!StringHelper.isNull(operateStatus)) {
			borpo.setOperateStatus(Integer.parseInt(operateStatus));
		} else {
			borpo.setOperateStatus(204);
		}
		// bovo.setRequiredDeliveryStartTime(requiredDeliveryStartTime
		// + " 00:00:00");
		// bovo.setRequiredDeliveryEndTime(requiredDeliveryEndTime +
		// " 23:59:59");
		bovo.setCreateStartTime(createStartTime);
		bovo.setCreateEndTime(createEndTime);
		gbvo.setBusinessGoods(bgpo);
		gbvo.setBusinessCategory(bcpo);
		bovo.setBusinessGoodsVO(gbvo);
		bovo.setBusinessOrderRecord(borpo);
		List list = businessOrderRecordStatService.orderGoodsPurchase(bovo, 0,
				0, orderField, orderDirection);
		this.totalCount = 0;
		contentList = new ArrayList<String[]>();

		for (int i = 0; i < list.size(); i++) {
			if (buyTeamTotal == null) {
				buyTeamTotal = new HashMap<Integer, Float>();
			}
			String[] content = new String[11];
			HashMap map = new HashMap();
			map = (HashMap) list.get(i);
			if (map == null)
				continue;
			content[0] = map.get("GOODS_NAME") == null ? "0" : map.get(
					"GOODS_NAME").toString();
			content[1] = map.get("sum(t.con)") == null ? "0" : StringHelper
					.formartDecimalToStr(new Float(map.get("sum(t.con)")
							.toString()).doubleValue(), "#.0");
			content[2] = map.get("WEIGHT_NAME") == null ? "0" : map.get(
					"WEIGHT_NAME").toString();
			content[3] = map.get("oprice") == null ? "0" : map.get("oprice")
					.toString();
			content[4] = map.get("countprice") == null ? "0" : StringHelper
					.formartDecimalToStr(new Float(map.get("countprice")
							.toString()).doubleValue(), "#.0");
			content[5] = map.get("tctg") == null ? "0" : map.get("tctg")
					.toString();
			content[6] = map.get("tpctg") == null ? "0" : map.get("tpctg")
					.toString();
			content[7] = map.get("bid") == null ? "0" : map.get("bid")
					.toString();
			content[8] = map.get("tgid") == null ? "0" : map.get("tgid")
					.toString();
			content[9] = map.get("tbdesc") == null ? "0" : map.get("tbdesc")
					.toString();
			content[10] = map.get("tgprice") == null ? "0" : map.get("tgprice")
					.toString();
			float t1 = new Float(content[4]);

			if (!StringHelper.isNull(content[7])
					&& new Integer(content[7]).intValue() != 0) {

				if (buyTeamTotal.containsKey(new Integer(content[7]))) {
					Float temp = buyTeamTotal.get(new Integer(content[7]));
					temp += new Float(content[4]);
					buyTeamTotal.put(new Integer(content[7]), temp);
				} else {
					Float temp = new Float(content[4]);
					buyTeamTotal.put(new Integer(content[7]), temp);
				}
			} else {
				Float temp = new Float(content[4]);
				buyTeamTotal.put(224, temp);
			}

			sumPrice += t1;
			contentList.add(content);
		}
		sumPrice = new Float(StringHelper.formartDecimalToStr(
				sumPrice.doubleValue(), "#.0"));
		createExcel();
		return StrutsResMSG.SUCCESS;
	}

	/**
	 * 生成EXCEL
	 */
	public void createExcel() {
		// 第一步，创建一个webbook，对应一个Excel文件
		HSSFWorkbook wb = new HSSFWorkbook();
		// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
		HSSFSheet sheet = wb.createSheet("采购单");

		// //创建页眉
		// Header header = sheet.getHeader();
		// //自定义页眉,并设置页眉 左中右显示信息
		// header.setCenter("Center Header");
		// header.setLeft("Left Header");
		// header.setRight(HSSFHeader.font("Stencil-Normal", "Italic") +
		// HSSFHeader.fontSize((short) 16) +
		// "Right w/ Stencil-Normal Italic font and size 16");

		HSSFCellStyle style = wb.createCellStyle();
		// 字体
		HSSFFont font = wb.createFont();
		font.setFontHeightInPoints((short) 10);
		style.setFont(font);
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
		style.setWrapText(true);
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框

		// set column
		sheet.setColumnWidth(0, 2500);
		sheet.setColumnWidth(1, 2500);
		sheet.setColumnWidth(2, 2000);
		sheet.setColumnWidth(3, 2000);
		sheet.setColumnWidth(4, 2000);
		sheet.setColumnWidth(5, 2000);
		sheet.setColumnWidth(6, 2000);
		sheet.setColumnWidth(7, 2000);
		sheet.setColumnWidth(8, 2000);
		sheet.setColumnWidth(9, 2000);
		sheet.setColumnWidth(10, 2000);
		int flag = 0;

		if (buyTeamTotal != null) {
			HSSFRow rowTotal = null;
			Iterator<Integer> it = buyTeamTotal.keySet().iterator();

			while (it.hasNext()) {

				rowTotal = sheet.createRow(flag);
				flag++;
				rowTotal.setHeight((short) 1250);
				Integer buyid = it.next();
				HSSFCell cell = rowTotal.createCell(0);
				cell.setCellStyle(style);
				for (Status e : buyTeamList) {
					if (e.getId().intValue() == new Integer(buyid).intValue())
						cell.setCellValue(e.getStatusName());
					else
						continue;
				}
				cell = rowTotal.createCell(1);
				cell.setCellStyle(style);
				cell.setCellValue(buyTeamTotal.get(buyid));
			}

			// 获取当前时间
			Date now = new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat(
					"yyyy/MM/dd HH:mm:ss");
			String nowTime = dateFormat.format(now);
			HSSFCell cell = rowTotal.createCell(3);
			cell.setCellStyle(style);
			cell.setCellValue("时间:\r\n" + nowTime);
		}
		flag += 1;

		// 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
		HSSFRow row = sheet.createRow((int) flag);
		row.setHeight((short) 850);
		// 第四步，创建单元格，并设置值表头 设置表头居中

		HSSFCell cell = row.createCell(0);
		cell.setCellValue("商品名称");
		cell.setCellStyle(style);
		cell = row.createCell(1);
		cell.setCellValue("备注");
		cell.setCellStyle(style);

		cell = row.createCell(2);
		cell.setCellValue("采购单价");
		cell.setCellStyle(style);
		cell = row.createCell(3);
		cell.setCellValue("实际采购数量及金额");
		cell.setCellStyle(style);

		cell = row.createCell(4);
		cell.setCellValue("商品数量");
		cell.setCellStyle(style);
		cell = row.createCell(5);
		cell.setCellValue("参考进价(单价)");
		cell.setCellStyle(style);
		cell = row.createCell(6);
		cell.setCellValue("商品售价(单价)");
		cell.setCellStyle(style);
		cell = row.createCell(7);
		cell.setCellValue("商品售价(总价)");
		cell.setCellStyle(style);

		cell = row.createCell(8);
		cell.setCellValue("商品分类");
		cell.setCellStyle(style);
		cell = row.createCell(9);
		cell.setCellValue("商品主分类");
		cell.setCellStyle(style);

		cell = row.createCell(10);
		cell.setCellValue("采购分组");
		cell.setCellStyle(style);

		// 第五步，写入实体数据 实际应用中这些数据从数据库得到，
		for (int i = 0; i < contentList.size(); i++) {

			flag++;

			row = sheet.createRow(flag);
			row.setHeight((short) 1250);
			String[] vo = (String[]) contentList.get(i);
			// 第四步，创建单元格，并设置值
			cell = row.createCell(0);
			cell.setCellStyle(style);
			cell.setCellValue(vo[0]);

			cell = row.createCell(1);
			cell.setCellStyle(style);
			cell.setCellValue(vo[9]);

			cell = row.createCell(2);
			cell.setCellStyle(style);
			cell.setCellValue("");

			cell = row.createCell(3);
			cell.setCellStyle(style);
			cell.setCellValue("____斤\r\n\n____元");

			cell = row.createCell(4);
			cell.setCellStyle(style);
			cell.setCellValue(vo[1] + "(" + vo[2] + ")");

			cell = row.createCell(5);
			cell.setCellStyle(style);
			cell.setCellValue(vo[10]);

			cell = row.createCell(6);
			cell.setCellStyle(style);
			cell.setCellValue(vo[3]);

			cell = row.createCell(7);
			cell.setCellStyle(style);
			cell.setCellValue(vo[4]);

			cell = row.createCell(8);
			cell.setCellStyle(style);
			cell.setCellValue(vo[5]);

			cell = row.createCell(9);
			cell.setCellStyle(style);
			cell.setCellValue(vo[6]);

			cell = row.createCell(10);
			cell.setCellStyle(style);
			String buyid = vo[7];
			for (Status e : buyTeamList) {
				if (StringHelper.isNull(buyid)
						|| new Integer(buyid).intValue() <= 0) {
					cell.setCellValue("未分配");
				} else {
					if (e.getId().intValue() == new Integer(buyid).intValue())
						cell.setCellValue(e.getStatusName());
					else
						continue;
				}
			}

		}
		row = sheet.createRow(flag + 1);
		row.setHeight((short) 850);

		// 第六步，将文件存到指定位置
		try {
			String pathFile = ServletActionContext.getServletContext()
					.getRealPath("") + "/themes/mall/excel/business_buy.xls";
			FileOutputStream fout = new FileOutputStream(pathFile);
			wb.write(fout);
			fout.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void setDateTime() {
		// 设置默认时间
		if (StringHelper.isNull(createStartTime)
				&& StringHelper.isNull(createEndTime)) {
			Date date = new Date();// 取时间
			// Calendar calendar = new GregorianCalendar();
			// calendar.setTime(date);
			// calendar.add(Calendar.DATE, 1);// 把日期往后增加一天.整数往后推,负数往前移动
			// date = calendar.getTime(); // 这个时间就是日期往后推一天的结果
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			String dateString = formatter.format(date);
			createStartTime = dateString + " 00:00:00";
			createEndTime = dateString + " 23:59:59";
		}
	}

	/**
	 * 加载商品类型
	 */
	private void findBusinessGoodsCategoryList() {
		this.businessCategoryList = businessCategoryService.findList();
		this.businessParentCategoryList = businessCategoryService
				.findParentList();
	}

	/**
	 * 加载所有订单处理状态
	 */
	private void findOperateStatusList() {
		this.operateStatusList = statusService.find(BusinessOrderRecord.class,
				"BusinessOperateStatus");
	}

	private String categoryId; // 商品类型ID
	private String parentCategoryId;
	private String goodsName; // 商品名称
	private String createStartTime;
	private String createEndTime;
	private String operateStatus;
	private List<BusinessCategory> businessCategoryList;
	private List<BusinessCategory> businessParentCategoryList;
	private List<String[]> contentList;
	private List<Status> operateStatusList;
	private Float sumPrice = 0f; // 采购单总售价
	private BusinessOrderVO query;
	private List<Status> buyTeamList;
	private Map<Integer, Float> buyTeamTotal;
	private List<BusinessOrderVO> voList;
	private List<Status> paymentTypeList;
	private List<Status> paymentStatusList;
	private List<Status> paymentProviderList;
	private List<Status> deliveryStatusList;
	private CommonStaff leaderStaff;
	private List<Status> billList;

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public List<BusinessCategory> getBusinessCategoryList() {
		return businessCategoryList;
	}

	public void setBusinessCategoryList(
			List<BusinessCategory> businessCategoryList) {
		this.businessCategoryList = businessCategoryList;
	}

	public List<String[]> getContentList() {
		return contentList;
	}

	public void setContentList(List<String[]> contentList) {
		this.contentList = contentList;
	}

	public String getOperateStatus() {
		return operateStatus;
	}

	public void setOperateStatus(String operateStatus) {
		this.operateStatus = operateStatus;
	}

	public List<Status> getOperateStatusList() {
		return operateStatusList;
	}

	public void setOperateStatusList(List<Status> operateStatusList) {
		this.operateStatusList = operateStatusList;
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

	@Override
	public boolean isPublic() {
		// TODO Auto-generated method stub
		return false;
	}

	public Float getSumPrice() {
		return sumPrice;
	}

	public void setSumPrice(Float sumPrice) {
		this.sumPrice = sumPrice;
	}

	public BusinessOrderVO getQuery() {
		return query;
	}

	public void setQuery(BusinessOrderVO query) {
		this.query = query;
	}

	public List<Status> getBuyTeamList() {
		return buyTeamList;
	}

	public void setBuyTeamList(List<Status> buyTeamList) {
		this.buyTeamList = buyTeamList;
	}

	public Map<Integer, Float> getBuyTeamTotal() {
		return buyTeamTotal;
	}

	public void setBuyTeamTotal(Map<Integer, Float> buyTeamTotal) {
		this.buyTeamTotal = buyTeamTotal;
	}

	public List<BusinessCategory> getBusinessParentCategoryList() {
		return businessParentCategoryList;
	}

	public void setBusinessParentCategoryList(
			List<BusinessCategory> businessParentCategoryList) {
		this.businessParentCategoryList = businessParentCategoryList;
	}

	public String getParentCategoryId() {
		return parentCategoryId;
	}

	public void setParentCategoryId(String parentCategoryId) {
		this.parentCategoryId = parentCategoryId;
	}

	public List<BusinessOrderVO> getVoList() {
		return voList;
	}

	public void setVoList(List<BusinessOrderVO> voList) {
		this.voList = voList;
	}

	public List<Status> getPaymentTypeList() {
		return paymentTypeList;
	}

	public List<Status> getPaymentStatusList() {
		return paymentStatusList;
	}

	public List<Status> getPaymentProviderList() {
		return paymentProviderList;
	}

	public List<Status> getDeliveryStatusList() {
		return deliveryStatusList;
	}

	public void setPaymentTypeList(List<Status> paymentTypeList) {
		this.paymentTypeList = paymentTypeList;
	}

	public void setPaymentStatusList(List<Status> paymentStatusList) {
		this.paymentStatusList = paymentStatusList;
	}

	public void setPaymentProviderList(List<Status> paymentProviderList) {
		this.paymentProviderList = paymentProviderList;
	}

	public void setDeliveryStatusList(List<Status> deliveryStatusList) {
		this.deliveryStatusList = deliveryStatusList;
	}

	public CommonStaff getLeaderStaff() {
		return leaderStaff;
	}

	public void setLeaderStaff(CommonStaff leaderStaff) {
		this.leaderStaff = leaderStaff;
	}

	public List<Status> getBillList() {
		return billList;
	}

	public void setBillList(List<Status> billList) {
		this.billList = billList;
	}
}
