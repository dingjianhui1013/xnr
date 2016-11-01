package com.xnradmin.core.dao.wx;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.xnradmin.core.dao.CommonDAO;
import com.xnradmin.core.dao.SmsRecordDAO;
import com.xnradmin.po.sms.SmsRecord;
import com.xnradmin.po.wx.connect.FarmerImage;

@Repository("FarmerImageDao")
public class FarmerImageDao {

	private static final Logger log = LoggerFactory
			.getLogger(FarmerImageDao.class);

	protected void initDao() {
		// do nothing
	}

	@Autowired
	private CommonDAO commonDao;

	public void save(FarmerImage farmerImage) {
		log.debug("saving FarmerImage instance");
		try {
			commonDao.save(farmerImage);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(FarmerImage farmerImage) {
		log.debug("deleting SmsRecord instance");
		try {
			commonDao.delete(farmerImage);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

//	public FarmerImage findById(java.lang.Integer id) {
//		log.debug("getting SmsRecord instance with id: " + id);
//		try {
//
//			return (FarmerImage) commonDao.findById(FarmerImage.class, id);
//		} catch (RuntimeException re) {
//			log.error("get failed", re);
//			throw re;
//		}
//	}
}
