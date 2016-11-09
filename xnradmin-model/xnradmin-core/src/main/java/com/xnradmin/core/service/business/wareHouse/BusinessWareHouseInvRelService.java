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
import com.xnradmin.core.dao.business.wareHouse.BusinessWareHouseInvRelDAO;
import com.xnradmin.core.service.business.wareHouse.BusinessWareHouseInvRelService;
import com.xnradmin.po.business.BusinessCategory;
import com.xnradmin.po.business.BusinessGoods;
import com.xnradmin.po.business.BusinessWeight;
import com.xnradmin.po.business.wareHouse.BusinessWareHouse;
import com.xnradmin.po.business.wareHouse.BusinessWareHouseInvRel;
import com.xnradmin.vo.business.BusinessWareHouseVO;

/**
 * @autohr: xiang_liu
 * 
 */
@Service("BusinessWareHouseInvRelService")
public class BusinessWareHouseInvRelService {

	@Autowired
	private BusinessWareHouseInvRelDAO dao;

	@Autowired
	private CommonDAO commonDao;

	static Log log = LogFactory.getLog(BusinessWareHouseInvRelService.class);

	/**
	 * 
	 * @param po
	 * @return int
	 */
	public int save(BusinessWareHouseInvRel po) {
		Serializable cls = dao.save(po);
		return Integer.parseInt((cls.toString()));
	}

	public BusinessWareHouseInvRel findByid(String id) {
		return dao.findById(Long.valueOf(id));
	}

	public List<BusinessWareHouseInvRel> findByGoodsId(String id) {
		return dao.findByProperty("goodsId", Long.valueOf(id));
	}
	
	/**
	 * @param po
	 * @return int
	 */
	public int modify(BusinessWareHouseInvRel po) {
		BusinessWareHouseInvRel old = new BusinessWareHouseInvRel();
		old = findByid(po.getId().toString());
		if (po.getWareHouseId() == null) {
			po.setWareHouseId(old.getWareHouseId());
		}
		if (po.getGoodsId() == null) {
			po.setGoodsId(old.getGoodsId());
		}
		if (po.getCount() == null) {
			po.setCount(old.getCount());
		}
		if (po.getWeightId() == null) {
			po.setWeightId(old.getWeightId());
		}
		if (po.getModifyStaffId() == null) {
			po.setModifyStaffId(old.getModifyStaffId());
		}
		po.setModifyTime(new Timestamp(System.currentTimeMillis()));
		this.dao.merge(po);
		return 0;
	}

	public void del(String id) {
		BusinessWareHouseInvRel po = this.dao.findById(Long.valueOf(id));
		this.dao.delete(po);
	}

	public int removeBusinessWareHouseInvRelId(String id) {
		return dao.removeBusinessWareHouseInvRelId(Integer.valueOf(id));
	}

	/**
	 * @param brandname
	 * @return int
	 */
	public int getCount(BusinessWareHouseVO vo) {
		String hql = "select count(*) from BusinessWareHouseInvRel a, BusinessWareHouse b, "
				+ " BusinessGoods c, BusinessCategory d, BusinessWeight e "
				+ " where a.wareHouseId = b.id and a.goodsId = c.id "
				+ " and c.goodsCategoryId = d.id and a.weightId = e.id";
		if (vo != null && vo.getBusinessWareHouseInvRel() != null) {
			if (vo.getBusinessWareHouseInvRel().getWareHouseId() != null) {
				hql = hql + " and a.wareHouseId = "
						+ vo.getBusinessWareHouseInvRel().getWareHouseId();
			}
			if (vo.getBusinessWareHouseInvRel().getGoodsId() != null) {
				hql = hql + " and a.goodsId = "
						+ vo.getBusinessWareHouseInvRel().getGoodsId();
			}
			if (vo.getBusinessGoods().getGoodsCategoryId() != null) {
				hql = hql + " and c.goodsCategoryId = "
						+ vo.getBusinessGoods().getGoodsCategoryId();
			}
			if (vo.getBusinessGoods().getGoodsWeightId() != null) {
				hql = hql + " and a.weightId = "
						+ vo.getBusinessGoods().getGoodsWeightId();
			}
			if (vo.getBusinessWareHouseInvRel().getCount() != null) {
				hql = hql + " and a.count = "
						+ vo.getBusinessWareHouseInvRel().getCount();
			}
			if (!StringHelper.isNull(vo.getCreateStartTime())) {
				hql = hql + " and a.createTime >='" + vo.getCreateStartTime()
						+ "'";
			}
			if (!StringHelper.isNull(vo.getCreateEndTime())) {
				hql = hql + " and a.createTime <='" + vo.getCreateEndTime() + "'";
			}
			if (vo.getBusinessWareHouseInvRel().getCreateStaffId() != null) {
				hql = hql + " and a.createStaffId ="
						+ vo.getBusinessWareHouseInvRel().getCreateStaffId();
			}
			if (!StringHelper.isNull(vo.getModifyStartTime())) {
				hql = hql + " and a.modifyTime <='" + vo.getModifyStartTime()
						+ "'";
			}
			if (!StringHelper.isNull(vo.getModifyEndTime())) {
				hql = hql + " and a.modifyTime >='" + vo.getModifyEndTime() + "'";
			}
			if (vo.getBusinessWareHouseInvRel().getModifyStaffId() != null) {
				hql = hql + " and a.modifyStaffId ="
						+ vo.getBusinessWareHouseInvRel().getModifyStaffId();
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
		String hql = "from BusinessWareHouseInvRel a, BusinessWareHouse b, "
				+ " BusinessGoods c, BusinessCategory d, BusinessWeight e "
				+ " where a.wareHouseId = b.id and a.goodsId = c.id "
				+ " and c.goodsCategoryId = d.id and a.weightId = e.id";
		if (vo != null && vo.getBusinessWareHouseInvRel() != null) {
			if (vo.getBusinessWareHouseInvRel().getWareHouseId() != null) {
				hql = hql + " and a.wareHouseId = "
						+ vo.getBusinessWareHouseInvRel().getWareHouseId();
			}
			if (vo.getBusinessWareHouseInvRel().getGoodsId() != null) {
				hql = hql + " and a.goodsId = "
						+ vo.getBusinessWareHouseInvRel().getGoodsId();
			}
			if (vo.getBusinessGoods().getGoodsCategoryId() != null) {
				hql = hql + " and c.goodsCategoryId = "
						+ vo.getBusinessGoods().getGoodsCategoryId();
			}
			if (vo.getBusinessGoods().getGoodsWeightId() != null) {
				hql = hql + " and a.weightId = "
						+ vo.getBusinessGoods().getGoodsWeightId();
			}
			if (vo.getBusinessWareHouseInvRel().getCount() != null) {
				hql = hql + " and a.count = "
						+ vo.getBusinessWareHouseInvRel().getCount();
			}
			if (!StringHelper.isNull(vo.getCreateStartTime())) {
				hql = hql + " and a.createTime >='" + vo.getCreateStartTime()
						+ "'";
			}
			if (!StringHelper.isNull(vo.getCreateEndTime())) {
				hql = hql + " and a.createTime <='" + vo.getCreateEndTime() + "'";
			}
			if (vo.getBusinessWareHouseInvRel().getCreateStaffId() != null) {
				hql = hql + " and a.createStaffId ="
						+ vo.getBusinessWareHouseInvRel().getCreateStaffId();
			}
			if (!StringHelper.isNull(vo.getModifyStartTime())) {
				hql = hql + " and a.modifyTime <='" + vo.getModifyStartTime()
						+ "'";
			}
			if (!StringHelper.isNull(vo.getModifyEndTime())) {
				hql = hql + " and a.modifyTime >='" + vo.getModifyEndTime() + "'";
			}
			if (vo.getBusinessWareHouseInvRel().getModifyStaffId() != null) {
				hql = hql + " and a.modifyStaffId ="
						+ vo.getBusinessWareHouseInvRel().getModifyStaffId();
			}
		}
		if (!StringHelper.isNull(orderField) && !StringHelper.isNull(direction)) {
			hql = hql + " order by " + orderField + " " + direction;
		} else {
			hql += " order by a.createTime desc";
		}
		List l = commonDao.getEntitiesByPropertiesWithHql(hql, curPage,
				pageSize);
		List<BusinessWareHouseVO> resList = new ArrayList<BusinessWareHouseVO>();
		for (int i = 0; i < l.size(); i++) {
			Object[] obj = (Object[]) l.get(i);
			BusinessWareHouseInvRel bhir = (BusinessWareHouseInvRel) obj[0];
			BusinessWareHouse bwh = (BusinessWareHouse) obj[1];
			BusinessGoods bg = (BusinessGoods) obj[2];
			BusinessCategory bc = (BusinessCategory) obj[3];
			BusinessWeight bw = (BusinessWeight) obj[4];
			BusinessWareHouseVO businessWareHouseVO = new BusinessWareHouseVO();
			businessWareHouseVO.setBusinessWareHouseInvRel(bhir);
			businessWareHouseVO.setBusinessWareHouse(bwh);
			businessWareHouseVO.setBusinessGoods(bg);
			businessWareHouseVO.setBusinessCategory(bc);
			businessWareHouseVO.setBusinessWeight(bw);
			resList.add(businessWareHouseVO);
		}
		return resList;
	}

	/**
	 * @return List<PrimaryConfiguration>
	 */
	public List<BusinessWareHouseInvRel> listAll() {
		return dao.findAll();
	}

}
