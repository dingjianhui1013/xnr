/**
 *2012-5-11 上午9:00:02
 */
package com.xnradmin.core.service.mall.seting;

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
import com.xnradmin.core.dao.mall.seting.PrimaryConfigurationDAO;
import com.xnradmin.constant.ViewConstant;
import com.xnradmin.po.mall.seting.PrimaryConfiguration;
import com.xnradmin.vo.mall.SetingVO;

/**
 * @autohr: xiang_liu
 * 
 */
@Service("PrimaryConfigurationService")
public class PrimaryConfigurationService {

	@Autowired
	private PrimaryConfigurationDAO dao;

	@Autowired
	private CommonDAO commonDao;

	static Log log = LogFactory.getLog(PrimaryConfigurationService.class);

	/**
	 * 
	 * @param po
	 * @return int
	 */
	public int save(PrimaryConfiguration po) {
		Serializable cls = dao.save(po);
		return Integer.parseInt((cls.toString()));
	}

	public PrimaryConfiguration findByid(String id) {
		return dao.findById(Integer.valueOf(id));
	}

	/**
	 * @param po
	 * @return int
	 */
	public int modify(PrimaryConfiguration po) {
		PrimaryConfiguration old = new PrimaryConfiguration();
		old = findByid(po.getId().toString());
		if (StringHelper.isNull(po.getMallName())) {
			po.setMallName(old.getMallName());
		}
		if (StringHelper.isNull(po.getMallDemoUrl())) {
			po.setMallDemoUrl(old.getMallDemoUrl());
		}
		if (StringHelper.isNull(po.getMallLogo())) {
			po.setMallLogo(old.getMallLogo());
		}
		if (StringHelper.isNull(po.getMallBackground())) {
			po.setMallBackground(old.getMallBackground());
		}
		if (po.getMallBackgroundStatus() == null) {
			po.setMallBackgroundStatus(old.getMallBackgroundStatus());
		}
		if (StringHelper.isNull(po.getAddress())) {
			po.setAddress(old.getAddress());
		}
		if (StringHelper.isNull(po.getMobile())) {
			po.setMobile(old.getMobile());
		}
		if (StringHelper.isNull(po.getCode())) {
			po.setCode(old.getCode());
		}
		if (StringHelper.isNull(po.getMallIntroduction())) {
			po.setMallIntroduction(old.getMallIntroduction());
		}
		if (po.getMallStatus() == null) {
			po.setMallStatus(old.getMallStatus());
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
		PrimaryConfiguration po = this.dao.findById(Integer.valueOf(id));
		this.dao.delete(po);
	}

	public int removePrimaryConfigurationId(String id) {
		return dao.removePrimaryConfigurationId(Integer.valueOf(id));
	}

	/**
	 * @param brandname
	 * @return int
	 */
	public int getCount(String mallName, String staffId, String mallStatus,
			String createStartTime, String createEndTime, String createStaffId,
			String modifyStartTime, String modifyEndTime, String modifyStaffId) {
		String hql = "select count(*) from PrimaryConfiguration where 1=1";
		if (!StringHelper.isNull(staffId)) {
			hql = hql + " and staffId = '" + staffId + "'";
		}
		if (!StringHelper.isNull(mallName)) {
			hql = hql + " and mallName like '%" + mallName + "%'";
		}
		if (!StringHelper.isNull(mallStatus)) {
			hql = hql + " and mallStatus = " + mallStatus;
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
	public List<SetingVO> listVO(String mallName, String staffId,
			String mallStatus, String createStartTime, String createEndTime,
			String createStaffId, String modifyStartTime, String modifyEndTime,
			String modifyStaffId, int curPage, int pageSize, String orderField,
			String direction) {
		String hql = "from PrimaryConfiguration where 1=1";
		if (!StringHelper.isNull(staffId)) {
			hql = hql + " and staffId = '" + staffId + "'";
		}
		if (!StringHelper.isNull(mallName)) {
			hql = hql + " and mallName like '%" + mallName + "%'";
		}
		if (!StringHelper.isNull(mallStatus)) {
			hql = hql + " and mallStatus = " + mallStatus;
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
				pageSize); // .findByQuerySplitPage(hql,curPage,pageSize);
		List<SetingVO> resList = new ArrayList<SetingVO>();
		for (int i = 0; i < l.size(); i++) {
			PrimaryConfiguration po = (PrimaryConfiguration) l.get(i);
			SetingVO vo = new SetingVO();
			if (po.getId() != null) {
				vo.setPrimaryConfigurationId(po.getId().toString());
			}
			vo.setMallDemoUrl(po.getMallDemoUrl());
			vo.setMallName(po.getMallName());
			vo.setMallLogo(po.getMallLogo());
			vo.setMallBackground(po.getMallBackground());
			if (po.getMallBackgroundStatus() != null) {
				vo.setMallBackgroundStatus(po.getMallBackgroundStatus()
						.toString());
			}
			vo.setAddress(po.getAddress());
			vo.setMobile(po.getMobile());
			vo.setCode(po.getCode());
			vo.setEmail(po.getEmail());
			vo.setMallIntroduction(po.getMallIntroduction());
			vo.setPrimaryConfigurationStaffId(po.getStaffId());
			if (po.getMallStatus() != null) {
				vo.setMallStatus(po.getMallStatus().toString());
			}
			if (po.getCreateTime() != null) {
				vo.setPrimaryConfigurationCreateTime(StringHelper.getSystime(
						ViewConstant.PAGE_DATE_FORMAT_SEC, po.getCreateTime()
								.getTime()));
			}
			if (po.getCreateStaffId() != null) {
				vo.setPrimaryConfigurationCreateStaffId(po.getCreateStaffId().toString());
			}
			if (po.getModifyTime() != null) {
				vo.setPrimaryConfigurationModifyTime(StringHelper.getSystime(
						ViewConstant.PAGE_DATE_FORMAT_SEC, po.getModifyTime()
								.getTime()));
			}
			if (po.getModifyStaffId() != null) {
				vo.setPrimaryConfigurationModifyStaffId(po.getModifyStaffId().toString());
			}
			resList.add(vo);
		}
		return resList;
	}

	/**
	 * @return List<PrimaryConfiguration>
	 */
	public List<PrimaryConfiguration> listAll() {
		return dao.findAll();
	}

}
