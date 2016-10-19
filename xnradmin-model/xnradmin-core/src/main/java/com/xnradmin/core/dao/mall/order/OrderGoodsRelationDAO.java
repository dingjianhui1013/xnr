package com.xnradmin.core.dao.mall.order;

import java.io.Serializable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Repository;

import com.xnradmin.core.dao.CommonDAO;
import com.xnradmin.po.mall.order.OrderGoodsRelation;
import com.xnradmin.po.mall.order.OrderRecord;


@Repository("OrderGoodsRelationDAO")
public class OrderGoodsRelationDAO{
    
    @Autowired
    private CommonDAO commonDao;
    
    private static final Logger log = LoggerFactory
            .getLogger(OrderGoodsRelationDAO.class);

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

    public Serializable save(OrderGoodsRelation orderGoodsRelation){
        log.debug("saving OrderGoodsRelation instance");
        Serializable cls;
        try{
        	cls = commonDao.save(orderGoodsRelation);
            log.debug("save successful");
        }catch(RuntimeException re){
            log.error("save failed",re);
            throw re;
        }
        return cls;
    }

    public void delete(OrderGoodsRelation orderGoodsRelation){
        log.debug("deleting OrderGoodsRelation instance");
        try{
            commonDao.delete(orderGoodsRelation);
            log.debug("delete successful");
        }catch(RuntimeException re){
            log.error("delete failed",re);
            throw re;
        }
    }

    public OrderGoodsRelation findById(Long id){
        log.debug("getting OrderGoodsRelation instance with id: " + id);
        try{

            return (OrderGoodsRelation) commonDao.findById(
            		OrderGoodsRelation.class,id);
        }catch(RuntimeException re){
            log.error("get failed",re);
            throw re;
        }
    }

    public List<OrderGoodsRelation> findByExample(OrderGoodsRelation instance){
        log.debug("finding OrderGoodsRelation instance by example");
        try{

            return commonDao.getEntitesForExample(instance,0,0);
        }catch(RuntimeException re){
            log.error("find by example failed",re);
            throw re;
        }
    }

    public List findByProperty(String propertyName,Object value){
        log.debug("finding OrderGoodsRelation instance with property: "
                + propertyName + ", value: " + value);
        try{
            String queryString = "from OrderGoodsRelation as model where model."
                    + propertyName + "= ?";
            return commonDao.getEntitiesByPropertiesWithHql(queryString,0,0,value);
        }catch(RuntimeException re){
            log.error("find by property name failed",re);
            throw re;
        }
    }
    
    public List findAll(){
        log.debug("finding all OrderGoodsRelation instances");
        try{
            String queryString = "from OrderGoodsRelation";
            return commonDao.getEntitiesByPropertiesWithHql(queryString,0,0);
        }catch(RuntimeException re){
            log.error("find all failed",re);
            throw re;
        }
    }

    public OrderGoodsRelation merge(OrderGoodsRelation detachedInstance){
        log.debug("merging OrderRecord instance");
        try{
        	OrderGoodsRelation result = (OrderGoodsRelation) commonDao
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        }catch(RuntimeException re){
            log.error("merge failed",re);
            throw re;
        }
    }

    public void attachDirty(OrderGoodsRelation instance){
        log.debug("attaching dirty OrderGoodsRelation instance");
        try{
            commonDao.saveOrUpdate(instance);
            log.debug("attach successful");
        }catch(RuntimeException re){
            log.error("attach failed",re);
            throw re;
        }
    }

    
    public int removeOrderGoodsRelationId(Long id){

        log.debug("removeOrderGoodsRelationId: " + id);
        try{
            String queryString = "delete  from OrderGoodsRelation as model where model.id = "
                    + id;
            return commonDao.executeUpdateOrDelete(queryString);
        }catch(RuntimeException re){
            log.error("removeOrderGoodsRelationId failed",re);
            throw re;
        }
    }
    
    
    public static OrderGoodsRelationDAO getFromApplicationContext(
            ApplicationContext ctx){
        return (OrderGoodsRelationDAO) ctx.getBean("OrderGoodsRelationDAO");
    }
}