package com.xnradmin.core.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xnradmin.core.action.ParentAction;
import com.xnradmin.core.dao.CommonDAO;
import com.xnradmin.core.dao.FarmerQrCodeDao;
import com.xnradmin.po.business.BusinessGoods;
import com.xnradmin.po.wx.connect.Farmer;
import com.xnradmin.po.wx.connect.FarmerQrCode;
import com.xnradmin.vo.FarmerQrCodeVo;
@Service("farmerQrCodeService")
public class FarmerQrCodeService {

	@Autowired
	private FarmerQrCodeDao farmerQrCodeDao;
	
	@Autowired
	private CommonDAO commonDao;	
	
	public void save(FarmerQrCode farmerQrCode)
	{
		farmerQrCodeDao.save(farmerQrCode);
	}
	public List<FarmerQrCodeVo> getListFarmerqrCode()
	{
		String hql = "from FarmerQrCode a,Farmer b ,BusinessGoods c where a.farmerId = b.userId and a.goodsId = c.id";
		List farmerQrCodes = commonDao.getEntitiesByPropertiesWithHql(hql, 0,0);
		List<FarmerQrCodeVo> resList = new LinkedList<FarmerQrCodeVo>();
		for (int i = 0; i < farmerQrCodes.size(); i++) {
			Object[] obj = (Object[]) farmerQrCodes.get(i);
			FarmerQrCode farmerQrCode = (FarmerQrCode)obj[0];
			Farmer farmer = (Farmer)obj[1];
			BusinessGoods businessGoods = (BusinessGoods)obj[2];
			FarmerQrCodeVo farmerQrCodeVo = new FarmerQrCodeVo();
			farmerQrCodeVo.setFarmerQrCode(farmerQrCode);
			farmerQrCodeVo.setFarmer(farmer);
			farmerQrCodeVo.setBusinessGoods(businessGoods);
			resList.add(farmerQrCodeVo);
		}
		return resList;
	}
}
