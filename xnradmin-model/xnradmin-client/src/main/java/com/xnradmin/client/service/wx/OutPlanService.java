package com.xnradmin.client.service.wx;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xnradmin.core.dao.CommonDAO;
import com.xnradmin.po.business.BusinessCategory;
import com.xnradmin.po.business.BusinessGoods;
import com.xnradmin.po.business.BusinessWeight;
import com.xnradmin.po.wx.OutPlan;
import com.xnradmin.po.wx.connect.Farmer;
import com.xnradmin.vo.business.OutPlanVO;

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
	
	public List<OutPlanVO> getList(OutPlanVO query,int pageNo,int pageSize){
		
		String hql = getHql(query);
		List list= commonDao.getEntitiesByPropertiesWithHql(hql, pageNo,pageSize);
		List<OutPlanVO> resList = new LinkedList<OutPlanVO>();
		for (int i = 0; i < list.size(); i++) {
			Object[] obj = (Object[]) list.get(i);
			OutPlan outPlan = (OutPlan) obj[0];
			Farmer farmer = (Farmer) obj[1];
			BusinessGoods businessGood = (BusinessGoods) obj[2];
			BusinessWeight businessWeight = (BusinessWeight) obj[3];
			BusinessCategory businessCategory = (BusinessCategory) obj[4];
			
			OutPlanVO outPlanVO = new OutPlanVO();
			outPlanVO.setOutPlan(outPlan);
			outPlanVO.setFarmer(farmer);
			outPlanVO.setBusinessGood(businessGood);
			outPlanVO.setBusinessWeight(businessWeight);
			outPlanVO.setBusinessCategory(businessCategory);
			resList.add(outPlanVO);
		}
		return resList;
	}
	public OutPlanVO getById(String id){
		
		String hql = "from OutPlan a,Farmer b,BusinessGoods c,BusinessWeight d,BusinessCategory e  where a.delFlage=0 and a.userId=b.userId"
				+ " and a.goodsId=c.id and a.unitId=d.id and a.businesCategoryId=e.id and a.id="+id;
		List list= commonDao.getEntitiesByPropertiesWithHql(hql, 0,0);
		Object[] obj = (Object[]) list.get(0);
		OutPlan outPlan = (OutPlan) obj[0];
		Farmer farmer = (Farmer) obj[1];
		BusinessGoods businessGood = (BusinessGoods) obj[2];
		BusinessWeight businessWeight = (BusinessWeight) obj[3];
		BusinessCategory businessCategory = (BusinessCategory) obj[4];
		
		OutPlanVO outPlanVO = new OutPlanVO();
		outPlanVO.setOutPlan(outPlan);
		outPlanVO.setFarmer(farmer);
		outPlanVO.setBusinessGood(businessGood);
		outPlanVO.setBusinessWeight(businessWeight);
		outPlanVO.setBusinessCategory(businessCategory);
		return outPlanVO;
	}
	
	private String getHql(OutPlanVO query) {
		StringBuffer hql = new StringBuffer();
		hql.append("from OutPlan a,Farmer b,BusinessGoods c,BusinessWeight d,BusinessCategory e  where a.delFlage=0 and a.userId=b.userId"
				+ " and a.goodsId=c.id and a.unitId=d.id and a.businesCategoryId=e.id");
		if (query == null){
			return hql.append(" order by a.id desc").toString();
		}
		return hql.toString();
	}
	
	public int getCount(OutPlanVO query) {
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

	public List<BusinessCategory> getBusinessCategoryS() {
		String hql = "from BusinessCategory";
		List<BusinessCategory> businessCategorys =  commonDao.getEntitiesByPropertiesWithHql(hql,0,0);
		return businessCategorys;
	}

	public List<BusinessGoods> getGoodsList(String businesCategoryId) {
		String hql = "from BusinessGoods where goodsCategoryId = '" +businesCategoryId+"'";
		List<BusinessGoods> goodList =  commonDao.getEntitiesByPropertiesWithHql(hql,0,0);
		return goodList;
	}
}
