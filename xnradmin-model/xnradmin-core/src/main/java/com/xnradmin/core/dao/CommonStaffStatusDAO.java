package com.xnradmin.core.dao;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Repository;

import com.xnradmin.po.CommonStaffStatus;

/**
 * A data access object (DAO) providing persistence and search support for
 * CommonStaffStatus entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.heinsaw.po.CommonStaffStatus
 * @author MyEclipse Persistence Tools
 */
@Repository("CommonStaffStatusDAO")
public class CommonStaffStatusDAO{

    @Autowired
    private CommonDAO commonDao;

    private static final Logger log = LoggerFactory
            .getLogger(CommonStaffStatusDAO.class);

    // property constants
    public static final String STATUS_NAME = "statusName";

    protected void initDao(){
        // do nothing
    }

    public void save(CommonStaffStatus transientInstance){
        log.debug("saving CommonStaffStatus instance");
        try{
            commonDao.save(transientInstance);
            log.debug("save successful");
        }catch(RuntimeException re){
            log.error("save failed",re);
            throw re;
        }
    }

    public void delete(CommonStaffStatus persistentInstance){
        log.debug("deleting CommonStaffStatus instance");
        try{
            commonDao.delete(persistentInstance);
            log.debug("delete successful");
        }catch(RuntimeException re){
            log.error("delete failed",re);
            throw re;
        }
    }

    public CommonStaffStatus findById(java.lang.Integer id){
        log.debug("getting CommonStaffStatus instance with id: " + id);
        try{
            return (CommonStaffStatus) commonDao.findById(
                    CommonStaffStatus.class,id);
        }catch(RuntimeException re){
            log.error("get failed",re);
            throw re;
        }
    }

    public List<CommonStaffStatus> findByExample(CommonStaffStatus instance){
        log.debug("finding CommonStaffStatus instance by example");
        try{
            return commonDao.getEntitesForExample(instance,0,0);
        }catch(RuntimeException re){
            log.error("find by example failed",re);
            throw re;
        }
    }

    public List findByProperty(String propertyName,Object value){
        log.debug("finding CommonStaffStatus instance with property: "
                + propertyName + ", value: " + value);
        try{
            String queryString = "from CommonStaffStatus as model where model."
                    + propertyName + "= ?";
            return commonDao.getEntitiesByPropertiesWithHql(queryString,0,0,
                    value);
        }catch(RuntimeException re){
            log.error("find by property name failed",re);
            throw re;
        }
    }

    public List<CommonStaffStatus> findByStatusName(Object statusName){
        return findByProperty(STATUS_NAME,statusName);
    }

    public List findAll(){
        log.debug("finding all CommonStaffStatus instances");
        try{
            String queryString = "from CommonStaffStatus";
            return commonDao.getEntitiesByPropertiesWithHql(queryString,0,0);
        }catch(RuntimeException re){
            log.error("find all failed",re);
            throw re;
        }
    }

    public CommonStaffStatus merge(CommonStaffStatus detachedInstance){
        log.debug("merging CommonStaffStatus instance");
        try{
            CommonStaffStatus result = (CommonStaffStatus) commonDao
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        }catch(RuntimeException re){
            log.error("merge failed",re);
            throw re;
        }
    }

    public void attachDirty(CommonStaffStatus instance){
        log.debug("attaching dirty CommonStaffStatus instance");
        try{
            commonDao.saveOrUpdate(instance);
            log.debug("attach successful");
        }catch(RuntimeException re){
            log.error("attach failed",re);
            throw re;
        }
    }

    public static CommonStaffStatusDAO getFromApplicationContext(
            ApplicationContext ctx){
        return (CommonStaffStatusDAO) ctx.getBean("CommonStaffStatusDAO");
    }
}