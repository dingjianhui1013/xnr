/**
 * 2013-7-24 上午2:10:23
 */
package com.xnradmin.core.dao;


import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.SimpleExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @autohr: bin_liu
 */
@Repository("CommonDAO")
public class CommonDAO<T> {

    @Autowired
    private CommonMasterDAO masterDao;

    @Autowired
    private CommonSlave01DAO slave01Dao;

    public int executeUpdateOrDelete(final String hql){
        return masterDao.executeUpdateOrDelete(hql);
    }

    public List getEntitiesByPropertiesWithHql(final String hql,
            final int pageNo,final int numPerPage,final Object... propValues){
        return slave01Dao.getEntitiesByPropertiesWithHql(hql,pageNo,numPerPage,
                propValues);
    }

    public List getEntitiesByExample(Object po,int pageNo,int numPerPage,
            String orderBy,String sort){
        return slave01Dao.getEntitiesByExample(po,pageNo,numPerPage,orderBy,
                sort);
    }

    public int getNumberOfEntitiesWithHql(String hql,final Object... propValues){
        return slave01Dao.getNumberOfEntitiesWithHql(hql,propValues);
    }

    public List getEntitesForExample(final Object po,final int pageNo,
            final int numPerPage,final List<SimpleExpression> express){
        return slave01Dao.getEntitesForExample(po,pageNo,numPerPage,express);
    }

    public List getEntitesForExample(Object po,int pageNo,int numPerPage,
            SimpleExpression express){
        return slave01Dao.getEntitesForExample(po,pageNo,numPerPage,express);
    }

    public List getEntitesForExample(Object po,int pageNo,int numPerPage){
        return slave01Dao.getEntitesForExample(po,pageNo,numPerPage);
    }

    public List getEntitesForExample(Object po,int pageNo,int numPerPage,
            List<SimpleExpression> express,String[] params){
        return slave01Dao.getEntitesForExample(po,pageNo,numPerPage,express,
                params);
    }

    public long getNumberLongOfEntitiesWithHql(String hql,Object... propValues){
        return slave01Dao.getNumberLongOfEntitiesWithHql(hql,propValues);
    }

    public Object getObjOfEntitiesWithHql(final String hql,
            final Object... propValues){
        return slave01Dao.getObjOfEntitiesWithHql(hql,propValues);
    }

    public T getEntityByPropertiesWithHql(final String hql,
            final Object... propValues){

        return (T) slave01Dao.getEntityByPropertiesWithHql(hql,propValues);
    }

    public Object findById(Class cls,Serializable id){
        return slave01Dao.findById(cls,id);
    }

    public Serializable save(Object transientInstance){
        return masterDao.save(transientInstance);
    }

    public void saveOrUpdate(Object transientInstance){
        masterDao.saveOrUpdate(transientInstance);
    }

    public Object merge(Object detachedInstance){
        return masterDao.merge(detachedInstance);

    }

    public Object modify(Object detachedInstance){
        return merge(detachedInstance);
    }

    public void delete(Object persistentInstance){
        masterDao.delete(persistentInstance);
    }

    public List findByProperty(Class cls,String propertyName,Object value){
        return slave01Dao.findByProperty(cls,propertyName,value);
    }
    
    

}
