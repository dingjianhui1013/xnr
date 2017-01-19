/**
 *2012-5-11 上午9:00:02
 */
package com.xnradmin.core.service.mall.order;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cntinker.util.StringHelper;
import com.xnradmin.core.service.StaffService;
import com.xnradmin.core.service.common.status.StatusService;
import com.xnradmin.core.dao.CommonDAO;
import com.xnradmin.core.dao.mall.order.OrderGoodsRelationDAO;
import com.xnradmin.constant.ViewConstant;
import com.xnradmin.po.mall.commodity.Goods;
import com.xnradmin.po.mall.order.OrderGoodsRelation;
import com.xnradmin.po.mall.order.OrderRecord;
import com.xnradmin.vo.mall.OrderVO;

/**
 * @autohr: xiang_liu
 * 
 */
@Service("OrderGoodsRelationService")
public class OrderGoodsRelationService {

	@Autowired
	private OrderGoodsRelationDAO dao;
	
	@Autowired
    private CommonDAO commonDao;

	@Autowired
	private StaffService staffService;
	
	@Autowired
	private StatusService statusService;
	
	static Log log = LogFactory.getLog(OrderGoodsRelationService.class);

	/**
	 * 
	 * @param po
	 * @return int
	 */
	public int save(OrderGoodsRelation po) {
		dao.save(po);
		return 0;
	}

	public OrderGoodsRelation findByid(String id) {
		return dao.findById(Long.valueOf(id));
	}

	/**	  
	 * @param po
	 * @return int
	 */
	public int modify(OrderGoodsRelation po) {
			OrderGoodsRelation old = new OrderGoodsRelation();
			old = findByid(po.getId().toString());
			if(po.getGoodsId()==null){
				po.setGoodsId(old.getGoodsId());
			}
			if(po.getOrderRecordId()==null){
				po.setOrderRecordId(old.getOrderRecordId());
			}
			if(po.getClientUserId()==null){
				po.setClientUserId(old.getClientUserId());
			}
			if(po.getGoodsCount()==null){
				po.setGoodsCount(old.getGoodsCount());
			}
			if(po.getCurrentPrice()==null){
				po.setCurrentPrice(old.getCurrentPrice());
			}
			if(po.getCurrentPriceType()==null){
				po.setCurrentPriceType(old.getCurrentPriceType());
			}
			if(po.getPrimaryConfigurationId()==null){
				po.setPrimaryConfigurationId(old.getPrimaryConfigurationId());
			}
			if(StringHelper.isNull(po.getStaffId())){
				po.setStaffId(old.getStaffId());
			}
			po.setOrderGoodsRelationTime(new Timestamp(System.currentTimeMillis()));
			this.dao.merge(po);
		return 0;
	}

	public void del(String id){
		OrderGoodsRelation po = this.dao.findById(Long.valueOf(id));
        this.dao.delete(po);
    }
	
	public int removeOrderGoodsRelationId(String id) {
		return dao.removeOrderGoodsRelationId(Long.valueOf(id));
	}
	
	
	/**
	 * @param brandname
	 * @return int
	 */
	public int getCount(String orderRecordId, String clientUserId, String goodsId, 
			String goodsCount, String primaryConfigurationId, String staffId, 
			String currentPriceType, String orderGoodsRelationStartTime, 
			String orderGoodsRelationEndTime) {
		String hql = "select count(*) from OrderGoodsRelation a, OrderRecord b, Goods c where "
				+ " a.orderRecordId = b.id and a.goodsId=c.id";
		if (!StringHelper.isNull(orderRecordId)) {
			hql = hql + " and a.orderRecordId = "+ orderRecordId ;
		}
		if (!StringHelper.isNull(goodsId)) {
			hql = hql + " and a.goodsId = "+ goodsId ;
		}
		if (!StringHelper.isNull(clientUserId)) {
			hql = hql + " and a.clientUserId = "+ clientUserId ;
		}
		if (!StringHelper.isNull(goodsCount)) {
			hql = hql + " and a.goodsCount = "+ goodsCount ;
		}
		if (!StringHelper.isNull(currentPriceType)) {
			hql = hql + " and a.currentPriceType = "+ currentPriceType ;
		}
		if (!StringHelper.isNull(primaryConfigurationId)) {
			hql = hql + " and a.primaryConfigurationId = "+ primaryConfigurationId ;
		}
		if (!StringHelper.isNull(staffId)) {
			hql = hql + " and a.staffId = '" + staffId + "'";
		}
		if (!StringHelper.isNull(orderGoodsRelationStartTime)) {
			hql = hql + " and a.orderGoodsRelationTime >='"+ orderGoodsRelationStartTime +"'" ;
		}
		if (!StringHelper.isNull(orderGoodsRelationEndTime)) {
			hql = hql + " and a.orderGoodsRelationTime <='"+ orderGoodsRelationEndTime +"'" ;
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
	public List<OrderVO> listVO(String orderRecordId, String clientUserId, String goodsId,
			String goodsCount, String primaryConfigurationId, String staffId,
			String currentPriceType, String createStartTime, String createEndTime,
			int curPage, int pageSize, String orderField, String direction) {
		String hql = "from OrderGoodsRelation a, OrderRecord b, Goods c where "
				+ " a.orderRecordId = b.id and a.goodsId=c.id";
		if (!StringHelper.isNull(orderRecordId)) {
			hql = hql + " and a.orderRecordId = "+ orderRecordId ;
		}
		if (!StringHelper.isNull(goodsId)) {
			hql = hql + " and a.goodsId = "+ goodsId ;
		}
		if (!StringHelper.isNull(clientUserId)) {
			hql = hql + " and a.clientUserId = "+ clientUserId ;
		}
		if (!StringHelper.isNull(goodsCount)) {
			hql = hql + " and a.goodsCount = "+ goodsCount ;
		}
		if (!StringHelper.isNull(currentPriceType)) {
			hql = hql + " and a.currentPriceType = "+ currentPriceType ;
		}
		if (!StringHelper.isNull(primaryConfigurationId)) {
			hql = hql + " and a.primaryConfigurationId = "+ primaryConfigurationId ;
		}
		if (!StringHelper.isNull(staffId)) {
			hql = hql + " and a.staffId = '" + staffId + "'";
		}
		if (!StringHelper.isNull(createStartTime)) {
			hql = hql + " and a.orderGoodsRelationTime >='"+ createStartTime +"'" ;
		}
		if (!StringHelper.isNull(createEndTime)) {
			hql = hql + " and a.orderGoodsRelationTime <='"+ createEndTime +"'" ;
		}
		if (!StringHelper.isNull(orderField) && !StringHelper.isNull(direction)) {
			hql = hql + " order by " + orderField + " " + direction;
		}else{
			hql +=" order by a.id";
		}
		List l = commonDao.getEntitiesByPropertiesWithHql(hql, curPage, pageSize); // .findByQuerySplitPage(hql,curPage,pageSize);
		List<OrderVO> resList = new ArrayList<OrderVO>();
		for (int i = 0; i < l.size(); i++) {
			Object[] obj = (Object[]) l.get(i);
			OrderGoodsRelation orderGoodsRelation = (OrderGoodsRelation)obj[0];
			OrderRecord orderRecord = (OrderRecord)obj[1];
			Goods goods = (Goods)obj[2];
			OrderVO vo = new OrderVO();
			//订单商品对应表
			if(orderGoodsRelation.getId()!=null){
				vo.setOrderGoodsRelationId(orderGoodsRelation.getId().toString());
			}
			if(orderGoodsRelation.getClientUserId()!=null){
				vo.setOrderRecordClientUserId(orderGoodsRelation.getClientUserId().toString());
			}
			if(orderGoodsRelation.getStaffId()!=null){
				vo.setOrderGoodsRelationStaffId(orderGoodsRelation.getStaffId().toString());
			}
			if(orderGoodsRelation.getOrderRecordId()!=null){
				vo.setOrderGoodsRelationOrderRecordId(orderGoodsRelation.getOrderRecordId().toString());
			}
			if(orderGoodsRelation.getGoodsId()!=null){
				vo.setOrderGoodsRelationGoodsId(orderGoodsRelation.getGoodsId().toString());
			}
			if(orderGoodsRelation.getGoodsCount()!=null){
				vo.setOrderGoodsRelationGoodsCount(orderGoodsRelation.getGoodsCount().toString());
			}
			if(orderGoodsRelation.getCurrentPrice()!=null){
				vo.setOrderGoodsRelationCurrentPrice(orderGoodsRelation.getCurrentPrice().toString());
			}
			if(orderGoodsRelation.getCurrentPriceType()!=null){
				vo.setOrderGoodsRelationCurrentPriceType(orderGoodsRelation.getCurrentPriceType().toString());
			}
			if(orderGoodsRelation.getPrimaryConfigurationId()!=null){
				vo.setOrderGoodsRelationPrimaryConfigurationId(orderGoodsRelation.getPrimaryConfigurationId().toString());
			}
			if(orderGoodsRelation.getOrderGoodsRelationTime()!=null){
				vo.setOrderGoodsRelationTime(StringHelper.getSystime(
                    ViewConstant.PAGE_DATE_FORMAT_SEC,orderGoodsRelation.getOrderGoodsRelationTime().getTime()));
			}
			
			//订单表
			if(orderRecord.getId()!=null){
				vo.setOrderRecordId(orderRecord.getId().toString());
			}
			if(orderRecord.getClientUserId()!=null){
				vo.setOrderRecordClientUserId(orderRecord.getClientUserId().toString());
			}
			vo.setOrderRecordStaffId(orderRecord.getStaffId());
			vo.setOrderRecordUserRealMobile(orderRecord.getUserRealMobile());
			vo.setOrderRecordUserRealPlane(orderRecord.getUserRealPlane());
			vo.setOrderRecordUserRealName(orderRecord.getUserRealName());
			vo.setOrderRecordUserRealAddress(orderRecord.getUserRealAddress());
			vo.setOrderRecordUserRealPostcode(orderRecord.getUserRealPostcode());
			if(orderRecord.getUserRealTime()!=null){
				vo.setOrderRecordUserRealTime(orderRecord.getUserRealTime().toString());
			}
			
			if(orderRecord.getPaymentType()!=null){
				vo.setOrderRecordPaymentType(orderRecord.getPaymentType().toString());
			}
			if(orderRecord.getPaymentStatus()!=null){
				vo.setOrderRecordPaymentStatus(orderRecord.getPaymentStatus().toString());
			}
			if(orderRecord.getPaymentProvider()!=null){
				vo.setOrderRecordPaymentProvider(orderRecord.getPaymentProvider().toString());
			}
			if(orderRecord.getPaymentTime()!=null){
				vo.setOrderRecordPaymentTime(StringHelper.getSystime(
						ViewConstant.PAGE_DATE_FORMAT_SEC, orderRecord.getPaymentTime()
								.getTime()));
			}
			if(orderRecord.getOperateTime()!=null){
				vo.setOrderRecordOperateTime(StringHelper.getSystime(
						ViewConstant.PAGE_DATE_FORMAT_SEC, orderRecord.getOperateTime()
								.getTime()));
			}
			if(orderRecord.getOperateStatus()!=null){
				vo.setOrderRecordOperateStatus(orderRecord.getOperateStatus().toString());
			}
			if(orderRecord.getCreateTime()!=null){
				vo.setOrderRecordCreateTime(StringHelper.getSystime(
						ViewConstant.PAGE_DATE_FORMAT_SEC, orderRecord.getCreateTime()
								.getTime()));
			}
			if(orderRecord.getOriginalPrice()!=null){
				vo.setOrderRecordOriginalPrice(orderRecord.getOriginalPrice().toString());
			}
			if(orderRecord.getTotalPrice()!=null){
				vo.setOrderRecordTotalPrice(orderRecord.getTotalPrice().toString());
			}
			if(orderRecord.getDeliveryStaffId()!=null){
				vo.setOrderRecordDliveryStaffId(orderRecord.getDeliveryStaffId().toString());
			}
			if(orderRecord.getDeliveryStartTime()!=null){
				vo.setOrderRecordDeliveryStartTime(StringHelper.getSystime(
						ViewConstant.PAGE_DATE_FORMAT_SEC, orderRecord.getDeliveryStartTime()
								.getTime()));
			}
			if(orderRecord.getDeliveryEndTime()!=null){
				vo.setOrderRecordDeliveryEndTime(StringHelper.getSystime(
						ViewConstant.PAGE_DATE_FORMAT_SEC, orderRecord.getDeliveryEndTime()
								.getTime()));
			}
			if(orderRecord.getLogisticsCompanyId()!=null){
				vo.setOrderRecordLogisticsCompanyId(orderRecord.getLogisticsCompanyId().toString());
			}
			if(orderRecord.getLogisticsCompanyName()!=null){
				vo.setOrderRecordLogisticsCompanyName(orderRecord.getLogisticsCompanyName().toString());
			}
			if(orderRecord.getDeliveryStatus()!=null){
				vo.setOrderRecordDeliveryStatus(orderRecord.getDeliveryStatus().toString());
			}
			if(orderRecord.getSourceId()!=null){
				vo.setOrderRecordSourceId(orderRecord.getSourceId().toString());
			}
			if(orderRecord.getSourceType()!=null){
				vo.setOrderRecordSourceType(orderRecord.getSourceType().toString());
			}
			vo.setOrderRecordSerNo(orderRecord.getSerNo());
			if(orderRecord.getSellerId()!=null){
				vo.setOrderRecordSellerId(orderRecord.getSellerId().toString());
			}
			if(orderRecord.getCusId()!=null){
				vo.setOrderRecordCusId(orderRecord.getCusId().toString());
			}
			if(orderRecord.getPrimaryConfigurationId()!=null){
				vo.setOrderRecordPrimaryConfigurationId(orderRecord.getPrimaryConfigurationId().toString());
			}
			vo.setGoods(goods);
			resList.add(vo);
		}
		return resList;
	}

	/**
	 * 
	 * @param dishId
	 * @return int
	 */
	public int removeOrderRecordId(Long orderRecordId){

        log.debug("removeOrderRecordId: " + orderRecordId);
        try{
            String queryString = "delete from OrderGoodsRelation as model where model.orderRecordId = "
                    + orderRecordId;
            return commonDao.executeUpdateOrDelete(queryString);
        }catch(RuntimeException re){
            log.error("removeDishId failed",re);
            throw re;
        }
    }
	
	/**
	 * 
	 * @param dishId
	 * @return int
	 */
	public int removeOrderRecordById(Long id){

        log.debug("setOffOrderRecordId: " + id);
        try{
            String queryString = "update BusinessOrderGoodsRelation br set br.delFlag=1 where br.id="
                    + id;
            return commonDao.executeUpdateOrDelete(queryString);
        }catch(RuntimeException re){
            log.error("removeDishId failed",re);
            throw re;
        }
    }
	/**
	 * @return List<OrderGoodsRelation>
	 */
	public List<OrderGoodsRelation> listAll() {
		return dao.findAll();
	}

}
