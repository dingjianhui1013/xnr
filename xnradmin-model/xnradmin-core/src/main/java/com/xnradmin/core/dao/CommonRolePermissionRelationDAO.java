package com.xnradmin.core.dao;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Repository;

import com.xnradmin.po.CommonRolePermissionRelation;

/**
 * A data access object (DAO) providing persistence and search support for
 * CommonRolePermissionRelation entities. Transaction control of the save(),
 * update() and delete() operations can directly support Spring
 * container-managed transactions or they can be augmented to handle
 * user-managed Spring transactions. Each of these methods provides additional
 * information for how to configure it for the desired type of transaction
 * control.
 * 
 * @see com.heinsaw.po.CommonRolePermissionRelation
 * @author MyEclipse Persistence Tools
 */
@Repository("CommonRolePermissionRelationDAO")
public class CommonRolePermissionRelationDAO{
    private static final Logger log = LoggerFactory
            .getLogger(CommonRolePermissionRelationDAO.class);

    @Autowired
    private CommonDAO commonDao;

    // property constants
    public static final String PERMISSION_ID = "permissionId";

    public static final String ROLE_ID = "roleId";

    protected void initDao(){
        // do nothing
    }

    public void save(CommonRolePermissionRelation transientInstance){
        log.debug("saving CommonRolePermissionRelation instance");
        try{
            commonDao.saveOrUpdate(transientInstance);
            log.debug("save successful");
        }catch(RuntimeException re){
            log.error("save failed",re);
            throw re;
        }
    }

    public void delete(CommonRolePermissionRelation persistentInstance){
        log.debug("deleting CommonRolePermissionRelation instance");
        try{
            commonDao.delete(persistentInstance);
            log.debug("delete successful");
        }catch(RuntimeException re){
            log.error("delete failed",re);
            throw re;
        }
    }

    public CommonRolePermissionRelation findById(java.lang.Integer id){
        log.debug("getting CommonRolePermissionRelation instance with id: "
                + id);
        try{
            return (CommonRolePermissionRelation) commonDao.findById(
                    CommonRolePermissionRelation.class,id);
        }catch(RuntimeException re){
            log.error("get failed",re);
            throw re;
        }
    }

    public List<CommonRolePermissionRelation> findByExample(
            CommonRolePermissionRelation instance){
        log.debug("finding CommonRolePermissionRelation instance by example");
        try{
            return commonDao.getEntitesForExample(instance,0,0);
        }catch(RuntimeException re){
            log.error("find by example failed",re);
            throw re;
        }
    }

    public List findByProperty(String propertyName,Object value){
        log.debug("finding CommonRolePermissionRelation instance with property: "
                + propertyName + ", value: " + value);
        try{
            String queryString = "from CommonRolePermissionRelation as model where model."
                    + propertyName + "= ?";
            return commonDao.getEntitiesByPropertiesWithHql(queryString,0,0,
                    value);
        }catch(RuntimeException re){
            log.error("find by property name failed",re);
            throw re;
        }
    }

    public List<Object[]> getUrl2Role(){
        log.debug("finding Url(menu) 2 roles Collection  ");
        try{
            String queryString = "select menu.menuLink,model.roleCode from CommonRole as model, CommonRolePermissionRelation as pr ,CommonPermissionMenuRelation as pm ,CommonMenu as menu  where model.id = pr.roleId and pr.permissionId=pm.permissionId  and pm.menuId=menu.id";
            return commonDao.getEntitiesByPropertiesWithHql(queryString,0,0);
        }catch(RuntimeException re){
            log.error("finding Url(menu) 2 roles Collection failed",re);
            throw re;
        }
    }

    public List<CommonRolePermissionRelation> findByPermissionId(
            Object permissionId){
        return findByProperty(PERMISSION_ID,permissionId);
    }

    public List<CommonRolePermissionRelation> findByRoleId(Object roleId){
        return findByProperty(ROLE_ID,roleId);
    }

    public List findAll(){
        log.debug("finding all CommonRolePermissionRelation instances");
        try{
            String queryString = "from CommonRolePermissionRelation";
            return commonDao.getEntitiesByPropertiesWithHql(queryString,0,0);
        }catch(RuntimeException re){
            log.error("find all failed",re);
            throw re;
        }
    }

    public CommonRolePermissionRelation merge(
            CommonRolePermissionRelation detachedInstance){
        log.debug("merging CommonRolePermissionRelation instance");
        try{
            CommonRolePermissionRelation result = (CommonRolePermissionRelation) commonDao
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        }catch(RuntimeException re){
            log.error("merge failed",re);
            throw re;
        }
    }

    public void attachDirty(CommonRolePermissionRelation instance){
        log.debug("attaching dirty CommonRolePermissionRelation instance");
        try{
            commonDao.saveOrUpdate(instance);
            log.debug("attach successful");
        }catch(RuntimeException re){
            log.error("attach failed",re);
            throw re;
        }
    }

    public static CommonRolePermissionRelationDAO getFromApplicationContext(
            ApplicationContext ctx){
        return (CommonRolePermissionRelationDAO) ctx
                .getBean("CommonRolePermissionRelationDAO");
    }

    public List<Integer> findAuthIdsByRoleId(Integer roleid){
        log.debug("finding AuthId for roleid: " + roleid);
        try{
            String queryString = "select model.permissionId  from CommonRolePermissionRelation as model where model.roleId = ?";
            return commonDao.getEntitiesByPropertiesWithHql(queryString,0,0,
                    roleid);
        }catch(RuntimeException re){
            log.error("find by property name failed",re);
            throw re;
        }
    }

    public int removePermission4RoleId(Integer roleid){
        log.debug("finding AuthId for roleid: " + roleid);
        try{
            String queryString = "delete  from CommonRolePermissionRelation as model where model.roleId = "
                    + roleid;
            return commonDao.executeUpdateOrDelete(queryString);
        }catch(RuntimeException re){
            log.error("find by property name failed",re);
            throw re;
        }
    }
}