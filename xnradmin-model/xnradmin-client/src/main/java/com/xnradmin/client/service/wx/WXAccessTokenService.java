/**
 *2014年8月22日 下午1:44:59
 */
package com.xnradmin.client.service.wx;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
import com.xnradmin.po.common.status.Status;
import com.xnradmin.po.wx.WXAccessToken;
import com.xnradmin.po.wx.WXUser;

/**
 * @author: liubin
 * 
 */
@Service("WXAccessTokenService")
public class WXAccessTokenService {

	static Log log = LogFactory.getLog(WXAccessTokenService.class);
	@Autowired
	private CommonDAO commonDao;

	@Autowired
	private StaffService staffService;
	@Autowired
	private StatusService statusService;
	@Autowired
	private WXUserService wxuserService;

	private Log wxlog = Log4jUtil.getLog("wxport");

	private Log exlog = Log4jUtil.getLog("ex");

	public WXAccessToken findByWxuserId(Long id, boolean reload) {
		WXAccessToken query = new WXAccessToken();
		query.setWxuserid(id);
		List<WXAccessToken> l = getList(query, 0, 0);
		if (l == null || l.size() <= 0) {
			WXAccessToken res = genToken(query, true);
			return res;
		}
		WXAccessToken res = l.get(0);
		
		return findByid(res.getId().toString(), reload);
	}

	public WXAccessToken reloadTokenByWXUserid(Long id) {
		return findByWxuserId(id, true);
	}

	/**
	 * 
	 * 带自动检测超时获取
	 * 
	 * @param id
	 * @return WXAccessToken
	 */
	public WXAccessToken findByid(String id, boolean isReload) {
		
		WXAccessToken token = findByIDByDB(id);
		token = genToken(token, isReload);
		return token;
	}

	private WXAccessToken genToken(WXAccessToken token, boolean isReload) {
		WXUser wxuser = wxuserService.findByid(token.getWxuserid().toString());

		Long lastGenTime = 0l;
		String res = "";
		if (token != null && token.getLastGenTime() != null
				&& token.getLastGenTime().getTime() > 0) {
			lastGenTime = token.getLastGenTime().getTime();
		}
		if(token==null ){
			token = new WXAccessToken();
			token.setExpiresin(0);
		}
		if(token.getExpiresin()==null){
			token.setExpiresin(0);
		}
		
		// 重新获取
		if ((System.currentTimeMillis() - lastGenTime) / 1000 > token
				.getExpiresin()
				|| StringHelper.isNull(token.getAccessToken())
				|| isReload) {
			Status wxurl = statusService.find(WXUrl.class, "url",
					"获取access_token");

			StringBuffer url = new StringBuffer();
			url.append(wxurl.getReadme());
			url.append("&appid=").append(wxuser.getAppid());
			url.append("&secret=").append(wxuser.getAppSecret());

			try {
				res = HttpHelper.sendGet(url.toString());
				token.setRes(res);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				exlog.debug(" get access token network error: " + token);
				return token;
			}
			JSONObject resObj = null;
			try {
				resObj = new JSONObject(res);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				exlog.debug(" get access token return json error res content: "
						+ res);
				return token;
			}
			try {
				Object accessToken = resObj.get("access_token");
				Object expires_in = resObj.get("expires_in");

				token.setAccessToken(accessToken.toString());
				token.setExpiresin(new Integer(expires_in.toString()));
				token.setLastGenTime(new Timestamp(System.currentTimeMillis()));
				commonDao.modify(token);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

				try {
					Object errcode = resObj.get("errcode");
					Object errmsg = resObj.get("errmsg");

					token.setErrcode(errcode.toString());
					token.setErrmsg(errmsg.toString());
				} catch (JSONException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();

					token.setErrcode("-1");
					token.setErrmsg("获取errmsg或errcode错误");
				}
			}

		}
		wxlog.debug(token);
		return token;
	}

	private WXAccessToken findByIDByDB(String id) {
		if (StringHelper.isNull(id)) {
			return null;
		}
		Object o = commonDao.findById(WXAccessToken.class, new Long(id));
		if (o == null)
			return null;
		return (WXAccessToken) o;
	}

	public List<WXAccessToken> getList(WXAccessToken query, int curPage,
			int pageSize) {
		String hql = getHql(query);
		List<WXAccessToken> l = commonDao.getEntitiesByPropertiesWithHql(hql,
				curPage, pageSize);
		return l;
	}

	private String getHql(WXAccessToken query) {
		StringBuffer hql = new StringBuffer();
		hql.append("from WXAccessToken");
		if (query != null && (!StringHelper.isNull(query.getAccessToken())
				|| query.getWxuserid() != null)) {
			hql.append(" where ");
		} else {
			return hql.toString();
		}
		int and = 0;
		if (!StringHelper.isNull(query.getAccessToken())) {
			if (and > 0)
				hql.append(" and ");
			hql.append(" accessToken like '%").append(query.getAccessToken())
					.append("%'");
			and++;
		}
		if (query.getWxuserid() != null) {
			if (and > 0)
				hql.append(" and ");
			hql.append(" wxuserid=").append(query.getWxuserid());
			and++;
		}
		return hql.toString();
	}

}
