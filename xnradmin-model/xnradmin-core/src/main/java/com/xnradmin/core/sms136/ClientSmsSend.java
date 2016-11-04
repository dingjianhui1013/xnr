package com.xnradmin.core.sms136;

import java.sql.Timestamp;
import java.util.List;

import com.xnradmin.core.service.CustomerService;
import com.xnradmin.core.service.SmsRecordService;
import com.xnradmin.core.sms136.sdk.ClientSDK;
import com.xnradmin.core.util.SpringBase;
import com.xnradmin.po.CommonCustomer;
import com.xnradmin.po.sms.SmsRecord;

public class ClientSmsSend {
	ClientSDK sdk = new ClientSDK();

	private String username = "376068867";
	private String password = "123456";

	private SmsRecordService service;
	
	private CommonCustomer customer;

	private CustomerService customerService;

	public ClientSmsSend() {
		service = (SmsRecordService) SpringBase.getCtx().getBean(
				"SmsRecordService");

		customerService = (CustomerService) SpringBase.getCtx().getBean(
				"CustomerService");
	}

	/**
	 * 测试发送短信
	 * 
	 * @throws Exception
	 */
	public boolean sendSms(int uid,String phone, String content) throws Exception {
//		content = content + "  【嗒嗒洗车】";
		String ret = sdk.sendSms(username, password, phone, content);
		System.out.println("send sms result:"+ret);
		if (!ret.trim().equals("0")) {
			return false;
		}

		List<CommonCustomer> customerlist = customerService
				.findByCusName("北京天信博易科技有限公司");
		if (customerlist != null && customerlist.size() > 0) {
			customer = customerlist.get(0);
		}
		// 入库
		SmsRecord smsRecord = new SmsRecord();
		smsRecord.setUid(uid);
		smsRecord.setContent(content);
		smsRecord.setPhone(phone);
		// smsRecord.setMsgID(msgID);
		smsRecord.setMtTime(new Timestamp(System.currentTimeMillis()));
		if (customer != null) {
			smsRecord.setCustomerId(customer.getId());
			smsRecord.setCustomerName(customer.getCustomerName());
		}

		// 入库
		service.save(smsRecord);
		return true;
	}

	public static void main(String[] args) {
		try {
			new ClientSmsSend().sendSms(2,"18210113786", "验证码为123423");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
