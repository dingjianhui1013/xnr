/*
 * 2015年3月7日下午18:08:36
 */

package com.xnradmin.stat.action.business;

import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;

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
import com.xnradmin.po.business.BusinessOrderRecord;
import com.xnradmin.stat.service.business.BusinessStatOrderRecordService;
import com.xnradmin.vo.StaffVO;
import com.xnradmin.vo.business.BusinessOrderVO;

/**
 * @author: yixiao
 * 
 */
@Controller
@Scope("prototype")
@Namespace("/page/stat/business")
@ParentPackage("json-default")
public class BusinessStatOrderRecordAction extends ParentAction{
	@Autowired
	private BusinessStatOrderRecordService businessStatOrderRecordStatService;
	
	@Action(value="orderRecordPurchase", results={
			@Result(name=StrutsResMSG.SUCCESS,location="/stat/business/orderRecordPurchase.jsp"),
			@Result(name = StrutsResMSG.FAILED, location = "/stat/business/orderRecordPurchase.jsp")
	})
	public String orderRecordPurchase() throws IllegalAccessException,
	IllegalArgumentException,InvocationTargetException,ClassNotFoundException,
	InstantiationException{
		setDateTime();
		BusinessOrderVO bovo = new BusinessOrderVO();
		BusinessOrderRecord bor = new BusinessOrderRecord();
		if(!StringHelper.isNull(clientUserName)){
			bor.setClientUserName(String.valueOf(clientUserName));
		}
		if(!StringHelper.isNull(clientUserMobile)){
			bor.setClientUserMobile(String.valueOf(clientUserMobile));
		}
		bovo.setCreateStartTime(createTime);
		bovo.setCreateEndTime(endTime);
		bovo.setStaffCreateStartTime(registrationCreatetime);
		bovo.setStaffCreateEndTime(registrationEndTim);
		bovo.setBusinessOrderRecord(bor);
		List list = businessStatOrderRecordStatService.orderRecordPurchase(bovo, 0, 0, orderField, orderDirection);
		this.totalCount=0;
		contentList = new ArrayList<String[]>();
		for(int i=0; i<list.size(); i++){
			String[] content = new String[6];
			HashMap map = new HashMap();
			map = (HashMap) list.get(i);
			if(map == null)
				continue;
			content[0] = map.get("createtime") == null ? "0" : map.get("createtime")
					.toString();
			content[1] = map.get("alone") == null ? "0" : map.get("alone")
					.toString();
			content[2] = map.get("allprice") == null ? "0" : map.get("allprice")
					.toString();
			content[3] = map.get("averageprice") == null ? "0" : map.get("averageprice")
					.toString();
			content[4] = map.get("single") == null ? "0" : map.get("single")
					.toString();
			content[5] = map.get("averagesingle") == null ? "0" :map.get("averagesingle")
					.toString();
			float t1 = new Float(content[1]);
			float t2 = new Float(content[2]);
			Integer t3 = new Integer(content[5]);
			sumPrice += t1;
			averagePrice += t2;
			averageSingle += t3;
			contentList.add(content);
		}
		sumPrice = new Float(StringHelper.formartDecimalToStr(sumPrice
				.doubleValue(), "#.0"));
		averagePrice = new Float(StringHelper.formartDecimalToStr(averagePrice
				.doubleValue(), "#.0"));
		return StrutsResMSG.SUCCESS;
	}
	private void setDateTime() {
		// 设置默认时间
		if (StringHelper.isNull(createTime)
				&& StringHelper.isNull(endTime)) {
			Date date = new Date();// 取时间
			Calendar calendar = new GregorianCalendar();
			calendar.setTime(date);
			calendar.add(Calendar.DATE, 1);// 把日期往后增加一天.整数往后推,负数往前移动
			date = calendar.getTime(); // 这个时间就是日期往后推一天的结果
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			String dateString = formatter.format(date);
			createTime = dateString;
			endTime = dateString;
		}
	}
	private String createTime;
	private String endTime;
	private String registrationCreatetime;
	private String registrationEndTim;
	private String clientUserName;//商户名称
	private String clientUserMobile;//商户电话
	private List<String[]> contentList;
	private Float sumPrice = 0f; // 采购单总售价
	private Float averagePrice = 0f;//采购单平均售价
	private Integer averageSingle =0;//用户平均下单数
	
	@Override
	public boolean isPublic() {
		// TODO Auto-generated method stub
		return false;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getClientUserName() {
		return clientUserName;
	}
	public void setClientUserName(String clientUserName) {
		this.clientUserName = clientUserName;
	}
	public String getClientUserMobile() {
		return clientUserMobile;
	}
	public void setClientUserMobile(String clientUserMobile) {
		this.clientUserMobile = clientUserMobile;
	}
	public List<String[]> getContentList() {
		return contentList;
	}
	public void setContentList(List<String[]> contentList) {
		this.contentList = contentList;
	}
	public Float getSumPrice() {
		return sumPrice;
	}
	public void setSumPrice(Float sumPrice) {
		this.sumPrice = sumPrice;
	}
	public float getAveragePrice() {
		return averagePrice;
	}
	public void setAveragePrice(float averagePrice) {
		this.averagePrice = averagePrice;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getRegistrationCreatetime() {
		return registrationCreatetime;
	}
	public void setRegistrationCreatetime(String registrationCreatetime) {
		this.registrationCreatetime = registrationCreatetime;
	}
	public String getRegistrationEndTim() {
		return registrationEndTim;
	}
	public void setRegistrationEndTim(String registrationEndTim) {
		this.registrationEndTim = registrationEndTim;
	}
	public Integer getAverageSingle() {
		return averageSingle;
	}
	public void setAverageSingle(Integer averagesingle) {
		this.averageSingle = averagesingle;
	}
}
