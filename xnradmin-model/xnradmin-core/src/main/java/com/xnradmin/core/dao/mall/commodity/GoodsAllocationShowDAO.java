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
import com.xnradmin.po.mall.commodity.GoodsAllocationShow;


@Repository("GoodsAllocationShowDAO")
public class GoodsAllocationShowDAO{
    
    @Autowired
    private CommonDAO commonDao;
    
    private static final Logger log = LoggerFactory
            .getLogger(GoodsAllocationShowDAO.class);

    // property constants

    public static final String ID="id";
    public static final String GOODS_ID="goodsId";//商品Id
    public static final String START_TIME="startTime";//开始时间
    public static final String END_TIME="endTime";//结束时间
    public static final String ALLOCATION_COUNT="allocationCount";//分配总数
    public static final String SALE_COUNT="saleCount";//占用总数（已销售）
    public static final String SURPLUS_COUNT="surplusCount";//剩余总数（分配总数-已销售）
    public static final String CREATE_TIME="createTime";//建立时间
    public static final String CREATE_STAFF_ID="createStaffId";//建立人
    public static final String MODIFY_TIME="modifyTime";//修改时间
    public static final String MODIFY_STAFF_ID="modifyStaffId";//修改人

    protected void initDao(){
        // do nothing
    }

    public Serializable save(GoodsAllocationShow goodsAllocationShow){
        log.debug("saving GoodsAllocationShow instance");
        Serializable cls;
        try{
        	cls = commonDao.save(goodsAllocationShow);
            log.debug("save successful");
        }catch(RuntimeException re){
            log.error("save failed",re);
            throw re;
        }
        return cls;
    }
    
    public void saveBach(List<GoodsAllocationShow> goodsAllocationShowList){
        log.debug("saving GoodsAllocationShow instance");
        try{
        	if(goodsAllocationShowList!=null){
        		for(GoodsAllocationShow goodsAllocationShow:goodsAllocationShowList){
        			commonDao.save(goodsAllocationShow);
        			log.debug("save successful");        		        			
        		}
        	}
        }catch(RuntimeException re){
            log.error("save failed",re);
            throw re;
        }
    }

    public void delete(GoodsAllocationShow goodsAllocationShow){
        log.debug("deleting GoodsDishRelation instance");
        try{
            commonDao.delete(goodsAllocationShow);
            log.debug("delete successful");
        }catch(RuntimeException re){
            log.error("delete failed",re);
            throw re;
        }
    }
    
    public void deleteBach(List<GoodsAllocationShow> goodsAllocationShowList){
        log.debug("deleting GoodsDishRelation bach");
        try{
        	if(goodsAllocationShowList!=null){
        		for(GoodsAllocationShow goodsAllocationShow:goodsAllocationShowList){
        			commonDao.delete(goodsAllocationShow);
        		}
        		log.debug("delete successful");
        	}
        }catch(RuntimeException re){
            log.error("delete failed",re);
            throw re;
        }
    }

    public GoodsAllocationShow findById(Integer id){
        log.debug("getting GoodsAllocationShow instance with id: " + id);
        try{

            return (GoodsAllocationShow) commonDao.findById(
            		GoodsAllocationShow.class,id);
        }catch(RuntimeException re){
            log.error("get failed",re);
            throw re;
        }
    }

    public List<GoodsAllocationShow> findByExample(GoodsAllocationShow instance){
        log.debug("finding GoodsAllocationShow instance by example");
        try{

            return commonDao.getEntitesForExample(instance,0,0);
        }catch(RuntimeException re){
            log.error("find by example failed",re);
            throw re;
        }
    }

    public List findByProperty(String propertyName,Object value){
        log.debug("finding GoodsAllocationShow instance with property: "
                + propertyName + ", value: " + value);
        try{
            String queryString = "from GoodsAllocationShow as model where model."
                    + propertyName + "= ?";
            return commonDao.getEntitiesByPropertiesWithHql(queryString,0,0,value);
        }catch(RuntimeException re){
            log.error("find by property name failed",re);
            throw re;
        }
    }

    public List findByOlay(String goodsId, String goodsAllocationShowId){
        log.debug("finding findByOlay "
                + goodsId + ":" + goodsAllocationShowId);
        try{
            String queryString = "from GoodsAllocationShow where goodsId= "+goodsId;
            if(!StringHelper.isNull(goodsAllocationShowId)){
            	queryString = queryString + " and id!="+goodsAllocationShowId;
            }
            queryString+= " order by id";
            log.debug(queryString);
            return commonDao.getEntitiesByPropertiesWithHql(queryString,0,0);
        }catch(RuntimeException re){
            log.error("find by property name failed",re);
            throw re;
        }
    }
    
    public List findByOlayToday(String goodsId, String goodsAllocationShowId){
        log.debug("finding findByOlay "
                + goodsId + ":" + goodsAllocationShowId);
        try{
            String queryString = "from GoodsAllocationShow gas where gas.goodsId= "+goodsId +" and gas.startTime<=now() and gas.endTime>now()";
            if(!StringHelper.isNull(goodsAllocationShowId)){
            	queryString = queryString + " and id!="+goodsAllocationShowId;
            }
            queryString+= " order by id";
            log.debug(queryString);
            return commonDao.getEntitiesByPropertiesWithHql(queryString,0,0);
        }catch(RuntimeException re){
            log.error("find by property name failed",re);
            throw re;
        }
    }
    
    public List findAll(){
        log.debug("finding all GoodsAllocationShow instances");
        try{
            String queryString = "from GoodsAllocationShow";
            return commonDao.getEntitiesByPropertiesWithHql(queryString,0,0);
        }catch(RuntimeException re){
            log.error("find all failed",re);
            throw re;
        }
    }

    public GoodsAllocationShow merge(GoodsAllocationShow detachedInstance){
        log.debug("merging GoodsAllocationShow instance");
        try{
        	GoodsAllocationShow result = (GoodsAllocationShow) commonDao
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        }catch(RuntimeException re){
            log.error("merge failed",re);
            throw re;
        }
    }

    public void attachDirty(GoodsAllocationShow instance){
        log.debug("attaching dirty GoodsAllocationShow instance");
        try{
            commonDao.saveOrUpdate(instance);
            log.debug("attach successful");
        }catch(RuntimeException re){
            log.error("attach failed",re);
            throw re;
        }
    }

    
    public int removeGoodsAllocationShowId(Long id){

        log.debug("removeGoodsAllocationShowId: " + id);
        try{
            String queryString = "delete  from GoodsAllocationShow as model where model.id = "
                    + id;
            return commonDao.executeUpdateOrDelete(queryString);
        }catch(RuntimeException re){
            log.error("removeGoodsAllocationShowId failed",re);
            throw re;
        }
    }
    
    
    public static GoodsAllocationShowDAO getFromApplicationContext(
            ApplicationContext ctx){
        return (GoodsAllocationShowDAO) ctx.getBean("GoodsAllocationShowDAO");
    }
}