package com.xnradmin.core.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cntinker.util.StringHelper;
import com.xnradmin.core.dao.CommonDAO;
import com.xnradmin.core.dao.CommonStaffDAO;
import com.xnradmin.core.dao.CommonStaffRoleRelationDAO;
import com.xnradmin.core.util.SecureUtil;
import com.xnradmin.constant.ViewConstant;
import com.xnradmin.po.CommonOrganization;
import com.xnradmin.po.CommonStaff;
import com.xnradmin.po.CommonStaffRoleRelation;
import com.xnradmin.po.CommonStaffStatus;
import com.xnradmin.vo.StaffVO;

/**
 * @autohr: bin_liu
 */
@Service("StaffService")
public class StaffService {
	static Log log = LogFactory.getLog(StaffService.class);

	@Autowired
	private CommonStaffDAO dao;
	@Autowired
	private CommonDAO commonDao;
	@Autowired
	private OrganizationService orgService;
	@Autowired
	private CommonStaffRoleRelationDAO srdao;

	/**
	 * @param po
	 */
	public int save(CommonStaff po) {
		if (this.dao.findByLoginId(po.getLoginId()).size() > 0) {
			return 1;
		}
		if (po.getLeadStaffId() != null && po.getLeadStaffId() > 0
				&& !StringHelper.isNull(po.getLeadStaffName())) {
			CommonStaff temp = this.dao.findById(po.getLeadStaffId());
			CommonOrganization org = orgService.findByid(temp
					.getOrganizationId().toString());
			po.setLeadStaffOrgId(temp.getOrganizationId());
			po.setLeadStaffOrgName(org.getOrganizationName());
		}

		// 部门总监唯一性判定
		if (po.getDirector() != null) {
			List<StaffVO> v = listVO(null, null, null, po.getOrganizationId()
					.toString(), null, null, null, null, null, 1, 0, 0, null,
					null);
			if (v != null && v.size() > 0) {
				return 2;
			}
		}
		Serializable cls = dao.save(po);
		return (Integer) cls;

	}

	public int saveBusinessUser(CommonStaff po) {
		if (this.dao.findByMobile(po.getMobile()).size() > 0) {
			return -1;
		}
		if (po.getLeadStaffId() != null && po.getLeadStaffId() > 0
				&& !StringHelper.isNull(po.getLeadStaffName())) {
			CommonStaff temp = this.dao.findById(po.getLeadStaffId());
			CommonOrganization org = orgService.findByid(temp
					.getOrganizationId().toString());
			po.setLeadStaffOrgId(temp.getOrganizationId());
			po.setLeadStaffOrgName(org.getOrganizationName());
		}
		Serializable cls = dao.save(po);
		return (Integer) cls;

	}

	public StaffVO findDirectorByOrgid(int orgid) {

		List<StaffVO> v = listVO(null, null, null,
				new Integer(orgid).toString(), null, null, null, null, null, 1,
				0, 0, null, null);
		if (v != null && v.size() > 0)
			return v.get(0);
		else
			return null;
	}

	public StaffVO findDirectorByStaffid(Integer staffid) {

		CommonStaff staff = findByid(staffid.toString());
		if (staff == null)
			return null;

		return findDirectorByOrgid(staff.getOrganizationId());
	}

	public CommonStaff findByid(String id) {
		return dao.findById(new Integer(id));
	}

	public CommonStaff findByLoginId(String id) {
		List<CommonStaff> l = dao.findByLoginId(id);
		if (l != null && l.size() > 0)
			return l.get(0);
		return null;
	}

	public List<CommonStaff> findAll() {
		List<CommonStaff> l = dao.findAll();
		return l;
	}

	/**
	 * 获取员工数量
	 * 
	 * @param porgid
	 * @return int
	 */
	public int getCount(String leaderStaffid, String loginid, String staffName,
			String orgid, String orgname, String sTime, String eTime,
			String statusid, String isManager, Integer isDiDirector,
			String orderField, String direction) {
		String hql = "select count(*)"
				+ getHql(leaderStaffid, loginid, staffName, orgid, orgname,
						sTime, eTime, statusid, isManager, isDiDirector,
						orderField, direction);
		return this.commonDao.getNumberOfEntitiesWithHql(hql);
	}

	public int getCountByRole(Integer roleid) {
		String hql = "select count(*)" + getHqlByRoleId(roleid);
		return this.commonDao.getNumberOfEntitiesWithHql(hql);
	}

	public int getCountByLeadstaff(Integer leadstaff, String orderField,
			String direction) {
		String hql = "select count(*)"
				+ getHqlByLeadStaffId(leadstaff, orderField, direction);
		return this.commonDao.getNumberOfEntitiesWithHql(hql);
	}

	/**
	 * 1=已经有相同的登录名<br>
	 * 2=已经有部门总监<br>
	 * 
	 * @param po
	 * @return int
	 */
	public int modify(CommonStaff po) {
		List l = this.dao.findByLoginId(po.getLoginId());

		CommonStaff old = null;
		if (l.size() > 0) {
			old = this.dao.findById(po.getId());
			if (!old.getLoginId().equals(po.getLoginId()))
				return 1;
		}

		if (po.getLeadStaffId() != null && po.getLeadStaffId() > 0
				&& !StringHelper.isNull(po.getLeadStaffName())) {
			CommonStaff temp = this.dao.findById(po.getLeadStaffId());
			CommonOrganization org = orgService.findByid(temp
					.getOrganizationId().toString());
			po.setLeadStaffOrgId(temp.getOrganizationId());
			po.setLeadStaffOrgName(org.getOrganizationName());
		}

		// 部门总监唯一性判定
		if (po.getDirector() != null) {
			List<StaffVO> v = listVO(null, null, null, po.getOrganizationId()
					.toString(), null, null, null, null, null, 1, 0, 0, null,
					null);
			for (StaffVO e : v) {
				if (e.getStaff().getId().intValue() != old.getId().intValue()) {
					return 2;
				}
			}
		}

		this.dao.merge(po);
		return 0;
	}

	/**
	 * modify用
	 * 
	 * @param id
	 * @return StaffVO
	 */
	public StaffVO findVoByid(String id) {
		String hql = "from CommonStaff a,CommonStaffStatus b,CommonOrganization c where a.statusId=b.id and a.organizationId=c.id and a.id="
				+ id;
		List<Object[]> l = commonDao.getEntitiesByPropertiesWithHql(hql, 1, 1);

		StaffVO vo = new StaffVO();
		if (l != null && l.size() > 0) {

			Object[] obj = (Object[]) l.get(0);
			CommonStaff staff = (CommonStaff) obj[0];
			CommonStaffStatus status = (CommonStaffStatus) obj[1];
			CommonOrganization org = (CommonOrganization) obj[2];

			vo.setStaff(staff);
			vo.setCreateTime(StringHelper.getSystime(
					ViewConstant.PAGE_DATE_FORMAT_DAY, staff.getCreateTime()
							.getTime()));
			vo.setEmail(staff.getEmail());
			vo.setLogin_id(staff.getLoginId());
			vo.setMobile(staff.getMobile());
			vo.setIdcard(staff.getIdcard());
			if (staff.getWorkinglife() != null)
				vo.setWorklife(staff.getWorkinglife().toString());
			if (staff.getWorkingtime() != null)
				vo.setWorkingtime(StringHelper.getSystime(
						ViewConstant.PAGE_DATE_FORMAT_DAY, staff
								.getWorkingtime().getTime()));
			vo.setStaff(staff);
			vo.setOrgid(org.getId().toString());

			vo.setOrgname(org.getOrganizationName());

			// porg
			if (org.getPraentOrganizationId() != null) {
				CommonOrganization porg = orgService.findByid(org
						.getPraentOrganizationId().toString());
				vo.setPorgid(porg.getId().toString());
				vo.setPorgname(porg.getOrganizationName());
			}
			// other..
			vo.setPswd(staff.getPassword());
			vo.setStaffNo(staff.getStaffNo());
			vo.setUserid(staff.getId().toString());
			vo.setStaffName(staff.getStaffName());
			vo.setBankInformation(staff.getBankInformation());
			if (staff.getDiscount() != null) {
				vo.setDiscount(staff.getDiscount().toString());
			}
			if (staff.getManager() != null)
				vo.setManager(staff.getManager());
			vo.setStatusName(status.getStatusName());
		}

		return vo;

	}

	/**
	 * 查询列表
	 * 
	 * @param orgTypeName
	 * @param curPage
	 * @param pageSize
	 * @param orderField
	 * @param direction
	 * @return List<StaffVO>
	 */
	public List<StaffVO> listVO(String leaderStaffid, String loginid,
			String staffName, String orgid, String orgname, String sTime,
			String eTime, String statusid, String isManager,
			Integer isDiDirector, int curPage, int pageSize, String orderField,
			String direction) {
		// String hql =
		// //"select a.createTime,a.email,a.loginId,a.mobile,a.organizationId,a.password,a.staffNo,a.id,a.staffName,b.statusName"
		String hql = getHql(leaderStaffid, loginid, staffName, orgid, orgname,
				sTime, eTime, statusid, isManager, isDiDirector, orderField,
				direction);

		List<Object[]> l = commonDao.getEntitiesByPropertiesWithHql(hql,
				curPage, pageSize);

		List<StaffVO> res = new ArrayList<StaffVO>();

		for (int i = 0; i < l.size(); i++) {
			StaffVO vo = new StaffVO();

			Object[] obj = (Object[]) l.get(i);
			CommonStaff staff = (CommonStaff) obj[0];
			CommonStaffStatus status = (CommonStaffStatus) obj[1];
			CommonOrganization org = (CommonOrganization) obj[2];

			vo.setStaff(staff);
			vo.setOrg(org);
			vo.setStatus(status);
			vo.setCreateTime(StringHelper.getSystime(
					ViewConstant.PAGE_DATE_FORMAT_DAY, staff.getCreateTime()
							.getTime()));
			vo.setEmail(staff.getEmail());
			vo.setLogin_id(staff.getLoginId());
			vo.setMobile(staff.getMobile());
			vo.setIdcard(staff.getIdcard());
			if (staff.getManager() != null)
				vo.setManager(staff.getManager());
			if (staff.getWorkinglife() != null)
				vo.setWorklife(staff.getWorkinglife().toString());
			if (staff.getWorkingtime() != null)
				vo.setWorkingtime(StringHelper.getSystime(
						ViewConstant.PAGE_DATE_FORMAT_DAY, staff
								.getWorkingtime().getTime()));

			vo.setOrgid(org.getId().toString());

			vo.setOrgname(org.getOrganizationName());

			// porg
			if (org.getPraentOrganizationId() != null) {
				CommonOrganization porg = orgService.findByid(org
						.getPraentOrganizationId().toString());
				vo.setPorgid(porg.getId().toString());
				vo.setPorgname(porg.getOrganizationName());
			}
			// other..
			vo.setPswd(staff.getPassword());
			vo.setStaffNo(staff.getStaffNo());
			vo.setUserid(staff.getId().toString());
			vo.setStaffName(staff.getStaffName());
			vo.setStatusid(status.getId().toString());
			vo.setStatusName(status.getStatusName());
			vo.setBankInformation(staff.getBankInformation());
			if (staff.getDiscount() != null) {
				vo.setDiscount(staff.getDiscount().toString());
			}
			res.add(vo);
		}

		return res;
	}

	/**
	 * 取一个部门指定的角色
	 * 
	 * @param roleid
	 * @param orgid
	 * @return StaffVO
	 */
	public StaffVO findByRoleid(Integer roleid, Integer orgid) {
		String hql = getHqlByRoleIdAndOrgid(roleid, orgid);
		List<Object[]> l = commonDao.getEntitiesByPropertiesWithHql(hql, 0, 0);
		List<StaffVO> res = new ArrayList<StaffVO>();
		for (int i = 0; i < l.size(); i++) {
			StaffVO vo = new StaffVO();

			Object[] obj = (Object[]) l.get(i);
			CommonStaff staff = (CommonStaff) obj[0];
			CommonStaffStatus status = (CommonStaffStatus) obj[1];
			CommonOrganization org = (CommonOrganization) obj[2];
			CommonStaffRoleRelation role = (CommonStaffRoleRelation) obj[3];

			vo.setStaff(staff);
			vo.setOrg(org);
			vo.setRole(role);
			vo.setStatus(status);

			vo.setCreateTime(StringHelper.getSystime(
					ViewConstant.PAGE_DATE_FORMAT_DAY, staff.getCreateTime()
							.getTime()));
			vo.setEmail(staff.getEmail());
			vo.setLogin_id(staff.getLoginId());
			vo.setMobile(staff.getMobile());
			vo.setIdcard(staff.getIdcard());
			if (staff.getManager() != null)
				vo.setManager(staff.getManager());
			if (staff.getWorkinglife() != null)
				vo.setWorklife(staff.getWorkinglife().toString());
			if (staff.getWorkingtime() != null)
				vo.setWorkingtime(StringHelper.getSystime(
						ViewConstant.PAGE_DATE_FORMAT_DAY, staff
								.getWorkingtime().getTime()));

			vo.setOrgid(org.getId().toString());

			vo.setOrgname(org.getOrganizationName());

			// porg
			if (org.getPraentOrganizationId() != null) {
				CommonOrganization porg = orgService.findByid(org
						.getPraentOrganizationId().toString());
				vo.setPorgid(porg.getId().toString());
				vo.setPorgname(porg.getOrganizationName());
			}
			// other..
			vo.setPswd(staff.getPassword());
			vo.setStaffNo(staff.getStaffNo());
			vo.setUserid(staff.getId().toString());
			vo.setStaffName(staff.getStaffName());
			vo.setStatusName(status.getStatusName());
			vo.setBankInformation(staff.getBankInformation());
			res.add(vo);
		}
		if (res == null || res.size() <= 0)
			return null;
		else
			return res.get(0);
	}

	/**
	 * 取一个人员旗下的员工
	 * 
	 * @param roleid
	 * @param orgid
	 * @return StaffVO
	 */
	/**
	 * 查询列表
	 * 
	 * @param orgTypeName
	 * @param curPage
	 * @param pageSize
	 * @param orderField
	 * @param direction
	 * @return List<StaffVO>
	 */
	public List<StaffVO> findByLeadStaffId(Integer leadstaffid, int curPage,
			int pageSize, String orderField, String direction) {
		String hql = getHqlByLeadStaffId(leadstaffid, orderField, direction);
		List<Object[]> l = commonDao.getEntitiesByPropertiesWithHql(hql,
				curPage, pageSize);

		List<StaffVO> res = new ArrayList<StaffVO>();

		for (int i = 0; i < l.size(); i++) {
			StaffVO vo = new StaffVO();

			Object[] obj = (Object[]) l.get(i);
			CommonStaff staff = (CommonStaff) obj[0];
			CommonStaffStatus status = (CommonStaffStatus) obj[1];
			CommonOrganization org = (CommonOrganization) obj[2];

			vo.setStaff(staff);
			vo.setOrg(org);
			vo.setStatus(status);
			vo.setCreateTime(StringHelper.getSystime(
					ViewConstant.PAGE_DATE_FORMAT_DAY, staff.getCreateTime()
							.getTime()));
			vo.setEmail(staff.getEmail());
			vo.setLogin_id(staff.getLoginId());
			vo.setMobile(staff.getMobile());
			vo.setIdcard(staff.getIdcard());
			if (staff.getManager() != null)
				vo.setManager(staff.getManager());
			if (staff.getWorkinglife() != null)
				vo.setWorklife(staff.getWorkinglife().toString());
			if (staff.getWorkingtime() != null)
				vo.setWorkingtime(StringHelper.getSystime(
						ViewConstant.PAGE_DATE_FORMAT_DAY, staff
								.getWorkingtime().getTime()));

			vo.setOrgid(org.getId().toString());

			vo.setOrgname(org.getOrganizationName());

			// porg
			if (org.getPraentOrganizationId() != null) {
				CommonOrganization porg = orgService.findByid(org
						.getPraentOrganizationId().toString());
				vo.setPorgid(porg.getId().toString());
				vo.setPorgname(porg.getOrganizationName());
			}
			// other..
			vo.setPswd(staff.getPassword());
			vo.setStaffNo(staff.getStaffNo());
			vo.setUserid(staff.getId().toString());
			vo.setStaffName(staff.getStaffName());
			vo.setStatusName(status.getStatusName());
			vo.setBankInformation(staff.getBankInformation());
			res.add(vo);
		}

		return res;
	}

	public List<StaffVO> listVORole(Integer roleid, int curPage, int pageSize,
			String orderField, String direction) {
		String hql = getHqlByRoleId(roleid);
		List<Object[]> l = commonDao.getEntitiesByPropertiesWithHql(hql,
				curPage, pageSize);
		List<StaffVO> res = new ArrayList<StaffVO>();

		for (int i = 0; i < l.size(); i++) {
			StaffVO vo = new StaffVO();

			Object[] obj = (Object[]) l.get(i);
			CommonStaff staff = (CommonStaff) obj[0];
			CommonStaffStatus status = (CommonStaffStatus) obj[1];
			CommonOrganization org = (CommonOrganization) obj[2];
			CommonStaffRoleRelation role = (CommonStaffRoleRelation) obj[3];

			vo.setStaff(staff);
			vo.setOrg(org);
			vo.setRole(role);
			vo.setStatus(status);

			vo.setCreateTime(StringHelper.getSystime(
					ViewConstant.PAGE_DATE_FORMAT_DAY, staff.getCreateTime()
							.getTime()));
			vo.setEmail(staff.getEmail());
			vo.setLogin_id(staff.getLoginId());
			vo.setMobile(staff.getMobile());
			vo.setIdcard(staff.getIdcard());
			if (staff.getManager() != null)
				vo.setManager(staff.getManager());
			if (staff.getWorkinglife() != null)
				vo.setWorklife(staff.getWorkinglife().toString());
			if (staff.getWorkingtime() != null)
				vo.setWorkingtime(StringHelper.getSystime(
						ViewConstant.PAGE_DATE_FORMAT_DAY, staff
								.getWorkingtime().getTime()));

			vo.setOrgid(org.getId().toString());

			vo.setOrgname(org.getOrganizationName());

			// porg
			if (org.getPraentOrganizationId() != null) {
				CommonOrganization porg = orgService.findByid(org
						.getPraentOrganizationId().toString());
				vo.setPorgid(porg.getId().toString());
				vo.setPorgname(porg.getOrganizationName());
			}
			// other..
			vo.setPswd(staff.getPassword());
			vo.setStaffNo(staff.getStaffNo());
			vo.setUserid(staff.getId().toString());
			vo.setStaffName(staff.getStaffName());
			vo.setStatusName(status.getStatusName());
			vo.setBankInformation(staff.getBankInformation());
			res.add(vo);
		}

		return res;
	}

	private String getHqlByRoleIdAndOrgid(Integer roleid, Integer orgid) {
		StringBuffer sb = new StringBuffer();
		sb.append("from CommonStaff staff,CommonStaffStatus status,CommonOrganization org,CommonStaffRoleRelation role where");
		sb.append(" staff.id=role.staffId and org.id=staff.organizationId");
		sb.append(" and staff.statusId=status.id");
		sb.append(" and role.roleId=").append(roleid);
		sb.append(" and staff.organizationId=").append(orgid);
		return sb.toString();
	}

	private String getHqlByRoleId(Integer roleid) {
		StringBuffer sb = new StringBuffer();
		sb.append("from CommonStaff staff,CommonStaffStatus status,CommonOrganization org,CommonStaffRoleRelation role where");
		sb.append(" staff.id=role.staffId and org.id=staff.organizationId");
		sb.append(" and staff.statusId=status.id");
		sb.append(" and role.roleId=").append(roleid);
		return sb.toString();
	}

	private String getHqlByLeadStaffId(Integer leadstaffid, String orderField,
			String direction) {
		StringBuffer sb = new StringBuffer();
		sb.append("from CommonStaff a,CommonStaffStatus b,CommonOrganization c where");
		sb.append(" c.id=a.organizationId");
		sb.append(" and a.statusId=b.id");
		sb.append(" and a.leadStaffId=").append(leadstaffid);
		if (!StringHelper.isNull(orderField) && !StringHelper.isNull(direction)) {
			sb.append(" order by " + orderField + " " + direction);
		}
		return sb.toString();
	}

	// 中文注释
	private String getHql(String leaderStaffid, String loginid,
			String staffName, String orgid, String orgname, String sTime,
			String eTime, String statusid, String isManager,
			Integer isDirector, String orderField, String direction) {
		StringBuffer sb = new StringBuffer();
		sb.append(" from CommonStaff a,CommonStaffStatus b,CommonOrganization c where a.statusId=b.id and a.organizationId=c.id ");
		if (!StringHelper.isNull(staffName) || !StringHelper.isNull(staffName)
				|| (!StringHelper.isNull(orgid))
				|| (!StringHelper.isNull(sTime) && !StringHelper.isNull(eTime))
				|| !StringHelper.isNull(statusid)
				|| !StringHelper.isNull(orgname)
				|| !StringHelper.isNull(leaderStaffid)) {

			if (!StringHelper.isNull(loginid)) {
				sb.append(" and a.loginId like'%");
				sb.append(loginid);
				sb.append("%'");
			}

			if (!StringHelper.isNull(staffName)) {
				sb.append(" and a.staffName like'%");
				sb.append(staffName);
				sb.append("%'");
			}
			if (!StringHelper.isNull(orgid)) {
				sb.append(" and a.organizationId=");
				sb.append(orgid);
			}
			if (!StringHelper.isNull(sTime) && !StringHelper.isNull(eTime)) {
				sb.append(" and a.createTime>=DATE_FORMAT('");
				sb.append(sTime);
				sb.append(" 00:00:00','%Y-%m-%d %H:%i:%s')");
				sb.append(" and a.createTime<=DATE_FORMAT('");
				sb.append(eTime);
				sb.append(" 23:59:59','%Y-%m-%d %H:%i:%s')");
			}
			if (!StringHelper.isNull(statusid) && !statusid.equals("all")) {
				sb.append(" and a.statusId=");
				sb.append(statusid);
			}
			if (!StringHelper.isNull(orgname)) {
				sb.append(" and c.organizationName like '%");
				sb.append(orgname);
				sb.append("%'");
			}
			if (!StringHelper.isNull(isManager)) {
				sb.append(" and a.manager=").append(isManager);
			}
			if (isDirector != null && isDirector.intValue() > 0) {
				sb.append(" and a.director=").append(isDirector);
			}
			if(!StringHelper.isNull(leaderStaffid)){
				sb.append(" and a.leadStaffId=");
				sb.append(leaderStaffid);
			}
		}
		if (!StringHelper.isNull(orderField) && !StringHelper.isNull(direction)) {
			sb.append(" order by " + orderField + " " + direction);
		}
		return sb.toString();
	}

	/**
	 * 不做物理删除,只把状态设置为离职
	 * 
	 * @param id
	 */
	public void del(String id) {
		CommonStaff po = this.dao.findById(new Integer(id));
		po.setStatusId(3);
		this.dao.merge(po);
		// this.dao.delete(po);
	}

	/**
	 * 变更状态
	 * 
	 * @param id
	 * @param status
	 */
	public void modifyStatus(String id, String status) {
		CommonStaff po = this.dao.findById(new Integer(id));
		po.setStatusId(new Integer(status));
		this.dao.merge(po);
	}

	/**
	 * 改密码：0-成功<br>
	 * 1-当前登录用户信息错误<br>
	 * 2-旧密码不正确<br>
	 * 
	 * @param currentStaff
	 * @param oldPwd
	 * @param newPwd
	 * @return int
	 */
	public int changePwd(CommonStaff currentStaff, String oldPwd, String newPwd) {
		if (currentStaff == null) {
			return 1;
		}
		CommonStaff old = new CommonStaff();
		old.setLoginId(currentStaff.getLoginId());
		old.setPassword(oldPwd);

		String n = SecureUtil.getEncodePswd(old);

		if (!currentStaff.getPassword().equals(n)) {
			return 2;
		}

		currentStaff.setPassword(newPwd);

		n = SecureUtil.getEncodePswd(currentStaff);

		currentStaff.setPassword(n);
		this.dao.merge(currentStaff);
		return 0;
	}

	/**
	 * 根据登陆名、密码和员工类型 查询
	 * 
	 * @param loginID
	 * @param pwd
	 * @param type
	 */
	public String getHqlByLoginInfo(String loginID, String pwd, String type) {
		StringBuffer sb = new StringBuffer();
		sb.append("from CommonStaff staff,CommonStaffStatus status,CommonOrganization org where");
		sb.append(" org.id=staff.organizationId");
		sb.append(" and staff.statusId=status.id");
		sb.append(" and staff.loginId='").append(loginID);
		sb.append("' and staff.password='").append(pwd);
		sb.append("'");
		return sb.toString();
	}

	/**
	 * 根据登陆名、密码和员工类型 查询
	 * 
	 * @param loginID
	 * @param pwd
	 * @param type
	 */
	public int findByLoginInfo(String loginID, String pwd, String type) {
		if (!type.equals("washer") && !type.equals("agent"))
			return -4;

		String hql = getHqlByLoginInfo(loginID, pwd, type);
		List<Object[]> l = commonDao.getEntitiesByPropertiesWithHql(hql, 1, 1);
		if (l == null || l.size() == 0)
			return -1;
		Object[] obj = l.get(0);
		if (obj == null || obj.length == 0)
			return -1;

		CommonStaff staff = (CommonStaff) obj[0];
		CommonStaffStatus staffStatus = (CommonStaffStatus) obj[1];
		CommonOrganization org = (CommonOrganization) obj[2];

		if (type.equals("washer") && org.getId() != 6) {
			return -1;
		}
		if (type.equals("agent") && org.getId() != 5) {
			return -1;
		}
		if (staffStatus.getStatusName().equals("冻结"))
			return -2;// 冻结
		if (staffStatus.getStatusName().equals("删除"))
			return -3;// 删除

		return staff.getId();
	}

	public CommonStaff statusIsNomal(String washId) {

		StringBuffer sb = new StringBuffer();
		sb.append("from CommonStaff staff,CommonStaffStatus status where");
		sb.append("  staff.statusId=status.id ");
		sb.append(" and staff.id=" + Integer.parseInt(washId));

		List<Object[]> l = commonDao.getEntitiesByPropertiesWithHql(
				sb.toString(), 1, 1);
		if (l == null || l.size() == 0)
			return null;

		Object[] obj = l.get(0);
		CommonStaff staff = (CommonStaff) obj[0];
		CommonStaffStatus staffStatus = (CommonStaffStatus) obj[1];
		if (staffStatus.getStatusName().equals("删除")
				|| staffStatus.getStatusName().equals("冻结")) {
			return null;
		}
		return staff;
	}

	public static void main(String[] args) {
		// log.debug(new StaffService().getByLoginInfo("xcga",
		// "d897704222ac7c3f80746e4edd5eeec8", "washer"));
	}

	public List<StaffVO> findByOrganizationId(int organizationId, int curPage,
			int pageSize, String orderField, String orderDirection) {
		String hql = getHqlByOrganizationId(organizationId, orderField,
				orderDirection);
		List<Object[]> l = commonDao.getEntitiesByPropertiesWithHql(hql,
				curPage, pageSize);

		List<StaffVO> res = new ArrayList<StaffVO>();

		for (int i = 0; i < l.size(); i++) {
			StaffVO vo = new StaffVO();

			Object[] obj = (Object[]) l.get(i);
			CommonStaff staff = (CommonStaff) obj[0];
			CommonStaffStatus status = (CommonStaffStatus) obj[1];
			CommonOrganization org = (CommonOrganization) obj[2];

			vo.setStaff(staff);
			vo.setOrg(org);
			vo.setStatus(status);
			vo.setCreateTime(StringHelper.getSystime(
					ViewConstant.PAGE_DATE_FORMAT_DAY, staff.getCreateTime()
							.getTime()));
			vo.setEmail(staff.getEmail());
			vo.setLogin_id(staff.getLoginId());
			vo.setMobile(staff.getMobile());
			vo.setIdcard(staff.getIdcard());
			if (staff.getManager() != null)
				vo.setManager(staff.getManager());
			if (staff.getWorkinglife() != null)
				vo.setWorklife(staff.getWorkinglife().toString());
			if (staff.getWorkingtime() != null)
				vo.setWorkingtime(StringHelper.getSystime(
						ViewConstant.PAGE_DATE_FORMAT_DAY, staff
								.getWorkingtime().getTime()));

			vo.setOrgid(org.getId().toString());

			vo.setOrgname(org.getOrganizationName());

			// porg
			if (org.getPraentOrganizationId() != null) {
				CommonOrganization porg = orgService.findByid(org
						.getPraentOrganizationId().toString());
				vo.setPorgid(porg.getId().toString());
				vo.setPorgname(porg.getOrganizationName());
			}
			// other..
			vo.setPswd(staff.getPassword());
			vo.setStaffNo(staff.getStaffNo());
			vo.setUserid(staff.getId().toString());
			vo.setStaffName(staff.getStaffName());
			vo.setStatusName(status.getStatusName());
			vo.setBankInformation(staff.getBankInformation());
			res.add(vo);
		}

		return res;
	}

	private String getHqlByOrganizationId(Integer organizationId,
			String orderField, String orderDirection) {
		StringBuffer sb = new StringBuffer();
		sb.append("from CommonStaff a,CommonStaffStatus b,CommonOrganization c where");
		sb.append(" c.id=a.organizationId");
		sb.append(" and a.statusId=b.id");
		sb.append(" and a.organizationId=").append(organizationId);
		if (!StringHelper.isNull(orderField)
				&& !StringHelper.isNull(orderDirection)) {
			sb.append(" order by " + orderField + " " + orderDirection);
		}
		log.debug(sb.toString());
		return sb.toString();
	}

	public int getCountByOrganizationId(Integer organizationId,
			String orderField, String direction) {
		String hql = "select count(*)"
				+ getHqlByOrganizationId(organizationId, orderField, direction);
		return this.commonDao.getNumberOfEntitiesWithHql(hql);
	}
}
