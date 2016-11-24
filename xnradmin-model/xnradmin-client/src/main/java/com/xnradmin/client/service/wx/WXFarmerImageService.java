package com.xnradmin.client.service.wx;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xnradmin.core.dao.CommonDAO;
import com.xnradmin.core.service.business.commodity.BusinessGoodsService;
import com.xnradmin.po.business.BusinessGoods;
import com.xnradmin.po.wx.connect.FarmerImage;
import com.xnradmin.po.wx.connect.FarmerImageBak;

@Service("wXFarmerImageService")
@Transactional
public class WXFarmerImageService { 
	@Autowired
	private BusinessGoodsService businessGoodsService;
	@Autowired
	private FarmerService farmerService;
	@Autowired
	private FarmerImageService farmerImageService;
	@Autowired
	private CommonDAO common;
	public void create(String userId,String picUrl) throws Exception, DocumentException
	{
		HttpServletRequest request = ServletActionContext.getRequest();
		String fileName=request.getSession().getServletContext().getRealPath("/WEB-INF/classes/wx/FarmerImages.xml");
		SAXReader reader = new SAXReader();
	    Document doc = reader.read(new FileInputStream(fileName));
	    Element root = doc.getRootElement();
	    List<Element> list = root.selectNodes("user[@name='"+userId+"']");
	    if(!list.isEmpty())
	    {
	    	Element e = list.get(0);
		    Element pir = e.addElement("picurl");
		    pir.addAttribute("name", picUrl);
		    pir.addElement("type").addAttribute("name", "");
	    }else
	    {
	    	Element e = root.addElement("user").addAttribute("name", userId);
	    	Element pir = e.addElement("picurl");
		    pir.addAttribute("name", picUrl);
		    pir.addElement("type").addAttribute("name", "");
	    }
	    XMLWriter writer = new XMLWriter(new FileWriter(fileName));
	    writer.write(doc);
	    writer.close();
	}
	public void save(String userId,String picUrl)
	{
		FarmerImageBak farmerImageBak = new FarmerImageBak();
		farmerImageBak.setPicUrl(picUrl);
		farmerImageBak.setUserId(userId);
		farmerImageBak.setCreateDate(new Date());
		common.save(farmerImageBak);
	}
	public Map<String, Integer> findImageByUserId(String userId,String type)
	{
		String index="0";
		Map<String, Integer> index_count = new HashMap<String, Integer>();
		int count = 0;
		String hql = "from FarmerImageBak where userId = '"+userId+"' and typeName is null order by createDate asc";
		List<FarmerImageBak> list = (List)common.getEntityByPropertiesWithHql(hql);
		BusinessGoods businessgoods = businessGoodsService.findByid(type);
		if(businessgoods!=null)
		{
			String typeName = businessGoodsService.findByid(type).getGoodsName();
			if(!list.isEmpty())
			{
				FarmerImageBak farmerImageBak =list.get(0);
				hql = "update FarmerImageBak set typeName = '"+typeName+"' where id ="+ farmerImageBak.getId();
				common.executeUpdateOrDelete(hql);
			}else
			{
				index="2";
			}
		}else
		{
			index="1";
		}
		count=list.size();
		count--;
		index_count.put(index, count);
		return index_count;
	}
	public static void main(String[] args) {
		new WXFarmerImageService().findImageByUserId("owt3dwds69_EA04QBLrTvTjNgmdI", "7589");
	}
	public Map read(String userId,String type)
	{
		String index="0";
		Map<String, Integer> index_count = new HashMap<String, Integer>();
		int count = 0;
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			String fileName=request.getSession().getServletContext().getRealPath("/WEB-INF/classes/wx/FarmerImages.xml");
			SAXReader reader = new SAXReader();
			Document doc = reader.read(new FileInputStream(fileName));
			Element root = doc.getRootElement();
			List<Element> list = root.selectNodes("user[@name='"+userId+"']");
			List<Element> picurls = list.get(0).selectNodes("picurl");
			String typeName = businessGoodsService.findByid(type).getGoodsName();
			for (Element element : picurls) {
				String picurl = element.attribute("name").getValue();
				List<Element> types = element.selectNodes("type");
				for (Element element2 : types) {
					String typevalue = element2.attribute("name").getValue();
					if(typevalue==null||"".equals(typevalue))
					{
						count++;
					}
				}
			}
			if(type!=null)
			{
				a:for (Element element : picurls) {
					index ="2";
					String picurl = element.attribute("name").getValue();
					List<Element> types = element.selectNodes("type");
					b:for (Element element2 : types) {
						String typevalue = element2.attribute("name").getValue();
						if(typevalue==null||"".equals(typevalue))
						{
							index = "0";
							element2.addAttribute("name", type);
							uploadImage(picurl, userId, type);
							count--;
							break a;
						}
					}
				}
				XMLWriter writer = new XMLWriter(new FileWriter(fileName));
				writer.write(doc);
				writer.close();
			}else
			{
				index="1";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			index = "1";
		} 
		index_count.put(index, count);
	    return index_count;
	}
	public void uploadImage(String picurl,String userId,String type)
	{
		String requestUrl = picurl;
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
			String userName = farmerService.getUserNameById(userId).getUserName();
			FarmerImage farmerImage =  new FarmerImage();
			farmerImage.setDate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
			farmerImage.setType(type);
			farmerImage.setUserId(userId);
			farmerImage.setUrl("/farmerImage"+File.separator+imageUrl+File.separator+imageName);
			farmerImage.setUserName(userName);
			farmerImageService.saveFarmerImage(farmerImage);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}  
