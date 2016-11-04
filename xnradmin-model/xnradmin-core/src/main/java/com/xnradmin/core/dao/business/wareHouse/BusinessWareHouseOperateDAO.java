package com.xnradmin.core.dao.business.wareHouse;

import java.io.Serializable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Repository;

import com.xnradmin.core.dao.CommonDAO;
import com.xnradmin.core.dao.business.wareHouse.BusinessWareHouseOperateDAO;
import com.xnradmin.po.business.wareHouse.BusinessWareHouseOperate;


@Repository("BusinessWareHouseOperateDAO")
public class BusinessWareHouseOperateDAO{
    
    @Autowired
    private CommonDAO commonDao;
    
    private static final Logger log = LoggerFactory
            .getLogger(BusinessWareHouseOperateDAO.class);

    // property constants

    public static final String ID="id";
    public static final String WAREHOUSE_ID="wareHouseId";//仓库ID
    public static final String GOODS_ID="goodsId";//商品ID
    public static final String COUNT="count";//商品总量
    public static final String OPERATION_STATUS = "operationStatus";//操作状态（入库，出库）
    public static final String REASON_STATUS = "reasonStatus";//操作理由（入库，出库）
    public static final String PRICE = "price";//出入库价格
    public static final String WEIGHT_ID = "weightId";//数量单位
    public static final String SUPPLIER_STAFF_ID = "supplierStaffId";//供货商ID
    public static final String PURCHASER_STAFF_ID = "purchaserStaffId";//采购商ID
    public static final String REMARK = "remark";//备注
    public static final String OPERATION_TYPE = "operationType";//操作类型（入库出库）
    public static final String CREATE_TIME="createTime";//建立时间
    public static final String CREATE_STAFF_ID="createStaffId";//建立人
    public static final String MODIFY_TIME="modifyTime";//修改时间
    public static final String MODIFY_STAFF_ID="modifyStaffId";//修改人

    protected void initDao(){
        // do nothing
    }

    public Serializable save(BusinessWareHouseOperate businessWareHouseOperate){
        log.debug("saving BusinessWareHouseOperate instance");
        Serializable cls;
        try{
        	cls = commonDao.save(businessWareHouseOperate);
            log.debug("save successful");
        }catch(RuntimeException re){
            log.error("save failed",re);
            throw re;
        }
        return cls;
    }

    public void delete(BusinessWareHouseOperate businessWareHouseOperate){
        log.debug("deleting BusinessWareHouseOperate instance");
        try{
            commonDao.delete(businessWareHouseOperate);
            log.debug("delete successful");
        }catch(RuntimeException re){
            log.error("delete failed",re);
            throw re;
        }
    }

    public BusinessWareHouseOperate findById(Long id){
        log.debug("getting BusinessWareHouseOperate instance with id: " + id);
        try{

            return (BusinessWareHouseOperate) commonDao.findById(
            		BusinessWareHouseOperate.class,id);
        }catch(RuntimeException re){
            log.error("get failed",re);
            throw re;
        }
    }

    public List<BusinessWareHouseOperate> findByExample(BusinessWareHouseOperate instance){
        log.debug("finding BusinessWareHouseOperate instance by example");
        try{

            return commonDao.getEntitesForExample(instance,0,0);
        }catch(RuntimeException re){
            log.error("find by example failed",re);
            throw re;
        }
    }

    public List findByProperty(String propertyName,Object value){
        log.debug("finding BusinessWareHouseOperate instance with property: "
                + propertyName + ", value: " + value);
        try{
            String queryString = "from BusinessWareHouseOperate as model where model."
                    + propertyName + "= ?";
            return commonDao.getEntitiesByPropertiesWithHql(queryString,0,0,value);
        }catch(RuntimeException re){
            log.error("find by property name failed",re);
            throw re;
        }
    }

    public List findAll(){
        log.debug("finding all BusinessWareHouseOperate instances");
        try{
            String queryString = "from BusinessWareHouseOperate";
            return commonDao.getEntitiesByPropertiesWithHql(queryString,0,0);
        }catch(RuntimeException re){
            log.error("find all failed",re);
            throw re;
        }
    }

    public BusinessWareHouseOperate merge(BusinessWareHouseOperate detachedInstance){
        log.debug("merging BusinessWareHouseOperate instance");
        try{
        	BusinessWareHouseOperate result = (BusinessWareHouseOperate) commonDao
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        }catch(RuntimeException re){
            log.error("merge failed",re);
            throw re;
        }
    }

    public void attachDirty(BusinessWareHouseOperate instance){
        log.debug("attaching dirty BusinessWareHouseOperate instance");
        try{
            commonDao.saveOrUpdate(instance);
            log.debug("attach successful");
        }catch(RuntimeException re){
            log.error("attach failed",re);
            throw re;
        }
    }

    
    public int removeBusinessWareHouseOperateId(Long id){

        log.debug("removeBusinessWareHouseOperateId: " + id);
        try{
            String queryString = "delete  from BusinessWareHouseOperate as model where model.id = "
                    + id;
            return commonDao.executeUpdateOrDelete(queryString);
        }catch(RuntimeException re){
            log.error("removeBusinessWareHouseOperateId failed",re);
            throw re;
        }
    }
    
    
    public static BusinessWareHouseOperate getFromApplicationContext(
            ApplicationContext ctx){
        return (BusinessWareHouseOperate) ctx.getBean("BusinessWareHouseOperateDAO");
    }
}