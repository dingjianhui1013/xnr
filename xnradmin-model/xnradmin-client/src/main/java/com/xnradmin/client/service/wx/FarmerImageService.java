package com.xnradmin.client.service.wx;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xnradmin.core.dao.CommonDAO;
import com.xnradmin.core.dao.wx.FarmerImageDao;
import com.xnradmin.po.wx.connect.FarmerImage;

@Service("farmerImageService")
@Transactional
public class FarmerImageService {

	@Autowired
	private FarmerImageDao farmerImageDao;

	@Autowired
	private CommonDAO commonDAO;
	
	public void saveFarmerImage(FarmerImage farmerImage) {
		farmerImageDao.save(farmerImage);
	}
	
	public List<FarmerImage> findAll(String userId){
		String hql = "from FarmerImage where userId="+userId;
		List list = commonDAO.getEntitiesByPropertiesWithHql(hql,0,0);
		return list;
	}
	public List<FarmerImage> getImageType(String userId){
		String hql = "selectd distinct type from FarmerImage where userId="+userId;
		List list = commonDAO.getEntitiesByPropertiesWithHql(hql,0,0);
		return list;
	}

}
