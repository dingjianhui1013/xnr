package com.xnradmin.core.dao.business.commodity;

import java.io.Serializable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Repository;

import com.xnradmin.core.dao.CommonDAO;
import com.xnradmin.po.business.BusinessUserFavorite;


@Repository("BusinessUserFavoriteDAO")
public class BusinessUserFavoriteDAO{
    
    @Autowired
    private CommonDAO commonDao;
    
    private static final Logger log = LoggerFactory
            .getLogger(BusinessUserFavoriteDAO.class);

    // property constants

    public static final String ID="id";
    public static final String GOODS_ID="goodsId";//商品Id
    public static final String STAFF_ID="staffId";//商户Id
    public static final String CLIENT_USER_INFO_ID="clientUserInfoId";//用户Id
    public static final String CREATE_TIME="createTime";//建立时间
   

    protected void initDao(){
        // do nothing
    }

    public Serializable save(BusinessUserFavorite businessUserFavorite){
        log.debug("saving BusinessUserFavorite instance");
        Serializable cls;
        try{
        	cls = commonDao.save(businessUserFavorite);
            log.debug("save successful");
        }catch(RuntimeException re){
            log.error("save failed",re);
            throw re;
        }
        return cls;
    }

    public void delete(BusinessUserFavorite businessUserFavorite){
        log.debug("deleting BusinessUserFavorite instance");
        try{
            commonDao.delete(businessUserFavorite);
            log.debug("delete successful");
        }catch(RuntimeException re){
            log.error("delete failed",re);
            throw re;
        }
    }
    
    public int deleteStaffIdAndGoodsId(int staffId, int goodsId){
        log.debug("deleting deleteStaffIdAndGoodsId instance");
        try{
            String queryString = "delete from BusinessUserFavorite as model where model.staffId = "
                    + staffId + " and model.goodsId=" + goodsId;
            return commonDao.executeUpdateOrDelete(queryString);
        }catch(RuntimeException re){
            log.error("deleteStaffIdAndGoodsId failed",re);
            throw re;
        }
    }

    public BusinessUserFavorite findById(Integer id){
        log.debug("getting BusinessUserFavorite instance with id: " + id);
        try{

            return (BusinessUserFavorite) commonDao.findById(
            		BusinessUserFavorite.class,id);
        }catch(RuntimeException re){
            log.error("get failed",re);
            throw re;
        }
    }

    public List<BusinessUserFavorite> findByExample(BusinessUserFavorite instance){
        log.debug("finding BusinessUserFavorite instance by example");
        try{

            return commonDao.getEntitesForExample(instance,0,0);
        }catch(RuntimeException re){
            log.error("find by example failed",re);
            throw re;
        }
    }

    public List<BusinessUserFavorite> findByProperty(String propertyName,Object value){
        log.debug("finding BusinessUserFavorite instance with property: "
                + propertyName + ", value: " + value);
        try{
            String queryString = "from BusinessUserFavorite as model where model."
                    + propertyName + "= ?";
            return commonDao.getEntitiesByPropertiesWithHql(queryString,0,0,value);
        }catch(RuntimeException re){
            log.error("find by property name failed",re);
            throw re;
        }
    }

    public List findAll(){
        log.debug("finding all BusinessUserFavorite instances");
        try{
            String queryString = "from BusinessUserFavorite";
            return commonDao.getEntitiesByPropertiesWithHql(queryString,0,0);
        }catch(RuntimeException re){
            log.error("find all failed",re);
            throw re;
        }
    }

    public BusinessUserFavorite merge(BusinessUserFavorite detachedInstance){
        log.debug("merging BusinessUserFavorite instance");
        try{
        	BusinessUserFavorite result = (BusinessUserFavorite) commonDao
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        }catch(RuntimeException re){
            log.error("merge failed",re);
            throw re;
        }
    }

    public void attachDirty(BusinessUserFavorite instance){
        log.debug("attaching dirty BusinessUserFavorite instance");
        try{
            commonDao.saveOrUpdate(instance);
            log.debug("attach successful");
        }catch(RuntimeException re){
            log.error("attach failed",re);
            throw re;
        }
    }

    
    public int removeBusinessUserFavoriteGoodsIdStaffId(Integer id, Integer StaffId){

        log.debug("removeBusinessUserFavoriteGoodsIdStaffId: " + id);
        try{
            String queryString = "delete  from BusinessUserFavorite as model where model.goodsId = "
                    + id + "and model.staffId = " + StaffId;
            return commonDao.executeUpdateOrDelete(queryString);
        }catch(RuntimeException re){
            log.error("removeBusinessUserFavoriteGoodsIdStaffId failed",re);
            throw re;
        }
    }
    

    
    public static BusinessUserFavoriteDAO getFromApplicationContext(
            ApplicationContext ctx){
        return (BusinessUserFavoriteDAO) ctx.getBean("BusinessUserFavoriteDAO");
    }
}