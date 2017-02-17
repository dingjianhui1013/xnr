package com.xnradmin.core.dao;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Repository;

import com.xnradmin.po.CommonCustomerContact;

/**
 * A data access object (DAO) providing persistence and search support for
 * CommonCustomerContact entities. Transaction control of the save(), update()
 * and delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.heinsaw.po.CommonCustomerContact
 * @author MyEclipse Persistence Tools
 */
@Repository("CommonCustomerContactDAO")
public class CommonCustomerContactDAO{

    @Autowired
    private CommonDAO dao;

    private static final Logger log = LoggerFactory
            .getLogger(CommonCustomerContactDAO.class);

    // property constants
    public static final String CUSTOMER_ID = "customerId";

    public static final String CONTACT_ID = "contactId";

    protected void initDao(){
        // do nothing
    }

    public void save(CommonCustomerContact transientInstance){
        log.debug("saving CommonCustomerContact instance");
        try{
            dao.save(transientInstance);
            log.debug("save successful");
        }catch(RuntimeException re){
            log.error("save failed",re);
            throw re;
        }
    }

    public void delete(CommonCustomerContact persistentInstance){
        log.debug("deleting CommonCustomerContact instance");
        try{
            dao.delete(persistentInstance);
            log.debug("delete successful");
        }catch(RuntimeException re){
            log.error("delete failed",re);
            throw re;
        }
    }

    public CommonCustomerContact findById(java.lang.Integer id){
        log.debug("getting CommonCustomerContact instance with id: " + id);
        try{
            CommonCustomerContact instance = (CommonCustomerContact) dao
                    .findById(CommonCustomerContact.class,id);
            return instance;
        }catch(RuntimeException re){
            log.error("get failed",re);
            throw re;
        }
    }

    public List<CommonCustomerContact> findByExample(
            CommonCustomerContact instance){
        log.debug("finding CommonCustomerContact instance by example");
        try{
            List<CommonCustomerContact> results = (List<CommonCustomerContact>) dao
                    .getEntitesForExample(instance,0,0);
            log.debug("find by example successful, result size: "
                    + results.size());
            return results;
        }catch(RuntimeException re){
            log.error("find by example failed",re);
            throw re;
        }
    }

    public List findByProperty(String propertyName,Object value){
        log.debug("finding CommonCustomerContact instance with property: "
                + propertyName + ", value: " + value);
        try{
            String queryString = "from CommonCustomerContact as model where model."
                    + propertyName + "= ?";
            return dao.getEntitiesByPropertiesWithHql(queryString,0,0);
        }catch(RuntimeException re){
            log.error("find by property name failed",re);
            throw re;
        }
    }

    public List<CommonCustomerContact> findByCustomerId(Object customerId){
        return findByProperty(CUSTOMER_ID,customerId);
    }

    public List<CommonCustomerContact> findByContactId(Object contactId){
        return findByProperty(CONTACT_ID,contactId);
    }

    public List findAll(){
        log.debug("finding all CommonCustomerContact instances");
        try{
            String queryString = "from CommonCustomerContact";
            return dao.getEntitiesByPropertiesWithHql(queryString,0,0);
        }catch(RuntimeException re){
            log.error("find all failed",re);
            throw re;
        }
    }

    public CommonCustomerContact merge(CommonCustomerContact detachedInstance){
        log.debug("merging CommonCustomerContact instance");
        try{
            CommonCustomerContact result = (CommonCustomerContact) dao
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        }catch(RuntimeException re){
            log.error("merge failed",re);
            throw re;
        }
    }

    public void attachDirty(CommonCustomerContact instance){
        log.debug("attaching dirty CommonCustomerContact instance");
        try{
            dao.saveOrUpdate(instance);
            log.debug("attach successful");
        }catch(RuntimeException re){
            log.error("attach failed",re);
            throw re;
        }
    }

    public static CommonCustomerContactDAO getFromApplicationContext(
            ApplicationContext ctx){
        return (CommonCustomerContactDAO) ctx
                .getBean("CommonCustomerContactDAO");
    }
}