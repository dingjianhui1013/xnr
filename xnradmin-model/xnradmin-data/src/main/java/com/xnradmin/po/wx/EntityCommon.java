package com.xnradmin.po.wx;

import java.util.Date;

import javax.persistence.Column;

public class EntityCommon {
	private Date createDate;
	private Date updateDate;
	private String createBy;
	private String updateBy;
	private int delFlage;
	@Column(name="create_date")
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	@Column(name="update_date")
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	@Column(name="create_by")
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	@Column(name="update_by")
	public String getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	@Column(name="del_flage")
	public int getDelFlage() {
		return delFlage;
	}
	public void setDelFlage(int delFlage) {
		this.delFlage = delFlage;
	}
	
}
