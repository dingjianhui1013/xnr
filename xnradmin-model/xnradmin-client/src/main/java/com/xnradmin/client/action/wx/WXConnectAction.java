package com.xnradmin.client.action.wx;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.cntinker.util.wx.connect.OAuth;
import com.cntinker.util.wx.connect.WXBizMsgCrypt;
import com.cntinker.util.wx.connect.WeixinUtil;
import com.xnradmin.client.service.wx.WXGetTokenService;
import com.xnradmin.client.service.wx.WeiXinConnectService;
import com.xnradmin.constant.StrutsResMSG;
import com.xnradmin.po.wx.connect.WXInit;
import com.xnradmin.po.wx.connect.WXurl;

@Controller
@Scope("prototype")
@Namespace("/page/wx/wxconnect")
@ParentPackage("json-default")
public class WXConnectAction {

	private static Logger log = Logger.getLogger(WXConnectAction.class);
	private String userId;
	private String userName;
	private String serverId;
	private OAuth oAuth;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getServerId() {
		return serverId;
	}

	public void setServerId(String serverId) {
		this.serverId = serverId;
	}

	@Autowired
	private WeiXinConnectService connectService;

	private static WXGetTokenService wXGetTokenService = new WXGetTokenService();

	@Action(value = "connect")
	public void connect() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		String method = request.getMethod();
		String sVerifyEchoStr = request.getParameter("echostr");
		if (method != null && method.equals("GET") && sVerifyEchoStr != null) {
			WXBizMsgCrypt wxcpt = null;
			try {
				wxcpt = new WXBizMsgCrypt(WXInit.TOKEN, WXInit.ENCODINGAESKEY,
						WXInit.CORPID);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			String sVerifyMsgSig = request.getParameter("msg_signature");
			String sVerifyTimeStamp = request.getParameter("timestamp");
			String sVerifyNonce = request.getParameter("nonce");
			sVerifyEchoStr = request.getParameter("echostr");
			try {
				String sEchoStr = wxcpt.VerifyURL(sVerifyMsgSig,
						sVerifyTimeStamp, sVerifyNonce, sVerifyEchoStr);
				response.getWriter().print(sEchoStr);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (method != null && method.equals("POST") && sVerifyEchoStr == null) {
			WXBizMsgCrypt wxcpt = null;
			try {
				wxcpt = new WXBizMsgCrypt(WXInit.TOKEN, WXInit.ENCODINGAESKEY,
						WXInit.CORPID);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			String sReqMsgSig = request.getParameter("msg_signature");
			String sReqTimeStamp = request.getParameter("timestamp");
			String sReqNonce = request.getParameter("nonce");
			BufferedReader br = new BufferedReader(new InputStreamReader(
					request.getInputStream()));
			StringBuffer sb = new StringBuffer();
			String currentLine = "";
			while ((currentLine = br.readLine()) != null) {
				sb.append(currentLine);
			}
			String sReqData = sb.toString();
			String sMsg = wxcpt.DecryptMsg(sReqMsgSig, sReqTimeStamp,
					sReqNonce, sReqData);
			String messageString = connectService.processRequest(sMsg);
			String sEncryptMsg = "";
			if (messageString != null) {
				sEncryptMsg = wxcpt.EncryptMsg(messageString, sReqTimeStamp,
						sReqNonce);
			}
			response.getWriter().print(sEncryptMsg);
		}
	}

	@Action(value = "oAuth", results = { @Result(name = StrutsResMSG.SUCCESS, location = "/wx/admin/seting/uploadImage/uploadImage.jsp") })
	public String oAuth() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String code = request.getParameter("code");
		String access_tokenString = wXGetTokenService.accessTokenIsOvertime();
		JSONObject userId = WeixinUtil.httpRequest(
				WXurl.WX_USERID_URL.replace("ACCESS_TOKEN", access_tokenString)
						.replace("CODE", code), "GET", null);
		if (userId.toString().indexOf("errcode") == -1) {
			JSONObject userInformation = WeixinUtil.httpRequest(
					WXurl.WX_USERNAME_URL.replace("ACCESS_TOKEN",
							access_tokenString).replace("USERID",
							userId.getString("UserId")), "GET", null);
			String userName = userInformation.getString("name");
			this.userId = userId.getString("UserId");
			this.userName = userName;

		}
		return StrutsResMSG.SUCCESS;
	}

	@Action(value = "createMenu")
	public void createMenu() {
		int type = WeixinUtil.createMenu();
		if (type == 0) {
			log.info("菜单创建成功 ");
		} else {
			log.info("菜单创建失败");
		}
	}

	@Action(value = "uploadF")
	public void uploadF() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String access_tokenString = wXGetTokenService.accessTokenIsOvertime();
		JSONObject jsapi_ticket = WeixinUtil.httpRequest(
				WXurl.WX_GET_JSAPI_TICKET.replace("ACCESS_TOKEN",
						access_tokenString), "GET", null);
		Long timep = Long.valueOf(new Date().getTime());
		String noncestr = "Wm3WZYTPz0wzccnW";
		String s1 = "jsapi_ticket="
				+ jsapi_ticket.getString("ticket")
				+ "&noncestr="
				+ noncestr
				+ "&timestamp="
				+ timep
				+ "&url=http://weixin.robustsoft.cn/xnr/wx/admin/seting/uploadImage/obtainImage.jsp";
		String signature = DigestUtils.shaHex(s1);
		HttpSession session = request.getSession();
		session.setAttribute("timep", timep);
		session.setAttribute("noncestr", noncestr);
		session.setAttribute("signature", signature);
		session.setAttribute("userName", userName);
		session.setAttribute("userId", userId);
	}

	@Action(value = "ceshi")
	public void ceshi() {
		String fileName = ServletActionContext.getServletContext().getRealPath(
				"/farmerImage");
		System.out.println(fileName);
	}

	@Action(value = "downloadF", results = { @Result(name = StrutsResMSG.SUCCESS, location = "/wx/admin/seting/uploadImage/uploadImage.jsp") })
	public String downloadF() {
		String serverIds[] = serverId.split(",");
		String requestUrl = "https://qyapi.weixin.qq.com/cgi-bin/media/get?access_token=ACCESS_TOKEN&media_id=MEDIA_ID";
		String access_token = wXGetTokenService.accessTokenIsOvertime();
		for (int i = 0; i < serverIds.length; i++) {

			requestUrl = requestUrl.replace("ACCESS_TOKEN", access_token)
					.replace("MEDIA_ID", serverIds[i]);
			HttpURLConnection conn = null;
			try {
				URL url = new URL(requestUrl);
				conn = (HttpURLConnection) url.openConnection();
				conn.setDoInput(true);
				conn.setRequestMethod("GET");
				conn.setConnectTimeout(30000);
				conn.setReadTimeout(30000);
				InputStream in = conn.getInputStream();
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				int b;
				while ((b = in.read()) != -1) {
					baos.write(b);
				}
				byte[] bytes = baos.toByteArray();
				BufferedOutputStream bos = null;
				String fileName = ServletActionContext.getServletContext()
						.getRealPath("/farmerImage")
						+ "/"
						+ new Date().getTime() + "_" + userId + ".jpg";
				File file = new File(fileName);
				if (!file.exists()) {
					file.createNewFile();
				}
				bos = new BufferedOutputStream(new FileOutputStream(file));
				bos.write(bytes);
				bos.close();
				baos.close();

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (conn != null) {
					conn.disconnect();
				}
			}
		}
		this.userId = userId;
		this.userName = userName;
		return StrutsResMSG.SUCCESS;
	}
}
