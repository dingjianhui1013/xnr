/**
 *2014年11月15日 下午11:43:24
 */
package com.xnradmin.stat.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.xnradmin.constant.StrutsResMSG;
import com.xnradmin.core.action.ParentAction;
import com.xnradmin.core.service.mall.order.OrderRecordService;
import com.xnradmin.vo.stat.OrderRecordVO;

/**
 * @author: liubin
 *
 */
@Controller
@Scope("prototype")
@Namespace("/page/model/stat/order")
@ParentPackage("json-default")
public class OrderModelStatAction extends ParentAction {

	private OrderRecordService orderRecordService;

	private OrderRecordVO orderRecordVO;

	@Action(value = "deliveryinfo", results = {
			@Result(name = StrutsResMSG.SUCCESS, location = "/stat/order/deliveryinfo.jsp"),
			@Result(name = StrutsResMSG.FAILED, location = "/stat/order/deliveryinfo.jsp") })
	public String deliveryinfo() {
		//orderRecordService.listVO(orderNo, orderRecordId, clientUserId, clientUserName, clientUserMobile, clientUserEmail, clientUserSex, clientUserType, clientUserTypeName, wxOpenId, staffId, userRealMobile, userRealPlane, userRealName, countryId, countryName, provinceId, provinceName, cityId, cityName, areaId, areaName, userRealAddress, userRealPostcode, userRealStartTime, userRealEndTime, paymentType, paymentTypeName, paymentStatus, paymentStatusName, paymentProvider, paymentProviderName, paymentStartTime, paymentEndTime, operateStartTime, operateEndTime, operateStatus, operateStatusName, createStartTime, createEndTime, originalPrice, totalPrice, logisticsCompanyId, logisticsCompanyName, deliveryStaffId, deliveryStaffName, deliveryStaffMobile, deliveryStartStartTime, deliveryStartEndTime, deliveryEndStartTime, deliveryEndEndTime, deliveryStatus, deliveryStatusName, sourceId, sourceName, sourceType, sourceTypeName, serNo, sellerId, sellerName, cusId, cusName, primaryConfigurationId, primaryConfigurationName, curPage, pageSize, orderField, direction)
		return StrutsResMSG.SUCCESS;
	}
	
	

	@Override
	public boolean isPublic() {
		// TODO Auto-generated method stub
		return false;
	}

	public OrderRecordVO getOrderRecordVO() {
		return orderRecordVO;
	}

	public void setOrderRecordVO(OrderRecordVO orderRecordVO) {
		this.orderRecordVO = orderRecordVO;
	}
}
