package com.xnradmin.core.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.xnradmin.po.wx.connect.FarmerQrCode;

@Repository("farmerQrCodeDao")
public class FarmerQrCodeDao {

	private static final Logger log = LoggerFactory
			.getLogger(FarmerQrCode.class);

	protected void initDao() {
		// do nothing
	}

	@Autowired
	private CommonDAO commonDao;

	public void save(FarmerQrCode farmerQrCode) {
		log.debug("saving farmerQrCode instance");
		try {
			commonDao.save(farmerQrCode);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(FarmerQrCode farmerQrCode) {
		log.debug("deleting farmerQrCode instance");
		try {
			commonDao.delete(farmerQrCode);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

}
