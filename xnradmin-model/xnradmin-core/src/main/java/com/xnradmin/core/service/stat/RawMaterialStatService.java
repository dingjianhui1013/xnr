/**
 *2012-7-24 下午5:09:02
 */
package com.xnradmin.core.service.stat;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.cntinker.util.PoiExcelHelper;
import com.cntinker.util.StringHelper;
import com.xnradmin.constant.ViewConstant;
import com.xnradmin.core.dao.CommonDAO;
import com.xnradmin.core.dao.dishes.UploadOrderDAO;
import com.xnradmin.core.service.common.status.StatusService;
import com.xnradmin.po.common.status.Status;
import com.xnradmin.po.dishes.Dish;
import com.xnradmin.po.dishes.UploadOrder;
import com.xnradmin.po.mall.order.Purchase;
import com.xnradmin.vo.dishes.DishesVO;
/**
 * @author: liuxiang
 * 
 */
@Service("RawMaterialStatService")
public class RawMaterialStatService {

	static Log log = LogFactory.getLog(RawMaterialStatService.class);
	
	@Autowired
	private StatusService statusService;

	@Autowired
    private CommonDAO commonDao;
	
	
	/**
	 * 汇总原料总量详细数据
	 * 
	 * @param curPage
	 * @param pageSize
	 * @param orderField
	 * @param direction
	 * @return List<Object[]>
	 */
	public List<Object[]> findPurchaseData(String createStartTime, String createEndTime,
			Purchase po, int curPage, int pageSize,String orderField, String direction) {
		String hql = "select rawMaterialName ,sum(number) as number, weightName from Purchase "
				+ " where 1=1 ";
		if (!StringHelper.isNull(createStartTime)
				&& !StringHelper.isNull(createEndTime)) {
			hql = hql + " and createTime > '" + createStartTime + "' "
					+ " and createTime < '" + createEndTime + "'";
		}
		if (po.getGoodsId()!=null) {
			hql = hql + " and goodsId = " + po.getGoodsId();
		}
		if (!StringHelper.isNull(po.getGoodsName())) {
			hql = hql + " and goodsName like '%" + po.getGoodsName() + "%'";
		}
		if (po.getGoodsCategoryId()!=null) {
			hql = hql + " and goodsCategoryId = " + po.getGoodsCategoryId();
		}
		if (!StringHelper.isNull(po.getGoodsCategoryName())) {
			hql = hql + " and goodsCategoryName like '%" + po.getGoodsCategoryName() + "%'";
		}
		if (po.getDishId()!=null) {
			hql = hql + " and dishId = " + po.getDishId();
		}
		if (!StringHelper.isNull(po.getDishName())) {
			hql = hql + " and dishName like '%" + po.getDishName() + "%'";
		}
		if (po.getDishTypeId()!=null) {
			hql = hql + " and dishTypeId = " + po.getDishTypeId();
		}
		if (!StringHelper.isNull(po.getDishTypeName())) {
			hql = hql + " and dishTypeName like '%" + po.getDishTypeName() + "%'";
		}
		if (po.getRawMaterialId()!=null) {
			hql = hql + " and rawMaterialId = " + po.getRawMaterialId();
		}
		if (!StringHelper.isNull(po.getRawMaterialName())) {
			hql = hql + " and rawMaterialName like '%" + po.getRawMaterialName() + "%'";
		}
		if (po.getRawMaterialTypeId()!=null) {
			hql = hql + " and rawMaterialTypeId = " + po.getRawMaterialTypeId();
		}
		if (!StringHelper.isNull(po.getRawMaterialTypeName())) {
			hql = hql + " and rawMaterialTypeName like '%" + po.getRawMaterialTypeName() + "%'";
		}
		if (po.getClientUserId()!=null) {
			hql = hql + " and clientUserId = " + po.getClientUserId();
		}
		if (po.getOrderRecordId()!=null) {
			hql = hql + " and orderRecordId = " + po.getOrderRecordId();
		}
		if (po.getOrderNo()!=null) {
			hql = hql + " and orderNo = '" + po.getOrderNo() + "'";
		}
		hql = hql + " group by rawMaterialId";
		if (!StringHelper.isNull(orderField)
				&& !StringHelper.isNull(direction)) {
			hql = hql + " order by " + orderField + " " + direction;
		} else {
			hql += " order by rawMaterialId desc";
		}
		List<Object[]> l = commonDao.getEntitiesByPropertiesWithHql(hql,
				curPage, pageSize);
		return l;
	}
}
