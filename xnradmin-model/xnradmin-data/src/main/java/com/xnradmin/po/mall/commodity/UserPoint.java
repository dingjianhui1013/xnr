/**
 *2014年8月25日 下午5:22:03
 */
package com.xnradmin.po.mall.commodity;

import static javax.persistence.GenerationType.IDENTITY;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Index;

import com.cntinker.util.ReflectHelper;
import com.xnradmin.po.CommonStaff;

/**
 * @author: liubin
 * 
 */
@Entity
@Table(name = "mall_user_point")
public class UserPoint implements java.io.Serializable {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	private Long id;

	public String toString() {
		String res = "";
		try {
			res = ReflectHelper.makeToString(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	@Index(name = "idx_mall_user_point_clientuserinfoid")
	private Long clientUserInfoId;

	// 用户积分
	private Long point;

	// 变动时间
	private Timestamp changeTime;

	// 如果后面有工作人员后台操作账户，则此字段不为空
	private CommonStaff opearStaffId;

	// 操作类型 - 诸如购物，增加5分，评论，增加10分这种逻辑用
	private Integer opearTypeId;

	// 操作类型名称
	private String opearTypeName;

	public Long getId() {
		return id;
	}

	public Long getClientUserInfoId() {
		return clientUserInfoId;
	}

	public Long getPoint() {
		return point;
	}

	public Timestamp getChangeTime() {
		return changeTime;
	}

	public CommonStaff getOpearStaffId() {
		return opearStaffId;
	}

	public Integer getOpearTypeId() {
		return opearTypeId;
	}

	public String getOpearTypeName() {
		return opearTypeName;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setClientUserInfoId(Long clientUserInfoId) {
		this.clientUserInfoId = clientUserInfoId;
	}

	public void setPoint(Long point) {
		this.point = point;
	}

	public void setChangeTime(Timestamp changeTime) {
		this.changeTime = changeTime;
	}

	public void setOpearStaffId(CommonStaff opearStaffId) {
		this.opearStaffId = opearStaffId;
	}

	public void setOpearTypeId(Integer opearTypeId) {
		this.opearTypeId = opearTypeId;
	}

	public void setOpearTypeName(String opearTypeName) {
		this.opearTypeName = opearTypeName;
	}
}
