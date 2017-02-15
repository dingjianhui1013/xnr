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
@Table(name = "combo")
public class Combo implements java.io.Serializable{

    // Fields

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
    
    private String comboName;  //套餐名称

    private Float comboPrice;  //套餐价格
    
    private String comboCycle;  //套餐周期 （秒） 字符串
    
    private String comboCycleStatus;  //套餐周期  对应于数据字典
    
    private String comboStatus;	//套餐状态 0--启用 1--关闭
    
    private Timestamp createTime;	//创建时间
    
    private Integer comboTimes;//套餐总次数
    
    /** default constructor */
    public Combo(){
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
    @Column(name = "combo_name")
	public String getComboName() {
		return comboName;
	}

	public void setComboName(String comboName) {
		this.comboName = comboName;
	}
	@Column(name = "combo_price")
	public Float getComboPrice() {
		return comboPrice;
	}

	public void setComboPrice(Float comboPrice) {
		this.comboPrice = comboPrice;
	}
	@Column(name = "combo_cycle")
	public String getComboCycle() {
		return comboCycle;
	}

	public void setComboCycle(String comboCycle) {
		this.comboCycle = comboCycle;
	}
	@Column(name = "combo_status")
	public String getComboStatus() {
		return comboStatus;
	}

	public void setComboStatus(String comboStatus) {
		this.comboStatus = comboStatus;
	}
	@Column(name = "create_time")
	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	@Column(name = "combo_times")
	public Integer getComboTimes() {
		return comboTimes;
	}

	public void setComboTimes(Integer comboTimes) {
		this.comboTimes = comboTimes;
	}
	
	@Column(name = "combo_cycle_status")
	public String getComboCycleStatus() {
		return comboCycleStatus;
	}
	
	public void setComboCycleStatus(String comboCycleStatus) {
		this.comboCycleStatus = comboCycleStatus;
	}
    
}