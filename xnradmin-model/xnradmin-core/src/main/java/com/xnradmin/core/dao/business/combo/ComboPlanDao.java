package com.xnradmin.core.dao.business.combo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.xnradmin.core.dao.CommonDAO;
import com.xnradmin.po.business.BusinessGoods;
import com.xnradmin.po.business.ComboPlan;
import com.xnradmin.po.business.ComboUser;

@Repository("ComboPlanDao")
public class ComboPlanDao {

	@Autowired
    private CommonDAO commonDao;
	
	private static final Logger log = LoggerFactory
            .getLogger(ComboPlanDao.class);

    protected void initDao(){
        // do nothing
    }
    public Serializable save(ComboPlan comboPlan){
        log.debug("saving BusinessGoods instance");
        Serializable cls;
        try{
        	cls = commonDao.save(comboPlan);
            log.debug("save successful");
        }catch(RuntimeException re){
            log.error("save failed",re);
            throw re;
        }
        return cls;
    }
	public List<BusinessGoods> findGoodsBycomboId(String comboId) {
		
		String hql = "from BusinessGoods b,ComboPlan c where b.id = c.goodsId and c.comboId = '"+comboId+"'";
		List<Object[]> objects = commonDao.getEntitiesByPropertiesWithHql(hql, 0, 0);
		List<BusinessGoods>  goodsList = new ArrayList<BusinessGoods>(); 
		for (Object[] obj : objects) {
			BusinessGoods goods  = (BusinessGoods) obj[0];
			goodsList.add(goods);
		}
		return goodsList;
	}
}
