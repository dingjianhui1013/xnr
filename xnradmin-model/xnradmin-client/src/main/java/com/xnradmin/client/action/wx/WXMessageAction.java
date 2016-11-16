/**
 *2014年8月31日 下午3:52:10
 */
package com.xnradmin.client.action.wx;

import java.io.IOException;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.xnradmin.client.service.wx.WXMessageService;
import com.xnradmin.client.service.wx.WXUserService;
import com.xnradmin.constant.AjaxResult;
import com.xnradmin.constant.StrutsResMSG;
import com.xnradmin.core.action.ParentAction;
import com.xnradmin.core.service.StaffService;
import com.xnradmin.core.service.common.status.StatusService;
import com.xnradmin.po.common.status.Status;
import com.xnradmin.po.wx.WXMessage;
import com.xnradmin.vo.client.wx.WXMessageVO;

/**
 * @author: liubin
 * 
 */
@Controller
@Scope("prototype")
@Namespace("/page/wx/wxmessage")
@ParentPackage("json-default")
public class WXMessageAction extends ParentAction {

	@Override
	public boolean isPublic() {
		// TODO Auto-generated method stub
		return false;
	}

	@Autowired
	private WXMessageService wxMessageService;
	@Autowired
	private StaffService staffService;
	@Autowired
	private WXUserService wxuserService;
	@Autowired
	private StatusService statusService;

	private WXMessageVO query;

	private List<WXMessageVO> voList;

	private List<Status> statusList;

	@Action(value = "info", results = { @Result(name = StrutsResMSG.SUCCESS, location = "/wx/admin/seting/wxmessage/info.jsp") })
	public String info() {
		setPageInfo();
		return StrutsResMSG.SUCCESS;
	}
	
	@Action(value = "view", results = { @Result(name = StrutsResMSG.SUCCESS, location = "/wx/admin/seting/wxmessage/view.jsp") })
	public String view(){
		this.query = this.wxMessageService.findByIdVO(this.query.getWxmessage().getId().toString());
		return StrutsResMSG.SUCCESS;
	}

	@Action(value = "addInfo", results = { @Result(name = StrutsResMSG.SUCCESS, location = "/wx/admin/seting/wxmessage/add.jsp") })
	public String addInfo() {
		setStatusList();
		return StrutsResMSG.SUCCESS;
	}
	
	@Action(value = "lookup", results = { @Result(name = StrutsResMSG.SUCCESS, location = "/wx/admin/seting/wxmessage/lookup.jsp") })
	public String lookup(){
		setPageInfo();
		return StrutsResMSG.SUCCESS;
	}

	@Action(value = "modifyInfo", results = { @Result(name = StrutsResMSG.SUCCESS, location = "/wx/admin/seting/wxmessage/modify.jsp") })
	public String modifyInfo() {
		setStatusList();
		this.query = wxMessageService.findByIdVO(this.query.getWxmessage()
				.getId().toString());
		return StrutsResMSG.SUCCESS;
	}

	@Action(value = "del", results = { @Result(name = StrutsResMSG.SUCCESS, type = "plainText") })
	public String del() throws IOException {
		int res = wxMessageService.delete(this.query);
		if (res == 0) {
			try {
				super.success(null, null, "wxmessageinfo", null);
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

	@Action(value = "add", results = { @Result(name = StrutsResMSG.SUCCESS, type = "plainText") })
	public String add() throws IOException {
		this.query.setCreateStaff(super.getCurrentStaff());

		int res = wxMessageService.save(this.query);
		if (res == 0) {
			super.success(null, AjaxResult.CALL_BACK_TYPE_CLOSECURRENT,
					"wxmessageinfo", null);
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

	@Action(value = "modify", results = { @Result(name = StrutsResMSG.SUCCESS, type = "plainText") })
	public String modify() throws IOException {
		int res = wxMessageService.modify(this.query);
		if (res == 0) {
			super.success(null, AjaxResult.CALL_BACK_TYPE_CLOSECURRENT,
					"wxmessageinfo", null);
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

	private void setStatusList() {
		this.statusList = statusService.find(WXMessage.class, "msgType");
	}

	private void setPageInfo() {
		setStatusList();
		this.voList = this.wxMessageService.voList(query, super.getPageNum(),
				super.getNumPerPage());
		super.totalCount = this.wxMessageService.getCount(query);
	}

	public WXMessageVO getQuery() {
		return query;
	}

	public List<WXMessageVO> getVoList() {
		return voList;
	}

	public void setQuery(WXMessageVO query) {
		this.query = query;
	}

	public void setVoList(List<WXMessageVO> voList) {
		this.voList = voList;
	}

	public List<Status> getStatusList() {
		return statusList;
	}

	public void setStatusList(List<Status> statusList) {
		this.statusList = statusList;
	}

}
