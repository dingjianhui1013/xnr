/**
 *2012-5-10 下午3:52:29
 */
package com.xnradmin.core.action.business.order;

import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
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

import com.cntinker.util.StringHelper;
import com.xnradmin.client.service.wx.OutPlanService;
import com.xnradmin.constant.AjaxResult;
import com.xnradmin.constant.StrutsResMSG;
import com.xnradmin.core.action.ParentAction;
import com.xnradmin.core.service.StaffService;
import com.xnradmin.core.service.business.commodity.BusinessGoodsService;
import com.xnradmin.core.service.business.commodity.BusinessWeightService;
import com.xnradmin.core.service.business.order.BusinessOrderGoodsRelationService;
import com.xnradmin.core.service.business.order.BusinessOrderRecordService;
import com.xnradmin.core.service.business.order.FarmerOrderRecordService;
import com.xnradmin.core.service.common.status.StatusService;
import com.xnradmin.core.service.mall.clientUser.ClientUserInfoService;
import com.xnradmin.core.service.mall.clientUser.ClientUserRegionInfoService;
import com.xnradmin.core.service.mall.region.AreaService;
import com.xnradmin.core.service.mall.seting.LogisticsCompanyService;
import com.xnradmin.core.service.mall.seting.PrimaryConfigurationService;
import com.xnradmin.po.CommonStaff;
import com.xnradmin.po.business.BusinessGoods;
import com.xnradmin.po.business.BusinessOrderGoodsRelation;
import com.xnradmin.po.business.BusinessOrderRecord;
import com.xnradmin.po.business.BusinessWeight;
import com.xnradmin.po.business.FarmerOrderRecord;
import com.xnradmin.po.client.ClientUserInfo;
import com.xnradmin.po.client.ClientUserRegionInfo;
import com.xnradmin.po.common.status.Status;
import com.xnradmin.po.mall.region.Area;
import com.xnradmin.po.mall.region.City;
import com.xnradmin.po.mall.region.Country;
import com.xnradmin.po.mall.region.Province;
import com.xnradmin.po.mall.seting.LogisticsCompany;
import com.xnradmin.po.mall.seting.PrimaryConfiguration;
import com.xnradmin.po.wx.OutPlan;
import com.xnradmin.vo.StaffVO;
import com.xnradmin.vo.business.BusinessOrderVO;
import com.xnradmin.vo.business.FarmerOrderVO;

/**
 * @autohr: xiang_liu
 * 
 */
@Controller
@Scope("prototype")
@Namespace("/page/business/admin/orderrecord")
@ParentPackage("json-default")
public class BusinessOrderRecordAction extends ParentAction {

	@Autowired
	private BusinessOrderRecordService orderRecordService;

	@Autowired
	private OutPlanService planService;
	
	@Autowired
	private FarmerOrderRecordService farmerOrderService;
	
	@Autowired
	private BusinessOrderGoodsRelationService orderGoodsRelationService;

	@Autowired
	private BusinessGoodsService goodsService;

	@Autowired
	private ClientUserInfoService clientUserInfoService;

	@Autowired
	private BusinessWeightService weightService;

	@Autowired
	private ClientUserRegionInfoService clientUserRegionInfoService;

	@Autowired
	private PrimaryConfigurationService primaryConfigurationService;

	@Autowired
	private LogisticsCompanyService logisticsCompanyService;
	@Autowired
	private AreaService areaService;
	@Autowired
	private StatusService statusService;

	@Autowired
	private StaffService staffService;

	private CommonStaff staff;

	private CommonStaff leaderStaff;
	
	private String ifChild;
	
	private Map<String, FarmerOrderRecord> items;
	

	@Override
	public boolean isPublic() {
		return false;
	};

	static Log log = LogFactory.getLog(BusinessOrderRecordAction.class);

	/**
	 * 跳转到信息页。。
	 * 
	 * @return
	 */
	@Action(value = "info", results = {
			@Result(name = "info", location = "/business/admin/orderrecord/info.jsp"),
			@Result(name = "statBusinessOrderInfo", location = "/stat/business/order/info.jsp") })
	public String info() {
		setPageInfo();

		if (StringHelper.isNull(divid))
			return "info";
		else
			return "statBusinessOrderInfo";

		// return StrutsResMSG.SUCCESS;
	}
	@Action(value = "frontUserInfo",results = {@Result(name =StrutsResMSG.SUCCESS, type="redirect",location = "/front/personalCenter.action",params = {"flag", "myorder" })})
	public String frontUserInfo()
	{
		setFrontPageInfo();
		return StrutsResMSG.SUCCESS;
		
	}
	@Action(value = "syncPrice", results = { @Result(name = StrutsResMSG.SUCCESS, type = "plainText") })
	public String syncPrice() throws IOException {
		orderRecordService.syncPrice(createStartTime, createEndTime);
		super.success(null, null, "businessOrderRecord", null);
		return null;
	}

	@Action(value = "detailinfo", results = { @Result(name = StrutsResMSG.SUCCESS, location = "/stat/business/order/detailinfo.jsp") })
	public String detailInfo() {
		findPaymentStatusList();
		findPaymentProviderList();
		findLogisticsCompanyList();
		findOperateStatusList();
		findDeliveryStatus();
		findPaymentTypeList();
		findStaffList();
		findBusinessWeightList();
		billList();
		// 加载该订单所有商品
		BusinessOrderVO vo = new BusinessOrderVO();
		BusinessOrderGoodsRelation po = new BusinessOrderGoodsRelation();
		if (!StringHelper.isNull(orderRecordId)) {
			po.setOrderRecordId(Long.valueOf(orderRecordId));
		}
		if (!StringHelper.isNull(clientUserId)) {
			po.setClientUserId(Integer.parseInt(clientUserId));
		}
		if (!StringHelper.isNull(orderGoodsRelationGoodsId)) {
			po.setGoodsId(Integer.parseInt(orderGoodsRelationGoodsId));
		}
		if (!StringHelper.isNull(orderGoodsRelationGoodsCount)) {
			po.setGoodsCount(Integer.parseInt(orderGoodsRelationGoodsCount));
		}
		if (!StringHelper.isNull(orderGoodsRelationPrimaryConfigurationId)) {
			po.setPrimaryConfigurationId(Integer
					.parseInt(orderGoodsRelationPrimaryConfigurationId));
		}
		po.setStaffId(deliveryStaffId);
		if (!StringHelper.isNull(orderGoodsRelationCurrentPriceType)) {
			po.setCurrentPriceType(Integer
					.parseInt(orderGoodsRelationCurrentPriceType));
		}
		vo.setCreateStartTime(createStartTime);
		vo.setCreateEndTime(createEndTime);
		vo.setBusinessOrderGoodsRelation(po);
		dvList = orderGoodsRelationService.listVO(vo, 0, 0, orderField,
				orderDirection);
		// 加载该订单信息
		orderRecord = orderRecordService.findByid(orderRecordId);
		staff = staffService.findByid(orderRecord.getStaffId());
		if (staff.getLeadStaffId() != null)
			leaderStaff = staffService.findByid(staff.getLeadStaffId()
					.toString());

		createExcel();
		return StrutsResMSG.SUCCESS;
	}

	/**
	 * 生成EXCEL
	 */
	public void createExcel() {
		// 第一步，创建一个webbook，对应一个Excel文件
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
		style.setWrapText(true);
		// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
		HSSFSheet sheet = wb.createSheet("配送单");
		// 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
		HSSFRow row0 = sheet.createRow((int) 0);
		HSSFCell cell0 = row0.createCell(0);
		cell0.setCellValue("商户名称:");
		cell0.setCellStyle(style);
		HSSFCell cell1 = row0.createCell(1);
		cell1.setCellValue(staff.getStaffName());
		cell1.setCellStyle(style);

		//
		HSSFRow row1 = sheet.createRow((int) 1);
		HSSFCell cell11 = row1.createCell(0);
		cell11.setCellValue("送达时间:");
		cell11.setCellStyle(style);
		HSSFCell cell12 = row1.createCell(1);
		cell12.setCellValue(StringHelper.getSystime("yyyy-MM-dd", orderRecord
				.getRequiredDeliveryTime().getTime())
				+ " 最早："
				+ staff.getTheEarliestTime()
				+ "点,最晚："
				+ staff.getTheLatestTime() + "点");
		cell12.setCellStyle(style);

		HSSFRow row2 = sheet.createRow((int) 2);
		HSSFCell cell21 = row2.createCell(0);
		cell21.setCellValue("详细地址:");
		cell21.setCellStyle(style);
		HSSFCell cell22 = row2.createCell(1);
		cell22.setCellValue(orderRecord.getUserRealAddress());
		cell22.setCellStyle(style);

		HSSFRow row3 = sheet.createRow((int) 3);
		HSSFCell cell31 = row3.createCell(0);
		cell31.setCellValue("收货人姓名:");
		cell31.setCellStyle(style);
		HSSFCell cell32 = row3.createCell(1);
		cell32.setCellValue(orderRecord.getUserRealName());
		cell32.setCellStyle(style);

		HSSFRow row4 = sheet.createRow((int) 4);
		HSSFCell cell41 = row4.createCell(0);
		cell41.setCellValue("收货人电话:");
		cell41.setCellStyle(style);
		HSSFCell cell42 = row4.createCell(1);
		cell42.setCellValue(orderRecord.getUserRealMobile());
		cell42.setCellStyle(style);

		HSSFRow row5 = sheet.createRow((int) 5);
		HSSFCell cell51 = row5.createCell(0);
		cell51.setCellValue("客户经理:");
		cell51.setCellStyle(style);
		HSSFCell cell52 = row5.createCell(1);
		cell52.setCellValue(leaderStaff.getStaffName());
		cell52.setCellStyle(style);

		HSSFRow row6 = sheet.createRow((int) 6);
		HSSFCell cell61 = row6.createCell(0);
		cell61.setCellValue("客户经理电话:");
		cell61.setCellStyle(style);
		HSSFCell cell62 = row6.createCell(1);
		cell62.setCellValue(leaderStaff.getMobile());
		cell62.setCellStyle(style);

		HSSFRow row7 = sheet.createRow((int) 7);
		HSSFCell cell71 = row7.createCell(0);
		cell71.setCellValue("备注:");
		cell71.setCellStyle(style);
		HSSFCell cell72 = row7.createCell(1);
		cell72.setCellValue(orderRecord.getUserRealDescription());
		cell72.setCellStyle(style);

		HSSFRow row8 = sheet.createRow((int) 8);
		HSSFCell cell81 = row8.createCell(0);
		cell81.setCellValue("所属省份:");
		cell81.setCellStyle(style);
		HSSFCell cell82 = row8.createCell(1);
		cell82.setCellValue(orderRecord.getProvinceName());
		cell82.setCellStyle(style);

		HSSFRow row9 = sheet.createRow((int) 9);
		HSSFCell cell91 = row9.createCell(0);
		cell91.setCellValue("所属城市:");
		cell91.setCellStyle(style);
		HSSFCell cell92 = row9.createCell(1);
		cell92.setCellValue(orderRecord.getCityName());
		cell92.setCellStyle(style);

		HSSFRow row10 = sheet.createRow((int) 10);
		HSSFCell cell101 = row10.createCell(0);
		cell101.setCellValue("所属区/县:");
		cell101.setCellStyle(style);
		HSSFCell cell102 = row10.createCell(1);
		cell102.setCellValue(orderRecord.getAreaName());
		cell102.setCellStyle(style);

		HSSFRow row11 = sheet.createRow((int) 11);
		HSSFCell cell111 = row11.createCell(0);
		cell111.setCellValue("配送方式:");
		cell111.setCellStyle(style);
		HSSFCell cell112 = row11.createCell(1);
		for (LogisticsCompany e : logisticsCompanyList) {
			if (e.getId().intValue() == orderRecord.getLogisticsCompanyId()
					.intValue()) {
				cell112.setCellValue(e.getCompanyName());
				break;
			} else
				cell112.setCellValue("");
		}
		cell112.setCellStyle(style);

		HSSFRow row12 = sheet.createRow((int) 12);
		HSSFCell cell121 = row12.createCell(0);
		cell121.setCellValue("支付方式:");
		cell121.setCellStyle(style);
		HSSFCell cell122 = row12.createCell(1);
		for (Status e : paymentProviderList) {
			if (e.getId().intValue() == orderRecord.getPaymentProvider()
					.intValue()) {
				cell122.setCellValue(e.getStatusName());
				break;
			} else
				cell122.setCellValue("");
		}
		cell122.setCellStyle(style);

		HSSFRow row13 = sheet.createRow((int) 13);
		HSSFCell cell131 = row13.createCell(0);
		cell131.setCellValue("处理状态:");
		cell131.setCellStyle(style);
		HSSFCell cell132 = row13.createCell(1);
		for (Status e : operateStatusList) {
			if (e.getId().intValue() == orderRecord.getOperateStatus()
					.intValue()) {
				cell132.setCellValue(e.getStatusName());
				break;
			} else
				cell132.setCellValue("");
		}
		cell132.setCellStyle(style);

		HSSFRow row14 = sheet.createRow((int) 14);
		HSSFCell cell141 = row14.createCell(0);
		cell141.setCellValue("支付状态:");
		cell141.setCellStyle(style);
		HSSFCell cell142 = row14.createCell(1);
		for (Status e : paymentStatusList) {
			if (e.getId().intValue() == orderRecord.getPaymentStatus()
					.intValue()) {
				cell142.setCellValue(e.getStatusName());
				break;
			} else
				cell142.setCellValue("");
		}
		cell142.setCellStyle(style);

		HSSFRow row15 = sheet.createRow((int) 15);
		HSSFCell cell151 = row15.createCell(0);
		cell151.setCellValue("配送状态:");
		cell151.setCellStyle(style);
		HSSFCell cell152 = row15.createCell(1);
		for (Status e : deliveryStatusList) {
			if (e.getId().intValue() == orderRecord.getDeliveryStatus()
					.intValue()) {
				cell152.setCellValue(e.getStatusName());
				break;
			} else
				cell152.setCellValue("");
		}
		cell152.setCellStyle(style);

		HSSFRow row16 = sheet.createRow((int) 16);
		HSSFCell cell161 = row16.createCell(0);
		cell161.setCellValue("订单总价:");
		cell161.setCellStyle(style);
		HSSFCell cell162 = row16.createCell(1);
		cell162.setCellValue(orderRecord.getTotalPrice());
		cell162.setCellStyle(style);

		HSSFRow row17 = sheet.createRow((int) 17);
		HSSFCell cel171 = row17.createCell(0);
		cel171.setCellValue("结算周期:");
		cel171.setCellStyle(style);
		HSSFCell cell172 = row17.createCell(1);
		for (Status e : billList) {
			if (e.getId().intValue() == staff.getBillTime().intValue()) {
				cell172.setCellValue(e.getStatusName());
				break;
			} else
				cell172.setCellValue("");
		}
		cell172.setCellStyle(style);

		HSSFRow row18 = sheet.createRow((int) 18);
		HSSFCell cell181 = row18.createCell(0);
		cell181.setCellValue("客户签字:");
		cell181.setCellStyle(style);

		HSSFRow row19 = sheet.createRow((int) 19);
		HSSFCell cell191 = row19.createCell(0);
		cell191.setCellValue("送货人员签字:");
		cell191.setCellStyle(style);

		HSSFRow row20 = sheet.createRow((int) 20);
		HSSFCell cell201 = row20.createCell(0);
		cell201.setCellValue("【货物清单】");
		cell201.setCellStyle(style);

		HSSFRow row21 = sheet.createRow((int) 21);
		HSSFCell cell = row21.createCell(0);
		cell.setCellValue("商品名称");
		cell.setCellStyle(style);

		cell = row21.createCell(1);
		cell.setCellValue("商品备注");
		cell.setCellStyle(style);

		cell = row21.createCell(2);
		cell.setCellValue("数量");
		cell.setCellStyle(style);

		cell = row21.createCell(3);
		cell.setCellValue("单位");
		cell.setCellStyle(style);

		cell = row21.createCell(4);
		cell.setCellValue("分类");
		cell.setCellStyle(style);

		cell = row21.createCell(5);
		cell.setCellValue("主分类");
		cell.setCellStyle(style);

		cell = row21.createCell(6);
		cell.setCellValue("单价");
		cell.setCellStyle(style);

		cell = row21.createCell(7);
		cell.setCellValue("总价");
		cell.setCellStyle(style);

		// 第五步，写入实体数据 实际应用中这些数据从数据库得到，
		for (int i = 0; i < dvList.size(); i++) {
			HSSFRow temprow = sheet.createRow((int) i + 22);
			BusinessOrderVO vo = (BusinessOrderVO) dvList.get(i);
			// 第四步，创建单元格，并设置值
			temprow.createCell(0).setCellValue(
					vo.getBusinessGoodsVO().getBusinessGoods().getGoodsName());

			temprow.createCell(1).setCellValue(
					vo.getBusinessGoodsVO().getBusinessGoods()
							.getGoodsDescription());

			temprow.createCell(2).setCellValue(
					vo.getBusinessOrderGoodsRelation().getGoodsCount());

			for (BusinessWeight e : weightList) {
				if (e.getId().intValue() == vo.getBusinessOrderGoodsRelation()
						.getGoodsWeightId().intValue()) {
					temprow.createCell(3).setCellValue(
							"(" + e.getWeightName() + ")");
					break;
				} else
					temprow.createCell(3).setCellValue("");
			}

			temprow.createCell(4).setCellValue(
					vo.getBusinessGoodsVO().getBusinessCategory()
							.getCategoryName());
			temprow.createCell(5).setCellValue(
					vo.getBusinessGoodsVO().getBusinessParentCategory()
							.getCategoryName());
			temprow.createCell(6).setCellValue(
					vo.getBusinessOrderGoodsRelation().getCurrentPrice());

			Float p = vo.getBusinessOrderGoodsRelation().getGoodsCount()
					* vo.getBusinessOrderGoodsRelation().getCurrentPrice();
			temprow.createCell(7).setCellValue(p);
		}
		// 第六步，将文件存到指定位置
		try {
			String file = ServletActionContext.getServletContext().getRealPath(
					"");
			FileOutputStream fout = new FileOutputStream(file
					+ "/themes/mall/excel/business_order_detail.xls");
			wb.write(fout);
			fout.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		sheet.autoSizeColumn((short) 0);
		sheet.autoSizeColumn((short) 1);
		sheet.autoSizeColumn((short) 2);
		sheet.autoSizeColumn((short) 3);
		sheet.autoSizeColumn((short) 4);
		sheet.autoSizeColumn((short) 5);
		sheet.autoSizeColumn((short) 6);
	}

	private void billList() {
		this.billList = statusService.find(CommonStaff.class, "staffBillTime");
	}

	public void setPageInfo() {
		// 设置排序
		setDateTime();
		findPaymentStatusList();
		findPaymentProviderList();
		findOperateStatusList();
		findDeliveryStatus();
		findPaymentTypeList();
		BusinessOrderVO vo = new BusinessOrderVO();
		BusinessOrderRecord po = new BusinessOrderRecord();
		if (!StringHelper.isNull(orderGoodsRelationClientUserId)) {
			po.setClientUserId(Integer.parseInt(orderGoodsRelationClientUserId));
		}
		po.setOrderNo(orderNo);
		if (!StringHelper.isNull(orderRecordId)) {
			po.setId(Long.valueOf(orderRecordId));
		}
		po.setClientUserName(clientUserName);
		po.setClientUserEmail(clientUserEmail);
		po.setClientUserMobile(clientUserMobile);
		po.setClientUserSex(clientUserSex);
		if (!StringHelper.isNull(clientUserType)) {
			po.setClientUserType(Integer.parseInt(clientUserType));
		}
		po.setClientUserTypeName(clientUserTypeName);
		po.setWxOpenId(wxOpenId);
		po.setStaffId(orderGoodsRelationStaffId);
		po.setUserRealMobile(userRealMobile);
		po.setUserRealPlane(userRealPlane);
		po.setUserRealName(userRealName);
		if (!StringHelper.isNull(countryId)) {
			po.setCountryId(Integer.parseInt(countryId));
		}
		po.setCountryName(countryName);
		if (!StringHelper.isNull(provinceId)) {
			po.setProvinceId(Integer.parseInt(provinceId));
		}
		po.setProvinceName(provinceName);
		if (!StringHelper.isNull(cityId)) {
			po.setCityId(Integer.parseInt(cityId));
		}
		po.setCityName(cityName);
		if (!StringHelper.isNull(areaId)) {
			po.setAreaId(Integer.parseInt(areaId));
		}
		po.setAreaName(areaName);
		po.setUserRealAddress(userRealAddress);
		po.setUserRealPostcode(userRealPostcode);
		if (!StringHelper.isNull(paymentType)) {
			po.setPaymentType(Integer.parseInt(paymentType));
		}
		po.setPaymentTypeName(paymentTypeName);
		if (!StringHelper.isNull(paymentStatus)) {
			po.setPaymentStatus(Integer.parseInt(paymentStatus));
		}
		po.setPaymentStatusName(paymentStatusName);
		if (!StringHelper.isNull(paymentProvider)) {
			po.setPaymentProvider(Integer.parseInt(paymentProvider));
		}
		po.setPaymentProviderName(paymentProviderName);
		if (!StringHelper.isNull(operateStatus)) {
			if (!operateStatus.equals("0")) {
				po.setOperateStatus(Integer.parseInt(operateStatus));
			}
		}
		po.setOperateStatusName(operateStatusName);
		if (!StringHelper.isNull(originalPrice)) {
			po.setOriginalPrice(Float.valueOf(originalPrice));
		}
		if (!StringHelper.isNull(totalPrice)) {
			po.setTotalPrice(Float.valueOf(totalPrice));
		}
		if (!StringHelper.isNull(purchasePrice)) {
			po.setPurchasePrice(Float.valueOf(purchasePrice));
		}
		if (!StringHelper.isNull(discount)) {
			po.setDiscount(Float.valueOf(discount));
		}
		if (!StringHelper.isNull(logisticsCompanyId)) {
			po.setLogisticsCompanyId(Integer.parseInt(logisticsCompanyId));
		}
		po.setLogisticsCompanyName(logisticsCompanyName);
		po.setDeliveryStaffId(deliveryStaffId);
		po.setDeliveryStaffName(deliveryStaffName);
		po.setDeliveryStaffMobile(deliveryStaffMobile);
		if (!StringHelper.isNull(deliveryStatus)) {
			po.setDeliveryStatus(Integer.parseInt(deliveryStatus));
		}
		po.setDeliveryStatusName(deliveryStatusName);
		if (!StringHelper.isNull(sourceId)) {
			po.setSourceId(Integer.parseInt(sourceId));
		}
		po.setSourceName(sourceName);
		if (!StringHelper.isNull(sourceType)) {
			po.setSourceType(Integer.parseInt(sourceType));
		}
		po.setSourceTypeName(sourceTypeName);
		po.setSerNo(serNo);
		if (!StringHelper.isNull(sellerId)) {
			po.setSellerId(Integer.parseInt(sellerId));
		}
		po.setSellerName(sellerName);
		if (!StringHelper.isNull(cusId)) {
			po.setCusId(Integer.parseInt(cusId));
		}
		po.setCusName(cusName);
		if (!StringHelper.isNull(orderGoodsRelationPrimaryConfigurationId)) {
			po.setPrimaryConfigurationId(Integer
					.parseInt(orderGoodsRelationPrimaryConfigurationId));
		}
		po.setPrimaryConfigurationName(primaryConfigurationName);
		if(!"0".equals(ifChild)){//是子订单
			po.setIsChild(0L);
		}
		vo.setBusinessOrderRecord(po);
		vo.setUserRealStartTime(userRealStartTime);
		vo.setUserRealEndTime(userRealEndTime);
		vo.setPaymentStartTime(paymentStartTime);
		vo.setPaymentEndTime(paymentEndTime);
		vo.setOperateStartTime(operateStartTime);
		vo.setOperateEndTime(operateEndTime);
		vo.setCreateStartTime(createStartTime);
		vo.setCreateEndTime(createEndTime);
		vo.setDeliveryStartStartTime(deliveryStartStartTime);
		vo.setDeliveryStartEndTime(deliveryStartEndTime);
		vo.setDeliveryEndStartTime(deliveryEndStartTime);
		vo.setDeliveryEndEndTime(deliveryEndEndTime);
		vo.setRequiredDeliveryStartTime(requiredDeliveryStartTime);
		vo.setRequiredDeliveryEndTime(requiredDeliveryEndTime);
		vo.setFinalDeliveryStartTime(finalDeliveryStartTime);
		vo.setFinalDeliveryEndTime(finalDeliveryEndTime);
		vo.setLeaderStaff(leaderStaff);
		vo.setProductName(productName);
		
		if(staff!=null){
			vo.setStaff(staff);
		}
		
		vo.setGroupBy("record.id");
		vo.setOrderBy("record.staffId");
		vo.setOrderByField("desc");
		this.voList = orderRecordService.listVO2(vo, getPageNum(),
				getNumPerPage(), orderField, orderDirection);
		vo.setGroupBy("");
		String select = "select count(distinct record.id) ";
		this.totalCount = orderRecordService.getCount2(select, vo);
	}
	private void setFrontPageInfo() {
		// 设置排序
		setDateTime();
		findPaymentStatusList();
		findPaymentProviderList();
		findOperateStatusList();
		findDeliveryStatus();
		findPaymentTypeList();
		BusinessOrderVO vo = new BusinessOrderVO();
		BusinessOrderRecord po = new BusinessOrderRecord();
		if (!StringHelper.isNull(orderGoodsRelationClientUserId)) {
			po.setClientUserId(Integer.parseInt(orderGoodsRelationClientUserId));
		}
		po.setOrderNo(orderNo);
		if (!StringHelper.isNull(orderRecordId)) {
			po.setId(Long.valueOf(orderRecordId));
		}
		po.setClientUserName(clientUserName);
		po.setClientUserEmail(clientUserEmail);
		po.setClientUserMobile(clientUserMobile);
		po.setClientUserSex(clientUserSex);
		if (!StringHelper.isNull(clientUserType)) {
			po.setClientUserType(Integer.parseInt(clientUserType));
		}
		po.setClientUserTypeName(clientUserTypeName);
		po.setWxOpenId(wxOpenId);
		po.setStaffId(orderGoodsRelationStaffId);
		po.setUserRealMobile(userRealMobile);
		po.setUserRealPlane(userRealPlane);
		po.setUserRealName(userRealName);
		if (!StringHelper.isNull(countryId)) {
			po.setCountryId(Integer.parseInt(countryId));
		}
		po.setCountryName(countryName);
		if (!StringHelper.isNull(provinceId)) {
			po.setProvinceId(Integer.parseInt(provinceId));
		}
		po.setProvinceName(provinceName);
		if (!StringHelper.isNull(cityId)) {
			po.setCityId(Integer.parseInt(cityId));
		}
		po.setCityName(cityName);
		if (!StringHelper.isNull(areaId)) {
			po.setAreaId(Integer.parseInt(areaId));
		}
		po.setAreaName(areaName);
		po.setUserRealAddress(userRealAddress);
		po.setUserRealPostcode(userRealPostcode);
		if (!StringHelper.isNull(paymentType)) {
			po.setPaymentType(Integer.parseInt(paymentType));
		}
		po.setPaymentTypeName(paymentTypeName);
		if (!StringHelper.isNull(paymentStatus)) {
			po.setPaymentStatus(Integer.parseInt(paymentStatus));
		}
		po.setPaymentStatusName(paymentStatusName);
		if (!StringHelper.isNull(paymentProvider)) {
			po.setPaymentProvider(Integer.parseInt(paymentProvider));
		}
		po.setPaymentProviderName(paymentProviderName);
		if (!StringHelper.isNull(operateStatus)) {
			if (!operateStatus.equals("0")) {
				po.setOperateStatus(Integer.parseInt(operateStatus));
			}
		}
		po.setOperateStatusName(operateStatusName);
		if (!StringHelper.isNull(originalPrice)) {
			po.setOriginalPrice(Float.valueOf(originalPrice));
		}
		if (!StringHelper.isNull(totalPrice)) {
			po.setTotalPrice(Float.valueOf(totalPrice));
		}
		if (!StringHelper.isNull(purchasePrice)) {
			po.setPurchasePrice(Float.valueOf(purchasePrice));
		}
		if (!StringHelper.isNull(discount)) {
			po.setDiscount(Float.valueOf(discount));
		}
		if (!StringHelper.isNull(logisticsCompanyId)) {
			po.setLogisticsCompanyId(Integer.parseInt(logisticsCompanyId));
		}
		po.setLogisticsCompanyName(logisticsCompanyName);
		po.setDeliveryStaffId(deliveryStaffId);
		po.setDeliveryStaffName(deliveryStaffName);
		po.setDeliveryStaffMobile(deliveryStaffMobile);
		if (!StringHelper.isNull(deliveryStatus)) {
			po.setDeliveryStatus(Integer.parseInt(deliveryStatus));
		}
		po.setDeliveryStatusName(deliveryStatusName);
		if (!StringHelper.isNull(sourceId)) {
			po.setSourceId(Integer.parseInt(sourceId));
		}
		po.setSourceName(sourceName);
		if (!StringHelper.isNull(sourceType)) {
			po.setSourceType(Integer.parseInt(sourceType));
		}
		po.setSourceTypeName(sourceTypeName);
		po.setSerNo(serNo);
		if (!StringHelper.isNull(sellerId)) {
			po.setSellerId(Integer.parseInt(sellerId));
		}
		po.setSellerName(sellerName);
		if (!StringHelper.isNull(cusId)) {
			po.setCusId(Integer.parseInt(cusId));
		}
		po.setCusName(cusName);
		if (!StringHelper.isNull(orderGoodsRelationPrimaryConfigurationId)) {
			po.setPrimaryConfigurationId(Integer
					.parseInt(orderGoodsRelationPrimaryConfigurationId));
		}
		po.setPrimaryConfigurationName(primaryConfigurationName);
		po.setClientUserId(Integer.parseInt(clientUserId));
		vo.setBusinessOrderRecord(po);
		vo.setUserRealStartTime(userRealStartTime);
		vo.setUserRealEndTime(userRealEndTime);
		vo.setPaymentStartTime(paymentStartTime);
		vo.setPaymentEndTime(paymentEndTime);
		vo.setOperateStartTime(operateStartTime);
		vo.setOperateEndTime(operateEndTime);
		vo.setCreateStartTime(createStartTime);
		vo.setCreateEndTime(createEndTime);
		vo.setDeliveryStartStartTime(deliveryStartStartTime);
		vo.setDeliveryStartEndTime(deliveryStartEndTime);
		vo.setDeliveryEndStartTime(deliveryEndStartTime);
		vo.setDeliveryEndEndTime(deliveryEndEndTime);
		vo.setRequiredDeliveryStartTime(requiredDeliveryStartTime);
		vo.setRequiredDeliveryEndTime(requiredDeliveryEndTime);
		vo.setFinalDeliveryStartTime(finalDeliveryStartTime);
		vo.setFinalDeliveryEndTime(finalDeliveryEndTime);
		vo.setLeaderStaff(leaderStaff);
		vo.setProductName(productName);
		
		if(staff!=null){
			vo.setStaff(staff);
		}
		
		vo.setGroupBy("record.id");
		vo.setOrderBy("record.staffId");
		vo.setOrderByField("desc");
		this.voList = orderRecordService.listVO2(vo, getPageNum(),
				getNumPerPage(), orderField, orderDirection);
		vo.setGroupBy("");
		String select = "select count(distinct record.id) ";
		this.totalCount = orderRecordService.getCount2(select, vo);
	}
	/**
	 * 加载所有支付状态
	 */
	private void findPaymentStatusList() {
		this.paymentStatusList = statusService.find(BusinessOrderRecord.class,
				"BusinessPaymentStatus");
	}

	/**
	 * 加载所有支付提供者类型
	 */
	private void findPaymentProviderList() {
		this.paymentProviderList = statusService.find(
				BusinessOrderRecord.class, "BusinessPaymentProvider");
	}

	/**
	 * 加载所有订单处理状态
	 */
	private void findOperateStatusList() {
		this.operateStatusList = statusService.find(BusinessOrderRecord.class,
				"BusinessOperateStatus");
	}

	/**
	 * 加载所有订单派送状态
	 */
	private void findDeliveryStatus() {
		this.deliveryStatusList = statusService.find(BusinessOrderRecord.class,
				"BusinessDeliveryStatus");
	}

	/**
	 * 加载所有支付类型
	 */
	private void findPaymentTypeList() {
		this.paymentTypeList = statusService.find(BusinessOrderRecord.class,
				"BusinessPaymentType");
	}

	/*
	 * 加载所有用户
	 */
	private void findStaffList() {
		this.staffList = staffService.listVO("", "", "", "", "", "", "", "",
				"", 0, 0, 0, "", "");
	}

	private void findLogisticsCompanyList() {
		this.logisticsCompanyList = logisticsCompanyService.listAll();
	}

	@Action(value = "addinfo", results = {
			@Result(name = StrutsResMSG.SUCCESS, location = "/business/admin/orderrecord/add.jsp"),
			@Result(name = StrutsResMSG.FAILED, location = "/business/admin/orderrecord/add.jsp") })
	public String addInfo() {
		if (!StringHelper.isNull(staffId)) {
			ClientUserInfo cui = clientUserInfoService.findByProperty(
					"staffId", Integer.parseInt(staffId));
			if (cui != null && cui.getId() != null) {
				clientUserRegionInfoList = clientUserRegionInfoService
						.findByProperty("clientUserInfoId", cui.getId());
			}
		}
		findPaymentProviderList();
		findLogisticsCompanyList();
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
		log.debug("staffId:::" + staffId);
		// 取得当前登录人信息
		currentStaff = super.getCurrentStaff();
		// ClientUserInfo clientUserInfo = clientUserInfoService
		// .findByid(clientUserId);
		ClientUserInfo clientUserInfo = clientUserInfoService.findByProperty(
				"staffId", Integer.parseInt(staffId));
		CommonStaff staff = staffService.findByid(staffId);
		// 批量增加商品
		if (goodsList == null) {
			super.error("未填写商品");
		} else if (staff != null) {
			log.debug("start:::");
			// 判定用户是否为新用户，新用户自动添加
			if (clientUserInfo == null || clientUserInfo.getId() == null) {
				ClientUserInfo po = new ClientUserInfo();
				po.setStaffId(staff.getId());
				po.setIswebsiteuser(1);
				po.setStatus(139);
				Status statusCode = statusService.findByid("139");
				po.setStatusName(statusCode.getStatusName());
				po.setType(154);
				Status typeCode = statusService.findByid("154");
				po.setTypeName(typeCode.getStatusName());
				po.setMobile(staff.getMobile());
				po.setLoginPassWord(staff.getPassword());
				po.setNickName(staff.getStaffName());
				po.setCreateTime(new Timestamp(System.currentTimeMillis()));
				po.setLastLoginTime(new Timestamp(System.currentTimeMillis()));
				po.setModifyTime(new Timestamp(System.currentTimeMillis()));
				po.setDiscount(staff.getDiscount());
				clientUserInfoService.saveBusinessUser(po);
			}
			ClientUserRegionInfo cri = new ClientUserRegionInfo();
			if (!StringHelper.isNull(clientUserRegionInfoId)) {
				if (clientUserRegionInfoId.equals("new") && provincePO != null
						&& cityPO != null && areaPO != null
						&& !StringHelper.isNull(userRealAddress)
						&& !StringHelper.isNull(userRealName)
						&& !StringHelper.isNull(userRealMobile)) {
					cri.setCountryId(1);
					cri.setCountryName("中国");

					if (provincePO != null) {
						cri.setProvinceId(provincePO.getId());
						cri.setProvinceName(provincePO.getProvince());
					}
					if (cityPO != null) {
						cri.setCityId(cityPO.getId());
						cri.setCityName(cityPO.getCity());
					}
					if (areaPO != null) {
						cri.setAreaId(areaPO.getId());
						cri.setAreaName(areaPO.getArea());
					}
					if (!StringHelper.isNull(userRealAddress)) {
						cri.setUserRealAddress(userRealAddress);
					}
					if (clientUserInfo != null) {
						cri.setClientUserInfoId(clientUserInfo.getId());
					}
					if (!StringHelper.isNull(userRealMobile)) {
						cri.setUserRealMobile(userRealMobile);
					}
					if (!StringHelper.isNull(userRealPlane)) {
						cri.setUserRealPlane(userRealPlane);
					}
					if (!StringHelper.isNull(userRealName)) {
						cri.setUserRealName(userRealName);
					}
					if (!StringHelper.isNull(userRealPostcode)) {
						cri.setUserRealPostcode(userRealPostcode);
					}
					if (!StringHelper.isNull(staffId)) {
						cri.setStaffId(Integer.parseInt(staffId));
					}
					cri.setCreateTime(new Timestamp(System.currentTimeMillis()));
					cri.setModifyTime(new Timestamp(System.currentTimeMillis()));
					clientUserRegionInfoService.saveBusiness(cri);
				} else {
					cri = clientUserRegionInfoService
							.findByid(clientUserRegionInfoId);
				}
			}
			// 增加新订单
			BusinessOrderRecord orderRecord = new BusinessOrderRecord();
			if (clientUserInfo != null) {
				orderRecord.setClientUserId(clientUserInfo.getId());
			}
			String outTradeNo = StringHelper.getSystime("yyyyMMddHHmmss")
					+ StringHelper.getRandom(5);
			orderRecord.setOrderNo(outTradeNo);
			orderRecord.setClientUserName(staff.getStaffName());
			orderRecord.setClientUserMobile(staff.getMobile());
			orderRecord.setClientUserEmail(staff.getEmail());
			orderRecord.setStaffId(staffId);
			orderRecord.setUserRealMobile(cri.getUserRealMobile());
			orderRecord.setCountryId(cri.getCountryId());
			orderRecord.setCountryName(cri.getCountryName());
			orderRecord.setProvinceId(cri.getProvinceId());
			orderRecord.setProvinceName(cri.getProvinceName());
			orderRecord.setCityId(cri.getCityId());
			orderRecord.setCityName(cri.getCityName());
			orderRecord.setAreaId(cri.getAreaId());
			orderRecord.setAreaName(cri.getAreaName());
			orderRecord.setUserRealAddress(cri.getUserRealAddress());
			orderRecord.setUserRealPlane(cri.getUserRealPlane());
			orderRecord.setUserRealName(cri.getUserRealName());
			orderRecord.setUserRealPostcode(cri.getUserRealPostcode());
			orderRecord.setTheEarliestTime(staff.getTheEarliestTime());
			orderRecord.setTheLatestTime(staff.getTheLatestTime());
			orderRecord.setPrimaryConfigurationId(1);
			orderRecord.setPrimaryConfigurationName("小农人");
			orderRecord.setBillTime(211);
			orderRecord.setBillTimeName("日结");
			if (!StringHelper.isNull(userRealTime)) {
				orderRecord.setUserRealTime(Timestamp.valueOf(userRealTime));
			}
			if (!StringHelper.isNull(userRealDescription)) {
				orderRecord.setUserRealDescription(userRealDescription);
			}
			if (!StringHelper.isNull(paymentProvider)) {
				orderRecord.setPaymentProvider(Integer
						.parseInt(paymentProvider));
				Status statusCode = statusService.findByid(paymentProvider);
				orderRecord.setPaymentProviderName(statusCode.getStatusName());
				// 如果是货到付款，状态为处理中
				if (paymentProvider.equals("197")) {
					orderRecord.setOperateStatus(204);
				}
				// 其他支付模式，状态为待处理
				else {
					orderRecord.setOperateStatus(203);
				}
				statusCode = statusService.findByid(orderRecord
						.getOperateStatus().toString());
				orderRecord.setOperateStatusName(statusCode.getStatusName());
			}
			if (!StringHelper.isNull(paymentType)) {
				orderRecord.setPaymentType(Integer.parseInt(paymentType));
				Status statusCode = statusService.findByid(paymentType);
				orderRecord.setPaymentTypeName(statusCode.getStatusName());
			}
			if (!StringHelper.isNull(logisticsCompanyId)) {
				orderRecord.setLogisticsCompanyId(Integer
						.parseInt(logisticsCompanyId));
				LogisticsCompany logisticsCompanyCode = logisticsCompanyService
						.findByid(logisticsCompanyId);
				orderRecord.setLogisticsCompanyName(logisticsCompanyCode
						.getCompanyName());
			}
			if (!StringHelper.isNull(requiredDeliveryTime)) {
				orderRecord.setRequiredDeliveryTime(Timestamp
						.valueOf(requiredDeliveryTime + " 00:00:00"));
			} else {
				Long l = StringHelper.getNextTimeLong(1, 1,
						System.currentTimeMillis());
				String str = StringHelper.getSystime("yyyy-MM-dd", l);

				orderRecord.setRequiredDeliveryTime(Timestamp.valueOf(str
						+ " 00:00:00"));
			}
			if (!StringHelper.isNull(finalDeliveryTime)) {
				orderRecord.setFinalDeliveryTime(Timestamp
						.valueOf(finalDeliveryTime + " 00:00:00"));
			}
			orderRecord.setPaymentStatus(201);
			Status statusCode = statusService.findByid(orderRecord
					.getPaymentStatus().toString());
			orderRecord.setPaymentStatusName(statusCode.getStatusName());
			orderRecord
					.setOperateTime(new Timestamp(System.currentTimeMillis()));
			orderRecord
					.setCreateTime(new Timestamp(System.currentTimeMillis()));
			// 累积订单价格
			Iterator<String> it = goodsList.keySet().iterator();
			Float tempOriginalPrice = 0f;
			Float tempTotalPrice = 0f;
			Float tempPurchasePrice = 0f;
			while (it.hasNext()) {
				String key = it.next();
				BusinessOrderVO dvo = goodsList.get(key);
				BusinessGoods goods = dvo.getBusinessGoodsVO()
						.getBusinessGoods();
				// 取得商品数量
				BigDecimal d1 = new BigDecimal(dvo
						.getBusinessOrderGoodsRelation().getGoodsCount());
				BigDecimal d2;
				// 计算原价
				d2 = new BigDecimal(goods.getGoodsOriginalPrice());
				BigDecimal o1 = new BigDecimal(d1.multiply(d2).floatValue());
				BigDecimal o2 = new BigDecimal(tempOriginalPrice);
				tempOriginalPrice = o1.add(o2).floatValue();
				// 计算进货价
				d2 = new BigDecimal(goods.getGoodsPurchasePrice());
				BigDecimal p1 = new BigDecimal(d1.multiply(d2).floatValue());
				BigDecimal p2 = new BigDecimal(tempPurchasePrice);
				tempPurchasePrice = p1.add(p2).floatValue();

				// 折扣计算(当折扣小于1的时候，则进行折扣计算)
				if (staff.getDiscount() != null && staff.getDiscount() != 1
						&& staff.getDiscount() > 0) {
					BigDecimal t1 = new BigDecimal(staff.getDiscount());
					BigDecimal t2 = new BigDecimal(tempOriginalPrice);
					tempTotalPrice = t1.multiply(t2).floatValue();
					orderRecord.setDiscount(staff.getDiscount());
				} else {
					tempTotalPrice = tempOriginalPrice;
					orderRecord.setDiscount(1f);
				}
			}
			orderRecord.setOriginalPrice(tempOriginalPrice);
			orderRecord.setTotalPrice(tempTotalPrice);
			orderRecord.setPurchasePrice(tempPurchasePrice);
			// 如果填写最终金额，则以填写金额为主
			if (!StringHelper.isNull(totalPrice)) {
				orderRecord.setTotalPrice(Float.valueOf(totalPrice));
			}
			orderRecord.setDeliveryStaffId(deliveryStaffId);
			orderRecord.setDeliveryStaffMobile(deliveryStaffMobile);
			orderRecord.setDeliveryStaffName(deliveryStaffName);
			orderRecord.setDeliveryStatus(207);
			statusCode = statusService.findByid(orderRecord.getDeliveryStatus()
					.toString());
			orderRecord.setDeliveryStatusName(statusCode.getStatusName());
			if (!StringHelper.isNull(primaryConfigurationId)) {
				orderRecord.setPrimaryConfigurationId(Integer
						.parseInt(primaryConfigurationId));
				PrimaryConfiguration primaryConfiguration = primaryConfigurationService
						.findByid(primaryConfigurationId);
				orderRecord.setPrimaryConfigurationName(primaryConfiguration
						.getMallName());
			}
			Long newOrderRecordId = orderRecordService.save(orderRecord);
			orderRecordId = String.valueOf(newOrderRecordId);
			// 为新订单添加商品
			it = goodsList.keySet().iterator();
			while (it.hasNext()) {

				String key = it.next();
				BusinessOrderVO dvo = goodsList.get(key);
				BusinessOrderGoodsRelation ogr = new BusinessOrderGoodsRelation();
				BusinessGoods goods = dvo.getBusinessGoodsVO()
						.getBusinessGoods();
				ogr.setOrderRecordId(newOrderRecordId);
				if (clientUserInfo != null) {
					ogr.setClientUserId(clientUserInfo.getId());
				}
				ogr.setGoodsId(goods.getId());
				ogr.setGoodsCount(dvo.getBusinessOrderGoodsRelation()
						.getGoodsCount());
				ogr.setPurchasePrice(goods.getGoodsPurchasePrice());
				BusinessGoods p = goodsService.findByid(goods.getId()
						.toString());
				ogr.setGoodsWeightId(p.getGoodsWeightId());
				// 计算折扣后价格
				if (staff.getDiscount() != null && staff.getDiscount() != 1
						&& staff.getDiscount() > 0) {
					BigDecimal r1 = new BigDecimal(staff.getDiscount());
					BigDecimal r2 = new BigDecimal(
							goods.getGoodsOriginalPrice());
					ogr.setDiscount(staff.getDiscount());
					ogr.setCurrentPrice(r1.multiply(r2).floatValue());
					ogr.setCurrentPriceType(216);
					ogr.setOriginalPrice(r1.multiply(r2).floatValue());
				} else {
					ogr.setDiscount(1f);
					ogr.setCurrentPrice(goods.getGoodsOriginalPrice());
					ogr.setCurrentPriceType(215);
					ogr.setOriginalPrice(goods.getGoodsOriginalPrice());
				}
				ogr.setStaffId(staffId);
				ogr.setPrimaryConfigurationId(1);
				ogr.setOrderGoodsRelationTime(new Timestamp(System
						.currentTimeMillis()));
				orderGoodsRelationService.save(ogr);
			}

			log.debug("end:::");
		} else {
			super.error("用户不存在");
		}
		super.success(null, AjaxResult.CALL_BACK_TYPE_CLOSECURRENT,
				"businessOrderRecord", null);
		return null;
	}

	/**
	 * 带信息到修改页
	 * 
	 * @return String
	 */
	@Action(value = "modifyinfo", results = {
			@Result(name = StrutsResMSG.SUCCESS, location = "/business/admin/orderrecord/modify.jsp"),
			@Result(name = StrutsResMSG.FAILED, location = "/business/admin/orderrecord/modify.jsp") })
	public String modifyinfo() {
		findPaymentStatusList();
		findPaymentProviderList();
		findLogisticsCompanyList();
		findOperateStatusList();
		findDeliveryStatus();
		findPaymentTypeList();
		//findStaffList();
		// 加载该订单所有商品
		BusinessOrderVO vo = new BusinessOrderVO();
		BusinessOrderGoodsRelation po = new BusinessOrderGoodsRelation();
		if (!StringHelper.isNull(orderRecordId)) {
			po.setOrderRecordId(Long.valueOf(orderRecordId));
		}
		if (!StringHelper.isNull(clientUserId)) {
			po.setClientUserId(Integer.parseInt(clientUserId));
		}
		if (!StringHelper.isNull(orderGoodsRelationGoodsId)) {
			po.setGoodsId(Integer.parseInt(orderGoodsRelationGoodsId));
		}
		if (!StringHelper.isNull(orderGoodsRelationGoodsCount)) {
			po.setGoodsCount(Integer.parseInt(orderGoodsRelationGoodsCount));
		}
		if (!StringHelper.isNull(orderGoodsRelationPrimaryConfigurationId)) {
			po.setPrimaryConfigurationId(Integer
					.parseInt(orderGoodsRelationPrimaryConfigurationId));
		}
		po.setStaffId(deliveryStaffId);
		if (!StringHelper.isNull(orderGoodsRelationCurrentPriceType)) {
			po.setCurrentPriceType(Integer
					.parseInt(orderGoodsRelationCurrentPriceType));
		}
		vo.setCreateStartTime(createStartTime);
		vo.setCreateEndTime(createEndTime);
		vo.setBusinessOrderGoodsRelation(po);
		dvList = orderGoodsRelationService.listVO(vo, 0, 0, orderField,
				orderDirection);
		// 加载该订单信息
		orderRecord = orderRecordService.findByid(orderRecordId);
		
		
		
		FarmerOrderVO farmerOrderVO = new FarmerOrderVO();
		
		FarmerOrderRecord farmerOrder = new FarmerOrderRecord();
		
		farmerOrder.setOrderRecordId(Long.parseLong(orderRecordId));
		
		farmerOrderVO.setFarmerOrder(farmerOrder);
		
		farmerOrderList = farmerOrderService.listVO(farmerOrderVO,0,0,null,null);
		
		return StrutsResMSG.SUCCESS;
	}

	/*
	 * 加载所有数量单位
	 */
	private void findBusinessWeightList() {
		this.weightList = weightService.listAll();
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
		log.debug("modify orderRecord: " + orderRecordId);
		// 取得当前登录人信息
		currentStaff = super.getCurrentStaff();
		// 批量增加菜品
		if (goodsList == null) {
			super.error("未填写商品");
		} else {
			log.debug("start:::");
			ClientUserRegionInfo cri = new ClientUserRegionInfo();
			if (!StringHelper.isNull(clientUserRegionInfoId)) {
				if (clientUserRegionInfoId.equals("new") && provincePO != null
						&& cityPO != null && areaPO != null
						&& !StringHelper.isNull(userRealAddress)
						&& !StringHelper.isNull(userRealName)
						&& !StringHelper.isNull(userRealMobile)) {
					cri.setCountryId(1);
					cri.setCountryName("中国");

					if (provincePO != null) {
						cri.setProvinceId(provincePO.getId());
						cri.setProvinceName(provincePO.getProvince());
					}
					if (cityPO != null) {
						cri.setCityId(cityPO.getId());
						cri.setCityName(cityPO.getCity());
					}
					if (areaPO != null) {
						cri.setAreaId(areaPO.getId());
						cri.setAreaName(areaPO.getArea());
					}
					if (!StringHelper.isNull(userRealAddress)) {
						cri.setUserRealAddress(userRealAddress);
					}
					if (!StringHelper.isNull(clientUserId)) {
						cri.setClientUserInfoId(Integer.parseInt(clientUserId));
					}
					if (!StringHelper.isNull(userRealMobile)) {
						cri.setUserRealMobile(userRealMobile);
					}
					if (!StringHelper.isNull(userRealPlane)) {
						cri.setUserRealPlane(userRealPlane);
					}
					if (!StringHelper.isNull(userRealName)) {
						cri.setUserRealName(userRealName);
					}
					if (!StringHelper.isNull(userRealPostcode)) {
						cri.setUserRealPostcode(userRealPostcode);
					}
					if (!StringHelper.isNull(staffId)) {
						cri.setStaffId(Integer.parseInt(staffId));
					}
					cri.setCreateTime(new Timestamp(System.currentTimeMillis()));
					cri.setModifyTime(new Timestamp(System.currentTimeMillis()));
					clientUserRegionInfoService.saveBusiness(cri);
				} else {
					cri = clientUserRegionInfoService
							.findByid(clientUserRegionInfoId);
				}
			}
			// 修改订单
			BusinessOrderRecord oldOrderRecord = new BusinessOrderRecord();
			oldOrderRecord = orderRecordService.findByid(orderRecordId);
			CommonStaff staff = staffService.findByid(oldOrderRecord
					.getStaffId());
			if (!StringHelper.isNull(clientUserId)) {
				oldOrderRecord.setClientUserId(Integer.parseInt(clientUserId));
			}
			if (!StringHelper.isNull(clientUserName)) {
				oldOrderRecord.setClientUserName(clientUserName);
			}
			if (!StringHelper.isNull(clientUserMobile)) {
				oldOrderRecord.setClientUserMobile(clientUserMobile);
			}
			if (!StringHelper.isNull(clientUserEmail)) {
				oldOrderRecord.setClientUserEmail(clientUserEmail);
			}
			if (!StringHelper.isNull(clientUserSex)) {
				oldOrderRecord.setClientUserSex(clientUserSex);
			}
			if (!StringHelper.isNull(wxOpenId)) {
				oldOrderRecord.setWxOpenId(wxOpenId);
			}
			if (!StringHelper.isNull(staffId)) {
				oldOrderRecord.setStaffId(staffId);
			}
			if (!StringHelper.isNull(userRealMobile)) {
				oldOrderRecord.setUserRealMobile(userRealMobile);
			}
			if (!StringHelper.isNull(userRealDescription)) {
				oldOrderRecord.setUserRealDescription(userRealDescription);
			}
			if (!StringHelper.isNull(countryId)) {
				oldOrderRecord.setCountryId(Integer.parseInt(countryId));
			}
			if (!StringHelper.isNull(countryName)) {
				oldOrderRecord.setCountryName(countryName);
			}
			if (countryPO != null) {
				oldOrderRecord.setCountryId(countryPO.getId());
				oldOrderRecord.setCountryName(countryPO.getCountry());
			}
			if (provincePO != null) {
				oldOrderRecord.setProvinceId(provincePO.getId());
				oldOrderRecord.setProvinceName(provincePO.getProvince());
			}
			if (cityPO != null) {
				oldOrderRecord.setCityId(cityPO.getId());
				oldOrderRecord.setCityName(cityPO.getCity());
			}
			if (areaPO != null) {
				oldOrderRecord.setAreaId(areaPO.getId());
				oldOrderRecord.setAreaName(areaPO.getArea());
			}
			if (!StringHelper.isNull(userRealAddress)) {
				oldOrderRecord.setUserRealAddress(userRealAddress);
			}
			if (!StringHelper.isNull(userRealPlane)) {
				oldOrderRecord.setUserRealPlane(userRealPlane);
			}
			if (!StringHelper.isNull(userRealName)) {
				oldOrderRecord.setUserRealName(userRealName);
			}
			if (!StringHelper.isNull(userRealPostcode)) {
				oldOrderRecord.setUserRealPostcode(userRealPostcode);
			}
			if (!StringHelper.isNull(userRealTime)) {
				oldOrderRecord.setUserRealTime(Timestamp.valueOf(userRealTime));
			}
			if (!StringHelper.isNull(paymentType)) {
				oldOrderRecord.setPaymentType(Integer.parseInt(paymentType));
				Status statusCode = statusService.findByid(paymentType);
				oldOrderRecord.setPaymentTypeName(statusCode.getStatusName());
			}
			if (!StringHelper.isNull(paymentStatus)) {
				oldOrderRecord
						.setPaymentStatus(Integer.parseInt(paymentStatus));
				Status statusCode = statusService.findByid(paymentStatus);
				oldOrderRecord.setPaymentStatusName(statusCode.getStatusName());
			}
			if (!StringHelper.isNull(paymentProvider)) {
				oldOrderRecord.setPaymentProvider(Integer
						.parseInt(paymentProvider));
				Status statusCode = statusService.findByid(paymentProvider);
				oldOrderRecord.setPaymentProviderName(statusCode
						.getStatusName());
			}
			if (!StringHelper.isNull(logisticsCompanyId)) {
				oldOrderRecord.setLogisticsCompanyId(Integer
						.parseInt(logisticsCompanyId));
				LogisticsCompany logisticsCompanyCode = logisticsCompanyService
						.findByid(logisticsCompanyId);
				oldOrderRecord.setLogisticsCompanyName(logisticsCompanyCode
						.getCompanyName());
			}
			oldOrderRecord.setOperateTime(new Timestamp(System
					.currentTimeMillis()));
			if (!StringHelper.isNull(operateStatus)) {
				oldOrderRecord
						.setOperateStatus(Integer.parseInt(operateStatus));
				Status statusCode = statusService.findByid(operateStatus);
				oldOrderRecord.setOperateStatusName(statusCode.getStatusName());
			}
			if (!StringHelper.isNull(requiredDeliveryTime)) {
				oldOrderRecord.setRequiredDeliveryTime(Timestamp
						.valueOf(requiredDeliveryTime + " 00:00:00"));
			}
			if (!StringHelper.isNull(orderCreateTime)) {
				oldOrderRecord.setCreateTime(Timestamp.valueOf(orderCreateTime
						+ " 00:00:00"));
			}
			if (!StringHelper.isNull(finalDeliveryTime)) {
				oldOrderRecord.setFinalDeliveryTime(Timestamp
						.valueOf(finalDeliveryTime));
			}
			// 累积订单价格
			Iterator<String> it = goodsList.keySet().iterator();
			Float tempOriginalPrice = 0f;
			Float tempTotalPrice = 0f;
			Float tempPurchasePrice = 0f;
			while (it.hasNext()) {
				String key = it.next();
				BusinessOrderVO dvo = goodsList.get(key);
				BusinessGoods goods = dvo.getBusinessGoodsVO()
						.getBusinessGoods();
				// 取得商品数量
				BigDecimal d1 = new BigDecimal(dvo
						.getBusinessOrderGoodsRelation().getGoodsCount());
				BigDecimal d2;
				// 计算原价
				d2 = new BigDecimal(goods.getGoodsOriginalPrice());
				BigDecimal o1 = new BigDecimal(d1.multiply(d2).floatValue());
				BigDecimal o2 = new BigDecimal(tempOriginalPrice);
				tempOriginalPrice = o1.add(o2).floatValue();

				// 计算进货价
				d2 = new BigDecimal(goods.getGoodsPurchasePrice());
				BigDecimal p1 = new BigDecimal(d1.multiply(d2).floatValue());
				BigDecimal p2 = new BigDecimal(tempPurchasePrice);
				tempPurchasePrice = p1.add(p2).floatValue();

				// 折扣计算(当折扣小于1的时候，则进行折扣计算)
				if (staff.getDiscount() != null && staff.getDiscount() != 1
						&& staff.getDiscount() > 0) {
					BigDecimal t1 = new BigDecimal(staff.getDiscount());
					BigDecimal t2 = new BigDecimal(tempOriginalPrice);
					tempTotalPrice = t1.multiply(t2).floatValue();
					oldOrderRecord.setDiscount(staff.getDiscount());
				} else {
					tempTotalPrice = tempOriginalPrice;
					oldOrderRecord.setDiscount(1f);
				}
			}
			oldOrderRecord.setOriginalPrice(tempOriginalPrice);
			oldOrderRecord.setTotalPrice(tempTotalPrice);
			oldOrderRecord.setPurchasePrice(tempPurchasePrice);
			// 如果填写最终金额，则以填写金额为主
			if (!StringHelper.isNull(totalPrice)) {
				oldOrderRecord.setTotalPrice(Float.valueOf(totalPrice));
			}
			if (!StringHelper.isNull(deliveryStaffId)) {
				oldOrderRecord.setDeliveryStaffId(deliveryStaffId);
			}
			if (!StringHelper.isNull(deliveryStaffMobile)) {
				oldOrderRecord.setDeliveryStaffMobile(deliveryStaffMobile);
			}
			if (!StringHelper.isNull(deliveryStaffName)) {
				oldOrderRecord.setDeliveryStaffName(deliveryStaffName);
			}
			if (!StringHelper.isNull(deliveryStatus)) {
				oldOrderRecord.setDeliveryStatus(Integer
						.parseInt(deliveryStatus));
				Status statusCode = statusService.findByid(deliveryStatus);
				oldOrderRecord
						.setDeliveryStatusName(statusCode.getStatusName());
			}
			if (!StringHelper.isNull(primaryConfigurationId)) {
				oldOrderRecord.setPrimaryConfigurationId(Integer
						.parseInt(primaryConfigurationId));
				PrimaryConfiguration primaryConfiguration = primaryConfigurationService
						.findByid(primaryConfigurationId);
				oldOrderRecord.setPrimaryConfigurationName(primaryConfiguration
						.getMallName());
			}
			orderRecordService.modify(oldOrderRecord);
			// 删除该订单所有商品
			orderGoodsRelationService.removeOrderRecordId(Long
					.valueOf(orderRecordId));
			// 为新订单添加商品
			it = goodsList.keySet().iterator();
			while (it.hasNext()) {
				String key = it.next();
				BusinessOrderVO dvo = goodsList.get(key);
				BusinessOrderGoodsRelation ogr = new BusinessOrderGoodsRelation();
				BusinessGoods goods = dvo.getBusinessGoodsVO()
						.getBusinessGoods();
				ogr.setOrderRecordId(Long.valueOf(orderRecordId));
				if (!StringHelper.isNull(clientUserId)) {
					ogr.setClientUserId(Integer.parseInt(clientUserId));
				}
				ogr.setGoodsId(goods.getId());
				ogr.setGoodsCount(dvo.getBusinessOrderGoodsRelation()
						.getGoodsCount());
				ogr.setOriginalPrice(goods.getGoodsOriginalPrice());
				ogr.setPurchasePrice(goods.getGoodsPurchasePrice());
				// 计算折扣后价格
				if (staff.getDiscount() != null && staff.getDiscount() != 1
						&& staff.getDiscount() > 0) {
					BigDecimal r1 = new BigDecimal(staff.getDiscount());
					BigDecimal r2 = new BigDecimal(
							goods.getGoodsOriginalPrice());
					ogr.setDiscount(staff.getDiscount());
					ogr.setCurrentPrice(r1.multiply(r2).floatValue());
					ogr.setCurrentPriceType(216);
				} else {
					ogr.setDiscount(1f);
					ogr.setCurrentPrice(goods.getGoodsOriginalPrice());
					ogr.setCurrentPriceType(215);
				}
				ogr.setStaffId(staffId);
				ogr.setPrimaryConfigurationId(1);
				ogr.setOrderGoodsRelationTime(new Timestamp(System
						.currentTimeMillis()));
				BusinessGoods p = goodsService.findByid(goods.getId()
						.toString());
				ogr.setGoodsWeightId(p.getGoodsWeightId());
				orderGoodsRelationService.save(ogr);
			}
			log.debug("end:::");
		}
		
		
		farmerOrderService.delByOrderId(Long.parseLong(orderRecordId));
		
		if(items!=null){
			Iterator<String> item = items.keySet().iterator();
			
			while (item.hasNext()) {
				String key = item.next();
				FarmerOrderRecord farmerOrder = items.get(key);
				
				OutPlan plan = planService.findById(farmerOrder.getOutPlanId().toString());
				
				farmerOrder.setFarmerUserId(plan.getUserId());
				farmerOrder.setGoodsId(Integer.parseInt(plan.getGoodsId()));
				farmerOrder.setOrderRecordId(Long.parseLong(orderRecordId));
				farmerOrder.setCreateTime(new Timestamp(new Date().getTime()));
				farmerOrder.setStaffId(this.getCurrentStaff().getId());
				plan.setOccupyAmount(plan.getOccupyAmount()+farmerOrder.getGoodsCount());
				plan.setValidAmount(plan.getValidAmount()-farmerOrder.getGoodsCount());
				if(deliveryStatus.equals("208"))
				{
					plan.setSendoutAmount(plan.getSendoutAmount()+farmerOrder.getGoodsCount());
				}
				planService.modify(plan);
				farmerOrderService.save(farmerOrder);
				
			}	
		}
		
		
		
		
		
		
		
		
		
		
		super.success(null, AjaxResult.CALL_BACK_TYPE_CLOSECURRENT,
				"businessOrderRecord", null);
		return null;
	}

	@Action(value = "del", results = { @Result(name = StrutsResMSG.SUCCESS, type = "plainText") })
	public String del() throws IOException, JSONException {
		orderRecordService.removeOrderRecordId(orderRecordId);
		orderGoodsRelationService.removeOrderRecordId(Long
				.valueOf(orderRecordId));
		super.success(null, null, "businessOrderRecord", null);
		return null;
	}

	private void setDateTime() {
		// 设置默认时间
		if (StringHelper.isNull(createStartTime)
				&& StringHelper.isNull(createEndTime)) {
			this.createStartTime = StringHelper.getSystime("yyyy-MM-dd");
			createStartTime = createStartTime + " 00:00:00";
			this.createEndTime = StringHelper.getSystime("yyyy-MM-dd");
			createEndTime = createEndTime + " 23:59:59";
		}
		if (StringHelper.isNull(operateStatus)) {
			this.operateStatus = "204";
		}

	}

	/**
	 * 外部调用，获取所有组织信息
	 * 
	 * @return String
	 * @throws IOException
	 */
	@Action(value = "all", results = { @Result(name = StrutsResMSG.SUCCESS, type = "plainText") })
	public String all() throws IOException {
		super.toJsonArray(orderRecordService.listAll());
		return null;
	}

	// clientUserRegionInfo
	private String clientUserRegionInfoId;
	private String clientUserRegionInfoUserRealAddress;
	private String clientUserInfoId;
	private String clientUserRegionInfoUserRealMobile; // 收货人用户手机号
	private String clientUserRegionInfoUserRealPlane; // 收货人用户座机
	private String clientUserRegionInfoUserRealName; // 收货人名称
	private String clientUserRegionInfoUserRealPostcode; // 邮政编码
	private String clientUserRegionInfoCreateTime;
	private String clientUserRegionInfoModifyTime;
	private String clientUserRegionInfoUserMobile;
	private String clientUserRegionInfoWxnickname;
	private String clientUserRegionInfoWxopenuid;
	private String clientUserRegionInfoEmail;
	private String clientUserRegionInfoSourceType;

	private String address;
	private List<Status> paymentTypeList;
	private List<Status> paymentStatusList;
	private List<Status> paymentProviderList;
	private List<Status> operateStatusList;
	private List<Status> deliveryStatusList;
	private List<StaffVO> staffList;
	private List<BusinessGoods> allGoodsList;
	private List<ClientUserRegionInfo> clientUserRegionInfoList;
	private Map<String, BusinessOrderVO> goodsList;
	private List<BusinessOrderVO> dvList;
	
	private List<FarmerOrderVO> farmerOrderList;
	
	private List<LogisticsCompany> logisticsCompanyList;
	private CommonStaff currentStaff;
	private CommonStaff deliveryStaff;
	private List<BusinessOrderVO> voList;
	private BusinessOrderVO businessOrderVO;
	private BusinessOrderRecord orderRecord;
	private BusinessGoods goods;
	private Status status;
	private Status isDiscount;
	private Country countryPO;
	private Province provincePO;
	private City cityPO;
	private Area areaPO;

	private String divid;

	// orderRecord
	private String orderRecordId;

	private String clientUserId; // 用户ID

	private String clientUserName; // 用户昵称

	private String clientUserMobile; // 用户手机号

	private String clientUserEmail; // 用户邮箱

	private String clientUserSex; // 用户性别

	private String clientUserType; // 用户类型（微信，APP，WEB，电话）

	private String clientUserTypeName; // 用户类型名称（微信，APP，WEB，电话）

	private String clientUserInfoStatus; // 用户状态（正常，屏蔽）

	private String clientUserInfoStatusName; // 用户状态（正常，屏蔽）

	private String clientUserInfoType; // 用户类型（客户端用户，WX用户，电话用户）

	private String clientUserInfoTypeName; // 用户类型（客户端用户，WX用户，电话用户）

	private String wxOpenId; // 微信OpenId

	private String staffId; // 隶属用户Id
	
	private String userRealMobile; // 收货人用户手机号

	private String userRealPlane; // 收货人用户座机

	private String userRealName; // 收货人名称

	private String countryId; // 收货人国家

	private String countryName; // 收货人国家名称

	private String provinceId; // 收货人省份

	private String provinceName; // 收货人省份名称

	private String cityId; // 收货人城市

	private String cityName; // 收货人城市名称

	private String areaId; // 收货人区县

	private String areaName; // 收货人区县名称

	private String userRealAddress; // 收货人地址

	private String userRealPostcode; // 邮政编码

	private String userRealDescription; // 备注

	private String userRealTime; // 用户收货时间

	private String userRealStartTime; // 用户收货开始时间

	private String userRealEndTime; // 用户收货结束时间

	private String paymentType; // 支付类型，充值账户支付，单次支付

	private String paymentTypeName; // 支付类型名称，充值账户支付，单次支付

	private String paymentStatus; // 支付状态，已支付，待支付，退款

	private String paymentStatusName; // 支付状态名称，已支付，待支付，退款

	private String paymentProvider; // 支付提供者，微信，支付宝，银联

	private String paymentProviderName; // 支付提供者名称，微信，支付宝，银联

	private String paymentTime; // 支付时间

	private String paymentStartTime; // 支付开始时间

	private String paymentEndTime; // 支付结束时间

	private String operateTime; // 订单操作时间（待处理，处理中，处理完成，订单退单）

	private String operateStartTime; // 订单操作开始时间（待处理，处理中，处理完成，订单退单）

	private String operateEndTime; // 订单操作结束时间（待处理，处理中，处理完成，订单退单）

	private String operateStatus; // 订单操作状态（待处理，处理中，处理完成，订单退单）

	private String operateStatusName; // 订单操作状态名称（待处理，处理中，处理完成，订单退单）

	private String createTime; // 订单生成时间

	private String createStartTime; // 订单生成开始时间

	private String createEndTime; // 订单生成结束时间

	private String originalPrice; // 原始结算价格

	private String totalPrice; // 最终结算价格

	private String purchasePrice; // 进货价格

	private String discount; // 当前用户折扣

	private String logisticsCompanyId; // 配送公司ID

	private String logisticsCompanyName; // 配送公司名称

	private String deliveryStaffId; // 送货人员ID

	private String deliveryStaffName; // 送货人员姓名

	private String deliveryStaffMobile; // 送货人员手机号

	private String deliveryStartTime; // 送货起始时间

	private String deliveryStartStartTime; // 送货查询起始时间

	private String deliveryStartEndTime; // 送货查询结束时间

	private String deliveryEndTime; // 送货送达时间

	private String deliveryEndStartTime; // 送货查询起始送达时间

	private String deliveryEndEndTime; // 送货查询结束送达时间

	private String requiredDeliveryTime; // 用户要求送达时间

	private String orderCreateTime; // 订单创建时间

	private String requiredDeliveryStartTime; // 用户要求送达查询起始时间

	private String requiredDeliveryEndTime; // 用户要求送达查询结束时间

	private String finalDeliveryTime; // 最终送达时间

	private String finalDeliveryStartTime; // 最终送达查询起始时间

	private String finalDeliveryEndTime; // 最终送达查询结束时间

	private String deliveryStatus; // 配送状态（未发货，已发货）

	private String deliveryStatusName; // 配送状态名称（未发货，已发货）

	private String sourceId; // 该订单用户推广来源ID（有可能是用户推广，代理商推广，线上推广）

	private String sourceName; // 该订单用户推广来源名称（有可能是用户推广，代理商推广，线上推广）

	private String sourceType; // 该订单用户推广来源类型 （有可能是用户推广，代理商推广，线上推广）

	private String sourceTypeName; // 该订单用户推广来源类型名称 （有可能是用户推广，代理商推广，线上推广）

	private String serNo; // 银行流水号

	private String sellerId; // 代理商ID

	private String sellerName; // 代理商名称

	private String cusId; // 合作方管理

	private String cusName; // 合作方名称

	private String primaryConfigurationId; // 对应商城ID

	private String primaryConfigurationName; // 对应商城名称

	// orderGoodsRelation
	private String orderGoodsRelationId;

	private String orderGoodsRelationOrderRecordId; // 订单ID

	private String orderNo; // 自定义订单编号

	private String orderGoodsRelationClientUserId; // 用户ID

	private String orderGoodsRelationGoodsId; // 商品Id

	private String orderGoodsRelationGoodsCount; // 商品数量

	private String orderGoodsRelationCurrentPrice; // 当前价格

	private String orderGoodsRelationCurrentPriceType; // 当前价格类型（原价，优惠价）

	private String orderGoodsRelationStaffId; // 隶属用户Id

	private String orderGoodsRelationPrimaryConfigurationId; // 对应商城ID

	private String orderGoodsRelationTime; // 生成时间

	private List<BusinessWeight> weightList;

	private List<Status> billList;

	private List<Status> buyTeamList;
	
	private String productName;
	

	public String getOrderRecordId() {
		return orderRecordId;
	}

	public void setOrderRecordId(String orderRecordId) {
		this.orderRecordId = orderRecordId;
	}

	public String getClientUserId() {
		return clientUserId;
	}

	public void setClientUserId(String clientUserId) {
		this.clientUserId = clientUserId;
	}

	public String getClientUserName() {
		return clientUserName;
	}

	public void setClientUserName(String clientUserName) {
		this.clientUserName = clientUserName;
	}

	public String getClientUserMobile() {
		return clientUserMobile;
	}

	public void setClientUserMobile(String clientUserMobile) {
		this.clientUserMobile = clientUserMobile;
	}

	public String getClientUserEmail() {
		return clientUserEmail;
	}

	public void setClientUserEmail(String clientUserEmail) {
		this.clientUserEmail = clientUserEmail;
	}

	public String getClientUserSex() {
		return clientUserSex;
	}

	public void setClientUserSex(String clientUserSex) {
		this.clientUserSex = clientUserSex;
	}

	public String getClientUserType() {
		return clientUserType;
	}

	public void setClientUserType(String clientUserType) {
		this.clientUserType = clientUserType;
	}

	public String getClientUserTypeName() {
		return clientUserTypeName;
	}

	public void setClientUserTypeName(String clientUserTypeName) {
		this.clientUserTypeName = clientUserTypeName;
	}

	public String getClientUserInfoStatus() {
		return clientUserInfoStatus;
	}

	public void setClientUserInfoStatus(String clientUserInfoStatus) {
		this.clientUserInfoStatus = clientUserInfoStatus;
	}

	public String getClientUserInfoStatusName() {
		return clientUserInfoStatusName;
	}

	public void setClientUserInfoStatusName(String clientUserInfoStatusName) {
		this.clientUserInfoStatusName = clientUserInfoStatusName;
	}

	public String getClientUserInfoType() {
		return clientUserInfoType;
	}

	public void setClientUserInfoType(String clientUserInfoType) {
		this.clientUserInfoType = clientUserInfoType;
	}

	public String getClientUserInfoTypeName() {
		return clientUserInfoTypeName;
	}

	public void setClientUserInfoTypeName(String clientUserInfoTypeName) {
		this.clientUserInfoTypeName = clientUserInfoTypeName;
	}

	public String getWxOpenId() {
		return wxOpenId;
	}

	public void setWxOpenId(String wxOpenId) {
		this.wxOpenId = wxOpenId;
	}

	public String getStaffId() {
		return staffId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

	public String getUserRealMobile() {
		return userRealMobile;
	}

	public void setUserRealMobile(String userRealMobile) {
		this.userRealMobile = userRealMobile;
	}

	public String getUserRealPlane() {
		return userRealPlane;
	}

	public void setUserRealPlane(String userRealPlane) {
		this.userRealPlane = userRealPlane;
	}

	public String getUserRealName() {
		return userRealName;
	}

	public void setUserRealName(String userRealName) {
		this.userRealName = userRealName;
	}

	public String getCountryId() {
		return countryId;
	}

	public void setCountryId(String countryId) {
		this.countryId = countryId;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public String getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public String getCityId() {
		return cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getUserRealAddress() {
		return userRealAddress;
	}

	public void setUserRealAddress(String userRealAddress) {
		this.userRealAddress = userRealAddress;
	}

	public String getUserRealPostcode() {
		return userRealPostcode;
	}

	public void setUserRealPostcode(String userRealPostcode) {
		this.userRealPostcode = userRealPostcode;
	}

	public String getUserRealDescription() {
		return userRealDescription;
	}

	public void setUserRealDescription(String userRealDescription) {
		this.userRealDescription = userRealDescription;
	}

	public String getUserRealTime() {
		return userRealTime;
	}

	public void setUserRealTime(String userRealTime) {
		this.userRealTime = userRealTime;
	}

	public String getUserRealStartTime() {
		return userRealStartTime;
	}

	public void setUserRealStartTime(String userRealStartTime) {
		this.userRealStartTime = userRealStartTime;
	}

	public String getUserRealEndTime() {
		return userRealEndTime;
	}

	public void setUserRealEndTime(String userRealEndTime) {
		this.userRealEndTime = userRealEndTime;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public String getPaymentTypeName() {
		return paymentTypeName;
	}

	public void setPaymentTypeName(String paymentTypeName) {
		this.paymentTypeName = paymentTypeName;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public String getPaymentStatusName() {
		return paymentStatusName;
	}

	public void setPaymentStatusName(String paymentStatusName) {
		this.paymentStatusName = paymentStatusName;
	}

	public String getPaymentProvider() {
		return paymentProvider;
	}

	public void setPaymentProvider(String paymentProvider) {
		this.paymentProvider = paymentProvider;
	}

	public String getPaymentProviderName() {
		return paymentProviderName;
	}

	public void setPaymentProviderName(String paymentProviderName) {
		this.paymentProviderName = paymentProviderName;
	}

	public String getPaymentTime() {
		return paymentTime;
	}

	public void setPaymentTime(String paymentTime) {
		this.paymentTime = paymentTime;
	}

	public String getPaymentStartTime() {
		return paymentStartTime;
	}

	public void setPaymentStartTime(String paymentStartTime) {
		this.paymentStartTime = paymentStartTime;
	}

	public String getPaymentEndTime() {
		return paymentEndTime;
	}

	public void setPaymentEndTime(String paymentEndTime) {
		this.paymentEndTime = paymentEndTime;
	}

	public String getOperateTime() {
		return operateTime;
	}

	public void setOperateTime(String operateTime) {
		this.operateTime = operateTime;
	}

	public String getOperateStartTime() {
		return operateStartTime;
	}

	public void setOperateStartTime(String operateStartTime) {
		this.operateStartTime = operateStartTime;
	}

	public String getOperateEndTime() {
		return operateEndTime;
	}

	public void setOperateEndTime(String operateEndTime) {
		this.operateEndTime = operateEndTime;
	}

	public String getOperateStatus() {
		return operateStatus;
	}

	public void setOperateStatus(String operateStatus) {
		this.operateStatus = operateStatus;
	}

	public String getOperateStatusName() {
		return operateStatusName;
	}

	public void setOperateStatusName(String operateStatusName) {
		this.operateStatusName = operateStatusName;
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

	public String getRequiredDeliveryTime() {
		return requiredDeliveryTime;
	}

	public void setRequiredDeliveryTime(String requiredDeliveryTime) {
		this.requiredDeliveryTime = requiredDeliveryTime;
	}

	public String getFinalDeliveryTime() {
		return finalDeliveryTime;
	}

	public void setFinalDeliveryTime(String finalDeliveryTime) {
		this.finalDeliveryTime = finalDeliveryTime;
	}

	public String getRequiredDeliveryStartTime() {
		return requiredDeliveryStartTime;
	}

	public void setRequiredDeliveryStartTime(String requiredDeliveryStartTime) {
		this.requiredDeliveryStartTime = requiredDeliveryStartTime;
	}

	public String getRequiredDeliveryEndTime() {
		return requiredDeliveryEndTime;
	}

	public void setRequiredDeliveryEndTime(String requiredDeliveryEndTime) {
		this.requiredDeliveryEndTime = requiredDeliveryEndTime;
	}

	public String getFinalDeliveryStartTime() {
		return finalDeliveryStartTime;
	}

	public void setFinalDeliveryStartTime(String finalDeliveryStartTime) {
		this.finalDeliveryStartTime = finalDeliveryStartTime;
	}

	public String getFinalDeliveryEndTime() {
		return finalDeliveryEndTime;
	}

	public void setFinalDeliveryEndTime(String finalDeliveryEndTime) {
		this.finalDeliveryEndTime = finalDeliveryEndTime;
	}

	public String getOriginalPrice() {
		return originalPrice;
	}

	public void setOriginalPrice(String originalPrice) {
		this.originalPrice = originalPrice;
	}

	public String getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getLogisticsCompanyId() {
		return logisticsCompanyId;
	}

	public void setLogisticsCompanyId(String logisticsCompanyId) {
		this.logisticsCompanyId = logisticsCompanyId;
	}

	public String getLogisticsCompanyName() {
		return logisticsCompanyName;
	}

	public void setLogisticsCompanyName(String logisticsCompanyName) {
		this.logisticsCompanyName = logisticsCompanyName;
	}

	public String getDeliveryStaffId() {
		return deliveryStaffId;
	}

	public void setDeliveryStaffId(String deliveryStaffId) {
		this.deliveryStaffId = deliveryStaffId;
	}

	public String getDeliveryStaffName() {
		return deliveryStaffName;
	}

	public void setDeliveryStaffName(String deliveryStaffName) {
		this.deliveryStaffName = deliveryStaffName;
	}

	public String getDeliveryStaffMobile() {
		return deliveryStaffMobile;
	}

	public void setDeliveryStaffMobile(String deliveryStaffMobile) {
		this.deliveryStaffMobile = deliveryStaffMobile;
	}

	public String getDeliveryStartTime() {
		return deliveryStartTime;
	}

	public void setDeliveryStartTime(String deliveryStartTime) {
		this.deliveryStartTime = deliveryStartTime;
	}

	public String getDeliveryStartStartTime() {
		return deliveryStartStartTime;
	}

	public void setDeliveryStartStartTime(String deliveryStartStartTime) {
		this.deliveryStartStartTime = deliveryStartStartTime;
	}

	public String getDeliveryStartEndTime() {
		return deliveryStartEndTime;
	}

	public void setDeliveryStartEndTime(String deliveryStartEndTime) {
		this.deliveryStartEndTime = deliveryStartEndTime;
	}

	public String getDeliveryEndTime() {
		return deliveryEndTime;
	}

	public void setDeliveryEndTime(String deliveryEndTime) {
		this.deliveryEndTime = deliveryEndTime;
	}

	public String getDeliveryEndStartTime() {
		return deliveryEndStartTime;
	}

	public void setDeliveryEndStartTime(String deliveryEndStartTime) {
		this.deliveryEndStartTime = deliveryEndStartTime;
	}

	public String getDeliveryEndEndTime() {
		return deliveryEndEndTime;
	}

	public void setDeliveryEndEndTime(String deliveryEndEndTime) {
		this.deliveryEndEndTime = deliveryEndEndTime;
	}

	public String getDeliveryStatus() {
		return deliveryStatus;
	}

	public void setDeliveryStatus(String deliveryStatus) {
		this.deliveryStatus = deliveryStatus;
	}

	public String getDeliveryStatusName() {
		return deliveryStatusName;
	}

	public void setDeliveryStatusName(String deliveryStatusName) {
		this.deliveryStatusName = deliveryStatusName;
	}

	public String getSourceId() {
		return sourceId;
	}

	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}

	public String getSourceName() {
		return sourceName;
	}

	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}

	public String getSourceType() {
		return sourceType;
	}

	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}

	public String getSourceTypeName() {
		return sourceTypeName;
	}

	public void setSourceTypeName(String sourceTypeName) {
		this.sourceTypeName = sourceTypeName;
	}

	public String getSerNo() {
		return serNo;
	}

	public void setSerNo(String serNo) {
		this.serNo = serNo;
	}

	public String getSellerId() {
		return sellerId;
	}

	public void setSellerId(String sellerId) {
		this.sellerId = sellerId;
	}

	public String getSellerName() {
		return sellerName;
	}

	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}

	public String getCusId() {
		return cusId;
	}

	public void setCusId(String cusId) {
		this.cusId = cusId;
	}

	public String getCusName() {
		return cusName;
	}

	public void setCusName(String cusName) {
		this.cusName = cusName;
	}

	public String getPrimaryConfigurationId() {
		return primaryConfigurationId;
	}

	public void setPrimaryConfigurationId(String primaryConfigurationId) {
		this.primaryConfigurationId = primaryConfigurationId;
	}

	public String getPrimaryConfigurationName() {
		return primaryConfigurationName;
	}

	public void setPrimaryConfigurationName(String primaryConfigurationName) {
		this.primaryConfigurationName = primaryConfigurationName;
	}

	public String getOrderGoodsRelationId() {
		return orderGoodsRelationId;
	}

	public void setOrderGoodsRelationId(String orderGoodsRelationId) {
		this.orderGoodsRelationId = orderGoodsRelationId;
	}

	public String getOrderGoodsRelationOrderRecordId() {
		return orderGoodsRelationOrderRecordId;
	}

	public void setOrderGoodsRelationOrderRecordId(
			String orderGoodsRelationOrderRecordId) {
		this.orderGoodsRelationOrderRecordId = orderGoodsRelationOrderRecordId;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getOrderGoodsRelationClientUserId() {
		return orderGoodsRelationClientUserId;
	}

	public void setOrderGoodsRelationClientUserId(
			String orderGoodsRelationClientUserId) {
		this.orderGoodsRelationClientUserId = orderGoodsRelationClientUserId;
	}

	public String getOrderGoodsRelationGoodsId() {
		return orderGoodsRelationGoodsId;
	}

	public void setOrderGoodsRelationGoodsId(String orderGoodsRelationGoodsId) {
		this.orderGoodsRelationGoodsId = orderGoodsRelationGoodsId;
	}

	public String getOrderGoodsRelationGoodsCount() {
		return orderGoodsRelationGoodsCount;
	}

	public void setOrderGoodsRelationGoodsCount(
			String orderGoodsRelationGoodsCount) {
		this.orderGoodsRelationGoodsCount = orderGoodsRelationGoodsCount;
	}

	public String getOrderGoodsRelationCurrentPrice() {
		return orderGoodsRelationCurrentPrice;
	}

	public void setOrderGoodsRelationCurrentPrice(
			String orderGoodsRelationCurrentPrice) {
		this.orderGoodsRelationCurrentPrice = orderGoodsRelationCurrentPrice;
	}

	public String getOrderGoodsRelationCurrentPriceType() {
		return orderGoodsRelationCurrentPriceType;
	}

	public void setOrderGoodsRelationCurrentPriceType(
			String orderGoodsRelationCurrentPriceType) {
		this.orderGoodsRelationCurrentPriceType = orderGoodsRelationCurrentPriceType;
	}

	public String getOrderGoodsRelationStaffId() {
		return orderGoodsRelationStaffId;
	}

	public void setOrderGoodsRelationStaffId(String orderGoodsRelationStaffId) {
		this.orderGoodsRelationStaffId = orderGoodsRelationStaffId;
	}

	public String getOrderGoodsRelationPrimaryConfigurationId() {
		return orderGoodsRelationPrimaryConfigurationId;
	}

	public void setOrderGoodsRelationPrimaryConfigurationId(
			String orderGoodsRelationPrimaryConfigurationId) {
		this.orderGoodsRelationPrimaryConfigurationId = orderGoodsRelationPrimaryConfigurationId;
	}

	public String getOrderGoodsRelationTime() {
		return orderGoodsRelationTime;
	}

	public void setOrderGoodsRelationTime(String orderGoodsRelationTime) {
		this.orderGoodsRelationTime = orderGoodsRelationTime;
	}

	public String getClientUserRegionInfoId() {
		return clientUserRegionInfoId;
	}

	public void setClientUserRegionInfoId(String clientUserRegionInfoId) {
		this.clientUserRegionInfoId = clientUserRegionInfoId;
	}

	public String getClientUserRegionInfoUserRealAddress() {
		return clientUserRegionInfoUserRealAddress;
	}

	public void setClientUserRegionInfoUserRealAddress(
			String clientUserRegionInfoUserRealAddress) {
		this.clientUserRegionInfoUserRealAddress = clientUserRegionInfoUserRealAddress;
	}

	public String getClientUserInfoId() {
		return clientUserInfoId;
	}

	public void setClientUserInfoId(String clientUserInfoId) {
		this.clientUserInfoId = clientUserInfoId;
	}

	public String getClientUserRegionInfoUserRealMobile() {
		return clientUserRegionInfoUserRealMobile;
	}

	public void setClientUserRegionInfoUserRealMobile(
			String clientUserRegionInfoUserRealMobile) {
		this.clientUserRegionInfoUserRealMobile = clientUserRegionInfoUserRealMobile;
	}

	public String getClientUserRegionInfoUserRealPlane() {
		return clientUserRegionInfoUserRealPlane;
	}

	public void setClientUserRegionInfoUserRealPlane(
			String clientUserRegionInfoUserRealPlane) {
		this.clientUserRegionInfoUserRealPlane = clientUserRegionInfoUserRealPlane;
	}

	public String getClientUserRegionInfoUserRealName() {
		return clientUserRegionInfoUserRealName;
	}

	public void setClientUserRegionInfoUserRealName(
			String clientUserRegionInfoUserRealName) {
		this.clientUserRegionInfoUserRealName = clientUserRegionInfoUserRealName;
	}

	public String getClientUserRegionInfoUserRealPostcode() {
		return clientUserRegionInfoUserRealPostcode;
	}

	public void setClientUserRegionInfoUserRealPostcode(
			String clientUserRegionInfoUserRealPostcode) {
		this.clientUserRegionInfoUserRealPostcode = clientUserRegionInfoUserRealPostcode;
	}

	public String getClientUserRegionInfoCreateTime() {
		return clientUserRegionInfoCreateTime;
	}

	public void setClientUserRegionInfoCreateTime(
			String clientUserRegionInfoCreateTime) {
		this.clientUserRegionInfoCreateTime = clientUserRegionInfoCreateTime;
	}

	public String getClientUserRegionInfoModifyTime() {
		return clientUserRegionInfoModifyTime;
	}

	public void setClientUserRegionInfoModifyTime(
			String clientUserRegionInfoModifyTime) {
		this.clientUserRegionInfoModifyTime = clientUserRegionInfoModifyTime;
	}

	public String getClientUserRegionInfoUserMobile() {
		return clientUserRegionInfoUserMobile;
	}

	public void setClientUserRegionInfoUserMobile(
			String clientUserRegionInfoUserMobile) {
		this.clientUserRegionInfoUserMobile = clientUserRegionInfoUserMobile;
	}

	public String getClientUserRegionInfoWxnickname() {
		return clientUserRegionInfoWxnickname;
	}

	public void setClientUserRegionInfoWxnickname(
			String clientUserRegionInfoWxnickname) {
		this.clientUserRegionInfoWxnickname = clientUserRegionInfoWxnickname;
	}

	public String getClientUserRegionInfoWxopenuid() {
		return clientUserRegionInfoWxopenuid;
	}

	public void setClientUserRegionInfoWxopenuid(
			String clientUserRegionInfoWxopenuid) {
		this.clientUserRegionInfoWxopenuid = clientUserRegionInfoWxopenuid;
	}

	public String getClientUserRegionInfoEmail() {
		return clientUserRegionInfoEmail;
	}

	public void setClientUserRegionInfoEmail(String clientUserRegionInfoEmail) {
		this.clientUserRegionInfoEmail = clientUserRegionInfoEmail;
	}

	public String getClientUserRegionInfoSourceType() {
		return clientUserRegionInfoSourceType;
	}

	public void setClientUserRegionInfoSourceType(
			String clientUserRegionInfoSourceType) {
		this.clientUserRegionInfoSourceType = clientUserRegionInfoSourceType;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<Status> getPaymentTypeList() {
		return paymentTypeList;
	}

	public void setPaymentTypeList(List<Status> paymentTypeList) {
		this.paymentTypeList = paymentTypeList;
	}

	public List<Status> getPaymentStatusList() {
		return paymentStatusList;
	}

	public void setPaymentStatusList(List<Status> paymentStatusList) {
		this.paymentStatusList = paymentStatusList;
	}

	public List<Status> getPaymentProviderList() {
		return paymentProviderList;
	}

	public void setPaymentProviderList(List<Status> paymentProviderList) {
		this.paymentProviderList = paymentProviderList;
	}

	public List<Status> getOperateStatusList() {
		return operateStatusList;
	}

	public void setOperateStatusList(List<Status> operateStatusList) {
		this.operateStatusList = operateStatusList;
	}

	public List<Status> getDeliveryStatusList() {
		return deliveryStatusList;
	}

	public void setDeliveryStatusList(List<Status> deliveryStatusList) {
		this.deliveryStatusList = deliveryStatusList;
	}

	public List<StaffVO> getStaffList() {
		return staffList;
	}

	public void setStaffList(List<StaffVO> staffList) {
		this.staffList = staffList;
	}

	public List<BusinessGoods> getAllGoodsList() {
		return allGoodsList;
	}

	public void setAllGoodsList(List<BusinessGoods> allGoodsList) {
		this.allGoodsList = allGoodsList;
	}

	public List<ClientUserRegionInfo> getClientUserRegionInfoList() {
		return clientUserRegionInfoList;
	}

	public void setClientUserRegionInfoList(
			List<ClientUserRegionInfo> clientUserRegionInfoList) {
		this.clientUserRegionInfoList = clientUserRegionInfoList;
	}

	public Map<String, BusinessOrderVO> getGoodsList() {
		return goodsList;
	}

	public void setGoodsList(Map<String, BusinessOrderVO> goodsList) {
		this.goodsList = goodsList;
	}

	
	
	
	public Map<String, FarmerOrderRecord> getItems() {
		return items;
	}

	public void setItems(Map<String, FarmerOrderRecord> items) {
		this.items = items;
	}

	public List<BusinessOrderVO> getDvList() {
		return dvList;
	}

	public void setDvList(List<BusinessOrderVO> dvList) {
		this.dvList = dvList;
	}

	
	
	
	
	
	
	public List<FarmerOrderVO> getFarmerOrderList() {
		return farmerOrderList;
	}

	public void setFarmerOrderList(List<FarmerOrderVO> farmerOrderList) {
		this.farmerOrderList = farmerOrderList;
	}

	public List<LogisticsCompany> getLogisticsCompanyList() {
		return logisticsCompanyList;
	}

	public void setLogisticsCompanyList(
			List<LogisticsCompany> logisticsCompanyList) {
		this.logisticsCompanyList = logisticsCompanyList;
	}

	public CommonStaff getCurrentStaff() {
		return currentStaff;
	}

	public void setCurrentStaff(CommonStaff currentStaff) {
		this.currentStaff = currentStaff;
	}

	public CommonStaff getDeliveryStaff() {
		return deliveryStaff;
	}

	public void setDeliveryStaff(CommonStaff deliveryStaff) {
		this.deliveryStaff = deliveryStaff;
	}

	public List<BusinessOrderVO> getVoList() {
		return voList;
	}

	public void setVoList(List<BusinessOrderVO> voList) {
		this.voList = voList;
	}

	public BusinessOrderVO getBusinessOrderVO() {
		return businessOrderVO;
	}

	public void setBusinessOrderVO(BusinessOrderVO businessOrderVO) {
		this.businessOrderVO = businessOrderVO;
	}

	public BusinessOrderRecord getOrderRecord() {
		return orderRecord;
	}

	public void setOrderRecord(BusinessOrderRecord orderRecord) {
		this.orderRecord = orderRecord;
	}

	public BusinessGoods getGoods() {
		return goods;
	}

	public void setGoods(BusinessGoods goods) {
		this.goods = goods;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getPurchasePrice() {
		return purchasePrice;
	}

	public void setPurchasePrice(String purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	public String getDiscount() {
		return discount;
	}

	public void setDiscount(String discount) {
		this.discount = discount;
	}

	public Status getIsDiscount() {
		return isDiscount;
	}

	public void setIsDiscount(Status isDiscount) {
		this.isDiscount = isDiscount;
	}

	public Country getCountryPO() {
		return countryPO;
	}

	public void setCountryPO(Country countryPO) {
		this.countryPO = countryPO;
	}

	public Province getProvincePO() {
		return provincePO;
	}

	public void setProvincePO(Province provincePO) {
		this.provincePO = provincePO;
	}

	public City getCityPO() {
		return cityPO;
	}

	public void setCityPO(City cityPO) {
		this.cityPO = cityPO;
	}

	public Area getAreaPO() {
		return areaPO;
	}

	public void setAreaPO(Area areaPO) {
		this.areaPO = areaPO;
	}

	public String getDivid() {
		return divid;
	}

	public void setDivid(String divid) {
		this.divid = divid;
	}

	public List<BusinessWeight> getWeightList() {
		return weightList;
	}

	public void setWeightList(List<BusinessWeight> weightList) {
		this.weightList = weightList;
	}

	public CommonStaff getStaff() {
		return staff;
	}

	public void setStaff(CommonStaff staff) {
		this.staff = staff;
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

	public List<Status> getBuyTeamList() {
		return buyTeamList;
	}

	public void setBuyTeamList(List<Status> buyTeamList) {
		this.buyTeamList = buyTeamList;
	}

	public String getOrderCreateTime() {
		return orderCreateTime;
	}

	public void setOrderCreateTime(String orderCreateTime) {
		this.orderCreateTime = orderCreateTime;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getIfChild() {
		return ifChild;
	}
	public void setIfChild(String ifChild) {
		this.ifChild = ifChild;
	}
	
}
