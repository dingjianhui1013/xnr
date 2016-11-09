/**
 * 2014年2月4日 下午5:16:27
 */
package com.xnradmin.core.action;

import java.io.IOException;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.xnradmin.core.service.CommonPortService;
import com.xnradmin.core.service.common.status.StatusService;
import com.xnradmin.constant.AjaxResult;
import com.xnradmin.constant.StrutsResMSG;
import com.xnradmin.po.CommonPort;
import com.xnradmin.po.CommonScript;
import com.xnradmin.po.common.status.Status;
import com.xnradmin.vo.client.CommonPortVO;

/**
 * @author: bin_liu
 */
@Controller
@Scope("prototype")
@Namespace("/page/port")
public class PortAction extends ParentAction {

	@Autowired
	private CommonPortService service;

	@Autowired
	private StatusService statusService;

	@Action(value = "info", results = { @Result(name = StrutsResMSG.SUCCESS, location = "/client/port/info.jsp") })
	public String info() throws IOException {
		setPageInfo();
		return StrutsResMSG.SUCCESS;
	}

	@Action(value = "addinfo", results = { @Result(name = StrutsResMSG.SUCCESS, location = "/client/port/add.jsp") })
	public String addInfo() {
		setList();
		return StrutsResMSG.SUCCESS;
	}

	@Action(value = "modifyInfo", results = { @Result(name = StrutsResMSG.SUCCESS, location = "/client/port/modify.jsp") })
	public String modifyInfo() {
		setList();
		this.query = service.findById(query.getPort().getId());
		return StrutsResMSG.SUCCESS;
	}

	@Action(value = "view", results = { @Result(name = StrutsResMSG.SUCCESS, location = "/client/port/view.jsp") })
	public String view() {
		setList();
		this.query = service.findById(query.getPort().getId());
		return StrutsResMSG.SUCCESS;
	}

	@Action(value = "add", results = { @Result(name = StrutsResMSG.SUCCESS, type = "plainText") })
	public String add() throws IOException {
		if (this.script != null && this.query.getScript() == null) {
			this.query.setScript(this.script);
		}
		int res = service.save(query, super.getCurrentStaff());
		if (res == -1)
			super.error("参数错误");
		else if (res == -2)
			super.error("已存在的接口名称");
		else if (res == -3)
			super.error("接口名称必须为英文，数字，下划线");
		else
			super.success(null, AjaxResult.CALL_BACK_TYPE_CLOSECURRENT,
					"portInfo", null);
		return null;
	}

	@Action(value = "modify", results = { @Result(name = StrutsResMSG.SUCCESS, type = "plainText") })
	public String modify() throws IOException {
		if (this.script != null && this.query.getScript() == null) {
			this.query.setScript(this.script);
		}
		int res = service.modify(query, super.getCurrentStaff());
		if (res == -1)
			super.error("参数错误");
		else if (res == -2)
			super.error("已存在的接口名称");
		else if (res == -3)
			super.error("接口名称必须为英文，数字，下划线");
		else
			super.success(null, AjaxResult.CALL_BACK_TYPE_CLOSECURRENT,
					"portInfo", null);
		return null;
	}

	@Action(value = "del", results = { @Result(name = StrutsResMSG.SUCCESS, type = "plainText") })
	public String del() throws IOException {
		int res = service.delte(query);
		if (res == 1)
			super.error("未查询到该接口");
		else if (res == 2)
			super.error("传入参数不能为空");
		else
			super.success(null, null, "portInfo", null);

		return null;
	}

	private void setPageInfo() {
		this.voList = service.listVO(query, getPageNum(), getNumPerPage());
		this.totalCount = service.getCount(query);
		setList();
	}

	private void setList() {
		this.statusList = statusService.find(CommonPort.class, "status");
		this.typeList = statusService.find(CommonPort.class, "type");
	}

	@Override
	public boolean isPublic() {
		// TODO Auto-generated method stub
		return false;
	}

	private List<CommonPortVO> voList;

	private CommonPortVO query;

	private CommonScript script;

	private List<Status> statusList;

	private List<Status> typeList;

	public CommonPortVO getQuery() {
		return query;
	}

	public void setQuery(CommonPortVO query) {
		this.query = query;
	}

	public CommonScript getScript() {
		return script;
	}

	public void setScript(CommonScript script) {
		this.script = script;
	}

	public List<CommonPortVO> getVoList() {
		return voList;
	}

	public void setVoList(List<CommonPortVO> voList) {
		this.voList = voList;
	}

	public List<Status> getStatusList() {
		return statusList;
	}

	public void setStatusList(List<Status> statusList) {
		this.statusList = statusList;
	}

	public List<Status> getTypeList() {
		return typeList;
	}

	public void setTypeList(List<Status> typeList) {
		this.typeList = typeList;
	}
}
