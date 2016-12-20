package com.xnradmin.client.action.front;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.xnradmin.client.service.front.ReceiptAddressService;
import com.xnradmin.constant.StrutsResMSG;
import com.xnradmin.po.front.FrontUser;
import com.xnradmin.po.front.ReceiptAddress;


@Controller
@Scope("prototype")
@Namespace("/front/receiptAddress")
@ParentPackage("json-default")
public class ReceiptAddressAction {

	
	@Autowired
	private ReceiptAddressService addressService;
	
	
	private FrontUser user;
	private ReceiptAddress addr;
	
	private String receiptName;
	private String detailedAddress;
	private String telAddress;
	private String county;
	private Long addressId;
	private int saveSuccess;
	private int getSuccess;
	private int deleteSuccess;
	
	
	
	
	
	public FrontUser getUser() {
		return user;
	}

	public void setUser(FrontUser user) {
		this.user = user;
	}

	public ReceiptAddress getAddr() {
		return addr;
	}

	public void setAddr(ReceiptAddress addr) {
		this.addr = addr;
	}

	public String getReceiptName() {
		return receiptName;
	}

	public void setReceiptName(String receiptName) {
		this.receiptName = receiptName;
	}

	public String getDetailedAddress() {
		return detailedAddress;
	}

	public void setDetailedAddress(String detailedAddress) {
		this.detailedAddress = detailedAddress;
	}

	public String getTelAddress() {
		return telAddress;
	}

	public void setTelAddress(String telAddress) {
		this.telAddress = telAddress;
	}

	

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public Long getAddressId() {
		return addressId;
	}

	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}



	public int getSaveSuccess() {
		return saveSuccess;
	}

	public void setSaveSuccess(int saveSuccess) {
		this.saveSuccess = saveSuccess;
	}

	public int getGetSuccess() {
		return getSuccess;
	}

	public void setGetSuccess(int getSuccess) {
		this.getSuccess = getSuccess;
	}

	public int getDeleteSuccess() {
		return deleteSuccess;
	}

	public void setDeleteSuccess(int deleteSuccess) {
		this.deleteSuccess = deleteSuccess;
	}

	/**
	 * 订单详情页面保存地址接口
	 * 
	 * @return String
	 * @throws Exception
	 */
	@Action(value = "saveNewReceiptAddress", results = { @Result(name = StrutsResMSG.SUCCESS, type = "json",params = {
			"enableGZIP", "true" }) })
	public String saveNewAddress() throws Exception {
		
		user = (FrontUser)ServletActionContext.getRequest().getSession().getAttribute("user");
		
		saveSuccess = 0;
		
		ReceiptAddress address = null;
	
		addr = addressService.findByType1("1");
		
		
		
		
		try {
			
			if(addressId!=0){
				address = addressService.findByid(addressId.toString());
				address.setDetailedAddress(detailedAddress);
				address.setFrontUserId(user.getId());
				address.setReceiptName(receiptName);
				address.setTel(telAddress);
				address.setProvince("山东省");
				address.setCity("济南市");
				address.setCounty(county);
				address.setType("1");
				addressService.update(address);
				
				if(addr.getId()!=addressId){
					addr.setType("0");
					addressService.update(addr);
				}
				
				saveSuccess = 2;
				
			}else{
				address = new ReceiptAddress();
				
				address.setDetailedAddress(detailedAddress);
				address.setFrontUserId(user.getId());
				address.setReceiptName(receiptName);
				address.setTel(telAddress);
				address.setProvince("山东省");
				address.setCity("济南市");
				address.setCounty(county);
				address.setType("1");
				addressService.save(address);
				addressId = new Long((long)address.getId());
				addr.setType("0");
				addressService.update(addr);
				saveSuccess = 1;
			}
			
			
			
			
		} catch (Exception e) {
			
		}
		
		
		
		
		
		return StrutsResMSG.SUCCESS;
	}
	
	
	
	
	
	
	
	
	/**
	 * 订单详情页面编辑地址获得编辑内容接口
	 * 
	 * @return String
	 * @throws Exception
	 */
	@Action(value = "getReceiptAddress", results = { @Result(name = StrutsResMSG.SUCCESS, type = "json",params = {
			"enableGZIP", "true" }) })
	public String getReceiptAddress() throws Exception {
		
		getSuccess = 0;	
		

		
		try {
			addr = addressService.findByid(addressId.toString());
			
			if(addr!=null){
				getSuccess = 1;
				receiptName = addr.getReceiptName();
				detailedAddress = addr.getDetailedAddress();
				telAddress = addr.getTel();
				county = addr.getCounty();
			}
			
			
		} catch (Exception e) {
			
		}
		
		
		
		
		
		return StrutsResMSG.SUCCESS;
	}
	
	
	/**
	 * 订单详情页删除接口
	 * 
	 * @return String
	 * @throws Exception
	 */
	@Action(value = "deleteReceiptAddress", results = { @Result(name = StrutsResMSG.SUCCESS, type = "json",params = {
			"enableGZIP", "true" }) })
	public String deleteReceiptAddress() throws Exception {
		
		deleteSuccess = 0;	
	
		try {
			addr = addressService.findByid(addressId.toString());
			
			
			if(addr!=null){
				
				/*if(addr.getType().equals("1")){
					
					ReceiptAddress address = addressService.findByTypeAndId();
					
					if(address!=null){
						address.setType("1");
					}
					
				}
				*/
				addressService.delete(addressId.toString());
			}
			
			
			
			
			deleteSuccess = 1;
			
		} catch (Exception e) {
			
		}
		
		
		
		
		
		return StrutsResMSG.SUCCESS;
	}
	
}
