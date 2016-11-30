/**
 * 2014年1月29日 下午5:06:24
 */
package com.xnradmin.core.test;

import java.io.IOException;
import java.net.MalformedURLException;

import org.apache.log4j.Logger;
import org.codehaus.commons.compiler.CompileException;
import org.json.JSONException;
import org.xml.sax.SAXException;

import com.cntinker.data.HttpReturnData;
import com.cntinker.util.HttpHelper;
import com.cntinker.util.HttpunitHelper;
import com.cntinker.util.StringHelper;
import com.meterware.httpunit.WebConversation;
import com.meterware.httpunit.WebResponse;
import com.xnradmin.core.util.EncodeDecodeUtil;

/**
 * @author: bin_liu
 */
public class TestHttp {
	private static Logger log = Logger.getLogger(TestHttp.class);
	private static void test() throws MalformedURLException, IOException {
		String url = "http://admin.didaxiche.com";
		log.debug(HttpHelper.sendGet(url));
	}

	private static void testPost() throws IOException {
		// String url = "http://localhost/interface/sync.jsp";
		String url = "http://115.28.9.26:8080/interface/sync.jsp";
		String content = "{\"clientUserInfo\":[{\"userUdid\":\"11111111\"},{\"userMobile\":\"13800000007\"}],\"valCode\":\"1231\",\"action\":\"userReg\"}";
		content = EncodeDecodeUtil.encode(content);
		log.debug(HttpHelper.postXml(url, content));
	}

	private static void testPostLogin() throws IOException {
		// String url = "http://localhost/interface/sync.jsp";
		// String url = "http://www.fxzw.org/sync.jsp";
		String url = "http://115.29.38.253:8080/interface/sync.jsp";
		// String url = "http://115.28.9.26:8080/interface/sync.jsp";
		// String content =
		// "{\"clientUserInfo\":[{\"userUdid\":\"11111111\"},{\"userMobile\":\"13800000001\"}],\"action\":\"userLogin\"}";
		String content = "{\"clientUserInfo\":[{\"userUdid\":\"2c41fb53ac0501ca2a5eaa9d714c4bc45ee0051d\"},{\"userMobile\":\"13811968624\"}],\"valCode\":\"\",\"action\":\"userReg\",\"type\":\"userValCodeLogin\"}";
		content = EncodeDecodeUtil.encode(content);
		log.debug(EncodeDecodeUtil.decode(HttpHelper.postXml(url,
				content)));
	}

	private static void testPostCarDamage() throws IOException {
		// String url = "http://localhost/interface/sync.jsp";
		String url = "http://115.28.9.26:8080/interface/sync.jsp";
		String content = "{\"action\":\"carDamage\"}";
		content = EncodeDecodeUtil.encode(content);
		log.debug(HttpHelper.postXml(url, content));
	}

	private static void testUserCarBrand() throws JSONException,
			CompileException, ClassNotFoundException, IOException {
		String url = "http://115.28.9.26:8080/interface/sync.jsp";
		String content = "{\"carBrand\":[{\"carBrandId\":\"3\"},{\"carTypeId\":\"\"}],\"flag\":\"brand\",\"action\":\"userCar\"}";
		content = EncodeDecodeUtil.encode(content);
		log.debug(HttpHelper.postXml(url, content));
	}

	private static void testUserRegion() throws JSONException,
			CompileException, ClassNotFoundException, IOException {
		String url = "http://115.28.9.26:8080/interface/sync.jsp";
		String content = "{\"description\":[{\"provinceId\":\"\"},{\"cityId\":\"\"},{\"areaId\":\"\"},{\"roadId\":\"\"},{\"communityId\":\"\"},{\"descriptionId\":\"\"}],\"carTypeId\":\"\",\"action\":\"userRegion\"}";
		content = EncodeDecodeUtil.encode(content);
		log.debug(EncodeDecodeUtil.decode(HttpHelper.postXml(url,
				content)));
	}

	private static void testPostUserSetInfo() throws IOException {
		// String url = "http://localhost/interface/sync.jsp";
		String url = "http://115.28.9.26:8080/interface/sync.jsp";
		String content = "{\"clientUserInfo\":[{\"userUdid\":\"863970020885893\"},{\"userMobile\":\"13801023944\"}],\"clientUserCarInfo\":[{\"carBrandId\":\"1\"},"
				+ "{\"carBrandModelsId\":\"412\"},{\"carTypeId\":\"3\"},{\"carBrandName\":\"奥迪\"},{\"carBrandModelsName\":\"奥迪A4\"},"
				+ "{\"licensePlateNumber\":\"京z123456\"}],\"type\":\"clientUserCarAdd\",\"action\":\"userInfoSet\"}";
		content = EncodeDecodeUtil.encode(content);
		log.debug(HttpHelper.postXml(url, content));
	}

	private static void testStaffLoginInfo() throws IOException {
		// String url = "http://localhost/interface/sync.jsp";
		String url = "http://115.28.9.26:8080/interface/sync.jsp";
		String content = "{\"staffInfo\":[{\"loginID\":\"1\"},{\"pwd\":\"18612599999\"}],\"action\":\"staffLogin\"}";
		content = EncodeDecodeUtil.encode(content);
		log.debug(HttpHelper.postXml(url, content));
	}

	private static void testUserOrder() throws IOException {
		// String url = "http://localhost/interface/sync.jsp";
		String url = "http://115.28.9.26:8080/interface/sync.jsp";
		// String content =
		// "{\"action\":\"UserOrder\",\"carTypeId\":\"\",\"type\":\"getOrderRegion\",\"description\":[{\"provinceId\":\"\"},{\"cityId\":\"\"},{\"areaId\":\"\"},{\"roadId\":\"\"},{\"communityId\":\"\"},{\"descriptionId\":\"\"}]}";
		String content = "{\"action\":\"UserOrder\",\"type\":\"getOrderCar\",\"userMobile\":\"18612597552\"}";
		content = EncodeDecodeUtil.encode(content);
		log.debug(EncodeDecodeUtil.decode(HttpHelper.postXml(url,
				content)));
	}

	private static void testWeather() throws IOException {
		// String url = "http://localhost/interface/sync.jsp";
		String url = "http://115.28.9.26:8080/interface/sync.jsp";
		String content = "{\"action\":\"weather\",\"city\":\"北京\",\"day\":\"0\"}";
		content = EncodeDecodeUtil.encode(content);
		String res = HttpHelper.postXml(url, content);
		log.debug(res);
		log.debug(EncodeDecodeUtil.decode(res));
	}

	private static void testUserOrderInfo() throws JSONException,
			CompileException, ClassNotFoundException, IOException {
		String url = "http://115.28.9.26:8080/interface/sync.jsp";
		String content = "{\"type\":\"orderAdd\",\"action\":\"userOrderInfo\",\"userId\":\"3\","
				+ "\"userName\":\"liuxiang\",\"userMobile\":\"13800000000\",\"carBrandId\":\"3\","
				+ "\"carBrandName\":\"奥迪\",\"carBrandModelsId\":\"3\",\"carBrandModelsName\":\"A6L\","
				+ "\"carTypeId\":\"3\",\"carTypeName\":\"小型车\",\"carDamageId\":\"2,3\","
				+ "\"carDamageName\":\"划痕,掉漆\",\"carDamageDesc\":\"右侧车门轻微划痕\","
				+ "\"licensePlateNumber\":\"京A12345\",\"regionDescriptionId\":\"2\","
				+ "\"regionDescription\":\"艾泽拉斯太阳井太阳一区太阳南路阳光小区南区\",\"buildingNumber\":\"18号楼\","
				+ "\"parkingSpace\":\"60\",\"landmarkBuilding\":\"麦当劳\","
				+ "\"regionOrderDescription\":\"18号楼门正对车位，黑色奥迪A6L\",\"userRechargeCount\":\"0\","
				+ "\"orderIp\":\"127.0.0.1\"}";
		// {"userRechargeCount":"0","action":"userOrderInfo","type":"orderAdd","carWashOrderRecordId":9,
		// "userProduct":[{"returnUrl":"127.0.0.1","ruleTypeName":"区间","productBusinessTypeName":"按次计费",
		// "sellerName":"代理商A","sellerMoney":"10.0","cusId":"1","productMoney":"15.0","productId":"3",
		// "productType":"19","ruleType":"24","productBusinessType":"11","ruleName":"自营小型车按次",
		// "sellerId":"39","productTypeName":"洗车类","ruleId":"7","cusName":"cusa","productName":"小型车按次"}]}

		// String content =
		// "{\"userRechargeCount\":\"0\",\"action\":\"userOrderInfo\",\"type\":\"orderUpdatePay\","
		// +
		// "\"carWashOrderRecordId\":\"9\",\"paymentProviderId\":\"511\",\"paymentProviderName\":\"银联支付\","
		// +
		// "\"userProduct\":[{\"returnUrl\":\"127.0.0.1\",\"ruleTypeName\":\"区间\","
		// +
		// "\"productBusinessTypeName\":\"按次计费\",\"sellerName\":\"代理商A\",\"sellerMoney\":\"10.0\","
		// +
		// "\"cusId\":\"1\",\"productMoney\":\"15.0\",\"productId\":\"3\",\"productType\":\"19\","
		// +
		// "\"ruleType\":\"24\",\"productBusinessType\":\"11\",\"ruleName\":\"自营小型车按次\","
		// +
		// "\"sellerId\":\"39\",\"productTypeName\":\"洗车类\",\"ruleId\":\"7\",\"cusName\":\"cusa\","
		// + "\"productName\":\"小型车按次\"}]}";

		// String content =
		// "{\"regionOrderDescription\":\"\",\"carDamageName\":\"划痕\",\"carTypeId\":\"1\","
		// +
		// "\"carBrandName\":\"奥迪\",\"carBrandModelsId\":\"1343\",\"orderIp\":\"127.0.0.1\","
		// +
		// "\"userRechargeCount\":\"12\",\"landmarkBuilding\":\"\",\"type\":\"orderAdd\","
		// +
		// "\"regionDescription\":\"艾泽拉斯太阳井太阳一区太阳南路阳光小区南区\",\"carDamageDesc\":\"\","
		// +
		// "\"carDamageId\":\"2\",\"regionDescriptionId\":\"2\",\"timecar\":\"\",\"userId\":\"193\","
		// +
		// "\"licensePlateNumber\":\"车牌号为:88888\",\"userMobile\":\"18612597550\","
		// +
		// "\"action\":\"userOrderInfo\",\"userName\":\"\",\"carTypeName\":\"大型车\",\"parkingSpace\":\"\","
		// +
		// "\"carBrandModelsName\":\"奥迪Q7\",\"buildingNumber\":\"\",\"carBrandId\":\"1\"}";
		// String content =
		// "{\"regionOrderDescription\":\"1\",\"carDamageName\":\"无损\",\"carTypeId\":\"2\","
		// +
		// "\"carBrandName\":\"奥迪\",\"carBrandModelsId\":\"1028\",\"orderIp\":\"127.0.0.1\","
		// +
		// "\"userRechargeCount\":\"42\",\"landmarkBuilding\":\"1\",\"type\":\"orderAdd\","
		// +
		// "\"regionDescription\":\"北京市市辖区西城区西直门A小区1-5号楼\",\"carDamageDesc\":\"\","
		// +
		// "\"carDamageId\":\"1\",\"regionDescriptionId\":\"3\",\"timecar\":\"2\",\"userId\":\"311\","
		// +
		// "\"licensePlateNumber\":\"67\",\"userMobile\":\"18612597550\",\"action\":\"userOrderInfo\","
		// + "\"userName\":\"\",\"carTypeName\":\"中形车\",\"parkingSpace\":\"1\","
		// +
		// "\"carBrandModelsName\":\"allroad\",\"buildingNumber\":\"1\",\"carBrandId\":\"1\"}";

		content = EncodeDecodeUtil.encode(content);
		log.debug(EncodeDecodeUtil.decode(HttpHelper.postXml(url,
				content)));
	}

	private static void testPay() throws IOException {
		String url = "http://115.28.9.26:8080/interface/sync.jsp";
		String content = "{\"action\":\"upmp\",\"version\":\"1.0.0\",\"charset\":\"UTF-8\""
				+ ",\"signMethod\":\"MD5\",\"signature\":\"b9a0542393198a48bc55a7b61d3bea57\",\"transType\":\"01\",\"merId\":\"880000000001037\""
				+ ",\"transStatus\":\"00\",\"respCode\":\"00\",\"respMsg\":\"\",\"serNo\":\"201403101759450009491\""
				+ ",\"orderId\":\"100000000000000135\",\"orderType\":\"525\",\"orderTypeName\":\"按次消费\",\"orderTime\":\"20140310175945\""
				+ ",\"settleAmount\":\"1500\",\"settleCurrency\":\"156\",\"settleDate\":\"0123\",\"exchangeRate\":\"0\""
				+ ",\"exchangeDate\":\"\",\"merReserved\":\"\",\"reqReserved\":\"\",\"sysReserved\":\"{traceTime=0310175945&acqCode=00215800&traceNumber=000949}\"}";
		content = EncodeDecodeUtil.encode(content);
		String res = HttpHelper.postXml(url, content);
		log.debug(res);
		log.debug(EncodeDecodeUtil.decode(res));
	}

	private static void testPaya() throws IOException {
		String url = "http://115.28.9.26:8080/interface/sync.jsp";
		String content = "{\"action\":\"userOrderInfo\",\"type\":\"orderModifyPayStatus\",\"carWashOrderRecordId\":\"100000000000000204\",\"serNo\":\"0\",\"status\":\"success\"}";
		content = EncodeDecodeUtil.encode(content);
		String res = HttpHelper.postXml(url, content);
		log.debug(res);
		log.debug(EncodeDecodeUtil.decode(res));
	}

	private static void testSoftware() throws IOException {
		String url = "http://115.28.9.26:8080/interface/sync.jsp";
		String content = "{\"action\":\"software\",\"softwareType\":\"532\",\"osType\":\"531\"}";
		content = EncodeDecodeUtil.encode(content);
		String res = HttpHelper.postXml(url, content);
		log.debug(res);
		log.debug(EncodeDecodeUtil.decode(res));
	}

	private static void testUserAccount() throws IOException {
		String url = "http://115.28.9.26:8080/interface/sync.jsp";
		String content = "{\"action\":\"userAccount\",\"type\":\"userOrderDetail\",\"carWashOrderRecordId\":\"100000000000000803\"}";
		content = EncodeDecodeUtil.encode(content);
		String res = HttpHelper.postXml(url, content);
		log.debug(res);
		log.debug(EncodeDecodeUtil.decode(res));
	}

	private static void testSend() throws IOException, SAXException {

		HttpunitHelper webLogin = new HttpunitHelper();
		WebConversation wc = webLogin.init(
				"vm/3.59.0 Webkit/1.0(Android 4.4.4; Sony/L39h)", true);

		WebResponse r = wc.getResponse("http://admin.51xnr.com/test/test.jsp");
		log.debug(r.getText());
	}

	private static void t() throws IOException, SAXException {
		String url = "http://www.123haitao.com/api/client/login/";
		//String url = "http://www.123haitao.com/api/client/register/";
		//String url = "http://www.fxzw.org/test.jsp?test=abc&dcd=123";
		//String url = "http://www.leihu365.com/test/test.php";
		String content = "{\"password\":\"111111\",\"username\":\"lin11\"}";
		//String content = "{\"username\":\"zhao\",\"code\":\"469425\",\"password\":\"123456\",\"email\":\"84771176@qq.com\",\"mobile\":\"15822789694\"}";
		HttpReturnData data = HttpHelper.postXmlData(url, content);
		log.debug(data.getHeader().toString());
		log.debug(StringHelper.convertUnicode(data.getContent()));
		
	}

	public static void main(String[] args) throws Exception {
		// testPost();
		// testPostLogin();
		// testPostCarDamage();
		// testPostUserSetInfo();
		// testStaffLoginInfo();
		// testUserCarBrand();
		// testUserOrderInfo();
		// testUserRegion();
		// testWeather();
		// testPaya();
		// testUserOrder();
		// testSoftware();
		// testUserAccount();
		// testSend();
		t();
	}
}
