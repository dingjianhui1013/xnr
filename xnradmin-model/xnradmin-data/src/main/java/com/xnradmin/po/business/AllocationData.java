package com.xnradmin.po.business;




import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "allocation_list")
public class AllocationData implements java.io.Serializable{

    // Fields

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;

	private String allocationUser; //分配人
    
	private Timestamp allocationTime; //分配时间
	
	private Timestamp startTimeCondition;    //分配开始条件
	
	private Timestamp endTimeCondition; //分配结束条件    
    
	private Integer allocationStatus;//分配状态
	
	private String orderRecord;//分配涉及到的订单
	
    /** default constructor */
    public AllocationData(){
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

    @Column(name = "allocation_user")
	public String getAllocationUser() {
		return allocationUser;
	}

	public void setAllocationUser(String allocationUser) {
		this.allocationUser = allocationUser;
	}
	@Column(name = "allocation_time" ,length=20)
	public Timestamp getAllocationTime() {
		return allocationTime;
	}

	public void setAllocationTime(Timestamp allocationTime) {
		this.allocationTime = allocationTime;
	}
	@Column(name = "start_time_condition" ,length=20)
	public Timestamp getStartTimeCondition() {
		return startTimeCondition;
	}

	public void setStartTimeCondition(Timestamp startTimeCondition) {
		this.startTimeCondition = startTimeCondition;
	}
	@Column(name = "end_time_condition" ,length=20)
	public Timestamp getEndTimeCondition() {
		return endTimeCondition;
	}

	public void setEndTimeCondition(Timestamp endTimeCondition) {
		this.endTimeCondition = endTimeCondition;
	}
	@Column(name = "allocation_status")
	public Integer getAllocationStatus() {
		return allocationStatus;
	}

	public void setAllocationStatus(Integer allocationStatus) {
		this.allocationStatus = allocationStatus;
	}
	@Column(name = "order_record")
	public String getOrderRecord() {
		return orderRecord;
	}

	public void setOrderRecord(String orderRecord) {
		this.orderRecord = orderRecord;
	}

	
}
