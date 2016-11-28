package com.xnradmin.client.action.farmers;

import java.io.IOException;
import java.util.List;

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
import com.xnradmin.constant.StrutsResMSG;
import com.xnradmin.core.action.ParentAction;
import com.xnradmin.core.service.business.commodity.BusinessGoodsService;
import com.xnradmin.po.business.BusinessGoods;
import com.xnradmin.po.wx.connect.Farmer;
import com.xnradmin.po.wx.connect.FarmerImage;

@Controller
@Scope("prototype")
@Namespace("/page/wx/farmer")
@ParentPackage("json-default")
public class farmerAction extends ParentAction{
	
	private Farmer query;
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
	private String status;
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
		farmerImages = farmerImageService.findFarmerImage(farmerId, goodsId);
		return StrutsResMSG.SUCCESS;
	}
	@Action(value="examine",results = { @Result(name = StrutsResMSG.SUCCESS, type = "plainText")})
	public String examine()
	{
		try {
			farmerService.examineUser(farmerId,status);
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
