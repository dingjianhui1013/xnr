/**
 * 2012-7-26 下午1:46:02
 */
package com.xnradmin.core.dao;


import java.io.Serializable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cntinker.util.StringHelper;
import com.xnradmin.po.CommonAttach;

/**
 * @author: liubin
 */
@Repository("CommonAttachDAO")
public class CommonAttachDAO{

    @Autowired
    private CommonDAO dao;

    private static final Logger log = LoggerFactory
            .getLogger(CommonAttachDAO.class);

    protected void initDao(){
        // do nothing
    }

    public Long save(CommonAttach transientInstance){
        log.debug("saving CommonAttach instance");
        try{
            Serializable cls = dao.save(transientInstance);
            return (Long) cls;

        }catch(RuntimeException re){
            log.error("save failed",re);
            throw re;
        }
    }

    public void delete(CommonAttach persistentInstance){
        log.debug("deleting CommonAttach instance");
        try{
        	dao.delete(persistentInstance);
            log.debug("delete successful");
        }catch(RuntimeException re){
            log.error("delete failed",re);
            throw re;
        }
    }

    public CommonAttach findById(java.lang.Long id){
        log.debug("getting CommonContact instance with id: " + id);
        try{
            CommonAttach instance = (CommonAttach) dao.findById(
                    CommonAttach.class,id);
            return instance;
        }catch(RuntimeException re){
            log.error("get failed",re);
            throw re;
        }
    }

    public List<CommonAttach> findByExample(CommonAttach instance){
        log.debug("finding CommonAttach instance by example");
        try{
            List<CommonAttach> results = (List<CommonAttach>) dao
                    .getEntitesForExample(instance,0,0);
            log.debug("find by example successful, result size: "
                    + results.size());
            return results;
        }catch(RuntimeException re){
            log.error("find by example failed",re);
            throw re;
        }
    }

    public List<CommonAttach> findByProperty(String propertyName,Object value){
        log.debug("finding CommonMenu instance with property: " + propertyName
                + ", value: " + value);
        try{
            String queryString = "from CommonAttach as model where model."
                    + propertyName + "= ?";

            return dao.getEntitiesByPropertiesWithHql(queryString,0,0,
                    value);
        }catch(RuntimeException re){
            log.error("find by property name failed",re);
            throw re;
        }
    }

    public List<CommonAttach> findByGroupid(Object groupid){
        return findByProperty("groupid",groupid);
    }

    public int findCountByGroupid(String groupid){
        String hql = "select count(*) from CommonAttach";
        if(!StringHelper.isNull(groupid)){
            hql += " where groupid='" + groupid + "'";
        }else{
            return 0;
        }
        return dao.getNumberOfEntitiesWithHql(hql);
    }

    public List findAll(){
        log.debug("finding all CommonAttach instances");
        try{
            String queryString = "from CommonAttach";

            return dao.getEntitiesByPropertiesWithHql(queryString,0,0);
        }catch(RuntimeException re){
            log.error("find all failed",re);
            throw re;
        }
    }

    public CommonAttach merge(CommonAttach detachedInstance){
        log.debug("merging CommonAttach instance");
        try{
            CommonAttach result = (CommonAttach) dao
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        }catch(RuntimeException re){
            log.error("merge failed",re);
            throw re;
        }
    }

    public void attachDirty(CommonAttach instance){
        log.debug("attaching dirty CommonAttach instance");
        try{
        	dao.saveOrUpdate(instance);
            log.debug("attach successful");
        }catch(RuntimeException re){
            log.error("attach failed",re);
            throw re;
        }
    }

}
