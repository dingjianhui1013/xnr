package com.xnradmin.core.dao.dishes;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Repository;

import com.cntinker.util.StringHelper;
import com.xnradmin.core.dao.CommonDAO;
import com.xnradmin.po.dishes.Collocation;


@Repository("CollocationDAO")
public class CollocationDAO{
    
    @Autowired
    private CommonDAO commonDao;
    
    private static final Logger log = LoggerFactory
            .getLogger(CollocationDAO.class);

    // property constants

    public static final String ID = "id";
    public static final String DISH_ID = "dishId";  //菜品ID
    public static final String RAW_MATERIAL_ID = "rawMaterialId";  //原料ID
    public static final String WEIGHT_ID = "weightId";  //重量单位
    public static final String NUMBER = "number";  //单位数量
    public static final String CREATE_TIME = "createTime";  //建立时间
    public static final String CREATE_STAFF_ID = "createStaffId";  //建立人
    public static final String MODIFY_TIME = "modifyTime";  //修改时间
    public static final String MODIFY_STAFF_ID = "modifyStaffId";  //修改人

    protected void initDao(){
        // do nothing
    }

    public void save(Collocation collocation){
        log.debug("saving Collocation instance");
        try{
            commonDao.save(collocation);
            log.debug("save successful");
        }catch(RuntimeException re){
            log.error("save failed",re);
            throw re;
        }
    }

    public void delete(Collocation collocation){
        log.debug("deleting Collocation instance");
        try{
            commonDao.delete(collocation);
            log.debug("delete successful");
        }catch(RuntimeException re){
            log.error("delete failed",re);
            throw re;
        }
    }

    public Collocation findById(java.lang.Long id){
        log.debug("getting Collocation instance with id: " + id);
        try{

            return (Collocation) commonDao.findById(
            		Collocation.class,id);
        }catch(RuntimeException re){
            log.error("get failed",re);
            throw re;
        }
    }

    public List<Collocation> findByExample(Collocation instance){
        log.debug("finding Collocation instance by example");
        try{

            return commonDao.getEntitesForExample(instance,0,0);
        }catch(RuntimeException re){
            log.error("find by example failed",re);
            throw re;
        }
    }

    public List findByProperty(String propertyName,Object value){
        log.debug("finding Collocation instance with property: "
                + propertyName + ", value: " + value);
        try{
            String queryString = "from Collocation as model where model."
                    + propertyName + "= ?";
            return commonDao.getEntitiesByPropertiesWithHql(queryString,0,0,value);
        }catch(RuntimeException re){
            log.error("find by property name failed",re);
            throw re;
        }
    }

    public List findAll(){
        log.debug("finding all Collocation instances");
        try{
            String queryString = "from Collocation";
            return commonDao.getEntitiesByPropertiesWithHql(queryString,0,0);
        }catch(RuntimeException re){
            log.error("find all failed",re);
            throw re;
        }
    }

    public Collocation merge(Collocation detachedInstance){
        log.debug("merging Dish instance");
        try{
        	Collocation result = (Collocation) commonDao
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        }catch(RuntimeException re){
            log.error("merge failed",re);
            throw re;
        }
    }

    public void attachDirty(Collocation instance){
        log.debug("attaching dirty Collocation instance");
        try{
            commonDao.saveOrUpdate(instance);
            log.debug("attach successful");
        }catch(RuntimeException re){
            log.error("attach failed",re);
            throw re;
        }
    }

    
    public int removeCollocationId(Long id){

        log.debug("removeCollocationId: " + id);
        try{
            String queryString = "delete  from Collocation as model where model.id = "
                    + id;
            return commonDao.executeUpdateOrDelete(queryString);
        }catch(RuntimeException re){
            log.error("removeDishId failed",re);
            throw re;
        }
    }
    
    public List findByOlay(String dishId, String rawMaterialId, String collocationId){
        log.debug("finding findByOlay "
                + dishId + ":" + rawMaterialId);
        try{
            String queryString = "from Collocation where dishId= "+dishId+" and rawMaterialId="+rawMaterialId;
            if(!StringHelper.isNull(collocationId)){
            	queryString = queryString + " and id!="+collocationId;
            }
            log.debug(queryString);
            return commonDao.getEntitiesByPropertiesWithHql(queryString,0,0);
        }catch(RuntimeException re){
            log.error("find by property name failed",re);
            throw re;
        }
    }
    
    public static CollocationDAO getFromApplicationContext(
            ApplicationContext ctx){
        return (CollocationDAO) ctx.getBean("CollocationDAO");
    }
}