package com.xnradmin.po.business;


import static javax.persistence.GenerationType.IDENTITY;

import java.sql.Timestamp;
import java.text.DecimalFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.cntinker.util.ReflectHelper;

/**
 * select * from phone_local where position(concat(id) in (select fid from test))!=0
 */
@Entity
@Table(name = "combo_user")
public class ComboUser implements java.io.Serializable{

    // Fields

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;

    private Integer userId;  //用户id
    
    private String comboId;  //套餐id

    private Timestamp comboStartTime;  //套餐开始时间
    
    private Timestamp comboEndTime;  //套餐结束时间
    
    private Float usingMoney;	//已配送金额
    
    private Integer usingTimes;	//已配送次数
    
    private Long orderId;//总订单号
    
    private Integer comboUserStatus;	//用户套餐状态 0--启用 1--禁用
    
    private Timestamp createTime; //用户套餐创建时间
    
    /** default constructor */
    public ComboUser(){
    }
    
    public String toString(){
        String res = "";
        try{
            res = ReflectHelper.makeToString(this);
        }catch(Exception e){
            e.printStackTrace();
        }
        return res;
    }

    // Property accessors
    @Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
    public Integer getId(){
        return this.id;
    }

    public void setId(Integer id){
        this.id = id;
    }

    @Column(name = "user_id")
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	@Column(name = "combo_id")
	public String getComboId() {
		return comboId;
	}

	public void setComboId(String comboId) {
		this.comboId = comboId;
	}
	@Column(name = "combo_start_time")
	public Timestamp getComboStartTime() {
		return comboStartTime;
	}

	public void setComboStartTime(Timestamp comboStartTime) {
		this.comboStartTime = comboStartTime;
	}
	@Column(name = "combo_end_time")
	public Timestamp getComboEndTime() {
		return comboEndTime;
	}

	public void setComboEndTime(Timestamp comboEndTime) {
		this.comboEndTime = comboEndTime;
	}
	@Column(name = "using_money")
	public Float getUsingMoney() {
		return usingMoney;
	}

	public void setUsingMoney(Float usingMoney) {
		this.usingMoney = usingMoney;
	}
	@Column(name = "using_times")
	public Integer getUsingTimes() {
		return usingTimes;
	}

	public void setUsingTimes(Integer usingTimes) {
		this.usingTimes = usingTimes;
	}
	@Column(name = "order_id")
	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	@Column(name = "combo_user_status")
	public Integer getComboUserStatus() {
		return comboUserStatus;
	}

	public void setComboUserStatus(Integer comboUserStatus) {
		this.comboUserStatus = comboUserStatus;
	}
	@Column(name = "create_time")
	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
  
}