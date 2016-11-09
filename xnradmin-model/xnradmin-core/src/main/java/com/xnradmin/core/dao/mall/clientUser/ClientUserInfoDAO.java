package com.xnradmin.core.dao.mall.clientUser;


import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Repository;

import com.cntinker.util.StringHelper;
import com.xnradmin.core.dao.CommonDAO;
import com.xnradmin.po.client.ClientUserInfo;

/**
 * A data access object (DAO) providing persistence and search support for
 * CommonOrganization entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.xicheadmin.po.client.ClientUserInfoAction.po.ClientUserInfo
 * @author MyEclipse Persistence Tools
 */
@Repository("ClientUserInfoDAO")
public class ClientUserInfoDAO{
    private static final Logger log = LoggerFactory
            .getLogger(ClientUserInfoDAO.class);
    
    @Autowired
    private CommonDAO commonDao;

    // property constants

    public static final String ID="id";
    public static final String NICK_NAME="nickName";
    public static final String EMAIL="email";
    public static final String MOBILE="mobile";
    public static final String LOGIN_PASSWORD="loginPassWord";
    public static final String PAYMENT_PASSWORD="paymentPassword";
    public static final String STATUS="status";
    public static final String STATUS_NAME="statusName";
    public static final String TYPE="type";
    public static final String TYPE_NAME="typeName";
    public static final String UUID="uuid";
    public static final String CREATE_TIME="createTime";
    public static final String LAST_LOGIN_TIME="lastLoginTime";
    public static final String MODIFY_TIME="modifyTime";
    public static final String SOURCE_ID="sourceId"; 
    public static final String SOURCE_NAME="sourceName"; 
    public static final String SOURCE_TYPE="sourceType";
    public static final String SOURCE_TYPE_NAME="sourceTypeName";
    public static final String DISCOUNT="discount";
    public static final String REGION_DESCRIPTION_ID="regionDescriptionId";
    public static final String WX_FROM_USER_NAME="wxfromusername";
    public static final String WX_TO_USER_NAME="wxtousername";
    public static final String WX_SUB_TIME="wxsubtime";
    public static final String WX_UNSUB_TIME="wxunsubtime";
    public static final String WX_LAST_ACTVIE_TIME="wxlastActvieTime";
    public static final String WX_MSG_TYPE="wxmsgtype";
    public static final String WX_EVENT="wxevent";
    public static final String WX_NET_WORK_TYPE="wxnetworktype";
    public static final String WX_OPENU_ID="wxopenuid";
    public static final String WX_NICK_NAME="wxnickname";
    public static final String WX_SEX="wxsex";
    public static final String WX_LANGUAGE="wxlanguage";
    public static final String WX_STATUS_ID="wxstatusid";
    public static final String WX_STATUS_NAME="wxstatusName";
    public static final String WX_CITY="wxcity";
    public static final String WX_PROVINCE="wxprovince";
    public static final String WX_COUNTRY="wxcountry";
    public static final String WX_HEADIMG_URL="wxheadimgurl";
    public static final String WX_UNION_ID="wxunionid";
    public static final String ISAPPUSER="isappuser";
    public static final String ISWXUSER="iswxuser";
    public static final String ISMOBILEUSER="ismobileuser";
    public static final String ISEMAILUSER="isemailuser";
    public static final String ISWEBSITEUSER="iswebsiteuser";


    protected void initDao(){
        // do nothing
    }

    public Serializable save(ClientUserInfo ClientUserInfo){
        log.debug("saving ClientUserInfo instance");
        Serializable serializable;
        try{
        	serializable = commonDao.save(ClientUserInfo);
            log.debug("save successful");
        }catch(RuntimeException re){
            log.error("save failed",re);
            throw re;
        }
        return serializable;
    }

    public void delete(ClientUserInfo ClientUserInfo){
        log.debug("deleting ClientUserInfo instance");
        try{
            commonDao.delete(ClientUserInfo);
            log.debug("delete successful");
        }catch(RuntimeException re){
            log.error("delete failed",re);
            throw re;
        }
    }

    public ClientUserInfo findById(java.lang.Integer id){
        log.debug("getting ClientUserInfo instance with id: " + id);
        try{

            return (ClientUserInfo) commonDao.findById(
            		ClientUserInfo.class,id);
        }catch(RuntimeException re){
            log.error("get failed",re);
            throw re;
        }
    }

    public List<ClientUserInfo> findByExample(ClientUserInfo instance){
        log.debug("finding ClientUserInfo instance by example");
        try{

            return commonDao.getEntitesForExample(instance,0,0);
        }catch(RuntimeException re){
            log.error("find by example failed",re);
            throw re;
        }
    }

    public List findByProperty(String propertyName,Object value){
        log.debug("finding ClientUserInfo instance with property: "
                + propertyName + ", value: " + value);
        try{
            String queryString = "from ClientUserInfo as model where model."
                    + propertyName + "= ?";
            return commonDao.getEntitiesByPropertiesWithHql(queryString,0,0,value);
        }catch(RuntimeException re){
            log.error("find by property name failed",re);
            throw re;
        }
    }

    public List findByOlay(String propertyName,Object value, Object id){
        log.debug("finding findByOlay "
                + value);
        try{
            String queryString = "from ClientUserInfo as model where model."
                    + propertyName + "= ?";
            if(id!=null){
            	queryString = queryString + " and id!="+id;
            }
            System.out.println("queryString:::"+queryString);
            return commonDao.getEntitiesByPropertiesWithHql(queryString,0,0,value);
        }catch(RuntimeException re){
            log.error("find by property name failed",re);
            throw re;
        }
    }
    
    public List findAll(){
        log.debug("finding all ClientUserInfo instances");
        try{
            String queryString = "from ClientUserInfo";
            return commonDao.getEntitiesByPropertiesWithHql(queryString,0,0);
        }catch(RuntimeException re){
            log.error("find all failed",re);
            throw re;
        }
    }

    public ClientUserInfo merge(ClientUserInfo detachedInstance){
        log.debug("merging ClientUserInfo instance");
        try{
        	ClientUserInfo result = (ClientUserInfo) commonDao
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        }catch(RuntimeException re){
            log.error("merge failed",re);
            throw re;
        }
    }

    public void attachDirty(ClientUserInfo instance){
        log.debug("attaching dirty ClientUserInfo instance");
        try{
            commonDao.saveOrUpdate(instance);
            log.debug("attach successful");
        }catch(RuntimeException re){
            log.error("attach failed",re);
            throw re;
        }
    }

    
    public int removeClientUserInfoId(Integer id){

        log.debug("removeClientUserInfoId: " + id);
        try{
            String queryString = "delete  from ClientUserInfo as model where model.id = "
                    + id;
            return commonDao.executeUpdateOrDelete(queryString);
        }catch(RuntimeException re){
            log.error("removeClientUserInfoId failed",re);
            throw re;
        }
    }
    
    public static ClientUserInfoDAO getFromApplicationContext(
            ApplicationContext ctx){
        return (ClientUserInfoDAO) ctx.getBean("ClientUserInfoDAO");
    }
}