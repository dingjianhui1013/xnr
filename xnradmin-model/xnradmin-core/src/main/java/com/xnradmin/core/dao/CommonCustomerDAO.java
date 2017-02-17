package com.xnradmin.core.dao;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Repository;

import com.xnradmin.po.CommonCustomer;

/**
 * A data access object (DAO) providing persistence and search support for
 * CommonCustomer entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.heinsaw.po.CommonCustomer
 * @author MyEclipse Persistence Tools
 */
@Repository("CommonCustomerDAO")
public class CommonCustomerDAO{
    @Autowired
    private CommonDAO dao;

    private static final Logger log = LoggerFactory
            .getLogger(CommonCustomerDAO.class);

    // property constants
    public static final String CUSTOMER_NAME = "customerName";

    public static final String CUSTOMER_ADDRESS = "customerAddress";

    public static final String PROVINCE = "province";

    public static final String CITY = "city";

    public static final String EXTENT = "extent";

    public static final String LEVEL = "level";

    public static final String COUNTRY = "country";

    protected void initDao(){
        // do nothing
    }

    public Integer save(CommonCustomer transientInstance){
        log.debug("saving CommonCustomer instance");
        Integer id = null;
        try{
            id = (Integer) dao.save(transientInstance);
            log.debug("save successful");
        }catch(RuntimeException re){
            log.error("save failed",re);
            throw re;
        }
        return id;
    }

    public void delete(CommonCustomer persistentInstance){
        log.debug("deleting CommonCustomer instance");
        try{
            dao.delete(persistentInstance);
            log.debug("delete successful");
        }catch(RuntimeException re){
            log.error("delete failed",re);
            throw re;
        }
    }

    public CommonCustomer findById(java.lang.Integer id){
        log.debug("getting CommonCustomer instance with id: " + id);
        try{
            CommonCustomer instance = (CommonCustomer) dao
                    .findById(CommonCustomer.class,id);
            return instance;
        }catch(RuntimeException re){
            log.error("get failed",re);
            throw re;
        }
    }

    public List<CommonCustomer> findByExample(CommonCustomer instance){
        log.debug("finding CommonCustomer instance by example");
        try{
            List<CommonCustomer> results = (List<CommonCustomer>) dao
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
        log.debug("finding CommonCustomer instance with property: "
                + propertyName + ", value: " + value);
        try{
            String queryString = "from CommonCustomer as model where model."
                    + propertyName + "= ?";
            return dao.getEntitiesByPropertiesWithHql(queryString,0,0);
        }catch(RuntimeException re){
            log.error("find by property name failed",re);
            throw re;
        }
    }

    public List<CommonCustomer> findByCustomerName(Object customerName){
        return findByProperty(CUSTOMER_NAME,customerName);
    }

    public List<CommonCustomer> findByCustomerAddress(Object customerAddress){
        return findByProperty(CUSTOMER_ADDRESS,customerAddress);
    }

    public List<CommonCustomer> findByProvince(Object province){
        return findByProperty(PROVINCE,province);
    }

    public List<CommonCustomer> findByCity(Object city){
        return findByProperty(CITY,city);
    }

    public List<CommonCustomer> findByExtent(Object extent){
        return findByProperty(EXTENT,extent);
    }

    public List<CommonCustomer> findByLevel(Object level){
        return findByProperty(LEVEL,level);
    }

    public List<CommonCustomer> findByCountry(Object country){
        return findByProperty(COUNTRY,country);
    }

    public List findAll(){
        log.debug("finding all CommonCustomer instances");
        try{
            String queryString = "from CommonCustomer";
            return dao.getEntitiesByPropertiesWithHql(queryString,0,0);
        }catch(RuntimeException re){
            log.error("find all failed",re);
            throw re;
        }
    }

    public CommonCustomer merge(CommonCustomer detachedInstance){
        log.debug("merging CommonCustomer instance");
        try{
            CommonCustomer result = (CommonCustomer) dao
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        }catch(RuntimeException re){
            log.error("merge failed",re);
            throw re;
        }
    }

    public void attachDirty(CommonCustomer instance){
        log.debug("attaching dirty CommonCustomer instance");
        try{
            dao.saveOrUpdate(instance);
            log.debug("attach successful");
        }catch(RuntimeException re){
            log.error("attach failed",re);
            throw re;
        }
    }

    

    public static CommonCustomerDAO getFromApplicationContext(
            ApplicationContext ctx){
        return (CommonCustomerDAO) ctx.getBean("CommonCustomerDAO");
    }
}