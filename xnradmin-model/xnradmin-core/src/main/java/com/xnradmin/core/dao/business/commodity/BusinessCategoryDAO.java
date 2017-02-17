package com.xnradmin.core.dao.business.commodity;

import java.io.Serializable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Repository;

import com.cntinker.util.StringHelper;
import com.xnradmin.core.dao.CommonDAO;
import com.xnradmin.po.business.BusinessCategory;


@Repository("BusinessCategoryDAO")
public class BusinessCategoryDAO{
    
    @Autowired
    private CommonDAO commonDao;
    
    private static final Logger log = LoggerFactory
            .getLogger(BusinessCategoryDAO.class);

    // property constants

    public static final String ID="id";
    public static final String SORT_ID="sortId";//排序Id
    public static final String CATEGORY_PARENT_ID="categoryParentId";//上级分类Id
    public static final String CATEGORY_NAME="categoryName";//分类名称
    public static final String CATEGORY_PHOTO="categoryPhoto";//分类图片
    public static final String CATEGORY_HEAD_PHOTO="categoryHeadPhoto";//分类置顶图片
    public static final String CATEGORY_DESCRIPTION="categoryDescription";//分类描述
    public static final String CATEGORY_STATUS="categoryStatus";//分类状态
    public static final String PRIMARY_CONFIGURATION_ID="primaryConfigurationId";//对应商城ID
    public static final String STAFF_ID="staffId";	//隶属用户Id
    public static final String CREATE_TIME="createTime";//建立时间
    public static final String CREATE_STAFF_ID="createStaffId";//建立人
    public static final String MODIFY_TIME="modifyTime";//修改时间
    public static final String MODIFY_STAFF_ID="modifyStaffId";//修改人

    protected void initDao(){
        // do nothing
    }

    public Serializable save(BusinessCategory businessCategory){
        log.debug("saving BusinessCategory instance");
        Serializable cls;
        try{
        	cls = commonDao.save(businessCategory);
            log.debug("save successful");
        }catch(RuntimeException re){
            log.error("save failed",re);
            throw re;
        }
        return cls;
    }

    public void delete(BusinessCategory businessCategory){
        log.debug("deleting BusinessCategory instance");
        try{
            commonDao.delete(businessCategory);
            log.debug("delete successful");
        }catch(RuntimeException re){
            log.error("delete failed",re);
            throw re;
        }
    }

    public BusinessCategory findById(Integer id){
        log.debug("getting BusinessCategory instance with id: " + id);
        try{

            return (BusinessCategory) commonDao.findById(
            		BusinessCategory.class,id);
        }catch(RuntimeException re){
            log.error("get failed",re);
            throw re;
        }
    }

    public List<BusinessCategory> findByExample(BusinessCategory instance){
        log.debug("finding BusinessCategory instance by example");
        try{

            return commonDao.getEntitesForExample(instance,0,0);
        }catch(RuntimeException re){
            log.error("find by example failed",re);
            throw re;
        }
    }

    public List findByProperty(String propertyName,Object value){
        log.debug("finding BusinessCategory instance with property: "
                + propertyName + ", value: " + value);
        try{
            String queryString = "from BusinessCategory as model where model."
                    + propertyName + "= ?";
            return commonDao.getEntitiesByPropertiesWithHql(queryString,0,0,value);
        }catch(RuntimeException re){
            log.error("find by property name failed",re);
            throw re;
        }
    }

    public List findAll(){
        log.debug("finding all BusinessCategory instances");
        try{
            String queryString = "from BusinessCategory";
            return commonDao.getEntitiesByPropertiesWithHql(queryString,0,0);
        }catch(RuntimeException re){
            log.error("find all failed",re);
            throw re;
        }
    }

    public BusinessCategory merge(BusinessCategory detachedInstance){
        log.debug("merging BusinessCategory instance");
        try{
        	BusinessCategory result = (BusinessCategory) commonDao
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        }catch(RuntimeException re){
            log.error("merge failed",re);
            throw re;
        }
    }

    public void attachDirty(BusinessCategory instance){
        log.debug("attaching dirty BusinessCategory instance");
        try{
            commonDao.saveOrUpdate(instance);
            log.debug("attach successful");
        }catch(RuntimeException re){
            log.error("attach failed",re);
            throw re;
        }
    }

    
    public int removeBusinessCategoryId(Integer id){

        log.debug("removeBusinessCategoryId: " + id);
        try{
            String queryString = "delete  from BusinessCategory as model where model.id = "
                    + id;
            return commonDao.executeUpdateOrDelete(queryString);
        }catch(RuntimeException re){
            log.error("removeBusinessCategoryId failed",re);
            throw re;
        }
    }
    
    
    public static BusinessCategoryDAO getFromApplicationContext(
            ApplicationContext ctx){
        return (BusinessCategoryDAO) ctx.getBean("BusinessCategoryDAO");
    }
}