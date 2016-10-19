package com.xnradmin.core.dao;


import java.io.Serializable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Repository;

import com.xnradmin.po.CommonContact;

/**
 * A data access object (DAO) providing persistence and search support for
 * CommonContact entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.heinsaw.po.CommonContact
 * @author MyEclipse Persistence Tools
 */
@Repository("CommonContactDAO")
public class CommonContactDAO{

    @Autowired
    private CommonDAO dao;

    private static final Logger log = LoggerFactory
            .getLogger(CommonContactDAO.class);

    // property constants
    public static final String CONTACT_NAME = "contactName";

    public static final String MOBILE = "mobile";

    public static final String EMAIL = "email";

    public static final String POSITION = "position";

    public static final String STATUS = "status";

    protected void initDao(){
        // do nothing
    }

    public Integer save(CommonContact transientInstance){
        log.debug("saving CommonContact instance");
        try{
            Serializable cls = dao.save(transientInstance);
            return (Integer) cls;

        }catch(RuntimeException re){
            log.error("save failed",re);
            throw re;
        }
    }

    public void delete(CommonContact persistentInstance){
        log.debug("deleting CommonContact instance");
        try{
            dao.delete(persistentInstance);
            log.debug("delete successful");
        }catch(RuntimeException re){
            log.error("delete failed",re);
            throw re;
        }
    }

    public CommonContact findById(java.lang.Integer id){
        log.debug("getting CommonContact instance with id: " + id);
        try{

            CommonContact instance = (CommonContact) dao.findById(
                    CommonContact.class,id);
            return instance;
        }catch(RuntimeException re){
            log.error("get failed",re);
            throw re;
        }
    }

    public List<CommonContact> findByExample(CommonContact instance){
        log.debug("finding CommonContact instance by example");
        try{
            List<CommonContact> results = (List<CommonContact>) dao
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
        log.debug("finding CommonContact instance with property: "
                + propertyName + ", value: " + value);
        try{
            String queryString = "from CommonContact as model where model."
                    + propertyName + "= ?";
            return dao.getEntitiesByPropertiesWithHql(queryString,0,0,value);
        }catch(RuntimeException re){
            log.error("find by property name failed",re);
            throw re;
        }
    }

    public List<CommonContact> findByContactName(Object contactName){
        return findByProperty(CONTACT_NAME,contactName);
    }

    public List<CommonContact> findByMobile(Object mobile){
        return findByProperty(MOBILE,mobile);
    }

    public List<CommonContact> findByEmail(Object email){
        return findByProperty(EMAIL,email);
    }

    public List<CommonContact> findByPosition(Object position){
        return findByProperty(POSITION,position);
    }

    public List<CommonContact> findByStatus(Object status){
        return findByProperty(STATUS,status);
    }

    public List findAll(){
        log.debug("finding all CommonContact instances");
        try{
            String queryString = "from CommonContact";
            return dao.getEntitiesByPropertiesWithHql(queryString,0,0);
        }catch(RuntimeException re){
            log.error("find all failed",re);
            throw re;
        }
    }

    public CommonContact merge(CommonContact detachedInstance){
        log.debug("merging CommonContact instance");
        try{
            CommonContact result = (CommonContact) dao.merge(detachedInstance);
            log.debug("merge successful");
            return result;
        }catch(RuntimeException re){
            log.error("merge failed",re);
            throw re;
        }
    }

    public void attachDirty(CommonContact instance){
        log.debug("attaching dirty CommonContact instance");
        try{
            dao.saveOrUpdate(instance);
            log.debug("attach successful");
        }catch(RuntimeException re){
            log.error("attach failed",re);
            throw re;
        }
    }

    public static CommonContactDAO getFromApplicationContext(
            ApplicationContext ctx){
        return (CommonContactDAO) ctx.getBean("CommonContactDAO");
    }
}