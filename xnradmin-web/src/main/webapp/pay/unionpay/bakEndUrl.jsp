<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.xnradmin.core.pay.upmp.service.*"%>
<%@ page import="com.xnradmin.po.pay.*"%>
<%@ page import="com.cntinker.util.*"%>
<%@ page import="com.xnradmin.core.util.*"%>
<%
	//获取银联POST过来异步通知信息
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
		params.put(name, valueStr);
	}
	System.out.println("bakEndUrl:::"+params);
	if(UpmpService.verifySignature(params)){// 服务器签名验证成功
		//请在这里加上商户的业务逻辑程序代码
		//获取通知返回参数，可参考接口文档中通知参数列表(以下仅供参考)
		String version = request.getParameter("version");  //版本
		String charset = request.getParameter("charset"); //编码方式
		String signMethod = request.getParameter("signMethod"); //加密方式
		String signature = request.getParameter("signature"); //加密码
		String transType = request.getParameter("transType"); //交易类型
		String merId = request.getParameter("merId"); //商户代码
		String transStatus = request.getParameter("transStatus");// 交易状态 00为成功
		String respCode = request.getParameter("respCode"); //响应码
		String respMsg = request.getParameter("respMsg"); //响应信息
		String serNo = request.getParameter("qn"); //流水号
		String orderId = request.getParameter("orderNumber"); //订单号
		String orderType = request.getParameter("orderType"); //订单类型
		String orderTypeName = request.getParameter("orderTypeName"); //订单类型名称
		String orderTime = request.getParameter("orderTime"); //订单开始时间
		String settleAmount = request.getParameter("settleAmount"); //订单价格
		String settleCurrency = request.getParameter("settleCurrency"); //清算币种
		String settleDate = request.getParameter("settleDate"); //清算日期(MMDD)
		String exchangeRate = request.getParameter("exchangeRate"); //清算汇率
		String exchangeDate = request.getParameter("exchangeDate");//兑换日期(MMDD)
		String merReserved = request.getParameter("merReserved"); //商户保留域
		String reqReserved = request.getParameter("reqReserved"); //请求方保留域
		String sysReserved = request.getParameter("sysReserved"); //系统保留域
		StringBuffer content = new StringBuffer();;
		content.append("{\"action\":\"upmp\"");
		if(version!=null && !StringHelper.isNull(version)){
			content.append(",\"version\":\""+version+"\"");
		}
		if(charset!=null && !StringHelper.isNull(charset)){
			content.append(",\"charset\":\""+charset+"\"");
		}
		if(signMethod!=null && !StringHelper.isNull(signMethod)){
			content.append(",\"signMethod\":\""+signMethod+"\"");
		}
		if(signature!=null && !StringHelper.isNull(signature)){
			content.append(",\"signature\":\""+signature+"\"");
		}
		if(transType!=null && !StringHelper.isNull(transType)){
			content.append(",\"transType\":\""+transType+"\"");
		}
		if(merId!=null && !StringHelper.isNull(merId)){
			content.append(",\"merId\":\""+merId+"\"");
		}
		if(transStatus!=null && !StringHelper.isNull(transStatus)){
			content.append(",\"transStatus\":\""+transStatus+"\"");
		}
		if(respCode!=null && !StringHelper.isNull(respCode)){
			content.append(",\"respCode\":\""+respCode+"\"");
		}
		if(respMsg!=null && !StringHelper.isNull(respMsg)){
			content.append(",\"respMsg\":\""+respMsg+"\"");
		}
		if(serNo!=null && !StringHelper.isNull(serNo)){
			content.append(",\"serNo\":\""+serNo+"\"");
		}
		if(orderId!=null && !StringHelper.isNull(orderId)){
			content.append(",\"orderId\":\""+orderId+"\"");
		}
		if(orderType!=null && !StringHelper.isNull(orderType)){
			content.append(",\"orderType\":\""+orderType+"\"");
		}
		if(orderTypeName!=null && !StringHelper.isNull(orderTypeName)){
			content.append(",\"orderTypeName\":\""+orderTypeName+"\"");
		}
		if(orderTime!=null && !StringHelper.isNull(orderTime)){
			content.append(",\"orderTime\":\""+orderTime+"\"");
		}
		if(settleAmount!=null && !StringHelper.isNull(settleAmount)){
			content.append(",\"settleAmount\":\""+settleAmount+"\"");
		}
		if(settleCurrency!=null && !StringHelper.isNull(settleCurrency)){
			content.append(",\"settleCurrency\":\""+settleCurrency+"\"");
		}
		if(settleDate!=null && !StringHelper.isNull(settleDate)){
			content.append(",\"settleDate\":\""+settleDate+"\"");
		}
		if(exchangeRate!=null && !StringHelper.isNull(exchangeRate)){
			content.append(",\"exchangeRate\":\""+exchangeRate+"\"");
		}
		if(exchangeDate!=null && !StringHelper.isNull(exchangeDate)){
			content.append(",\"exchangeDate\":\""+exchangeDate+"\"");
		}
		if(merReserved!=null && !StringHelper.isNull(merReserved)){
			content.append(",\"merReserved\":\""+merReserved+"\"");
		}
		if(reqReserved!=null && !StringHelper.isNull(reqReserved)){
			content.append(",\"reqReserved\":\""+reqReserved+"\"");
		}
		if(sysReserved!=null && !StringHelper.isNull(sysReserved)){
			content.append(",\"sysReserved\":\""+sysReserved+"\"");
		}
		content.append("}");
		System.out.println("contentaaa::::"+content.toString());
		String postContent = EncodeDecodeUtil.encode(content.toString());
		String url = "http://115.29.38.253:8080/interface/sync.jsp";
		System.out.println("postaaa::::"+HttpHelper.postXml(url, postContent));
		if (null != transStatus && transStatus.equals("00")) {
		    // 交易处理成功	
		} else {
		}
		out.println("success");
	}else{// 服务器签名验证失败
		out.println("fail");
		System.out.println("fail::::");
	}
%>
