package com.xnradmin.core.dao;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Repository;

import com.xnradmin.po.CommonRole;
import com.xnradmin.po.CommonStaff;

/**
 * A data access object (DAO) providing persistence and search support for
 * CommonRole entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.heinsaw.po.CommonRole
 * @author MyEclipse Persistence Tools
 */
@Repository("CommonRoleDAO")
public class CommonRoleDAO{
    
    @Autowired
    private CommonDAO commonDao;
    
    private static final Logger log = LoggerFactory
            .getLogger(CommonRoleDAO.class);

    // property constants
    public static final String ROLE_NAME = "roleName";

    public static final String DESCRIPTION = "description";

    protected void initDao(){
        // do nothing
    }

    public void save(CommonRole transientInstance){
        log.debug("saving CommonRole instance");
        try{
            commonDao.save(transientInstance);
            log.debug("save successful");
        }catch(RuntimeException re){
            log.error("save failed",re);
            throw re;
        }
    }

    public void delete(CommonRole persistentInstance){
        log.debug("deleting CommonRole instance");
        try{
            commonDao.delete(persistentInstance);
            log.debug("delete successful");
        }catch(RuntimeException re){
            log.error("delete failed",re);
            throw re;
        }
    }

    public CommonRole findById(java.lang.Integer id){
        log.debug("getting CommonRole instance with id: " + id);
        try{
            return (CommonRole) commonDao.findById(CommonRole.class,id);
        }catch(RuntimeException re){
            log.error("get failed",re);
            throw re;
        }
    }

    public List<CommonRole> findByExample(CommonRole instance){
        log.debug("finding CommonRole instance by example");
        try{
            return commonDao.getEntitesForExample(instance,0,0);
        }catch(RuntimeException re){
            log.error("find by example failed",re);
            throw re;
        }
    }

    public List findByProperty(String propertyName,Object value){
        log.debug("finding CommonRole instance with property: " + propertyName
                + ", value: " + value);
        try{
            String queryString = "from CommonRole as model where model."
                    + propertyName + "= ?";
            return commonDao.getEntitiesByPropertiesWithHql(queryString,0,0,value);
        }catch(RuntimeException re){
            log.error("find by property name failed",re);
            throw re;
        }
    }

    public List<CommonRole> getRoles4User(CommonStaff user){
        log.debug("finding CommonRoles for UserID  " + user.getLoginId());
        try{
            String queryString = "select model from CommonRole as model, CommonStaffRoleRelation as r  where model.id = r.roleId and r.staffId = ?";

            return commonDao.getEntitiesByPropertiesWithHql(queryString,0,0,
                    user.getId());
        }catch(RuntimeException re){
            log.error("find by property name failed",re);
            throw re;
        }
    }

    public List<CommonRole> findByRoleName(Object roleName){
        return findByProperty(ROLE_NAME,roleName);
    }

    public List<CommonRole> findByDescription(Object description){
        return findByProperty(DESCRIPTION,description);
    }

    public List<CommonRole> findEnabledRoles(){
        return findByProperty("enabled",true);
    }

    public List<CommonRole> findByRoleCode(Object roleCode){
        return findByProperty("roleCode",roleCode);
    }

    public List findAll(){
        log.debug("finding all CommonRole instances");
        try{
            String queryString = "from CommonRole";
            return commonDao.getEntitiesByPropertiesWithHql(queryString,0,0);
        }catch(RuntimeException re){
            log.error("find all failed",re);
            throw re;
        }
    }

    public CommonRole merge(CommonRole detachedInstance){
        log.debug("merging CommonRole instance");
        try{
            CommonRole result = (CommonRole) commonDao.merge(detachedInstance);
            log.debug("merge successful");
            return result;
        }catch(RuntimeException re){
            log.error("merge failed",re);
            throw re;
        }
    }

    public void attachDirty(CommonRole instance){
        log.debug("attaching dirty CommonRole instance");
        try{
            commonDao.saveOrUpdate(instance);
            log.debug("attach successful");
        }catch(RuntimeException re){
            log.error("attach failed",re);
            throw re;
        }
    }

    public static CommonRoleDAO getFromApplicationContext(ApplicationContext ctx){
        return (CommonRoleDAO) ctx.getBean("CommonRoleDAO");
    }
}