/**
 *2012-5-11 上午9:00:02
 */
package com.xnradmin.stat.service.business;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cntinker.util.StringHelper;
import com.xnradmin.core.dao.CommonDAO;
import com.xnradmin.core.dao.CommonJDBCDAO;
import com.xnradmin.core.dao.business.order.BusinessOrderRecordDAO;
import com.xnradmin.po.business.BusinessOrderRecord;
import com.xnradmin.vo.business.BusinessOrderVO;

/**
 * @autohr: xiang_liu
 * 
 */
@Service("AccountManagerStatService")
public class AccountManagerStatService {

	@Autowired
	private BusinessOrderRecordDAO dao;

	@Autowired
	private CommonDAO commonDao;

	@Autowired
	private CommonJDBCDAO commonJDBCDAO;

	static Log log = LogFactory.getLog(AccountManagerStatService.class);

	
	/**
	 * 客户经理订单与金额汇总
	 * 
	 * @param curPage
	 * @param pageSize
	 * @param orderField
	 * @param direction
	 * @return List<Object[]>
	 */
	public List<Object[]> findAccountManagerStat(BusinessOrderVO vo,
			int curPage, int pageSize) {
		String hql = "select b.leadStaffId, b.leadStaffName, count(*), sum(a.totalPrice) "
				+ " from BusinessOrderRecord a, CommonStaff b"
				+ " where a.staffId = b.id ";
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
			if (vo.getLeaderStaff()!=null && vo.getLeaderStaff().getId()!=null) {
				hql = hql + " and b.leadStaffId = "
						+ vo.getLeaderStaff().getId();
			}
			if (!StringHelper.isNull(vo.getStaffCreateStartTime())) {
				hql = hql + " and b.createTime >='" + vo.getStaffCreateStartTime()
						+ "'";
			}
			if (!StringHelper.isNull(vo.getStaffCreateEndTime())) {
				hql = hql + " and b.createTime <='" + vo.getStaffCreateEndTime()
						+ "'";
			}
		}
		hql = hql + " group by b.leadStaffId, b.leadStaffName";
		List<Object[]> l = commonDao.getEntitiesByPropertiesWithHql(hql,
				curPage, pageSize);
		return l;
	}
	
	
	/**
	 * 客户经理下采购商数量
	 * 
	 * @param curPage
	 * @param pageSize
	 * @param orderField
	 * @param direction
	 * @return List<Object[]>
	 */
	public List<Object[]> findLeadStaffCount(BusinessOrderVO vo) {
		String hql = "select leadStaffId, count(*) from CommonStaff where organizationId=15 "
				+ "group by leadStaffId";
		List<Object[]> l = commonDao.getEntitiesByPropertiesWithHql(hql, 0, 0);
		return l;
	}
	/**
	 * @return List<BusinessOrderRecord>
	 */
	public List<BusinessOrderRecord> listAll() {
		return dao.findAll();
	}

}
