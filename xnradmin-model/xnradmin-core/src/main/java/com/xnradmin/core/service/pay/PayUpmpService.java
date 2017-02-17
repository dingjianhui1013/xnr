/**
 *2012-5-11 上午9:00:02
 */
package com.xnradmin.core.service.pay;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cntinker.util.StringHelper;
import com.xnradmin.core.dao.CommonDAO;
import com.xnradmin.core.dao.pay.PayUpmpDAO;
import com.xnradmin.constant.ViewConstant;
import com.xnradmin.po.pay.PayUpmp;
import com.xnradmin.vo.pay.PayUpmpVO;

/**
 * @autohr: xiang_liu
 * 
 */
@Service("PayUpmpService")
public class PayUpmpService {

	@Autowired
	private PayUpmpDAO dao;
	
	@Autowired
    private CommonDAO commonDao;

	static Log log = LogFactory.getLog(PayUpmpService.class);

	/**
	 * 
	 * @param po
	 * @return int
	 */
	public int save(PayUpmp po) {
		if (this.dao.findByProperty("serNo", po.getSerNo()).size() > 0) {
			return 1;
		}
		dao.save(po);
		return 0;
	}

	public PayUpmp findByid(String id) {
		return dao.findById(Long.valueOf(id));
	}

	/**	  
	 * @param po
	 * @return int
	 */
	public int modify(PayUpmp po) {
		if(po.getId()!=null){
			PayUpmp old = new PayUpmp();
			old = findByid(po.getId().toString());
			if(StringHelper.isNull(po.getVersion())){
				po.setVersion(old.getVersion());
			}
			if(StringHelper.isNull(po.getCharset())){
				po.setCharset(old.getCharset());
			}
			if(StringHelper.isNull(po.getSignMethod())){
				po.setSignMethod(old.getSignMethod());
			}
			if(StringHelper.isNull(po.getSignature())){
				po.setSignature(old.getSignature());
			}
			if(StringHelper.isNull(po.getTransType())){
				po.setTransType(old.getTransType());
			}
			if(StringHelper.isNull(po.getMerId())){
				po.setMerId(old.getMerId());
			}
			if(StringHelper.isNull(po.getTransStatus())){
				po.setTransStatus(old.getTransStatus());
			}
			if(StringHelper.isNull(po.getRespCode())){
				po.setRespCode(old.getRespCode());
			}
			if(StringHelper.isNull(po.getRespMsg())){
				po.setRespMsg(old.getRespMsg());
			}
			if(StringHelper.isNull(po.getSerNo())){
				po.setSerNo(old.getSerNo());
			}
			if(po.getOrderId()==null){
				po.setOrderId(old.getOrderId());
			}
			if(po.getOrderType()==null){
				po.setOrderType(old.getOrderType());
			}
			if(StringHelper.isNull(po.getOrderTypeName())){
				po.setOrderTypeName(old.getOrderTypeName());
			}
			if(po.getOrderTime()==null){
				po.setOrderTime(old.getOrderTime());
			}
			if(StringHelper.isNull(po.getSettleAmount())){
				po.setSettleAmount(old.getSettleAmount());
			}
			if(StringHelper.isNull(po.getSettleCurrency())){
				po.setSettleCurrency(old.getSettleCurrency());
			}
			if(po.getSettleDate()==null){
				po.setSettleDate(old.getSettleDate());
			}
			if(StringHelper.isNull(po.getExchangeRate())){
				po.setExchangeRate(old.getExchangeRate());
			}
			if(po.getExchangeDate()==null){
				po.setExchangeDate(old.getExchangeDate());
			}
			if(StringHelper.isNull(po.getMerReserved())){
				po.setMerReserved(old.getMerReserved());
			}
			if(StringHelper.isNull(po.getReqReserved())){
				po.setReqReserved(old.getReqReserved());
			}
			if(StringHelper.isNull(po.getSysReserved())){
				po.setSysReserved(old.getSysReserved());
			}
			po.setModifytime(new Timestamp(System.currentTimeMillis()));
			this.dao.merge(po);
		}else{
			return 1;
		}
		return 0;
	}

	public void del(String id){
		PayUpmp po = this.dao.findById(Long.valueOf(id));
        this.dao.delete(po);
    }
	
	public int removePayUpmpId(String id) {
		return dao.removePayUpmpId(Long.valueOf(id));
	}
	
	
	/**
	 * @param brandname
	 * @return int
	 */
	public int getCount(String serNo, String orderId, String orderType, 
			String settleAmount, String startOrderTime, String endOrderTime) {
		String hql = "select count(*) from PayUpmp where 1=1";
		if (!StringHelper.isNull(serNo)) {
			hql = hql + " serNo = "+ serNo ;
		}
		if (!StringHelper.isNull(orderId)) {
			hql = hql + " orderId = "+ orderId ;
		}
		if (!StringHelper.isNull(orderType)) {
			hql = hql + " orderType = "+ orderType ;
		}
		if (!StringHelper.isNull(settleAmount)) {
			hql = hql + " settleAmount = "+ settleAmount ;
		}
		if (!StringHelper.isNull(startOrderTime)) {
			hql = hql + " orderTime >='"+ startOrderTime +"'" ;
		}
		if (!StringHelper.isNull(endOrderTime)) {
			hql = hql + " orderTime <='"+ endOrderTime +"'" ;
		}
		return this.commonDao.getNumberOfEntitiesWithHql(hql);
	}
	
	/**
	 * 
	 * @param firstletter
	 * @param curPage
	 * @param pageSize
	 * @param orderField
	 * @param direction
	 * @return List<GetOrgListVO>
	 */
	public List<PayUpmpVO> listVO(String serNo, String orderId, String orderType, 
			String settleAmount, String startOrderTime, String endOrderTime,
			int curPage, int pageSize, String orderField, String direction) {
		String hql = "from PayUpmp";
		if (!StringHelper.isNull(serNo)) {
			hql = hql + " serNo = "+ serNo ;
		}
		if (!StringHelper.isNull(orderId)) {
			hql = hql + " orderId = "+ orderId ;
		}
		if (!StringHelper.isNull(orderType)) {
			hql = hql + " orderType = "+ orderType ;
		}
		if (!StringHelper.isNull(settleAmount)) {
			hql = hql + " settleAmount = "+ settleAmount ;
		}
		if (!StringHelper.isNull(startOrderTime)) {
			hql = hql + " orderTime >='"+ startOrderTime +"'" ;
		}
		if (!StringHelper.isNull(endOrderTime)) {
			hql = hql + " orderTime <='"+ endOrderTime +"'" ;
		}
		if (!StringHelper.isNull(orderField) && !StringHelper.isNull(direction)) {
			hql = hql + " order by " + orderField + " " + direction;
		}else{
			hql +=" order by id desc";
		}
		List l = commonDao.getEntitiesByPropertiesWithHql(hql, curPage, pageSize); // .findByQuerySplitPage(hql,curPage,pageSize);
		List<PayUpmpVO> resList = new ArrayList<PayUpmpVO>();
		for (int i = 0; i < l.size(); i++) {
			PayUpmp po = (PayUpmp) l.get(i);
			PayUpmpVO vo = new PayUpmpVO();
			vo.setPayUpmpid(po.getId().toString());
			vo.setVersion(po.getVersion());
			vo.setCharset(po.getCharset());
			vo.setSignMethod(po.getSignMethod());
			vo.setSignature(po.getSignature());
			vo.setTransType(po.getTransType());
			vo.setMerId(po.getMerId());
			vo.setTransStatus(po.getTransStatus());
			vo.setRespCode(po.getRespCode());
			vo.setRespMsg(po.getRespMsg());
			vo.setSerNo(po.getSerNo());
			if(po.getOrderId()!=null){
				vo.setOrderId(po.getOrderId().toString());
			}
			if(po.getOrderType()!=null){
				vo.setOrderType(po.getOrderType().toString());
			}
			vo.setOrderTypeName(po.getOrderTypeName());
			if(po.getOrderTime()!=null){
				vo.setOrderTime(StringHelper.getSystime(
                    ViewConstant.PAGE_DATE_FORMAT_SEC,po.getOrderTime().getTime()));
			}
			vo.setSettleAmount(po.getSettleAmount());
			vo.setSettleCurrency(po.getSettleCurrency());
			if(po.getSettleDate()!=null){
				vo.setSettleDate(StringHelper.getSystime(
	                    ViewConstant.PAGE_DATE_FORMAT_SEC,po.getSettleDate().getTime()));
			}
			vo.setExchangeRate(po.getExchangeRate());
			if(po.getExchangeDate()!=null){
				vo.setExchangeDate(StringHelper.getSystime(
	                    ViewConstant.PAGE_DATE_FORMAT_SEC,po.getExchangeDate().getTime()));
			}
			vo.setMerReserved(po.getMerReserved());
			vo.setReqReserved(po.getReqReserved());
			vo.setSysReserved(po.getSysReserved());
			resList.add(vo);
		}
		return resList;
	}

	/**
	 * @return List<PayUpmp>
	 */
	public List<PayUpmp> listAll() {
		return dao.findAll();
	}

}
