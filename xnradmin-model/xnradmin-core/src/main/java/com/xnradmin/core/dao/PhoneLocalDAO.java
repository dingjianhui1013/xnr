package com.xnradmin.core.dao;


import java.util.List;

import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Repository;

import com.xnradmin.po.CommonStaff;
import com.xnradmin.po.PhoneLocal;

/**
 * A data access object (DAO) providing persistence and search support for
 * PhoneLocal entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.xnradmin.po.PhoneLocal
 * @author MyEclipse Persistence Tools
 */
@Repository("PhoneLocalDAO")
public class PhoneLocalDAO{
    private static final Logger log = LoggerFactory
            .getLogger(PhoneLocalDAO.class);
    
    @Autowired
    private CommonDAO commonDao;

    // property constants
    public static final String PHONEHEAD = "phonehead";

    public static final String LOCAL = "local";

    public static final String LOCALCODE = "localcode";

    public static final String TYPE = "type";

    public static final String TYPETWO = "typetwo";

    protected void initDao(){
        // do nothing
    }

    public void save(PhoneLocal transientInstance){
        log.debug("saving PhoneLocal instance");
        try{
            commonDao.save(transientInstance);
            log.debug("save successful");
        }catch(RuntimeException re){
            log.error("save failed",re);
            throw re;
        }
    }

    public void delete(PhoneLocal persistentInstance){
        log.debug("deleting PhoneLocal instance");
        try{
            commonDao.delete(persistentInstance);
            log.debug("delete successful");
        }catch(RuntimeException re){
            log.error("delete failed",re);
            throw re;
        }
    }

    public PhoneLocal findById(java.lang.Integer id){
        log.debug("getting PhoneLocal instance with id: " + id);
        try{
            PhoneLocal instance = (PhoneLocal) commonDao.findById(PhoneLocal.class,
                    id);
            return instance;
        }catch(RuntimeException re){
            log.error("get failed",re);
            throw re;
        }
    }

    public List<PhoneLocal> findByExample(PhoneLocal instance){
        log.debug("finding PhoneLocal instance by example");
        try{

            return commonDao.getEntitesForExample(instance,0,0);
        }catch(RuntimeException re){
            log.error("find by example failed",re);
            throw re;
        }
    }

    public List findByProperty(String propertyName,Object value){
        log.debug("finding PhoneLocal instance with property: " + propertyName
                + ", value: " + value);
        try{

            String queryString = "from PhoneLocal as model where model."
                    + propertyName + "= ?";
            return commonDao.getEntitiesByPropertiesWithHql(queryString,0,0,value);
        }catch(RuntimeException re){
            log.error("find by property name failed",re);
            throw re;
        }
    }

    public List<PhoneLocal> findByPhonehead(Object phonehead){
        return findByProperty(PHONEHEAD,phonehead);
    }

    public List<PhoneLocal> findByLocal(Object local){
        return findByProperty(LOCAL,local);
    }

    public List<PhoneLocal> findByLocalcode(Object localcode){
        return findByProperty(LOCALCODE,localcode);
    }

    public List<PhoneLocal> findByType(Object type){
        return findByProperty(TYPE,type);
    }

    public List<PhoneLocal> findByTypetwo(Object typetwo){
        return findByProperty(TYPETWO,typetwo);
    }

    public List findAll(){
        log.debug("finding all PhoneLocal instances");
        try{
            String queryString = "from PhoneLocal";
            return commonDao.getEntitiesByPropertiesWithHql(queryString,0,0);
        }catch(RuntimeException re){
            log.error("find all failed",re);
            throw re;
        }
    }

    public PhoneLocal merge(PhoneLocal detachedInstance){
        log.debug("merging PhoneLocal instance");
        try{

            PhoneLocal result = (PhoneLocal) commonDao.merge(detachedInstance);
            log.debug("merge successful");
            return result;
        }catch(RuntimeException re){
            log.error("merge failed",re);
            throw re;
        }
    }

    public void attachDirty(PhoneLocal instance){
        log.debug("attaching dirty PhoneLocal instance");
        try{
            commonDao.saveOrUpdate(instance);
            log.debug("attach successful");
        }catch(RuntimeException re){
            log.error("attach failed",re);
            throw re;
        }
    }

    public static PhoneLocalDAO getFromApplicationContext(ApplicationContext ctx){
        return (PhoneLocalDAO) ctx.getBean("PhoneLocalDAO");
    }
}