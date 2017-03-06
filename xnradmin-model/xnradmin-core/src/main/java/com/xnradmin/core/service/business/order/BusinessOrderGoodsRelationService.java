/**
 *2012-5-11 上午9:00:02
 */
package com.xnradmin.core.service.business.order;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cntinker.util.StringHelper;
import com.xnradmin.core.dao.CommonDAO;
import com.xnradmin.core.dao.business.order.BusinessOrderGoodsRelationDAO;
import com.xnradmin.core.service.StaffService;
import com.xnradmin.core.service.business.commodity.BusinessCategoryService;
import com.xnradmin.core.service.common.status.StatusService;
import com.xnradmin.po.business.BusinessCategory;
import com.xnradmin.po.business.BusinessGoods;
import com.xnradmin.po.business.BusinessOrderGoodsRelation;
import com.xnradmin.po.business.BusinessOrderRecord;
import com.xnradmin.po.business.Combo;
import com.xnradmin.vo.business.BusinessGoodsVO;
import com.xnradmin.vo.business.BusinessOrderRelationVO;
import com.xnradmin.vo.business.BusinessOrderVO;

/**
 * @autohr: xiang_liu
 * 
 */
@Service("BusinessOrderGoodsRelationService")
public class BusinessOrderGoodsRelationService {

	@Autowired
	private BusinessOrderGoodsRelationDAO dao;

	@Autowired
	private CommonDAO commonDao;

	@Autowired
	private StaffService staffService;

	@Autowired
	private StatusService statusService;
	
	@Autowired
	private BusinessCategoryService businessCategoryService;

	static Log log = LogFactory.getLog(BusinessOrderGoodsRelationService.class);

	/**
	 * 
	 * @param po
	 * @return int
	 */
	public int save(BusinessOrderGoodsRelation po) {
		dao.save(po);
		return 0;
	}

	public BusinessOrderGoodsRelation findByid(String id) {
		return dao.findById(Long.valueOf(id));
	}
	
	public List<BusinessOrderGoodsRelation> findByExample(BusinessOrderGoodsRelation instance) {
		return dao.findByExample(instance);
	}

	
	public int removeAllocation(Integer id) {
		String hql = "update BusinessOrderGoodsRelation br set br.delFlag=null , br.allocationId=null where br.allocationId='"+id+"'";
		return commonDao.executeUpdateOrDelete(hql);
	}
	/**
	 * @param po
	 * @return int
	 */
	public int modify(BusinessOrderGoodsRelation po) {
		BusinessOrderGoodsRelation old = new BusinessOrderGoodsRelation();
		old = findByid(po.getId().toString());
		if (po.getGoodsId() == null) {
			po.setGoodsId(old.getGoodsId());
		}
		if (po.getOrderRecordId() == null) {
			po.setOrderRecordId(old.getOrderRecordId());
		}
		if (po.getClientUserId() == null) {
			po.setClientUserId(old.getClientUserId());
		}
		if (po.getGoodsCount() == null) {
			po.setGoodsCount(old.getGoodsCount());
		}
		if (po.getCurrentPrice() == null) {
			po.setCurrentPrice(old.getCurrentPrice());
		}
		if (po.getCurrentPriceType() == null) {
			po.setCurrentPriceType(old.getCurrentPriceType());
		}
		if (po.getPrimaryConfigurationId() == null) {
			po.setPrimaryConfigurationId(old.getPrimaryConfigurationId());
		}
		if (StringHelper.isNull(po.getStaffId())) {
			po.setStaffId(old.getStaffId());
		}
		po.setOrderGoodsRelationTime(new Timestamp(System.currentTimeMillis()));
		this.dao.merge(po);
		return 0;
	}

	public void del(String id) {
		BusinessOrderGoodsRelation po = this.dao.findById(Long.valueOf(id));
		this.dao.delete(po);
	}

	public int removeOrderGoodsRelationId(String id) {
		return dao.removeBusinessOrderGoodsRelationId(Long.valueOf(id));
	}

	/**
	 * @param brandname
	 * @return int
	 */
	public int getCount(BusinessOrderVO vo) {
		String hql = "select count(*) from BusinessOrderGoodsRelation a, BusinessOrderRecord b, BusinessGoods c where "
				+ " a.orderRecordId = b.id and a.goodsId=c.id";
		if (vo != null && vo.getBusinessOrderGoodsRelation() != null) {
			if (vo.getBusinessOrderGoodsRelation().getOrderRecordId() != null) {
				hql = hql + " and a.orderRecordId = "
						+ vo.getBusinessOrderGoodsRelation().getOrderRecordId();
			}
			if (vo.getBusinessOrderGoodsRelation().getGoodsId() != null) {
				hql = hql + " and a.goodsId = "
						+ vo.getBusinessOrderGoodsRelation().getGoodsId();
			}
			if (vo.getBusinessOrderGoodsRelation().getClientUserId() != null) {
				hql = hql + " and a.clientUserId = "
						+ vo.getBusinessOrderGoodsRelation().getClientUserId();
			}
			if (vo.getBusinessOrderGoodsRelation().getGoodsCount() != null) {
				hql = hql + " and a.goodsCount = "
						+ vo.getBusinessOrderGoodsRelation().getGoodsCount();
			}
			if (vo.getBusinessOrderGoodsRelation().getCurrentPriceType() != null) {
				hql = hql
						+ " and a.currentPriceType = "
						+ vo.getBusinessOrderGoodsRelation()
								.getCurrentPriceType();
			}
			if (vo.getBusinessOrderGoodsRelation().getPrimaryConfigurationId() != null) {
				hql = hql
						+ " and a.primaryConfigurationId = "
						+ vo.getBusinessOrderGoodsRelation()
								.getPrimaryConfigurationId();
			}
			if (vo.getBusinessOrderGoodsRelation().getStaffId() != null) {
				hql = hql + " and a.staffId = '"
						+ vo.getBusinessOrderGoodsRelation().getStaffId() + "'";
			}
			if (!StringHelper.isNull(vo.getCreateStartTime())) {
				hql = hql + " and a.orderGoodsRelationTime >='"
						+ vo.getCreateStartTime() + "'";
			}
			if (!StringHelper.isNull(vo.getCreateEndTime())) {
				hql = hql + " and a.orderGoodsRelationTime <='"
						+ vo.getCreateEndTime() + "'";
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
	public List<BusinessOrderVO> listVO(BusinessOrderVO vo, int curPage, int pageSize,
			String orderField, String direction) {
		String hql = "from BusinessOrderGoodsRelation a, BusinessOrderRecord b, BusinessGoods c where "
				+ " a.orderRecordId = b.id and a.goodsId=c.id";
		if (vo != null && vo.getBusinessOrderGoodsRelation() != null) {
			if (vo.getBusinessOrderGoodsRelation().getOrderRecordId() != null) {
				hql = hql + " and a.orderRecordId = "
						+ vo.getBusinessOrderGoodsRelation().getOrderRecordId();
			}
			if (vo.getBusinessOrderGoodsRelation().getGoodsId() != null) {
				hql = hql + " and a.goodsId = "
						+ vo.getBusinessOrderGoodsRelation().getGoodsId();
			}
			if (vo.getBusinessOrderGoodsRelation().getClientUserId() != null) {
				hql = hql + " and a.clientUserId = "
						+ vo.getBusinessOrderGoodsRelation().getClientUserId();
			}
			if (vo.getBusinessOrderGoodsRelation().getGoodsCount() != null) {
				hql = hql + " and a.goodsCount = "
						+ vo.getBusinessOrderGoodsRelation().getGoodsCount();
			}
			if (vo.getBusinessOrderGoodsRelation().getCurrentPriceType() != null) {
				hql = hql
						+ " and a.currentPriceType = "
						+ vo.getBusinessOrderGoodsRelation()
								.getCurrentPriceType();
			}
			if (vo.getBusinessOrderGoodsRelation().getPrimaryConfigurationId() != null) {
				hql = hql
						+ " and a.primaryConfigurationId = "
						+ vo.getBusinessOrderGoodsRelation()
								.getPrimaryConfigurationId();
			}
			if (vo.getBusinessOrderGoodsRelation().getStaffId() != null) {
				hql = hql + " and a.staffId = '"
						+ vo.getBusinessOrderGoodsRelation().getStaffId() + "'";
			}
			if (!StringHelper.isNull(vo.getCreateStartTime())) {
				hql = hql + " and a.orderGoodsRelationTime >='"
						+ vo.getCreateStartTime() + "'";
			}
			if (!StringHelper.isNull(vo.getCreateEndTime())) {
				hql = hql + " and a.orderGoodsRelationTime <='"
						+ vo.getCreateEndTime() + "'";
			}
		}
		if (!StringHelper.isNull(orderField) && !StringHelper.isNull(direction)) {
			hql = hql + " order by " + orderField + " " + direction;
		} else {
			hql += " order by c.goodsCategoryId";
		}
		List l = commonDao.getEntitiesByPropertiesWithHql(hql, curPage,
				pageSize); // .findByQuerySplitPage(hql,curPage,pageSize);
		List<BusinessOrderVO> resList = new ArrayList<BusinessOrderVO>();
		for (int i = 0; i < l.size(); i++) {
			Object[] obj = (Object[]) l.get(i);
			BusinessOrderGoodsRelation businessOrderGoodsRelation = (BusinessOrderGoodsRelation) obj[0];
			BusinessOrderRecord orderRecord = (BusinessOrderRecord) obj[1];
			BusinessGoods goods = (BusinessGoods) obj[2];
			
			BusinessCategory cate = businessCategoryService.findByid(goods.getGoodsCategoryId());
			BusinessCategory pcate = businessCategoryService.findByid(cate.getCategoryParentId().toString());
			//将取得的商品数据放入商品VO中
			BusinessGoodsVO businessGoodsVo = new BusinessGoodsVO();
			businessGoodsVo.setBusinessGoods(goods);
			businessGoodsVo.setBusinessCategory(cate);
			businessGoodsVo.setBusinessParentCategory(pcate);
			
			//将取得的订单数据放入订单VO中
			BusinessOrderVO businessOrderVo = new BusinessOrderVO();
			businessOrderVo.setBusinessGoodsVO(businessGoodsVo); //存入商品VO
			businessOrderVo.setBusinessOrderGoodsRelation(businessOrderGoodsRelation); //存入订单商品对应表PO
			businessOrderVo.setBusinessOrderRecord(orderRecord); //存入订单PO
			
			
			
			
			resList.add(businessOrderVo);
		}
		return resList;
	}

	/**
	 * 
	 * @param dishId
	 * @return int
	 */
	public int removeOrderRecordId(Long orderRecordId) {

		log.debug("removeOrderRecordId: " + orderRecordId);
		try {
			String queryString = "delete from BusinessOrderGoodsRelation as model where model.orderRecordId = "
					+ orderRecordId;
			return commonDao.executeUpdateOrDelete(queryString);
		} catch (RuntimeException re) {
			log.error("removeDishId failed", re);
			throw re;
		}
	}

	/**
	 * @return List<OrderGoodsRelation>
	 */
	public List<BusinessOrderGoodsRelation> listAll() {
		return dao.findAll();
	}

	public List<BusinessGoods> findGoodsName(Long id) {
		String hql = "from BusinessOrderGoodsRelation a ,BusinessGoods b where a.goodsId=b.id and a.orderRecordId='"+id+"'";
		List l = commonDao.getEntitiesByPropertiesWithHql(hql, 0, 0);
		List<BusinessGoods> goods  = new ArrayList<BusinessGoods>();
		for (int i = 0; i < l.size(); i++) {
			Object[] obj = (Object[]) l.get(i);
			goods.add((BusinessGoods) obj[1]);
		}
		return goods;
	}

	public List<Combo> findComboName(Long id)
	{
		String hql = "from BusinessOrderGoodsRelation a ,Combo c where a.comboId=c.id and a.orderRecordId='"+id+"'";
		List l = commonDao.getEntitiesByPropertiesWithHql(hql, 0, 0);
		List<Combo> combo  = new ArrayList<Combo>();
		for (int i = 0; i < l.size(); i++) {
			Object[] obj = (Object[]) l.get(i);
			combo.add((Combo) obj[1]);
		}
		return combo;
	}
	
	public List<BusinessOrderRelationVO> findByOrderRecordId(Long borId) {
		String hql = "from BusinessOrderGoodsRelation a ,BusinessGoods b where a.goodsId=b.id and a.orderRecordId='"+borId+"'";
		List l = commonDao.getEntitiesByPropertiesWithHql(hql, 0, 0);
		List<BusinessOrderRelationVO> borvo = new ArrayList<BusinessOrderRelationVO>();
		for (int i = 0; i < l.size(); i++) {
			Object[] obj = (Object[]) l.get(i);
			BusinessOrderRelationVO bo = new BusinessOrderRelationVO();
			BusinessOrderGoodsRelation bogr = (BusinessOrderGoodsRelation)obj[0];
			BusinessGoods bg = (BusinessGoods)obj[1];
			bo.setOrderGoodsRelation(bogr);
			bo.setBusinessGoods(bg);
			borvo.add(bo);
		}
		return borvo;
	}
	public List<BusinessOrderRelationVO> findByComboOrderRecordId(Long borId) {
		String hql = "from BusinessOrderGoodsRelation a ,Combo c where a.comboId=c.id and a.orderRecordId='"+borId+"'";
		List l = commonDao.getEntitiesByPropertiesWithHql(hql, 0, 0);
		List<BusinessOrderRelationVO> borvo = new ArrayList<BusinessOrderRelationVO>();
		for (int i = 0; i < l.size(); i++) {
			Object[] obj = (Object[]) l.get(i);
			BusinessOrderRelationVO bo = new BusinessOrderRelationVO();
			BusinessOrderGoodsRelation bogr = (BusinessOrderGoodsRelation)obj[0];
			Combo combo = (Combo)obj[1];
			bo.setOrderGoodsRelation(bogr);
			bo.setCombo(combo);
			borvo.add(bo);
		}
		return borvo;
	}
	public List<BusinessOrderGoodsRelation> findByComboOrderRecordIds(String comboId,Long borId) {
		String hql = "from BusinessOrderGoodsRelation a where a.orderRecordId='"+borId+"' and a.comboId = "+comboId;
		List<BusinessOrderGoodsRelation> list = commonDao.getEntitiesByPropertiesWithHql(hql,0,0);
		return list;
	}
	
	public List<BusinessOrderGoodsRelation> findByOrderId(Long orderId){
		String hql = "from BusinessOrderGoodsRelation where orderRecordId="+orderId;
		
		 List<BusinessOrderGoodsRelation> l = commonDao.getEntitiesByPropertiesWithHql(hql,0,0);
		 
		 return l;
	}

	public List<BusinessOrderGoodsRelation> findByGoodsId(String goodsId) {
		String hql = "from BusinessOrderGoodsRelation where goodsId = '" +goodsId+"'";
		 List<BusinessOrderGoodsRelation> l = commonDao.getEntitiesByPropertiesWithHql(hql,0,0);
		 return l;
	}
	
	
	
	
	
}
