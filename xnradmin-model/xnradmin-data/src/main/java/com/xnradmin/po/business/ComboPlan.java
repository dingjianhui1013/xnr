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
@Table(name = "combo_plan")
public class ComboPlan implements java.io.Serializable{

    // Fields

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;

    private String comboPlanType;  //计划类型 0--固定时间 1--固定周期 2--固定周期固定时间

    private String comboPlanDate;  //固定时间 type=1时 固定时间如2017-03-01日期的秒值    type=2时 固定时间为一个周期长度 单位秒 表示从现在开始 每一个周期长度配送一次 type=3时  固定时间是一个周期长度内的相对时间如每周周三 该值为周三开始那天相对于周一的相对时间两天的秒数
    
    private String comboPlanCycle;  //周期长度 秒
    
    private Timestamp comboPlanCreatTime;	//计划生成时间
    
    private Integer goodsId;	//商品id
    
    private Integer goodsNumber; //商品数量
    
    private Integer comboId;	//套餐id
    
    /** default constructor */
    public ComboPlan(){
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

    @Column(name = "combo_plan_type")
	public String getComboPlanType() {
		return comboPlanType;
	}

	public void setComboPlanType(String comboPlanType) {
		this.comboPlanType = comboPlanType;
	}
	@Column(name = "combo_plan_date")
	public String getComboPlanDate() {
		return comboPlanDate;
	}

	public void setComboPlanDate(String comboPlanDate) {
		this.comboPlanDate = comboPlanDate;
	}
	@Column(name = "combo_plan_cycle")
	public String getComboPlanCycle() {
		return comboPlanCycle;
	}

	public void setComboPlanCycle(String comboPlanCycle) {
		this.comboPlanCycle = comboPlanCycle;
	}
	@Column(name = "combo_plan_creat_time")
	public Timestamp getComboPlanCreatTime() {
		return comboPlanCreatTime;
	}

	public void setComboPlanCreatTime(Timestamp comboPlanCreatTime) {
		this.comboPlanCreatTime = comboPlanCreatTime;
	}
	@Column(name = "goods_id")
	public Integer getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}
	@Column(name = "goods_number")
	public Integer getGoodsNumber() {
		return goodsNumber;
	}

	public void setGoodsNumber(Integer goodsNumber) {
		this.goodsNumber = goodsNumber;
	}
	@Column(name = "combo_id")
	public Integer getComboId() {
		return comboId;
	}

	public void setComboId(Integer comboId) {
		this.comboId = comboId;
	}
    
   
}