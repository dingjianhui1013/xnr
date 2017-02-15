/**
 *2012-5-11 上午9:00:02
 */
package com.xnradmin.core.service.business.combo;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xnradmin.core.dao.business.combo.ComboDAO;
import com.xnradmin.po.business.BusinessGoods;
import com.xnradmin.po.business.Combo;
import com.xnradmin.po.business.ComboGoods;
import com.xnradmin.po.business.ComboPlan;
import com.xnradmin.vo.business.ComboGoodsVO;
import com.xnradmin.vo.business.ComboPlanVO;
import com.xnradmin.vo.business.ComboVO;

/**
 * @autohr: xiang_liu
 * 
 */
@Service("ComboService")
public class ComboService {

	@Autowired
	private ComboDAO dao;

	public List<Combo> findByPage(ComboVO comboVo, int pageNum,
			int numPerPage, String orderField, String orderDirection) {
		return dao.findByPage(comboVo, pageNum, numPerPage, orderField, orderDirection);
	}

	public int getCount(ComboVO comboVo) {
		return dao.getCount(comboVo);
	}

	public void modify(ComboVO comboVo) {
		boolean isModify = false;
		//修改
		if(comboVo!=null&&comboVo.getCombo()!=null&&comboVo.getCombo().getId()!=null){
			isModify=true;
		}else{//新增
			isModify=false;
		}
		if(!isModify){
			//保存套餐表
			Combo combo = comboVo.getCombo();
			combo.setComboStatus("0");
			combo.setCreateTime(new Timestamp(System.currentTimeMillis()));
			dao.saveCombo(combo);
			//保存套餐商品表
			for(ComboGoodsVO cvo:comboVo.getComboGoodsList()){
				BusinessGoods bg = cvo.getBusinessGoods();
				ComboGoods cg = cvo.getComboGoods();
				cg.setComboId(combo.getId());
				cg.setGoodsId(bg.getId());
				dao.saveComboGoods(cg);
			}
			//保存套餐配送表
			for(ComboPlanVO cpvo:comboVo.getComboPlanList()){
				BusinessGoods bg = cpvo.getBusinessGoods();
				ComboPlan cp = cpvo.getComboPlan();
				cp.setComboId(combo.getId());
				cp.setGoodsId(bg.getId());
				dao.saveComboPlan(cp);
			}
		}
		// TODO 最后修改套餐的总次数
		
	}

}
