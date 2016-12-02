package com.xnradmin.core.service.pay;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xnradmin.core.dao.CommonDAO;
import com.xnradmin.po.pay.Alipay;
import com.xnradmin.po.pay.Reconciliation;

@Service("alipayService")
public class AlipayService {
	@Autowired
    private CommonDAO commonDao;
	public void save(Alipay alipay)
	{
		commonDao.save(alipay);
	}
	public void saveRecon(Reconciliation reconciliation) {
		commonDao.save(reconciliation);
		
	}
}
