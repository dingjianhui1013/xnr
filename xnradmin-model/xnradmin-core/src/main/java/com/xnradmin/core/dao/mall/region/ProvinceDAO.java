package com.xnradmin.core.dao.mall.region;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Repository;

import com.xnradmin.core.dao.CommonDAO;
import com.xnradmin.po.mall.region.Province;

@Repository("ProvinceDAO")
public class ProvinceDAO{
    
    @Autowired
    private CommonDAO commonDao;
    
    private static final Logger log = LoggerFactory
            .getLogger(ProvinceDAO.class);

    // property constants

    public static final String ID = "id";
    public static final String PROVINCE = "province";
    public static final String COUNTRY_ID = "countryId";
    public static final String COUNTRY = "country";

    protected void initDao(){
        // do nothing
    }

    public void save(Province province){
        log.debug("saving Province instance");
        try{
            commonDao.save(province);
            log.debug("save successful");
        }catch(RuntimeException re){
            log.error("save failed",re);
            throw re;
        }
    }

    public void delete(Province carbrand){
        log.debug("deleting Province instance");
        try{
            commonDao.delete(carbrand);
            log.debug("delete successful");
        }catch(RuntimeException re){
            log.error("delete failed",re);
            throw re;
        }
    }

    public Province findById(java.lang.Integer id){
        log.debug("getting Province instance with id: " + id);
        try{

            return (Province) commonDao.findById(
            		Province.class,id);
        }catch(RuntimeException re){
            log.error("get failed",re);
            throw re;
        }
    }

    public List<Province> findByExample(Province instance){
        log.debug("finding Province instance by example");
        try{

            return commonDao.getEntitesForExample(instance,0,0);
        }catch(RuntimeException re){
            log.error("find by example failed",re);
            throw re;
        }
    }

    public List findByProperty(String propertyName,Object value){
        log.debug("finding Province instance with property: "
                + propertyName + ", value: " + value);
        try{
            String queryString = "from Province as model where model."
                    + propertyName + "= ?";
            return commonDao.getEntitiesByPropertiesWithHql(queryString,0,0,value);
        }catch(RuntimeException re){
            log.error("find by property name failed",re);
            throw re;
        }
    }

    public List<Province> findByProvince(
            Object Province){
        return findByProperty(PROVINCE,Province);
    }


    public List findAll(){
        log.debug("finding all Province instances");
        try{
            String queryString = "from Province";
            return commonDao.getEntitiesByPropertiesWithHql(queryString,0,0);
        }catch(RuntimeException re){
            log.error("find all failed",re);
            throw re;
        }
    }

    public Province merge(Province detachedInstance){
        log.debug("merging Province instance");
        try{
        	Province result = (Province) commonDao
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        }catch(RuntimeException re){
            log.error("merge failed",re);
            throw re;
        }
    }

    public void attachDirty(Province instance){
        log.debug("attaching dirty Province instance");
        try{
            commonDao.saveOrUpdate(instance);
            log.debug("attach successful");
        }catch(RuntimeException re){
            log.error("attach failed",re);
            throw re;
        }
    }

    
    public int removeProvinceId(Integer id){

        log.debug("removeProvinceId: " + id);
        try{
            String queryString = "delete  from Province as model where model.id = "
                    + id;
            return commonDao.executeUpdateOrDelete(queryString);
        }catch(RuntimeException re){
            log.error("removeProvinceId failed",re);
            throw re;
        }
    }
    
    public static ProvinceDAO getFromApplicationContext(
            ApplicationContext ctx){
        return (ProvinceDAO) ctx.getBean("ProvinceDAO");
    }
}