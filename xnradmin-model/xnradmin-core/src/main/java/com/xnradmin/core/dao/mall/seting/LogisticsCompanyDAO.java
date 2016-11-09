package com.xnradmin.core.dao.mall.seting;

import java.io.Serializable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Repository;

import com.cntinker.util.StringHelper;
import com.xnradmin.core.dao.CommonDAO;
import com.xnradmin.po.mall.seting.LogisticsCompany;


@Repository("LogisticsCompanyDAO")
public class LogisticsCompanyDAO{
    
    @Autowired
    private CommonDAO commonDao;
    
    private static final Logger log = LoggerFactory
            .getLogger(LogisticsCompanyDAO.class);

    // property constants

    public static final String ID="id";
    public static final String SORT_ID="sortId";//排序Id
    public static final String COMPANY_NAME="companynName";//公司名称
    public static final String COMPANY_URL="companynUrl";//公司网址
    public static final String STAFF_ID="staffId";	//隶属用户Id
    public static final String PRIMARY_CONFIGURATION_ID="primaryConfigurationId";	//商城Id
    public static final String CREATE_TIME="createTime";//建立时间
    public static final String CREATE_STAFF_ID="createStaffId";//建立人
    public static final String MODIFY_TIME="modifyTime";//修改时间
    public static final String MODIFY_STAFF_ID="modifyStaffId";//修改人

    protected void initDao(){
        // do nothing
    }

    public Serializable save(LogisticsCompany logisticsCompany){
        log.debug("saving LogisticsCompany instance");
        Serializable cls;
        try{
        	cls = commonDao.save(logisticsCompany);
            log.debug("save successful");
        }catch(RuntimeException re){
            log.error("save failed",re);
            throw re;
        }
        return cls;
    }

    public void delete(LogisticsCompany logisticsCompany){
        log.debug("deleting LogisticsCompany instance");
        try{
            commonDao.delete(logisticsCompany);
            log.debug("delete successful");
        }catch(RuntimeException re){
            log.error("delete failed",re);
            throw re;
        }
    }

    public LogisticsCompany findById(Integer id){
        log.debug("getting LogisticsCompany instance with id: " + id);
        try{

            return (LogisticsCompany) commonDao.findById(
            		LogisticsCompany.class,id);
        }catch(RuntimeException re){
            log.error("get failed",re);
            throw re;
        }
    }

    public List<LogisticsCompany> findByExample(LogisticsCompany instance){
        log.debug("finding LogisticsCompany instance by example");
        try{

            return commonDao.getEntitesForExample(instance,0,0);
        }catch(RuntimeException re){
            log.error("find by example failed",re);
            throw re;
        }
    }

    public List findByProperty(String propertyName,Object value){
        log.debug("finding LogisticsCompany instance with property: "
                + propertyName + ", value: " + value);
        try{
            String queryString = "from LogisticsCompany as model where model."
                    + propertyName + "= ?";
            return commonDao.getEntitiesByPropertiesWithHql(queryString,0,0,value);
        }catch(RuntimeException re){
            log.error("find by property name failed",re);
            throw re;
        }
    }

    public List findAll(){
        log.debug("finding all LogisticsCompany instances");
        try{
            String queryString = "from LogisticsCompany";
            return commonDao.getEntitiesByPropertiesWithHql(queryString,0,0);
        }catch(RuntimeException re){
            log.error("find all failed",re);
            throw re;
        }
    }

    public LogisticsCompany merge(LogisticsCompany detachedInstance){
        log.debug("merging DeliveryMode instance");
        try{
        	LogisticsCompany result = (LogisticsCompany) commonDao
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        }catch(RuntimeException re){
            log.error("merge failed",re);
            throw re;
        }
    }

    public void attachDirty(LogisticsCompany instance){
        log.debug("attaching dirty LogisticsCompany instance");
        try{
            commonDao.saveOrUpdate(instance);
            log.debug("attach successful");
        }catch(RuntimeException re){
            log.error("attach failed",re);
            throw re;
        }
    }

    
    public int removeLogisticsCompanyId(Integer id){

        log.debug("removeLogisticsCompanyId: " + id);
        try{
            String queryString = "delete  from LogisticsCompany as model where model.id = "
                    + id;
            return commonDao.executeUpdateOrDelete(queryString);
        }catch(RuntimeException re){
            log.error("removeLogisticsCompanyId failed",re);
            throw re;
        }
    }
    
    
    public static LogisticsCompanyDAO getFromApplicationContext(
            ApplicationContext ctx){
        return (LogisticsCompanyDAO) ctx.getBean("LogisticsCompanyDAO");
    }
}