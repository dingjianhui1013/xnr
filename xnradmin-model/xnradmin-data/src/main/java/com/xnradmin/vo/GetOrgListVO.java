/**
 *2012-4-23 上午02:02:34
 */
package com.xnradmin.vo;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @autohr: bin_liu
 */
@XmlRootElement
public class GetOrgListVO implements Serializable {

	/**
     * 
     */
	private static final long serialVersionUID = 1L;

	private String orgid;

	private String orgname;

	private String porgid;

	private String porgname;

	private String type;

	public String getOrgid() {
		return orgid;
	}

	public void setOrgid(String orgid) {
		this.orgid = orgid;
	}

	public String getOrgname() {
		return orgname;
	}

	public void setOrgname(String orgname) {
		this.orgname = orgname;
	}

	public String getPorgid() {
		return porgid;
	}

	public void setPorgid(String porgid) {
		this.porgid = porgid;
	}

	public String getPorgname() {
		return porgname;
	}

	public void setPorgname(String porgname) {
		this.porgname = porgname;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("GetOrgListVO[");
		sb.append(" orgid:").append(orgid);
		sb.append(",orgname:").append(orgname);
		sb.append(",porgid:").append(porgid);
		sb.append(",porgname:").append(porgname);
		sb.append(",type:").append(type);
		sb.append("]");
		return sb.toString();
	}

}
