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
		String hql = "from FarmerImage where userId='"+userId+"'";
		List<FarmerImage> list = commonDAO.getEntitiesByPropertiesWithHql(hql,0,0);
		return list;
	}
	public List getImageDates(String userId){
		String hql = "select distinct  date from FarmerImage where userId='"+userId+"'";
//		String hql = " from FarmerImage x where (x.type,x.date )in (SELECT y.type,MAX(y.date)from FarmerImage y )";
		List list = commonDAO.getEntitiesByPropertiesWithHql(hql,0,0);
		return list;
	}
	public List<String> findByType(String date,String userId)
	{
		
		String hql = "select distinct type from FarmerImage where date ='"+date+"'"+"and userId = '"+userId+"'";
		List<String> list = commonDAO.getEntitiesByPropertiesWithHql(hql, 0,0);
		return list;
	}
	public List<String> findByImages(String type,String images,String userId)
	{
		String hql = "select url from FarmerImage where type ='"+type+"'and date ='"+images+"'" +"and userId ='"+userId+"'";
		List<String> list = commonDAO.getEntitiesByPropertiesWithHql(hql, 0,0);
		return list;
	}
	public void delectImages(String imageUrl) {
		try {
			String hql = "delete from FarmerImage where url='"+imageUrl+"'";
			commonDAO.executeUpdateOrDelete(hql);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
