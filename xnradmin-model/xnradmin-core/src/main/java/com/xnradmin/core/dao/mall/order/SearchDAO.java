package com.xnradmin.core.dao.mall.order;

import java.io.Serializable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Repository;

import com.cntinker.util.StringHelper;
import com.xnradmin.core.dao.CommonDAO;
import com.xnradmin.po.mall.order.OrderRecord;
import com.xnradmin.po.mall.order.Search;
import com.xnradmin.po.mall.order.ShoppingCart;


@Repository("SearchDAO")
public class SearchDAO{
    
    @Autowired
    private CommonDAO commonDao;
    
    private static final Logger log = LoggerFactory
            .getLogger(SearchDAO.class);

    // property constants

    public static final String ID = "id";                                                                        
    public static final String CLIENT_USER_ID = "clientUserId"; //用户ID
    public static final String SEARCH_VALUE = "searchValue"; //搜索文字
    public static final String SEARCH_TIME = "searchTime"; //搜索时间

    protected void initDao(){
        // do nothing
    }

    public Serializable save(Search search){
        log.debug("saving Search instance");
        Serializable cls;
        try{
        	cls = commonDao.save(search);
            log.debug("save successful");
        }catch(RuntimeException re){
            log.error("save failed",re);
            throw re;
        }
        return cls;
    }

    public void delete(Search search){
        log.debug("deleting Search instance");
        try{
            commonDao.delete(search);
            log.debug("delete successful");
        }catch(RuntimeException re){
            log.error("delete failed",re);
            throw re;
        }
    }

    public Search findById(Long id){
        log.debug("getting Search instance with id: " + id);
        try{

            return (Search) commonDao.findById(
            		Search.class,id);
        }catch(RuntimeException re){
            log.error("get failed",re);
            throw re;
        }
    }

    public List<Search> findByExample(Search instance){
        log.debug("finding Search instance by example");
        try{

            return commonDao.getEntitesForExample(instance,0,0);
        }catch(RuntimeException re){
            log.error("find by example failed",re);
            throw re;
        }
    }

    public List findByProperty(String propertyName,Object value){
        log.debug("finding Search instance with property: "
                + propertyName + ", value: " + value);
        try{
            String queryString = "from Search as model where model."
                    + propertyName + "= ?";
            return commonDao.getEntitiesByPropertiesWithHql(queryString,0,0,value);
        }catch(RuntimeException re){
            log.error("find by property name failed",re);
            throw re;
        }
    }
    
    
    public List findAll(){
        log.debug("finding all Search instances");
        try{
            String queryString = "from Search";
            return commonDao.getEntitiesByPropertiesWithHql(queryString,0,0);
        }catch(RuntimeException re){
            log.error("find all failed",re);
            throw re;
        }
    }

    public Search merge(Search detachedInstance){
        log.debug("merging Search instance");
        try{
        	Search result = (Search) commonDao
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        }catch(RuntimeException re){
            log.error("merge failed",re);
            throw re;
        }
    }

    public void attachDirty(Search instance){
        log.debug("attaching dirty Search instance");
        try{
            commonDao.saveOrUpdate(instance);
            log.debug("attach successful");
        }catch(RuntimeException re){
            log.error("attach failed",re);
            throw re;
        }
    }

    
    public int removeSearchId(Long id){

        log.debug("removeSearchId: " + id);
        try{
            String queryString = "delete  from Search as model where model.id = "
                    + id;
            return commonDao.executeUpdateOrDelete(queryString);
        }catch(RuntimeException re){
            log.error("removeSearchId failed",re);
            throw re;
        }
    }
    
    
    public static SearchDAO getFromApplicationContext(
            ApplicationContext ctx){
        return (SearchDAO) ctx.getBean("SearchDAO");
    }
}