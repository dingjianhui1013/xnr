package com.xnradmin.client.service.wx;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cntinker.util.StringHelper;
import com.cntinker.util.Qrcode.MatrixToImageWriter;
import com.cntinker.util.wx.connect.Text;
import com.cntinker.util.wx.connect.TextMessage;
import com.cntinker.util.wx.connect.TextMessageF;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.xnradmin.client.action.wx.WXConnectAction;
import com.xnradmin.core.dao.CommonDAO;
import com.xnradmin.core.dao.wx.FarmerDao;
import com.xnradmin.po.wx.connect.Farmer;
import com.xnradmin.po.wx.connect.FarmerExamine;
import com.xnradmin.po.wx.connect.FarmerQrCode;
import com.xnradmin.po.wx.connect.WXInit;
import com.xnradmin.po.wx.connect.WXfInit;
import com.xnradmin.po.wx.connect.WXurl;
import com.xnradmin.vo.business.OutPlanVO;

@Service("farmerService")
@Transactional
public class FarmerService {
	private static Logger log = Logger.getLogger(WXConnectAction.class);
	@Autowired
	private FarmerDao farmerDao;
	@Autowired
	private CommonDAO commonDao;
	
	public void saveFarmer(Farmer farmer)
	{
		farmerDao.save(farmer);
	}
	public void delFarmer(Farmer farmer)
	{
		farmerDao.delete(farmer);;
	}
	public List<Farmer> getList(Farmer query,int pageNo,int pageSize){
		
		String hql = getHql(query);
 		List<Farmer> farmers= commonDao.getEntitiesByPropertiesWithHql(hql, pageNo,pageSize);
		return farmers;
	}
	
	private String getHql(Farmer query) {
		StringBuffer hql = new StringBuffer();
		hql.append("from Farmer");
		if (query == null){
			return hql.append(" order by id desc").toString();
		}
		int isAnd = 0;
		if (!StringHelper.isNull(query.getUserName())
				|| !StringHelper.isNull(query.getUserId())) {
			hql.append(" where ");
		}
		if (!StringHelper.isNull(query.getUserName())) {
			if (isAnd > 0)
				hql.append(" and ");
			hql.append(" userName like '%").append(query.getUserName())
					.append("%'");
		}
		if (!StringHelper.isNull(query.getUserId())) {
			if (isAnd > 0)
				hql.append(" and ");
			hql.append(" userId like '%").append(query.getUserId())
					.append("%'");
		}
		hql.append(" order by id desc").toString();
		return hql.toString();
	}

	public int getCount(Farmer query) {
		String hql = "select count(*) " + getHql(query);
		return commonDao.getNumberOfEntitiesWithHql(hql);
	}
	public String[] getFenleiById(String farmerId){
		String hql = "select types from Farmer where id="+farmerId;
		List types = (List)commonDao.getEntityByPropertiesWithHql(hql);
		String s = (String)types.get(0)==null?",":(String)types.get(0);
		String[] split = s.split(",");
		return split;
	}
	public String getFenleiByUserId(String userId){
		String hql = "select types from Farmer where userId='"+userId+"'";
		List types = (List)commonDao.getEntityByPropertiesWithHql(hql);
		String s=null;
		if(!types.isEmpty())
		{
			s = types==null?"":(String)types.get(0);
		}
		return s;
}
	public String[] getFenleisByUserId(String userId){
		String hql = "select types from Farmer where userId='"+userId+"'";
		List types = (List)commonDao.getEntityByPropertiesWithHql(hql);
		String s = (String)types.get(0)==null?",":(String)types.get(0);
		String[] split = s.split(",");
		return split;
}
	
	/**
	 * 更新农户对应的商品
	 * @param id   农户id
	 * @param types  商品类型
	 */
	public void saveTypes(String id,String types){
		String hql ="";
		if(types==null){
			hql ="update Farmer set types=null where id="+id;
		}else{
			types = types.replace(" ", ""); 
			hql ="update Farmer set types='"+types+"' where id="+id;
		}
		commonDao.executeUpdateOrDelete(hql);
	}
	public Farmer getUserNameById(String userId)
	{
		String hql = "from Farmer where userId='"+userId+"'";
		List<Farmer> user = (List)commonDao.getEntityByPropertiesWithHql(hql);
		if(!user.isEmpty())
		{
			return user.get(0);
		}else
		{
			return null;
		}
	}
	/***
	 * 生成二维码
	 * @param farmerId 农户id
	 * @param goodsId  菜品id
	 * @param imageUrl 图片保存路径
	 */
	public void generateCode(String farmerId,String goodsId,String imageUrl,String skipUrl) {
		try {

			String path = ServletActionContext.getServletContext().getRealPath("/farmerQrCodeImage")+File.separator+farmerId+File.separator+goodsId;
			String imageName = imageUrl;
			File dir = new File(path);
			if (!dir.exists()) {
				dir.mkdirs();
			}
			MultiFormatWriter multiFormatWriter = new MultiFormatWriter();

			Map hints = new HashMap();
			hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
			BitMatrix bitMatrix = multiFormatWriter.encode(skipUrl,
					BarcodeFormat.QR_CODE, 100, 100, hints);
			File file1 = new File(dir, imageName);

			MatrixToImageWriter.writeToFile(bitMatrix, "png", file1);
			log.debug("二维码生成成功!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void UpdateCode(FarmerQrCode farmerQrCode) {
		try {
			String path = ServletActionContext.getServletContext().getRealPath("/farmerQrCodeImage")+File.separator+farmerQrCode.getFarmerId()+File.separator+farmerQrCode.getGoodsId();
			String imageName = farmerQrCode.getQrCodeUrl();
			File dir = new File(path);
			if (!dir.exists()) {
				dir.mkdirs();
			}
			MultiFormatWriter multiFormatWriter = new MultiFormatWriter();

			Map hints = new HashMap();
			hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
			BitMatrix bitMatrix = multiFormatWriter.encode(farmerQrCode.getSkipUrl(),
					BarcodeFormat.QR_CODE, 100, 100, hints);
			File file1 = new File(dir, imageName);

			MatrixToImageWriter.writeToFile(bitMatrix, "png", file1);
			log.debug("二维码生成成功!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getStatus(String userId) {
		String hql = "from Farmer where userId='"+userId+"'";
		List<Farmer> list= (List<Farmer>)commonDao.getEntityByPropertiesWithHql(hql);
		String status = null;
		if(!list.isEmpty())
		{
			Farmer farmer = list.get(0);
			status = farmer.getStatus();
		}
		return status;
	}

	public void examineUser(String farmerId, String status) {
		String hql = "update Farmer set status = '"+status+"' where userId='"+farmerId+"'";
		commonDao.executeUpdateOrDelete(hql);
	}
	public void examineUser(String farmerId, String status,String remarks,Integer personId) {
		String hql = "update Farmer set status = '"+status+"',remarks = '"+remarks+"',examineStaff= '"+personId+"' where userId='"+farmerId+"'";
		commonDao.executeUpdateOrDelete(hql);
	}

	public List<FarmerExamine> findExamineByUserId(String userId) {
		String hql = "from FarmerExamine where farmerId = '"+userId+"'";
		List<FarmerExamine> list = (List)commonDao.getEntityByPropertiesWithHql(hql);
		return list;
	}
	public void examineRelease(String farmerId,String status,String remarks)
	{
		String message = "";
		if(status.equals("1"))
		{
			message = "你的账户已经通过审核。";
		}else if(status.equals("2"))
		{
			message = "你的账户未通过审核。未通过原因："+remarks+"<a href=\""+WXfInit.SERVICEURLW+"/xnr/page/wx/farmer/farmerExamineEdit.action?farmerId="+farmerId+"\">请前往页面进行审核信息。</a>";
			
		}else
		{
			message = "您的信息已经提交，请耐心等待审核！";
		}
		
		String access_tokenF = WXFGetTokenService.accessTokenIsOvertime();
		/*
		 * 此处为企业号发送消息文本。
		 	String access_token = WXGetTokenService.accessTokenIsOvertime();
		    Text text = new Text();
		    text.setContent(message);
		    TextMessage textMessage = new TextMessage();
		    textMessage.setTouser(farmerId);
		    textMessage.setMsgtype("text");
		   //如果是服务号此处注释掉
		    textMessage.setAgentid(WXInit.AGENT_ID);
		    textMessage.setText(text);
		    textMessage.setSafe(0);
		    String outputStr = JSONObject.fromObject(textMessage).toString();
		   JSONObject json =  WeixinUtil.httpRequest(WXurl.WX_MESSARW_TO_FROMUSER.replace("ACCESS_TOKEN", access_token), "POST", outputStr);
		 */
		/*
		 * 此处为服务好发送文本。
		 */
		Text text = new Text();
		text.setContent(message);
		TextMessageF textMessageF =new TextMessageF();
		textMessageF.setTouser(farmerId);
		textMessageF.setMsgtype("text");
		textMessageF.setText(text);
		String outputStr = JSONObject.fromObject(textMessageF).toString();
		JSONObject jsons =  WeixinUtil.httpRequest(WXurl.WXF_MESSARW_TO_USER.replace("ACCESS_TOKEN", access_tokenF), "POST", outputStr);
		if(jsons.toString().indexOf("40001")!=-1)
		{
		  access_tokenF = WXFGetTokenService.getAccessToken();
		  WXFGetTokenService.writeAccessToken(access_tokenF, new Date().getTime(), ServletActionContext.getRequest());
		  jsons =  WeixinUtil.httpRequest(WXurl.WXF_MESSARW_TO_USER.replace("ACCESS_TOKEN", access_tokenF), "POST", outputStr);
		}
	}

	public void updateFarmerExamine(FarmerExamine farmerExamine) {
		StringBuffer  hql = new StringBuffer("update FarmerExamine set ");
		if(farmerExamine.getFarmerName()!=null)
		{
			hql.append(" farmerName = '"+farmerExamine.getFarmerName()+"'");
		}
		if(farmerExamine.getFarmerTel()!=null)
		{
			hql.append(", farmerTel = '"+farmerExamine.getFarmerTel()+"'");
		}
		if(farmerExamine.getContractNumber()!=null)
		{
			hql.append(", contractNumber = '"+farmerExamine.getContractNumber()+"'");
		}
		hql.append(" where id="+farmerExamine.getId());
		commonDao.executeUpdateOrDelete(hql.toString());
	}
	
}
