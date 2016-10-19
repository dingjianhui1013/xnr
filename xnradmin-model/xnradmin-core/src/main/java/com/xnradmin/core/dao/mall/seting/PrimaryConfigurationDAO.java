package com.xnradmin.core.dao.mall.seting;

import java.io.Serializable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Repository;

import com.cntinker.util.StringHelper;
import com.xnradmin.core.dao.CommonDAO;
import com.xnradmin.po.mall.seting.PrimaryConfiguration;


@Repository("PrimaryConfigurationDAO")
public class PrimaryConfigurationDAO{
    
    @Autowired
    private CommonDAO commonDao;
    
    private static final Logger log = LoggerFactory
            .getLogger(PrimaryConfigurationDAO.class);

    // property constants

    public static final String ID="id";
    public static final String MALL_DEMO_URL="mallDemoUrl";//商城预览地址
    public static final String MALL_NAME="mallName";//商城名称
    public static final String MALL_LOGO="mallLogo";//商城logo图
    public static final String MALL_BACKGROUND="mallBackground";//商城背景
    public static final String MALL_BACKGROUND_STATUS="mallBackgroundStatus";//是否开启背景
    public static final String ADDRESS="address";//联系地址
    public static final String MOBILE="mobile";//联系电话
    public static final String CODE="code";//邮编
    public static final String EMAIL="email";//邮箱地址
    public static final String MALL_INTRODUCTION="mallIntroduction";//商城介绍
    public static final String STAFF_ID="staffId";	//隶属用户Id
    public static final String CREATE_TIME="createTime";//建立时间
    public static final String CREATE_STAFF_ID="createStaffId";//建立人
    public static final String MODIFY_TIME="modifyTime";//修改时间
    public static final String MODIFY_STAFF_ID="modifyStaffId";//修改人

    protected void initDao(){
        // do nothing
    }

    public Serializable save(PrimaryConfiguration primaryConfiguration){
        log.debug("saving PrimaryConfiguration instance");
        Serializable cls;
        try{
        	cls = commonDao.save(primaryConfiguration);
            log.debug("save successful");
        }catch(RuntimeException re){
            log.error("save failed",re);
            throw re;
        }
        return cls;
    }

    public void delete(PrimaryConfiguration primaryConfiguration){
        log.debug("deleting PrimaryConfiguration instance");
        try{
            commonDao.delete(primaryConfiguration);
            log.debug("delete successful");
        }catch(RuntimeException re){
            log.error("delete failed",re);
            throw re;
        }
    }

    public PrimaryConfiguration findById(Integer id){
        log.debug("getting Primary instance with id: " + id);
        try{

            return (PrimaryConfiguration) commonDao.findById(
            		PrimaryConfiguration.class,id);
        }catch(RuntimeException re){
            log.error("get failed",re);
            throw re;
        }
    }

    public List<PrimaryConfiguration> findByExample(PrimaryConfiguration instance){
        log.debug("finding PrimaryConfiguration instance by example");
        try{

            return commonDao.getEntitesForExample(instance,0,0);
        }catch(RuntimeException re){
            log.error("find by example failed",re);
            throw re;
        }
    }

    public List findByProperty(String propertyName,Object value){
        log.debug("finding Primary instance with property: "
                + propertyName + ", value: " + value);
        try{
            String queryString = "from PrimaryConfiguration as model where model."
                    + propertyName + "= ?";
            return commonDao.getEntitiesByPropertiesWithHql(queryString,0,0,value);
        }catch(RuntimeException re){
            log.error("find by property name failed",re);
            throw re;
        }
    }

    public List findAll(){
        log.debug("finding all PrimaryConfiguration instances");
        try{
            String queryString = "from PrimaryConfiguration";
            return commonDao.getEntitiesByPropertiesWithHql(queryString,0,0);
        }catch(RuntimeException re){
            log.error("find all failed",re);
            throw re;
        }
    }

    public PrimaryConfiguration merge(PrimaryConfiguration detachedInstance){
        log.debug("merging Primary instance");
        try{
        	PrimaryConfiguration result = (PrimaryConfiguration) commonDao
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        }catch(RuntimeException re){
            log.error("merge failed",re);
            throw re;
        }
    }

    public void attachDirty(PrimaryConfiguration instance){
        log.debug("attaching dirty Primary instance");
        try{
            commonDao.saveOrUpdate(instance);
            log.debug("attach successful");
        }catch(RuntimeException re){
            log.error("attach failed",re);
            throw re;
        }
    }

    
    public int removePrimaryConfigurationId(Integer id){

        log.debug("removePrimaryId: " + id);
        try{
            String queryString = "delete  from PrimaryConfiguration as model where model.id = "
                    + id;
            return commonDao.executeUpdateOrDelete(queryString);
        }catch(RuntimeException re){
            log.error("removePrimaryId failed",re);
            throw re;
        }
    }
    
    
    public static PrimaryConfigurationDAO getFromApplicationContext(
            ApplicationContext ctx){
        return (PrimaryConfigurationDAO) ctx.getBean("PrimaryConfigurationDAO");
    }
}