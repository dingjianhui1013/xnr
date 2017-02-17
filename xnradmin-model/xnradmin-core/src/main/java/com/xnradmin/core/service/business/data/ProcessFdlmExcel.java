/**
 *2014年12月31日 下午2:11:58
 */
package com.xnradmin.core.service.business.data;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

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
import com.xnradmin.po.business.BusinessData;
import com.xnradmin.vo.business.BusinessDataFDLMTypeVO;

/**
 * @author: liubin
 *
 */
@Service("ProcessFdlmExcel")
public class ProcessFdlmExcel {

	// 饭店联盟的statuscode ID - 3
	private String sourceId = "3";

	// 细分页面
	private String mainUrl = "http://115.28.252.36/RA/category/listapp?releaseNum=";

	// 分类页面
	private String typeUrl = "http://115.28.252.36/RA/categorytype/listapp";

	public void genFdlmExcel(HttpServletResponse response) throws IOException,
			SAXException, JSONException {
		String u = mainUrl + 1;

		process(u, response);
	}

	private Map<Integer, BusinessDataFDLMTypeVO> findAllType()
			throws IOException, SAXException, JSONException {
		Map<Integer, BusinessDataFDLMTypeVO> res = new HashMap<Integer, BusinessDataFDLMTypeVO>();
		HttpunitHelper webLogin = new HttpunitHelper();
		WebConversation wc = webLogin.init(
				"vm/3.59.0 Webkit/1.0(Android 4.4.4; Sony/L39h)", true);
		WebResponse r = wc.getResponse(typeUrl);

		String content = r.getText();
		JSONObject jo = new JSONObject(content);
		JSONArray data = (JSONArray) jo.get("data");
		for (int i = 0; i < data.length(); i++) {
			BusinessDataFDLMTypeVO vo = new BusinessDataFDLMTypeVO();
			JSONObject temp = (JSONObject) data.get(i);
			Integer id = new Integer(temp.get("iD").toString());
			String typeName = temp.get("type_name").toString();
			Integer pid = new Integer(temp.get("parentId").toString());
			String url = temp.get("url").toString();

			vo.setParentId(pid);
			vo.setTypeName(typeName);
			vo.setTypeid(id);
			vo.setUrl(url);

			res.put(id, vo);
		}

		return res;
	}

	private BusinessDataFDLMTypeVO getTypeByTypeid(Integer typeid)
			throws IOException, SAXException, JSONException {
		BusinessDataFDLMTypeVO v = new BusinessDataFDLMTypeVO();
		Map<Integer, BusinessDataFDLMTypeVO> lst = findAllType();
		Iterator<Integer> it = lst.keySet().iterator();
		while (it.hasNext()) {
			Integer k = (Integer) it.next();
			if (k.intValue() == typeid.intValue())
				return lst.get(k);
		}
		return null;
	}

	private void process(String url, HttpServletResponse response)
			throws IOException, SAXException, JSONException {

		HttpunitHelper webLogin = new HttpunitHelper();
		WebConversation wc = webLogin.init(
				"vm/3.59.0 Webkit/1.0(Android 4.4.4; Sony/L39h)", true);

		WebResponse r = wc.getResponse(url);
		// log.debug(r.getText());

		String content = r.getText();

		JSONObject jo = new JSONObject(content);
		JSONArray data = (JSONArray) jo.get("data");

		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet("fdlm");
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
		cell.setCellValue("上级分类");
		cell.setCellStyle(style);

		cell = row.createCell(3);
		cell.setCellValue("单位");
		cell.setCellStyle(style);

		cell = row.createCell(4);
		cell.setCellValue("单价");
		cell.setCellStyle(style);

		cell = row.createCell(5);
		cell.setCellValue("总价");
		cell.setCellStyle(style);

		cell = row.createCell(6);
		cell.setCellValue("更新时间");
		cell.setCellStyle(style);

		int flag = 0;

		for (int i = 0; i < data.length(); i++) {

			JSONObject temp = (JSONObject) data.get(i);

			BusinessData po = new BusinessData();
			po.setProductName(temp.get("commodityName").toString());
			po.setPrice(new Double(temp.get("price").toString()));
			po.setUnit(temp.get("unit").toString());
			po.setUpdateTime(new Timestamp(System.currentTimeMillis()));

			BusinessDataFDLMTypeVO type = getTypeByTypeid(new Integer(temp.get(
					"typeId").toString()));
			BusinessDataFDLMTypeVO ptype = getTypeByTypeid(type.getParentId());

			po.setCategory(type.getTypeName());
			po.setParentCategory(ptype.getTypeName());

			if (po.getProductName().indexOf("斤") > -1)
				po = processSinglePrice(po);

			flag = processSheet(po, wb, sheet, flag);
		}

		try {

			response.setContentType("application/msexcel;charset=GBK");
			response.setHeader("Content-Disposition", "attachment;filename=\""
					+ new String("fdlm_goods_list.xls".getBytes(), "UTF-8")
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

		HSSFCell cell2 = row.createCell(2);
		cell2.setCellStyle(style);
		cell2.setCellValue(data.getParentCategory());

		HSSFCell cell3 = row.createCell(3);
		cell3.setCellStyle(style);
		cell3.setCellValue(data.getUnit());

		HSSFCell cell4 = row.createCell(4);
		cell4.setCellStyle(style);
		if (data.getSinglePrice() != null)
			cell4.setCellValue(StringHelper.formartDecimalToStr(data
					.getSinglePrice()));
		else
			cell4.setCellValue(StringHelper.formartDecimalToStr(data.getPrice()));

		HSSFCell cell5 = row.createCell(5);
		cell5.setCellStyle(style);
		cell5.setCellValue(StringHelper.formartDecimalToStr(data.getPrice()));

		HSSFCell cell6 = row.createCell(6);
		cell6.setCellStyle(style);
		cell6.setCellValue(StringHelper.getSystime("yyyy-MM-dd HH:mm:ss", data
				.getUpdateTime().getTime()));

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
