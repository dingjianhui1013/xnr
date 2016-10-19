/**
 *2014年12月1日 下午3:17:52
*/
package com.xnradmin.vo.business;

import com.cntinker.util.ReflectHelper;
import com.xnradmin.po.business.BusinessData;

/**
 * @author: liubin
 *
 */
public class BusinessDataVO implements java.io.Serializable{

	private BusinessData businessData;
	
	private String queryStime;
	
	private String queryEtime;
	
	public String toString() {
		String res = "";
		try {
			res = ReflectHelper.makeToString(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public BusinessData getBusinessData() {
		return businessData;
	}

	public String getQueryStime() {
		return queryStime;
	}

	public String getQueryEtime() {
		return queryEtime;
	}

	public void setBusinessData(BusinessData businessData) {
		this.businessData = businessData;
	}

	public void setQueryStime(String queryStime) {
		this.queryStime = queryStime;
	}

	public void setQueryEtime(String queryEtime) {
		this.queryEtime = queryEtime;
	}
}
