package com.xnradmin.core.dao;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Repository;

import com.xnradmin.po.CommonPermissionMenuRelation;

/**
 * A data access object (DAO) providing persistence and search support for
 * CommonPermissionMenuRelation entities. Transaction control of the save(),
 * update() and delete() operations can directly support Spring
 * container-managed transactions or they can be augmented to handle
 * user-managed Spring transactions. Each of these methods provides additional
 * information for how to configure it for the desired type of transaction
 * control.
 * 
 * @see com.heinsaw.po.CommonPermissionMenuRelation
 * @author MyEclipse Persistence Tools
 */
@Repository("CommonPermissionMenuRelationDAO")
public class CommonPermissionMenuRelationDAO{
    private static final Logger log = LoggerFactory
            .getLogger(CommonPermissionMenuRelationDAO.class);

    @Autowired
    private CommonDAO commonDao;

    // property constants
    public static final String PERMISSION_ID = "permissionId";

    public static final String MENU_ID = "menuId";

    protected void initDao(){
        // do nothing
    }

    public void save(CommonPermissionMenuRelation transientInstance){
        log.debug("saving CommonPermissionMenuRelation instance");
        try{
            commonDao.saveOrUpdate(transientInstance);
            log.debug("save successful");
        }catch(RuntimeException re){
            log.error("save failed",re);
            throw re;
        }
    }

    public void delete(CommonPermissionMenuRelation persistentInstance){
        log.debug("deleting CommonPermissionMenuRelation instance");
        try{
            commonDao.delete(persistentInstance);
            log.debug("delete successful");
        }catch(RuntimeException re){
            log.error("delete failed",re);
            throw re;
        }
    }

    public CommonPermissionMenuRelation findById(java.lang.Integer id){
        log.debug("getting CommonPermissionMenuRelation instance with id: "
                + id);
        try{
            return (CommonPermissionMenuRelation) commonDao.findById(
                    CommonPermissionMenuRelation.class,id);
        }catch(RuntimeException re){
            log.error("get failed",re);
            throw re;
        }
    }

    public List<CommonPermissionMenuRelation> findByExample(
            CommonPermissionMenuRelation instance){
        log.debug("finding CommonPermissionMenuRelation instance by example");
        try{
            return commonDao.getEntitesForExample(instance,0,0);

        }catch(RuntimeException re){
            log.error("find by example failed",re);
            throw re;
        }
    }

    public List findByProperty(String propertyName,Object value){
        log.debug("finding CommonPermissionMenuRelation instance with property: "
                + propertyName + ", value: " + value);
        try{
            String queryString = "from CommonPermissionMenuRelation as model where model."
                    + propertyName + "= ?";
            return commonDao.getEntitiesByPropertiesWithHql(queryString,0,0,
                    value);
        }catch(RuntimeException re){
            log.error("find by property name failed",re);
            throw re;
        }
    }

    public List<CommonPermissionMenuRelation> findByPermissionId(
            Object permissionId){
        return findByProperty(PERMISSION_ID,permissionId);
    }

    public List<CommonPermissionMenuRelation> findByMenuId(Object menuId){
        return findByProperty(MENU_ID,menuId);
    }

    public List<Integer> findAuthIdsByMenuId(Integer menuId){
        log.debug("finding AuthId for menuID: " + menuId);
        try{
            String queryString = "select model.permissionId  from CommonPermissionMenuRelation as model where model.menuId = ?";
            return commonDao.getEntitiesByPropertiesWithHql(queryString,0,0,
                    menuId);
        }catch(RuntimeException re){
            log.error("find by property name failed",re);
            throw re;
        }
    }

    public int removePermission4MenuId(Integer menuId){
        log.debug("finding AuthId for menuID: " + menuId);
        try{
            String queryString = "delete  from CommonPermissionMenuRelation as model where model.menuId = "
                    + menuId;
            return commonDao.executeUpdateOrDelete(queryString);
        }catch(RuntimeException re){
            log.error("find by property name failed",re);
            throw re;
        }
    }

    public List findAll(){
        log.debug("finding all CommonPermissionMenuRelation instances");
        try{
            String queryString = "from CommonPermissionMenuRelation";
            return commonDao.getEntitiesByPropertiesWithHql(queryString,0,0);
        }catch(RuntimeException re){
            log.error("find all failed",re);
            throw re;
        }
    }

    public CommonPermissionMenuRelation merge(
            CommonPermissionMenuRelation detachedInstance){
        log.debug("merging CommonPermissionMenuRelation instance");
        try{
            CommonPermissionMenuRelation result = (CommonPermissionMenuRelation) commonDao
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        }catch(RuntimeException re){
            log.error("merge failed",re);
            throw re;
        }
    }

    public void attachDirty(CommonPermissionMenuRelation instance){
        log.debug("attaching dirty CommonPermissionMenuRelation instance");
        try{
            commonDao.saveOrUpdate(instance);
            log.debug("attach successful");
        }catch(RuntimeException re){
            log.error("attach failed",re);
            throw re;
        }
    }

    public static CommonPermissionMenuRelationDAO getFromApplicationContext(
            ApplicationContext ctx){
        return (CommonPermissionMenuRelationDAO) ctx
                .getBean("CommonPermissionMenuRelationDAO");
    }
}