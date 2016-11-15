package com.xnradmin.po.client;

import static javax.persistence.GenerationType.IDENTITY;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * CommonMenu entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "client_software")
public class ClientSoftware implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String softwareName = "";
	private Integer softwareType;
	private String softwareTypeName = "";
	private Integer osType;
	private String osTypeName = "";
	private String softwarePath = "";
	private Integer updateMode;
	private String updateModeName = "";
	private String versionNumber = "";
	private String description = "";
	private Timestamp createTime;
	private Timestamp modifyTime;
	
	// Constructors

	/** default constructor */
	public ClientSoftware() {
	    
	}

	/** minimal constructor */
	public ClientSoftware(Integer id) {
		this.id = id;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "SOFTWARE_MAME" , length = 50)
	public String getSoftwareName() {
		return softwareName;
	}

	public void setSoftwareName(String softwareName) {
		this.softwareName = softwareName;
	}

	@Column(name = "SOFTWARE_TYPE" , length = 11)
	public Integer getSoftwareType() {
		return softwareType;
	}

	public void setSoftwareType(Integer softwareType) {
		this.softwareType = softwareType;
	}

	@Column(name = "SOFTWARE_TYPE_MAME" , length = 50)
	public String getSoftwareTypeName() {
		return softwareTypeName;
	}

	public void setSoftwareTypeName(String softwareTypeName) {
		this.softwareTypeName = softwareTypeName;
	}

	@Column(name = "OS_TYPE" , length = 11)
	public Integer getOsType() {
		return osType;
	}

	public void setOsType(Integer osType) {
		this.osType = osType;
	}

	@Column(name = "OS_MAME" , length = 50)
	public String getOsTypeName() {
		return osTypeName;
	}

	public void setOsTypeName(String osTypeName) {
		this.osTypeName = osTypeName;
	}

	@Column(name = "SOFTWARE_PATH" , length = 400)
	public String getSoftwarePath() {
		return softwarePath;
	}

	public void setSoftwarePath(String softwarePath) {
		this.softwarePath = softwarePath;
	}

	@Column(name = "UPDATE_MODE" , length = 11)
	public Integer getUpdateMode() {
		return updateMode;
	}

	public void setUpdateMode(Integer updateMode) {
		this.updateMode = updateMode;
	}

	@Column(name = "UPDATE_MODE_NAME" , length = 50)
	public String getUpdateModeName() {
		return updateModeName;
	}

	public void setUpdateModeName(String updateModeName) {
		this.updateModeName = updateModeName;
	}

	@Column(name = "VERSION_NUMBER" , length = 50)
	public String getVersionNumber() {
		return versionNumber;
	}

	public void setVersionNumber(String versionNumber) {
		this.versionNumber = versionNumber;
	}

	@Column(name = "DESCRIPTION" , length = 200)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "CREATE_TIME")
	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	@Column(name = "MODIFY_TIME")
	public Timestamp getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Timestamp modifyTime) {
		this.modifyTime = modifyTime;
	}

	
	
}