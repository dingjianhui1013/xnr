package com.xnradmin.core.service.business.order;

import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xnradmin.core.dao.CommonDAO;
import com.xnradmin.core.dao.CommonJDBCDAO;
import com.xnradmin.core.dao.business.order.FarmerOrderRecordDAO;
import com.xnradmin.core.service.StaffService;
import com.xnradmin.po.business.BusinessGoods;
import com.xnradmin.po.business.FarmerOrderRecord;
import com.xnradmin.po.wx.OutPlan;
import com.xnradmin.po.wx.connect.Farmer;
import com.xnradmin.vo.business.FarmerOrderVO;


/**
 * @autohr: xiang_liu
 * 
 */
@Service("FarmerOrderRecordService")
public class FarmerOrderRecordService {

	@Autowired
	private FarmerOrderRecordDAO dao;

	@Autowired
	private CommonDAO commonDao;

	@Autowired
	private CommonJDBCDAO jdbcDao;

	@Autowired
	private StaffService staffService;

	static Log log = LogFactory.getLog(BusinessOrderRecordService.class);




	/**
	 * 
	 * @param po
	 * @return int
	 */
	public void save(FarmerOrderRecord po) {
		commonDao.save(po);
	}

	public FarmerOrderRecord findByid(String id) {
		return dao.findById(Integer.valueOf(id));
	}

	public void del(String id) {
		FarmerOrderRecord po = this.dao.findById(Integer.valueOf(id));
		this.dao.delete(po);
	}

	
	public void delByOrderId(Long orderId){
		if(orderId!=null){
			//删除以前所有的
			List<FarmerOrderRecord> list= this.listByOrderId(orderId);
			this.dao.deleteBach(list);
		}
	}
	

	/**
	 * @return List<BusinessOrderRecord>
	 */
	public List<FarmerOrderRecord> listAll() {
		return dao.findAll();
	}

	
	public List<FarmerOrderRecord> listByOrderId(Long orderId){
		String hql = "from FarmerOrderRecord where orderRecordId = " + orderId;
		
		return  commonDao.getEntitiesByPropertiesWithHql(hql, 0, 0);
	}
	
	public List<FarmerOrderVO> listVO(FarmerOrderVO vo, int curPage,
			int pageSize, String orderField, String direction) {
		
		String hql = getHql(vo);
		
		List l = commonDao.getEntitiesByPropertiesWithHql(hql, curPage,
				pageSize);
		List<FarmerOrderVO> resList = new LinkedList<FarmerOrderVO>();
		for (int i = 0; i < l.size(); i++) {
			Object[] obj = (Object[]) l.get(i);
			OutPlan outPlan = (OutPlan) obj[0];
			Farmer farmer = (Farmer) obj[1];
			BusinessGoods goods = (BusinessGoods) obj[2];
			FarmerOrderRecord farmerOrder = (FarmerOrderRecord) obj[3];
			FarmerOrderVO farmerOrderVO = new FarmerOrderVO();
			farmerOrderVO.setOutPlan(outPlan);
			farmerOrderVO.setBusinessGoods(goods);
			farmerOrderVO.setFarmer(farmer);
			farmerOrderVO.setFarmerOrder(farmerOrder);
			resList.add(farmerOrderVO);
		}
		return resList;
	}
	
	
	private String getHql(FarmerOrderVO query) {
		StringBuffer hql = new StringBuffer();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		hql.append("FROM OutPlan a,Farmer b,BusinessGoods c,FarmerOrderRecord d,AllocationData e WHERE a.delFlage = 0 "
				+ " AND d.outPlanId = a.id	AND d.goodsId = c.id AND d.farmerUserId = b.userId	and e.id = d.orderRecordId ");
		if (query == null){
			return hql.append(" order by d.id desc").toString();
		}
		
		if(query.getFarmerOrder()!=null){
			if(query.getFarmerOrder().getOrderRecordId()!=null){
				hql.append(" and d.orderRecordId = ").append(query.getFarmerOrder().getOrderRecordId());
			}
		}
		
		if(query.getOutPlan()!=null){
			if(query.getOutPlan().getId()!=null){
				hql.append(" and a.id like '%").append(query.getOutPlan().getId()).append("%'");
			}	
			if(query.getOutPlan().getStartTime()!=null){
				String format = simpleDateFormat.format(query.getOutPlan().getStartTime());
				hql.append(" and a.startTime >= '").append(format).append("'");
			}
			if(query.getOutPlan().getEndTime()!=null){
				String format = simpleDateFormat.format(query.getOutPlan().getEndTime());
				hql.append(" and a.endTime <= '").append(format).append("'");
			}
			if(query.getOutPlan().getExamine()!=null){
				hql.append(" and a.examine = '").append(query.getOutPlan().getExamine()).append("'");
			}
		}
		
		if(query.getFarmer()!=null){
			if(query.getFarmer().getUserName()!=null&&!query.getFarmer().getUserName().equals("")){
				hql.append(" and b.userName like '%").append(query.getFarmer().getUserName()).append("%'");
			}	
		}
		
		if(query.getBusinessGoods()!=null){
			if(query.getBusinessGoods().getGoodsName()!=null&&!query.getBusinessGoods().getGoodsName().equals("")){
				hql.append(" and c.goodsName like '%").append(query.getBusinessGoods().getGoodsName()).append("%'");
			}
		}
		
		
		if(query.getAllocationData()!=null){
			if(query.getAllocationData().getId()!=null&&!query.getAllocationData().getId().equals("")){
				hql.append(" and e.id = ").append(query.getAllocationData().getId());
			}
		}
		
		hql.append(" order by d.id desc");
		return hql.toString();
	}

//	public long findByOutplanId(String deleteId) {
//		String hql = "select count(id) from FarmerOrderRecord where outPlanId = '"+deleteId+"'";
//		long count = commonDao.getNumberLongOfEntitiesWithHql(hql);
//		return count;
//	}
//	
}

