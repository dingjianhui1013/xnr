package com.xnradmin.core.dao.business.order;

import java.io.Serializable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Repository;

import com.xnradmin.core.dao.CommonDAO;
import com.xnradmin.po.business.BusinessOrderGoodsRelation;


@Repository("BusinessOrderGoodsRelationDAO")
public class BusinessOrderGoodsRelationDAO{
    
    @Autowired
    private CommonDAO commonDao;
    
    private static final Logger log = LoggerFactory
            .getLogger(BusinessOrderGoodsRelationDAO.class);

    // property constants

    public static final String ID = "id";                                                      
    public static final String ORDER_RECORD_ID = "orderRecordId"; //订单ID                     
    public static final String CLIENT_USER_ID = "clientUserId"; //用户ID                       
    public static final String GOODS_ID = "goodsId"; //商品Id                                  
    public static final String GOODS_COUNT = "goodsCount"; //商品数量                          
    public static final String CURRENT_PRICE = "currentPrice"; //当前价格                      
    public static final String CURRENT_PRICE_TYPE = "currentPriceType"; //当前价格类型（原价，优惠价）
    public static final String STAFF_ID = "staffId";//隶属用户Id                               
    public static final String PRIMARY_CONFIGURATION_ID = "primaryConfigurationId"; //对应商城ID
    public static final String ORDER_GOODS_RELATION_TIME= "orderGoodsRelationTime"; //生成时间 

    protected void initDao(){
        // do nothing
    }

    public Serializable save(BusinessOrderGoodsRelation businessOrderGoodsRelation){
        log.debug("saving BusinessOrderGoodsRelation instance");
        Serializable cls;
        try{
        	cls = commonDao.save(businessOrderGoodsRelation);
            log.debug("save successful");
        }catch(RuntimeException re){
            log.error("save failed",re);
            throw re;
        }
        return cls;
    }

    public void delete(BusinessOrderGoodsRelation businessOrderGoodsRelation){
        log.debug("deleting BusinessOrderGoodsRelation instance");
        try{
            commonDao.delete(businessOrderGoodsRelation);
            log.debug("delete successful");
        }catch(RuntimeException re){
            log.error("delete failed",re);
            throw re;
        }
    }

    public BusinessOrderGoodsRelation findById(Long id){
        log.debug("getting BusinessOrderGoodsRelation instance with id: " + id);
        try{

            return (BusinessOrderGoodsRelation) commonDao.findById(
            		BusinessOrderGoodsRelation.class,id);
        }catch(RuntimeException re){
            log.error("get failed",re);
            throw re;
        }
    }

    public List<BusinessOrderGoodsRelation> findByExample(BusinessOrderGoodsRelation instance){
        log.debug("finding BusinessOrderGoodsRelation instance by example");
        try{

            return commonDao.getEntitesForExample(instance,0,0);
        }catch(RuntimeException re){
            log.error("find by example failed",re);
            throw re;
        }
    }

    public List findByProperty(String propertyName,Object value){
        log.debug("finding BusinessOrderGoodsRelation instance with property: "
                + propertyName + ", value: " + value);
        try{
            String queryString = "from BusinessOrderGoodsRelation as model where model."
                    + propertyName + "= ?";
            return commonDao.getEntitiesByPropertiesWithHql(queryString,0,0,value);
        }catch(RuntimeException re){
            log.error("find by property name failed",re);
            throw re;
        }
    }
    
    public List findAll(){
        log.debug("finding all BusinessOrderGoodsRelation instances");
        try{
            String queryString = "from BusinessOrderGoodsRelation";
            return commonDao.getEntitiesByPropertiesWithHql(queryString,0,0);
        }catch(RuntimeException re){
            log.error("find all failed",re);
            throw re;
        }
    }

    public BusinessOrderGoodsRelation merge(BusinessOrderGoodsRelation detachedInstance){
        log.debug("merging OrderRecord instance");
        try{
        	BusinessOrderGoodsRelation result = (BusinessOrderGoodsRelation) commonDao
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        }catch(RuntimeException re){
            log.error("merge failed",re);
            throw re;
        }
    }

    public void attachDirty(BusinessOrderGoodsRelation instance){
        log.debug("attaching dirty BusinessOrderGoodsRelation instance");
        try{
            commonDao.saveOrUpdate(instance);
            log.debug("attach successful");
        }catch(RuntimeException re){
            log.error("attach failed",re);
            throw re;
        }
    }

    
    public int removeBusinessOrderGoodsRelationId(Long id){

        log.debug("removeBusinessOrderGoodsRelationId: " + id);
        try{
            String queryString = "delete  from BusinessOrderGoodsRelation as model where model.id = "
                    + id;
            return commonDao.executeUpdateOrDelete(queryString);
        }catch(RuntimeException re){
            log.error("removeBusinessOrderGoodsRelationId failed",re);
            throw re;
        }
    }
    
    
    public static BusinessOrderGoodsRelationDAO getFromApplicationContext(
            ApplicationContext ctx){
        return (BusinessOrderGoodsRelationDAO) ctx.getBean("BusinessOrderGoodsRelationDAO");
    }
}