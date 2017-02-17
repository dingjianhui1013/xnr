package com.xnradmin.core.dao.business.wareHouse;

import java.io.Serializable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Repository;

import com.xnradmin.core.dao.CommonDAO;
import com.xnradmin.core.dao.business.wareHouse.BusinessWareHouseInvRelDAO;
import com.xnradmin.po.business.wareHouse.BusinessWareHouseInvRel;


@Repository("BusinessWareHouseInvRelDAO")
public class BusinessWareHouseInvRelDAO{
    
    @Autowired
    private CommonDAO commonDao;
    
    private static final Logger log = LoggerFactory
            .getLogger(BusinessWareHouseInvRelDAO.class);

    // property constants

    public static final String ID="id";
    public static final String WAREHOUSE_ID="wareHouseId";//仓库ID
    public static final String GOODS_ID="goodsId";//商品ID
    public static final String COUNT="count";//商品总量
    public static final String WEIGHT_ID = "weightId";//数量单位
    public static final String CREATE_TIME="createTime";//建立时间
    public static final String CREATE_STAFF_ID="createStaffId";//建立人
    public static final String MODIFY_TIME="modifyTime";//修改时间
    public static final String MODIFY_STAFF_ID="modifyStaffId";//修改人

    protected void initDao(){
        // do nothing
    }

    public Serializable save(BusinessWareHouseInvRel businessWareHouseInvRel){
        log.debug("saving BusinessWareHouseInvRel instance");
        Serializable cls;
        try{
        	cls = commonDao.save(businessWareHouseInvRel);
            log.debug("save successful");
        }catch(RuntimeException re){
            log.error("save failed",re);
            throw re;
        }
        return cls;
    }

    public void delete(BusinessWareHouseInvRel businessWareHouseInvRel){
        log.debug("deleting BusinessWareHouseInvRel instance");
        try{
            commonDao.delete(businessWareHouseInvRel);
            log.debug("delete successful");
        }catch(RuntimeException re){
            log.error("delete failed",re);
            throw re;
        }
    }

    public BusinessWareHouseInvRel findById(Long id){
        log.debug("getting BusinessWareHouseInvRel instance with id: " + id);
        try{

            return (BusinessWareHouseInvRel) commonDao.findById(
            		BusinessWareHouseInvRel.class,id);
        }catch(RuntimeException re){
            log.error("get failed",re);
            throw re;
        }
    }

    public List<BusinessWareHouseInvRel> findByExample(BusinessWareHouseInvRel instance){
        log.debug("finding BusinessWareHouseInvRel instance by example");
        try{

            return commonDao.getEntitesForExample(instance,0,0);
        }catch(RuntimeException re){
            log.error("find by example failed",re);
            throw re;
        }
    }

    public List findByProperty(String propertyName,Object value){
        log.debug("finding BusinessWareHouseInvRel instance with property: "
                + propertyName + ", value: " + value);
        try{
            String queryString = "from BusinessWareHouseInvRel as model where model."
                    + propertyName + "= ?";
            return commonDao.getEntitiesByPropertiesWithHql(queryString,0,0,value);
        }catch(RuntimeException re){
            log.error("find by property name failed",re);
            throw re;
        }
    }

    public List findAll(){
        log.debug("finding all BusinessWareHouseInvRel instances");
        try{
            String queryString = "from BusinessWareHouseInvRel";
            return commonDao.getEntitiesByPropertiesWithHql(queryString,0,0);
        }catch(RuntimeException re){
            log.error("find all failed",re);
            throw re;
        }
    }

    public BusinessWareHouseInvRel merge(BusinessWareHouseInvRel detachedInstance){
        log.debug("merging BusinessWareHouseInvRel instance");
        try{
        	BusinessWareHouseInvRel result = (BusinessWareHouseInvRel) commonDao
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        }catch(RuntimeException re){
            log.error("merge failed",re);
            throw re;
        }
    }

    public void attachDirty(BusinessWareHouseInvRel instance){
        log.debug("attaching dirty BusinessWareHouseInvRel instance");
        try{
            commonDao.saveOrUpdate(instance);
            log.debug("attach successful");
        }catch(RuntimeException re){
            log.error("attach failed",re);
            throw re;
        }
    }

    
    public int removeBusinessWareHouseInvRelId(Integer id){

        log.debug("removeBusinessWareHouseInvRelId: " + id);
        try{
            String queryString = "delete  from BusinessWareHouseInvRel as model where model.id = "
                    + id;
            return commonDao.executeUpdateOrDelete(queryString);
        }catch(RuntimeException re){
            log.error("removeBusinessWareHouseInvRelId failed",re);
            throw re;
        }
    }
    
    
    public static BusinessWareHouseInvRel getFromApplicationContext(
            ApplicationContext ctx){
        return (BusinessWareHouseInvRel) ctx.getBean("BusinessWareHouseInvRelDAO");
    }
}