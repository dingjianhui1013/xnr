package com.xnradmin.core.dao;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Repository;

import com.xnradmin.po.CommonStaffRoleRelation;

/**
 * A data access object (DAO) providing persistence and search support for
 * CommonStaffRoleRelation entities. Transaction control of the save(), update()
 * and delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.heinsaw.po.CommonStaffRoleRelation
 * @author MyEclipse Persistence Tools
 */
@Repository("CommonStaffRoleRelationDAO")
public class CommonStaffRoleRelationDAO{

    @Autowired
    private CommonDAO commonDao;

    private static final Logger log = LoggerFactory
            .getLogger(CommonStaffRoleRelationDAO.class);

    // property constants
    public static final String STAFF_ID = "staffId";

    public static final String ROLE_ID = "roleId";

    protected void initDao(){
        // do nothing
    }

    public void save(CommonStaffRoleRelation transientInstance){
        log.debug("saving CommonStaffRoleRelation instance");
        try{
            commonDao.saveOrUpdate(transientInstance);
            log.debug("save successful");
        }catch(RuntimeException re){
            log.error("save failed",re);
            throw re;
        }
    }

    public void delete(CommonStaffRoleRelation persistentInstance){
        log.debug("deleting CommonStaffRoleRelation instance");
        try{
            commonDao.delete(persistentInstance);
            log.debug("delete successful");
        }catch(RuntimeException re){
            log.error("delete failed",re);
            throw re;
        }
    }

    public CommonStaffRoleRelation findById(java.lang.Integer id){
        log.debug("getting CommonStaffRoleRelation instance with id: " + id);
        try{
            return (CommonStaffRoleRelation) commonDao.findById(
                    CommonStaffRoleRelation.class,id);
        }catch(RuntimeException re){
            log.error("get failed",re);
            throw re;
        }
    }

    public List<CommonStaffRoleRelation> findByExample(
            CommonStaffRoleRelation instance){
        log.debug("finding CommonStaffRoleRelation instance by example");
        try{
            return commonDao.getEntitesForExample(instance,0,0);

        }catch(RuntimeException re){
            log.error("find by example failed",re);
            throw re;
        }
    }

    public List findByProperty(String propertyName,Object value){
        log.debug("finding CommonStaffRoleRelation instance with property: "
                + propertyName + ", value: " + value);
        try{
            String queryString = "from CommonStaffRoleRelation as model where model."
                    + propertyName + "= ?";
            return commonDao.getEntitiesByPropertiesWithHql(queryString,0,0,
                    value);
        }catch(RuntimeException re){
            log.error("find by property name failed",re);
            throw re;
        }
    }

    public List<CommonStaffRoleRelation> findByStaffId(Object staffId){
        return findByProperty(STAFF_ID,staffId);
    }

    public List<CommonStaffRoleRelation> findByRoleId(Object roleId){
        return findByProperty(ROLE_ID,roleId);
    }

    public List findAll(){
        log.debug("finding all CommonStaffRoleRelation instances");
        try{
            String queryString = "from CommonStaffRoleRelation";
            return commonDao.getEntitiesByPropertiesWithHql(queryString,0,0);
        }catch(RuntimeException re){
            log.error("find all failed",re);
            throw re;
        }
    }

    public CommonStaffRoleRelation merge(
            CommonStaffRoleRelation detachedInstance){
        log.debug("merging CommonStaffRoleRelation instance");
        try{
            CommonStaffRoleRelation result = (CommonStaffRoleRelation) commonDao
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        }catch(RuntimeException re){
            log.error("merge failed",re);
            throw re;
        }
    }

    public void attachDirty(CommonStaffRoleRelation instance){
        log.debug("attaching dirty CommonStaffRoleRelation instance");
        try{
            commonDao.saveOrUpdate(instance);
            log.debug("attach successful");
        }catch(RuntimeException re){
            log.error("attach failed",re);
            throw re;
        }
    }

    public static CommonStaffRoleRelationDAO getFromApplicationContext(
            ApplicationContext ctx){
        return (CommonStaffRoleRelationDAO) ctx
                .getBean("CommonStaffRoleRelationDAO");
    }

    public List<Integer> getRoleId4User(Integer userid){
        log.debug("finding RoleIds for userid: " + userid);
        try{
            String queryString = "select model.roleId  from CommonStaffRoleRelation as model where model.staffId = "
                    + userid;
            return commonDao.getEntitiesByPropertiesWithHql(queryString,0,0);
        }catch(RuntimeException re){
            log.error("find by property name failed",re);
            throw re;
        }
    }

    public int removeRoles4userId(Integer userid){

        log.debug("removeRoles4userId: " + userid);
        try{
            String queryString = "delete  from CommonStaffRoleRelation as model where model.staffId = "
                    + userid;
            return commonDao.executeUpdateOrDelete(queryString);
        }catch(RuntimeException re){
            log.error("removeRoles4userId failed",re);
            throw re;
        }
    }
}