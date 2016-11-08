package com.xnradmin.client.service.wx;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cntinker.util.StringHelper;
import com.xnradmin.core.dao.CommonDAO;
import com.xnradmin.core.dao.wx.FarmerDao;
import com.xnradmin.po.wx.connect.Farmer;
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
		List<Farmer> farmers= commonDao.getEntitiesByPropertiesWithHql(hql, 0,0);
		return farmers;
	}
	
	private String getHql(Farmer query) {
		StringBuffer hql = new StringBuffer();
		hql.append("from Farmer");
//		if (query == null){
//			return hql.append(" order by id desc").toString();
//		}
//		if (query.getUserName() != null&& !StringHelper.isNull(query.getUserName())) {
//			hql.append(" and ");
//			hql.append(" userName like '%").append(query.getUserName()).append("%'");
//		}
//		if (query.getUserId() != null&& !StringHelper.isNull(query.getUserId())) {
//			hql.append(" and ");
//			hql.append(" userId like '%").append(query.getUserId()).append("%'");
//		}
//		if (query.getTypes() != null&& !StringHelper.isNull(query.getTypes())) {
//			hql.append(" and ");
//			hql.append(" types like '%").append(query.getTypes()).append("%'");
//		}
		
		return hql.toString();
	}

	public int getCount(Farmer query) {
		// TODO Auto-generated method stub
		return 0;
	}
}
