package com.xnradmin.client.action.wx;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.cntinker.util.StringHelper;
import com.xnradmin.client.service.wx.FarmerService;
import com.xnradmin.client.service.wx.OutPlanService;
import com.xnradmin.client.service.wx.WXGetTokenService;
import com.xnradmin.client.service.wx.WeixinUtil;
import com.xnradmin.constant.AjaxResult;
import com.xnradmin.constant.StrutsResMSG;
import com.xnradmin.core.action.ParentAction;
import com.xnradmin.core.service.business.commodity.BusinessGoodsService;
import com.xnradmin.po.business.BusinessCategory;
import com.xnradmin.po.business.BusinessGoods;
import com.xnradmin.po.business.BusinessWeight;
import com.xnradmin.po.wx.OutPlan;
import com.xnradmin.po.wx.connect.Farmer;
import com.xnradmin.po.wx.connect.WXInit;
import com.xnradmin.po.wx.connect.WXfInit;
import com.xnradmin.po.wx.connect.WXurl;
import com.xnradmin.vo.business.BusinessGoodsVO;
import com.xnradmin.vo.business.OutPlanVO;

@Controller
@Scope("prototype")
@Namespace("/page/wx/outplan")
@ParentPackage("json-default")
public class OutPlanAction extends ParentAction{
	private static Logger log = Logger.getLogger(OutPlanAction.class);
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
	private String status;
	private boolean outplanStatus;//验证生产计划时间是否重复
	private String startTime;//
	private String endTime;
	private String userId;
	
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public boolean isOutplanStatus() {
		return outplanStatus;
	}
	public void setOutplanStatus(boolean outplanStatus) {
		this.outplanStatus = outplanStatus;
	}
	
	private String goodsName;
	private String farmerName;
	
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
	
	
	
	
	
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public String getFarmerName() {
		return farmerName;
	}
	public void setFarmerName(String farmerName) {
		this.farmerName = farmerName;
	}
	@Autowired
	private OutPlanService outPlanService ;
	@Autowired
	private WXConnectAction wxconnectaction;
	@Autowired
	private FarmerService farmerService;
	@Autowired
	private BusinessGoodsService businessGoodsService;
	/**
	 * 企业号生产计划跳转
	 * @return
	 */
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
		this.status  = farmerService.getStatus(userId.getString("UserId"));
        goodslist = businessGoodsService.getTypeNameById(types);
		return StrutsResMSG.SUCCESS;
	}
	/***
	 * 服务号跳转生产计划
	 * @return
	 */
	@Action(value = "outplanF",results = { @Result(name = StrutsResMSG.SUCCESS, location = "/wx/admin/seting/outplan/outplanF.jsp") })
	public String outplanF(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String code = request.getParameter("code");
		JSONObject userId = WeixinUtil.httpRequest(
				WXurl.WXF_USERID_URL.replace("APPID", WXfInit.APPID).replace("SECRET", WXfInit.APPSECRET)
						.replace("CODE", code), "GET", null);
		this.userId = userId.getString("openid");
		String types = farmerService.getFenleiByUserId(userId.getString("openid"));
		this.status  = farmerService.getStatus(userId.getString("openid"));
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
	/**
	 * 企业号保存生产计划
	 * @return
	 */
	@Action(value = "save",results = { @Result(name = StrutsResMSG.SUCCESS, type="redirect",location = "https://open.weixin.qq.com/connect/oauth2/authorize?appid="+WXInit.CORPID+"&redirect_uri="+WXInit.SERVICEURL+"%2fxnr%2fpage%2fwx%2fpersonalCenter%2flist.action&response_type=code&scope=SCOPE&state=STATE#wechat_redirect") })
	public String save(){
		outplan.setCreateBy(outplan.getUserId());
		outplan.setExamine(0);//待审核
		outPlanService.save(outplan);
		return StrutsResMSG.SUCCESS;
	}
	/**
	 * 服务号保存生产计划
	 * @return
	 */
	@Action(value = "saveF",results = { @Result(name = StrutsResMSG.SUCCESS, type="redirect",location = "https://open.weixin.qq.com/connect/oauth2/authorize?appid="+WXfInit.APPID+"&redirect_uri="+WXfInit.SERVICEURL+"%2fxnr%2fpage%2fwx%2fpersonalCenter%2flistF.action&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect") })
	public String saveF(){
		outplan.setCreateBy(outplan.getUserId());
		outplan.setExamine(0);//待审核
		outPlanService.save(outplan);
		return StrutsResMSG.SUCCESS;
	}
	/**
	 * 企业号删除生产计划
	 * @return
	 */
	@Action(value = "deletePlan",results = { @Result(name = StrutsResMSG.SUCCESS, type="redirect",location = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx9eb4133bf836c7ae&redirect_uri="+WXInit.SERVICEURL+"%2fxnr%2fpage%2fwx%2fpersonalCenter%2flist.action&response_type=code&scope=SCOPE&state=STATE#wechat_redirect") })
	public String delete(){
		outPlanService.delete(deleteId);
		return StrutsResMSG.SUCCESS;
	}
	/**
	 * 服务号 删除生产计划
	 * @return
	 */
	@Action(value = "deletePlanF",results = { @Result(name = StrutsResMSG.SUCCESS, type="redirect",location = "https://open.weixin.qq.com/connect/oauth2/authorize?appid="+WXfInit.APPID+"&redirect_uri="+WXfInit.SERVICEURL+"%2fxnr%2fpage%2fwx%2fpersonalCenter%2flistF.action&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect") })
	public String deleteF(){
		outPlanService.delete(deleteId);
		return StrutsResMSG.SUCCESS;
	}
	/**
	 * 企业号 生产计划修改
	 * @return
	 */
	@Action(value = "editPlanForm",results = { @Result(name = StrutsResMSG.SUCCESS,location ="/wx/admin/seting/outplan/planEdit.jsp" ) })//  
	public String eidtForm(){
		OutPlanVO outPlanVO = outPlanService.getById(eidtId);
		String types = farmerService.getFenleiByUserId(outPlanVO.getOutPlan().getUserId());
        goodslist = businessGoodsService.getTypeNameById(types);
		ServletActionContext.getRequest().setAttribute("outPlanVO",  outPlanVO);
		return StrutsResMSG.SUCCESS;
	}
	/**
	 * 服务号修改生产计划
	 * @return
	 */
	@Action(value = "editPlanFormF",results = { @Result(name = StrutsResMSG.SUCCESS,location ="/wx/admin/seting/outplan/planEditF.jsp" ) })//  
	public String eidtFormF(){
		OutPlanVO outPlanVO = outPlanService.getById(eidtId);
		String types = farmerService.getFenleiByUserId(outPlanVO.getOutPlan().getUserId());
        goodslist = businessGoodsService.getTypeNameById(types);
		ServletActionContext.getRequest().setAttribute("outPlanVO",  outPlanVO);
		return StrutsResMSG.SUCCESS;
	}
	
	@Action(value = "saveEdit",results = { @Result(name = StrutsResMSG.SUCCESS, type="redirect",location ="https://open.weixin.qq.com/connect/oauth2/authorize?appid="+WXInit.CORPID+"&redirect_uri="+WXInit.SERVICEURL+"%2fxnr%2fpage%2fwx%2fpersonalCenter%2flist.action&response_type=code&scope=SCOPE&state=STATE#wechat_redirect" ) })
	public String saveEdit(){
		outPlanService.saveEdit(outplan);
		return StrutsResMSG.SUCCESS;
	}
	/***
	 * 服务号保存修改的生产计划
	 * @return
	 */
	@Action(value = "saveEditF",results = { @Result(name = StrutsResMSG.SUCCESS, type="redirect",location = "https://open.weixin.qq.com/connect/oauth2/authorize?appid="+WXfInit.APPID+"&redirect_uri="+WXfInit.SERVICEURL+"%2fxnr%2fpage%2fwx%2fpersonalCenter%2flistF.action&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect") })
	public String saveEditF(){
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
	 * 生产计划时间验证
	 * @return
	 */
	@Action(value = "validationDate",results = {@Result(name = StrutsResMSG.SUCCESS,type="json")})
	public String validationDate() {
		this.outplanStatus = outPlanService.validationDate(this.userId,this.startTime,this.endTime);
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
	
	
	
	/**
	 * 跳转到信息页。。
	 * 
	 * @return
	 */
	@Action(value = "lookup", results = {
			@Result(name = StrutsResMSG.SUCCESS, location = "/business/admin/outPlan/lookup.jsp"),
			@Result(name = StrutsResMSG.FAILED, location = "/business/admin/outPlan/lookup.jsp") })
	public String lookup() {
		setLookupPageInfo();
		return StrutsResMSG.SUCCESS;
	}
	
	
	
	
	private void setLookupPageInfo() {
		// 设置排序
		
		OutPlanVO vo = new OutPlanVO();
		Farmer farmer = new Farmer();
		BusinessGoods goods = new BusinessGoods();
		if (!StringHelper.isNull(goodsName)) {
			goods.setGoodsName(goodsName);
		}
		if (!StringHelper.isNull(farmerName)) {
			farmer.setUserName(farmerName);
		}
		
		vo.setFarmer(farmer);
		vo.setBusinessGoods(goods);
		

//		this.voList = outPlanService.listVO(vo, getPageNum(), getNumPerPage(),
//				orderField, orderDirection);
//		this.totalCount = outPlanService.getCount(vo);

	}
	
	
	
	
}
