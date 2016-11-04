/**
 * 2013-7-21 下午2:45:04
 */
package com.xnradmin.po.system;


import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @autohr: bin_liu
 */
@Entity
@Table(name = "sys_log4j")
public class SysLog4j implements Serializable{

    private Long id;

    private String logName;

    private String log4jFile;

    private String outLogPath;
    
    private Timestamp createTime;
    
    private Integer logType;
    
    private String logTypeName;
        

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getLogName(){
        return logName;
    }

    public void setLogName(String logName){
        this.logName = logName;
    }

    public String getLog4jFile(){
        return log4jFile;
    }

    public void setLog4jFile(String log4jFile){
        this.log4jFile = log4jFile;
    }

    public String getOutLogPath(){
        return outLogPath;
    }

    public void setOutLogPath(String outLogPath){
        this.outLogPath = outLogPath;
    }

    public Timestamp getCreateTime(){
        return createTime;
    }

    public void setCreateTime(Timestamp createTime){
        this.createTime = createTime;
    }

    public Integer getLogType(){
        return logType;
    }

    public void setLogType(Integer logType){
        this.logType = logType;
    }

    public String getLogTypeName(){
        return logTypeName;
    }

    public void setLogTypeName(String logTypeName){
        this.logTypeName = logTypeName;
    }

}
