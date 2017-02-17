/**
 *2014年8月31日 下午4:23:43
 */
package com.xnradmin.client.service.wx;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cntinker.util.StringHelper;
import com.google.gson.JsonArray;
import com.xnradmin.core.dao.CommonDAO;
import com.xnradmin.core.service.StaffService;
import com.xnradmin.core.service.common.status.StatusService;
import com.xnradmin.core.util.Log4jUtil;
import com.xnradmin.po.wx.WXReactiveMessage;
import com.xnradmin.vo.client.wx.WXMenuVO;
import com.xnradmin.vo.client.wx.WXMessageVO;
import com.xnradmin.vo.client.wx.WXReactiveMessageVO;
import com.xnradmin.vo.client.wx.WXUserVO;

/**
 * @author: liubin
 * 
 */
@Service("WXReactiveMessageService")
public class WXReactiveMessageService {

	static Log log = LogFactory.getLog(WXReactiveMessageService.class);

	private Log wxlog = Log4jUtil.getLog("wxport");

	@Autowired
	private CommonDAO commonDao;
	@Autowired
	private StaffService staffService;
	@Autowired
	private StatusService statusService;
	@Autowired
	private WXAccessTokenService accessTokenService;
	@Autowired
	private WXUserService wxuserService;
	@Autowired
	private WXMenuService wxmenuService;

	/**
	 * 0 - 成功<br>
	 * 1 - 所需参数不能位空<br>
	 * 
	 * @param query
	 * @return int
	 * @throws JSONException
	 */
	public int save(WXReactiveMessageVO query, Map<String, WXMessageVO> msglist)
			throws JSONException {
		if (query == null || query.getReactiveMessage() == null)
			return 1;

		WXReactiveMessage reactiveMsg = query.getReactiveMessage();
		if (query != null && query.getWxmenu() != null
				&& query.getWxmenu().getMenu() != null)
			reactiveMsg.setWxmenuid(query.getWxmenu().getMenu().getId());
		reactiveMsg.setCreateTime(new Timestamp(System.currentTimeMillis()));
		reactiveMsg.setWxuserid(new Long(query.getWxuservo().getWxuserid()));
		reactiveMsg.setCreateStaff(query.getCreateStaff().getId());

		if (msglist != null && msglist.size() > 0) {
			String json = toJson(msglist);
			reactiveMsg.setMsgJson(json);
		}

		this.commonDao.save(reactiveMsg);
		return 0;
	}

	public int modify(WXReactiveMessageVO query,
			Map<String, WXMessageVO> msglist) throws JSONException {
		if (query == null || query.getReactiveMessage() == null)
			return 1;

		WXReactiveMessage reactiveMsg = query.getReactiveMessage();
		WXReactiveMessageVO old = findByID(reactiveMsg.getId().toString());
		reactiveMsg.setCreateStaff(old.getReactiveMessage().getCreateStaff());
		reactiveMsg.setCreateTime(old.getReactiveMessage().getCreateTime());

		if (query != null && query.getWxmenu() != null
				&& query.getWxmenu().getMenu() != null)
			reactiveMsg.setWxmenuid(query.getWxmenu().getMenu().getId());
		reactiveMsg.setWxuserid(new Long(query.getWxuservo().getWxuserid()));

		if (msglist != null && msglist.size() > 0) {
			String json = toJson(msglist);
			reactiveMsg.setMsgJson(json);
		}

		this.commonDao.modify(reactiveMsg);
		return 0;
	}

	private String toJson(Map<String, WXMessageVO> msglist)
			throws JSONException {
		Iterator<String> it = msglist.keySet().iterator();
		JSONArray res = new JSONArray();
		while (it.hasNext()) {
			String key = it.next();
			WXMessageVO v = msglist.get(key);
			JSONObject json = new JSONObject();
			json.put("id", v.getId());
			if (!StringHelper.isNull(v.getSortOrder()))
				json.put("sortOrder", v.getSortOrder());
			else
				json.put("sortOrder", "0");

			if (!StringHelper.isNull(v.getMsgContent()))
				json.put("msgContent", v.getMsgContent());
			else
				json.put("msgContent", "");
			json.put("msgTitle", v.getMsgTitle());

			res.put(json);
		}
		return res.toString();
	}

	private List<WXMessageVO> jsonToList(String json) throws JSONException {
		Map<String, WXMessageVO> m = new TreeMap<String, WXMessageVO>();
		JSONArray array = new JSONArray(json);
		for (int i = 0; i < array.length(); i++) {
			JSONObject js = (JSONObject) array.get(i);
			WXMessageVO v = new WXMessageVO();
			v.setLoopid(new Integer(i).toString());
			v.setId(js.getString("id"));
			v.setMsgContent(js.getString("msgContent"));
			v.setMsgTitle(js.getString("msgTitle"));
			v.setSortOrder(js.getString("sortOrder"));
			m.put(new Integer(i).toString(), v);
		}
		m = sort(m);

		List<WXMessageVO> res = new LinkedList<WXMessageVO>();
		Iterator<String> it = m.keySet().iterator();
		while (it.hasNext()) {
			String key = it.next();
			WXMessageVO v = m.get(key);
			res.add(v);
		}

		return res;
	}

	/**
	 * 定制排序
	 * 
	 * @param m
	 * @return Map<String, WXMessageVO>
	 */
	private Map<String, WXMessageVO> sort(Map<String, WXMessageVO> m) {
		List<Map.Entry<String, WXMessageVO>> list = new LinkedList<Map.Entry<String, WXMessageVO>>(
				m.entrySet());
		Collections.sort(list,
				new Comparator<Map.Entry<String, WXMessageVO>>() {
					public int compare(Map.Entry<String, WXMessageVO> o1,
							Map.Entry<String, WXMessageVO> o2) {
						return (o2.getValue().getSortOrder()).compareTo(o1
								.getValue().getSortOrder());
					}
				});
		Map<String, WXMessageVO> result = new LinkedHashMap<String, WXMessageVO>();
		for (Map.Entry<String, WXMessageVO> entry : list) {
			result.put(entry.getKey(), entry.getValue());
		}
		return result;
	}

	public WXReactiveMessageVO findByID(String id) throws JSONException {
		Object o = this.commonDao.findById(WXReactiveMessage.class,
				new Long(id));
		if (o == null)
			return null;
		WXReactiveMessage p = (WXReactiveMessage) o;
		WXUserVO uservo = wxuserService.findByidVO(p.getWxuserid().toString());

		WXReactiveMessageVO v = new WXReactiveMessageVO();
		v.setReactiveMessage(p);
		v.setWxuservo(uservo);

		if (p.getWxmenuid() != null) {
			WXMenuVO wxmenu = wxmenuService
					.findByid(p.getWxmenuid().toString());
			v.setWxmenu(wxmenu);
		}
		if(!StringHelper.isNull(p.getMsgJson())){
			List<WXMessageVO> msgList = jsonToList(p.getMsgJson());
			v.setMsgList(msgList);
		}
		
		return v;
	}

	public int delete(WXReactiveMessageVO query) throws JSONException {
		if (query == null || query.getReactiveMessage() == null)
			return 1;
		WXReactiveMessageVO v = findByID(query.getReactiveMessage().getId()
				.toString());
		this.commonDao.delete(v.getReactiveMessage());
		return 0;
	}

	public List<WXReactiveMessageVO> voList(WXReactiveMessageVO query,
			int pageNo, int pageSize) {
		String hql = getHql(query);
		List<WXReactiveMessage> l = commonDao.getEntitiesByPropertiesWithHql(
				hql, pageNo, pageSize);
		List<WXReactiveMessageVO> res = new LinkedList<WXReactiveMessageVO>();
		for (WXReactiveMessage e : l) {
			WXReactiveMessageVO v = new WXReactiveMessageVO();
			v.setReactiveMessage(e);

			WXUserVO uservo = wxuserService.findByidVO(e.getWxuserid()
					.toString());
			v.setWxuservo(uservo);

			if (e.getWxmenuid() != null) {
				WXMenuVO wxmenu = wxmenuService.findByid(e.getWxmenuid()
						.toString());
				v.setWxmenu(wxmenu);
			}

			res.add(v);
		}
		return res;
	}

	public int getCount(WXReactiveMessageVO query) {
		String hql = "select count(*) " + getHql(query);
		return commonDao.getNumberOfEntitiesWithHql(hql);
	}

	private String getHql(WXReactiveMessageVO query) {
		StringBuffer hql = new StringBuffer();
		boolean isWhere = false;
		boolean isAnd = false;
		if (query != null
				&& (query.getReactiveMessage() != null && (query
						.getReactiveMessage().getMsgTypeId() != null
						|| !StringHelper.isNull(query.getReactiveMessage()
								.getRecContent())
						|| query.getReactiveMessage().getWxuserid() != null || query
						.getReactiveMessage().getWxmenuid() != null))) {
			isWhere = true;
		}
		hql.append("from WXReactiveMessage");
		if (isWhere)
			hql.append(" where ");
		if (query != null && query.getReactiveMessage() != null
				&& query.getReactiveMessage().getMsgTypeId() != null) {
			if (isAnd)
				hql.append(" and ");
			if (query.getReactiveMessage().getMsgTypeId() != null)
				hql.append(" msgTypeId=").append(
						query.getReactiveMessage().getMsgTypeId());
		}
		if (query != null
				&& query.getReactiveMessage() != null
				&& !StringHelper.isNull(query.getReactiveMessage()
						.getRecContent())) {
			if (isAnd)
				hql.append(" and ");
			if (query.getReactiveMessage().getRecContent() != null)
				hql.append(" reactiveMessage like '%")
						.append(query.getReactiveMessage().getRecContent())
						.append("%'");
		}
		if (query != null && query.getReactiveMessage() != null
				&& query.getReactiveMessage().getWxuserid() != null) {
			if (isAnd)
				hql.append(" and ");
			if (query.getReactiveMessage().getWxuserid() != null)
				hql.append(" wxuserid =").append(
						query.getReactiveMessage().getWxuserid());
		}
		if (query != null && query.getReactiveMessage() != null
				&& query.getReactiveMessage().getWxmenuid() != null) {
			if (isAnd)
				hql.append(" and ");
			if (query.getReactiveMessage().getWxmenuid() != null)
				hql.append(" wxmenuid =").append(
						query.getReactiveMessage().getWxmenuid());
		}
		return hql.toString();
	}
}
