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
import com.xnradmin.po.mall.seting.PayMentMode;


@Repository("PayMentModeDAO")
public class PayMentModeDAO{
    
    @Autowired
    private CommonDAO commonDao;
    
    private static final Logger log = LoggerFactory
            .getLogger(PayMentModeDAO.class);

    // property constants

    public static final String ID="id";
    public static final String SORT_ID="sortId";//排序Id
    public static final String PAYMENT_TYPE_ID="paymentTypeId";//支付类型ID
    public static final String PAYMENT_TYPE_NAME="paymentTypeName";//支付类型名称
    public static final String STAFF_ID="staffId";	//隶属用户Id
    public static final String PRIMARY_CONFIGURATION_ID="primaryConfigurationId";	//商城Id
    public static final String CREATE_TIME="createTime";//建立时间
    public static final String CREATE_STAFF_ID="createStaffId";//建立人
    public static final String MODIFY_TIME="modifyTime";//修改时间
    public static final String MODIFY_STAFF_ID="modifyStaffId";//修改人

    protected void initDao(){
        // do nothing
    }

    public Serializable save(PayMentMode payMentMode){
        log.debug("saving PayMentMode instance");
        Serializable cls;
        try{
        	cls = commonDao.save(payMentMode);
            log.debug("save successful");
        }catch(RuntimeException re){
            log.error("save failed",re);
            throw re;
        }
        return cls;
    }

    public void delete(PayMentMode payMentMode){
        log.debug("deleting PayMentMode instance");
        try{
            commonDao.delete(payMentMode);
            log.debug("delete successful");
        }catch(RuntimeException re){
            log.error("delete failed",re);
            throw re;
        }
    }

    public PayMentMode findById(Integer id){
        log.debug("getting PayMentMode instance with id: " + id);
        try{

            return (PayMentMode) commonDao.findById(
            		PayMentMode.class,id);
        }catch(RuntimeException re){
            log.error("get failed",re);
            throw re;
        }
    }

    public List<PayMentMode> findByExample(PayMentMode instance){
        log.debug("finding PayMentMode instance by example");
        try{

            return commonDao.getEntitesForExample(instance,0,0);
        }catch(RuntimeException re){
            log.error("find by example failed",re);
            throw re;
        }
    }

    public List findByProperty(String propertyName,Object value){
        log.debug("finding PayMentMode instance with property: "
                + propertyName + ", value: " + value);
        try{
            String queryString = "from PayMentMode as model where model."
                    + propertyName + "= ?";
            return commonDao.getEntitiesByPropertiesWithHql(queryString,0,0,value);
        }catch(RuntimeException re){
            log.error("find by property name failed",re);
            throw re;
        }
    }

    public List findAll(){
        log.debug("finding all PayMentMode instances");
        try{
            String queryString = "from PayMentMode";
            return commonDao.getEntitiesByPropertiesWithHql(queryString,0,0);
        }catch(RuntimeException re){
            log.error("find all failed",re);
            throw re;
        }
    }

    public PayMentMode merge(PayMentMode detachedInstance){
        log.debug("merging PayMentMode instance");
        try{
        	PayMentMode result = (PayMentMode) commonDao
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        }catch(RuntimeException re){
            log.error("merge failed",re);
            throw re;
        }
    }

    public void attachDirty(PayMentMode instance){
        log.debug("attaching dirty PayMentMode instance");
        try{
            commonDao.saveOrUpdate(instance);
            log.debug("attach successful");
        }catch(RuntimeException re){
            log.error("attach failed",re);
            throw re;
        }
    }

    
    public int removePayMentModeId(Integer id){

        log.debug("removePayMentModeId: " + id);
        try{
            String queryString = "delete  from PayMentMode as model where model.id = "
                    + id;
            return commonDao.executeUpdateOrDelete(queryString);
        }catch(RuntimeException re){
            log.error("removePayMentModeId failed",re);
            throw re;
        }
    }
    
    
    public static PayMentModeDAO getFromApplicationContext(
            ApplicationContext ctx){
        return (PayMentModeDAO) ctx.getBean("PayMentModeDAO");
    }
}