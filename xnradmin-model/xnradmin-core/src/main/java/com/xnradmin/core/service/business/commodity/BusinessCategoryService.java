/**
 *2012-5-11 上午9:00:02
 */
package com.xnradmin.core.service.business.commodity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cntinker.util.StringHelper;
import com.xnradmin.core.dao.CommonDAO;
import com.xnradmin.core.dao.business.commodity.BusinessCategoryDAO;
import com.xnradmin.po.business.BusinessCategory;
import com.xnradmin.vo.business.BusinessGoodsVO;

/**
 * @autohr: xiang_liu
 * 
 */
@Service("BusinessCategoryService")
public class BusinessCategoryService {

	@Autowired
	private BusinessCategoryDAO dao;

	@Autowired
	private CommonDAO commonDao;

	static Log log = LogFactory.getLog(BusinessCategoryService.class);

	/**
	 * 
	 * @param po
	 * @return int
	 */
	public int save(BusinessCategory po) {
		Serializable cls = dao.save(po);
		return Integer.parseInt((cls.toString()));
	}

	public BusinessCategory findByid(String id) {
		return dao.findById(Integer.valueOf(id));
	}

	/**
	 * @param po
	 * @return int
	 */
	public int modify(BusinessCategory po) {
		BusinessCategory old = new BusinessCategory();
		old = findByid(po.getId().toString());
		if (StringHelper.isNull(po.getCategoryName())) {
			po.setCategoryName(old.getCategoryName());
		}
		if (po.getSortId() == null) {
			po.setSortId(old.getSortId());
		}
		if (po.getCategoryParentId() == null) {
			po.setCategoryParentId(old.getCategoryParentId());
		}
		if (po.getCategoryLevel() == null) {
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
		if (po.getCategoryStatus() == null) {
			po.setCategoryStatus(old.getCategoryStatus());
		}
		if (po.getPrimaryConfigurationId() == null) {
			po.setPrimaryConfigurationId(old.getPrimaryConfigurationId());
		}
		if (po.getModifyStaffId() == null) {
			po.setModifyStaffId(old.getModifyStaffId());
		}
		po.setModifyTime(new Timestamp(System.currentTimeMillis()));
		this.dao.merge(po);
		return 0;
	}

	public void del(String id) {
		BusinessCategory po = this.dao.findById(Integer.valueOf(id));
		this.dao.delete(po);
	}

	public int removeCategoryId(String id) {
		return dao.removeBusinessCategoryId(Integer.valueOf(id));
	}

	/**
	 * @param brandname
	 * @return int
	 */
	public int getCount(BusinessGoodsVO vo) {
		String hql = "select count(*) from BusinessCategory where 1=1";
		if (vo != null && vo.getBusinessCategory() != null) {
			if (!StringHelper
					.isNull(vo.getBusinessCategory().getCategoryName())) {
				hql = hql + " and categoryName like '%"
						+ vo.getBusinessCategory().getCategoryName() + "%'";
			}
			if (vo.getBusinessCategory().getCategoryParentId() != null) {
				hql = hql + " and categoryParentId = "
						+ vo.getBusinessCategory().getCategoryParentId();
			}
			if (vo.getBusinessCategory().getCategoryLevel() != null) {
				hql = hql + " and categoryLevel = "
						+ vo.getBusinessCategory().getCategoryLevel();
			}
			if (vo.getBusinessCategory().getCategoryStatus() != null) {
				hql = hql + " and categoryStatus = "
						+ vo.getBusinessCategory().getCategoryStatus();
			}
			if (vo.getBusinessCategory().getPrimaryConfigurationId() != null) {
				hql = hql + " and primaryConfigurationId = "
						+ vo.getBusinessCategory().getPrimaryConfigurationId();
			}
			if (!StringHelper.isNull(vo.getCreateStartTime())) {
				hql = hql + " and createTime >='" + vo.getCreateStartTime()
						+ "'";
			}
			if (!StringHelper.isNull(vo.getCreateEndTime())) {
				hql = hql + " and createTime <='" + vo.getCreateEndTime() + "'";
			}
			if (vo.getBusinessCategory().getCreateStaffId() != null) {
				hql = hql + " and createStaffId ="
						+ vo.getBusinessCategory().getCreateStaffId();
			}
			if (!StringHelper.isNull(vo.getModifyStartTime())) {
				hql = hql + " and modifyTime <='" + vo.getModifyStartTime()
						+ "'";
			}
			if (!StringHelper.isNull(vo.getModifyEndTime())) {
				hql = hql + " and modifyTime >='" + vo.getModifyEndTime() + "'";
			}
			if (vo.getBusinessCategory().getModifyStaffId() != null) {
				hql = hql + " and modifyStaffId ="
						+ vo.getBusinessCategory().getModifyStaffId();
			}
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
	public List<BusinessGoodsVO> listVO(BusinessGoodsVO vo, int curPage,
			int pageSize, String orderField, String direction) {
		String hql = "from BusinessCategory where 1=1"; //and categoryLevel = 1";
		if (vo != null && vo.getBusinessCategory() != null) {
			if (!StringHelper
					.isNull(vo.getBusinessCategory().getCategoryName())) {
				hql = hql + " and categoryName like '%"
						+ vo.getBusinessCategory().getCategoryName() + "%'";
			}
			if (vo.getBusinessCategory().getCategoryParentId() != null) {
				hql = hql + " and categoryParentId = "
						+ vo.getBusinessCategory().getCategoryParentId();
			}
			if (vo.getBusinessCategory().getCategoryLevel() != null) {
				hql = hql + " and categoryLevel = "
						+ vo.getBusinessCategory().getCategoryLevel();
			}
			if (vo.getBusinessCategory().getCategoryStatus() != null) {
				hql = hql + " and categoryStatus = "
						+ vo.getBusinessCategory().getCategoryStatus();
			}
			if (vo.getBusinessCategory().getPrimaryConfigurationId() != null) {
				hql = hql + " and primaryConfigurationId = "
						+ vo.getBusinessCategory().getPrimaryConfigurationId();
			}
			if (!StringHelper.isNull(vo.getCreateStartTime())) {
				hql = hql + " and createTime >='" + vo.getCreateStartTime()
						+ "'";
			}
			if (!StringHelper.isNull(vo.getCreateEndTime())) {
				hql = hql + " and createTime <='" + vo.getCreateEndTime() + "'";
			}
			if (vo.getBusinessCategory().getCreateStaffId() != null) {
				hql = hql + " and createStaffId ="
						+ vo.getBusinessCategory().getCreateStaffId();
			}
			if (!StringHelper.isNull(vo.getModifyStartTime())) {
				hql = hql + " and modifyTime <='" + vo.getModifyStartTime()
						+ "'";
			}
			if (!StringHelper.isNull(vo.getModifyEndTime())) {
				hql = hql + " and modifyTime >='" + vo.getModifyEndTime() + "'";
			}
			if (vo.getBusinessCategory().getModifyStaffId() != null) {
				hql = hql + " and modifyStaffId ="
						+ vo.getBusinessCategory().getModifyStaffId();
			}
		}
		if (!StringHelper.isNull(orderField) && !StringHelper.isNull(direction)) {
			hql = hql + " order by " + orderField + " " + direction;
		} else {
			hql += " order by id desc";
		}
		List l = commonDao.getEntitiesByPropertiesWithHql(hql, curPage,
				pageSize);
		List<BusinessGoodsVO> resList = new LinkedList<BusinessGoodsVO>();
		for (int i = 0; i < l.size(); i++) {
			BusinessCategory po = (BusinessCategory) l.get(i);
			BusinessGoodsVO businessGoodsVO = new BusinessGoodsVO();
			businessGoodsVO.setBusinessCategory(po);
			resList.add(businessGoodsVO);
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
	public List<BusinessCategory> webIndex(BusinessGoodsVO vo, int curPage,
			int pageSize, String orderField, String direction) {
		String hql = "from BusinessCategory where 1=1";
		if (vo != null && vo.getBusinessCategory() != null) {
			if (!StringHelper
					.isNull(vo.getBusinessCategory().getCategoryName())) {
				hql = hql + " and categoryName like '%"
						+ vo.getBusinessCategory().getCategoryName() + "%'";
			}
			if (vo.getBusinessCategory().getCategoryParentId() != null) {
				hql = hql + " and categoryParentId = "
						+ vo.getBusinessCategory().getCategoryParentId();
			}
			if (vo.getBusinessCategory().getCategoryLevel() != null) {
				hql = hql + " and categoryLevel = "
						+ vo.getBusinessCategory().getCategoryLevel();
			}
			if (vo.getBusinessCategory().getCategoryStatus() != null) {
				hql = hql + " and categoryStatus = "
						+ vo.getBusinessCategory().getCategoryStatus();
			}
			if (vo.getBusinessCategory().getPrimaryConfigurationId() != null) {
				hql = hql + " and primaryConfigurationId = "
						+ vo.getBusinessCategory().getPrimaryConfigurationId();
			}
		}
		if (!StringHelper.isNull(orderField)
				&& !StringHelper.isNull(direction)) {
			hql = hql + " order by " + orderField + " " + direction;
		} else {
			hql += " order by id desc";
		}
		List l = commonDao.getEntitiesByPropertiesWithHql(hql, curPage,
				pageSize);
		List<BusinessCategory> resList = new LinkedList<BusinessCategory>();
		for (int i = 0; i < l.size(); i++) {
			BusinessCategory po = (BusinessCategory) l.get(i);
			resList.add(po);
		}
		return resList;
	}

	/**
	 * 生菜配送
	 * 
	 * @param firstletter
	 * @param curPage
	 * @param pageSize
	 * @param orderField
	 * @param direction
	 * @return List<GetOrgListVO>
	 */
	public List<BusinessCategory> webBusinessList(BusinessGoodsVO vo, int curPage, int pageSize,
			String orderField, String direction) {
		String hql = "from BusinessCategory where id<>153";
		if (vo != null && vo.getBusinessCategory() != null) {
			if (!StringHelper
					.isNull(vo.getBusinessCategory().getCategoryName())) {
				hql = hql + " and categoryName like '%"
						+ vo.getBusinessCategory().getCategoryName() + "%'";
			}
			if (vo.getBusinessCategory().getCategoryParentId() != null) {
				hql = hql + " and categoryParentId = "
						+ vo.getBusinessCategory().getCategoryParentId();
			}
			if (vo.getBusinessCategory().getCategoryLevel() != null) {
				hql = hql + " and categoryLevel = "
						+ vo.getBusinessCategory().getCategoryLevel();
			}
			if (vo.getBusinessCategory().getCategoryStatus() != null) {
				hql = hql + " and categoryStatus = "
						+ vo.getBusinessCategory().getCategoryStatus();
			}
			if (vo.getBusinessCategory().getPrimaryConfigurationId() != null) {
				hql = hql + " and primaryConfigurationId = "
						+ vo.getBusinessCategory().getPrimaryConfigurationId();
			}
		}
		if (!StringHelper.isNull(orderField)
				&& !StringHelper.isNull(direction)) {
			hql = hql + " order by " + orderField + " " + direction;
		} else {
			hql += " order by id desc";
		}
		List l = commonDao.getEntitiesByPropertiesWithHql(hql, curPage,
				pageSize);
		List<BusinessCategory> resList = new LinkedList<BusinessCategory>();
		for (int i = 0; i < l.size(); i++) {
			BusinessCategory po = (BusinessCategory) l.get(i);
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
	public List<BusinessGoodsVO> webList(BusinessGoodsVO vo, int curPage, int pageSize,
			String orderField, String direction) {
		String hql = "from BusinessCategory where 1=1";
		if (vo != null && vo.getBusinessCategory() != null) {
			if (!StringHelper
					.isNull(vo.getBusinessCategory().getCategoryName())) {
				hql = hql + " and categoryName like '%"
						+ vo.getBusinessCategory().getCategoryName() + "%'";
			}
			if (vo.getBusinessCategory().getCategoryParentId() != null) {
				hql = hql + " and categoryParentId = "
						+ vo.getBusinessCategory().getCategoryParentId();
			}
			if (vo.getBusinessCategory().getCategoryLevel() != null) {
				hql = hql + " and categoryLevel = "
						+ vo.getBusinessCategory().getCategoryLevel();
			}
			if (vo.getBusinessCategory().getCategoryStatus() != null) {
				hql = hql + " and categoryStatus = "
						+ vo.getBusinessCategory().getCategoryStatus();
			}
			if (vo.getBusinessCategory().getPrimaryConfigurationId() != null) {
				hql = hql + " and primaryConfigurationId = "
						+ vo.getBusinessCategory().getPrimaryConfigurationId();
			}
		}
		if (!StringHelper.isNull(orderField) && !StringHelper.isNull(direction)) {
			hql = hql + " order by " + orderField + " " + direction;
		} else {
			hql += " order by id desc";
		}
		List l = commonDao.getEntitiesByPropertiesWithHql(hql, curPage,
				pageSize);
		LinkedHashMap map = findCategoryGoodsCount(vo);
		List<BusinessGoodsVO> resList = new LinkedList<BusinessGoodsVO>();
		for (int i = 0; i < l.size(); i++) {
			BusinessCategory po = (BusinessCategory) l.get(i);
			BusinessGoodsVO businessGoodsVO = new BusinessGoodsVO();
			if (po.getId() != null) {
				if (map != null && map.get(po.getId()) != null) {
					businessGoodsVO.setCategoryGoodsCount(map.get(po.getId())
							.toString());
				}
				businessGoodsVO.setBusinessCategory(po);
			}
			resList.add(businessGoodsVO);
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
	public LinkedHashMap<String, String> findCategoryGoodsCount(BusinessGoodsVO vo) {
		String hql = "select a.id ,count(*) from BusinessCategory a, BusinessGoods b where a.id=b.goodsCategoryId";
		if (vo != null && vo.getBusinessCategory() != null) {
			if (!StringHelper
					.isNull(vo.getBusinessCategory().getCategoryName())) {
				hql = hql + " and a.categoryName like '%"
						+ vo.getBusinessCategory().getCategoryName() + "%'";
			}
			if (vo.getBusinessCategory().getCategoryParentId() != null) {
				hql = hql + " and a.categoryParentId = "
						+ vo.getBusinessCategory().getCategoryParentId();
			}
			if (vo.getBusinessCategory().getCategoryLevel() != null) {
				hql = hql + " and a.categoryLevel = "
						+ vo.getBusinessCategory().getCategoryLevel();
			}
			if (vo.getBusinessCategory().getCategoryStatus() != null) {
				hql = hql + " and a.categoryStatus = "
						+ vo.getBusinessCategory().getCategoryStatus();
			}
			if (vo.getBusinessCategory().getPrimaryConfigurationId() != null) {
				hql = hql + " and a.primaryConfigurationId = "
						+ vo.getBusinessCategory().getPrimaryConfigurationId();
			}
		}
		hql = hql + " group by a.id";
		List<Object[]> list = commonDao.getEntitiesByPropertiesWithHql(hql, 0,
				0);
		LinkedHashMap map = new LinkedHashMap();
		for (int i = 0; i < list.size(); i++) {
			Object[] a = list.get(i);
			if (a == null) {
				continue;
			}
			map.put(a[0] == null ? "0" : a[0].toString(), a[1] == null ? "0"
					: a[1].toString());
		}
		return map;
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
	public List<BusinessCategory> findList() {
		String hql = "from BusinessCategory where categoryParentId !=0  and categoryLevel !=0 "
				+ "  order by categoryParentId asc, id asc";
		List l = commonDao.getEntitiesByPropertiesWithHql(hql, 0,
				0);
		List<BusinessCategory> resList = new LinkedList<BusinessCategory>();
		for (int i = 0; i < l.size(); i++) {
			BusinessCategory po = (BusinessCategory) l.get(i);
			resList.add(po);
		}
		return resList;
	}
	
	public List<BusinessCategory> findParentList() {
		String hql = "from BusinessCategory where categoryParentId =0  and categoryLevel = 0 "
				+ "  order by categoryParentId asc, id desc";
		List l = commonDao.getEntitiesByPropertiesWithHql(hql, 0,
				0);
		List<BusinessCategory> resList = new LinkedList<BusinessCategory>();
		for (int i = 0; i < l.size(); i++) {
			BusinessCategory po = (BusinessCategory) l.get(i);
			resList.add(po);
		}
		return resList;
	}

	
	/**
	 * @return List<PrimaryConfiguration>
	 */
	public List<BusinessCategory> listAll() {
		return dao.findAll();
	}

}
