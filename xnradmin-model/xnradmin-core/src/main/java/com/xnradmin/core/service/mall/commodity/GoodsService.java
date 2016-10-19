/**
 *2012-5-11 上午9:00:02
 */
package com.xnradmin.core.service.mall.commodity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cntinker.util.StringHelper;
import com.xnradmin.constant.ViewConstant;
import com.xnradmin.core.dao.CommonDAO;
import com.xnradmin.core.dao.mall.commodity.GoodsDAO;
import com.xnradmin.core.service.common.status.StatusService;
import com.xnradmin.po.common.status.Status;
import com.xnradmin.po.mall.commodity.Goods;
import com.xnradmin.vo.mall.CommodityVO;

/**
 * @autohr: xiang_liu
 * 
 */
@Service("GoodsService")
public class GoodsService {

	@Autowired
	private GoodsDAO dao;

	@Autowired
	private CommonDAO commonDao;
	
	@Autowired
	private StatusService statusService;

	static Log log = LogFactory.getLog(GoodsService.class);

	/**
	 * 
	 * @param po
	 * @return int
	 */
	public int save(Goods po) {
		Serializable cls = dao.save(po);
		return Integer.parseInt((cls.toString()));
	}

	public Goods findByid(String id) {
		return dao.findById(Integer.valueOf(id));
	}

	public int findByGoodsName(String goodsName, String id) {
		return dao.findByOlay(goodsName, id).size();
	}
	/**
	 * @param po
	 * @return int
	 */
	public int modify(Goods po) {
		Goods old = new Goods();
		old = findByid(po.getId().toString());
		if (StringHelper.isNull(po.getGoodsName())) {
			po.setGoodsName(old.getGoodsName());
		}
		if (po.getSortId()==null) {
			po.setSortId(old.getSortId());
		}
		if (po.getGoodsParentId()==null) {
			po.setGoodsParentId(old.getGoodsParentId());
		}
		if (StringHelper.isNull(po.getGoodsCategoryId())) {
			po.setGoodsCategoryId(old.getGoodsCategoryId());
		}
		if (StringHelper.isNull(po.getGoodsDescription())) {
			po.setGoodsDescription(old.getGoodsDescription());
		}
		if (po.getGoodsOriginalPrice()==null) {
			po.setGoodsOriginalPrice(old.getGoodsOriginalPrice());
		}
		if (po.getGoodsPreferentialPrice()==null) {
			po.setGoodsPreferentialPrice(old.getGoodsPreferentialPrice());
		}
		if (po.getGoodsBrandId()==null) {
			po.setGoodsBrandId(old.getGoodsBrandId());
		}
		if (po.getGoodsWeight()==null) {
			po.setGoodsWeight(old.getGoodsWeight());
		}
		if (po.getGoodsStock()==null) {
			po.setGoodsStock(old.getGoodsStock());
		}
		if (po.getGoodsStatus()==null) {
			po.setGoodsStatus(old.getGoodsStatus());
		}
		if (po.getIsFreeLogistics()==null) {
			po.setIsFreeLogistics(old.getIsFreeLogistics());
		}
		if (po.getIsNewGoods()==null) {
			po.setIsNewGoods(old.getIsNewGoods());
		}
		if (po.getIsDiscountGoods()==null) {
			po.setIsDiscountGoods(old.getIsDiscountGoods());
		}
		if (po.getIsHotSaleGoods()==null) {
			po.setIsHotSaleGoods(old.getIsHotSaleGoods());
		}
		if (StringHelper.isNull(po.getGoodsLogo())) {
			po.setGoodsLogo(old.getGoodsLogo());
		}
		if (po.getPrimaryConfigurationId() == null) {
			po.setPrimaryConfigurationId(old.getPrimaryConfigurationId());
		}
		if (po.getStaffId() == null) {
			po.setStaffId(old.getStaffId());
		}
		if (po.getModifyStaffId() == null) {
			po.setModifyStaffId(old.getModifyStaffId());
		}
		po.setModifyTime(new Timestamp(System.currentTimeMillis()));
		this.dao.merge(po);
		//级联更新购物车价格和类型
		String updateShoppingCart = "";
		if(po.getIsDiscountGoods()==120){
			updateShoppingCart = "update ShoppingCart set currentPrice =  "+po.getGoodsPreferentialPrice()
					+ ", originalPrice="+po.getGoodsOriginalPrice()+",currentPriceType="+po.getIsDiscountGoods()
					+" where goodsId = " + po.getId();
		}
		if(po.getIsDiscountGoods()==121){
			updateShoppingCart = "update ShoppingCart set currentPrice = " + po.getGoodsOriginalPrice()
					+ ", originalPrice="+po.getGoodsOriginalPrice()+",currentPriceType="+po.getIsDiscountGoods()
					+" where goodsId = " + po.getId();
		}
		commonDao.executeUpdateOrDelete(updateShoppingCart);
		updateShoppingCart = "update ShoppingCart set originalTotalPrice = (originalPrice*goodsCount)"
				+ ", totalPrice=(currentPrice*goodsCount) where goodsId = " + po.getId();
		commonDao.executeUpdateOrDelete(updateShoppingCart);
		if(po.getGoodsStatus()==119){
			String delShoppingCart = "delete from ShoppingCart where goodsId = " + po.getId();
			commonDao.executeUpdateOrDelete(delShoppingCart);
		}
		return 0;
	}

	public void del(String id) {
		Goods po = this.dao.findById(Integer.valueOf(id));
		this.dao.delete(po);
		String delShoppingCart = "delete from ShoppingCart where goodsId = " + po.getId();
		commonDao.executeUpdateOrDelete(delShoppingCart);
	}

	public int removeGoodsId(String id) {
		String delShoppingCart = "delete from ShoppingCart where goodsId = " + id;
		commonDao.executeUpdateOrDelete(delShoppingCart);
		return dao.removeGoodsId(Integer.valueOf(id));
	}

	/**
	 * @param brandname
	 * @return int
	 */
	public int getCount(String goodsCategoryId, String goodsParentId ,String goodsName, 
			String goodsBrandId, String goodsStatus, String primaryConfigurationId, 
			String isFreeLogistics, String isNewGoods, String isDiscountGoods, 
			String isHotSaleGoods, String staffId, String createStartTime, String createEndTime, 
			String createStaffId, String modifyStartTime, String modifyEndTime, String modifyStaffId) {
		String hql = "select count(*) from Goods where 1=1";
		if (!StringHelper.isNull(staffId)) {
			hql = hql + " and staffId = '" + staffId + "'";
		}
		if (!StringHelper.isNull(goodsName)) {
			hql = hql + " and goodsName like '%" + goodsName + "%'";
		}
		if (!StringHelper.isNull(goodsParentId)) {
			hql = hql + " and goodsParentId = " + goodsParentId;
		}
		if (!StringHelper.isNull(goodsCategoryId)) {
			hql = hql + " and goodsCategoryId = " + goodsCategoryId;
		}
		if (!StringHelper.isNull(goodsBrandId)) {
			hql = hql + " and goodsBrandId = " + goodsBrandId;
		}
		if (!StringHelper.isNull(goodsStatus)) {
			hql = hql + " and goodsStatus = " + goodsStatus;
		}
		if (!StringHelper.isNull(primaryConfigurationId)) {
			hql = hql + " and primaryConfigurationId = " + primaryConfigurationId;
		}
		if (!StringHelper.isNull(isFreeLogistics)) {
			hql = hql + " and isFreeLogistics = " + isFreeLogistics;
		}
		if (!StringHelper.isNull(isNewGoods)) {
			hql = hql + " and isNewGoods = " + isNewGoods;
		}
		if (!StringHelper.isNull(isDiscountGoods)) {
			hql = hql + " and isDiscountGoods = " + isDiscountGoods;
		}
		if (!StringHelper.isNull(isHotSaleGoods)) {
			hql = hql + " and isHotSaleGoods = " + isHotSaleGoods;
		}
		if (!StringHelper.isNull(createStartTime)) {
			hql = hql + " and createTime >='" + createStartTime + "'";
		}
		if (!StringHelper.isNull(createEndTime)) {
			hql = hql + " and createTime <='" + createEndTime + "'";
		}
		if (!StringHelper.isNull(createStaffId)) {
			hql = hql + " and createStaffId >='" + createStaffId + "'";
		}
		if (!StringHelper.isNull(modifyStartTime)) {
			hql = hql + " and modifyTime <='" + modifyStartTime + "'";
		}
		if (!StringHelper.isNull(modifyEndTime)) {
			hql = hql + " and modifyTime >='" + modifyEndTime + "'";
		}
		if (!StringHelper.isNull(modifyStaffId)) {
			hql = hql + " and modifyStaffId <='" + modifyStaffId + "'";
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
	public List<CommodityVO> listVO (String goodsCategoryId, String goodsParentId, String goodsName, 
			String goodsBrandId, String goodsStatus, String primaryConfigurationId, String isFreeLogistics,
			String isNewGoods, String isDiscountGoods, String isHotSaleGoods, String staffId,
			String createStartTime, String createEndTime, String createStaffId,
			String modifyStartTime, String modifyEndTime, String modifyStaffId, 
			int curPage, int pageSize, String orderField, String direction) {
		String hql = "from Goods where 1=1";
		if (!StringHelper.isNull(staffId)) {
			hql = hql + " and staffId = '" + staffId + "'";
		}
		if (!StringHelper.isNull(goodsName)) {
			hql = hql + " and goodsName like '%" + goodsName + "%'";
		}
		if (!StringHelper.isNull(goodsParentId)) {
			hql = hql + " and goodsParentId = " + goodsParentId;
		}
		if (!StringHelper.isNull(goodsCategoryId)) {
			hql = hql + " and goodsCategoryId = " + goodsCategoryId;
		}
		if (!StringHelper.isNull(goodsBrandId)) {
			hql = hql + " and goodsBrandId = " + goodsBrandId;
		}
		if (!StringHelper.isNull(goodsStatus)) {
			hql = hql + " and goodsStatus = " + goodsStatus;
		}
		if (!StringHelper.isNull(primaryConfigurationId)) {
			hql = hql + " and primaryConfigurationId = " + primaryConfigurationId;
		}
		if (!StringHelper.isNull(isFreeLogistics)) {
			hql = hql + " and isFreeLogistics = " + isFreeLogistics;
		}
		if (!StringHelper.isNull(isNewGoods)) {
			hql = hql + " and isNewGoods = " + isNewGoods;
		}
		if (!StringHelper.isNull(isDiscountGoods)) {
			hql = hql + " and isDiscountGoods = " + isDiscountGoods;
		}
		if (!StringHelper.isNull(isHotSaleGoods)) {
			hql = hql + " and isHotSaleGoods = " + isHotSaleGoods;
		}
		if (!StringHelper.isNull(createStartTime)) {
			hql = hql + " and createTime >='" + createStartTime + "'";
		}
		if (!StringHelper.isNull(createEndTime)) {
			hql = hql + " and createTime <='" + createEndTime + "'";
		}
		if (!StringHelper.isNull(createStaffId)) {
			hql = hql + " and createStaffId >='" + createStaffId + "'";
		}
		if (!StringHelper.isNull(modifyStartTime)) {
			hql = hql + " and modifyTime <='" + modifyStartTime + "'";
		}
		if (!StringHelper.isNull(modifyEndTime)) {
			hql = hql + " and modifyTime >='" + modifyEndTime + "'";
		}
		if (!StringHelper.isNull(modifyStaffId)) {
			hql = hql + " and modifyStaffId <='" + modifyStaffId + "'";
		}
		if (!StringHelper.isNull(orderField) && !StringHelper.isNull(direction)) {
			hql = hql + " order by " + orderField + " " + direction;
		} else {
			hql += " order by id desc";
		}
		List l = commonDao.getEntitiesByPropertiesWithHql(hql, curPage,
				pageSize);
		List<CommodityVO> resList = new ArrayList<CommodityVO>();
		for (int i = 0; i < l.size(); i++) {
			Goods po = (Goods) l.get(i);
			CommodityVO vo = new CommodityVO();
			if (po.getId() != null) {
				vo.setGoodsId(po.getId().toString());
			}
			if (po.getSortId() != null) {
				vo.setCategorySortId(po.getSortId().toString());
			}
			if (po.getGoodsParentId() != null) {
				vo.setGoodsParentId(po.getGoodsParentId().toString());
			}
			if (po.getGoodsCategoryId() != null) {
				vo.setGoodsCategoryId(po.getGoodsCategoryId().toString());
			}
			vo.setGoodsName(po.getGoodsName());
			vo.setGoodsDescription(po.getGoodsDescription());
			if (po.getGoodsPreferentialPrice()!= null) {
				vo.setGoodsPreferentialPrice(po.getGoodsPreferentialPrice().toString());
			}
			if (po.getGoodsOriginalPrice() != null) {
				vo.setGoodsOriginalPrice(po.getGoodsOriginalPrice().toString());
			}
			
			if (po.getGoodsBrandId()!= null) {
				vo.setGoodsBrandId(po.getGoodsBrandId().toString());
			}
			if (po.getGoodsWeight()!= null) {
				vo.setGoodsWeight(po.getGoodsWeight().toString());
			}
			if (po.getGoodsStock()!= null) {
				vo.setGoodsStock(po.getGoodsStock().toString());
			}
			if (po.getGoodsStatus()!= null) {
				vo.setGoodsStatus(po.getGoodsStatus().toString());
				Status s = statusService.findByid(po.getGoodsStatus().toString());
				vo.setGoodsStatusCode(s.getStatusCode());
			}
			if (po.getIsFreeLogistics()!= null) {
				vo.setIsFreeLogistics(po.getIsFreeLogistics().toString());
			}
			if (po.getIsNewGoods()!= null) {
				vo.setIsNewGoods(po.getIsNewGoods().toString());
			}
			if (po.getIsDiscountGoods()!= null) {
				vo.setIsDiscountGoods(po.getIsDiscountGoods().toString());
			}
			if (po.getIsHotSaleGoods()!= null) {
				vo.setIsHotSaleGoods(po.getIsHotSaleGoods().toString());
			}
			vo.setGoodsLogo(po.getGoodsLogo());
			vo.setGoodsBigLogo(po.getGoodsBigLogo());
			if (po.getPrimaryConfigurationId()!= null) {
				vo.setGoodsPrimaryConfigurationId(po.getPrimaryConfigurationId().toString());
			}
			vo.setGoodsStaffId(po.getStaffId());
			if (po.getCreateTime() != null) {
				vo.setGoodsCreateTime(StringHelper.getSystime(
						ViewConstant.PAGE_DATE_FORMAT_SEC, po.getCreateTime()
								.getTime()));
			}
			if (po.getCreateStaffId() != null) {
				vo.setGoodsCreateStaffId(po.getCreateStaffId().toString());
			}
			if (po.getModifyTime() != null) {
				vo.setGoodsModifyTime(StringHelper.getSystime(
						ViewConstant.PAGE_DATE_FORMAT_SEC, po.getModifyTime()
								.getTime()));
			}
			if (po.getModifyStaffId() != null) {
				vo.setGoodsModifyStaffId(po.getModifyStaffId().toString());
			}
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
	 * @return List<GetOrgListVO>
	 */
	public List<Goods> webList (String goodsCategoryId, String goodsParentId, String goodsName, 
			String goodsBrandId, String goodsStatus, String primaryConfigurationId, 
			String isFreeLogistics, String isNewGoods, String isDiscountGoods, String isHotSaleGoods,
			int curPage, int pageSize, String orderField, String direction) {
		String hql = "from Goods where 1=1";
		if (!StringHelper.isNull(goodsName)) {
			hql = hql + " and goodsName like '%" + goodsName + "%'";
		}
		if (!StringHelper.isNull(goodsParentId)) {
			hql = hql + " and goodsParentId = " + goodsParentId;
		}
		if (!StringHelper.isNull(goodsCategoryId)) {
			hql = hql + " and goodsCategoryId = " + goodsCategoryId;
		}
		if (!StringHelper.isNull(goodsBrandId)) {
			hql = hql + " and goodsBrandId = " + goodsBrandId;
		}
		if (!StringHelper.isNull(goodsStatus)) {
			hql = hql + " and goodsStatus = " + goodsStatus;
		}
		if (!StringHelper.isNull(primaryConfigurationId)) {
			hql = hql + " and primaryConfigurationId = " + primaryConfigurationId;
		}
		if (!StringHelper.isNull(isFreeLogistics)) {
			hql = hql + " and isFreeLogistics = " + isFreeLogistics;
		}
		if (!StringHelper.isNull(isNewGoods)) {
			hql = hql + " and isNewGoods = " + isNewGoods;
		}
		if (!StringHelper.isNull(isDiscountGoods)) {
			hql = hql + " and isDiscountGoods = " + isDiscountGoods;
		}
		if (!StringHelper.isNull(isHotSaleGoods)) {
			hql = hql + " and isHotSaleGoods = " + isHotSaleGoods;
		}
		if (!StringHelper.isNull(orderField) && !StringHelper.isNull(direction)) {
			hql = hql + " order by " + orderField + " " + direction;
		} else {
			hql += " order by id desc";
		}
		List l = commonDao.getEntitiesByPropertiesWithHql(hql, curPage,
				pageSize);
		List<Goods> resList = new ArrayList<Goods>();
		for (int i = 0; i < l.size(); i++) {
			Goods po = (Goods) l.get(i);
			resList.add(po);
		}
		return resList;
	}
	
	/**
	 * @return List<PrimaryConfiguration>
	 */
	public List<Goods> listAll() {
		return dao.findAll();
	}

}
