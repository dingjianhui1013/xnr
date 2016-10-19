/**
 *2014年2月9日 下午11:48:20
*/
package com.xnradmin.po.client;

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
 * @author: bin_liu
 *
 */
@Entity
@Table(name = "client_user_login_log")
public class ClientUserLoginLog implements java.io.Serializable{

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    private Long id;

    @Index(name = "idx_client_user_reg_moblie")
    private String mobile;

    @Index(name = "idx_client_user_reg_linkid")
    private String linkid;

    private String validCode;        

    private String content;

    private String udid;

    private Timestamp loginTime;        

    public String toString(){
        String res = "";
        try{
            res = ReflectHelper.makeToString(this);
        }catch(Exception e){
            e.printStackTrace();
        }
        return res;
    }

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getMobile(){
        return mobile;
    }

    public void setMobile(String mobile){
        this.mobile = mobile;
    }

    public String getLinkid(){
        return linkid;
    }

    public void setLinkid(String linkid){
        this.linkid = linkid;
    }

    public String getValidCode(){
        return validCode;
    }

    public void setValidCode(String validCode){
        this.validCode = validCode;
    }

    public String getContent(){
        return content;
    }

    public void setContent(String content){
        this.content = content;
    }

    public String getUdid(){
        return udid;
    }

    public void setUdid(String udid){
        this.udid = udid;
    }

    public Timestamp getLoginTime(){
        return loginTime;
    }

    public void setLoginTime(Timestamp loginTime){
        this.loginTime = loginTime;
    }
}
