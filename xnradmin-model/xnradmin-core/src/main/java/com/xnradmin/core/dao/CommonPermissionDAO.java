package com.xnradmin.core.dao;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Repository;

import com.xnradmin.po.CommonPermission;

/**
 * A data access object (DAO) providing persistence and search support for
 * CommonPermission entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.heinsaw.po.CommonPermission
 * @author MyEclipse Persistence Tools
 */
@Repository("CommonPermissionDAO")
public class CommonPermissionDAO{
    private static final Logger log = LoggerFactory
            .getLogger(CommonPermissionDAO.class);

    @Autowired
    private CommonDAO commonDao;

    // property constants
    public static final String PERMISSION_NAME = "permissionName";

    protected void initDao(){
        // do nothing
    }

    public void save(CommonPermission transientInstance){
        log.debug("saving CommonPermission instance");
        try{
            commonDao.saveOrUpdate(transientInstance);
            log.debug("save successful");
        }catch(RuntimeException re){
            log.error("save failed",re);
            throw re;
        }
    }

    public void delete(CommonPermission persistentInstance){
        log.debug("deleting CommonPermission instance");
        try{
            commonDao.delete(persistentInstance);
            log.debug("delete successful");
        }catch(RuntimeException re){
            log.error("delete failed",re);
            throw re;
        }
    }

    public CommonPermission findById(java.lang.Integer id){
        log.debug("getting CommonPermission instance with id: " + id);
        try{
            return (CommonPermission) commonDao.findById(
                    CommonPermission.class,id);
        }catch(RuntimeException re){
            log.error("get failed",re);
            throw re;
        }
    }

    public List<CommonPermission> findByExample(CommonPermission instance){
        log.debug("finding CommonPermission instance by example");
        try{

            return commonDao.getEntitesForExample(instance,0,0);
        }catch(RuntimeException re){
            log.error("find by example failed",re);
            throw re;
        }
    }

    public List findByProperty(String propertyName,Object value){
        log.debug("finding CommonPermission instance with property: "
                + propertyName + ", value: " + value);
        try{
            String queryString = "from CommonPermission as model where model."
                    + propertyName + "= ? order by id desc";
            return commonDao.getEntitiesByPropertiesWithHql(queryString,0,0,
                    value);
        }catch(RuntimeException re){
            log.error("find by property name failed",re);
            throw re;
        }
    }

    public List<CommonPermission> findByPermissionName(Object permissionName){
        return findByProperty(PERMISSION_NAME,permissionName);
    }

    public List<CommonPermission> findValidPermissions(){
        return findByProperty("enabled",true);
    }

    public List findAll(){
        log.debug("finding all CommonPermission instances");
        try{
            String queryString = "from CommonPermission";
            return commonDao.getEntitiesByPropertiesWithHql(queryString,0,0);
        }catch(RuntimeException re){
            log.error("find all failed",re);
            throw re;
        }
    }

    public CommonPermission merge(CommonPermission detachedInstance){
        log.debug("merging CommonPermission instance");
        try{
            CommonPermission result = (CommonPermission) commonDao
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        }catch(RuntimeException re){
            log.error("merge failed",re);
            throw re;
        }
    }

    public void attachDirty(CommonPermission instance){
        log.debug("attaching dirty CommonPermission instance");
        try{
            commonDao.saveOrUpdate(instance);
            log.debug("attach successful");
        }catch(RuntimeException re){
            log.error("attach failed",re);
            throw re;
        }
    }

    public static CommonPermissionDAO getFromApplicationContext(
            ApplicationContext ctx){
        return (CommonPermissionDAO) ctx.getBean("CommonPermissionDAO");
    }
}