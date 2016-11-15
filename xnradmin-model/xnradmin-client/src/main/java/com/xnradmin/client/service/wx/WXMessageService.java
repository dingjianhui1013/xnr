/**
 *2014年8月31日 下午3:53:06
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
import com.xnradmin.core.service.UploadService;
import com.xnradmin.core.service.common.status.StatusService;
import com.xnradmin.core.util.Log4jUtil;
import com.xnradmin.po.CommonAttach;
import com.xnradmin.po.common.status.Status;
import com.xnradmin.po.wx.WXMessage;
import com.xnradmin.vo.client.wx.WXMessageVO;

/**
 * @author: liubin
 * 
 */
@Service("WXMessageService")
public class WXMessageService {

	static Log log = LogFactory.getLog(WXMessageService.class);

	private Log wxlog = Log4jUtil.getLog("wxport");

	@Autowired
	private CommonDAO commonDao;
	@Autowired
	private WXUserService wxuserService;
	@Autowired
	private UploadService uploadService;
	@Autowired
	private StatusService statusService;
	@Autowired
	private StaffService staffService;

	/**
	 * 1 - 必须指定一个微信用户<br>
	 * 
	 * @param vo
	 * @return int
	 */
	public int delete(WXMessageVO vo) {
		if (vo == null || vo.getWxmessage() == null) {
			return 1;
		}
		commonDao.delete(vo.getWxmessage());

		return 0;
	}

	/**
	 * 1 - 必须制定一个微信用户<br>
	 * 2 - 图文消息的图片数量最多只能有一张<br>
	 * 3 - 图文消息必须有一张图片<br>
	 * 
	 * @param vo
	 * @return int
	 */
	public int save(WXMessageVO vo) {
		if (vo == null || vo.getWxmessage() == null) {
			return 1;
		}
		WXMessage p = vo.getWxmessage();
		if (vo != null && vo.getUpload() != null
				&& !StringHelper.isNull(vo.getUpload().getGroupid())) {
			List<CommonAttach> l = uploadService.getAttachsByGroupid(vo
					.getUpload().getGroupid());
			if (l != null && l.size() > 1)
				return 2;
			p.setUploadGroupid(vo.getUpload().getGroupid());
		}
		p.setMsgTypeId(vo.getMsgType().getId());
		p.setCreateStaffID(vo.getCreateStaff().getId());
		p.setCreateTime(new Timestamp(System.currentTimeMillis()));

		// 文本消息
		// Status textStatus = statusService.find(WXMessage.class,
		// "msgType","1");
		// 图文消息
		Status newsStatus = statusService.findByStatusCode(WXMessage.class,
				"msgType", "2");
		if (p.getMsgTypeId().intValue() == newsStatus.getId().intValue()) {
			if (vo.getUpload() == null
					|| StringHelper.isNull(vo.getUpload().getGroupid()))
				return 3;
		}

		commonDao.save(p);
		return 0;
	}

	public int modify(WXMessageVO vo) {
		if (vo == null || vo.getWxmessage() == null
				|| vo.getWxmessage().getId() == null) {
			return 1;
		}
		WXMessage old = this.findById(vo.getWxmessage().getId().toString());

		WXMessage p = vo.getWxmessage();
		p.setCreateStaffID(old.getCreateStaffID());
		p.setCreateTime(old.getCreateTime());

		if (vo != null && vo.getUpload() != null
				&& !StringHelper.isNull(vo.getUpload().getGroupid())) {
			List<CommonAttach> l = uploadService.getAttachsByGroupid(vo
					.getUpload().getGroupid());
			if (l != null && l.size() > 1)
				return 2;
			p.setUploadGroupid(vo.getUpload().getGroupid());
		}
		p.setMsgTypeId(vo.getMsgType().getId());

		// 文本消息
		// Status textStatus = statusService.find(WXMessage.class,
		// "msgType","1");
		// 图文消息
		Status newsStatus = statusService.findByStatusCode(WXMessage.class,
				"msgType", "2");
		if (p.getMsgTypeId().intValue() == newsStatus.getId().intValue()) {
			if (vo.getUpload() == null
					|| StringHelper.isNull(vo.getUpload().getGroupid()))
				return 3;
		}
		commonDao.modify(p);
		return 0;
	}

	public WXMessageVO findByIdVO(String id) {
		WXMessage p = findById(id);
		WXMessageVO v = new WXMessageVO();
		v.setWxmessage(p);

		if (!StringHelper.isNull(p.getUploadGroupid())) {
			List<CommonAttach> lst = uploadService.getAttachsByGroupid(p
					.getUploadGroupid());
			v.setUpload(lst.get(0));
		}
		if (!StringHelper.isNull(p.getContent())) {
			if (p.getContent().trim().length() > 10)
				v.setMsgContent(p.getContent().trim());
			else
				v.setMsgContent(p.getContent());
		}
		v.setMsgType(statusService.findByid(p.getMsgTypeId().toString()));
		v.setCreateStaff(staffService.findByid(p.getCreateStaffID().toString()));
		return v;
	}

	public WXMessage findById(String id) {
		Object o = commonDao.findById(WXMessage.class, new Long(id));
		if (o == null)
			return null;
		return (WXMessage) o;
	}

	public int getCount(WXMessageVO query) {
		String hql = "select count(*) " + getHql(query);
		return commonDao.getNumberOfEntitiesWithHql(hql);
	}

	public List<WXMessageVO> voList(WXMessageVO query, int pageNo, int pageSize) {
		String hql = getHql(query);
		List<WXMessage> l = commonDao.getEntitiesByPropertiesWithHql(hql,
				pageNo, pageSize);
		if (l == null || l.size() <= 0)
			return null;

		List<WXMessageVO> res = new LinkedList<WXMessageVO>();
		for (WXMessage e : l) {
			WXMessageVO v = new WXMessageVO();
			v.setWxmessage(e);
			if (!StringHelper.isNull(e.getUploadGroupid())) {
				List<CommonAttach> lst = uploadService.getAttachsByGroupid(e
						.getUploadGroupid());
				v.setUpload(lst.get(0));
			}
			v.setMsgType(statusService.findByid(e.getMsgTypeId().toString()));
			if (!StringHelper.isNull(e.getContent())) {
				if (e.getContent().trim().length() > 10)
					v.setMsgContent(e.getContent().trim());
				else
					v.setMsgContent(e.getContent());
			}
			res.add(v);
		}
		return res;
	}

	private String getHql(WXMessageVO query) {
		StringBuffer hql = new StringBuffer();
		hql.append("from WXMessage ");
		boolean iswhere = query != null
				&& (query.getWxmessage() != null && (!StringHelper.isNull(query
						.getWxmessage().getContent())
						|| !StringHelper.isNull(query.getWxmessage()
								.getMsgTitle()) || query.getWxmessage()
						.getMsgTypeId() != null));
		if (iswhere) {
			hql.append(" where ");
		}
		boolean isAnd = false;
		if (query != null && query.getWxmessage() != null
				&& !StringHelper.isNull(query.getWxmessage().getContent())) {
			if (isAnd)
				hql.append(" and ");
			hql.append(" content like '%")
					.append(query.getWxmessage().getContent()).append("%'");
			isAnd = true;
		}
		if (query != null && query.getWxmessage() != null
				&& !StringHelper.isNull(query.getWxmessage().getMsgTitle())) {
			if (isAnd)
				hql.append(" and ");
			hql.append(" msgTitle like '%")
					.append(query.getWxmessage().getMsgTitle()).append("%'");
			isAnd = true;
		}
		if (query != null && query.getWxmessage() != null
				&& query.getWxmessage().getMsgTypeId() != null) {
			if (isAnd)
				hql.append(" and ");
			hql.append(" msgTypeId =").append(
					query.getWxmessage().getMsgTypeId());
			isAnd = true;
		}
		// hql.append(" order by id,sortOrder desc");

		return hql.toString();
	}
}
