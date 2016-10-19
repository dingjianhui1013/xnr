package com.xnradmin.core.dao.business.commodity;

import java.io.Serializable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Repository;

import com.cntinker.util.StringHelper;
import com.xnradmin.core.dao.CommonDAO;
import com.xnradmin.po.business.BusinessWeight;


@Repository("BusinessWeightDAO")
public class BusinessWeightDAO{
    
    @Autowired
    private CommonDAO commonDao;
    
    private static final Logger log = LoggerFactory
            .getLogger(BusinessWeightDAO.class);

    // property constants

    public static final String ID="id";
    public static final String SORT_ID="sortId";//单位排序
    public static final String WEIGHT_NAME="weightName";//单位名称
    public static final String WEIGHT_STATUS="weightStatus";//单位状态
    public static final String WEIGHT_DESCRIPTION="weightDescription";//单位描述
    public static final String PRIMARY_CONFIGURATION_ID="primaryConfigurationId";//对应商城ID
    public static final String CREATE_TIME="createTime";//建立时间
    public static final String CREATE_STAFF_ID="createStaffId";//建立人
    public static final String MODIFY_TIME="modifyTime";//修改时间
    public static final String MODIFY_STAFF_ID="modifyStaffId";//修改人

    protected void initDao(){
        // do nothing
    }

    public Serializable save(BusinessWeight businessWeight){
        log.debug("saving BusinessWeight instance");
        Serializable cls;
        try{
        	cls = commonDao.save(businessWeight);
            log.debug("save successful");
        }catch(RuntimeException re){
            log.error("save failed",re);
            throw re;
        }
        return cls;
    }

    public void delete(BusinessWeight businessWeight){
        log.debug("deleting BusinessWeight instance");
        try{
            commonDao.delete(businessWeight);
            log.debug("delete successful");
        }catch(RuntimeException re){
            log.error("delete failed",re);
            throw re;
        }
    }

    public BusinessWeight findById(Integer id){
        log.debug("getting BusinessWeight instance with id: " + id);
        try{

            return (BusinessWeight) commonDao.findById(
            		BusinessWeight.class,id);
        }catch(RuntimeException re){
            log.error("get failed",re);
            throw re;
        }
    }

    public List<BusinessWeight> findByExample(BusinessWeight instance){
        log.debug("finding BusinessWeight instance by example");
        try{

            return commonDao.getEntitesForExample(instance,0,0);
        }catch(RuntimeException re){
            log.error("find by example failed",re);
            throw re;
        }
    }

    public List findByProperty(String propertyName,Object value){
        log.debug("finding BusinessWeight instance with property: "
                + propertyName + ", value: " + value);
        try{
            String queryString = "from BusinessWeight as model where model."
                    + propertyName + "= ?";
            return commonDao.getEntitiesByPropertiesWithHql(queryString,0,0,value);
        }catch(RuntimeException re){
            log.error("find by property name failed",re);
            throw re;
        }
    }

    public List findAll(){
        log.debug("finding all BusinessWeight instances");
        try{
            String queryString = "from BusinessWeight";
            return commonDao.getEntitiesByPropertiesWithHql(queryString,0,0);
        }catch(RuntimeException re){
            log.error("find all failed",re);
            throw re;
        }
    }

    public BusinessWeight merge(BusinessWeight detachedInstance){
        log.debug("merging BusinessWeight instance");
        try{
        	BusinessWeight result = (BusinessWeight) commonDao
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        }catch(RuntimeException re){
            log.error("merge failed",re);
            throw re;
        }
    }

    public void attachDirty(BusinessWeight instance){
        log.debug("attaching dirty BusinessWeight instance");
        try{
            commonDao.saveOrUpdate(instance);
            log.debug("attach successful");
        }catch(RuntimeException re){
            log.error("attach failed",re);
            throw re;
        }
    }

    
    public int removeBusinessWeightId(Integer id){

        log.debug("removeBusinessWeightId: " + id);
        try{
            String queryString = "delete  from BusinessWeight as model where model.id = "
                    + id;
            return commonDao.executeUpdateOrDelete(queryString);
        }catch(RuntimeException re){
            log.error("removeBusinessWeightId failed",re);
            throw re;
        }
    }
    
    
    public static BusinessWeightDAO getFromApplicationContext(
            ApplicationContext ctx){
        return (BusinessWeightDAO) ctx.getBean("BusinessWeightDAO");
    }
}