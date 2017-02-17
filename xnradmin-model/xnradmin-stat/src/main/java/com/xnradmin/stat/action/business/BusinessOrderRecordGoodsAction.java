/**
 *2012-5-10 下午3:52:29
 */
package com.xnradmin.stat.action.business;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
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
import com.xnradmin.po.common.status.Status;
import com.xnradmin.stat.service.business.BusinessOrderRecordGoodsService;
import com.xnradmin.vo.business.BusinessOrderVO;

/**
 * @autohr: xiang_liu
 * 
 */
@Controller
@Scope("prototype")
@Namespace("/page/stat/business")
@ParentPackage("json-default")
public class BusinessOrderRecordGoodsAction extends ParentAction {

	@Autowired
	private BusinessOrderRecordGoodsService businessOrderRecordGoodsService;

	@Override
	public boolean isPublic() {
		return false;
	};

	static Log log = LogFactory.getLog(BusinessOrderRecordGoodsAction.class);

	/**
	 * 跳转到信息页。。
	 * 
	 * @return
	 */
	@Action(value = "orderGoodsStat", results = {
			@Result(name = StrutsResMSG.SUCCESS, location = "/stat/business/orderGoodsStat.jsp"),
			@Result(name = StrutsResMSG.FAILED, location = "/stat/business/orderGoodsStat.jsp") })
	public String accountManager() {
		setPageInfo();
		return StrutsResMSG.SUCCESS;
	}

	private void setPageInfo() {
		// 设置排序
		setDateTime();
		BusinessOrderVO vo = new BusinessOrderVO();
		vo.setCreateStartTime(createStartTime);
		vo.setCreateEndTime(createEndTime);
		List<Object[]> list = businessOrderRecordGoodsService.findOrderGoodsStat(vo, 0, 0);
		//this.totalCount = list.size();
		contentList = new ArrayList<String[]>();
		for (int i = 0; i < list.size(); i++) {
			String[] content = new String[4];
			Object[] a = list.get(i);
			if (a == null)
				continue;
			content[0] = a[0] == null ? "0" : a[0].toString();
			content[1] = a[1] == null ? "0" : a[1].toString();
			content[2] = a[2] == null ? "0" : a[2].toString();
			content[3] = a[3] == null ? "0" : a[3].toString();
			contentList.add(content);
		}
		createExcel();
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
	}
	
	
	/**
	 * 生成EXCEL
	 */
	public void createExcel() {
		// 第一步，创建一个webbook，对应一个Excel文件
		HSSFWorkbook wb = new HSSFWorkbook();
		// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
		HSSFSheet sheet = wb.createSheet("分拣单");
		HSSFCellStyle style = wb.createCellStyle();
		// 字体
		HSSFFont font = wb.createFont();
		font.setFontHeightInPoints((short) 12);
		style.setFont(font);
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
		style.setWrapText(true);
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框

		// set column
		sheet.setColumnWidth(0, 8000);
		sheet.setColumnWidth(1, 5000);
		sheet.setColumnWidth(2, 5000);
		sheet.setColumnWidth(3, 5000);

		int flag = 0;

		// 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
		HSSFRow row = sheet.createRow((int) flag);
		row.setHeight((short) 850);
		// 第四步，创建单元格，并设置值表头 设置表头居中

		HSSFCell cell = row.createCell(0);
		cell.setCellValue("商品名称");
		cell.setCellStyle(style);
		cell = row.createCell(1);
		cell.setCellValue("单位名称");
		cell.setCellStyle(style);

		cell = row.createCell(2);
		cell.setCellValue("单位数量");
		cell.setCellStyle(style);
		cell = row.createCell(3);
		cell.setCellValue("需要份数");
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
			cell.setCellValue(vo[1]);

			cell = row.createCell(2);
			cell.setCellStyle(style);
			cell.setCellValue(vo[2]);

			cell = row.createCell(3);
			cell.setCellStyle(style);
			cell.setCellValue(vo[3]);
		}
		row = sheet.createRow(flag + 1);
		row.setHeight((short) 850);

		// 第六步，将文件存到指定位置
		try {
			String pathFile = ServletActionContext.getServletContext()
					.getRealPath("") + "/themes/mall/excel/business_goods_sorting.xls";
			FileOutputStream fout = new FileOutputStream(pathFile);
			wb.write(fout);
			fout.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private List<String[]> contentList;
	private String createStartTime;
	private String createEndTime;

	public List<String[]> getContentList() {
		return contentList;
	}

	public void setContentList(List<String[]> contentList) {
		this.contentList = contentList;
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
	
}
