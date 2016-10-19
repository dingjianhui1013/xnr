package com.xnradmin.core.dao;


import java.io.Serializable;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;
import org.hibernate.jdbc.ReturningWork;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cntinker.util.StringHelper;

/**
 * Data access object (DAO) for domain model
 */
@Transactional(value = "transactionManager_master", readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class BaseHibernateMasterDAO<T> {

    private static final Logger log = LoggerFactory
            .getLogger(BaseHibernateMasterDAO.class);

    private SessionFactory sessionFactory;
    
    private String dbname;

    public String getDbname(){
        return dbname;
    }
        
    @Value(value="${platform.dbanme}")
    public void setDbname(String dbname){
        this.dbname = dbname;
    }

    @Resource(name = "sessionFactory_master")
    public void setSessionFactory(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    public SessionFactory getSessionFactory(){
        return sessionFactory;
    }

    public Session getSession(){
        // 事务必须是开启的(Required)，否则获取不到
        return sessionFactory.getCurrentSession();
    }

    public int executeUpdateOrDelete(final String hql){
        Query query = getSession().createQuery(hql);
        return query.executeUpdate();
    }

    public List getEntitiesByPropertiesWithHql(final String hql,
            final int pageNo,final int numPerPage,final Object... propValues){
        Query query = getSession().createQuery(hql);
        for(int i = 0;i < propValues.length;i ++ ){
            query.setParameter(i,propValues[i]);
        }
        if(pageNo > 0){
            query.setFirstResult( ( pageNo - 1 ) * numPerPage);
            query.setMaxResults(numPerPage);
        }
        return query.list();
    }

    public List getEntitiesByExample(final Object po,final int pageNo,
            final int numPerPage,final String orderBy,final String sort){
        Example example = Example.create(po).excludeZeroes() // exclude
                .ignoreCase() // perform case insensitive string
                .enableLike(MatchMode.ANYWHERE);
        Criteria criteria = getSession().createCriteria(po.getClass()).add(
                example);

        if(pageNo > 0){
            criteria.setFirstResult( ( pageNo - 1 ) * numPerPage);
            criteria.setMaxResults(numPerPage);
        }

        if( ( orderBy != null && orderBy.trim().length() > 0 )
                && ( sort != null && sort.trim().length() > 0 )){
            if(sort.trim().toLowerCase().equals("asc")){
                criteria.addOrder(Order.asc(orderBy.trim()));
            }else if(sort.trim().toLowerCase().equals("desc")){
                criteria.addOrder(Order.desc(orderBy.trim()));
            }
        }
        return criteria.list();

    }

    public int getNumberOfEntitiesWithHql(final String hql,
            final Object... propValues){
        Query query = getSession().createQuery(hql);
        if(propValues != null && propValues.length > 0){
            for(int i = 0;i < propValues.length;i ++ ){
                query.setParameter(i,propValues[i]);
            }
        }
        Object obj = query.uniqueResult();
        if(obj == null)
            return 0;
        else
            return ( (Long) obj ).intValue();
    }

    public List getEntitesForExample(final Object po,final int pageNo,
            final int numPerPage,final List<SimpleExpression> express){
        Example example = Example.create(po).excludeZeroes().ignoreCase()
                .enableLike(MatchMode.ANYWHERE);
        Criteria criteria = getSession().createCriteria(po.getClass()).add(
                example);

        if(pageNo > 0){
            criteria.setFirstResult( ( pageNo - 1 ) * numPerPage);
            criteria.setMaxResults(numPerPage);
        }
        if(!express.isEmpty()){
            for(SimpleExpression exp : express){
                criteria.add(exp);
            }
        }
        return criteria.list();
    }

    public List getEntitesForExample(final Object po,final int pageNo,
            final int numPerPage,final SimpleExpression express){
        List<SimpleExpression> l = new ArrayList<SimpleExpression>();
        return getEntitesForExample(po,pageNo,numPerPage,l);
    }

    public List getEntitesForExample(final Object po,final int pageNo,
            final int numPerPage){
        return getEntitesForExample(po,pageNo,numPerPage,new ArrayList());
    }

    public List getEntitesForExample(final Object po,final int pageNo,
            final int numPerPage,final List<SimpleExpression> express,
            final String[] params){
        Example example = Example.create(po).excludeZeroes().ignoreCase()
                .enableLike(MatchMode.ANYWHERE);
        Criteria cruteria = getSession().createCriteria(po.getClass()).add(
                example);
        if(!express.isEmpty()){
            for(SimpleExpression exp : express){
                cruteria.add(exp);
            }
        }
        if(params != null && params.length > 0){
            cruteria.add(Restrictions.not(Restrictions.in("propValue",params)));
        }
        return cruteria.list();
    }

    public long getNumberLongOfEntitiesWithHql(final String hql,
            final Object... propValues){

        Query query = getSession().createQuery(hql);
        if(propValues != null && propValues.length > 0){
            for(int i = 0;i < propValues.length;i ++ ){
                query.setParameter(i,propValues[i]);
            }
        }
        Object obj = query.uniqueResult();

        if(obj == null)
            return 0;
        else
            return ( (Long) obj );
    }

    public Object getObjOfEntitiesWithHql(final String hql,
            final Object... propValues){
        Query query = getSession().createQuery(hql);
        if(propValues != null && propValues.length > 0){
            for(int i = 0;i < propValues.length;i ++ ){
                query.setParameter(i,propValues[i]);
            }
        }
        return query.uniqueResult();

    }

    public List getEntityByPropertiesWithHql(final String hql,
            final Object... propValues){
        Query query = getSession().createQuery(hql);
        for(int i = 0;i < propValues.length;i ++ ){
            query.setParameter(i,propValues[i]);
        }

        return query.list();
    }

    public Object findById(Class cls,Serializable id){
        log.debug("getting Object instance with id: " + id);
        return getSession().get(cls.getName(),id);
    }

    public Serializable save(Object transientInstance){
        log.debug("saving Object instance");
        try{
            Serializable cls = getSession().save(transientInstance);
            return cls;

        }catch(RuntimeException re){
            log.error("save failed",re);
            throw re;
        }
    }

    public void saveOrUpdate(Object transientInstance){
        log.debug("saving Object instance");
        try{
            getSession().saveOrUpdate(transientInstance);

        }catch(RuntimeException re){
            log.error("save failed",re);
            throw re;
        }
    }

    public Object merge(Object detachedInstance){
        try{
            Object result = getSession().merge(detachedInstance);
            log.debug("modify successful");
            return result;
        }catch(RuntimeException re){
            log.error("modify failed",re);
            throw re;
        }

    }

    public Object modify(Object detachedInstance){
        return merge(detachedInstance);
    }

    public void delete(Object persistentInstance){
        log.debug("deleting obj instance");
        try{
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        }catch(RuntimeException re){
            log.error("delete failed",re);
            throw re;
        }
    }

    public List findByProperty(Class cls,String propertyName,Object value){
        log.debug("finding Object instance with property: " + propertyName
                + ", value: " + value);
        try{
            String queryString = "from " + cls.getName()
                    + " as model where model." + propertyName + "= ?";

            Object obj = getEntityByPropertiesWithHql(queryString,value);

            return (List) obj;
        }catch(RuntimeException re){
            log.error("find by property name failed",re);
            throw re;
        }
    }

    public void lock(Object entity,LockMode lockMode){
        getSession().lock(entity,lockMode);
    }

}