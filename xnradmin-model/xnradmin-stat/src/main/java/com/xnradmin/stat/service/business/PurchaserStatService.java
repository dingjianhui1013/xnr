/**
 *2012-5-11 上午9:00:02
 */
package com.xnradmin.stat.service.business;
import java.lang.reflect.InvocationTargetException;
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
@Service("PurchaserStatService")
public class PurchaserStatService {

	@Autowired
	private BusinessOrderRecordDAO dao;

	@Autowired
	private CommonDAO commonDao;

	@Autowired
	private CommonJDBCDAO commonJDBCDAO;

	static Log log = LogFactory.getLog(PurchaserStatService.class);

	
	/**
	 * 客户经理订单与金额汇总
	 * 
	 * @param curPage
	 * @param pageSize
	 * @param orderField
	 * @param direction
	 * @return List<Object[]>
	 */
	public List<Object[]> findPurchaserStat(BusinessOrderVO vo,
			int curPage, int pageSize) throws Exception{
		String hql = " from business_order_record a, common_staff b, business_goods c, "
				+ " business_order_goods_relation d, business_category e"
				+ " where a.STAFF_ID = b.id and a.id = d.ORDER_RECORD_ID "
				+ "	and d.GOODS_ID=c.id and c.GOODS_CATEGORY_ID=e.id ";
		String selectHql = "select b.STAFF_NAME";
		String groupHql = " group by b.STAFF_NAME";
		if (vo != null && vo.getBusinessOrderRecord() != null) {
			if (!StringHelper.isNull(vo.getBusinessOrderRecord().getStaffId())) {
				hql = hql + " and a.STAFF_ID = '"
						+ vo.getBusinessOrderRecord().getStaffId() + "'";
			}
			if (vo.getBusinessOrderRecord().getPaymentType() != null) {
				hql = hql + " and a.PAYMENT_TYPE = "
						+ vo.getBusinessOrderRecord().getPaymentType();
			}
			if (vo.getBusinessOrderRecord().getPaymentStatus() != null) {
				hql = hql + " and a.PAYMENT_STATUS = "
						+ vo.getBusinessOrderRecord().getPaymentStatus();
			}
			if (vo.getBusinessOrderRecord().getPaymentProvider() != null) {
				hql = hql + " and a.PAYMENT_PROVIDER = "
						+ vo.getBusinessOrderRecord().getPaymentProvider();
			}
			if (!StringHelper.isNull(vo.getPaymentStartTime())) {
				hql = hql + " and a.PAYMENT_TIME >='" + vo.getPaymentStartTime()
						+ "'";
			}
			if (!StringHelper.isNull(vo.getPaymentEndTime())) {
				hql = hql + " and a.PAYMENT_TIME <='" + vo.getPaymentEndTime()
						+ "'";
			}
			if (!StringHelper.isNull(vo.getOperateStartTime())) {
				hql = hql + " and a.OPERATE_TIME >='" + vo.getOperateStartTime()
						+ "'";
			}
			if (!StringHelper.isNull(vo.getOperateEndTime())) {
				hql = hql + " and a.OPERATE_TIME <='" + vo.getOperateEndTime()
						+ "'";
			}
			if (vo.getBusinessOrderRecord().getOperateStatus() != null
					&& !vo.getBusinessOrderRecord().getOperateStatus()
							.equals("0")) {
				hql = hql + " and a.OPERATE_STATUS = "
						+ vo.getBusinessOrderRecord().getOperateStatus();
			}
			if (!StringHelper.isNull(vo.getCreateStartTime())) {
				hql = hql + " and a.CREATE_TIME >='" + vo.getCreateStartTime()
						+ "'";
			}
			if (!StringHelper.isNull(vo.getCreateEndTime())) {
				hql = hql + " and a.CREATE_TIME <='" + vo.getCreateEndTime()
						+ "'";
			}
			if (vo.getLeaderStaff()!=null && vo.getLeaderStaff().getId()!=null) {
				hql = hql + " and b.leadStaffId = "
						+ vo.getLeaderStaff().getId();
			}
			if (!StringHelper.isNull(vo.getStaffCreateStartTime())) {
				hql = hql + " and b.CREATE_TIME >='" + vo.getStaffCreateStartTime()
						+ "'";
			}
			if (!StringHelper.isNull(vo.getStaffCreateEndTime())) {
				hql = hql + " and b.CREATE_TIME <='" + vo.getStaffCreateEndTime()
						+ "'";
			}
			if(vo.getBusinessGoods() != null){
				if(vo.getBusinessGoods().getId() != null){
					hql = hql + " and c.id = "
							+ vo.getBusinessGoods().getId();
				}
			}
			if(vo.getBusinessCategory() != null){
				if(vo.getBusinessCategory().getId() != null){
					hql = hql + " and e.id = "
							+ vo.getBusinessCategory().getId();
				}
			}
			//拼接SQL
			if(vo.getBusinessGoods() != null){
				if(vo.getBusinessGoods().getId() != null){
					selectHql = selectHql + " ,c.GOODS_NAME";
					groupHql = groupHql + " ,c.GOODS_NAME";
				}
			}
			if(vo.getBusinessCategory() != null){
				if(vo.getBusinessCategory().getId() != null){
					selectHql = selectHql + " ,e.CATEGORY_NAME";
					groupHql = groupHql + " ,e.CATEGORY_NAME";
				}
			}
			if (vo.getLeaderStaff()!=null && vo.getLeaderStaff().getId()!=null) {
				selectHql = selectHql + " ,b.leadStaffName";
				groupHql = groupHql + " ,b.leadStaffName";
				
			}
			selectHql = selectHql + " ,count(DISTINCT a.id) as n,sum(DISTINCT a.TOTAL_PRICE) as t,sum(DISTINCT a.TOTAL_PRICE)/count(DISTINCT a.id) as p";
		}
		hql = selectHql + hql + groupHql;
		List l = commonJDBCDAO.findBySQLListMap(hql, curPage, pageSize);
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
