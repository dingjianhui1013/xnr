package com.xnradmin.core.dao.wx;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.xnradmin.core.dao.CommonDAO;
import com.xnradmin.po.wx.connect.Farmer;
import com.xnradmin.po.wx.connect.FarmerImage;

@Repository("farmerDao")
public class FarmerDao {

	private static final Logger log = LoggerFactory
			.getLogger(Farmer.class);

	protected void initDao() {
		// do nothing
	}

	@Autowired
	private CommonDAO commonDao;

	public void save(Farmer farmer) {
		log.debug("saving farmer instance");
		try {
			commonDao.save(farmer);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Farmer farmer) {
		log.debug("deleting farmer instance");
		try {
			commonDao.delete(farmer);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

}
