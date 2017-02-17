/**
 *2014年9月9日 下午7:14:55
 */
package com.xnradmin.client.service.wx;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.DocumentException;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.cntinker.util.StringHelper;
import com.xnradmin.core.service.UploadService;
import com.xnradmin.core.service.common.status.StatusService;
import com.xnradmin.core.util.Log4jUtil;
import com.xnradmin.core.util.WxUtil;
import com.xnradmin.po.CommonAttach;
import com.xnradmin.po.common.status.Status;
import com.xnradmin.po.wx.WXMessage;
import com.xnradmin.po.wx.WXReactiveMessage;
import com.xnradmin.vo.client.wx.WXMessageVO;
import com.xnradmin.vo.client.wx.WXReactiveMessageVO;

/**
 * @author: liubin
 *
 */
@Service("WxReactiveMessagePortService")
public class WxReactiveMessagePortService {

	static Log log = LogFactory.getLog(WxReactiveMessagePortService.class);

	private Log wxlog = Log4jUtil.getLog("wxport");

	@Autowired
	private WXReactiveMessageService wxreactiveMessageService;
	@Autowired
	private StatusService statusService;
	@Autowired
	private UploadService uploadService;
	@Autowired
	private WXMessageService wxMessageService;

	private List<WXReactiveMessageVO> findByWxUserid(String wxuserid) {
		if (StringHelper.isNull(wxuserid))
			return null;
		WXReactiveMessageVO query = new WXReactiveMessageVO();
		WXReactiveMessage queryReactiveMsg = new WXReactiveMessage();
		queryReactiveMsg.setWxuserid(new Long(wxuserid));
		query.setReactiveMessage(queryReactiveMsg);

		List<WXReactiveMessageVO> rv = wxreactiveMessageService.voList(query,
				0, 0);

		return rv;
	}

	public WXReactiveMessageVO findByClick(String wxuserid, String clickContent)
			throws JSONException {
		List<WXReactiveMessageVO> l = findByWxUserid(wxuserid);
		for (WXReactiveMessageVO e : l) {
			if (e.getWxmenu() == null || e.getWxmenu().getMenu() == null)
				continue;
			if (e.getWxmenu().getMenu().getWxkey().equals(clickContent)) {
				WXReactiveMessageVO r = wxreactiveMessageService.findByID(e
						.getReactiveMessage().getId().toString());
				return r;
			}
		}
		return null;
	}

	public WXReactiveMessageVO findByInput(String wxuserid, String inputContent)
			throws JSONException {
		List<WXReactiveMessageVO> l = findByWxUserid(wxuserid);
		for (WXReactiveMessageVO e : l) {
			if (e.getReactiveMessage().getRecContent().equals(inputContent)) {
				WXReactiveMessageVO r = wxreactiveMessageService.findByID(e
						.getReactiveMessage().getId().toString());
				return r;
			}
		}
		return null;
	}

	public String toWxPortMsg(String wxInputSource, WXReactiveMessageVO vo)
			throws IllegalAccessException, DocumentException {

		WxUtil wu = new WxUtil(wxInputSource);
		String msgType = wu.getDataFromKey("MsgType");
		if (StringHelper.isNull(msgType))
			return "";

		if (vo.getMsgList() != null && vo.getMsgList().size() > 1) {
			return getNews(wu, vo);
		} else {
			WXMessageVO v = vo.getMsgList().get(0);
			v = wxMessageService.findByIdVO(v.getId());
			Status news = statusService.findByStatusCode(WXMessage.class,
					"msgType", "2");
			if (v.getWxmessage().getMsgTypeId().intValue() == news.getId()
					.intValue())
				return getNews(wu, vo);
			return getText(wu, vo);
		}

	}

	private String getNews(WxUtil wu, WXReactiveMessageVO vo)
			throws IllegalAccessException, DocumentException {
		Status urlRoot = statusService.findByStatusCode(WXMessage.class,
				"rootImgurl", "0");

		List<WXMessageVO> msgList = vo.getMsgList();

		StringBuffer out = new StringBuffer();
		out.append("<xml>");
		out.append("<ToUserName><![CDATA[" + wu.getDataFromKey("FromUserName")
				+ "]]></ToUserName>");
		out.append("<FromUserName><![CDATA[" + wu.getDataFromKey("ToUserName")
				+ "]]></FromUserName>");
		out.append("<CreateTime>"
				+ StringHelper.toUnixTimestamp(System.currentTimeMillis())
				+ "</CreateTime>");
		out.append("<MsgType><![CDATA[news]]></MsgType>");

		out.append("<ArticleCount>" + msgList.size() + "</ArticleCount>");
		out.append("<Articles>");
		for (WXMessageVO e : msgList) {
			e = wxMessageService.findByIdVO(e.getId());
			out.append("<item>");
			out.append("<Title><![CDATA[" + e.getWxmessage().getMsgTitle()
					+ "]]></Title> ");
			out.append("<Description><![CDATA[" + e.getWxmessage().getContent()
					+ "]]></Description>");
			List<CommonAttach> attachList = uploadService.getByGroupid(e
					.getWxmessage().getUploadGroupid());
			if (attachList == null || attachList.size() <= 0)
				return "";
			String imgUrl = urlRoot.getStatusName() + this.webPath
					+ attachList.get(0).getPath()
					+ attachList.get(0).getAttachName();
			out.append("<PicUrl><![CDATA[" + imgUrl + "]]></PicUrl>");

			String u = "";
			u = getUrl(e.getWxmessage().getClickUrl(),
					"uid=" + wu.getDataFromKey("FromUserName"));
			out.append("<Url><![CDATA[" + u + "]]></Url>");
			out.append("</item>");
		}

		out.append("</Articles>");
		out.append("</xml>");

		wxlog.debug(out.toString());

		return out.toString();
	}

	private String getUrl(String sourceUrl, String parameter) {
		StringBuffer sb = new StringBuffer();
		sb.append(sourceUrl);
		if (sourceUrl.endsWith("/") || sourceUrl.indexOf("?") < 0) {
			sb.append("?");
		} else {
			sb.append("&");
		}
		sb.append(parameter);
		return sb.toString();
	}

	private String getText(WxUtil wu, WXReactiveMessageVO vo)
			throws IllegalAccessException, DocumentException {
		StringBuffer out = new StringBuffer();
		List<WXMessageVO> msgList = vo.getMsgList();
		if (msgList == null || msgList.size() <= 0)
			return "";
		WXMessageVO msg = msgList.get(0);
		msg = wxMessageService.findByIdVO(msg.getId());
		out.append("<xml>");
		out.append("<ToUserName><![CDATA[" + wu.getDataFromKey("FromUserName")
				+ "]]></ToUserName>");
		out.append("<FromUserName><![CDATA[" + wu.getDataFromKey("ToUserName")
				+ "]]></FromUserName>");

		out.append("<CreateTime>"
				+ StringHelper.toUnixTimestamp(System.currentTimeMillis())
				+ "</CreateTime>");
		out.append("<MsgType><![CDATA[text]]></MsgType>");
		out.append("<Content><![CDATA[" + msg.getMsgContent() + "]]></Content>");
		out.append("</xml>");
		return out.toString();
	}

	private String webPath;

	public String getWebPath() {
		return webPath;
	}

	@Value(value = "${upload.webPath}")
	public void setWebPath(String webPath) {
		this.webPath = webPath;
	}
}
