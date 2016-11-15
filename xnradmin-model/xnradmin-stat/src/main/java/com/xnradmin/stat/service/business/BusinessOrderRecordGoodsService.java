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
import com.xnradmin.core.dao.business.order.BusinessOrderRecordDAO;
import com.xnradmin.vo.business.BusinessOrderVO;

/**
 * @autohr: xiang_liu
 * 
 */
@Service("BusinessOrderRecordGoodsService")
public class BusinessOrderRecordGoodsService {

	@Autowired
	private BusinessOrderRecordDAO dao;

	@Autowired
	private CommonDAO commonDao;

	static Log log = LogFactory.getLog(BusinessOrderRecordGoodsService.class);

	
	/**
	 * 客户经理订单与金额汇总
	 * 
	 * @param curPage
	 * @param pageSize
	 * @param orderField
	 * @param direction
	 * @return List<Object[]>
	 */
	public List<Object[]> findOrderGoodsStat(BusinessOrderVO vo,
			int curPage, int pageSize) {
		String hql = "select b.goodsName, c.weightName, a.goodsCount, count(*)"
				+ " from BusinessOrderGoodsRelation a, BusinessGoods b, BusinessWeight c,"
				+ " BusinessOrderRecord d where a.goodsId = b.id and a.goodsWeightId = c.id "
				+ " and d.id = a.orderRecordId and d.operateStatus=204 ";
		if (vo != null) {
			if (!StringHelper.isNull(vo.getCreateStartTime())) {
				hql = hql + " and d.createTime >='" + vo.getCreateStartTime()
						+ "'";
			}
			if (!StringHelper.isNull(vo.getCreateEndTime())) {
				hql = hql + " and d.createTime <='" + vo.getCreateEndTime()
						+ "'";
			}
		}
		hql = hql + " group by b.goodsName, c.weightName, a.goodsCount";
		List<Object[]> l = commonDao.getEntitiesByPropertiesWithHql(hql,
				curPage, pageSize);
		return l;
	}
}
