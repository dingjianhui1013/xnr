package com.xnradmin.core.service.pay;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xnradmin.core.dao.CommonDAO;
import com.xnradmin.core.service.business.order.BusinessOrderGoodsRelationService;
import com.xnradmin.core.service.mall.order.OrderGoodsRelationService;
import com.xnradmin.po.business.BusinessGoods;
import com.xnradmin.po.business.Combo;
import com.xnradmin.po.pay.Alipay;
import com.xnradmin.po.pay.Reconciliation;

@Service("alipayService")
public class AlipayService {
	@Autowired
    private CommonDAO commonDao;
	@Autowired
	private BusinessOrderGoodsRelationService orderGoodsRelationService;
	public void save(Alipay alipay)
	{
		commonDao.save(alipay);
	}
	public void saveRecon(Reconciliation reconciliation) {
		commonDao.save(reconciliation);
		
	}
	public Alipay payInfo(String outTradeNo,Long id,String totalMoney)
	{
		List<BusinessGoods> goodsNames = orderGoodsRelationService.findGoodsName(id);
		List<Combo> combos  = orderGoodsRelationService.findComboName(id);
		String subject = "";
		int index=0;
		if(goodsNames.size()>3){
			index = 3;
		}else
		{
			index = goodsNames.size();
		}
		for (int i = 0; i < index; i++) {
			subject+="  "+goodsNames.get(i).getGoodsName();
		}
		for (Combo combo : combos) {
			subject+=" "+combo.getComboName();
		}
		Alipay ali = new Alipay();
		ali.setSubject("康源公社["+subject+"]");
		ali.setOutTradeNo(outTradeNo);
		ali.setTotalFee(totalMoney);// 正式是这个数字，测试暂时用0.01；
//		ali.setTotalFee("0.01");
		return ali;
	}
}
