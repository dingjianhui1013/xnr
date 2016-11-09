/**
 *2012-5-11 上午9:00:02
 */
package com.xnradmin.core.service.mall.clientUser;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cntinker.util.StringHelper;
import com.xnradmin.core.dao.CommonDAO;
import com.xnradmin.core.dao.mall.clientUser.ClientUserRegionInfoDAO;
import com.xnradmin.po.client.ClientUserRegionInfo;
import com.xnradmin.po.client.ClientUserInfo;
import com.xnradmin.vo.client.ClientUserVO;

/**
 * @autohr: xiang_liu
 * 
 */
@Service("ClientUserRegionInfoService")
public class ClientUserRegionInfoService {

	@Autowired
	private ClientUserRegionInfoDAO dao;
	
	@Autowired
	private CommonDAO commonDao;

	static Log log = LogFactory.getLog(ClientUserRegionInfoService.class);

	/**
	 * 
	 * @param po
	 * @return int
	 */
	public int save(ClientUserRegionInfo po) {
		int i = 0;
		//每个用户最多绑定20个地区
		if(getCount(po.getClientUserInfoId().toString(),null,null,
				null,null,null,null,null,null,null,null,null,null,null,null)<20){
			dao.save(po);
			i=0;
		}else{
			i=1;
		}
		return i;
	}

	/**
	 * 
	 * @param po
	 * @return int
	 */
	public int saveBusiness(ClientUserRegionInfo po) {
		int i = 0;
		dao.save(po);
		return i;
	}
	/**
	 * 
	 * @param po
	 * @return int
	 */
	public int saveWx(ClientUserRegionInfo po) {
		int i = 0;
		//每个用户最多绑定20个地区
		if(getCount(po.getClientUserInfoId().toString(),null,null,
				null,null,null,null,null,null,null,null,null,null,null,null)<20){
			dao.save(po);
			i=0;
		}else{
			i=1;
		}
		return i;
	}
	
	public ClientUserRegionInfo findByid(String id) {
		return dao.findById(new Integer(id));
	}

	public List<ClientUserRegionInfo> findByProperty(String propertyName,Object value){
		return dao.findByProperty(propertyName,value);
	}
	
	/**	  
	 * @param po
	 * @return int
	 */
	public int modify(ClientUserRegionInfo po) {
		this.dao.merge(po);
		return 0;
	}

	public void del(String id){
		ClientUserRegionInfo po = this.dao.findById(new Integer(id));
        this.dao.delete(po);
    }
	
	public int removeClientUserRegionInfoId(Integer id) {
		return dao.removeClientUserRegionInfoId(id);
	}
	
	public int removeClientUserInfoId(Integer id) {
		return dao.removeClientUserInfoId(id);
	}
	
	/**
	 * @param brandname
	 * @param brandmodelsname
	 * @param typename
	 * @param brandid
	 * @param cartypeid
	 * @return int
	 */
	public int getCount(String clientUserInfoId, String userMobile, String wxnickname, 
			String wxopenuid, String email, String countryName, String provinceName,
			String cityName, String areaName, String userRealMobile, String userRealPlane,
			String userRealName, String sourceType, String createStartTime, String createEndTime) {
		String hql = "select count(*) from ClientUserInfo a ,ClientUserRegionInfo b "
				+ " where a.id = b.clientUserInfoId ";
		if (clientUserInfoId!=null) {
			hql = hql + " and a.id=" + clientUserInfoId;
		}
		if (userMobile!=null) {
			hql = hql + " and a.mobile like '%" + userMobile +"%'";
		}
		if (wxnickname!=null) {
			hql = hql + " and a.wxnickname like '%" + wxnickname +"%'";
		}
		if (wxopenuid!=null) {
			hql = hql + " and a.wxopenuid='" + wxopenuid +"'";
		}
		if (email!=null) {
			hql = hql + " and a.email like '%" + email +"%'";
		}
		if (countryName!=null) {
			hql = hql + " and a.countryName like '%" + countryName +"%'";
		}
		if (provinceName!=null) {
			hql = hql + " and a.provinceName like '%" + provinceName +"%'";
		}
		if (cityName!=null) {
			hql = hql + " and a.cityName like '%" + cityName +"%'";
		}
		if (areaName!=null) {
			hql = hql + " and a.areaName like '%" + areaName +"%'";
		}
		if (userRealMobile!=null) {
			hql = hql + " and b.userRealMobile like '%" + userRealMobile +"%'";
		}
		if (userRealPlane!=null) {
			hql = hql + " and b.userRealPlane like '%" + userRealPlane +"%'";
		}
		if (userRealName!=null) {
			hql = hql + " and b.userRealName like '%" + userRealName +"%'";
		}
		if (sourceType!=null) {
			hql = hql + " and a.sourceType=" + sourceType;
		}
		if (createStartTime!=null ) {
			hql = hql + " and a.createTime>'" + createStartTime +"'";
		}
		if (createEndTime!=null ) {
			hql = hql + " and a.createTime<'" + createEndTime +"'";
		}
		
		return this.commonDao.getNumberOfEntitiesWithHql(hql);
	}
	
	/**
	 * @param curPage
	 * @param pageSize
	 * @param orderField
	 * @param direction
	 * @return List<GetOrgListVO>
	 */
	public List<ClientUserVO> listVO(String clientUserInfoId, String userMobile, String wxnickname, 
			String wxopenuid, String email, String countryName, String provinceName,
			String cityName, String areaName, String userRealMobile, String userRealPlane,
			String userRealName, String sourceType, String createStartTime, String createEndTime,
			int curPage, int pageSize, String orderField, String direction) {
		String hql = " from ClientUserInfo a ,ClientUserRegionInfo b "
				+ " where a.id = b.clientUserInfoId ";
		if (clientUserInfoId!=null) {
			hql = hql + " and a.id=" + clientUserInfoId;
		}
		if (userMobile!=null) {
			hql = hql + " and a.mobile like '%" + userMobile +"%'";
		}
		if (wxnickname!=null) {
			hql = hql + " and a.wxnickname like '%" + wxnickname +"%'";
		}
		if (wxopenuid!=null) {
			hql = hql + " and a.wxopenuid='" + wxopenuid +"'";
		}
		if (email!=null) {
			hql = hql + " and a.email like '%" + email +"%'";
		}
		if (countryName!=null) {
			hql = hql + " and a.countryName like '%" + countryName +"%'";
		}
		if (provinceName!=null) {
			hql = hql + " and a.provinceName like '%" + provinceName +"%'";
		}
		if (cityName!=null) {
			hql = hql + " and a.cityName like '%" + cityName +"%'";
		}
		if (areaName!=null) {
			hql = hql + " and a.areaName like '%" + areaName +"%'";
		}
		if (userRealMobile!=null) {
			hql = hql + " and b.userRealMobile like '%" + userRealMobile +"%'";
		}
		if (userRealPlane!=null) {
			hql = hql + " and b.userRealPlane like '%" + userRealPlane +"%'";
		}
		if (userRealName!=null) {
			hql = hql + " and b.userRealName like '%" + userRealName +"%'";
		}
		if (sourceType!=null) {
			hql = hql + " and a.sourceType=" + sourceType;
		}
		if (createStartTime!=null ) {
			hql = hql + " and a.createTime>'" + createStartTime +"'";
		}
		if (createEndTime!=null ) {
			hql = hql + " and a.createTime<'" + createEndTime +"'";
		}
		if (!StringHelper.isNull(orderField) && !StringHelper.isNull(direction)) {
			hql = hql + " order by " + orderField + " " + direction;
		}else{
			hql +=" order by a.id desc";
		}

		List l = commonDao.getEntitiesByPropertiesWithHql(hql, curPage, pageSize);
		List<ClientUserVO> resList = new ArrayList<ClientUserVO>();
		for (int i = 0; i < l.size(); i++) {
			Object[] obj = (Object[]) l.get(i);
			ClientUserInfo cui = (ClientUserInfo) obj[0];
			ClientUserRegionInfo curi = (ClientUserRegionInfo) obj[1];
			ClientUserVO vo = new ClientUserVO();

			//用户数据
			if(cui!=null){
				if(cui.getId()!=null){
					vo.setClientUserInfoId(cui.getId().toString());
				}
				if(!StringHelper.isNull(cui.getNickName())){
					vo.setNickName(cui.getNickName().toString());
				}
				if(!StringHelper.isNull(cui.getEmail())){
					vo.setEmail(cui.getEmail());
				}
				if(!StringHelper.isNull(cui.getMobile())){
					vo.setMobile(cui.getMobile());
				}
				if(!StringHelper.isNull(cui.getLoginPassWord())){
					vo.setLoginPassWord(cui.getLoginPassWord());
				}
				if(!StringHelper.isNull(cui.getPaymentPassword())){
					vo.setPaymentPassword(cui.getPaymentPassword());
				}
				if(cui.getStatus()!=null){
					vo.setStatus(cui.getStatus().toString());
				}
				if(!StringHelper.isNull(cui.getStatusName())){
					vo.setStatusName(cui.getStatusName());
				}
				if(cui.getType()!=null){
					vo.setType(cui.getType().toString());
				}
				if(!StringHelper.isNull(cui.getTypeName())){
					vo.setTypeName(cui.getTypeName());
				}
				if(!StringHelper.isNull(cui.getUuid())){
					vo.setUuid(cui.getUuid());
				}
				if(cui.getLastLoginTime()!=null){
					vo.setLastLoginTime(cui.getLastLoginTime().toString());
				}
				if(cui.getCreateTime()!=null){
					vo.setClientUserInfoCreateTime(cui.getCreateTime().toString());
				}
				if(cui.getModifyTime()!=null){
					vo.setClientUserInfoModifyTime(cui.getModifyTime().toString());
				}
				if(cui.getSourceId()!=null){
					vo.setSourceId(cui.getSourceId().toString());
				}
				if(!StringHelper.isNull(cui.getSourceName())){
					vo.setSourceName(cui.getSourceName());
				}
				if(cui.getSourceType()!=null){
					vo.setSourceType(cui.getSourceType().toString());
				}
				if(!StringHelper.isNull(cui.getSourceTypeName())){
					vo.setSourceTypeName(cui.getSourceTypeName());
				}
				if(cui.getDiscount()!=null){
					vo.setDiscount(cui.getDiscount().toString());
				}
				if(!StringHelper.isNull(cui.getWxfromusername())){
					vo.setWxfromusername(cui.getWxfromusername());
				}
				if(!StringHelper.isNull(cui.getWxtousername())){
					vo.setWxtousername(cui.getWxtousername());
				}
				if(cui.getWxsubtime()!=null){
					vo.setWxsubtime(cui.getWxsubtime().toString());
				}
				if(cui.getWxunsubtime()!=null){
					vo.setWxunsubtime(cui.getWxunsubtime().toString());
				}
				if(cui.getWxlastActvieTime()!=null){
					vo.setWxlastActvieTime(cui.getWxlastActvieTime().toString());
				}
				if(!StringHelper.isNull(cui.getWxmsgtype())){
					vo.setWxmsgtype(cui.getWxmsgtype());
				}
				if(!StringHelper.isNull(cui.getWxevent())){
					vo.setWxevent(cui.getWxevent());
				}
				if(!StringHelper.isNull(cui.getWxnetworktype())){
					vo.setWxnetworktype(cui.getWxnetworktype());
				}
				if(!StringHelper.isNull(cui.getWxopenuid())){
					vo.setWxopenuid(cui.getWxopenuid());
				}
				if(!StringHelper.isNull(cui.getWxnickname())){
					vo.setWxnickname(cui.getWxnickname());
				}
				if(!StringHelper.isNull(cui.getWxsex())){
					vo.setWxsex(cui.getWxsex());
				}
				if(!StringHelper.isNull(cui.getWxlanguage())){
					vo.setWxlanguage(cui.getWxlanguage());
				}
				if(cui.getWxstatusid()!=null){
					vo.setWxstatusid(cui.getWxstatusid().toString());
				}
				if(!StringHelper.isNull(cui.getWxstatusName())){
					vo.setWxstatusName(cui.getWxstatusName());
				}
				if(!StringHelper.isNull(cui.getWxcity())){
					vo.setWxcity(cui.getWxcity());
				}
				if(!StringHelper.isNull(cui.getWxprovince())){
					vo.setWxprovince(cui.getWxprovince());
				}
				if(!StringHelper.isNull(cui.getWxcountry())){
					vo.setWxcountry(cui.getWxcountry());
				}
				if(!StringHelper.isNull(cui.getWxheadimgurl())){
					vo.setWxheadimgurl(cui.getWxheadimgurl());
				}
				if(!StringHelper.isNull(cui.getWxunionid())){
					vo.setWxunionid(cui.getWxunionid());
				}
				if(cui.getIsappuser()!=null){
					vo.setIsappuser(cui.getIsappuser().toString());
				}
				if(cui.getIsemailuser()!=null){
					vo.setIsemailuser(cui.getIsemailuser().toString());
				}
				if(cui.getIsmobileuser()!=null){
					vo.setIsmobileuser(cui.getIsmobileuser().toString());
				}
				if(cui.getIswebsiteuser()!=null){
					vo.setIswebsiteuser(cui.getIswebsiteuser().toString());
				}
				if(cui.getIswxuser()!=null){
					vo.setIswxuser(cui.getIswxuser().toString());
				}
			}
			//用户地区组合数据
			if(curi!=null){
				if(curi.getId()!=null){
					vo.setClientUserRegionInfoId(curi.getId().toString());
				}
				if(curi.getCountryId()!=null){
					vo.setCountryId(curi.getCountryId().toString());
				}
				if(!StringHelper.isNull(curi.getCountryName())){
					vo.setCountryName(curi.getCountryName());
				}
				if(curi.getProvinceId()!=null){
					vo.setProvinceId(curi.getProvinceId().toString());
				}
				if(!StringHelper.isNull(curi.getProvinceName())){
					vo.setProvinceName(curi.getProvinceName());
				}
				if(curi.getCityId()!=null){
					vo.setCityId(curi.getCityId().toString());
				}
				if(!StringHelper.isNull(curi.getCityName())){
					vo.setCityName(curi.getCityName());
				}
				if(curi.getAreaId()!=null){
					vo.setAreaId(curi.getAreaId().toString());
				}
				if(!StringHelper.isNull(curi.getAreaName())){
					vo.setAreaName(curi.getAreaName());
				}
				if(!StringHelper.isNull(curi.getUserRealAddress())){
					vo.setUserRealAddress(curi.getUserRealAddress());
				}
				if(curi.getClientUserInfoId()!=null){
					vo.setClientUserRegionInfoClientUserInfoId(curi.getClientUserInfoId().toString());
				}
				if(!StringHelper.isNull(curi.getUserRealMobile())){
					vo.setUserRealMobile(curi.getUserRealMobile());
				}
				if(!StringHelper.isNull(curi.getUserRealName())){
					vo.setUserRealName(curi.getUserRealName());
				}
				if(!StringHelper.isNull(curi.getUserRealPlane())){
					vo.setUserRealPlane(curi.getUserRealPlane());
				}
				if(!StringHelper.isNull(curi.getUserRealPostcode())){
					vo.setUserRealPostcode(curi.getUserRealPostcode());
				}
				if(curi.getCreateTime()!=null){
					vo.setClientUserRegionInfoCreateTime(curi.getCreateTime().toString());
				}
				if(curi.getModifyTime()!=null){
					vo.setClientUserRegionInfoModifyTime(curi.getModifyTime().toString());
				}
				
			}
			resList.add(vo);
		}
		return resList;
	}
	/**
	 * @return List<CarBrandModels>
	 */
	public List<ClientUserRegionInfo> listAll() {
		return dao.findAll();
	}

}
