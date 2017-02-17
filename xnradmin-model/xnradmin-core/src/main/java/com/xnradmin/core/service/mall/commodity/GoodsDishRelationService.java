/**
 *2012-5-11 上午9:00:02
 */
package com.xnradmin.core.service.mall.commodity;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cntinker.util.StringHelper;
import com.xnradmin.core.service.StaffService;
import com.xnradmin.core.service.common.status.StatusService;
import com.xnradmin.po.CommonStaff;
import com.xnradmin.core.dao.CommonDAO;
import com.xnradmin.core.dao.mall.commodity.GoodsDishRelationDAO;
import com.xnradmin.constant.ViewConstant;
import com.xnradmin.po.mall.commodity.Goods;
import com.xnradmin.po.mall.commodity.GoodsDishRelation;
import com.xnradmin.po.dishes.Dish;
import com.xnradmin.vo.mall.CommodityVO;

/**
 * @autohr: xiang_liu
 * 
 */
@Service("GoodsDishRelationService")
public class GoodsDishRelationService {

	@Autowired
	private GoodsDishRelationDAO dao;
	
	@Autowired
    private CommonDAO commonDao;

	@Autowired
	private StaffService staffService;
	
	@Autowired
	private StatusService statusService;
	
	static Log log = LogFactory.getLog(GoodsDishRelationService.class);

	/**
	 * 
	 * @param po
	 * @return int
	 */
	public int save(GoodsDishRelation po) {
		if (this.dao.findByOlay(po.getGoodsId().toString(), po.getDishId().toString() , "").size() > 0) {
			return 1;
		}
		dao.save(po);
		return 0;
	}

	public GoodsDishRelation findByid(String id) {
		return dao.findById(Long.valueOf(id));
	}

	/**	  
	 * @param po
	 * @return int
	 */
	public int modify(GoodsDishRelation po) {
		if(po.getId()!=null && this.dao.findByOlay(po.getGoodsId().toString(), po.getDishId().toString() , "").size() >= 0){
			GoodsDishRelation old = new GoodsDishRelation();
			old = findByid(po.getId().toString());
			if(po.getGoodsId()==null){
				po.setGoodsId(old.getGoodsId());
			}
			if(po.getDishId()==null){
				po.setDishId(old.getDishId());
			}
			if(po.getSortId()==null){
				po.setSortId(old.getSortId());
			}
			if(po.getDishCount()==null){
				po.setDishCount(old.getDishCount());
			}
			if(po.getPrimaryConfigurationId()==null){
				po.setPrimaryConfigurationId(old.getPrimaryConfigurationId());
			}
			if(StringHelper.isNull(po.getStaffId())){
				po.setStaffId(old.getStaffId());
			}
			if(po.getModifyStaffId()==null){
				po.setModifyStaffId(old.getModifyStaffId());
			}
			po.setModifyTime(new Timestamp(System.currentTimeMillis()));
			this.dao.merge(po);
		}else{
			return 1;
		}
		return 0;
	}

	public void del(String id){
		GoodsDishRelation po = this.dao.findById(Long.valueOf(id));
        this.dao.delete(po);
    }
	
	public int removeGoodsDishRelationId(String id) {
		return dao.removeGoodsDishRelationId(Long.valueOf(id));
	}
	
	
	/**
	 * @param brandname
	 * @return int
	 */
	public int getCount(String goodsId, String dishId, String sortId, 
			String dishCount, String primaryConfigurationId, String staffId,
			String createStartTime, String createEndTime, String createStaffId,
			String modifyStartTime, String modifyEndTime, String modifyStaffId) {
		String hql = "select count(*) from GoodsDishRelation a, Goods b, Dish c where "
				+ " a.goodsId = b.id and a.dishId=c.id";
		if (!StringHelper.isNull(goodsId)) {
			hql = hql + " and a.goodsId = "+ goodsId ;
		}
		if (!StringHelper.isNull(dishId)) {
			hql = hql + " and a.dishId = "+ dishId ;
		}
		if (!StringHelper.isNull(sortId)) {
			hql = hql + " and a.sortId = "+ sortId ;
		}
		if (!StringHelper.isNull(dishCount)) {
			hql = hql + " and a.dishCount = "+ dishCount ;
		}
		if (!StringHelper.isNull(primaryConfigurationId)) {
			hql = hql + " and a.primaryConfigurationId = "+ primaryConfigurationId ;
		}
		if (!StringHelper.isNull(staffId)) {
			hql = hql + " and a.staffId = '" + staffId + "'";
		}
		if (!StringHelper.isNull(createStartTime)) {
			hql = hql + " and a.createTime >='"+ createStartTime +"'" ;
		}
		if (!StringHelper.isNull(createEndTime)) {
			hql = hql + " and a.createTime <='"+ createEndTime +"'" ;
		}
		if (!StringHelper.isNull(createStaffId)) {
			hql = hql + " and a.createStaffId >='"+ createStaffId +"'" ;
		}
		if (!StringHelper.isNull(modifyStartTime)) {
			hql = hql + " and a.modifyTime <='"+ modifyStartTime +"'" ;
		}
		if (!StringHelper.isNull(modifyEndTime)) {
			hql = hql + " and a.modifyTime >='"+ modifyEndTime +"'" ;
		}
		if (!StringHelper.isNull(modifyStaffId)) {
			hql = hql + " and a.modifyStaffId <='"+ modifyStaffId +"'" ;
		}
		return this.commonDao.getNumberOfEntitiesWithHql(hql);
	}
	
	/**
	 * 
	 * @param firstletter
	 * @param curPage
	 * @param pageSize
	 * @param orderField
	 * @param direction
	 * @return List<GetOrgListVO>
	 */
	public List<CommodityVO> listVO(String goodsId, String dishId, String sortId, 
			String dishCount, String primaryConfigurationId, String staffId,
			String createStartTime, String createEndTime, String createStaffId,
			String modifyStartTime, String modifyEndTime, String modifyStaffId,
			int curPage, int pageSize, String orderField, String direction) {
		String hql = "from GoodsDishRelation a, Goods b, Dish c where "
				+ " a.goodsId = b.id and a.dishId=c.id";
		if (!StringHelper.isNull(goodsId)) {
			hql = hql + " and a.goodsId = "+ goodsId ;
		}
		if (!StringHelper.isNull(dishId)) {
			hql = hql + " and a.dishId = "+ dishId ;
		}
		if (!StringHelper.isNull(sortId)) {
			hql = hql + " and a.sortId = "+ sortId ;
		}
		if (!StringHelper.isNull(dishCount)) {
			hql = hql + " and a.dishCount = "+ dishCount ;
		}
		if (!StringHelper.isNull(primaryConfigurationId)) {
			hql = hql + " and a.primaryConfigurationId = "+ primaryConfigurationId ;
		}
		if (!StringHelper.isNull(staffId)) {
			hql = hql + " and a.staffId = '" + staffId + "'";
		}
		if (!StringHelper.isNull(createStartTime)) {
			hql = hql + " and a.createTime >='"+ createStartTime +"'" ;
		}
		if (!StringHelper.isNull(createEndTime)) {
			hql = hql + " and a.createTime <='"+ createEndTime +"'" ;
		}
		if (!StringHelper.isNull(createStaffId)) {
			hql = hql + " and a.createStaffId >='"+ createStaffId +"'" ;
		}
		if (!StringHelper.isNull(modifyStartTime)) {
			hql = hql + " and a.modifyTime <='"+ modifyStartTime +"'" ;
		}
		if (!StringHelper.isNull(modifyEndTime)) {
			hql = hql + " and a.modifyTime >='"+ modifyEndTime +"'" ;
		}
		if (!StringHelper.isNull(modifyStaffId)) {
			hql = hql + " and a.modifyStaffId <='"+ modifyStaffId +"'" ;
		}
		if (!StringHelper.isNull(orderField) && !StringHelper.isNull(direction)) {
			hql = hql + " order by " + orderField + " " + direction;
		}else{
			hql +=" order by a.sortId";
		}
		List l = commonDao.getEntitiesByPropertiesWithHql(hql, curPage, pageSize); // .findByQuerySplitPage(hql,curPage,pageSize);
		List<CommodityVO> resList = new ArrayList<CommodityVO>();
		for (int i = 0; i < l.size(); i++) {
			Object[] obj = (Object[]) l.get(i);
			GoodsDishRelation goodsDishRelation = (GoodsDishRelation)obj[0];
			Goods goods = (Goods)obj[1];
			Dish dish = (Dish)obj[2];
			CommodityVO vo = new CommodityVO();
			if(goodsDishRelation.getId()!=null){
				vo.setGoodsDishrelationDishId(goodsDishRelation.getId().toString());
			}
			if(goodsDishRelation.getSortId()!=null){
				vo.setGoodsDishrelationSortId(goodsDishRelation.getSortId().toString());
			}
			if(goodsDishRelation.getGoodsId()!=null){
				vo.setGoodsDishrelationGoodsId(goodsDishRelation.getGoodsId().toString());
			}
			if(goodsDishRelation.getDishId()!=null){
				vo.setGoodsDishrelationDishId(goodsDishRelation.getDishId().toString());
			}
			if(goodsDishRelation.getDishCount()!=null){
				vo.setGoodsDishrelationDishCount(goodsDishRelation.getDishCount().toString());
			}
			if(goodsDishRelation.getPrimaryConfigurationId()!=null){
				vo.setGoodsDishrelationpPrimaryConfigurationId(goodsDishRelation.getPrimaryConfigurationId().toString());
			}
			if(goodsDishRelation.getStaffId()!=null){
				vo.setGoodsDishrelationStaffId(goodsDishRelation.getStaffId().toString());
			}
			if(goodsDishRelation.getCreateTime()!=null){
				vo.setGoodsDishrelationCreateTime(StringHelper.getSystime(
                    ViewConstant.PAGE_DATE_FORMAT_SEC,goodsDishRelation.getCreateTime().getTime()));
			}
			if(goodsDishRelation.getCreateStaffId()!=null){
				vo.setGoodsDishrelationCreateStaffId(goodsDishRelation.getCreateStaffId().toString());
				CommonStaff cs = staffService.findByid(goodsDishRelation.getCreateStaffId().toString());
				vo.setGoodsDishrelationCreateStaffName(cs.getStaffName());
			}
			if(goodsDishRelation.getModifyTime()!=null){
				vo.setGoodsDishrelationModifyTime(StringHelper.getSystime(
	                    ViewConstant.PAGE_DATE_FORMAT_SEC,goodsDishRelation.getModifyTime().getTime()));
			}
			if(goodsDishRelation.getModifyStaffId()!=null){
				vo.setGoodsDishrelationModifyStaffId(goodsDishRelation.getModifyStaffId().toString());
				CommonStaff cs = staffService.findByid(goodsDishRelation.getModifyStaffId().toString());
				vo.setGoodsDishrelationModifyStaffName(cs.getStaffName());
			}
			vo.setGoodsName(goods.getGoodsName());
			vo.setDish(dish);
			resList.add(vo);
		}
		return resList;
	}

	/**
	 * 
	 * @param dishId
	 * @return int
	 */
	public int removeGoodsId(Integer goodsId){

        log.debug("removeGoodsId: " + goodsId);
        try{
            String queryString = "delete from GoodsDishRelation as model where model.goodsId = "
                    + goodsId;
            return commonDao.executeUpdateOrDelete(queryString);
        }catch(RuntimeException re){
            log.error("removeDishId failed",re);
            throw re;
        }
    }
	/**
	 * @return List<PayUpmp>
	 */
	public List<GoodsDishRelation> listAll() {
		return dao.findAll();
	}

}
