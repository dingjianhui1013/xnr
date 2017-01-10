package com.xnradmin.client.service.wx;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cntinker.util.StringHelper;
import com.cntinker.util.wx.connect.Text;
import com.cntinker.util.wx.connect.TextMessage;
import com.cntinker.util.wx.connect.TextMessageF;
import com.xnradmin.client.action.wx.WXConnectAction;
import com.xnradmin.core.dao.CommonDAO;
import com.xnradmin.po.business.BusinessCategory;
import com.xnradmin.po.business.BusinessGoods;
import com.xnradmin.po.business.BusinessWeight;
import com.xnradmin.po.wx.OutPlan;
import com.xnradmin.po.wx.connect.Farmer;
import com.xnradmin.po.wx.connect.WXInit;
import com.xnradmin.po.wx.connect.WXurl;
import com.xnradmin.vo.OutPlanCountVO;
import com.xnradmin.vo.business.BusinessGoodsVO;
import com.xnradmin.vo.business.OutPlanVO;

@Service("OutPlanService")
public class OutPlanService {
	
	private static Logger log = Logger.getLogger(OutPlanService.class);
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
		Date endTime = outplan.getEndTime();
		endTime.setHours(23);
		endTime.setMinutes(59);
		endTime.setSeconds(59);
		outplan.setEndTime(endTime);
		outplan.setOccupyAmount(0d);
		outplan.setValidAmount(Double.valueOf(outplan.getOutput()));
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
										+ " unitId='"+outPlan.getUnitId()+"',"
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
//			BusinessCategory businessCategory = (BusinessCategory) obj[4];
			
			OutPlanVO outPlanVO = new OutPlanVO();
			outPlanVO.setOutPlan(outPlan);
			outPlanVO.setFarmer(farmer);
			outPlanVO.setBusinessGoods(businessGood);
			outPlanVO.setBusinessWeight(businessWeight);
//			outPlanVO.setBusinessCategory(businessCategory);
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
			
			OutPlanVO outPlanVO = new OutPlanVO();
			outPlanVO.setOutPlan(outPlan);
			outPlanVO.setFarmer(farmer);
			outPlanVO.setBusinessGoods(businessGood);
			outPlanVO.setBusinessWeight(businessWeight);
			resList.add(outPlanVO);
		}
		return resList;
	}
	public OutPlanVO getById(String id){
		
		String hql = "from OutPlan a,Farmer b,BusinessGoods c,BusinessWeight d where a.delFlage=0 and a.userId=b.userId"
				+ " and a.goodsId=c.id and a.unitId=d.id and a.id="+id;
		List list= commonDao.getEntitiesByPropertiesWithHql(hql, 0,0);
		Object[] obj = (Object[]) list.get(0);
		OutPlan outPlan = (OutPlan) obj[0];
		Farmer farmer = (Farmer) obj[1];
		BusinessGoods businessGood = (BusinessGoods) obj[2];
		BusinessWeight businessWeight = (BusinessWeight) obj[3];
		
		OutPlanVO outPlanVO = new OutPlanVO();
		outPlanVO.setOutPlan(outPlan);
		outPlanVO.setFarmer(farmer);
		outPlanVO.setBusinessGoods(businessGood);
		outPlanVO.setBusinessWeight(businessWeight);
		return outPlanVO;
	}
	
	private String getHql(OutPlanVO query) {
		StringBuffer hql = new StringBuffer();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		hql.append("from OutPlan a,Farmer b,BusinessGoods c,BusinessWeight d where a.delFlage=0 and a.userId=b.userId"
				+ " and a.goodsId=c.id and a.unitId=d.id");
		if (query == null){
			return hql.append(" order by a.id desc").toString();
		}
		
		if(query.getOutPlan()!=null){
			if(query.getOutPlan().getId()!=null){
				hql.append(" and a.id like '%").append(query.getOutPlan().getId()).append("%'");
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
		}
		
		if(query.getFarmer()!=null){
			if(query.getFarmer().getUserName()!=null&&!query.getFarmer().getUserName().equals("")){
				hql.append(" and b.userName like '%").append(query.getFarmer().getUserName()).append("%'");
			}	
		}
		
		if(query.getBusinessGoods()!=null){
			if(query.getBusinessGoods().getGoodsName()!=null&&!query.getBusinessGoods().getGoodsName().equals("")){
				hql.append(" and c.goodsName like '%").append(query.getBusinessGoods().getGoodsName()).append("%'");
			}
		}
		
		hql.append(" and a.examine = '1'");
		hql.append(" order by a.id desc");
		return hql.toString();
	}
	private String getHqlByUserId(String userId) {
		StringBuffer hql = new StringBuffer();
		hql.append("from OutPlan a,Farmer b,BusinessGoods c,BusinessWeight d where a.delFlage=0 and a.userId=b.userId"
				+ " and a.goodsId=c.id and a.unitId=d.id ");
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
	public boolean examine(String id,String personId){
		boolean isok = false;
		if(id!=null&&!"".equals(id)){
			String hql = "update OutPlan set examine=1,remarks=null,examinePerson='"+personId+"' where id="+id;
			try {
				commonDao.executeUpdateOrDelete(hql);
				isok = true;
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		return isok;
	}
	public boolean validationDate(String userId,String startTime,String endTime,String goodsId){
		String hql = "from OutPlan where userId='"+userId+ "' and goodsId='"+goodsId+"' and startTime<='"+startTime+"' and endTime>='"+startTime+"'";
		try {
			List l = commonDao.getEntitiesByPropertiesWithHql(hql,0,0);
			if(l.size()>0){
				return false;
			}
			hql = "from OutPlan where userId='"+userId+ "' and goodsId='"+goodsId+"' and startTime<='"+endTime+"' and endTime>='"+endTime+"'";
			l = commonDao.getEntitiesByPropertiesWithHql(hql,0,0);
			if(l.size()>0){
				return false;
			}
			hql = "from OutPlan where userId='"+userId+ "' and goodsId='"+goodsId+"' and startTime>='"+startTime+"' and endTime<='"+endTime+"'";
			l = commonDao.getEntitiesByPropertiesWithHql(hql,0,0);
			if(l.size()>0){
				return false;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return true;
	}
	
	public boolean examineNo(String id,String remarks,String personId){
		boolean isok = false;
		if(id!=null&&!"".equals(id)){
			String hql = "update OutPlan set examine=2 ,remarks='"+remarks+"',examinePerson='"+personId+"' where id="+id;
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
					+ "商品类型："+outPlanVO.getBusinessGoods().getGoodsName()+"\n"
					+ "预计产出时间："+new SimpleDateFormat("yyyy-MM-dd").format(outPlanVO.getOutPlan().getStartTime())+"至"
							+ new SimpleDateFormat("yyyy-MM-dd").format(outPlanVO.getOutPlan().getEndTime())+"\n"
					+ " 产量为："+outPlanVO.getOutPlan().getOutput()+outPlanVO.getBusinessWeight().getWeightName()
					+ "\n已经通过审核";
		}
		if(outPlanVO.getOutPlan().getExamine()==2)
		{
			message="您提交的生产计划\n"
					+ "商品类型："+outPlanVO.getBusinessGoods().getGoodsName()+"\n"
					+ "预计产出时间："+new SimpleDateFormat("yyyy-MM-dd").format(outPlanVO.getOutPlan().getStartTime())+"至"
							+ new SimpleDateFormat("yyyy-MM-dd").format(outPlanVO.getOutPlan().getEndTime())+"\n"
					+ " 产量为："+outPlanVO.getOutPlan().getOutput()+outPlanVO.getBusinessWeight().getWeightName()
					+ "\n被拒绝。拒绝原因为："+outPlanVO.getOutPlan().getRemarks();
		}
		log.debug("***********************");
		log.debug(message+"***********message************");
		log.debug("***********************");
//		String access_token = WXGetTokenService.accessTokenIsOvertime();
//	    Text text = new Text();
//	    text.setContent(message);
//	    TextMessage textMessage = new TextMessage();
//	    textMessage.setTouser(outPlanVO.getOutPlan().getUserId());
//	    textMessage.setMsgtype("text");
//	   //如果是服务号此处注释掉
////	    textMessage.setAgentid(WXInit.AGENT_ID);
//	    textMessage.setText(text);
//	    textMessage.setSafe(0);
//	    String outputStr = JSONObject.fromObject(textMessage).toString();
//	    WeixinUtil.httpRequest(WXurl.WX_MESSARW_TO_FROMUSER.replace("ACCESS_TOKEN", access_token), "POST", outputStr);
		String access_tokenF = WXFGetTokenService.accessTokenIsOvertime();
		Text text = new Text();
		text.setContent(message);
		TextMessageF textMessageF =new TextMessageF();
		textMessageF.setTouser(outPlanVO.getOutPlan().getUserId());
		textMessageF.setMsgtype("text");
		textMessageF.setText(text);
		String outputStr = JSONObject.fromObject(textMessageF).toString();
		log.debug("***********************");
		log.debug(outputStr+"***********outputStr************");
		log.debug("***********************");
		JSONObject jsons =  WeixinUtil.httpRequest(WXurl.WXF_MESSARW_TO_USER.replace("ACCESS_TOKEN", access_tokenF), "POST", outputStr);
		if(jsons.toString().indexOf("40001")!=-1)
		{
			access_tokenF = WXFGetTokenService.getAccessToken();
			WXFGetTokenService.writeAccessToken(access_tokenF, new Date().getTime(), ServletActionContext.getRequest());
			WeixinUtil.httpRequest(WXurl.WXF_MESSARW_TO_USER.replace("ACCESS_TOKEN", access_tokenF), "POST", outputStr);
		}
		
		log.debug("***********************");
		log.debug(jsons+"***********jsons************");
		log.debug("***********************");
	}
	
	
	
	
	
	
	/**
	 * 
	 * @param firstletter
	 * @param curPage
	 * @param pageSize
	 * @param orderField
	 * @param direction
	 * @return List<GetOrgListVO>
	 */
	public List<OutPlanVO> listVO(OutPlanVO vo, int curPage,
			int pageSize, String orderField, String direction) {
		
		String hql = getHql(vo);
		
		List l = commonDao.getEntitiesByPropertiesWithHql(hql, curPage,
				pageSize);
		List<OutPlanVO> resList = new LinkedList<OutPlanVO>();
		for (int i = 0; i < l.size(); i++) {
			Object[] obj = (Object[]) l.get(i);
			OutPlan outPlan = (OutPlan) obj[0];
			Farmer farmer = (Farmer) obj[1];
			BusinessGoods goods = (BusinessGoods) obj[2];
			
			OutPlanVO outPlanVo = new OutPlanVO();
			outPlanVo.setOutPlan(outPlan);
			outPlanVo.setBusinessGoods(goods);
			outPlanVo.setFarmer(farmer);
			resList.add(outPlanVo);
		}
		return resList;
	}
	public int modify(OutPlan outPlan)
	{
		OutPlan old = new OutPlan();
		old = findById(outPlan.getId().toString());
		if (StringHelper.isNull(outPlan.getUserId())) {
			outPlan.setUserId(old.getUserId());
		}
		if(StringHelper.isNull(outPlan.getGoodsId()))
		{
			outPlan.setGoodsId(old.getGoodsId());
		}
		if(outPlan.getStartTime()==null)
		{
			outPlan.setStartTime(old.getStartTime());
		}
		if(outPlan.getEndTime()==null)
		{
			outPlan.setEndTime(old.getEndTime());
		}
		if(StringHelper.isNull(outPlan.getUnitId()))
		{
			outPlan.setUnitId(old.getUnitId());
		}
		if(StringHelper.isNull(outPlan.getOutput()))
		{
			outPlan.setOutput(old.getOutput());
		}
		if(outPlan.getCreateDate()==null)
		{
			outPlan.setCreateDate(old.getCreateDate());
		}
		if(outPlan.getUpdateDate()==null)
		{
			outPlan.setUpdateDate(old.getUpdateDate());
		}
		if(StringHelper.isNull(outPlan.getCreateBy()))
		{
			outPlan.setCreateBy(old.getCreateBy());
		}
		if(StringHelper.isNull(outPlan.getUpdateBy()))
		{
			outPlan.setUpdateBy(old.getUpdateBy());
		}
		if(StringHelper.isNull(outPlan.getDelFlage()+""))
		{
			outPlan.setDelFlage(old.getDelFlage());
		}
		if(StringHelper.isNull(outPlan.getExaminePerson()))
		{
			outPlan.setExaminePerson(old.getExaminePerson());
		}
		if(StringHelper.isNull(outPlan.getRemarks()))
		{
			outPlan.setRemarks(old.getRemarks());
		}
		if(outPlan.getExamine()==null)
		{
			outPlan.setExamine(old.getExamine());
		}
		if(outPlan.getOccupyAmount()==null)
		{
			outPlan.setOccupyAmount(old.getOccupyAmount());
		}
		if(outPlan.getValidAmount()==null)
		{
			outPlan.setValidAmount(old.getValidAmount());
		}
		commonDao.merge(outPlan);
		return 0;
	}
	
	public List<OutPlanCountVO> findOccupyAmountCount(String userId)
	{
		String hql = "select sum(a.occupyAmount),sum(a.validAmount),sum(a.output),a.goodsId,b.goodsName,sum(a.sendoutAmount) from OutPlan a,BusinessGoods b where a.userId='"+userId+"' and a.examine='1' and a.goodsId=b.id GROUP BY a.goodsId";
		List list =  commonDao.getEntitiesByPropertiesWithHql(hql, 0, 0);
		List<OutPlanCountVO> opcv = new ArrayList<OutPlanCountVO>();
		for (int i = 0; i < list.size(); i++) {
			Object[] obj= (Object[])list.get(i);
			OutPlanCountVO ov = new OutPlanCountVO();
			ov.setOccupyAmountSum(obj[0].toString());
			ov.setValidAmountSum((String)obj[1].toString());
			ov.setOutPutSum(obj[2].toString());
			ov.setGoodsId((String)obj[3]);
			ov.setGoodsName(obj[4].toString());
			ov.setSendoutAmount(obj[5].toString());
			opcv.add(ov);
		}
		return opcv;
	}
	
	
}
