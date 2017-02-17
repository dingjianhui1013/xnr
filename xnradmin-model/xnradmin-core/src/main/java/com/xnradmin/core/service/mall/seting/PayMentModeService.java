/**
 *2012-5-11 上午9:00:02
 */
package com.xnradmin.core.service.mall.seting;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cntinker.util.StringHelper;
import com.xnradmin.core.dao.CommonDAO;
import com.xnradmin.core.dao.mall.seting.PayMentModeDAO;
import com.xnradmin.constant.ViewConstant;
import com.xnradmin.po.mall.seting.PayMentMode;
import com.xnradmin.vo.mall.SetingVO;

/**
 * @autohr: xiang_liu
 * 
 */
@Service("PayMentModeService")
public class PayMentModeService {

	@Autowired
	private PayMentModeDAO dao;
	
	@Autowired
    private CommonDAO commonDao;

	static Log log = LogFactory.getLog(PayMentModeService.class);

	/**
	 * 
	 * @param po
	 * @return int
	 */
	public int save(PayMentMode po) {
		Serializable cls = dao.save(po);
		return Integer.parseInt((cls.toString()));
	}

	public PayMentMode findByid(String id) {
		return dao.findById(Integer.valueOf(id));
	}

	/**	  
	 * @param po
	 * @return int
	 */
	public int modify(PayMentMode po) {
		PayMentMode old = new PayMentMode();
			old = findByid(po.getId().toString());
			if(po.getSortId()==null){
				po.setSortId(old.getSortId());
			}
			if(StringHelper.isNull(po.getPaymentTypeName())){
				po.setPaymentTypeName(old.getPaymentTypeName());
			}
			if(po.getPaymentTypeId()==null){
				po.setPaymentTypeId(old.getPaymentTypeId());
			}
			if(po.getStaffId()==null){
				po.setStaffId(old.getStaffId());
			}
			if(po.getPrimaryConfigurationId()==null){
				po.setPrimaryConfigurationId(old.getPrimaryConfigurationId());
			}
			if(po.getModifyStaffId()==null){
				po.setModifyStaffId(old.getModifyStaffId());
			}
			po.setModifyTime(new Timestamp(System.currentTimeMillis()));
			this.dao.merge(po);
			return 0;
	}

	public void del(String id){
		PayMentMode po = this.dao.findById(Integer.valueOf(id));
        this.dao.delete(po);
    }
	
	public int removePayMentModeId(String id) {
		return dao.removePayMentModeId(Integer.valueOf(id));
	}
	
	
	/**
	 * @param brandname
	 * @return int
	 */
	public int getCount(String paymentTypeName, String paymentTypeId, 
			String staffId, String primaryConfigurationId,
			String createStartTime, String createEndTime, String createStaffId,
			String modifyStartTime, String modifyEndTime, String modifyStaffId) {
		String hql = "select count(*) from PayMentMode where 1=1";
		if (!StringHelper.isNull(staffId)) {
			hql = hql + " and staffId = '" + staffId + "'";
		}
		if (!StringHelper.isNull(paymentTypeName)) {
			hql = hql + " and deliveryModeName like '%"+ paymentTypeName +"%'";
		}
		if (!StringHelper.isNull(paymentTypeId)) {
			hql = hql + " and paymentTypeId = "+ paymentTypeId;
		}
		if (!StringHelper.isNull(primaryConfigurationId)) {
			hql = hql + " and primaryConfigurationId = "+ primaryConfigurationId;
		}
		if (!StringHelper.isNull(createStartTime)) {
			hql = hql + " and createTime >='"+ createStartTime +"'" ;
		}
		if (!StringHelper.isNull(createEndTime)) {
			hql = hql + " and createTime <='"+ createEndTime +"'" ;
		}
		if (!StringHelper.isNull(createStaffId)) {
			hql = hql + " and createStaffId >='"+ createStaffId +"'" ;
		}
		if (!StringHelper.isNull(modifyStartTime)) {
			hql = hql + " and modifyTime <='"+ modifyStartTime +"'" ;
		}
		if (!StringHelper.isNull(modifyEndTime)) {
			hql = hql + " and modifyTime >='"+ modifyEndTime +"'" ;
		}
		if (!StringHelper.isNull(modifyStaffId)) {
			hql = hql + " and modifyStaffId <='"+ modifyStaffId +"'" ;
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
	public List<SetingVO> listVO(String paymentTypeName, String paymentTypeId, 
			String staffId, String primaryConfigurationId,
			String createStartTime, String createEndTime, String createStaffId,
			String modifyStartTime, String modifyEndTime, String modifyStaffId,
			int curPage, int pageSize, String orderField, String direction) {
		String hql = "from PayMentMode where 1=1";
		if (!StringHelper.isNull(staffId)) {
			hql = hql + " and staffId = '" + staffId + "'";
		}
		if (!StringHelper.isNull(paymentTypeName)) {
			hql = hql + " and deliveryModeName like '%"+ paymentTypeName +"%'";
		}
		if (!StringHelper.isNull(paymentTypeId)) {
			hql = hql + " and paymentTypeId = "+ paymentTypeId;
		}
		if (!StringHelper.isNull(primaryConfigurationId)) {
			hql = hql + " and primaryConfigurationId = "+ primaryConfigurationId;
		}
		if (!StringHelper.isNull(createStartTime)) {
			hql = hql + " and createTime >='"+ createStartTime +"'" ;
		}
		if (!StringHelper.isNull(createEndTime)) {
			hql = hql + " and createTime <='"+ createEndTime +"'" ;
		}
		if (!StringHelper.isNull(createStaffId)) {
			hql = hql + " and createStaffId >='"+ createStaffId +"'" ;
		}
		if (!StringHelper.isNull(modifyStartTime)) {
			hql = hql + " and modifyTime <='"+ modifyStartTime +"'" ;
		}
		if (!StringHelper.isNull(modifyEndTime)) {
			hql = hql + " and modifyTime >='"+ modifyEndTime +"'" ;
		}
		if (!StringHelper.isNull(modifyStaffId)) {
			hql = hql + " and modifyStaffId <='"+ modifyStaffId +"'" ;
		}
		if (!StringHelper.isNull(orderField) && !StringHelper.isNull(direction)) {
			hql = hql + " order by " + orderField + " " + direction;
		}else{
			hql +=" order by id desc";
		}
		List l = commonDao.getEntitiesByPropertiesWithHql(hql, curPage, pageSize); // .findByQuerySplitPage(hql,curPage,pageSize);
		List<SetingVO> resList = new ArrayList<SetingVO>();
		for (int i = 0; i < l.size(); i++) {
			PayMentMode po = (PayMentMode) l.get(i);
			SetingVO vo = new SetingVO();
			if(po.getId()!=null){
				vo.setPayMentModeId(po.getId().toString());
			}
			vo.setPayMentModeSortId(po.getSortId());
			vo.setPaymentName(po.getPaymentTypeName());
			if(po.getPaymentTypeId()!=null){
				vo.setPaymentTypeId(po.getPaymentTypeId().toString());
			}
			vo.setPayMentModeStaffId(po.getStaffId());
			if(po.getPrimaryConfigurationId()!=null){
				vo.setPayMentModePrimaryConfigurationId(po.getPrimaryConfigurationId().toString());
			}
			if(po.getCreateTime()!=null){
				vo.setPayMentModeCreateTime(StringHelper.getSystime(
                    ViewConstant.PAGE_DATE_FORMAT_SEC,po.getCreateTime().getTime()));
			}
			if(po.getCreateStaffId()!=null){
				vo.setPayMentModeCreateStaffId(po.getCreateStaffId().toString());
			}
			if(po.getModifyTime()!=null){
				vo.setPayMentModeModifyTime(StringHelper.getSystime(
	                    ViewConstant.PAGE_DATE_FORMAT_SEC,po.getModifyTime().getTime()));
			}
			if(po.getModifyStaffId()!=null){
				vo.setPayMentModeModifyStaffId(po.getModifyStaffId().toString());
			}
			resList.add(vo);
		}
		return resList;
	}

	/**
	 * @return List<PayUpmp>
	 */
	public List<PayMentMode> listAll() {
		return dao.findAll();
	}

}
