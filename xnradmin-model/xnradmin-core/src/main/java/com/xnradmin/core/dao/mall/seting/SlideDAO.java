package com.xnradmin.core.dao.mall.seting;

import java.io.Serializable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Repository;

import com.cntinker.util.StringHelper;
import com.xnradmin.core.dao.CommonDAO;
import com.xnradmin.po.mall.seting.Slide;


@Repository("SlideDAO")
public class SlideDAO{
    
    @Autowired
    private CommonDAO commonDao;
    
    private static final Logger log = LoggerFactory
            .getLogger(SlideDAO.class);

    // property constants

    public static final String ID="id";
    public static final String SORT_ID="sortId";//排序Id
    public static final String TITLE="title";//标题
    public static final String PIC_PATH="picPath";//本地图片位置
    public static final String PIC_URL="picUrl";//外链图片URL
    public static final String SHOW_STATUS="showStatus";//是否显示
    public static final String SHOW_POSITION="showPosition";//显示位置
    public static final String STAFF_ID="staffId";	//隶属用户Id
    public static final String PRIMARY_CONFIGURATION_ID="primaryConfigurationId";	//商城Id
    public static final String PIC_TYPE="prcType";	//图片类型（LOGO，背景，幻灯片）
    public static final String CREATE_TIME="createTime";//建立时间
    public static final String CREATE_STAFF_ID="createStaffId";//建立人
    public static final String MODIFY_TIME="modifyTime";//修改时间
    public static final String MODIFY_STAFF_ID="modifyStaffId";//修改人

    protected void initDao(){
        // do nothing
    }

    public Serializable save(Slide slide){
        log.debug("saving Slide instance");
        Serializable cls;
        try{
        	cls = commonDao.save(slide);
            log.debug("save successful");
        }catch(RuntimeException re){
            log.error("save failed",re);
            throw re;
        }
        return cls;
    }

    public void delete(Slide slide){
        log.debug("deleting Slide instance");
        try{
            commonDao.delete(slide);
            log.debug("delete successful");
        }catch(RuntimeException re){
            log.error("delete failed",re);
            throw re;
        }
    }

    public Slide findById(Integer id){
        log.debug("getting Slide instance with id: " + id);
        try{

            return (Slide) commonDao.findById(
            		Slide.class,id);
        }catch(RuntimeException re){
            log.error("get failed",re);
            throw re;
        }
    }

    public List<Slide> findByExample(Slide instance){
        log.debug("finding Slide instance by example");
        try{

            return commonDao.getEntitesForExample(instance,0,0);
        }catch(RuntimeException re){
            log.error("find by example failed",re);
            throw re;
        }
    }

    public List findByProperty(String propertyName,Object value){
        log.debug("finding Slide instance with property: "
                + propertyName + ", value: " + value);
        try{
            String queryString = "from Slide as model where model."
                    + propertyName + "= ?";
            return commonDao.getEntitiesByPropertiesWithHql(queryString,0,0,value);
        }catch(RuntimeException re){
            log.error("find by property name failed",re);
            throw re;
        }
    }

    public List findAll(){
        log.debug("finding all Slide instances");
        try{
            String queryString = "from Slide";
            return commonDao.getEntitiesByPropertiesWithHql(queryString,0,0);
        }catch(RuntimeException re){
            log.error("find all failed",re);
            throw re;
        }
    }

    public Slide merge(Slide detachedInstance){
        log.debug("merging Slide instance");
        try{
        	Slide result = (Slide) commonDao
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        }catch(RuntimeException re){
            log.error("merge failed",re);
            throw re;
        }
    }

    public void attachDirty(Slide instance){
        log.debug("attaching dirty Slide instance");
        try{
            commonDao.saveOrUpdate(instance);
            log.debug("attach successful");
        }catch(RuntimeException re){
            log.error("attach failed",re);
            throw re;
        }
    }

    
    public int removeSlideId(Integer id){

        log.debug("removeSlideId: " + id);
        try{
            String queryString = "delete  from Slide as model where model.id = "
                    + id;
            return commonDao.executeUpdateOrDelete(queryString);
        }catch(RuntimeException re){
            log.error("removeSlideId failed",re);
            throw re;
        }
    }
    
    
    public static SlideDAO getFromApplicationContext(
            ApplicationContext ctx){
        return (SlideDAO) ctx.getBean("SlideDAO");
    }
}