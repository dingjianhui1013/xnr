package com.xnradmin.client.service.wx;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cntinker.util.StringHelper;
import com.xnradmin.core.dao.CommonDAO;
import com.xnradmin.core.util.SpringBase;
import com.xnradmin.po.wx.OutPlan;
import com.xnradmin.po.wx.WXAccessToken;
import com.xnradmin.po.wx.connect.Farmer;
import com.xnradmin.vo.client.wx.WXMenuVO;

@Service("OutPlanService")
public class OutPlanService {
	
	@Autowired
	private CommonDAO commonDao;
	
	public List<OutPlan> findAll(String userId){
		try {
			String hql = " from OutPlan where delFlage=0 and userId='"+userId+"' order by createDate desc";
			List<OutPlan> list =  commonDao.getEntitiesByPropertiesWithHql(hql,0,0);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void save(OutPlan outplan){
		try{
		outplan.setDelFlage(0);
		outplan.setCreateDate(new Date());
		commonDao.save(outplan);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public void delete(String id){
		try {
			String hql = "delete from OutPlan where id="+id;
			commonDao.executeUpdateOrDelete(hql);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public OutPlan findById(String id){
		String hql = "from OutPlan where id="+id;
		List<OutPlan> list = commonDao.getEntitiesByPropertiesWithHql(hql,0,0);
		return list.get(0);
	}
	public void saveEdit(OutPlan outPlan){
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String hql = "update OutPlan set goodsId='"+outPlan.getGoodsId()+"',"
				+ " startTime='"+simpleDateFormat.format(outPlan.getStartTime())+"',"
						+ " endTime='"+simpleDateFormat.format(outPlan.getEndTime())+"',"
								+ " output='"+outPlan.getOutput()+"',"
										+ " unitId='"+outPlan.getUnitId()+"'"
												+ " where id="+outPlan.getId();
		commonDao.executeUpdateOrDelete(hql);
	}
	
	public List<OutPlan> getList(OutPlan query,int pageNo,int pageSize){
		
		String hql = getHql(query);;
		List<OutPlan> farmers= commonDao.getEntitiesByPropertiesWithHql(hql, pageNo,pageSize);
		return farmers;
	}
	
	private String getHql(OutPlan query) {
		StringBuffer hql = new StringBuffer();
		hql.append("from OutPlan ");
		if (query == null){
			return hql.append(" order by id desc").toString();
		}
//		if (query.getMenu() != null&& !StringHelper.isNull(query.getMenu().getMenuName())) {
//			hql.append(" and ");
//			hql.append(" menuName like '%").append(query.getMenu().getMenuName()).append("%'");
//		}
		return hql.toString();
	}
	
	public int getCount(OutPlan query) {
		String hql = "select count(*) " + getHql(query);
		return commonDao.getNumberOfEntitiesWithHql(hql);
	}
//	private String getHql(OutPlan query) {
//		StringBuffer hql = new StringBuffer();
//		hql.append("from OutPlan");
//
//		if (query == null)
//			return hql.append(" order by id desc").toString();
//
//		int isAnd = 0;
//
//		boolean iswhere = false;
//		iswhere = query != null
//				&& (query.getMenu() != null && (query.getMenu().getWxuserid() != null
//						|| !StringHelper.isNull(query.getMenu().getMenuName())
//						|| query.getMenu().getMenuLevel() != null || query
//						.getMenu().getTypeid() != null));
//		if (iswhere) {
//			hql.append(" where ");
//		}
//		if (query.getMenu() != null && query.getMenu().getWxuserid() != null) {
//			if (isAnd > 0)
//				hql.append(" and ");
//			hql.append(" wxuserid=").append(query.getMenu().getWxuserid());
//			isAnd++;
//		}
//		if (query.getMenu() != null
//				&& !StringHelper.isNull(query.getMenu().getMenuName())) {
//			if (isAnd > 0)
//				hql.append(" and ");
//			hql.append(" menuName like '%")
//					.append(query.getMenu().getMenuName()).append("%'");
//			isAnd++;
//		}
//		if (query.getMenu() != null && query.getMenu().getMenuLevel() != null) {
//			if (isAnd > 0)
//				hql.append(" and ");
//			hql.append(" menuLevel =").append(query.getMenu().getMenuLevel());
//			isAnd++;
//		}
//		if (query.getMenu() != null && query.getMenu().getTypeid() != null) {
//			if (isAnd > 0)
//				hql.append(" and ");
//			hql.append(" typeid =").append(query.getMenu().getTypeid());
//			isAnd++;
//		}
//		hql.append(" order by id desc");
//		return hql.toString();
//	}
}
