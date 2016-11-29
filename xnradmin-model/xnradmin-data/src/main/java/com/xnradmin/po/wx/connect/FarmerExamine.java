package com.xnradmin.po.wx.connect;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="farmer_examine")
public class FarmerExamine {

	private Long id;
	private String farmerName;
	private String farmerTel;
	private String farmerId;
	private String contractNumber;
	@Id
	@GeneratedValue(strategy = IDENTITY)
    @Column(name = "ID", unique = true, nullable = false) 
	public Long getId() {
		return id;
	}
	@Column(name="farmer_name")
	public String getFarmerName() {
		return farmerName;
	}
	@Column(name="farmer_tel")
	public String getFarmerTel() {
		return farmerTel;
	}
	@Column(name="farmer_id")
	public String getFarmerId() {
		return farmerId;
	}
	@Column(name="contract_number")
	public String getContractNumber() {
		return contractNumber;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setFarmerName(String farmerName) {
		this.farmerName = farmerName;
	}
	public void setFarmerTel(String farmerTel) {
		this.farmerTel = farmerTel;
	}
	public void setFarmerId(String farmerId) {
		this.farmerId = farmerId;
	}
	public void setContractNumber(String contractNumber) {
		this.contractNumber = contractNumber;
	}
	
}
