/**
 * 2012-5-12 下午1:40:05
 */
package com.xnradmin.core.service;


import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xnradmin.core.dao.CommonPermissionDAO;
import com.xnradmin.core.dao.CommonPermissionMenuRelationDAO;
import com.xnradmin.core.dao.CommonRolePermissionRelationDAO;
import com.xnradmin.po.CommonMenu;
import com.xnradmin.po.CommonPermission;
import com.xnradmin.po.CommonPermissionMenuRelation;
import com.xnradmin.po.CommonRole;
import com.xnradmin.po.CommonRolePermissionRelation;

/**
 * @autohr: bin_liu
 */
@Service("PermissionService")
public class PermissionService{

    static Log log = LogFactory.getLog(PermissionService.class);

    @Autowired
    private CommonPermissionDAO dao;

    @Autowired
    private CommonPermissionMenuRelationDAO relationDao;

    @Autowired
    private CommonRolePermissionRelationDAO rolePermissionDao;

    public int save(CommonPermission po){
        this.dao.save(po);
        return 0;
    }

    public int modify(CommonPermission po){
        this.dao.merge(po);
        return 0;
    }

    /**
     * @param role
     * @param menuList
     * @return int
     */
    public int auth(CommonRole role,List<CommonMenu> menuList){
        return 0;
    }

    public List<CommonPermission> findValidPermissions(){
        return this.dao.findValidPermissions();
    }

    public CommonPermission findById(Integer id){
        return this.dao.findById(id);

    }

    public List<Integer> findAuthIdsByMenuId(Integer menuID){
        return this.relationDao.findAuthIdsByMenuId(menuID);

    }

    public List<Integer> findAuthIdsByRoleId(Integer roleid){
        return this.rolePermissionDao.findAuthIdsByRoleId(roleid);

    }

    public int removePermission4MenuId(Integer menuId){
        return this.relationDao.removePermission4MenuId(menuId);
    }

    public int removePermission4RoleId(Integer roleid){
        return this.rolePermissionDao.removePermission4RoleId(roleid);
    }

    public void savePermissionMenuRelation(CommonPermissionMenuRelation cpm){
        this.relationDao.save(cpm);

    }

    public void saveRolePermissionRelation(CommonRolePermissionRelation crp){
        this.rolePermissionDao.save(crp);

    }

}
