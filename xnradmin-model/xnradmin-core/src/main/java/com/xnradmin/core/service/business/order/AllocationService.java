package com.xnradmin.core.service.business.order;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cntinker.util.StringHelper;
import com.xnradmin.core.dao.CommonDAO;
import com.xnradmin.core.dao.CommonJDBCDAO;
import com.xnradmin.core.dao.business.order.AllocationListDAO;
import com.xnradmin.core.dao.business.order.BusinessOrderRecordDAO;
import com.xnradmin.core.dao.business.order.FarmerOrderRecordDAO;
import com.xnradmin.core.service.StaffService;
import com.xnradmin.po.business.AllocationData;
import com.xnradmin.po.business.BusinessGoods;
import com.xnradmin.po.business.BusinessOrderGoodsRelation;
import com.xnradmin.po.business.BusinessOrderRecord;
import com.xnradmin.po.business.FarmerOrderRecord;
import com.xnradmin.po.mall.commodity.GoodsAllocationShow;
import com.xnradmin.po.wx.OutPlan;
import com.xnradmin.po.wx.connect.Farmer;
import com.xnradmin.vo.business.FarmerOrderVO;
import com.xnradmin.vo.business.OutPlanVO;


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
		return dao.findById(Long.valueOf(id));
	}

	public void del(String id) {
		AllocationData po = this.dao.findById(Long.valueOf(id));
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

}

