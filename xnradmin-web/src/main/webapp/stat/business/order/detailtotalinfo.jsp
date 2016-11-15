<%@ page contentType="text/html;charset=UTF-8" language="java"
	import="java.io.*" import="java.util.*" import="java.text.*"
	import="com.cntinker.util.*" 
	import="sun.misc.BASE64Encoder" import="java.net.*"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
	        + request.getServerName() + ":" + request.getServerPort()
	        + path + "/";
		
	String outputExcel = basePath+"page/stat/business/outputDetailTotalExcel.action";
	
	request.setAttribute("outputExcel",outputExcel);
	
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<style type="text/css">
	p{font-size: 20px; max-height: 90px;}
	.td{font-size: 18px; font-weight: bold;}
	.pan{font-size: 20px; font-weight: bold; line-height: 35px;}
</style>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="icon" href="javascript:$.printBox('w_list_print_business_order_detail_total_info')" style="page-break-after: auto;"><span>打印配送单</span></a></li>
		</ul>
	</div>
	
	<div class="pageFormContent" layoutH="56">
	
	<div id="w_list_print_business_order_detail_total_info">
			<fieldset>
				<legend>用户信息</legend>
				<table class="table" width="100%" nowrapTD="false">
					<tr>
						<td>
							<span class="td">商户：${query.staff.staffName}</span>			
						</td>
						<td>
							<span class="td">
							送达时间：<fmt:formatDate value="${query.businessOrderRecord.requiredDeliveryTime}" pattern="yyyy-MM-dd"/> 
							最早：${query.staff.theEarliestTime}点-最晚${query.staff.theLatestTime}点
							</span>
						</td>
					</tr>
					<tr>
						<td>
							<span class="td">
							详细地址：${query.businessOrderRecord.userRealAddress}
							</span>
						</td>
						<td>
							<span class="td">
							收货人名称：${query.businessOrderRecord.userRealName}
							</span>	
						</td>
					</tr>
					<tr>
						<td>
							<span class="td">
							收货人电话：${query.businessOrderRecord.userRealMobile}
							</span>
						</td>
						<td>
							<span class="td">
							客户经理：${query.leaderStaff.staffName}
							</span>
						</td>
					</tr>
					<tr>
						<td>
							<span class="td">
							客户经理电话：${query.leaderStaff.mobile}
							</span>
						</td>
						<td>
							<span class="td">
							地区：${query.businessOrderRecord.cityName} / ${query.businessOrderRecord.areaName}
							</span>
						</td>					
					</tr>
					
					
					<tr>
						<td>
						<span class="td">
							处理状态：
							<c:if test="${operateStatusList!=null}">
							<c:forEach items="${operateStatusList}" var="loop">
								<c:choose>
									<c:when test="${loop.id==query.businessOrderRecord.operateStatus}">
										${loop.statusName}
									</c:when>
								</c:choose>
							</c:forEach>
						</c:if>
						</span>
						</td>
						<td>
						<span class="td">
							支付状态：
							<c:if test="${paymentStatusList!=null}">
								<c:forEach items="${paymentStatusList}" var="loop">
									<c:choose>
										<c:when test="${loop.id==query.businessOrderRecord.paymentStatus}">
											${loop.statusName}
										</c:when>
									</c:choose>
								</c:forEach>
							</c:if>
						</span>
						</td>
					</tr>
					<tr>
						<td>
						<span class="td">
							配送状态：
							<c:if test="${deliveryStatusList!=null}">
								<c:forEach items="${deliveryStatusList}" var="loop">
									<c:choose>
										<c:when test="${loop.id==query.businessOrderRecord.deliveryStatus}">
											${loop.statusName}
										</c:when>
									</c:choose>
								</c:forEach>
							</c:if>
						</span>
						</td>
						<td>
						<span class="td">
							支付方式：
							<c:if test="${paymentProviderList!=null}">
							<c:forEach items="${paymentProviderList}" var="loop">
								<c:choose>
									<c:when test="${loop.id==query.businessOrderRecord.paymentProvider}">
										${loop.statusName}
									</c:when>
								</c:choose>
							</c:forEach>
						</c:if>
						</span>
						</td>
					</tr>
					<tr>
						<td>
						<span class="td">
							结算周期：
							<c:if test="${billList!=null}">
								<c:forEach items="${billList}" var="loop2">
									<c:choose>
										<c:when test="${loop2.id==query.staff.billTime}">
											${loop2.statusName}
										</c:when>
									</c:choose>
								</c:forEach>
							</c:if>
							</span>
						</td>
						
						<td>
						<span class="td">
							应收总款：${query.totalPrice}
						</span>
						</td>
					</tr>
					
					
					<c:if test="${query.orderDesc!=null}">
						<c:forEach items="${query.orderDesc}" var="loop">
						<tr>
							<td colspan="2">
							<span style="font-size: 22px; font-weight: bold;">
							${loop}
							</span>
							</td>
						</tr>
						</c:forEach>
					</c:if>

				</table>
				<p>
					客户确认签字：
				</p>
				<p>
					送货人员签字：
				</p>
				<p>
					实际应收总款：
				</p>
			</fieldset>
					
			<fieldset>
				<legend>货物清单</legend>
				<table class="table" width="92%" nowrapTD="false">
					<tr>
						<td width="4%"><span class="td">序号</span></td>
						<td><span class="td">商品名称</span></td>
						<td><span class="td">商品备注</span></td>
						<td width="6%" class="pan"><span class="td">数量</span></td>
						<td width="5%" class="pan"><span class="td">单位</span></td>
						<td width=8% class="pan"><span class="td">单价</span></td>						 
						<td width=8% class="pan"><span class="td">总价</span></td>
						<td width=8% class="pan"><span class="td">实际数量</span></td>
						<td width=8% class="pan"><span class="td">实际总价</span></td>
					</tr>
					<c:if test="${contentList!=null}">
									<c:forEach items="${contentList}" var="loop" varStatus="status">
									<tr>
											<td>
												<span class="pan">
													${status.index + 1}
												</span>
											</td>
											<td>
												<span class="pan">
													${loop[0]}
												</span>
											</td>
											<td>
												<span class="pan">
													${loop[9]}
												</span>
											</td>
											<td>
												<span class="pan">
													${loop[1]}
												</span>
											</td>
											<td>
												<span class="pan">
													${loop[2]}
												</span>
											</td>
											<td>
												<span class="pan">
													${loop[3]}
												</span>
											</td>
											<td>
												<span class="pan">
													${loop[4]}
												</span>
											</td>
											<td></td>
											<td></td>
										</tr> 
									</c:forEach>
							</c:if>
				</table>
			</fieldset>		
	
		
	</div>
	</div>
	
</div>