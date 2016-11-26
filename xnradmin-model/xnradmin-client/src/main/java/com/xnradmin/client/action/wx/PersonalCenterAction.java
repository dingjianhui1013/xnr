package com.xnradmin.client.action.wx;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.xnradmin.client.service.wx.FarmerImageService;
import com.xnradmin.client.service.wx.FarmerService;
import com.xnradmin.client.service.wx.OutPlanService;
import com.xnradmin.client.service.wx.WXGetTokenService;
import com.xnradmin.client.service.wx.WeixinUtil;
import com.xnradmin.constant.StrutsResMSG;
import com.xnradmin.core.service.business.commodity.BusinessGoodsService;
import com.xnradmin.po.wx.OutPlan;
import com.xnradmin.po.wx.connect.WXfInit;
import com.xnradmin.po.wx.connect.WXurl;
import com.xnradmin.vo.business.OutPlanVO;

@Controller
@Scope("prototype")
@Namespace("/page/wx/personalCenter")
@ParentPackage("json-default")
public class PersonalCenterAction {
	
	private static Logger log = Logger.getLogger(PersonalCenterAction.class);
	@Autowired
	private OutPlanService outPlanService ;
	@Autowired
	private FarmerImageService farmerImageService ;
	@Autowired
	private BusinessGoodsService businessGoodsService;
	@Autowired 
	private FarmerService farmerService;
	private String imageUrl;
	private String status;
	private String imageid;
	
	public String getImageid() {
		return imageid;
	}
	public void setImageid(String imageid) {
		this.imageid = imageid;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	/***
	 * 企业号个人中心跳转
	 * @return
	 */
	@Action(value = "list",results = { @Result(name = StrutsResMSG.SUCCESS, location = "/wx/admin/seting/personalCenter/personalCenter.jsp") })
	public String personalCenter(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String code = request.getParameter("code");
		String access_tokenString = WXGetTokenService.accessTokenIsOvertime();
		JSONObject userId = WeixinUtil.httpRequest(
				WXurl.WX_USERID_URL.replace("ACCESS_TOKEN", access_tokenString)
						.replace("CODE", code), "GET", null);
		List<OutPlanVO> outplans = outPlanService.getListByUserId(userId.getString("UserId"),0,0);
		ServletActionContext.getRequest().setAttribute("outplans", outplans);
		List<Map<String, List<Map<String, List<String>>>>> date_type_images = new ArrayList<Map<String,List<Map<String,List<String>>>>>();
		List<String> imagedates = farmerImageService.getImageDates(userId.getString("UserId"));
		this.status  = farmerService.getStatus(userId.getString("UserId"));
		for (String images : imagedates) {
			Map<String, List<Map<String, List<String>>>> date_type_image = new HashMap<String, List<Map<String, List<String>>>>();
			Map<String, List<String>> type_images = new HashMap<String, List<String>>();
			List<Map<String, List<String>>> type_imagesList= new ArrayList<Map<String,List<String>>>();
			List<String> typeList = farmerImageService.findByType(images,userId.getString("UserId"));
			for (String type : typeList) {
			    String	typeName = businessGoodsService.findByid(type).getGoodsName();
				List<String> imageList = farmerImageService.findByImages(type,images,userId.getString("UserId"));
				type_images.put(typeName, imageList);
			}
			type_imagesList.add(type_images);
			date_type_image.put(images, type_imagesList);
			date_type_images.add(date_type_image);
		}
		ServletActionContext.getRequest().setAttribute("date_type_images", date_type_images);
		return StrutsResMSG.SUCCESS;
	}
	/***
	 * 服务号个人中心跳转
	 * @return
	 */
	@Action(value = "listF",results = { @Result(name = StrutsResMSG.SUCCESS, location = "/wx/admin/seting/personalCenter/personalCenterF.jsp") })
	public String personalCenterF(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String code = request.getParameter("code");
		JSONObject userId = WeixinUtil.httpRequest(
				WXurl.WXF_USERID_URL.replace("APPID", WXfInit.APPID).replace("SECRET", WXfInit.APPSECRET)
						.replace("CODE", code), "GET", null);
		List<OutPlanVO> outplans = outPlanService.getListByUserId(userId.getString("openid"),0,0);
		ServletActionContext.getRequest().setAttribute("outplans", outplans);
		List<Map<String, List<Map<String, List<String>>>>> date_type_images = new ArrayList<Map<String,List<Map<String,List<String>>>>>();
		List<String> imagedates = farmerImageService.getImageDates(userId.getString("openid"));
		this.status = farmerService.getStatus(userId.getString("openid"));
		for (String images : imagedates) {
			Map<String, List<Map<String, List<String>>>> date_type_image = new HashMap<String, List<Map<String, List<String>>>>();
			Map<String, List<String>> type_images = new HashMap<String, List<String>>();
			List<Map<String, List<String>>> type_imagesList= new ArrayList<Map<String,List<String>>>();
			List<String> typeList = farmerImageService.findByType(images,userId.getString("openid"));
			for (String type : typeList) {
			    String	typeName = businessGoodsService.findByid(type).getGoodsName();
				List<String> imageList = farmerImageService.findByImages(type,images,userId.getString("openid"));
				type_images.put(typeName, imageList);
			}
			type_imagesList.add(type_images);
			date_type_image.put(images, type_imagesList);
			date_type_images.add(date_type_image);
		}
		ServletActionContext.getRequest().setAttribute("date_type_images", date_type_images);
		return StrutsResMSG.SUCCESS;
	}
	@Action(value = "test",results = { @Result(name = StrutsResMSG.SUCCESS, location = "/wx/admin/seting/personalCenter/personalCenter.jsp") })
	public String test(){
		OutPlan outplan = new OutPlan(); 
//		List<OutPlan> outplans = outPlanService.findAll("dingjinghui");
		List<OutPlanVO> outplans = outPlanService.getListByUserId("dingjinghui",0,0);
		outPlanService.save(outplan);
//		ServletActionContext.getRequest().setAttribute("outplans", outplans);
		return StrutsResMSG.SUCCESS;
	}
	@Action(value="deleteImage", results = {@Result(name = StrutsResMSG.SUCCESS, type="json")})
	public String deleteImage()
	{
		try {
			farmerImageService.delectImages(imageUrl);
			status="0";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			status="1";
			imageid=null;
			
		}
		return StrutsResMSG.SUCCESS;
	}
}
