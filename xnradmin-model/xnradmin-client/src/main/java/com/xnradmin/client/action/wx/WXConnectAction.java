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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.ResponseBody;

import com.cntinker.util.wx.connect.OAuth;
import com.cntinker.util.wx.connect.WXBizMsgCrypt;
import com.cntinker.util.wx.connect.WeixinUtil;
import com.xnradmin.client.service.wx.FarmerImageService;
import com.xnradmin.client.service.wx.WXGetTokenService;
import com.xnradmin.client.service.wx.WeiXinConnectService;
import com.xnradmin.constant.StrutsResMSG;
import com.xnradmin.po.wx.connect.FarmerImage;
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
	private String type;
	

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

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
	
	@Autowired
	private FarmerImageService farmerImageService;

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
		String access_tokenString = WXGetTokenService.accessTokenIsOvertime();
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
		String access_tokenString = WXGetTokenService.accessTokenIsOvertime();
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

	@Action(value = "ceshi",results = { @Result(name = StrutsResMSG.SUCCESS, location = "/wx/admin/seting/uploadImage/uploadImage1.jsp") })
	public String ceshi() {
		List<Map<String, List<Map<String, List<String>>>>> date_type_images = new ArrayList<Map<String,List<Map<String,List<String>>>>>();
		List<String> imagedates = farmerImageService.getImageDates("dingjinghui");
		for (String images : imagedates) {
			Map<String, List<Map<String, List<String>>>> date_type_image = new HashMap<String, List<Map<String, List<String>>>>();
			Map<String, List<String>> type_images = new HashMap<String, List<String>>();
			List<Map<String, List<String>>> type_imagesList= new ArrayList<Map<String,List<String>>>();
//			Set<String> types = new LinkedHashSet<String>();//日期对应的类型
			List<String> typeList = farmerImageService.findByType(images);
//			for (String typel : typeList) {
//				types.add(typel);
//			}
			for (String type : typeList) {
				List<String> imageList = farmerImageService.findByImages(type,images);
				type_images.put(type, imageList);
			}
			type_imagesList.add(type_images);
			date_type_image.put(images, type_imagesList);
			date_type_images.add(date_type_image);
		}
		return StrutsResMSG.SUCCESS;
	}

	@ResponseBody
	@Action(value = "downloadF")
	public void downloadF() {
		String serverIds[] = serverId.split(",");
		String Url = "https://qyapi.weixin.qq.com/cgi-bin/media/get?access_token=ACCESS_TOKEN&media_id=MEDIA_ID";
		String access_token = WXGetTokenService.accessTokenIsOvertime();
		for (int i = 0; i < serverIds.length; i++) {
			String requestUrl = Url.replace("ACCESS_TOKEN", access_token)
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
				String imageUrl = userId+File.separator+type;
				String filePath = ServletActionContext.getServletContext()
						.getRealPath("/farmerImage");
				String imageName = new Date().getTime() + "_" + userId + ".jpg";
				String fileName = filePath+File.separator+imageUrl+File.separator+imageName;
				File file = new File(filePath+File.separator+imageUrl);
				if (!file.exists()) {
					file.mkdirs();
				}
				File imageFile = new File(fileName);
				imageFile.createNewFile();
				bos = new BufferedOutputStream(new FileOutputStream(imageFile));
				bos.write(bytes);
				bos.close();
				baos.close();
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
				FarmerImage farmerImage  = new FarmerImage();
				farmerImage.setUrl("/farmerImage"+File.separator+imageUrl+File.separator+imageName);
				farmerImage.setUserName(userName);
				farmerImage.setUserId(userId);
				farmerImage.setType(type);
				farmerImage.setDate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
				farmerImageService.saveFarmerImage(farmerImage);
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
	}
}