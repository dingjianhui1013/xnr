package com.xnradmin.core.dao.mall.order;

import java.io.Serializable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Repository;

import com.xnradmin.core.dao.CommonDAO;
import com.xnradmin.po.mall.order.Purchase;


@Repository("PurchaseDAO")
public class PurchaseDAO{
    
    @Autowired
    private CommonDAO commonDao;
    
    private static final Logger log = LoggerFactory
            .getLogger(PurchaseDAO.class);

    // property constants

    public static final String ID = "id";                                                                        
    public static final String CLIENT_USER_ID = "clientUserId"; //用户ID
    public static final String ORDER_RECORD_ID = "orderRecordId"; //订单ID
    public static final String ORDER_NO = "orderNo"; //订单号
    public static final String GOODS_ID = "goodsId"; //商品ID
    public static final String GOODS_NAME = "goodsName"; //商品名称
    public static final String GOODS_COUNT = "goodsCount"; //商品数量
    public static final String DISH_ID = "dishId"; //菜品ID
	public static final String DISH_NAME = "dishName"; //菜品名称
	public static final String DISH_COUNT = "dishCount"; //菜品名称
	public static final String RAW_MATERIAL_ID = "rawMaterialId"; //材料ID
	public static final String RAW_MATERIAL_NAME = "rawMaterialName"; //材料名称
	public static final String WEIGHT_ID = "weightId"; //数量单位ID
	public static final String WEIGHT_NAME = "weightName"; //数量单位名称
	public static final String NUMBER = "number"; //采购数量
	public static final String CREATE_TIME = "createTime"; //搜索时间 

    protected void initDao(){
        // do nothing
    }

    public Serializable save(Purchase Purchase){
        log.debug("saving Purchase instance");
        Serializable cls;
        try{
        	cls = commonDao.save(Purchase);
            log.debug("save successful");
        }catch(RuntimeException re){
            log.error("save failed",re);
            throw re;
        }
        return cls;
    }

    public void delete(Purchase purchase){
        log.debug("deleting Purchase instance");
        try{
            commonDao.delete(purchase);
            log.debug("delete successful");
        }catch(RuntimeException re){
            log.error("delete failed",re);
            throw re;
        }
    }

    public Purchase findById(Long id){
        log.debug("getting Purchase instance with id: " + id);
        try{

            return (Purchase) commonDao.findById(
            		Purchase.class,id);
        }catch(RuntimeException re){
            log.error("get failed",re);
            throw re;
        }
    }

    public List<Purchase> findByExample(Purchase instance){
        log.debug("finding Purchase instance by example");
        try{

            return commonDao.getEntitesForExample(instance,0,0);
        }catch(RuntimeException re){
            log.error("find by example failed",re);
            throw re;
        }
    }

    public List findByProperty(String propertyName,Object value){
        log.debug("finding Purchase instance with property: "
                + propertyName + ", value: " + value);
        try{
            String queryString = "from Purchase as model where model."
                    + propertyName + "= ?";
            return commonDao.getEntitiesByPropertiesWithHql(queryString,0,0,value);
        }catch(RuntimeException re){
            log.error("find by property name failed",re);
            throw re;
        }
    }
    
    
    public List findAll(){
        log.debug("finding all Purchase instances");
        try{
            String queryString = "from Purchase";
            return commonDao.getEntitiesByPropertiesWithHql(queryString,0,0);
        }catch(RuntimeException re){
            log.error("find all failed",re);
            throw re;
        }
    }

    public Purchase merge(Purchase detachedInstance){
        log.debug("merging Purchase instance");
        try{
        	Purchase result = (Purchase) commonDao
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        }catch(RuntimeException re){
            log.error("merge failed",re);
            throw re;
        }
    }

    public void attachDirty(Purchase instance){
        log.debug("attaching dirty Purchase instance");
        try{
            commonDao.saveOrUpdate(instance);
            log.debug("attach successful");
        }catch(RuntimeException re){
            log.error("attach failed",re);
            throw re;
        }
    }

    
    public int removePurchaseId(Long id){

        log.debug("removePurchaseId: " + id);
        try{
            String queryString = "delete  from Purchase as model where model.id = "
                    + id;
            return commonDao.executeUpdateOrDelete(queryString);
        }catch(RuntimeException re){
            log.error("removePurchaseId failed",re);
            throw re;
        }
    }
    
    public int removeOrderRecordId(Long id){

        log.debug("removeOrderRecordId: " + id);
        try{
            String queryString = "delete  from Purchase as model where model.orderRecordId = "
                    + id;
            return commonDao.executeUpdateOrDelete(queryString);
        }catch(RuntimeException re){
            log.error("removeOrderRecordId failed",re);
            throw re;
        }
    }
    
    public static PurchaseDAO getFromApplicationContext(
            ApplicationContext ctx){
        return (PurchaseDAO) ctx.getBean("PurchaseDAO");
    }
}