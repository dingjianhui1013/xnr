/**
 * 2014年2月4日 下午5:18:14
 */
package com.xnradmin.po;


import static javax.persistence.GenerationType.IDENTITY;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Index;

import com.cntinker.util.ReflectHelper;

/**
 * @author: bin_liu
 */
@Entity
@Table(name = "common_port")
public class CommonPort implements java.io.Serializable{
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    private Integer id;

    @Index(name = "idx_common_port_portname")
    private String portName;

    private Integer scriptId;

    private String scriptClassName;

    private Timestamp createTime;

    private String createStaffName;

    private Integer createStaffId;

    private Integer status;

    private String statusName;
    
    private String portDesc;

    @Column(name = "inputSource", length = 4096)
    private String inputSource;

    @Column(name = "outputSource", length = 4096)
    private String outputSource;
    
    private Boolean inputEncrypt;
    
    private Boolean outputEncrypt;
    
    @Column(name = "inputDecodeSource", length = 4096)
    private String inputDecodeSource;

    @Column(name = "outputDecodeSource", length = 4096)
    private String outputDecodeSource;

    private Integer inputType;

    private String intputTypeDesc;

    private Integer outputType;

    private String outputTypeDesc;
    
    private Integer modifyStaffId;
    
    private String modifyStaffName;
    
    private Timestamp modifyTime;

    public String toString(){
        String res = "";
        try{
            res = ReflectHelper.makeToString(this);
        }catch(Exception e){
            e.printStackTrace();
        }
        return res;
    }

    public Integer getId(){
        return id;
    }

    public void setId(Integer id){
        this.id = id;
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

    public String getInputDecodeSource(){
        return inputDecodeSource;
    }

    public void setInputDecodeSource(String inputDecodeSource){
        this.inputDecodeSource = inputDecodeSource;
    }

    public String getOutputDecodeSource(){
        return outputDecodeSource;
    }

    public void setOutputDecodeSource(String outputDecodeSource){
        this.outputDecodeSource = outputDecodeSource;
    }

    public Integer getInputType(){
        return inputType;
    }

    public String getPortName(){
        return portName;
    }

    public void setPortName(String portName){
        this.portName = portName;
    }

    public void setInputType(Integer inputType){
        this.inputType = inputType;
    }

    public String getIntputTypeDesc(){
        return intputTypeDesc;
    }

    public void setIntputTypeDesc(String intputTypeDesc){
        this.intputTypeDesc = intputTypeDesc;
    }

    public Integer getOutputType(){
        return outputType;
    }

    public void setOutputType(Integer outputType){
        this.outputType = outputType;
    }

    public String getOutputTypeDesc(){
        return outputTypeDesc;
    }

    public void setOutputTypeDesc(String outputTypeDesc){
        this.outputTypeDesc = outputTypeDesc;
    }

    public Integer getScriptId(){
        return scriptId;
    }

    public void setScriptId(Integer scriptId){
        this.scriptId = scriptId;
    }

    public String getScriptClassName(){
        return scriptClassName;
    }

    public void setScriptClassName(String scriptClassName){
        this.scriptClassName = scriptClassName;
    }

    public String getPortDesc(){
        return portDesc;
    }

    public void setPortDesc(String portDesc){
        this.portDesc = portDesc;
    }

    public Integer getModifyStaffId(){
        return modifyStaffId;
    }

    public void setModifyStaffId(Integer modifyStaffId){
        this.modifyStaffId = modifyStaffId;
    }

    public String getModifyStaffName(){
        return modifyStaffName;
    }

    public void setModifyStaffName(String modifyStaffName){
        this.modifyStaffName = modifyStaffName;
    }

    public Timestamp getModifyTime(){
        return modifyTime;
    }

    public void setModifyTime(Timestamp modifyTime){
        this.modifyTime = modifyTime;
    }

    public Boolean getInputEncrypt(){
        return inputEncrypt;
    }

    public void setInputEncrypt(Boolean inputEncrypt){
        this.inputEncrypt = inputEncrypt;
    }

    public Boolean getOutputEncrypt(){
        return outputEncrypt;
    }

    public void setOutputEncrypt(Boolean outputEncrypt){
        this.outputEncrypt = outputEncrypt;
    }

   

  
}
