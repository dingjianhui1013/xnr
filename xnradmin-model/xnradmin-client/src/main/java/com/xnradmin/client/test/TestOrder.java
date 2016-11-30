/**
 *2014年10月12日 下午11:22:03
 */
package com.xnradmin.client.test;

import java.sql.Timestamp;

import com.xnradmin.core.service.mall.order.OrderRecordService;
import com.xnradmin.core.util.SpringBase;
import com.xnradmin.po.mall.order.OrderRecord;

/**
 * @author: liubin
 *
 */
public class TestOrder {

	private static void testModifyOrder(String out_trade_no) {
		OrderRecordService orderService = (OrderRecordService) SpringBase
				.getCtx().getBean("OrderRecordService");
		OrderRecord order = orderService.findByOutOderNo(out_trade_no);
		
		order.setPaymentStatus(165);
		order.setPaymentStatusName("已支付");
		order.setOperateStatus(169);
		order.setOperateStatusName("处理中");
		order.setPaymentTime(new Timestamp(System.currentTimeMillis()));
		order.setOperateTime(new Timestamp(System.currentTimeMillis()));
		orderService.modify(order);
	}

	public static void main(String[] args) throws Exception {
		testModifyOrder("201410122229138957");
	}
}
