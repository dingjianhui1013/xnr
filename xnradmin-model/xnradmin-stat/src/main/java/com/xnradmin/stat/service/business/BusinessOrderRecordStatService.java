/**
 *2012-5-11 上午9:00:02
 */
package com.xnradmin.stat.service.business;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.Region;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cntinker.util.FileHelper;
import com.cntinker.util.MathHelper;
import com.cntinker.util.StringHelper;
import com.cntinker.util.ZipHelper;
import com.xnradmin.core.dao.CommonDAO;
import com.xnradmin.core.dao.CommonJDBCDAO;
import com.xnradmin.core.dao.business.order.BusinessOrderRecordDAO;
import com.xnradmin.core.service.StaffService;
import com.xnradmin.core.service.common.status.StatusService;
import com.xnradmin.po.CommonStaff;
import com.xnradmin.po.business.BusinessCategory;
import com.xnradmin.po.business.BusinessGoods;
import com.xnradmin.po.business.BusinessOrderGoodsRelation;
import com.xnradmin.po.business.BusinessOrderRecord;
import com.xnradmin.po.common.status.Status;
import com.xnradmin.vo.business.BusinessOrderVO;

/**
 * @autohr: xiang_liu
 * 
 */
@Service("BusinessOrderRecordStatService")
public class BusinessOrderRecordStatService {

	@Autowired
	private BusinessOrderRecordDAO dao;

	@Autowired
	private CommonDAO commonDao;

	@Autowired
	private CommonJDBCDAO commonJDBCDAO;

	@Autowired
	private StaffService staffService;

	@Autowired
	private StatusService statusService;

	static Log log = LogFactory.getLog(BusinessOrderRecordStatService.class);

	/**
	 * @param query
	 * @return BusinessOrderVO
	 * @throws InstantiationException
	 * @throws ClassNotFoundException
	 * @throws InvocationTargetException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public List<String[]> getStatInfoDetail(BusinessOrderVO query)
			throws IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, ClassNotFoundException,
			InstantiationException {

		List<String[]> returnList = new ArrayList<String[]>();
		String hql = getTotalHql(query);
		List list = commonJDBCDAO.findBySQLListMap(hql, 0, 0);

		CommonStaff cus = null;

		if (query != null && query.getStaff() != null
				&& query.getStaff().getId() != null)
			cus = staffService.findByid(query.getStaff().getId().toString());

		for (int i = 0; i < list.size(); i++) {
			HashMap map = new HashMap();
			map = (HashMap) list.get(i);
			String[] content = new String[11];

			if (map == null)
				continue;
			content[0] = map.get("GOODS_NAME") == null ? "0" : map.get(
					"GOODS_NAME").toString();
			content[1] = map.get("sum(t.con)") == null ? "0" : StringHelper
					.formartDecimalToStr(new Float(map.get("sum(t.con)")
							.toString()).doubleValue(), "0.00");
			content[2] = map.get("WEIGHT_NAME") == null ? "0" : map.get(
					"WEIGHT_NAME").toString();
			content[3] = map.get("oprice") == null ? "0" :  StringHelper
					.formartDecimalToStr(new Float(map.get("oprice")
							.toString()).doubleValue(), "0.00");;
			content[4] = map.get("countprice") == null ? "0" : StringHelper
					.formartDecimalToStr(new Float(map.get("countprice")
							.toString()).doubleValue(), "0.00");
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

			// 处理折扣
			if (cus != null && cus.getViewDiscount() != null) {
				// 单价
				content[3] = StringHelper.formartDecimalToStr(new Double(
						content[3]).doubleValue(), "0.00");

				content[4] = StringHelper.formartDecimalToStr(new Double(
						content[4]).doubleValue(), "0.00");
			}

			returnList.add(content);
		}

		return returnList;
	}

	/**
	 * 获取指定用户的所有描述
	 * 
	 * @param query
	 * @return String[]
	 */
	public String[] getOrderDesc(BusinessOrderVO query, List<BusinessOrderVO> v) {
		List<String> returnRes = new ArrayList<String>();

		returnRes.add("备注:");
		if (query != null && query.getStaff() != null
				&& !StringHelper.isNull(query.getStaff().getUserDesc())) {
			returnRes.add(query.getStaff().getUserDesc());
		}
		for (BusinessOrderVO e : v) {
			BusinessOrderRecord record = e.getBusinessOrderRecord();

			if (!StringHelper.isNull(record.getUserRealDescription()))
				returnRes.add(record.getUserRealDescription() + ",");
		}
		return (String[]) returnRes.toArray(new String[0]);
	}

	/**
	 * 订单列表汇总
	 * 
	 * @param query
	 * @param pageNo
	 * @param pageSize
	 * @return List<BusinessOrderVO>
	 */
	public List<BusinessOrderVO> getStatInfoList(BusinessOrderVO query,
			int pageNo, int pageSize) {
		String hql = getHqlByStatInfo(query);
		List<Object[]> res = this.commonDao.getEntitiesByPropertiesWithHql(hql,
				pageNo, pageSize);
		List<BusinessOrderVO> returnList = new ArrayList<BusinessOrderVO>();
		
		for (int i=0;i<res.size();i++) {
			Object[] e = res.get(i);
			BusinessOrderRecord record = (BusinessOrderRecord) e[0];
			BusinessOrderGoodsRelation rel = (BusinessOrderGoodsRelation) e[1];
			BusinessGoods goods = (BusinessGoods) e[2];
			BusinessCategory cate = (BusinessCategory) e[3];
			CommonStaff staff = (CommonStaff) e[4];

			CommonStaff leadStaff = new CommonStaff();
			leadStaff.setId(staff.getLeadStaffId());
			leadStaff.setStaffName(staff.getLeadStaffName());
			leadStaff.setLeadStaffOrgId(staff.getLeadStaffOrgId());
			leadStaff.setLeadStaffOrgName(staff.getLeadStaffOrgName());

			BusinessOrderVO v = new BusinessOrderVO();
			v.setBusinessOrderRecord(record);
			v.setBusinessOrderGoodsRelation(rel);
			v.setBusinessGoods(goods);
			v.setBusinessCategory(cate);
			v.setStaff(staff);
			v.setLeaderStaff(leadStaff);
			
			//计算该用户所有订单总价
			CommonStaff tempStaff = new CommonStaff();
			tempStaff.setId(staff.getId());
			
			BusinessOrderVO v2 = query.clone();
			v2.setStaff(tempStaff);
			String tempHql = "select sum(rel.goodsCount*rel.originalPrice) "+getHqlByStatInfo(v2);
			List l = commonDao.getEntitiesByPropertiesWithHql(tempHql, 0, 0);
			Double d = (Double)l.get(0);
			String strd = StringHelper.formartDecimalToStr(d);
			v.setOrderSumPrice(strd);
			
			//统计该用户的订单总数
			String tempHql2 = "select count(distinct record.id) "+getHqlByStatInfo(v2);
			v.setOrderCount(commonDao.getNumberOfEntitiesWithHql(tempHql2));
			v.setSerno(i);
			returnList.add(v);
		}
		return returnList;
	}

	/**
	 * 获取统计页面查询汇总
	 * 
	 * @param query
	 * @return Integer
	 */
	public Integer getStatInfoCount(BusinessOrderVO query) {
		String hql = "select count(distinct record.staffId) "
				+ getHqlByStatInfo(query);
		return this.commonDao.getNumberOfEntitiesWithHql(hql);
	}

	public String getTotalPrice(List<BusinessOrderVO> list) {
		Double d = 0.0d;
		for (BusinessOrderVO e : list) {
			d += e.getBusinessOrderRecord().getTotalPrice();
		}
		return StringHelper.formartDecimalToStr(d.doubleValue(), "#.0");
	}

	/**
	 * 获取统计页面HQL
	 * 
	 * @param query
	 * @return String
	 */
	private String getHqlByStatInfo(BusinessOrderVO query) {
		StringBuffer hql = new StringBuffer();
		hql.append("from BusinessOrderRecord record,BusinessOrderGoodsRelation rel,BusinessGoods goods,BusinessCategory cate,CommonStaff staff");
		hql.append(" where rel.goodsId=goods.id and record.id=rel.orderRecordId");
		hql.append(" and cate.id=goods.goodsCategoryId");
		hql.append(" and staff.id=record.staffId");

		if (query != null) {
			// 分类
			if (query.getBusinessCategory() != null
					&& query.getBusinessCategory().getId() != null) {
				hql.append(" and cate.id=").append(
						query.getBusinessCategory().getId());
			}
			// 商品
			if (query.getBusinessGoods() != null
					&& query.getBusinessGoods().getId() != null) {
				hql.append(" and goods.id=").append(
						query.getBusinessGoods().getId());
			}
			// 商品名称
			if (!StringHelper.isNull(query.getProductName())) {
				hql.append(" and goods.goodsName like '%")
						.append(query.getProductName()).append("%'");
			}
			// 订单日期
			if (!StringHelper.isNull(query.getCreateStartTime())
					&& !StringHelper.isNull(query.getCreateEndTime())) {
				hql.append(" and record.createTime>=DATE_FORMAT('");
				hql.append(query.getCreateStartTime());
				hql.append("','%Y-%m-%d %H:%i:%s')");
				hql.append(" and record.createTime<=DATE_FORMAT('");
				hql.append(query.getCreateEndTime());
				hql.append("','%Y-%m-%d %H:%i:%s')");
			}
			// 订单ID
			if (query.getBusinessOrderRecord() != null) {
				if (query.getBusinessOrderRecord().getId() != null) {
					hql.append(" and record.id=").append(
							query.getBusinessOrderRecord().getId());
				}
			}

			// 指定商户
			if (query.getStaff() != null) {
				if (query.getStaff().getId() != null) {
					hql.append(" and record.staffId=").append(
							query.getStaff().getId());
				}
			}
			
			//商户
			if(query.getBusinessOrderRecord() != null){
				if(query.getBusinessOrderRecord().getClientUserName() != null){
					hql.append(" and record.clientUserName like '%").append(
							query.getBusinessOrderRecord().getClientUserName())
							.append("%'");
				}
			}
			// 地区
			if(query.getBusinessOrderRecord() != null){
				if(query.getBusinessOrderRecord().getAreaName() != null){
					hql.append(" and record.areaName like '%").append(
							query.getBusinessOrderRecord().getAreaName())
							.append("%'");
				}
			}

			// 指定商户经理
			if (query.getLeaderStaff() != null) {
				if (query.getLeaderStaff().getId() != null) {
					hql.append(" and staff.leadStaffId=").append(
							query.getLeaderStaff().getId());
				}
			}

			// 收货人手机号
			if (query.getBusinessOrderRecord() != null) {
				if (!StringHelper.isNull(query.getBusinessOrderRecord()
						.getUserRealMobile())) {
					hql.append(" and record.userRealMobile like '%")
							.append(query.getBusinessOrderRecord()
									.getUserRealMobile()).append("%'");
				}
				// 真实手机号
				if (!StringHelper.isNull(query.getBusinessOrderRecord()
						.getClientUserMobile())) {
					hql.append(" and record.clientUserMobile like '%")
							.append(query.getBusinessOrderRecord()
									.getClientUserMobile()).append("%'");
				}

				// 收货人姓名
				if (!StringHelper.isNull(query.getBusinessOrderRecord()
						.getUserRealName())) {
					hql.append(" and record.userRealName like '%")
							.append(query.getBusinessOrderRecord()
									.getUserRealName()).append("%'");
				}
			}

			// 复选
			if (query.getQueryCateList() != null
					&& query.getQueryCateList().length > 0) {
				hql.append(" and cate.id in(");
				hql.append(StringHelper.arrayToString(query.getQueryCateList()));
				hql.append(")");
			}

			// group by
			if (!StringHelper.isNull(query.getGroupBy())) {
				hql.append(" group by ").append(query.getGroupBy());
			}

			// order by
			if (!StringHelper.isNull(query.getOrderBy())
					&& !StringHelper.isNull(query.getOrderByField())) {
				hql.append(" order by ").append(query.getOrderBy()).append(" ")
						.append(query.getOrderByField());
			}
		}

		return hql.toString();
	}

	/**
	 * 
	 * @param firstletter
	 * @param curPage
	 * @param pageSize
	 * @param orderField
	 * @param direction
	 * @return List<OrderVO>
	 * @throws InstantiationException
	 * @throws ClassNotFoundException
	 * @throws InvocationTargetException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public List orderGoodsPurchase(BusinessOrderVO vo, int curPage,
			int pageSize, String orderField, String direction)
			throws IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, ClassNotFoundException,
			InstantiationException {
		String hql = getTotalHql(vo);
		List list = commonJDBCDAO.findBySQLListMap(hql, 0, 0);
		return list;
	}

	private String getTotalHql(BusinessOrderVO vo) {
		String hql = "select t.GOODS_NAME, t.WEIGHT_NAME ,sum(t.con),t.oprice as oprice,sum(t.con)*t.oprice as countprice,"
				+ "t.ctg as tctg,t.pctg as tpctg,t.buyid as bid,t.gid as tgid,"
				+ "t.bdesc as tbdesc,t.gprice as tgprice "
				+ " from "
				+ " ( select b.GOODS_NAME , d.WEIGHT_NAME , (b.GOODS_WEIGHT*c.GOODS_COUNT) as con, "
				//+ ",round(c.original_price/GOODS_WEIGHT,1) as oprice,"
				+" c.original_price as oprice,"

				+ " e.CATEGORY_NAME as ctg ,f.CATEGORY_NAME as pctg,b.buyTeamId as buyid,b.id as gid, "
				+ " b.GOODS_DESCRIPTION as bdesc,b.GOODS_PURCHASE_PRICE as gprice"
				+ " from business_order_record a, business_goods b, business_order_goods_relation c , "
				+ " business_weight d ,"
				+ "business_category e,business_category f"
				+ " where c.ORDER_RECORD_ID=a.id and c.GOODS_ID=b.id "
				+ " and e.ID=b.GOODS_CATEGORY_ID and e.CATEGORY_PARENT_ID=f.ID"
				+ " and b.GOODS_WEIGHT_ID = d.id ";
		if (vo != null) {
			if (vo.getBusinessOrderRecord() != null
					&& !StringHelper.isNull(vo.getBusinessGoodsVO()
							.getBusinessGoods().getGoodsName())) {
				hql = hql
						+ " and b.GOODS_NAME like '%"
						+ vo.getBusinessGoodsVO().getBusinessGoods()
								.getGoodsName() + "%'";
			}
			if (vo.getBusinessGoodsVO() != null
					&& vo.getBusinessGoodsVO().getBusinessCategory().getId() != null) {
				hql = hql + " and b.GOODS_CATEGORY_ID = "
						+ vo.getBusinessGoodsVO().getBusinessCategory().getId();
			}
			if (vo.getBusinessGoodsVO() != null
					&& vo.getBusinessGoodsVO().getBusinessCategory()
							.getCategoryParentId() != null) {
				hql = hql
						+ " and e.CATEGORY_PARENT_ID = "
						+ vo.getBusinessGoodsVO().getBusinessCategory()
								.getCategoryParentId();
			}
			if (vo.getBusinessOrderRecord() != null
					&& vo.getBusinessOrderRecord().getOperateStatus() != null) {
				hql = hql + " and a.OPERATE_STATUS = "
						+ vo.getBusinessOrderRecord().getOperateStatus();
			}
			if (!StringHelper.isNull(vo.getRequiredDeliveryStartTime())) {
				hql = hql + " and a.REQUIRED_DELIVERY_TIME >='"
						+ vo.getRequiredDeliveryStartTime() + "'";
			}
			if (!StringHelper.isNull(vo.getRequiredDeliveryEndTime())) {
				hql = hql + " and a.REQUIRED_DELIVERY_TIME <='"
						+ vo.getRequiredDeliveryEndTime() + "'";
			}
			if (!StringHelper.isNull(vo.getFinalDeliveryStartTime())) {
				hql = hql + " and a.FINAL_DELIVERY_TIME >='"
						+ vo.getFinalDeliveryStartTime() + "'";
			}
			if (!StringHelper.isNull(vo.getFinalDeliveryEndTime())) {
				hql = hql + " and a.FINAL_DELIVERY_TIME <='"
						+ vo.getFinalDeliveryEndTime() + "'";
			}
			if (!StringHelper.isNull(vo.getCreateStartTime())
					&& !StringHelper.isNull(vo.getCreateEndTime())) {
				hql = hql + " and a.CREATE_TIME >='" + vo.getCreateStartTime()
						+ "'";
				hql = hql + " and a.CREATE_TIME <='" + vo.getCreateEndTime()
						+ "'";
			}

			if (vo.getStaff() != null && vo.getStaff().getId() != null) {
				hql = hql + " and a.STAFF_ID=" + vo.getStaff().getId();
			}
		}
		hql = hql
				+ " order by b.GOODS_NAME ) as t group by t.GOODS_NAME, t.WEIGHT_NAME order by  bid,t.GOODS_NAME";

		return hql;
	}

	/**
	 * @return List<BusinessOrderRecord>
	 */
	public List<BusinessOrderRecord> listAll() {
		return dao.findAll();
	}

	/**
	 * 处理所有订单导出excel
	 * 
	 * @param response
	 * @param lst
	 * @param query
	 * @throws IOException
	 * @throws InstantiationException
	 * @throws ClassNotFoundException
	 * @throws InvocationTargetException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public void processOutputOrderDetailTotalExcel(BusinessOrderVO query)
			throws IOException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException,
			ClassNotFoundException, InstantiationException {

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

		List<BusinessOrderVO> lst = getStatInfoList(query, 0, 0);
		query.setGroupBy(null);

		// 建立文件夹
		String dir = ServletActionContext.getServletContext().getRealPath("")
				+ "/themes/mall/excel/detail/"
				+ StringHelper.getSystime("yyyyMM") + "/"
				+ StringHelper.getSystime("dd") + "/";

		FileHelper.mkdir(dir);

		FileHelper.clearDir(dir);
		
		for (int i=0;i<lst.size();i++) {
			BusinessOrderVO e  = lst.get(i);
			BusinessOrderVO qe = new BusinessOrderVO();
			qe.setCreateStartTime(query.getCreateStartTime());
			qe.setCreateEndTime(query.getCreateEndTime());
			qe.setStaff(e.getStaff());
			qe.setSerno(i);
			createOrderDetailExcelFile(dir, qe);

		}

		String outFileName = StringHelper.getSystime("yyyyMMdd") + ".zip";
		String outFile = dir + "/" + outFileName;

		File f = new File(outFile);
		if (f.exists())
			f.delete();
		// 打包
		ZipHelper.compress(dir, outFile);

		// 输出
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("Application/Octet-stream;charset=gbk");
		response.setHeader("Content-Disposition", "attachment;filename=\""
				+ new String(outFileName.getBytes(), "gbk") + "\"");

		FileInputStream in = new FileInputStream(f);
		OutputStream out = response.getOutputStream();
		byte[] bs = new byte[1024];
		int len = 0;
		while ((len = in.read(bs)) != -1) {
			out.write(bs);
		}
		out.close();
		in.close();
	}

	/**
	 * 指定商户指定日期内的合并型订单
	 * 
	 * @param dir
	 * @return File
	 * @throws InstantiationException
	 * @throws ClassNotFoundException
	 * @throws InvocationTargetException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws IOException
	 */
	private File createOrderDetailExcelFile(String dir, BusinessOrderVO query)
			throws IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, ClassNotFoundException,
			InstantiationException, IOException {
		BusinessOrderVO q = query.clone();
		List<String[]> contentList = getStatInfoDetail(q);
		q.setGroupBy("record.id");
		List<BusinessOrderVO> v = getStatInfoList(q, 0, 0);
		if (v != null && v.size() > 0) {
			q = v.get(0);
			q.setSerno(query.getSerno());
			q.setCreateStartTime(query.getCreateStartTime());
			q.setCreateEndTime(query.getCreateEndTime());
			// q.setStaff(q1.getStaff());
			// q.setLeaderStaff(q1.getLeaderStaff());
		}
		// CommonStaff s = staffService.findByid(query.getBusinessOrderRecord()
		// .getStaffId());

		CommonStaff ls = null;
		if (query.getStaff() != null
				&& query.getStaff().getLeadStaffId() != null)
			ls = staffService.findByid(query.getStaff().getLeadStaffId()
					.toString());
		q.setOrderDesc(getOrderDesc(query, v));
		q.setTotalPrice(getTotalPrice(v));
		q.setLeaderStaff(ls);

		// 加载所有订单处理状态
		operateStatusList = statusService.find(BusinessOrderRecord.class,
				"BusinessOperateStatus");
		// 加载所有订单派送状态
		deliveryStatusList = statusService.find(BusinessOrderRecord.class,
				"BusinessDeliveryStatus");
		// 加载所有支付状态
		paymentStatusList = statusService.find(BusinessOrderRecord.class,
				"BusinessPaymentStatus");
		// 加载所有支付提供者类型
		paymentProviderList = statusService.find(BusinessOrderRecord.class,
				"BusinessPaymentProvider");

		billList = statusService.find(CommonStaff.class, "staffBillTime");

		return createSingleOrderDetailExcelFile(dir, q, contentList);
	}

	/**
	 * 创建文件
	 * 
	 * @param q
	 * @param contentList
	 * @throws IOException
	 */
	private File createSingleOrderDetailExcelFile(String dir,
			BusinessOrderVO q, List<String[]> contentList) throws IOException {
		// 第一步，创建一个webbook，对应一个Excel文件
		HSSFWorkbook wb = new HSSFWorkbook();
		// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
		HSSFSheet sheet = wb.createSheet("配送单");
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

		int col = 0;
		// set column
		sheet.setColumnWidth(col, 5500);
		col++;
		sheet.setColumnWidth(col, 5000);
		col++;
		sheet.setColumnWidth(col, 2000);
		col++;
		sheet.setColumnWidth(col, 2000);
		col++;
		// sheet.setColumnWidth(col, 2500);
		// col++;
		// sheet.setColumnWidth(col, 2500);
		// col++;
		sheet.setColumnWidth(col, 2000);
		col++;
		sheet.setColumnWidth(col, 2000);
		col++;
		sheet.setColumnWidth(col, 2500);
		col++;
		sheet.setColumnWidth(col, 2500);

		// 创建商户基本信息
		int flag = 0;
		flag = complateBusiness(q, wb, sheet, style, flag);

		// 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
		HSSFRow row = sheet.createRow((int) flag);
		flag++;
		row = sheet.createRow((int) flag);
		row.setHeight((short) 850);
		// 第四步，创建单元格，并设置值表头 设置表头居中
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		complateGoodsInfo(contentList, sheet, style, flag);

		String pathFile = dir + "/"
				+ new String(q.getStaff().getStaffName().getBytes(), "UTF-8")
				+ StringHelper.getSystime("yyyyMMdd") + ".xls";
		FileOutputStream fout = new FileOutputStream(pathFile);
		wb.write(fout);
		fout.close();

		return null;
	}

	/**
	 * excel 完善客户信息
	 * 
	 * @param q
	 * @param sheet
	 * @param style
	 * @param flag
	 */
	private int complateBusiness(BusinessOrderVO q, HSSFWorkbook wb,
			HSSFSheet sheet, HSSFCellStyle style, int flag) {
		flag++;
		int defualtHeight = 350;
		HSSFSheet sheetTitle = sheet;

		/*
		 * 设定合并单元格区域范围 firstRow 0-based lastRow 0-based firstCol 0-based lastCol
		 * 0-based
		 */
		CellRangeAddress cra = new CellRangeAddress(flag, flag, 0, 7);
		sheetTitle.addMergedRegion(cra);

		// title
		HSSFCellStyle styleTitle = wb.createCellStyle();
		// 字体
		HSSFFont font = wb.createFont();
		font.setFontHeightInPoints((short) 17);
		styleTitle.setFont(font);
		styleTitle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
		styleTitle.setWrapText(true);
		styleTitle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		styleTitle.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框
		styleTitle.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框
		styleTitle.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框

		List<String> title = new ArrayList<String>();
		title.add("小农人儿 - 专业餐饮供应商 - 400-615-8618");
		title.add("");
		title.add("");
		title.add("");
		title.add("");
		title.add("");
		title.add("");
		title.add("");

		flag = createGoodsDetailUtil(sheetTitle, flag, styleTitle, title, 450,
				false);

		HSSFCellStyle styleInfo = wb.createCellStyle();
		// 字体
		HSSFFont font2 = wb.createFont();
		font2.setFontHeightInPoints((short) 10);
		styleInfo.setFont(font2);
		styleInfo.setAlignment(HSSFCellStyle.ALIGN_LEFT); // 创建一个居中格式
		styleInfo.setWrapText(true);
		styleInfo.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		styleInfo.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框
		styleInfo.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框
		styleInfo.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框

		// line 1
		List<String> oneRowContent = new ArrayList<String>();

		oneRowContent.add("商户:" + q.getStaff().getStaffName());
		oneRowContent.add("");
		oneRowContent.add("");
		oneRowContent.add("");
		oneRowContent.add("");
		oneRowContent.add("");
		oneRowContent.add("");
		oneRowContent.add("");

		cra = new CellRangeAddress(flag, flag, 0, 7);
		sheet.addMergedRegion(cra);
		flag = createGoodsDetailUtil(sheet, flag, styleInfo, oneRowContent,
				defualtHeight, false);
		
		//line 2
		oneRowContent = new ArrayList<String>();
		oneRowContent.add("序号："+q.getSerno());
		oneRowContent.add("");
		oneRowContent.add("");
		oneRowContent.add("");
		oneRowContent.add("");
		oneRowContent.add("");
		oneRowContent.add("");
		oneRowContent.add("");
		cra = new CellRangeAddress(flag, flag, 0, 7);
		sheet.addMergedRegion(cra);
		flag = createGoodsDetailUtil(sheet, flag, styleInfo, oneRowContent,
				defualtHeight, false);
		
		// line 3
		oneRowContent = new ArrayList<String>();

		String s = "送达时间:"
				+ StringHelper.getSystime("yyyy-MM-dd", q
						.getBusinessOrderRecord().getRequiredDeliveryTime()
						.getTime()) + " " + q.getStaff().getTheEarliestTime()
				+ "点到" + q.getStaff().getTheLatestTime() + "点";
		oneRowContent.add(s);
		oneRowContent.add("");
		oneRowContent.add("");
		oneRowContent.add("");
		oneRowContent.add("");
		oneRowContent.add("");
		oneRowContent.add("");
		oneRowContent.add("");
		cra = new CellRangeAddress(flag, flag, 0, 7);
		sheet.addMergedRegion(cra);
		flag = createGoodsDetailUtil(sheet, flag, styleInfo, oneRowContent,
				defualtHeight, false);
		
		oneRowContent = new ArrayList<String>();
		oneRowContent.add("详细地址:"
				+ q.getBusinessOrderRecord().getUserRealAddress());
		oneRowContent.add("");
		oneRowContent.add("");
		oneRowContent.add("");
		oneRowContent.add("");
		oneRowContent.add("");
		oneRowContent.add("");
		oneRowContent.add("");
		cra = new CellRangeAddress(flag, flag, 0, 7);
		sheet.addMergedRegion(cra);
		flag = createGoodsDetailUtil(sheet, flag, styleInfo, oneRowContent,
				defualtHeight, false);

		// line 2
		oneRowContent = new ArrayList<String>();
		oneRowContent.add("收货人电话:"
				+ q.getBusinessOrderRecord().getUserRealMobile());
		oneRowContent.add("");
		oneRowContent.add("");
		oneRowContent.add("");
		oneRowContent.add("");
		oneRowContent.add("");
		oneRowContent.add("");
		oneRowContent.add("");

		cra = new CellRangeAddress(flag, flag, 0, 7);
		sheet.addMergedRegion(cra);
		flag = createGoodsDetailUtil(sheet, flag, styleInfo, oneRowContent,
				defualtHeight, false);

		// line 4
		oneRowContent = new ArrayList<String>();
		s = "支付状态:";
		for (Status e : paymentStatusList) {
			if (e.getId().intValue() == q.getBusinessOrderRecord()
					.getPaymentStatus().intValue()) {
				s += e.getStatusName();
				break;
			}
		}
		oneRowContent.add(s);
		oneRowContent.add("");
		oneRowContent.add("");
		oneRowContent.add("");
		oneRowContent.add("");
		oneRowContent.add("");
		oneRowContent.add("");
		oneRowContent.add("");

		cra = new CellRangeAddress(flag, flag, 0, 7);
		sheet.addMergedRegion(cra);
		flag = createGoodsDetailUtil(sheet, flag, styleInfo, oneRowContent,
				defualtHeight, false);

		oneRowContent = new ArrayList<String>();
		s = "支付方式:";
		for (Status e : paymentProviderList) {
			if (e.getId().intValue() == q.getBusinessOrderRecord()
					.getPaymentProvider().intValue()) {
				s += e.getStatusName();
				break;
			}
		}
		oneRowContent.add(s);
		oneRowContent.add("");
		oneRowContent.add("");
		oneRowContent.add("");
		oneRowContent.add("");
		oneRowContent.add("");
		oneRowContent.add("");
		oneRowContent.add("");

		cra = new CellRangeAddress(flag, flag, 0, 7);
		sheet.addMergedRegion(cra);
		flag = createGoodsDetailUtil(sheet, flag, styleInfo, oneRowContent,
				defualtHeight, false);

		// line 5
		oneRowContent = new ArrayList<String>();
		s = "结算周期:";
		if (q.getStaff() != null && q.getStaff().getBillTime() != null) {
			for (Status e : billList) {
				if (e.getId().intValue() == q.getStaff().getBillTime()
						.intValue()) {
					s += e.getStatusName();
					break;
				}
			}
		} else {
			s += "";
		}
		oneRowContent.add(s);
		oneRowContent.add("");
		oneRowContent.add("");
		oneRowContent.add("");
		oneRowContent.add("");
		oneRowContent.add("");
		oneRowContent.add("");
		oneRowContent.add("");

		cra = new CellRangeAddress(flag, flag, 0, 7);
		sheet.addMergedRegion(cra);
		flag = createGoodsDetailUtil(sheet, flag, styleInfo, oneRowContent,
				defualtHeight, false);

		oneRowContent = new ArrayList<String>();
		oneRowContent.add("应收总款:" + q.getTotalPrice());
		oneRowContent.add("");
		oneRowContent.add("");
		oneRowContent.add("");
		oneRowContent.add("");
		oneRowContent.add("");
		oneRowContent.add("");
		oneRowContent.add("");

		cra = new CellRangeAddress(flag, flag, 0, 7);
		sheet.addMergedRegion(cra);
		flag = createGoodsDetailUtil(sheet, flag, styleInfo, oneRowContent,
				defualtHeight, false);

		oneRowContent = new ArrayList<String>();
		oneRowContent.add("实际应收总款:");
		oneRowContent.add("");
		oneRowContent.add("");
		oneRowContent.add("");
		oneRowContent.add("");
		oneRowContent.add("");
		oneRowContent.add("");
		oneRowContent.add("");
		cra = new CellRangeAddress(flag, flag, 0, 7);
		sheet.addMergedRegion(cra);
		flag = createGoodsDetailUtil(sheet, flag, styleInfo, oneRowContent,
				defualtHeight, false);

		oneRowContent = new ArrayList<String>();
		oneRowContent.add(Arrays.deepToString(q.getOrderDesc()));
		oneRowContent.add("");
		oneRowContent.add("");
		oneRowContent.add("");
		oneRowContent.add("");
		oneRowContent.add("");
		oneRowContent.add("");
		oneRowContent.add("");
		cra = new CellRangeAddress(flag, flag, 0, 7);
		sheet.addMergedRegion(cra);
		flag = createGoodsDetailUtil(sheet, flag, styleInfo, oneRowContent, 0,
				false);

		// line 6
		oneRowContent = new ArrayList<String>();
		oneRowContent.add("客户签字");
		oneRowContent.add("");
		oneRowContent.add("");
		oneRowContent.add("");
		oneRowContent.add("");
		oneRowContent.add("");
		oneRowContent.add("");
		oneRowContent.add("");
		cra = new CellRangeAddress(flag, flag, 0, 7);
		sheet.addMergedRegion(cra);
		flag = createGoodsDetailUtil(sheet, flag, styleInfo, oneRowContent,
				defualtHeight, false);

		oneRowContent = new ArrayList<String>();
		oneRowContent.add("送货人员签字:");
		oneRowContent.add("");
		oneRowContent.add("");
		oneRowContent.add("");
		oneRowContent.add("");
		oneRowContent.add("");
		oneRowContent.add("");
		oneRowContent.add("");
		cra = new CellRangeAddress(flag, flag, 0, 7);
		sheet.addMergedRegion(cra);
		flag = createGoodsDetailUtil(sheet, flag, styleInfo, oneRowContent,
				defualtHeight, false);

		return flag;
	}

	/**
	 * 商品列表
	 * 
	 * @param contentList
	 * @param sheet
	 * @param style
	 * @param flag
	 */
	private void complateGoodsInfo(List<String[]> contentList, HSSFSheet sheet,
			HSSFCellStyle style, int flag) {

		int defualtHeight = 950;
		List<String> oneRowContent = new ArrayList<String>();
		oneRowContent.add("商品名称");
		oneRowContent.add("商品备注");
		oneRowContent.add("数量");
		oneRowContent.add("单位");
		// oneRowContent.add("分类");
		// oneRowContent.add("主分类");
		oneRowContent.add("单价");
		oneRowContent.add("总价");
		oneRowContent.add("实际数量");
		oneRowContent.add("实际总价");
		flag = createGoodsDetailUtil(sheet, flag, style, oneRowContent, 350,
				false);

		for (String[] e : contentList) {
			oneRowContent = new ArrayList<String>();
			oneRowContent.add(e[0]);
			oneRowContent.add(e[9]);
			oneRowContent.add(e[1]);
			oneRowContent.add(e[2]);
			// oneRowContent.add(e[5]);
			// oneRowContent.add(e[6]);
			oneRowContent.add(e[3]);
			oneRowContent.add(e[4]);
			oneRowContent.add("");
			oneRowContent.add("");
			flag = createGoodsDetailUtil(sheet, flag, style, oneRowContent, 0,
					false);

		}

	}

	/**
	 * 构建一行EXCEL
	 * 
	 * @param sheet
	 * @param flag
	 * @param style
	 * @param content
	 *            <列号,内容>
	 * @param hasSpace
	 * @return int
	 */
	private int createGoodsDetailUtil(HSSFSheet sheet, int flag,
			HSSFCellStyle style, List<String> content, int rowHeight,
			boolean hasSpace) {
		if (content == null || content.size() <= 0)
			return flag;
		HSSFRow rowTotal = sheet.createRow(flag);
		flag++;
		int col = 0;
		rowTotal.setHeight((short) rowHeight);
		// line 1
		HSSFCell cell = null;

		int maxLength = 0;
		List<Integer> lengths = new ArrayList<Integer>();
		for (String e : content) {
			cell = rowTotal.createCell(col);
			cell.setCellStyle(style);
			cell.setCellValue(e);
			col++;
			// space
			if (hasSpace) {
				cell = rowTotal.createCell(col);
				cell.setCellStyle(style);
				col++;
			}
			lengths.add(e.length());
		}

		int[] nums = new int[lengths.size()];
		for (int i = 0; i < lengths.size(); i++) {
			nums[i] = lengths.get(i);
		}

		if (rowHeight <= 0) {
			maxLength = MathHelper.getMaxNumber(nums);

			double a = maxLength / 2 * 100;
			String a1 = StringHelper.formartDecimalToStr(a, "0.0");
			Double d1 = new Double(a1);

			if (maxLength > 5)
				rowTotal.setHeight(d1.shortValue());
			else
				rowTotal.setHeight((short) 350);
		}
		return flag;
	}

	private List<Status> operateStatusList;
	// 加载所有订单派送状态
	private List<Status> deliveryStatusList;
	// 加载所有支付状态
	private List<Status> paymentStatusList;
	// 加载所有支付提供者类型
	private List<Status> paymentProviderList;

	private List<Status> billList;

}
