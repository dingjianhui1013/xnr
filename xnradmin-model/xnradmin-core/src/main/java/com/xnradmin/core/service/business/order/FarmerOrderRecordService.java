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
import com.xnradmin.core.dao.business.order.BusinessOrderRecordDAO;
import com.xnradmin.core.service.StaffService;
import com.xnradmin.po.business.BusinessGoods;
import com.xnradmin.po.business.BusinessOrderRecord;
import com.xnradmin.po.business.FarmerOrderRecord;
import com.xnradmin.po.wx.OutPlan;
import com.xnradmin.po.wx.connect.Farmer;
import com.xnradmin.vo.business.FarmerOrderVO;
import com.xnradmin.vo.business.OutPlanVO;


/**
 * @autohr: xiang_liu
 * 
 */
@Service("FarmerOrderRecordService")
public class FarmerOrderRecordService {

	@Autowired
	private BusinessOrderRecordDAO dao;

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

	public BusinessOrderRecord findByid(String id) {
		return dao.findById(Long.valueOf(id));
	}



	/**
	 * @param po
	 * @return int
	 */
	public int modify(BusinessOrderRecord po) {
		BusinessOrderRecord old = new BusinessOrderRecord();
		old = findByid(po.getId().toString());
		if (po.getClientUserId() == null) {
			po.setClientUserId(old.getClientUserId());
		}
		if (StringHelper.isNull(po.getClientUserName())) {
			po.setClientUserName(old.getClientUserName());
		}
		if (StringHelper.isNull(po.getClientUserMobile())) {
			po.setClientUserMobile(old.getClientUserMobile());
		}
		if (StringHelper.isNull(po.getClientUserEmail())) {
			po.setClientUserEmail(old.getClientUserEmail());
		}
		if (StringHelper.isNull(po.getClientUserSex())) {
			po.setClientUserSex(old.getClientUserSex());
		}
		if (po.getClientUserType() != null) {
			po.setClientUserType(old.getClientUserType());
		}
		if (StringHelper.isNull(po.getClientUserTypeName())) {
			po.setClientUserTypeName(old.getClientUserTypeName());
		}
		if (StringHelper.isNull(po.getWxOpenId())) {
			po.setWxOpenId(old.getWxOpenId());
		}
		if (StringHelper.isNull(po.getStaffId())) {
			po.setStaffId(old.getStaffId());
		}
		if (StringHelper.isNull(po.getUserRealMobile())) {
			po.setUserRealMobile(old.getUserRealMobile());
		}
		if (StringHelper.isNull(po.getUserRealName())) {
			po.setUserRealName(old.getUserRealName());
		}
		if (StringHelper.isNull(po.getUserRealPlane())) {
			po.setUserRealPlane(old.getUserRealPlane());
		}
		if (StringHelper.isNull(po.getUserRealDescription())) {
			po.setUserRealDescription(old.getUserRealDescription());
		}
		if (po.getCountryId() != null) {
			po.setCountryId(old.getCountryId());
		}
		if (StringHelper.isNull(po.getCountryName())) {
			po.setCountryName(old.getCountryName());
		}
		if (po.getProvinceId() != null) {
			po.setProvinceId(old.getProvinceId());
		}
		if (StringHelper.isNull(po.getProvinceName())) {
			po.setProvinceName(old.getProvinceName());
		}
		if (po.getCityId() != null) {
			po.setCityId(old.getCityId());
		}
		if (StringHelper.isNull(po.getCityName())) {
			po.setCityName(old.getCityName());
		}
		if (po.getAreaId() != null) {
			po.setAreaId(old.getAreaId());
		}
		if (StringHelper.isNull(po.getAreaName())) {
			po.setAreaName(old.getAreaName());
		}
		if (StringHelper.isNull(po.getUserRealAddress())) {
			po.setUserRealAddress(old.getUserRealAddress());
		}
		if (StringHelper.isNull(po.getUserRealPostcode())) {
			po.setUserRealPostcode(old.getUserRealPostcode());
		}
		if (po.getUserRealTime() == null) {
			po.setUserRealTime(old.getUserRealTime());
		}
		if (po.getPaymentType() == null) {
			po.setPaymentType(old.getPaymentType());
		}
		if (StringHelper.isNull(po.getPaymentTypeName())) {
			po.setPaymentTypeName(old.getPaymentTypeName());
		}
		if (po.getPaymentStatus() == null) {
			po.setPaymentStatus(old.getPaymentStatus());
		}
		if (StringHelper.isNull(po.getPaymentStatusName())) {
			po.setPaymentStatusName(old.getPaymentStatusName());
		}
		if (po.getPaymentProvider() == null) {
			po.setPaymentProvider(old.getPaymentProvider());
		}
		if (StringHelper.isNull(po.getPaymentProviderName())) {
			po.setPaymentProviderName(old.getPaymentProviderName());
		}
		if (po.getPaymentTime() == null) {
			po.setPaymentTime(old.getPaymentTime());
		}
		if (po.getOperateTime() == null) {
			po.setOperateTime(old.getOperateTime());
		}
		if (po.getOperateStatus() == null) {
			po.setOperateStatus(old.getOperateStatus());
		}
		if (StringHelper.isNull(po.getOperateStatusName())) {
			po.setOperateStatusName(old.getOperateStatusName());
		}
		if (po.getCreateTime() == null) {
			po.setCreateTime(old.getCreateTime());
		}
		if (po.getOriginalPrice() == null) {
			po.setOriginalPrice(old.getOriginalPrice());
		}
		if (po.getTotalPrice() == null) {
			po.setTotalPrice(old.getTotalPrice());
		}
		if (po.getLogisticsCompanyId() == null) {
			po.setLogisticsCompanyId(old.getLogisticsCompanyId());
		}
		if (po.getLogisticsCompanyName() == null) {
			po.setLogisticsCompanyName(old.getLogisticsCompanyName());
		}
		if (po.getDeliveryStaffId() == null) {
			po.setDeliveryStaffId(old.getDeliveryStaffId());
		}
		if (StringHelper.isNull(po.getDeliveryStaffName())) {
			po.setDeliveryStaffName(old.getDeliveryStaffName());
		}
		if (StringHelper.isNull(po.getDeliveryStaffMobile())) {
			po.setDeliveryStaffMobile(old.getDeliveryStaffMobile());
		}
		if (po.getDeliveryStartTime() == null) {
			po.setDeliveryStartTime(old.getDeliveryStartTime());
		}
		if (po.getDeliveryEndTime() == null) {
			po.setDeliveryEndTime(old.getDeliveryEndTime());
		}
		if (po.getDeliveryStatus() == null) {
			po.setDeliveryStatus(old.getDeliveryStatus());
		}
		if (StringHelper.isNull(po.getDeliveryStatusName())) {
			po.setDeliveryStatusName(old.getDeliveryStatusName());
		}
		if (po.getSourceName() == null) {
			po.setSourceName(old.getSourceName());
		}
		if (StringHelper.isNull(po.getSourceName())) {
			po.setSourceName(old.getSourceName());
		}
		if (po.getSourceType() == null) {
			po.setSourceType(old.getSourceType());
		}
		if (StringHelper.isNull(po.getSourceTypeName())) {
			po.setSourceTypeName(old.getSourceTypeName());
		}
		if (StringHelper.isNull(po.getSerNo())) {
			po.setSerNo(old.getSerNo());
		}
		if (po.getSellerId() == null) {
			po.setSellerId(old.getSellerId());
		}
		if (StringHelper.isNull(po.getSellerName())) {
			po.setSellerName(old.getSellerName());
		}
		if (po.getCusId() == null) {
			po.setCusId(old.getCusId());
		}
		if (StringHelper.isNull(po.getCusName())) {
			po.setCusName(old.getCusName());
		}
		if (po.getPrimaryConfigurationId() == null) {
			po.setPrimaryConfigurationId(old.getPrimaryConfigurationId());
		}
		if (StringHelper.isNull(po.getPrimaryConfigurationName())) {
			po.setPrimaryConfigurationName(old.getPrimaryConfigurationName());
		}
		this.dao.merge(po);
		return 0;
	}

	public void del(String id) {
		BusinessOrderRecord po = this.dao.findById(Long.valueOf(id));
		this.dao.delete(po);
	}

	

	/**
	 * @return List<BusinessOrderRecord>
	 */
	public List<BusinessOrderRecord> listAll() {
		return dao.findAll();
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
		hql.append("from OutPlan a,Farmer b,BusinessGoods c,FarmerOrderRecord d where a.delFlage=0 and a.userId=b.userId"
				+ " and a.goodsId=c.id and d.outPlanId=a.id and d.goodsId=c.id and d.farmerUserId=b.userId");
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
		
		
		hql.append(" order by d.id desc");
		return hql.toString();
	}

	public long findByOutplanId(String deleteId) {
		String hql = "select count(id) from FarmerOrderRecord where outPlanId = '"+deleteId+"'";
		long count = commonDao.getNumberLongOfEntitiesWithHql(hql);
		return count;
	}
	
}

