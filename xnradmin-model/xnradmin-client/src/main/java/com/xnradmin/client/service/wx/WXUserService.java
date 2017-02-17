/**
 *2014年8月15日 下午4:56:39
 */
package com.xnradmin.client.service.wx;

import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cntinker.util.StringHelper;
import com.xnradmin.core.dao.CommonDAO;
import com.xnradmin.core.service.StaffService;
import com.xnradmin.core.service.common.status.StatusService;
import com.xnradmin.po.CommonStaff;
import com.xnradmin.po.common.status.Status;
import com.xnradmin.po.wx.WXAccessToken;
import com.xnradmin.po.wx.WXUser;
import com.xnradmin.vo.client.wx.WXUserVO;

/**
 * @author: liubin
 * 
 */
@Service("WXUserService")
public class WXUserService {
	static Log log = LogFactory.getLog(WXUserService.class);
	@Autowired
	private CommonDAO commonDao;
	@Autowired
	private StaffService staffService;
	@Autowired
	private StatusService statusService;
	@Autowired
	private WXAccessTokenService accessTokenService;
	
	
	
	/**
	 * return :<br>
	 * 0 - 正常<br>
	 * 1 - 已存在的appid<br>
	 * 2 - 微信用户已在系统内<br>
	 * 
	 * @param po
	 * @return int
	 */
	public int save(WXUser po) {

		String hqlAppid = "select count(*) from WXUser where appid='"
				+ po.getAppid() + "'";
		int countAppid = commonDao.getNumberOfEntitiesWithHql(hqlAppid);
		if (countAppid > 0) {
			return 1;
		}
		String hqlStaffid = "select count(*) from WXUser where staffId="
				+ po.getStaffId();
		int countStaffid = commonDao.getNumberOfEntitiesWithHql(hqlStaffid);
		if (countStaffid > 0) {
			return 2;
		}

		commonDao.save(po);
		return 0;
	}

	/**
	 * 0 - 正常<br>
	 * 1 - 已存在的appid<br>
	 * 2 - 微信用户已在系统内<br>
	 * 
	 * @param vo
	 * @return int
	 */
	public int modify(WXUserVO vo, CommonStaff modifyStaff) {
		//vo = findByidVO(vo.getWxuser().getId().toString());
		WXUser user = vo.getWxuser();
		
		CommonStaff staff = vo.getStaff();

		user.setStaffId(staff.getId());
		user.setModifyStaffId(modifyStaff.getId());
		user.setModifyTime(new Timestamp(System.currentTimeMillis()));

		/**
		 * String hqlAppid = "from WXUser where appid='" + user.getAppid() +
		 * "'"; List<WXUser> appObject =
		 * commonDao.getEntitiesByPropertiesWithHql( hqlAppid, 0, 0); if
		 * (appObject != null && appObject.size() > 0) { WXUser w =
		 * appObject.get(0); if (w.getAppid().equals(user.getAppid())) { return
		 * 1; } } String hqlStaffid =
		 * "select count(*) from WXUser where staffId=" + user.getStaffId();
		 * List<WXUser> staffObject = commonDao.getEntitiesByPropertiesWithHql(
		 * hqlStaffid, 0, 0); if (staffObject != null && staffObject.size() > 0)
		 * { WXUser w = staffObject.get(0); if
		 * (w.getAppid().equals(user.getAppid())) { return 2; } }
		 */

		commonDao.modify(user);
		return 0;
	}

	public WXUserVO findByidVO(String id) {
		WXUserVO v = new WXUserVO();

		WXUser wxuser = findByid(id);
		v.setWxuser(wxuser);

		CommonStaff staff = staffService.findByid(wxuser.getStaffId()
				.toString());
		v.setStaff(staff);

		Status status = statusService.findByid(wxuser.getStatus().toString());
		v.setStatus(status);

		WXAccessToken token = accessTokenService.findByWxuserId(wxuser.getId(), false);
		v.setWxaccessToken(token);
		
		v.setWxuserid(wxuser.getId().toString());
		v.setStaffName(staff.getStaffName());
		
		return v;
	}

	public WXUser findByid(String id) {
		Object o = commonDao.findById(WXUser.class, new Long(id));
		if (o == null)
			return null;
		return (WXUser) o;
	}
	

	public List<WXUserVO> list(WXUserVO query, int curPage, int pageSize) {
		String hql = getHql(query);
		List<WXUser> l = commonDao.getEntitiesByPropertiesWithHql(hql, curPage,
				pageSize);
		List<WXUserVO> res = new LinkedList<WXUserVO>();
		for (WXUser e : l) {
			CommonStaff staff = staffService
					.findByid(e.getStaffId().toString());
			Status status = statusService.findByid(e.getStatus().toString());
			WXAccessToken token = accessTokenService.findByWxuserId(e.getId(), false);
			WXUserVO v = new WXUserVO();
			v.setWxuser(e);
			v.setStatus(status);
			v.setStaff(staff);
			v.setWxaccessToken(token);
			res.add(v);
		}
		return res;
	}

	public int getCount(WXUserVO query) {
		String hql = "select count(*) " + getHql(query);
		return commonDao.getNumberOfEntitiesWithHql(hql);
	}

	private String getHql(WXUserVO query) {
		StringBuffer sb = new StringBuffer();
		sb.append("from WXUser");
		if (query == null || query.getWxuser() == null) {
			return sb.toString();
		}
		int isAnd = 0;
		if (!StringHelper.isNull(query.getWxuser().getAppid())
				|| !StringHelper.isNull(query.getWxuser().getToken())) {
			sb.append(" where ");
		}
		if (!StringHelper.isNull(query.getWxuser().getAppid())) {
			if (isAnd > 0)
				sb.append(" and ");
			sb.append(" appid like '%").append(query.getWxuser().getAppid())
					.append("%'");
		}
		if (!StringHelper.isNull(query.getWxuser().getToken())) {
			if (isAnd > 0)
				sb.append(" and ");
			sb.append(" token like '%").append(query.getWxuser().getToken())
					.append("%'");
		}
		if (query.getWxuser().getStatus() != null
				&& query.getWxuser().getStatus().intValue() > 0) {
			if (isAnd > 0)
				sb.append(" and ");
			sb.append(" status =").append(query.getWxuser().getStatus());
		}
		if (query.getWxuser().getStaffId() != null
				&& query.getWxuser().getStaffId().intValue() > 0) {
			if (isAnd > 0)
				sb.append(" and ");
			sb.append(" staffId =").append(query.getStaff().getId());
		}
		
		
		return sb.toString();
	}

}
