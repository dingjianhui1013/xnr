/**
 * 2012-4-28 下午04:50:58
 */
package com.xnradmin.vo;


import java.io.Serializable;

import com.xnradmin.po.CommonOrganization;
import com.xnradmin.po.CommonStaff;
import com.xnradmin.po.CommonStaffRoleRelation;
import com.xnradmin.po.CommonStaffStatus;

/**
 * @autohr: bin_liu
 */
public class StaffVO implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private String userid;

    private String login_id;

    private String staffName;

    public String getStaffName(){
        return staffName;
    }

    public void setStaffName(String staffName){
        this.staffName = staffName;
    }

    private String staffNo;

    private String orgid;

    private String orgname;

    private String porgid;

    private String porgname;

    private String createTime;

    private String mobile;

    private String email;

    private String pswd;

    private String statusid;

    private String statusName;

    private String idcard;

    private String worklife;

    private String workingtime;

    private String bankInformation;
    
    private String discount;
    
    private boolean manager;

    private CommonStaff staff;

    private CommonOrganization org;

    private CommonStaffRoleRelation role;

    private CommonStaffStatus status;

    public String toString(){
        StringBuffer sb = new StringBuffer();
        sb.append("UserListVO:[");
        sb.append(" userid:").append(userid);
        sb.append(",login_id:").append(login_id);
        sb.append(",staffName:").append(staffName);
        sb.append(",orgid:").append(orgid);
        sb.append(",orgname:").append(orgname);
        sb.append(",porgid:").append(porgid);
        sb.append(",porgname:").append(porgname);
        sb.append(",createTime:").append(createTime);
        sb.append(",mobile:").append(mobile);
        sb.append(",email:").append(email);
        sb.append(",pswd:").append(pswd);
        sb.append(",staffNo:").append(staffNo);
        sb.append(",statusid:").append(statusid);
        sb.append(",statusName:").append(statusName);
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

    public String getMobile(){
        return mobile;
    }

    public void setMobile(String mobile){
        this.mobile = mobile;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getPswd(){
        return pswd;
    }

    public void setPswd(String pswd){
        this.pswd = pswd;
    }

    public String getOrgid(){
        return orgid;
    }

    public void setOrgid(String orgid){
        this.orgid = orgid;
    }

    public String getOrgname(){
        return orgname;
    }

    public void setOrgname(String orgname){
        this.orgname = orgname;
    }

    public String getPorgid(){
        return porgid;
    }

    public void setPorgid(String porgid){
        this.porgid = porgid;
    }

    public String getPorgname(){
        return porgname;
    }

    public void setPorgname(String porgname){
        this.porgname = porgname;
    }

    public String getUserid(){
        return userid;
    }

    public void setUserid(String userid){
        this.userid = userid;
    }

    public String getLogin_id(){
        return login_id;
    }

    public void setLogin_id(String login_id){
        this.login_id = login_id;
    }

    public String getCreateTime(){
        return createTime;
    }

    public void setCreateTime(String createTime){
        this.createTime = createTime;
    }

    public String getStaffNo(){
        return staffNo;
    }

    public void setStaffNo(String staffNo){
        this.staffNo = staffNo;
    }

    public String getIdcard(){
        return idcard;
    }

    public void setIdcard(String idcard){
        this.idcard = idcard;
    }

    public String getWorklife(){
        return worklife;
    }

    public void setWorklife(String worklife){
        this.worklife = worklife;
    }

    public String getWorkingtime(){
        return workingtime;
    }

    public void setWorkingtime(String workingtime){
        this.workingtime = workingtime;
    }

    public boolean isManager(){
        return manager;
    }

    public void setManager(boolean manager){
        this.manager = manager;
    }

    public CommonStaff getStaff(){
        return staff;
    }

    public void setStaff(CommonStaff staff){
        this.staff = staff;
    }

    public CommonOrganization getOrg(){
        return org;
    }

    public void setOrg(CommonOrganization org){
        this.org = org;
    }

    public CommonStaffRoleRelation getRole(){
        return role;
    }

    public void setRole(CommonStaffRoleRelation role){
        this.role = role;
    }

    public CommonStaffStatus getStatus(){
        return status;
    }

    public void setStatus(CommonStaffStatus status){
        this.status = status;
    }

	public String getBankInformation() {
		return bankInformation;
	}

	public void setBankInformation(String bankInformation) {
		this.bankInformation = bankInformation;
	}

	public String getDiscount() {
		return discount;
	}

	public void setDiscount(String discount) {
		this.discount = discount;
	}

}
