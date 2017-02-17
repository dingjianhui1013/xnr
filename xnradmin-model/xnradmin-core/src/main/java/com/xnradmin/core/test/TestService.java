/**
 *2014年8月14日 下午4:50:51
 */
package com.xnradmin.core.test;

import org.apache.log4j.Logger;

import com.xnradmin.core.service.common.status.StatusService;
import com.xnradmin.core.service.mall.order.PurchaseService;
import com.xnradmin.core.util.SpringBase;
import com.xnradmin.dto.client.SyncDTOAck;
import com.xnradmin.po.common.status.Status;
import com.xnradmin.po.mall.order.Purchase;

/**
 * @author: liubin
 * 
 */
public class TestService {
	private static Logger log = Logger.getLogger(TestService.class);
	private static void testStatus() {
		StatusService statusService = (StatusService) SpringBase.getCtx()
				.getBean("StatusService");
		Status status = statusService.find(SyncDTOAck.class, "status", "WX接口IO异常");
		log.debug(status);
	}

	private static void testPurchase() {
		PurchaseService purchaseService = (PurchaseService) SpringBase.getCtx()
				.getBean("PurchaseService");
		int purchase = purchaseService.save("100000000000000079");
		log.debug(purchase);
	}
	
	public static void main(String[] args) throws Exception {
		//testStatus();
		testPurchase();
	}
}
