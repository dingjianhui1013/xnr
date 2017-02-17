package com.xnradmin.core.dao.business.combo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Repository;

import com.cntinker.util.StringHelper;
import com.xnradmin.core.dao.CommonDAO;
import com.xnradmin.po.business.BusinessGoods;
import com.xnradmin.po.business.Combo;
import com.xnradmin.po.business.ComboGoods;
import com.xnradmin.po.business.ComboPlan;
import com.xnradmin.po.common.status.Status;
import com.xnradmin.vo.business.ComboVO;


@Repository("ComboDAO")
public class ComboDAO{
    
    @Autowired
    private CommonDAO commonDao;
    
    private static final Logger log = LoggerFactory
            .getLogger(ComboDAO.class);

    protected void initDao(){
        // do nothing
    }

    public Serializable save(BusinessGoods businessGoods){
        log.debug("saving BusinessGoods instance");
        Serializable cls;
        try{
        	cls = commonDao.save(businessGoods);
            log.debug("save successful");
        }catch(RuntimeException re){
            log.error("save failed",re);
            throw re;
        }
        return cls;
    }

    public void delete(BusinessGoods businessGoods){
        log.debug("deleting BusinessGoods instance");
        try{
            commonDao.delete(businessGoods);
            log.debug("delete successful");
        }catch(RuntimeException re){
            log.error("delete failed",re);
            throw re;
        }
    }

    public ComboVO findById(Integer id){
    	/*String hql = "from Combo c,ComboGoods cg "
    			+ " where c.id = cg.comboId and  c.id = "+id;

		List<Object[]> l = commonDao.getEntitiesByPropertiesWithHql(hql,0,0);
		List<ComboGoods> comboGoodsList = new ArrayList<>();
		List<ComboPlan> comboPlanList = new ArrayList<>();
		ComboVO resVo = new ComboVO();
		for (int i = 0; i < l.size(); i++) {
			Object[] obj = l.get(i);
			if(i==1){
				resVo.setCombo((Combo)obj[0]);
			}
			//ComboGoodsVO cgo
			comboGoodsList.add((ComboGoods)obj[1]);
		}
		resVo.setComboGoodsList(comboGoodsList);
		
		hql = "from Combo c, ComboPlan cp "
    			+ " where c.id = cp.comboId and  c.id = "+id;

		l = commonDao.getEntitiesByPropertiesWithHql(hql,0,0);
		for (int i = 0; i < l.size(); i++) {
			Object[] obj = l.get(i);
			comboPlanList.add((ComboPlan)obj[1]);
		}
		resVo.setComboPlanList(comboPlanList);
		return resVo;*/
        return null;
    }
    public ComboVO findById(String comboId)
    {
    	String hql = "from Combo c , Status s where c.id="+comboId+" and s.id=c.comboCycleStatus";
    	List list=commonDao.getEntitiesByPropertiesWithHql(hql,0,0);
    	List<ComboVO> comboVOs = new ArrayList<ComboVO>();
    	for (int i = 0; i < list.size(); i++) {
			ComboVO comboVo = new ComboVO();
			Object[] object = (Object[])list.get(i);
			Combo combo = (Combo)object[0];
			Status status = (Status)object[1];
			comboVo.setCombo(combo);
			comboVo.setStatus(status);
			comboVOs.add(comboVo);
		}
    	if(!comboVOs.isEmpty())
    	{
    		
    		return comboVOs.get(0);
    	}else {
			return null;
		}
    	
    }

    public List<BusinessGoods> findByExample(BusinessGoods instance){
        log.debug("finding BusinessGoods instance by example");
        try{

            return commonDao.getEntitesForExample(instance,0,0);
        }catch(RuntimeException re){
            log.error("find by example failed",re);
            throw re;
        }
    }

    public List findByProperty(String propertyName,Object value){
        log.debug("finding BusinessGoods instance with property: "
                + propertyName + ", value: " + value);
        try{
            String queryString = "from BusinessGoods as model where model."
                    + propertyName + "= ?";
            return commonDao.getEntitiesByPropertiesWithHql(queryString,0,0,value);
        }catch(RuntimeException re){
            log.error("find by property name failed",re);
            throw re;
        }
    }

    public List findAll(){
        log.debug("finding all BusinessGoods instances");
        try{
            String queryString = "from BusinessGoods";
            return commonDao.getEntitiesByPropertiesWithHql(queryString,0,0);
        }catch(RuntimeException re){
            log.error("find all failed",re);
            throw re;
        }
    }

    public BusinessGoods merge(BusinessGoods detachedInstance){
        log.debug("merging BusinessGoods instance");
        try{
        	BusinessGoods result = (BusinessGoods) commonDao
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        }catch(RuntimeException re){
            log.error("merge failed",re);
            throw re;
        }
    }

    public void attachDirty(BusinessGoods instance){
        log.debug("attaching dirty BusinessGoods instance");
        try{
            commonDao.saveOrUpdate(instance);
            log.debug("attach successful");
        }catch(RuntimeException re){
            log.error("attach failed",re);
            throw re;
        }
    }

    
    public int removeBusinessGoodsId(Integer id){

        log.debug("removeBusinessGoodsId: " + id);
        try{
            String queryString = "delete  from BusinessGoods as model where model.id = "
                    + id;
            return commonDao.executeUpdateOrDelete(queryString);
        }catch(RuntimeException re){
            log.error("removeBusinessGoodsId failed",re);
            throw re;
        }
    }
    
    public List findByOlay(String BusinessGoodsName, String BusinessGoodsId){
        log.debug("finding findByOlay "
                + BusinessGoodsName);
        try{
            String queryString = "from BusinessGoods where BusinessGoodsName= '"+BusinessGoodsName+"'";
            if(!StringHelper.isNull(BusinessGoodsId)){
            	queryString = queryString + " and id!="+BusinessGoodsId;
            }
            return commonDao.getEntitiesByPropertiesWithHql(queryString,0,0);
        }catch(RuntimeException re){
            log.error("find by property name failed",re);
            throw re;
        }
    }
    
    public static ComboDAO getFromApplicationContext(
            ApplicationContext ctx){
        return (ComboDAO) ctx.getBean("ComboDAO");
    }

	public List<Combo> findByPage(ComboVO vo, int curPage, int pageSize,
			String orderField, String direction) {
		String hql = "from Combo c where 1=1 ";
		if (vo!=null && vo.getCombo() != null) {
			if (!StringHelper.isNull(vo.getCombo().getComboName())) {
				hql = hql + " and c.comboName like '%"
						+ vo.getCombo().getComboName() + "%'";
			}
		}
		if (!StringHelper.isNull(orderField) && !StringHelper.isNull(direction)) {
			hql = hql + " order by " + orderField + " " + direction;
		} else {
			hql += " order by c.id desc";
		}
		List l = commonDao.getEntitiesByPropertiesWithHql(hql, curPage,
				pageSize);
		return l;
	}

	public int getCount(ComboVO vo) {
		String hql = "select count(*) from Combo c where 1=1 ";
		if (vo!=null && vo.getCombo() != null) {
			if (!StringHelper.isNull(vo.getCombo().getComboName())) {
				hql = hql + " and c.comboName like '%"
						+ vo.getCombo().getComboName() + "%'";
			}
		}
		return this.commonDao.getNumberOfEntitiesWithHql(hql);
	}

	public Serializable saveCombo(Combo combo) {
		log.debug("saving Combo instance");
        Serializable cls;
        try{
        	cls = commonDao.save(combo);
            log.debug("save successful");
        }catch(RuntimeException re){
            log.error("save failed",re);
            throw re;
        }
        return cls;
	}

	public Serializable saveComboGoods(ComboGoods comboGoods) {
		log.debug("saving ComboGoods instance");
        Serializable cls;
        try{
        	cls = commonDao.save(comboGoods);
            log.debug("save successful");
        }catch(RuntimeException re){
            log.error("save failed",re);
            throw re;
        }
        return cls;
	}

	public Serializable saveComboPlan(ComboPlan comboPlan) {
		log.debug("saving ComboPlan instance");
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

	public List<ComboVO> findAllCombo() {
		String hql = "from Combo c , Status s where c.comboStatus = 0 and s.id=c.comboCycleStatus";
		List list = commonDao.getEntitiesByPropertiesWithHql(hql,0,0);
		List<ComboVO> comboVo = new ArrayList<ComboVO>();
		for (int i = 0; i < list.size(); i++) {
			ComboVO combov = new ComboVO();
			Object[] obj = (Object[]) list.get(i);
			Combo  combo = (Combo)obj[0];
			Status status = (Status)obj[1];
			combov.setCombo(combo);
			combov.setStatus(status);
			comboVo.add(combov);
		}
		return comboVo;
	}
	
}