package com.xnradmin.core.dao;


import java.io.Serializable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Repository;

import com.xnradmin.po.CommonStaff;

/**
 * A data access object (DAO) providing persistence and search support for
 * CommonStaff entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.heinsaw.po.CommonStaff
 * @author MyEclipse Persistence Tools
 */
@Repository("CommonStaffDAO")
public class CommonStaffDAO{
    private static final Logger log = LoggerFactory
            .getLogger(CommonStaffDAO.class);

    @Autowired
    private CommonDAO commonDao;

    // property constants
    public static final String LOGIN_ID = "loginId";

    public static final String STAFF_NAME = "staffName";

    public static final String STAFF_NO = "staffNo";

    public static final String POSITION_NAME = "positionName";

    public static final String PASSWORD = "password";

    public static final String MOBILE = "mobile";

    public static final String EMAIL = "email";

    public static final String ORGANIZATION_ID = "organizationId";

    public static final String STATUS_ID = "statusId";

    public static final String IDCARD = "idcard";

    public static final String WORKINGLIFE = "workinglife";

    public static final String MANAGER = "manager";

    public static final String EDU = "edu";
    
    public static final String BAND_INFORMATION = "band_information";

    protected void initDao(){
        // do nothing
    }

    public Serializable save(CommonStaff transientInstance){
        log.debug("saving CommonStaff instance");
        Serializable cls;
        try{
        	cls = commonDao.save(transientInstance);
            log.debug("save successful");
        }catch(RuntimeException re){
            log.error("save failed",re);
            throw re;
        }
        return cls;
    }

    public void delete(CommonStaff persistentInstance){
        log.debug("deleting CommonStaff instance");
        try{
            commonDao.delete(persistentInstance);
            log.debug("delete successful");
        }catch(RuntimeException re){
            log.error("delete failed",re);
            throw re;
        }
    }

    public CommonStaff findById(java.lang.Integer id){
        log.debug("getting CommonStaff instance with id: " + id);
        try{
            return (CommonStaff) commonDao.findById(CommonStaff.class,id);
        }catch(RuntimeException re){
            log.error("get failed",re);
            throw re;
        }
    }

    public List<CommonStaff> findByExample(CommonStaff instance){
        log.debug("finding CommonStaff instance by example");
        try{
            return commonDao.getEntitesForExample(instance,0,0);
        }catch(RuntimeException re){
            log.error("find by example failed",re);
            throw re;
        }
    }

    public List findByProperty(String propertyName,Object value){
        log.debug("finding CommonStaff instance with property: " + propertyName
                + ", value: " + value);
        try{
            String queryString = "from CommonStaff as model where model."
                    + propertyName + "= ?";
            return commonDao.getEntitiesByPropertiesWithHql(queryString,0,0,
                    value);
        }catch(RuntimeException re){
            log.error("find by property name failed",re);
            throw re;
        }
    }

    public List<CommonStaff> findByLoginId(Object loginId){
        return findByProperty(LOGIN_ID,loginId);
    }

    public List<CommonStaff> findByStaffName(Object staffName){
        return findByProperty(STAFF_NAME,staffName);
    }

    public List<CommonStaff> findByStaffNo(Object staffNo){
        return findByProperty(STAFF_NO,staffNo);
    }

    public List<CommonStaff> findByPositionName(Object positionName){
        return findByProperty(POSITION_NAME,positionName);
    }

    public List<CommonStaff> findByPassword(Object password){
        return findByProperty(PASSWORD,password);
    }

    public List<CommonStaff> findByMobile(Object mobile){
        return findByProperty(MOBILE,mobile);
    }

    public List<CommonStaff> findByEmail(Object email){
        return findByProperty(EMAIL,email);
    }

    public List<CommonStaff> findByOrganizationId(Object organizationId){
        return findByProperty(ORGANIZATION_ID,organizationId);
    }

    public List<CommonStaff> findByStatusId(Object statusId){
        return findByProperty(STATUS_ID,statusId);
    }

    public List<CommonStaff> findByIdcard(Object idcard){
        return findByProperty(IDCARD,idcard);
    }

    public List<CommonStaff> findByWorkinglife(Object workinglife){
        return findByProperty(WORKINGLIFE,workinglife);
    }

    public List<CommonStaff> findByManager(Object manager){
        return findByProperty(MANAGER,manager);
    }

    public List<CommonStaff> findByEdu(Object edu){
        return findByProperty(EDU,edu);
    }

    public List<CommonStaff> findByBandInformation(Object band_information){
        return findByProperty(BAND_INFORMATION,band_information);
    }
    
    public List findAll(){
        log.debug("finding all CommonStaff instances");
        try{
            String queryString = "from CommonStaff";
            return commonDao.getEntitiesByPropertiesWithHql(queryString,0,0);
        }catch(RuntimeException re){
            log.error("find all failed",re);
            throw re;
        }
    }

    public CommonStaff merge(CommonStaff detachedInstance){
        log.debug("merging CommonStaff instance");
        try{
            CommonStaff result = (CommonStaff) commonDao
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        }catch(RuntimeException re){
            log.error("merge failed",re);
            throw re;
        }
    }

    public void attachDirty(CommonStaff instance){
        log.debug("attaching dirty CommonStaff instance");
        try{
            commonDao.saveOrUpdate(instance);
            log.debug("attach successful");
        }catch(RuntimeException re){
            log.error("attach failed",re);
            throw re;
        }
    }

    public static CommonStaffDAO getFromApplicationContext(
            ApplicationContext ctx){
        return (CommonStaffDAO) ctx.getBean("CommonStaffDAO");
    }
}