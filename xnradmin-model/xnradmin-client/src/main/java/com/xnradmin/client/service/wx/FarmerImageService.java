package com.xnradmin.client.service.wx;

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
	public void save(FarmerImage farmerImage)
	{
		commonDAO.save(farmerImage);
	}

}
