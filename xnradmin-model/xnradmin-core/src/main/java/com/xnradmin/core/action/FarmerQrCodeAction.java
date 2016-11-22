package com.xnradmin.core.action;

import java.io.File;
import java.util.Date;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.xnradmin.client.service.wx.FarmerService;
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
	private List<FarmerQrCodeVo> farmerQrCodeVo;
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
	@Action(value = "info", results = { @Result(name = StrutsResMSG.SUCCESS, location = "/farmerQrCode/info.jsp") })
	public String info()
	{
		this.farmerQrCodeVo = farmerQrCodeService.getListFarmerqrCode();
		return StrutsResMSG.SUCCESS;
	}
	@Action(value="generateQr",results = {@Result(name = StrutsResMSG.SUCCESS, type="json")})
	public String generateQr()
	{
		try {
			String[] fenleiById = farmerService.getFenleisByUserId(farmerId);
			for (String string : fenleiById) {
				FarmerQrCode farmerQrCode = new FarmerQrCode();
				farmerQrCode.setFarmerId(farmerId);
				farmerQrCode.setGoodsId(string);
				String url = "/farmerQrCodeImage"+File.separator+farmerId+File.separator+string;
				String imageName = new Date().getTime()+"_"+farmerId+".png";
				farmerQrCode.setQrCodeUrl(url+File.separator+imageName);
				farmerService.generateCode(farmerId,string,imageName);
				farmerQrCodeService.save(farmerQrCode);
			}
			status="0";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			status="1";
		}
		return StrutsResMSG.SUCCESS;
	}
	@Override
	public boolean isPublic() {
		// TODO Auto-generated method stub
		return false;
	}
	
} 
