/**
 *2016年11月8日 下午4:16:52
 */
package com.xnradmin.po.inv;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.json.JSONArray;
import org.json.JSONObject;

import com.cntinker.util.ReflectHelper;

/**
 * 预占表
 * 
 * @author: liubin
 *
 */
@Entity
@Table(name = "inv_campon")
public class InvCampon implements java.io.Serializable {
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;

	private Integer orderId; // 销售订单id
	private String inputOrderIds; // 一个预占可能会占用多个进货单,以逗号区分，来源订单id，比如：1,2,3,这里对应的表是com.xnradmin.po.wx.outplan
	private Integer goodsId; // 商品Id
	private Double campOnNum; // 预占数量/重量
	private Integer storeId; // 仓库id

	/**
	 * 预占的详情，对应每个供货单对应扣除的数量/重量，以JSON格式保存<br>
	 * 例:[{"camponNum":21.5,"inputOrderId":12},{"camponNum":17.5,"inputOrderId":
	 * 31 }]<br>
	 * 示例代码:<br>
	 * JSONArray lst = new JSONArray();<br>
	 * JSONObject o1 = new JSONObject();<br>
	 * o1.put("inputOrderId", 12); <br>
	 * o1.put("camponNum", 21.5);<br>
	 * 
	 * JSONObject o2 = new JSONObject(); <br>
	 * o2.put("inputOrderId", 31);<br>
	 * o2.put("camponNum", 17.5);<br>
	 * 
	 * lst.put(o1); <br>
	 * lst.put(o2);<br>
	 * log.debug(lst.toString());<br>
	 */
	@Column(name = "campon_detail", columnDefinition = "longtext", length = 4096)
	private String camponDetail;

	/**
	 * 1-预占成功 0-取消预占 2-已出库
	 */
	private Integer status;

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

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public String getInputOrderIds() {
		return inputOrderIds;
	}

	public void setInputOrderIds(String inputOrderIds) {
		this.inputOrderIds = inputOrderIds;
	}

	public Integer getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}

	public Double getCampOnNum() {
		return campOnNum;
	}

	public void setCampOnNum(Double campOnNum) {
		this.campOnNum = campOnNum;
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public String getCamponDetail() {
		return camponDetail;
	}

	public void setCamponDetail(String camponDetail) {
		this.camponDetail = camponDetail;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}
