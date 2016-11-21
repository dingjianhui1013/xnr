package com.xnradmin.client.service.front;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xnradmin.core.dao.CommonDAO;
import com.xnradmin.po.front.FrontUser;

@Service("FrontUserService")
@Transactional
public class FrontUserService {
	
	@Autowired
	private CommonDAO commonDao;
	
	
	public boolean isExistloginName(String phone){
	    String hql = "from FrontUser where phone="+phone;
        List<FrontUser> list = commonDao.getEntitiesByPropertiesWithHql(hql,0,0);
        if(list.size()>0){
            return true;
        }else{
            return false;
        }
        
	}
	
}
