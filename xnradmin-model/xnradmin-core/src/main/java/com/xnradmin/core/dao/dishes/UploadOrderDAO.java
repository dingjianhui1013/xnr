package com.xnradmin.core.dao.dishes;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Repository;

import com.cntinker.util.StringHelper;
import com.xnradmin.core.dao.CommonDAO;
import com.xnradmin.po.dishes.RawMaterial;
import com.xnradmin.po.dishes.UploadOrder;


@Repository("UploadOrderDAO")
public class UploadOrderDAO{
    
    @Autowired
    private CommonDAO commonDao;
    
    private static final Logger log = LoggerFactory
            .getLogger(UploadOrderDAO.class);

    // property constants

    public static final String ID = "id";
    public static final String ORDER_NUMBER = "orderNumber";  		//订单编号
    public static final String ORDER_TIME = "orderTime";  			//下单时间
    public static final String DISH_NAME = "dishName";  			//菜品名称
    public static final String PROPERTY = "property";  				//商品属性
    public static final String UNIT_PRICE = "unitPrice";  			//商品单价
    public static final String DISH_COUNT = "dishCount";  			//商品数量
    public static final String TOTAL_PRICE = "totalPrice";  		//商品总价
    public static final String PAY_STATUS = "payStatus";  			//支付状态
    public static final String DISPATCH_STATUS = "dispatchStatus";  //派单状态
    public static final String ORDER_STATUS = "orderStatus";  		//订单状态
    public static final String CUSTOMER = "customer";  				//收货人
    public static final String MOBILE = "mobile";  					//手机号
    public static final String ADDERSS = "address";  				//地址
    public static final String CODE = "code";  						//邮编
    public static final String MODIFY_TIME = "modifyTime";  		//修改时间
    public static final String MODIFY_STAFF_ID = "modifyStaffId";	//修改人

    protected void initDao(){
        // do nothing
    }

    public void save(UploadOrder uploadOrder){
        log.debug("saving UploadOrer instance");
        try{
            commonDao.save(uploadOrder);
            log.debug("save successful");
        }catch(RuntimeException re){
            log.error("save failed",re);
            throw re;
        }
    }

    public void delete(UploadOrder uploadOrder){
        log.debug("deleting UploadOrder instance");
        try{
            commonDao.delete(uploadOrder);
            log.debug("delete successful");
        }catch(RuntimeException re){
            log.error("delete failed",re);
            throw re;
        }
    }

    public UploadOrder findById(Integer id){
        log.debug("getting UploadOrder instance with id: " + id);
        try{

            return (UploadOrder) commonDao.findById(
            		UploadOrder.class,id);
        }catch(RuntimeException re){
            log.error("get failed",re);
            throw re;
        }
    }

    public List<UploadOrder> findByExample(UploadOrder instance){
        log.debug("finding UploadOrder instance by example");
        try{

            return commonDao.getEntitesForExample(instance,0,0);
        }catch(RuntimeException re){
            log.error("find by example failed",re);
            throw re;
        }
    }

    public List findByProperty(String propertyName,Object value){
        log.debug("finding UploadOrder instance with property: "
                + propertyName + ", value: " + value);
        try{
            String queryString = "from UploadOrder as model where model."
                    + propertyName + "= ?";
            return commonDao.getEntitiesByPropertiesWithHql(queryString,0,0,value);
        }catch(RuntimeException re){
            log.error("find by property name failed",re);
            throw re;
        }
    }

    public List findAll(){
        log.debug("finding all UploadOrder instances");
        try{
            String queryString = "from UploadOrder";
            return commonDao.getEntitiesByPropertiesWithHql(queryString,0,0);
        }catch(RuntimeException re){
            log.error("find all failed",re);
            throw re;
        }
    }

    public UploadOrder merge(UploadOrder detachedInstance){
        log.debug("merging RawMaterial instance");
        try{
        	UploadOrder result = (UploadOrder) commonDao
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        }catch(RuntimeException re){
            log.error("merge failed",re);
            throw re;
        }
    }

    public void attachDirty(UploadOrder instance){
        log.debug("attaching dirty UploadOrder instance");
        try{
            commonDao.saveOrUpdate(instance);
            log.debug("attach successful");
        }catch(RuntimeException re){
            log.error("attach failed",re);
            throw re;
        }
    }

    
    public int removeRawMaterialId(Integer id){

        log.debug("removeDishId: " + id);
        try{
            String queryString = "delete  from UploadOrder as model where model.id = "
                    + id;
            return commonDao.executeUpdateOrDelete(queryString);
        }catch(RuntimeException re){
            log.error("removeUploadOrderId failed",re);
            throw re;
        }
    }
    
    
    public static UploadOrderDAO getFromApplicationContext(
            ApplicationContext ctx){
        return (UploadOrderDAO) ctx.getBean("UploadOrderDAO");
    }
}