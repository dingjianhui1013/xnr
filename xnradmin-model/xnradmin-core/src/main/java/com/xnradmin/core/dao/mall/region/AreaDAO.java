package com.xnradmin.core.dao.mall.region;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Repository;

import com.xnradmin.core.dao.CommonDAO;
import com.xnradmin.po.mall.region.Area;

@Repository("AreaDAO")
public class AreaDAO{
    private static final Logger log = LoggerFactory
            .getLogger(AreaDAO.class);
    
    @Autowired
    private CommonDAO commonDao;

    // property constants
    public static final String ID = "id";
    public static final String AREA = "area";
    public static final String CITY_ID = "cityId";
	public static final String CITY = "city";
	public static final String PROVINCE_ID = "provinceId";
    public static final String PROVINCE = "province";
    public static final String COUNTRY_ID = "countryId";
    public static final String COUNTRY = "country";
    protected void initDao(){
        // do nothing
    }

    public void save(Area area){
        log.debug("saving AreaDAO instance");
        try{
            commonDao.save(area);
            log.debug("save successful");
        }catch(RuntimeException re){
            log.error("save failed",re);
            throw re;
        }
    }

    public void delete(Area area){
        log.debug("deleting AreaDAO instance");
        try{
            commonDao.delete(area);
            log.debug("delete successful");
        }catch(RuntimeException re){
            log.error("delete failed",re);
            throw re;
        }
    }

    public Area findById(java.lang.Integer id){
        log.debug("getting AreaDAO instance with id: " + id);
        try{

            return (Area) commonDao.findById(
            		Area.class,id);
        }catch(RuntimeException re){
            log.error("get failed",re);
            throw re;
        }
    }

    public List<Area> findByExample(AreaDAO instance){
        log.debug("finding AreaDAO instance by example");
        try{

            return commonDao.getEntitesForExample(instance,0,0);
        }catch(RuntimeException re){
            log.error("find by example failed",re);
            throw re;
        }
    }

    public List findByArea(String area,Object value){
        log.debug("finding Area instance with property: "
                + area + ", value: " + value);
        try{
            String queryString = "from Area as model where model."
                    + area + "= ?";
            return commonDao.getEntitiesByPropertiesWithHql(queryString,0,0,value);
        }catch(RuntimeException re){
            log.error("find by property name failed",re);
            throw re;
        }
    }

    public List<Area> findByProvinceId(
            Object provinceId){
        return findByArea(PROVINCE_ID,provinceId);
    }

    public List<Area> findByCityId(
            Object cityId){
        return findByArea(CITY_ID,cityId);
    }

    public List<Area> findByArea(
            Object area){
        return findByArea(AREA,area);
    }
    
    public List findAll(){
        log.debug("finding all Area instances");
        try{
            String queryString = "from Area";
            return commonDao.getEntitiesByPropertiesWithHql(queryString,0,0);
        }catch(RuntimeException re){
            log.error("find all failed",re);
            throw re;
        }
    }

    public Area merge(Area detachedInstance){
        log.debug("merging Area instance");
        try{
        	Area result = (Area) commonDao
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        }catch(RuntimeException re){
            log.error("merge failed",re);
            throw re;
        }
    }

    public void attachDirty(Area instance){
        log.debug("attaching dirty Area instance");
        try{
            commonDao.saveOrUpdate(instance);
            log.debug("attach successful");
        }catch(RuntimeException re){
            log.error("attach failed",re);
            throw re;
        }
    }

    
    public int removeAreaId(Integer id){

        log.debug("AreaId: " + id);
        try{
            String queryString = "delete  from Area as model where model.id = "
                    + id;
            return commonDao.executeUpdateOrDelete(queryString);
        }catch(RuntimeException re){
            log.error("removeAreaId failed",re);
            throw re;
        }
    }
	
    public static AreaDAO getFromApplicationContext(
            ApplicationContext ctx){
        return (AreaDAO) ctx.getBean("AreaDAO");
    }
}