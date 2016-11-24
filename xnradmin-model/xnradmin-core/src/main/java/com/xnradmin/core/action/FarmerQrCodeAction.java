package com.xnradmin.core.action;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.xnradmin.client.service.wx.FarmerService;
import com.xnradmin.constant.AjaxResult;
import com.xnradmin.constant.StrutsResMSG;
import com.xnradmin.core.service.FarmerQrCodeService;
import com.xnradmin.po.wx.connect.FarmerQrCode;
import com.xnradmin.vo.FarmerQrCodeVo;

@Controller
@Scope("prototype")
@Namespace("/page/farmerQrCode")
@ParentPackage("json-default")
public class FarmerQrCodeAction extends ParentAction{
	@Autowired
	private FarmerQrCodeService farmerQrCodeService;
	@Autowired
	private FarmerService farmerService;
	
	private List<FarmerQrCode> farmerQrCodes;
	private FarmerQrCodeVo query;
	private FarmerQrCode farmerQrCode;
	private String farmerId;
	private String status;
	private int index;
	private int fenleiL;
	private List<FarmerQrCodeVo> farmerQrCodeVo;
	private String qrCodeId;
	public List<FarmerQrCode> getFarmerQrCodes() {
		return farmerQrCodes;
	}
	public FarmerQrCodeVo getQuery() {
		return query;
	}
	public FarmerQrCode getFarmerQrCode() {
		return farmerQrCode;
	}
	public String getFarmerId() {
		return farmerId;
	}
	public String getStatus() {
		return status;
	}
	public List<FarmerQrCodeVo> getFarmerQrCodeVo() {
		return farmerQrCodeVo;
	}
	public void setFarmerQrCodes(List<FarmerQrCode> farmerQrCodes) {
		this.farmerQrCodes = farmerQrCodes;
	}
	public void setQuery(FarmerQrCodeVo query) {
		this.query = query;
	}
	public void setFarmerQrCode(FarmerQrCode farmerQrCode) {
		this.farmerQrCode = farmerQrCode;
	}
	public void setFarmerId(String farmerId) {
		this.farmerId = farmerId;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public void setFarmerQrCodeVo(List<FarmerQrCodeVo> farmerQrCodeVo) {
		this.farmerQrCodeVo = farmerQrCodeVo;
	}
	public String getQrCodeId() {
		return qrCodeId;
	}
	public void setQrCodeId(String qrCodeId) {
		this.qrCodeId = qrCodeId;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public int getFenleiL() {
		return fenleiL;
	}
	public void setFenleiL(int fenleiL) {
		this.fenleiL = fenleiL;
	}
	@Action(value = "info", results = { @Result(name = StrutsResMSG.SUCCESS, location = "/farmerQrCode/info.jsp") })
	public String info()
	{
		setPageInfo();
		return StrutsResMSG.SUCCESS;
	}
	private void setPageInfo()
	{
		this.farmerQrCodeVo = this.farmerQrCodeService.getList(query, super.getPageNum(),super.getNumPerPage());
		super.totalCount = this.farmerQrCodeService.getCount(query);
	}
	@Action(value="generateQr",results = {@Result(name = StrutsResMSG.SUCCESS, type="json")})
	public String generateQr()
	{
		int count =0;
		String[] fenleiById = farmerService.getFenleisByUserId(farmerId);
		fenleiL = fenleiById.length;
			for (String string : fenleiById) {
				FarmerQrCode farmerQrCode = new FarmerQrCode();
				farmerQrCode.setFarmerId(farmerId);
				farmerQrCode.setGoodsId(string);
				String url = "/farmerQrCodeImage"+File.separator+farmerId+File.separator+string;
				String imageName = new Date().getTime()+"_"+farmerId+".png";
				String skipUrl =  "http://weixin.robustsoft.cn/xnr/page/wx/farmer/showFarmerImage.action?farmerId="+farmerId+"&goodsId="+string;
				farmerQrCode.setQrCodeUrl(url+File.separator+imageName);
				farmerQrCode.setSkipUrl(skipUrl);
				farmerService.generateCode(farmerId,string,imageName,skipUrl);
				try {
					farmerQrCodeService.save(farmerQrCode);
					count++;
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			index = fenleiL - count;
		return StrutsResMSG.SUCCESS;
	}
	@Action(value="anthinfo",results ={@Result(name=StrutsResMSG.SUCCESS,location="/farmerQrCode/anthinfo.jsp")})
	public String anthInfo()
	{
		farmerQrCode = farmerQrCodeService.getFarmerqrCodeById(qrCodeId);
		return StrutsResMSG.SUCCESS;
	}
	@Action(value = "saveAnthinfo", results = { @Result(name = StrutsResMSG.SUCCESS, type = "plainText") })
	public String saveExamineNo() throws JSONException, IOException {
		farmerQrCodeService.saveUrl(farmerQrCode);
		farmerService.UpdateCode(farmerQrCode);
		super.success(null, AjaxResult.CALL_BACK_TYPE_CLOSECURRENT, "FarmerManagement",null);
		return null;
	}
	@Action(value="deleteFarmerQrCode", results = { @Result(name = StrutsResMSG.SUCCESS, type = "plainText") })
	public String deleteFarmerQrCode()
	{
		try {
			farmerQrCodeService.deleteFarmerQrCode(farmerQrCode);
			super.success(null, null, "ewm",null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	@Action(value="downloadFarmerQrCode",results = { @Result(name = StrutsResMSG.SUCCESS, type = "plainText")})
	public String downloadFarmerQrCode() throws Exception
	{
		String filepath = ServletActionContext.getServletContext()
		.getRealPath(farmerQrCode.getQrCodeUrl());
		File file = new File(filepath);
		String fileName = new Date().getTime()+".png";
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType(response.getContentType());
		response.setHeader("Content-disposition",
				"attachment; filename="+fileName);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int len = 0;
		FileInputStream inputStream = new FileInputStream(file);
		byte [] buffer  = new byte[3];
		while((len = inputStream.read(buffer)) != -1)
		{
			baos.write(buffer, 0,  len);
		}
		byte[] bytes = baos.toByteArray();
		response.setHeader("Content-Length", String.valueOf(bytes.length));
		BufferedOutputStream bos = null;
		bos = new BufferedOutputStream(response.getOutputStream());
		bos.write(bytes);
		bos.close();
		baos.close();
		return null;
	}
	@Override
	public boolean isPublic() {
		// TODO Auto-generated method stub
		return false;
	}
	
} 
