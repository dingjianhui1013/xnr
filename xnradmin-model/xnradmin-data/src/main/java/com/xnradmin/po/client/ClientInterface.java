/**
 * 2014年1月24日 上午9:46:15
 */
package com.xnradmin.po.client;


import static javax.persistence.GenerationType.IDENTITY;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.cntinker.util.ReflectHelper;

/**
 * @author: bin_liu
 */
@Entity
@Table(name = "client_interface")
public class ClientInterface implements java.io.Serializable{
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    private Long id;

    private String faceName;

    private String className;

    private Timestamp createTime;

    private String createStaffName;

    private Integer createStaffId;

    private Integer status;

    private String statusName;

    private String inputSource;

    private String outputSource;

    public String toString(){
        String res = "";
        try{
            res = ReflectHelper.makeToString(this);
        }catch(Exception e){
            e.printStackTrace();
        }
        return res;
    }

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getFaceName(){
        return faceName;
    }

    public void setFaceName(String faceName){
        this.faceName = faceName;
    }

    public String getClassName(){
        return className;
    }

    public void setClassName(String className){
        this.className = className;
    }

    public Timestamp getCreateTime(){
        return createTime;
    }

    public void setCreateTime(Timestamp createTime){
        this.createTime = createTime;
    }

    public String getCreateStaffName(){
        return createStaffName;
    }

    public void setCreateStaffName(String createStaffName){
        this.createStaffName = createStaffName;
    }

    public Integer getCreateStaffId(){
        return createStaffId;
    }

    public void setCreateStaffId(Integer createStaffId){
        this.createStaffId = createStaffId;
    }

    public Integer getStatus(){
        return status;
    }

    public void setStatus(Integer status){
        this.status = status;
    }

    public String getStatusName(){
        return statusName;
    }

    public void setStatusName(String statusName){
        this.statusName = statusName;
    }

    public String getInputSource(){
        return inputSource;
    }

    public void setInputSource(String inputSource){
        this.inputSource = inputSource;
    }

    public String getOutputSource(){
        return outputSource;
    }

    public void setOutputSource(String outputSource){
        this.outputSource = outputSource;
    }

}
