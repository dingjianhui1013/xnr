/**
 *2012-5-11 上午9:00:02
 */
package com.xnradmin.core.service.dishes;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cntinker.util.StringHelper;
import com.xnradmin.core.dao.CommonDAO;
import com.xnradmin.core.dao.dishes.DishDAO;
import com.xnradmin.constant.ViewConstant;
import com.xnradmin.po.dishes.Dish;
import com.xnradmin.vo.dishes.DishesVO;

/**
 * @autohr: xiang_liu
 * 
 */
@Service("DishService")
public class DishService {

	@Autowired
	private DishDAO dao;
	
	@Autowired
    private CommonDAO commonDao;

	static Log log = LogFactory.getLog(DishService.class);

	/**
	 * 
	 * @param po
	 * @return int
	 */
	public int save(Dish po) {
		Serializable cls = dao.save(po);
		return Integer.parseInt((cls.toString()));
	}

	public Dish findByid(String id) {
		return dao.findById(Integer.valueOf(id));
	}

	public int findByDishName(String dishName, String id) {
		return dao.findByOlay(dishName, id).size();
	}
	/**	  
	 * @param po
	 * @return int
	 */
	public int modify(Dish po) {
		if(po.getId()!=null && this.dao.findByOlay(po.getDishName(), po.getId().toString()).size() <= 0){
			Dish old = new Dish();
			old = findByid(po.getId().toString());
			if(StringHelper.isNull(po.getDishName())){
				po.setDishName(old.getDishName());
			}
			if(StringHelper.isNull(po.getIntroduction())){
				po.setIntroduction(old.getIntroduction());
			}
			if(StringHelper.isNull(po.getCookingMethod())){
				po.setCookingMethod(old.getCookingMethod());
			}
			if(StringHelper.isNull(po.getPicUrl())){
				po.setPicUrl(old.getPicUrl());
			}
			if(po.getDishTypeId()==null){
				po.setDishTypeId(old.getDishTypeId());
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
		Dish po = this.dao.findById(Integer.valueOf(id));
        this.dao.delete(po);
    }
	
	public int removeDishId(String id) {
		return dao.removeDishId(Integer.valueOf(id));
	}
	
	
	/**
	 * @param brandname
	 * @return int
	 */
	public int getCount(String dishName, String introduction, 
			String cookingMethod, String picUrl, String dishTypeId, 
			String createStartTime, String createEndTime, String createStaffId,
			String modifyStartTime, String modifyEndTime, String modifyStaffId) {
		String hql = "select count(*) from Dish where 1=1";
		if (!StringHelper.isNull(dishName)) {
			hql = hql + " and dishName = '"+ dishName +"'";
		}
		if (!StringHelper.isNull(introduction)) {
			hql = hql + " and introduction like '%"+ introduction +"%'";
		}
		if (!StringHelper.isNull(cookingMethod)) {
			hql = hql + " and cookingMethod like '%"+ cookingMethod +"%'";
		}
		if (!StringHelper.isNull(picUrl)) {
			hql = hql + " and picUrl like '%"+ picUrl +"%'";
		}
		if (!StringHelper.isNull(dishTypeId)) {
			hql = hql + " and dishTypeId = "+ dishTypeId ;
		}
		if (!StringHelper.isNull(createStartTime)) {
			hql = hql + " and createTime >='"+ createStartTime +"'" ;
		}
		if (!StringHelper.isNull(createEndTime)) {
			hql = hql + " and createTime <='"+ createEndTime +"'" ;
		}
		if (!StringHelper.isNull(createStaffId)) {
			hql = hql + " and createStaffId >='"+ createStaffId +"'" ;
		}
		if (!StringHelper.isNull(modifyStartTime)) {
			hql = hql + " and modifyTime <='"+ modifyStartTime +"'" ;
		}
		if (!StringHelper.isNull(modifyEndTime)) {
			hql = hql + " and modifyTime >='"+ modifyEndTime +"'" ;
		}
		if (!StringHelper.isNull(modifyStaffId)) {
			hql = hql + " and modifyStaffId <='"+ modifyStaffId +"'" ;
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
	public List<DishesVO> listVO(String dishName, String introduction, 
			String cookingMethod, String picUrl, String dishTypeId, 
			String createStartTime, String createEndTime, String createStaffId,
			String modifyStartTime, String modifyEndTime, String modifyStaffId,
			int curPage, int pageSize, String orderField, String direction) {
		String hql = "from Dish where 1=1";
		if (!StringHelper.isNull(dishName)) {
			hql = hql + " and dishName = '"+ dishName +"'";
		}
		if (!StringHelper.isNull(introduction)) {
			hql = hql + " and introduction like '%"+ introduction +"%'";
		}
		if (!StringHelper.isNull(cookingMethod)) {
			hql = hql + " and cookingMethod like '%"+ cookingMethod +"%'";
		}
		if (!StringHelper.isNull(picUrl)) {
			hql = hql + " and picUrl like '%"+ picUrl +"%'";
		}
		if (!StringHelper.isNull(dishTypeId)) {
			hql = hql + " and dishTypeId = "+ dishTypeId ;
		}
		if (!StringHelper.isNull(createStartTime)) {
			hql = hql + " and createTime >='"+ createStartTime +"'" ;
		}
		if (!StringHelper.isNull(createEndTime)) {
			hql = hql + " and createTime <='"+ createEndTime +"'" ;
		}
		if (!StringHelper.isNull(createStaffId)) {
			hql = hql + " and createStaffId >='"+ createStaffId +"'" ;
		}
		if (!StringHelper.isNull(modifyStartTime)) {
			hql = hql + " and modifyTime <='"+ modifyStartTime +"'" ;
		}
		if (!StringHelper.isNull(modifyEndTime)) {
			hql = hql + " and modifyTime >='"+ modifyEndTime +"'" ;
		}
		if (!StringHelper.isNull(modifyStaffId)) {
			hql = hql + " and modifyStaffId <='"+ modifyStaffId +"'" ;
		}
		if (!StringHelper.isNull(orderField) && !StringHelper.isNull(direction)) {
			hql = hql + " order by " + orderField + " " + direction;
		}else{
			hql +=" order by id desc";
		}
		List l = commonDao.getEntitiesByPropertiesWithHql(hql, curPage, pageSize); // .findByQuerySplitPage(hql,curPage,pageSize);
		List<DishesVO> resList = new ArrayList<DishesVO>();
		for (int i = 0; i < l.size(); i++) {
			Dish po = (Dish) l.get(i);
			DishesVO vo = new DishesVO();
			if(po.getId()!=null){
				vo.setDishId(po.getId().toString());
			}
			vo.setDishName(po.getDishName());
			vo.setIntroduction(po.getIntroduction());
			vo.setCookingMethod(po.getCookingMethod());
			vo.setPicUrl(po.getPicUrl());
			if(po.getDishTypeId()!=null){
				vo.setDishTypeId(po.getDishTypeId().toString());
			}
			if(po.getCreateTime()!=null){
				vo.setCreateTime(StringHelper.getSystime(
                    ViewConstant.PAGE_DATE_FORMAT_SEC,po.getCreateTime().getTime()));
			}
			if(po.getCreateStaffId()!=null){
				vo.setCreateStaffId(po.getCreateStaffId().toString());
			}
			if(po.getModifyTime()!=null){
				vo.setModifyTime(StringHelper.getSystime(
	                    ViewConstant.PAGE_DATE_FORMAT_SEC,po.getModifyTime().getTime()));
			}
			if(po.getModifyStaffId()!=null){
				vo.setModifyStaffId(po.getModifyStaffId().toString());
			}
			resList.add(vo);
		}
		return resList;
	}

	/**
	 * @return List<PayUpmp>
	 */
	public List<Dish> listAll() {
		return dao.findAll();
	}

}
