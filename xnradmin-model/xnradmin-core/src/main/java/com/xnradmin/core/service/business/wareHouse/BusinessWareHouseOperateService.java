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
import com.xnradmin.core.dao.business.wareHouse.BusinessWareHouseOperateDAO;
import com.xnradmin.core.service.business.wareHouse.BusinessWareHouseOperateService;
import com.xnradmin.po.business.BusinessCategory;
import com.xnradmin.po.business.BusinessGoods;
import com.xnradmin.po.business.BusinessWeight;
import com.xnradmin.po.business.wareHouse.BusinessWareHouse;
import com.xnradmin.po.business.wareHouse.BusinessWareHouseOperate;
import com.xnradmin.vo.business.BusinessWareHouseVO;

/**
 * @autohr: xiang_liu
 * 
 */
@Service("BusinessWareHouseOperateService")
public class BusinessWareHouseOperateService {

	@Autowired
	private BusinessWareHouseOperateDAO dao;

	@Autowired
	private CommonDAO commonDao;

	static Log log = LogFactory.getLog(BusinessWareHouseOperateService.class);

	/**
	 * 
	 * @param po
	 * @return int
	 */
	public Long save(BusinessWareHouseOperate po) {
		Serializable cls = dao.save(po);
		return Long.valueOf(cls.toString());
	}

	public BusinessWareHouseOperate findByid(String id) {
		return dao.findById(Long.valueOf(id));
	}

	/**
	 * @param po
	 * @return int
	 */
	public int modify(BusinessWareHouseOperate po) {
		BusinessWareHouseOperate old = new BusinessWareHouseOperate();
		old = findByid(po.getId().toString());
		if (po.getWareHouseId() == null) {
			po.setWareHouseId(old.getWareHouseId());
		}
		if (po.getGoodsId() == null) {
			po.setGoodsId(old.getGoodsId());
		}
		if (po.getCategoryId() == null) {
			po.setCategoryId(old.getCategoryId());
		}
		if (po.getOperationStatus() == null) {
			po.setOperationStatus(old.getOperationStatus());
		}
		if (po.getReasonStatus() == null) {
			po.setReasonStatus(old.getReasonStatus());
		}
		if (po.getPrice() == null) {
			po.setPrice(old.getPrice());
		}
		if (po.getCount() == null) {
			po.setCount(old.getCount());
		}
		if (po.getWeightId() == null) {
			po.setWeightId(old.getWeightId());
		}
		if (po.getSupplierStaffId() == null) {
			po.setSupplierStaffId(old.getSupplierStaffId());
		}
		if (po.getPurchaserStaffId() == null) {
			po.setPurchaserStaffId(old.getPurchaserStaffId());
		}
		if (po.getRemark() == null) {
			po.setRemark(old.getRemark());
		}

		if (po.getModifyStaffId() == null) {
			po.setModifyStaffId(old.getModifyStaffId());
		}
		po.setModifyTime(new Timestamp(System.currentTimeMillis()));
		this.dao.merge(po);
		return 0;
	}

	public void del(String id) {
		BusinessWareHouseOperate po = this.dao.findById(Long.valueOf(id));
		this.dao.delete(po);
	}

	public int removeBusinessWareHouseOperateId(String id) {
		return dao.removeBusinessWareHouseOperateId(Long.valueOf(id));
	}

	/**
	 * @param brandname
	 * @return int
	 */
	public int getCount(BusinessWareHouseVO vo) {
		String hql = "select count(*) from BusinessWareHouseOperate a, BusinessWareHouse b, "
				+ " BusinessGoods c, BusinessCategory d, BusinessWeight e"
				+ " where a.wareHouseId = b.id and a.goodsId = c.id and c.goodsCategoryId = d.id"
				+ " and a.weightId = e.id";
		if (vo != null && vo.getBusinessWareHouseOperate() != null) {
			if (vo.getBusinessWareHouseOperate().getWareHouseId() != null) {
				hql = hql + " and a.wareHouseId = "
						+ vo.getBusinessWareHouseOperate().getWareHouseId();
			}
			if (vo.getBusinessWareHouseOperate().getGoodsId() != null) {
				hql = hql + " and a.goodsId = "
						+ vo.getBusinessWareHouseOperate().getGoodsId();
			}
			if (vo.getBusinessWareHouseOperate().getCategoryId() != null) {
				hql = hql + " and c.goodsCategoryId = "
						+ vo.getBusinessWareHouseOperate().getCategoryId();
			}
			if (vo.getBusinessWareHouseOperate().getCount() != null) {
				hql = hql + " and a.count = "
						+ vo.getBusinessWareHouseOperate().getCount();
			}
			if (vo.getBusinessWareHouseOperate().getOperationStatus() != null) {
				hql = hql + " and a.operationStatus = "
						+ vo.getBusinessWareHouseOperate().getOperationStatus();
			}
			if (vo.getBusinessWareHouseOperate().getReasonStatus() != null) {
				hql = hql + " and a.reasonStatus = "
						+ vo.getBusinessWareHouseOperate().getReasonStatus();
			}
			if (vo.getBusinessWareHouseOperate().getPrice() != null) {
				hql = hql + " and a.price = "
						+ vo.getBusinessWareHouseOperate().getPrice();
			}
			if (vo.getBusinessWareHouseOperate().getWeightId() != null) {
				hql = hql + " and a.weightId = "
						+ vo.getBusinessWareHouseOperate().getWeightId();
			}
			if (vo.getBusinessWareHouseOperate().getSupplierStaffId() != null) {
				hql = hql + " and a.supplierStaffId = "
						+ vo.getBusinessWareHouseOperate().getSupplierStaffId();
			}
			if (vo.getBusinessWareHouseOperate().getPurchaserStaffId() != null) {
				hql = hql + " and a.purchaserStaffId = "
						+ vo.getBusinessWareHouseOperate().getPurchaserStaffId();
			}
			if (vo.getBusinessWareHouseOperate().getRemark() != null) {
				hql = hql + " and a.remark = "
						+ vo.getBusinessWareHouseOperate().getRemark();
			}

			if (!StringHelper.isNull(vo.getCreateStartTime())) {
				hql = hql + " and a.createTime >='" + vo.getCreateStartTime()
						+ "'";
			}
			if (!StringHelper.isNull(vo.getCreateEndTime())) {
				hql = hql + " and a.createTime <='" + vo.getCreateEndTime() + "'";
			}
			if (vo.getBusinessWareHouseOperate().getCreateStaffId() != null) {
				hql = hql + " and a.createStaffId ="
						+ vo.getBusinessWareHouseOperate().getCreateStaffId();
			}
			if (!StringHelper.isNull(vo.getModifyStartTime())) {
				hql = hql + " and a.modifyTime <='" + vo.getModifyStartTime()
						+ "'";
			}
			if (!StringHelper.isNull(vo.getModifyEndTime())) {
				hql = hql + " and a.modifyTime >='" + vo.getModifyEndTime() + "'";
			}
			if (vo.getBusinessWareHouseOperate().getModifyStaffId() != null) {
				hql = hql + " and a.modifyStaffId ="
						+ vo.getBusinessWareHouseOperate().getModifyStaffId();
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
		String hql = "from BusinessWareHouseOperate a, BusinessWareHouse b, "
				+ " BusinessGoods c, BusinessCategory d, BusinessWeight e"
				+ " where a.wareHouseId = b.id and a.goodsId = c.id and c.goodsCategoryId = d.id"
				+ " and a.weightId = e.id";
		if (vo != null && vo.getBusinessWareHouseOperate() != null) {
			if (vo.getBusinessWareHouseOperate().getWareHouseId() != null) {
				hql = hql + " and a.wareHouseId = "
						+ vo.getBusinessWareHouseOperate().getWareHouseId();
			}
			if (vo.getBusinessWareHouseOperate().getGoodsId() != null) {
				hql = hql + " and a.goodsId = "
						+ vo.getBusinessWareHouseOperate().getGoodsId();
			}
			if (vo.getBusinessWareHouseOperate().getCategoryId() != null) {
				hql = hql + " and c.goodsCategoryId = "
						+ vo.getBusinessWareHouseOperate().getCategoryId();
			}
			if (vo.getBusinessWareHouseOperate().getCount() != null) {
				hql = hql + " and a.count = "
						+ vo.getBusinessWareHouseOperate().getCount();
			}
			if (vo.getBusinessWareHouseOperate().getOperationStatus() != null) {
				hql = hql + " and a.operationStatus = "
						+ vo.getBusinessWareHouseOperate().getOperationStatus();
			}
			if (vo.getBusinessWareHouseOperate().getReasonStatus() != null) {
				hql = hql + " and a.reasonStatus = "
						+ vo.getBusinessWareHouseOperate().getReasonStatus();
			}
			if (vo.getBusinessWareHouseOperate().getPrice() != null) {
				hql = hql + " and a.price = "
						+ vo.getBusinessWareHouseOperate().getPrice();
			}
			if (vo.getBusinessWareHouseOperate().getWeightId() != null) {
				hql = hql + " and a.weightId = "
						+ vo.getBusinessWareHouseOperate().getWeightId();
			}
			if (vo.getBusinessWareHouseOperate().getSupplierStaffId() != null) {
				hql = hql + " and a.supplierStaffId = "
						+ vo.getBusinessWareHouseOperate().getSupplierStaffId();
			}
			if (vo.getBusinessWareHouseOperate().getPurchaserStaffId() != null) {
				hql = hql + " and a.purchaserStaffId = "
						+ vo.getBusinessWareHouseOperate().getPurchaserStaffId();
			}
			if (vo.getBusinessWareHouseOperate().getRemark() != null) {
				hql = hql + " and a.remark = "
						+ vo.getBusinessWareHouseOperate().getRemark();
			}

			if (!StringHelper.isNull(vo.getCreateStartTime())) {
				hql = hql + " and a.createTime >='" + vo.getCreateStartTime()
						+ "'";
			}
			if (!StringHelper.isNull(vo.getCreateEndTime())) {
				hql = hql + " and a.createTime <='" + vo.getCreateEndTime() + "'";
			}
			if (vo.getBusinessWareHouseOperate().getCreateStaffId() != null) {
				hql = hql + " and a.createStaffId ="
						+ vo.getBusinessWareHouseOperate().getCreateStaffId();
			}
			if (!StringHelper.isNull(vo.getModifyStartTime())) {
				hql = hql + " and a.modifyTime <='" + vo.getModifyStartTime()
						+ "'";
			}
			if (!StringHelper.isNull(vo.getModifyEndTime())) {
				hql = hql + " and a.modifyTime >='" + vo.getModifyEndTime() + "'";
			}
			if (vo.getBusinessWareHouseOperate().getModifyStaffId() != null) {
				hql = hql + " and a.modifyStaffId ="
						+ vo.getBusinessWareHouseOperate().getModifyStaffId();
			}
		}
		if (!StringHelper.isNull(orderField) && !StringHelper.isNull(direction)) {
			hql = hql + " order by " + orderField + " " + direction;
		} else {
			hql += " order by d.id, c.goodsName asc";
		}
		List l = commonDao.getEntitiesByPropertiesWithHql(hql, curPage,
				pageSize);
		List<BusinessWareHouseVO> resList = new ArrayList<BusinessWareHouseVO>();
		for (int i = 0; i < l.size(); i++) {
			Object[] obj = (Object[]) l.get(i);
			BusinessWareHouseOperate bho = (BusinessWareHouseOperate) obj[0];
			BusinessWareHouse bwh = (BusinessWareHouse) obj[1];
			BusinessGoods bg = (BusinessGoods) obj[2];
			BusinessCategory bc = (BusinessCategory) obj[3];
			BusinessWeight bw = (BusinessWeight) obj[4];
			BusinessWareHouseVO businessWareHouseVO = new BusinessWareHouseVO();
			businessWareHouseVO.setBusinessWareHouseOperate(bho);
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
	public List<BusinessWareHouseOperate> listAll() {
		return dao.findAll();
	}

}
