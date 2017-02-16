/**
 *2012-5-11 上午9:00:02
 */
package com.xnradmin.core.service.business.combo;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xnradmin.core.dao.business.combo.ComboDAO;
import com.xnradmin.po.business.BusinessGoods;
import com.xnradmin.po.business.Combo;
import com.xnradmin.po.business.ComboGoods;
import com.xnradmin.po.business.ComboPlan;
import com.xnradmin.po.business.ComboUser;
import com.xnradmin.vo.business.ComboGoodsVO;
import com.xnradmin.vo.business.ComboPlanVO;
import com.xnradmin.vo.business.ComboUserVO;
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
		
		//Map<Integer,Integer> goodNum = new HashMap<>();整理配送计划
		
		Combo combo = null;
		if(!isModify){
			//保存套餐表
			combo = comboVo.getCombo();
			combo.setComboStatus("0");
			combo.setCreateTime(new Timestamp(System.currentTimeMillis()));
			dao.saveCombo(combo);
		}else{
			combo=dao.findOneComboById(comboVo.getCombo().getId());
			Combo newCombo = comboVo.getCombo();
			combo.setComboCycleStatus(newCombo.getComboCycleStatus());
			combo.setComboName(newCombo.getComboName());
			combo.setComboPrice(newCombo.getComboPrice());
			combo.setComboImgBig(newCombo.getComboImgBig());
			combo.setComboImgSmall(newCombo.getComboImgSmall());
			dao.mergeCombo(combo);
		}
		if(isModify){
			//删除已有的套餐商品
			dao.deleteComboGoodsByComboId(combo.getId());
		}
		//保存套餐商品表
		for(ComboGoodsVO cvo:comboVo.getComboGoodsList()){
			BusinessGoods bg = cvo.getBusinessGoods();
			ComboGoods cg = cvo.getComboGoods();
			cg.setComboId(combo.getId());
			cg.setGoodsId(bg.getId());
			//goodNum.put(bg.getId(), cg.getGoodsCount());
			dao.saveComboGoods(cg);
		}
		if(isModify){
			//删除已有的套餐配送计划
			dao.deleteComboPlanByComboId(combo.getId());
		}
		//保存套餐配送表
		for(ComboPlanVO cpvo:comboVo.getComboPlanList()){
			BusinessGoods bg = cpvo.getBusinessGoods();
			String comboCycleStr = cpvo.getComboCycleStr();
			String[] types=null;
			if("".equals(comboCycleStr)){//固定时间
				
			}else{
				types = cpvo.getComboCycleStr().split("#");
			}
			ComboPlan cp = cpvo.getComboPlan();
			if(types!=null){
				if("1".equals(types[0])){
					cp.setComboPlanType(types[0]);
					cp.setComboPlanCycle(types[1]);					
				}else if("2".equals(types[0])){
					cp.setComboPlanType(types[0]);
					cp.setComboPlanCycle(types[1]);		
					cp.setComboPlanDate(types[2]);
				}
			}else{
				cp.setComboPlanType("0");
				cp.setComboPlanCycle("-1");//没有周期
			}
			cp.setComboId(combo.getId());
			cp.setGoodsId(bg.getId());
			cp.setComboPlanCreatTime(new Timestamp(System.currentTimeMillis()));
			dao.saveComboPlan(cp);
		}
		// TODO 最后修改套餐的总次数  生成伪订单  伪订单 主要存储 订单时间 天为单位 相对时间 便于生成用户的伪订单
		
		
	}

	public ComboVO getComboVOById(ComboVO comboVo) {
		return dao.findById(comboVo.getCombo().getId());
	}

	public boolean delComboVOById(ComboVO comboVo) {
		if(comboVo!=null&&comboVo.getCombo()!=null&&comboVo.getCombo().getId()!=null){
			Combo combo=dao.findOneComboById(comboVo.getCombo().getId());
			//套餐未被使用
			ComboUser cu = dao.findComboUserByComboId(combo.getId());
			if(combo!=null&&cu==null){
				dao.deleteComboGoodsByComboId(combo.getId());
				dao.deleteComboPlanByComboId(combo.getId());
				dao.delete(combo);
				return true;
			}else{
				return false;
			}
		}
		return true;
	}

	public boolean modifyCombo(ComboVO comboVo) {
		if(comboVo!=null&&comboVo.getCombo()!=null&&comboVo.getCombo().getId()!=null){
			Combo combo=dao.findOneComboById(comboVo.getCombo().getId());
			//套餐未被使用
			ComboUser cu = dao.findComboUserByComboId(combo.getId());
			if(combo!=null&&cu==null){
				if("0".equals(combo.getComboStatus())){
					combo.setComboStatus("1");
				}else if("1".equals(combo.getComboStatus())){
					combo.setComboStatus("0");
				}
				dao.mergeCombo(combo);
				return true;
			}else{
				return false;
			}
		}
		return true;
		
	}

	public List<ComboUserVO> findComboUsesrByPage(ComboUserVO comboUserVo,
			int pageNum, int numPerPage, String orderField,
			String orderDirection) {
		return dao.findComboUsesrByPage(comboUserVo, pageNum, numPerPage, orderField, orderDirection);
	}

	public int getComboUserCount(ComboUserVO comboUserVo) {
		return dao.getComboUserCount(comboUserVo);
	}

}
