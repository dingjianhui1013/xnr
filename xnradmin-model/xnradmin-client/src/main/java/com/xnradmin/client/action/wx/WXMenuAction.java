/**
 *2014年8月23日 下午3:07:14
 */
package com.xnradmin.client.action.wx;

import java.io.IOException;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.cntinker.util.StringHelper;
import com.xnradmin.client.service.wx.WXMenuService;
import com.xnradmin.client.service.wx.WXUserService;
import com.xnradmin.constant.AjaxResult;
import com.xnradmin.constant.StrutsResMSG;
import com.xnradmin.core.action.ParentAction;
import com.xnradmin.core.service.common.status.StatusService;
import com.xnradmin.po.common.status.Status;
import com.xnradmin.po.wx.WXMenu;
import com.xnradmin.vo.client.wx.WXMenuVO;
import com.xnradmin.vo.client.wx.WXUserVO;

/**
 * @author: liubin
 * 
 */
@Controller
@Scope("prototype")
@Namespace("/page/wx/wxmenu")
@ParentPackage("json-default")
public class WXMenuAction extends ParentAction {

	@Override
	public boolean isPublic() {
		// TODO Auto-generated method stub
		return false;
	}

	private WXMenuVO query;

	private List<WXMenuVO> voList;

	private WXUserVO wxuservo;

	@Autowired
	private WXMenuService wxMenuService;
	@Autowired
	private WXUserService wxuserService;
	@Autowired
	private StatusService statusService;

	private List<Status> statusList;

	@Action(value = "info", results = { @Result(name = StrutsResMSG.SUCCESS, location = "/wx/admin/seting/wxmenu/info.jsp") })
	public String info() {
		setPageInfo();
		return StrutsResMSG.SUCCESS;
	}

	@Action(value = "lookup", results = { @Result(name = StrutsResMSG.SUCCESS, location = "/wx/admin/seting/wxmenu/lookup.jsp") })
	public String lookup() {
		setPageInfo();
		return StrutsResMSG.SUCCESS;
	}

	@Action(value = "addInfo", results = { @Result(name = StrutsResMSG.SUCCESS, location = "/wx/admin/seting/wxmenu/add.jsp") })
	public String addInfo() {
		setStatusList();
		return StrutsResMSG.SUCCESS;
	}

	@Action(value = "modifyInfo", results = { @Result(name = StrutsResMSG.SUCCESS, location = "/wx/admin/seting/wxmenu/modify.jsp") })
	public String modifyInfo() {
		setStatusList();
		this.query = wxMenuService.findByid(this.query.getMenu().getId()
				.toString());
		return StrutsResMSG.SUCCESS;
	}

	@Action(value = "add", results = { @Result(name = StrutsResMSG.SUCCESS, type = "plainText") })
	public String add() throws IOException {
		if (query == null || this.wxuservo == null
				|| StringHelper.isNull(this.wxuservo.getWxuserid())) {
			try {
				super.error("内部错误");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
		this.query.setWxuservo(this.wxuservo);
		this.query.setCreateStaff(super.getCurrentStaff());
		int res = wxMenuService.save(query);
		try {
			if (res == 0) {
				super.success(null, AjaxResult.CALL_BACK_TYPE_CLOSECURRENT,
						"wxmenuinfo", null);
				return null;
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			super.error("系统内部错误");
		}
		return null;
	}

	@Action(value = "modify", results = { @Result(name = StrutsResMSG.SUCCESS, type = "plainText") })
	public String modify() throws IOException {
		if (query == null || this.wxuservo == null
				|| StringHelper.isNull(this.wxuservo.getWxuserid())) {
			try {
				super.error("内部错误");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
		this.query.setWxuservo(this.wxuservo);
		this.query.setModifyStaff(super.getCurrentStaff());
		int res = wxMenuService.modify(query);
		try {
			if (res == 0) {
				super.success(null, AjaxResult.CALL_BACK_TYPE_CLOSECURRENT,
						"wxmenuinfo", null);
				return null;
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			super.error("系统内部错误");
		}
		return null;
	}

	@Action(value = "del", results = { @Result(name = StrutsResMSG.SUCCESS, type = "plainText") })
	public String del() throws IOException {
		int res = wxMenuService.delete(query);
		try {
			if (res == 0) {
				super.success(null, null, "wxmenuinfo", null);
				return null;
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			super.error("系统内部错误");
		}
		return null;
	}

	/**
	 * 在wxuser/info下调用
	 * 
	 * @return
	 * @throws IOException
	 * @throws JSONException
	 */
	@Action(value = "reload", results = { @Result(name = StrutsResMSG.SUCCESS, type = "plainText") })
	public String reload() throws IOException, JSONException {
		String res = wxMenuService.reload(this.query.getWxuservo().getWxuser()
				.getId().toString());
		JSONObject obj = new JSONObject(res);
		super.success(res, null, "wxuserInfo", null);

		return null;
	}

	private void setStatusList() {
		this.statusList = statusService.find(WXMenu.class, "wxmenutype");
	}

	private void setPageInfo() {
		setStatusList();
		if (this.wxuservo != null
				&& !StringHelper.isNull(wxuservo.getWxuserid())) {
			if (this.query.getMenu() == null) {
				WXMenu m = new WXMenu();
				m.setWxuserid(new Long(wxuservo.getWxuserid()));
			}
		}
		this.voList = this.wxMenuService.getList(query, super.getPageNum(),
				super.getNumPerPage());
		super.totalCount = this.wxMenuService.getCount(query);
	}

	public WXMenuVO getQuery() {
		return query;
	}

	public List<WXMenuVO> getVoList() {
		return voList;
	}

	public List<Status> getStatusList() {
		return statusList;
	}

	public void setQuery(WXMenuVO query) {
		this.query = query;
	}

	public void setVoList(List<WXMenuVO> voList) {
		this.voList = voList;
	}

	public void setStatusList(List<Status> statusList) {
		this.statusList = statusList;
	}

	public WXUserVO getWxuservo() {
		return wxuservo;
	}

	public void setWxuservo(WXUserVO wxuservo) {
		this.wxuservo = wxuservo;
	}

}
