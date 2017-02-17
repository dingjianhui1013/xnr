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
import com.xnradmin.core.dao.mall.seting.DeliveryModeDAO;
import com.xnradmin.constant.ViewConstant;
import com.xnradmin.po.mall.seting.DeliveryMode;
import com.xnradmin.vo.mall.SetingVO;

/**
 * @autohr: xiang_liu
 * 
 */
@Service("DeliveryModeService")
public class DeliveryModeService {

	@Autowired
	private DeliveryModeDAO dao;
	
	@Autowired
    private CommonDAO commonDao;

	static Log log = LogFactory.getLog(DeliveryModeService.class);

	/**
	 * 
	 * @param po
	 * @return int
	 */
	public int save(DeliveryMode po) {
		Serializable cls = dao.save(po);
		return Integer.parseInt((cls.toString()));
	}

	public DeliveryMode findByid(String id) {
		return dao.findById(Integer.valueOf(id));
	}

	/**	  
	 * @param po
	 * @return int
	 */
	public int modify(DeliveryMode po) {
		DeliveryMode old = new DeliveryMode();
			old = findByid(po.getId().toString());
			if(po.getSortId()==null){
				po.setSortId(old.getSortId());
			}
			if(StringHelper.isNull(po.getDeliveryModeName())){
				po.setDeliveryModeName(old.getDeliveryModeName());
			}
			if(po.getLogisticsCompanyId()==null){
				po.setLogisticsCompanyId(old.getLogisticsCompanyId());
			}
			if(StringHelper.isNull(po.getFirstWeight())){
				po.setFirstWeight(old.getFirstWeight());
			}
			if(po.getFirstPrice()==null){
				po.setFirstPrice(old.getFirstPrice());
			}
			if(StringHelper.isNull(po.getContinuedHeavyWeight())){
				po.setContinuedHeavyWeight(old.getContinuedHeavyWeight());
			}
			if(po.getContinuedHeavyPrice()==null){
				po.setContinuedHeavyPrice(old.getContinuedHeavyPrice());
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
		DeliveryMode po = this.dao.findById(Integer.valueOf(id));
        this.dao.delete(po);
    }
	
	public int removeDeliveryModeId(String id) {
		return dao.removeDeliveryModeId(Integer.valueOf(id));
	}
	
	
	/**
	 * @param brandname
	 * @return int
	 */
	public int getCount(String deliveryModeName, String logisticsCompanyId, 
			String staffId, String primaryConfigurationId,
			String createStartTime, String createEndTime, String createStaffId,
			String modifyStartTime, String modifyEndTime, String modifyStaffId) {
		String hql = "select count(*) from DeliveryMode where 1=1";
		if (!StringHelper.isNull(staffId)) {
			hql = hql + " and staffId = '" + staffId + "'";
		}
		if (!StringHelper.isNull(deliveryModeName)) {
			hql = hql + " and deliveryModeName like '%"+ deliveryModeName +"%'";
		}
		if (!StringHelper.isNull(logisticsCompanyId)) {
			hql = hql + " and logisticsCompanyId = "+ logisticsCompanyId;
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
	public List<SetingVO> listVO(String deliveryModeName, String logisticsCompanyId, 
			String staffId, String primaryConfigurationId,
			String createStartTime, String createEndTime, String createStaffId,
			String modifyStartTime, String modifyEndTime, String modifyStaffId,
			int curPage, int pageSize, String orderField, String direction) {
		String hql = "from DeliveryMode where 1=1";
		if (!StringHelper.isNull(staffId)) {
			hql = hql + " and staffId = '" + staffId + "'";
		}
		if (!StringHelper.isNull(deliveryModeName)) {
			hql = hql + " and deliveryModeName like '%"+ deliveryModeName +"%'";
		}
		if (!StringHelper.isNull(logisticsCompanyId)) {
			hql = hql + " and logisticsCompanyId = "+ logisticsCompanyId;
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
			DeliveryMode po = (DeliveryMode) l.get(i);
			SetingVO vo = new SetingVO();
			if(po.getId()!=null){
				vo.setDeliverModeId(po.getId().toString());
			}
			vo.setDeliverModeSortId(po.getSortId());
			vo.setDeliveryModeName(po.getDeliveryModeName());
			if(po.getLogisticsCompanyId()!=null){
				vo.setDeliverModeLogisticsCompanyId(po.getLogisticsCompanyId().toString());
			}
			vo.setFirstWeight(po.getFirstWeight());
			if(po.getFirstPrice()!=null){
				vo.setFirstPrice(po.getFirstPrice().toString());
			}
			vo.setContinuedHeavyWeight(po.getContinuedHeavyWeight());
			if(po.getContinuedHeavyPrice()!=null){
				vo.setContinuedHeavyPrice(po.getContinuedHeavyPrice().toString());
			}
			vo.setDeliverModeStaffId(po.getStaffId());
			if(po.getPrimaryConfigurationId()!=null){
				vo.setDeliverModeLogisticsCompanyId(po.getPrimaryConfigurationId().toString());
			}
			if(po.getCreateTime()!=null){
				vo.setDeliverModeCreateTime(StringHelper.getSystime(
                    ViewConstant.PAGE_DATE_FORMAT_SEC,po.getCreateTime().getTime()));
			}
			if(po.getCreateStaffId()!=null){
				vo.setDeliverModeCreateStaffId(po.getCreateStaffId().toString());
			}
			if(po.getModifyTime()!=null){
				vo.setDeliverModeModifyTime(StringHelper.getSystime(
	                    ViewConstant.PAGE_DATE_FORMAT_SEC,po.getModifyTime().getTime()));
			}
			if(po.getModifyStaffId()!=null){
				vo.setDeliverModeModifyStaffId(po.getModifyStaffId().toString());
			}
			resList.add(vo);
		}
		return resList;
	}

	/**
	 * @return List<PayUpmp>
	 */
	public List<DeliveryMode> listAll() {
		return dao.findAll();
	}

}
