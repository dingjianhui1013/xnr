package com.xnradmin.client.service.wx;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xnradmin.core.dao.CommonDAO;
import com.xnradmin.core.util.SpringBase;
import com.xnradmin.po.wx.OutPlan;
import com.xnradmin.po.wx.WXAccessToken;

@Service("OutPlanService")
public class OutPlanService {
	
	@Autowired
	private CommonDAO commonDao;
	
	public List<OutPlan> findAll(String userId){
		try {
			String hql = " from OutPlan where delFlage=0 and userId="+userId+" order by createDate desc";
			List<OutPlan> list =  commonDao.getEntitiesByPropertiesWithHql(hql,0,0);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void save(OutPlan outplan){
		try{
		outplan.setDelFlage(0);
		outplan.setCreateDate(new Date());
		commonDao.save(outplan);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
