package com.xnradmin.po.common.status;


import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.cntinker.util.ReflectHelper;

/**
 * CommonMenu entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "status")
public class Status implements java.io.Serializable{

    // Fields

    private Integer id;

    private String statusName;

    private String description;

    private String className;
    
    private Integer sort;
    
    private String statusCode;
    
    private String readme;

    public String toString() {
		String res = "";
		try {
			res = ReflectHelper.makeToString(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
    
    // Constructors

    /** default constructor */
    public Status(){

    }

    /** minimal constructor */
    public Status(Integer id){
        this.id = id;
    }

    /** full constructor */
    public Status(Integer id,String statusName,String description){
        this.id = id;
        this.statusName = statusName;
        this.description = description;
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

    @Column(name = "STATUS_NAME", length = 50)
    public String getStatusName(){
        return this.statusName;
    }

    public void setStatusName(String statusName){
        this.statusName = statusName;
    }

    public String getClassName(){
        return className;
    }

    public void setClassName(String className){
        this.className = className;
    }

    @Column(name = "DESCTIPTION", length = 100)
    public String getDescription(){
        return description;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public Integer getSort(){
        return sort;
    }

    public void setSort(Integer sort){
        this.sort = sort;
    }

    public String getStatusCode(){
        return statusCode;
    }

    public void setStatusCode(String statusCode){
        this.statusCode = statusCode;
    }

	public String getReadme() {
		return readme;
	}

	public void setReadme(String readme) {
		this.readme = readme;
	}

}