package com.xnradmin.core.sms.examples;

import java.net.URLEncoder;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.cntinker.util.StringHelper;
import com.xnradmin.core.cache.MemCachedBase;
import com.xnradmin.core.dao.CommonCustomerDAO;
import com.xnradmin.core.service.CustomerService;
import com.xnradmin.core.service.SmsRecordService;
import com.xnradmin.core.service.templates.TemplatesClassService;
import com.xnradmin.core.sms.service.SmsService;
import com.xnradmin.core.util.SpringBase;
import com.xnradmin.constant.CacheKey;
import com.xnradmin.po.CommonCustomer;
import com.xnradmin.po.sms.SmsRecord;

public class SmsExample {
	private static Logger log = Logger.getLogger(SmsExample.class);
	private SmsRecordService service;

	private MemCachedBase cache;
	
	private CustomerService customerService;
	
	private CommonCustomer customer;

	public SmsExample() {
		service = (SmsRecordService) SpringBase.getCtx().getBean(
				"SmsRecordService");
		cache = (MemCachedBase) SpringBase.getCtx().getBean("MemCachedBase");
		
		customerService=(CustomerService)SpringBase.getCtx().getBean(
				"CustomerService");
	}

	public boolean sendSms(int uid, String phone, String content) {
		Map<String, String> sendInfoMap = new HashMap<>();
//		content=content+"  【嗒嗒洗车】";
		sendInfoMap.put("Mobile", phone);
		sendInfoMap.put("Message", URLEncoder.encode(content));

		// SmsService.sendSmsGet(sendInfoMap);
		// SmsService.sendSmsPost(sendInfoMap);
		// SmsService.showReport();
		
		List<CommonCustomer> customerlist = customerService
				.findByCusName("杭州焦健");
		if (customerlist != null && customerlist.size() > 0) {
			customer = customerlist.get(0);
		}
		// 调用下发短信接口，并且获得MsgID
		List<Map<String, String>> resList = SmsService.postReport(sendInfoMap);
		if (resList != null && resList.size() >= 1) {
			Map<String, String> resMap = resList.get(0);
			if (resMap != null) {
				log.debug("map is not null");
				String p = resMap.get("phone");
				String msgID = resMap.get("msgID");

				// 入库
				SmsRecord smsRecord = new SmsRecord();
				smsRecord.setUid(uid);
				smsRecord.setContent(content);
				smsRecord.setPhone(p);
				smsRecord.setMsgID(msgID);
				smsRecord.setMtTime(new Timestamp(System.currentTimeMillis()));
				if(customer!=null){
					smsRecord.setCustomerId(customer.getId());	
					smsRecord.setCustomerName(customer.getCustomerName());	
				}
				
				// 入库
				log.debug("map is not null");
				service.save(smsRecord);

				// 判断号码是否提交成功
				if (!StringHelper.isNull(msgID) && !msgID.equals("0")) {
					// 加入缓存
					cache.add(CacheKey.SMS_INFO_KEY + msgID, smsRecord);
					log.debug("获取缓存值："+cache.get(CacheKey.SMS_INFO_KEY + msgID));
				}
			}
		} else {
			return false;
		}
		return true;
	}

	public static void main(String[] args) {
		new SmsExample().sendSms(1, "13552435313", "验证码为1231");
	}
}
