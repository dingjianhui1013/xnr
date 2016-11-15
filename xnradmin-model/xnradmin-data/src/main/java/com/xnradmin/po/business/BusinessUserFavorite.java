/**
 *2014年12月14日 下午1:15:42
 */
package com.xnradmin.po.business;

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
 * @author: liubin
 *
 */
@Entity
@Table(name = "business_user_favorite")
public class BusinessUserFavorite implements java.io.Serializable {

	private Long id;

	@Index(name = "idx_business_user_favorite_staffid")
	private Integer staffId;

	@Index(name = "idx_business_user_favorite_clientuserinfoid")
	private Integer clientUserInfoId;

	@Index(name = "idx_business_user_favorite_goodsid")
	private Integer goodsId;

	private Timestamp createTime;

	public String toString() {
		String res = "";
		try {
			res = ReflectHelper.makeToString(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	@Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name = "CREATE_TIME")
	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	@Column(name = "GOODS_ID")
	public Integer getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}

	@Column(name = "STAFF_ID")
	public Integer getStaffId() {
		return staffId;
	}

	public void setStaffId(Integer staffId) {
		this.staffId = staffId;
	}
	
	@Column(name = "CLIENT_USER_INFO_ID")
	public Integer getClientUserInfoId() {
		return clientUserInfoId;
	}

	public void setClientUserInfoId(Integer clientUserInfoId) {
		this.clientUserInfoId = clientUserInfoId;
	}
}
