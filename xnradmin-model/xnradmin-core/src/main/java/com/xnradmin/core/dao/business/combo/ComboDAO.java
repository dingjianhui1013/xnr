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
import com.xnradmin.po.business.ComboUser;
import com.xnradmin.vo.business.ComboGoodsVO;
import com.xnradmin.vo.business.ComboPlanVO;
import com.xnradmin.vo.business.ComboUserVO;
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

    public void delete(Combo combo){
        log.debug("deleting Combo instance");
        try{
            commonDao.delete(combo);
            log.debug("delete successful");
        }catch(RuntimeException re){
            log.error("delete failed",re);
            throw re;
        }
    }
    
    public void deleteComboGoods(ComboGoods comboGoods){
        log.debug("deleting ComboGoods instance");
        try{
            commonDao.delete(comboGoods);
            log.debug("delete successful");
        }catch(RuntimeException re){
            log.error("delete failed",re);
            throw re;
        }
    }

    public ComboVO findById(Integer id){
    	String hql = "from Combo c,ComboGoods cg,BusinessGoods bg "
    			+ " where c.id = cg.comboId and bg.id=cg.goodsId and  c.id = "+id;

		List<Object[]> l = commonDao.getEntitiesByPropertiesWithHql(hql,0,0);
		List<ComboGoodsVO> comboGoodsList = new ArrayList<>();
		List<ComboPlanVO> comboPlanList = new ArrayList<>();
		ComboVO resVo = new ComboVO();
		for (int i = 0; i < l.size(); i++) {
			Object[] obj = l.get(i);
			if(i==0){
				resVo.setCombo((Combo)obj[0]);
			}
			ComboGoodsVO cgo = new ComboGoodsVO();
			cgo.setComboGoods((ComboGoods)obj[1]);
			cgo.setBusinessGoods((BusinessGoods)obj[2]);
			comboGoodsList.add(cgo);
		}
		resVo.setComboGoodsList(comboGoodsList);
		
		hql = "from Combo c, ComboPlan cp,BusinessGoods bg "
    			+ " where c.id = cp.comboId and bg.id=cp.goodsId and  c.id = "+id;

		l = commonDao.getEntitiesByPropertiesWithHql(hql,0,0);
		for (int i = 0; i < l.size(); i++) {
			Object[] obj = l.get(i);
			ComboPlanVO cpo = new ComboPlanVO();
			ComboPlan cp = (ComboPlan)obj[1];
			cpo.setComboPlan(cp);
			cpo.setBusinessGoods(((BusinessGoods)obj[2]));
			if(cp.getComboPlanType().equals("0")){//固定时间
				cpo.setComboCycleStr("");
			}else if(cp.getComboPlanType().equals("1")){//固定周期
				cpo.setComboCycleStr(cp.getComboPlanType()+"#"+cp.getComboPlanCycle());
			}else{
				cpo.setComboCycleStr(cp.getComboPlanType()+"#"+cp.getComboPlanCycle()+"#"+cp.getComboPlanDate());
			}
			comboPlanList.add(cpo);
		}
		resVo.setComboPlanList(comboPlanList);
		return resVo;
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

    public Combo mergeCombo(Combo detachedInstance){
        log.debug("merging Combo instance");
        try{
        	Combo result = (Combo) commonDao
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

	public Combo findOneComboById(Integer id) {
		log.debug("getting Combo instance with id: " + id);
        try{

            return (Combo) commonDao.findById(
            		Combo.class,id);
        }catch(RuntimeException re){
            log.error("get failed",re);
            throw re;
        }
	}

	public int deleteComboGoodsByComboId(Integer id) {
		log.debug("delete ComboGoods ComboId is "
                + id);
        try{
            String queryString = "delete from ComboGoods where comboId= '"+id+"'";
            return commonDao.executeUpdateOrDelete(queryString);
        }catch(RuntimeException re){
            log.error("find by property name failed",re);
            throw re;
        }
		
	}

	public int deleteComboPlanByComboId(Integer id) {
		log.debug("delete ComboPlan ComboId is "
                + id);
        try{
            String queryString = "delete from ComboPlan where comboId= '"+id+"'";
            return commonDao.executeUpdateOrDelete(queryString);
        }catch(RuntimeException re){
            log.error("find by property name failed",re);
            throw re;
        }
	}

	public ComboUser findComboUserByComboId(Integer id) {
		log.debug("getting ComboUser instance with id: " + id);
        try{

            return (ComboUser) commonDao.findById(
            		ComboUser.class,id);
        }catch(RuntimeException re){
            log.error("get failed",re);
            throw re;
        }
	}

	public List<ComboUserVO> findComboUsesrByPage(ComboUserVO comboUserVo,
			int pageNum, int numPerPage, String orderField,
			String direction) {
		String hql = "from FrontUser fu, ComboUser cu,Combo c where cu.userId=fu.id and cu.comboId=c.id ";
		//查询条件有 用户姓名，套餐名称，时间
		if (comboUserVo!=null && comboUserVo.getCombo() != null) {
			if (!StringHelper.isNull(comboUserVo.getCombo().getComboName())) {
				hql = hql + " and c.comboName like '%"
						+ comboUserVo.getCombo().getComboName() + "%'";
			}
		}
		if (comboUserVo!=null && comboUserVo.getFrontUser() != null) {
			if (!StringHelper.isNull(comboUserVo.getFrontUser().getUserName())) {
				hql = hql + " and fu.userName like '%"
						+ comboUserVo.getFrontUser().getUserName() + "%'";
			}
		}
		if (comboUserVo!=null && comboUserVo.getComboUser() != null) {
			if (!StringHelper.isNull(comboUserVo.getComboStartTime())) {
				hql = hql + " and cu.comboStartTime >='"
						+ comboUserVo.getComboStartTime() + "'";
			}
		}
		if (comboUserVo!=null && comboUserVo.getComboUser() != null) {
			if (!StringHelper.isNull(comboUserVo.getComboEndTime())) {
				hql = hql + " and cu.comboEndTime <='"
						+ comboUserVo.getComboEndTime() + "'";
			}
		}
		if (!StringHelper.isNull(orderField) && !StringHelper.isNull(direction)) {
			hql = hql + " order by " + orderField + " " + direction;
		} else {
			hql += " order by c.id desc";
		}
		List l = commonDao.getEntitiesByPropertiesWithHql(hql, pageNum,
				numPerPage);
		return l;
	}

	public int getComboUserCount(ComboUserVO comboUserVo) {
		String hql = "select count(*) from FrontUser fu, ComboUser cu,Combo c where cu.userId=fu.id and cu.comboId=c.id ";
		//查询条件有 用户姓名，套餐名称，时间
		if (comboUserVo!=null && comboUserVo.getCombo() != null) {
			if (!StringHelper.isNull(comboUserVo.getCombo().getComboName())) {
				hql = hql + " and c.comboName like '%"
						+ comboUserVo.getCombo().getComboName() + "%'";
			}
		}
		if (comboUserVo!=null && comboUserVo.getFrontUser() != null) {
			if (!StringHelper.isNull(comboUserVo.getFrontUser().getUserName())) {
				hql = hql + " and fu.userName like '%"
						+ comboUserVo.getFrontUser().getUserName() + "%'";
			}
		}
		if (comboUserVo!=null && comboUserVo.getComboUser() != null) {
			if (!StringHelper.isNull(comboUserVo.getComboStartTime())) {
				hql = hql + " and cu.comboStartTime >='"
						+ comboUserVo.getComboStartTime() + "'";
			}
		}
		if (comboUserVo!=null && comboUserVo.getComboUser() != null) {
			if (!StringHelper.isNull(comboUserVo.getComboEndTime())) {
				hql = hql + " and cu.comboEndTime <='"
						+ comboUserVo.getComboEndTime() + "'";
			}
		}
		return this.commonDao.getNumberOfEntitiesWithHql(hql);
	}
	
}