/**
 * 2014年1月12日 下午5:33:09
 */
package com.xnradmin.core.test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Iterator;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xml.sax.SAXException;

import com.cntinker.util.FileHelper;
import com.cntinker.util.HttpunitHelper;
import com.cntinker.util.StringHelper;
import com.meterware.httpunit.WebConversation;
import com.meterware.httpunit.WebResponse;
import com.xnradmin.po.CommonMenu;
import com.xnradmin.po.CommonStaff;

/**
 * @author: bin_liu
 */
public class TestJson {
	private static Logger log = Logger.getLogger(TestJson.class);
	private static void test() throws JSONException, org.json.JSONException {
		String in = " {\"userMobile\":\"18612597550\",\"action\":\"userAccount\",\"userOrder\":[{\"carWashOrderRecordId\":\"100000000000000229\",\"userProduct\":[{\"returnUrl\":\"127.0.0.1\",\"ruleTypeName\":\"区间\",\"productBusinessTypeName\":\"按次计费\",\"sellerMoney\":\"10.0\",\"sellerName\":\"张三\",\"cusId\":\"1\",\"productMoney\":\"15.0\",\"productType\":\"19\",\"productId\":\"3\",\"washCount\":\"1\",\"ruleType\":\"24\",\"productBusinessType\":\"11\",\"productTypeName\":\"洗车类\",\"sellerId\":\"48\",\"ruleName\":\"自营小型车按次\",\"ruleId\":\"7\",\"cusName\":\"cusa\",\"productName\":\"小型车按次\",\"sellerMobile\":\"18911810974\"},{\"returnUrl\":\"127.0.0.1\",\"ruleTypeName\":\"区间\",\"productBusinessTypeName\":\"包卡计次\",\"sellerMoney\":\"10.0\",\"sellerName\":\"代理商A\",\"cusId\":\"1\",\"productMoney\":\"100.0\",\"productType\":\"19\",\"productId\":\"6\",\"washCount\":\"8\",\"ruleType\":\"24\",\"productBusinessType\":\"12\",\"productTypeName\":\"洗车类\",\"sellerId\":\"39\",\"ruleName\":\"自营小型车包次卡\",\"ruleId\":\"9\",\"cusName\":\"cusa\",\"productName\":\"小型车包次卡\",\"sellerMobile\":\"13811968624\"}]}],\"userCar\":[{\"licensePlateNumber\":\"88888\",\"carBrandName\":\"奥迪\",\"carTypeId\":\"3\",\"carTypeName\":\"小型车\",\"carBrandModelsId\":\"3\",\"carBrandModelsName\":\"A6L\",\"carBrandId\":\"3\"}],\"userRechargeCount\":\"27\",\"userId\":\"193\"}";
		JSONObject o = new JSONObject(in);

		log.debug(o.get("userMobile"));

		Object obj = o.get("userCar");
		log.debug(obj.toString());

		JSONArray a = new JSONArray(obj.toString());
		log.debug(a);

		JSONObject usercar = (JSONObject) a.get(0);
		log.debug(usercar.get("carBrandName"));
		log.debug(usercar.get("licensePlateNumber"));
		log.debug(usercar.get("carBrandModelsId"));
	}

	private static void test2() throws JSONException {
		CommonStaff staff = new CommonStaff();
		staff.setCreateTime(new Timestamp(System.currentTimeMillis()));
		staff.setLoginId("guest");
		CommonMenu menu = new CommonMenu();
		menu.setEnName("abc");
		menu.setMenuLink("/page/test");

		JSONObject obj = new JSONObject(staff);
		JSONObject obj2 = new JSONObject(menu);

		JSONObject all = new JSONObject();
		all.append("staff", obj);
		all.append("menu", obj2);

		log.debug(obj);
		log.debug(all);
	}

	private static void testJson3() throws JSONException, org.json.JSONException {
		JSONObject obj = new JSONObject();
		JSONArray a = new JSONArray();

		JSONObject btn1 = new JSONObject();
		btn1.put("type", "click");
		btn1.put("name", "test");
		btn1.put("key", "V1001_TODAY_MUSIC");
		JSONObject btn2 = new JSONObject();
		btn2.put("type", "click");
		btn2.put("name", "歌手简介");
		btn2.put("key", "V1001_TODAY_MUSIC");
		JSONObject btn3 = new JSONObject();
		JSONArray subbtn = new JSONArray();
		JSONObject subbtn1 = new JSONObject();
		subbtn1.put("type", "view");
		subbtn1.put("name", "歌手简介");
		subbtn1.put("url", "http://www.baidu.com");
		subbtn.put(0, subbtn1);
		btn3.put("sub_button", subbtn);

		a.put(0, btn1);
		a.put(1, btn2);
		a.put(2, btn3);
		obj.put("button", a);

		JSONObject temp = new JSONObject();
		temp.put("name", "testbusiness");

		obj.put("loginid", temp);
		log.debug(obj.toString());
	}

	private static void testJson1() throws FileNotFoundException, IOException,
			JSONException, org.json.JSONException {
		// 饭店联盟
		String f = "/Users/liubin/temp/json/json1.txt";
		String content = FileHelper.getContent(f);
		JSONObject jo = new JSONObject(content);
		JSONArray data = (JSONArray) jo.get("data");

		for (int i = 0; i < data.length(); i++) {
			JSONObject temp = (JSONObject) data.get(i);
			StringBuffer sb = new StringBuffer();
			sb.append("名称：").append(temp.get("commodityName"));
			sb.append(" | 价格：").append(temp.get("price"));
			sb.append(" | 单位：").append(temp.get("unit"));
			sb.append(" | 分类ID：").append(temp.get("typeId"));
			log.debug(sb.toString());
		}

		log.debug(data.length());
	}

	private static void testFdlmType() throws IOException, SAXException,
			JSONException, org.json.JSONException {
		String u = "http://115.28.252.36/RA/categorytype/listapp";

		HttpunitHelper webLogin = new HttpunitHelper();
		WebConversation wc = webLogin.init(
				"vm/3.59.0 Webkit/1.0(Android 4.4.4; Sony/L39h)", true);

		WebResponse r = wc.getResponse(u);
		log.debug(r.getText());
		String content = r.getText();
		JSONObject jo = new JSONObject(content);
		JSONArray data = (JSONArray) jo.get("data");
		for (int i = 0; i < data.length(); i++) {
			JSONObject temp = (JSONObject) data.get(i);
			StringBuffer sb = new StringBuffer();
			sb.append("名称：").append(temp.get("type_name"));
			sb.append(" | ID：").append(temp.get("iD"));
			sb.append(" | 父类：").append(temp.get("parentId"));
			sb.append(" | url：").append(temp.get("url"));
			log.debug(sb.toString());
		}
	}

	private static void testYsmc() throws IOException, SAXException,
			JSONException, org.json.JSONException {
		String u = "http://online.yunshanmeicai.com/preview/preview";

		HttpunitHelper webLogin = new HttpunitHelper();
		WebConversation wc = webLogin.init(
				"vm/3.59.0 Webkit/1.0(Android 4.4.4; Sony/L39h)", true);

		WebResponse r = wc.getResponse(u);
		// log.debug(r.getText());

		log.debug("----------------");

		String temp = StringHelper.spiltStr(r.getText(), "window.data",
				"console.log(data)");
		// log.debug(temp);

		String temp2 = StringHelper.spiltStr(temp, "=", "};");

		String res = temp2 + "};";
		

		res = StringHelper.convertUnicode(res);
		log.debug(res);
		processYsmcExcel(res);

	}

	private static void processYsmcExcel(String res) throws JSONException, org.json.JSONException {
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

		int flag = 0;

		while (it.hasNext()) {
			String k = it.next().toString();
			// log.debug(jo.get(k));
			flag = processYsmcSubLevel1(k,jo.get(k).toString(), wb, sheet, row,
					flag);
		}

		FileOutputStream fout;
		try {
			fout = new FileOutputStream("/Users/liubin/temp/ysmc"
					+ StringHelper.getSystime("yyyyMMdd") + ".xls");
			wb.write(fout);
			fout.close();
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
	private static int processYsmcSubLevel1(String type1,String content, HSSFWorkbook wb,
			HSSFSheet sheet, HSSFRow row, int flag) throws JSONException, org.json.JSONException {
		JSONObject jo = new JSONObject(content);
		Iterator it = jo.keys();

		while (it.hasNext()) {
			String k = it.next().toString();
			// log.debug(jo.get(k));

			flag = processYsmcSubLevel2(type1,k,jo.get(k).toString(), wb, sheet, row,
					flag);

		}
		return flag;

	}

	private static int processYsmcSubLevel2(String type1,String type2,String content, HSSFWorkbook wb,
			HSSFSheet sheet, HSSFRow row, int flag) throws JSONException, org.json.JSONException {
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
			log.debug(sb.toString());

			int f = flag + 1;
			row = sheet.createRow(f);
			log.debug("row: " + f);

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

	private static void testpx() throws JSONException {
		JSONObject o = new JSONObject();
		JSONObject b = new JSONObject();
		b.put("1", "1");
		b.put("2", "2");
		o.put("aa", b);
		o.put("bb", b);
		o.put("cc", b);
		o.put("dd", b);
		log.debug(o.isNull("1"));
		log.debug(o.toString());
		
	}
	
	public static void main(String[] args) throws Exception {
		// testFdlmType();
		// testJson1();
		test();
		//test();
	}
}
