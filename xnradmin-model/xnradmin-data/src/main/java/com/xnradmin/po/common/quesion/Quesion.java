/**
 *2015年1月30日 下午8:38:08
 */
package com.xnradmin.po.common.quesion;

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
 *
 */
@Entity
@Table(name = "quesion")
public class Quesion implements java.io.Serializable {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	private Long id;

	@Column(name = "quesion_description", length = 5000)
	private String quesion_description;

	private Integer createStaff;

	private Timestamp createTime;

	private Timestamp lastModifyTime;

	private Integer lastModifyStaff;

	public String toString() {
		String res = "";
		try {
			res = ReflectHelper.makeToString(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public Long getId() {
		return id;
	}

	public String getQuesion_description() {
		return quesion_description;
	}

	public Integer getCreateStaff() {
		return createStaff;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public Timestamp getLastModifyTime() {
		return lastModifyTime;
	}

	public Integer getLastModifyStaff() {
		return lastModifyStaff;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setQuesion_description(String quesion_description) {
		this.quesion_description = quesion_description;
	}

	public void setCreateStaff(Integer createStaff) {
		this.createStaff = createStaff;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public void setLastModifyTime(Timestamp lastModifyTime) {
		this.lastModifyTime = lastModifyTime;
	}

	public void setLastModifyStaff(Integer lastModifyStaff) {
		this.lastModifyStaff = lastModifyStaff;
	}

}
