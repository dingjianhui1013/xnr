/**
 *2014年8月15日 下午4:56:21
 */
package com.xnradmin.client.action.wx;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.xnradmin.client.service.wx.WXAccessTokenService;
import com.xnradmin.client.service.wx.WXUserService;
import com.xnradmin.constant.AjaxResult;
import com.xnradmin.constant.StrutsResMSG;
import com.xnradmin.core.action.ParentAction;
import com.xnradmin.core.service.common.status.StatusService;
import com.xnradmin.po.CommonStaff;
import com.xnradmin.po.common.status.Status;
import com.xnradmin.po.wx.WXUser;
import com.xnradmin.vo.client.wx.WXUserVO;

/**
 * @author: liubin
 * 
 */
@Controller
@Scope("prototype")
@Namespace("/page/wx/wxuser")
@ParentPackage("json-default")
public class WXUserAction extends ParentAction {
	@Autowired
	private WXUserService service;

	@Autowired
	private StatusService statusService;
	
	@Autowired
	private WXAccessTokenService accessTokenService;

	private WXUserVO query;

	private List<Status> statusList;
	private List<WXUserVO> voList;
	
	private CommonStaff staff;
	@Override
	public boolean isPublic() {
		// TODO Auto-generated method stub
		return false;
	}

	@Action(value = "info", results = { @Result(name = StrutsResMSG.SUCCESS, location = "/wx/admin/seting/wxuser/info.jsp") })
	public String info() {
		System.out.println("ceshi");
		setPageInfo();
		return StrutsResMSG.SUCCESS;
	}

	@Action(value = "view", results = { @Result(name = StrutsResMSG.SUCCESS, location = "/wx/admin/seting/wxuser/view.jsp") })
	public String view() {
		this.query = service.findByidVO(this.query.getWxuser().getId()
				.toString());
		return StrutsResMSG.SUCCESS;
	}

	@Action(value = "addInfo", results = { @Result(name = StrutsResMSG.SUCCESS, location = "/wx/admin/seting/wxuser/add.jsp") })
	public String addInfo() {
		setPageInfo();
		return StrutsResMSG.SUCCESS;
	}

	@Action(value = "modifyInfo", results = { @Result(name = StrutsResMSG.SUCCESS, location = "/wx/admin/seting/wxuser/modify.jsp") })
	public String modifyInfo() {
		setStatusList();
		this.query = service.findByidVO(query.getWxuser().getId().toString());
		return StrutsResMSG.SUCCESS;
	}

	@Action(value = "lookup", results = { @Result(name = StrutsResMSG.SUCCESS, location = "/wx/admin/seting/wxuser/lookup.jsp") })
	public String lookup() {
		setPageInfo();
		return StrutsResMSG.SUCCESS;
	}
	
	@Action(value = "reload", results = { @Result(name = StrutsResMSG.SUCCESS, type = "plainText") })
	public String reload() throws IOException {
		accessTokenService.reloadTokenByWXUserid(this.query.getWxuser().getId());
		super.success("重载成功",null,"wxuserInfo",null);
		return null;
	}

	@Action(value = "modify", results = { @Result(name = StrutsResMSG.SUCCESS, type = "plainText") })
	public String modify() throws IOException {
		int res = this.service.modify(this.query, super.getCurrentStaff());
		try {
			if (res == 0) {
				super.success(null, AjaxResult.CALL_BACK_TYPE_CLOSECURRENT,
						"wxuserInfo", null);
				return null;
			} else if (res == 1) {
				super.error("已存在的appid");
				return null;
			} else if (res == 2) {
				super.error("微信用户已在");
				return null;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			super.error("系统内部错误");
		}
		return null;
	}

	@Action(value = "add", results = { @Result(name = StrutsResMSG.SUCCESS, type = "plainText") })
	public String add() throws IOException {

		if (query == null || query.getStaff() == null
				|| query.getStaff().getId() == null) {
			try {
				super.error("内部错误");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}

		WXUser wxuser = this.query.getWxuser();
		wxuser.setCreateTime(new Timestamp(System.currentTimeMillis()));
		wxuser.setCreateStaffId(super.getCurrentStaff().getId());
		wxuser.setStaffId(this.query.getStaff().getId());

		int res = service.save(wxuser);

		try {
			if (res == 0) {
				super.success(null, AjaxResult.CALL_BACK_TYPE_CLOSECURRENT,
						"wxuserInfo", null);
				return null;
			} else if (res == 1) {
				super.error("已存在的appid");
				return null;
			} else if (res == 2) {
				super.error("微信用户已在");
				return null;
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			super.error("系统内部错误");
		}
		return null;
	}

	private void setPageInfo() {
		setStatusList();
		if(this.staff!=null && this.staff.getId()!=null){
			this.query.setStaff(staff);
		}
		this.voList = this.service.list(query, super.getPageNum(),
				super.getNumPerPage());
		super.totalCount = this.service.getCount(query);
	}

	private void setStatusList() {
		this.statusList = statusService.find(WXUser.class, "status");
	}

	public List<Status> getStatusList() {
		return statusList;
	}

	public void setStatusList(List<Status> statusList) {
		this.statusList = statusList;
	}

	public WXUserVO getQuery() {
		return query;
	}

	public void setQuery(WXUserVO query) {
		this.query = query;
	}

	public List<WXUserVO> getVoList() {
		return voList;
	}

	public void setVoList(List<WXUserVO> voList) {
		this.voList = voList;
	}

	public CommonStaff getStaff() {
		return staff;
	}

	public void setStaff(CommonStaff staff) {
		this.staff = staff;
	}

}
