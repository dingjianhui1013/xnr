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
		
		String hql = getHql(query);;
		List<Farmer> farmers= commonDao.getEntitiesByPropertiesWithHql(hql, pageNo,pageSize);
		return farmers;
	}
	
	private String getHql(Farmer query) {
		StringBuffer hql = new StringBuffer();
		hql.append("from Farmer where 1=1");
		if (query == null){
			return hql.append(" order by id desc").toString();
		}
//		if (query.getMenu() != null&& !StringHelper.isNull(query.getMenu().getMenuName())) {
//			hql.append(" and ");
//			hql.append(" menuName like '%").append(query.getMenu().getMenuName()).append("%'");
//		}
		return hql.toString();
	}
}
