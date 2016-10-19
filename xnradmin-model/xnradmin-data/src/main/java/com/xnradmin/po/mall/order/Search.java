package com.xnradmin.po.mall.order;


import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * select * from phone_local where position(concat(id) in (select fid from test))!=0
 */
@Entity
@Table(name = "search")
public class Search implements java.io.Serializable{

    // Fields

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
    
	private Integer clientUserId; //用户ID
	
	private String searchValue; //搜索文字
    
    private Timestamp searchTime; //搜索时间 
    
    /** default constructor */
    public Search(){
    }

    // Property accessors
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    public Long getId(){
        return this.id;
    }

    public void setId(Long id){
        this.id = id;
    }

	@Column(name = "CLIENT_USER_ID")
	public Integer getClientUserId() {
		return clientUserId;
	}

	public void setClientUserId(Integer clientUserId) {
		this.clientUserId = clientUserId;
	}

	@Column(name = "SEARCH_VALUE")
	public String getSearchValue() {
		return searchValue;
	}

	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}

	public Timestamp getSearchTime() {
		return searchTime;
	}

	public void setSearchTime(Timestamp searchTime) {
		this.searchTime = searchTime;
	}

	
}