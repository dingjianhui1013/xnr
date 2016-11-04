/**
 * 2013-7-22 上午4:42:32
 */
package com.xnradmin.po.system;


import java.sql.Timestamp;

import com.cntinker.util.ReflectHelper;

/**
 * @autohr: bin_liu
 */

public class SysVar{

    private Long id;

    private String varName;

    private Integer varTypeId;

    private String varTypeName;

    private Long classId;

    private String className;

    private String classPackageName;

    private Timestamp createTime;

    private Timestamp lastModifyTime;

    private Long createStaffId;

    private String createStaffName;

    private Long lastModifyStaffId;

    private String lastModifyStaffName;

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

    public String getVarName(){
        return varName;
    }

    public void setVarName(String varName){
        this.varName = varName;
    }

    public Integer getVarTypeId(){
        return varTypeId;
    }

    public void setVarTypeId(Integer varTypeId){
        this.varTypeId = varTypeId;
    }

    public String getVarTypeName(){
        return varTypeName;
    }

    public void setVarTypeName(String varTypeName){
        this.varTypeName = varTypeName;
    }

    public Long getClassId(){
        return classId;
    }

    public void setClassId(Long classId){
        this.classId = classId;
    }

    public String getClassName(){
        return className;
    }

    public void setClassName(String className){
        this.className = className;
    }

    public String getClassPackageName(){
        return classPackageName;
    }

    public void setClassPackageName(String classPackageName){
        this.classPackageName = classPackageName;
    }

    public Timestamp getCreateTime(){
        return createTime;
    }

    public void setCreateTime(Timestamp createTime){
        this.createTime = createTime;
    }

    public Timestamp getLastModifyTime(){
        return lastModifyTime;
    }

    public void setLastModifyTime(Timestamp lastModifyTime){
        this.lastModifyTime = lastModifyTime;
    }

    public Long getCreateStaffId(){
        return createStaffId;
    }

    public void setCreateStaffId(Long createStaffId){
        this.createStaffId = createStaffId;
    }

    public String getCreateStaffName(){
        return createStaffName;
    }

    public void setCreateStaffName(String createStaffName){
        this.createStaffName = createStaffName;
    }

    public Long getLastModifyStaffId(){
        return lastModifyStaffId;
    }

    public void setLastModifyStaffId(Long lastModifyStaffId){
        this.lastModifyStaffId = lastModifyStaffId;
    }

    public String getLastModifyStaffName(){
        return lastModifyStaffName;
    }

    public void setLastModifyStaffName(String lastModifyStaffName){
        this.lastModifyStaffName = lastModifyStaffName;
    }

}
