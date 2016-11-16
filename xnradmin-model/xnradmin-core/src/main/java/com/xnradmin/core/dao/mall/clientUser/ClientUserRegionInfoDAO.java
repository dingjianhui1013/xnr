package com.xnradmin.core.dao.mall.clientUser;


import java.sql.Timestamp;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Repository;

import com.xnradmin.core.dao.CommonDAO;
import com.xnradmin.po.client.ClientUserRegionInfo;

/**
 * A data access object (DAO) providing persistence and search support for
 * CommonOrganization entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.heinsaw.po.ClientUserRegionInfo
 * @author MyEclipse Persistence Tools
 */
@Repository("ClientUserRegionInfoDAO")
public class ClientUserRegionInfoDAO{
    private static final Logger log = LoggerFactory
            .getLogger(ClientUserRegionInfoDAO.class);
    
    @Autowired
    private CommonDAO commonDao;

    // property constants
    public static final String ID="id";
    public static final String COUNTRY_ID="countryId";
    public static final String COUNTRY_NAME="countryName";
    public static final String PROVINCE_ID="provinceId";
    public static final String PROVINCE_NAME="provinceName";
    public static final String CITY_ID="cityId";
    public static final String CITY_NAME="cityName";
    public static final String AREA_ID="areaId";
    public static final String AREA_NAME="areaName";
    public static final String DESCRIPTION="description";
    public static final String CLIENT_USER_INFO_ID="clientUserInfoId";
    public static final String USER_REAL_MOBILE="userRealMobile";
    public static final String USER_REAL_PLANE="userRealPlane";
    public static final String USER_REAL_NAME="userRealName";
    public static final String USER_REAL_POSTCODE="userRealPostcode";
    public static final String CREATE_TIME="createTime";
    public static final String MODIFY_TIME="modifyTime";
	
    protected void initDao(){
        // do nothing
    }

    public void save(ClientUserRegionInfo clientUserRegionInfo){
        log.debug("saving ClientUserRegionInfo instance");
        try{
            commonDao.save(clientUserRegionInfo);
            log.debug("save successful");
        }catch(RuntimeException re){
            log.error("save failed",re);
            throw re;
        }
    }

    public void delete(ClientUserRegionInfo clientUserRegionInfo){
        log.debug("deleting ClientUserRegionInfo instance");
        try{
            commonDao.delete(clientUserRegionInfo);
            log.debug("delete successful");
        }catch(RuntimeException re){
            log.error("delete failed",re);
            throw re;
        }
    }

    public ClientUserRegionInfo findById(java.lang.Integer id){
        log.debug("getting ClientUserRegionInfo instance with id: " + id);
        try{

            return (ClientUserRegionInfo) commonDao.findById(
            		ClientUserRegionInfo.class,id);
        }catch(RuntimeException re){
            log.error("get failed",re);
            throw re;
        }
    }

    public List<ClientUserRegionInfo> findByExample(ClientUserRegionInfo instance){
        log.debug("finding ClientUserRegionInfo instance by example");
        try{

            return commonDao.getEntitesForExample(instance,0,0);
        }catch(RuntimeException re){
            log.error("find by example failed",re);
            throw re;
        }
    }

    public List<ClientUserRegionInfo> findByProperty(String propertyName,Object value){
        log.debug("finding ClientUserRegionInfo instance with property: "
                + propertyName + ", value: " + value);
        try{
            String queryString = "from ClientUserRegionInfo as model where model."
                    + propertyName + "= ? order by modifyTime desc";
            return commonDao.getEntitiesByPropertiesWithHql(queryString,0,0,value);
        }catch(RuntimeException re){
            log.error("find by property name failed",re);
            throw re;
        }
    }

    public List findAll(){
        log.debug("finding all ClientUserRegionInfo instances");
        try{
            String queryString = "from ClientUserRegionInfo";
            return commonDao.getEntitiesByPropertiesWithHql(queryString,0,0);
        }catch(RuntimeException re){
            log.error("find all failed",re);
            throw re;
        }
    }

    public ClientUserRegionInfo merge(ClientUserRegionInfo detachedInstance){
        log.debug("merging ClientUserRegionInfo instance");
        try{
        	ClientUserRegionInfo result = (ClientUserRegionInfo) commonDao
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        }catch(RuntimeException re){
            log.error("merge failed",re);
            throw re;
        }
    }

    public void attachDirty(ClientUserRegionInfo instance){
        log.debug("attaching dirty ClientUserRegionInfo instance");
        try{
            commonDao.saveOrUpdate(instance);
            log.debug("attach successful");
        }catch(RuntimeException re){
            log.error("attach failed",re);
            throw re;
        }
    }

    
    public int removeClientUserRegionInfoId(Integer id){

        log.debug("removeClientUserRegionInfoId: " + id);
        try{
            String queryString = "delete  from ClientUserRegionInfo as model where model.id = "
                    + id;
            return commonDao.executeUpdateOrDelete(queryString);
        }catch(RuntimeException re){
            log.error("removeClientUserRegionInfoId failed",re);
            throw re;
        }
    }
    
    public int removeClientUserInfoId(Integer id){

        log.debug("removeClientUserRegionInfoId: " + id);
        try{
            String queryString = "delete  from ClientUserRegionInfo as model where model.clientUserInfoId = "
                    + id;
            return commonDao.executeUpdateOrDelete(queryString);
        }catch(RuntimeException re){
            log.error("removeClientUserRegionInfoId failed",re);
            throw re;
        }
    }
    
    public static ClientUserRegionInfoDAO getFromApplicationContext(
            ApplicationContext ctx){
        return (ClientUserRegionInfoDAO) ctx.getBean("ClientUserRegionInfoDAO");
    }
}