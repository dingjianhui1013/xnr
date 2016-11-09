package com.xnradmin.core.dao.pay;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Repository;

import com.xnradmin.core.dao.CommonDAO;
import com.xnradmin.core.dao.pay.PayUpmpDAO;
import com.xnradmin.po.pay.PayUpmp;


@Repository("PayUpmpDAO")
public class PayUpmpDAO{
    
    @Autowired
    private CommonDAO commonDao;
    
    private static final Logger log = LoggerFactory
            .getLogger(PayUpmpDAO.class);

    // property constants

    public static final String ID = "id";
    public static final String CHARSET = "charset";
    public static final String EXCHANGE_DATE = "exchangeDate";
    public static final String EXCHANGE_RATE = "exchangeRate";
    public static final String MER_ID = "merId";
    public static final String MER_RESERVED = "merReserved";
    public static final String MODIFYTIME = "modifyTime";
    public static final String ORDER_ID = "orderId";
    public static final String ORDER_TIME = "orderTime";
    public static final String ORDER_TYPE = "orderType";
    public static final String ORDER_TYPE_NAME = "orderTypeName";
    public static final String RECEIVETIME = "receivetime";
    public static final String REQ_RESERVED = "reqReserved";
    public static final String RESP_CODE = "respCode";
    public static final String RESP_MSG = "respMsg";
    public static final String SER_NO = "serNo";
    public static final String SETTLE_AMOUNT = "settleAmount";
    public static final String SETTLE_CURRENCY = "settleCurrency";
    public static final String SETTLE_DATE = "settleDate";
    public static final String SIGN_METHOD = "signMethod";
    public static final String SIGNATURE = "signature";
    public static final String SYS_RESERVED = "sysReserved";
    public static final String TRANS_STATUS = "transStatus";
    public static final String TRANS_TYPE = "transType";
    public static final String VERSION = "version";

    protected void initDao(){
        // do nothing
    }

    public void save(PayUpmp payUpmp){
        log.debug("saving PayUpmp instance");
        try{
            commonDao.save(payUpmp);
            log.debug("save successful");
        }catch(RuntimeException re){
            log.error("save failed",re);
            throw re;
        }
    }

    public void delete(PayUpmp payUpmp){
        log.debug("deleting PayUpmp instance");
        try{
            commonDao.delete(payUpmp);
            log.debug("delete successful");
        }catch(RuntimeException re){
            log.error("delete failed",re);
            throw re;
        }
    }

    public PayUpmp findById(java.lang.Long id){
        log.debug("getting PayUpmp instance with id: " + id);
        try{

            return (PayUpmp) commonDao.findById(
            		PayUpmp.class,id);
        }catch(RuntimeException re){
            log.error("get failed",re);
            throw re;
        }
    }

    public List<PayUpmp> findByExample(PayUpmp instance){
        log.debug("finding PayUpmp instance by example");
        try{

            return commonDao.getEntitesForExample(instance,0,0);
        }catch(RuntimeException re){
            log.error("find by example failed",re);
            throw re;
        }
    }

    public List findByProperty(String propertyName,Object value){
        log.debug("finding PayUpmp instance with property: "
                + propertyName + ", value: " + value);
        try{
            String queryString = "from PayUpmp as model where model."
                    + propertyName + "= ?";
            return commonDao.getEntitiesByPropertiesWithHql(queryString,0,0,value);
        }catch(RuntimeException re){
            log.error("find by property name failed",re);
            throw re;
        }
    }

    public List findAll(){
        log.debug("finding all PayUpmp instances");
        try{
            String queryString = "from PayUpmp";
            return commonDao.getEntitiesByPropertiesWithHql(queryString,0,0);
        }catch(RuntimeException re){
            log.error("find all failed",re);
            throw re;
        }
    }

    public PayUpmp merge(PayUpmp detachedInstance){
        log.debug("merging PayUpmp instance");
        try{
        	PayUpmp result = (PayUpmp) commonDao
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        }catch(RuntimeException re){
            log.error("merge failed",re);
            throw re;
        }
    }

    public void attachDirty(PayUpmp instance){
        log.debug("attaching dirty PayUpmp instance");
        try{
            commonDao.saveOrUpdate(instance);
            log.debug("attach successful");
        }catch(RuntimeException re){
            log.error("attach failed",re);
            throw re;
        }
    }

    
    public int removePayUpmpId(Long id){

        log.debug("removePayUpmpId: " + id);
        try{
            String queryString = "delete  from PayUpmp as model where model.id = "
                    + id;
            return commonDao.executeUpdateOrDelete(queryString);
        }catch(RuntimeException re){
            log.error("removePayUpmpId failed",re);
            throw re;
        }
    }
    
    public static PayUpmpDAO getFromApplicationContext(
            ApplicationContext ctx){
        return (PayUpmpDAO) ctx.getBean("PayUpmpDAO");
    }
}