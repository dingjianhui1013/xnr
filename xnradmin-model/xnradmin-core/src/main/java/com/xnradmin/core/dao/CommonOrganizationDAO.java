package com.xnradmin.core.dao;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Repository;
import com.xnradmin.po.CommonOrganization;

/**
 * A data access object (DAO) providing persistence and search support for
 * CommonOrganization entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.heinsaw.po.CommonOrganization
 * @author MyEclipse Persistence Tools
 */
@Repository("CommonOrganizationDAO")
public class CommonOrganizationDAO{

    @Autowired
    private CommonDAO commonDao;

    private static final Logger log = LoggerFactory
            .getLogger(CommonOrganizationDAO.class);

    // property constants
    public static final String PRAENT_ORGANIZATION_ID = "praentOrganizationId";

    public static final String ORGANIZATION_NAME = "organizationName";

    public static final String ORGANIZATION_TYPE_ID = "organizationTypeId";

    protected void initDao(){
        // do nothing
    }

    public void save(CommonOrganization transientInstance){
        log.debug("saving CommonOrganization instance");
        try{
            commonDao.save(transientInstance);
            log.debug("save successful");
        }catch(RuntimeException re){
            log.error("save failed",re);
            throw re;
        }
    }

    public void delete(CommonOrganization persistentInstance){
        log.debug("deleting CommonOrganization instance");
        try{
            commonDao.delete(persistentInstance);
            log.debug("delete successful");
        }catch(RuntimeException re){
            log.error("delete failed",re);
            throw re;
        }
    }

    public CommonOrganization findById(java.lang.Integer id){
        log.debug("getting CommonOrganization instance with id: " + id);
        try{

            return (CommonOrganization) commonDao.findById(
                    CommonOrganization.class,id);
        }catch(RuntimeException re){
            log.error("get failed",re);
            throw re;
        }
    }

    public List<CommonOrganization> findByExample(CommonOrganization instance){
        log.debug("finding CommonOrganization instance by example");
        try{

            return commonDao.getEntitesForExample(instance,0,0);
        }catch(RuntimeException re){
            log.error("find by example failed",re);
            throw re;
        }
    }

    public List findByProperty(String propertyName,Object value){
        log.debug("finding CommonOrganization instance with property: "
                + propertyName + ", value: " + value);
        try{
            String queryString = "from CommonOrganization as model where model."
                    + propertyName + "= ?";
            return commonDao.getEntitiesByPropertiesWithHql(queryString,0,0,
                    value);
        }catch(RuntimeException re){
            log.error("find by property name failed",re);
            throw re;
        }
    }

    public List<CommonOrganization> findByPraentOrganizationId(
            Object praentOrganizationId){
        return findByProperty(PRAENT_ORGANIZATION_ID,praentOrganizationId);
    }

    public List<CommonOrganization> findByOrganizationTypeId(
            Object organizationTypeId){
        return findByProperty(ORGANIZATION_TYPE_ID,organizationTypeId);
    }

    public List<CommonOrganization> findByOrganizationName(
            Object organizationName){
        return findByProperty(ORGANIZATION_NAME,organizationName);
    }

    public List findAll(){
        log.debug("finding all CommonOrganization instances");
        try{
            String queryString = "from CommonOrganization";
            return commonDao.getEntitiesByPropertiesWithHql(queryString,0,0);
        }catch(RuntimeException re){
            log.error("find all failed",re);
            throw re;
        }
    }

    public CommonOrganization merge(CommonOrganization detachedInstance){
        log.debug("merging CommonOrganization instance");
        try{
            CommonOrganization result = (CommonOrganization) commonDao
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        }catch(RuntimeException re){
            log.error("merge failed",re);
            throw re;
        }
    }

    public void attachDirty(CommonOrganization instance){
        log.debug("attaching dirty CommonOrganization instance");
        try{
            commonDao.saveOrUpdate(instance);
            log.debug("attach successful");
        }catch(RuntimeException re){
            log.error("attach failed",re);
            throw re;
        }
    }

    public static CommonOrganizationDAO getFromApplicationContext(
            ApplicationContext ctx){
        return (CommonOrganizationDAO) ctx.getBean("CommonOrganizationDAO");
    }
}