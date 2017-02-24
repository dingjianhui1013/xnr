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
@Table(name = "pseudo_orders")
public class PseudoOrders implements java.io.Serializable{

    // Fields

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
    
    private String comboPlanIds;  //订单计划id 逗号分隔的字符串

    private Integer orderDay;  //相对于套餐的天数
    
    private String dayKey;//周期的key combo_plan_type#combo_plan_cycle#combo_plan_date
    
    private Integer orderUnit;//周期单位 1--周，2--月，3--季，4--年
    
    private Integer comboId;//套餐id
    
    /** default constructor */
    public PseudoOrders(){
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
    @Column(name = "ID", unique = true, nullable = false)
    public Integer getId(){
        return this.id;
    }

    public void setId(Integer id){
        this.id = id;
    }

	@Column(name = "combo_plan_ids")
	public String getComboPlanIds() {
		return comboPlanIds;
	}

	public void setComboPlanIds(String comboPlanIds) {
		this.comboPlanIds = comboPlanIds;
	}
	@Column(name = "order_day")
	public Integer getOrderDay() {
		return orderDay;
	}

	public void setOrderDay(Integer orderDay) {
		this.orderDay = orderDay;
	}
	
	@Column(name = "day_key")
	public String getDayKey() {
		return dayKey;
	}

	public void setDayKey(String dayKey) {
		this.dayKey = dayKey;
	}
	
	@Column(name = "order_unit")
	public Integer getOrderUnit() {
		return orderUnit;
	}

	public void setOrderUnit(Integer orderUnit) {
		this.orderUnit = orderUnit;
	}
	@Column(name = "combo_id")
	public Integer getComboId() {
		return comboId;
	}

	public void setComboId(Integer comboId) {
		this.comboId = comboId;
	}
	
}