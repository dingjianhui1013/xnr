package com.xnradmin.core.service.business.combo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xnradmin.core.dao.business.combo.ComboPlanDao;
import com.xnradmin.po.business.BusinessGoods;

@Service("ComboPlanService")
public class ComboPlanService {

	@Autowired
	private ComboPlanDao comboPlanDao;
	
	public List<BusinessGoods> findGoodsBycomboId(String comboId)
	{
		List<BusinessGoods> goodsList = comboPlanDao.findGoodsBycomboId(comboId);
		return goodsList;
	}
}
