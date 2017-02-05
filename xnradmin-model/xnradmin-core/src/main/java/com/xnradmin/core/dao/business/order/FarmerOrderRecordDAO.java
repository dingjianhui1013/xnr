package com.xnradmin.core.dao.business.order;

import java.io.Serializable;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.xnradmin.core.dao.CommonDAO;
import com.xnradmin.po.business.FarmerOrderRecord;



@Repository("FarmerOrderRecordDAO")
public class FarmerOrderRecordDAO{
    
    @Autowired
    private CommonDAO commonDao;
    
    private static final Logger log = LoggerFactory
            .getLogger(FarmerOrderRecordDAO.class);

    // property constants

   
    protected void initDao(){
        // do nothing
    }

    public Serializable save(FarmerOrderRecord farmerOrderRecord){
        log.debug("saving FarmerOrderRecord instance");
        Serializable cls;
        try{
        	cls = commonDao.save(farmerOrderRecord);
            log.debug("save successful");
        }catch(RuntimeException re){
            log.error("save failed",re);
            throw re;
        }
        return cls;
    }

    public void delete(FarmerOrderRecord farmerOrderRecord){
        log.debug("deleting FarmerOrderRecord instance");
        try{
            commonDao.delete(farmerOrderRecord);
            log.debug("delete successful");
        }catch(RuntimeException re){
            log.error("delete failed",re);
            throw re;
        }
    }

    public FarmerOrderRecord findById(Integer id){
        log.debug("getting FarmerOrderRecord instance with id: " + id);
        try{

            return (FarmerOrderRecord) commonDao.findById(
            		FarmerOrderRecord.class,id);
        }catch(RuntimeException re){
            log.error("get failed",re);
            throw re;
        }
    }

    
    
    public List findAll(){
        log.debug("finding all FarmerOrderRecord instances");
        try{
            String queryString = "from FarmerOrderRecord";
            return commonDao.getEntitiesByPropertiesWithHql(queryString,0,0);
        }catch(RuntimeException re){
            log.error("find all failed",re);
            throw re;
        }
    }
    
    
    public void deleteBach(List<FarmerOrderRecord> farmerOrderRecordList){
        log.debug("deleting FarmerOrderRecord bach");
        try{
        	if(farmerOrderRecordList!=null){
        		for(FarmerOrderRecord farmerOrderRecord:farmerOrderRecordList){
        			commonDao.delete(farmerOrderRecord);
        		}
        		log.debug("delete successful");
        	}
        }catch(RuntimeException re){
            log.error("delete failed",re);
            throw re;
        }
    }
    
    
    
}