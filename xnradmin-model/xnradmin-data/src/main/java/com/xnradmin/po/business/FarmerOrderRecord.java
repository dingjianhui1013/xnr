package com.xnradmin.po.business;




import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "farmer_order_record")
public class FarmerOrderRecord implements java.io.Serializable{

    // Fields

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;

	private Long orderRecordId; //订单ID
    
	private Long outPlanId; //生成计划id
	
	private String farmerUserId;    //农户id
	
	private Integer goodsId; //商品Id
    
    private Integer goodsCount; //商品数量
      
    private Integer staffId;	//分配订单id
    
    private Timestamp createTime; //生成时间 
    
    
    /** default constructor */
    public FarmerOrderRecord(){
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

    @Column(name = "ORDER_RECORD_ID" ,length=20)
	public Long getOrderRecordId() {
		return orderRecordId;
	}

	public void setOrderRecordId(Long orderRecordId) {
		this.orderRecordId = orderRecordId;
	}

	@Column(name = "GOODS_COUNT")
	public Integer getGoodsCount() {
		return goodsCount;
	}

	public void setGoodsCount(Integer goodsCount) {
		this.goodsCount = goodsCount;
	}

	@Column(name = "GOODS_ID")
	public Integer getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}
	
	@Column(name = "STAFF_ID")
	public Integer getStaffId() {
		return staffId;
	}

	public void setStaffId(Integer staffId) {
		this.staffId = staffId;
	}

	

	public String getFarmerUserId() {
		return farmerUserId;
	}

	public void setFarmerUserId(String farmerUserId) {
		this.farmerUserId = farmerUserId;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Long getOutPlanId() {
		return outPlanId;
	}

	public void setOutPlanId(Long outPlanId) {
		this.outPlanId = outPlanId;
	}

	
	
}
