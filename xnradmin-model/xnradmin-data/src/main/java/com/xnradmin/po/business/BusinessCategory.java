package com.xnradmin.po.business;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Id;
import javax.persistence.Table;

import com.cntinker.util.ReflectHelper;

/**
 * select * from phone_local where position(concat(id) in (select fid from
 * test))!=0
 */
@Entity
@Table(name = "business_category")
public class BusinessCategory implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;

	private Integer sortId; // 排序Id

	private Integer categoryParentId; // 上级分类Id

	private Integer categoryLevel; // 分类级别

	private String categoryName; // 分类名称

	private String categoryLogo; // 分类图片

	private String categoryHeadLogo; // 分类置顶图片

	private String categoryDescription; // 分类描述

	private Integer categoryStatus; // 分类状态

	private Integer primaryConfigurationId; // 对应商城ID

	private Timestamp createTime; // 建立时间

	private Integer createStaffId; // 建立人

	private Timestamp modifyTime; // 修改时间

	private Integer modifyStaffId; // 修改人

	/** default constructor */
	public BusinessCategory() {
	}

	public String toString() {
		String res = "";
		try {
			res = ReflectHelper.makeToString(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
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

	@Column(name = "SORT_ID")
	public Integer getSortId() {
		return sortId;
	}

	public void setSortId(Integer sortId) {
		this.sortId = sortId;
	}

	@Column(name = "CATEGORY_PARENT_ID")
	public Integer getCategoryParentId() {
		return categoryParentId;
	}

	public void setCategoryParentId(Integer categoryParentId) {
		this.categoryParentId = categoryParentId;
	}

	@Column(name = "CATEGORY_NAME", length = 100)
	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	@Column(name = "CATEGORY_LOGO", length = 400)
	public String getCategoryLogo() {
		return categoryLogo;
	}

	public void setCategoryLogo(String categoryLogo) {
		this.categoryLogo = categoryLogo;
	}

	@Column(name = "CATEGORY_HEAD_LOGO", length = 400)
	public String getCategoryHeadLogo() {
		return categoryHeadLogo;
	}

	public void setCategoryHeadLogo(String categoryHeadLogo) {
		this.categoryHeadLogo = categoryHeadLogo;
	}

	@Column(name = "CATEGORY_DESCRIPTION", length = 400)
	public String getCategoryDescription() {
		return categoryDescription;
	}

	public void setCategoryDescription(String categoryDescription) {
		this.categoryDescription = categoryDescription;
	}

	@Column(name = "CATEGORY_STATUS")
	public Integer getCategoryStatus() {
		return categoryStatus;
	}

	public void setCategoryStatus(Integer categoryStatus) {
		this.categoryStatus = categoryStatus;
	}

	@Column(name = "CREATE_TIME")
	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	@Column(name = "CREATE_STAFF_ID")
	public Integer getCreateStaffId() {
		return createStaffId;
	}

	public void setCreateStaffId(Integer createStaffId) {
		this.createStaffId = createStaffId;
	}

	@Column(name = "MODIFY_TIME")
	public Timestamp getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Timestamp modifyTime) {
		this.modifyTime = modifyTime;
	}

	@Column(name = "MODIFY_STAFF_ID")
	public Integer getModifyStaffId() {
		return modifyStaffId;
	}

	public void setModifyStaffId(Integer modifyStaffId) {
		this.modifyStaffId = modifyStaffId;
	}

	@Column(name = "PRIMARY_CONFIGURATION_ID")
	public Integer getPrimaryConfigurationId() {
		return primaryConfigurationId;
	}

	public void setPrimaryConfigurationId(Integer primaryConfigurationId) {
		this.primaryConfigurationId = primaryConfigurationId;
	}

	@Column(name = "CATEGORY_LEVEL")
	public Integer getCategoryLevel() {
		return categoryLevel;
	}

	public void setCategoryLevel(Integer categoryLevel) {
		this.categoryLevel = categoryLevel;
	}

}