package com.xnradmin.core.dao;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Repository;

import com.xnradmin.po.sms.SmsRecord;

/**
 * A data access object (DAO) providing persistence and search support for
 * CommonOrganization entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.xnradmin.po.client.ClientUserInfoAction.po.ClientUserInfo
 * @author MyEclipse Persistence Tools
 */
@Repository("SmsRecordDAO")
public class SmsRecordDAO{
    private static final Logger log = LoggerFactory
            .getLogger(SmsRecordDAO.class);
    
    @Autowired
    private CommonDAO commonDao;

    // property constants
    
    public static final String ID = "id";
    
    public static final String UID="uid";// 用户uid

    public static final String PHONE="phone"; // 用户手机号码

    public static final String CONTENT="content"; // 发送内容

    public static final String MT_TIME="mtTime"; // 发送时间

    public static final String MSGID="msgID";// 下发唯一id

    public static final String STATUS_TIME="status_time"; // 发送时间

    public static final String STATUS="status"; // 状态
    
    protected void initDao(){
        // do nothing
    }

    public void save(SmsRecord smsRecord){
        log.debug("saving SmsRecord instance");
        try{
            commonDao.save(smsRecord);
            log.debug("save successful");
        }catch(RuntimeException re){
            log.error("save failed",re);
            throw re;
        }
    }

    public void delete(SmsRecord smsRecord){
        log.debug("deleting SmsRecord instance");
        try{
            commonDao.delete(smsRecord);
            log.debug("delete successful");
        }catch(RuntimeException re){
            log.error("delete failed",re);
            throw re;
        }
    }

    public SmsRecord findById(java.lang.Integer id){
        log.debug("getting SmsRecord instance with id: " + id);
        try{

            return (SmsRecord) commonDao.findById(
            		SmsRecord.class,id);
        }catch(RuntimeException re){
            log.error("get failed",re);
            throw re;
        }
    }

    public List<SmsRecord> findByExample(SmsRecord instance){
        log.debug("finding SmsRecord instance by example");
        try{

            return commonDao.getEntitesForExample(instance,0,0);
        }catch(RuntimeException re){
            log.error("find by example failed",re);
            throw re;
        }
    }

    public List findByProperty(String propertyName,Object value){
        log.debug("finding SmsRecord instance with property: "
                + propertyName + ", value: " + value);
        try{
            String queryString = "from SmsRecord as model where model."
                    + propertyName + "= ?";
            return commonDao.getEntitiesByPropertiesWithHql(queryString,0,0,value);
        }catch(RuntimeException re){
            log.error("find by property name failed",re);
            throw re;
        }
    }

    public List findAll(){
        log.debug("finding all SmsRecord instances");
        try{
            String queryString = "from SmsRecord";
            return commonDao.getEntitiesByPropertiesWithHql(queryString,0,0);
        }catch(RuntimeException re){
            log.error("find all failed",re);
            throw re;
        }
    }

    public SmsRecord merge(SmsRecord detachedInstance){
        log.debug("merging WashCarProduct instance");
        try{
        	SmsRecord result = (SmsRecord) commonDao
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        }catch(RuntimeException re){
            log.error("merge failed",re);
            throw re;
        }
    }

    public void attachDirty(SmsRecord instance){
        log.debug("attaching dirty SmsRecord instance");
        try{
            commonDao.saveOrUpdate(instance);
            log.debug("attach successful");
        }catch(RuntimeException re){
            log.error("attach failed",re);
            throw re;
        }
    }

    
    public int removeSmsRecordId(Integer id){

        log.debug("removeSmsRecordId: " + id);
        try{
            String queryString = "delete  from SmsRecord as model where model.id = "
                    + id;
            return commonDao.executeUpdateOrDelete(queryString);
        }catch(RuntimeException re){
            log.error("removeSmsRecordId failed",re);
            throw re;
        }
    }
    
    public static SmsRecordDAO getFromApplicationContext(
            ApplicationContext ctx){
        return (SmsRecordDAO) ctx.getBean("SmsRecordDAO");
    }
}