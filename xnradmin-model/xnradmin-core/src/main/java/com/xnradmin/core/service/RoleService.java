/**
 *2012-5-12 上午9:52:01
 */
package com.xnradmin.core.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cntinker.util.StringHelper;
import com.xnradmin.core.dao.CommonDAO;
import com.xnradmin.core.dao.CommonRoleDAO;
import com.xnradmin.core.dao.CommonRolePermissionRelationDAO;
import com.xnradmin.core.dao.CommonStaffRoleRelationDAO;
import com.xnradmin.po.CommonMenu;
import com.xnradmin.po.CommonPermission;
import com.xnradmin.po.CommonPermissionMenuRelation;
import com.xnradmin.po.CommonRole;
import com.xnradmin.po.CommonRolePermissionRelation;
import com.xnradmin.po.CommonStaff;
import com.xnradmin.po.CommonStaffRoleRelation;

/**
 * @autohr: bin_liu
 * 
 */
@Service("RoleService")
public class RoleService {
	static Log log = LogFactory.getLog(RoleService.class);
	@Autowired
	private CommonRoleDAO dao;
	
	@Autowired
    private CommonDAO commonDao;

	@Autowired
	private CommonRolePermissionRelationDAO prdao;
	@Autowired
	private CommonStaffRoleRelationDAO srdao;

	/**
	 * 
	 * @param po
	 * @return
	 */
	public int save(CommonRole po) {
		if (this.dao.findByRoleName(po.getRoleName()).size() > 0) {
			return 1;
		}
		dao.save(po);
		return 0;
	}

	public int saveStaffRoleRelation(CommonStaffRoleRelation csr) {
		srdao.save(csr);
		return 0;

	}

	public List<Object[]> getUrl2Role() {
		return prdao.getUrl2Role();
	}

	/**
	 * 
	 * @param po
	 * @return int
	 */
	public int modify(CommonRole po) {
		List l = this.dao.findByRoleName(po.getRoleName());
		if (l.size() > 0) {
			CommonRole old = this.dao.findById(po.getId());
			if (!old.getRoleName().equals(po.getRoleName()))
				return 1;
		}
		this.dao.merge(po);
		return 0;
	}

	public CommonRole findByid(String id) {
		return dao.findById(new Integer(id));
	}

	public CommonRole findRoleByCode(String id) {
		return dao.findById(new Integer(id));
	}

	public List<CommonRole> getValidRoles() {
		return dao.findEnabledRoles();
	}

	public List<CommonRole> getRoles4User(CommonStaff user) {
		return dao.getRoles4User(user);
	}

	public List<Integer> getRoleId4User(Integer userid) {
		return srdao.getRoleId4User(userid);
	}

	/**
	 * @param porgid
	 * @return int
	 */
	public int getCount(String roleName) {
		String hql = "select count(*) from CommonRole";
		if (!StringHelper.isNull(roleName)) {
			hql = hql + " where ROLE_NAME like '%" + roleName + "%'";
		}
		return this.commonDao.getNumberOfEntitiesWithHql(hql);
	}

	/**
	 * @param orgTypeName
	 * @param curPage
	 * @param pageSize
	 * @param orderField
	 * @param direction
	 * @return List<CommonRole>
	 */
	public List<CommonRole> listVO(String roleName, int curPage, int pageSize,
			String orderField, String direction) {
		String hql = "from CommonRole";
		if (!StringHelper.isNull(roleName)) {
			hql = hql + " where ROLE_NAME like '%" + roleName + "%'";
		}
		if (!StringHelper.isNull(orderField) && !StringHelper.isNull(direction)) {
			hql = hql + " order by " + orderField + " " + direction;
		}else{
			hql+=" order by id desc";
		}

		List<CommonRole> l = commonDao.getEntitiesByPropertiesWithHql(hql, curPage,
				pageSize);

		return l;

	}

	public int removeRoles4userId(Integer userid) {

		return srdao.removeRoles4userId(userid);
	}

	public List<CommonMenu> findMenuListByRoleid(List<Integer> roleid,
			String menuLevel, String parentMenuId) {

		String in = "";
		for (int i = 0; i < roleid.size(); i++) {
			in += roleid.get(i);
			if (i < roleid.size() - 1)
				in += ",";
		}

		StringBuffer sb = new StringBuffer();
		sb.append(" from CommonRole a,CommonRolePermissionRelation b,CommonPermission c,CommonPermissionMenuRelation d,CommonMenu e ");
		sb.append(" where a.id=b.roleId and b.permissionId=c.id and d.permissionId=c.id and d.menuId=e.id ");
		if (!StringHelper.isNull(menuLevel))
			sb.append(" and e.menuLevel=").append(menuLevel);
		if (!StringHelper.isNull(parentMenuId))
			sb.append(" and e.praentMenuId=").append(parentMenuId);
		sb.append(" and a.id in(").append(in).append(")");
		sb.append(" group by e.id");	
		sb.append(" order by e.sortOrder desc");

		List<Object[]> l = commonDao.getEntitiesByPropertiesWithHql(sb.toString(), 0,
				0);
		List<CommonMenu> res = new ArrayList<CommonMenu>();
		for (int i = 0; i < l.size(); i++) {
			Object[] obj = (Object[]) l.get(i);
			CommonRole role = (CommonRole) obj[0];
			CommonRolePermissionRelation rolePRel = (CommonRolePermissionRelation) obj[1];
			CommonPermission per = (CommonPermission) obj[2];
			CommonPermissionMenuRelation perMenuRel = (CommonPermissionMenuRelation) obj[3];
			CommonMenu menu = (CommonMenu) obj[4];
			res.add(menu);
		}
		return res;
	}

}
