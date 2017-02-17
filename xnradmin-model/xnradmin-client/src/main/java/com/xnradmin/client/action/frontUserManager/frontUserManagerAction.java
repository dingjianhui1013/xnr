package com.xnradmin.client.action.frontUserManager;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.cntinker.util.StringHelper;
import com.xnradmin.client.service.front.FrontUserService;
import com.xnradmin.constant.AjaxResult;
import com.xnradmin.constant.StrutsResMSG;
import com.xnradmin.core.action.ParentAction;
import com.xnradmin.po.business.BusinessWeight;
import com.xnradmin.po.front.FrontUser;

@Controller
@Scope("prototype")
@Namespace("/frontUserManager")
@ParentPackage("json-default")
public class frontUserManagerAction extends ParentAction{
	private FrontUser query;//查询条件
    private List<FrontUser> voList;
    private FrontUser addfrontUser;//添加用户
    private FrontUser updatefrontUser;//修改用户信息
    private String typeId;//审核通过
	private String typeNoId;//审核不通过
	private String resetId;//重置密码
	private boolean typeStatus;//审核结果 json
	private boolean resetStatus;//重置结果
    @Autowired
    private FrontUserService frontUserService;
    
    
    

	public boolean isResetStatus() {
		return resetStatus;
	}

	public void setResetStatus(boolean resetStatus) {
		this.resetStatus = resetStatus;
	}

	public String getResetId() {
		return resetId;
	}

	public void setResetId(String resetId) {
		this.resetId = resetId;
	}

	public boolean isTypeStatus() {
		return typeStatus;
	}

	public void setTypeStatus(boolean typeStatus) {
		this.typeStatus = typeStatus;
	}

	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	public String getTypeNoId() {
		return typeNoId;
	}

	public void setTypeNoId(String typeNoId) {
		this.typeNoId = typeNoId;
	}

	public FrontUser getQuery() {
		return query;
	}

	public void setQuery(FrontUser query) {
		this.query = query;
	}

	public List<FrontUser> getVoList() {
		return voList;
	}

	public void setVoList(List<FrontUser> voList) {
		this.voList = voList;
	}

	public FrontUser getAddfrontUser() {
		return addfrontUser;
	}

	public void setAddfrontUser(FrontUser addfrontUser) {
		this.addfrontUser = addfrontUser;
	}

	public FrontUser getUpdatefrontUser() {
		return updatefrontUser;
	}

	public void setUpdatefrontUser(FrontUser updatefrontUser) {
		this.updatefrontUser = updatefrontUser;
	}

	/**
	 * 跳转到信息页。。
	 * 
	 * @return
	 */
	@Action(value = "info", results = {
			@Result(name = StrutsResMSG.SUCCESS, location = "/business/admin/frontUserManager/info.jsp"),
			@Result(name = StrutsResMSG.FAILED, location = "/business/admin/frontUserManager/info.jsp") })
	public String info() {
		this.voList = frontUserService.findAll(query, super.getPageNum(),super.getNumPerPage());
		super.totalCount = this.frontUserService.getCount(query);
		
		return StrutsResMSG.SUCCESS;
	}
	/**
	 * 到添加页
	 * 
	 * @return String
	 */
	@Action(value = "addinfo", results = {
			@Result(name = StrutsResMSG.SUCCESS, location = "/business/admin/frontUserManager/add.jsp"),
			@Result(name = StrutsResMSG.FAILED, location = "/business/admin/frontUserManager/add.jsp") })
	public String addinfo() {
		return StrutsResMSG.SUCCESS;
	}
	/**
	 * 保存对象接口
	 * 
	 * @return String
	 * @throws Exception
	 */
	@Action(value = "add", results = { @Result(name = StrutsResMSG.SUCCESS, type = "plainText") })
	public String add() throws Exception {
		frontUserService.save(this.addfrontUser);
		super.success(null, AjaxResult.CALL_BACK_TYPE_CLOSECURRENT, "frontUserManager",
				null);
		return null;
	}

	/**
	 * 带信息到修改页
	 * 
	 * @return String
	 */
	@Action(value = "modifyinfo", results = {
			@Result(name = StrutsResMSG.SUCCESS, location = "/business/admin/frontUserManager/modify.jsp"),
			@Result(name = StrutsResMSG.FAILED, location = "/business/admin/frontUserManager/modify.jsp") })
	public String modifyinfo() {
		this.updatefrontUser = frontUserService.findByid(updatefrontUser.getId().toString());
		return StrutsResMSG.SUCCESS;
	}

	/**
	 * 更新对象接口
	 * 
	 * @return String
	 * @throws Exception
	 */
	@Action(value = "modify", results = { @Result(name = StrutsResMSG.SUCCESS, type = "plainText") })
	public String modify() throws Exception {
		boolean modify = this.frontUserService.modify(this.updatefrontUser);
		if(modify){
			super.success(null, AjaxResult.CALL_BACK_TYPE_CLOSECURRENT, "frontUserManager",
					null);
		}
		return null;
	}

	@Action(value = "del", results = { @Result(name = StrutsResMSG.SUCCESS, type = "plainText") })
	public String del() throws IOException, JSONException {
		boolean delete = frontUserService.delete(updatefrontUser.getId().toString());
		if(delete){
			super.success(null,null, "frontUserManager",
					null);
		}
		return null;
	}
	/**
	 * 审核通过
	 * @return
	 */
	@Action(value = "type",results = {@Result(name = StrutsResMSG.SUCCESS,type="json")})
	public String type() {
		this.typeStatus = frontUserService.type(typeId);
		 return  StrutsResMSG.SUCCESS;
	}
	/**
	 * 审核拒绝
	 * @return
	 */
	@Action(value = "typeNo",results = {@Result(name = StrutsResMSG.SUCCESS,type="json")})
	public String typeNo() {
		this.typeStatus = frontUserService.typeNo(typeNoId);
		 return  StrutsResMSG.SUCCESS;
	}
	/**
	 * 重置密码
	 * @return
	 */
	@Action(value = "reset",results = {@Result(name = StrutsResMSG.SUCCESS,type="json")})
	public String reset() {
		this.resetStatus= frontUserService.reset(resetId);
		 return  StrutsResMSG.SUCCESS;
	}
	@Override
	public boolean isPublic() {
		// TODO Auto-generated method stub
		return false;
	}
}
