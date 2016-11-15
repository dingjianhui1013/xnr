/**
 * 2012-7-26 下午1:35:14
 */
package com.xnradmin.po;


import static javax.persistence.GenerationType.IDENTITY;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.cntinker.util.ReflectHelper;

/**
 * @author: liubin
 */
@Entity
@Table(name = "common_attach")
public class CommonAttach implements java.io.Serializable{

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    private Long id;

    private String path;

    private String attachName;

    private Integer uploadStaffid;

    private String uploadStaffName;

    private Integer uploadStaffOrgid;

    private String uploadStaffOrgName;

    private Timestamp uploadTime;

    private Integer spid;

    private String spName;

    private Integer cpid;

    private String cpName;

    /**
     * 参考com.xnradmin.constant.UploadConstant中的常量
     */
    private Integer uploadModel;

    private Integer status;

    private String remark;

    private String groupid;

    private String oldFileName;

    private String suffix;

    private Long contractid;

    private String uploadCount;

    private String realPath;
    
    public String toString(){
        String res = "";
        try{
            res = ReflectHelper.makeToString(this);
        }catch(Exception e){
            e.printStackTrace();
        }
        return res;
    }

    public Long getContractid(){
        return contractid;
    }

    public void setContractid(Long contractid){
        this.contractid = contractid;
    }

    public String getGroupid(){
        return groupid;
    }

    public void setGroupid(String groupid){
        this.groupid = groupid;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getPath(){
        return path;
    }

    public void setPath(String path){
        this.path = path;
    }

    public String getAttachName(){
        return attachName;
    }

    public void setAttachName(String attachName){
        this.attachName = attachName;
    }

    public Integer getUploadStaffid(){
        return uploadStaffid;
    }

    public void setUploadStaffid(Integer uploadStaffid){
        this.uploadStaffid = uploadStaffid;
    }

    public String getUploadStaffName(){
        return uploadStaffName;
    }

    public void setUploadStaffName(String uploadStaffName){
        this.uploadStaffName = uploadStaffName;
    }

    public Integer getUploadStaffOrgid(){
        return uploadStaffOrgid;
    }

    public void setUploadStaffOrgid(Integer uploadStaffOrgid){
        this.uploadStaffOrgid = uploadStaffOrgid;
    }

    public String getUploadStaffOrgName(){
        return uploadStaffOrgName;
    }

    public void setUploadStaffOrgName(String uploadStaffOrgName){
        this.uploadStaffOrgName = uploadStaffOrgName;
    }

    public Timestamp getUploadTime(){
        return uploadTime;
    }

    public void setUploadTime(Timestamp uploadTime){
        this.uploadTime = uploadTime;
    }

    public Integer getUploadModel(){
        return uploadModel;
    }

    public void setUploadModel(Integer uploadModel){
        this.uploadModel = uploadModel;
    }

    public Integer getStatus(){
        return status;
    }

    public void setStatus(Integer status){
        this.status = status;
    }

    public String getRemark(){
        return remark;
    }

    public void setRemark(String remark){
        this.remark = remark;
    }

    public String getOldFileName(){
        return oldFileName;
    }

    public void setOldFileName(String oldFileName){
        this.oldFileName = oldFileName;
    }

    public String getSuffix(){
        return suffix;
    }

    public void setSuffix(String suffix){
        this.suffix = suffix;
    }

    public String getUploadCount(){
        return uploadCount;
    }

    public void setUploadCount(String uploadCount){
        this.uploadCount = uploadCount;
    }

    public String getRealPath(){
        return realPath;
    }

    public void setRealPath(String realPath){
        this.realPath = realPath;
    }

    public Integer getSpid(){
        return spid;
    }

    public void setSpid(Integer spid){
        this.spid = spid;
    }

    public String getSpName(){
        return spName;
    }

    public void setSpName(String spName){
        this.spName = spName;
    }

    public Integer getCpid(){
        return cpid;
    }

    public void setCpid(Integer cpid){
        this.cpid = cpid;
    }

    public String getCpName(){
        return cpName;
    }

    public void setCpName(String cpName){
        this.cpName = cpName;
    }

}
