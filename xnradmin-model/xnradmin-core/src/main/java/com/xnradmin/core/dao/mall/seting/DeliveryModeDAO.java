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
import com.xnradmin.po.dishes.Dish;
import com.xnradmin.po.mall.seting.DeliveryMode;


@Repository("DeliveryModeDAO")
public class DeliveryModeDAO{
    
    @Autowired
    private CommonDAO commonDao;
    
    private static final Logger log = LoggerFactory
            .getLogger(DeliveryModeDAO.class);

    // property constants

    public static final String ID="id";
    public static final String SORT_ID="sortId";//排序Id
    public static final String DELIVERY_MODE_NAME="deliveryModeName";//配送方式名称
    public static final String LOGISTICS_COMPANY_ID="logisticsCompanyId";//默认物流公司Id
    public static final String FIRST_WEIGHT="firstWeight";	//首重单位
    public static final String FIRST_PRICE="firstPrice";	//首重价格
    public static final String CONTINUE_DHEAVY_WEIGHT="continuedHeavyWeight";//续重单位
    public static final String CONTINUE_DHEAVY_PRICE="continuedHeavyPrice";	//续重价格
    public static final String STAFF_ID="staffId";	//隶属用户Id
    public static final String PRIMARY_CONFIGURATION_ID="primaryConfigurationId";	//商城Id
    public static final String CREATE_TIME="createTime";//建立时间
    public static final String CREATE_STAFF_ID="createStaffId";//建立人
    public static final String MODIFY_TIME="modifyTime";//修改时间
    public static final String MODIFY_STAFF_ID="modifyStaffId";//修改人

    protected void initDao(){
        // do nothing
    }

    public Serializable save(DeliveryMode deliveryMode){
        log.debug("saving DeliveryMode instance");
        Serializable cls;
        try{
        	cls = commonDao.save(deliveryMode);
            log.debug("save successful");
        }catch(RuntimeException re){
            log.error("save failed",re);
            throw re;
        }
        return cls;
    }

    public void delete(DeliveryMode deliveryMode){
        log.debug("deleting DeliveryMode instance");
        try{
            commonDao.delete(deliveryMode);
            log.debug("delete successful");
        }catch(RuntimeException re){
            log.error("delete failed",re);
            throw re;
        }
    }

    public DeliveryMode findById(Integer id){
        log.debug("getting DeliveryMode instance with id: " + id);
        try{

            return (DeliveryMode) commonDao.findById(
            		DeliveryMode.class,id);
        }catch(RuntimeException re){
            log.error("get failed",re);
            throw re;
        }
    }

    public List<DeliveryMode> findByExample(DeliveryMode instance){
        log.debug("finding DeliveryMode instance by example");
        try{

            return commonDao.getEntitesForExample(instance,0,0);
        }catch(RuntimeException re){
            log.error("find by example failed",re);
            throw re;
        }
    }

    public List findByProperty(String propertyName,Object value){
        log.debug("finding DeliveryMode instance with property: "
                + propertyName + ", value: " + value);
        try{
            String queryString = "from DeliveryMode as model where model."
                    + propertyName + "= ?";
            return commonDao.getEntitiesByPropertiesWithHql(queryString,0,0,value);
        }catch(RuntimeException re){
            log.error("find by property name failed",re);
            throw re;
        }
    }

    public List findAll(){
        log.debug("finding all DeliveryMode instances");
        try{
            String queryString = "from DeliveryMode";
            return commonDao.getEntitiesByPropertiesWithHql(queryString,0,0);
        }catch(RuntimeException re){
            log.error("find all failed",re);
            throw re;
        }
    }

    public DeliveryMode merge(DeliveryMode detachedInstance){
        log.debug("merging DeliveryMode instance");
        try{
        	DeliveryMode result = (DeliveryMode) commonDao
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        }catch(RuntimeException re){
            log.error("merge failed",re);
            throw re;
        }
    }

    public void attachDirty(DeliveryMode instance){
        log.debug("attaching dirty DeliveryMode instance");
        try{
            commonDao.saveOrUpdate(instance);
            log.debug("attach successful");
        }catch(RuntimeException re){
            log.error("attach failed",re);
            throw re;
        }
    }

    
    public int removeDeliveryModeId(Integer id){

        log.debug("removeDeliveryModeId: " + id);
        try{
            String queryString = "delete  from DeliveryMode as model where model.id = "
                    + id;
            return commonDao.executeUpdateOrDelete(queryString);
        }catch(RuntimeException re){
            log.error("removeDeliveryModeId failed",re);
            throw re;
        }
    }
    
    
    public static DeliveryModeDAO getFromApplicationContext(
            ApplicationContext ctx){
        return (DeliveryModeDAO) ctx.getBean("DeliveryModeDAO");
    }
}