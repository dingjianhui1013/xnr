/**
 *2012-5-11 上午9:00:02
 */
package com.xnradmin.core.service.business.order;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cntinker.util.StringHelper;
import com.xnradmin.core.dao.CommonDAO;
import com.xnradmin.core.dao.CommonJDBCDAO;
import com.xnradmin.core.dao.business.order.BusinessOrderRecordDAO;
import com.xnradmin.core.service.StaffService;
import com.xnradmin.core.service.business.combo.ComboPlanService;
import com.xnradmin.core.service.business.combo.ComboService;
import com.xnradmin.core.service.business.commodity.BusinessGoodsService;
import com.xnradmin.core.service.mall.commodity.GoodsService;
import com.xnradmin.po.CommonStaff;
import com.xnradmin.po.business.BusinessCategory;
import com.xnradmin.po.business.BusinessGoods;
import com.xnradmin.po.business.BusinessOrderGoodsRelation;
import com.xnradmin.po.business.BusinessOrderRecord;
import com.xnradmin.po.business.Combo;
import com.xnradmin.po.business.ComboPlan;
import com.xnradmin.po.client.ClientUserInfo;
import com.xnradmin.vo.business.BusinessOrderRelationVO;
import com.xnradmin.vo.business.BusinessOrderVO;
import com.xnradmin.vo.business.ComboVO;

/**
 * @autohr: xiang_liu
 * 
 */
@Service("BusinessOrderRecordService")
public class BusinessOrderRecordService {

	@Autowired
	private BusinessOrderRecordDAO dao;

	@Autowired
	private CommonDAO commonDao;
	
	@Autowired
	private BusinessOrderGoodsRelationService businessOrderGoodsRelationService;

	@Autowired
	private CommonJDBCDAO jdbcDao;

	@Autowired
	private StaffService staffService;
	
	@Autowired
	private BusinessGoodsService goodsService;
	
	@Autowired
	private ComboService comboService;
	
	@Autowired
	private ComboPlanService comboPlanService;
	
	
	static Log log = LogFactory.getLog(BusinessOrderRecordService.class);

	public void syncPrice(String startTime, String endTime) {
		//String systime = StringHelper.getSystime("yyyy-MM-dd");

		// String stime = StringHelper.getTime(startTime, "yyyy-MM-dd");
		// String etime = StringHelper.getTime(endTime, "yyyy-MM-dd");

		String updateSql = "update business_order_goods_relation a set a.CURRENT_PRICE=(";
		updateSql += "select b.GOODS_ORIGINAL_PRICE from business_goods b where b.id=a.goods_id ";
		updateSql += ") where a.ORDER_GOODS_RELATION_TIME >=";
		updateSql += "'" + startTime + "'";
		updateSql += " and a.ORDER_GOODS_RELATION_TIME <='" + endTime + "'";

		String updateSql2 = "update business_order_goods_relation a set a.ORIGINAL_PRICE=(";
		updateSql2 += "select b.GOODS_ORIGINAL_PRICE from business_goods b where b.id=a.goods_id ";
		updateSql2 += ") where a.ORDER_GOODS_RELATION_TIME >=";
		updateSql2 += "'" + startTime + "'";
		updateSql2 += " and a.ORDER_GOODS_RELATION_TIME <='" + endTime + "'";

		jdbcDao.exeSql(updateSql);
		jdbcDao.exeSql(updateSql2);

		BusinessOrderVO vo = new BusinessOrderVO();
		BusinessOrderRecord order = new BusinessOrderRecord();
		if (StringHelper.isNull(startTime))
			vo.setCreateStartTime(StringHelper.getSystime("yyyy-MM-dd")
					+ " 00:00:00");
		else
			vo.setCreateStartTime(startTime);
		if (StringHelper.isNull(endTime))
			vo.setCreateEndTime(StringHelper.getSystime("yyyy-MM-dd")
					+ " 23:59:59");
		else
			vo.setCreateEndTime(endTime);
		vo.setBusinessOrderRecord(order);
		// 先查出指定时间内的所有的独立商户
		List<BusinessOrderVO> lst = listVO(vo, 0, 0, null, null);

		for (BusinessOrderVO e : lst) {
			processOrderSyncPrice(e);
		}
	}

	private void processOrderSyncPrice(BusinessOrderVO vo) {

		if (vo == null || vo.getBusinessOrderRecord() == null
				|| vo.getBusinessOrderRecord().getId() == null)
			return;
		BusinessOrderRecord oldOrderRecord = vo.getBusinessOrderRecord();
		// 只用orderid查询
		BusinessOrderVO queryVO = new BusinessOrderVO();
		BusinessOrderRecord queryOrder = new BusinessOrderRecord();
		queryOrder.setId(oldOrderRecord.getId());
		queryVO.setBusinessOrderRecord(queryOrder);

		List<BusinessOrderVO> lst = webList(queryVO, 0, 0, null, null);
		BusinessOrderVO res = lst.get(0);
		// 累积订单价格

		Float tempOriginalPrice = 0f;
		Float tempTotalPrice = 0f;
		Float tempPurchasePrice = 0f;

		List<BusinessOrderGoodsRelation> rlst = findGoodsRelationList(oldOrderRecord
				.getId());

		for (BusinessOrderGoodsRelation e : rlst) {
			BigDecimal d1 = new BigDecimal(e.getGoodsCount());
			BigDecimal d2;
			// 计算原价
			d2 = new BigDecimal(e.getOriginalPrice());
			BigDecimal o1 = new BigDecimal(d1.multiply(d2).floatValue());
			BigDecimal o2 = new BigDecimal(tempOriginalPrice);
			tempOriginalPrice = o1.add(o2).floatValue();

			// 计算进货价
			d2 = new BigDecimal(e.getPurchasePrice());
			BigDecimal p1 = new BigDecimal(d1.multiply(d2).floatValue());
			BigDecimal p2 = new BigDecimal(tempPurchasePrice);
			tempPurchasePrice = p1.add(p2).floatValue();

			// 折扣计算(当折扣小于1的时候，则进行折扣计算)
			if (res.getStaff().getDiscount() != null
					&& res.getStaff().getDiscount() != 1
					&& res.getStaff().getDiscount() > 0) {
				BigDecimal t1 = new BigDecimal(res.getStaff().getDiscount());
				BigDecimal t2 = new BigDecimal(tempOriginalPrice);
				tempTotalPrice = t1.multiply(t2).floatValue();
				oldOrderRecord.setDiscount(res.getStaff().getDiscount());
			} else {
				tempTotalPrice = tempOriginalPrice;
				oldOrderRecord.setDiscount(1f);
			}
		}
		oldOrderRecord.setOriginalPrice(tempOriginalPrice);
		oldOrderRecord.setTotalPrice(tempTotalPrice);
		oldOrderRecord.setPurchasePrice(tempPurchasePrice);

		commonDao.modify(oldOrderRecord);
	}

	/**
	 * 
	 * @param po
	 * @return int
	 */
	public Long save(BusinessOrderRecord po) {
		Serializable cls = dao.save(po);
		return Long.valueOf(((cls.toString())));
	}

	public BusinessOrderRecord findByid(String id) {
		return dao.findById(Long.valueOf(id));
	}

	public BusinessOrderRecord findByOutOderNo(String outOrderNo) {
		String hql = "from BusinessOrderRecord where orderNo='" + outOrderNo
				+ "'";
		List<BusinessOrderRecord> l = commonDao.getEntitiesByPropertiesWithHql(
				hql, 1, 1);
		if (l == null || l.size() <= 0)
			return null;
		return l.get(0);
	}

	/**
	 * @param po
	 * @return int
	 */
	public int modify(BusinessOrderRecord po) {
		BusinessOrderRecord old = new BusinessOrderRecord();
		old = findByid(po.getId().toString());
		if (po.getClientUserId() == null) {
			po.setClientUserId(old.getClientUserId());
		}
		if (StringHelper.isNull(po.getClientUserName())) {
			po.setClientUserName(old.getClientUserName());
		}
		if (StringHelper.isNull(po.getClientUserMobile())) {
			po.setClientUserMobile(old.getClientUserMobile());
		}
		if (StringHelper.isNull(po.getClientUserEmail())) {
			po.setClientUserEmail(old.getClientUserEmail());
		}
		if (StringHelper.isNull(po.getClientUserSex())) {
			po.setClientUserSex(old.getClientUserSex());
		}
		if (po.getClientUserType() != null) {
			po.setClientUserType(old.getClientUserType());
		}
		if (StringHelper.isNull(po.getClientUserTypeName())) {
			po.setClientUserTypeName(old.getClientUserTypeName());
		}
		if (StringHelper.isNull(po.getWxOpenId())) {
			po.setWxOpenId(old.getWxOpenId());
		}
		if (StringHelper.isNull(po.getStaffId())) {
			po.setStaffId(old.getStaffId());
		}
		if (StringHelper.isNull(po.getUserRealMobile())) {
			po.setUserRealMobile(old.getUserRealMobile());
		}
		if (StringHelper.isNull(po.getUserRealName())) {
			po.setUserRealName(old.getUserRealName());
		}
		if (StringHelper.isNull(po.getUserRealPlane())) {
			po.setUserRealPlane(old.getUserRealPlane());
		}
		if (StringHelper.isNull(po.getUserRealDescription())) {
			po.setUserRealDescription(old.getUserRealDescription());
		}
		if (po.getCountryId() != null) {
			po.setCountryId(old.getCountryId());
		}
		if (StringHelper.isNull(po.getCountryName())) {
			po.setCountryName(old.getCountryName());
		}
		if (po.getProvinceId() != null) {
			po.setProvinceId(old.getProvinceId());
		}
		if (StringHelper.isNull(po.getProvinceName())) {
			po.setProvinceName(old.getProvinceName());
		}
		if (po.getCityId() != null) {
			po.setCityId(old.getCityId());
		}
		if (StringHelper.isNull(po.getCityName())) {
			po.setCityName(old.getCityName());
		}
		if (po.getAreaId() != null) {
			po.setAreaId(old.getAreaId());
		}
		if (StringHelper.isNull(po.getAreaName())) {
			po.setAreaName(old.getAreaName());
		}
		if (StringHelper.isNull(po.getUserRealAddress())) {
			po.setUserRealAddress(old.getUserRealAddress());
		}
		if (StringHelper.isNull(po.getUserRealPostcode())) {
			po.setUserRealPostcode(old.getUserRealPostcode());
		}
		if (po.getUserRealTime() == null) {
			po.setUserRealTime(old.getUserRealTime());
		}
		if (po.getPaymentType() == null) {
			po.setPaymentType(old.getPaymentType());
		}
		if (StringHelper.isNull(po.getPaymentTypeName())) {
			po.setPaymentTypeName(old.getPaymentTypeName());
		}
		if (po.getPaymentStatus() == null) {
			po.setPaymentStatus(old.getPaymentStatus());
		}
		if (StringHelper.isNull(po.getPaymentStatusName())) {
			po.setPaymentStatusName(old.getPaymentStatusName());
		}
		if (po.getPaymentProvider() == null) {
			po.setPaymentProvider(old.getPaymentProvider());
		}
		if (StringHelper.isNull(po.getPaymentProviderName())) {
			po.setPaymentProviderName(old.getPaymentProviderName());
		}
		if (po.getPaymentTime() == null) {
			po.setPaymentTime(old.getPaymentTime());
		}
		if (po.getOperateTime() == null) {
			po.setOperateTime(old.getOperateTime());
		}
		if (po.getOperateStatus() == null) {
			po.setOperateStatus(old.getOperateStatus());
		}
		if (StringHelper.isNull(po.getOperateStatusName())) {
			po.setOperateStatusName(old.getOperateStatusName());
		}
		if (po.getCreateTime() == null) {
			po.setCreateTime(old.getCreateTime());
		}
		if (po.getOriginalPrice() == null) {
			po.setOriginalPrice(old.getOriginalPrice());
		}
		if (po.getTotalPrice() == null) {
			po.setTotalPrice(old.getTotalPrice());
		}
		if (po.getLogisticsCompanyId() == null) {
			po.setLogisticsCompanyId(old.getLogisticsCompanyId());
		}
		if (po.getLogisticsCompanyName() == null) {
			po.setLogisticsCompanyName(old.getLogisticsCompanyName());
		}
		if (po.getDeliveryStaffId() == null) {
			po.setDeliveryStaffId(old.getDeliveryStaffId());
		}
		if (StringHelper.isNull(po.getDeliveryStaffName())) {
			po.setDeliveryStaffName(old.getDeliveryStaffName());
		}
		if (StringHelper.isNull(po.getDeliveryStaffMobile())) {
			po.setDeliveryStaffMobile(old.getDeliveryStaffMobile());
		}
		if (po.getDeliveryStartTime() == null) {
			po.setDeliveryStartTime(old.getDeliveryStartTime());
		}
		if (po.getDeliveryEndTime() == null) {
			po.setDeliveryEndTime(old.getDeliveryEndTime());
		}
		if (po.getDeliveryStatus() == null) {
			po.setDeliveryStatus(old.getDeliveryStatus());
		}
		if (StringHelper.isNull(po.getDeliveryStatusName())) {
			po.setDeliveryStatusName(old.getDeliveryStatusName());
		}
		if (po.getSourceName() == null) {
			po.setSourceName(old.getSourceName());
		}
		if (StringHelper.isNull(po.getSourceName())) {
			po.setSourceName(old.getSourceName());
		}
		if (po.getSourceType() == null) {
			po.setSourceType(old.getSourceType());
		}
		if (StringHelper.isNull(po.getSourceTypeName())) {
			po.setSourceTypeName(old.getSourceTypeName());
		}
		if (StringHelper.isNull(po.getSerNo())) {
			po.setSerNo(old.getSerNo());
		}
		if (po.getSellerId() == null) {
			po.setSellerId(old.getSellerId());
		}
		if (StringHelper.isNull(po.getSellerName())) {
			po.setSellerName(old.getSellerName());
		}
		if (po.getCusId() == null) {
			po.setCusId(old.getCusId());
		}
		if (StringHelper.isNull(po.getCusName())) {
			po.setCusName(old.getCusName());
		}
		if (po.getPrimaryConfigurationId() == null) {
			po.setPrimaryConfigurationId(old.getPrimaryConfigurationId());
		}
		if (StringHelper.isNull(po.getPrimaryConfigurationName())) {
			po.setPrimaryConfigurationName(old.getPrimaryConfigurationName());
		}
		this.dao.merge(po);
		return 0;
	}

	public void del(String id) {
		BusinessOrderRecord po = this.dao.findById(Long.valueOf(id));
		this.dao.delete(po);
	}

	public int removeOrderRecordId(String id) {
		return dao.removeBusinessOrderRecordId(Long.valueOf(id));
	}

	/**
	 * @param clientUserId
	 * @return int
	 */
	public int getUserOrderCount(String clientUserId) {
		String hql = "select count(*) from BusinessOrderRecord where clientUserId = "
				+ clientUserId;
		return this.commonDao.getNumberOfEntitiesWithHql(hql);
	}

	/**
	 * @param VO
	 * @return int
	 */
	public int getCount(BusinessOrderVO vo) {
		String hql = "select count(*) from BusinessOrderRecord where 1=1";
		if (vo != null && vo.getBusinessOrderRecord() != null) {
			if (!StringHelper.isNull(vo.getBusinessOrderRecord().getOrderNo())) {
				hql = hql + " and orderNo = '"
						+ vo.getBusinessOrderRecord().getOrderNo() + "'";
			}
			if (vo.getBusinessOrderRecord().getId() != null) {
				hql = hql + " and id = " + vo.getBusinessOrderRecord().getId();
			}
			if (vo.getBusinessOrderRecord().getClientUserId() != null) {
				hql = hql + " and clientUserId = "
						+ vo.getBusinessOrderRecord().getClientUserId();
			}
			if (!StringHelper.isNull(vo.getBusinessOrderRecord()
					.getClientUserName())) {
				hql = hql + " and clientUserName like '%"
						+ vo.getBusinessOrderRecord().getClientUserName()
						+ "%'";
			}
			if (!StringHelper.isNull(vo.getBusinessOrderRecord()
					.getClientUserMobile())) {
				hql = hql + " and clientUserMobile = '"
						+ vo.getBusinessOrderRecord().getClientUserMobile()
						+ "'";
			}
			if (!StringHelper.isNull(vo.getBusinessOrderRecord()
					.getClientUserEmail())) {
				hql = hql + " and clientUserEmail = '"
						+ vo.getBusinessOrderRecord().getClientUserEmail()
						+ "'";
			}
			if (!StringHelper.isNull(vo.getBusinessOrderRecord()
					.getClientUserSex())) {
				hql = hql + " and clientUserSex = '"
						+ vo.getBusinessOrderRecord().getClientUserSex() + "'";
			}
			if (vo.getBusinessOrderRecord().getClientUserType() != null) {
				hql = hql + " and clientUserType = "
						+ vo.getBusinessOrderRecord().getClientUserType();
			}
			if (!StringHelper.isNull(vo.getBusinessOrderRecord()
					.getClientUserTypeName())) {
				hql = hql + " and clientUserTypeName like '%"
						+ vo.getBusinessOrderRecord().getClientUserTypeName()
						+ "%'";
			}
			if (!StringHelper.isNull(vo.getBusinessOrderRecord().getWxOpenId())) {
				hql = hql + " and wxOpenId = '"
						+ vo.getBusinessOrderRecord().getWxOpenId() + "'";
			}
			if (!StringHelper.isNull(vo.getBusinessOrderRecord().getStaffId())) {
				hql = hql + " and staffId = '"
						+ vo.getBusinessOrderRecord().getStaffId() + "'";
			}
			if (!StringHelper.isNull(vo.getBusinessOrderRecord()
					.getUserRealName())) {
				hql = hql + " and userRealName like '%"
						+ vo.getBusinessOrderRecord().getUserRealName() + "%'";
			}
			if (vo.getBusinessOrderRecord().getCountryId() != null) {
				hql = hql + " and countryId = "
						+ vo.getBusinessOrderRecord().getCountryId();
			}
			if (!StringHelper.isNull(vo.getBusinessOrderRecord()
					.getCountryName())) {
				hql = hql + " and countryName like '%"
						+ vo.getBusinessOrderRecord().getCountryName() + "%'";
			}
			if (vo.getBusinessOrderRecord().getProvinceId() != null) {
				hql = hql + " and provinceId = "
						+ vo.getBusinessOrderRecord().getProvinceId();
			}
			if (!StringHelper.isNull(vo.getBusinessOrderRecord()
					.getProvinceName())) {
				hql = hql + " and provinceName like '%"
						+ vo.getBusinessOrderRecord().getProvinceName() + "%'";
			}
			if (vo.getBusinessOrderRecord().getCityId() != null) {
				hql = hql + " and cityId = '"
						+ vo.getBusinessOrderRecord().getCityId() + "'";
			}
			if (!StringHelper.isNull(vo.getBusinessOrderRecord().getCityName())) {
				hql = hql + " and cityName like '%"
						+ vo.getBusinessOrderRecord().getCityName() + "%'";
			}
			if (vo.getBusinessOrderRecord().getAreaId() != null) {
				hql = hql + " and areaId = "
						+ vo.getBusinessOrderRecord().getAreaId();
			}
			if (!StringHelper.isNull(vo.getBusinessOrderRecord().getAreaName())) {
				hql = hql + " and areaName like '%"
						+ vo.getBusinessOrderRecord().getAreaName() + "%'";
			}
			if (!StringHelper.isNull(vo.getBusinessOrderRecord()
					.getUserRealAddress())) {
				hql = hql + " and userRealAddress like '%"
						+ vo.getBusinessOrderRecord().getUserRealAddress()
						+ "%'";
			}
			if (!StringHelper.isNull(vo.getBusinessOrderRecord()
					.getUserRealMobile())) {
				hql = hql + " and userRealMobile = "
						+ vo.getBusinessOrderRecord().getUserRealMobile();
			}
			if (!StringHelper.isNull(vo.getBusinessOrderRecord()
					.getUserRealPlane())) {
				hql = hql + " and userRealPlane = "
						+ vo.getBusinessOrderRecord().getUserRealPlane();
			}
			if (!StringHelper.isNull(vo.getBusinessOrderRecord()
					.getUserRealPostcode())) {
				hql = hql + " and userRealPostcode = "
						+ vo.getBusinessOrderRecord().getUserRealPostcode();
			}
			if (vo.getBusinessOrderRecord().getPrimaryConfigurationId() != null) {
				hql = hql
						+ " and primaryConfigurationId = "
						+ vo.getBusinessOrderRecord()
								.getPrimaryConfigurationId();
			}
			if (!StringHelper.isNull(vo.getUserRealStartTime())) {
				hql = hql + " and userRealTime >='" + vo.getUserRealStartTime()
						+ "'";
			}
			if (!StringHelper.isNull(vo.getUserRealEndTime())) {
				hql = hql + " and userRealTime <='" + vo.getUserRealEndTime()
						+ "'";
			}
			if (vo.getBusinessOrderRecord().getPaymentType() != null) {
				hql = hql + " and paymentType = "
						+ vo.getBusinessOrderRecord().getPaymentType();
			}
			if (!StringHelper.isNull(vo.getBusinessOrderRecord()
					.getPaymentTypeName())) {
				hql = hql + " and paymentTypeName like '"
						+ vo.getBusinessOrderRecord().getPaymentTypeName()
						+ "%'";
			}
			if (vo.getBusinessOrderRecord().getPaymentStatus() != null) {
				hql = hql + " and paymentStatus = "
						+ vo.getBusinessOrderRecord().getPaymentStatus();
			}
			if (!StringHelper.isNull(vo.getBusinessOrderRecord()
					.getPaymentStatusName())) {
				hql = hql + " and paymentStatusName like '%"
						+ vo.getBusinessOrderRecord().getPaymentStatusName()
						+ "%'";
			}
			if (vo.getBusinessOrderRecord().getPaymentProvider() != null) {
				hql = hql + " and paymentProvider = "
						+ vo.getBusinessOrderRecord().getPaymentProvider();
			}
			if (!StringHelper.isNull(vo.getBusinessOrderRecord()
					.getPaymentProviderName())) {
				hql = hql + " and paymentProviderName like '%"
						+ vo.getBusinessOrderRecord().getPaymentProviderName()
						+ "%'";
			}
			if (!StringHelper.isNull(vo.getPaymentStartTime())) {
				hql = hql + " and paymentTime >='" + vo.getPaymentStartTime()
						+ "'";
			}
			if (!StringHelper.isNull(vo.getPaymentEndTime())) {
				hql = hql + " and paymentTime <='" + vo.getPaymentEndTime()
						+ "'";
			}
			if (!StringHelper.isNull(vo.getOperateStartTime())) {
				hql = hql + " and operateTime >='" + vo.getOperateStartTime()
						+ "'";
			}
			if (!StringHelper.isNull(vo.getOperateEndTime())) {
				hql = hql + " and operateTime <='" + vo.getOperateEndTime()
						+ "'";
			}
			if (vo.getBusinessOrderRecord().getOperateStatus() != null
					&& !vo.getBusinessOrderRecord().getOperateStatus()
							.equals("0")) {
				hql = hql + " and operateStatus = "
						+ vo.getBusinessOrderRecord().getOperateStatus();
			}
			if (!StringHelper.isNull(vo.getBusinessOrderRecord()
					.getOperateStatusName())) {
				hql = hql + " and operateStatusName like '%"
						+ vo.getBusinessOrderRecord().getOperateStatusName()
						+ "%'";
			}
			if (!StringHelper.isNull(vo.getCreateStartTime())) {
				hql = hql + " and createTime >='" + vo.getCreateStartTime()
						+ "'";
			}
			if (!StringHelper.isNull(vo.getCreateEndTime())) {
				hql = hql + " and createTime <='" + vo.getCreateEndTime() + "'";
			}
			if (vo.getBusinessOrderRecord().getOriginalPrice() != null) {
				hql = hql + " and originalPrice = "
						+ vo.getBusinessOrderRecord().getOriginalPrice();
			}
			if (vo.getBusinessOrderRecord().getTotalPrice() != null) {
				hql = hql + " and totalPrice = "
						+ vo.getBusinessOrderRecord().getTotalPrice();
			}
			if (vo.getBusinessOrderRecord().getLogisticsCompanyId() != null) {
				hql = hql + " and logisticsCompanyId = "
						+ vo.getBusinessOrderRecord().getLogisticsCompanyId();
			}
			if (!StringHelper.isNull(vo.getBusinessOrderRecord()
					.getLogisticsCompanyName())) {
				hql = hql + " and logisticsCompanyName like '%"
						+ vo.getBusinessOrderRecord().getLogisticsCompanyName()
						+ "%'";
			}
			if (vo.getBusinessOrderRecord().getDeliveryStaffId() != null) {
				hql = hql + " and deliveryStaffId = "
						+ vo.getBusinessOrderRecord().getDeliveryStaffId();
			}
			if (!StringHelper.isNull(vo.getBusinessOrderRecord()
					.getDeliveryStaffName())) {
				hql = hql + " and deliveryStaffName like '%"
						+ vo.getBusinessOrderRecord().getDeliveryStaffName()
						+ "%'";
			}
			if (!StringHelper.isNull(vo.getBusinessOrderRecord()
					.getDeliveryStaffMobile())) {
				hql = hql + " and deliveryStaffMobile = '"
						+ vo.getBusinessOrderRecord().getDeliveryStaffMobile()
						+ "'";
			}
			if (!StringHelper.isNull(vo.getDeliveryStartStartTime())) {
				hql = hql + " and deliveryStartTime >='"
						+ vo.getDeliveryStartStartTime() + "'";
			}
			if (!StringHelper.isNull(vo.getDeliveryStartEndTime())) {
				hql = hql + " and deliveryStartTime <='"
						+ vo.getDeliveryStartEndTime() + "'";
			}
			if (!StringHelper.isNull(vo.getDeliveryEndStartTime())) {
				hql = hql + " and deliveryEndTime >='"
						+ vo.getDeliveryEndStartTime() + "'";
			}
			if (!StringHelper.isNull(vo.getDeliveryEndEndTime())) {
				hql = hql + " and deliveryEndTime <='"
						+ vo.getDeliveryEndEndTime() + "'";
			}
			if (vo.getBusinessOrderRecord().getDeliveryStatus() != null) {
				hql = hql + " and deliveryStatus = "
						+ vo.getBusinessOrderRecord().getDeliveryStatus();
			}
			if (!StringHelper.isNull(vo.getBusinessOrderRecord()
					.getDeliveryStatusName())) {
				hql = hql + " and deliveryStatusName like '%"
						+ vo.getBusinessOrderRecord().getDeliveryStatusName()
						+ "%'";
			}
			if (vo.getBusinessOrderRecord().getSourceId() != null) {
				hql = hql + " and sourceId = "
						+ vo.getBusinessOrderRecord().getSourceId();
			}
			if (!StringHelper.isNull(vo.getBusinessOrderRecord()
					.getSourceName())) {
				hql = hql + " and sourceName like '%"
						+ vo.getBusinessOrderRecord().getSourceName() + "%'";
			}
			if (vo.getBusinessOrderRecord().getSourceType() != null) {
				hql = hql + " and sourceType = "
						+ vo.getBusinessOrderRecord().getSourceType();
			}
			if (!StringHelper.isNull(vo.getBusinessOrderRecord()
					.getSourceTypeName())) {
				hql = hql + " and sourceTypeName like '%"
						+ vo.getBusinessOrderRecord().getSourceTypeName()
						+ "%'";
			}
			if (!StringHelper.isNull(vo.getBusinessOrderRecord().getSerNo())) {
				hql = hql + " and serNo = '"
						+ vo.getBusinessOrderRecord().getSerNo() + "'";
			}
			if (vo.getBusinessOrderRecord().getSellerId() != null) {
				hql = hql + " and sellerId = "
						+ vo.getBusinessOrderRecord().getSellerId();
			}
			if (!StringHelper.isNull(vo.getBusinessOrderRecord()
					.getSellerName())) {
				hql = hql + " and sellerName like '%"
						+ vo.getBusinessOrderRecord().getSellerName() + "%'";
			}
			if (vo.getBusinessOrderRecord().getCusId() != null) {
				hql = hql + " and cusId = "
						+ vo.getBusinessOrderRecord().getCusId();
			}
			if (!StringHelper.isNull(vo.getBusinessOrderRecord().getCusName())) {
				hql = hql + " and cusName like '%"
						+ vo.getBusinessOrderRecord().getCusName() + "%'";
			}
			if (vo.getBusinessOrderRecord().getIsChild()!=null) {
				hql = hql + " and isChild = '"
						+ vo.getBusinessOrderRecord().getIsChild()
						+ "'";
			}
		}
		return this.commonDao.getNumberOfEntitiesWithHql(hql);
	}

	private BusinessOrderVO processMutilParameter(
			BusinessOrderVO businessOrderVO) throws ParseException {
		DateFormat dft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String orderNo = businessOrderVO == null
				|| businessOrderVO.getBusinessOrderRecord() == null ? ""
				: businessOrderVO.getBusinessOrderRecord().getOrderNo();
		String BusinessOrderRecordId = businessOrderVO == null
				|| businessOrderVO.getBusinessOrderRecord() == null
				|| businessOrderVO.getBusinessOrderRecord().getId() == null ? ""
				: businessOrderVO.getBusinessOrderRecord().getId().toString();
		String clientUserId = businessOrderVO == null
				|| businessOrderVO.getBusinessOrderRecord() == null
				|| businessOrderVO.getBusinessOrderRecord().getClientUserId() == null ? ""
				: businessOrderVO.getBusinessOrderRecord().getClientUserId()
						.toString();
		String clientUserName = businessOrderVO == null
				|| businessOrderVO.getBusinessOrderRecord() == null ? ""
				: businessOrderVO.getBusinessOrderRecord().getClientUserName();
		String clientUserMobile = businessOrderVO == null
				|| businessOrderVO.getBusinessOrderRecord() == null ? ""
				: businessOrderVO.getBusinessOrderRecord()
						.getClientUserMobile();
		String clientUserEmail = businessOrderVO == null
				|| businessOrderVO.getBusinessOrderRecord() == null ? ""
				: businessOrderVO.getBusinessOrderRecord().getClientUserEmail();
		String clientUserSex = businessOrderVO == null
				|| businessOrderVO.getBusinessOrderRecord() == null ? ""
				: businessOrderVO.getBusinessOrderRecord().getClientUserSex();
		String clientUserType = businessOrderVO == null
				|| businessOrderVO.getBusinessOrderRecord() == null
				|| businessOrderVO.getBusinessOrderRecord().getClientUserType() == null ? ""
				: businessOrderVO.getBusinessOrderRecord().getClientUserType()
						.toString();
		String clientUserTypeName = businessOrderVO == null
				|| businessOrderVO.getBusinessOrderRecord() == null ? ""
				: businessOrderVO.getBusinessOrderRecord()
						.getClientUserTypeName();
		String wxOpenId = businessOrderVO == null
				|| businessOrderVO.getBusinessOrderRecord() == null ? ""
				: businessOrderVO.getBusinessOrderRecord().getWxOpenId();
		String staffId = businessOrderVO == null
				|| businessOrderVO.getBusinessOrderRecord() == null ? ""
				: businessOrderVO.getBusinessOrderRecord().getStaffId();
		String userRealMobile = businessOrderVO == null
				|| businessOrderVO.getBusinessOrderRecord() == null ? ""
				: businessOrderVO.getBusinessOrderRecord().getUserRealMobile();
		String userRealPlane = businessOrderVO == null
				|| businessOrderVO.getBusinessOrderRecord() == null ? ""
				: businessOrderVO.getBusinessOrderRecord().getUserRealPlane();
		String userRealName = businessOrderVO == null
				|| businessOrderVO.getBusinessOrderRecord() == null ? ""
				: businessOrderVO.getBusinessOrderRecord().getUserRealName();
		String countryId = businessOrderVO == null
				|| businessOrderVO.getBusinessOrderRecord() == null
				|| businessOrderVO.getBusinessOrderRecord().getCountryId() == null ? ""
				: businessOrderVO.getBusinessOrderRecord().getCountryId()
						.toString();
		String countryName = businessOrderVO == null
				|| businessOrderVO.getBusinessOrderRecord() == null ? ""
				: businessOrderVO.getBusinessOrderRecord().getCountryName();
		String provinceId = businessOrderVO == null
				|| businessOrderVO.getBusinessOrderRecord() == null
				|| businessOrderVO.getBusinessOrderRecord().getProvinceId() == null ? ""
				: businessOrderVO.getBusinessOrderRecord().getProvinceId()
						.toString();
		String provinceName = businessOrderVO == null
				|| businessOrderVO.getBusinessOrderRecord() == null ? ""
				: businessOrderVO.getBusinessOrderRecord().getProvinceName();
		String cityId = businessOrderVO == null
				|| businessOrderVO.getBusinessOrderRecord() == null
				|| businessOrderVO.getBusinessOrderRecord().getCityId() == null ? ""
				: businessOrderVO.getBusinessOrderRecord().getCityId()
						.toString();
		String cityName = businessOrderVO == null
				|| businessOrderVO.getBusinessOrderRecord() == null ? ""
				: businessOrderVO.getBusinessOrderRecord().getCityName();
		String areaId = businessOrderVO == null
				|| businessOrderVO.getBusinessOrderRecord() == null
				|| businessOrderVO.getBusinessOrderRecord().getAreaId() == null ? ""
				: businessOrderVO.getBusinessOrderRecord().getAreaId()
						.toString();
		String areaName = businessOrderVO == null
				|| businessOrderVO.getBusinessOrderRecord() == null ? ""
				: businessOrderVO.getBusinessOrderRecord().getAreaName();
		String userRealAddress = businessOrderVO == null
				|| businessOrderVO.getBusinessOrderRecord() == null ? ""
				: businessOrderVO.getBusinessOrderRecord().getUserRealAddress();
		String userRealPostcode = businessOrderVO == null
				|| businessOrderVO.getBusinessOrderRecord() == null ? ""
				: businessOrderVO.getBusinessOrderRecord()
						.getUserRealPostcode();
		String userRealStartTime = businessOrderVO == null ? ""
				: businessOrderVO.getUserRealStartTime();
		String userRealEndTime = businessOrderVO == null ? "" : businessOrderVO
				.getUserRealEndTime();
		String paymentType = businessOrderVO == null
				|| businessOrderVO.getBusinessOrderRecord() == null
				|| businessOrderVO.getBusinessOrderRecord().getPaymentType() == null ? ""
				: businessOrderVO.getBusinessOrderRecord().getPaymentType()
						.toString();
		String paymentTypeName = businessOrderVO == null
				|| businessOrderVO.getBusinessOrderRecord() == null ? ""
				: businessOrderVO.getBusinessOrderRecord().getPaymentTypeName();
		String paymentStatus = businessOrderVO == null
				|| businessOrderVO.getBusinessOrderRecord() == null
				|| businessOrderVO.getBusinessOrderRecord().getPaymentStatus() == null ? ""
				: businessOrderVO.getBusinessOrderRecord().getPaymentStatus()
						.toString();
		String paymentStatusName = businessOrderVO == null
				|| businessOrderVO.getBusinessOrderRecord() == null ? ""
				: businessOrderVO.getBusinessOrderRecord()
						.getPaymentStatusName();
		String paymentProvider = businessOrderVO == null
				|| businessOrderVO.getBusinessOrderRecord() == null
				|| businessOrderVO.getBusinessOrderRecord()
						.getPaymentProvider() == null ? "" : businessOrderVO
				.getBusinessOrderRecord().getPaymentProvider().toString();
		String paymentProviderName = businessOrderVO == null
				|| businessOrderVO.getBusinessOrderRecord() == null ? ""
				: businessOrderVO.getBusinessOrderRecord()
						.getPaymentProviderName();
		String paymentStartTime = businessOrderVO == null ? ""
				: businessOrderVO.getPaymentStartTime();
		String paymentEndTime = businessOrderVO == null ? "" : businessOrderVO
				.getPaymentEndTime();
		String operateStartTime = businessOrderVO == null
				|| businessOrderVO.getBusinessOrderRecord() == null ? ""
				: businessOrderVO.getOperateStartTime();
		String operateEndTime = businessOrderVO == null ? "" : businessOrderVO
				.getOperateEndTime();
		String operateStatus = businessOrderVO == null
				|| businessOrderVO.getBusinessOrderRecord() == null
				|| businessOrderVO.getBusinessOrderRecord().getOperateStatus() == null ? ""
				: businessOrderVO.getBusinessOrderRecord().getOperateStatus()
						.toString();
		String operateStatusName = businessOrderVO == null
				|| businessOrderVO.getBusinessOrderRecord() == null ? ""
				: businessOrderVO.getBusinessOrderRecord()
						.getOperateStatusName();
		String createTime = businessOrderVO == null ? "" : businessOrderVO
				.getBusinessOrderRecord().getCreateTime().toString();
		String createStartTime = businessOrderVO == null ? "" : businessOrderVO
				.getCreateStartTime();
		String createEndTime = businessOrderVO == null ? "" : businessOrderVO
				.getCreateEndTime();
		String originalPrice = businessOrderVO == null
				|| businessOrderVO.getBusinessOrderRecord() == null
				|| businessOrderVO.getBusinessOrderRecord().getOriginalPrice() == null ? ""
				: businessOrderVO.getBusinessOrderRecord().getOriginalPrice()
						.toString();
		String totalPrice = businessOrderVO == null
				|| businessOrderVO.getBusinessOrderRecord() == null
				|| businessOrderVO.getBusinessOrderRecord().getTotalPrice() == null ? ""
				: businessOrderVO.getBusinessOrderRecord().getTotalPrice()
						.toString();
		String logisticsCompanyId = businessOrderVO == null
				|| businessOrderVO.getBusinessOrderRecord() == null
				|| businessOrderVO.getBusinessOrderRecord()
						.getLogisticsCompanyId() == null ? "" : businessOrderVO
				.getBusinessOrderRecord().getLogisticsCompanyId().toString();
		String logisticsCompanyName = businessOrderVO == null
				|| businessOrderVO.getBusinessOrderRecord() == null ? ""
				: businessOrderVO.getBusinessOrderRecord()
						.getLogisticsCompanyName();
		String deliveryStaffId = businessOrderVO == null
				|| businessOrderVO.getBusinessOrderRecord() == null
				|| businessOrderVO.getBusinessOrderRecord()
						.getDeliveryStaffId() == null ? "" : businessOrderVO
				.getBusinessOrderRecord().getDeliveryStaffId().toString();
		String deliveryStaffName = businessOrderVO == null
				|| businessOrderVO.getBusinessOrderRecord() == null ? ""
				: businessOrderVO.getBusinessOrderRecord()
						.getDeliveryStaffName();
		String deliveryStaffMobile = businessOrderVO == null
				|| businessOrderVO.getBusinessOrderRecord() == null ? ""
				: businessOrderVO.getBusinessOrderRecord()
						.getDeliveryStaffMobile();
		String deliveryStartStartTime = businessOrderVO == null ? ""
				: businessOrderVO.getDeliveryStartStartTime();
		String deliveryStartEndTime = businessOrderVO == null ? ""
				: businessOrderVO.getDeliveryStartEndTime();
		String deliveryEndStartTime = businessOrderVO == null
				|| businessOrderVO.getBusinessOrderRecord() == null ? ""
				: businessOrderVO.getDeliveryEndStartTime();
		String deliveryEndEndTime = businessOrderVO == null ? ""
				: businessOrderVO.getDeliveryEndEndTime();
		String deliveryStatus = businessOrderVO == null
				|| businessOrderVO.getBusinessOrderRecord() == null
				|| businessOrderVO.getBusinessOrderRecord().getDeliveryStatus() == null ? ""
				: businessOrderVO.getBusinessOrderRecord().getDeliveryStatus()
						.toString();
		String deliveryStatusName = businessOrderVO == null
				|| businessOrderVO.getBusinessOrderRecord() == null ? ""
				: businessOrderVO.getBusinessOrderRecord()
						.getDeliveryStatusName();
		String sourceId = businessOrderVO == null
				|| businessOrderVO.getBusinessOrderRecord() == null
				|| businessOrderVO.getBusinessOrderRecord().getSourceId() == null ? ""
				: businessOrderVO.getBusinessOrderRecord().getSourceId()
						.toString();
		String sourceName = businessOrderVO == null
				|| businessOrderVO.getBusinessOrderRecord() == null ? ""
				: businessOrderVO.getBusinessOrderRecord().getSourceName();
		String sourceType = businessOrderVO == null
				|| businessOrderVO.getBusinessOrderRecord() == null
				|| businessOrderVO.getBusinessOrderRecord().getSourceId() == null ? ""
				: businessOrderVO.getBusinessOrderRecord().getSourceType()
						.toString();
		String sourceTypeName = businessOrderVO == null
				|| businessOrderVO.getBusinessOrderRecord() == null ? ""
				: businessOrderVO.getBusinessOrderRecord().getSourceTypeName();
		String serNo = businessOrderVO == null
				|| businessOrderVO.getBusinessOrderRecord() == null ? ""
				: businessOrderVO.getBusinessOrderRecord().getSerNo();
		String sellerId = businessOrderVO == null
				|| businessOrderVO.getBusinessOrderRecord() == null
				|| businessOrderVO.getBusinessOrderRecord().getSellerId() == null ? ""
				: businessOrderVO.getBusinessOrderRecord().getSellerId()
						.toString();
		String sellerName = businessOrderVO == null
				|| businessOrderVO.getBusinessOrderRecord() == null ? ""
				: businessOrderVO.getBusinessOrderRecord().getSellerName();
		String cusId = businessOrderVO == null
				|| businessOrderVO.getBusinessOrderRecord() == null
				|| businessOrderVO.getBusinessOrderRecord().getCusId() == null ? ""
				: businessOrderVO.getBusinessOrderRecord().getCusId()
						.toString();
		String cusName = businessOrderVO == null
				|| businessOrderVO.getBusinessOrderRecord() == null ? ""
				: businessOrderVO.getBusinessOrderRecord().getCusName();
		String primaryConfigurationId = businessOrderVO == null
				|| businessOrderVO.getBusinessOrderRecord() == null
				|| businessOrderVO.getBusinessOrderRecord()
						.getPrimaryConfigurationId() == null ? ""
				: businessOrderVO.getBusinessOrderRecord()
						.getPrimaryConfigurationId().toString();
		String primaryConfigurationName = businessOrderVO == null
				|| businessOrderVO.getBusinessOrderRecord() == null ? ""
				: businessOrderVO.getBusinessOrderRecord()
						.getPrimaryConfigurationName();

		BusinessOrderVO res = new BusinessOrderVO();

		BusinessOrderRecord or = new BusinessOrderRecord();
		if (!StringHelper.isNull(areaId))
			or.setAreaId(new Integer(areaId));
		or.setAreaName(areaName);
		if (!StringHelper.isNull(cityId))
			or.setCityId(new Integer(cityId));

		or.setCityName(cityName);
		or.setClientUserEmail(clientUserEmail);
		if (!StringHelper.isNull(clientUserId))
			or.setClientUserId(new Integer(clientUserId));
		or.setClientUserMobile(clientUserMobile);
		or.setClientUserName(clientUserName);
		or.setClientUserSex(clientUserSex);
		if (!StringHelper.isNull(clientUserType))
			or.setClientUserType(new Integer(clientUserType));
		or.setClientUserTypeName(clientUserTypeName);
		if (!StringHelper.isNull(countryId))
			or.setCountryId(new Integer(countryId));
		or.setCountryName(countryName);
		if (!StringHelper.isNull(createTime))
			or.setCreateTime(new Timestamp(dft.parse(createTime).getTime()));
		if (!StringHelper.isNull(cusId))
			or.setCusId(new Integer(cusId));
		or.setCusName(cusName);
		if (!StringHelper.isNull(deliveryEndStartTime))
			or.setDeliveryEndTime(new Timestamp(dft.parse(deliveryEndStartTime)
					.getTime()));

		ClientUserInfo csi = new ClientUserInfo();

		BusinessOrderGoodsRelation ogr = new BusinessOrderGoodsRelation();

		res.setBusinessOrderRecord(or);
		res.setClientUserInfo(csi);
		res.setBusinessOrderGoodsRelation(ogr);
		return res;
	}

	/**
	 * 新版info页调用数据
	 * 
	 * @param vo
	 * @param curPage
	 * @param pageSize
	 * @param orderField
	 * @param direction
	 * @return List<BusinessOrderVO>
	 */
	public List<BusinessOrderVO> listVO2(BusinessOrderVO vo, int curPage,
			int pageSize, String orderField, String direction) {
		String hql = getHql(vo);

		List<Object[]> res = this.commonDao.getEntitiesByPropertiesWithHql(hql,
				curPage, pageSize);
		List<BusinessOrderVO> returnList = new ArrayList<BusinessOrderVO>();
		for (Object[] e : res) {
			BusinessOrderRecord record = (BusinessOrderRecord) e[0];
			BusinessOrderGoodsRelation rel = (BusinessOrderGoodsRelation) e[1];
			//BusinessGoods goods = (BusinessGoods) e[2];
			//BusinessCategory cate = (BusinessCategory) e[3];
			CommonStaff staff = (CommonStaff) e[2];

			
			CommonStaff leadStaff = new CommonStaff();
			leadStaff.setId(staff.getLeadStaffId());
			leadStaff.setStaffName(staff.getLeadStaffName());
			leadStaff.setLeadStaffOrgId(staff.getLeadStaffOrgId());
			leadStaff.setLeadStaffOrgName(staff.getLeadStaffOrgName());

			BusinessOrderVO v = new BusinessOrderVO();
			v.setBusinessOrderRecord(record);
			v.setBusinessOrderGoodsRelation(rel);
			//v.setBusinessGoods(goods);
			//v.setBusinessCategory(cate);
			v.setStaff(staff);
			v.setLeaderStaff(leadStaff);

			returnList.add(v);
		}

		return returnList;
	}
	public List<BusinessOrderVO> listVO3(BusinessOrderVO vo, int curPage,
			int pageSize, String orderField, String direction) {
		String hql = getHql(vo);

		List<Object[]> res = this.commonDao.getEntitiesByPropertiesWithHql(hql,
				curPage, pageSize);
		List<BusinessOrderVO> returnList = new ArrayList<BusinessOrderVO>();
		for (Object[] e : res) {
			BusinessOrderRecord record = (BusinessOrderRecord) e[0];
			BusinessOrderGoodsRelation rel = (BusinessOrderGoodsRelation) e[1];
//			BusinessGoods goods = (BusinessGoods) e[2];
//			BusinessCategory cate = (BusinessCategory) e[3];
			CommonStaff staff = (CommonStaff) e[2];
			Long borId = record.getId();
			List<BusinessOrderRelationVO> bogr = new ArrayList<BusinessOrderRelationVO>();
			BusinessGoods goods = null;
			Combo combo = null;
			if(rel.getGoodsId()!=null)
			{
				goods = goodsService.findByid(rel.getGoodsId().toString());
				bogr = businessOrderGoodsRelationService.findByOrderRecordId(borId);
			}
			if(rel.getComboId()!=null)
			{
				combo = comboService.findByCombo(rel.getComboId().toString());
				bogr = businessOrderGoodsRelationService.findByComboOrderRecordId(borId);
			}
			CommonStaff leadStaff = new CommonStaff();
			leadStaff.setId(staff.getLeadStaffId());
			leadStaff.setStaffName(staff.getLeadStaffName());
			leadStaff.setLeadStaffOrgId(staff.getLeadStaffOrgId());
			leadStaff.setLeadStaffOrgName(staff.getLeadStaffOrgName());

			BusinessOrderVO v = new BusinessOrderVO();
			v.setBusinessOrderRelationVO(bogr);
			v.setBusinessOrderRecord(record);
			v.setBusinessOrderGoodsRelation(rel);
			v.setBusinessGoods(goods);
//			v.setBusinessCategory(cate);
			v.setCombo(combo);
			v.setStaff(staff);
			v.setLeaderStaff(leadStaff);

			returnList.add(v);
		}

		return returnList;
	}
	public Integer getCount2(String select, BusinessOrderVO vo) {
		String hql = select + getHql(vo);

		return commonDao.getNumberOfEntitiesWithHql(hql);
	}

	/**
	 * 
	 * @param firstletter
	 * @param curPage
	 * @param pageSize
	 * @param orderField
	 * @param direction
	 * @return List<OrderVO>
	 */
	public List<BusinessOrderVO> listVO(BusinessOrderVO vo, int curPage,
			int pageSize, String orderField, String direction) {
		String hql = "from BusinessOrderRecord where 1=1";
		if (vo != null && vo.getBusinessOrderRecord() != null) {
			if (!StringHelper.isNull(vo.getBusinessOrderRecord().getOrderNo())) {
				hql = hql + " and orderNo = '"
						+ vo.getBusinessOrderRecord().getOrderNo() + "'";
			}
			if (vo.getBusinessOrderRecord().getId() != null) {
				hql = hql + " and id = " + vo.getBusinessOrderRecord().getId();
			}
			if (vo.getBusinessOrderRecord().getClientUserId() != null) {
				hql = hql + " and clientUserId = "
						+ vo.getBusinessOrderRecord().getClientUserId();
			}
			if (!StringHelper.isNull(vo.getBusinessOrderRecord()
					.getClientUserName())) {
				hql = hql + " and clientUserName like '%"
						+ vo.getBusinessOrderRecord().getClientUserName()
						+ "%'";
			}
			if (!StringHelper.isNull(vo.getBusinessOrderRecord()
					.getClientUserMobile())) {
				hql = hql + " and clientUserMobile = '"
						+ vo.getBusinessOrderRecord().getClientUserMobile()
						+ "'";
			}
			if (!StringHelper.isNull(vo.getBusinessOrderRecord()
					.getClientUserEmail())) {
				hql = hql + " and clientUserEmail = '"
						+ vo.getBusinessOrderRecord().getClientUserEmail()
						+ "'";
			}
			if (!StringHelper.isNull(vo.getBusinessOrderRecord()
					.getClientUserSex())) {
				hql = hql + " and clientUserSex = '"
						+ vo.getBusinessOrderRecord().getClientUserSex() + "'";
			}
			if (vo.getBusinessOrderRecord().getClientUserType() != null) {
				hql = hql + " and clientUserType = "
						+ vo.getBusinessOrderRecord().getClientUserType();
			}
			if (!StringHelper.isNull(vo.getBusinessOrderRecord()
					.getClientUserTypeName())) {
				hql = hql + " and clientUserTypeName like '%"
						+ vo.getBusinessOrderRecord().getClientUserTypeName()
						+ "%'";
			}
			if (!StringHelper.isNull(vo.getBusinessOrderRecord().getWxOpenId())) {
				hql = hql + " and wxOpenId = '"
						+ vo.getBusinessOrderRecord().getWxOpenId() + "'";
			}
			if (!StringHelper.isNull(vo.getBusinessOrderRecord().getStaffId())) {
				hql = hql + " and staffId = '"
						+ vo.getBusinessOrderRecord().getStaffId() + "'";
			}
			if (!StringHelper.isNull(vo.getBusinessOrderRecord()
					.getUserRealName())) {
				hql = hql + " and userRealName like '%"
						+ vo.getBusinessOrderRecord().getUserRealName() + "%'";
			}
			if (vo.getBusinessOrderRecord().getCountryId() != null) {
				hql = hql + " and countryId = "
						+ vo.getBusinessOrderRecord().getCountryId();
			}
			if (!StringHelper.isNull(vo.getBusinessOrderRecord()
					.getCountryName())) {
				hql = hql + " and countryName like '%"
						+ vo.getBusinessOrderRecord().getCountryName() + "%'";
			}
			if (vo.getBusinessOrderRecord().getProvinceId() != null) {
				hql = hql + " and provinceId = "
						+ vo.getBusinessOrderRecord().getProvinceId();
			}
			if (!StringHelper.isNull(vo.getBusinessOrderRecord()
					.getProvinceName())) {
				hql = hql + " and provinceName like '%"
						+ vo.getBusinessOrderRecord().getProvinceName() + "%'";
			}
			if (vo.getBusinessOrderRecord().getCityId() != null) {
				hql = hql + " and cityId = '"
						+ vo.getBusinessOrderRecord().getCityId() + "'";
			}
			if (!StringHelper.isNull(vo.getBusinessOrderRecord().getCityName())) {
				hql = hql + " and cityName like '%"
						+ vo.getBusinessOrderRecord().getCityName() + "%'";
			}
			if (vo.getBusinessOrderRecord().getAreaId() != null) {
				hql = hql + " and areaId = "
						+ vo.getBusinessOrderRecord().getAreaId();
			}
			if (!StringHelper.isNull(vo.getBusinessOrderRecord().getAreaName())) {
				hql = hql + " and areaName like '%"
						+ vo.getBusinessOrderRecord().getAreaName() + "%'";
			}
			if (!StringHelper.isNull(vo.getBusinessOrderRecord()
					.getUserRealAddress())) {
				hql = hql + " and userRealAddress like '%"
						+ vo.getBusinessOrderRecord().getUserRealAddress()
						+ "%'";
			}
			if (!StringHelper.isNull(vo.getBusinessOrderRecord()
					.getUserRealMobile())) {
				hql = hql + " and userRealMobile = "
						+ vo.getBusinessOrderRecord().getUserRealMobile();
			}
			if (!StringHelper.isNull(vo.getBusinessOrderRecord()
					.getUserRealPlane())) {
				hql = hql + " and userRealPlane = "
						+ vo.getBusinessOrderRecord().getUserRealPlane();
			}
			if (!StringHelper.isNull(vo.getBusinessOrderRecord()
					.getUserRealPostcode())) {
				hql = hql + " and userRealPostcode = "
						+ vo.getBusinessOrderRecord().getUserRealPostcode();
			}
			if (vo.getBusinessOrderRecord().getPrimaryConfigurationId() != null) {
				hql = hql
						+ " and primaryConfigurationId = "
						+ vo.getBusinessOrderRecord()
								.getPrimaryConfigurationId();
			}
			if (!StringHelper.isNull(vo.getUserRealStartTime())) {
				hql = hql + " and userRealTime >='" + vo.getUserRealStartTime()
						+ "'";
			}
			if (!StringHelper.isNull(vo.getUserRealEndTime())) {
				hql = hql + " and userRealTime <='" + vo.getUserRealEndTime()
						+ "'";
			}
			if (vo.getBusinessOrderRecord().getPaymentType() != null) {
				hql = hql + " and paymentType = "
						+ vo.getBusinessOrderRecord().getPaymentType();
			}
			if (!StringHelper.isNull(vo.getBusinessOrderRecord()
					.getPaymentTypeName())) {
				hql = hql + " and paymentTypeName like '"
						+ vo.getBusinessOrderRecord().getPaymentTypeName()
						+ "%'";
			}
			if (vo.getBusinessOrderRecord().getPaymentStatus() != null) {
				hql = hql + " and paymentStatus = "
						+ vo.getBusinessOrderRecord().getPaymentStatus();
			}
			if (!StringHelper.isNull(vo.getBusinessOrderRecord()
					.getPaymentStatusName())) {
				hql = hql + " and paymentStatusName like '%"
						+ vo.getBusinessOrderRecord().getPaymentStatusName()
						+ "%'";
			}
			if (vo.getBusinessOrderRecord().getPaymentProvider() != null) {
				hql = hql + " and paymentProvider = "
						+ vo.getBusinessOrderRecord().getPaymentProvider();
			}
			if (!StringHelper.isNull(vo.getBusinessOrderRecord()
					.getPaymentProviderName())) {
				hql = hql + " and paymentProviderName like '%"
						+ vo.getBusinessOrderRecord().getPaymentProviderName()
						+ "%'";
			}
			if (!StringHelper.isNull(vo.getPaymentStartTime())) {
				hql = hql + " and paymentTime >='" + vo.getPaymentStartTime()
						+ "'";
			}
			if (!StringHelper.isNull(vo.getPaymentEndTime())) {
				hql = hql + " and paymentTime <='" + vo.getPaymentEndTime()
						+ "'";
			}
			if (!StringHelper.isNull(vo.getOperateStartTime())) {
				hql = hql + " and operateTime >='" + vo.getOperateStartTime()
						+ "'";
			}
			if (!StringHelper.isNull(vo.getOperateEndTime())) {
				hql = hql + " and operateTime <='" + vo.getOperateEndTime()
						+ "'";
			}
			if (!StringHelper.isNull(vo.getRequiredDeliveryStartTime())) {
				hql = hql + " and requiredDeliveryTime >='"
						+ vo.getRequiredDeliveryStartTime() + "'";
			}
			if (!StringHelper.isNull(vo.getRequiredDeliveryEndTime())) {
				hql = hql + " and requiredDeliveryTime <='"
						+ vo.getRequiredDeliveryEndTime() + "'";
			}
			if (!StringHelper.isNull(vo.getFinalDeliveryStartTime())) {
				hql = hql + " and finalDeliveryTime >='"
						+ vo.getFinalDeliveryStartTime() + "'";
			}
			if (!StringHelper.isNull(vo.getFinalDeliveryEndTime())) {
				hql = hql + " and finalDeliveryTime <='"
						+ vo.getFinalDeliveryEndTime() + "'";
			}
			if (vo.getBusinessOrderRecord().getOperateStatus() != null
					&& !vo.getBusinessOrderRecord().getOperateStatus()
							.equals("0")) {
				hql = hql + " and operateStatus = "
						+ vo.getBusinessOrderRecord().getOperateStatus();
			}
			if (!StringHelper.isNull(vo.getBusinessOrderRecord()
					.getOperateStatusName())) {
				hql = hql + " and operateStatusName like '%"
						+ vo.getBusinessOrderRecord().getOperateStatusName()
						+ "%'";
			}
			if (!StringHelper.isNull(vo.getCreateStartTime())) {
				hql = hql + " and createTime >='" + vo.getCreateStartTime()
						+ "'";
			}
			if (!StringHelper.isNull(vo.getCreateEndTime())) {
				hql = hql + " and createTime <='" + vo.getCreateEndTime() + "'";
			}
			if (vo.getBusinessOrderRecord().getOriginalPrice() != null) {
				hql = hql + " and originalPrice = "
						+ vo.getBusinessOrderRecord().getOriginalPrice();
			}
			if (vo.getBusinessOrderRecord().getTotalPrice() != null) {
				hql = hql + " and totalPrice = "
						+ vo.getBusinessOrderRecord().getTotalPrice();
			}
			if (vo.getBusinessOrderRecord().getLogisticsCompanyId() != null) {
				hql = hql + " and logisticsCompanyId = "
						+ vo.getBusinessOrderRecord().getLogisticsCompanyId();
			}
			if (!StringHelper.isNull(vo.getBusinessOrderRecord()
					.getLogisticsCompanyName())) {
				hql = hql + " and logisticsCompanyName like '%"
						+ vo.getBusinessOrderRecord().getLogisticsCompanyName()
						+ "%'";
			}
			if (vo.getBusinessOrderRecord().getDeliveryStaffId() != null) {
				hql = hql + " and deliveryStaffId = "
						+ vo.getBusinessOrderRecord().getDeliveryStaffId();
			}
			if (!StringHelper.isNull(vo.getBusinessOrderRecord()
					.getDeliveryStaffName())) {
				hql = hql + " and deliveryStaffName like '%"
						+ vo.getBusinessOrderRecord().getDeliveryStaffName()
						+ "%'";
			}
			if (!StringHelper.isNull(vo.getBusinessOrderRecord()
					.getDeliveryStaffMobile())) {
				hql = hql + " and deliveryStaffMobile = '"
						+ vo.getBusinessOrderRecord().getDeliveryStaffMobile()
						+ "'";
			}
			if (!StringHelper.isNull(vo.getDeliveryStartStartTime())) {
				hql = hql + " and deliveryStartTime >='"
						+ vo.getDeliveryStartStartTime() + "'";
			}
			if (!StringHelper.isNull(vo.getDeliveryStartEndTime())) {
				hql = hql + " and deliveryStartTime <='"
						+ vo.getDeliveryStartEndTime() + "'";
			}
			if (!StringHelper.isNull(vo.getDeliveryEndStartTime())) {
				hql = hql + " and deliveryEndTime >='"
						+ vo.getDeliveryEndStartTime() + "'";
			}
			if (!StringHelper.isNull(vo.getDeliveryEndEndTime())) {
				hql = hql + " and deliveryEndTime <='"
						+ vo.getDeliveryEndEndTime() + "'";
			}
			if (vo.getBusinessOrderRecord().getDeliveryStatus() != null) {
				hql = hql + " and deliveryStatus = "
						+ vo.getBusinessOrderRecord().getDeliveryStatus();
			}
			if (!StringHelper.isNull(vo.getBusinessOrderRecord()
					.getDeliveryStatusName())) {
				hql = hql + " and deliveryStatusName like '%"
						+ vo.getBusinessOrderRecord().getDeliveryStatusName()
						+ "%'";
			}
			if (vo.getBusinessOrderRecord().getSourceId() != null) {
				hql = hql + " and sourceId = "
						+ vo.getBusinessOrderRecord().getSourceId();
			}
			if (!StringHelper.isNull(vo.getBusinessOrderRecord()
					.getSourceName())) {
				hql = hql + " and sourceName like '%"
						+ vo.getBusinessOrderRecord().getSourceName() + "%'";
			}
			if (vo.getBusinessOrderRecord().getSourceType() != null) {
				hql = hql + " and sourceType = "
						+ vo.getBusinessOrderRecord().getSourceType();
			}
			if (!StringHelper.isNull(vo.getBusinessOrderRecord()
					.getSourceTypeName())) {
				hql = hql + " and sourceTypeName like '%"
						+ vo.getBusinessOrderRecord().getSourceTypeName()
						+ "%'";
			}
			if (!StringHelper.isNull(vo.getBusinessOrderRecord().getSerNo())) {
				hql = hql + " and serNo = '"
						+ vo.getBusinessOrderRecord().getSerNo() + "'";
			}
			if (vo.getBusinessOrderRecord().getSellerId() != null) {
				hql = hql + " and sellerId = "
						+ vo.getBusinessOrderRecord().getSellerId();
			}
			if (!StringHelper.isNull(vo.getBusinessOrderRecord()
					.getSellerName())) {
				hql = hql + " and sellerName like '%"
						+ vo.getBusinessOrderRecord().getSellerName() + "%'";
			}
			if (vo.getBusinessOrderRecord().getCusId() != null) {
				hql = hql + " and cusId = "
						+ vo.getBusinessOrderRecord().getCusId();
			}
			if (!StringHelper.isNull(vo.getBusinessOrderRecord().getCusName())) {
				hql = hql + " and cusName like '%"
						+ vo.getBusinessOrderRecord().getCusName() + "%'";
			}
			if (vo.getBusinessOrderRecord().getIsChild()!=null) {
				hql = hql + " and isChild = '"
						+ vo.getBusinessOrderRecord().getIsChild()
						+ "'";
			}
			if (vo.getBusinessOrderRecord().getComboId()!=null) {
				hql = hql + " and comboID = '"
						+ vo.getBusinessOrderRecord().getComboId()
						+ "'";
			}
		}
		if (!StringHelper.isNull(orderField) && !StringHelper.isNull(direction)) {
			hql = hql + " order by " + orderField + " " + direction;
		} else {
			hql += " order by id desc";
		}
		List l = commonDao.getEntitiesByPropertiesWithHql(hql, curPage,
				pageSize);
		List<BusinessOrderVO> resList = new ArrayList<BusinessOrderVO>();
		for (int i = 0; i < l.size(); i++) {
			BusinessOrderRecord po = (BusinessOrderRecord) l.get(i);
			BusinessOrderVO businessOrderVO = new BusinessOrderVO();
			businessOrderVO.setBusinessOrderRecord(po);

			CommonStaff staff = staffService.findByid(po.getStaffId());
			businessOrderVO.setStaff(staff);

			if (staff.getLeadStaffId() != null) {
				CommonStaff leaderStaff = staffService.findByid(staff
						.getLeadStaffId().toString());
				businessOrderVO.setLeaderStaff(leaderStaff);
			}

			resList.add(businessOrderVO);
		}
		return resList;
	}

	public List<BusinessOrderGoodsRelation> findGoodsRelationList(Long orderId) {
		String hql = "from BusinessOrderGoodsRelation where orderRecordId="
				+ orderId;
		// List<BusinessOrderGoodsRelation> rst = new
		// ArrayList<BusinessOrderGoodsRelation>();
		List<BusinessOrderGoodsRelation> l = commonDao
				.getEntitiesByPropertiesWithHql(hql, 0, 0);
		return l;
	}

	/**
	 * 
	 * @param firstletter
	 * @param curPage
	 * @param pageSize
	 * @param orderField
	 * @param direction
	 * @return List<OrderVO>
	 */
	public List<BusinessOrderVO> webList(BusinessOrderVO vo, int curPage,
			int pageSize, String orderField, String direction) {
		String hql = "from BusinessOrderRecord a, BusinessGoods b,BusinessOrderGoodsRelation c where c.orderRecordId=a.id"
				+ " and c.goodsId=b.id";
		if (vo != null && vo.getBusinessOrderRecord() != null) {
			if (!StringHelper.isNull(vo.getBusinessOrderRecord().getOrderNo())) {
				hql = hql + " and a.orderNo = '"
						+ vo.getBusinessOrderRecord().getOrderNo() + "'";
			}
			if (vo.getBusinessOrderRecord().getId() != null) {
				hql = hql + " and a.id = "
						+ vo.getBusinessOrderRecord().getId();
			}
			if (vo.getBusinessOrderRecord().getClientUserId() != null) {
				hql = hql + " and a.clientUserId = "
						+ vo.getBusinessOrderRecord().getClientUserId();
			}
			if (!StringHelper.isNull(vo.getBusinessOrderRecord()
					.getClientUserName())) {
				hql = hql + " and a.clientUserName like '%"
						+ vo.getBusinessOrderRecord().getClientUserName()
						+ "%'";
			}
			if (!StringHelper.isNull(vo.getBusinessOrderRecord()
					.getClientUserMobile())) {
				hql = hql + " and a.clientUserMobile = '"
						+ vo.getBusinessOrderRecord().getClientUserMobile()
						+ "'";
			}
			if (!StringHelper.isNull(vo.getBusinessOrderRecord()
					.getClientUserEmail())) {
				hql = hql + " and a.clientUserEmail = '"
						+ vo.getBusinessOrderRecord().getClientUserEmail()
						+ "'";
			}
			if (!StringHelper.isNull(vo.getBusinessOrderRecord()
					.getClientUserSex())) {
				hql = hql + " and a.clientUserSex = '"
						+ vo.getBusinessOrderRecord().getClientUserSex() + "'";
			}
			if (vo.getBusinessOrderRecord().getClientUserType() != null) {
				hql = hql + " and a.clientUserType = "
						+ vo.getBusinessOrderRecord().getClientUserType();
			}
			if (!StringHelper.isNull(vo.getBusinessOrderRecord()
					.getClientUserTypeName())) {
				hql = hql + " and a.clientUserTypeName like '%"
						+ vo.getBusinessOrderRecord().getClientUserTypeName()
						+ "%'";
			}
			if (!StringHelper.isNull(vo.getBusinessOrderRecord().getWxOpenId())) {
				hql = hql + " and a.wxOpenId = '"
						+ vo.getBusinessOrderRecord().getWxOpenId() + "'";
			}
			if (!StringHelper.isNull(vo.getBusinessOrderRecord().getStaffId())) {
				hql = hql + " and a.staffId = '"
						+ vo.getBusinessOrderRecord().getStaffId() + "'";
			}
			if (!StringHelper.isNull(vo.getBusinessOrderRecord()
					.getUserRealName())) {
				hql = hql + " and a.userRealName like '%"
						+ vo.getBusinessOrderRecord().getUserRealName() + "%'";
			}
			if (vo.getBusinessOrderRecord().getCountryId() != null) {
				hql = hql + " and a.countryId = "
						+ vo.getBusinessOrderRecord().getCountryId();
			}
			if (!StringHelper.isNull(vo.getBusinessOrderRecord()
					.getCountryName())) {
				hql = hql + " and a.countryName like '%"
						+ vo.getBusinessOrderRecord().getCountryName() + "%'";
			}
			if (vo.getBusinessOrderRecord().getProvinceId() != null) {
				hql = hql + " and a.provinceId = "
						+ vo.getBusinessOrderRecord().getProvinceId();
			}
			if (!StringHelper.isNull(vo.getBusinessOrderRecord()
					.getProvinceName())) {
				hql = hql + " and a.provinceName like '%"
						+ vo.getBusinessOrderRecord().getProvinceName() + "%'";
			}
			if (vo.getBusinessOrderRecord().getCityId() != null) {
				hql = hql + " and a.cityId = '"
						+ vo.getBusinessOrderRecord().getCityId() + "'";
			}
			if (!StringHelper.isNull(vo.getBusinessOrderRecord().getCityName())) {
				hql = hql + " and a.cityName like '%"
						+ vo.getBusinessOrderRecord().getCityName() + "%'";
			}
			if (vo.getBusinessOrderRecord().getAreaId() != null) {
				hql = hql + " and a.areaId = "
						+ vo.getBusinessOrderRecord().getAreaId();
			}
			if (!StringHelper.isNull(vo.getBusinessOrderRecord().getAreaName())) {
				hql = hql + " and a.areaName like '%"
						+ vo.getBusinessOrderRecord().getAreaName() + "%'";
			}
			if (!StringHelper.isNull(vo.getBusinessOrderRecord()
					.getUserRealAddress())) {
				hql = hql + " and a.userRealAddress like '%"
						+ vo.getBusinessOrderRecord().getUserRealAddress()
						+ "%'";
			}
			if (!StringHelper.isNull(vo.getBusinessOrderRecord()
					.getUserRealMobile())) {
				hql = hql + " and a.userRealMobile = "
						+ vo.getBusinessOrderRecord().getUserRealMobile();
			}
			if (!StringHelper.isNull(vo.getBusinessOrderRecord()
					.getUserRealPlane())) {
				hql = hql + " and a.userRealPlane = "
						+ vo.getBusinessOrderRecord().getUserRealPlane();
			}
			if (!StringHelper.isNull(vo.getBusinessOrderRecord()
					.getUserRealPostcode())) {
				hql = hql + " and a.userRealPostcode = "
						+ vo.getBusinessOrderRecord().getUserRealPostcode();
			}
			if (vo.getBusinessOrderRecord().getPrimaryConfigurationId() != null) {
				hql = hql
						+ " and a.primaryConfigurationId = "
						+ vo.getBusinessOrderRecord()
								.getPrimaryConfigurationId();
			}
			if (!StringHelper.isNull(vo.getUserRealStartTime())) {
				hql = hql + " and a.userRealTime >='"
						+ vo.getUserRealStartTime() + "'";
			}
			if (!StringHelper.isNull(vo.getUserRealEndTime())) {
				hql = hql + " and a.userRealTime <='" + vo.getUserRealEndTime()
						+ "'";
			}
			if (vo.getBusinessOrderRecord().getPaymentType() != null) {
				hql = hql + " and a.paymentType = "
						+ vo.getBusinessOrderRecord().getPaymentType();
			}
			if (!StringHelper.isNull(vo.getBusinessOrderRecord()
					.getPaymentTypeName())) {
				hql = hql + " and a.paymentTypeName like '"
						+ vo.getBusinessOrderRecord().getPaymentTypeName()
						+ "%'";
			}
			if (vo.getBusinessOrderRecord().getPaymentStatus() != null) {
				hql = hql + " and a.paymentStatus = "
						+ vo.getBusinessOrderRecord().getPaymentStatus();
			}
			if (!StringHelper.isNull(vo.getBusinessOrderRecord()
					.getPaymentStatusName())) {
				hql = hql + " and a.paymentStatusName like '%"
						+ vo.getBusinessOrderRecord().getPaymentStatusName()
						+ "%'";
			}
			if (vo.getBusinessOrderRecord().getPaymentProvider() != null) {
				hql = hql + " and a.paymentProvider = "
						+ vo.getBusinessOrderRecord().getPaymentProvider();
			}
			if (!StringHelper.isNull(vo.getBusinessOrderRecord()
					.getPaymentProviderName())) {
				hql = hql + " and a.paymentProviderName like '%"
						+ vo.getBusinessOrderRecord().getPaymentProviderName()
						+ "%'";
			}
			if (!StringHelper.isNull(vo.getPaymentStartTime())) {
				hql = hql + " and a.paymentTime >='" + vo.getPaymentStartTime()
						+ "'";
			}
			if (!StringHelper.isNull(vo.getPaymentEndTime())) {
				hql = hql + " and a.paymentTime <='" + vo.getPaymentEndTime()
						+ "'";
			}
			if (!StringHelper.isNull(vo.getOperateStartTime())) {
				hql = hql + " and a.operateTime >='" + vo.getOperateStartTime()
						+ "'";
			}
			if (!StringHelper.isNull(vo.getOperateEndTime())) {
				hql = hql + " and a.operateTime <='" + vo.getOperateEndTime()
						+ "'";
			}
			if (vo.getBusinessOrderRecord().getOperateStatus() != null
					&& !vo.getBusinessOrderRecord().getOperateStatus()
							.equals("0")) {
				hql = hql + " and a.operateStatus = "
						+ vo.getBusinessOrderRecord().getOperateStatus();
			}
			if (!StringHelper.isNull(vo.getBusinessOrderRecord()
					.getOperateStatusName())) {
				hql = hql + " and a.operateStatusName like '%"
						+ vo.getBusinessOrderRecord().getOperateStatusName()
						+ "%'";
			}
			if (!StringHelper.isNull(vo.getCreateStartTime())) {
				hql = hql + " and a.createTime >='" + vo.getCreateStartTime()
						+ "'";
			}
			if (!StringHelper.isNull(vo.getCreateEndTime())) {
				hql = hql + " and a.createTime <='" + vo.getCreateEndTime()
						+ "'";
			}
			if (vo.getBusinessOrderRecord().getOriginalPrice() != null) {
				hql = hql + " and a.originalPrice = "
						+ vo.getBusinessOrderRecord().getOriginalPrice();
			}
			if (vo.getBusinessOrderRecord().getTotalPrice() != null) {
				hql = hql + " and a.totalPrice = "
						+ vo.getBusinessOrderRecord().getTotalPrice();
			}
			if (vo.getBusinessOrderRecord().getLogisticsCompanyId() != null) {
				hql = hql + " and a.logisticsCompanyId = "
						+ vo.getBusinessOrderRecord().getLogisticsCompanyId();
			}
			if (!StringHelper.isNull(vo.getBusinessOrderRecord()
					.getLogisticsCompanyName())) {
				hql = hql + " and a.logisticsCompanyName like '%"
						+ vo.getBusinessOrderRecord().getLogisticsCompanyName()
						+ "%'";
			}
			if (vo.getBusinessOrderRecord().getDeliveryStaffId() != null) {
				hql = hql + " and a.deliveryStaffId = "
						+ vo.getBusinessOrderRecord().getDeliveryStaffId();
			}
			if (!StringHelper.isNull(vo.getBusinessOrderRecord()
					.getDeliveryStaffName())) {
				hql = hql + " and a.deliveryStaffName like '%"
						+ vo.getBusinessOrderRecord().getDeliveryStaffName()
						+ "%'";
			}
			if (!StringHelper.isNull(vo.getBusinessOrderRecord()
					.getDeliveryStaffMobile())) {
				hql = hql + " and a.deliveryStaffMobile = '"
						+ vo.getBusinessOrderRecord().getDeliveryStaffMobile()
						+ "'";
			}
			if (!StringHelper.isNull(vo.getDeliveryStartStartTime())) {
				hql = hql + " and a.deliveryStartTime >='"
						+ vo.getDeliveryStartStartTime() + "'";
			}
			if (!StringHelper.isNull(vo.getDeliveryStartEndTime())) {
				hql = hql + " and a.deliveryStartTime <='"
						+ vo.getDeliveryStartEndTime() + "'";
			}
			if (!StringHelper.isNull(vo.getDeliveryEndStartTime())) {
				hql = hql + " and a.deliveryEndTime >='"
						+ vo.getDeliveryEndStartTime() + "'";
			}
			if (!StringHelper.isNull(vo.getDeliveryEndEndTime())) {
				hql = hql + " and a.deliveryEndTime <='"
						+ vo.getDeliveryEndEndTime() + "'";
			}
			if (vo.getBusinessOrderRecord().getDeliveryStatus() != null) {
				hql = hql + " and a.deliveryStatus = "
						+ vo.getBusinessOrderRecord().getDeliveryStatus();
			}
			if (!StringHelper.isNull(vo.getBusinessOrderRecord()
					.getDeliveryStatusName())) {
				hql = hql + " and a.deliveryStatusName like '%"
						+ vo.getBusinessOrderRecord().getDeliveryStatusName()
						+ "%'";
			}
			if (vo.getBusinessOrderRecord().getSourceId() != null) {
				hql = hql + " and a.sourceId = "
						+ vo.getBusinessOrderRecord().getSourceId();
			}
			if (!StringHelper.isNull(vo.getBusinessOrderRecord()
					.getSourceName())) {
				hql = hql + " and a.sourceName like '%"
						+ vo.getBusinessOrderRecord().getSourceName() + "%'";
			}
			if (vo.getBusinessOrderRecord().getSourceType() != null) {
				hql = hql + " and a.sourceType = "
						+ vo.getBusinessOrderRecord().getSourceType();
			}
			if (!StringHelper.isNull(vo.getBusinessOrderRecord()
					.getSourceTypeName())) {
				hql = hql + " and a.sourceTypeName like '%"
						+ vo.getBusinessOrderRecord().getSourceTypeName()
						+ "%'";
			}
			if (!StringHelper.isNull(vo.getBusinessOrderRecord().getSerNo())) {
				hql = hql + " and a.serNo = '"
						+ vo.getBusinessOrderRecord().getSerNo() + "'";
			}
			if (vo.getBusinessOrderRecord().getSellerId() != null) {
				hql = hql + " and a.sellerId = "
						+ vo.getBusinessOrderRecord().getSellerId();
			}
			if (!StringHelper.isNull(vo.getBusinessOrderRecord()
					.getSellerName())) {
				hql = hql + " and a.sellerName like '%"
						+ vo.getBusinessOrderRecord().getSellerName() + "%'";
			}
			if (vo.getBusinessOrderRecord().getCusId() != null) {
				hql = hql + " and a.cusId = "
						+ vo.getBusinessOrderRecord().getCusId();
			}
			if (!StringHelper.isNull(vo.getBusinessOrderRecord().getCusName())) {
				hql = hql + " and a.cusName like '%"
						+ vo.getBusinessOrderRecord().getCusName() + "%'";
			}
		}
		if (!StringHelper.isNull(orderField) && !StringHelper.isNull(direction)) {
			hql = hql + " order by " + orderField + " " + direction;
		} else {
			hql += " order by a.id desc,c.orderRecordId desc";
		}
		List l = commonDao.getEntitiesByPropertiesWithHql(hql, curPage,
				pageSize);
		List<BusinessOrderVO> resList = new LinkedList<BusinessOrderVO>();
		String tempBusinessOrderRecordId = "0";
		BusinessOrderVO businessOrderVO = new BusinessOrderVO();
		List<BusinessGoods> tempGoodsList = new LinkedList<BusinessGoods>();
		List<BusinessOrderGoodsRelation> tempOrderGoodsRelationList = new LinkedList<BusinessOrderGoodsRelation>();
		CommonStaff staff = null;
		for (int i = 0; i < l.size(); i++) {
			Object[] obj = (Object[]) l.get(i);
			BusinessOrderRecord po = (BusinessOrderRecord) obj[0];
			BusinessGoods goods = (BusinessGoods) obj[1];
			BusinessOrderGoodsRelation orderGoodsRelation = (BusinessOrderGoodsRelation) obj[2];
			// CommonStaff staff = (CommonStaff)obj[3];

			if (goods != null) {
				staff = staffService.findByid(po.getStaffId());
			} else {
				log.debug("goods is null! --> " + vo + " | -->" + hql);
			}

			businessOrderVO.setStaff(staff);
			// 判定是不是同一个订单的商品，如果是就汇总到订单中
			if (!tempBusinessOrderRecordId.equals(po.getId().toString())) {
				if (!tempBusinessOrderRecordId.equals("0")) {
					businessOrderVO.setBusinessGoodsList(tempGoodsList);
					businessOrderVO
							.setBusinessOrderGoodsRelationList(tempOrderGoodsRelationList);
					resList.add(businessOrderVO);
				}
				tempBusinessOrderRecordId = po.getId().toString();
				businessOrderVO = new BusinessOrderVO(); // 初始化新订单
				tempGoodsList = new ArrayList<BusinessGoods>(); // 初始化订单所有商品列表
				tempOrderGoodsRelationList = new LinkedList<BusinessOrderGoodsRelation>();// 初始化订单所有商品中间表列表
				// BusinessOrderRecord
				businessOrderVO.setBusinessOrderRecord(po);
				businessOrderVO.setStaff(staff);
				goods.setGoodsOriginalPrice(goods.getGoodsOriginalPrice()
						* staff.getViewDiscount());
				tempGoodsList.add(goods);
				tempOrderGoodsRelationList.add(orderGoodsRelation);
				if (i + 1 == l.size()) {
					businessOrderVO.setBusinessGoodsList(tempGoodsList);
					businessOrderVO
							.setBusinessOrderGoodsRelationList(tempOrderGoodsRelationList);
					resList.add(businessOrderVO);
				}
			} else {
				goods.setGoodsOriginalPrice(goods.getGoodsOriginalPrice()
						* staff.getViewDiscount());
				tempGoodsList.add(goods);
				tempOrderGoodsRelationList.add(orderGoodsRelation);
				if (i + 1 == l.size()) {
					businessOrderVO.setBusinessGoodsList(tempGoodsList);
					businessOrderVO
							.setBusinessOrderGoodsRelationList(tempOrderGoodsRelationList);
					resList.add(businessOrderVO);
				}
			}
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
	 * @return List<OrderVO>
	 */
	public List<BusinessOrderVO> orderListExcel(BusinessOrderVO vo,
			int curPage, int pageSize, String orderField, String direction) {
		String hql = "from BusinessOrderRecord a, BusinessGoods b, BusinessOrderGoodsRelation c where c.orderRecordId=a.id"
				+ " and c.goodsId=b.id";
		if (vo != null && vo.getBusinessOrderRecord() != null) {
			if (!StringHelper.isNull(vo.getBusinessOrderRecord().getOrderNo())) {
				hql = hql + " and a.orderNo = '"
						+ vo.getBusinessOrderRecord().getOrderNo() + "'";
			}
			if (vo.getBusinessOrderRecord().getId() != null) {
				hql = hql + " and a.id = "
						+ vo.getBusinessOrderRecord().getId();
			}
			if (vo.getBusinessOrderRecord().getClientUserId() != null) {
				hql = hql + " and a.clientUserId = "
						+ vo.getBusinessOrderRecord().getClientUserId();
			}
			if (!StringHelper.isNull(vo.getBusinessOrderRecord()
					.getClientUserName())) {
				hql = hql + " and a.clientUserName like '%"
						+ vo.getBusinessOrderRecord().getClientUserName()
						+ "%'";
			}
			if (!StringHelper.isNull(vo.getBusinessOrderRecord()
					.getClientUserMobile())) {
				hql = hql + " and a.clientUserMobile = '"
						+ vo.getBusinessOrderRecord().getClientUserMobile()
						+ "'";
			}
			if (!StringHelper.isNull(vo.getBusinessOrderRecord()
					.getClientUserEmail())) {
				hql = hql + " and a.clientUserEmail = '"
						+ vo.getBusinessOrderRecord().getClientUserEmail()
						+ "'";
			}
			if (!StringHelper.isNull(vo.getBusinessOrderRecord()
					.getClientUserSex())) {
				hql = hql + " and a.clientUserSex = '"
						+ vo.getBusinessOrderRecord().getClientUserSex() + "'";
			}
			if (vo.getBusinessOrderRecord().getClientUserType() != null) {
				hql = hql + " and a.clientUserType = "
						+ vo.getBusinessOrderRecord().getClientUserType();
			}
			if (!StringHelper.isNull(vo.getBusinessOrderRecord()
					.getClientUserTypeName())) {
				hql = hql + " and a.clientUserTypeName like '%"
						+ vo.getBusinessOrderRecord().getClientUserTypeName()
						+ "%'";
			}
			if (!StringHelper.isNull(vo.getBusinessOrderRecord().getWxOpenId())) {
				hql = hql + " and a.wxOpenId = '"
						+ vo.getBusinessOrderRecord().getWxOpenId() + "'";
			}
			if (!StringHelper.isNull(vo.getBusinessOrderRecord().getStaffId())) {
				hql = hql + " and a.staffId = '"
						+ vo.getBusinessOrderRecord().getStaffId() + "'";
			}
			if (!StringHelper.isNull(vo.getBusinessOrderRecord()
					.getUserRealName())) {
				hql = hql + " and a.userRealName like '%"
						+ vo.getBusinessOrderRecord().getUserRealName() + "%'";
			}
			if (vo.getBusinessOrderRecord().getCountryId() != null) {
				hql = hql + " and a.countryId = "
						+ vo.getBusinessOrderRecord().getCountryId();
			}
			if (!StringHelper.isNull(vo.getBusinessOrderRecord()
					.getCountryName())) {
				hql = hql + " and a.countryName like '%"
						+ vo.getBusinessOrderRecord().getCountryName() + "%'";
			}
			if (vo.getBusinessOrderRecord().getProvinceId() != null) {
				hql = hql + " and a.provinceId = "
						+ vo.getBusinessOrderRecord().getProvinceId();
			}
			if (!StringHelper.isNull(vo.getBusinessOrderRecord()
					.getProvinceName())) {
				hql = hql + " and a.provinceName like '%"
						+ vo.getBusinessOrderRecord().getProvinceName() + "%'";
			}
			if (vo.getBusinessOrderRecord().getCityId() != null) {
				hql = hql + " and a.cityId = '"
						+ vo.getBusinessOrderRecord().getCityId() + "'";
			}
			if (!StringHelper.isNull(vo.getBusinessOrderRecord().getCityName())) {
				hql = hql + " and a.cityName like '%"
						+ vo.getBusinessOrderRecord().getCityName() + "%'";
			}
			if (vo.getBusinessOrderRecord().getAreaId() != null) {
				hql = hql + " and a.areaId = "
						+ vo.getBusinessOrderRecord().getAreaId();
			}
			if (!StringHelper.isNull(vo.getBusinessOrderRecord().getAreaName())) {
				hql = hql + " and a.areaName like '%"
						+ vo.getBusinessOrderRecord().getAreaName() + "%'";
			}
			if (!StringHelper.isNull(vo.getBusinessOrderRecord()
					.getUserRealAddress())) {
				hql = hql + " and a.userRealAddress like '%"
						+ vo.getBusinessOrderRecord().getUserRealAddress()
						+ "%'";
			}
			if (!StringHelper.isNull(vo.getBusinessOrderRecord()
					.getUserRealMobile())) {
				hql = hql + " and a.userRealMobile = "
						+ vo.getBusinessOrderRecord().getUserRealMobile();
			}
			if (!StringHelper.isNull(vo.getBusinessOrderRecord()
					.getUserRealPlane())) {
				hql = hql + " and a.userRealPlane = "
						+ vo.getBusinessOrderRecord().getUserRealPlane();
			}
			if (!StringHelper.isNull(vo.getBusinessOrderRecord()
					.getUserRealPostcode())) {
				hql = hql + " and a.userRealPostcode = "
						+ vo.getBusinessOrderRecord().getUserRealPostcode();
			}
			if (vo.getBusinessOrderRecord().getPrimaryConfigurationId() != null) {
				hql = hql
						+ " and a.primaryConfigurationId = "
						+ vo.getBusinessOrderRecord()
								.getPrimaryConfigurationId();
			}
			if (!StringHelper.isNull(vo.getUserRealStartTime())) {
				hql = hql + " and a.userRealTime >='"
						+ vo.getUserRealStartTime() + "'";
			}
			if (!StringHelper.isNull(vo.getUserRealEndTime())) {
				hql = hql + " and a.userRealTime <='" + vo.getUserRealEndTime()
						+ "'";
			}
			if (vo.getBusinessOrderRecord().getPaymentType() != null) {
				hql = hql + " and a.paymentType = "
						+ vo.getBusinessOrderRecord().getPaymentType();
			}
			if (!StringHelper.isNull(vo.getBusinessOrderRecord()
					.getPaymentTypeName())) {
				hql = hql + " and a.paymentTypeName like '"
						+ vo.getBusinessOrderRecord().getPaymentTypeName()
						+ "%'";
			}
			if (vo.getBusinessOrderRecord().getPaymentStatus() != null) {
				hql = hql + " and a.paymentStatus = "
						+ vo.getBusinessOrderRecord().getPaymentStatus();
			}
			if (!StringHelper.isNull(vo.getBusinessOrderRecord()
					.getPaymentStatusName())) {
				hql = hql + " and a.paymentStatusName like '%"
						+ vo.getBusinessOrderRecord().getPaymentStatusName()
						+ "%'";
			}
			if (vo.getBusinessOrderRecord().getPaymentProvider() != null) {
				hql = hql + " and a.paymentProvider = "
						+ vo.getBusinessOrderRecord().getPaymentProvider();
			}
			if (!StringHelper.isNull(vo.getBusinessOrderRecord()
					.getPaymentProviderName())) {
				hql = hql + " and a.paymentProviderName like '%"
						+ vo.getBusinessOrderRecord().getPaymentProviderName()
						+ "%'";
			}
			if (!StringHelper.isNull(vo.getPaymentStartTime())) {
				hql = hql + " and a.paymentTime >='" + vo.getPaymentStartTime()
						+ "'";
			}
			if (!StringHelper.isNull(vo.getPaymentEndTime())) {
				hql = hql + " and a.paymentTime <='" + vo.getPaymentEndTime()
						+ "'";
			}
			if (!StringHelper.isNull(vo.getOperateStartTime())) {
				hql = hql + " and a.operateTime >='" + vo.getOperateStartTime()
						+ "'";
			}
			if (!StringHelper.isNull(vo.getOperateEndTime())) {
				hql = hql + " and a.operateTime <='" + vo.getOperateEndTime()
						+ "'";
			}
			if (vo.getBusinessOrderRecord().getOperateStatus() != null
					&& !vo.getBusinessOrderRecord().getOperateStatus()
							.equals("0")) {
				hql = hql + " and a.operateStatus = "
						+ vo.getBusinessOrderRecord().getOperateStatus();
			}
			if (!StringHelper.isNull(vo.getBusinessOrderRecord()
					.getOperateStatusName())) {
				hql = hql + " and a.operateStatusName like '%"
						+ vo.getBusinessOrderRecord().getOperateStatusName()
						+ "%'";
			}
			if (!StringHelper.isNull(vo.getCreateStartTime())) {
				hql = hql + " and a.createTime >='" + vo.getCreateStartTime()
						+ "'";
			}
			if (!StringHelper.isNull(vo.getCreateEndTime())) {
				hql = hql + " and a.createTime <='" + vo.getCreateEndTime()
						+ "'";
			}
			if (vo.getBusinessOrderRecord().getOriginalPrice() != null) {
				hql = hql + " and a.originalPrice = "
						+ vo.getBusinessOrderRecord().getOriginalPrice();
			}
			if (vo.getBusinessOrderRecord().getTotalPrice() != null) {
				hql = hql + " and a.totalPrice = "
						+ vo.getBusinessOrderRecord().getTotalPrice();
			}
			if (vo.getBusinessOrderRecord().getLogisticsCompanyId() != null) {
				hql = hql + " and a.logisticsCompanyId = "
						+ vo.getBusinessOrderRecord().getLogisticsCompanyId();
			}
			if (!StringHelper.isNull(vo.getBusinessOrderRecord()
					.getLogisticsCompanyName())) {
				hql = hql + " and a.logisticsCompanyName like '%"
						+ vo.getBusinessOrderRecord().getLogisticsCompanyName()
						+ "%'";
			}
			if (vo.getBusinessOrderRecord().getDeliveryStaffId() != null) {
				hql = hql + " and a.deliveryStaffId = "
						+ vo.getBusinessOrderRecord().getDeliveryStaffId();
			}
			if (!StringHelper.isNull(vo.getBusinessOrderRecord()
					.getDeliveryStaffName())) {
				hql = hql + " and a.deliveryStaffName like '%"
						+ vo.getBusinessOrderRecord().getDeliveryStaffName()
						+ "%'";
			}
			if (!StringHelper.isNull(vo.getBusinessOrderRecord()
					.getDeliveryStaffMobile())) {
				hql = hql + " and a.deliveryStaffMobile = '"
						+ vo.getBusinessOrderRecord().getDeliveryStaffMobile()
						+ "'";
			}
			if (!StringHelper.isNull(vo.getDeliveryStartStartTime())) {
				hql = hql + " and a.deliveryStartTime >='"
						+ vo.getDeliveryStartStartTime() + "'";
			}
			if (!StringHelper.isNull(vo.getDeliveryStartEndTime())) {
				hql = hql + " and a.deliveryStartTime <='"
						+ vo.getDeliveryStartEndTime() + "'";
			}
			if (!StringHelper.isNull(vo.getDeliveryEndStartTime())) {
				hql = hql + " and a.deliveryEndTime >='"
						+ vo.getDeliveryEndStartTime() + "'";
			}
			if (!StringHelper.isNull(vo.getDeliveryEndEndTime())) {
				hql = hql + " and a.deliveryEndTime <='"
						+ vo.getDeliveryEndEndTime() + "'";
			}
			if (vo.getBusinessOrderRecord().getDeliveryStatus() != null) {
				hql = hql + " and a.deliveryStatus = "
						+ vo.getBusinessOrderRecord().getDeliveryStatus();
			}
			if (!StringHelper.isNull(vo.getBusinessOrderRecord()
					.getDeliveryStatusName())) {
				hql = hql + " and a.deliveryStatusName like '%"
						+ vo.getBusinessOrderRecord().getDeliveryStatusName()
						+ "%'";
			}
			if (vo.getBusinessOrderRecord().getSourceId() != null) {
				hql = hql + " and a.sourceId = "
						+ vo.getBusinessOrderRecord().getSourceId();
			}
			if (!StringHelper.isNull(vo.getBusinessOrderRecord()
					.getSourceName())) {
				hql = hql + " and a.sourceName like '%"
						+ vo.getBusinessOrderRecord().getSourceName() + "%'";
			}
			if (vo.getBusinessOrderRecord().getSourceType() != null) {
				hql = hql + " and a.sourceType = "
						+ vo.getBusinessOrderRecord().getSourceType();
			}
			if (!StringHelper.isNull(vo.getBusinessOrderRecord()
					.getSourceTypeName())) {
				hql = hql + " and a.sourceTypeName like '%"
						+ vo.getBusinessOrderRecord().getSourceTypeName()
						+ "%'";
			}
			if (!StringHelper.isNull(vo.getBusinessOrderRecord().getSerNo())) {
				hql = hql + " and a.serNo = '"
						+ vo.getBusinessOrderRecord().getSerNo() + "'";
			}
			if (vo.getBusinessOrderRecord().getSellerId() != null) {
				hql = hql + " and a.sellerId = "
						+ vo.getBusinessOrderRecord().getSellerId();
			}
			if (!StringHelper.isNull(vo.getBusinessOrderRecord()
					.getSellerName())) {
				hql = hql + " and a.sellerName like '%"
						+ vo.getBusinessOrderRecord().getSellerName() + "%'";
			}
			if (vo.getBusinessOrderRecord().getCusId() != null) {
				hql = hql + " and a.cusId = "
						+ vo.getBusinessOrderRecord().getCusId();
			}
			if (!StringHelper.isNull(vo.getBusinessOrderRecord().getCusName())) {
				hql = hql + " and a.cusName like '%"
						+ vo.getBusinessOrderRecord().getCusName() + "%'";
			}
		}
		if (!StringHelper.isNull(orderField) && !StringHelper.isNull(direction)) {
			hql = hql + " order by " + orderField + " " + direction;
		} else {
			hql += " order by a.id desc,c.orderRecordId desc, a.clientUserId";
		}
		List l = commonDao.getEntitiesByPropertiesWithHql(hql, curPage,
				pageSize);
		List<BusinessOrderVO> resList = new LinkedList<BusinessOrderVO>();
		String tempBusinessOrderRecordId = "0";
		BusinessOrderVO businessOrderVO = new BusinessOrderVO();
		List<BusinessGoods> tempGoodsList = new LinkedList<BusinessGoods>();
		List<BusinessOrderGoodsRelation> tempOrderGoodsRelationList = new LinkedList<BusinessOrderGoodsRelation>();
		for (int i = 0; i < l.size(); i++) {
			Object[] obj = (Object[]) l.get(i);
			BusinessOrderRecord po = (BusinessOrderRecord) obj[0];
			BusinessGoods goods = (BusinessGoods) obj[1];
			BusinessOrderGoodsRelation orderGoodsRelation = (BusinessOrderGoodsRelation) obj[2];
			// 判定是不是同一个订单的商品，如果是就汇总到订单中
			if (!tempBusinessOrderRecordId.equals(po.getId().toString())) {
				if (!tempBusinessOrderRecordId.equals("0")) {
					businessOrderVO.setBusinessGoodsList(tempGoodsList);
					businessOrderVO
							.setBusinessOrderGoodsRelationList(tempOrderGoodsRelationList);
					resList.add(businessOrderVO);
				}
				tempBusinessOrderRecordId = po.getId().toString();
				businessOrderVO = new BusinessOrderVO(); // 初始化新订单
				tempGoodsList = new ArrayList<BusinessGoods>(); // 初始化订单所有商品列表
				tempOrderGoodsRelationList = new LinkedList<BusinessOrderGoodsRelation>();// 初始化订单所有商品中间表列表
				// BusinessOrderRecord
				businessOrderVO.setBusinessOrderRecord(po);
				tempGoodsList.add(goods);
				tempOrderGoodsRelationList.add(orderGoodsRelation);
				if (i + 1 == l.size()) {
					businessOrderVO.setBusinessGoodsList(tempGoodsList);
					businessOrderVO
							.setBusinessOrderGoodsRelationList(tempOrderGoodsRelationList);
					resList.add(businessOrderVO);
				}
			} else {
				tempGoodsList.add(goods);
				tempOrderGoodsRelationList.add(orderGoodsRelation);
				if (i + 1 == l.size()) {
					businessOrderVO.setBusinessGoodsList(tempGoodsList);
					businessOrderVO
							.setBusinessOrderGoodsRelationList(tempOrderGoodsRelationList);
					resList.add(businessOrderVO);
				}
			}
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
	 * @return List<OrderVO>
	 */
	public List<Object[]> orderGoodsCount(BusinessOrderVO vo, int curPage,
			int pageSize, String orderField, String direction) {
		String hql = "select b.goodsName, count(*) from BusinessOrderRecord a, BusinessGoods b, BusinessOrderGoodsRelation c "
				+ " where c.orderRecordId=a.id and c.goodsId=b.id";
		if (vo != null && vo.getBusinessOrderRecord() != null) {
			if (!StringHelper.isNull(vo.getBusinessOrderRecord().getOrderNo())) {
				hql = hql + " and a.orderNo = '"
						+ vo.getBusinessOrderRecord().getOrderNo() + "'";
			}
			if (vo.getBusinessOrderRecord().getId() != null) {
				hql = hql + " and a.id = "
						+ vo.getBusinessOrderRecord().getId();
			}
			if (vo.getBusinessOrderRecord().getClientUserId() != null) {
				hql = hql + " and a.clientUserId = "
						+ vo.getBusinessOrderRecord().getClientUserId();
			}
			if (!StringHelper.isNull(vo.getBusinessOrderRecord()
					.getClientUserName())) {
				hql = hql + " and a.clientUserName like '%"
						+ vo.getBusinessOrderRecord().getClientUserName()
						+ "%'";
			}
			if (!StringHelper.isNull(vo.getBusinessOrderRecord()
					.getClientUserMobile())) {
				hql = hql + " and a.clientUserMobile = '"
						+ vo.getBusinessOrderRecord().getClientUserMobile()
						+ "'";
			}
			if (!StringHelper.isNull(vo.getBusinessOrderRecord()
					.getClientUserEmail())) {
				hql = hql + " and a.clientUserEmail = '"
						+ vo.getBusinessOrderRecord().getClientUserEmail()
						+ "'";
			}
			if (!StringHelper.isNull(vo.getBusinessOrderRecord()
					.getClientUserSex())) {
				hql = hql + " and a.clientUserSex = '"
						+ vo.getBusinessOrderRecord().getClientUserSex() + "'";
			}
			if (vo.getBusinessOrderRecord().getClientUserType() != null) {
				hql = hql + " and a.clientUserType = "
						+ vo.getBusinessOrderRecord().getClientUserType();
			}
			if (!StringHelper.isNull(vo.getBusinessOrderRecord()
					.getClientUserTypeName())) {
				hql = hql + " and a.clientUserTypeName like '%"
						+ vo.getBusinessOrderRecord().getClientUserTypeName()
						+ "%'";
			}
			if (!StringHelper.isNull(vo.getBusinessOrderRecord().getWxOpenId())) {
				hql = hql + " and a.wxOpenId = '"
						+ vo.getBusinessOrderRecord().getWxOpenId() + "'";
			}
			if (!StringHelper.isNull(vo.getBusinessOrderRecord().getStaffId())) {
				hql = hql + " and a.staffId = '"
						+ vo.getBusinessOrderRecord().getStaffId() + "'";
			}
			if (!StringHelper.isNull(vo.getBusinessOrderRecord()
					.getUserRealName())) {
				hql = hql + " and a.userRealName like '%"
						+ vo.getBusinessOrderRecord().getUserRealName() + "%'";
			}
			if (vo.getBusinessOrderRecord().getCountryId() != null) {
				hql = hql + " and a.countryId = "
						+ vo.getBusinessOrderRecord().getCountryId();
			}
			if (!StringHelper.isNull(vo.getBusinessOrderRecord()
					.getCountryName())) {
				hql = hql + " and a.countryName like '%"
						+ vo.getBusinessOrderRecord().getCountryName() + "%'";
			}
			if (vo.getBusinessOrderRecord().getProvinceId() != null) {
				hql = hql + " and a.provinceId = "
						+ vo.getBusinessOrderRecord().getProvinceId();
			}
			if (!StringHelper.isNull(vo.getBusinessOrderRecord()
					.getProvinceName())) {
				hql = hql + " and a.provinceName like '%"
						+ vo.getBusinessOrderRecord().getProvinceName() + "%'";
			}
			if (vo.getBusinessOrderRecord().getCityId() != null) {
				hql = hql + " and a.cityId = '"
						+ vo.getBusinessOrderRecord().getCityId() + "'";
			}
			if (!StringHelper.isNull(vo.getBusinessOrderRecord().getCityName())) {
				hql = hql + " and a.cityName like '%"
						+ vo.getBusinessOrderRecord().getCityName() + "%'";
			}
			if (vo.getBusinessOrderRecord().getAreaId() != null) {
				hql = hql + " and a.areaId = "
						+ vo.getBusinessOrderRecord().getAreaId();
			}
			if (!StringHelper.isNull(vo.getBusinessOrderRecord().getAreaName())) {
				hql = hql + " and a.areaName like '%"
						+ vo.getBusinessOrderRecord().getAreaName() + "%'";
			}
			if (!StringHelper.isNull(vo.getBusinessOrderRecord()
					.getUserRealAddress())) {
				hql = hql + " and a.userRealAddress like '%"
						+ vo.getBusinessOrderRecord().getUserRealAddress()
						+ "%'";
			}
			if (!StringHelper.isNull(vo.getBusinessOrderRecord()
					.getUserRealMobile())) {
				hql = hql + " and a.userRealMobile = "
						+ vo.getBusinessOrderRecord().getUserRealMobile();
			}
			if (!StringHelper.isNull(vo.getBusinessOrderRecord()
					.getUserRealPlane())) {
				hql = hql + " and a.userRealPlane = "
						+ vo.getBusinessOrderRecord().getUserRealPlane();
			}
			if (!StringHelper.isNull(vo.getBusinessOrderRecord()
					.getUserRealPostcode())) {
				hql = hql + " and a.userRealPostcode = "
						+ vo.getBusinessOrderRecord().getUserRealPostcode();
			}
			if (vo.getBusinessOrderRecord().getPrimaryConfigurationId() != null) {
				hql = hql
						+ " and a.primaryConfigurationId = "
						+ vo.getBusinessOrderRecord()
								.getPrimaryConfigurationId();
			}
			if (!StringHelper.isNull(vo.getUserRealStartTime())) {
				hql = hql + " and a.userRealTime >='"
						+ vo.getUserRealStartTime() + "'";
			}
			if (!StringHelper.isNull(vo.getUserRealEndTime())) {
				hql = hql + " and a.userRealTime <='" + vo.getUserRealEndTime()
						+ "'";
			}
			if (vo.getBusinessOrderRecord().getPaymentType() != null) {
				hql = hql + " and a.paymentType = "
						+ vo.getBusinessOrderRecord().getPaymentType();
			}
			if (!StringHelper.isNull(vo.getBusinessOrderRecord()
					.getPaymentTypeName())) {
				hql = hql + " and a.paymentTypeName like '"
						+ vo.getBusinessOrderRecord().getPaymentTypeName()
						+ "%'";
			}
			if (vo.getBusinessOrderRecord().getPaymentStatus() != null) {
				hql = hql + " and a.paymentStatus = "
						+ vo.getBusinessOrderRecord().getPaymentStatus();
			}
			if (!StringHelper.isNull(vo.getBusinessOrderRecord()
					.getPaymentStatusName())) {
				hql = hql + " and a.paymentStatusName like '%"
						+ vo.getBusinessOrderRecord().getPaymentStatusName()
						+ "%'";
			}
			if (vo.getBusinessOrderRecord().getPaymentProvider() != null) {
				hql = hql + " and a.paymentProvider = "
						+ vo.getBusinessOrderRecord().getPaymentProvider();
			}
			if (!StringHelper.isNull(vo.getBusinessOrderRecord()
					.getPaymentProviderName())) {
				hql = hql + " and a.paymentProviderName like '%"
						+ vo.getBusinessOrderRecord().getPaymentProviderName()
						+ "%'";
			}
			if (!StringHelper.isNull(vo.getPaymentStartTime())) {
				hql = hql + " and a.paymentTime >='" + vo.getPaymentStartTime()
						+ "'";
			}
			if (!StringHelper.isNull(vo.getPaymentEndTime())) {
				hql = hql + " and a.paymentTime <='" + vo.getPaymentEndTime()
						+ "'";
			}
			if (!StringHelper.isNull(vo.getOperateStartTime())) {
				hql = hql + " and a.operateTime >='" + vo.getOperateStartTime()
						+ "'";
			}
			if (!StringHelper.isNull(vo.getOperateEndTime())) {
				hql = hql + " and a.operateTime <='" + vo.getOperateEndTime()
						+ "'";
			}
			if (vo.getBusinessOrderRecord().getOperateStatus() != null
					&& !vo.getBusinessOrderRecord().getOperateStatus()
							.equals("0")) {
				hql = hql + " and a.operateStatus = "
						+ vo.getBusinessOrderRecord().getOperateStatus();
			}
			if (!StringHelper.isNull(vo.getBusinessOrderRecord()
					.getOperateStatusName())) {
				hql = hql + " and a.operateStatusName like '%"
						+ vo.getBusinessOrderRecord().getOperateStatusName()
						+ "%'";
			}
			if (!StringHelper.isNull(vo.getCreateStartTime())) {
				hql = hql + " and a.createTime >='" + vo.getCreateStartTime()
						+ "'";
			}
			if (!StringHelper.isNull(vo.getCreateEndTime())) {
				hql = hql + " and a.createTime <='" + vo.getCreateEndTime()
						+ "'";
			}
			if (vo.getBusinessOrderRecord().getOriginalPrice() != null) {
				hql = hql + " and a.originalPrice = "
						+ vo.getBusinessOrderRecord().getOriginalPrice();
			}
			if (vo.getBusinessOrderRecord().getTotalPrice() != null) {
				hql = hql + " and a.totalPrice = "
						+ vo.getBusinessOrderRecord().getTotalPrice();
			}
			if (vo.getBusinessOrderRecord().getLogisticsCompanyId() != null) {
				hql = hql + " and a.logisticsCompanyId = "
						+ vo.getBusinessOrderRecord().getLogisticsCompanyId();
			}
			if (!StringHelper.isNull(vo.getBusinessOrderRecord()
					.getLogisticsCompanyName())) {
				hql = hql + " and a.logisticsCompanyName like '%"
						+ vo.getBusinessOrderRecord().getLogisticsCompanyName()
						+ "%'";
			}
			if (vo.getBusinessOrderRecord().getDeliveryStaffId() != null) {
				hql = hql + " and a.deliveryStaffId = "
						+ vo.getBusinessOrderRecord().getDeliveryStaffId();
			}
			if (!StringHelper.isNull(vo.getBusinessOrderRecord()
					.getDeliveryStaffName())) {
				hql = hql + " and a.deliveryStaffName like '%"
						+ vo.getBusinessOrderRecord().getDeliveryStaffName()
						+ "%'";
			}
			if (!StringHelper.isNull(vo.getBusinessOrderRecord()
					.getDeliveryStaffMobile())) {
				hql = hql + " and a.deliveryStaffMobile = '"
						+ vo.getBusinessOrderRecord().getDeliveryStaffMobile()
						+ "'";
			}
			if (!StringHelper.isNull(vo.getDeliveryStartStartTime())) {
				hql = hql + " and a.deliveryStartTime >='"
						+ vo.getDeliveryStartStartTime() + "'";
			}
			if (!StringHelper.isNull(vo.getDeliveryStartEndTime())) {
				hql = hql + " and a.deliveryStartTime <='"
						+ vo.getDeliveryStartEndTime() + "'";
			}
			if (!StringHelper.isNull(vo.getDeliveryEndStartTime())) {
				hql = hql + " and a.deliveryEndTime >='"
						+ vo.getDeliveryEndStartTime() + "'";
			}
			if (!StringHelper.isNull(vo.getDeliveryEndEndTime())) {
				hql = hql + " and a.deliveryEndTime <='"
						+ vo.getDeliveryEndEndTime() + "'";
			}
			if (vo.getBusinessOrderRecord().getDeliveryStatus() != null) {
				hql = hql + " and a.deliveryStatus = "
						+ vo.getBusinessOrderRecord().getDeliveryStatus();
			}
			if (!StringHelper.isNull(vo.getBusinessOrderRecord()
					.getDeliveryStatusName())) {
				hql = hql + " and a.deliveryStatusName like '%"
						+ vo.getBusinessOrderRecord().getDeliveryStatusName()
						+ "%'";
			}
			if (vo.getBusinessOrderRecord().getSourceId() != null) {
				hql = hql + " and a.sourceId = "
						+ vo.getBusinessOrderRecord().getSourceId();
			}
			if (!StringHelper.isNull(vo.getBusinessOrderRecord()
					.getSourceName())) {
				hql = hql + " and a.sourceName like '%"
						+ vo.getBusinessOrderRecord().getSourceName() + "%'";
			}
			if (vo.getBusinessOrderRecord().getSourceType() != null) {
				hql = hql + " and a.sourceType = "
						+ vo.getBusinessOrderRecord().getSourceType();
			}
			if (!StringHelper.isNull(vo.getBusinessOrderRecord()
					.getSourceTypeName())) {
				hql = hql + " and a.sourceTypeName like '%"
						+ vo.getBusinessOrderRecord().getSourceTypeName()
						+ "%'";
			}
			if (!StringHelper.isNull(vo.getBusinessOrderRecord().getSerNo())) {
				hql = hql + " and a.serNo = '"
						+ vo.getBusinessOrderRecord().getSerNo() + "'";
			}
			if (vo.getBusinessOrderRecord().getSellerId() != null) {
				hql = hql + " and a.sellerId = "
						+ vo.getBusinessOrderRecord().getSellerId();
			}
			if (!StringHelper.isNull(vo.getBusinessOrderRecord()
					.getSellerName())) {
				hql = hql + " and a.sellerName like '%"
						+ vo.getBusinessOrderRecord().getSellerName() + "%'";
			}
			if (vo.getBusinessOrderRecord().getCusId() != null) {
				hql = hql + " and a.cusId = "
						+ vo.getBusinessOrderRecord().getCusId();
			}
			if (!StringHelper.isNull(vo.getBusinessOrderRecord().getCusName())) {
				hql = hql + " and a.cusName like '%"
						+ vo.getBusinessOrderRecord().getCusName() + "%'";
			}
		}
		hql = hql + " group by b.goodsName";
		if (!StringHelper.isNull(orderField) && !StringHelper.isNull(direction)) {
			hql = hql + " order by " + orderField + " " + direction;
		} else {
			hql += " order by b.id";
		}
		List<Object[]> l = commonDao.getEntitiesByPropertiesWithHql(hql,
				curPage, pageSize);
		return l;
	}

	private String getHql(BusinessOrderVO query) {
//		StringBuffer hql = new StringBuffer();
//		hql.append("from BusinessOrderRecord record,BusinessOrderGoodsRelation rel,BusinessGoods goods,BusinessCategory cate,CommonStaff staff");
//		hql.append(" where rel.goodsId=goods.id and record.id=rel.orderRecordId");
//		hql.append(" and cate.id=goods.goodsCategoryId");
//		hql.append(" and staff.id=record.staffId");
		StringBuffer hql = new StringBuffer();
		hql.append("from BusinessOrderRecord record,BusinessOrderGoodsRelation rel,"
				+ " CommonStaff staff");
		
		hql.append(" where record.id=rel.orderRecordId");
		hql.append(" and staff.id=record.staffId");

		if (query != null) {
			// 分类
			if (query.getBusinessCategory() != null
					&& query.getBusinessCategory().getId() != null) {
				hql.append(" and cate.id=").append(
						query.getBusinessCategory().getId());
			}
			// 商品
			if (query.getBusinessGoods() != null
					&& query.getBusinessGoods().getId() != null) {
				hql.append(" and goods.id=").append(
						query.getBusinessGoods().getId());
			}
			if (!StringHelper.isNull(query.getProductName())) {
				hql.append(" and goods.goodsName like '%")
						.append(query.getProductName()).append("%'");
			}
			// 订单日期
			if (!StringHelper.isNull(query.getCreateStartTime())
					&& !StringHelper.isNull(query.getCreateEndTime())) {
				hql.append(" and record.createTime>=DATE_FORMAT('");
				hql.append(query.getCreateStartTime());
				hql.append("','%Y-%m-%d %H:%i:%s')");
				hql.append(" and record.createTime<=DATE_FORMAT('");
				hql.append(query.getCreateEndTime());
				hql.append("','%Y-%m-%d %H:%i:%s')");
			}
			// 订单ID
			if (query.getBusinessOrderRecord() != null) {
				if (query.getBusinessOrderRecord().getId() != null) {
					hql.append(" and record.id=").append(
							query.getBusinessOrderRecord().getId());
				}
			}

			// 指定商户
			if (query.getStaff() != null) {
				if (query.getStaff().getId() != null) {
					hql.append(" and record.staffId=").append(
							query.getStaff().getId());
				}
				if (!StringHelper.isNull(query.getStaff().getStaffName()))
					hql.append(" and staff.staffName like '%")
							.append(query.getStaff().getStaffName())
							.append("%'");
			}

			// 指定商户经理
			if (query.getLeaderStaff() != null) {
				if (query.getLeaderStaff().getId() != null) {
					hql.append(" and staff.leadStaffId=").append(
							query.getLeaderStaff().getId());
				}
			}

			// 收货人手机号
			if (query.getBusinessOrderRecord() != null) {
				if (!StringHelper.isNull(query.getBusinessOrderRecord()
						.getUserRealMobile())) {
					hql.append(" and record.userRealMobile like '%")
							.append(query.getBusinessOrderRecord()
									.getUserRealMobile()).append("%'");
				}
				// 真实手机号
				if (!StringHelper.isNull(query.getBusinessOrderRecord()
						.getClientUserMobile())) {
					hql.append(" and record.clientUserMobile like '%")
							.append(query.getBusinessOrderRecord()
									.getClientUserMobile()).append("%'");
				}

				// 收货人姓名
				if (!StringHelper.isNull(query.getBusinessOrderRecord()
						.getUserRealName())) {
					hql.append(" and record.userRealName like '%")
							.append(query.getBusinessOrderRecord()
									.getUserRealName()).append("%'");
				}
				// 指定用户Id
				if(query.getBusinessOrderRecord()
						.getClientUserId()!=null)
				{
					hql.append(" and record.clientUserId = ").append(query.getBusinessOrderRecord()
							.getClientUserId());
				}
				//支付状态
				if(query.getBusinessOrderRecord().getPaymentStatus()!=null)
				{
					hql.append(" and record.paymentStatus =").append(query.getBusinessOrderRecord().getPaymentStatus());
				}
				//订单操作状态
				if(query.getBusinessOrderRecord().getOperateStatus()!=null)
				{
					hql.append(" and record.operateStatus = ").append(query.getBusinessOrderRecord().getOperateStatus());
				}
				//支付渠道
				if(query.getBusinessOrderRecord().getPaymentProvider()!=null)
				{
					hql.append(" and record.paymentProvider = ").append(query.getBusinessOrderRecord().getPaymentProvider());
				}
				//派送状态
				if(query.getBusinessOrderRecord().getDeliveryStatus()!=null)
				{
					hql.append(" and record.deliveryStatus = ").append(query.getBusinessOrderRecord().getDeliveryStatus());
				}
				//订单编号
				if(!StringHelper.isNull(query.getBusinessOrderRecord().getOrderNo()))
				{
					hql.append(" and record.orderNo = '").append(query.getBusinessOrderRecord().getOrderNo()+"'");
				}
				//是否是子订单
				if(query.getBusinessOrderRecord().getIsChild()==null){
					hql.append(" and record.isChild is null ");
				}else{
					hql.append(" and record.isChild !=null ");
				}
			}

			// 复选
			if (query.getQueryCateList() != null
					&& query.getQueryCateList().length > 0) {
				hql.append(" and cate.id in(");
				hql.append(StringHelper.arrayToString(query.getQueryCateList()));
				hql.append(")");
			}

			// group by
			if (!StringHelper.isNull(query.getGroupBy())) {
				hql.append(" group by ").append(query.getGroupBy());
			}

			// order by
			if (!StringHelper.isNull(query.getOrderBy())
					&& !StringHelper.isNull(query.getOrderByField())) {
				hql.append(" order by ").append(query.getOrderBy()).append(" ")
						.append(query.getOrderByField());
			}
		}

		return hql.toString();
	}
	/**
	 * @return List<BusinessOrderRecord>
	 */
	public List<BusinessOrderRecord> listAll() {
		return dao.findAll();
	}

	public List<BusinessOrderRecord> findAlreadyDelivered() {
		String hql  = "from BusinessOrderRecord where deliveryStatus = 208";
		List<BusinessOrderRecord> orders = commonDao.getEntitiesByPropertiesWithHql(hql,0,0);
		return orders;
	}

	public List<ComboVO> findChildOrder(String businessOrderRecordId,
			String comboId,int pageNum, int pageSize) {
		String hql = "from BusinessOrderRecord b,Combo c where b.isChild = '"+businessOrderRecordId+"' and b.comboID = '"+ comboId+"' and c.id= '"+ comboId+"'" ;
		List<Object[]> objects = commonDao.getEntitiesByPropertiesWithHql(hql,pageNum,pageSize);
		List<ComboVO> comboVOs = new ArrayList<ComboVO>();
		for (Object[] obj : objects) {
			ComboVO comboVO = new ComboVO();
			BusinessOrderRecord bor = (BusinessOrderRecord)obj[0];
			Combo combo = (Combo) obj[1];
			List<BusinessGoods> goodsList = comboPlanService.findGoodsBycomboId(combo.getId().toString());
			comboVO.setCombo(combo);
			comboVO.setOrderRecord(bor);
			comboVO.setGoodsList(goodsList);
			comboVOs.add(comboVO);
		}
 		return comboVOs;
	}
	public int findChildOrderCount(String businessOrderRecordId,
			String comboId) {
		String hql = "from BusinessOrderRecord b,Combo c where b.isChild = '"+businessOrderRecordId+"' and b.comboID = '"+ comboId+"' and c.id= '"+ comboId+"'" ;
		List<Object[]> objects = commonDao.getEntitiesByPropertiesWithHql(hql,0,0);
 		return objects.size();
	}

}
