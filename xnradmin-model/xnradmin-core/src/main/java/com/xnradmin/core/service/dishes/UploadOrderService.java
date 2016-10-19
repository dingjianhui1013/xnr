/**
 *2012-7-24 下午5:09:02
 */
package com.xnradmin.core.service.dishes;

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
import com.xnradmin.vo.dishes.DishesVO;
/**
 * @author: liuxiang
 * 
 */
@Service("UploadOrderService")
public class UploadOrderService {

	static Log log = LogFactory.getLog(UploadOrderService.class);

	@Autowired
	private UploadOrderDAO dao;
	
	@Autowired
	private StatusService statusService;

	@Autowired
    private CommonDAO commonDao;
	
	public UploadOrder findByid(String id) {
		return dao.findById(new Integer(id));
	}
	
	/**
	 *	订单入表
	 */
	public void excelIntoDB(String sourceFile) throws Exception {
		List<ArrayList<String>> dataLst = new PoiExcelHelper().read(sourceFile,
				0);
		System.out.println("文件总行数：" + dataLst.size());
		for (int i = 0; i < dataLst.size(); i++) {
			UploadOrder uploadOrder = null;
			if(i>0){
				uploadOrder = new UploadOrder();
				List<String> l = dataLst.get(i);
				if(!StringHelper.isNull(l.get(1))){
					uploadOrder.setOrderNumber(l.get(1));
				}
				if(!StringHelper.isNull(l.get(2)) && l.get(2).length()>10){
					uploadOrder.setOrderTime(Timestamp.valueOf(l.get(2)));
				}
				if(!StringHelper.isNull(l.get(3))){
					uploadOrder.setDishName(l.get(3));
				}
				if(!StringHelper.isNull(l.get(4))){
					uploadOrder.setProperty(l.get(4));
				}
				if(!StringHelper.isNull(l.get(5))){
					uploadOrder.setUnitPrice(Float.valueOf(l.get(5)));
				}
				if(!StringHelper.isNull(l.get(6))){
					uploadOrder.setDishCount(Integer.parseInt(l.get(6)));
				}
				if(!StringHelper.isNull(l.get(7))){
					uploadOrder.setTotalPrice(Float.valueOf(l.get(7)));
				}
				if(!StringHelper.isNull(l.get(8))){
					List<Status> statusServiceList = statusService.findByStatusCode(UploadOrder.class,l.get(8));
					if(statusServiceList!=null && statusServiceList.size()>0){
						Status st = statusServiceList.get(0);
						uploadOrder.setPayStatusId(st.getId());
					}
				}
				if(!StringHelper.isNull(l.get(9))){
					List<Status> statusServiceList = statusService.findByStatusCode(UploadOrder.class,l.get(9));
					if(statusServiceList!=null && statusServiceList.size()>0){
						Status st = statusServiceList.get(0);
						uploadOrder.setDispatchStatusId(st.getId());
					}
				}
				if(!StringHelper.isNull(l.get(10))){
					List<Status> statusServiceList = statusService.findByStatusCode(UploadOrder.class,l.get(10));
					if(statusServiceList!=null && statusServiceList.size()>0){
						Status st = statusServiceList.get(0);
						uploadOrder.setOrderStatusId(st.getId());
					}
				}
				if(!StringHelper.isNull(l.get(11))){
					uploadOrder.setCustomer(l.get(11));
				}
				if(!StringHelper.isNull(l.get(12))){
					uploadOrder.setMobile(l.get(12));
				}
				if(!StringHelper.isNull(l.get(13))){
					uploadOrder.setAddress(l.get(13));
				}
				if(!StringHelper.isNull(l.get(14))){
					uploadOrder.setCode(l.get(14));
				}
			}
			if(uploadOrder!=null){
				dao.save(uploadOrder);
			}
		}
	}
	
	private String path;

	public String getPath() {
		return path;
	}

	@Value(value="${upload.path}")
	public void setPath(String path) {
		this.path = path;
	}
	
	
	
	
	/**
	 * @return count
	 */
	public int getCount(String id, String orderNumber, String orderStartTime,
			String orderEndTime, String dishName, String property, String unitPrice, String dishCount,
			String totalPrice, String payStatusId, String dispatchStatusId, String orderStatusId,
			String customer, String mobile, String address, String code, String createStaffId,
			String createStartTime, String createEndTime) {
		String hql = "select count(*) from UploadOrder a where 1=1";
		if (!StringHelper.isNull(id)) {
			hql = hql + " and a.id =" + id;
		}
		if (!StringHelper.isNull(orderNumber)) {
			hql = hql + " and a.orderNumber ='" + orderNumber +"'";
		}
		if (!StringHelper.isNull(dishName)) {
			hql = hql + " and a.dishName ='" + dishName + "'";
		}
		if (!StringHelper.isNull(property)) {
			hql = hql + " and a.property = '" + property + "'";
		}
		if (!StringHelper.isNull(orderStartTime)
				&& !StringHelper.isNull(orderEndTime)) {
			hql = hql + " and a.orderTime > '" + orderStartTime
					+ "' and a.orderTime < '" + orderEndTime + "'";
		}
		if (!StringHelper.isNull(unitPrice)) {
			hql = hql + " and a.unitPrice = '" + unitPrice + "'";
		}
		if (!StringHelper.isNull(dishCount)) {
			hql = hql + " and a.dishCount = " + dishCount;
		}
		if (!StringHelper.isNull(totalPrice)) {
			hql = hql + " and a.money = " + totalPrice;
		}
		if (!StringHelper.isNull(payStatusId)) {
			hql = hql + " and a.payStatusId = " + payStatusId;
		}
		if (!StringHelper.isNull(dispatchStatusId)) {
			hql = hql + " and a.productBusinessType = " + dispatchStatusId;
		}
		if (!StringHelper.isNull(orderStatusId)) {
			hql = hql + " and a.orderStatusId = " + orderStatusId;
		}
		if (!StringHelper.isNull(customer)) {
			hql = hql + " and a.customer = '" + customer + "'";
		}
		if (!StringHelper.isNull(mobile)) {
			hql = hql + " and a.mobile = '" + mobile + "'";
		}
		if (!StringHelper.isNull(address)) {
			hql = hql + " and a.address like '%" + address + "%'";
		}
		if (!StringHelper.isNull(code)) {
			hql = hql + " and a.code = " + code;
		}
		if (!StringHelper.isNull(createStaffId)) {
			hql = hql + " and a.createStaffId = " + createStaffId;
		}
		if (!StringHelper.isNull(createStartTime)
				&& !StringHelper.isNull(createEndTime)) {
			hql = hql + " and a.createTime > '" + createStartTime
					+ "' and a.createTime < '" + createEndTime + "'";
		}
		System.out.println("hql::" + hql);
		return this.commonDao.getNumberOfEntitiesWithHql(hql);
		
	}
	
	
	/**
	 * @param curPage
	 * @param pageSize
	 * @param orderField
	 * @param direction
	 * @return List<GetOrgListVO>
	 */
	public List<DishesVO> listVO(String id, String orderNumber, String orderStartTime,
			String orderEndTime, String dishName, String property, String unitPrice, String dishCount,
			String totalPrice, String payStatusId, String dispatchStatusId, String orderStatusId,
			String customer, String mobile, String address, String code, String createStaffId,
			String createStartTime, String createEndTime, int curPage, int pageSize,String orderField, 
			String direction) {
		String hql = "from UploadOrder a where 1=1";
		if (!StringHelper.isNull(id)) {
			hql = hql + " and a.id =" + id;
		}
		if (!StringHelper.isNull(orderNumber)) {
			hql = hql + " and a.orderNumber ='" + orderNumber +"'";
		}
		if (!StringHelper.isNull(dishName)) {
			hql = hql + " and a.dishName ='" + dishName + "'";
		}
		if (!StringHelper.isNull(property)) {
			hql = hql + " and a.property = '" + property + "'";
		}
		if (!StringHelper.isNull(orderStartTime)
				&& !StringHelper.isNull(orderEndTime)) {
			hql = hql + " and a.orderTime > '" + orderStartTime
					+ "' and a.orderTime < '" + orderEndTime + "'";
		}
		if (!StringHelper.isNull(unitPrice)) {
			hql = hql + " and a.unitPrice = '" + unitPrice + "'";
		}
		if (!StringHelper.isNull(dishCount)) {
			hql = hql + " and a.dishCount = " + dishCount;
		}
		if (!StringHelper.isNull(totalPrice)) {
			hql = hql + " and a.money = " + totalPrice;
		}
		if (!StringHelper.isNull(payStatusId)) {
			hql = hql + " and a.payStatusId = " + payStatusId;
		}
		if (!StringHelper.isNull(dispatchStatusId)) {
			hql = hql + " and a.productBusinessType = " + dispatchStatusId;
		}
		if (!StringHelper.isNull(orderStatusId)) {
			hql = hql + " and a.orderStatusId = " + orderStatusId;
		}
		if (!StringHelper.isNull(customer)) {
			hql = hql + " and a.customer = '" + customer + "'";
		}
		if (!StringHelper.isNull(mobile)) {
			hql = hql + " and a.mobile = '" + mobile + "'";
		}
		if (!StringHelper.isNull(address)) {
			hql = hql + " and a.address like '%" + address + "%'";
		}
		if (!StringHelper.isNull(code)) {
			hql = hql + " and a.code = " + code;
		}
		if (!StringHelper.isNull(createStaffId)) {
			hql = hql + " and a.createStaffId = " + createStaffId;
		}
		if (!StringHelper.isNull(createStartTime)
				&& !StringHelper.isNull(createEndTime)) {
			hql = hql + " and a.createTime > '" + createStartTime
					+ "' and a.createTime < '" + createEndTime + "'";
		}
		if (!StringHelper.isNull(orderField) && !StringHelper.isNull(direction)) {
			hql = hql + " order by " + orderField + " " + direction;
		} else {
			hql += " order by a.id desc";
		}
		System.out.println("hql::" + hql);
		List l = commonDao.getEntitiesByPropertiesWithHql(hql, curPage,
				pageSize);
		List<DishesVO> resList = new ArrayList<DishesVO>();
		for (int i = 0; i < l.size(); i++) {
			UploadOrder po = (UploadOrder) l.get(i);
			DishesVO vo = new DishesVO();
			vo.setUploadOrderId(po.getId().toString());
			if (po.getOrderNumber() != null) {
				vo.setOrderNumber(po.getOrderNumber().toString());
			}
			if (po.getOrderTime() != null) {
				vo.setOrderTime(StringHelper.getSystime(
						ViewConstant.PAGE_DATE_FORMAT_SEC, po.getOrderTime()
								.getTime()));
			}
			vo.setDishName(po.getDishName());
			vo.setProperty(po.getProperty());
			if (po.getUnitPrice() != null) {
				vo.setUnitPrice(po.getUnitPrice().toString());
			}
			if (po.getDishCount() != null) {
				vo.setDishCount(po.getDishCount().toString());
			}
			if (po.getTotalPrice() != null) {
				vo.setTotalPrice(po.getTotalPrice().toString());
			}
			if (po.getOrderStatusId() != null) {
				vo.setOrderStatusId(po.getOrderStatusId().toString());
			}
			if (po.getPayStatusId() != null) {
				vo.setPayStatusId(po.getPayStatusId().toString());
			}
			if (po.getDispatchStatusId() != null) {
				vo.setDispatchStatusId(po.getDispatchStatusId().toString());
			}
			vo.setCustomer(po.getCustomer());
			vo.setMobile(po.getMobile());
			vo.setAddress(po.getAddress());
			vo.setCode(po.getCode());
			if (po.getCreateTime() != null) {
				vo.setCreateTime(StringHelper.getSystime(
						ViewConstant.PAGE_DATE_FORMAT_SEC, po
								.getCreateTime().getTime()));
			}
			if (po.getCreateStaffId() != null) {
				vo.setCreateStaffId(po.getCreateStaffId().toString());
			}
			resList.add(vo);
		}

		return resList;
	}
	
	
	/**
	 * 汇总菜品总量
	 * 
	 * @param curPage
	 * @param pageSize
	 * @param orderField
	 * @param direction
	 * @return List<Object[]>
	 */
	public List<Object[]> getDishCount(String orderNumber, String orderStartTime,
			String orderEndTime, String dishName, String property, String unitPrice, 
			String dishCount, String totalPrice, String payStatusId, String dispatchStatusId, 
			String orderStatusId, String customer, String mobile, String address, 
			String code, String createStaffId, String createStartTime, String createEndTime,
			int curPage, int pageSize,String orderField, String direction) {
		String hql = "select dishName, sum(dishCount) from UploadOrder where 1=1";
		if (!StringHelper.isNull(orderNumber)) {
			hql = hql + " and orderNumber ='" + orderNumber +"'";
		}
		if (!StringHelper.isNull(orderStartTime)
				&& !StringHelper.isNull(orderEndTime)) {
			hql = hql + " and orderTime > '" + orderStartTime
					+ "' and orderTime < '" + orderEndTime + "'";
		}
		if (!StringHelper.isNull(dishName)) {
			hql = hql + " and dishName ='" + dishName + "'";
		}
		if (!StringHelper.isNull(property)) {
			hql = hql + " and property = '" + property + "'";
		}
		if (!StringHelper.isNull(unitPrice)) {
			hql = hql + " and unitPrice = " + unitPrice;
		}
		if (!StringHelper.isNull(dishCount)) {
			hql = hql + " and dishCount = " + dishCount;
		}
		if (!StringHelper.isNull(totalPrice)) {
			hql = hql + " and totalPrice = " + totalPrice;
		}
		if (!StringHelper.isNull(payStatusId)) {
			hql = hql + " and payStatusId = " + payStatusId;
		}
		if (!StringHelper.isNull(dispatchStatusId)) {
			hql = hql + " and dispatchStatusId = " + dispatchStatusId;
		}
		if (!StringHelper.isNull(orderStatusId)) {
			hql = hql + " and orderStatusId = " + orderStatusId;
		}
		if (!StringHelper.isNull(customer)) {
			hql = hql + " and customer = '" + customer + "'";
		}
		if (!StringHelper.isNull(mobile)) {
			hql = hql + " and mobile = '" + mobile + "'";
		}
		if (!StringHelper.isNull(address)) {
			hql = hql + " and address like '%" + address + "%'";
		}
		if (!StringHelper.isNull(code)) {
			hql = hql + " and code = '" + code + "'";
		}
		if (!StringHelper.isNull(createStaffId)) {
			hql = hql + " and createStaffId = " + createStaffId;
		}
		if (!StringHelper.isNull(createStartTime)
				&& !StringHelper.isNull(createEndTime)) {
			hql = hql + " and createTime > '" + createStartTime
					+ "' and createTime < '" + createEndTime + "'";
		}
		hql = hql + " group by dishName";
		if (!StringHelper.isNull(orderField)
				&& !StringHelper.isNull(direction)) {
			hql = hql + " order by " + orderField + " " + direction;
		} else {
			hql += " order by id desc";
		}
		System.out.println(hql);
		List<Object[]> l = commonDao.getEntitiesByPropertiesWithHql(hql,
				curPage, pageSize);
		return l;
	}
	
	
	/**
	 * 汇总原料总量
	 * 
	 * @param curPage
	 * @param pageSize
	 * @param orderField
	 * @param direction
	 * @return List<Object[]>
	 */
	public List<Object[]> getRawMaterialCount(String orderStartTime, String orderEndTime, 
			String dishName, String unitPrice, String dishCount,
			String totalPrice, String payStatusId, String dispatchStatusId, String orderStatusId,
			String customer, String mobile, String address, String code, String createStaffId,
			String createStartTime, String createEndTime,String materialName,
			int curPage, int pageSize,String orderField, String direction) {
		String hql = "select b.materialName,d.statusCode,sum(c.number*e.dishCount) from Dish a ,RawMaterial b ,"
				+ " Collocation c, Status d, UploadOrder e where c.dishId=a.id "
				+ " and c.rawMaterialId=b.id and c.weightId=d.id and e.dishName=a.dishName ";

		if (!StringHelper.isNull(orderStartTime)
				&& !StringHelper.isNull(orderEndTime)) {
			hql = hql + " and e.orderTime > '" + orderStartTime
					+ "' and e.orderTime < '" + orderEndTime + "'";
		}
		if (!StringHelper.isNull(dishName)) {
			hql = hql + " and e.dishName ='" + dishName + "'";
		}
		if (!StringHelper.isNull(unitPrice)) {
			hql = hql + " and e.unitPrice = " + unitPrice;
		}
		if (!StringHelper.isNull(dishCount)) {
			hql = hql + " and e.dishCount = " + dishCount;
		}
		if (!StringHelper.isNull(totalPrice)) {
			hql = hql + " and e.totalPrice = " + totalPrice;
		}
		if (!StringHelper.isNull(payStatusId)) {
			hql = hql + " and e.payStatusId = " + payStatusId;
		}
		if (!StringHelper.isNull(dispatchStatusId)) {
			hql = hql + " and e.dispatchStatusId = " + dispatchStatusId;
		}
		if (!StringHelper.isNull(orderStatusId)) {
			hql = hql + " and e.orderStatusId = " + orderStatusId;
		}
		if (!StringHelper.isNull(customer)) {
			hql = hql + " and e.customer = '" + customer + "'";
		}
		if (!StringHelper.isNull(mobile)) {
			hql = hql + " and e.mobile = '" + mobile + "'";
		}
		if (!StringHelper.isNull(address)) {
			hql = hql + " and e.address like '%" + address + "%'";
		}
		if (!StringHelper.isNull(code)) {
			hql = hql + " and e.code = '" + code + "'";
		}
		if (!StringHelper.isNull(createStaffId)) {
			hql = hql + " and e.createStaffId = " + createStaffId;
		}
		if (!StringHelper.isNull(createStartTime)
				&& !StringHelper.isNull(createEndTime)) {
			hql = hql + " and e.createTime > '" + createStartTime
					+ "' and e.createTime < '" + createEndTime + "'";
		}
		if (!StringHelper.isNull(materialName)) {
			hql = hql + " and b.materialName = '" + materialName + "'";
		}
		hql = hql + " group by b.materialName, d.statusCode";
		if (!StringHelper.isNull(orderField)
				&& !StringHelper.isNull(direction)) {
			hql = hql + " order by " + orderField + " " + direction;
		} else {
			hql += " order by e.id desc";
		}
		System.out.println(hql);
		List<Object[]> l = commonDao.getEntitiesByPropertiesWithHql(hql,
				curPage, pageSize);
		return l;
	}
	
	/**
	 * @return List<PayUpmp>
	 */
	public List<UploadOrder> listAll() {
		return dao.findAll();
	}

}
