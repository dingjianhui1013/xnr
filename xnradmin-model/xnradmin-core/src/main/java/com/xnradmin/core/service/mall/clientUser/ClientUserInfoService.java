/**
 *2012-5-11 上午9:00:02
 */
package com.xnradmin.core.service.mall.clientUser;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cntinker.util.StringHelper;
import com.xnradmin.constant.ViewConstant;
import com.xnradmin.core.dao.CommonDAO;
import com.xnradmin.core.dao.CommonJDBCDAO;
import com.xnradmin.core.dao.mall.clientUser.ClientUserInfoDAO;
import com.xnradmin.core.util.SecureUtil;
import com.xnradmin.core.util.SpringBase;
import com.xnradmin.po.client.ClientUserInfo;
import com.xnradmin.po.mall.commodity.Category;
import com.xnradmin.vo.client.ClientUserVO;
import com.xnradmin.vo.mall.CommodityVO;

/**
 * @autohr: xiang_liu
 * 
 */
@Service("ClientUserInfoService")
public class ClientUserInfoService {

	@Autowired
	private ClientUserInfoDAO dao;

	@Autowired
	private CommonDAO commonDao;

	static Log log = LogFactory.getLog(ClientUserInfoService.class);

	/**
	 * 
	 * @param po
	 * @return int
	 */
	public int save(ClientUserInfo po) {
		if (po.getIsmobileuser() != null
				&& po.getIsmobileuser()==1
				&& this.dao.findByOlay("mobile", po.getMobile(), null).size() > 0) {
			return 1;
		} else if (po.getIsemailuser() != null
				&& po.getIsemailuser()==1
				&& this.dao.findByOlay("email", po.getEmail(), null).size() > 0) {
			return 1;
		} else if (po.getIswxuser() != null
				&& po.getIswxuser()==1
				&& this.dao.findByOlay("wxopenuid", po.getWxopenuid(), null)
						.size() > 0) {
			return 1;
		} else{
			Serializable serializable = dao.save(po);
			return 0;
		}
	}

	/**
	 * 
	 * @param po
	 * @return int
	 */
	public int saveBusinessUser(ClientUserInfo po) {
		Serializable serializable; 
		if (this.dao.findByOlay("staffId", po.getStaffId(), null).size() > 0) {
			ClientUserInfo clientUserInfo = (ClientUserInfo) this.dao
					.findByOlay("staffId", po.getStaffId(), null).get(0);
			serializable = clientUserInfo.getId();
		} else {
			serializable = dao.save(po);
		}
		return (Integer) serializable;
	}
	/**
	 * 
	 * @param po
	 * @return int
	 */
	public Map saveRetrunId(ClientUserInfo po) {
		Map map = new HashMap();
		if (po.getIsmobileuser()!=null && po.getIsmobileuser().equals("1")
				&& this.dao.findByOlay("mobile", po.getMobile(), null).size() > 0) {
			map.put("code", "1");
		} else if (po.getIsemailuser()!=null && po.getIsemailuser().equals("1")
				&& this.dao.findByOlay("email", po.getEmail(), null).size() > 0) {
			map.put("code", "1");
		} else if (po.getIswxuser()!=null && po.getIswxuser().equals("1")
				&& this.dao.findByOlay("wxopenuid", po.getWxopenuid(), null)
						.size() > 0) {
			map.put("code", "1");
		} else {
			Serializable serializable = dao.save(po);
			map.put("code", "0");
			map.put("serializable", serializable);
		}
		return map;
	}

	/**
	 * 
	 * @param po
	 * @return int
	 */
	public Serializable saveWx(ClientUserInfo po) {
		Serializable serializable;
		if (this.dao.findByOlay("wxopenuid", po.getWxopenuid(), null).size() > 0) {
			ClientUserInfo clientUserInfo = (ClientUserInfo) this.dao
					.findByOlay("wxopenuid", po.getWxopenuid(), null).get(0);
			serializable = clientUserInfo.getId();
		} else {
			serializable = dao.save(po);
		}
		return serializable;
	}

	public ClientUserInfo findByid(String id) {
		return dao.findById(new Integer(id));
	}

	/**
	 * 
	 * @param mobile
	 * @return po
	 */
	public ClientUserInfo findByMobile(String mobile) {
		List l = dao.findByProperty("mobile", mobile);
		ClientUserInfo cui = new ClientUserInfo();
		if (l != null && l.size() > 0) {
			cui = (ClientUserInfo) l.get(0);
		}
		return cui;
	}
	
	public ClientUserInfo findByWxOpenid(String wxopenid){
		String hql = "from ClientUserInfo where wxopenuid='"+wxopenid+"'";
		List<ClientUserInfo> l = commonDao.getEntitiesByPropertiesWithHql(hql, 1, 1);
		if(l==null || l.size()<=0)
			return null;
		return l.get(0);
	}
	
	/**
	 * 
	 * @param mobile
	 * @return po
	 */
	public ClientUserInfo findByProperty(String key, Object value) {
		List l = dao.findByProperty(key, value);
		ClientUserInfo cui = new ClientUserInfo();
		if (l != null && l.size() > 0) {
			cui = (ClientUserInfo) l.get(0);
		}
		return cui;
	}

	/**
	 * @param po
	 * @return int
	 */
	public int modify(ClientUserInfo po) {
		if (po.getIsmobileuser()!=null && po.getIsmobileuser().equals("1")
				&& this.dao.findByOlay("mobile", po.getMobile(), po.getId())
						.size() > 0) {
			return 1;
		} else if (po.getIsemailuser()!=null && po.getIsemailuser().equals("1")
				&& this.dao.findByOlay("email", po.getEmail(), po.getId())
						.size() > 0) {
			return 1;
		} else if (po.getIswxuser()!=null &&po.getIswxuser().equals("1")
				&& this.dao.findByOlay("wxopenuid", po.getWxopenuid(),
						po.getId()).size() > 0) {
			return 1;
		}
		this.dao.merge(po);
		return 0;
	}

	public void del(String id) {
		ClientUserInfo po = this.dao.findById(new Integer(id));
		this.dao.delete(po);
	}

	public int removeClientUserInfoId(Integer id) {
		return dao.removeClientUserInfoId(id);
	}

	/**
	 * @return int
	 */
	public int getCount(String id, String nickName, String email,
			String mobile, String status, String type, String uuid,
			String createStartTime, String createEndTime, String sourceId,
			String sourceType, String wxnickname, String wxsex, String wxcity,
			String wxprovince, String wxcountry, String isappuser,
			String iswxuser, String ismobileuser, String isemailuser,
			String iswebsiteuser) {
		String hql = "select count(*) from ClientUserInfo";
		hql = hql + " where 1=1";
		if (!StringHelper.isNull(nickName)) {
			hql = hql + " and nickName like '%" + nickName + "%'";
		}
		if (!StringHelper.isNull(email)) {
			hql = hql + " and email like '%" + email + "%'";
		}
		if (!StringHelper.isNull(mobile)) {
			hql = hql + " and mobile like '%" + mobile + "%'";
		}
		if (!StringHelper.isNull(status)) {
			hql = hql + " and status = " + status;
		}
		if (!StringHelper.isNull(type)) {
			hql = hql + " and type = " + type;
		}
		if (!StringHelper.isNull(uuid)) {
			hql = hql + " and uuid ='" + uuid + "'";
		}
		if (!StringHelper.isNull(createStartTime)) {
			hql = hql + " and createTime >'" + createStartTime + "'";
		}
		if (!StringHelper.isNull(createEndTime)) {
			hql = hql + " and createTime <'" + createEndTime + "'";
		}
		if (!StringHelper.isNull(sourceId)) {
			hql = hql + " and sourceId =" + sourceId;
		}
		if (!StringHelper.isNull(sourceType)) {
			hql = hql + " and sourceType =" + sourceType;
		}
		if (!StringHelper.isNull(wxnickname)) {
			hql = hql + " and wxnickname like '%" + wxnickname + "%'";
		}
		if (!StringHelper.isNull(wxsex)) {
			hql = hql + " and wxsex ='" + wxsex + "'";
		}
		if (!StringHelper.isNull(wxcity)) {
			hql = hql + " and wxcity like '%" + wxcity + "%'";
		}
		if (!StringHelper.isNull(wxprovince)) {
			hql = hql + " and wxprovince like '%" + wxprovince + "%'";
		}
		if (!StringHelper.isNull(wxcountry)) {
			hql = hql + " and wxcountry like '%" + wxcountry + "%'";
		}
		if (!StringHelper.isNull(isappuser)) {
			hql = hql + " and isappuser = " + isappuser;
		}
		if (!StringHelper.isNull(iswxuser)) {
			hql = hql + " and iswxuser = " + iswxuser;
		}
		if (!StringHelper.isNull(ismobileuser)) {
			hql = hql + " and ismobileuser = " + ismobileuser;
		}
		if (!StringHelper.isNull(isemailuser)) {
			hql = hql + " and isemailuser = " + isemailuser;
		}
		if (!StringHelper.isNull(iswebsiteuser)) {
			hql = hql + " and iswebsiteuser = " + iswebsiteuser;
		}
		return this.commonDao.getNumberOfEntitiesWithHql(hql);
	}

	/**
	 * @param curPage
	 * @param pageSize
	 * @param orderField
	 * @param direction
	 * @return List<GetOrgListVO>
	 */
	public List<ClientUserVO> listVO(String id, String nickName, String email,
			String mobile, String status, String type, String uuid,
			String createStartTime, String createEndTime, String sourceId,
			String sourceType, String wxnickname, String wxsex, String wxcity,
			String wxprovince, String wxcountry, String isappuser,
			String iswxuser, String ismobileuser, String isemailuser,
			String iswebsiteuser, int curPage, int pageSize, String orderField,
			String direction) {
		String hql = "from ClientUserInfo";
		hql = hql + " where 1=1";
		if (!StringHelper.isNull(nickName)) {
			hql = hql + " and nickName like '%" + nickName + "%'";
		}
		if (!StringHelper.isNull(email)) {
			hql = hql + " and email like '%" + email + "%'";
		}
		if (!StringHelper.isNull(mobile)) {
			hql = hql + " and mobile like '%" + mobile + "%'";
		}
		if (!StringHelper.isNull(status)) {
			hql = hql + " and status = " + status;
		}
		if (!StringHelper.isNull(type)) {
			hql = hql + " and type = " + type;
		}
		if (!StringHelper.isNull(uuid)) {
			hql = hql + " and uuid ='" + uuid + "'";
		}
		if (!StringHelper.isNull(createStartTime)) {
			hql = hql + " and createTime >'" + createStartTime + "'";
		}
		if (!StringHelper.isNull(createEndTime)) {
			hql = hql + " and createTime <'" + createEndTime + "'";
		}
		if (!StringHelper.isNull(sourceId)) {
			hql = hql + " and sourceId =" + sourceId;
		}
		if (!StringHelper.isNull(sourceType)) {
			hql = hql + " and sourceType =" + sourceType;
		}
		if (!StringHelper.isNull(wxnickname)) {
			hql = hql + " and wxnickname like '%" + wxnickname + "%'";
		}
		if (!StringHelper.isNull(wxsex)) {
			hql = hql + " and wxsex ='" + wxsex + "'";
		}
		if (!StringHelper.isNull(wxcity)) {
			hql = hql + " and wxcity like '%" + wxcity + "%'";
		}
		if (!StringHelper.isNull(wxprovince)) {
			hql = hql + " and wxprovince like '%" + wxprovince + "%'";
		}
		if (!StringHelper.isNull(wxcountry)) {
			hql = hql + " and wxcountry like '%" + wxcountry + "%'";
		}
		if (!StringHelper.isNull(isappuser)) {
			hql = hql + " and isappuser = " + isappuser;
		}
		if (!StringHelper.isNull(iswxuser)) {
			hql = hql + " and iswxuser = " + iswxuser;
		}
		if (!StringHelper.isNull(ismobileuser)) {
			hql = hql + " and ismobileuser = " + ismobileuser;
		}
		if (!StringHelper.isNull(isemailuser)) {
			hql = hql + " and isemailuser = " + isemailuser;
		}
		if (!StringHelper.isNull(iswebsiteuser)) {
			hql = hql + " and iswebsiteuser = " + iswebsiteuser;
		}
		if (!StringHelper.isNull(orderField) && !StringHelper.isNull(direction)) {
			hql = hql + " order by " + orderField + " " + direction;
		} else {
			hql += " order by id desc";
		}
		List l = commonDao.getEntitiesByPropertiesWithHql(hql, curPage,
				pageSize);
		List<ClientUserVO> resList = new ArrayList<ClientUserVO>();
		for (int i = 0; i < l.size(); i++) {
			ClientUserInfo po = (ClientUserInfo) l.get(i);
			ClientUserVO vo = new ClientUserVO();
			if (po.getId() != null) {
				vo.setClientUserInfoId(po.getId().toString());
			}
			vo.setNickName(po.getNickName());
			vo.setEmail(po.getEmail());
			vo.setMobile(po.getMobile());
			vo.setLoginPassWord(po.getLoginPassWord());
			vo.setPaymentPassword(po.getPaymentPassword());
			if (po.getStatus() != null) {
				vo.setStatus(po.getStatus().toString());
			}
			vo.setStatusName(po.getStatusName());
			if (po.getType() != null) {
				vo.setType(po.getType().toString());
			}
			vo.setTypeName(po.getTypeName());
			vo.setUuid(po.getUuid());
			if (po.getCreateTime() != null) {
				vo.setClientUserInfoCreateTime(po.getCreateTime().toString());
			}
			if (po.getModifyTime() != null) {
				vo.setClientUserInfoModifyTime(po.getModifyTime().toString());
			}
			if (po.getLastLoginTime() != null) {
				vo.setLastLoginTime(po.getLastLoginTime().toString());
			}
			if (po.getSourceId() != null) {
				vo.setSourceId(po.getSourceId().toString());
			}
			vo.setSourceName(po.getSourceName());
			if (po.getSourceType() != null) {
				vo.setSourceType(po.getSourceType().toString());
			}
			vo.setSourceTypeName(po.getSourceTypeName());
			if (po.getDiscount() != null) {
				vo.setDiscount(po.getDiscount().toString());
			}
			vo.setWxfromusername(po.getWxfromusername());
			vo.setWxtousername(po.getWxtousername());
			if (po.getWxsubtime() != null) {
				vo.setWxsubtime(StringHelper.getSystime(
						ViewConstant.PAGE_DATE_FORMAT_SEC, po.getWxsubtime()
								.getTime()));
			}
			if (po.getWxunsubtime() != null) {
				vo.setWxunsubtime(StringHelper.getSystime(
						ViewConstant.PAGE_DATE_FORMAT_SEC, po.getWxunsubtime()
								.getTime()));
			}
			if (po.getWxlastActvieTime() != null) {
				vo.setWxlastActvieTime(StringHelper.getSystime(
						ViewConstant.PAGE_DATE_FORMAT_SEC, po
								.getWxlastActvieTime().getTime()));
			}
			vo.setWxmsgtype(po.getWxmsgtype());
			vo.setWxevent(po.getWxevent());
			vo.setWxnetworktype(po.getWxnetworktype());
			vo.setWxopenuid(po.getWxopenuid());
			vo.setWxnickname(po.getWxnickname());
			vo.setWxsex(po.getWxsex());
			vo.setWxlanguage(po.getWxlanguage());
			if (po.getWxstatusid() != null) {
				vo.setWxstatusid(po.getWxstatusid().toString());
			}
			vo.setWxstatusName(po.getWxstatusName());
			vo.setWxcity(po.getWxcity());
			vo.setWxprovince(po.getWxprovince());
			vo.setWxcountry(po.getWxcountry());
			vo.setWxheadimgurl(po.getWxheadimgurl());
			vo.setWxunionid(po.getWxunionid());
			if (po.getIsappuser() != null) {
				vo.setIsappuser(po.getIsappuser().toString());
			}
			if (po.getIsemailuser() != null) {
				vo.setIsemailuser(po.getIsemailuser().toString());
			}
			if (po.getIsmobileuser() != null) {
				vo.setIsmobileuser(po.getIsmobileuser().toString());
			}
			if (po.getIswebsiteuser() != null) {
				vo.setIswebsiteuser(po.getIswebsiteuser().toString());
			}
			if (po.getIswxuser() != null) {
				vo.setIswxuser(po.getIswxuser().toString());
			}
			resList.add(vo);
		}
		return resList;
	}

	/**
	 * @return List<ClientUserInfo>
	 */
	public List<ClientUserInfo> listAll() {
		return dao.findAll();
	}

}
