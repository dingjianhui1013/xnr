package com.xnradmin.core.dao.mall.order;

import java.io.Serializable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Repository;

import com.cntinker.util.StringHelper;
import com.xnradmin.core.dao.CommonDAO;
import com.xnradmin.po.mall.order.OrderRecord;
import com.xnradmin.po.mall.order.ShoppingCart;


@Repository("ShoppingCartDAO")
public class ShoppingCartDAO{
    
    @Autowired
    private CommonDAO commonDao;
    
    private static final Logger log = LoggerFactory
            .getLogger(ShoppingCartDAO.class);

    // property constants

    public static final String ID = "id";                                                                        
    public static final String CLIENT_USER_ID = "clientUserId"; //用户ID
    public static final String GOODS_ID = "goodsId"; //商品Id
    public static final String GOODS_COUNT = "goodsCount"; //商品数量
    public static final String CURRENT_PRICE = "currentPrice"; //当前价格
    public static final String CURRENT_PRICE_TYPE = "currentPriceType"; //当前价格类型（原价，优惠价）
    public static final String TOTAL_PRICE = "totalPrice"; //当前价格
    public static final String STAFF_ID = "staffId";//隶属用户Id\
    public static final String PRIMARY_CONFIGURATION_ID = "primaryConfigurationId"; //对应商城ID
    public static final String SHOPPING_CART_TIME= "shoppingCartTime"; //生成时间 

    protected void initDao(){
        // do nothing
    }

    public Serializable save(ShoppingCart shoppingCart){
        log.debug("saving ShoppingCart instance");
        Serializable cls;
        try{
        	cls = commonDao.save(shoppingCart);
            log.debug("save successful");
        }catch(RuntimeException re){
            log.error("save failed",re);
            throw re;
        }
        return cls;
    }

    public void delete(ShoppingCart shoppingCart){
        log.debug("deleting ShoppingCart instance");
        try{
            commonDao.delete(shoppingCart);
            log.debug("delete successful");
        }catch(RuntimeException re){
            log.error("delete failed",re);
            throw re;
        }
    }

    public ShoppingCart findById(int id){
        log.debug("getting ShoppingCart instance with id: " + id);
        try{

            return (ShoppingCart) commonDao.findById(
            		ShoppingCart.class,id);
        }catch(RuntimeException re){
            log.error("get failed",re);
            throw re;
        }
    }

    public List<ShoppingCart> findByExample(ShoppingCart instance){
        log.debug("finding ShoppingCart instance by example");
        try{

            return commonDao.getEntitesForExample(instance,0,0);
        }catch(RuntimeException re){
            log.error("find by example failed",re);
            throw re;
        }
    }

    public List findByProperty(String propertyName,Object value){
        log.debug("finding ShoppingCart instance with property: "
                + propertyName + ", value: " + value);
        try{
            String queryString = "from ShoppingCart as model where model."
                    + propertyName + "= ?";
            return commonDao.getEntitiesByPropertiesWithHql(queryString,0,0,value);
        }catch(RuntimeException re){
            log.error("find by property name failed",re);
            throw re;
        }
    }
    
    public List findByOlay(String goodsId, String clientUserId){
        log.debug("finding findByOlay "
                + goodsId + ":" + clientUserId);
        try{
            String queryString = "from ShoppingCart where goodsId= "+goodsId+" and clientUserId="+clientUserId;
            return commonDao.getEntitiesByPropertiesWithHql(queryString,0,0);
        }catch(RuntimeException re){
            log.error("find by property name failed",re);
            throw re;
        }
    }
    
    public List findAll(){
        log.debug("finding all ShoppingCart instances");
        try{
            String queryString = "from ShoppingCart";
            return commonDao.getEntitiesByPropertiesWithHql(queryString,0,0);
        }catch(RuntimeException re){
            log.error("find all failed",re);
            throw re;
        }
    }

    public ShoppingCart merge(ShoppingCart detachedInstance){
        log.debug("merging ShoppingCart instance");
        try{
        	ShoppingCart result = (ShoppingCart) commonDao
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        }catch(RuntimeException re){
            log.error("merge failed",re);
            throw re;
        }
    }

    public void attachDirty(ShoppingCart instance){
        log.debug("attaching dirty ShoppingCart instance");
        try{
            commonDao.saveOrUpdate(instance);
            log.debug("attach successful");
        }catch(RuntimeException re){
            log.error("attach failed",re);
            throw re;
        }
    }

    
    public int removeShoppingCartId(Long id){

        log.debug("removeShoppingCartId: " + id);
        try{
            String queryString = "delete  from ShoppingCart as model where model.id = "
                    + id;
            return commonDao.executeUpdateOrDelete(queryString);
        }catch(RuntimeException re){
            log.error("removeOrderRecordId failed",re);
            throw re;
        }
    }
    
    
    public static ShoppingCartDAO getFromApplicationContext(
            ApplicationContext ctx){
        return (ShoppingCartDAO) ctx.getBean("ShoppingCartDAO");
    }
}