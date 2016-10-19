/**
 *2014年12月2日 下午7:42:04
 */
package com.xnradmin.vo.business;

import com.cntinker.util.ReflectHelper;

/**
 * @author: liubin
 * 
 *          饭店联盟的分类VO
 *
 */
public class BusinessDataFDLMTypeVO implements java.io.Serializable {
	
	private Integer typeid;
	
	private Integer parentId;
	
	private String typeName;
	
	private String url;
	
	public String toString() {
		String res = "";
		try {
			res = ReflectHelper.makeToString(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public Integer getTypeid() {
		return typeid;
	}

	public Integer getParentId() {
		return parentId;
	}

	public String getTypeName() {
		return typeName;
	}

	public String getUrl() {
		return url;
	}

	public void setTypeid(Integer typeid) {
		this.typeid = typeid;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
