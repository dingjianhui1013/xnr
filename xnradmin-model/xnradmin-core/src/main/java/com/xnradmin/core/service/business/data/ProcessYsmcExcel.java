/**
 *2014年12月31日 下午2:09:06
*/
package com.xnradmin.core.service.business.data;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import com.cntinker.util.HttpunitHelper;
import com.cntinker.util.StringHelper;
import com.meterware.httpunit.WebConversation;
import com.meterware.httpunit.WebResponse;
import com.xnradmin.core.pay.wxpay.RequestHandler;
import com.xnradmin.core.util.SpringBase;

/**
 * @author: liubin
 *
 */
@Service("ProcessYsmcExcel")
public class ProcessYsmcExcel {

	private static Logger log = Logger.getLogger(ProcessYsmcExcel.class);
	/**
	 * @param response
	 * @throws IOException
	 * @throws SAXException
	 * @throws JSONException
	 * @throws org.json.JSONException
	 */
	public void genYsmcFile(HttpServletResponse response)
			throws IOException, SAXException, JSONException,
			org.json.JSONException {
		String u = "http://online.yunshanmeicai.com/preview/preview";

		HttpunitHelper webLogin = new HttpunitHelper();
		WebConversation wc = webLogin.init(
				"vm/3.59.0 Webkit/1.0(Android 4.4.4; Sony/L39h)", true);

		WebResponse r = wc.getResponse(u);
		//log.debug(r.getText());

		String temp = StringHelper.spiltStr(r.getText(), "window.data",
				"var navHtml");

		String temp2 = StringHelper.spiltStr(temp, "=", "};");

		String res = temp2 + "};";

		res = StringHelper.convertUnicode(res);
		processYsmcExcel(res, response);

	}

	private void processYsmcExcel(String res, HttpServletResponse response)
			throws JSONException, org.json.JSONException {
		JSONObject jo = new JSONObject(res);

		Iterator it = jo.keys();

		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet("ysmc");
		HSSFRow row = sheet.createRow((int) 0);
		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
		HSSFCell cell = row.createCell(0);
		cell.setCellValue("名称");
		cell.setCellStyle(style);
		cell = row.createCell(1);

		cell.setCellValue("备注");
		cell.setCellStyle(style);
		cell = row.createCell(2);

		cell.setCellValue("单位");
		cell.setCellStyle(style);
		cell = row.createCell(3);

		cell.setCellValue("独立单位");
		cell.setCellStyle(style);
		cell = row.createCell(4);

		cell.setCellValue("品牌");
		cell.setCellStyle(style);
		cell = row.createCell(5);

		cell.setCellValue("品牌2");
		cell.setCellStyle(style);
		cell = row.createCell(6);

		cell.setCellValue("分类");
		cell.setCellStyle(style);
		cell = row.createCell(7);

		cell.setCellValue("单价");
		cell.setCellStyle(style);
		cell = row.createCell(8);

		cell.setCellValue("总价");
		cell.setCellStyle(style);
		cell = row.createCell(9);

		cell.setCellValue("主分类");
		cell.setCellStyle(style);
		cell = row.createCell(10);

		cell.setCellValue("子分类");
		cell.setCellStyle(style);
		cell = row.createCell(11);
		cell.setCellValue("更新时间");
		cell.setCellStyle(style);

		int flag = 0;

		while (it.hasNext()) {
			String k = it.next().toString();
			// log.debug(jo.get(k));
			flag = processYsmcSubLevel1(k, jo.get(k).toString(), wb, sheet,
					row, flag);
		}

		try {

			response.setContentType("application/msexcel;charset=GBK");
			response.setHeader("Content-Disposition", "attachment;filename=\""
					+ new String("ysmc_goods_list.xls".getBytes(), "UTF-8")
					+ "\"");
			wb.write(response.getOutputStream());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 云杉美菜1级分类
	 * 
	 * @param content
	 * @throws JSONException
	 * @throws org.json.JSONException
	 */
	private int processYsmcSubLevel1(String type1, String content,
			HSSFWorkbook wb, HSSFSheet sheet, HSSFRow row, int flag)
			throws JSONException, org.json.JSONException {
		JSONObject jo = new JSONObject(content);
		Iterator it = jo.keys();

		while (it.hasNext()) {
			String k = it.next().toString();
			// log.debug(jo.get(k));

			flag = processYsmcSubLevel2(type1, k, jo.get(k).toString(), wb,
					sheet, row, flag);

		}
		return flag;

	}

	private int processYsmcSubLevel2(String type1, String type2,
			String content, HSSFWorkbook wb, HSSFSheet sheet, HSSFRow row,
			int flag) throws JSONException, org.json.JSONException {
		JSONArray data = new JSONArray(content);

		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式

		for (int i = 0; i < data.length(); i++) {

			JSONObject temp = (JSONObject) data.get(i);

			log.debug(temp);

			StringBuffer sb = new StringBuffer();
			sb.append("名称：").append(temp.get("name"));
			sb.append(" | 备注：").append(temp.get("level"));
			sb.append(" | 单位：").append(temp.get("format"));
			sb.append(" | 独立单位：").append(temp.get("unit"));
			sb.append(" | 品牌:").append(temp.get("own_brand"));
			sb.append(" | 品牌2: ").append(temp.get("sell_brand"));
			sb.append(" | 分类：").append(temp.get("class1"));
			sb.append(" | 主分类：").append(type1);
			sb.append(" | 子分类：").append(type2);
			sb.append(" | 单价：").append(temp.get("price"));
			sb.append(" | 总价：").append(temp.get("commodity_total_price"));
			

			int f = flag + 1;
			row = sheet.createRow(f);
			

			HSSFCell cell0 = row.createCell(0);
			cell0.setCellStyle(style);
			cell0.setCellValue(temp.get("name").toString());

			HSSFCell cell1 = row.createCell(1);
			cell1.setCellStyle(style);
			cell1.setCellValue(temp.get("level").toString());

			HSSFCell cell2 = row.createCell(2);
			cell2.setCellStyle(style);
			cell2.setCellValue(temp.get("format").toString());

			HSSFCell cell3 = row.createCell(3);
			cell3.setCellStyle(style);
			cell3.setCellValue(temp.get("unit").toString());

			HSSFCell cell4 = row.createCell(4);
			cell4.setCellStyle(style);
			cell4.setCellValue(temp.get("own_brand").toString());

			HSSFCell cell5 = row.createCell(5);
			cell5.setCellStyle(style);
			cell5.setCellValue(temp.get("sell_brand").toString());

			HSSFCell cell6 = row.createCell(6);
			cell6.setCellStyle(style);
			cell6.setCellValue(temp.get("class1").toString());

			HSSFCell cell7 = row.createCell(7);
			cell7.setCellStyle(style);
			cell7.setCellValue(temp.get("price").toString());

			HSSFCell cell8 = row.createCell(8);
			cell8.setCellStyle(style);
			cell8.setCellValue(temp.get("commodity_total_price").toString());

			HSSFCell cell9 = row.createCell(9);
			cell9.setCellStyle(style);
			cell9.setCellValue(type1);

			HSSFCell cell10 = row.createCell(10);
			cell10.setCellStyle(style);
			cell10.setCellValue(type2);
			
			HSSFCell cell11 = row.createCell(11);
			cell11.setCellStyle(style);
			cell11.setCellValue(StringHelper.getSystime());

			// row.createCell(0).setCellValue(temp.get("name").toString());
			// row.createCell(1).setCellValue(temp.get("format").toString());
			// row.createCell(2).setCellValue(temp.get("unit").toString());
			// row.createCell(3).setCellValue(temp.get("own_brand").toString());
			// row.createCell(4).setCellValue(temp.get("sell_brand").toString());
			// row.createCell(5).setCellValue(temp.get("class1").toString());
			// row.createCell(6).setCellValue(temp.get("price").toString());

			flag = f;
		}
		return flag;
	}
	
	public static void main(String[] args)throws Exception{
		ProcessYsmcExcel e = (ProcessYsmcExcel)SpringBase.getCtx().getBean("ProcessYsmcExcel");
		e.genYsmcFile(null);
	}
}
