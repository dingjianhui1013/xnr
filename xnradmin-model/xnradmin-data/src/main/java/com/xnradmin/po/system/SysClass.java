/**
 * 2013-7-21 下午2:01:51
 */
package com.xnradmin.po.system;


import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @autohr: bin_liu
 */
@Entity
@Table(name = "sys_class")
public class SysClass{

    private Integer id;

    private String className;

    private String packageName;

    private Integer itisInterface;

    private Integer itisPO;

    private Integer itisDTO;

    private Integer itisQO;

    private Integer itisVO;

    private Integer itisHibernatePO;

    private String hibernatePOTableName;

    private String tempFile;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    public Integer getId(){
        return id;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public String getClassName(){
        return className;
    }

    public void setClassName(String className){
        this.className = className;
    }

    public String getPackageName(){
        return packageName;
    }

    public void setPackageName(String packageName){
        this.packageName = packageName;
    }

    public Integer getItisInterface(){
        return itisInterface;
    }

    public void setItisInterface(Integer itisInterface){
        this.itisInterface = itisInterface;
    }

    public Integer getItisPO(){
        return itisPO;
    }

    public void setItisPO(Integer itisPO){
        this.itisPO = itisPO;
    }

    public Integer getItisDTO(){
        return itisDTO;
    }

    public void setItisDTO(Integer itisDTO){
        this.itisDTO = itisDTO;
    }

    public Integer getItisQO(){
        return itisQO;
    }

    public void setItisQO(Integer itisQO){
        this.itisQO = itisQO;
    }

    public Integer getItisVO(){
        return itisVO;
    }

    public void setItisVO(Integer itisVO){
        this.itisVO = itisVO;
    }

    public Integer getItisHibernatePO(){
        return itisHibernatePO;
    }

    public void setItisHibernatePO(Integer itisHibernatePO){
        this.itisHibernatePO = itisHibernatePO;
    }

    public String getHibernatePOTableName(){
        return hibernatePOTableName;
    }

    public void setHibernatePOTableName(String hibernatePOTableName){
        this.hibernatePOTableName = hibernatePOTableName;
    }

    public String getTempFile(){
        return tempFile;
    }

    public void setTempFile(String tempFile){
        this.tempFile = tempFile;
    }
}
