package com.xnradmin.client.service.wx;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xnradmin.core.dao.CommonDAO;
import com.xnradmin.core.util.SpringBase;
import com.xnradmin.po.wx.OutPlan;

public class OutPlanService {
	
	@Autowired
	private CommonDAO commonDao;
	public void save(OutPlan outplan){
		outplan.setCreateDate(new Date());
		commonDao.save(outplan);
	}
}
