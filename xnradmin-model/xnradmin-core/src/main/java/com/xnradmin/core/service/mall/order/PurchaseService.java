/**
 *2012-5-11 上午9:00:02
 */
package com.xnradmin.core.service.mall.order;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cntinker.util.StringHelper;
import com.xnradmin.core.service.StaffService;
import com.xnradmin.core.service.common.status.StatusService;
import com.xnradmin.core.service.mall.commodity.CategoryService;
import com.xnradmin.core.dao.CommonDAO;
import com.xnradmin.core.dao.mall.order.OrderGoodsRelationDAO;
import com.xnradmin.core.dao.mall.order.PurchaseDAO;
import com.xnradmin.constant.ViewConstant;
import com.xnradmin.po.common.status.Status;
import com.xnradmin.po.dishes.Dish;
import com.xnradmin.po.dishes.RawMaterial;
import com.xnradmin.po.mall.commodity.Category;
import com.xnradmin.po.mall.commodity.Goods;
import com.xnradmin.po.mall.order.OrderGoodsRelation;
import com.xnradmin.po.mall.order.OrderRecord;
import com.xnradmin.po.mall.order.Purchase;
import com.xnradmin.vo.mall.OrderVO;

/**
 * @autohr: xiang_liu
 * 
 */
@Service("PurchaseService")
public class PurchaseService {

	@Autowired
	private PurchaseDAO dao;

	@Autowired
	private OrderRecordService orderRecordService;

	@Autowired
	private CommonDAO commonDao;

	@Autowired
	private StatusService statusService;
	
	@Autowired
	private CategoryService categoryService;
	
	static Log log = LogFactory.getLog(PurchaseService.class);

	/**
	 * 
	 * @param po
	 * @return int
	 */
	public int save(String orderRecordId) {
		if (!StringHelper.isNull(orderRecordId)) {
			OrderRecord orderRecord = orderRecordService
					.findByid(orderRecordId);
			if (orderRecord != null && orderRecord.getId() != null) {
				removeOrderRecordId(orderRecordId);
				List<Purchase> poList = findSave(orderRecordId);
				if (poList != null && poList.size() > 0) {
					List<Category> categoryList = categoryService.listAll();
					List<Status> dishTypeList = statusService.find(Dish.class,
							"dishType");
					List<Status> materialTypeList = statusService.find(RawMaterial.class,
							"materialType");
					for (int i = 0; poList.size() > i; i++) {
						Purchase po = poList.get(i);
						po.setOrderRecordId(orderRecord.getId());
						po.setOrderNo(orderRecord.getOrderNo());
						po.setClientUserId(orderRecord.getClientUserId());
						po.setCreateTime(orderRecord.getCreateTime());
						for(int j = 0 ; materialTypeList.size() > j ; j++){
							Status status = materialTypeList.get(j);
							if(status.getId()==po.getRawMaterialTypeId()){
								po.setRawMaterialTypeName(status.getStatusCode());
							}
						}
						for(int j = 0 ; dishTypeList.size() > j ; j++){
							Status status = dishTypeList.get(j);
							if(status.getId()==po.getDishTypeId()){
								po.setDishTypeName(status.getStatusCode());
							}
						}
						for(int j = 0 ; categoryList.size() > j ; j++){
							Category category = categoryList.get(j);
							if(category.getId()==po.getGoodsCategoryId()){
								po.setGoodsCategoryName(category.getCategoryName());
							}
						}
						dao.save(po);
					}
				}
			}
			return 0;
		} else {
			return -1;
		}
	}

	public Purchase findByid(String id) {
		return dao.findById(Long.valueOf(id));
	}

	public List<Purchase> findByOrderRecordId(String id) {
		return dao.findByProperty("orderRecordId", Long.valueOf(id));
	}

	public int removePurchaseId(String id) {
		return dao.removePurchaseId(Long.valueOf(id));
	}

	public int removeOrderRecordId(String id) {
		return dao.removeOrderRecordId(Long.valueOf(id));
	}
	
	/**
	 * 
	 * @param firstletter
	 * @param curPage
	 * @param pageSize
	 * @param orderField
	 * @param direction
	 * @return List<Purchase>
	 */
	public List<Purchase> findSave(String orderRecordId) {
		String hql = "select b.id as goodsId, b.goodsName, b.goodsCategoryId, a.goodsCount, d.id as dishId, "
				+ " d.dishName, d.dishTypeId, c.dishCount, f.id as materialId, f.materialName, f.materialTypeId, "
				+ " e.weightId, g.statusCode as weightName , (a.goodsCount*c.dishCount*e.number) as number "
				+ " from OrderGoodsRelation a, Goods b, GoodsDishRelation c, Dish d, Collocation e, RawMaterial f, "
				+ " Status g where a.orderRecordId=" + orderRecordId + " "
				+ " and a.goodsId=b.id and b.id=c.goodsId and c.dishId=d.id and d.id=e.dishId and "
				+ " e.rawMaterialId=f.id and e.weightId=g.id order by b.id";
		List<Object[]> resList = commonDao.getEntitiesByPropertiesWithHql(hql,
				0, 0);
		List<Purchase> poList = new LinkedList();
		for (int i = 0; i < resList.size(); i++) {
			Object[] a = resList.get(i);
			Purchase po = new Purchase();
			if (a == null) {
				continue;
			}
			po.setGoodsId(a[0] == null ? 0 : Integer.parseInt(a[0].toString()));
			po.setGoodsName(a[1] == null ? "0" : a[1].toString());
			po.setGoodsCategoryId(a[2] == null ? 0 : Integer.parseInt(a[2].toString()));
			po.setGoodsCount(a[3] == null ? 0 : Integer.parseInt(a[3].toString()));
			po.setDishId(a[4] == null ? 0 : Integer.parseInt(a[4].toString()));
			po.setDishName(a[5] == null ? "0" : a[5].toString());
			po.setDishTypeId(a[6] == null ? 0 : Integer.parseInt(a[6].toString()));
			po.setDishCount(a[7] == null ? 0 : Integer.parseInt(a[7].toString()));
			po.setRawMaterialId(a[8] == null ? 0 : Integer.parseInt(a[8].toString()));
			po.setRawMaterialName(a[9] == null ? "0" : a[9].toString());
			po.setRawMaterialTypeId(a[10] == null ? 0 : Integer.parseInt(a[10].toString()));
			po.setWeightId(a[11] == null ? 0 : Integer.parseInt(a[11].toString()));
			po.setWeightName(a[12] == null ? "0" : a[12].toString());
			po.setNumber(a[13] == null ? 0 : Float.valueOf(a[13].toString()));
			poList.add(po);
		}
		return poList;
	}

}
