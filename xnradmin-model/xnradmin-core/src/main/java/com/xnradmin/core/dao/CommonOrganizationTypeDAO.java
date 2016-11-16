package com.xnradmin.core.dao;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Repository;

import com.xnradmin.po.CommonOrganizationType;

/**
 * A data access object (DAO) providing persistence and search support for
 * CommonOrganizationType entities. Transaction control of the save(), update()
 * and delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.heinsaw.po.CommonOrganizationType
 * @author MyEclipse Persistence Tools
 */
@Repository("CommonOrganizationTypeDAO")
public class CommonOrganizationTypeDAO {
    private static final Logger log = LoggerFactory
            .getLogger(CommonOrganizationTypeDAO.class);
    
    @Autowired
    private CommonDAO commonDao;
    
    // property constants
    public static final String ORGANIZATION_TYPE_NAME = "organizationTypeName";

    protected void initDao(){
        // do nothing
    }

    public void save(CommonOrganizationType transientInstance){
        log.debug("saving CommonOrganizationType instance");
        try{
            commonDao.save(transientInstance);
            log.debug("save successful");
        }catch(RuntimeException re){
            log.error("save failed",re);
            throw re;
        }
    }

    public void delete(CommonOrganizationType persistentInstance){
        log.debug("deleting CommonOrganizationType instance");
        try{
            commonDao.delete(persistentInstance);
            log.debug("delete successful");
        }catch(RuntimeException re){
            log.error("delete failed",re);
            throw re;
        }
    }

    public CommonOrganizationType findById(java.lang.Integer id){
        log.debug("getting CommonOrganizationType instance with id: " + id);
        try{

            return (CommonOrganizationType) commonDao.findById(
                    CommonOrganizationType.class,id);
        }catch(RuntimeException re){
            log.error("get failed",re);
            throw re;
        }
    }

    public List<CommonOrganizationType> findByExample(
            CommonOrganizationType instance){
        log.debug("finding CommonOrganizationType instance by example");
        try{

            return commonDao.getEntitesForExample(instance,0,0);
        }catch(RuntimeException re){
            log.error("find by example failed",re);
            throw re;
        }
    }

    public List findByProperty(String propertyName,Object value){
        log.debug("finding CommonOrganizationType instance with property: "
                + propertyName + ", value: " + value);
        try{
            String queryString = "from CommonOrganizationType as model where model."
                    + propertyName + "= ?";
            return commonDao.getEntitiesByPropertiesWithHql(queryString,0,0,value);
        }catch(RuntimeException re){
            log.error("find by property name failed",re);
            throw re;
        }
    }

    public List<CommonOrganizationType> findByOrganizationTypeName(
            Object organizationTypeName){
        return findByProperty(ORGANIZATION_TYPE_NAME,organizationTypeName);
    }

    public List findAll(){
        log.debug("finding all CommonOrganizationType instances");
        try{
            String queryString = "from CommonOrganizationType";
            return commonDao.getEntitiesByPropertiesWithHql(queryString,0,0);
        }catch(RuntimeException re){
            log.error("find all failed",re);
            throw re;
        }
    }

    public CommonOrganizationType merge(CommonOrganizationType detachedInstance){
        log.debug("merging CommonOrganizationType instance");
        try{
            CommonOrganizationType result = (CommonOrganizationType) commonDao
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        }catch(RuntimeException re){
            log.error("merge failed",re);
            throw re;
        }
    }

    public void attachDirty(CommonOrganizationType instance){
        log.debug("attaching dirty CommonOrganizationType instance");
        try{
            commonDao.saveOrUpdate(instance);
            log.debug("attach successful");
        }catch(RuntimeException re){
            log.error("attach failed",re);
            throw re;
        }
    }

    public static CommonOrganizationTypeDAO getFromApplicationContext(
            ApplicationContext ctx){
        return (CommonOrganizationTypeDAO) ctx
                .getBean("CommonOrganizationTypeDAO");
    }
}