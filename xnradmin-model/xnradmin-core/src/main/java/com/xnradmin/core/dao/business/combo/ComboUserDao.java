package com.xnradmin.core.dao.business.combo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.xnradmin.core.dao.CommonDAO;
import com.xnradmin.core.service.business.order.BusinessOrderGoodsRelationService;
import com.xnradmin.po.business.BusinessOrderGoodsRelation;
import com.xnradmin.po.business.BusinessOrderRecord;
import com.xnradmin.po.business.Combo;
import com.xnradmin.po.business.ComboUser;
import com.xnradmin.vo.business.ComboVO;

@Repository("ComboUserDAO")
public class ComboUserDao {
	
	@Autowired
    private CommonDAO commonDao;
    @Autowired
    private BusinessOrderGoodsRelationService orderRelationService;
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
	public List<ComboVO> findByComboVOs(Long orderId,Integer userId) {
		String hql ="from ComboUser u,Combo c where u.userId = "+userId+" and u.comboId = c.id and u.orderId = "+userId;
		List<Object[]> list = commonDao.getEntitiesByPropertiesWithHql(hql, 0, 0);
		List<ComboVO> comboVOs = new ArrayList<ComboVO>();
		for (Object[] object : list) {
			ComboVO comboVO = new ComboVO();
			ComboUser comboUser = (ComboUser)object[0];
			Combo combo = (Combo)object[1];
			comboVO.setComboUser(comboUser);
			comboVO.setCombo(combo);
			comboVOs.add(comboVO);
		}
		return comboVOs;
	}
	//周期订单查询
	public List<ComboVO> findByComboVOs(Long userId,int curPage,int pageSize) {
		String hql ="from ComboUser u,Combo c ,BusinessOrderRecord b where u.userId = "+userId+" and u.comboId = c.id and b.id=u.orderId";
		List<Object[]> list = commonDao.getEntitiesByPropertiesWithHql(hql, curPage, pageSize);
		List<ComboVO> comboVOs = new ArrayList<ComboVO>();
		for (Object[] object : list) {
			ComboVO comboVO = new ComboVO();
			ComboUser comboUser = (ComboUser)object[0];
			Combo combo = (Combo)object[1];
			BusinessOrderRecord orderRecord = (BusinessOrderRecord)object[2];
			List<BusinessOrderGoodsRelation> businessOrderGoodsRelations =  orderRelationService.findByComboOrderRecordIds(combo.getId().toString(),orderRecord.getId());
			if(!businessOrderGoodsRelations.isEmpty())
			{
				orderRecord.setTotalCount(businessOrderGoodsRelations.get(0).getGoodsCount());
			}
			comboVO.setComboUser(comboUser);
			comboVO.setCombo(combo);
			comboVO.setOrderRecord(orderRecord);
			comboVOs.add(comboVO);
		}
		return comboVOs;
	}
	public int getCount(Long userId) {
		String hql = "select count(id) from ComboUser c where c.userId="+userId;
		Integer count =  commonDao.getNumberOfEntitiesWithHql(hql);
		return count;
	}
	public ComboUser findById(String comboUserId) {
		return (ComboUser) commonDao.findById(ComboUser.class,Integer.parseInt(comboUserId));
	}
	    
}
