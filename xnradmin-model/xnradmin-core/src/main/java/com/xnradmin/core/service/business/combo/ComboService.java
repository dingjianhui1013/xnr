/**
 *2012-5-11 上午9:00:02
 */
package com.xnradmin.core.service.business.combo;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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
import com.xnradmin.po.business.PseudoOrders;
import com.xnradmin.po.common.status.Status;
import com.xnradmin.vo.business.BusinessOrderVO;
import com.xnradmin.vo.business.ComboGoodsVO;
import com.xnradmin.vo.business.ComboPlanVO;
import com.xnradmin.vo.business.ComboUserGoodsVO;
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

	public void modify(ComboVO comboVo, List<Status> statusList) {
		boolean isModify = false;
		//修改
		if(comboVo!=null&&comboVo.getCombo()!=null&&comboVo.getCombo().getId()!=null){
			isModify=true;
		}else{//新增
			isModify=false;
		}
		
		Map<String,List<ComboPlan>> tempPlan = new HashMap<>();//整理配送计划
		Map<Integer,Integer> goodsCount = new HashMap<>();//各类商品汇总
		
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
		/*if(isModify){
			//删除已有的套餐商品
			dao.deleteComboGoodsByComboId(combo.getId());
		}
		//保存套餐商品表
		for(ComboGoodsVO cvo:comboVo.getComboGoodsList()){
			BusinessGoods bg = cvo.getBusinessGoods();
			ComboGoods cg = cvo.getComboGoods();
			cg.setComboId(combo.getId());
			cg.setGoodsId(bg.getId());
			if(goodsCount.get(bg.getId())==null){
				goodsCount.put(bg.getId(), cg.getGoodsCount());				
			}else{
				goodsCount.put(bg.getId(), goodsCount.get(bg.getId())+cg.getGoodsCount());	
			}
			dao.saveComboGoods(cg);
		}*/
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
				//计划整理到templan
				String stringKey = cpvo.getComboCycleStr();
				List<ComboPlan> comboPlanList=null;
				if(tempPlan.get(stringKey)==null){
					comboPlanList = new ArrayList<>();
				}else{
					comboPlanList = tempPlan.get(stringKey);
				}
				comboPlanList.add(cp);
				tempPlan.put(stringKey, comboPlanList);
			}else{
				cp.setComboPlanType("0");
				cp.setComboPlanCycle("-1");//没有周期
				String dateKey = cp.getComboPlanDate();
				//计划整理到templan
				List<ComboPlan> comboPlanList=null;
				if(tempPlan.get(dateKey)==null){
					comboPlanList = new ArrayList<>();
				}else{
					comboPlanList = tempPlan.get(dateKey);
				}
				comboPlanList.add(cp);
				tempPlan.put(dateKey, comboPlanList);
			}
			cp.setComboId(combo.getId());
			cp.setGoodsId(bg.getId());
			cp.setComboPlanCreatTime(new Timestamp(System.currentTimeMillis()));
			dao.saveComboPlan(cp);
		}
		
		// 最后修改套餐的总次数  生成伪订单  伪订单 主要存储 订单时间 天为单位 相对时间 便于生成用户的伪订单
		// 循环套餐周期   第一天定义为 周一 
		int totalDay = 0;
		Map<Integer,Status> statusMap = new HashMap<Integer,Status>();
		for(Status s:statusList){
			statusMap.put(s.getId(), s);
		}
		Status comboCycleStatus = statusMap.get(Integer.parseInt(combo.getComboCycleStatus()));
		if(comboCycleStatus.getRemark()!=null){
			totalDay=Integer.parseInt(comboCycleStatus.getRemark());					
		}
		if(isModify){
			//删除已有的套餐伪订单
			dao.deletePseudoOrdersByComboId(combo.getId());
		}
		int timesTotal=0;
		Iterator<String> it = tempPlan.keySet().iterator();
		while(it.hasNext()){
			String s = it.next();
			/** 方法一 依照套餐计划 按时序 生成一系列的伪订单表 
			 * 
			 *  每一条伪订单 含有相对于套餐开始时间的时间差
			 *  定时任务按照时间差判断 改下哪一条订单
			 *  问题：订单次数是根据计划生成的 不确定性；必须明确套餐的开始时间 这个要根据周计划月计划固定周期区分 firstDay firstWeek firstMonth
			 */
			/*for(int i=0;i<totalDay;i++){
				if(s.contains("#")){
					String[] statusArray = s.split("#");
					if("1".equals(statusArray[0])){//固定周期
						int parentCicle = Integer.parseInt(statusMap.get(Integer.parseInt(statusArray[1])).getRemark());//每3天
						if(i%parentCicle==(parentCicle-1)){
							PseudoOrders po = new PseudoOrders();
							List<ComboPlan> planList = tempPlan.get(s);
							String planIds = "";
							for(ComboPlan c:planList){
								if("".equals(planIds)){
									planIds+=c.getId();
								}else{
									planIds+=","+c.getId();
								}
							}
							po.setComboPlanIds(planIds);
							po.setDayKey(s);
							po.setOrderUnit(0);
							po.setOrderDay(i);
							po.setComboId(combo.getId());
							dao.savePseudoOrders(po);
							timesTotal++;
						}
					}else if("2".equals(statusArray[0])){//固定周期固定时间
						int parentCicle = Integer.parseInt(statusMap.get(Integer.parseInt(statusArray[1])).getRemark());//一周 7  一月  30
						int chileCicle = Integer.parseInt(statusMap.get(Integer.parseInt(statusArray[2])).getRemark());//周二  2   月初   1
						//i%7==1 每一周周二  type 周
						//i%30==0 月初 type 月
						
						if(i%parentCicle==(chileCicle-1)){
							PseudoOrders po = new PseudoOrders();
							List<ComboPlan> planList = tempPlan.get(s);
							String planIds = "";
							for(ComboPlan c:planList){
								if("".equals(planIds)){
									planIds+=c.getId();
								}else{
									planIds+=","+c.getId();
								}
							}
							po.setComboPlanIds(planIds);
							po.setDayKey(s);
							if(parentCicle>=7&&parentCicle<30){
								po.setOrderUnit(1);
							}else if(parentCicle>=30&&parentCicle<90){
								po.setOrderUnit(2);
							}else if(parentCicle>=90&&parentCicle<360){
								po.setOrderUnit(3);
							}else if(parentCicle==365){
								po.setOrderUnit(4);
							}
							po.setOrderDay(i);
							po.setComboId(combo.getId());
							dao.savePseudoOrders(po);
							timesTotal++;
						}
					}
				}else{//固定时间类型  直接添加一条伪订单
					
					PseudoOrders po = new PseudoOrders();
					List<ComboPlan> planList = tempPlan.get(s);
					String planIds = "";
					for(ComboPlan c:planList){
						if("".equals(planIds)){
							planIds+=c.getId();
						}else{
							planIds+=","+c.getId();
						}
					}
					po.setComboPlanIds(planIds);
					po.setDayKey(s);
					po.setOrderUnit(0);
					po.setComboId(combo.getId());
					dao.savePseudoOrders(po);
					timesTotal++;
					it.remove();
					break;
				}
			}*/
			/** 方法二 依照套餐计划 按任务 生成一系列的伪订单表 
			 * 
			 *  每一条伪订单 都是一个伪订单任务
			 *  定时任务按照时间  判断该伪订单是否下订单
			 *  问题：订单次数是固定的 时间不一定准 完成固定的次数
			 */
			int baseTime = 0;//一个月按照4周 一年按照52周 一季按照13周
			switch (totalDay) {
			case 30:
				baseTime=4;
				break;
			case 120:
				baseTime=13;
				break;
			case 365:
				baseTime=52;
				break;
			default:
				break;
			}
				
			if(s.contains("#")){
				String[] statusArray = s.split("#");
				if("1".equals(statusArray[0])){//固定周期  不做处理
						/*int parentCicle = Integer.parseInt(statusMap.get(Integer.parseInt(statusArray[1])).getRemark());//每3天
						if(i%parentCicle==(parentCicle-1)){
							PseudoOrders po = new PseudoOrders();
							List<ComboPlan> planList = tempPlan.get(s);
							String planIds = "";
							for(ComboPlan c:planList){
								if("".equals(planIds)){
									planIds+=c.getId();
								}else{
									planIds+=","+c.getId();
								}
							}
							po.setComboPlanIds(planIds);
							po.setDayKey(s);
							po.setOrderUnit(0);
							po.setOrderDay(i);
							po.setComboId(combo.getId());
							dao.savePseudoOrders(po);
							timesTotal++;
						}*/
				}else if("2".equals(statusArray[0])){//固定周期固定时间
						int parentCicle = Integer.parseInt(statusMap.get(Integer.parseInt(statusArray[1])).getRemark());//一周 7  一月  30
						int chileCicle = Integer.parseInt(statusMap.get(Integer.parseInt(statusArray[2])).getRemark());//周二  2   月初   1
						//i%7==1 每一周周二  type 周
						//i%30==0 月初 type 月
						PseudoOrders po = new PseudoOrders();
						List<ComboPlan> planList = tempPlan.get(s);
						String planIds = "";
						for(ComboPlan c:planList){
							if("".equals(planIds)){
								planIds+=c.getId();
							}else{
								planIds+=","+c.getId();
							}
						}
						po.setComboPlanIds(planIds);
						po.setDayKey(s);
						if(parentCicle>=7&&parentCicle<30){
							po.setOrderUnit(1);
						}else if(parentCicle>=30&&parentCicle<90){
							po.setOrderUnit(2);
						}else if(parentCicle>=90&&parentCicle<360){
							po.setOrderUnit(3);
						}else if(parentCicle==365){
							po.setOrderUnit(4);
						}
						po.setOrderDay(chileCicle);
						po.setComboId(combo.getId());
						dao.savePseudoOrders(po);
						
						timesTotal+=baseTime;
					}
				}else{//固定时间类型  直接添加一条伪订单
					
					PseudoOrders po = new PseudoOrders();
					List<ComboPlan> planList = tempPlan.get(s);
					String planIds = "";
					for(ComboPlan c:planList){
						if("".equals(planIds)){
							planIds+=c.getId();
						}else{
							planIds+=","+c.getId();
						}
					}
					po.setComboPlanIds(planIds);
					po.setDayKey(s);
					po.setOrderUnit(0);
					po.setComboId(combo.getId());
					dao.savePseudoOrders(po);
					timesTotal++;
					it.remove();
				}
		
		}
	
		
		//套餐配送次数修改
		combo.setComboTimes(timesTotal);
		dao.mergeCombo(combo);
	}

	public ComboVO getComboVOById(ComboVO comboVo) {
		return dao.findById(comboVo.getCombo().getId());
	}

	public boolean delComboVOById(ComboVO comboVo) {
		if(comboVo!=null&&comboVo.getCombo()!=null&&comboVo.getCombo().getId()!=null){
			Combo combo=dao.findOneComboById(comboVo.getCombo().getId());
			//套餐未被使用
			List<ComboUser> cu = dao.findComboUserByComboId(combo.getId());
			if(combo!=null&&(cu==null||cu.size()<=0)){
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
			List<ComboUser> cu = dao.findComboUserByComboId(combo.getId());
			if(combo!=null&&(cu==null||cu.size()<=0)){
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
	public List<ComboVO> findAll() {
		List<ComboVO> list = dao.findAllCombo();
		return list;
	}

	public ComboVO findById(String comboId) {
		// TODO Auto-generated method stub
		return dao.findById(comboId);
		 
	}
	public List<ComboUserVO> findComboUsesrByPage(ComboUserVO comboUserVo,
			int pageNum, int numPerPage, String orderField,
			String orderDirection) {
		return dao.findComboUsesrByPage(comboUserVo, pageNum, numPerPage, orderField, orderDirection);
	}

	public int getComboUserCount(ComboUserVO comboUserVo) {
		return dao.getComboUserCount(comboUserVo);
	}

	public List<ComboUserVO> selectUserComboPseudoOrders() {
		return dao.selectUserComboPseudoOrders();
	}

	public Map<Integer, ComboPlanVO> findComboPlanByPlanId(Integer id) {
		Map<Integer, ComboPlanVO> cpMap = new HashMap<>();
		List<ComboPlanVO> l = dao.findComboPlanByPlanId(id);
		for(ComboPlanVO cp:l){
			cpMap.put(cp.getComboPlan().getId(), cp);
		}
		return cpMap;
	}

	public Map<Integer, ComboUserGoodsVO> findComboGoodsByComboUserId(Integer id) {
		Map<Integer, ComboUserGoodsVO> cgMap = new HashMap<>();
		List<ComboGoodsVO> bgList = dao.findComboGoodsByComboUserId(id);
		for(ComboGoodsVO cg:bgList){
			ComboUserGoodsVO cv = new ComboUserGoodsVO();
			cv.setComboGoodsVO(cg);
			cgMap.put(cg.getBusinessGoods().getId(), cv);
		}
		return cgMap;
	}

	public List<BusinessOrderVO> findBusinessOrderRelationVOByOrderId(
			Long orderId, Integer comboId) {
		return dao.findBusinessOrderRelationVOByOrderId(orderId,comboId);
	}

	public boolean modifyComboUser(ComboUserVO comboUserVo) {
		if(comboUserVo!=null&&comboUserVo.getComboUser()!=null&&comboUserVo.getComboUser().getId()!=null){
			//用户套餐存在
			ComboUser comboUser=dao.findComboUserById(comboUserVo.getComboUser().getId());
			if(comboUser!=null){
				//由于更改用户套餐状态也是这个方法 所以一下判断是否为更改用户状态
				if(comboUserVo.getComboUser().getComboUserStatus()==null){
					if(0==comboUser.getComboUserStatus()){
						comboUser.setComboUserStatus(1);
					}else if(1==comboUser.getComboUserStatus()){
						comboUser.setComboUserStatus(0);
					}
				}else{					
					comboUser = comboUserVo.getComboUser();
				}
				dao.mergeComboUser(comboUser);
				return true;
			}else{
				return false;
			}
		}
		return true;
	}
	public Combo findByCombo(String id) {
		Combo combo = dao.findOneComboById(Integer.parseInt(id));
		return combo;
	}
}
