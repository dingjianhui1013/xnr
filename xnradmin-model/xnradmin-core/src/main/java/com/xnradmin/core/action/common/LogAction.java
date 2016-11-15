/**
 *2014年8月15日 下午6:30:07
 */
package com.xnradmin.core.action.common;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.xnradmin.constant.StrutsResMSG;
import com.xnradmin.core.action.ParentAction;
import com.xnradmin.core.service.common.LogService;
import com.xnradmin.po.common.Log;

/**
 * @author: liubin
 * 
 */
@Controller
@Scope("prototype")
@Namespace("/page/log")
public class LogAction extends ParentAction {

	private List<Log> voList;

	@Autowired
	private LogService service;
	
	private Log query;

	@Action(value = "info", results = { @Result(name = StrutsResMSG.SUCCESS, location = "/common/log/info.jsp") })
	public String info() {
		setPageInfo();
		return StrutsResMSG.SUCCESS;
	}
	
	@Action(value = "addInfo", results = { @Result(name = StrutsResMSG.SUCCESS, location = "/common/log/add.jsp") })
	public String addInfo(){
		return StrutsResMSG.SUCCESS;
	}
	
	@Action(value = "modifyInfo", results = { @Result(name = StrutsResMSG.SUCCESS, location = "/common/log/modify.jsp") })
	public String modifyInfo(){
		return StrutsResMSG.SUCCESS;
	}
	
	
	
	 private void setPageInfo(){
	        this.voList = service.listVO(query,getPageNum(),getNumPerPage());
	        this.totalCount = service.getCount(query);
	    }

	@Override
	public boolean isPublic() {
		// TODO Auto-generated method stub
		return false;
	}

	public Log getQuery() {
		return query;
	}

	public void setQuery(Log query) {
		this.query = query;
	}

}
