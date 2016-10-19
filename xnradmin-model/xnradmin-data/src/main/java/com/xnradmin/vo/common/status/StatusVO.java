/**
 * 2012-4-28 下午04:50:58
 */
package com.xnradmin.vo.common.status;


import java.io.Serializable;

import com.xnradmin.po.CommonOrganization;
import com.xnradmin.po.CommonStaff;
import com.xnradmin.po.CommonStaffRoleRelation;
import com.xnradmin.po.CommonStaffStatus;

/**
 * @autohr: bin_liu
 */
public class StatusVO implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private String statusid;

    private String statusName;

    private String description;


    public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String toString(){
        StringBuffer sb = new StringBuffer();
        sb.append("StatusVO:[");
        sb.append("statusid:").append(statusid);
        sb.append(",statusName:").append(statusName);
        sb.append(",description:").append(description);
        sb.append("]");
        return sb.toString();
    }

    public String getStatusid(){
        return statusid;
    }

    public void setStatusid(String statusid){
        this.statusid = statusid;
    }

    public String getStatusName(){
        return statusName;
    }

    public void setStatusName(String statusName){
        this.statusName = statusName;
    }

}
