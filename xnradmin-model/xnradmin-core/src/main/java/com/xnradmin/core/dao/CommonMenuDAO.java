package com.xnradmin.core.dao;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Repository;

import com.xnradmin.po.CommonMenu;

@Repository("CommonMenuDAO")
public class CommonMenuDAO {
    
    @Autowired
    private CommonDAO commonDao;
    
    private static final Logger log = LoggerFactory
            .getLogger(CommonMenuDAO.class);

    // property constants
    public static final String PRAENT_MENU_ID = "praentMenuId";

    public static final String MENU_NAME = "menuName";

    public static final String EN_NAME = "enName";

    public static final String MENU_LEVEL = "menuLevel";

    public static final String MENU_LINK = "menuLink";

    public static final String STATUS = "status";

    protected void initDao(){
        // do nothing
    }

    public void save(CommonMenu transientInstance){
        log.debug("saving CommonMenu instance");
        try{
            commonDao.save(transientInstance);
            log.debug("save successful");
        }catch(RuntimeException re){
            log.error("save failed",re);
            throw re;
        }
    }

    public void delete(CommonMenu persistentInstance){
        log.debug("deleting CommonMenu instance");
        try{
            commonDao.delete(persistentInstance);
            log.debug("delete successful");
        }catch(RuntimeException re){
            log.error("delete failed",re);
            throw re;
        }
    }

    public CommonMenu findById(Integer id){
        log.debug("getting CommonMenu instance with id: " + id);
        try{
            return (CommonMenu) commonDao.findById(CommonMenu.class,id);
        }catch(RuntimeException re){
            log.error("get failed",re);
            throw re;
        }
    }

    public List<CommonMenu> findByExample(CommonMenu instance){
        log.debug("finding CommonMenu instance by example");
        try{

            return commonDao.getEntitesForExample(instance,0,0);
        }catch(RuntimeException re){
            log.error("find by example failed",re);
            throw re;
        }
    }

    public List findByProperty(String propertyName,Object value){
        log.debug("finding CommonMenu instance with property: " + propertyName
                + ", value: " + value);
        try{
            String queryString = "from CommonMenu as model where model."
                    + propertyName + "= ?";

            return commonDao.getEntitiesByPropertiesWithHql(queryString,0,0,value);
        }catch(RuntimeException re){
            log.error("find by property name failed",re);
            throw re;
        }
    }

    public List<CommonMenu> findByPraentMenuId(Object praentMenuId){
        return findByProperty(PRAENT_MENU_ID,praentMenuId);
    }

    public List<CommonMenu> findByMenuName(Object menuName){
        return findByProperty(MENU_NAME,menuName);
    }

    public List<CommonMenu> findByEnName(Object enName){
        return findByProperty(EN_NAME,enName);
    }

    public List<CommonMenu> findByMenuLevel(Object menuLevel){
        return findByProperty(MENU_LEVEL,menuLevel);
    }

    public List<CommonMenu> findByMenuLink(Object menuLink){
        return findByProperty(MENU_LINK,menuLink);
    }

    public List<CommonMenu> findByStatus(Object status){
        return findByProperty(STATUS,status);
    }

    public List findAll(){
        log.debug("finding all CommonMenu instances");
        try{
            String queryString = "from CommonMenu";

            return commonDao.getEntitiesByPropertiesWithHql(queryString,0,0);
        }catch(RuntimeException re){
            log.error("find all failed",re);
            throw re;
        }
    }

    public CommonMenu merge(CommonMenu detachedInstance){
        log.debug("merging CommonMenu instance");
        try{
            CommonMenu result = (CommonMenu) commonDao.merge(detachedInstance);
            log.debug("merge successful");
            return result;
        }catch(RuntimeException re){
            log.error("merge failed",re);
            throw re;
        }
    }

    public void attachDirty(CommonMenu instance){
        log.debug("attaching dirty CommonMenu instance");
        try{
            commonDao.saveOrUpdate(instance);
            log.debug("attach successful");
        }catch(RuntimeException re){
            log.error("attach failed",re);
            throw re;
        }
    }

    public static CommonMenuDAO getFromApplicationContext(ApplicationContext ctx){
        return (CommonMenuDAO) ctx.getBean("CommonMenuDAO");
    }
}