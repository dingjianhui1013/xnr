package com.xnradmin.core.task;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xnradmin.client.service.front.ReceiptAddressService;
import com.xnradmin.core.service.business.combo.ComboService;
import com.xnradmin.core.service.business.order.BusinessOrderGoodsRelationService;
import com.xnradmin.core.service.business.order.BusinessOrderRecordService;
import com.xnradmin.core.service.common.status.StatusService;
import com.xnradmin.po.business.BusinessGoods;
import com.xnradmin.po.business.BusinessOrderGoodsRelation;
import com.xnradmin.po.business.BusinessOrderRecord;
import com.xnradmin.po.business.ComboPlan;
import com.xnradmin.po.business.ComboUser;
import com.xnradmin.po.business.PseudoOrders;
import com.xnradmin.po.common.status.Status;
import com.xnradmin.po.front.ReceiptAddress;
import com.xnradmin.po.mall.order.ShoppingCart;
import com.xnradmin.vo.business.ComboPlanVO;
import com.xnradmin.vo.business.ComboUserVO;
import com.xnradmin.vo.front.BusinessGoodsCartVo;

@Service(value = "autoMakeOrder")
public class MakeOrderTask {
	
	@Autowired
	ComboService comboService;
	
	@Autowired
	private BusinessOrderRecordService orderRecordService;
	
	@Autowired
	private ReceiptAddressService addressService;
	
	@Autowired
	private StatusService statusService;
	

	@Autowired
	private BusinessOrderGoodsRelationService orderGoodsRelationService;
	
    public void process() {
        this.makeOrderTask();
    }


    /**
     * 调用log的bash
     */
    @Transactional(readOnly = false)
    private void makeOrderTask() {
    	Calendar calendar = Calendar.getInstance(); 
    	//取出当天星期 月 年 日
//    	int year = calendar.get(Calendar.YEAR);
//    	int month = calendar.get(Calendar.MONTH); //从0开始
//    	int week = calendar.get(Calendar.DAY_OF_WEEK);
//    	int DAY_OF_MONTH = calendar.get(Calendar.DAY_OF_MONTH); //本月几号
//    	int DAY_OF_YEAR = calendar.get(Calendar.DAY_OF_YEAR);  //今年第几天
    	long todyTime = calendar.getTimeInMillis();
    	
    	int dayDiff = 0;
    	int weekDiff = 0;
    	int monthDiff = 0;
    	int yearDiff = 0;
    	
    	//遍历用户的套餐 如果套餐在时间期内 查看套餐伪订单计划 如果有明天的分配任务 生成一条子订单 
    	List<ComboUserVO> comboUserlist = comboService.selectUserComboPseudoOrders();
    	for(ComboUserVO cvo:comboUserlist){
    		//查询套餐伪订单 时间判别   
    		//根据套餐开始时间  计算第一次周计划时间到现在的天数，第一次月计划时间现在的天数，第一次年计划到现在的天数
    		// TODO  以后排除节假日 或者周末
    		ComboUser cu = cvo.getComboUser();
    		Timestamp dayTime = cu.getFirstDay();
    		Timestamp monthTime = cu.getFirstMonth();
    		Timestamp weekTime = cu.getFirstWeek();
    		Timestamp yearTime = cu.getFirstYear();
    		
    		//时间差
    		dayDiff   =  (int)(todyTime-dayTime.getTime())/(1000*3600*24)+1;
    		weekDiff  =  (int)(todyTime-weekTime.getTime())/(1000*3600*24)+1;
    		monthDiff =  (int)(todyTime-monthTime.getTime())/(1000*3600*24)+1;
    		yearDiff  =  (int)(todyTime-yearTime.getTime())/(1000*3600*24)+1;
    		
    		boolean isMake=false;
    		PseudoOrders po = cvo.getPseudoOrders();
    		//周期单位 1--周，2--月，3--季，4--年
    		int unit = po.getOrderUnit();
    		if(po.getOrderUnit()==1){//周
    			if(po.getOrderDay()==weekDiff){
    				isMake=true;
    			}
    		}else if(unit==2){//月
    			if(po.getOrderDay()==monthDiff){
    				isMake=true;
    			}
			}
    		
    		if(isMake){
    			//查出总订单
    			BusinessOrderRecord olde = orderRecordService.findByid(cu.getOrderId()+"");
    			//查出套餐计划
    			Map<Integer,ComboPlanVO> planMap = comboService.findComboPlanByPlanId(cvo.getCombo().getId());
    			//生成子订单 和订单商品
    			BusinessOrderRecord chileRecord = new BusinessOrderRecord();
    			chileRecord.setClientUserId(olde.getClientUserId());
    			chileRecord.setStaffId("2");
    			ReceiptAddress address = addressService.findByUserId(Long.parseLong(cu.getUserId()+""));
    			chileRecord.setOrderNo(null);
    			chileRecord.setClientUserName(address.getReceiptName());
    			chileRecord.setClientUserMobile(address.getTel());
    			chileRecord.setClientUserEmail(address.getEmail());
    		
    			chileRecord.setUserRealMobile(address.getTel());
    			chileRecord.setCountryId(null);
    			chileRecord.setCountryName(address.getCounty());
    			chileRecord.setProvinceId(null);
    			chileRecord.setProvinceName(address.getProvince());
    			chileRecord.setCityId(null);
    			chileRecord.setCityName(address.getCity());
    			chileRecord.setAreaId(null);
    			chileRecord.setAreaName(address.getDetailedAddress());
    			chileRecord.setUserRealAddress(address.getDetailedAddress());
    			chileRecord.setUserRealPlane(null);
    			chileRecord.setUserRealName(address.getReceiptName());
    			chileRecord.setUserRealPostcode(null);
    			chileRecord.setTheEarliestTime(null);
    			chileRecord.setTheLatestTime(null);
    			chileRecord.setBillTime(null);
    			chileRecord.setBillTimeName(null);
    			
    			String StrutsMessage = "";
    			Status statusCode = new Status();
    			chileRecord.setPaymentProvider(statusCode.getId());
    			chileRecord.setPaymentProviderName(statusCode.getStatusName());
    			chileRecord.setPaymentProviderName(statusCode.getStatusName());
    			
    			// 状态为待处理
    			chileRecord.setOperateStatus(203);
    			statusCode = statusService.findByid("203");
    			chileRecord.setOperateStatusName(statusCode.getStatusName());
    			chileRecord.setLogisticsCompanyId(1);
    			chileRecord.setLogisticsCompanyName(olde.getLogisticsCompanyName());
    			chileRecord.setPaymentStatus(200);
    			statusCode = statusService.findByid("200");
    			chileRecord.setPaymentStatusName(statusCode.getStatusName());
    			chileRecord
    					.setOperateTime(new Timestamp(System.currentTimeMillis()));
    			chileRecord
    					.setCreateTime(new Timestamp(System.currentTimeMillis()));
    			
    			
    			
    			chileRecord.setDeliveryStatus(207);
    			statusCode = statusService.findByid("207");
    			chileRecord.setDeliveryStatusName(statusCode.getStatusName());
    			chileRecord.setPrimaryConfigurationId(1);
    			chileRecord.setPrimaryConfigurationName(olde.getPrimaryConfigurationName());
    			chileRecord.setUserRealDescription(olde.getUserRealDescription());
    			chileRecord.setIsChild(cu.getOrderId());
    			Long newOrderRecordId = orderRecordService.save(chileRecord);
    			
    			String planIds = po.getComboPlanIds();
    			//orderRecordId = String.valueOf(newOrderRecordId);
    			
    			Integer totalCount = 0;
    			Float totalMoney = 0f;
    			if(planIds!=""){
    				String[] planId = planIds.split(",");
    				for(String plan:planId){
    					ComboPlanVO cp = planMap.get(Integer.parseInt(plan));
    					BusinessGoods bg = cp.getBusinessGoods();
    					ComboPlan comboPlan = cp.getComboPlan();
    					BusinessOrderGoodsRelation relation = new BusinessOrderGoodsRelation();
    					relation.setClientUserId(cu.getUserId());
    					double currtenPrice = bg.getGoodsOriginalPrice()*comboPlan.getGoodsNumber();
	   	        		relation.setCurrentPrice(Float.parseFloat(currtenPrice+""));
	   	        		relation.setCurrentPriceType(215);
	   	        		relation.setGoodsCount(comboPlan.getGoodsNumber());
	   	        		relation.setGoodsId(bg.getId());
	   	        		relation.setGoodsWeightId(bg.getGoodsWeightId());
	   	        		relation.setOrderGoodsRelationTime(new Timestamp(new Date().getTime()));
	   	        		relation.setOrderRecordId(newOrderRecordId);
	   	        		relation.setOriginalPrice(bg.getGoodsOriginalPrice());
	   	        		relation.setPurchasePrice(bg.getGoodsPurchasePrice());
	   	        		orderGoodsRelationService.save(relation);
	   	        		totalMoney+=Float.parseFloat(currtenPrice+"");
	   	        		totalCount+=comboPlan.getGoodsNumber();
    				}
    			}
    			
    			chileRecord.setTotalCount(totalCount);
    			chileRecord.setOriginalPrice(totalMoney);
    			chileRecord.setTotalPrice(totalMoney);
    			chileRecord.setPurchasePrice(totalMoney);
    			orderRecordService.modify(chileRecord);
    		}
    		System.out.println("--------------------");
    	}
    }
}