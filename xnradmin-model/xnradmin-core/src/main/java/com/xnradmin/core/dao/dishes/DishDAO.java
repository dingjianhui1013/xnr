package com.xnradmin.core.dao.dishes;

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


@Repository("DishDAO")
public class DishDAO{
    
    @Autowired
    private CommonDAO commonDao;
    
    private static final Logger log = LoggerFactory
            .getLogger(DishDAO.class);

    // property constants

    public static final String ID = "id";
    public static final String DISH_NAME = "dishName";  //菜品名
    public static final String INTRODUCTION = "introduction";  //菜品介绍
    public static final String COOKING_METHOD = "cookingMethod";  //烹饪方法
    public static final String PIC_URL = "picUrl";  //图片连接
    public static final String DISH_TYPE_ID = "dishTypeId";  //菜品类型
    public static final String CREATE_TIME = "createTime";  //建立时间
    public static final String CREATE_STAFF_ID = "createStaffId";  //建立人
    public static final String MODIFY_TIME = "modifyTime";  //修改时间
    public static final String MODIFY_STAFF_ID = "modifyStaffId";  //修改人

    protected void initDao(){
        // do nothing
    }

    public Serializable save(Dish dish){
        log.debug("saving Dish instance");
        Serializable cls;
        try{
        	cls = commonDao.save(dish);
            log.debug("save successful");
        }catch(RuntimeException re){
            log.error("save failed",re);
            throw re;
        }
        return cls;
    }

    public void delete(Dish dish){
        log.debug("deleting Dish instance");
        try{
            commonDao.delete(dish);
            log.debug("delete successful");
        }catch(RuntimeException re){
            log.error("delete failed",re);
            throw re;
        }
    }

    public Dish findById(Integer id){
        log.debug("getting Dish instance with id: " + id);
        try{

            return (Dish) commonDao.findById(
            		Dish.class,id);
        }catch(RuntimeException re){
            log.error("get failed",re);
            throw re;
        }
    }

    public List<Dish> findByExample(Dish instance){
        log.debug("finding Dish instance by example");
        try{

            return commonDao.getEntitesForExample(instance,0,0);
        }catch(RuntimeException re){
            log.error("find by example failed",re);
            throw re;
        }
    }

    public List findByProperty(String propertyName,Object value){
        log.debug("finding Dish instance with property: "
                + propertyName + ", value: " + value);
        try{
            String queryString = "from Dish as model where model."
                    + propertyName + "= ?";
            return commonDao.getEntitiesByPropertiesWithHql(queryString,0,0,value);
        }catch(RuntimeException re){
            log.error("find by property name failed",re);
            throw re;
        }
    }

    public List findAll(){
        log.debug("finding all Dish instances");
        try{
            String queryString = "from Dish";
            return commonDao.getEntitiesByPropertiesWithHql(queryString,0,0);
        }catch(RuntimeException re){
            log.error("find all failed",re);
            throw re;
        }
    }

    public Dish merge(Dish detachedInstance){
        log.debug("merging Dish instance");
        try{
        	Dish result = (Dish) commonDao
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        }catch(RuntimeException re){
            log.error("merge failed",re);
            throw re;
        }
    }

    public void attachDirty(Dish instance){
        log.debug("attaching dirty Dish instance");
        try{
            commonDao.saveOrUpdate(instance);
            log.debug("attach successful");
        }catch(RuntimeException re){
            log.error("attach failed",re);
            throw re;
        }
    }

    
    public int removeDishId(Integer id){

        log.debug("removeDishId: " + id);
        try{
            String queryString = "delete  from Dish as model where model.id = "
                    + id;
            return commonDao.executeUpdateOrDelete(queryString);
        }catch(RuntimeException re){
            log.error("removeDishId failed",re);
            throw re;
        }
    }
    
    public List findByOlay(String dishName, String dishId){
        log.debug("finding findByOlay "
                + dishName);
        try{
            String queryString = "from Dish where dishName= '"+dishName+"'";
            if(!StringHelper.isNull(dishId)){
            	queryString = queryString + " and id!="+dishId;
            }
            log.debug(queryString);
            return commonDao.getEntitiesByPropertiesWithHql(queryString,0,0);
        }catch(RuntimeException re){
            log.error("find by property name failed",re);
            throw re;
        }
    }
    
    public static DishDAO getFromApplicationContext(
            ApplicationContext ctx){
        return (DishDAO) ctx.getBean("DishDAO");
    }
}