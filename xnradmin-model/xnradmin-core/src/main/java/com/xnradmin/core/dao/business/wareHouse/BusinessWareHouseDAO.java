package com.xnradmin.core.dao.business.wareHouse;

import java.io.Serializable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Repository;

import com.xnradmin.core.dao.CommonDAO;
import com.xnradmin.core.dao.business.wareHouse.BusinessWareHouseDAO;
import com.xnradmin.po.business.wareHouse.BusinessWareHouse;


@Repository("BusinessWareHouseDAO")
public class BusinessWareHouseDAO{
    
    @Autowired
    private CommonDAO commonDao;
    
    private static final Logger log = LoggerFactory
            .getLogger(BusinessWareHouseDAO.class);

    // property constants

    public static final String ID="id";
    public static final String WAREHOUSE_NAME="wareHouseName";//仓库名称
    public static final String WAREHOUSE_ADDRESS="wareHouseAddress";//仓库地址
    public static final String WAREHOUSE_SERNO="wareHouseSerno";//仓库序号
    public static final String WAREHOUSE_STATUS="wareHouseStatus";//仓库状态
    public static final String CREATE_TIME="createTime";//建立时间
    public static final String CREATE_STAFF_ID="createStaffId";//建立人
    public static final String MODIFY_TIME="modifyTime";//修改时间
    public static final String MODIFY_STAFF_ID="modifyStaffId";//修改人

    protected void initDao(){
        // do nothing
    }

    public Serializable save(BusinessWareHouse businessWareHouse){
        log.debug("saving BusinessWareHouse instance");
        Serializable cls;
        try{
        	cls = commonDao.save(businessWareHouse);
            log.debug("save successful");
        }catch(RuntimeException re){
            log.error("save failed",re);
            throw re;
        }
        return cls;
    }

    public void delete(BusinessWareHouse businessWareHouse){
        log.debug("deleting BusinessWareHouse instance");
        try{
            commonDao.delete(businessWareHouse);
            log.debug("delete successful");
        }catch(RuntimeException re){
            log.error("delete failed",re);
            throw re;
        }
    }

    public BusinessWareHouse findById(Long id){
        log.debug("getting BusinessWareHouse instance with id: " + id);
        try{

            return (BusinessWareHouse) commonDao.findById(
            		BusinessWareHouse.class,id);
        }catch(RuntimeException re){
            log.error("get failed",re);
            throw re;
        }
    }

    public List<BusinessWareHouse> findByExample(BusinessWareHouse instance){
        log.debug("finding BusinessWareHouse instance by example");
        try{

            return commonDao.getEntitesForExample(instance,0,0);
        }catch(RuntimeException re){
            log.error("find by example failed",re);
            throw re;
        }
    }

    public List findByProperty(String propertyName,Object value){
        log.debug("finding BusinessWareHouse instance with property: "
                + propertyName + ", value: " + value);
        try{
            String queryString = "from BusinessWareHouse as model where model."
                    + propertyName + "= ?";
            return commonDao.getEntitiesByPropertiesWithHql(queryString,0,0,value);
        }catch(RuntimeException re){
            log.error("find by property name failed",re);
            throw re;
        }
    }

    public List findAll(){
        log.debug("finding all BusinessWareHouse instances");
        try{
            String queryString = "from BusinessWareHouse";
            return commonDao.getEntitiesByPropertiesWithHql(queryString,0,0);
        }catch(RuntimeException re){
            log.error("find all failed",re);
            throw re;
        }
    }

    public BusinessWareHouse merge(BusinessWareHouse detachedInstance){
        log.debug("merging BusinessWareHouse instance");
        try{
        	BusinessWareHouse result = (BusinessWareHouse) commonDao
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        }catch(RuntimeException re){
            log.error("merge failed",re);
            throw re;
        }
    }

    public void attachDirty(BusinessWareHouse instance){
        log.debug("attaching dirty BusinessWareHouse instance");
        try{
            commonDao.saveOrUpdate(instance);
            log.debug("attach successful");
        }catch(RuntimeException re){
            log.error("attach failed",re);
            throw re;
        }
    }

    
    public int removeBusinessWareHouseId(Integer id){

        log.debug("removeBusinessWareHouseId: " + id);
        try{
            String queryString = "delete  from BusinessWareHouse as model where model.id = "
                    + id;
            return commonDao.executeUpdateOrDelete(queryString);
        }catch(RuntimeException re){
            log.error("removeBusinessWareHouseId failed",re);
            throw re;
        }
    }
    
    
    public static BusinessWareHouse getFromApplicationContext(
            ApplicationContext ctx){
        return (BusinessWareHouse) ctx.getBean("BusinessWareHouseDAO");
    }
}