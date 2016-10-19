/**
 *2012-10-10 下午3:23:55
 */
package com.xnradmin.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: bin_liu
 * 
 */
public class MsgType {

	public static final int SYSMSG = 1;
	public static final int USERMSG = 2;
	public static final int UNSEND = 3;

	private static Map<Integer, String> statusMap = new HashMap<Integer, String>();

	static {
		statusMap.put(SYSMSG, "系统消息");
		statusMap.put(USERMSG, "用户消息");
		statusMap.put(UNSEND, "未发送消息,用户邮箱和手机号均为空");
	}

	public static String getResultMsg(int status) {
		String result = statusMap.get(status);
		if (result == null || result.length() == 0) {
			return "未知的状态";
		}

		return result;
	}
}
