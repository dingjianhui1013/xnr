/**
 *2014年7月31日 下午2:03:59
 */
package com.xnradmin.client.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.codehaus.commons.compiler.CompileException;
import org.dom4j.DocumentException;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cntinker.util.SHA1;
import com.cntinker.util.StringHelper;
import com.xnradmin.client.service.wx.WXUserService;
import com.xnradmin.core.service.CommonPortService;
import com.xnradmin.core.service.ScriptService;
import com.xnradmin.core.service.common.status.StatusService;
import com.xnradmin.core.service.mall.clientUser.ClientUserInfoService;
import com.xnradmin.core.util.Log4jUtil;
import com.xnradmin.core.util.SpringBase;
import com.xnradmin.core.util.WxPortUtil;
import com.xnradmin.core.util.WxUtil;
import com.xnradmin.dto.ScriptDTO;
import com.xnradmin.dto.client.SyncDTOAck;
import com.xnradmin.dto.wx.WxUserCookie;
import com.xnradmin.po.CommonPort;
import com.xnradmin.po.common.status.Status;
import com.xnradmin.po.wx.WXUser;
import com.xnradmin.vo.client.script.ScriptVO;

/**
 * @author: liubin
 * 
 */
@Service("WxPortService")
public class WxPortService {

	private Log exLog = Log4jUtil.getLog("ex");

	private Log log = Log4jUtil.getLog("wxport");

	@Autowired
	private CommonPortService portService;

	@Autowired
	private StatusService statusService;

	@Autowired
	private ScriptService scriptService;

	@Autowired
	private WXUserService wxuserService;

	@Autowired
	private ClientUserInfoService clientUserInfoService;

	// / ┏┓　　　┏┓
	// ┏┛┻━━━┛┻┓
	// ┃　　　　　　　┃ 　
	// ┃　　　━　　　┃
	// ┃　┳┛　┗┳　┃
	// ┃　　　　　　　┃
	// ┃　　　┻　　　┃
	// ┃　　　　　　　┃
	// ┗━┓　　　┏━┛
	// //. ┃　　　┃ 神兽保佑　　　　　　　　
	// /// ┃　　　┃ 代码无BUG！
	// /// ┃　　　┗━━━┓
	// /// ┃　　　　　　　┣┓
	// /// ┃　　　　　　　┏┛
	// /// ┗┓┓┏━┳┓┏┛
	// ///// ┃┫┫　┃┫┫
	// ///// ┗┻┛　┗┻┛
	public void commonInterface(HttpServletRequest request,
			HttpServletResponse response) throws IOException, JSONException {
		JSONObject out = new JSONObject();
		SyncDTOAck res = null;
		String wxuserid = request.getParameter("userid");
		log.debug("wxuserid:" + wxuserid);
		try {

			String source = toSource(request);
			log.debug("in: " + source);

			if (!validWxUser(request, response, wxuserid)) {
				log.debug("平台内未找到用户：" + wxuserid);
				return;
			}

			// valid..
			if (validWx(wxuserid, request, response)) {
				log.debug("验证消息 wxuserid：" + wxuserid);
				return;
			}

			// process follow
			if (processFirstAllow(response, source, wxuserid)) {
				log.debug("首次关注验证消息 wxuserid:" + wxuserid);
				return;
			}
			res = processWxMain(source, wxuserid);
			if (res == null) {
				res = new SyncDTOAck();
				res.setWxOut("");
			}
		} catch (CompileException e) {
			Status status = statusService.find(SyncDTOAck.class, "status",
					"脚本编译异常");
			out.put("status", status.getStatusCode().toString());
			out.put("statusDesc", status.getStatusName());
			response.getWriter().print(out.toString());
		} catch (ClassNotFoundException e) {
			Status status = statusService.find(SyncDTOAck.class, "status",
					"调用类未找到");
			out.put("status", status.getStatusCode().toString());
			out.put("statusDesc", status.getStatusName());
			response.getWriter().print(out.toString());
		} catch (IOException e) {
			Status status = statusService.find(SyncDTOAck.class, "status",
					"WX接口IO异常");
			out.put("status", status.getStatusCode().toString());
			out.put("statusDesc", status.getStatusName());
			response.getWriter().print(out.toString());
		} catch (JSONException e) {
			Status status = statusService.find(SyncDTOAck.class, "status",
					"传入JSON格式有误");
			out.put("status", status.getStatusCode().toString());
			out.put("statusDesc", status.getStatusName());
			response.getWriter().print(out.toString());
		} catch (DocumentException e) {
			Status status = statusService.find(SyncDTOAck.class, "status",
					"传入微信XML格式有误");
			out.put("status", status.getStatusCode().toString());
			out.put("statusDesc", status.getStatusName());
			response.getWriter().print(out.toString());
		} catch (IllegalAccessException e) {
			Status status = statusService.find(SyncDTOAck.class, "status",
					"传入微信XML格式有误");
			out.put("status", status.getStatusCode().toString());
			out.put("statusDesc", status.getStatusName());
			response.getWriter().print(out.toString());
		}

		log.debug("out: " + res);
		response.setCharacterEncoding("UTF-8");
		response.getWriter().print(res.getWxOut());
	}

	private SyncDTOAck processWxMain(String source, String wxuserid)
			throws IllegalAccessException, DocumentException, CompileException,
			ClassNotFoundException, IOException {
		WxUtil wu = new WxUtil(source);
		StringBuffer out = new StringBuffer();
		String msgType = wu.getDataFromKey("MsgType");

		log.debug("processWxMain msgType:" + msgType);

		String action = getActionByMsgType(msgType);

		log.debug("processWxMain action:" + action);

		if (StringHelper.isNull(action)) {
			log.debug("未匹配上ACTION：" + source);
			return null;
		}

		List<CommonPort> l = portService.findByName(action);

		log.debug("processWxMain port size:" + l.size());

		if (l == null || l.size() <= 0) {
			Status status = statusService.find(SyncDTOAck.class, "status",
					"未找到接口");
			out.append("status:").append(status.getId().toString());
			out.append("statusDesc:").append(status.getStatusName());
		}
		CommonPort port = portService.findByNameOne(action);

		log.debug("processWxMain port:" + port);

		ScriptDTO sdto = SpringBase.getScriptDTO();
		ScriptVO v = scriptService.findByClassName(port.getScriptClassName());

		log.debug("processWxMain script:" + v);

		Object o = scriptService.executeMethod(sdto.getScriptMethods()[0],
				v.getScriptdto(), wxuserid, source);
		if (o == null)
			return null;
		return (SyncDTOAck) o;

	}

	private String getActionByMsgType(String msgType) {
		if (StringHelper.isNull(msgType))
			return null;
		// if(msgType.equals("event"))
		// return "wxuserEvent";
		return "wxuserMsg";
	}

	private boolean processFirstAllow(HttpServletResponse response,
			String source, String wxuserid) {

		WxUtil wu = new WxUtil(source);

		try {
			String touserName = wu.getDataFromKey("ToUserName");
			String FromUserName = wu.getDataFromKey("FromUserName");
			String createTime = wu.getDataFromKey("CreateTime");
			String event = wu.getDataFromKey("Event");

			log.debug("event: " + event);

			if (StringHelper.isNull(event))
				return false;

			if (!event.equalsIgnoreCase("subscribe")) {
				return false;
			}
			WXUser wxuser = wxuserService.findByid(wxuserid);
			log.debug(wxuser);

			StringBuffer sb = new StringBuffer();
			sb.append("<xml>");
			sb.append("<ToUserName><![CDATA[" + FromUserName
					+ "]]></ToUserName>");
			sb.append("<FromUserName><![CDATA[" + touserName
					+ "]]></FromUserName>");
			sb.append("<CreateTime>"
					+ StringHelper.toUnixTimestamp(System.currentTimeMillis())
					+ "</CreateTime>");
			sb.append("<MsgType><![CDATA[text]]></MsgType>");
			sb.append(" <Content><![CDATA[" + wxuser.getWelcome()
					+ "]]></Content>");
			sb.append(" </xml>");

			log.debug("follow : " + sb.toString());
			response.setCharacterEncoding("UTF-8");
			response.getWriter().print(
					sb.toString() + " | "
							+ new String(sb.toString().getBytes(), "UTF-8"));

		} catch (IllegalAccessException | DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * 验证微信用户的有效性
	 * 
	 * @param request
	 * @param response
	 * @param wxuserid
	 * @return boolean
	 * @throws JSONException
	 * @throws IOException
	 */
	private boolean validWxUser(HttpServletRequest request,
			HttpServletResponse response, String wxuserid)
			throws JSONException, IOException {
		JSONObject out = new JSONObject();
		if (StringHelper.isNull(wxuserid)) {
			Status status = statusService.find(SyncDTOAck.class, "status",
					"微信用户未找到");
			out.put("status", status.getId().toString());
			out.put("statusCode", status.getStatusCode().toString());
			out.put("statusDesc", status.getStatusName());
			response.getWriter().print(out.toString());
			log.debug(out);
			return false;
		}
		return true;
	}

	/**
	 * 返回true则不往下继续处理
	 * 
	 * @param request
	 * @param response
	 * @param source
	 * @return boolean
	 * @throws IOException
	 */
	private String processClick(HttpServletRequest request,
			HttpServletResponse response, String source) throws IOException {
		WxUtil wu = new WxUtil(source);
		String touserName = null;
		String FromUserName = null;
		String createTime = null;
		String event = null;
		try {
			touserName = wu.getDataFromKey("ToUserName");

			FromUserName = wu.getDataFromKey("FromUserName");
			createTime = wu.getDataFromKey("CreateTime");
			event = wu.getDataFromKey("Event");
		} catch (IllegalAccessException | DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			exLog.debug("处理cookie时解析错误:" + source);
			return null;
		}

		if (StringHelper.isNull(event) || !event.equalsIgnoreCase("VIEW")) {
			log.debug("非跳转链接事件");
			return null;
		}

		log.debug("FromUserName: " + FromUserName);
		String url = request.getParameter("url");
		if (StringHelper.isNull(url)) {
			log.debug("未设置URL，继续往下处理");
			return null;
		}
		StringBuffer sb = new StringBuffer();
		sb.append(url);
		sb.append("?openuid=").append(FromUserName);

		return sb.toString();
	}

	private boolean validWx(String wxuserid, HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		WXUser wxuser = wxuserService.findByid(wxuserid);
		String token = wxuser.getToken();// "baa79f7034f9cd21ef923123b58a2d24";
		String timestamp = request.getParameter("timestamp");
		String echostr = request.getParameter("echostr");
		String signature = request.getParameter("signature");
		String nonce = request.getParameter("nonce");

		if (StringHelper.isNull(timestamp) || StringHelper.isNull(echostr)
				|| StringHelper.isNull(signature) || StringHelper.isNull(nonce)) {
			log.debug("非接口验证返回");
			return false;
		}

		boolean res = false;

		String[] str = { token, timestamp, nonce };
		Arrays.sort(str); // 字典序排序
		String bigStr = str[0] + str[1] + str[2];
		// SHA1加密
		String digest = new SHA1().getDigestOfString(bigStr.getBytes())
				.toLowerCase();

		log.debug("valid signature：" + signature);
		log.debug("valid digest：" + digest);

		// 确认请求来至微信
		if (digest.equals(signature)) {
			response.getWriter().print(echostr);
			res = true;
		} else {
			log.debug("validWx error : is not wx message");
		}

		return res;
	}

	private String toSource(HttpServletRequest request) throws IOException {
		// String source = request.getInputStream().toString();
		BufferedReader br = new BufferedReader(new InputStreamReader(
				request.getInputStream()));
		StringBuffer sb = new StringBuffer();
		String currentLine = "";
		while ((currentLine = br.readLine()) != null) {
			sb.append(currentLine);
		}
		String source = sb.toString();
		log.debug(source);
		return source;
	}

}
