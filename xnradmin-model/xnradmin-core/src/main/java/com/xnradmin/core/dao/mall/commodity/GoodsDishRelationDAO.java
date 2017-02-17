package com.xnradmin.core.dao.mall.commodity;

import java.io.Serializable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Repository;

import com.cntinker.util.StringHelper;
import com.xnradmin.core.dao.CommonDAO;
import com.xnradmin.po.mall.commodity.GoodsDishRelation;


@Repository("GoodsDishRelationDAO")
public class GoodsDishRelationDAO{
    
    @Autowired
    private CommonDAO commonDao;
    
    private static final Logger log = LoggerFactory
            .getLogger(GoodsDishRelationDAO.class);

    // property constants

    public static final String ID="id";
    public static final String SORT_ID="sortId";//排序Id
    public static final String GOODS_ID="goodsId";//商品Id
    public static final String DISH_ID="dishId";//菜品Id
    public static final String PRIMARY_CONFIGURATION_ID="primaryConfigurationId";//对应商城ID
    public static final String STAFF_ID="staffId";	//隶属用户Id
    public static final String CREATE_TIME="createTime";//建立时间
    public static final String CREATE_STAFF_ID="createStaffId";//建立人
    public static final String MODIFY_TIME="modifyTime";//修改时间
    public static final String MODIFY_STAFF_ID="modifyStaffId";//修改人

    protected void initDao(){
        // do nothing
    }

    public Serializable save(GoodsDishRelation goodsDishRelation){
        log.debug("saving GoodsDishRelation instance");
        Serializable cls;
        try{
        	cls = commonDao.save(goodsDishRelation);
            log.debug("save successful");
        }catch(RuntimeException re){
            log.error("save failed",re);
            throw re;
        }
        return cls;
    }

    public void delete(GoodsDishRelation goodsDishRelation){
        log.debug("deleting GoodsDishRelation instance");
        try{
            commonDao.delete(goodsDishRelation);
            log.debug("delete successful");
        }catch(RuntimeException re){
            log.error("delete failed",re);
            throw re;
        }
    }

    public GoodsDishRelation findById(Long id){
        log.debug("getting GoodsDishRelation instance with id: " + id);
        try{

            return (GoodsDishRelation) commonDao.findById(
            		GoodsDishRelation.class,id);
        }catch(RuntimeException re){
            log.error("get failed",re);
            throw re;
        }
    }

    public List<GoodsDishRelation> findByExample(GoodsDishRelation instance){
        log.debug("finding GoodsDishRelation instance by example");
        try{

            return commonDao.getEntitesForExample(instance,0,0);
        }catch(RuntimeException re){
            log.error("find by example failed",re);
            throw re;
        }
    }

    public List findByProperty(String propertyName,Object value){
        log.debug("finding GoodsDishRelation instance with property: "
                + propertyName + ", value: " + value);
        try{
            String queryString = "from GoodsDishRelation as model where model."
                    + propertyName + "= ?";
            return commonDao.getEntitiesByPropertiesWithHql(queryString,0,0,value);
        }catch(RuntimeException re){
            log.error("find by property name failed",re);
            throw re;
        }
    }

    public List findByOlay(String goodsId, String dishId, String goodsDishRelationId){
        log.debug("finding findByOlay "
                + goodsId + ":" + dishId + ":" + goodsDishRelationId);
        try{
            String queryString = "from GoodsDishRelation where goodsId= "+goodsId+" and dishId="+dishId;
            if(!StringHelper.isNull(goodsDishRelationId)){
            	queryString = queryString + " and id!="+goodsDishRelationId;
            }
            log.debug(queryString);
            return commonDao.getEntitiesByPropertiesWithHql(queryString,0,0);
        }catch(RuntimeException re){
            log.error("find by property name failed",re);
            throw re;
        }
    }
    
    public List findAll(){
        log.debug("finding all GoodsDishRelation instances");
        try{
            String queryString = "from GoodsDishRelation";
            return commonDao.getEntitiesByPropertiesWithHql(queryString,0,0);
        }catch(RuntimeException re){
            log.error("find all failed",re);
            throw re;
        }
    }

    public GoodsDishRelation merge(GoodsDishRelation detachedInstance){
        log.debug("merging GoodsDishRelation instance");
        try{
        	GoodsDishRelation result = (GoodsDishRelation) commonDao
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        }catch(RuntimeException re){
            log.error("merge failed",re);
            throw re;
        }
    }

    public void attachDirty(GoodsDishRelation instance){
        log.debug("attaching dirty GoodsDishRelation instance");
        try{
            commonDao.saveOrUpdate(instance);
            log.debug("attach successful");
        }catch(RuntimeException re){
            log.error("attach failed",re);
            throw re;
        }
    }

    
    public int removeGoodsDishRelationId(Long id){

        log.debug("removeGoodsDishRelationId: " + id);
        try{
            String queryString = "delete  from GoodsDishRelation as model where model.id = "
                    + id;
            return commonDao.executeUpdateOrDelete(queryString);
        }catch(RuntimeException re){
            log.error("removeGoodsDishRelationId failed",re);
            throw re;
        }
    }
    
    
    public static GoodsDishRelationDAO getFromApplicationContext(
            ApplicationContext ctx){
        return (GoodsDishRelationDAO) ctx.getBean("GoodsDishRelationDAO");
    }
}