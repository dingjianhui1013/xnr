package com.xnradmin.core.action.alipay;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.xnradmin.core.pay.alipay.util.AlipayNotify;
import com.xnradmin.core.service.business.order.BusinessOrderRecordService;
import com.xnradmin.core.service.pay.AlipayService;
import com.xnradmin.po.business.BusinessOrderRecord;
import com.xnradmin.po.pay.Reconciliation;

@Controller
@Scope("prototype")
@Namespace("/page/alipay")
@ParentPackage("json-default")
public class AlipayAction {

	private static Logger log = Logger.getLogger(AlipayAction.class);
	@Autowired
	private BusinessOrderRecordService orderRecordService;
	
	@Autowired
	private AlipayService alipayService;
	public BusinessOrderRecordService getOrderRecordService() {
		return orderRecordService;
	}


	public void setOrderRecordService(BusinessOrderRecordService orderRecordService) {
		this.orderRecordService = orderRecordService;
	}


	@Action(value="notify")
	public void notiFy() throws Exception
	{
		//获取支付宝POST过来反馈信息
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse out = ServletActionContext.getResponse();
		Map<String,String> params = new HashMap<String,String>();
		Map requestParams = request.getParameterMap();
		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i]
						: valueStr + values[i] + ",";
			}
			//乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
			//valueStr = new String(valueStr.getBytes("ISO-8859-1"), "gbk");
			params.put(name, valueStr);
		}
		
		//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
		//商户订单号

		String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");

		//支付宝交易号

		String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");

		//交易状态
		String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"),"UTF-8");

		//交易支付时间
		String gmt_payment  = new String(request.getParameter("gmt_payment").getBytes("ISO-8859-1"),"UTF-8");
		
		//买家支付宝账号
		String buyer_id = new String(request.getParameter("buyer_id").getBytes("ISO-8859-1"),"UTF-8");
		//买家支付宝号
		String buyer_email = new String(request.getParameter("buyer_email").getBytes("ISO-8859-1"),"UTF-8");
		
		//支付金额
		String total_fee = new String(request.getParameter("total_fee").getBytes("ISO-8859-1"),"UTF-8");
		
		//卖家支付宝账号
		String seller_id = new String(request.getParameter("seller_id").getBytes("ISO-8859-1"),"UTF-8");
		//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//

		if(AlipayNotify.verify(params)){//验证成功
			//////////////////////////////////////////////////////////////////////////////////////////
			//请在这里加上商户的业务逻辑程序代码

			//——请根据您的业务逻辑来编写程序（以下代码仅作参考）——
			
			if(trade_status.equals("TRADE_FINISHED")){
				//判断该笔订单是否在商户网站中已经做过处理
				BusinessOrderRecord orderRecord = orderRecordService.findByOutOderNo(out_trade_no);
				if(orderRecord.getTotalPrice().equals(total_fee))
				{
					if(!orderRecord.getOperateStatusName().equals("处理完成"))
					{
						orderRecord.setPaymentTime(Timestamp.valueOf(gmt_payment));
						orderRecord.setOperateStatusName("处理完成");
						orderRecordService.modify(orderRecord);
						Reconciliation reconciliation = new Reconciliation();
						reconciliation.setAlipayTradNo(trade_no);
						reconciliation.setTradeStatus("完成");
						reconciliation.setBuyerId(buyer_id);
						reconciliation.setOprderRecordId(orderRecord.getId());
						reconciliation.setBuyerMail(buyer_email);
						reconciliation.setIsRefund("yes");
						alipayService.saveRecon(reconciliation);
					}
				}
					//如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
				
					//请务必判断请求时的total_fee、seller_id与通知时获取的total_fee、seller_id为一致的
				
					//如果有做过处理，不执行商户的业务程序
					
				//注意：
				//退款日期超过可退款期限后（如三个月可退款），支付宝系统发送该交易状态通知
			} else if (trade_status.equals("TRADE_SUCCESS")){
				//判断该笔订单是否在商户网站中已经做过处理
					//如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
					//请务必判断请求时的total_fee、seller_id与通知时获取的total_fee、seller_id为一致的
					//如果有做过处理，不执行商户的业务程序
				BusinessOrderRecord orderRecord = orderRecordService.findByOutOderNo(out_trade_no);
				if(orderRecord.getTotalPrice().equals(total_fee))
				{
					if(!orderRecord.getOperateStatusName().equals("处理完成"))
					{
						orderRecord.setPaymentTime(Timestamp.valueOf(gmt_payment));
						orderRecord.setOperateStatusName("处理完成");
						orderRecordService.modify(orderRecord);
						Reconciliation reconciliation = new Reconciliation();
						reconciliation.setAlipayTradNo(trade_no);
						reconciliation.setTradeStatus("完成");
						reconciliation.setBuyerId(buyer_id);
						reconciliation.setOprderRecordId(orderRecord.getId());
						reconciliation.setBuyerMail(buyer_email);
						reconciliation.setIsRefund("no");
						alipayService.saveRecon(reconciliation);
					}
				}
				//注意：
				//付款完成后，支付宝系统发送该交易状态通知
			}else if(trade_status.equals("TRADE_PENDING"))
			{
				BusinessOrderRecord orderRecord = orderRecordService.findByOutOderNo(out_trade_no);
				if(orderRecord.getTotalPrice().equals(total_fee))
				{
					if(!orderRecord.getOperateStatusName().equals("等待收款"))
					{
						orderRecord.setPaymentTime(Timestamp.valueOf(gmt_payment));
						orderRecord.setOperateStatusName("等待收款");
						orderRecordService.modify(orderRecord);
						Reconciliation reconciliation = new Reconciliation();
						reconciliation.setAlipayTradNo(trade_no);
						reconciliation.setTradeStatus("等待收款");
						reconciliation.setBuyerId(buyer_id);
						reconciliation.setOprderRecordId(orderRecord.getId());
						reconciliation.setBuyerMail(buyer_email);
						reconciliation.setIsRefund("no");
						alipayService.saveRecon(reconciliation);
					}
				}
			}else if(trade_status.equals("WAIT_BUYER_PAY"))
			{
				BusinessOrderRecord orderRecord = orderRecordService.findByOutOderNo(out_trade_no);
				if(orderRecord.getTotalPrice().equals(total_fee))
				{
					if(!orderRecord.getOperateStatusName().equals("等待买家付款"))
					{
						orderRecord.setPaymentTime(Timestamp.valueOf(gmt_payment));
						orderRecord.setOperateStatusName("等待买家付款");
						orderRecordService.modify(orderRecord);
						Reconciliation reconciliation = new Reconciliation();
						reconciliation.setAlipayTradNo(trade_no);
						reconciliation.setTradeStatus("等待买家付款");
						reconciliation.setBuyerId(buyer_id);
						reconciliation.setOprderRecordId(orderRecord.getId());
						reconciliation.setBuyerMail(buyer_email);
						reconciliation.setIsRefund("no");
						alipayService.saveRecon(reconciliation);
					}
				}
			}

			//——请根据您的业务逻辑来编写程序（以上代码仅作参考）——
			out.getWriter().print("success");	//请不要修改或删除

			//////////////////////////////////////////////////////////////////////////////////////////
		}else{//验证失败
			out.getWriter().print("fail");
		}
	}
}
