package com.xnradmin.core.dao.mall.region;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Repository;

import com.xnradmin.core.dao.CommonDAO;
import com.xnradmin.po.mall.region.Country;

@Repository("CountryDAO")
public class CountryDAO{
    
    @Autowired
    private CommonDAO commonDao;
    
    private static final Logger log = LoggerFactory
            .getLogger(ProvinceDAO.class);

    // property constants

    public static final String ID = "id";
    public static final String COUNTRY = "country";

    protected void initDao(){
        // do nothing
    }

    public void save(Country country){
        log.debug("saving country instance");
        try{
            commonDao.save(country);
            log.debug("save successful");
        }catch(RuntimeException re){
            log.error("save failed",re);
            throw re;
        }
    }

    public void delete(Country country){
        log.debug("deleting country instance");
        try{
            commonDao.delete(country);
            log.debug("delete successful");
        }catch(RuntimeException re){
            log.error("delete failed",re);
            throw re;
        }
    }

    public Country findById(java.lang.Integer id){
        log.debug("getting Country instance with id: " + id);
        try{

            return (Country) commonDao.findById(
            		Country.class,id);
        }catch(RuntimeException re){
            log.error("get failed",re);
            throw re;
        }
    }

    public List<Country> findByExample(Country instance){
        log.debug("finding Country instance by example");
        try{

            return commonDao.getEntitesForExample(instance,0,0);
        }catch(RuntimeException re){
            log.error("find by example failed",re);
            throw re;
        }
    }

    public List findByProperty(String propertyName,Object value){
        log.debug("finding Country instance with property: "
                + propertyName + ", value: " + value);
        try{
            String queryString = "from Country as model where model."
                    + propertyName + "= ?";
            return commonDao.getEntitiesByPropertiesWithHql(queryString,0,0,value);
        }catch(RuntimeException re){
            log.error("find by Country name failed",re);
            throw re;
        }
    }

    public List<Country> findByProvince(
            Object country){
        return findByProperty(COUNTRY,country);
    }


    public List findAll(){
        log.debug("finding all Province instances");
        try{
            String queryString = "from Country";
            return commonDao.getEntitiesByPropertiesWithHql(queryString,0,0);
        }catch(RuntimeException re){
            log.error("find all failed",re);
            throw re;
        }
    }

    public Country merge(Country detachedInstance){
        log.debug("merging Country instance");
        try{
        	Country result = (Country) commonDao
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        }catch(RuntimeException re){
            log.error("merge failed",re);
            throw re;
        }
    }

    public void attachDirty(Country instance){
        log.debug("attaching dirty Country instance");
        try{
            commonDao.saveOrUpdate(instance);
            log.debug("attach successful");
        }catch(RuntimeException re){
            log.error("attach failed",re);
            throw re;
        }
    }

    
    public int removeProvinceId(Integer id){

        log.debug("removeCountryId: " + id);
        try{
            String queryString = "delete  from Country as model where model.id = "
                    + id;
            return commonDao.executeUpdateOrDelete(queryString);
        }catch(RuntimeException re){
            log.error("removeCarBrandId failed",re);
            throw re;
        }
    }
    
    public static CountryDAO getFromApplicationContext(
            ApplicationContext ctx){
        return (CountryDAO) ctx.getBean("CountryDAO");
    }
}