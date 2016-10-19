/**
 *2012-5-11 上午9:00:02
 */
package com.xnradmin.core.service;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xnradmin.core.dao.CommonDAO;
import com.xnradmin.core.dao.SmsRecordDAO;
import com.xnradmin.po.sms.SmsRecord;

/**
 * @autohr: xiang_liu
 * 
 */			 
@Service("SmsRecordService")
public class SmsRecordService {

	@Autowired
	private SmsRecordDAO dao;
	
	@Autowired
	private CommonDAO commonDao;

	static Log log = LogFactory.getLog(SmsRecordService.class);

	/**
	 * 
	 * @param po
	 * @return int
	 */
	public int save(SmsRecord po) {
		dao.save(po);
		return 0;
	}

	public SmsRecord findByid(String id) {
		return dao.findById(new Integer(id));
	}

	/**	  
	 * @param po
	 * @return int
	 */
	public int modify(SmsRecord po) {
		this.dao.merge(po);
		return 0;
	}

	public void del(String id){
		SmsRecord po = this.dao.findById(new Integer(id));
        this.dao.delete(po);
    }
	
	public int removeSmsRecordId(Integer id) {
		return dao.removeSmsRecordId(id);
	}
	
	/**
	 * @return List<ClientUserInfo>
	 */
	public List listAll() {
		return dao.findAll();
	}

}
