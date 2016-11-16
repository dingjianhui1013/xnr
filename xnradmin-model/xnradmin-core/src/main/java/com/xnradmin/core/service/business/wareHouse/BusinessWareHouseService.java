/**
 *2012-5-11 上午9:00:02
 */
package com.xnradmin.core.service.business.wareHouse;

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
import com.xnradmin.core.dao.business.wareHouse.BusinessWareHouseDAO;
import com.xnradmin.core.service.business.wareHouse.BusinessWareHouseService;
import com.xnradmin.po.business.wareHouse.BusinessWareHouse;
import com.xnradmin.vo.business.BusinessWareHouseVO;

/**
 * @autohr: xiang_liu
 * 
 */
@Service("BusinessWareHouseService")
public class BusinessWareHouseService {

	@Autowired
	private BusinessWareHouseDAO dao;

	@Autowired
	private CommonDAO commonDao;

	static Log log = LogFactory.getLog(BusinessWareHouseService.class);

	/**
	 * 
	 * @param po
	 * @return int
	 */
	public int save(BusinessWareHouse po) {
		Serializable cls = dao.save(po);
		return Integer.parseInt((cls.toString()));
	}

	public BusinessWareHouse findByid(String id) {
		return dao.findById(Long.valueOf(id));
	}

	/**
	 * @param po
	 * @return int
	 */
	public int modify(BusinessWareHouse po) {
		BusinessWareHouse old = new BusinessWareHouse();
		old = findByid(po.getId().toString());
		if (StringHelper.isNull(po.getWareHouseName())) {
			po.setWareHouseName(old.getWareHouseName());
		}
		if (po.getWareHouseAddress() == null) {
			po.setWareHouseAddress(old.getWareHouseAddress());
		}
		if (po.getWareHouseSerno() == null) {
			po.setWareHouseSerno(old.getWareHouseSerno());
		}
		if (po.getWareHouseStatus() == null) {
			po.setWareHouseStatus(old.getWareHouseStatus());
		}
		if (po.getModifyStaffId() == null) {
			po.setModifyStaffId(old.getModifyStaffId());
		}
		po.setModifyTime(new Timestamp(System.currentTimeMillis()));
		this.dao.merge(po);
		return 0;
	}

	public void del(String id) {
		BusinessWareHouse po = this.dao.findById(Long.valueOf(id));
		this.dao.delete(po);
	}

	public int removeBusinessWareHouseId(String id) {
		return dao.removeBusinessWareHouseId(Integer.valueOf(id));
	}

	/**
	 * @param brandname
	 * @return int
	 */
	public int getCount(BusinessWareHouseVO vo) {
		String hql = "select count(*) from BusinessWareHouse where 1=1";
		if (vo != null && vo.getBusinessWareHouse() != null) {
			if (!StringHelper.isNull(vo.getBusinessWareHouse().getWareHouseName())) {
				hql = hql + " and wareHouseName like '%"
						+ vo.getBusinessWareHouse().getWareHouseName() + "%'";
			}
			if (vo.getBusinessWareHouse().getWareHouseAddress() != null) {
				hql = hql + " and wareHouseAddress = "
						+ vo.getBusinessWareHouse().getWareHouseAddress();
			}
			if (vo.getBusinessWareHouse().getWareHouseSerno() != null) {
				hql = hql + " and wareHouseSerno = "
						+ vo.getBusinessWareHouse().getWareHouseSerno();
			}
			if (vo.getBusinessWareHouse().getWareHouseStatus() != null) {
				hql = hql + " and wareHouseStatus = "
						+ vo.getBusinessWareHouse().getWareHouseStatus();
			}
			if (!StringHelper.isNull(vo.getCreateStartTime())) {
				hql = hql + " and createTime >='" + vo.getCreateStartTime()
						+ "'";
			}
			if (!StringHelper.isNull(vo.getCreateEndTime())) {
				hql = hql + " and createTime <='" + vo.getCreateEndTime() + "'";
			}
			if (vo.getBusinessWareHouse().getCreateStaffId() != null) {
				hql = hql + " and createStaffId ="
						+ vo.getBusinessWareHouse().getCreateStaffId();
			}
			if (!StringHelper.isNull(vo.getModifyStartTime())) {
				hql = hql + " and modifyTime <='" + vo.getModifyStartTime()
						+ "'";
			}
			if (!StringHelper.isNull(vo.getModifyEndTime())) {
				hql = hql + " and modifyTime >='" + vo.getModifyEndTime() + "'";
			}
			if (vo.getBusinessWareHouse().getModifyStaffId() != null) {
				hql = hql + " and modifyStaffId ="
						+ vo.getBusinessWareHouse().getModifyStaffId();
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
	public List<BusinessWareHouseVO> listVO(BusinessWareHouseVO vo, int curPage, int pageSize, 
			String orderField, String direction) {
		String hql = "from BusinessWareHouse where 1=1";
		if (vo != null && vo.getBusinessWareHouse() != null) {
			if (!StringHelper.isNull(vo.getBusinessWareHouse().getWareHouseName())) {
				hql = hql + " and wareHouseName like '%"
						+ vo.getBusinessWareHouse().getWareHouseName() + "%'";
			}
			if (vo.getBusinessWareHouse().getWareHouseAddress() != null) {
				hql = hql + " and wareHouseAddress like '%"
						+ vo.getBusinessWareHouse().getWareHouseAddress() + "%'";
			}
			if (vo.getBusinessWareHouse().getWareHouseSerno() != null) {
				hql = hql + " and wareHouseSerno = "
						+ vo.getBusinessWareHouse().getWareHouseSerno();
			}
			if (vo.getBusinessWareHouse().getWareHouseStatus() != null) {
				hql = hql + " and wareHouseStatus = "
						+ vo.getBusinessWareHouse().getWareHouseStatus();
			}
			if (!StringHelper.isNull(vo.getCreateStartTime())) {
				hql = hql + " and createTime >='" + vo.getCreateStartTime()
						+ "'";
			}
			if (!StringHelper.isNull(vo.getCreateEndTime())) {
				hql = hql + " and createTime <='" + vo.getCreateEndTime() + "'";
			}
			if (vo.getBusinessWareHouse().getCreateStaffId() != null) {
				hql = hql + " and createStaffId ="
						+ vo.getBusinessWareHouse().getCreateStaffId();
			}
			if (!StringHelper.isNull(vo.getModifyStartTime())) {
				hql = hql + " and modifyTime <='" + vo.getModifyStartTime()
						+ "'";
			}
			if (!StringHelper.isNull(vo.getModifyEndTime())) {
				hql = hql + " and modifyTime >='" + vo.getModifyEndTime() + "'";
			}
			if (vo.getBusinessWareHouse().getModifyStaffId() != null) {
				hql = hql + " and modifyStaffId ="
						+ vo.getBusinessWareHouse().getModifyStaffId();
			}
		}
		if (!StringHelper.isNull(orderField) && !StringHelper.isNull(direction)) {
			hql = hql + " order by " + orderField + " " + direction;
		} else {
			hql += " order by id desc";
		}
		List l = commonDao.getEntitiesByPropertiesWithHql(hql, curPage,
				pageSize);
		List<BusinessWareHouseVO> resList = new ArrayList<BusinessWareHouseVO>();
		for (int i = 0; i < l.size(); i++) {
			BusinessWareHouse po = (BusinessWareHouse) l.get(i);
			BusinessWareHouseVO businessWareHouseVO = new BusinessWareHouseVO();
			businessWareHouseVO.setBusinessWareHouse(po);
			resList.add(businessWareHouseVO);
		}
		return resList;
	}

	/**
	 * @return List<PrimaryConfiguration>
	 */
	public List<BusinessWareHouse> listAll() {
		return dao.findAll();
	}

}
