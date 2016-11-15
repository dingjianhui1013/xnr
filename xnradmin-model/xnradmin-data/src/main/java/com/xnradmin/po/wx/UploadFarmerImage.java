package com.xnradmin.po.wx;

public class UploadFarmerImage {
	private String picurl;
	private String type;
	public UploadFarmerImage() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UploadFarmerImage(String picurl, String type) {
		super();
		this.picurl = picurl;
		this.type = type;
	}
	public String getPicurl() {
		return picurl;
	}
	public String getType() {
		return type;
	}
	public void setPicurl(String picurl) {
		this.picurl = picurl;
	}
	public void setType(String type) {
		this.type = type;
	}
	
}
