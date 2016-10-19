/**
 * 2013-7-16 下午4:07:49
 */
package com.xnradmin.dto;


import java.io.Serializable;

import com.cntinker.util.ReflectHelper;

/**
 * @autohr: bin_liu
 */
public class MongoDBSource implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private String address;

    private String port;

    private String usernmae;

    private String password;

    private String database;

    private Integer socketTimeout;

    private Integer connectTimeout;

    private Integer maxWaitTime;

    private Integer connectionsPerHost;

    private boolean autoConnectRetry;

    private boolean slaveOk;

    private boolean safe;

    private String mappackage;

    public String toString(){
        String res = "";
        try{
            res = ReflectHelper.makeToString(this);
        }catch(Exception e){
            e.printStackTrace();
        }
        return res;
    }

    public String getAddress(){
        return address;
    }

    public void setAddress(String address){
        this.address = address;
    }

    public String getPort(){
        return port;
    }

    public void setPort(String port){
        this.port = port;
    }

    public String getUsernmae(){
        return usernmae;
    }

    public void setUsernmae(String usernmae){
        this.usernmae = usernmae;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getDatabase(){
        return database;
    }

    public void setDatabase(String database){
        this.database = database;
    }

    public Integer getSocketTimeout(){
        return socketTimeout;
    }

    public void setSocketTimeout(Integer socketTimeout){
        this.socketTimeout = socketTimeout;
    }

    public Integer getConnectTimeout(){
        return connectTimeout;
    }

    public void setConnectTimeout(Integer connectTimeout){
        this.connectTimeout = connectTimeout;
    }

    public Integer getMaxWaitTime(){
        return maxWaitTime;
    }

    public void setMaxWaitTime(Integer maxWaitTime){
        this.maxWaitTime = maxWaitTime;
    }

    public Integer getConnectionsPerHost(){
        return connectionsPerHost;
    }

    public void setConnectionsPerHost(Integer connectionsPerHost){
        this.connectionsPerHost = connectionsPerHost;
    }

    public boolean getAutoConnectRetry(){
        return autoConnectRetry;
    }

    public void setAutoConnectRetry(boolean autoConnectRetry){
        this.autoConnectRetry = autoConnectRetry;
    }

    public boolean getSlaveOk(){
        return slaveOk;
    }

    public void setSlaveOk(boolean slaveOk){
        this.slaveOk = slaveOk;
    }

    public boolean getSafe(){
        return safe;
    }

    public void setSafe(boolean safe){
        this.safe = safe;
    }

    public String getMappackage(){
        return mappackage;
    }

    public void setMappackage(String mappackage){
        this.mappackage = mappackage;
    }

}
