package com.xnradmin.vo.front;

public class ProductDetailVo {

	private String FirstClassification;
	private String secoundClassification;
	private String foodClassification;
	private String FirstName;
	private String secoundName;
	private String foodName;
	public String getFirstClassification() {
		return FirstClassification;
	}
	public String getSecoundClassification() {
		return secoundClassification;
	}
	public String getFoodClassification() {
		return foodClassification;
	}
	public void setFirstClassification(String firstClassification) {
		FirstClassification = firstClassification;
	}
	public void setSecoundClassification(String secoundClassification) {
		this.secoundClassification = secoundClassification;
	}
	public void setFoodClassification(String foodClassification) {
		this.foodClassification = foodClassification;
	}
	public String getFirstName() {
		return FirstName;
	}
	public String getSecoundName() {
		return secoundName;
	}
	public String getFoodName() {
		return foodName;
	}
	public void setFirstName(String firstName) {
		FirstName = firstName;
	}
	public void setSecoundName(String secoundName) {
		this.secoundName = secoundName;
	}
	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}
	
}