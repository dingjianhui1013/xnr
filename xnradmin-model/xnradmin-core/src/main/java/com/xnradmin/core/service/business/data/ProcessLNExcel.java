/**
 *2015年3月3日 下午3:16:54
 */
package com.xnradmin.core.service.business.data;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.common.usermodel.Hyperlink;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFHyperlink;
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
import com.xnradmin.po.business.BusinessData;

/**
 * @author: liubin
 *
 */
@Service("ProcessLNExcel")
public class ProcessLNExcel {

	// 链农的statuscode ID - 4
	private String sourceId = "4";

	// 商品列表
	private String mainUrl = "http://m.blueface.cn/api/goods/list";//"http://123.56.45.136/api/goods/list";//"http://m.blueface.cn/api/goods/list";

	private Map<String, String> cateMap = new HashMap<String, String>();

	public ProcessLNExcel() {
		cateMap.put("0", "常购");
		cateMap.put("1", "蔬菜水果");
		cateMap.put("2", "肉禽水产");
		cateMap.put("3", "米面粮油");
		cateMap.put("4", "调料其他");
		cateMap.put("36", "禽蛋粮油");
		cateMap.put("34", "冷鲜肉类");
		cateMap.put("37", "调料干货");
		cateMap.put("16", "餐饮半成品");
		cateMap.put("25", "酒水饮料");
		cateMap.put("21", "餐饮用品用具");
		cateMap.put("35", "水产冻货");
		cateMap.put("38", "预售-勿订");
	}

	public void genExcel(HttpServletResponse response) throws IOException,
			SAXException, JSONException {
		String u = mainUrl;

		process(u, response);
	}

	private void process(String url, HttpServletResponse response)
			throws IOException, SAXException, JSONException {

		HttpunitHelper webLogin = new HttpunitHelper();
		WebConversation wc = webLogin.init(
				"vm/3.59.0 Webkit/1.0(Android 4.4.4; Sony/L39h)", true);

		WebResponse r = wc.getResponse(url);
		// System.out.println(r.getText());

		String content = r.getText();

		content = StringHelper.convertUnicode(content);

		JSONObject jo = new JSONObject(content);
		JSONArray data = (JSONArray) jo.get("info");

		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet("ln");
		HSSFRow row = sheet.createRow((int) 0);
		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
		HSSFCell cell = row.createCell(0);
		cell.setCellValue("名称");
		cell.setCellStyle(style);

		cell = row.createCell(1);
		cell.setCellValue("分类");
		cell.setCellStyle(style);

		cell = row.createCell(2);
		cell.setCellValue("单位");
		cell.setCellStyle(style);

		cell = row.createCell(3);
		cell.setCellValue("单价");
		cell.setCellStyle(style);

		cell = row.createCell(4);
		cell.setCellValue("更新时间");
		cell.setCellStyle(style);

		cell = row.createCell(5);
		cell.setCellValue("商品图片");
		cell.setCellStyle(style);

		int flag = 0;

		for (int i = 0; i < data.length(); i++) {

			JSONObject temp = (JSONObject) data.get(i);

			BusinessData po = new BusinessData();
			po.setProductName(temp.get("name").toString());
			po.setPrice(new Double(temp.get("show_price").toString()));
			po.setUnit(temp.get("show_unit").toString());
			po.setCategory(cateMap.get(temp.get("cat_id").toString()));
			po.setUpdateTime(new Timestamp(System.currentTimeMillis()));
			po.setImgUrl(temp.get("img").toString());

			flag = processSheet(po, wb, sheet, flag);
		}

		try {

			response.setContentType("application/msexcel;charset=GBK");
			response.setHeader("Content-Disposition", "attachment;filename=\""
					+ new String("ln_goods_list.xls".getBytes(), "UTF-8")
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

	private int processSheet(BusinessData data, HSSFWorkbook wb,
			HSSFSheet sheet, int flag) {
		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式

		int f = flag + 1;
		HSSFRow row = sheet.createRow(f);
		HSSFCell cell0 = row.createCell(0);
		cell0.setCellStyle(style);
		cell0.setCellValue(data.getProductName());

		HSSFCell cell1 = row.createCell(1);
		cell1.setCellStyle(style);
		cell1.setCellValue(data.getCategory());

		HSSFCell cell3 = row.createCell(2);
		cell3.setCellStyle(style);
		cell3.setCellValue(data.getUnit());

		HSSFCell cell4 = row.createCell(3);
		cell4.setCellStyle(style);
		if (data.getSinglePrice() != null)
			cell4.setCellValue(StringHelper.formartDecimalToStr(data
					.getSinglePrice()));
		else
			cell4.setCellValue(StringHelper.formartDecimalToStr(data.getPrice()));

		HSSFCell cell6 = row.createCell(4);
		cell6.setCellStyle(style);
		cell6.setCellValue(StringHelper.getSystime("yyyy-MM-dd HH:mm:ss", data
				.getUpdateTime().getTime()));

		HSSFCell cell5 = row.createCell(5);
		cell5.setCellStyle(style);

		HSSFHyperlink link = wb.getCreationHelper().createHyperlink(Hyperlink.LINK_URL);
		link.setAddress(data.getImgUrl());

		cell5.setCellValue("图片链接");
		cell5.setHyperlink(link);

		return f;
	}

	private BusinessData processSinglePrice(BusinessData goods) {
		BusinessData res = goods;
		String gName = processGoodsName(goods.getProductName());
		if (StringHelper.isNull(gName))
			return res;

		Float f = getCountFormGoodsname(goods.getProductName());

		if (f == null || f.floatValue() <= 0.0f)
			return res;

		Double p = goods.getPrice() / f;
		// update
		res.setPrice(goods.getPrice());
		res.setSinglePrice(p);

		return res;

	}

	private String processGoodsName(String goodsName) {
		String res = goodsName;
		if (res.indexOf("(") < 0 || res.indexOf(")") < 0)
			return null;
		res = goodsName.substring(0, goodsName.indexOf("("));
		return res;
	}

	private Float getCountFormGoodsname(String goodsName) {
		String res = goodsName;
		if (res.indexOf("(") < 0 || res.indexOf(")") < 0)
			return null;
		res = goodsName.substring(goodsName.indexOf("(") + 1,
				goodsName.indexOf(")"));
		res = res.substring(0, res.indexOf("斤"));

		if (res.indexOf(",") > -1) {
			res = res.substring(res.indexOf(",") + 1);
		}

		int f = 0;
		if (!StringHelper.isDigit(res)) {

			for (int i = 0, size = res.length(); i < size; i++) {
				if (!Character.isDigit(res.charAt(i))) {
					f++;
					continue;
				} else
					break;
			}

		}

		res = res.substring(f, res.length());

		return new Float(res);
	}
}
