/**
 * 2013年10月5日 下午4:30:29
 */
package com.xnradmin.core.test;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.codehaus.commons.compiler.CompileException;
import org.json.JSONException;
import org.json.JSONObject;

import com.xnradmin.core.util.ScriptHelper;
import com.xnradmin.core.util.SpringBase;
import com.xnradmin.dto.ScriptDTO;

/**
 * @autohr: bin_liu
 */
public class TestScriptV2 {
	private static Logger log = Logger.getLogger(TestScriptV2.class);
	private static void testScript() throws Exception {
		ScriptHelper scriptHelper = (ScriptHelper) SpringBase.getCtx().getBean(
				"ScriptHelper");
		log.debug(scriptHelper
				.findScriptFileByName("com.xnradmin.script.ScriptUserReg"));

		int flag = 0;
		while (true) {
			Thread.sleep(1 * 1000);

			if (flag == 10)
				scriptHelper.reload();
			ScriptDTO dto = scriptHelper
					.find("com.xnradmin.script.ScriptUserReg");
			log.debug("dto:" + dto);
			Object o = scriptHelper.executeMethod("exe", dto, null);
			log.debug("flag: " + flag + " | " + o);
			flag++;
		}

	}

	private static void testScriptMethod() throws InterruptedException,
			CompileException, ClassNotFoundException, IOException {
		ScriptHelper scriptHelper = (ScriptHelper) SpringBase.getCtx().getBean(
				"ScriptHelper");
		int flag = 0;
		while (true) {
			Thread.sleep(1 * 1000);

			if (flag == 10)
				scriptHelper.reload();
			ScriptDTO dto = scriptHelper
					.find("com.xnradmin.script.business.TestPO");
			log.debug("dto:" + dto);
			Object o = scriptHelper.executeMethod("test2", dto, "a", 2);
			log.debug("flag: " + flag + " | " + o);
			flag++;
		}
	}

	private static void temp() {

	}

	private static void testUserReg() throws JSONException, CompileException,
			ClassNotFoundException, IOException {
		// String content =
		// "{\"clientUserInfo\":[{\"userUdid\":\"323232323\"},{\"userMobile\":\"13200001111\"}],\"valCode\":\"\",\"action\":\"userReg\",\"type\":\"\"}";

		// String content =
		// "{\"clientUserInfo\":[{\"userUdid\":\"323232323\"},{\"userMobile\":\"13811663676\"}],\"valCode\":\"\",\"action\":\"userReg\",\"type\":\"userGetValCode\"}";

		String content = "{\"clientUserInfo\":[{\"userUdid\":\"323232323\"},{\"userMobile\":\"13300001111\"}],\"valCode\":\"\",\"action\":\"userReg\",\"type\":\"userValCodeLogin\"}";
		JSONObject o = new JSONObject(content);

		// ------如果想设断点测试脚本，则在此下注释掉脚本运行，转去直接运行templates下的类
		ScriptHelper scriptHelper = (ScriptHelper) SpringBase.getCtx().getBean(
				"ScriptHelper");
		ScriptDTO dto = scriptHelper
				.find("com.xnradmin.script.business.ScriptUserReg");
		Object out = scriptHelper.executeMethod("execute", dto, o);

		// ----------templates类运行测试
		// ScriptUserReg u = new ScriptUserReg();
		// Object out =u.execute(o);
		log.debug("out: " + out);
	}

	private static void testUserLogin() throws JSONException, CompileException,
			ClassNotFoundException, IOException {
		String content = "{\"clientUserInfo\":[{\"userUdid\":\"11111111\"},{\"userMobile\":\"13800000001\"}],\"action\":\"userLogin\"}";
		JSONObject o = new JSONObject(content);

		// ------如果想设断点测试脚本，则在此下注释掉脚本运行，转去直接运行templates下的类
		ScriptHelper scriptHelper = (ScriptHelper) SpringBase.getCtx().getBean(
				"ScriptHelper");
		ScriptDTO dto = scriptHelper
				.find("com.xnradmin.script.business.ScriptUserLogin");
		Object out = scriptHelper.executeMethod("execute", dto, o);

		// ----------templates类运行测试
		// ScriptUserReg u = new ScriptUserReg();
		// Object out =u.execute(o);
		log.debug("out: " + out);
	}

	private static void testUserRegion() throws JSONException,
			CompileException, ClassNotFoundException, IOException {
		String content = "{\"description\":[{\"provinceId\":\"\"},{\"cityId\":\"\"},{\"areaId\":\"\"},{\"roadId\":\"\"},{\"communityId\":\"\"},{\"descriptionId\":\"\"}],\"carTypeId\":\"\",\"action\":\"userRegion\"}";
		JSONObject o = new JSONObject(content);

		// ------如果想设断点测试脚本，则在此下注释掉脚本运行，转去直接运行templates下的类
		ScriptHelper scriptHelper = (ScriptHelper) SpringBase.getCtx().getBean(
				"ScriptHelper");
		ScriptDTO dto = scriptHelper
				.find("com.xnradmin.script.business.SrciptUserRegion");
		Object out = scriptHelper.executeMethod("execute", dto, o);

		// ----------templates类运行测试
		// ScriptUserReg u = new ScriptUserReg();
		// Object out =u.execute(o);
		log.debug("out: " + out);
	}

	private static void testUserCarBrand() throws JSONException,
			CompileException, ClassNotFoundException, IOException {
		String content = "{\"carBrand\":[{\"carBrandId\":\"3\"},{\"carTypeId\":\"\"}],\"flag\":\"models\",\"action\":\"userCar\"}";
		JSONObject o = new JSONObject(content);

		// ------如果想设断点测试脚本，则在此下注释掉脚本运行，转去直接运行templates下的类
		ScriptHelper scriptHelper = (ScriptHelper) SpringBase.getCtx().getBean(
				"ScriptHelper");
		ScriptDTO dto = scriptHelper
				.find("com.xnradmin.script.business.SrciptUserCar");
		Object out = scriptHelper.executeMethod("execute", dto, o);

		// ----------templates类运行测试
		// ScriptUserReg u = new ScriptUserReg();
		// Object out =u.execute(o);
		log.debug("out: " + out);
	}

	private static void testUserCarDamage() throws JSONException,
			CompileException, ClassNotFoundException, IOException {
		String content = "{\"action\":\"carDamage\"}";
		JSONObject o = new JSONObject(content);

		// ------如果想设断点测试脚本，则在此下注释掉脚本运行，转去直接运行templates下的类
		ScriptHelper scriptHelper = (ScriptHelper) SpringBase.getCtx().getBean(
				"ScriptHelper");
		ScriptDTO dto = scriptHelper
				.find("com.xnradmin.script.business.SrciptCarDamage");
		Object out = scriptHelper.executeMethod("execute", dto, o);

		// ----------templates类运行测试
		// ScriptUserReg u = new ScriptUserReg();
		// Object out =u.execute(o);
		log.debug("out: " + out);
	}

	private static void testPostUserSetInfo() throws JSONException,
			CompileException, ClassNotFoundException, IOException {
//		String content = "{\"clientUserInfo\":[{\"userUdid\":\"123456789\"},{\"userMobile\":\"13811663676\"}],"
//				+ "\"clientUserCarInfo\":[{\"carBrandId\":\"10\"},{\"carBrandModelsId\":\"90\"},"
//				+ "{\"carBrandName\":\"奔驰\"},{\"carBrandModelsName\":\"奔驰S级\"},"
//				+ "{\"licensePlateNumber\":\"京z123456\"}],\"type\":\"clientUserCarAdd\",\"action\":\"userInfoSet\"}";
//		String content = "{\"clientUserInfo\":[{\"userUdid\":\"1\"},{\"userMobile\":\"13811663676\"}],"
//				+ "\"clientUserCarInfo\":[{\"carBrandId\":\"10\"},{\"carBrandModelsId\":\"88\"},"
//				+ "{\"carBrandName\":\"奔驰\"},{\"carBrandModelsName\":\"奔驰SL级\"},"
//				+ "{\"licensePlateNumber\":\"京P123456789\"}],\"type\":\"clientUserCarModify\","
//				+ "\"clientUserCarInfoId\":\"286\",\"action\":\"userInfoSet\"}";
//		String content = "{\"clientUserInfo\":[{\"userUdid\":\"123456789\"},{\"userMobile\":\"13811663676\"}],"
//				+ "\"clientUserRegionInfo\":[{\"buildingNumber\":\"10号楼\"},{\"parkingSpace\":\"88号车位\"},"
//				+ "{\"landmarkBuilding\":\"麦当劳\"},{\"regionOrderDescription\":\"10号楼下对面车位\"},"
//				+ "{\"regionDescriptionId\":\"255\"}],\"type\":\"clientUserRegionAdd\","
//				+ "\"action\":\"userInfoSet\"}";
		//{"status":545,"action":"userInfoSet","type":"clientUserRegionAdd","statusDesc":"设置常用地区成功"}
		String content = "{\"clientUserInfo\":[{\"userUdid\":\"123456789\"},{\"userMobile\":\"13811663676\"}],"
				+ "\"clientUserRegionInfo\":[{\"buildingNumber\":\"9号楼\"},{\"parkingSpace\":\"99号车位\"},"
				+ "{\"landmarkBuilding\":\"必胜客\"},{\"regionOrderDescription\":\"9号楼下对面车位\"},"
				+ "{\"regionDescriptionId\":\"256\"}],\"type\":\"clientUserRegionModify\","
				+ "\"clientUserRegionInfoId\":\"2\",\"action\":\"userInfoSet\"}";
		//{"status":545,"action":"userInfoSet","type":"clientUserRegionModify","statusDesc":"设置常用地区成功"}
		JSONObject o = new JSONObject(content);

		// ------如果想设断点测试脚本，则在此下注释掉脚本运行，转去直接运行templates下的类
		ScriptHelper scriptHelper = (ScriptHelper) SpringBase.getCtx().getBean(
				"ScriptHelper");
		ScriptDTO dto = scriptHelper
				.find("com.xnradmin.script.business.ScriptUserInfoSet");
		Object out = scriptHelper.executeMethod("execute", dto, o);

		// ----------templates类运行测试
		// ScriptUserReg u = new ScriptUserReg();
		// Object out =u.execute(o);
		log.debug("out: " + out);
	}

	private static void testUserOrder() throws JSONException, CompileException,
			ClassNotFoundException, IOException {

		// String content =
		// "{\"action\":\"UserOrder\",\"type\":\"getWashTime\",\"userRegionDescriptionId\":\"1\"}";
		// {"action":"UserOrder","type":"getWashTime","washTime":"2014-02-28 20:36:46"}

		String content = "{\"action\":\"UserOrder\",\"carTypeId\":\"\",\"userMobile\":\"13811663676\",\"type\":\"getOrderRegion\",\"page\":\"0\",\"pageCount\":\"0\",\"description\":[{\"provinceId\":\"\"},{\"cityId\":\"\"},{\"areaId\":\"\"},{\"roadId\":\"\"},{\"communityId\":\"\"},{\"descriptionId\":\"\"},{\"communityName\":\"山水文园\"}]}";
		//{"total":7,"pageCount":"0","userRegionInfo":[{"areaName":"东城区","cityName":"北京市","communityName":"新中街住宅小区","provinceName":"北京市","descriptionId":"256","roadName":"东直门东直门外新中街"}],"page":"0","description":[{"provinceId":""},{"cityId":""},{"areaId":""},{"roadId":""},{"communityId":""},{"descriptionId":""},{"communityName":"山水文园"}],"action":"UserOrder","type":"getOrderRegion","userRegionDescription":[{"description":"山水文园五期","descriptionId":"5838"},{"description":"山水文园二期","descriptionId":"5849"},{"description":"山水文园四期","descriptionId":"5850"},{"description":"山水文园西园","descriptionId":"5851"},{"description":"山水文园商铺","descriptionId":"5853"},{"description":"山水文园一期","descriptionId":"5855"},{"description":"山水文园三期","descriptionId":"5856"}]}
		// {"total":2,"pageCount":"0","page":"0","description":[{"provinceId":""},{"cityId":""},{"areaId":""},{"roadId":""},{"communityId":""},{"descriptionId":""},{"communityName":"管"}],"action":"UserOrder","type":"getOrderRegion","userRegionDescription":[{"description":"北京市北京市朝阳区管庄路管庄西里小区全区","descriptionId":"8"},{"description":"北京市北京市朝阳区管庄路管庄东里小区全区","descriptionId":"10"}]}

		// String content =
		// "{\"action\":\"UserOrder\",\"type\":\"getOrderCar\",\"userMobile\":\"18612597550\"}";
		// {"carDamage":[{"id":1,"name":"无损"},{"id":2,"name":"划痕"},{"id":3,"name":"掉漆"},{"id":4,"name":"碰撞"},{"id":5,"name":"车灯损坏"}],"userRechargeCount":"0","action":"UserOrder","userMobile":"18612599999","type":"getOrderCar","userCar":[{"carTypeId":"3","carBrandName":"奔驰","carBrandModelsId":"1","carTypeName":"小型车","carBrandModelsName":"S600","carBrandId":"7"},{"carTypeId":"3","carBrandName":"奔驰","carBrandModelsId":"1","carTypeName":"小型车","carBrandModelsName":"S600","carBrandId":"7"},{"carTypeId":"3","carBrandName":"奔驰","carBrandModelsId":"1","carTypeName":"小型车","carBrandModelsName":"S600","carBrandId":"7"},{"carTypeId":"3","carBrandName":"奔驰","carBrandModelsId":"1","carTypeName":"小型车","carBrandModelsName":"S600","carBrandId":"7"},{"carTypeId":"3","carBrandName":"奔驰","carBrandModelsId":"1","carTypeName":"小型车","carBrandModelsName":"S600","carBrandId":"7"}]}

		JSONObject o = new JSONObject(content);

		// ------如果想设断点测试脚本，则在此下注释掉脚本运行，转去直接运行templates下的类
		ScriptHelper scriptHelper = (ScriptHelper) SpringBase.getCtx().getBean(
				"ScriptHelper");
		ScriptDTO dto = scriptHelper
				.find("com.xnradmin.script.business.order.ScriptUserOrder");
		Object out = scriptHelper.executeMethod("execute", dto, o);
		log.debug("out: " + out);
	}

	private static void testUserOrderInfo() throws JSONException,
			CompileException, ClassNotFoundException, IOException {
//		String content = "{\"type\":\"orderAdd\",\"action\":\"userOrderInfo\",\"userId\":\"400\","
//				+ "\"userName\":\"\",\"userMobile\":\"13811663676\",\"carBrandId\":\"3\","
//				+ "\"carBrandName\":\"奥迪\",\"carBrandModelsId\":\"3\",\"carBrandModelsName\":\"A6L\","
//				+ "\"carTypeId\":\"3\",\"carTypeName\":\"小型车\",\"carDamageId\":\"2,3\","
//				+ "\"carDamageName\":\"划痕,掉漆\",\"carDamageDesc\":\"右侧车门轻微划痕\","
//				+ "\"licensePlateNumber\":\"京A12345\",\"regionDescriptionId\":\"11\","
//				+ "\"regionDescription\":\"北京市北京市房山区京良路首创新悦都全区\",\"buildingNumber\":\"18号楼\","
//				+ "\"parkingSpace\":\"60\",\"landmarkBuilding\":\"麦当劳\","
//				+ "\"timecar\":2,"
//				+ "\"regionOrderDescription\":\"18号楼门正对车位，黑色奥迪A6L\",\"userRechargeCount\":\"0\","
//				+ "\"orderIp\":\"127.0.0.1\"}";

		// 失败
		// {"descriptionNotOpened":"很抱歉，您所在的社区暂时无法提供服务，开通后我们将第一时间通知您，感谢您的支持","ruleCount":0,"userRechargeCount":"0","action":"userOrderInfo","type":"orderAdd","carWashOrderRecordId":100000000000001320,"userProduct":[]}

		// 成功
		// {"ruleCount":1,"userRechargeCount":"0","action":"userOrderInfo","type":"orderAdd","carWashOrderRecordId":
		// 100000000000000750,"userProduct":[{"returnUrl":"127.0.0.1","carTypeId":"3","ruleTypeName":"区间",
		// "productBusinessTypeName":"包卡计次","sellerName":"代理商A","sellerMoney":"10.0","productMoney":"100.0",
		// "cusId":"1","productId":"6","productType":"19","washCount":"8","ruleType":"24",
		// "productBusinessType":"12","ruleName":"自营小型车包次卡","sellerId":"39",
		// "productTypeName":"洗车类","ruleId":"9","cusName":"cusa","carTypeName":"小型车",
		// "productName":"小型车包次卡","sellerMobile":"13811968624"}]}

		// String content =
		// "{\"userRechargeCount\":\"0\",\"action\":\"userOrderInfo\",\"type\":\"orderUpdatePay\","
		// +
		// "\"carWashOrderRecordId\":\"100000000000000002\",\"paymentProviderId\":\"511\",\"paymentProviderName\":\"银联支付\","
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
		// + "\"productName\":\"小型车按次\",\"sellerMobile\":\"13811968624\"}]}";

		// {"serNo":"201403051622500082432","paymentProviderId":"511","status":514,"action":"userOrderInfo",
		// "type":"orderUpdatePay","carWashOrderRecordId":"100000000000000002","statusDesc":"获取流水号成功",
		// "paymentProviderName":"银联支付"}

//		 String content =
//		 "{\"userRechargeCount\":\"0\",\"action\":\"userOrderInfo\",\"type\":\"orderUpdateCard\","
//		 +
//		 "\"userMobile\":\"13811663676\",\"carWashOrderRecordId\":\"100000000000001625\",\"paymentProviderId\":\"526\",\"paymentProviderName\":\"账户余额消费\","
//		 +
//		 "\"userProduct\":[{\"returnUrl\":\"127.0.0.1\",\"ruleTypeName\":\"区间\","
//		 +
//		 "\"productBusinessTypeName\":\"充值计费\",\"sellerName\":\"房山\",\"sellerMoney\":\"90.0\","
//		 +
//		 "\"cusId\":\"1\",\"productMoney\":\"100.0\",\"productId\":\"22\",\"productType\":\"551\","
//		 +
//		 "\"ruleType\":\"24\",\"productBusinessType\":\"11\",\"ruleName\":\"首创新悦都\","
//		 +
//		 "\"sellerId\":\"74\",\"productTypeName\":\"车表美容类\",\"ruleId\":\"43\",\"cusName\":\"cusa\","
//		 + "\"productName\":\"打蜡（小型车，自带蜡，含洗车）\",\"sellerMobile\":\"13811968624\"}]}";

		 //{"status":516,"action":"userOrderInfo","type":"orderUpdateCard","carWashOrderRecordId":"100000000000001625","statusDesc":"消费余额成功"}
//		 String content =
//		 "{\"action\":\"userOrderInfo\",\"type\":\"orderModifyPayStatus\",\"carWashOrderRecordId\":\"100000000000001625\",\"serNo\":\"0\",\"status\":\"success\"}";
		
		
		 String content = "{\"action\":\"userOrderInfo\",\"type\":\"getRecharge\",\"carTypeId\":\"3\",\"carTypeName\":\"小型车\",\"userRechargeCount\":\"0\",\"userRechargeMoney\":\"150\"}";
		//{"descriptionNotOpened":"ok","ruleCount":1,"userRechargeMoney":"150","userRechargeCount":"0","action":"userOrderInfo","type":"getRecharge","carWashOrderRecordId":0,"userProduct":[{"returnUrl":"127.0.0.1","carTypeId":"3","ruleTypeName":"区间","productBusinessTypeName":"充值计费","sellerName":"房山","sellerMoney":"0.0","productMoney":"0.01","cusId":"1","productId":"23","productType":"19","washCount":"0","ruleType":"24","productBusinessType":"12","ruleName":"首创新悦都","sellerId":"74","productTypeName":"洗车类","ruleId":"42","cusName":"cusa","carTypeName":"小型车","productName":"小形车冲100反20","sellerMobile":"13877777777"}]}			
		JSONObject o = new JSONObject(content);

		// ------如果想设断点测试脚本，则在此下注释掉脚本运行，转去直接运行templates下的类
		ScriptHelper scriptHelper = (ScriptHelper) SpringBase.getCtx().getBean(
				"ScriptHelper");
		ScriptDTO dto = scriptHelper
				.find("com.xnradmin.script.business.order.ScriptUserOrderInfo");
		Object out = scriptHelper.executeMethod("execute", dto, o);

		// ----------templates类运行测试
		// ScriptUserReg u = new ScriptUserReg();
		// Object out =u.execute(o);
		log.debug("out: " + out);
	}

	private static void testUserRecharge() throws JSONException,
			CompileException, ClassNotFoundException, IOException {

//		String content = "{\"userRechargeCount\":\"0\",\"action\":\"userRecharge\",\"userId\":\"400\","
//				+ "\"userName\":\"\",\"userMobile\":\"13811663676\",\"type\":\"addRechargePay\","
//				+ "\"paymentProviderId\":\"511\",\"paymentProviderName\":\"银联支付\","
//				+ "\"userProduct\":[{\"returnUrl\":\"127.0.0.1\",\"ruleTypeName\":\"区间\","
//				+ "\"sellerName\":\"房山\",\"sellerMoney\":\"0.0\","
//				+ "\"cusId\":\"1\",\"productMoney\":\"100.0\",\"productId\":\"23\",\"productType\":\"19\","
//				+ "\"ruleType\":\"24\",\"ruleName\":\"首创新悦都\","
//				+ "\"sellerId\":\"74\",\"productTypeName\":\"洗车类\",\"carTypeId\":\"3\",\"carTypeName\":\"小型车\","
//				+ "\"ruleId\":\"42\",\"cusName\":\"cusa\",\"washCount\":\"0\","
//				+ "\"productName\":\"小形车冲100反20\",\"sellerMobile\":\"13811968624\"}]}";

		// {"serNo":"201403062035180011702","userRechargeConsumeRecordId":"200000000000000007"
		// ,"paymentProviderId":"511","status":514,"action":"userRecharge","type":"addRechargePay",
		// "statusDesc":"获取流水号成功","paymentProviderName":"银联支付"}
		// String content =
		// "{\"action\":\"userRecharge\",\"userMobile\":\"13800000000\",\"type\":\"findRechargeCount\"}";
		 String content =
		 "{\"action\":\"userRecharge\",\"type\":\"orderModifyPayStatus\",\"carUserRechargeConsumeRecordId\":\"200000000000000172\",\"serNo\":\"201405091556130080142\",\"status\":\"success\"}";

		JSONObject o = new JSONObject(content);

		// ------如果想设断点测试脚本，则在此下注释掉脚本运行，转去直接运行templates下的类
		ScriptHelper scriptHelper = (ScriptHelper) SpringBase.getCtx().getBean(
				"ScriptHelper");
		ScriptDTO dto = scriptHelper
				.find("com.xnradmin.script.business.recharge.ScriptUserRecharge");
		Object out = scriptHelper.executeMethod("execute", dto, o);

		// ----------templates类运行测试
		// ScriptUserReg u = new ScriptUserReg();
		// Object out =u.execute(o);
		log.debug("out: " + out);
	}

	private static void testStaffLogin() throws JSONException,
			CompileException, ClassNotFoundException, IOException {
		String content = "{\"staffInfo\":[{\"loginID\":\"xcgc\"},{\"pwd\":\"1234\"}],\"type\":\"washer\",\"action\":\"staffLogin\"}";
		JSONObject o = new JSONObject(content);

		// ------如果想设断点测试脚本，则在此下注释掉脚本运行，转去直接运行templates下的类
		ScriptHelper scriptHelper = (ScriptHelper) SpringBase.getCtx().getBean(
				"ScriptHelper");
		ScriptDTO dto = scriptHelper
				.find("com.xnradmin.script.business.ScriptStaffLogin");
		Object out = scriptHelper.executeMethod("execute", dto, o);

		// ----------templates类运行测试
		// ScriptUserReg u = new ScriptUserReg();
		// Object out =u.execute(o);
		log.debug("out: " + out);
	}

	private static void testScripDetailOrder() throws JSONException,
			CompileException, ClassNotFoundException, IOException {
		String content = "{\"action\":\"detailOrder\",\"id\":\"100000000000000020\",\"washId\":\"40\",\"statusType\":\"prepare\"}";
		JSONObject o = new JSONObject(content);

		// ------如果想设断点测试脚本，则在此下注释掉脚本运行，转去直接运行templates下的类
		ScriptHelper scriptHelper = (ScriptHelper) SpringBase.getCtx().getBean(
				"ScriptHelper");
		ScriptDTO dto = scriptHelper
				.find("com.xnradmin.script.business.order.ScripDetailOrder");
		Object out = scriptHelper.executeMethod("execute", dto, o);

		// ----------templates类运行测试
		// ScriptUserReg u = new ScriptUserReg();
		// Object out =u.execute(o);
		log.debug("out: " + out);
	}

	private static void testScriptWashAccountInfo() throws JSONException,
			CompileException, ClassNotFoundException, IOException {
		String content = "{\"action\":\"washAccountInfo\",\"washId\":\"57\"}";
		JSONObject o = new JSONObject(content);

		// ------如果想设断点测试脚本，则在此下注释掉脚本运行，转去直接运行templates下的类
		ScriptHelper scriptHelper = (ScriptHelper) SpringBase.getCtx().getBean(
				"ScriptHelper");
		ScriptDTO dto = scriptHelper
				.find("com.xnradmin.script.business.washer.ScriptWashAccountInfo");
		Object out = scriptHelper.executeMethod("execute", dto, o);

		// ----------templates类运行测试
		// ScriptUserReg u = new ScriptUserReg();
		// Object out =u.execute(o);
		log.debug("out: " + out);
	}

	private static void testScriptWashCompletedOrder() throws JSONException,
			CompileException, ClassNotFoundException, IOException {
		String content = "{\"action\":\"washCompletedOrder\",\"completedInfo\":[{\"washId\":\"49\"},{\"time\":\"\"},{\"page\":\"1\"},{\"pageCount\":\"5\"}]}";
		JSONObject o = new JSONObject(content);

		// ------如果想设断点测试脚本，则在此下注释掉脚本运行，转去直接运行templates下的类
		ScriptHelper scriptHelper = (ScriptHelper) SpringBase.getCtx().getBean(
				"ScriptHelper");
		ScriptDTO dto = scriptHelper
				.find("com.xnradmin.script.business.washer.ScriptWashCompletedOrder");
		Object out = scriptHelper.executeMethod("execute", dto, o);

		// ----------templates类运行测试
		// ScriptUserReg u = new ScriptUserReg();
		// Object out =u.execute(o);
		log.debug("out: " + out);
	}

	private static void testScriptWashHandleOrder() throws JSONException,
			CompileException, ClassNotFoundException, IOException {
		String content = "{\"action\":\"washHandleOrder\",\"washId\":\"49\",\"orderId\":\"100000000000000002\",\"handleType\":\"refuse\",\"describe\":\"联系不到车主\"}";
		JSONObject o = new JSONObject(content);

		// ------如果想设断点测试脚本，则在此下注释掉脚本运行，转去直接运行templates下的类
		ScriptHelper scriptHelper = (ScriptHelper) SpringBase.getCtx().getBean(
				"ScriptHelper");
		ScriptDTO dto = scriptHelper
				.find("com.xnradmin.script.business.washer.ScriptWashHandleOrder");
		Object out = scriptHelper.executeMethod("execute", dto, o);

		// ----------templates类运行测试
		// ScriptUserReg u = new ScriptUserReg();
		// Object out =u.execute(o);
		log.debug("out: " + out);
	}

	private static void testScriptWashLeaveHandle() throws JSONException,
			CompileException, ClassNotFoundException, IOException {
		String content = "{\"action\":\"washLeaveHandle\",\"washId\":\"57\",\"handleType\":\"cancelLeave\"}";
		JSONObject o = new JSONObject(content);

		// ------如果想设断点测试脚本，则在此下注释掉脚本运行，转去直接运行templates下的类
		ScriptHelper scriptHelper = (ScriptHelper) SpringBase.getCtx().getBean(
				"ScriptHelper");
		ScriptDTO dto = scriptHelper
				.find("com.xnradmin.script.business.washer.ScriptWashLeaveHandle");
		Object out = scriptHelper.executeMethod("execute", dto, o);

		// ----------templates类运行测试
		// ScriptUserReg u = new ScriptUserReg();
		// Object out =u.execute(o);
		log.debug("out: " + out);
	}

	private static void testScriptUserAccount() throws JSONException,
			CompileException, ClassNotFoundException, IOException {
		// String content =
		// "{\"action\":\"userAccount\",\"userMobile\":\"13300003333\",\"operateStatus\":\"\""
		// + ",\"paymentStatus\":\"509\",\"page\":\"0\",\"pageCount\":\"0\"}";
		String content = "{\"action\":\"userAccount\",\"userMobile\":\"13911071322\",\"pageCount\":\"0\",\"paymentStatus\":\"509\",\"type\":\"userAccount\",\"page\":\"0\",\"operateStatus\":\"\"}";
		JSONObject o = new JSONObject(content);

		// ------如果想设断点测试脚本，则在此下注释掉脚本运行，转去直接运行templates下的类
		ScriptHelper scriptHelper = (ScriptHelper) SpringBase.getCtx().getBean(
				"ScriptHelper");
		ScriptDTO dto = scriptHelper
				.find("com.xnradmin.script.business.ScriptUserAccount");
		Object out = scriptHelper.executeMethod("execute", dto, o);

		// ----------templates类运行测试
		// ScriptUserReg u = new ScriptUserReg();
		// Object out =u.execute(o);
		log.debug("out: " + out);
	}

	private static void testOrderUpdatePay() throws JSONException,
			CompileException, ClassNotFoundException, IOException {
		String content = "{\"action\":\"userOrderInfo\",\"type\":\"orderModifyPayStatus\",\"carWashOrderRecordId\":\"100000000000000204\",\"serNo\":\"0\",\"status\":\"success\"}";
		JSONObject o = new JSONObject(content);

		// ------如果想设断点测试脚本，则在此下注释掉脚本运行，转去直接运行templates下的类
		ScriptHelper scriptHelper = (ScriptHelper) SpringBase.getCtx().getBean(
				"ScriptHelper");
		ScriptDTO dto = scriptHelper
				.find("com.xnradmin.script.business.ScriptUserAccount");
		Object out = scriptHelper.executeMethod("execute", dto, o);

		// ----------templates类运行测试
		// ScriptUserReg u = new ScriptUserReg();
		// Object out =u.execute(o);
		log.debug("out: " + out);
	}

	private static void testScriptWashAcceptedOrder() throws JSONException,
			CompileException, ClassNotFoundException, IOException {
		String content = "{\"action\":\"washAcceptOrder\",\"washId\":\"52\"}";
		JSONObject o = new JSONObject(content);

		// ------如果想设断点测试脚本，则在此下注释掉脚本运行，转去直接运行templates下的类
		ScriptHelper scriptHelper = (ScriptHelper) SpringBase.getCtx().getBean(
				"ScriptHelper");
		ScriptDTO dto = scriptHelper
				.find("com.xnradmin.script.business.washer.ScriptWashAcceptOrder");
		Object out = scriptHelper.executeMethod("execute", dto, o);

		// ----------templates类运行测试
		// ScriptUserReg u = new ScriptUserReg();
		// Object out =u.execute(o);
		log.debug("out: " + out);
	}

	private static void testScriptPayUpmp() throws JSONException,
			CompileException, ClassNotFoundException, IOException {
		String content = "{\"action\":\"upmp\",\"version\":\"1.0.0\",\"charset\":\"UTF-8\",\"signMethod\":\"MD5\",\"signature\":\"cbaaecc53c53b02d497bfa7f942cadd5\",\"transType\":\"01\",\"merId\":\"898110248990381\",\"transStatus\":\"00\",\"respCode\":\"00\",\"serNo\":\"201404071725330119271\",\"orderId\":\"200000000000000066\",\"orderTime\":\"20140407172533\",\"settleAmount\":\"990\",\"settleCurrency\":\"156\",\"settleDate\":\"0407\",\"exchangeRate\":\"0\",\"sysReserved\":\"{traceTime=0407172533&acqCode=48031000&traceNumber=011927}\"}";
		JSONObject o = new JSONObject(content);
		// ------如果想设断点测试脚本，则在此下注释掉脚本运行，转去直接运行templates下的类
		ScriptHelper scriptHelper = (ScriptHelper) SpringBase.getCtx().getBean(
				"ScriptHelper");
		ScriptDTO dto = scriptHelper
				.find("com.xnradmin.script.business.order.pay.ScriptUpmp");
		Object out = scriptHelper.executeMethod("execute", dto, o);

		// ----------templates类运行测试
		// ScriptUserReg u = new ScriptUserReg();
		// Object out =u.execute(o);
		log.debug("out: " + out);
	}

	private static void testScriptCustomerServicePhone() throws JSONException,
			CompileException, ClassNotFoundException, IOException {
		String content = "{\"action\":\"upmp\"}";
		JSONObject o = new JSONObject(content);
		// ------如果想设断点测试脚本，则在此下注释掉脚本运行，转去直接运行templates下的类
		ScriptHelper scriptHelper = (ScriptHelper) SpringBase.getCtx().getBean(
				"ScriptHelper");
		ScriptDTO dto = scriptHelper
				.find("com.xnradmin.script.business.order.ScriptCustomerServicePhone");
		Object out = scriptHelper.executeMethod("execute", dto, o);
		log.debug("out: " + out);
	}

	public static void main(String[] args) throws Exception {

		// testScriptMethod();
		// testUserReg();
		// testUserLogin();
		// testUserRegion();
		// testUserCarBrand();
		// testUserCarDamage();
//		 testPostUserSetInfo();
		// testScriptWashAccountInfo();
		// testStaffLogin();
		// testScripDetailOrder();
		// testScriptWashAccountInfo();
		// testScriptWashCompletedOrder();
		// testScriptWashHandleOrder();
		// testScriptWashLeaveHandle();
//		testUserOrder();
		 testUserOrderInfo();
//		 testUserRecharge();
		// testScriptUserAccount();
		// testOrderUpdatePay();
		// testScriptWashAcceptedOrder();
		// testScriptPayUpmp();
//		 testScriptCustomerServicePhone();
	}
}
