/**
 *2012-5-11 上午9:00:02
 */
package com.xnradmin.core.service.mall.order;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cntinker.util.StringHelper;
import com.xnradmin.constant.ViewConstant;
import com.xnradmin.core.dao.CommonDAO;
import com.xnradmin.core.dao.mall.order.OrderRecordDAO;
import com.xnradmin.po.client.ClientUserInfo;
import com.xnradmin.po.mall.commodity.Goods;
import com.xnradmin.po.mall.order.OrderGoodsRelation;
import com.xnradmin.po.mall.order.OrderRecord;
import com.xnradmin.vo.mall.OrderVO;
import com.xnradmin.vo.stat.OrderRecordVO;

/**
 * @autohr: xiang_liu
 * 
 */
@Service("OrderRecordService")
public class OrderRecordService {

	@Autowired
	private OrderRecordDAO dao;

	@Autowired
	private CommonDAO commonDao;

	static Log log = LogFactory.getLog(OrderRecordService.class);

	/**
	 * 
	 * @param po
	 * @return int
	 */
	public Long save(OrderRecord po) {
		Serializable cls = dao.save(po);
		return Long.valueOf(((cls.toString())));
	}

	public OrderRecord findByid(String id) {
		return dao.findById(Long.valueOf(id));
	}

	public OrderRecord findByOutOderNo(String outOrderNo) {
		String hql = "from OrderRecord where orderNo='" + outOrderNo + "'";
		List<OrderRecord> l = commonDao.getEntitiesByPropertiesWithHql(hql, 1,
				1);
		if (l == null || l.size() <= 0)
			return null;
		return l.get(0);
	}

	/**
	 * @param po
	 * @return int
	 */
	public int modify(OrderRecord po) {
		OrderRecord old = new OrderRecord();
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
		OrderRecord po = this.dao.findById(Long.valueOf(id));
		this.dao.delete(po);
	}

	public int removeOrderRecordId(String id) {
		return dao.removeOrderRecordId(Long.valueOf(id));
	}

	/**
	 * @param brandname
	 * @return int
	 */
	public int getUserOrderCount(String clientUserId) {
		String hql = "select count(*) from OrderRecord where clientUserId = "
				+ clientUserId;
		return this.commonDao.getNumberOfEntitiesWithHql(hql);
	}

	/**
	 * @param brandname
	 * @return int
	 */
	public int getCount(String orderNo, String orderRecordId,
			String clientUserId, String clientUserName,
			String clientUserMobile, String clientUserEmail,
			String clientUserSex, String clientUserType,
			String clientUserTypeName, String wxOpenId, String staffId,
			String userRealMobile, String userRealPlane, String userRealName,
			String countryId, String countryName, String provinceId,
			String provinceName, String cityId, String cityName, String areaId,
			String areaName, String userRealAddress, String userRealPostcode,
			String userRealStartTime, String userRealEndTime,
			String paymentType, String paymentTypeName, String paymentStatus,
			String paymentStatusName, String paymentProvider,
			String paymentProviderName, String paymentStartTime,
			String paymentEndTime, String operateStartTime,
			String operateEndTime, String operateStatus,
			String operateStatusName, String createStartTime,
			String createEndTime, String originalPrice, String totalPrice,
			String logisticsCompanyId, String logisticsCompanyName,
			String deliveryStaffId, String deliveryStaffName,
			String deliveryStaffMobile, String deliveryStartStartTime,
			String deliveryStartEndTime, String deliveryEndStartTime,
			String deliveryEndEndTime, String deliveryStatus,
			String deliveryStatusName, String sourceId, String sourceName,
			String sourceType, String sourceTypeName, String serNo,
			String sellerId, String sellerName, String cusId, String cusName,
			String primaryConfigurationId, String primaryConfigurationName) {
		String hql = "select count(*) from OrderRecord where 1=1";
		if (!StringHelper.isNull(orderNo)) {
			hql = hql + " and orderNo = '" + orderNo + "'";
		}
		if (!StringHelper.isNull(orderRecordId)) {
			hql = hql + " and id = '" + orderRecordId + "'";
		}
		if (!StringHelper.isNull(clientUserId)) {
			hql = hql + " and clientUserId = '" + clientUserId + "'";
		}
		if (!StringHelper.isNull(clientUserName)) {
			hql = hql + " and clientUserName like '%" + clientUserName + "%'";
		}
		if (!StringHelper.isNull(clientUserMobile)) {
			hql = hql + " and clientUserMobile = '" + clientUserMobile + "'";
		}
		if (!StringHelper.isNull(clientUserEmail)) {
			hql = hql + " and clientUserEmail = '" + clientUserEmail + "'";
		}
		if (!StringHelper.isNull(clientUserSex)) {
			hql = hql + " and clientUserSex = '" + clientUserSex + "'";
		}
		if (!StringHelper.isNull(clientUserType)) {
			hql = hql + " and clientUserType = " + clientUserType;
		}
		if (!StringHelper.isNull(clientUserTypeName)) {
			hql = hql + " and clientUserTypeName like '%" + clientUserTypeName
					+ "%'";
		}
		if (!StringHelper.isNull(wxOpenId)) {
			hql = hql + " and wxOpenId = '" + wxOpenId + "'";
		}
		if (!StringHelper.isNull(staffId)) {
			hql = hql + " and staffId = '" + staffId + "'";
		}
		if (!StringHelper.isNull(userRealName)) {
			hql = hql + " and userRealName like '%" + userRealName + "%'";
		}
		if (!StringHelper.isNull(countryId)) {
			hql = hql + " and countryId = '" + countryId + "'";
		}
		if (!StringHelper.isNull(countryName)) {
			hql = hql + " and countryName like '%" + countryName + "%'";
		}
		if (!StringHelper.isNull(provinceId)) {
			hql = hql + " and provinceId = '" + provinceId + "'";
		}
		if (!StringHelper.isNull(provinceName)) {
			hql = hql + " and provinceName like '%" + provinceName + "%'";
		}
		if (!StringHelper.isNull(cityId)) {
			hql = hql + " and cityId = '" + cityId + "'";
		}
		if (!StringHelper.isNull(cityName)) {
			hql = hql + " and cityName like '%" + cityName + "%'";
		}
		if (!StringHelper.isNull(areaId)) {
			hql = hql + " and areaId = '" + areaId + "'";
		}
		if (!StringHelper.isNull(areaName)) {
			hql = hql + " and areaName like '%" + areaName + "%'";
		}
		if (!StringHelper.isNull(userRealAddress)) {
			hql = hql + " and userRealAddress like '%" + userRealAddress + "%'";
		}
		if (!StringHelper.isNull(userRealMobile)) {
			hql = hql + " and userRealMobile = " + userRealMobile;
		}
		if (!StringHelper.isNull(userRealPlane)) {
			hql = hql + " and userRealPlane = " + userRealPlane;
		}
		if (!StringHelper.isNull(userRealPostcode)) {
			hql = hql + " and userRealPostcode = " + userRealPostcode;
		}
		if (!StringHelper.isNull(primaryConfigurationId)) {
			hql = hql + " and primaryConfigurationId = "
					+ primaryConfigurationId;
		}
		if (!StringHelper.isNull(userRealStartTime)) {
			hql = hql + " and userRealTime >='" + userRealStartTime + "'";
		}
		if (!StringHelper.isNull(userRealEndTime)) {
			hql = hql + " and userRealTime <='" + userRealEndTime + "'";
		}
		if (!StringHelper.isNull(paymentType)) {
			hql = hql + " and paymentType = " + paymentType;
		}
		if (!StringHelper.isNull(paymentTypeName)) {
			hql = hql + " and paymentTypeName like '" + paymentTypeName + "%'";
		}
		if (!StringHelper.isNull(paymentStatus)) {
			hql = hql + " and paymentStatus = " + paymentStatus;
		}
		if (!StringHelper.isNull(paymentStatusName)) {
			hql = hql + " and paymentStatusName like '%" + paymentStatusName
					+ "%'";
		}
		if (!StringHelper.isNull(paymentProvider)) {
			hql = hql + " and paymentProvider = " + paymentProvider;
		}
		if (!StringHelper.isNull(paymentProviderName)) {
			hql = hql + " and paymentProviderName like '%"
					+ paymentProviderName + "%'";
		}
		if (!StringHelper.isNull(paymentStartTime)) {
			hql = hql + " and paymentTime >='" + paymentStartTime + "'";
		}
		if (!StringHelper.isNull(paymentEndTime)) {
			hql = hql + " and paymentTime <='" + paymentEndTime + "'";
		}
		if (!StringHelper.isNull(operateStartTime)) {
			hql = hql + " and operateTime >='" + operateStartTime + "'";
		}
		if (!StringHelper.isNull(operateEndTime)) {
			hql = hql + " and operateTime <='" + operateEndTime + "'";
		}
		if (!StringHelper.isNull(operateStatus) && !operateStatus.equals("0")) {
			hql = hql + " and operateStatus = " + operateStatus;
		}
		if (!StringHelper.isNull(operateStatusName)) {
			hql = hql + " and operateStatusName like '%" + operateStatusName
					+ "%'";
		}
		if (!StringHelper.isNull(createStartTime)) {
			hql = hql + " and createTime >='" + createStartTime + "'";
		}
		if (!StringHelper.isNull(createEndTime)) {
			hql = hql + " and createTime <='" + createEndTime + "'";
		}
		if (!StringHelper.isNull(originalPrice)) {
			hql = hql + " and originalPrice = " + originalPrice;
		}
		if (!StringHelper.isNull(totalPrice)) {
			hql = hql + " and totalPrice = " + totalPrice;
		}
		if (!StringHelper.isNull(logisticsCompanyId)) {
			hql = hql + " and logisticsCompanyId = " + logisticsCompanyId;
		}
		if (!StringHelper.isNull(logisticsCompanyName)) {
			hql = hql + " and logisticsCompanyName like '%"
					+ logisticsCompanyName + "%'";
		}
		if (!StringHelper.isNull(deliveryStaffId)) {
			hql = hql + " and deliveryStaffId = " + deliveryStaffId;
		}
		if (!StringHelper.isNull(deliveryStaffName)) {
			hql = hql + " and deliveryStaffName like '%" + deliveryStaffName
					+ "%'";
		}
		if (!StringHelper.isNull(deliveryStaffMobile)) {
			hql = hql + " and deliveryStaffMobile = " + deliveryStaffMobile;
		}
		if (!StringHelper.isNull(deliveryStartStartTime)) {
			hql = hql + " and deliveryStartTime >='" + deliveryStartStartTime
					+ "'";
		}
		if (!StringHelper.isNull(deliveryStartEndTime)) {
			hql = hql + " and deliveryStartTime <='" + deliveryStartEndTime
					+ "'";
		}
		if (!StringHelper.isNull(deliveryEndStartTime)) {
			hql = hql + " and deliveryEndTime >='" + deliveryEndStartTime + "'";
		}
		if (!StringHelper.isNull(deliveryEndEndTime)) {
			hql = hql + " and deliveryEndTime <='" + deliveryEndEndTime + "'";
		}
		if (!StringHelper.isNull(deliveryStatus)) {
			hql = hql + " and deliveryStatus = " + deliveryStatus;
		}
		if (!StringHelper.isNull(deliveryStatusName)) {
			hql = hql + " and deliveryStatusName like '%" + deliveryStatusName
					+ "%'";
		}
		if (!StringHelper.isNull(sourceId)) {
			hql = hql + " and sourceId = " + sourceId;
		}
		if (!StringHelper.isNull(sourceName)) {
			hql = hql + " and sourceName like '%" + sourceName + "%'";
		}
		if (!StringHelper.isNull(sourceType)) {
			hql = hql + " and sourceType = " + sourceType;
		}
		if (!StringHelper.isNull(sourceTypeName)) {
			hql = hql + " and sourceTypeName like '%" + sourceTypeName + "%'";
		}
		if (!StringHelper.isNull(serNo)) {
			hql = hql + " and serNo = '" + serNo + "'";
		}
		if (!StringHelper.isNull(sellerId)) {
			hql = hql + " and sellerId = " + sellerId;
		}
		if (!StringHelper.isNull(sellerName)) {
			hql = hql + " and sellerName like '%" + sellerName + "%'";
		}
		if (!StringHelper.isNull(cusId)) {
			hql = hql + " and cusId = " + cusId;
		}
		if (!StringHelper.isNull(cusName)) {
			hql = hql + " and cusName like '%" + cusName + "%'";
		}
		return this.commonDao.getNumberOfEntitiesWithHql(hql);
	}

	public List<OrderVO> listVO(OrderRecordVO orderRecordVO, int curPage,
			int pageSize, String orderField, String direction) {

		List<OrderVO> res = listVO(null, null, null, null, null, null, null,
				null, null, null, null, null, null, null, null, null, null,
				null, null, null, null, null, null, null, null, null, null,
				null, null, null, null, null, null, null, null, null, null,
				null, null, null, null, null, null, null, null, null, null,
				null, null, null, null, null, null, null, null, null, null,
				null, null, null, null, null, null, null, curPage, pageSize,
				orderField, direction);
		return res;
	}

	private OrderRecordVO processMutilParameter(OrderRecordVO orderRecordVO)
			throws ParseException {
		DateFormat dft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String orderNo = orderRecordVO == null
				|| orderRecordVO.getOrderRecord() == null ? "" : orderRecordVO
				.getOrderRecord().getOrderNo();
		String orderRecordId = orderRecordVO == null
				|| orderRecordVO.getOrderRecord() == null
				|| orderRecordVO.getOrderRecord().getId() == null ? ""
				: orderRecordVO.getOrderRecord().getId().toString();
		String clientUserId = orderRecordVO == null
				|| orderRecordVO.getOrderRecord() == null
				|| orderRecordVO.getOrderRecord().getClientUserId() == null ? ""
				: orderRecordVO.getOrderRecord().getClientUserId().toString();
		String clientUserName = orderRecordVO == null
				|| orderRecordVO.getOrderRecord() == null ? "" : orderRecordVO
				.getOrderRecord().getClientUserName();
		String clientUserMobile = orderRecordVO == null
				|| orderRecordVO.getOrderRecord() == null ? "" : orderRecordVO
				.getOrderRecord().getClientUserMobile();
		String clientUserEmail = orderRecordVO == null
				|| orderRecordVO.getOrderRecord() == null ? "" : orderRecordVO
				.getOrderRecord().getClientUserEmail();
		String clientUserSex = orderRecordVO == null
				|| orderRecordVO.getOrderRecord() == null ? "" : orderRecordVO
				.getOrderRecord().getClientUserSex();
		String clientUserType = orderRecordVO == null
				|| orderRecordVO.getOrderRecord() == null
				|| orderRecordVO.getOrderRecord().getClientUserType() == null ? ""
				: orderRecordVO.getOrderRecord().getClientUserType().toString();
		String clientUserTypeName = orderRecordVO == null
				|| orderRecordVO.getOrderRecord() == null ? "" : orderRecordVO
				.getOrderRecord().getClientUserTypeName();
		String wxOpenId = orderRecordVO == null
				|| orderRecordVO.getOrderRecord() == null ? "" : orderRecordVO
				.getOrderRecord().getWxOpenId();
		String staffId = orderRecordVO == null
				|| orderRecordVO.getOrderRecord() == null ? "" : orderRecordVO
				.getOrderRecord().getStaffId();
		String userRealMobile = orderRecordVO == null
				|| orderRecordVO.getOrderRecord() == null ? "" : orderRecordVO
				.getOrderRecord().getUserRealMobile();
		String userRealPlane = orderRecordVO == null
				|| orderRecordVO.getOrderRecord() == null ? "" : orderRecordVO
				.getOrderRecord().getUserRealPlane();
		String userRealName = orderRecordVO == null
				|| orderRecordVO.getOrderRecord() == null ? "" : orderRecordVO
				.getOrderRecord().getUserRealName();
		String countryId = orderRecordVO == null
				|| orderRecordVO.getOrderRecord() == null
				|| orderRecordVO.getOrderRecord().getCountryId() == null ? ""
				: orderRecordVO.getOrderRecord().getCountryId().toString();
		String countryName = orderRecordVO == null
				|| orderRecordVO.getOrderRecord() == null ? "" : orderRecordVO
				.getOrderRecord().getCountryName();
		String provinceId = orderRecordVO == null
				|| orderRecordVO.getOrderRecord() == null
				|| orderRecordVO.getOrderRecord().getProvinceId() == null ? ""
				: orderRecordVO.getOrderRecord().getProvinceId().toString();
		String provinceName = orderRecordVO == null
				|| orderRecordVO.getOrderRecord() == null ? "" : orderRecordVO
				.getOrderRecord().getProvinceName();
		String cityId = orderRecordVO == null
				|| orderRecordVO.getOrderRecord() == null
				|| orderRecordVO.getOrderRecord().getCityId() == null ? ""
				: orderRecordVO.getOrderRecord().getCityId().toString();
		String cityName = orderRecordVO == null
				|| orderRecordVO.getOrderRecord() == null ? "" : orderRecordVO
				.getOrderRecord().getCityName();
		String areaId = orderRecordVO == null
				|| orderRecordVO.getOrderRecord() == null
				|| orderRecordVO.getOrderRecord().getAreaId() == null ? ""
				: orderRecordVO.getOrderRecord().getAreaId().toString();
		String areaName = orderRecordVO == null
				|| orderRecordVO.getOrderRecord() == null ? "" : orderRecordVO
				.getOrderRecord().getAreaName();
		String userRealAddress = orderRecordVO == null
				|| orderRecordVO.getOrderRecord() == null ? "" : orderRecordVO
				.getOrderRecord().getUserRealAddress();
		String userRealPostcode = orderRecordVO == null
				|| orderRecordVO.getOrderRecord() == null ? "" : orderRecordVO
				.getOrderRecord().getUserRealPostcode();
		String userRealStartTime = orderRecordVO == null ? "" : orderRecordVO
				.getUserRealStartTime();
		String userRealEndTime = orderRecordVO == null ? "" : orderRecordVO
				.getUserRealEndTime();
		String paymentType = orderRecordVO == null
				|| orderRecordVO.getOrderRecord() == null
				|| orderRecordVO.getOrderRecord().getPaymentType() == null ? ""
				: orderRecordVO.getOrderRecord().getPaymentType().toString();
		String paymentTypeName = orderRecordVO == null
				|| orderRecordVO.getOrderRecord() == null ? "" : orderRecordVO
				.getOrderRecord().getPaymentTypeName();
		String paymentStatus = orderRecordVO == null
				|| orderRecordVO.getOrderRecord() == null
				|| orderRecordVO.getOrderRecord().getPaymentStatus() == null ? ""
				: orderRecordVO.getOrderRecord().getPaymentStatus().toString();
		String paymentStatusName = orderRecordVO == null
				|| orderRecordVO.getOrderRecord() == null ? "" : orderRecordVO
				.getOrderRecord().getPaymentStatusName();
		String paymentProvider = orderRecordVO == null
				|| orderRecordVO.getOrderRecord() == null
				|| orderRecordVO.getOrderRecord().getPaymentProvider() == null ? ""
				: orderRecordVO.getOrderRecord().getPaymentProvider()
						.toString();
		String paymentProviderName = orderRecordVO == null
				|| orderRecordVO.getOrderRecord() == null ? "" : orderRecordVO
				.getOrderRecord().getPaymentProviderName();
		String paymentStartTime = orderRecordVO == null ? "" : orderRecordVO
				.getPaymentStartTime();
		String paymentEndTime = orderRecordVO == null ? "" : orderRecordVO
				.getPaymentEndTime();
		String operateStartTime = orderRecordVO == null
				|| orderRecordVO.getOrderRecord() == null ? "" : orderRecordVO
				.getOperateStartTime();
		String operateEndTime = orderRecordVO == null ? "" : orderRecordVO
				.getOperateEndTime();
		String operateStatus = orderRecordVO == null
				|| orderRecordVO.getOrderRecord() == null
				|| orderRecordVO.getOrderRecord().getOperateStatus() == null ? ""
				: orderRecordVO.getOrderRecord().getOperateStatus().toString();
		String operateStatusName = orderRecordVO == null
				|| orderRecordVO.getOrderRecord() == null ? "" : orderRecordVO
				.getOrderRecord().getOperateStatusName();
		String createTime = orderRecordVO == null ? "" : orderRecordVO
				.getCreateTime();
		String createStartTime = orderRecordVO == null ? "" : orderRecordVO
				.getCreateStartTime();
		String createEndTime = orderRecordVO == null ? "" : orderRecordVO
				.getCreateEndTime();
		String originalPrice = orderRecordVO == null
				|| orderRecordVO.getOrderRecord() == null
				|| orderRecordVO.getOrderRecord().getOriginalPrice() == null ? ""
				: orderRecordVO.getOrderRecord().getOriginalPrice().toString();
		String totalPrice = orderRecordVO == null
				|| orderRecordVO.getOrderRecord() == null
				|| orderRecordVO.getOrderRecord().getTotalPrice() == null ? ""
				: orderRecordVO.getOrderRecord().getTotalPrice().toString();
		String logisticsCompanyId = orderRecordVO == null
				|| orderRecordVO.getOrderRecord() == null
				|| orderRecordVO.getOrderRecord().getLogisticsCompanyId() == null ? ""
				: orderRecordVO.getOrderRecord().getLogisticsCompanyId()
						.toString();
		String logisticsCompanyName = orderRecordVO == null
				|| orderRecordVO.getOrderRecord() == null ? "" : orderRecordVO
				.getOrderRecord().getLogisticsCompanyName();
		String deliveryStaffId = orderRecordVO == null
				|| orderRecordVO.getOrderRecord() == null
				|| orderRecordVO.getOrderRecord().getDeliveryStaffId() == null ? ""
				: orderRecordVO.getOrderRecord().getDeliveryStaffId()
						.toString();
		String deliveryStaffName = orderRecordVO == null
				|| orderRecordVO.getOrderRecord() == null ? "" : orderRecordVO
				.getOrderRecord().getDeliveryStaffName();
		String deliveryStaffMobile = orderRecordVO == null
				|| orderRecordVO.getOrderRecord() == null ? "" : orderRecordVO
				.getOrderRecord().getDeliveryStaffMobile();
		String deliveryStartStartTime = orderRecordVO == null ? ""
				: orderRecordVO.getDeliveryStartStartTime();
		String deliveryStartEndTime = orderRecordVO == null ? ""
				: orderRecordVO.getDeliveryStartEndTime();
		String deliveryEndStartTime = orderRecordVO == null
				|| orderRecordVO.getOrderRecord() == null ? "" : orderRecordVO
				.getDeliveryEndStartTime();
		String deliveryEndEndTime = orderRecordVO == null ? "" : orderRecordVO
				.getDeliveryEndEndTime();
		String deliveryStatus = orderRecordVO == null
				|| orderRecordVO.getOrderRecord() == null
				|| orderRecordVO.getOrderRecord().getDeliveryStatus() == null ? ""
				: orderRecordVO.getOrderRecord().getDeliveryStatus().toString();
		String deliveryStatusName = orderRecordVO == null
				|| orderRecordVO.getOrderRecord() == null ? "" : orderRecordVO
				.getOrderRecord().getDeliveryStatusName();
		String sourceId = orderRecordVO == null
				|| orderRecordVO.getOrderRecord() == null
				|| orderRecordVO.getOrderRecord().getSourceId() == null ? ""
				: orderRecordVO.getOrderRecord().getSourceId().toString();
		String sourceName = orderRecordVO == null
				|| orderRecordVO.getOrderRecord() == null ? "" : orderRecordVO
				.getOrderRecord().getSourceName();
		String sourceType = orderRecordVO == null
				|| orderRecordVO.getOrderRecord() == null
				|| orderRecordVO.getOrderRecord().getSourceId() == null ? ""
				: orderRecordVO.getOrderRecord().getSourceType().toString();
		String sourceTypeName = orderRecordVO == null
				|| orderRecordVO.getOrderRecord() == null ? "" : orderRecordVO
				.getOrderRecord().getSourceTypeName();
		String serNo = orderRecordVO == null
				|| orderRecordVO.getOrderRecord() == null ? "" : orderRecordVO
				.getOrderRecord().getSerNo();
		String sellerId = orderRecordVO == null
				|| orderRecordVO.getOrderRecord() == null
				|| orderRecordVO.getOrderRecord().getSellerId() == null ? ""
				: orderRecordVO.getOrderRecord().getSellerId().toString();
		String sellerName = orderRecordVO == null
				|| orderRecordVO.getOrderRecord() == null ? "" : orderRecordVO
				.getOrderRecord().getSellerName();
		String cusId = orderRecordVO == null
				|| orderRecordVO.getOrderRecord() == null
				|| orderRecordVO.getOrderRecord().getCusId() == null ? ""
				: orderRecordVO.getOrderRecord().getCusId().toString();
		String cusName = orderRecordVO == null
				|| orderRecordVO.getOrderRecord() == null ? "" : orderRecordVO
				.getOrderRecord().getCusName();
		String primaryConfigurationId = orderRecordVO == null
				|| orderRecordVO.getOrderRecord() == null
				|| orderRecordVO.getOrderRecord().getPrimaryConfigurationId() == null ? ""
				: orderRecordVO.getOrderRecord().getPrimaryConfigurationId()
						.toString();
		String primaryConfigurationName = orderRecordVO == null
				|| orderRecordVO.getOrderRecord() == null ? "" : orderRecordVO
				.getOrderRecord().getPrimaryConfigurationName();

		OrderRecordVO res = new OrderRecordVO();

		OrderRecord or = new OrderRecord();
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

		OrderGoodsRelation ogr = new OrderGoodsRelation();

		res.setOrderRecord(or);
		res.setClientUserInfo(csi);
		res.setOrderGoodsRelation(ogr);
		return res;
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
	public List<OrderVO> listVO(String orderNo, String orderRecordId,
			String clientUserId, String clientUserName,
			String clientUserMobile, String clientUserEmail,
			String clientUserSex, String clientUserType,
			String clientUserTypeName, String wxOpenId, String staffId,
			String userRealMobile, String userRealPlane, String userRealName,
			String countryId, String countryName, String provinceId,
			String provinceName, String cityId, String cityName, String areaId,
			String areaName, String userRealAddress, String userRealPostcode,
			String userRealStartTime, String userRealEndTime,
			String paymentType, String paymentTypeName, String paymentStatus,
			String paymentStatusName, String paymentProvider,
			String paymentProviderName, String paymentStartTime,
			String paymentEndTime, String operateStartTime,
			String operateEndTime, String operateStatus,
			String operateStatusName, String createStartTime,
			String createEndTime, String originalPrice, String totalPrice,
			String logisticsCompanyId, String logisticsCompanyName,
			String deliveryStaffId, String deliveryStaffName,
			String deliveryStaffMobile, String deliveryStartStartTime,
			String deliveryStartEndTime, String deliveryEndStartTime,
			String deliveryEndEndTime, String deliveryStatus,
			String deliveryStatusName, String sourceId, String sourceName,
			String sourceType, String sourceTypeName, String serNo,
			String sellerId, String sellerName, String cusId, String cusName,
			String primaryConfigurationId, String primaryConfigurationName,
			int curPage, int pageSize, String orderField, String direction) {
		String hql = "from OrderRecord where 1=1";
		if (!StringHelper.isNull(orderNo)) {
			hql = hql + " and orderNo = '" + orderNo + "'";
		}
		if (!StringHelper.isNull(orderRecordId)) {
			hql = hql + " and id = '" + orderRecordId + "'";
		}
		if (!StringHelper.isNull(clientUserId)) {
			hql = hql + " and clientUserId = '" + clientUserId + "'";
		}
		if (!StringHelper.isNull(clientUserName)) {
			hql = hql + " and clientUserName like '%" + clientUserName + "%'";
		}
		if (!StringHelper.isNull(clientUserMobile)) {
			hql = hql + " and clientUserMobile = '" + clientUserMobile + "'";
		}
		if (!StringHelper.isNull(clientUserEmail)) {
			hql = hql + " and clientUserEmail = '" + clientUserEmail + "'";
		}
		if (!StringHelper.isNull(clientUserSex)) {
			hql = hql + " and clientUserSex = '" + clientUserSex + "'";
		}
		if (!StringHelper.isNull(clientUserType)) {
			hql = hql + " and clientUserType = " + clientUserType;
		}
		if (!StringHelper.isNull(clientUserTypeName)) {
			hql = hql + " and clientUserTypeName like '%" + clientUserTypeName
					+ "%'";
		}
		if (!StringHelper.isNull(wxOpenId)) {
			hql = hql + " and wxOpenId = '" + wxOpenId + "'";
		}
		if (!StringHelper.isNull(staffId)) {
			hql = hql + " and staffId = '" + staffId + "'";
		}
		if (!StringHelper.isNull(userRealName)) {
			hql = hql + " and userRealName like '%" + userRealName + "%'";
		}
		if (!StringHelper.isNull(countryId)) {
			hql = hql + " and countryId = '" + countryId + "'";
		}
		if (!StringHelper.isNull(countryName)) {
			hql = hql + " and countryName like '%" + countryName + "%'";
		}
		if (!StringHelper.isNull(provinceId)) {
			hql = hql + " and provinceId = '" + provinceId + "'";
		}
		if (!StringHelper.isNull(provinceName)) {
			hql = hql + " and provinceName like '%" + provinceName + "%'";
		}
		if (!StringHelper.isNull(cityId)) {
			hql = hql + " and cityId = '" + cityId + "'";
		}
		if (!StringHelper.isNull(cityName)) {
			hql = hql + " and cityName like '%" + cityName + "%'";
		}
		if (!StringHelper.isNull(areaId)) {
			hql = hql + " and areaId = '" + areaId + "'";
		}
		if (!StringHelper.isNull(areaName)) {
			hql = hql + " and areaName like '%" + areaName + "%'";
		}
		if (!StringHelper.isNull(userRealAddress)) {
			hql = hql + " and userRealAddress like '%" + userRealAddress + "%'";
		}
		if (!StringHelper.isNull(userRealMobile)) {
			hql = hql + " and userRealMobile = " + userRealMobile;
		}
		if (!StringHelper.isNull(userRealPlane)) {
			hql = hql + " and userRealPlane = " + userRealPlane;
		}
		if (!StringHelper.isNull(userRealPostcode)) {
			hql = hql + " and userRealPostcode = " + userRealPostcode;
		}
		if (!StringHelper.isNull(primaryConfigurationId)) {
			hql = hql + " and primaryConfigurationId = "
					+ primaryConfigurationId;
		}
		if (!StringHelper.isNull(userRealStartTime)) {
			hql = hql + " and userRealTime >='" + userRealStartTime + "'";
		}
		if (!StringHelper.isNull(userRealEndTime)) {
			hql = hql + " and userRealTime <='" + userRealEndTime + "'";
		}
		if (!StringHelper.isNull(paymentType)) {
			hql = hql + " and paymentType = " + paymentType;
		}
		if (!StringHelper.isNull(paymentTypeName)) {
			hql = hql + " and paymentTypeName like '" + paymentTypeName + "%'";
		}
		if (!StringHelper.isNull(paymentStatus)) {
			hql = hql + " and paymentStatus = " + paymentStatus;
		}
		if (!StringHelper.isNull(paymentStatusName)) {
			hql = hql + " and paymentStatusName like '%" + paymentStatusName
					+ "%'";
		}
		if (!StringHelper.isNull(paymentProvider)) {
			hql = hql + " and paymentProvider = " + paymentProvider;
		}
		if (!StringHelper.isNull(paymentProviderName)) {
			hql = hql + " and paymentProviderName like '%"
					+ paymentProviderName + "%'";
		}
		if (!StringHelper.isNull(paymentStartTime)) {
			hql = hql + " and paymentTime >='" + paymentStartTime + "'";
		}
		if (!StringHelper.isNull(paymentEndTime)) {
			hql = hql + " and paymentTime <='" + paymentEndTime + "'";
		}
		if (!StringHelper.isNull(operateStartTime)) {
			hql = hql + " and operateTime >='" + operateStartTime + "'";
		}
		if (!StringHelper.isNull(operateEndTime)) {
			hql = hql + " and operateTime <='" + operateEndTime + "'";
		}
		if (!StringHelper.isNull(operateStatus) && !operateStatus.equals("0")) {
			hql = hql + " and operateStatus = " + operateStatus;
		}
		if (!StringHelper.isNull(operateStatusName)) {
			hql = hql + " and operateStatusName like '%" + operateStatusName
					+ "%'";
		}
		if (!StringHelper.isNull(createStartTime)) {
			hql = hql + " and createTime >='" + createStartTime + "'";
		}
		if (!StringHelper.isNull(createEndTime)) {
			hql = hql + " and createTime <='" + createEndTime + "'";
		}
		if (!StringHelper.isNull(originalPrice)) {
			hql = hql + " and originalPrice = " + originalPrice;
		}
		if (!StringHelper.isNull(totalPrice)) {
			hql = hql + " and totalPrice = " + totalPrice;
		}
		if (!StringHelper.isNull(logisticsCompanyId)) {
			hql = hql + " and logisticsCompanyId = " + logisticsCompanyId;
		}
		if (!StringHelper.isNull(logisticsCompanyName)) {
			hql = hql + " and logisticsCompanyName like '%"
					+ logisticsCompanyName + "%'";
		}
		if (!StringHelper.isNull(deliveryStaffId)) {
			hql = hql + " and deliveryStaffId = " + deliveryStaffId;
		}
		if (!StringHelper.isNull(deliveryStaffName)) {
			hql = hql + " and deliveryStaffName like '%" + deliveryStaffName
					+ "%'";
		}
		if (!StringHelper.isNull(deliveryStaffMobile)) {
			hql = hql + " and deliveryStaffMobile = " + deliveryStaffMobile;
		}
		if (!StringHelper.isNull(deliveryStartStartTime)) {
			hql = hql + " and deliveryStartTime >='" + deliveryStartStartTime
					+ "'";
		}
		if (!StringHelper.isNull(deliveryStartEndTime)) {
			hql = hql + " and deliveryStartTime <='" + deliveryStartEndTime
					+ "'";
		}
		if (!StringHelper.isNull(deliveryEndStartTime)) {
			hql = hql + " and deliveryEndTime >='" + deliveryEndStartTime + "'";
		}
		if (!StringHelper.isNull(deliveryEndEndTime)) {
			hql = hql + " and deliveryEndTime <='" + deliveryEndEndTime + "'";
		}
		if (!StringHelper.isNull(deliveryStatus)) {
			hql = hql + " and deliveryStatus = " + deliveryStatus;
		}
		if (!StringHelper.isNull(deliveryStatusName)) {
			hql = hql + " and deliveryStatusName like '%" + deliveryStatusName
					+ "%'";
		}
		if (!StringHelper.isNull(sourceId)) {
			hql = hql + " and sourceId = " + sourceId;
		}
		if (!StringHelper.isNull(sourceName)) {
			hql = hql + " and sourceName like '%" + sourceName + "%'";
		}
		if (!StringHelper.isNull(sourceType)) {
			hql = hql + " and sourceType = " + sourceType;
		}
		if (!StringHelper.isNull(sourceTypeName)) {
			hql = hql + " and sourceTypeName like '%" + sourceTypeName + "%'";
		}
		if (!StringHelper.isNull(serNo)) {
			hql = hql + " and serNo = '" + serNo + "'";
		}
		if (!StringHelper.isNull(sellerId)) {
			hql = hql + " and sellerId = " + sellerId;
		}
		if (!StringHelper.isNull(sellerName)) {
			hql = hql + " and sellerName like '%" + sellerName + "%'";
		}
		if (!StringHelper.isNull(cusId)) {
			hql = hql + " and cusId = " + cusId;
		}
		if (!StringHelper.isNull(cusName)) {
			hql = hql + " and cusName like '%" + cusName + "%'";
		}
		if (!StringHelper.isNull(orderField) && !StringHelper.isNull(direction)) {
			hql = hql + " order by " + orderField + " " + direction;
		} else {
			hql += " order by id desc";
		}
		List l = commonDao.getEntitiesByPropertiesWithHql(hql, curPage,
				pageSize);
		List<OrderVO> resList = new ArrayList<OrderVO>();
		for (int i = 0; i < l.size(); i++) {
			OrderRecord po = (OrderRecord) l.get(i);
			OrderVO vo = new OrderVO();
			if (po.getId() != null) {
				vo.setOrderRecordId(po.getId().toString());
			}
			if (po.getClientUserId() != null) {
				vo.setOrderRecordClientUserId(po.getClientUserId().toString());
			}
			vo.setOrderRecordClientUserMobile(po.getClientUserMobile());
			vo.setOrderRecordClientUserEmail(po.getClientUserEmail());
			vo.setOrderRecordClientUserName(po.getClientUserName());
			vo.setOrderRecordClientUserSex(po.getClientUserSex());
			vo.setOrderRecordWxOpenId(po.getWxOpenId());
			vo.setOrderRecordStaffId(po.getStaffId());
			vo.setOrderRecordUserRealMobile(po.getUserRealMobile());
			vo.setOrderRecordUserRealPlane(po.getUserRealPlane());
			vo.setOrderRecordUserRealName(po.getUserRealName());
			vo.setOrderNo(po.getOrderNo());
			vo.setOrderRecordUserRealDescription(po.getUserRealDescription());
			if (po.getCountryId() != null) {
				vo.setOrderRecordCountryId(po.getCountryId().toString());
			}
			vo.setOrderRecordCountryName(po.getCountryName());
			if (po.getProvinceId() != null) {
				vo.setOrderRecordProvinceId(po.getProvinceId().toString());
			}
			vo.setOrderRecordProvinceName(po.getProvinceName());
			if (po.getCityId() != null) {
				vo.setOrderRecordCityId(po.getCityId().toString());
			}
			vo.setOrderRecordCityName(po.getCityName());
			if (po.getAreaId() != null) {
				vo.setOrderRecordAreaId(po.getAreaId().toString());
			}
			vo.setOrderRecordAreaName(po.getAreaName());
			vo.setOrderRecordUserRealAddress(po.getUserRealAddress());
			vo.setOrderRecordUserRealPostcode(po.getUserRealPostcode());
			if (po.getUserRealTime() != null) {
				vo.setOrderRecordUserRealTime(po.getUserRealTime().toString());
			}

			if (po.getPaymentType() != null) {
				vo.setOrderRecordPaymentType(po.getPaymentType().toString());
			}
			vo.setOrderRecordPaymentTypeName(po.getPaymentTypeName());
			if (po.getPaymentStatus() != null) {
				vo.setOrderRecordPaymentStatus(po.getPaymentStatus().toString());
			}
			vo.setOrderRecordPaymentStatusName(po.getPaymentStatusName());
			if (po.getPaymentProvider() != null) {
				vo.setOrderRecordPaymentProvider(po.getPaymentProvider()
						.toString());
			}
			vo.setOrderRecordPaymentProviderName(po.getPaymentProviderName());
			if (po.getPaymentTime() != null) {
				vo.setOrderRecordPaymentTime(StringHelper.getSystime(
						ViewConstant.PAGE_DATE_FORMAT_SEC, po.getPaymentTime()
								.getTime()));
			}
			if (po.getOperateTime() != null) {
				vo.setOrderRecordOperateTime(StringHelper.getSystime(
						ViewConstant.PAGE_DATE_FORMAT_SEC, po.getOperateTime()
								.getTime()));
			}
			if (po.getOperateStatus() != null) {
				vo.setOrderRecordOperateStatus(po.getOperateStatus().toString());
			}
			vo.setOrderRecordOperateStatusName(po.getOperateStatusName());
			if (po.getCreateTime() != null) {
				vo.setOrderRecordCreateTime(StringHelper.getSystime(
						ViewConstant.PAGE_DATE_FORMAT_SEC, po.getCreateTime()
								.getTime()));
			}
			if (po.getOriginalPrice() != null) {
				vo.setOrderRecordOriginalPrice(po.getOriginalPrice().toString());
			}
			if (po.getTotalPrice() != null) {
				vo.setOrderRecordTotalPrice(po.getTotalPrice().toString());
			}
			if (po.getLogisticsCompanyId() != null) {
				vo.setOrderRecordLogisticsCompanyId(po.getLogisticsCompanyId()
						.toString());
			}
			if (po.getLogisticsCompanyName() != null) {
				vo.setOrderRecordLogisticsCompanyName(po
						.getLogisticsCompanyName());
			}
			if (po.getDeliveryStaffId() != null) {
				vo.setOrderRecordDliveryStaffId(po.getDeliveryStaffId()
						.toString());
			}
			vo.setOrderRecordDeliveryStaffName(po.getDeliveryStaffName());
			vo.setOrderRecordDeliveryStaffMobile(po.getDeliveryStaffMobile());
			if (po.getDeliveryStartTime() != null) {
				vo.setOrderRecordDeliveryStartTime(StringHelper.getSystime(
						ViewConstant.PAGE_DATE_FORMAT_SEC, po
								.getDeliveryStartTime().getTime()));
			}
			if (po.getDeliveryEndTime() != null) {
				vo.setOrderRecordDeliveryEndTime(StringHelper.getSystime(
						ViewConstant.PAGE_DATE_FORMAT_SEC, po
								.getDeliveryEndTime().getTime()));
			}
			if (po.getDeliveryStatus() != null) {
				vo.setOrderRecordDeliveryStatus(po.getDeliveryStatus()
						.toString());
			}
			vo.setOrderRecordDeliveryStatusName(po.getDeliveryStatusName());
			if (po.getSourceId() != null) {
				vo.setOrderRecordSourceId(po.getSourceId().toString());
			}
			vo.setOrderRecordSourceName(po.getSourceName());
			if (po.getSourceType() != null) {
				vo.setOrderRecordSourceType(po.getSourceType().toString());
			}
			vo.setOrderRecordSourceTypeName(po.getSourceTypeName());
			vo.setOrderRecordSerNo(po.getSerNo());
			if (po.getSellerId() != null) {
				vo.setOrderRecordSellerId(po.getSellerId().toString());
			}
			vo.setOrderRecordSellerName(po.getSellerName());
			if (po.getCusId() != null) {
				vo.setOrderRecordCusId(po.getCusId().toString());
			}
			vo.setOrderRecordCusName(po.getCusName());
			if (po.getPrimaryConfigurationId() != null) {
				vo.setOrderRecordPrimaryConfigurationId(po
						.getPrimaryConfigurationId().toString());
			}
			vo.setOrderRecordPrimaryConfigurationName(po
					.getPrimaryConfigurationName());
			resList.add(vo);
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
	public List<OrderVO> webList(String orderNo, String orderRecordId,
			String clientUserId, String clientUserName,
			String clientUserMobile, String clientUserEmail,
			String clientUserSex, String clientUserType,
			String clientUserTypeName, String wxOpenId, String staffId,
			String userRealMobile, String userRealPlane, String userRealName,
			String countryId, String countryName, String provinceId,
			String provinceName, String cityId, String cityName, String areaId,
			String areaName, String userRealAddress, String userRealPostcode,
			String userRealStartTime, String userRealEndTime,
			String paymentType, String paymentTypeName, String paymentStatus,
			String paymentStatusName, String paymentProvider,
			String paymentProviderName, String paymentStartTime,
			String paymentEndTime, String operateStartTime,
			String operateEndTime, String operateStatus,
			String operateStatusName, String createStartTime,
			String createEndTime, String originalPrice, String totalPrice,
			String logisticsCompanyId, String logisticsCompanyName,
			String deliveryStaffId, String deliveryStaffName,
			String deliveryStaffMobile, String deliveryStartStartTime,
			String deliveryStartEndTime, String deliveryEndStartTime,
			String deliveryEndEndTime, String deliveryStatus,
			String deliveryStatusName, String sourceId, String sourceName,
			String sourceType, String sourceTypeName, String serNo,
			String sellerId, String sellerName, String cusId, String cusName,
			String primaryConfigurationId, String primaryConfigurationName,
			int curPage, int pageSize, String orderField, String direction) {
		String hql = "from OrderRecord a, Goods b,OrderGoodsRelation c where c.orderRecordId=a.id"
				+ " and c.goodsId=b.id";
		if (!StringHelper.isNull(orderNo)) {
			hql = hql + " and a.orderNo = '" + orderNo + "'";
		}
		if (!StringHelper.isNull(orderRecordId)) {
			hql = hql + " and a.id = '" + orderRecordId + "'";
		}
		if (!StringHelper.isNull(clientUserId)) {
			hql = hql + " and a.clientUserId = '" + clientUserId + "'";
		}
		if (!StringHelper.isNull(clientUserName)) {
			hql = hql + " and a.clientUserName like '%" + clientUserName + "%'";
		}
		if (!StringHelper.isNull(clientUserMobile)) {
			hql = hql + " and a.clientUserMobile = '" + clientUserMobile + "'";
		}
		if (!StringHelper.isNull(clientUserEmail)) {
			hql = hql + " and a.clientUserEmail = '" + clientUserEmail + "'";
		}
		if (!StringHelper.isNull(clientUserSex)) {
			hql = hql + " and a.clientUserSex = '" + clientUserSex + "'";
		}
		if (!StringHelper.isNull(clientUserType)) {
			hql = hql + " and a.clientUserType = " + clientUserType;
		}
		if (!StringHelper.isNull(clientUserTypeName)) {
			hql = hql + " and a.clientUserTypeName like '%"
					+ clientUserTypeName + "%'";
		}
		if (!StringHelper.isNull(wxOpenId)) {
			hql = hql + " and a.wxOpenId = '" + wxOpenId + "'";
		}
		if (!StringHelper.isNull(staffId)) {
			hql = hql + " and a.staffId = '" + staffId + "'";
		}
		if (!StringHelper.isNull(userRealName)) {
			hql = hql + " and a.userRealName like '%" + userRealName + "%'";
		}
		if (!StringHelper.isNull(countryId)) {
			hql = hql + " and a.countryId = '" + countryId + "'";
		}
		if (!StringHelper.isNull(countryName)) {
			hql = hql + " and a.countryName like '%" + countryName + "%'";
		}
		if (!StringHelper.isNull(provinceId)) {
			hql = hql + " and a.provinceId = '" + provinceId + "'";
		}
		if (!StringHelper.isNull(provinceName)) {
			hql = hql + " and a.provinceName like '%" + provinceName + "%'";
		}
		if (!StringHelper.isNull(cityId)) {
			hql = hql + " and a.cityId = '" + cityId + "'";
		}
		if (!StringHelper.isNull(cityName)) {
			hql = hql + " and a.cityName like '%" + cityName + "%'";
		}
		if (!StringHelper.isNull(areaId)) {
			hql = hql + " and a.areaId = '" + areaId + "'";
		}
		if (!StringHelper.isNull(areaName)) {
			hql = hql + " and a.areaName like '%" + areaName + "%'";
		}
		if (!StringHelper.isNull(userRealAddress)) {
			hql = hql + " and a.userRealAddress like '%" + userRealAddress
					+ "%'";
		}
		if (!StringHelper.isNull(userRealMobile)) {
			hql = hql + " and a.userRealMobile = " + userRealMobile;
		}
		if (!StringHelper.isNull(userRealPlane)) {
			hql = hql + " and a.userRealPlane = " + userRealPlane;
		}
		if (!StringHelper.isNull(userRealPostcode)) {
			hql = hql + " and a.userRealPostcode = " + userRealPostcode;
		}
		if (!StringHelper.isNull(primaryConfigurationId)) {
			hql = hql + " and a.primaryConfigurationId = "
					+ primaryConfigurationId;
		}
		if (!StringHelper.isNull(userRealStartTime)) {
			hql = hql + " and a.userRealTime >='" + userRealStartTime + "'";
		}
		if (!StringHelper.isNull(userRealEndTime)) {
			hql = hql + " and a.userRealTime <='" + userRealEndTime + "'";
		}
		if (!StringHelper.isNull(paymentType)) {
			hql = hql + " and a.paymentType = " + paymentType;
		}
		if (!StringHelper.isNull(paymentTypeName)) {
			hql = hql + " and a.paymentTypeName like '" + paymentTypeName
					+ "%'";
		}
		if (!StringHelper.isNull(paymentStatus)) {
			hql = hql + " and a.paymentStatus = " + paymentStatus;
		}
		if (!StringHelper.isNull(paymentStatusName)) {
			hql = hql + " and a.paymentStatusName like '%" + paymentStatusName
					+ "%'";
		}
		if (!StringHelper.isNull(paymentProvider)) {
			hql = hql + " and a.paymentProvider = " + paymentProvider;
		}
		if (!StringHelper.isNull(paymentProviderName)) {
			hql = hql + " and a.paymentProviderName like '%"
					+ paymentProviderName + "%'";
		}
		if (!StringHelper.isNull(paymentStartTime)) {
			hql = hql + " and a.paymentTime >='" + paymentStartTime + "'";
		}
		if (!StringHelper.isNull(paymentEndTime)) {
			hql = hql + " and a.paymentTime <='" + paymentEndTime + "'";
		}
		if (!StringHelper.isNull(operateStartTime)) {
			hql = hql + " and a.operateTime >='" + operateStartTime + "'";
		}
		if (!StringHelper.isNull(operateEndTime)) {
			hql = hql + " and a.operateTime <='" + operateEndTime + "'";
		}
		if (!StringHelper.isNull(operateStatus)) {
			hql = hql + " and a.operateStatus = " + operateStatus;
		}
		if (!StringHelper.isNull(operateStatusName)) {
			hql = hql + " and a.operateStatusName like '%" + operateStatusName
					+ "%'";
		}
		if (!StringHelper.isNull(createStartTime)) {
			hql = hql + " and a.createTime >='" + createStartTime + "'";
		}
		if (!StringHelper.isNull(createEndTime)) {
			hql = hql + " and a.createTime <='" + createEndTime + "'";
		}
		if (!StringHelper.isNull(originalPrice)) {
			hql = hql + " and a.originalPrice = " + originalPrice;
		}
		if (!StringHelper.isNull(totalPrice)) {
			hql = hql + " and a.totalPrice = " + totalPrice;
		}
		if (!StringHelper.isNull(logisticsCompanyId)) {
			hql = hql + " and a.logisticsCompanyId = " + logisticsCompanyId;
		}
		if (!StringHelper.isNull(logisticsCompanyName)) {
			hql = hql + " and a.logisticsCompanyName like '%"
					+ logisticsCompanyName + "%'";
		}
		if (!StringHelper.isNull(deliveryStaffId)) {
			hql = hql + " and a.deliveryStaffId = " + deliveryStaffId;
		}
		if (!StringHelper.isNull(deliveryStaffName)) {
			hql = hql + " and a.deliveryStaffName like '%" + deliveryStaffName
					+ "%'";
		}
		if (!StringHelper.isNull(deliveryStaffMobile)) {
			hql = hql + " and a.deliveryStaffMobile = " + deliveryStaffMobile;
		}
		if (!StringHelper.isNull(deliveryStartStartTime)) {
			hql = hql + " and a.deliveryStartTime >='" + deliveryStartStartTime
					+ "'";
		}
		if (!StringHelper.isNull(deliveryStartEndTime)) {
			hql = hql + " and a.deliveryStartTime <='" + deliveryStartEndTime
					+ "'";
		}
		if (!StringHelper.isNull(deliveryEndStartTime)) {
			hql = hql + " and a.deliveryEndTime >='" + deliveryEndStartTime
					+ "'";
		}
		if (!StringHelper.isNull(deliveryEndEndTime)) {
			hql = hql + " and a.deliveryEndTime <='" + deliveryEndEndTime + "'";
		}
		if (!StringHelper.isNull(deliveryStatus)) {
			hql = hql + " and a.deliveryStatus = " + deliveryStatus;
		}
		if (!StringHelper.isNull(deliveryStatusName)) {
			hql = hql + " and a.deliveryStatusName like '%"
					+ deliveryStatusName + "%'";
		}
		if (!StringHelper.isNull(sourceId)) {
			hql = hql + " and a.sourceId = " + sourceId;
		}
		if (!StringHelper.isNull(sourceName)) {
			hql = hql + " and a.sourceName like '%" + sourceName + "%'";
		}
		if (!StringHelper.isNull(sourceType)) {
			hql = hql + " and a.sourceType = " + sourceType;
		}
		if (!StringHelper.isNull(sourceTypeName)) {
			hql = hql + " and a.sourceTypeName like '%" + sourceTypeName + "%'";
		}
		if (!StringHelper.isNull(serNo)) {
			hql = hql + " and a.serNo = '" + serNo + "'";
		}
		if (!StringHelper.isNull(sellerId)) {
			hql = hql + " and a.sellerId = " + sellerId;
		}
		if (!StringHelper.isNull(sellerName)) {
			hql = hql + " and a.sellerName like '%" + sellerName + "%'";
		}
		if (!StringHelper.isNull(cusId)) {
			hql = hql + " and a.cusId = " + cusId;
		}
		if (!StringHelper.isNull(cusName)) {
			hql = hql + " and a.cusName like '%" + cusName + "%'";
		}
		if (!StringHelper.isNull(orderField) && !StringHelper.isNull(direction)) {
			hql = hql + " order by " + orderField + " " + direction;
		} else {
			hql += " order by a.id desc,c.orderRecordId desc";
		}
		List l = commonDao.getEntitiesByPropertiesWithHql(hql, curPage,
				pageSize);
		List<OrderVO> resList = new LinkedList<OrderVO>();
		String tempOrderRecordId = "0";
		OrderVO vo = new OrderVO();
		List<Goods> tempGoodsList = new LinkedList<Goods>();
		List<OrderGoodsRelation> tempOrderGoodsRelationList = new LinkedList<OrderGoodsRelation>();
		for (int i = 0; i < l.size(); i++) {
			Object[] obj = (Object[]) l.get(i);
			OrderRecord po = (OrderRecord) obj[0];
			Goods goods = (Goods) obj[1];
			OrderGoodsRelation orderGoodsRelation = (OrderGoodsRelation) obj[2];
			// 判定是不是同一个订单的商品，如果是就汇总到订单中
			if (!tempOrderRecordId.equals(po.getId().toString())) {
				if (!tempOrderRecordId.equals("0")) {
					vo.setGoodsList(tempGoodsList);
					vo.setOrderGoodsRelationList(tempOrderGoodsRelationList);
					resList.add(vo);
				}
				tempOrderRecordId = po.getId().toString();
				vo = new OrderVO(); // 初始化新订单
				tempGoodsList = new ArrayList<Goods>(); // 初始化订单所有商品列表
				tempOrderGoodsRelationList = new LinkedList<OrderGoodsRelation>();// 初始化订单所有商品中间表列表
				// orderRecord
				if (po.getId() != null) {
					vo.setOrderRecordId(po.getId().toString());
				}
				if (po.getClientUserId() != null) {
					vo.setOrderRecordClientUserId(po.getClientUserId()
							.toString());
				}
				vo.setOrderRecordClientUserMobile(po.getClientUserMobile());
				vo.setOrderRecordClientUserEmail(po.getClientUserEmail());
				vo.setOrderRecordClientUserName(po.getClientUserName());
				vo.setOrderRecordClientUserSex(po.getClientUserSex());
				vo.setOrderRecordWxOpenId(po.getWxOpenId());
				vo.setOrderRecordStaffId(po.getStaffId());
				vo.setOrderRecordUserRealMobile(po.getUserRealMobile());
				vo.setOrderRecordUserRealPlane(po.getUserRealPlane());
				vo.setOrderRecordUserRealName(po.getUserRealName());
				vo.setOrderRecordUserRealDescription(po
						.getUserRealDescription());
				vo.setOrderNo(po.getOrderNo());
				if (po.getCountryId() != null) {
					vo.setOrderRecordCountryId(po.getCountryId().toString());
				}
				vo.setOrderRecordCountryName(po.getCountryName());
				if (po.getProvinceId() != null) {
					vo.setOrderRecordProvinceId(po.getProvinceId().toString());
				}
				vo.setOrderRecordProvinceName(po.getProvinceName());
				if (po.getCityId() != null) {
					vo.setOrderRecordCityId(po.getCityId().toString());
				}
				vo.setOrderRecordCityName(po.getCityName());
				if (po.getAreaId() != null) {
					vo.setOrderRecordAreaId(po.getAreaId().toString());
				}
				vo.setOrderRecordAreaName(po.getAreaName());
				vo.setOrderRecordUserRealAddress(po.getUserRealAddress());
				vo.setOrderRecordUserRealPostcode(po.getUserRealPostcode());
				if (po.getUserRealTime() != null) {
					vo.setOrderRecordUserRealTime(po.getUserRealTime()
							.toString());
				}

				if (po.getPaymentType() != null) {
					vo.setOrderRecordPaymentType(po.getPaymentType().toString());
				}
				vo.setOrderRecordPaymentTypeName(po.getPaymentTypeName());
				if (po.getPaymentStatus() != null) {
					vo.setOrderRecordPaymentStatus(po.getPaymentStatus()
							.toString());
				}
				vo.setOrderRecordPaymentStatusName(po.getPaymentStatusName());
				if (po.getPaymentProvider() != null) {
					vo.setOrderRecordPaymentProvider(po.getPaymentProvider()
							.toString());
				}
				vo.setOrderRecordPaymentProviderName(po
						.getPaymentProviderName());
				if (po.getPaymentTime() != null) {
					vo.setOrderRecordPaymentTime(StringHelper.getSystime(
							ViewConstant.PAGE_DATE_FORMAT_SEC, po
									.getPaymentTime().getTime()));
				}
				if (po.getOperateTime() != null) {
					vo.setOrderRecordOperateTime(StringHelper.getSystime(
							ViewConstant.PAGE_DATE_FORMAT_SEC, po
									.getOperateTime().getTime()));
				}
				if (po.getOperateStatus() != null) {
					vo.setOrderRecordOperateStatus(po.getOperateStatus()
							.toString());
				}
				vo.setOrderRecordOperateStatusName(po.getOperateStatusName());
				if (po.getCreateTime() != null) {
					vo.setOrderRecordCreateTime(StringHelper.getSystime(
							ViewConstant.PAGE_DATE_FORMAT_SEC, po
									.getCreateTime().getTime()));
				}
				if (po.getOriginalPrice() != null) {
					vo.setOrderRecordOriginalPrice(po.getOriginalPrice()
							.toString());
				}
				if (po.getTotalPrice() != null) {
					vo.setOrderRecordTotalPrice(po.getTotalPrice().toString());
				}
				if (po.getLogisticsCompanyId() != null) {
					vo.setOrderRecordLogisticsCompanyId(po
							.getLogisticsCompanyId().toString());
				}
				if (po.getLogisticsCompanyName() != null) {
					vo.setOrderRecordLogisticsCompanyName(po
							.getLogisticsCompanyName());
				}
				if (po.getDeliveryStaffId() != null) {
					vo.setOrderRecordDliveryStaffId(po.getDeliveryStaffId()
							.toString());
				}
				vo.setOrderRecordDeliveryStaffName(po.getDeliveryStaffName());
				vo.setOrderRecordDeliveryStaffMobile(po
						.getDeliveryStaffMobile());
				if (po.getDeliveryStartTime() != null) {
					vo.setOrderRecordDeliveryStartTime(StringHelper.getSystime(
							ViewConstant.PAGE_DATE_FORMAT_SEC, po
									.getDeliveryStartTime().getTime()));
				}
				if (po.getDeliveryEndTime() != null) {
					vo.setOrderRecordDeliveryEndTime(StringHelper.getSystime(
							ViewConstant.PAGE_DATE_FORMAT_SEC, po
									.getDeliveryEndTime().getTime()));
				}
				if (po.getDeliveryStatus() != null) {
					vo.setOrderRecordDeliveryStatus(po.getDeliveryStatus()
							.toString());
				}
				vo.setOrderRecordDeliveryStatusName(po.getDeliveryStatusName());
				if (po.getSourceId() != null) {
					vo.setOrderRecordSourceId(po.getSourceId().toString());
				}
				vo.setOrderRecordSourceName(po.getSourceName());
				if (po.getSourceType() != null) {
					vo.setOrderRecordSourceType(po.getSourceType().toString());
				}
				vo.setOrderRecordSourceTypeName(po.getSourceTypeName());
				vo.setOrderRecordSerNo(po.getSerNo());
				if (po.getSellerId() != null) {
					vo.setOrderRecordSellerId(po.getSellerId().toString());
				}
				vo.setOrderRecordSellerName(po.getSellerName());
				if (po.getCusId() != null) {
					vo.setOrderRecordCusId(po.getCusId().toString());
				}
				vo.setOrderRecordCusName(po.getCusName());
				if (po.getPrimaryConfigurationId() != null) {
					vo.setOrderRecordPrimaryConfigurationId(po
							.getPrimaryConfigurationId().toString());
				}
				vo.setOrderRecordPrimaryConfigurationName(po
						.getPrimaryConfigurationName());

				tempGoodsList.add(goods);
				tempOrderGoodsRelationList.add(orderGoodsRelation);
				if (i + 1 == l.size()) {
					vo.setGoodsList(tempGoodsList);
					vo.setOrderGoodsRelationList(tempOrderGoodsRelationList);
					resList.add(vo);
				}
			} else {
				tempGoodsList.add(goods);
				tempOrderGoodsRelationList.add(orderGoodsRelation);
				if (i + 1 == l.size()) {
					vo.setGoodsList(tempGoodsList);
					vo.setOrderGoodsRelationList(tempOrderGoodsRelationList);
					resList.add(vo);
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
	public List<OrderVO> orderListExcel(String orderNo, String orderRecordId,
			String clientUserId, String clientUserName,
			String clientUserMobile, String clientUserEmail,
			String clientUserSex, String clientUserType,
			String clientUserTypeName, String wxOpenId, String staffId,
			String userRealMobile, String userRealPlane, String userRealName,
			String countryId, String countryName, String provinceId,
			String provinceName, String cityId, String cityName, String areaId,
			String areaName, String userRealAddress, String userRealPostcode,
			String userRealStartTime, String userRealEndTime,
			String paymentType, String paymentTypeName, String paymentStatus,
			String paymentStatusName, String paymentProvider,
			String paymentProviderName, String paymentStartTime,
			String paymentEndTime, String operateStartTime,
			String operateEndTime, String operateStatus,
			String operateStatusName, String createStartTime,
			String createEndTime, String originalPrice, String totalPrice,
			String logisticsCompanyId, String logisticsCompanyName,
			String deliveryStaffId, String deliveryStaffName,
			String deliveryStaffMobile, String deliveryStartStartTime,
			String deliveryStartEndTime, String deliveryEndStartTime,
			String deliveryEndEndTime, String deliveryStatus,
			String deliveryStatusName, String sourceId, String sourceName,
			String sourceType, String sourceTypeName, String serNo,
			String sellerId, String sellerName, String cusId, String cusName,
			String primaryConfigurationId, String primaryConfigurationName,
			int curPage, int pageSize, String orderField, String direction) {
		String hql = "from OrderRecord a, Goods b,OrderGoodsRelation c where c.orderRecordId=a.id"
				+ " and c.goodsId=b.id";
		if (!StringHelper.isNull(orderNo)) {
			hql = hql + " and a.orderNo = '" + orderNo + "'";
		}
		if (!StringHelper.isNull(orderRecordId)) {
			hql = hql + " and a.id = '" + orderRecordId + "'";
		}
		if (!StringHelper.isNull(clientUserId)) {
			hql = hql + " and a.clientUserId = '" + clientUserId + "'";
		}
		if (!StringHelper.isNull(clientUserName)) {
			hql = hql + " and a.clientUserName like '%" + clientUserName + "%'";
		}
		if (!StringHelper.isNull(clientUserMobile)) {
			hql = hql + " and a.clientUserMobile = '" + clientUserMobile + "'";
		}
		if (!StringHelper.isNull(clientUserEmail)) {
			hql = hql + " and a.clientUserEmail = '" + clientUserEmail + "'";
		}
		if (!StringHelper.isNull(clientUserSex)) {
			hql = hql + " and a.clientUserSex = '" + clientUserSex + "'";
		}
		if (!StringHelper.isNull(clientUserType)) {
			hql = hql + " and a.clientUserType = " + clientUserType;
		}
		if (!StringHelper.isNull(clientUserTypeName)) {
			hql = hql + " and a.clientUserTypeName like '%"
					+ clientUserTypeName + "%'";
		}
		if (!StringHelper.isNull(wxOpenId)) {
			hql = hql + " and a.wxOpenId = '" + wxOpenId + "'";
		}
		if (!StringHelper.isNull(staffId)) {
			hql = hql + " and a.staffId = '" + staffId + "'";
		}
		if (!StringHelper.isNull(userRealName)) {
			hql = hql + " and a.userRealName like '%" + userRealName + "%'";
		}
		if (!StringHelper.isNull(countryId)) {
			hql = hql + " and a.countryId = '" + countryId + "'";
		}
		if (!StringHelper.isNull(countryName)) {
			hql = hql + " and a.countryName like '%" + countryName + "%'";
		}
		if (!StringHelper.isNull(provinceId)) {
			hql = hql + " and a.provinceId = '" + provinceId + "'";
		}
		if (!StringHelper.isNull(provinceName)) {
			hql = hql + " and a.provinceName like '%" + provinceName + "%'";
		}
		if (!StringHelper.isNull(cityId)) {
			hql = hql + " and a.cityId = '" + cityId + "'";
		}
		if (!StringHelper.isNull(cityName)) {
			hql = hql + " and a.cityName like '%" + cityName + "%'";
		}
		if (!StringHelper.isNull(areaId)) {
			hql = hql + " and a.areaId = '" + areaId + "'";
		}
		if (!StringHelper.isNull(areaName)) {
			hql = hql + " and a.areaName like '%" + areaName + "%'";
		}
		if (!StringHelper.isNull(userRealAddress)) {
			hql = hql + " and a.userRealAddress like '%" + userRealAddress
					+ "%'";
		}
		if (!StringHelper.isNull(userRealMobile)) {
			hql = hql + " and a.userRealMobile = " + userRealMobile;
		}
		if (!StringHelper.isNull(userRealPlane)) {
			hql = hql + " and a.userRealPlane = " + userRealPlane;
		}
		if (!StringHelper.isNull(userRealPostcode)) {
			hql = hql + " and a.userRealPostcode = " + userRealPostcode;
		}
		if (!StringHelper.isNull(primaryConfigurationId)) {
			hql = hql + " and a.primaryConfigurationId = "
					+ primaryConfigurationId;
		}
		if (!StringHelper.isNull(userRealStartTime)) {
			hql = hql + " and a.userRealTime >='" + userRealStartTime + "'";
		}
		if (!StringHelper.isNull(userRealEndTime)) {
			hql = hql + " and a.userRealTime <='" + userRealEndTime + "'";
		}
		if (!StringHelper.isNull(paymentType)) {
			hql = hql + " and a.paymentType = " + paymentType;
		}
		if (!StringHelper.isNull(paymentTypeName)) {
			hql = hql + " and a.paymentTypeName like '" + paymentTypeName
					+ "%'";
		}
		if (!StringHelper.isNull(paymentStatus)) {
			hql = hql + " and a.paymentStatus = " + paymentStatus;
		}
		if (!StringHelper.isNull(paymentStatusName)) {
			hql = hql + " and a.paymentStatusName like '%" + paymentStatusName
					+ "%'";
		}
		if (!StringHelper.isNull(paymentProvider)) {
			hql = hql + " and a.paymentProvider = " + paymentProvider;
		}
		if (!StringHelper.isNull(paymentProviderName)) {
			hql = hql + " and a.paymentProviderName like '%"
					+ paymentProviderName + "%'";
		}
		if (!StringHelper.isNull(paymentStartTime)) {
			hql = hql + " and a.paymentTime >='" + paymentStartTime + "'";
		}
		if (!StringHelper.isNull(paymentEndTime)) {
			hql = hql + " and a.paymentTime <='" + paymentEndTime + "'";
		}
		if (!StringHelper.isNull(operateStartTime)) {
			hql = hql + " and a.operateTime >='" + operateStartTime + "'";
		}
		if (!StringHelper.isNull(operateEndTime)) {
			hql = hql + " and a.operateTime <='" + operateEndTime + "'";
		}
		if (!StringHelper.isNull(operateStatus) && !operateStatus.equals("0")) {
			hql = hql + " and a.operateStatus = " + operateStatus;
		}
		if (!StringHelper.isNull(operateStatusName)) {
			hql = hql + " and a.operateStatusName like '%" + operateStatusName
					+ "%'";
		}
		if (!StringHelper.isNull(createStartTime)) {
			hql = hql + " and a.createTime >='" + createStartTime + "'";
		}
		if (!StringHelper.isNull(createEndTime)) {
			hql = hql + " and a.createTime <='" + createEndTime + "'";
		}
		if (!StringHelper.isNull(originalPrice)) {
			hql = hql + " and a.originalPrice = " + originalPrice;
		}
		if (!StringHelper.isNull(totalPrice)) {
			hql = hql + " and a.totalPrice = " + totalPrice;
		}
		if (!StringHelper.isNull(logisticsCompanyId)) {
			hql = hql + " and a.logisticsCompanyId = " + logisticsCompanyId;
		}
		if (!StringHelper.isNull(logisticsCompanyName)) {
			hql = hql + " and a.logisticsCompanyName like '%"
					+ logisticsCompanyName + "%'";
		}
		if (!StringHelper.isNull(deliveryStaffId)) {
			hql = hql + " and a.deliveryStaffId = " + deliveryStaffId;
		}
		if (!StringHelper.isNull(deliveryStaffName)) {
			hql = hql + " and a.deliveryStaffName like '%" + deliveryStaffName
					+ "%'";
		}
		if (!StringHelper.isNull(deliveryStaffMobile)) {
			hql = hql + " and a.deliveryStaffMobile = " + deliveryStaffMobile;
		}
		if (!StringHelper.isNull(deliveryStartStartTime)) {
			hql = hql + " and a.deliveryStartTime >='" + deliveryStartStartTime
					+ "'";
		}
		if (!StringHelper.isNull(deliveryStartEndTime)) {
			hql = hql + " and a.deliveryStartTime <='" + deliveryStartEndTime
					+ "'";
		}
		if (!StringHelper.isNull(deliveryEndStartTime)) {
			hql = hql + " and a.deliveryEndTime >='" + deliveryEndStartTime
					+ "'";
		}
		if (!StringHelper.isNull(deliveryEndEndTime)) {
			hql = hql + " and a.deliveryEndTime <='" + deliveryEndEndTime + "'";
		}
		if (!StringHelper.isNull(deliveryStatus)) {
			hql = hql + " and a.deliveryStatus = " + deliveryStatus;
		}
		if (!StringHelper.isNull(deliveryStatusName)) {
			hql = hql + " and a.deliveryStatusName like '%"
					+ deliveryStatusName + "%'";
		}
		if (!StringHelper.isNull(sourceId)) {
			hql = hql + " and a.sourceId = " + sourceId;
		}
		if (!StringHelper.isNull(sourceName)) {
			hql = hql + " and a.sourceName like '%" + sourceName + "%'";
		}
		if (!StringHelper.isNull(sourceType)) {
			hql = hql + " and a.sourceType = " + sourceType;
		}
		if (!StringHelper.isNull(sourceTypeName)) {
			hql = hql + " and a.sourceTypeName like '%" + sourceTypeName + "%'";
		}
		if (!StringHelper.isNull(serNo)) {
			hql = hql + " and a.serNo = '" + serNo + "'";
		}
		if (!StringHelper.isNull(sellerId)) {
			hql = hql + " and a.sellerId = " + sellerId;
		}
		if (!StringHelper.isNull(sellerName)) {
			hql = hql + " and a.sellerName like '%" + sellerName + "%'";
		}
		if (!StringHelper.isNull(cusId)) {
			hql = hql + " and a.cusId = " + cusId;
		}
		if (!StringHelper.isNull(cusName)) {
			hql = hql + " and a.cusName like '%" + cusName + "%'";
		}
		if (!StringHelper.isNull(orderField) && !StringHelper.isNull(direction)) {
			hql = hql + " order by " + orderField + " " + direction;
		} else {
			hql += " order by a.id desc,c.orderRecordId desc, a.clientUserId";
		}
		List l = commonDao.getEntitiesByPropertiesWithHql(hql, curPage,
				pageSize);
		List<OrderVO> resList = new LinkedList<OrderVO>();
		String tempOrderRecordId = "0";
		OrderVO vo = new OrderVO();
		List<Goods> tempGoodsList = new LinkedList<Goods>();
		List<OrderGoodsRelation> tempOrderGoodsRelationList = new LinkedList<OrderGoodsRelation>();
		for (int i = 0; i < l.size(); i++) {
			Object[] obj = (Object[]) l.get(i);
			OrderRecord po = (OrderRecord) obj[0];
			Goods goods = (Goods) obj[1];
			OrderGoodsRelation orderGoodsRelation = (OrderGoodsRelation) obj[2];
			// 判定是不是同一个订单的商品，如果是就汇总到订单中
			if (!tempOrderRecordId.equals(po.getId().toString())) {
				if (!tempOrderRecordId.equals("0")) {
					vo.setGoodsList(tempGoodsList);
					vo.setOrderGoodsRelationList(tempOrderGoodsRelationList);
					resList.add(vo);
				}
				tempOrderRecordId = po.getId().toString();
				vo = new OrderVO(); // 初始化新订单
				tempGoodsList = new ArrayList<Goods>(); // 初始化订单所有商品列表
				tempOrderGoodsRelationList = new LinkedList<OrderGoodsRelation>();// 初始化订单所有商品中间表列表
				// orderRecord
				if (po.getId() != null) {
					vo.setOrderRecordId(po.getId().toString());
				}
				if (po.getClientUserId() != null) {
					vo.setOrderRecordClientUserId(po.getClientUserId()
							.toString());
				}
				vo.setOrderRecordClientUserMobile(po.getClientUserMobile());
				vo.setOrderRecordClientUserEmail(po.getClientUserEmail());
				vo.setOrderRecordClientUserName(po.getClientUserName());
				vo.setOrderRecordClientUserSex(po.getClientUserSex());
				vo.setOrderRecordWxOpenId(po.getWxOpenId());
				vo.setOrderRecordStaffId(po.getStaffId());
				vo.setOrderRecordUserRealMobile(po.getUserRealMobile());
				vo.setOrderRecordUserRealPlane(po.getUserRealPlane());
				vo.setOrderRecordUserRealName(po.getUserRealName());
				vo.setOrderRecordUserRealDescription(po
						.getUserRealDescription());
				vo.setOrderNo(po.getOrderNo());
				if (po.getCountryId() != null) {
					vo.setOrderRecordCountryId(po.getCountryId().toString());
				}
				vo.setOrderRecordCountryName(po.getCountryName());
				if (po.getProvinceId() != null) {
					vo.setOrderRecordProvinceId(po.getProvinceId().toString());
				}
				vo.setOrderRecordProvinceName(po.getProvinceName());
				if (po.getCityId() != null) {
					vo.setOrderRecordCityId(po.getCityId().toString());
				}
				vo.setOrderRecordCityName(po.getCityName());
				if (po.getAreaId() != null) {
					vo.setOrderRecordAreaId(po.getAreaId().toString());
				}
				vo.setOrderRecordAreaName(po.getAreaName());
				vo.setOrderRecordUserRealAddress(po.getUserRealAddress());
				vo.setOrderRecordUserRealPostcode(po.getUserRealPostcode());
				if (po.getUserRealTime() != null) {
					vo.setOrderRecordUserRealTime(po.getUserRealTime()
							.toString());
				}

				if (po.getPaymentType() != null) {
					vo.setOrderRecordPaymentType(po.getPaymentType().toString());
				}
				vo.setOrderRecordPaymentTypeName(po.getPaymentTypeName());
				if (po.getPaymentStatus() != null) {
					vo.setOrderRecordPaymentStatus(po.getPaymentStatus()
							.toString());
				}
				vo.setOrderRecordPaymentStatusName(po.getPaymentStatusName());
				if (po.getPaymentProvider() != null) {
					vo.setOrderRecordPaymentProvider(po.getPaymentProvider()
							.toString());
				}
				vo.setOrderRecordPaymentProviderName(po
						.getPaymentProviderName());
				if (po.getPaymentTime() != null) {
					vo.setOrderRecordPaymentTime(StringHelper.getSystime(
							ViewConstant.PAGE_DATE_FORMAT_SEC, po
									.getPaymentTime().getTime()));
				}
				if (po.getOperateTime() != null) {
					vo.setOrderRecordOperateTime(StringHelper.getSystime(
							ViewConstant.PAGE_DATE_FORMAT_SEC, po
									.getOperateTime().getTime()));
				}
				if (po.getOperateStatus() != null) {
					vo.setOrderRecordOperateStatus(po.getOperateStatus()
							.toString());
				}
				vo.setOrderRecordOperateStatusName(po.getOperateStatusName());
				if (po.getCreateTime() != null) {
					vo.setOrderRecordCreateTime(StringHelper.getSystime(
							ViewConstant.PAGE_DATE_FORMAT_SEC, po
									.getCreateTime().getTime()));
				}
				if (po.getOriginalPrice() != null) {
					vo.setOrderRecordOriginalPrice(po.getOriginalPrice()
							.toString());
				}
				if (po.getTotalPrice() != null) {
					vo.setOrderRecordTotalPrice(po.getTotalPrice().toString());
				}
				if (po.getLogisticsCompanyId() != null) {
					vo.setOrderRecordLogisticsCompanyId(po
							.getLogisticsCompanyId().toString());
				}
				if (po.getLogisticsCompanyName() != null) {
					vo.setOrderRecordLogisticsCompanyName(po
							.getLogisticsCompanyName());
				}
				if (po.getDeliveryStaffId() != null) {
					vo.setOrderRecordDliveryStaffId(po.getDeliveryStaffId()
							.toString());
				}
				vo.setOrderRecordDeliveryStaffName(po.getDeliveryStaffName());
				vo.setOrderRecordDeliveryStaffMobile(po
						.getDeliveryStaffMobile());
				if (po.getDeliveryStartTime() != null) {
					vo.setOrderRecordDeliveryStartTime(StringHelper.getSystime(
							ViewConstant.PAGE_DATE_FORMAT_SEC, po
									.getDeliveryStartTime().getTime()));
				} else {
					if (po.getPaymentProvider() == 152) {
						SimpleDateFormat sdf = new SimpleDateFormat(
								"yyyy-MM-dd HH:mm:ss");
						Date d;
						try {
							d = sdf.parse(StringHelper.getSystime(
									ViewConstant.PAGE_DATE_FORMAT_SEC, po
											.getCreateTime().getTime()));
							vo.setOrderRecordDeliveryStartTime(sdf
									.format(new Date(d.getTime() + 1 * 24 * 60
											* 60 * 1000)));
						} catch (ParseException e) {
							e.printStackTrace();
						}
					} else {
						if (po.getPaymentTime() != null) {
							SimpleDateFormat sdf = new SimpleDateFormat(
									"yyyy-MM-dd HH:mm:ss");
							Date d;
							try {
								d = sdf.parse(StringHelper.getSystime(
										ViewConstant.PAGE_DATE_FORMAT_SEC, po
												.getPaymentTime().getTime()));
								vo.setOrderRecordDeliveryStartTime(sdf
										.format(new Date(d.getTime() + 1 * 24
												* 60 * 60 * 1000)));
							} catch (ParseException e) {
								e.printStackTrace();
							}
						}
					}
				}
				if (po.getDeliveryEndTime() != null) {
					vo.setOrderRecordDeliveryEndTime(StringHelper.getSystime(
							ViewConstant.PAGE_DATE_FORMAT_SEC, po
									.getDeliveryEndTime().getTime()));
				}
				if (po.getDeliveryStatus() != null) {
					vo.setOrderRecordDeliveryStatus(po.getDeliveryStatus()
							.toString());
				}
				vo.setOrderRecordDeliveryStatusName(po.getDeliveryStatusName());
				if (po.getSourceId() != null) {
					vo.setOrderRecordSourceId(po.getSourceId().toString());
				}
				vo.setOrderRecordSourceName(po.getSourceName());
				if (po.getSourceType() != null) {
					vo.setOrderRecordSourceType(po.getSourceType().toString());
				}
				vo.setOrderRecordSourceTypeName(po.getSourceTypeName());
				vo.setOrderRecordSerNo(po.getSerNo());
				if (po.getSellerId() != null) {
					vo.setOrderRecordSellerId(po.getSellerId().toString());
				}
				vo.setOrderRecordSellerName(po.getSellerName());
				if (po.getCusId() != null) {
					vo.setOrderRecordCusId(po.getCusId().toString());
				}
				vo.setOrderRecordCusName(po.getCusName());
				if (po.getPrimaryConfigurationId() != null) {
					vo.setOrderRecordPrimaryConfigurationId(po
							.getPrimaryConfigurationId().toString());
				}
				vo.setOrderRecordPrimaryConfigurationName(po
						.getPrimaryConfigurationName());

				tempGoodsList.add(goods);
				tempOrderGoodsRelationList.add(orderGoodsRelation);
				if (i + 1 == l.size()) {
					vo.setGoodsList(tempGoodsList);
					vo.setOrderGoodsRelationList(tempOrderGoodsRelationList);
					resList.add(vo);
				}
			} else {
				tempGoodsList.add(goods);
				tempOrderGoodsRelationList.add(orderGoodsRelation);
				if (i + 1 == l.size()) {
					vo.setGoodsList(tempGoodsList);
					vo.setOrderGoodsRelationList(tempOrderGoodsRelationList);
					resList.add(vo);
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
	public List<Object[]> orderGoodsCount(String orderNo, String orderRecordId,
			String clientUserId, String clientUserName,
			String clientUserMobile, String clientUserEmail,
			String clientUserSex, String clientUserType,
			String clientUserTypeName, String wxOpenId, String staffId,
			String userRealMobile, String userRealPlane, String userRealName,
			String countryId, String countryName, String provinceId,
			String provinceName, String cityId, String cityName, String areaId,
			String areaName, String userRealAddress, String userRealPostcode,
			String userRealStartTime, String userRealEndTime,
			String paymentType, String paymentTypeName, String paymentStatus,
			String paymentStatusName, String paymentProvider,
			String paymentProviderName, String paymentStartTime,
			String paymentEndTime, String operateStartTime,
			String operateEndTime, String operateStatus,
			String operateStatusName, String createStartTime,
			String createEndTime, String originalPrice, String totalPrice,
			String logisticsCompanyId, String logisticsCompanyName,
			String deliveryStaffId, String deliveryStaffName,
			String deliveryStaffMobile, String deliveryStartStartTime,
			String deliveryStartEndTime, String deliveryEndStartTime,
			String deliveryEndEndTime, String deliveryStatus,
			String deliveryStatusName, String sourceId, String sourceName,
			String sourceType, String sourceTypeName, String serNo,
			String sellerId, String sellerName, String cusId, String cusName,
			String primaryConfigurationId, String primaryConfigurationName,
			int curPage, int pageSize, String orderField, String direction) {
		String hql = "select b.goodsName, count(*) from OrderRecord a, Goods b,OrderGoodsRelation c "
				+ " where c.orderRecordId=a.id and c.goodsId=b.id";
		if (!StringHelper.isNull(orderNo)) {
			hql = hql + " and a.orderNo = '" + orderNo + "'";
		}
		if (!StringHelper.isNull(orderRecordId)) {
			hql = hql + " and a.id = '" + orderRecordId + "'";
		}
		if (!StringHelper.isNull(clientUserId)) {
			hql = hql + " and a.clientUserId = '" + clientUserId + "'";
		}
		if (!StringHelper.isNull(clientUserName)) {
			hql = hql + " and a.clientUserName like '%" + clientUserName + "%'";
		}
		if (!StringHelper.isNull(clientUserMobile)) {
			hql = hql + " and a.clientUserMobile = '" + clientUserMobile + "'";
		}
		if (!StringHelper.isNull(clientUserEmail)) {
			hql = hql + " and a.clientUserEmail = '" + clientUserEmail + "'";
		}
		if (!StringHelper.isNull(clientUserSex)) {
			hql = hql + " and a.clientUserSex = '" + clientUserSex + "'";
		}
		if (!StringHelper.isNull(clientUserType)) {
			hql = hql + " and a.clientUserType = " + clientUserType;
		}
		if (!StringHelper.isNull(clientUserTypeName)) {
			hql = hql + " and a.clientUserTypeName like '%"
					+ clientUserTypeName + "%'";
		}
		if (!StringHelper.isNull(wxOpenId)) {
			hql = hql + " and a.wxOpenId = '" + wxOpenId + "'";
		}
		if (!StringHelper.isNull(staffId)) {
			hql = hql + " and a.staffId = '" + staffId + "'";
		}
		if (!StringHelper.isNull(userRealName)) {
			hql = hql + " and a.userRealName like '%" + userRealName + "%'";
		}
		if (!StringHelper.isNull(countryId)) {
			hql = hql + " and a.countryId = '" + countryId + "'";
		}
		if (!StringHelper.isNull(countryName)) {
			hql = hql + " and a.countryName like '%" + countryName + "%'";
		}
		if (!StringHelper.isNull(provinceId)) {
			hql = hql + " and a.provinceId = '" + provinceId + "'";
		}
		if (!StringHelper.isNull(provinceName)) {
			hql = hql + " and a.provinceName like '%" + provinceName + "%'";
		}
		if (!StringHelper.isNull(cityId)) {
			hql = hql + " and a.cityId = '" + cityId + "'";
		}
		if (!StringHelper.isNull(cityName)) {
			hql = hql + " and a.cityName like '%" + cityName + "%'";
		}
		if (!StringHelper.isNull(areaId)) {
			hql = hql + " and a.areaId = '" + areaId + "'";
		}
		if (!StringHelper.isNull(areaName)) {
			hql = hql + " and a.areaName like '%" + areaName + "%'";
		}
		if (!StringHelper.isNull(userRealAddress)) {
			hql = hql + " and a.userRealAddress like '%" + userRealAddress
					+ "%'";
		}
		if (!StringHelper.isNull(userRealMobile)) {
			hql = hql + " and a.userRealMobile = " + userRealMobile;
		}
		if (!StringHelper.isNull(userRealPlane)) {
			hql = hql + " and a.userRealPlane = " + userRealPlane;
		}
		if (!StringHelper.isNull(userRealPostcode)) {
			hql = hql + " and a.userRealPostcode = " + userRealPostcode;
		}
		if (!StringHelper.isNull(primaryConfigurationId)) {
			hql = hql + " and a.primaryConfigurationId = "
					+ primaryConfigurationId;
		}
		if (!StringHelper.isNull(userRealStartTime)) {
			hql = hql + " and a.userRealTime >='" + userRealStartTime + "'";
		}
		if (!StringHelper.isNull(userRealEndTime)) {
			hql = hql + " and a.userRealTime <='" + userRealEndTime + "'";
		}
		if (!StringHelper.isNull(paymentType)) {
			hql = hql + " and a.paymentType = " + paymentType;
		}
		if (!StringHelper.isNull(paymentTypeName)) {
			hql = hql + " and a.paymentTypeName like '" + paymentTypeName
					+ "%'";
		}
		if (!StringHelper.isNull(paymentStatus)) {
			hql = hql + " and a.paymentStatus = " + paymentStatus;
		}
		if (!StringHelper.isNull(paymentStatusName)) {
			hql = hql + " and a.paymentStatusName like '%" + paymentStatusName
					+ "%'";
		}
		if (!StringHelper.isNull(paymentProvider)) {
			hql = hql + " and a.paymentProvider = " + paymentProvider;
		}
		if (!StringHelper.isNull(paymentProviderName)) {
			hql = hql + " and a.paymentProviderName like '%"
					+ paymentProviderName + "%'";
		}
		if (!StringHelper.isNull(paymentStartTime)) {
			hql = hql + " and a.paymentTime >='" + paymentStartTime + "'";
		}
		if (!StringHelper.isNull(paymentEndTime)) {
			hql = hql + " and a.paymentTime <='" + paymentEndTime + "'";
		}
		if (!StringHelper.isNull(operateStartTime)) {
			hql = hql + " and a.operateTime >='" + operateStartTime + "'";
		}
		if (!StringHelper.isNull(operateEndTime)) {
			hql = hql + " and a.operateTime <='" + operateEndTime + "'";
		}
		if (!StringHelper.isNull(operateStatus) && !operateStatus.equals("0")) {
			hql = hql + " and a.operateStatus = " + operateStatus;
		}
		if (!StringHelper.isNull(operateStatusName)) {
			hql = hql + " and a.operateStatusName like '%" + operateStatusName
					+ "%'";
		}
		if (!StringHelper.isNull(createStartTime)) {
			hql = hql + " and a.createTime >='" + createStartTime + "'";
		}
		if (!StringHelper.isNull(createEndTime)) {
			hql = hql + " and a.createTime <='" + createEndTime + "'";
		}
		if (!StringHelper.isNull(originalPrice)) {
			hql = hql + " and a.originalPrice = " + originalPrice;
		}
		if (!StringHelper.isNull(totalPrice)) {
			hql = hql + " and a.totalPrice = " + totalPrice;
		}
		if (!StringHelper.isNull(logisticsCompanyId)) {
			hql = hql + " and a.logisticsCompanyId = " + logisticsCompanyId;
		}
		if (!StringHelper.isNull(logisticsCompanyName)) {
			hql = hql + " and a.logisticsCompanyName like '%"
					+ logisticsCompanyName + "%'";
		}
		if (!StringHelper.isNull(deliveryStaffId)) {
			hql = hql + " and a.deliveryStaffId = " + deliveryStaffId;
		}
		if (!StringHelper.isNull(deliveryStaffName)) {
			hql = hql + " and a.deliveryStaffName like '%" + deliveryStaffName
					+ "%'";
		}
		if (!StringHelper.isNull(deliveryStaffMobile)) {
			hql = hql + " and a.deliveryStaffMobile = " + deliveryStaffMobile;
		}
		if (!StringHelper.isNull(deliveryStartStartTime)) {
			hql = hql + " and a.deliveryStartTime >='" + deliveryStartStartTime
					+ "'";
		}
		if (!StringHelper.isNull(deliveryStartEndTime)) {
			hql = hql + " and a.deliveryStartTime <='" + deliveryStartEndTime
					+ "'";
		}
		if (!StringHelper.isNull(deliveryEndStartTime)) {
			hql = hql + " and a.deliveryEndTime >='" + deliveryEndStartTime
					+ "'";
		}
		if (!StringHelper.isNull(deliveryEndEndTime)) {
			hql = hql + " and a.deliveryEndTime <='" + deliveryEndEndTime + "'";
		}
		if (!StringHelper.isNull(deliveryStatus)) {
			hql = hql + " and a.deliveryStatus = " + deliveryStatus;
		}
		if (!StringHelper.isNull(deliveryStatusName)) {
			hql = hql + " and a.deliveryStatusName like '%"
					+ deliveryStatusName + "%'";
		}
		if (!StringHelper.isNull(sourceId)) {
			hql = hql + " and a.sourceId = " + sourceId;
		}
		if (!StringHelper.isNull(sourceName)) {
			hql = hql + " and a.sourceName like '%" + sourceName + "%'";
		}
		if (!StringHelper.isNull(sourceType)) {
			hql = hql + " and a.sourceType = " + sourceType;
		}
		if (!StringHelper.isNull(sourceTypeName)) {
			hql = hql + " and a.sourceTypeName like '%" + sourceTypeName + "%'";
		}
		if (!StringHelper.isNull(serNo)) {
			hql = hql + " and a.serNo = '" + serNo + "'";
		}
		if (!StringHelper.isNull(sellerId)) {
			hql = hql + " and a.sellerId = " + sellerId;
		}
		if (!StringHelper.isNull(sellerName)) {
			hql = hql + " and a.sellerName like '%" + sellerName + "%'";
		}
		if (!StringHelper.isNull(cusId)) {
			hql = hql + " and a.cusId = " + cusId;
		}
		if (!StringHelper.isNull(cusName)) {
			hql = hql + " and a.cusName like '%" + cusName + "%'";
		}
		hql = hql + " group by b.goodsName";
		if (!StringHelper.isNull(orderField) && !StringHelper.isNull(direction)) {
			hql = hql + " order by " + orderField + " " + direction;
		} else {
			hql += " order by b.id";
		}
		System.out.println(hql);
		List<Object[]> l = commonDao.getEntitiesByPropertiesWithHql(hql,
				curPage, pageSize);
		return l;
	}

	/**
	 * @return List<OrderRecord>
	 */
	public List<OrderRecord> listAll() {
		return dao.findAll();
	}

}
