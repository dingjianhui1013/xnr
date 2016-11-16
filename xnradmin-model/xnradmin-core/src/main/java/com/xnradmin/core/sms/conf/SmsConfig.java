package com.xnradmin.core.sms.conf;

import java.io.InputStream;
import java.util.PropertyResourceBundle;

public class SmsConfig {
	public static String USER_NAME;
	public static String USER_PWD;
	
	public static String SMS_SEND_POST_URL;
	public static String SMS_SEND_GET_URL;
	public static String SMS_BALANCE_GET_URL;
	public static String SMS_REPORT_POST_URL;
	public static String SMS_REPORT_GET_URL;
	public static String SMS_GET_MO_URL;
	public static String SMS_SHOW_REPORT_URL;
	
	private static final String KEY_USER_NAME="user.name";
	private static final String KEY_USER_PWD="user.pwd";
	
	private static final String KEY_SMS_SEND_POST_URL="sms.send.post.url";
	private static final String KEY_SMS_SEND_GET_URL="sms.send.get.url";
	private static final String KEY_SMS_BALANCE_GET_URL="sms.balance.get.url";
	private static final String KEY_SMS_REPORT_POST_URL="sms.report.post.url";
	private static final String KEY_SMS_REPORT_GET_URL="sms.report.get.url";
	private static final String KEY_SMS_GET_MO_URL="sms.get.mo.url";
	private static final String KEY_SMS_SHOW_REPORT_URL="sms.show.report.url";
	
	private static final String CONF_FILE_NAME = "com/xnradmin/core/sms/conf/sms.properties";
	
	static {
        try {
			InputStream fis = SmsConfig.class.getClassLoader().getResourceAsStream(CONF_FILE_NAME);
			PropertyResourceBundle props = new PropertyResourceBundle(fis);
			USER_NAME = props.getString(KEY_USER_NAME);
			USER_PWD = props.getString(KEY_USER_PWD);
			SMS_SEND_POST_URL = props.getString(KEY_SMS_SEND_POST_URL);
			SMS_SEND_GET_URL = props.getString(KEY_SMS_SEND_GET_URL);
			SMS_BALANCE_GET_URL = props.getString(KEY_SMS_BALANCE_GET_URL);
			SMS_REPORT_POST_URL = props.getString(KEY_SMS_REPORT_POST_URL);
			SMS_REPORT_GET_URL = props.getString(KEY_SMS_REPORT_GET_URL);
			SMS_GET_MO_URL = props.getString(KEY_SMS_GET_MO_URL);
			SMS_SHOW_REPORT_URL = props.getString(KEY_SMS_SHOW_REPORT_URL);
        } catch(Exception e) {
        	e.printStackTrace();
        }
    }
}
