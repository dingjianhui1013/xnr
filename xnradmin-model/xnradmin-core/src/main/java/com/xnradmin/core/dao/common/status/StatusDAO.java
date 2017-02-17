package com.xnradmin.core.dao.common.status;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Repository;

import com.xnradmin.core.dao.CommonDAO;
import com.xnradmin.po.common.status.Status;

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
@Repository("StatusDAO")
public class StatusDAO{

    @Autowired
    private CommonDAO commonDao;

    private static final Logger log = LoggerFactory
            .getLogger(StatusDAO.class);

    // property constants
    public static final String STATUS_NAME = "statusName";

    protected void initDao(){
        // do nothing
    }

    public void save(Status transientInstance){
        log.debug("saving Status instance");
        try{
            commonDao.save(transientInstance);
            log.debug("save successful");
        }catch(RuntimeException re){
            log.error("save failed",re);
            throw re;
        }
    }

    public void delete(Status persistentInstance){
        log.debug("deleting Status instance");
        try{
            commonDao.delete(persistentInstance);
            log.debug("delete successful");
        }catch(RuntimeException re){
            log.error("delete failed",re);
            throw re;
        }
    }

    public Status findById(java.lang.Integer id){
        log.debug("getting Status instance with id: " + id);
        try{
            return (Status) commonDao.findById(
            		Status.class,id);
        }catch(RuntimeException re){
            log.error("get failed",re);
            throw re;
        }
    }

    public List<Status> findByExample(Status instance){
        log.debug("finding Status instance by example");
        try{
            return commonDao.getEntitesForExample(instance,0,0);
        }catch(RuntimeException re){
            log.error("find by example failed",re);
            throw re;
        }
    }

    public List findByProperty(String propertyName,Object value){
        log.debug("finding Status instance with property: "
                + propertyName + ", value: " + value);
        try{
            String queryString = "from Status as model where model."
                    + propertyName + "= ?";
            return commonDao.getEntitiesByPropertiesWithHql(queryString,0,0,
                    value);
        }catch(RuntimeException re){
            log.error("find by property name failed",re);
            throw re;
        }
    }

    public List<Status> findByStatusName(Object statusName){
        return findByProperty(STATUS_NAME,statusName);
    }

    public List findAll(){
        log.debug("finding all Status instances");
        try{
            String queryString = "from Status";
            return commonDao.getEntitiesByPropertiesWithHql(queryString,0,0);
        }catch(RuntimeException re){
            log.error("find all failed",re);
            throw re;
        }
    }

    public Status merge(Status detachedInstance){
        log.debug("merging Status instance");
        try{
        	Status result = (Status) commonDao
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        }catch(RuntimeException re){
            log.error("merge failed",re);
            throw re;
        }
    }

    public void attachDirty(Status instance){
        log.debug("attaching dirty Status instance");
        try{
            commonDao.saveOrUpdate(instance);
            log.debug("attach successful");
        }catch(RuntimeException re){
            log.error("attach failed",re);
            throw re;
        }
    }

    public static StatusDAO getFromApplicationContext(
            ApplicationContext ctx){
        return (StatusDAO) ctx.getBean("CommonStaffStatusDAO");
    }
}