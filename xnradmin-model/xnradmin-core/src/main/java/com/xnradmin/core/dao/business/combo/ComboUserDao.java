package com.xnradmin.core.dao.business.combo;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.xnradmin.core.dao.CommonDAO;
import com.xnradmin.po.business.ComboUser;

@Repository("ComboUserDAO")
public class ComboUserDao {
	
	@Autowired
    private CommonDAO commonDao;
    
    private static final Logger log = LoggerFactory
            .getLogger(ComboUserDao.class);

    protected void initDao(){
        // do nothing
    }
    public Serializable save(ComboUser comboUser){
        log.debug("saving BusinessGoods instance");
        Serializable cls;
        try{
        	cls = commonDao.save(comboUser);
            log.debug("save successful");
        }catch(RuntimeException re){
            log.error("save failed",re);
            throw re;
        }
        return cls;
    }
	    
}
