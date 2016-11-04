package com.xnradmin.po.mall.commodity;


import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * select * from phone_local where position(concat(id) in (select fid from test))!=0
 */
@Entity
@Table(name = "goods_dish_relation")
public class GoodsDishRelation implements java.io.Serializable{

    // Fields

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;

    private Integer sortId;  //显示排序Id

    private Integer goodsId;  //商品Id
    
    private Integer dishId;  //菜品Id
    
    private Integer dishCount; //菜品数量
    
    private String staffId;	//隶属用户Id
    
    private Integer primaryConfigurationId; //对应商城ID
    
	private Timestamp createTime;  //建立时间
	
	private Integer createStaffId;  //建立人
	
	private Timestamp modifyTime;  //修改时间

	private Integer modifyStaffId;  //修改人
	
    /** default constructor */
    public GoodsDishRelation(){
    }

    // Property accessors
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    public Integer getId(){
        return this.id;
    }

    public void setId(Integer id){
        this.id = id;
    }

    @Column(name = "SORT_ID")
	public Integer getSortId() {
		return sortId;
	}

	public void setSortId(Integer sortId) {
		this.sortId = sortId;
	}

	@Column(name = "GOODS_ID")
	public Integer getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}

	@Column(name = "DISH_ID")
	public Integer getDishId() {
		return dishId;
	}

	public void setDishId(Integer dishId) {
		this.dishId = dishId;
	}

	@Column(name = "STAFF_ID")
	public String getStaffId() {
		return staffId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
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

	@Column(name = "DISH_COUNT")
	public Integer getDishCount() {
		return dishCount;
	}

	public void setDishCount(Integer dishCount) {
		this.dishCount = dishCount;
	}

	public void setPrimaryConfigurationId(Integer primaryConfigurationId) {
		this.primaryConfigurationId = primaryConfigurationId;
	}
}