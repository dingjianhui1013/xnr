package com.xnradmin.client.action.wx;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.xnradmin.client.service.wx.OutPlanService;
import com.xnradmin.client.service.wx.WXGetTokenService;
import com.xnradmin.client.service.wx.WeixinUtil;
import com.xnradmin.constant.StrutsResMSG;
import com.xnradmin.core.action.ParentAction;
import com.xnradmin.po.business.BusinessCategory;
import com.xnradmin.po.business.BusinessGoods;
import com.xnradmin.po.wx.OutPlan;
import com.xnradmin.po.wx.connect.WXurl;
import com.xnradmin.vo.business.OutPlanVO;

@Controller
@Scope("prototype")
@Namespace("/page/wx/outplan")
@ParentPackage("json-default")
public class OutPlanAction extends ParentAction{
	
	private OutPlan outplan ;
	private String deleteId;
	private String eidtId;
	private List<BusinessGoods> goodslist;
	private List<BusinessCategory> businesCategorys;
	private String businesCategoryId;
	
	private OutPlanVO query;//后台查询条件
	private List<OutPlanVO> voList;//后台列表
	
	public OutPlanVO getQuery() {
		return query;
	}
	public void setQuery(OutPlanVO query) {
		this.query = query;
	}
	
	public List<OutPlanVO> getVoList() {
		return voList;
	}
	public void setVoList(List<OutPlanVO> voList) {
		this.voList = voList;
	}
	public String getEidtId() {
		return eidtId;
	}
	public void setEidtId(String eidtId) {
		this.eidtId = eidtId;
	}
	public OutPlan getOutplan() {
		return outplan;
	}
	public void setOutplan(OutPlan outplan) {
		this.outplan = outplan;
	}
	private String userId;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public List<BusinessCategory> getBusinesCategorys() {
		return businesCategorys;
	}
	public void setBusinesCategorys(List<BusinessCategory> businesCategorys) {
		this.businesCategorys = businesCategorys;
	}
	public String getDeleteId() {
		return deleteId;
	}
	public void setDeleteId(String deleteId) {
		this.deleteId = deleteId;
	}
	public String getBusinesCategoryId() {
		return businesCategoryId;
	}
	public void setBusinesCategoryId(String businesCategoryId) {
		this.businesCategoryId = businesCategoryId;
	}
	
	public List<BusinessGoods> getGoodslist() {
		return goodslist;
	}
	public void setGoodslist(List<BusinessGoods> goodslist) {
		this.goodslist = goodslist;
	}
	@Autowired
	private OutPlanService outPlanService ;
		
	@Action(value = "outplan",results = { @Result(name = StrutsResMSG.SUCCESS, location = "/wx/admin/seting/outplan/outplan.jsp") })
	public String outplan(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String code = request.getParameter("code");
		String access_tokenString = WXGetTokenService.accessTokenIsOvertime();
		JSONObject userId = WeixinUtil.httpRequest(
				WXurl.WX_USERID_URL.replace("ACCESS_TOKEN", access_tokenString)
						.replace("CODE", code), "GET", null);
		this.userId = userId.getString("UserId");
		businesCategorys = outPlanService.getBusinessCategoryS();
		return StrutsResMSG.SUCCESS;
	}
	@Action(value="getGoods",results = {@Result(name = StrutsResMSG.SUCCESS, type="json")})
	public String getGoods()
	{
		goodslist = outPlanService.getGoodsList(businesCategoryId);
		return StrutsResMSG.SUCCESS;
	}
	
	@Action(value = "save",results = { @Result(name = StrutsResMSG.SUCCESS, type="redirect",location = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx9eb4133bf836c7ae&redirect_uri=http%3a%2f%2fweixin.robustsoft.cn%2fxnr%2fpage%2fwx%2fpersonalCenter%2flist.action&response_type=code&scope=SCOPE&state=STATE#wechat_redirect") })
	public String save(){
		outPlanService.save(outplan);
		return StrutsResMSG.SUCCESS;
	}
	@Action(value = "deletePlan",results = { @Result(name = StrutsResMSG.SUCCESS, type="redirect",location = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx9eb4133bf836c7ae&redirect_uri=http%3a%2f%2fweixin.robustsoft.cn%2fxnr%2fpage%2fwx%2fpersonalCenter%2flist.action&response_type=code&scope=SCOPE&state=STATE#wechat_redirect") })
	public String delete(){
		outPlanService.delete(deleteId);
		return StrutsResMSG.SUCCESS;
	}
	@Action(value = "editPlanForm",results = { @Result(name = StrutsResMSG.SUCCESS,location ="/wx/admin/seting/outplan/planEdit.jsp" ) })//  
	public String eidtForm(){
		OutPlan outPlan = outPlanService.findById(eidtId);
		ServletActionContext.getRequest().setAttribute("outPlan",  outPlan);
		return StrutsResMSG.SUCCESS;
	}
	@Action(value = "saveEdit",results = { @Result(name = StrutsResMSG.SUCCESS, type="redirect",location ="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx9eb4133bf836c7ae&redirect_uri=http%3a%2f%2fweixin.robustsoft.cn%2fxnr%2fpage%2fwx%2fpersonalCenter%2flist.action&response_type=code&scope=SCOPE&state=STATE#wechat_redirect" ) })
	public String saveEdit(){
		outPlanService.saveEdit(outplan);
		return StrutsResMSG.SUCCESS;
	}
	/**
	 * 后台信息
	 * @return
	 */
	@Action(value = "info", results = { @Result(name = StrutsResMSG.SUCCESS, location = "/business/admin/outPlan/info.jsp") })
	public String info() {
		setPageInfo();
		return StrutsResMSG.SUCCESS;
	}
	
	private void setPageInfo() {
		this.voList = this.outPlanService.getList(query, super.getPageNum(),super.getNumPerPage());
		super.totalCount = this.outPlanService.getCount(query);
	}
	
	@Override
	public boolean isPublic() {
		// TODO Auto-generated method stub
		return false;
	}
	
	
}
