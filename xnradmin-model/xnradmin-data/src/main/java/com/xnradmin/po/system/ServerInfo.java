/**
 * 2013-7-21 下午1:21:51
 */
package com.xnradmin.po.system;


import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @autohr: bin_liu
 */
@Entity
@Table(name = "sys_serverinfo")
public class ServerInfo{

    private Integer id;

    private String serverName;

    private String connectionUser;

    private String connectionPswd;

    private String serverIp;

    private String serverPort;

    private String remark;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    public Integer getId(){
        return id;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public String getServerName(){
        return serverName;
    }

    public void setServerName(String serverName){
        this.serverName = serverName;
    }

    public String getConnectionUser(){
        return connectionUser;
    }

    public void setConnectionUser(String connectionUser){
        this.connectionUser = connectionUser;
    }

    public String getConnectionPswd(){
        return connectionPswd;
    }

    public void setConnectionPswd(String connectionPswd){
        this.connectionPswd = connectionPswd;
    }

    public String getServerIp(){
        return serverIp;
    }

    public void setServerIp(String serverIp){
        this.serverIp = serverIp;
    }

    public String getServerPort(){
        return serverPort;
    }

    public void setServerPort(String serverPort){
        this.serverPort = serverPort;
    }

    public String getRemark(){
        return remark;
    }

    public void setRemark(String remark){
        this.remark = remark;
    }

}
