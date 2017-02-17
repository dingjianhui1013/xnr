/**
 * 2009-11-17 2:46:09
 */
package com.xnradmin.core.cache;


import java.util.Date;

import com.cntinker.util.StringHelper;
import com.danga.MemCached.MemCachedClient;
import com.danga.MemCached.SockIOPool;

/**
 * @autohr: bin_liu
 */
public class MemCachedBase{

    private static MemCachedClient mcc;

    private static SockIOPool pool;

    public MemCachedBase(){

    }

    protected static MemCachedBase memCachedManager = new MemCachedBase();

    public void shutDown(){
        pool.shutDown();
    }

    public void init(){
        // 获取socke连接池的实例对象
        pool = SockIOPool.getInstance();

        String[] serverList = StringHelper.splitStr(servers,",");
        String[] weightList = StringHelper.splitStr(weights,",");

        Integer[] wList = StringHelper.strToInt(weightList);

        // 设置服务器信息
        pool.setServers(serverList);
        pool.setWeights(wList);

        // 设置初始连接数、最小和最大连接数以及最大处理时间
        pool.setInitConn(initConn);
        pool.setMinConn(minConn);
        pool.setMaxConn(maxConn);
        pool.setMaxIdle(maxIdle);

        // 设置主线程的睡眠时间
        pool.setMaintSleep(maintSleep);

        // 设置TCP的参数，连接超时等
        pool.setNagle(nagle);
        pool.setSocketTO(socketTO);
        pool.setSocketConnectTO(socketConnectTO);
        pool.setAliveCheck(aliveCheck);
        // 初始化连接池
        pool.initialize();
        mcc = new MemCachedClient();
    }

    private String servers;

    private String weights;

    private Integer initConn;

    private Integer minConn;

    private Integer maxConn;

    private Integer maxIdle;

    private Integer maintSleep;

    private boolean nagle;

    private Integer socketTO;

    private Integer socketConnectTO;

    private boolean aliveCheck;

    private Long defaultExptime;

    public boolean isAliveCheck(){
        return aliveCheck;
    }

    public void setAliveCheck(boolean aliveCheck){
        this.aliveCheck = aliveCheck;
    }

    public String getServers(){
        return servers;
    }

    public void setServers(String servers){
        this.servers = servers;
    }

    public boolean set(String key,Object value){
        return set(key,value,new Date(new Date().getTime() + defaultExptime));
    }

    public synchronized boolean set(String key,Object value,Date expiry){
        return mcc.set(key,value,expiry);
    }

    public boolean add(String key,Object value){
        return add(key,value,new Date(new Date().getTime() + defaultExptime));
    }

    public boolean add(String key,Object value,Date expiry){
        return mcc.add(key,value,expiry);
    }

    public synchronized boolean replace(String key,Object value){
        return replace(key,value,
                new Date(new Date().getTime() + defaultExptime));
    }

    public synchronized boolean replace(String key,Object value,Date expiry){
        return mcc.replace(key,value,expiry);
    }

    public synchronized boolean delete(String key){
        return mcc.delete(key);
    }

    /**
     * 根据指定的关键字获取对象.
     * 
     * @param key
     * @return
     */
    public Object get(String key){
        return mcc.get(key);
    }

    public boolean isExists(String key){
        return mcc.keyExists(key);
    }

    public Integer getInitConn(){
        return initConn;
    }

    public void setInitConn(Integer initConn){
        this.initConn = initConn;
    }

    public MemCachedClient getMcc(){
        return mcc;
    }

    public void setMcc(MemCachedClient mcc){
        this.mcc = mcc;
    }

    public String getWeights(){
        return weights;
    }

    public void setWeights(String weights){
        this.weights = weights;
    }

    public Integer getMinConn(){
        return minConn;
    }

    public void setMinConn(Integer minConn){
        this.minConn = minConn;
    }

    public Integer getMaxConn(){
        return maxConn;
    }

    public void setMaxConn(Integer maxConn){
        this.maxConn = maxConn;
    }

    public Integer getMaxIdle(){
        return maxIdle;
    }

    public void setMaxIdle(Integer maxIdle){
        this.maxIdle = maxIdle;
    }

    public Integer getMaintSleep(){
        return maintSleep;
    }

    public void setMaintSleep(Integer maintSleep){
        this.maintSleep = maintSleep;
    }

    public boolean isNagle(){
        return nagle;
    }

    public void setNagle(boolean nagle){
        this.nagle = nagle;
    }

    public Integer getSocketTO(){
        return socketTO;
    }

    public void setSocketTO(Integer socketTO){
        this.socketTO = socketTO;
    }

    public Integer getSocketConnectTO(){
        return socketConnectTO;
    }

    public void setSocketConnectTO(Integer socketConnectTO){
        this.socketConnectTO = socketConnectTO;
    }

    public Long getDefaultExptime(){
        return defaultExptime;
    }

    public void setDefaultExptime(Long defaultExptime){
        this.defaultExptime = defaultExptime;
    }

}
