package com.xnradmin.client.action.wx;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xnradmin.client.service.wx.FarmerService;
import com.xnradmin.client.service.wx.OutPlanService;
import com.xnradmin.client.service.wx.WXGetTokenService;
import com.xnradmin.client.service.wx.WeixinUtil;
import com.xnradmin.constant.AjaxResult;
import com.xnradmin.constant.StrutsResMSG;
import com.xnradmin.core.action.ParentAction;
import com.xnradmin.core.service.business.commodity.BusinessGoodsService;
import com.xnradmin.po.CommonPermissionMenuRelation;
import com.xnradmin.po.CommonStaff;
import com.xnradmin.po.business.BusinessCategory;
import com.xnradmin.po.business.BusinessGoods;
import com.xnradmin.po.business.BusinessWeight;
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
	private String examineId;//审核通过
	private String examineNoId;//审核不通过
	private String remarks;//拒绝理由
	private List<BusinessGoods> goodslist;
	private List<BusinessCategory> businesCategorys;
	private String businesCategoryId;
	private boolean examineStatus;//审核结果 json
	private OutPlanVO query;//后台查询条件
	private List<OutPlanVO> voList;//后台列表
	private String weightId;
	private BusinessWeight businessWeight;
	
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getExamineNoId() {
		return examineNoId;
	}
	public void setExamineNoId(String examineNoId) {
		this.examineNoId = examineNoId;
	}
	public boolean getExamineStatus() {
		return examineStatus;
	}
	public void setExamineStatus(boolean examineStatus) {
		this.examineStatus = examineStatus;
	}
	public OutPlanVO getQuery() {
		return query;
	}
	public void setQuery(OutPlanVO query) {
		this.query = query;
	}
	
	
	public String getExamineId() {
		return examineId;
	}
	public void setExamineId(String examineId) {
		this.examineId = examineId;
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
	public String getWeightId() {
		return weightId;
	}
	public void setWeightId(String weightId) {
		this.weightId = weightId;
	}
	public BusinessWeight getBusinessWeight() {
		return businessWeight;
	}
	public void setBusinessWeight(BusinessWeight businessWeight) {
		this.businessWeight = businessWeight;
	}
	
	@Autowired
	private OutPlanService outPlanService ;
	@Autowired
	private WXConnectAction wxconnectaction;
	@Autowired
	private FarmerService farmerService;
	@Autowired
	private BusinessGoodsService businessGoodsService;
	@Action(value = "outplan",results = { @Result(name = StrutsResMSG.SUCCESS, location = "/wx/admin/seting/outplan/outplan.jsp") })
	public String outplan(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String code = request.getParameter("code");
		String access_tokenString = WXGetTokenService.accessTokenIsOvertime();
		JSONObject userId = WeixinUtil.httpRequest(
				WXurl.WX_USERID_URL.replace("ACCESS_TOKEN", access_tokenString)
						.replace("CODE", code), "GET", null);
		this.userId = userId.getString("UserId");
		String types = farmerService.getFenleiByUserId(userId.getString("UserId"));
        goodslist = businessGoodsService.getTypeNameById(types);
		return StrutsResMSG.SUCCESS;
	}
	@Action(value="getGoods",results = {@Result(name = StrutsResMSG.SUCCESS, type="json")})
	public String getGoods()
	{
		goodslist = outPlanService.getGoodsList(businesCategoryId);
		return StrutsResMSG.SUCCESS;
	}
	
	@Action(value="getWeight",results = {@Result(name = StrutsResMSG.SUCCESS, type="json")})
	public String gerWeight()
	{
		businessWeight = outPlanService.getWeight(weightId);
		return StrutsResMSG.SUCCESS;
	}
	
	@Action(value = "save",results = { @Result(name = StrutsResMSG.SUCCESS, type="redirect",location = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx9eb4133bf836c7ae&redirect_uri=http%3a%2f%2fweixin.robustsoft.cn%2fxnr%2fpage%2fwx%2fpersonalCenter%2flist.action&response_type=code&scope=SCOPE&state=STATE#wechat_redirect") })
	public String save(){
		outplan.setCreateBy(outplan.getUserId());
		outplan.setExamine(0);//待审核
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
		OutPlanVO outPlanVO = outPlanService.getById(eidtId);
		String types = farmerService.getFenleiByUserId(outPlanVO.getOutPlan().getUserId());
        goodslist = businessGoodsService.getTypeNameById(types);
		ServletActionContext.getRequest().setAttribute("outPlanVO",  outPlanVO);
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
	/**
	 * 生产计划审核通过
	 * @return
	 */
	@Action(value = "examine",results = {@Result(name = StrutsResMSG.SUCCESS,type="json")})
	public String examine() {
		Integer personId = super.getCurrentStaff().getId();
		this.examineStatus  = outPlanService.examine(examineId,personId.toString());
		outPlanService.examineRelease(examineId);
		 return  StrutsResMSG.SUCCESS;
	}
	/**
	 * 带信息去拒绝页面
	 * @return
	 */
	@Action(value = "examineNo", results = { @Result(name = StrutsResMSG.SUCCESS, location = "/business/admin/outPlan/NoPage.jsp") })
	public String examineNo(){
		 return  StrutsResMSG.SUCCESS;
	}
	/**
	 * 审核拒绝
	 * 
	 * @return String
	 * @throws JSONException
	 * @throws IOException
	 */
	@Action(value = "saveExamineNo", results = { @Result(name = StrutsResMSG.SUCCESS, type = "plainText") })
	public String saveExamineNo() throws JSONException, IOException {
		Integer personId = super.getCurrentStaff().getId();
		outPlanService.examineNo(this.examineNoId,this.remarks,personId.toString());
		super.success(null, AjaxResult.CALL_BACK_TYPE_CLOSECURRENT, "outPlanManagement",null);
		outPlanService.examineRelease(examineNoId);
		return null;
	}
	@Override
	public boolean isPublic() {
		// TODO Auto-generated method stub
		return true;
	}
	
	
}
