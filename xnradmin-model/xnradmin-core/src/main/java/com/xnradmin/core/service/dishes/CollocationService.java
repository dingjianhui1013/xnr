/**
 *2012-5-11 上午9:00:02
 */
package com.xnradmin.core.service.dishes;

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
import com.xnradmin.core.dao.dishes.CollocationDAO;
import com.xnradmin.constant.ViewConstant;
import com.xnradmin.po.common.status.Status;
import com.xnradmin.po.dishes.Collocation;
import com.xnradmin.po.dishes.Dish;
import com.xnradmin.po.dishes.RawMaterial;
import com.xnradmin.vo.dishes.DishesVO;

/**
 * @autohr: xiang_liu
 * 
 */
@Service("CollocationService")
public class CollocationService {

	@Autowired
	private CollocationDAO dao;
	
	@Autowired
    private CommonDAO commonDao;

	@Autowired
	private StaffService staffService;
	
	@Autowired
	private StatusService statusService;
	
	static Log log = LogFactory.getLog(CollocationService.class);

	/**
	 * 
	 * @param po
	 * @return int
	 */
	public int save(Collocation po) {
		if (this.dao.findByOlay(po.getDishId().toString(), po.getRawMaterialId().toString() , "").size() > 0) {
			return 1;
		}
		dao.save(po);
		return 0;
	}

	public Collocation findByid(String id) {
		return dao.findById(Long.valueOf(id));
	}

	/**	  
	 * @param po
	 * @return int
	 */
	public int modify(Collocation po) {
		if(po.getId()!=null && this.dao.findByOlay(po.getDishId().toString(), po.getRawMaterialId().toString() , "").size() >= 0){
			Collocation old = new Collocation();
			old = findByid(po.getId().toString());
			if(po.getDishId()==null){
				po.setDishId(old.getDishId());
			}
			if(po.getRawMaterialId()==null){
				po.setRawMaterialId(old.getRawMaterialId());
			}
			if(po.getWeightId()==null){
				po.setWeightId(old.getWeightId());
			}
			if(po.getNumber()==null){
				po.setNumber(old.getNumber());
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
		Collocation po = this.dao.findById(Long.valueOf(id));
        this.dao.delete(po);
    }
	
	public int removeCollocationId(String id) {
		return dao.removeCollocationId(Long.valueOf(id));
	}
	
	
	/**
	 * @param brandname
	 * @return int
	 */
	public int getCount(String dishId, String rawMaterialId, 
			String weightId, String number,
			String createStartTime, String createEndTime, String createStaffId,
			String modifyStartTime, String modifyEndTime, String modifyStaffId) {
		String hql = "select count(*) from Collocation a, Dish b, RawMaterial c where "
				+ " a.dishId = b.id and a.rawMaterialId=c.id";
		if (!StringHelper.isNull(dishId)) {
			hql = hql + " and a.dishId = "+ dishId ;
		}
		if (!StringHelper.isNull(rawMaterialId)) {
			hql = hql + " and a.rawMaterialId = "+ rawMaterialId ;
		}
		if (!StringHelper.isNull(weightId)) {
			hql = hql + " and a.weightId = "+ weightId ;
		}
		if (!StringHelper.isNull(number)) {
			hql = hql + " and a.number = "+ number ;
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
	public List<DishesVO> listVO(String dishId, String rawMaterialId, 
			String weightId, String number,
			String createStartTime, String createEndTime, String createStaffId,
			String modifyStartTime, String modifyEndTime, String modifyStaffId,
			int curPage, int pageSize, String orderField, String direction) {
		String hql = "from Collocation a, Dish b, RawMaterial c where "
				+ " a.dishId = b.id and a.rawMaterialId=c.id";
		if (!StringHelper.isNull(dishId)) {
			hql = hql + " and a.dishId = "+ dishId ;
		}
		if (!StringHelper.isNull(rawMaterialId)) {
			hql = hql + " and a.rawMaterialId = "+ rawMaterialId ;
		}
		if (!StringHelper.isNull(weightId)) {
			hql = hql + " and a.weightId = "+ weightId ;
		}
		if (!StringHelper.isNull(number)) {
			hql = hql + " and a.number = "+ number ;
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
			hql +=" order by a.id desc";
		}
		List l = commonDao.getEntitiesByPropertiesWithHql(hql, curPage, pageSize); // .findByQuerySplitPage(hql,curPage,pageSize);
		List<DishesVO> resList = new ArrayList<DishesVO>();
		for (int i = 0; i < l.size(); i++) {
			Object[] obj = (Object[]) l.get(i);
			Collocation coll = (Collocation)obj[0];
			Dish dish = (Dish)obj[1];
			RawMaterial rawMaterial = (RawMaterial)obj[2];
			DishesVO vo = new DishesVO();
			if(coll.getId()!=null){
				vo.setCollocationId(coll.getId().toString());
			}
			if(coll.getDishId()!=null){
				vo.setDishId(coll.getDishId().toString());
			}
			if(coll.getRawMaterialId()!=null){
				vo.setRawMaterialId(coll.getRawMaterialId().toString());
			}
			if(coll.getWeightId()!=null){
				vo.setWeightId(coll.getWeightId().toString());
				Status status = statusService.findByid(coll.getWeightId().toString());
				vo.setStatus(status);
			}
			if(coll.getCreateTime()!=null){
				vo.setCreateTime(StringHelper.getSystime(
                    ViewConstant.PAGE_DATE_FORMAT_SEC,coll.getCreateTime().getTime()));
			}
			if(coll.getCreateStaffId()!=null){
				vo.setCreateStaffId(coll.getCreateStaffId().toString());
				CommonStaff cs = staffService.findByid(coll.getCreateStaffId().toString());
				vo.setCreateStaffName(cs.getStaffName());
			}
			if(coll.getModifyTime()!=null){
				vo.setModifyTime(StringHelper.getSystime(
	                    ViewConstant.PAGE_DATE_FORMAT_SEC,coll.getModifyTime().getTime()));
			}
			if(coll.getModifyStaffId()!=null){
				vo.setModifyStaffId(coll.getModifyStaffId().toString());
				CommonStaff cs = staffService.findByid(coll.getModifyStaffId().toString());
				vo.setModifyStaffName(cs.getStaffName());
			}
			vo.setDishName(dish.getDishName());
			vo.setRawMaterial(rawMaterial);
			vo.setNumber(coll.getNumber().toString());
			resList.add(vo);
		}
		return resList;
	}

	/**
	 * 
	 * @param dishId
	 * @return int
	 */
	public int removeDishId(Integer dishId){

        log.debug("removeDishId: " + dishId);
        try{
            String queryString = "delete from Collocation as model where model.dishId = "
                    + dishId;
            return commonDao.executeUpdateOrDelete(queryString);
        }catch(RuntimeException re){
            log.error("removeDishId failed",re);
            throw re;
        }
    }
	/**
	 * @return List<PayUpmp>
	 */
	public List<Collocation> listAll() {
		return dao.findAll();
	}

}
