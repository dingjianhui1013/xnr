/**
 * 2014年2月3日 下午2:23:47
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
@Table(name = "common_script")
public class CommonScript implements java.io.Serializable{

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    private Integer id;

    private String scriptDIR;

    private String scriptEncode;

    private String scriptMethods;

    @Index(name = "idx_common_script_classname")
    private String className;

    private String sourceFile;

    private Timestamp createTime;

    private Timestamp modifyTime;

    private Integer createStaffId;

    private String createStaffName;

    private Integer modifyStaffId;

    private String modifyStaffName;

    private Integer status;

    private String statusDesc;

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

    public String getScriptDIR(){
        return scriptDIR;
    }

    public void setScriptDIR(String scriptDIR){
        this.scriptDIR = scriptDIR;
    }

    public String getScriptEncode(){
        return scriptEncode;
    }

    public void setScriptEncode(String scriptEncode){
        this.scriptEncode = scriptEncode;
    }

    public String getScriptMethods(){
        return scriptMethods;
    }

    public void setScriptMethods(String scriptMethods){
        this.scriptMethods = scriptMethods;
    }

    public String getClassName(){
        return className;
    }

    public void setClassName(String className){
        this.className = className;
    }

    public String getSourceFile(){
        return sourceFile;
    }

    public void setSourceFile(String sourceFile){
        this.sourceFile = sourceFile;
    }

    public Timestamp getCreateTime(){
        return createTime;
    }

    public void setCreateTime(Timestamp createTime){
        this.createTime = createTime;
    }

    public Timestamp getModifyTime(){
        return modifyTime;
    }

    public void setModifyTime(Timestamp modifyTime){
        this.modifyTime = modifyTime;
    }

    public Integer getCreateStaffId(){
        return createStaffId;
    }

    public void setCreateStaffId(Integer createStaffId){
        this.createStaffId = createStaffId;
    }

    public String getCreateStaffName(){
        return createStaffName;
    }

    public void setCreateStaffName(String createStaffName){
        this.createStaffName = createStaffName;
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

    public Integer getStatus(){
        return status;
    }

    public void setStatus(Integer status){
        this.status = status;
    }

    public String getStatusDesc(){
        return statusDesc;
    }

    public void setStatusDesc(String statusDesc){
        this.statusDesc = statusDesc;
    }

}
