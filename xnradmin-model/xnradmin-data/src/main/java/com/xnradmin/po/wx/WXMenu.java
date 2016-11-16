/**
 *2014年8月25日 上午1:43:51
 */
package com.xnradmin.po.wx;

import static javax.persistence.GenerationType.IDENTITY;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Index;

import com.cntinker.util.ReflectHelper;

/**
 * @author: liubin
 * 
 */
@Entity
@Table(name = "wx_menu")
public class WXMenu implements java.io.Serializable {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	private Long id;

	@Index(name = "idx_wx_menu_wxuserid")
	private Long wxuserid;

	/**
	 * 菜单标题，不超过16个字节，子菜单不超过40个字节<br>
	 * 一级菜单最多4个汉字，二级菜单最多7个汉字，多出来的部分将会以“...”代替。<br>
	 * 请注意，创建自定义菜单后，由于微信客户端缓存，需要24小时微信客户端才会展现出来。<br>
	 * 建议测试时可以尝试取消关注公众账号后再次关注，则可以看到创建后的效果。<br>
	 */
	private String menuName;

	/**
	 * 系统内的菜单级别
	 * 
	 * 对应微信的：<br>
	 * button 一级菜单数组，个数应为1~3个<br>
	 * sub_button 二级菜单数组，个数应为1~5个<br>
	 */
	private Integer menuLevel;

	// 上级菜单ID
	private Long parentMenuId;

	// 菜单的响应动作类型，目前有click、view两种类型,对应系统内的status
	private Integer typeid;

	// click类型必须 ,菜单KEY值，用于消息接口推送，不超过128字节
	private String wxkey;

	// view类型必须 ,网页链接，用户点击菜单可打开链接，不超过256字节
	private String url;

	private Timestamp createTime;

	private Timestamp modifyTime;

	private Integer createStaffId;

	private Integer modifyStaffId;

	private Integer sortOrder;

	public String toString() {
		String res = "";
		try {
			res = ReflectHelper.makeToString(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getWxuserid() {
		return wxuserid;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setWxuserid(Long wxuserid) {
		this.wxuserid = wxuserid;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public Integer getMenuLevel() {
		return menuLevel;
	}

	public Long getParentMenuId() {
		return parentMenuId;
	}

	public Integer getTypeid() {
		return typeid;
	}

	public String getWxkey() {
		return wxkey;
	}

	public String getUrl() {
		return url;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public Timestamp getModifyTime() {
		return modifyTime;
	}

	public Integer getCreateStaffId() {
		return createStaffId;
	}

	public Integer getModifyStaffId() {
		return modifyStaffId;
	}

	public Integer getSortOrder() {
		return sortOrder;
	}

	public void setMenuLevel(Integer menuLevel) {
		this.menuLevel = menuLevel;
	}

	public void setParentMenuId(Long parentMenuId) {
		this.parentMenuId = parentMenuId;
	}

	public void setTypeid(Integer typeid) {
		this.typeid = typeid;
	}

	public void setWxkey(String wxkey) {
		this.wxkey = wxkey;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public void setModifyTime(Timestamp modifyTime) {
		this.modifyTime = modifyTime;
	}

	public void setCreateStaffId(Integer createStaffId) {
		this.createStaffId = createStaffId;
	}

	public void setModifyStaffId(Integer modifyStaffId) {
		this.modifyStaffId = modifyStaffId;
	}

	public void setSortOrder(Integer sortOrder) {
		this.sortOrder = sortOrder;
	}

	
}
