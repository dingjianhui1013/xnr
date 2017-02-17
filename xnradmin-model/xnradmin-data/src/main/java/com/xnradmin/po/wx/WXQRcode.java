/**
 *2014年9月11日 下午9:04:33
 */
package com.xnradmin.po.wx;

import static javax.persistence.GenerationType.IDENTITY;

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
@Table(name = "wx_qrcode")
public class WXQRcode implements java.io.Serializable {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	private Long id;

	@Index(name = "idx_wx_qrcode_staffid")
	private Integer staffId;

	private Long attachId;

	private String realPath;

	private String ticket;

	private String url;
	@Index(name = "idx_wx_qrcode_wxuserid")
	private Long wxuserid;

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

	public Integer getStaffId() {
		return staffId;
	}

	public Long getAttachId() {
		return attachId;
	}

	public String getRealPath() {
		return realPath;
	}

	public String getTicket() {
		return ticket;
	}

	public String getUrl() {
		return url;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setStaffId(Integer staffId) {
		this.staffId = staffId;
	}

	public void setAttachId(Long attachId) {
		this.attachId = attachId;
	}

	public void setRealPath(String realPath) {
		this.realPath = realPath;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Long getWxuserid() {
		return wxuserid;
	}

	public void setWxuserid(Long wxuserid) {
		this.wxuserid = wxuserid;
	}

}
