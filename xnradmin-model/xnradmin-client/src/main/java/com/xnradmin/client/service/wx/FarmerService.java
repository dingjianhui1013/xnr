package com.xnradmin.client.service.wx;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cntinker.util.StringHelper;
import com.xnradmin.core.dao.CommonDAO;
import com.xnradmin.core.dao.wx.FarmerDao;
import com.xnradmin.po.wx.connect.Farmer;
import com.xnradmin.vo.business.OutPlanVO;
import com.xnradmin.vo.client.wx.WXMenuVO;

@Service("farmerService")
@Transactional
public class FarmerService {

	@Autowired
	private FarmerDao farmerDao;
	@Autowired
	private CommonDAO commonDao;
	
	public void saveFarmer(Farmer farmer)
	{
		farmerDao.save(farmer);
	}
	
	public List<Farmer> getList(Farmer query,int pageNo,int pageSize){
		
		String hql = getHql(query);
 		List<Farmer> farmers= commonDao.getEntitiesByPropertiesWithHql(hql, pageNo,pageSize);
		return farmers;
	}
	
	private String getHql(Farmer query) {
		StringBuffer hql = new StringBuffer();
		hql.append("from Farmer");
		if (query == null){
			return hql.append(" order by id desc").toString();
		}
		int isAnd = 0;
		if (!StringHelper.isNull(query.getUserName())
				|| !StringHelper.isNull(query.getUserId())) {
			hql.append(" where ");
		}
		if (!StringHelper.isNull(query.getUserName())) {
			if (isAnd > 0)
				hql.append(" and ");
			hql.append(" userName like '%").append(query.getUserName())
					.append("%'");
		}
		if (!StringHelper.isNull(query.getUserId())) {
			if (isAnd > 0)
				hql.append(" and ");
			hql.append(" userId like '%").append(query.getUserId())
					.append("%'");
		}
		hql.append(" order by id desc").toString();
		return hql.toString();
	}

	public int getCount(Farmer query) {
		String hql = "select count(*) " + getHql(query);
		return commonDao.getNumberOfEntitiesWithHql(hql);
	}
	public String[] getFenleiById(String farmerId){
		String hql = "select types from Farmer where id="+farmerId;
		List types = (List)commonDao.getEntityByPropertiesWithHql(hql);
		String s = (String)types.get(0)==null?",":(String)types.get(0);
		String[] split = s.split(",");
		return split;
	}
	public String getFenleiByUserId(String userId){
		String hql = "select types from Farmer where userId='"+userId+"'";
		List types = (List)commonDao.getEntityByPropertiesWithHql(hql);
		String s = types==null?"":(String)types.get(0);
		return s;
}
	
	/**
	 * 更新农户对应的商品
	 * @param id   农户id
	 * @param types  商品类型
	 */
	public void saveTypes(String id,String types){
		String hql ="";
		if(types==null){
			hql ="update Farmer set types=null where id="+id;
		}else{
			types = types.replace(" ", ""); 
			hql ="update Farmer set types='"+types+"' where id="+id;
		}
		commonDao.executeUpdateOrDelete(hql);
	}
}
