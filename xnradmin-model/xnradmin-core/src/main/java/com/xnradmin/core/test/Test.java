/**
 *2014年1月16日 下午3:17:53
 */
package com.xnradmin.core.test;

/**
 * @author: bin_liu
 * 
 */
public class Test {

	public static void main(String[] args) throws Exception {
		System.out.println("123123");
		System.out.println(new Test().updateStaffStatus("40", "leave"));
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
