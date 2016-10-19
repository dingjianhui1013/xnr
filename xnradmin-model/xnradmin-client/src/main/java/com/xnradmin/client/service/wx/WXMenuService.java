/**
 *2014年8月23日 下午3:07:05
 */
package com.xnradmin.client.service.wx;

import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cntinker.util.HttpHelper;
import com.cntinker.util.StringHelper;
import com.xnradmin.core.dao.CommonDAO;
import com.xnradmin.core.service.StaffService;
import com.xnradmin.core.service.common.status.StatusService;
import com.xnradmin.core.util.Log4jUtil;
import com.xnradmin.dto.wx.WXUrl;
import com.xnradmin.po.CommonStaff;
import com.xnradmin.po.common.status.Status;
import com.xnradmin.po.wx.WXAccessToken;
import com.xnradmin.po.wx.WXMenu;
import com.xnradmin.vo.client.wx.WXMenuVO;
import com.xnradmin.vo.client.wx.WXUserVO;

/**
 * @author: liubin
 * 
 */
@Service("WXMenuService")
public class WXMenuService {

	static Log log = LogFactory.getLog(WXMenuService.class);

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

	/**
	 * 如果menulevel=1则查询1级菜单，2为查询子菜单,menuid为一级菜单ID，可以为空
	 * 
	 * @param wxuserid
	 * @param menulevel
	 * @param menuid
	 * @return List<WXMenuVO>
	 */
	private List<WXMenuVO> findMenu(String wxuserid, int menulevel,
			String menuid) {
		StringBuffer hql = new StringBuffer("from WXMenu where ");
		hql.append("wxuserid=").append(wxuserid);
		if (!StringHelper.isNull(menuid) && menulevel == 2) {
			hql.append(" and parentMenuId=").append(menuid);
		}
		hql.append(" and  menuLevel=").append(menulevel);
		hql.append("order by sortOrder desc");

		List<WXMenu> l = commonDao.getEntitiesByPropertiesWithHql(
				hql.toString(), 0, 0);
		if (l == null || l.size() <= 0) {
			return null;
		}
		List<WXMenuVO> res = new LinkedList<WXMenuVO>();
		for (WXMenu e : l) {
			WXMenuVO v = new WXMenuVO();
			v.setMenu(e);
			v.setWxuservo(wxuserService.findByidVO(e.getWxuserid().toString()));
			v.setCreateStaff(staffService.findByid(e.getCreateStaffId()
					.toString()));
			if (e.getModifyStaffId() != null
					&& e.getModifyStaffId().intValue() > 0)
				v.setModifyStaff(staffService.findByid(e.getModifyStaffId()
						.toString()));
			if (e.getParentMenuId() != null
					&& e.getParentMenuId().longValue() > 0) {
				WXMenuVO p = findByid(e.getParentMenuId().toString());
				if (p != null && p.getMenu() != null)
					v.setParentMenu(p.getMenu());

			}

			v.setMenuType(statusService.findByid(e.getTypeid().toString()));
			res.add(v);
		}
		return res;
	}

	public List<Status> findMenuType() {
		return statusService.find(WXMenu.class, "wxmenutype");
	}

	public boolean hasSubBtn(Long menuid) {
		String hql = "select count(*) from WXMenu where parentMenuId=" + menuid;
		int count = commonDao.getNumberOfEntitiesWithHql(hql);
		if (count > 0)
			return true;
		return false;
	}

	public WXMenuVO findByid(String id) {
		if (StringHelper.isNull(id))
			return null;
		Object o = commonDao.findById(WXMenu.class, new Long(id));
		if (o == null)
			return null;
		WXMenuVO vo = new WXMenuVO();
		WXMenu menu = (WXMenu) o;
		vo.setMenu(menu);

		CommonStaff createStaff = staffService.findByid(menu.getCreateStaffId()
				.toString());
		vo.setCreateStaff(createStaff);
		if (menu.getModifyStaffId() != null
				&& menu.getModifyStaffId().intValue() > 0) {
			CommonStaff modifyStaff = staffService.findByid(menu
					.getModifyStaffId().toString());
			vo.setModifyStaff(modifyStaff);
		}
		WXUserVO wxuservo = wxuserService.findByidVO(menu.getWxuserid()
				.toString());
		vo.setWxuservo(wxuservo);
		if (menu.getParentMenuId() != null
				&& menu.getParentMenuId().longValue() > 0) {

			WXMenuVO p = findByid(menu.getParentMenuId().toString());
			if (p != null && p.getMenu() != null)
				vo.setParentMenu(p.getMenu());
		}
		vo.setMenuType(statusService.findByid(menu.getTypeid().toString()));
		return vo;
	}

	/**
	 * return : <br>
	 * 0 - 成功<br>
	 * 1 - 传入wxuserid为空<br>
	 * 2 - 该用户下未创建1级菜单<br>
	 * 3 - 未正确返回JSON<br>
	 * 
	 * @param wxuserid
	 * @return int
	 * @throws JSONException
	 * @throws IOException
	 * @throws MalformedURLException
	 */
	public String reload(String wxuserid) throws JSONException,
			MalformedURLException, IOException {
		if (StringHelper.isNull(wxuserid))
			return "微信用户ID不能为空";
		List<WXMenuVO> level1 = findMenu(wxuserid, 1, null);
		if (level1 == null || level1.size() <= 0)
			return "该用户下还未设置1级菜单";

		String out = getMenuJson(wxuserid, level1);
		if (StringHelper.isNull(out))
			return "无有效JSON输出";

		Status menuDel = statusService.find(WXUrl.class, "url", "自定义菜单删除接口");
		Status menuCreate = statusService.find(WXUrl.class, "url", "自定义菜单创建接口");
		WXAccessToken accessToken = accessTokenService.findByWxuserId(new Long(
				wxuserid), false);

		String urlDel = menuDel.getReadme() + accessToken.getAccessToken();
		String delres = HttpHelper.sendGet(urlDel);
		wxlog.debug("微信删除菜单: wxuserid=" + wxuserid + " | 返回：" + delres);

		String urlCreate = menuCreate.getReadme()
				+ accessToken.getAccessToken();
		String createRes = HttpHelper.postXml(urlCreate, out, "UTF-8");
		wxlog.debug("微信创建菜单: wxuserid=" + wxuserid + " | 返回：" + createRes);

		return createRes;
	}

	/**
	 * 构建返回的json
	 * 
	 * @param wxuserid
	 * @param level1
	 * @return String
	 * @throws JSONException
	 */
	private String getMenuJson(String wxuserid, List<WXMenuVO> level1)
			throws JSONException {
		JSONObject res = new JSONObject();
		JSONArray btn = new JSONArray();
		for (WXMenuVO e : level1) {
			JSONObject element = new JSONObject();
			Status btnType = statusService.findByid(e.getMenu().getTypeid()
					.toString());
			element.put("type", btnType.getStatusName());
			element.put("name", e.getMenu().getMenuName());

			Status viewType = statusService.find(WXMenu.class, "wxmenutype",
					"view");
			//
			// Status clickType = statusService.find(WXMenu.class, "wxmenutype",
			// "click");
			if (btnType.getId().intValue() == viewType.getId().intValue()) {
				element.put("url", e.getMenu().getUrl());
			} else {
				element.put("key", e.getMenu().getWxkey());
			}

			JSONArray subbtn = getSubMenu(wxuserid, e.getMenu().getId()
					.toString());
			if (subbtn != null)
				element.put("sub_button", subbtn);
			btn.put(element);

		}
		res.put("button", btn);
		return res.toString();
	}

	private JSONArray getSubMenu(String wxuserid, String menuid)
			throws JSONException {

		JSONObject subbtn = new JSONObject();
		JSONArray btn = new JSONArray();
		List<WXMenuVO> level2 = findMenu(wxuserid, 2, menuid);
		if (level2 == null || level2.size() <= 0)
			return null;
		for (WXMenuVO e : level2) {
			JSONObject element = new JSONObject();
			Status btnType = statusService.findByid(e.getMenu().getTypeid()
					.toString());
			element.put("type", btnType.getStatusName());
			element.put("name", e.getMenu().getMenuName());

			Status viewType = statusService.find(WXMenu.class, "wxmenutype",
					"view");
			//
			// Status clickType = statusService.find(WXMenu.class, "wxmenutype",
			// "click");
			if (btnType.getId().intValue() == viewType.getId().intValue()) {
				element.put("url", e.getMenu().getUrl());
			} else {
				element.put("key", e.getMenu().getWxkey());
			}
			btn.put(element);
		}
		// subbtn.put("sub_button", btn);
		return btn;
	}

	public int save(WXMenuVO vo) {
		WXMenu po = vo.getMenu();
		po.setCreateStaffId(vo.getCreateStaff().getId());
		po.setCreateTime(new Timestamp(System.currentTimeMillis()));
		po.setParentMenuId(vo.getParentMenu().getId());
		po.setWxuserid(new Long(vo.getWxuservo().getWxuserid()));
		this.commonDao.save(po);
		return 0;
	}

	public int modify(WXMenuVO vo) {
		WXMenuVO old = findByid(vo.getMenu().getId().toString());
		WXMenu po = vo.getMenu();
		po.setId(old.getMenu().getId());
		po.setCreateStaffId(old.getMenu().getCreateStaffId());
		po.setCreateTime(old.getMenu().getCreateTime());
		po.setModifyStaffId(vo.getModifyStaff().getId());
		po.setModifyTime(new Timestamp(System.currentTimeMillis()));
		po.setParentMenuId(vo.getParentMenu().getId());
		po.setWxuserid(new Long(vo.getWxuservo().getWxuserid()));
		this.commonDao.modify(po);
		return 0;
	}

	public int delete(WXMenuVO vo) {
		WXMenuVO old = findByid(vo.getMenu().getId().toString());
		this.commonDao.delete(old.getMenu());
		return 0;
	}

	public int getCount(WXMenuVO query) {
		String hql = "select count(*) " + getHql(query);
		return commonDao.getNumberOfEntitiesWithHql(hql);
	}

	public List<WXMenuVO> getList(WXMenuVO query, int pageNo, int pageSize) {
		String hql = getHql(query);
		List<WXMenu> l = commonDao.getEntitiesByPropertiesWithHql(hql, pageNo,
				pageSize);
		List<WXMenuVO> res = new LinkedList<WXMenuVO>();
		for (WXMenu e : l) {
			WXMenuVO vo = new WXMenuVO();
			CommonStaff createStaff = staffService.findByid(e
					.getCreateStaffId().toString());
			vo.setCreateStaff(createStaff);
			if (e.getModifyStaffId() != null
					&& e.getModifyStaffId().intValue() > 0) {
				CommonStaff modifyStaff = staffService.findByid(e
						.getModifyStaffId().toString());
				vo.setModifyStaff(modifyStaff);
			}
			WXUserVO wxuservo = wxuserService.findByidVO(e.getWxuserid()
					.toString());
			vo.setWxuservo(wxuservo);
			if (e.getParentMenuId() != null
					&& e.getParentMenuId().longValue() > 0) {
				WXMenuVO p = findByid(e.getParentMenuId().toString());
				if (p != null && p.getMenu() != null)
					vo.setParentMenu(p.getMenu());
			}
			vo.setMenuType(statusService.findByid(e.getTypeid().toString()));
			vo.setMenu(e);
			res.add(vo);
		}
		return res;
	}

	private String getHql(WXMenuVO query) {
		StringBuffer hql = new StringBuffer();
		hql.append("from WXMenu");

		if (query == null)
			return hql.append(" order by id desc").toString();

		int isAnd = 0;

		boolean iswhere = false;
		iswhere = query != null
				&& (query.getMenu() != null && (query.getMenu().getWxuserid() != null
						|| !StringHelper.isNull(query.getMenu().getMenuName())
						|| query.getMenu().getMenuLevel() != null || query
						.getMenu().getTypeid() != null));
		if (iswhere) {
			hql.append(" where ");
		}
		if (query.getMenu() != null && query.getMenu().getWxuserid() != null) {
			if (isAnd > 0)
				hql.append(" and ");
			hql.append(" wxuserid=").append(query.getMenu().getWxuserid());
			isAnd++;
		}
		if (query.getMenu() != null
				&& !StringHelper.isNull(query.getMenu().getMenuName())) {
			if (isAnd > 0)
				hql.append(" and ");
			hql.append(" menuName like '%")
					.append(query.getMenu().getMenuName()).append("%'");
			isAnd++;
		}
		if (query.getMenu() != null && query.getMenu().getMenuLevel() != null) {
			if (isAnd > 0)
				hql.append(" and ");
			hql.append(" menuLevel =").append(query.getMenu().getMenuLevel());
			isAnd++;
		}
		if (query.getMenu() != null && query.getMenu().getTypeid() != null) {
			if (isAnd > 0)
				hql.append(" and ");
			hql.append(" typeid =").append(query.getMenu().getTypeid());
			isAnd++;
		}
		hql.append(" order by id desc");
		return hql.toString();
	}
}
