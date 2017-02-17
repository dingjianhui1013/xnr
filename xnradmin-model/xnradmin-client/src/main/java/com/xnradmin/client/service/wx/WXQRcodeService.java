/**
 *2014年9月12日 上午10:59:16
 */
package com.xnradmin.client.service.wx;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.cntinker.util.FileHelper;
import com.cntinker.util.HttpHelper;
import com.cntinker.util.QrcodeUtil;
import com.cntinker.util.StringHelper;
import com.cntinker.uuid.UUIDGener;
import com.google.zxing.WriterException;
import com.xnradmin.core.dao.CommonDAO;
import com.xnradmin.core.service.StaffService;
import com.xnradmin.core.service.UploadService;
import com.xnradmin.core.service.common.status.StatusService;
import com.xnradmin.core.util.Log4jUtil;
import com.xnradmin.dto.wx.WXUrl;
import com.xnradmin.po.CommonAttach;
import com.xnradmin.po.CommonStaff;
import com.xnradmin.po.common.status.Status;
import com.xnradmin.po.wx.WXQRcode;
import com.xnradmin.vo.client.wx.WXQrcodeVO;
import com.xnradmin.vo.client.wx.WXUserVO;

/**
 * @author: liubin
 *
 */
@Service("WXQRcodeService")
public class WXQRcodeService {

	static Log log = LogFactory.getLog(WXQRcodeService.class);

	private Log wxlog = Log4jUtil.getLog("wxport");

	private Log exlog = Log4jUtil.getLog("ex");

	@Autowired
	private CommonDAO commonDao;

	@Autowired
	private StatusService statusService;
	@Autowired
	private WXAccessTokenService accessTokenService;
	@Autowired
	private UploadService uploadService;
	@Autowired
	private StaffService staffService;
	@Autowired
	private WXUserService wxuserService;

	public WXQrcodeVO findByStaffid(Integer staffid) {
		String hql = "from WXQRcode where staffId=" + staffid;
		List<WXQRcode> lst = commonDao
				.getEntitiesByPropertiesWithHql(hql, 0, 0);
		if (lst == null || lst.size() <= 0)
			return null;
		WXQRcode p = lst.get(0);
		WXQrcodeVO v = this.findByID(p.getId().toString());
		return v;
	}

	public WXQrcodeVO findByID(String id) {
		Object obj = commonDao.findById(WXQRcode.class, new Long(id));
		if (obj == null)
			return null;
		WXQRcode po = (WXQRcode) obj;
		WXQrcodeVO res = new WXQrcodeVO();
		res.setWxqrcode(po);
		CommonStaff staff = staffService.findByid(po.getStaffId().toString());
		res.setStaff(staff);
		CommonAttach attach = uploadService.getAttach(po.getAttachId());
		res.setAttach(attach);
		WXUserVO wxuservo = wxuserService.findByidVO(po.getWxuserid()
				.toString());
		res.setWxuservo(wxuservo);
		return res;
	}

	public List<WXQrcodeVO> getVoList(WXQrcodeVO query, int pageNo, int pageSize) {
		String hql = getHql(query);
		List<WXQrcodeVO> res = new LinkedList<WXQrcodeVO>();
		List<WXQRcode> lst = commonDao.getEntitiesByPropertiesWithHql(hql,
				pageNo, pageSize);
		for (WXQRcode e : lst) {
			WXQrcodeVO v = new WXQrcodeVO();
			v.setWxqrcode(e);
			CommonAttach attach = uploadService.getAttach(e.getAttachId());
			v.setAttach(attach);
			CommonStaff staff = staffService
					.findByid(e.getStaffId().toString());
			v.setStaff(staff);
			WXUserVO wxuservo = wxuserService.findByidVO(e.getWxuserid()
					.toString());
			v.setWxuservo(wxuservo);

			res.add(v);
		}
		return res;
	}

	public int getCount(WXQrcodeVO query) {
		String hql = "select count(*) " + getHql(query);
		return commonDao.getNumberOfEntitiesWithHql(hql);
	}

	private String getHql(WXQrcodeVO query) {
		StringBuffer sb = new StringBuffer();
		sb.append("from WXQRcode ");
		boolean isWhere = false;
		boolean isAnd = false;
		if (query == null) {
			return sb.toString();
		}
		if (query != null
				&& (query.getWxqrcode() != null && (query.getWxqrcode()
						.getAttachId() != null
						|| query.getWxqrcode().getStaffId() != null
						|| !StringHelper
								.isNull(query.getWxqrcode().getTicket()) || query
						.getWxqrcode().getWxuserid() != null))) {
			isWhere = true;
		}
		if (isWhere)
			sb.append(" where ");
		if (query.getWxqrcode() != null
				&& query.getWxqrcode().getAttachId() != null) {
			if (isAnd)
				sb.append(" and ");
			sb.append(" attachId=").append(query.getWxqrcode().getAttachId());
			isAnd = true;
		}
		if (query.getWxqrcode() != null
				&& query.getWxqrcode().getStaffId() != null) {
			if (isAnd)
				sb.append(" and ");
			sb.append(" staffId=").append(query.getWxqrcode().getStaffId());
			isAnd = true;
		}
		if (query.getWxqrcode() != null
				&& !StringHelper.isNull(query.getWxqrcode().getTicket())) {
			if (isAnd)
				sb.append(" and ");
			sb.append(" ticket='").append(query.getWxqrcode().getTicket())
					.append("'");
			isAnd = true;
		}
		if (query.getWxqrcode() != null
				&& query.getWxqrcode().getWxuserid() != null) {
			if (isAnd)
				sb.append(" and ");
			sb.append(" wxuserid=").append(query.getWxqrcode().getWxuserid());
			isAnd = true;
		}
		return sb.toString();
	}

	public WXQRcode genNoLimitQRcode(String wxuserid, String scene_id)
			throws IOException {
		return processGenQRcode(wxuserid, scene_id, false);
	}

	public WXQRcode genTempQRcode(String wxuserid, String scene_id)
			throws IOException {
		return processGenQRcode(wxuserid, scene_id, true);
	}

	private WXQRcode processGenQRcode(String wxuserid, String scene_id,
			boolean isTemp) throws IOException {
		WXQRcode p = new WXQRcode();
		String body = "{\"action_name\": \"QR_LIMIT_SCENE\", \"action_info\": {\"scene\": {\"scene_id\": "
				+ scene_id + "}}}";
		String tempBody = "{\"expire_seconds\": 1800, \"action_name\": \"QR_SCENE\",\"action_info\": {\"scene\": {\"scene_id\": "
				+ scene_id + "}}}";
		Status status = statusService.find(WXUrl.class, "url", "生成带参数的二维码");
		String url = status.getReadme();
		url += accessTokenService.findByWxuserId(new Long(wxuserid), false)
				.getAccessToken();

		String post = "";
		if (isTemp)
			post = tempBody;
		else
			post = body;

		String res = HttpHelper.postXml(url, post);

		log.debug("processGenQRcode : " + res);

		JSONObject resObj = null;
		try {
			resObj = new JSONObject(res);

			if (resObj == null || StringHelper.isNull(resObj.toString()))
				return null;

			String ticket = resObj.get("ticket").toString();
			String u = resObj.get("url").toString();

			p.setUrl(u);
			p.setTicket(ticket);
			p.setWxuserid(new Long(wxuserid));
			p = insDB(p);

		} catch (JSONException e) {
			e.printStackTrace();
			exlog.error(StringHelper.getStackInfo(e));
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			exlog.error(StringHelper.getStackInfo(e));
			return null;
		}

		return p;
	}

	private WXQRcode insDB(WXQRcode p) throws Exception {
		if (p == null || StringHelper.isNull(p.getTicket()))
			return null;
		String gourpid = UUIDGener.getUUID();

		Status tmpath = statusService.findByStatusCode(WXQRcode.class,
				"temppath", "1");
		FileHelper.mkdir(tmpath.getStatusName());

		CommonAttach po = new CommonAttach();
		po.setUploadStaffid(5);
		po.setUploadStaffName("system");

		po.setUploadStaffOrgid(10);
		po.setUploadStaffOrgName("system");

		String f = genQrcodeFile(p.getUrl());
		File file = new File(f);
		String oldFileName = "wxqrcode.png";

		CommonAttach up = uploadService.upload(oldFileName, gourpid, "二维码",
				file, po);
		p.setAttachId(up.getId());
		this.commonDao.save(p);
		return p;
	}

	private String genQrcodeFile(String url) throws IOException,
			WriterException {
		String text = url;
		int width = 500;
		int height = 500;
		String format = "png";
		String charset = "utf-8";

		Status tmpath = statusService.findByStatusCode(WXQRcode.class,
				"temppath", "1");
		String f = tmpath.getStatusName() + "temp.png";

		QrcodeUtil.createImg(text, f, width, height, format, charset);

		return f;
	}

	private String path;

	public String getPath() {
		return path;
	}

	@Value(value = "${upload.path}")
	public void setPath(String path) {
		this.path = path;
	}
}
