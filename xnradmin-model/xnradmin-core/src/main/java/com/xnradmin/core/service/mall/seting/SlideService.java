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
import com.xnradmin.core.dao.mall.seting.SlideDAO;
import com.xnradmin.constant.ViewConstant;
import com.xnradmin.po.mall.seting.Slide;
import com.xnradmin.vo.mall.SetingVO;

/**
 * @autohr: xiang_liu
 * 
 */
@Service("SlideService")
public class SlideService {

	@Autowired
	private SlideDAO dao;
	
	@Autowired
    private CommonDAO commonDao;

	static Log log = LogFactory.getLog(SlideService.class);

	/**
	 * 
	 * @param po
	 * @return int
	 */
	public int save(Slide po) {
		Serializable cls = dao.save(po);
		return Integer.parseInt((cls.toString()));
	}

	public Slide findByid(String id) {
		return dao.findById(Integer.valueOf(id));
	}

	/**	  
	 * @param po
	 * @return int
	 */
	public int modify(Slide po) {
		Slide old = new Slide();
			old = findByid(po.getId().toString());
			if(po.getSortId()==null){
				po.setSortId(old.getSortId());
			}
			if(StringHelper.isNull(po.getTitle())){
				po.setTitle(old.getTitle());
			}
			if(StringHelper.isNull(po.getPicPath())){
				po.setPicPath(old.getPicPath());
			}
			if(StringHelper.isNull(po.getPicUrl())){
				po.setPicUrl(old.getPicUrl());
			}
			if(po.getShowStatus()==null){
				po.setShowStatus(old.getShowStatus());
			}
			if(StringHelper.isNull(po.getShowPosition())){
				po.setShowPosition(old.getShowPosition());
			}
			if(po.getStaffId()==null){
				po.setStaffId(old.getStaffId());
			}
			if(po.getPrimaryConfigurationId()==null){
				po.setPrimaryConfigurationId(old.getPrimaryConfigurationId());
			}
			if(po.getPicType()==null){
				po.setPicType(old.getPicType());
			}
			if(po.getModifyStaffId()==null){
				po.setModifyStaffId(old.getModifyStaffId());
			}
			po.setModifyTime(new Timestamp(System.currentTimeMillis()));
			this.dao.merge(po);
			return 0;
	}

	public void del(String id){
		Slide po = this.dao.findById(Integer.valueOf(id));
        this.dao.delete(po);
    }
	
	public int removeSlideId(String id) {
		return dao.removeSlideId(Integer.valueOf(id));
	}
	
	
	/**
	 * @param brandname
	 * @return int
	 */
	public int getCount(String title, String showStatus, String staffId,
			String primaryConfigurationId, String picType, 
			String createStartTime, String createEndTime, String createStaffId,
			String modifyStartTime, String modifyEndTime, String modifyStaffId) {
		String hql = "select count(*) from Slide where 1=1";
		if (!StringHelper.isNull(staffId)) {
			hql = hql + " and staffId = '" + staffId + "'";
		}
		if (!StringHelper.isNull(title)) {
			hql = hql + " and title like '%"+ title +"%'";
		}
		if (!StringHelper.isNull(showStatus)) {
			hql = hql + " and showStatus = "+ showStatus;
		}
		if (!StringHelper.isNull(primaryConfigurationId)) {
			hql = hql + " and primaryConfigurationId = "+ primaryConfigurationId;
		}
		if (!StringHelper.isNull(picType)) {
			hql = hql + " and picType = "+ picType;
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
	public List<SetingVO> listVO(String title, String showStatus, String staffId,
			String primaryConfigurationId, String picType, 
			String createStartTime, String createEndTime, String createStaffId,
			String modifyStartTime, String modifyEndTime, String modifyStaffId,
			int curPage, int pageSize, String orderField, String direction) {
		String hql = "from Slide where 1=1";
		if (!StringHelper.isNull(staffId)) {
			hql = hql + " and staffId = '" + staffId + "'";
		}
		if (!StringHelper.isNull(title)) {
			hql = hql + " and title like '%"+ title +"%'";
		}
		if (!StringHelper.isNull(showStatus)) {
			hql = hql + " and showStatus = "+ showStatus;
		}
		if (!StringHelper.isNull(primaryConfigurationId)) {
			hql = hql + " and primaryConfigurationId = "+ primaryConfigurationId;
		}
		if (!StringHelper.isNull(picType)) {
			hql = hql + " and picType = "+ picType;
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
			Slide po = (Slide) l.get(i);
			SetingVO vo = new SetingVO();
			if(po.getId()!=null){
				vo.setSlideId(po.getId().toString());
			}
			vo.setSlideSortId(po.getSortId());
			vo.setTitle(po.getTitle());
			vo.setPicPath(po.getPicPath());
			vo.setPicUrl(po.getPicUrl());
			if(po.getShowStatus()!=null){
				vo.setShowStatus(po.getShowStatus().toString());
			}
			vo.setShowPosition(po.getShowPosition());
			vo.setSlideStaffId(po.getStaffId());
			if(po.getPrimaryConfigurationId()!=null){
				vo.setSlidePrimaryConfigurationId(po.getPrimaryConfigurationId().toString());
			}
			if(po.getPicType()!=null){
				vo.setPicType(po.getPicType().toString());
			}
			if(po.getCreateTime()!=null){
				vo.setSlideCreateTime(StringHelper.getSystime(
                    ViewConstant.PAGE_DATE_FORMAT_SEC,po.getCreateTime().getTime()));
			}
			if(po.getCreateStaffId()!=null){
				vo.setSlideCreateStaffId(po.getCreateStaffId().toString());
			}
			if(po.getModifyTime()!=null){
				vo.setSlideModifyTime(StringHelper.getSystime(
	                    ViewConstant.PAGE_DATE_FORMAT_SEC,po.getModifyTime().getTime()));
			}
			if(po.getModifyStaffId()!=null){
				vo.setSlideModifyStaffId(po.getModifyStaffId().toString());
			}
			resList.add(vo);
		}
		return resList;
	}

	/**
	 * @return List<PayUpmp>
	 */
	public List<Slide> listAll() {
		return dao.findAll();
	}

}
