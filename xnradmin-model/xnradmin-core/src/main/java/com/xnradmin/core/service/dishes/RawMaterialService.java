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
import com.xnradmin.core.dao.CommonDAO;
import com.xnradmin.core.dao.dishes.RawMaterialDAO;
import com.xnradmin.constant.ViewConstant;
import com.xnradmin.po.dishes.RawMaterial;
import com.xnradmin.vo.dishes.DishesVO;

/**
 * @autohr: xiang_liu
 * 
 */
@Service("RawMaterialService")
public class RawMaterialService {

	@Autowired
	private RawMaterialDAO dao;
	
	@Autowired
    private CommonDAO commonDao;

	static Log log = LogFactory.getLog(RawMaterialService.class);

	/**
	 * 
	 * @param po
	 * @return int
	 */
	public int save(RawMaterial po) {
		if (this.dao.findByOlay(po.getMaterialName(),"").size() > 0) {
			return 1;
		}
		dao.save(po);
		return 0;
	}

	public RawMaterial findByid(String id) {
		return dao.findById(Integer.valueOf(id));
	}

	/**	  
	 * @param po
	 * @return int
	 */
	public int modify(RawMaterial po) {
		if(po.getId()!=null && this.dao.findByOlay(po.getMaterialName(),po.getId().toString()).size() <= 0){
			this.dao.merge(po);
		}else{
			return 1;
		}
		return 0;
	}

	public void del(String id){
		RawMaterial po = this.dao.findById(Integer.valueOf(id));
        this.dao.delete(po);
    }
	
	public int removeRawMaterialId(String id) {
		return dao.removeRawMaterialId(Long.valueOf(id));
	}
	
	
	/**
	 * @param materialName
	 * @param materialTypeId
	 * @return int
	 */
	public int getCount(String materialName, String materialTypeId, 
			String createStartTime, String createEndTime, String createStaffId,
			String modifyStartTime, String modifyEndTime, String modifyStaffId) {
		String hql = "select count(*) from RawMaterial where 1=1";
		if (!StringHelper.isNull(materialName)) {
			hql = hql + " and materialName = '"+ materialName +"'" ;
		}
		if (!StringHelper.isNull(materialTypeId)) {
			hql = hql + " and materialTypeId = "+ materialTypeId ;
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
	public List<DishesVO> listVO(String materialName, String materialTypeId, 
			String createStartTime, String createEndTime, String createStaffId,
			String modifyStartTime, String modifyEndTime, String modifyStaffId,
			int curPage, int pageSize, String orderField, String direction) {
		String hql = "from RawMaterial where 1=1";
		if (!StringHelper.isNull(materialName)) {
			hql = hql + " and materialName = '"+ materialName +"'";
		}
		if (!StringHelper.isNull(materialTypeId)) {
			hql = hql + " and materialTypeId = "+ materialTypeId ;
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
			RawMaterial po = (RawMaterial) l.get(i);
			DishesVO vo = new DishesVO();
			if(po.getId()!=null){
				vo.setRawMaterialId(po.getId().toString());
			}
			vo.setMaterialName(po.getMaterialName());
			if(po.getMaterialTypeId()!=null){
				vo.setMaterialTypeId(po.getMaterialTypeId().toString());
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
	public List<RawMaterial> listAll() {
		return dao.findAll();
	}

}
