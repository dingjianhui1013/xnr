/**
 *2014年8月21日 下午4:54:08
 */
package com.xnradmin.core.action;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.xnradmin.constant.StrutsResMSG;
import com.xnradmin.core.service.pay.PayInfoService;
import com.xnradmin.vo.pay.PayInfoVO;

/**
 * @author: liubin
 * 
 */
@Controller
@Scope("prototype")
@Namespace("/page/payInfo")
public class PayInfoAction extends ParentAction {

	@Autowired
	private PayInfoService service;

	private PayInfoVO query;

	private List<PayInfoVO> voList;

	@Override
	public boolean isPublic() {
		// TODO Auto-generated method stub
		return false;
	}

	@Action(value = "info", results = { @Result(name = StrutsResMSG.FAILED, location = "/pay/payinfo/info.jsp") })
	public String info() {

		return StrutsResMSG.SUCCESS;
	}

	@Action(value = "addInfo", results = { @Result(name = StrutsResMSG.FAILED, location = "/pay/payinfo/add.jsp") })
	public String addInfo() {
		return StrutsResMSG.SUCCESS;
	}

	@Action(value = "add", results = { @Result(name = StrutsResMSG.SUCCESS, type = "plainText") })
	public String add() {
		return null;
	}

	@Action(value = "modifyInfo", results = { @Result(name = StrutsResMSG.FAILED, location = "/pay/payinfo/modify.jsp") })
	public String modifyInfo() {
		return StrutsResMSG.SUCCESS;
	}

	@Action(value = "modify", results = { @Result(name = StrutsResMSG.SUCCESS, type = "plainText") })
	public String modify() {
		return null;
	}

	public List<PayInfoVO> getVoList() {
		return voList;
	}

	public void setVoList(List<PayInfoVO> voList) {
		this.voList = voList;
	}

	public PayInfoVO getQuery() {
		return query;
	}

	public void setQuery(PayInfoVO query) {
		this.query = query;
	}

}
