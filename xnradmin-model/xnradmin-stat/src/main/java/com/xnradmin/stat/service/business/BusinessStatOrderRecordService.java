/*
 * 2015-03-08 18:08:32
 */
package com.xnradmin.stat.service.business;
/*
 * yixiao
 */
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
import com.xnradmin.vo.StaffVO;
import com.xnradmin.vo.business.BusinessOrderVO;

@Service("BusinessStatOrderRecordService")
public class BusinessStatOrderRecordService {
	
	@Autowired
	private BusinessOrderRecordDAO dao;
	
	@Autowired
	private CommonJDBCDAO commonJDBCDAO;

	static Log log = LogFactory.getLog(BusinessStatOrderRecordService.class);
	/**
		 * 
		 * @param firstletter
		 * @param curPage
		 * @param pageSize
		 * @param orderField
		 * @param direction
		 * @return List<OrderVO>
		 * @throws InstantiationException
		 * @throws ClassNotFoundException
		 * @throws InvocationTargetException
		 * @throws IllegalArgumentException
		 * @throws IllegalAccessException
		 */
		public List orderRecordPurchase(BusinessOrderVO bovo, int curPage,
				int pageSize, String orderField, String direction)
				throws IllegalAccessException, IllegalArgumentException,
				InvocationTargetException, ClassNotFoundException,
				InstantiationException {
			String hql1 = "SELECT DATE_FORMAT(a.CREATE_TIME,'%Y-%m-%d') as createtime, "
					+"COUNT(DISTINCT(a.CLIENT_USER_ID)) as alone, " 
					+"ROUND(SUM(a.ORIGINAL_PRICE),2) as allprice, "
					+"ROUND(SUM(a.ORIGINAL_PRICE) /COUNT(DISTINCT(a.CLIENT_USER_ID)),2) as averageprice,"
					+"COUNT(a.id) AS single,"
					+"ROUND(COUNT(a.id) /COUNT(DISTINCT(a.staff_id))) AS averagesingle"
					+" FROM business_order_record AS a,common_staff AS b"
					+ " WHERE a.STAFF_ID=b.id";
			if(bovo !=null && bovo.getBusinessOrderRecord()!=null){
				if(!StringHelper.isNull(bovo.getBusinessOrderRecord().getClientUserName())){
					hql1 = hql1 
						+" and a.CLIENT_USER_NAME like '%"
						+bovo.getBusinessOrderRecord().getClientUserName()+"%'";
				}
				if(bovo.getBusinessOrderRecord().getClientUserMobile()!=null){
					hql1 = hql1
						+" and a.CLIENT_USER_MOBILE like '%"
						+bovo.getBusinessOrderRecord().getClientUserMobile()+"%'";
				}
				if(!StringHelper.isNull(bovo.getCreateStartTime())){
					hql1 = hql1 
						+" and DATE_FORMAT(a.CREATE_TIME,'%Y-%m-%d')>= '"
						+bovo.getCreateStartTime() +"'";
				}
				if(!StringHelper.isNull(bovo.getCreateEndTime())){
					hql1 = hql1
						+" and DATE_FORMAT(a.CREATE_TIME,'%Y-%m-%d')<= '"
						+bovo.getCreateEndTime() +"'";
				}
				if(!StringHelper.isNull(bovo.getStaffCreateStartTime())){
					hql1 = hql1
						+" and DATE_FORMAT(b.CREATE_TIME,'%Y-%m-%d')>= '"
						+bovo.getStaffCreateStartTime() +"'";
				}
				if(!StringHelper.isNull(bovo.getStaffCreateEndTime())){
					hql1 = hql1
						+" and DATE_FORMAT(b.CREATE_TIME,'%Y-%m-%d')<= '"
						+bovo.getStaffCreateEndTime() +"'";
				}
			}
			hql1 = hql1 
					+" group by DATE_FORMAT(a.CREATE_TIME,'%Y-%m-%d')  order by a.id desc";
			List list = commonJDBCDAO.findBySQLListMap(hql1, 0, 0);
			return list;
		}
		/**
		 * @return List<BusinessOrderRecord>
		 */
		public List<BusinessOrderRecord> listAll() {
			return dao.findAll();
		}

}
