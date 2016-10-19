/**
 *2012-7-24 下午5:09:02
 */
package com.xnradmin.core.service.stat;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cntinker.util.StringHelper;
import com.xnradmin.core.dao.CommonDAO;
import com.xnradmin.core.service.common.status.StatusService;
import com.xnradmin.po.business.BusinessCategory;
import com.xnradmin.po.business.BusinessGoods;
import com.xnradmin.po.business.BusinessWeight;
import com.xnradmin.po.business.wareHouse.BusinessWareHouse;
import com.xnradmin.po.business.wareHouse.BusinessWareHouseOperate;
import com.xnradmin.vo.business.BusinessWareHouseVO;

/**
 * @author: liuxiang
 * 
 */
@Service("BusinessWareHouseStatService")
public class BusinessWareHouseStatService {

	static Log log = LogFactory.getLog(BusinessWareHouseStatService.class);

	@Autowired
	private StatusService statusService;

	@Autowired
	private CommonDAO commonDao;

	/**
	 * 出入库汇总
	 * 
	 * @param curPage
	 * @param pageSize
	 * @param orderField
	 * @param direction
	 * @return List<Object[]>
	 */
	public List<Object[]> findInOrOutWareHouse(BusinessWareHouseVO vo,
			int curPage, int pageSize) {
		String hql = "select b.wareHouseName, c.goodsName, e.weightName, f.statusName, sum(a.count), sum(a.price) "
				+ " from BusinessWareHouseOperate a, BusinessWareHouse b, "
				+ " BusinessGoods c, BusinessCategory d, BusinessWeight e, Status f"
				+ " where a.wareHouseId = b.id and a.goodsId = c.id and c.goodsCategoryId = d.id"
				+ " and a.weightId = e.id and a.operationStatus = f.id";
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
				hql = hql
						+ " and a.purchaserStaffId = "
						+ vo.getBusinessWareHouseOperate()
								.getPurchaserStaffId();
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
				hql = hql + " and a.createTime <='" + vo.getCreateEndTime()
						+ "'";
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
				hql = hql + " and a.modifyTime >='" + vo.getModifyEndTime()
						+ "'";
			}
			if (vo.getBusinessWareHouseOperate().getModifyStaffId() != null) {
				hql = hql + " and a.modifyStaffId ="
						+ vo.getBusinessWareHouseOperate().getModifyStaffId();
			}
		}
		hql = hql + " group by b.wareHouseName, c.goodsName, e.weightName, f.statusName";
		List<Object[]> l = commonDao.getEntitiesByPropertiesWithHql(hql,
				curPage, pageSize);
		return l;
	}

	/**
	 * 出入金额汇总
	 * 
	 * @param curPage
	 * @param pageSize
	 * @param orderField
	 * @param direction
	 * @return List<Object[]>
	 */
	public List<Object[]> findWareHousePrice(BusinessWareHouseVO vo,
			int curPage, int pageSize) {
		String hql = "select b.wareHouseName, f.statusName, sum(a.price) "
				+ " from BusinessWareHouseOperate a, BusinessWareHouse b, "
				+ " BusinessGoods c, BusinessCategory d, BusinessWeight e, Status f"
				+ " where a.wareHouseId = b.id and a.goodsId = c.id and c.goodsCategoryId = d.id"
				+ " and a.weightId = e.id and a.operationStatus = f.id";
		if (vo != null || vo.getBusinessWareHouseOperate() != null) {
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
				hql = hql
						+ " and a.purchaserStaffId = "
						+ vo.getBusinessWareHouseOperate()
								.getPurchaserStaffId();
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
				hql = hql + " and a.createTime <='" + vo.getCreateEndTime()
						+ "'";
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
				hql = hql + " and a.modifyTime >='" + vo.getModifyEndTime()
						+ "'";
			}
			if (vo.getBusinessWareHouseOperate().getModifyStaffId() != null) {
				hql = hql + " and a.modifyStaffId ="
						+ vo.getBusinessWareHouseOperate().getModifyStaffId();
			}
		}
		hql = hql + " group by b.wareHouseName, f.statusName";
		List<Object[]> l = commonDao.getEntitiesByPropertiesWithHql(hql,
				curPage, pageSize);
		return l;
	}
	
	
	/**
	 * 仓库详细流水
	 * @param firstletter
	 * @param curPage
	 * @param pageSize
	 * @param orderField
	 * @param direction
	 * @return List<GetOrgListVO>
	 */
	public List<BusinessWareHouseVO> findWareHouseOperate(BusinessWareHouseVO vo, int curPage, int pageSize, 
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
}