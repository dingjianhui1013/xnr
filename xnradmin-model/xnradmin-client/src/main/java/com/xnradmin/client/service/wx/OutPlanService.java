package com.xnradmin.client.service.wx;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cntinker.util.wx.connect.Text;
import com.cntinker.util.wx.connect.TextMessage;
import com.xnradmin.core.dao.CommonDAO;
import com.xnradmin.po.business.BusinessCategory;
import com.xnradmin.po.business.BusinessGoods;
import com.xnradmin.po.business.BusinessWeight;
import com.xnradmin.po.wx.OutPlan;
import com.xnradmin.po.wx.connect.Farmer;
import com.xnradmin.po.wx.connect.WXInit;
import com.xnradmin.vo.business.OutPlanVO;

@Service("OutPlanService")
public class OutPlanService {
	
	@Autowired
	private CommonDAO commonDao;
	
	public List<OutPlan> findAll(String userId){
		try {
			String hql = " from OutPlan where delFlage=0 and userId='"+userId+"' order by createDate desc";
			List<OutPlan> list =  commonDao.getEntitiesByPropertiesWithHql(hql,0,0);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void save(OutPlan outplan){
		try{
		outplan.setDelFlage(0);
		outplan.setCreateDate(new Date());
		commonDao.save(outplan);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public void delete(String id){
		try {
			String hql = "delete from OutPlan where id="+id;
			commonDao.executeUpdateOrDelete(hql);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public OutPlan findById(String id){
		String hql = "from OutPlan where id="+id;
		List<OutPlan> list = commonDao.getEntitiesByPropertiesWithHql(hql,0,0);
		return list.get(0);
	}
	public void saveEdit(OutPlan outPlan){
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String hql = "update OutPlan set goodsId='"+outPlan.getGoodsId()+"',"
				+ " startTime='"+simpleDateFormat.format(outPlan.getStartTime())+"',"
						+ " endTime='"+simpleDateFormat.format(outPlan.getEndTime())+"',"
								+ " output='"+outPlan.getOutput()+"',"
										+ " unitId='"+outPlan.getUnitId()+"'"
											+" examine=0"
												+ " where id="+outPlan.getId();
		commonDao.executeUpdateOrDelete(hql);
	}
	
	public List<OutPlanVO> getList(OutPlanVO query,int pageNo,int pageSize){
		
		String hql = getHql(query);
		List list= commonDao.getEntitiesByPropertiesWithHql(hql, pageNo,pageSize);
		List<OutPlanVO> resList = new LinkedList<OutPlanVO>();
		for (int i = 0; i < list.size(); i++) {
			Object[] obj = (Object[]) list.get(i);
			OutPlan outPlan = (OutPlan) obj[0];
			Farmer farmer = (Farmer) obj[1];
			BusinessGoods businessGood = (BusinessGoods) obj[2];
			BusinessWeight businessWeight = (BusinessWeight) obj[3];
			BusinessCategory businessCategory = (BusinessCategory) obj[4];
			
			OutPlanVO outPlanVO = new OutPlanVO();
			outPlanVO.setOutPlan(outPlan);
			outPlanVO.setFarmer(farmer);
			outPlanVO.setBusinessGood(businessGood);
			outPlanVO.setBusinessWeight(businessWeight);
			outPlanVO.setBusinessCategory(businessCategory);
			resList.add(outPlanVO);
		}
		return resList;
	}
public List<OutPlanVO> getListByUserId(String userId,int pageNo,int pageSize){
		
		String hql = getHqlByUserId(userId);
		List list= commonDao.getEntitiesByPropertiesWithHql(hql, pageNo,pageSize);
		List<OutPlanVO> resList = new LinkedList<OutPlanVO>();
		for (int i = 0; i < list.size(); i++) {
			Object[] obj = (Object[]) list.get(i);
			OutPlan outPlan = (OutPlan) obj[0];
			Farmer farmer = (Farmer) obj[1];
			BusinessGoods businessGood = (BusinessGoods) obj[2];
			BusinessWeight businessWeight = (BusinessWeight) obj[3];
			BusinessCategory businessCategory = (BusinessCategory) obj[4];
			
			OutPlanVO outPlanVO = new OutPlanVO();
			outPlanVO.setOutPlan(outPlan);
			outPlanVO.setFarmer(farmer);
			outPlanVO.setBusinessGood(businessGood);
			outPlanVO.setBusinessWeight(businessWeight);
			outPlanVO.setBusinessCategory(businessCategory);
			resList.add(outPlanVO);
		}
		return resList;
	}
	public OutPlanVO getById(String id){
		
		String hql = "from OutPlan a,Farmer b,BusinessGoods c,BusinessWeight d,BusinessCategory e  where a.delFlage=0 and a.userId=b.userId"
				+ " and a.goodsId=c.id and a.unitId=d.id and a.businesCategoryId=e.id and a.id="+id;
		List list= commonDao.getEntitiesByPropertiesWithHql(hql, 0,0);
		Object[] obj = (Object[]) list.get(0);
		OutPlan outPlan = (OutPlan) obj[0];
		Farmer farmer = (Farmer) obj[1];
		BusinessGoods businessGood = (BusinessGoods) obj[2];
		BusinessWeight businessWeight = (BusinessWeight) obj[3];
		BusinessCategory businessCategory = (BusinessCategory) obj[4];
		
		OutPlanVO outPlanVO = new OutPlanVO();
		outPlanVO.setOutPlan(outPlan);
		outPlanVO.setFarmer(farmer);
		outPlanVO.setBusinessGood(businessGood);
		outPlanVO.setBusinessWeight(businessWeight);
		outPlanVO.setBusinessCategory(businessCategory);
		return outPlanVO;
	}
	
	private String getHql(OutPlanVO query) {
		StringBuffer hql = new StringBuffer();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		hql.append("from OutPlan a,Farmer b,BusinessGoods c,BusinessWeight d,BusinessCategory e  where a.delFlage=0 and a.userId=b.userId"
				+ " and a.goodsId=c.id and a.unitId=d.id and a.businesCategoryId=e.id");
		if (query == null){
			return hql.append(" order by a.id desc").toString();
		}
		if(query.getOutPlan().getId()!=null){
			hql.append(" and a.id like '%").append(query.getOutPlan().getId()).append("%'");
		}
		if(query.getFarmer().getUserName()!=null&&!query.getFarmer().getUserName().equals("")){
			hql.append(" and b.userName like '%").append(query.getFarmer().getUserName()).append("%'");
		}
		if(query.getBusinessGood().getGoodsName()!=null&&!query.getBusinessGood().getGoodsName().equals("")){
			hql.append(" and c.goodsName like '%").append(query.getBusinessGood().getGoodsName()).append("%'");
		}
		if(query.getOutPlan().getStartTime()!=null){
			String format = simpleDateFormat.format(query.getOutPlan().getStartTime());
			hql.append(" and a.startTime >= '").append(format).append("'");
		}
		if(query.getOutPlan().getEndTime()!=null){
			String format = simpleDateFormat.format(query.getOutPlan().getEndTime());
			hql.append(" and a.endTime <= '").append(format).append("'");
		}
		if(query.getOutPlan().getExamine()!=null){
			hql.append(" and a.examine = '").append(query.getOutPlan().getExamine()).append("'");
		}
		hql.append(" order by a.id desc");
		return hql.toString();
	}
	private String getHqlByUserId(String userId) {
		StringBuffer hql = new StringBuffer();
		hql.append("from OutPlan a,Farmer b,BusinessGoods c,BusinessWeight d,BusinessCategory e  where a.delFlage=0 and a.userId=b.userId"
				+ " and a.goodsId=c.id and a.unitId=d.id and a.businesCategoryId=e.id");
		if(StringUtils.isNotEmpty(userId))
		{
			hql.append(" and a.userId='"+userId+"'");
		}
		hql.append(" order by a.createDate desc");
		return hql.toString();
	}
	
	public int getCount(OutPlanVO query) {
		String hql = "select count(*) " + getHql(query);
		return commonDao.getNumberOfEntitiesWithHql(hql);
	}

	public List<BusinessCategory> getBusinessCategoryS() {
		String hql = "from BusinessCategory";
		List<BusinessCategory> businessCategorys =  commonDao.getEntitiesByPropertiesWithHql(hql,0,0);
		return businessCategorys;
	}

	public List<BusinessGoods> getGoodsList(String businesCategoryId) {
		String hql = "from BusinessGoods where goodsCategoryId = '" +businesCategoryId+"'";
		List<BusinessGoods> goodList =  commonDao.getEntitiesByPropertiesWithHql(hql,0,0);
		return goodList;
	}
	public boolean examine(String id){
		boolean isok = false;
		if(id!=null&&!"".equals(id)){
			String hql = "update OutPlan set examine=1,remarks=null where id="+id;
			try {
				commonDao.executeUpdateOrDelete(hql);
				isok = true;
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		return isok;
	}
	public boolean examineNo(String id,String remarks){
		boolean isok = false;
		if(id!=null&&!"".equals(id)){
			String hql = "update OutPlan set examine=2 ,remarks='"+remarks+"' where id="+id;
			try {
				commonDao.executeUpdateOrDelete(hql);
				isok = true;
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		return isok;
	}
	public BusinessWeight getWeight(String weightId) {
		String hql = "from BusinessWeight where id="+weightId;
		List<BusinessWeight> businessWeight =commonDao.getEntitiesByPropertiesWithHql(hql,0,0);
		return businessWeight.get(0);
	}
	
	public void examineRelease(String outPlanId)
	{
		OutPlanVO outPlanVO = this.getById(outPlanId);
		String message = "";
		if(outPlanVO.getOutPlan().getExamine()==1)
		{
			message="您提交的生产计划\n"
					+ "商品类型："+outPlanVO.getBusinessGood().getGoodsName()+"\n"
					+ "预计产出时间："+new SimpleDateFormat("yyyy-MM-dd").format(outPlanVO.getOutPlan().getStartTime())+"至"
							+ new SimpleDateFormat("yyyy-MM-dd").format(outPlanVO.getOutPlan().getEndTime())+"\n"
					+ " 产量为："+outPlanVO.getOutPlan().getOutput()+outPlanVO.getBusinessWeight().getWeightName()
					+ "\n已经通过审核";
		}
		if(outPlanVO.getOutPlan().getExamine()==2)
		{
			message="您提交的生产计划被拒绝，拒绝原因为："+outPlanVO.getOutPlan().getRemarks();
		}
		String access_token = WXGetTokenService.accessTokenIsOvertime();
	    Text text = new Text();
	    text.setContent(message);
	    TextMessage textMessage = new TextMessage();
	    textMessage.setTouser(outPlanVO.getOutPlan().getUserId());
	    textMessage.setMsgtype("text");
	    textMessage.setAgentid(WXInit.AGENT_ID);
	    textMessage.setText(text);
	    textMessage.setSafe(0);
	    String outputStr = JSONObject.fromObject(textMessage).toString();
	    WeixinUtil.httpRequest("https://qyapi.weixin.qq.com/cgi-bin/message/send?access_token=" + access_token, "POST", outputStr);
	}
}
