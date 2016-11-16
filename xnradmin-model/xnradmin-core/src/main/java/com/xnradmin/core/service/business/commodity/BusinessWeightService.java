/**
 *2012-5-11 上午9:00:02
 */
package com.xnradmin.core.service.business.commodity;

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
import com.xnradmin.core.dao.business.commodity.BusinessWeightDAO;
import com.xnradmin.po.business.BusinessWeight;
import com.xnradmin.vo.business.BusinessGoodsVO;

/**
 * @autohr: xiang_liu
 * 
 */
@Service("BusinessWeightService")
public class BusinessWeightService {

	@Autowired
	private BusinessWeightDAO dao;

	@Autowired
	private CommonDAO commonDao;

	static Log log = LogFactory.getLog(BusinessWeightService.class);

	/**
	 * 
	 * @param po
	 * @return int
	 */
	public int save(BusinessWeight po) {
		Serializable cls = dao.save(po);
		return Integer.parseInt((cls.toString()));
	}

	public BusinessWeight findByid(String id) {
		return dao.findById(Integer.valueOf(id));
	}

	/**
	 * @param po
	 * @return int
	 */
	public int modify(BusinessWeight po) {
		BusinessWeight old = new BusinessWeight();
		old = findByid(po.getId().toString());
		if (StringHelper.isNull(po.getWeightName())) {
			po.setWeightName(old.getWeightName());
		}
		if (po.getSortId() == null) {
			po.setSortId(old.getSortId());
		}
		if (po.getWeightStatus() == null) {
			po.setWeightStatus(old.getWeightStatus());
		}
		if (StringHelper.isNull(po.getWeightDescription())) {
			po.setWeightDescription(old.getWeightDescription());
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
		BusinessWeight po = this.dao.findById(Integer.valueOf(id));
		this.dao.delete(po);
	}

	public int removeBusinessWeightId(String id) {
		return dao.removeBusinessWeightId(Integer.valueOf(id));
	}

	/**
	 * @param brandname
	 * @return int
	 */
	public int getCount(BusinessGoodsVO vo) {
		String hql = "select count(*) from BusinessWeight where 1=1";
		if (vo != null && vo.getBusinessWeight() != null) {
			if (!StringHelper.isNull(vo.getBusinessWeight().getWeightName())) {
				hql = hql + " and weightName like '%"
						+ vo.getBusinessWeight().getWeightName() + "%'";
			}
			if (vo.getBusinessWeight().getWeightStatus() != null) {
				hql = hql + " and weightStatus = "
						+ vo.getBusinessWeight().getWeightStatus();
			}
			if (vo.getBusinessWeight().getPrimaryConfigurationId() != null) {
				hql = hql + " and primaryConfigurationId = "
						+ vo.getBusinessWeight().getPrimaryConfigurationId();
			}
			if (!StringHelper.isNull(vo.getCreateStartTime())) {
				hql = hql + " and createTime >='" + vo.getCreateStartTime()
						+ "'";
			}
			if (!StringHelper.isNull(vo.getCreateEndTime())) {
				hql = hql + " and createTime <='" + vo.getCreateEndTime() + "'";
			}
			if (vo.getBusinessWeight().getCreateStaffId() != null) {
				hql = hql + " and createStaffId ="
						+ vo.getBusinessWeight().getCreateStaffId();
			}
			if (!StringHelper.isNull(vo.getModifyStartTime())) {
				hql = hql + " and modifyTime <='" + vo.getModifyStartTime()
						+ "'";
			}
			if (!StringHelper.isNull(vo.getModifyEndTime())) {
				hql = hql + " and modifyTime >='" + vo.getModifyEndTime() + "'";
			}
			if (vo.getBusinessWeight().getModifyStaffId() != null) {
				hql = hql + " and modifyStaffId ="
						+ vo.getBusinessWeight().getModifyStaffId();
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
	public List<BusinessGoodsVO> listVO(BusinessGoodsVO vo, int curPage, int pageSize, 
			String orderField, String direction) {
		String hql = "from BusinessWeight where 1=1";
		if (vo != null && vo.getBusinessWeight() != null) {
			if (!StringHelper.isNull(vo.getBusinessWeight().getWeightName())) {
				hql = hql + " and weightName like '%"
						+ vo.getBusinessWeight().getWeightName() + "%'";
			}
			if (vo.getBusinessWeight().getWeightStatus() != null) {
				hql = hql + " and weightStatus = "
						+ vo.getBusinessWeight().getWeightStatus();
			}
			if (vo.getBusinessWeight().getPrimaryConfigurationId() != null) {
				hql = hql + " and primaryConfigurationId = "
						+ vo.getBusinessWeight().getPrimaryConfigurationId();
			}
			if (!StringHelper.isNull(vo.getCreateStartTime())) {
				hql = hql + " and createTime >='" + vo.getCreateStartTime()
						+ "'";
			}
			if (!StringHelper.isNull(vo.getCreateEndTime())) {
				hql = hql + " and createTime <='" + vo.getCreateEndTime() + "'";
			}
			if (vo.getBusinessWeight().getCreateStaffId() != null) {
				hql = hql + " and createStaffId ="
						+ vo.getBusinessWeight().getCreateStaffId();
			}
			if (!StringHelper.isNull(vo.getModifyStartTime())) {
				hql = hql + " and modifyTime <='" + vo.getModifyStartTime()
						+ "'";
			}
			if (!StringHelper.isNull(vo.getModifyEndTime())) {
				hql = hql + " and modifyTime >='" + vo.getModifyEndTime() + "'";
			}
			if (vo.getBusinessWeight().getModifyStaffId() != null) {
				hql = hql + " and modifyStaffId ="
						+ vo.getBusinessWeight().getModifyStaffId();
			}
		}
		if (!StringHelper.isNull(orderField) && !StringHelper.isNull(direction)) {
			hql = hql + " order by " + orderField + " " + direction;
		} else {
			hql += " order by id desc";
		}
		List l = commonDao.getEntitiesByPropertiesWithHql(hql, curPage,
				pageSize);
		List<BusinessGoodsVO> resList = new ArrayList<BusinessGoodsVO>();
		for (int i = 0; i < l.size(); i++) {
			BusinessWeight po = (BusinessWeight) l.get(i);
			BusinessGoodsVO businessGoodsVO = new BusinessGoodsVO();
			businessGoodsVO.setBusinessWeight(po);
			resList.add(businessGoodsVO);
		}
		return resList;
	}

	/**
	 * @return List<PrimaryConfiguration>
	 */
	public List<BusinessWeight> listAll() {
		return dao.findAll();
	}

}
