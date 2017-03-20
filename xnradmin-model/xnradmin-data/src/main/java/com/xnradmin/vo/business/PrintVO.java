/**
 *2014年11月25日 下午3:44:55
*/
package com.xnradmin.vo.business;

import java.util.List;

import com.cntinker.util.ReflectHelper;
import com.xnradmin.po.business.BusinessGoods;
import com.xnradmin.po.business.BusinessOrderGoodsRelation;
import com.xnradmin.po.business.BusinessOrderRecord;

/**
 * @author: liubin
 *
 */
public class PrintVO implements java.io.Serializable{

	private static final long serialVersionUID = 1L;

	private ComboVO combo;
	
	private BusinessOrderRecord businessOrderRecord;
	
	private String businessOrderRecordDeliveryStartTime;//前台不方便处理转换成字符串
	
	private List<BusinessOrderVO> businessOrderVOList;
	
	public String toString() {
		String res = "";
		try {
			res = ReflectHelper.makeToString(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public ComboVO getCombo() {
		return combo;
	}

	public void setCombo(ComboVO combo) {
		this.combo = combo;
	}

	public BusinessOrderRecord getBusinessOrderRecord() {
		return businessOrderRecord;
	}

	public void setBusinessOrderRecord(BusinessOrderRecord businessOrderRecord) {
		this.businessOrderRecord = businessOrderRecord;
	}

	public List<BusinessOrderVO> getBusinessOrderVOList() {
		return businessOrderVOList;
	}

	public void setBusinessOrderVOList(List<BusinessOrderVO> businessOrderVOList) {
		this.businessOrderVOList = businessOrderVOList;
	}

	public String getBusinessOrderRecordDeliveryStartTime() {
		return businessOrderRecordDeliveryStartTime;
	}

	public void setBusinessOrderRecordDeliveryStartTime(
			String businessOrderRecordDeliveryStartTime) {
		this.businessOrderRecordDeliveryStartTime = businessOrderRecordDeliveryStartTime;
	}

}
