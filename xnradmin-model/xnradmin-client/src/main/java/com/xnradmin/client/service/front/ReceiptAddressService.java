package com.xnradmin.client.service.front;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xnradmin.core.dao.CommonDAO;
import com.xnradmin.po.front.FrontUser;
import com.xnradmin.po.front.ReceiptAddress;
import com.xnradmin.po.wx.connect.Farmer;

@Service("ReceiptAddressService")
@Transactional
public class ReceiptAddressService {
	
	@Autowired
	private CommonDAO commonDao;
	
	
	/**
	 * 
	 */
	public boolean save(ReceiptAddress receiptAddress){
		commonDao.save(receiptAddress);
		return true;
		
	}
	
	public ReceiptAddress findByid(String id){
		String hql = "from ReceiptAddress where id="+id;
		List<ReceiptAddress> list = commonDao.getEntitiesByPropertiesWithHql(hql,0,0);
		if(list.isEmpty()){
			return null;
		}else{
			return list.get(0);
		}
	}
	
	public List<ReceiptAddress> findByUserId(Long userId, String type){
		
		String hql = " from ReceiptAddress where userId = "+userId+" and type = "+type;
		
		List<ReceiptAddress> list = commonDao.getEntitiesByPropertiesWithHql(hql, 0, 0);
		
		if(list.isEmpty()){
			return null;
		}else{
			return list;
		}
	}
	
	
	
	public ReceiptAddress findByUserId(Long userId){
		
		String hql = " from ReceiptAddress where userId = "+userId+" and type = 0";
		
		List<ReceiptAddress> list = commonDao.getEntitiesByPropertiesWithHql(hql, 0, 0);
		
		if(list.isEmpty()){
			return null;
		}else{
			return list.get(0);
		}
	}
	
	
	
	public boolean delete(String id){
		try {
			String hql = "delete from ReceiptAddress where id="+id;
			commonDao.executeUpdateOrDelete(hql.toString());
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	

	
}