package com.xnradmin.core.dao.business.order;

import java.io.Serializable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.xnradmin.core.dao.CommonDAO;
import com.xnradmin.po.business.AllocationData;



@Repository("AllocationListDAO")
public class AllocationListDAO{
    
    @Autowired
    private CommonDAO commonDao;
    
    private static final Logger log = LoggerFactory
            .getLogger(AllocationListDAO.class);

    // property constants

   
    protected void initDao(){
        // do nothing
    }

    public Serializable save(AllocationData allocationData){
        log.debug("saving AllocationData instance");
        Serializable cls;
        try{
        	cls = commonDao.save(allocationData);
            log.debug("save successful");
        }catch(RuntimeException re){
            log.error("save failed",re);
            throw re;
        }
        return cls;
    }

    public void delete(AllocationData allocationData){
        log.debug("deleting AllocationData instance");
        try{
            commonDao.delete(allocationData);
            log.debug("delete successful");
        }catch(RuntimeException re){
            log.error("delete failed",re);
            throw re;
        }
    }

    public AllocationData findById(Integer id){
        log.debug("getting AllocationData instance with id: " + id);
        try{

            return (AllocationData) commonDao.findById(
            		AllocationData.class,id);
        }catch(RuntimeException re){
            log.error("get failed",re);
            throw re;
        }
    }

    public List findAll(){
        log.debug("finding all AllocationData instances");
        try{
            String queryString = "from AllocationData";
            return commonDao.getEntitiesByPropertiesWithHql(queryString,0,0);
        }catch(RuntimeException re){
            log.error("find all failed",re);
            throw re;
        }
    }
    
    public void deleteBach(List<AllocationData> farmerOrderRecordList){
        log.debug("deleting AllocationData bach");
        try{
        	if(farmerOrderRecordList!=null){
        		for(AllocationData allocationData:farmerOrderRecordList){
        			commonDao.delete(allocationData);
        		}
        		log.debug("delete successful");
        	}
        }catch(RuntimeException re){
            log.error("delete failed",re);
            throw re;
        }
    }
}