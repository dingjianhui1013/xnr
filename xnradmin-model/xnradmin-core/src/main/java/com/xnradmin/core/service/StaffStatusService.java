/**
 *2012-5-14 上午6:36:30
 */
package com.xnradmin.core.service;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cntinker.util.StringHelper;
import com.xnradmin.core.dao.CommonDAO;
import com.xnradmin.core.dao.CommonStaffStatusDAO;
import com.xnradmin.po.CommonStaffStatus;

/**
 * @autohr: bin_liu
 * 
 */
@Service("StaffStatusService")
public class StaffStatusService {

	static Log log = LogFactory.getLog(StaffStatusService.class);

	@Autowired
	private CommonStaffStatusDAO dao;
	
	@Autowired
    private CommonDAO commonDao;

	/**
	 * @param po
	 * @return int
	 */
	public int save(CommonStaffStatus po) {
		if (this.dao.findByStatusName(po.getStatusName()).size() > 0) {
			return 1;
		}
		dao.save(po);
		return 0;
	}

	public CommonStaffStatus findByid(String id) {
		return dao.findById(new Integer(id));
	}

	/**
	 * @param porgid
	 * @return int
	 */
	public int getCountByPorgid(String statusName) {
		String hql = "select count(*) from CommonStaffStatus";
		if (!StringHelper.isNull(statusName)) {
			hql = hql + " where STATUS_NAME like '%" + statusName + "%'";
		}
		return this.commonDao.getNumberOfEntitiesWithHql(hql);
	}

	/**
	 * 
	 * @param po
	 * @return int
	 */
	public int modify(CommonStaffStatus po) {
		List l = this.dao.findByStatusName((po.getStatusName()));
		if (l.size() > 0) {
			CommonStaffStatus old = this.dao.findById(po.getId());
			if (!old.getStatusName().equals(po.getStatusName()))
				return 1;
		}
		this.dao.merge(po);
		return 0;
	}

	/**
	 * @param orgTypeName
	 * @param curPage
	 * @param pageSize
	 * @param orderField
	 * @param direction
	 * @return List<CommonStaffStatus>
	 */
	public List<CommonStaffStatus> listVO(String statusName, int curPage,
			int pageSize, String orderField, String direction) {
		String hql = "from CommonStaffStatus";
		if (!StringHelper.isNull(statusName)) {
			hql = hql + " where STATUS_NAME like '%" + statusName + "%'";
		}
		if (!StringHelper.isNull(orderField) && !StringHelper.isNull(direction)) {
			hql = hql + " order by " + orderField + " " + direction;
		}

		List l = commonDao.getEntitiesByPropertiesWithHql(hql, curPage, pageSize);

		return l;

	}

	/**
	 * @return List<CommonStaffStatus>
	 */
	public List<CommonStaffStatus> listAll() {
		return dao.findAll();
	}

}
