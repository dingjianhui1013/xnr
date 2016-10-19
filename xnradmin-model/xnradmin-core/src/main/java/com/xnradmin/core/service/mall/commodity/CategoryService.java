/**
 *2012-5-11 上午9:00:02
 */
package com.xnradmin.core.service.mall.commodity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cntinker.util.StringHelper;
import com.xnradmin.core.dao.CommonDAO;
import com.xnradmin.core.dao.mall.commodity.CategoryDAO;
import com.xnradmin.constant.ViewConstant;
import com.xnradmin.po.mall.commodity.Category;
import com.xnradmin.vo.mall.CommodityVO;

/**
 * @autohr: xiang_liu
 * 
 */
@Service("CategoryService")
public class CategoryService {

	@Autowired
	private CategoryDAO dao;

	@Autowired
	private CommonDAO commonDao;

	static Log log = LogFactory.getLog(CategoryService.class);

	/**
	 * 
	 * @param po
	 * @return int
	 */
	public int save(Category po) {
		Serializable cls = dao.save(po);
		return Integer.parseInt((cls.toString()));
	}

	public Category findByid(String id) {
		return dao.findById(Integer.valueOf(id));
	}

	/**
	 * @param po
	 * @return int
	 */
	public int modify(Category po) {
		Category old = new Category();
		old = findByid(po.getId().toString());
		if (StringHelper.isNull(po.getCategoryName())) {
			po.setCategoryName(old.getCategoryName());
		}
		if (po.getSortId()==null) {
			po.setSortId(old.getSortId());
		}
		if (po.getCategoryParentId()==null) {
			po.setCategoryParentId(old.getCategoryParentId());
		}
		if (po.getCategoryLevel()==null) {
			po.setCategoryLevel(old.getCategoryLevel());
		}
		if (StringHelper.isNull(po.getCategoryLogo())) {
			po.setCategoryLogo(old.getCategoryLogo());
		}
		if (StringHelper.isNull(po.getCategoryHeadLogo())) {
			po.setCategoryHeadLogo(old.getCategoryHeadLogo());
		}
		if (StringHelper.isNull(po.getCategoryDescription())) {
			po.setCategoryDescription(old.getCategoryDescription());
		}
		if (po.getCategoryStatus()==null) {
			po.setCategoryStatus(old.getCategoryStatus());
		}
		if (po.getPrimaryConfigurationId() == null) {
			po.setPrimaryConfigurationId(old.getPrimaryConfigurationId());
		}
		if (po.getStaffId() == null) {
			po.setStaffId(old.getStaffId());
		}
		if (po.getModifyStaffId() == null) {
			po.setModifyStaffId(old.getModifyStaffId());
		}
		po.setModifyTime(new Timestamp(System.currentTimeMillis()));
		this.dao.merge(po);
		return 0;
	}

	public void del(String id) {
		Category po = this.dao.findById(Integer.valueOf(id));
		this.dao.delete(po);
	}

	public int removeCategoryId(String id) {
		return dao.removeCategoryId(Integer.valueOf(id));
	}

	/**
	 * @param brandname
	 * @return int
	 */
	public int getCount(String categoryParentId, String categoryName, String categoryLevel,
			String categoryStatus, String primaryConfigurationId, String staffId,
			String createStartTime, String createEndTime, String createStaffId,
			String modifyStartTime, String modifyEndTime, String modifyStaffId) {
		String hql = "select count(*) from Category where 1=1";
		if (!StringHelper.isNull(staffId)) {
			hql = hql + " and staffId = '" + staffId + "'";
		}
		if (!StringHelper.isNull(categoryName)) {
			hql = hql + " and categoryName like '%" + categoryName + "%'";
		}
		if (!StringHelper.isNull(categoryParentId)) {
			hql = hql + " and categoryParentId = " + categoryParentId;
		}
		if (!StringHelper.isNull(categoryLevel)) {
			hql = hql + " and categoryLevel = " + categoryLevel;
		}
		if (!StringHelper.isNull(categoryStatus)) {
			hql = hql + " and categoryStatus = " + categoryStatus;
		}
		if (!StringHelper.isNull(primaryConfigurationId)) {
			hql = hql + " and primaryConfigurationId = " + primaryConfigurationId;
		}
		if (!StringHelper.isNull(createStartTime)) {
			hql = hql + " and createTime >='" + createStartTime + "'";
		}
		if (!StringHelper.isNull(createEndTime)) {
			hql = hql + " and createTime <='" + createEndTime + "'";
		}
		if (!StringHelper.isNull(createStaffId)) {
			hql = hql + " and createStaffId >='" + createStaffId + "'";
		}
		if (!StringHelper.isNull(modifyStartTime)) {
			hql = hql + " and modifyTime <='" + modifyStartTime + "'";
		}
		if (!StringHelper.isNull(modifyEndTime)) {
			hql = hql + " and modifyTime >='" + modifyEndTime + "'";
		}
		if (!StringHelper.isNull(modifyStaffId)) {
			hql = hql + " and modifyStaffId <='" + modifyStaffId + "'";
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
	public List<CommodityVO> listVO (String categoryParentId, String categoryName, String categoryLevel,
					String categoryStatus, String primaryConfigurationId, String staffId,
					String createStartTime, String createEndTime, String createStaffId,
					String modifyStartTime, String modifyEndTime, String modifyStaffId, 
					int curPage, int pageSize, String orderField, String direction) {
		String hql = "from Category where 1=1";
		if (!StringHelper.isNull(staffId)) {
			hql = hql + " and staffId = '" + staffId + "'";
		}
		if (!StringHelper.isNull(categoryName)) {
			hql = hql + " and categoryName like '%" + categoryName + "%'";
		}
		if (!StringHelper.isNull(categoryParentId)) {
			hql = hql + " and categoryParentId = " + categoryParentId;
		}
		if (!StringHelper.isNull(categoryLevel)) {
			hql = hql + " and categoryLevel = " + categoryLevel;
		}
		if (!StringHelper.isNull(categoryStatus)) {
			hql = hql + " and categoryStatus = " + categoryStatus;
		}
		if (!StringHelper.isNull(primaryConfigurationId)) {
			hql = hql + " and primaryConfigurationId = " + primaryConfigurationId;
		}
		if (!StringHelper.isNull(createStartTime)) {
			hql = hql + " and createTime >='" + createStartTime + "'";
		}
		if (!StringHelper.isNull(createEndTime)) {
			hql = hql + " and createTime <='" + createEndTime + "'";
		}
		if (!StringHelper.isNull(createStaffId)) {
			hql = hql + " and createStaffId >='" + createStaffId + "'";
		}
		if (!StringHelper.isNull(modifyStartTime)) {
			hql = hql + " and modifyTime <='" + modifyStartTime + "'";
		}
		if (!StringHelper.isNull(modifyEndTime)) {
			hql = hql + " and modifyTime >='" + modifyEndTime + "'";
		}
		if (!StringHelper.isNull(modifyStaffId)) {
			hql = hql + " and modifyStaffId <='" + modifyStaffId + "'";
		}
		if (!StringHelper.isNull(orderField) && !StringHelper.isNull(direction)) {
			hql = hql + " order by " + orderField + " " + direction;
		} else {
			hql += " order by id desc";
		}
		List l = commonDao.getEntitiesByPropertiesWithHql(hql, curPage,
				pageSize);
		List<CommodityVO> resList = new ArrayList<CommodityVO>();
		for (int i = 0; i < l.size(); i++) {
			Category po = (Category) l.get(i);
			CommodityVO vo = new CommodityVO();
			if (po.getId() != null) {
				vo.setCategoryId(po.getId().toString());
			}
			if (po.getSortId() != null) {
				vo.setCategorySortId(po.getSortId().toString());
			}
			if (po.getCategoryParentId() != null) {
				vo.setCategoryParentId(po.getCategoryParentId().toString());
			}
			if (po.getCategoryLevel() != null) {
				vo.setCategoryLevel(po.getCategoryLevel().toString());
			}
			vo.setCategoryName(po.getCategoryName());
			vo.setCategoryLogo(po.getCategoryLogo());
			vo.setCategoryHeadLogo(po.getCategoryHeadLogo());
			vo.setCategoryDescription(po.getCategoryDescription());
			if (po.getCategoryStatus()!= null) {
				vo.setCategoryStatus(po.getCategoryStatus().toString());
			}
			if (po.getPrimaryConfigurationId()!= null) {
				vo.setCategoryPrimaryConfigurationId(po.getPrimaryConfigurationId().toString());
			}
			vo.setCategoryStaffId(po.getStaffId());
			if (po.getCreateTime() != null) {
				vo.setCategoryCreateTime(StringHelper.getSystime(
						ViewConstant.PAGE_DATE_FORMAT_SEC, po.getCreateTime()
								.getTime()));
			}
			if (po.getCreateStaffId() != null) {
				vo.setCategoryCreateStaffId(po.getCreateStaffId().toString());
			}
			if (po.getModifyTime() != null) {
				vo.setCategoryModifyTime(StringHelper.getSystime(
						ViewConstant.PAGE_DATE_FORMAT_SEC, po.getModifyTime()
								.getTime()));
			}
			if (po.getModifyStaffId() != null) {
				vo.setCategoryModifyStaffId(po.getModifyStaffId().toString());
			}
			resList.add(vo);
		}
		return resList;
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
	public List<Category> webIndex (String categoryParentId, String categoryName, String categoryLevel,
					String categoryStatus, String primaryConfigurationId,
					int curPage, int pageSize, String orderField, String direction) {
		String hql = "from Category where 1=1";
		if (!StringHelper.isNull(categoryName)) {
			hql = hql + " and categoryName like '%" + categoryName + "%'";
		}
		if (!StringHelper.isNull(categoryParentId)) {
			hql = hql + " and categoryParentId = " + categoryParentId;
		}
		if (!StringHelper.isNull(categoryLevel)) {
			hql = hql + " and categoryLevel = " + categoryLevel;
		}
		if (!StringHelper.isNull(categoryStatus)) {
			hql = hql + " and categoryStatus != " + categoryStatus;
		}
		if (!StringHelper.isNull(primaryConfigurationId)) {
			hql = hql + " and primaryConfigurationId = " + primaryConfigurationId;
		}
		if (!StringHelper.isNull(orderField) && !StringHelper.isNull(direction)) {
			hql = hql + " order by " + orderField + " " + direction;
		} else {
			hql += " order by id desc";
		}
		List l = commonDao.getEntitiesByPropertiesWithHql(hql, curPage,
				pageSize);
		List<Category> resList = new ArrayList<Category>();
		for (int i = 0; i < l.size(); i++) {
			Category po = (Category) l.get(i);
			resList.add(po);
		}
		return resList;
	}
	
	
	/**
	 * 生菜配送
	 * @param firstletter
	 * @param curPage
	 * @param pageSize
	 * @param orderField
	 * @param direction
	 * @return List<GetOrgListVO>
	 */
	public List<Category> webBusinessIndex (String categoryParentId, String categoryName, String categoryLevel,
					String categoryStatus, String primaryConfigurationId,
					int curPage, int pageSize, String orderField, String direction) {
		String hql = "from Category where 1=1";
		if (!StringHelper.isNull(categoryName)) {
			hql = hql + " and categoryName like '%" + categoryName + "%'";
		}
		if (!StringHelper.isNull(categoryParentId)) {
			hql = hql + " and categoryParentId in (" + categoryParentId + ")";
		}
		if (!StringHelper.isNull(categoryLevel)) {
			hql = hql + " and categoryLevel = " + categoryLevel;
		}
		if (!StringHelper.isNull(categoryStatus)) {
			hql = hql + " and categoryStatus != " + categoryStatus;
		}
		if (!StringHelper.isNull(primaryConfigurationId)) {
			hql = hql + " and primaryConfigurationId = " + primaryConfigurationId;
		}
		if (!StringHelper.isNull(orderField) && !StringHelper.isNull(direction)) {
			hql = hql + " order by " + orderField + " " + direction;
		} else {
			hql += " order by id desc";
		}
		List l = commonDao.getEntitiesByPropertiesWithHql(hql, curPage,
				pageSize);
		List<Category> resList = new ArrayList<Category>();
		for (int i = 0; i < l.size(); i++) {
			Category po = (Category) l.get(i);
			resList.add(po);
		}
		return resList;
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
	public List<CommodityVO> webList (String categoryParentId, String categoryName, String categoryLevel,
					String categoryStatus, String primaryConfigurationId,
					int curPage, int pageSize, String orderField, String direction) {
		String hql = "from Category where 1=1";
		if (!StringHelper.isNull(categoryName)) {
			hql = hql + " and categoryName like '%" + categoryName + "%'";
		}
		if (!StringHelper.isNull(categoryParentId)) {
			hql = hql + " and categoryParentId = " + categoryParentId;
		}
		if (!StringHelper.isNull(categoryLevel)) {
			hql = hql + " and categoryLevel = " + categoryLevel;
		}
		if (!StringHelper.isNull(categoryStatus)) {
			hql = hql + " and categoryStatus != " + categoryStatus;
		}
		if (!StringHelper.isNull(primaryConfigurationId)) {
			hql = hql + " and primaryConfigurationId = " + primaryConfigurationId;
		}
		if (!StringHelper.isNull(orderField) && !StringHelper.isNull(direction)) {
			hql = hql + " order by " + orderField + " " + direction;
		} else {
			hql += " order by id desc";
		}
		List l = commonDao.getEntitiesByPropertiesWithHql(hql, curPage,
				pageSize);
		HashMap map = findCategoryGoodsCount(categoryParentId, categoryName, categoryLevel, 
				categoryStatus, primaryConfigurationId);
		
		List<CommodityVO> resList = new ArrayList<CommodityVO>();
		for (int i = 0; i < l.size(); i++) {
			Category po = (Category) l.get(i);
			CommodityVO vo = new CommodityVO();
			vo.setCategoryDescription(po.getCategoryDescription());
			vo.setCategoryHeadLogo(po.getCategoryHeadLogo());
			if(po.getId()!=null){
				vo.setCategoryId(po.getId().toString());
				if(map!=null && map.get(vo.getCategoryId())!=null){
					vo.setCategoryGoodsCount(map.get(vo.getCategoryId()).toString());
				}
			}
			vo.setCategoryLogo(po.getCategoryLogo());
			vo.setCategoryName(po.getCategoryName());
			if(po.getCategoryParentId()!=null){
				vo.setCategoryParentId(po.getCategoryParentId().toString());
			}
			resList.add(vo);
		}
		return resList;
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
	public HashMap<String,String> findCategoryGoodsCount (String categoryParentId, 
			String categoryName, String categoryLevel, 
			String categoryStatus, String primaryConfigurationId
			) {
		String hql = "select a.id ,count(*) from Category a, Goods b where a.id=b.goodsCategoryId";
		if (!StringHelper.isNull(categoryName)) {
			hql = hql + " and a.categoryName like '%" + categoryName + "%'";
		}
		if (!StringHelper.isNull(categoryParentId)) {
			hql = hql + " and a.categoryParentId = " + categoryParentId;
		}
		if (!StringHelper.isNull(categoryLevel)) {
			hql = hql + " and a.categoryLevel = " + categoryLevel;
		}
		if (!StringHelper.isNull(categoryStatus)) {
			hql = hql + " and a.categoryStatus != " + categoryStatus;
		}
		if (!StringHelper.isNull(primaryConfigurationId)) {
			hql = hql + " and a.primaryConfigurationId = " + primaryConfigurationId;
		}
		hql = hql + " group by a.id";
		List<Object[]> list = commonDao.getEntitiesByPropertiesWithHql(hql,
				0, 0);
		HashMap map = new HashMap();
		for (int i = 0; i < list.size(); i++) {
			Object[] a = list.get(i);
			if (a == null){
				continue;
			}
			map.put(a[0] == null ? "0" : a[0].toString(), a[1] == null ? "0" : a[1].toString());
		}
		return map;
	}
	/**
	 * @return List<PrimaryConfiguration>
	 */
	public List<Category> listAll() {
		return dao.findAll();
	}

}
