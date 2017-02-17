/**
 *2014年8月31日 下午4:23:51
 */
package com.xnradmin.client.action.wx;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.xnradmin.client.service.wx.WXReactiveMessageService;
import com.xnradmin.constant.AjaxResult;
import com.xnradmin.constant.StrutsResMSG;
import com.xnradmin.core.action.ParentAction;
import com.xnradmin.core.service.common.status.StatusService;
import com.xnradmin.po.CommonAttach;
import com.xnradmin.po.common.status.Status;
import com.xnradmin.po.wx.WXMessage;
import com.xnradmin.po.wx.WXReactiveMessage;
import com.xnradmin.vo.client.wx.WXMessageVO;
import com.xnradmin.vo.client.wx.WXReactiveMessageVO;

/**
 * @author: liubin
 * 
 */
@Controller
@Scope("prototype")
@Namespace("/page/wx/wxreactivemessage")
@ParentPackage("json-default")
public class WXReactiveMessageAction extends ParentAction {

	@Override
	public boolean isPublic() {
		// TODO Auto-generated method stub
		return false;
	}

	@Autowired
	private WXReactiveMessageService reactiveMessageService;
	@Autowired
	private StatusService statusService;

	@Action(value = "info", results = { @Result(name = StrutsResMSG.SUCCESS, location = "/wx/admin/seting/wxreactivemessage/info.jsp") })
	public String info() {
		setPageInfo();
		return StrutsResMSG.SUCCESS;
	}

	@Action(value = "addInfo", results = { @Result(name = StrutsResMSG.SUCCESS, location = "/wx/admin/seting/wxreactivemessage/add.jsp") })
	public String addInfo() {
		setStatusList();
		return StrutsResMSG.SUCCESS;
	}

	@Action(value = "modifyInfo", results = { @Result(name = StrutsResMSG.SUCCESS, location = "/wx/admin/seting/wxreactivemessage/modify.jsp") })
	public String modifyInfo() throws JSONException {
		setStatusList();
		this.query = reactiveMessageService.findByID(this.query
				.getReactiveMessage().getId().toString());
		return StrutsResMSG.SUCCESS;
	}

	@Action(value = "add", results = { @Result(name = StrutsResMSG.SUCCESS, type = "plainText") })
	public String add() throws IOException, JSONException {
		this.query.setCreateStaff(super.getCurrentStaff());
		int res = reactiveMessageService.save(this.query, this.wxmessagelist);
		if (res == 0) {
			super.success(null, AjaxResult.CALL_BACK_TYPE_CLOSECURRENT,
					"wxreactivemessageinfo", null);
			return null;
		} else if (res == 1) {
			super.error("所需参数不能为空");
			return null;
		}
		return null;
	}

	@Action(value = "modify", results = { @Result(name = StrutsResMSG.SUCCESS, type = "plainText") })
	public String modify() throws IOException, JSONException {
		int res = reactiveMessageService.modify(this.query,this.wxmessagelist);
		if (res == 0) {
			super.success(null, AjaxResult.CALL_BACK_TYPE_CLOSECURRENT,
					"wxreactivemessageinfo", null);
			return null;
		} else if (res == 1) {
			super.error("必须制定一个微信用户");
			return null;
		} else if (res == 2) {
			super.error("图文消息的图片数量最多只能有一张");
			return null;
		} else if (res == 3) {
			super.error("图文消息必须有一张图片");
			return null;
		}
		return null;
	}

	@Action(value = "del", results = { @Result(name = StrutsResMSG.SUCCESS, type = "plainText") })
	public String del() throws IOException, JSONException {
		int res = reactiveMessageService.delete(this.query);
		if (res == 0) {
			try {
				super.success(null, null, "wxreactivemessageinfo", null);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				super.error("系统内部错误");
			}
			return null;
		} else if (res == 1) {
			super.error("必须制定一个微信用户");
			return null;
		}
		return null;
	}

	private void setPageInfo() {
		this.voList = this.reactiveMessageService.voList(query,
				super.getPageNum(), super.getNumPerPage());
		super.totalCount = this.reactiveMessageService.getCount(query);
	}

	private WXReactiveMessageVO query;

	private List<WXReactiveMessageVO> voList;

	private List<Status> statusList;

	private Map<String, WXMessageVO> wxmessagelist;

	private void setStatusList() {
		this.statusList = statusService
				.find(WXReactiveMessage.class, "msgType");
	}

	public List<WXReactiveMessageVO> getVoList() {
		return voList;
	}

	public List<Status> getStatusList() {
		return statusList;
	}

	public void setVoList(List<WXReactiveMessageVO> voList) {
		this.voList = voList;
	}

	public void setStatusList(List<Status> statusList) {
		this.statusList = statusList;
	}

	public Map<String, WXMessageVO> getWxmessagelist() {
		return wxmessagelist;
	}

	public void setWxmessagelist(Map<String, WXMessageVO> wxmessagelist) {
		this.wxmessagelist = wxmessagelist;
	}

	public WXReactiveMessageVO getQuery() {
		return query;
	}

	public void setQuery(WXReactiveMessageVO query) {
		this.query = query;
	}

}
