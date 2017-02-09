package com.xnradmin.core.action.alipay;

import java.io.IOException;
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
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.xnradmin.constant.StrutsResMSG;
import com.xnradmin.core.pay.alipay.util.AlipayNotify;
import com.xnradmin.core.service.business.order.BusinessOrderRecordService;
import com.xnradmin.core.service.pay.AlipayService;
import com.xnradmin.po.business.BusinessOrderRecord;
import com.xnradmin.po.pay.Alipay;
import com.xnradmin.po.pay.Reconciliation;

@Controller
@Scope("prototype")
@Namespace("/page/alipay")
@ParentPackage("json-default")
public class AlipayAction {

	private static Logger log = Logger.getLogger(AlipayAction.class);
	private String orderId;
	private Alipay alipay;
	
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public Alipay getAlipay() {
		return alipay;
	}
	public void setAlipay(Alipay alipay) {
		this.alipay = alipay;
	}
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


	@Action(value="notify",results={@Result(name = StrutsResMSG.SUCCESS,type="redirect", location = "/front/index.action"),@Result(name = StrutsResMSG.FAILED,type="redirect", location = "/front/index.action")})
	public String notiFy() throws Exception
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
				changePayStatus(out_trade_no, "待处理", total_fee, gmt_payment, "已支付",trade_no,"完成", buyer_id, buyer_email,"yes");
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
				changePayStatus(out_trade_no, "待处理", total_fee, gmt_payment, "已支付",trade_no,"完成", buyer_id, buyer_email,"no");
				//注意：
				//付款完成后，支付宝系统发送该交易状态通知
			}else if(trade_status.equals("TRADE_PENDING"))
			{
				changePayStatus(out_trade_no, "等待收款", total_fee, gmt_payment, "未收款",trade_no,"等待收款", buyer_id, buyer_email,"no");
			}else if(trade_status.equals("WAIT_BUYER_PAY"))
			{
				changePayStatus(out_trade_no, "等待买家付款", total_fee, gmt_payment, "未支付",trade_no,"等待买家付款", buyer_id, buyer_email,"no");
			}

			//——请根据您的业务逻辑来编写程序（以上代码仅作参考）——
			out.getWriter().print("success");	//请不要修改或删除
			log.debug("alipay notify success");
			return StrutsResMSG.SUCCESS;
			//////////////////////////////////////////////////////////////////////////////////////////
		}else{//验证失败
			out.getWriter().print("fail");
			log.debug("alipay notify fail");
			return StrutsResMSG.FAILED;
		}
	}
	@Action(value = "returUrl", results = {@Result(name = StrutsResMSG.SUCCESS,type="redirect", location = "/front/index.action"),@Result(name = StrutsResMSG.FAILED,type="redirect", location = "/front/index.action")})
	public String returUrl() throws Exception
	{
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
//	 		valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
			params.put(name, valueStr);
		}
		
		//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
		//商户订单号

		String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");

		//支付宝交易号

		String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");

		//交易状态
		String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"),"UTF-8");

		//通知时间
		String notify_time  = new String(request.getParameter("notify_time").getBytes("ISO-8859-1"),"UTF-8");
		
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
				changePayStatus(out_trade_no, "待处理", total_fee, notify_time, "已支付",trade_no,"完成", buyer_id, buyer_email,"yes");
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
				changePayStatus(out_trade_no, "待处理", total_fee, notify_time, "已支付",trade_no,"完成", buyer_id, buyer_email,"no");
				//注意：
				//付款完成后，支付宝系统发送该交易状态通知
			}else if(trade_status.equals("TRADE_PENDING"))
			{
				changePayStatus(out_trade_no, "等待收款", total_fee, notify_time, "未收款",trade_no,"等待收款", buyer_id, buyer_email,"no");
			}else if(trade_status.equals("WAIT_BUYER_PAY"))
			{
				changePayStatus(out_trade_no, "等待买家付款", total_fee, notify_time, "未支付",trade_no,"等待买家付款", buyer_id, buyer_email,"no");
			}

			//——请根据您的业务逻辑来编写程序（以上代码仅作参考）——
			log.debug("alipay notify success");
			return StrutsResMSG.SUCCESS;
			//////////////////////////////////////////////////////////////////////////////////////////
		}else{//验证失败
			log.debug("alipay notify failed");
			return StrutsResMSG.FAILED;
		}
	}
	@Action(value="againPayment",results = {@Result(name = StrutsResMSG.ALIPAY, location = "/pay/alipay/alipayapi.jsp")})
	public String againPayment()
	{
		BusinessOrderRecord bor = orderRecordService.findByid(orderId); 
		String outTradeNo = bor.getOrderNo();
		Long newOrderRecordId = bor.getId();
		String totalMoney = bor.getTotalPrice().toString();
		this.alipay = alipayService.payInfo(outTradeNo, newOrderRecordId,totalMoney);
		return StrutsResMSG.ALIPAY;
	}
	/***
	 * 更改订单支付状态，保存支付宝交易号和订单号关系
	 * @param out_trade_no 订单号
	 * @param operateStatusName 订单操作状态
	 * @param totla_fee 订单金额
	 * @param gmt_payment 交易支付时间
	 * @param paymentName	支付状态名字
	 * @param trade_no		支付宝交易号
	 * @param trades		支付宝交易状态
	 * @param buyer_id	买家支付宝号
	 * @param buyer_email	买家支付宝账号
	 * @param isRefund		是否可以退款
	 */
	public void changePayStatus(String out_trade_no,String operateStatusName,String totla_fee,String gmt_payment,String paymentName,String trade_no,String trades,String buyer_id,String buyer_email,String isRefund)
	{
		BusinessOrderRecord orderRecord = orderRecordService.findByOutOderNo(out_trade_no);
		log.debug("************");
		log.debug("********orderRecord.getTotalPrice()****"+orderRecord.getTotalPrice().toString());
		if(totla_fee.indexOf(".") > 0){
						totla_fee = totla_fee.replaceAll("0+?$", "");//去掉后面无用的零
						totla_fee = totla_fee.replaceAll("[.]$", "");//如小数点后面全是零则去掉小数点
			}
		log.debug("******totla_fee*****"+totla_fee);
		log.debug("*****if*******"+totla_fee.equals(orderRecord.getTotalPrice().toString()));
	
		if(totla_fee.equals(orderRecord.getTotalPrice().toString()))//判断支付宝支付价格是不是和订单相同。
		{
			if(orderRecord.getPaymentStatus().equals(201))
			{
				orderRecord.setPaymentTime(Timestamp.valueOf(gmt_payment));
				if(operateStatusName.equals("待处理"))
				{
					orderRecord.setOperateStatus(203);
				}
				orderRecord.setOperateStatusName(operateStatusName);
				orderRecord.setPaymentStatusName(paymentName);
				
				if(paymentName.equals("已支付"))
				{
					orderRecord.setPaymentStatus(200);
				}else if(paymentName.equals("未支付"))
				{
					orderRecord.setPaymentStatus(201);
				}
				orderRecordService.modify(orderRecord);
				Reconciliation reconciliation = new Reconciliation();
				reconciliation.setAlipayTradNo(trade_no);
				reconciliation.setTradeStatus(trades);
				reconciliation.setBuyerId(buyer_id);
				reconciliation.setOprderRecordId(orderRecord.getId());
				reconciliation.setBuyerMail(buyer_email);
				reconciliation.setIsRefund(isRefund);
				alipayService.saveRecon(reconciliation);
			}
		}
	}
	
}

