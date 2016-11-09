/**
 *2016年11月8日 下午12:14:06
 */
package com.xnradmin.po.inv;

import static javax.persistence.GenerationType.IDENTITY;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.cntinker.util.ReflectHelper;

/**
 * 库存相关LOG表
 * 
 * @author: liubin
 *
 */
@Entity
@Table(name = "inv_sync_log")
public class InvSyncLog implements java.io.Serializable {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;

	/**
	 * 同步类型 0-入库 1-出库 2-盘点 3-预占 4-取消预占 5-查询加盟商订单的货物来源 <br>
	 * 6-异常记录<br>
	 */
	private Integer syncType;
	private Integer syncStatus; // 同步状态 1-成功 0-失败
	@Column(name = "ack_content", columnDefinition = "longtext", length = 4096)
	private String ackContent; // 我方返回的信息体内容
	@Column(name = "sync_content", columnDefinition = "longtext", length = 4096)
	private String syncContent; // 对方同步过来的信息内容
	private String checkStoreId;// 盘点ID
	@Column(name = "sync_url", columnDefinition = "longtext", length = 4096)
	private String syncUrl; // 同步URL
	@Column(name = "store_ids", columnDefinition = "longtext", length = 4096)
	private String storeIds; // 仓库IDS,格式:1,2,3,4,
	@Column(name = "goods_ids", columnDefinition = "longtext", length = 4096)
	private String goodsIds; // 商品IDS,格式：1,2,3,4,
	@Column(name = "order_ids", columnDefinition = "longtext", length = 4096)
	private String orderIds; // 同步多个平台订单的时候记录
	private String sign; // 签名
	private Timestamp lastSyncTime; // 上次同步时间
	@Column(name = "ack_err_code", columnDefinition = "longtext", length = 4096)
	private String ackErrCode; // 本次请求处理的结果 1000代表正常
	@Column(name = "ack_err_msg", columnDefinition = "longtext", length = 4096)
	private String ackErrMsg; // 本次请求的处理结果的文字描述
	@Column(name = "ack_data", columnDefinition = "longtext", length = 4096)
	private String ackData; // 补充完整的用例及说明

	public String toString() {
		String res = "";
		try {
			res = ReflectHelper.makeToString(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSyncType() {
		return syncType;
	}

	public void setSyncType(Integer syncType) {
		this.syncType = syncType;
	}

	public Integer getSyncStatus() {
		return syncStatus;
	}

	public void setSyncStatus(Integer syncStatus) {
		this.syncStatus = syncStatus;
	}

	public String getAckContent() {
		return ackContent;
	}

	public void setAckContent(String ackContent) {
		this.ackContent = ackContent;
	}

	public String getSyncContent() {
		return syncContent;
	}

	public void setSyncContent(String syncContent) {
		this.syncContent = syncContent;
	}

	public String getCheckStoreId() {
		return checkStoreId;
	}

	public void setCheckStoreId(String checkStoreId) {
		this.checkStoreId = checkStoreId;
	}

	public String getSyncUrl() {
		return syncUrl;
	}

	public void setSyncUrl(String syncUrl) {
		this.syncUrl = syncUrl;
	}

	public String getStoreIds() {
		return storeIds;
	}

	public void setStoreIds(String storeIds) {
		this.storeIds = storeIds;
	}

	public String getGoodsIds() {
		return goodsIds;
	}

	public void setGoodsIds(String goodsIds) {
		this.goodsIds = goodsIds;
	}

	public String getOrderIds() {
		return orderIds;
	}

	public void setOrderIds(String orderIds) {
		this.orderIds = orderIds;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public Timestamp getLastSyncTime() {
		return lastSyncTime;
	}

	public void setLastSyncTime(Timestamp lastSyncTime) {
		this.lastSyncTime = lastSyncTime;
	}

	public String getAckErrCode() {
		return ackErrCode;
	}

	public void setAckErrCode(String ackErrCode) {
		this.ackErrCode = ackErrCode;
	}

	public String getAckErrMsg() {
		return ackErrMsg;
	}

	public void setAckErrMsg(String ackErrMsg) {
		this.ackErrMsg = ackErrMsg;
	}

	public String getAckData() {
		return ackData;
	}

	public void setAckData(String ackData) {
		this.ackData = ackData;
	}
}
