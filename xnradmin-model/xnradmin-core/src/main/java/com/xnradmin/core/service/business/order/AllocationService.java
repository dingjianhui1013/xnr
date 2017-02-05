package com.xnradmin.core.service.business.order;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cntinker.util.StringHelper;
import com.xnradmin.core.dao.CommonDAO;
import com.xnradmin.core.dao.business.order.AllocationListDAO;
import com.xnradmin.core.service.StaffService;
import com.xnradmin.po.CommonStaff;
import com.xnradmin.po.business.AllocationData;
import com.xnradmin.po.business.BusinessCategory;
import com.xnradmin.po.business.BusinessGoods;
import com.xnradmin.po.business.BusinessOrderGoodsRelation;
import com.xnradmin.po.business.BusinessOrderRecord;
import com.xnradmin.vo.business.AllocationVO;
import com.xnradmin.vo.business.BusinessOrderVO;


/**
 * @autohr: xiang_liu
 * 
 */
@Service("AllocationService")
public class AllocationService {

	@Autowired
	private AllocationListDAO dao;

	@Autowired
	private CommonDAO commonDao;

	@Autowired
	private StaffService staffService;

	static Log log = LogFactory.getLog(BusinessOrderRecordService.class);




	/**
	 * 
	 * @param po
	 * @return int
	 */
	public void save(AllocationData po) {
		commonDao.save(po);
	}

	public AllocationData findByid(String id) {
		return dao.findById(Integer.valueOf(id));
	}

	public void del(String id) {
		AllocationData po = this.dao.findById(Integer.valueOf(id));
		this.dao.delete(po);
	}

	
	public void delByOrderId(Long orderId){
		if(orderId!=null){
			//删除以前所有的
			List<AllocationData> list= this.listByOrderId(orderId);
			this.dao.deleteBach(list);
		}
	}
	

	/**
	 * @return List<BusinessOrderRecord>
	 */
	public List<AllocationData> listAll() {
		return dao.findAll();
	}

	
	public List<AllocationData> listByOrderId(Long orderId){
		String hql = "from AllocationData where orderRecordId = " + orderId;
		
		return  commonDao.getEntitiesByPropertiesWithHql(hql, 0, 0);
	}

	/**
	 * 新版info页调用数据
	 * 
	 * @param vo
	 * @param curPage
	 * @param pageSize
	 * @param orderField
	 * @param direction
	 * @return List<BusinessOrderVO>
	 */
	public List<AllocationVO> listVO(AllocationVO vo, int curPage,
			int pageSize, String orderField, String direction) {
		String hql = getHql(vo);

		List<Object[]> res = this.commonDao.getEntitiesByPropertiesWithHql(hql,
				curPage, pageSize);
		List<AllocationVO> returnList = new ArrayList<AllocationVO>();
		if(res!=null&&res.size()>0){
			for (Object[] e : res) {
				AllocationData allocationData = (AllocationData) e[0];
				CommonStaff staff = (CommonStaff) e[1];
				AllocationVO v = new AllocationVO();
				v.setAllocationData(allocationData);
				v.setStaff(staff);
				returnList.add(v);
			}			
		}else{
			returnList=null;
		}

		return returnList;
	}
	
	private String getHql(AllocationVO query) {
		StringBuffer hql = new StringBuffer();
		hql.append("from AllocationData a, CommonStaff b ");
		hql.append(" where a.allocationUser=b.id ");

		if (query != null) {
			// 分配日期
			if (!StringHelper.isNull(query.getCreateStartTime())
					&& !StringHelper.isNull(query.getCreateEndTime())) {
				hql.append(" and a.startTimeCondition>=DATE_FORMAT('");
				hql.append(query.getCreateStartTime());
				hql.append("','%Y-%m-%d %H:%i:%s')");
				hql.append(" and a.endTimeCondition<=DATE_FORMAT('");
				hql.append(query.getCreateEndTime());
				hql.append("','%Y-%m-%d %H:%i:%s')");
			}

			// 指定分配人
			if (query.getStaff() != null) {
				if (query.getStaff().getStaffName() != null) {
					hql.append(" and b.staffName like '%").append(query.getStaff().getStaffName()).append("%'");
				}
			}

			// group by
			if (!StringHelper.isNull(query.getGroupBy())) {
				hql.append(" group by ").append(query.getGroupBy());
			}

			// order by
			if (!StringHelper.isNull(query.getOrderBy())
					&& !StringHelper.isNull(query.getOrderByField())) {
				hql.append(" order by ").append(query.getOrderBy()).append(" ")
						.append(query.getOrderByField());
			}
		}

		return hql.toString();
	}
	
	public Integer getCount(String select, AllocationVO vo) {
		String hql = select + getHql(vo);

		return commonDao.getNumberOfEntitiesWithHql(hql);
	}
}

