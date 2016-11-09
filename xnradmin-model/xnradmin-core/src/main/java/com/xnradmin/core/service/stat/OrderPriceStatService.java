/**
 *2012-7-24 下午5:09:02
 */
package com.xnradmin.core.service.stat;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cntinker.util.StringHelper;
import com.xnradmin.core.dao.CommonDAO;
import com.xnradmin.core.service.common.status.StatusService;
import com.xnradmin.po.mall.order.OrderRecord;
/**
 * @author: liuxiang
 * 
 */
@Service("OrderPriceStatService")
public class OrderPriceStatService {

	static Log log = LogFactory.getLog(OrderPriceStatService.class);
	
	@Autowired
	private StatusService statusService;

	@Autowired
    private CommonDAO commonDao;
	
	
	/**
	 * 汇总订单金额
	 * 
	 * @param curPage
	 * @param pageSize
	 * @param orderField
	 * @param direction
	 * @return List<Object[]>
	 */
	public List<Object[]> findOrderPrice(String createStartTime, String createEndTime,
			OrderRecord po, int curPage, int pageSize,String orderField, String direction) {
		String hql = "select sum(originalPrice) as originalPrice,sum(totalPrice) as totalPrice from OrderRecord "
				+ " where 1=1 ";
		if (!StringHelper.isNull(createStartTime)
				&& !StringHelper.isNull(createEndTime)) {
			hql = hql + " and createTime > '" + createStartTime + "' "
					+ " and createTime < '" + createEndTime + "'";
		}
		if (!StringHelper.isNull(po.getWxOpenId())) {
			hql = hql + " and wxOpenId = " + po.getWxOpenId();
		}
		if (!StringHelper.isNull(po.getClientUserMobile())) {
			hql = hql + " and clientUserMobile like '%" + po.getClientUserMobile() + "%'";
		}
		if (po.getId()!=null) {
			hql = hql + " and id = " + po.getId();
		}
		if (!StringHelper.isNull(po.getUserRealName())) {
			hql = hql + " and userRealName like '%" + po.getUserRealName() + "%'";
		}
		if (!StringHelper.isNull(po.getUserRealMobile())) {
			hql = hql + " and userRealMobile like '%" + po.getUserRealMobile() + "%'";
		}
		if (po.getPaymentProvider()!=null) {
			hql = hql + " and paymentProvider = " + po.getPaymentProvider();
		}
		if (po.getPaymentStatus()!=null) {
			hql = hql + " and paymentStatus = " + po.getPaymentStatus();
		}
		if (po.getDeliveryStatus()!=null) {
			hql = hql + " and deliveryStatus = " + po.getDeliveryStatus();
		}
		if (po.getOperateStatus()!=null) {
			hql = hql + " and operateStatus = " + po.getOperateStatus();
		}
		if (!StringHelper.isNull(po.getOrderNo())) {
			hql = hql + " and orderNo = '" + po.getOrderNo() + "'";
		}
		List<Object[]> l = commonDao.getEntitiesByPropertiesWithHql(hql,
				curPage, pageSize);
		return l;
	}
}
