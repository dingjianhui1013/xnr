package com.xnradmin.client.action.farmers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.xnradmin.client.service.wx.FarmerImageService;
import com.xnradmin.client.service.wx.FarmerService;
import com.xnradmin.constant.AjaxResult;
import com.xnradmin.constant.SessionConstant;
import com.xnradmin.constant.StrutsResMSG;
import com.xnradmin.core.action.ParentAction;
import com.xnradmin.core.dao.CommonDAO;
import com.xnradmin.core.service.business.commodity.BusinessGoodsService;
import com.xnradmin.core.util.AjaxUtil;
import com.xnradmin.po.business.BusinessGoods;
import com.xnradmin.po.wx.connect.Farmer;
import com.xnradmin.po.wx.connect.FarmerExamine;
import com.xnradmin.po.wx.connect.FarmerImage;

@Controller
@Scope("prototype")
@Namespace("/page/wx/farmer")
@ParentPackage("json-default")
public class farmerAction extends ParentAction{
	
	private Farmer query;
	private BusinessGoods goods;
	private List<Farmer> farmerList;
	String farmerId;
	String types;
	String[] fenleiById;
	List<BusinessGoods> allBusinessGoods;
	private String goodsId;
	private List<FarmerImage> farmerImages;
	@Autowired FarmerService farmerService;
	@Autowired BusinessGoodsService  businessGoodsService;
	@Autowired
	private FarmerImageService farmerImageService;
	@Autowired
	private CommonDAO commonDAO;
	private String status;
	private FarmerExamine farmerExamine;
	private String msg;
	private String remarks;
	public List<BusinessGoods> getAllBusinessGoods() {
		return allBusinessGoods;
	}
	public void setAllBusinessGoods(List<BusinessGoods> allBusinessGoods) {
		this.allBusinessGoods = allBusinessGoods;
	}
	public String[] getFenleiById() {
		return fenleiById;
	}
	public void setFenleiById(String[] fenleiById) {
		this.fenleiById = fenleiById;
	}
	public String getFarmerId() {
		return farmerId;
	}
	public void setFarmerId(String farmerId) {
		this.farmerId = farmerId;
	}
	public String getTypes() {
		return types;
	}
	public void setTypes(String types) {
		this.types = types;
	}
	public Farmer getQuery() {
		return query;
	}
	public void setQuery(Farmer query) {
		this.query = query;
	}
	public List<Farmer> getFarmerList() {
		return farmerList;
	}
	public void setFarmerList(List<Farmer> farmerList) {
		this.farmerList = farmerList;
	}
	public String getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}
	public List<FarmerImage> getFarmerImages() {
		return farmerImages;
	}
	public void setFarmerImages(List<FarmerImage> farmerImages) {
		this.farmerImages = farmerImages;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public FarmerExamine getFarmerExamine() {
		return farmerExamine;
	}
	public void setFarmerExamine(FarmerExamine farmerExamine) {
		this.farmerExamine = farmerExamine;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public BusinessGoods getGoods() {
		return goods;
	}
	public void setGoods(BusinessGoods goods) {
		this.goods = goods;
	}
	@Override
	public boolean isPublic() {
		return true;
	}
	
	@Action(value = "info", results = { @Result(name = StrutsResMSG.SUCCESS, location = "/business/admin/farmer/info.jsp") })
	public String info() {
		setPageInfo();
		return StrutsResMSG.SUCCESS;
	}
	
	private void setPageInfo() {
		this.farmerList = this.farmerService.getList(query, super.getPageNum(),
				super.getNumPerPage());
		super.totalCount = this.farmerService.getCount(query);
	}
	@Action(value="farmerExamine",results = {@Result(name = StrutsResMSG.SUCCESS, location = "/wx/admin/seting/examine/examine.jsp") })
	public String farmerExamine()
	{
		this.farmerId = farmerId;
		return StrutsResMSG.SUCCESS;
	}
	@Action(value="farmerExamineEdit",results = {@Result(name = StrutsResMSG.SUCCESS, location = "/wx/admin/seting/examine/examineEdit.jsp") })
	public String farmerExamineEdit()
	{
		this.farmerId = farmerId;
		List<FarmerExamine>  farmerExamines = farmerService.findExamineByUserId(farmerId);
		if(!farmerExamines.isEmpty())
		{
			farmerExamine = farmerExamines.get(0);
		}
		return StrutsResMSG.SUCCESS;
	}
	@Action(value="saveFarmerExamine",results = {@Result(name = StrutsResMSG.SUCCESS, location = "/wx/admin/seting/examine/examine.jsp") })
	public String saveFarmerExamine()
	{
		Farmer farmer = farmerService.getUserNameById(farmerExamine.getFarmerId());
		farmerService.examineUser(farmerExamine.getFarmerId(), "3");
		farmerService.examineRelease(farmerId, "3", remarks);
		commonDAO.save(farmerExamine);
		this.msg = "审核信息已提交，请等待！";
		return StrutsResMSG.SUCCESS;
	}
	@Action(value="updateFarmerExamine",results = {@Result(name = StrutsResMSG.SUCCESS, location = "/wx/admin/seting/examine/examineEdit.jsp") })
	public String updateFarmerExamine()
	{
		Farmer farmer = farmerService.getUserNameById(farmerExamine.getFarmerId());
		farmerService.examineUser(farmerExamine.getFarmerId(), "3");
		farmerService.examineRelease(farmerId, "3", remarks);
		farmerService.updateFarmerExamine(farmerExamine);
		this.msg = "审核信息已修改，请等待！";
		return StrutsResMSG.SUCCESS;
	}
	@Action(value="showExamine",results = {@Result(name=StrutsResMSG.SUCCESS,location = "/wx/admin/seting/examine/examineInfo.jsp")})
	public String showExamine()
	{
		List<FarmerExamine> farmerExamines = farmerService.findExamineByUserId(farmerId);
		if(!farmerExamines.isEmpty())
		{
			farmerExamine = farmerExamines.get(0);
			query = farmerService.getUserNameById(farmerExamine.getFarmerId());
		}
		return StrutsResMSG.SUCCESS;
	}
	/**
	 * 带信息到分类页面
	 * 
	 * @return String
	 */
	@Action(value = "anthinfo", results = {@Result(name = StrutsResMSG.SUCCESS, location = "/business/admin/farmer/anthinfo.jsp") })
	public String anthinfo() {
		this.fenleiById = farmerService.getFenleiById(farmerId);
		this.allBusinessGoods = businessGoodsService.listAll();
		return StrutsResMSG.SUCCESS;
	}
	/**
	 * 更新农户商品类型
	 * @return
	 * @throws JSONException
	 * @throws IOException
	 */
	@Action(value = "saveAnthinfo", results = { @Result(name = StrutsResMSG.SUCCESS, type = "plainText") })
	public String saveExamineNo() throws JSONException, IOException {
		farmerService.saveTypes(this.farmerId,this.types);
		super.success(null, AjaxResult.CALL_BACK_TYPE_CLOSECURRENT, "FarmerManagement",null);
		return null;
	}
	/**
	 * 用户扫描二维码展示农户菜品图片
	 * @return String
	 */
	@Action(value="showFarmerImage",results = {@Result(name=StrutsResMSG.SUCCESS,location="/business/admin/farmer/showFarmerIamge.jsp")})
	public String showFarmerImage()
	{
		query = farmerService.getUserNameById(farmerId);
		goods = businessGoodsService.findByid(goodsId);
		farmerImages = farmerImageService.findFarmerImage(farmerId, goodsId);
		return StrutsResMSG.SUCCESS;
	}
	@Action(value="examineNo",results = { @Result(name = StrutsResMSG.SUCCESS, location = "/business/admin/farmer/noPage.jsp")})
	public String examineNO()
	{
		this.farmerId = farmerId;
		this.status = status;
		return  StrutsResMSG.SUCCESS;
	}
	@Action(value="saveExamineNO",results = { @Result(name = StrutsResMSG.SUCCESS, type = "plainText")})
	public String saveExamineNO()
	{
		try {
			Integer personId =0;
			if(super.getCurrentStaff()==null)
			{
				HttpServletRequest request = ServletActionContext.getRequest();
		        HttpServletResponse response = ServletActionContext.getResponse();
		        HttpSession session = request.getSession();
		        Object obj = session.getAttribute(SessionConstant.SESSION_LOGIN_STAFF);
		        if(true){
		            if(obj == null){
		            	ServletActionContext.getResponse().setContentType(
		                        "text/html;charset=utf-8");
		                PrintWriter out = ServletActionContext.getResponse().getWriter();
		                out.print(AjaxUtil.getTimeout("登陆超时，请重新登陆"));
		                out.flush();
		                out.close();
		            }
		        }
			}else
			{
				personId = super.getCurrentStaff().getId();
				farmerService.examineUser(farmerId,status,remarks,personId);
				farmerService.examineRelease(farmerId, status, remarks);
				super.success(null, AjaxResult.CALL_BACK_TYPE_CLOSECURRENT, "FarmerManagement",null);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return StrutsResMSG.SUCCESS;
	}
	@Action(value="examine",results = { @Result(name = StrutsResMSG.SUCCESS, type = "plainText")})
	public String examine()
	{
		try {
			farmerService.examineUser(farmerId,status);
			farmerService.examineRelease(farmerId, status, remarks);
			super.success(null, null, "FarmerManagement",null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			try {
				super.error("系统异常");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		return StrutsResMSG.SUCCESS;
	}
	
	 
}
