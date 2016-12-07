package com.xnradmin.client.action.wx;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
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

import com.cntinker.util.SHA1;
import com.cntinker.util.wx.connect.SignUtil;
import com.cntinker.util.wx.connect.WXBizMsgCrypt;
import com.xnradmin.client.service.wx.FarmerImageService;
import com.xnradmin.client.service.wx.FarmerService;
import com.xnradmin.client.service.wx.OutPlanService;
import com.xnradmin.client.service.wx.WXFGetTokenService;
import com.xnradmin.client.service.wx.WXFarmerImageService;
import com.xnradmin.client.service.wx.WXGetTokenService;
import com.xnradmin.client.service.wx.WeiXinConnectService;
import com.xnradmin.client.service.wx.WeixinUtil;
import com.xnradmin.constant.StrutsResMSG;
import com.xnradmin.core.service.business.commodity.BusinessGoodsService;
import com.xnradmin.core.service.mall.order.ShoppingCartService;
import com.xnradmin.po.business.BusinessCategory;
import com.xnradmin.po.business.BusinessGoods;
import com.xnradmin.po.wx.connect.FarmerImage;
import com.xnradmin.po.wx.connect.WXInit;
import com.xnradmin.po.wx.connect.WXfInit;
import com.xnradmin.po.wx.connect.WXurl;
import com.xnradmin.vo.business.OutPlanVO;

@Controller
@Scope("prototype")
@Namespace("/page/wx/wxconnect")
@ParentPackage("json-default")
public class WXConnectAction {

	private static Logger log = Logger.getLogger(WXConnectAction.class);
	private String userId ;
	private String userName;
	private String serverId;
	private String type;
	private String outPlanId;
	private OutPlanVO outplanVO;
	private String status;
	private List<BusinessCategory> businesCategorys;
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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
	public String getOutPlanId() {
		return outPlanId;
	}

	public void setOutPlanId(String outPlanId) {
		this.outPlanId = outPlanId;
	}

	public OutPlanVO getOutplanVO() {
		return outplanVO;
	}

	public void setOutplanVO(OutPlanVO outplanVO) {
		this.outplanVO = outplanVO;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Autowired
	private WeiXinConnectService connectService;
	@Autowired
	private OutPlanService outPlanService;
	@Autowired
	private FarmerImageService farmerImageService;
	@Autowired
	private BusinessGoodsService businessGoodsService;
	@Autowired
	private FarmerService farmerService;
	@Autowired
	private WXFarmerImageService wXFarmerImageService;
	@Autowired
	private ShoppingCartService shoppingCartService;
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
	/***
	 * 服务号链接方式
	 * @throws IOException
	 */
	@Action(value="wxConnect")
	public void wxconnect() throws IOException
	{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		String method = request.getMethod();
		if(method != null && method.equals("GET"))
		{
			String signature = request.getParameter("signature");  
	        // 时间戳  
	        String timestamp = request.getParameter("timestamp");
	        // 随机数  
	        String nonce = request.getParameter("nonce");  
	        // 随机字符串  
	        String echostr = request.getParameter("echostr");  
	        PrintWriter out = response.getWriter();  
	        
	        // 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败  
	        if (SignUtil.checkSignature(WXfInit.TOKEN,signature, timestamp, nonce)) {  
	            out.print(echostr);  
	        }  
	        out.close();  
	        out = null;  
		}
		if(method != null && method.equals("POST"))
		{
			request.setCharacterEncoding("UTF-8");  
	        response.setCharacterEncoding("UTF-8");  
	  
	        // 调用核心业务类接收消息、处理消息  
	        String respMessage = connectService.processRequest();  
	        // 响应消息  
	        PrintWriter out = response.getWriter();  
	        out.print(respMessage);  
	        out.close();  
		}
		
	}
	/***
	 * 企业号点击链接跳转到上传页面
	 * @return
	 */
	@Action(value = "oAuth", results = { @Result(name = StrutsResMSG.SUCCESS, location = "/wx/admin/seting/uploadImage/uploadImage.jsp") })
	public String oAuth(){
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
			this.status = farmerService.getStatus(userId.getString("UserId"));
			this.userName = userName;

		}
		ServletActionContext.getRequest().getSession().setAttribute("userId", this.userId);
		return StrutsResMSG.SUCCESS;
	}
	/***
	 * 服务号点击链接跳转到上传页面
	 * @return
	 */
	@Action(value = "oAuthF", results = { @Result(name = StrutsResMSG.SUCCESS, location = "/wx/admin/seting/uploadImage/uploadImageF.jsp") })
	public String oAuthF(){
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		String code = request.getParameter("code");
		String access_tokenString = WXFGetTokenService.accessTokenIsOvertime();
		JSONObject userIdObject = WeixinUtil.httpRequest(
				WXurl.WXF_USERID_URL.replace("APPID", WXfInit.APPID).replace("SECRET", WXfInit.APPSECRET)
						.replace("CODE", code), "GET", null);
		if (userIdObject.toString().indexOf("errcode") == -1) {
			String userId = userIdObject.getString("openid");
			JSONObject userInformation = WeixinUtil.httpRequest(
					WXurl.WXF_USERNAME_URL.replace("ACCESS_TOKEN",
							access_tokenString).replace("OPENID",
									userId), "GET", null);
			if (userInformation.toString().indexOf("40001")!=-1){
				access_tokenString =WXFGetTokenService.getAccessToken();
				userInformation = WeixinUtil.httpRequest(
						WXurl.WXF_USERNAME_URL.replace("ACCESS_TOKEN",
								access_tokenString).replace("OPENID",
										userId), "GET", null);
			}
			String userName = userInformation.getString("nickname");
			this.status = farmerService.getStatus(userId);
//			this.userId = userId;
			session.setAttribute("userId", userId);
			this.userName = userName;
		}
		return StrutsResMSG.SUCCESS;
	}
	
	/***
	 * 企业号创建菜单
	 */
	@Action(value = "createMenu")
	public void createMenu() {
		int type = WeixinUtil.createMenu();
		if (type == 0) {
			log.info("菜单创建成功 ");
		} else {
			log.info("菜单创建失败");
		}
	}
	/***
	 * 服务号创建菜单
	 */
	@Action(value="createFMenu")
	public void createFMenu()
	{
		int type = WeixinUtil.createFMenu();
		if (type == 0) {
			log.info("菜单创建成功 ");
		} else {
			log.info("菜单创建失败");
		}
	}

	@Action(value = "uploadFF")
	public void uploadFF() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String access_tokenString = WXFGetTokenService.accessTokenIsOvertime();
		JSONObject jsapi_ticket = WeixinUtil.httpRequest(
				WXurl.WXF_GET_JSAPI_TICKET.replace("ACCESS_TOKEN",
						access_tokenString), "GET", null);
		Long time = Long.valueOf( new Date().getTime());
		String timep = time.toString().substring(0, 10);
		String noncestr = "Wm3WZYTPz0wzccnW";
		String s1 = "jsapi_ticket="
				+ jsapi_ticket.getString("ticket")
				+ "&noncestr="
				+ noncestr
				+ "&timestamp="
				+ timep
				+ "&url="+WXfInit.SERVICEURLW+"/xnr/wx/admin/seting/uploadImage/obtainImageF.jsp";
		String signature = new SHA1().getDigestOfStringX(s1.getBytes());
		String types = farmerService.getFenleiByUserId(userId);
		HttpSession session = request.getSession();
		if(types!=null)
		{
			List<BusinessGoods> typeNames = businessGoodsService.getTypeNameById(types);
			session.setAttribute("typeNames", typeNames);
		}
		session.setAttribute("timep", timep);
		session.setAttribute("noncestr", noncestr);
		session.setAttribute("signature", signature);
		session.setAttribute("userName", userName);
//		session.setAttribute("userId", userId);
		session.setAttribute("appId", WXfInit.APPID);
		session.setAttribute("skiptUrl", WXurl.WX_CLICK_URL.replace("APPID", WXfInit.APPID).replace("REDIRECT_URI",WXfInit.SERVICEURL+"%2fxnr%2fpage%2fwx%2fpersonalCenter%2flistF.action").replace("SCOPE", "snsapi_base"));
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
				+ "&url="+WXfInit.SERVICEURLW+"/xnr/wx/admin/seting/uploadImage/obtainImage.jsp";
		String signature = DigestUtils.shaHex(s1);
//		businesCategorys = outPlanService.getBusinessCategoryS();
		String types = farmerService.getFenleiByUserId(userId);
		HttpSession session = request.getSession();
		session.setAttribute("timep", timep);
		session.setAttribute("noncestr", noncestr);
		session.setAttribute("signature", signature);
		session.setAttribute("userName", userName);
		session.setAttribute("userId", userId);
		session.setAttribute("appId", WXInit.CORPID);
		if(type!=null)
		{
			List<BusinessGoods> typeNames = businessGoodsService.getTypeNameById(types);
			session.setAttribute("typeNames", typeNames);
		}
	}

	@Action(value = "ceshi",results = { @Result(name = StrutsResMSG.SUCCESS, location = "/wx/admin/seting/personalCenter/personalCenter.jsp") })
	public String ceshi() {
//		List<Map<String, List<Map<String, List<String>>>>> date_type_images = new ArrayList<Map<String,List<Map<String,List<String>>>>>();
//		List<String> imagedates = farmerImageService.getImageDates("owt3dwebfPG2E19lodY9oqIwZ0wk");
//		for (String images : imagedates) {
//			Map<String, List<Map<String, List<String>>>> date_type_image = new HashMap<String, List<Map<String, List<String>>>>();
//			Map<String, List<String>> type_images = new HashMap<String, List<String>>();
//			List<Map<String, List<String>>> type_imagesList= new ArrayList<Map<String,List<String>>>();
//			List<String> typeList = farmerImageService.findByType(images,"owt3dwebfPG2E19lodY9oqIwZ0wk");
//			for (String type : typeList) {
//			    String	typeName = businessGoodsService.findByid(type).getGoodsName();
//				List<String> imageList = farmerImageService.findByImages(type,images,"owt3dwebfPG2E19lodY9oqIwZ0wk");
//				type_images.put(typeName, imageList);
//			}
//			type_imagesList.add(type_images);
//			date_type_image.put(images, type_imagesList);
//			date_type_images.add(date_type_image);
//		}
//		ServletActionContext.getRequest().setAttribute("date_type_images", date_type_images);
//		List<OutPlanVO> outplans = outPlanService.getListByUserId("dingjinghui",0,0);
		return StrutsResMSG.SUCCESS;
	}

	/***
	 * 服务号下载
	 */
	@ResponseBody
	@Action(value = "downloadFF")
	public void downloadFF() {
		String serverIds[] = serverId.split(",");
		String access_token = WXFGetTokenService.accessTokenIsOvertime();
		for (int i = 0; i < serverIds.length; i++) {
			String requestUrl = WXurl.WXF_DOWN_IMAGE.replace("ACCESS_TOKEN", access_token)
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
				String typeName = businessGoodsService.findByid(type).getGoodsName();
				String imageUrl = userId+File.separator+typeName;
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
//		this.userId = userId;
		this.userName = userName;
	}
	@ResponseBody
	@Action(value = "downloadF")
	public void downloadF() {
		String serverIds[] = serverId.split(",");
		String access_token = WXGetTokenService.accessTokenIsOvertime();
		for (int i = 0; i < serverIds.length; i++) {
			String requestUrl = WXurl.WX_DOWN_IMAGE.replace("ACCESS_TOKEN", access_token)
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
				String typeName = businessGoodsService.findByid(type).getGoodsName();
				String imageUrl = userId+File.separator+typeName;
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
