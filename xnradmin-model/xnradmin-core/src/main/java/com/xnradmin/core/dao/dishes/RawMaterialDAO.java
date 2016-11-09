package com.xnradmin.core.dao.dishes;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Repository;

import com.cntinker.util.StringHelper;
import com.xnradmin.core.dao.CommonDAO;
import com.xnradmin.po.dishes.RawMaterial;


@Repository("RawMaterialDAO")
public class RawMaterialDAO{
    
    @Autowired
    private CommonDAO commonDao;
    
    private static final Logger log = LoggerFactory
            .getLogger(RawMaterialDAO.class);

    // property constants

    public static final String ID = "id";
    public static final String MATERIAL_NAME = "materialName";  //原料名
    public static final String MATERIAL_TYPE_ID = "materialTypeId";  //原料类型
    public static final String WEIGHT_ID = "weightId";  //重量单位
    public static final String CREATE_TIME = "createTime";  //建立时间
    public static final String CREATE_STAFF_ID = "createStaffId";  //建立人
    public static final String MODIFY_TIME = "modifyTime";  //修改时间
    public static final String MODIFY_STAFF_ID = "modifyStaffId";  //修改人

    protected void initDao(){
        // do nothing
    }

    public void save(RawMaterial rawMaterial){
        log.debug("saving RawMaterial instance");
        try{
            commonDao.save(rawMaterial);
            log.debug("save successful");
        }catch(RuntimeException re){
            log.error("save failed",re);
            throw re;
        }
    }

    public void delete(RawMaterial rawMaterial){
        log.debug("deleting RawMaterial instance");
        try{
            commonDao.delete(rawMaterial);
            log.debug("delete successful");
        }catch(RuntimeException re){
            log.error("delete failed",re);
            throw re;
        }
    }

    public RawMaterial findById(Integer id){
        log.debug("getting RawMaterial instance with id: " + id);
        try{

            return (RawMaterial) commonDao.findById(
            		RawMaterial.class,id);
        }catch(RuntimeException re){
            log.error("get failed",re);
            throw re;
        }
    }

    public List<RawMaterial> findByExample(RawMaterial instance){
        log.debug("finding RawMaterial instance by example");
        try{

            return commonDao.getEntitesForExample(instance,0,0);
        }catch(RuntimeException re){
            log.error("find by example failed",re);
            throw re;
        }
    }

    public List findByProperty(String propertyName,Object value){
        log.debug("finding Dish instance with property: "
                + propertyName + ", value: " + value);
        try{
            String queryString = "from RawMaterial as model where model."
                    + propertyName + "= ?";
            return commonDao.getEntitiesByPropertiesWithHql(queryString,0,0,value);
        }catch(RuntimeException re){
            log.error("find by property name failed",re);
            throw re;
        }
    }

    public List findAll(){
        log.debug("finding all RawMaterial instances");
        try{
            String queryString = "from RawMaterial";
            return commonDao.getEntitiesByPropertiesWithHql(queryString,0,0);
        }catch(RuntimeException re){
            log.error("find all failed",re);
            throw re;
        }
    }

    public RawMaterial merge(RawMaterial detachedInstance){
        log.debug("merging RawMaterial instance");
        try{
        	RawMaterial result = (RawMaterial) commonDao
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        }catch(RuntimeException re){
            log.error("merge failed",re);
            throw re;
        }
    }

    public void attachDirty(RawMaterial instance){
        log.debug("attaching dirty RawMaterial instance");
        try{
            commonDao.saveOrUpdate(instance);
            log.debug("attach successful");
        }catch(RuntimeException re){
            log.error("attach failed",re);
            throw re;
        }
    }

    
    public int removeRawMaterialId(Long id){

        log.debug("removeDishId: " + id);
        try{
            String queryString = "delete  from RawMaterial as model where model.id = "
                    + id;
            return commonDao.executeUpdateOrDelete(queryString);
        }catch(RuntimeException re){
            log.error("removeDishId failed",re);
            throw re;
        }
    }
    
    public List findByOlay(String materialName, String rawMaterialId){
        log.debug("finding findByOlay "
                + materialName);
        try{
            String queryString = "from RawMaterial where materialName= '"+materialName+"'";
            if(!StringHelper.isNull(rawMaterialId)){
            	queryString = queryString + " and id!="+rawMaterialId;
            }
            System.out.println(queryString);
            return commonDao.getEntitiesByPropertiesWithHql(queryString,0,0);
        }catch(RuntimeException re){
            log.error("find by property name failed",re);
            throw re;
        }
    }
    
    public static RawMaterialDAO getFromApplicationContext(
            ApplicationContext ctx){
        return (RawMaterialDAO) ctx.getBean("RawMaterialDAO");
    }
}