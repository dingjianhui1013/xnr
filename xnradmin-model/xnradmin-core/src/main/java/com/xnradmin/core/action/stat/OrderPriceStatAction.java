/**
 *2012-5-10 下午3:52:29
 */
package com.xnradmin.core.action.stat;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.cntinker.util.StringHelper;
import com.xnradmin.constant.StrutsResMSG;
import com.xnradmin.core.action.ParentAction;
import com.xnradmin.core.service.common.status.StatusService;
import com.xnradmin.core.service.mall.commodity.CategoryService;
import com.xnradmin.core.service.stat.OrderPriceStatService;
import com.xnradmin.core.service.stat.RawMaterialStatService;
import com.xnradmin.po.common.status.Status;
import com.xnradmin.po.dishes.Dish;
import com.xnradmin.po.dishes.RawMaterial;
import com.xnradmin.po.mall.commodity.Category;
import com.xnradmin.po.mall.commodity.Goods;
import com.xnradmin.po.mall.order.OrderRecord;
import com.xnradmin.po.mall.order.Purchase;

/**
 * @autohr: xiang_liu
 * 
 */
@Controller
@Scope("prototype")
@Namespace("/page/stat/order")
@ParentPackage("json-default")
public class OrderPriceStatAction extends ParentAction {

	@Autowired
	private StatusService statusService;
	
	@Autowired
	private OrderPriceStatService orderPriceStatService;
	
	@Override
	public boolean isPublic() {
		return false;
	};

	static Log log = LogFactory.getLog(OrderPriceStatAction.class);
	
	private String clientUserId; //用户ID
	private String clientUserMobile; //用户手机号
	private String userRealMobile; //收货人手机号
	private String userRealName; //收货人名称
	private String orderRecordId; //订单ID
	private String orderNo; //订单号
	private String wxOpenId; //微信ID
	private String paymentProvider; //支付模式
	private String paymentStatus; //支付状态
	private String deliveryStatus; //配送状态
	private String operateStatus; //处理状态
    private String createStartTime; //搜索起始时间
    private String createEndTime; //搜索结束时间
	private List<String[]> priceList;
	private List<Status> paymentProviderList;
	private List<Status> paymentStatusList;
	private List<Status> deliveryStatusList;
	private List<Status> operateStatusList;
	
	public String getClientUserId() {
		return clientUserId;
	}

	public void setClientUserId(String clientUserId) {
		this.clientUserId = clientUserId;
	}

	public String getClientUserMobile() {
		return clientUserMobile;
	}

	public void setClientUserMobile(String clientUserMobile) {
		this.clientUserMobile = clientUserMobile;
	}

	public String getUserRealMobile() {
		return userRealMobile;
	}

	public void setUserRealMobile(String userRealMobile) {
		this.userRealMobile = userRealMobile;
	}

	public String getUserRealName() {
		return userRealName;
	}

	public void setUserRealName(String userRealName) {
		this.userRealName = userRealName;
	}

	public String getOrderRecordId() {
		return orderRecordId;
	}

	public void setOrderRecordId(String orderRecordId) {
		this.orderRecordId = orderRecordId;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getWxOpenId() {
		return wxOpenId;
	}

	public void setWxOpenId(String wxOpenId) {
		this.wxOpenId = wxOpenId;
	}

	public String getPaymentProvider() {
		return paymentProvider;
	}

	public void setPaymentProvider(String paymentProvider) {
		this.paymentProvider = paymentProvider;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public String getDeliveryStatus() {
		return deliveryStatus;
	}

	public void setDeliveryStatus(String deliveryStatus) {
		this.deliveryStatus = deliveryStatus;
	}

	public String getOperateStatus() {
		return operateStatus;
	}

	public void setOperateStatus(String operateStatus) {
		this.operateStatus = operateStatus;
	}

	public String getCreateStartTime() {
		return createStartTime;
	}

	public void setCreateStartTime(String createStartTime) {
		this.createStartTime = createStartTime;
	}

	public String getCreateEndTime() {
		return createEndTime;
	}

	public void setCreateEndTime(String createEndTime) {
		this.createEndTime = createEndTime;
	}

	public List<String[]> getPriceList() {
		return priceList;
	}

	public void setPriceList(List<String[]> priceList) {
		this.priceList = priceList;
	}

	public List<Status> getPaymentProviderList() {
		return paymentProviderList;
	}

	public void setPaymentProviderList(List<Status> paymentProviderList) {
		this.paymentProviderList = paymentProviderList;
	}

	public List<Status> getPaymentStatusList() {
		return paymentStatusList;
	}

	public void setPaymentStatusList(List<Status> paymentStatusList) {
		this.paymentStatusList = paymentStatusList;
	}

	public List<Status> getDeliveryStatusList() {
		return deliveryStatusList;
	}

	public void setDeliveryStatusList(List<Status> deliveryStatusList) {
		this.deliveryStatusList = deliveryStatusList;
	}

	public List<Status> getOperateStatusList() {
		return operateStatusList;
	}

	public void setOperateStatusList(List<Status> operateStatusList) {
		this.operateStatusList = operateStatusList;
	}

	/*
	 * 加载支付渠道
	 * */
	private void findPaymentProviderList(){
		this.paymentProviderList = statusService.find(OrderRecord.class,"paymentProvider");
	}
	
	/*
	 * 加载支付状态
	 * */
	private void findPaymentStatusList(){
		this.paymentStatusList = statusService.find(OrderRecord.class,"paymentStatus");
	}
	
	/*
	 * 加载配送状态
	 * */
	private void findDeliveryStatusList(){
		this.deliveryStatusList = statusService.find(OrderRecord.class,"deliveryStatus");
	}
	
	/*
	 * 加载处理状态
	 * */
	private void findOperateStatusList(){
		this.operateStatusList = statusService.find(OrderRecord.class,"operateStatus");
	}
	
	/**
	 * 材料清单汇总
	 * 
	 * @return
	 */
	@Action(value = "sumPrice", results = {
			@Result(name = StrutsResMSG.SUCCESS, location = "/stat/order/sumPrice.jsp"),
			@Result(name = StrutsResMSG.FAILED, location = "/stat/order/sumPrice.jsp") })
	public String getRawMaterialCount() {
		setDateTime();
		findPaymentProviderList();
		findPaymentStatusList();
		findDeliveryStatusList();
		findOperateStatusList();
		OrderRecord po = new OrderRecord();
		if(!StringHelper.isNull(clientUserId)){
			po.setClientUserId(Integer.parseInt(clientUserId));
		}
		po.setClientUserMobile(clientUserMobile);
		if(!StringHelper.isNull(orderRecordId)){
			po.setId(Long.valueOf(orderRecordId));
		}
		po.setOrderNo(orderNo);
		po.setUserRealMobile(userRealMobile);
		po.setUserRealName(userRealName);
		po.setWxOpenId(wxOpenId);
		if(!StringHelper.isNull(paymentProvider)){
			po.setPaymentProvider(Integer.parseInt(paymentProvider));
		}
		if(!StringHelper.isNull(paymentStatus)){
			po.setPaymentStatus(Integer.parseInt(paymentStatus));
		}
		if(!StringHelper.isNull(deliveryStatus)){
			po.setDeliveryStatus(Integer.parseInt(deliveryStatus));
		}
		if(!StringHelper.isNull(operateStatus)){
			po.setOperateStatus(Integer.parseInt(operateStatus));
		}
		
		List<Object[]> list = orderPriceStatService.findOrderPrice(createStartTime, createEndTime, 
				po, getPageNum(), getNumPerPage(), orderField, orderDirection);
		this.totalCount = list.size();
		priceList = new ArrayList<String[]>();
		for (int i = 0; i < list.size(); i++) {
			String[] content = new String[2];
			Object[] a = list.get(i);
			if (a == null)
				continue;
			content[0] = a[0] == null ? "0" : a[0].toString();
			content[1] = a[1] == null ? "0" : a[1].toString();
			BigDecimal b = new BigDecimal(content[0]);
			BigDecimal c = new BigDecimal(content[1]);
			content[0] = b.setScale(2, java.math.BigDecimal.ROUND_HALF_UP).toString();  
			content[1] = c.setScale(2, java.math.BigDecimal.ROUND_HALF_UP).toString();  
			priceList.add(content);
		}
		return StrutsResMSG.SUCCESS;
	}

	private void setDateTime() {
		// 设置默认时间
		if (StringHelper.isNull(createStartTime)
				&& StringHelper.isNull(createEndTime)) {
			this.createStartTime = StringHelper.getSystime("yyyy-MM-dd");
			createStartTime = createStartTime + " 00:00:00";
			this.createEndTime = StringHelper.getSystime("yyyy-MM-dd");
			createEndTime = createEndTime + " 23:59:59";
		}
	}

}
