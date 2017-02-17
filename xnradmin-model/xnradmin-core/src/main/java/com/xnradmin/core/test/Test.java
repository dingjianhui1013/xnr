/**
 *2014年1月16日 下午3:17:53
 */
package com.xnradmin.core.test;

import org.apache.log4j.Logger;

/**
 * @author: bin_liu
 * 
 */
public class Test {

	private static Logger log = Logger.getLogger(Test.class);
	public static void main(String[] args) throws Exception {
		log.debug("123123");
		log.debug(new Test().updateStaffStatus("40", "leave"));
	}

	public String updateStaffStatus(String washId, String handleType) {
		StringBuffer sb = new StringBuffer();
		sb.append("from CommonStaff staff,CommonStaffStatus state where");
		sb.append("  staff.statusId=state.ID ");
		sb.append(" and staff.id=" + Integer.parseInt(washId));
//				== "leave" ? "('正常','工作')"
//				: "('休息')");
		sb.append(" and state.statusName in ('正常','工作')" );
		return sb.toString();
	}
}
