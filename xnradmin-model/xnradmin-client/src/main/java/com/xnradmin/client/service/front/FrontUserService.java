package com.xnradmin.client.service.front;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cntinker.security.MD5Encoder;
import com.xnradmin.core.dao.CommonDAO;
import com.xnradmin.po.front.FrontUser;
import com.xnradmin.po.wx.connect.Farmer;

@Service("FrontUserService")
@Transactional
public class FrontUserService {
	
	@Autowired
	private CommonDAO commonDao;
	
	
	public boolean isExistloginName(String phone){
	    String hql = "from FrontUser where phone='"+phone+"'";
        List<FrontUser> list = commonDao.getEntitiesByPropertiesWithHql(hql,0,0);
        if(list.size()>0){
            return true;
        }else{
            return false;
        }
        
	}
	/**
	 * 
	 */
	public boolean save(FrontUser frontUser){
		try {
			frontUser.setCreateDate(new Date());
			frontUser.setDelFlag("0");
			frontUser.setType("0");
			if(frontUser.getUserName()==null||"".equals(frontUser.getUserName())){
				frontUser.setUserName(frontUser.getPhone());
			}
			frontUser.setPassword(MD5Encoder.encode32(frontUser.getPassword()));
			commonDao.save(frontUser);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}
	/**
	 * 根据手机或者邮箱查询用户
	 */
	public FrontUser getByPhoneOrEmail(String userName,String password){
		String hql = "from FrontUser where type=1 and password='"+password+"' and (phone='"+userName+"' or email='"+userName+"')";
		List<FrontUser> list = commonDao.getEntitiesByPropertiesWithHql(hql,0,0);
		if(list.isEmpty()){
			return null;
		}else{
			return list.get(0);
		}
	}
	public FrontUser findByid(String id){
		String hql = "from FrontUser where id="+id;
		List<FrontUser> list = commonDao.getEntitiesByPropertiesWithHql(hql,0,0);
		if(list.isEmpty()){
			return null;
		}else{
			return list.get(0);
		}
	}
	public List<FrontUser> findAll(FrontUser query, int PageNum,int NumPerPage){
		String hql = getHql(query);
		List<FrontUser> list = commonDao.getEntitiesByPropertiesWithHql(hql.toString(),PageNum,NumPerPage);
		return list;
	}
	public int getCount(FrontUser query) {
		String hql = "select count(*) " + getHql(query);
		return commonDao.getNumberOfEntitiesWithHql(hql);
	}
	private String getHql(FrontUser query){
		StringBuffer hql = new StringBuffer("from FrontUser where 1=1 ");
		if(null!=query){
			if(query.getUserName()!=null&&!"".equals(query.getUserName())){
				hql.append(" and userName like '%").append(query.getUserName()).append("%' ");
			}
			if(query.getPhone()!=null&&!"".equals(query.getPhone())){
				hql.append(" and phone like '%").append(query.getPhone()).append("%' ");
			}
			if(query.getEmail()!=null&&!"".equals(query.getEmail())){
				hql.append(" and email like '%").append(query.getEmail()).append("%' ");
			}
			if(query.getType()!=null&&!"".equals(query.getType())){
				hql.append(" and type=").append(query.getType());
			}
		}
		hql.append("order by id");
		return hql.toString();
	}
	public boolean modify(FrontUser frontUser){
		try {
			StringBuffer hql = new StringBuffer("update FrontUser set ");
			if(frontUser.getUserName()!=null&&!"".equals(frontUser.getUserName())){
				hql.append("userName='").append(frontUser.getUserName()).append("', ");
			}
			if(frontUser.getPhone()!=null&&!"".equals(frontUser.getPhone())){
				hql.append("phone='").append(frontUser.getPhone()).append("', ");
			}
			if(frontUser.getEmail()!=null&&!"".equals(frontUser.getEmail())){
				hql.append("email='").append(frontUser.getEmail()).append("' ");
			}
			hql.append("where id=").append(frontUser.getId());
			commonDao.executeUpdateOrDelete(hql.toString());
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	public boolean modifyName(FrontUser frontUser){
		try {
			StringBuffer hql = new StringBuffer("update FrontUser set ");
			if(frontUser.getUserName()!=null&&!"".equals(frontUser.getUserName())){
				hql.append("userName='").append(frontUser.getUserName()).append("' ");
			}
			hql.append("where id=").append(frontUser.getId());
			commonDao.executeUpdateOrDelete(hql.toString());
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	public boolean modifyPassword(FrontUser frontUser){
		try {
			frontUser.setPassword(MD5Encoder.encode32(frontUser.getPassword()));
			StringBuffer hql = new StringBuffer("update FrontUser set ");
			if(frontUser.getPassword()!=null&&!"".equals(frontUser.getPassword())){
				hql.append("password='").append(frontUser.getPassword()).append("' ");
			}
			hql.append("where id=").append(frontUser.getId());
			commonDao.executeUpdateOrDelete(hql.toString());
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	public boolean delete(String id){
		try {
			String hql = "delete from FrontUser where id="+id;
			commonDao.executeUpdateOrDelete(hql.toString());
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	public boolean type(String id){
		boolean isok = false;
		if(id!=null&&!"".equals(id)){
			String hql = "update FrontUser set type=1 where id="+id;
			try {
				commonDao.executeUpdateOrDelete(hql);
				isok = true;
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		return isok;
	}
	public boolean typeNo(String id){
		boolean isok = false;
		if(id!=null&&!"".equals(id)){
			String hql = "update FrontUser set type=2 where id="+id;
			try {
				commonDao.executeUpdateOrDelete(hql);
				isok = true;
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		return isok;
	}
	public boolean reset(String id){
		boolean isok = false;
		if(id!=null&&!"".equals(id)){
			String hql = "update FrontUser set password='111111' where id="+id;
			try {
				commonDao.executeUpdateOrDelete(hql);
				isok = true;
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		return isok;
	}
}
