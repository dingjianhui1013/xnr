/**
 *2015年1月31日 下午1:20:49
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
@Table(name = "quesion_log")
public class QuesionLog implements java.io.Serializable {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	private Long id;

	private Long quesionId;

	private Integer modifyStaff;

	private Timestamp modifyTime;

	@Column(name = "quesion_description", length = 5000)
	private String quesion_description;

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

	public Long getQuesionId() {
		return quesionId;
	}

	public Integer getModifyStaff() {
		return modifyStaff;
	}

	public Timestamp getModifyTime() {
		return modifyTime;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setQuesionId(Long quesionId) {
		this.quesionId = quesionId;
	}

	public void setModifyStaff(Integer modifyStaff) {
		this.modifyStaff = modifyStaff;
	}

	public void setModifyTime(Timestamp modifyTime) {
		this.modifyTime = modifyTime;
	}

	public String getQuesion_description() {
		return quesion_description;
	}

	public void setQuesion_description(String quesion_description) {
		this.quesion_description = quesion_description;
	}
}
