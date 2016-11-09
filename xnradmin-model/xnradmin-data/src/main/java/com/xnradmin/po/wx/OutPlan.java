package com.xnradmin.po.wx;

import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "outplan")
public class OutPlan  implements java.io.Serializable{
	private Long id;
	private String businesCategoryId;
	private String userId; //用户id
	private String goodsId;//商品id
	private Date startTime;//开始
	private Date endTime;//结束时间
	private String unitId;//单位id
	private String output;//计划产量
	
	private Date createDate;//创建时间
	private Date updateDate;//更新时间
	private String createBy;//创建人
	private String updateBy;//更新人
	private int delFlage;//删除状态
	private Integer examine;//审核状态
	private String remarks;//备注
	private String examinePerson;
	
	@Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Column(name="user_id")
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	@Column(name="goods_id")
	public String getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}
	@Column(name="start_time")
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	@Column(name="end_time")
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	@Column(name="unit_id")
	public String getUnitId() {
		return unitId;
	}
	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}
	@Column(name="output")
	public String getOutput() {
		return output;
	}
	public void setOutput(String output) {
		this.output = output;
	}
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
	@Column(name="examine")
	public Integer getExamine() {
		return examine;
	}
	@Column(name="remarks")
	public String getRemarks() {
		return remarks;
	}
	public void setExamine(int examine) {
		this.examine = examine;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	@Column(name="busines_category_id")
	public String getBusinesCategoryId() {
		return businesCategoryId;
	}
	public void setBusinesCategoryId(String businesCategoryId) {
		this.businesCategoryId = businesCategoryId;
	}
	@Column(name="examine_person")
	public String getExaminePerson() {
		return examinePerson;
	}
	public void setExaminePerson(String examinePerson) {
		this.examinePerson = examinePerson;
	}
	
	
}
