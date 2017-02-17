package com.xnradmin.core.sms.examples;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.cntinker.util.StringHelper;
import com.xnradmin.core.cache.MemCachedBase;
import com.xnradmin.core.service.SmsRecordService;
import com.xnradmin.core.sms.service.SmsService;
import com.xnradmin.core.util.SpringBase;
import com.xnradmin.constant.CacheKey;
import com.xnradmin.po.sms.SmsRecord;

public class StatusListener {
	private static Logger log = Logger.getLogger(StatusListener.class);
	private MemCachedBase cache;
	private SmsRecordService service;
	private static StatusListener statusListener;
	
	public static StatusListener getInstance(){
		if(statusListener==null){
			statusListener=new StatusListener();
		}
		return statusListener;
	}
	private StatusListener() {
		service = (SmsRecordService) SpringBase.getCtx().getBean(
				"SmsRecordService");
		cache = (MemCachedBase) SpringBase.getCtx().getBean("MemCachedBase");
	}

	/**
	 * 获取状态报告
	 */
	public void showReport() {
		List<Map<String, String>> reportList = SmsService.showReport();
		for (Map<String, String> map : reportList) {
			if (map == null)
				return;

			String msgID = map.get("msgID");
			String phone = map.get("phone");
			String sTime = map.get("statusTime");
			String status = map.get("status");
			log.debug("show report");

			if (StringHelper.isNull(msgID))
				continue;

			SmsRecord record = (SmsRecord) cache.get(CacheKey.SMS_INFO_KEY
					+ msgID);
		
			
			log.debug("cached record :" +cache.get(CacheKey.SMS_INFO_KEY
					+ msgID));
			log.debug(record.getMsgID()+":"+msgID);
			log.debug(record.getPhone());
			log.debug(sTime);
			
			if (record == null || !record.getMsgID().trim().equals(msgID.trim()) ||!record.getPhone().equals(phone))continue;
			
			log.debug("cached record msgId:" + record.getMsgID());
			log.debug("msgID is succ");

			if (record.getMtTime() == null || StringHelper.isNull(sTime)
					|| StringHelper.isNull(msgID))
				continue;
			log.debug("Time is succ");

			SimpleDateFormat df = new SimpleDateFormat("dd");// 获得天
			try {
				Timestamp statusTime = StringHelper.getTimestamp(sTime,"yyyy/MM/dd HH:mm:ss");

				String day = df.format(record.getMtTime());// 下发时间
				String sday = df.format(statusTime);// 状态报告时间
				log.debug("mt:" + day);
				log.debug("st:" + sday);

				// 判断是否超过3天
				if (Integer.parseInt(sday) - Integer.parseInt(day) >= 3)
					return;
				log.debug("update is succ");
				// 更新数据库
				record.setStatus(status);
				record.setStatusTime(statusTime);

				service.modify(record);

			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}

	public void execute() {
		// while (true) {
		// try {
		// log.debug("lnl");
		showReport();
		// Thread.sleep(5*60*1000);
		// } catch (InterruptedException e) {
		// TODO Auto-generated catch block
		// e.printStackTrace();
		// }
	}

	// }
	public static void main(String[] args) {

		List<Map<String, String>>reportReslist = new ArrayList<Map<String, String>>();
		Map<String, String>reportResMap = new HashMap<String, String>();
		reportResMap.put("msgID", "2307417");
		reportResMap.put("phone", "13552435313");
		reportResMap.put("statusTime", "2014/3/5 8:04:11");
		reportResMap.put("status", "1");
		
		Map<String, String>reportResMap1 = new HashMap<String, String>();
		reportResMap1.put("msgID", "2307418");
		reportResMap1.put("phone", "13552435313");
		reportResMap1.put("statusTime", "2014/3/5 8:04:31");
		reportResMap1.put("status", "1");
		
		Map<String, String>reportResMap2 = new HashMap<String, String>();
		reportResMap2.put("msgID", "2307420");
		reportResMap2.put("phone", "13552435313");
		reportResMap2.put("statusTime", "2014/3/5 8:04:51");
		reportResMap2.put("status", "1");
	
		
		Map<String, String>reportResMap3 = new HashMap<String, String>();
		reportResMap3.put("msgID", "2307956");
		reportResMap3.put("phone", "13552435313");
		reportResMap3.put("statusTime", "2014/3/5 8:05:11");
		reportResMap3.put("status", "1");
		reportReslist.add(reportResMap);
		reportReslist.add(reportResMap1);
		reportReslist.add(reportResMap2);
		reportReslist.add(reportResMap3);
		new StatusListener().execute();
		
	}
}
