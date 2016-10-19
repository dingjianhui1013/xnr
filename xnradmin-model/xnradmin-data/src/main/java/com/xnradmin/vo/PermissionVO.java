/**
 * 2012-5-15 下午12:01:21
 */
package com.xnradmin.vo;


import java.io.Serializable;

import com.xnradmin.po.CommonMenu;

/**
 * @autohr: bin_liu
 */
public class PermissionVO implements Serializable{

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    private String permissionId;

    private String permissionName;

    private CommonMenu menuList;

    public String getPermissionId(){
        return permissionId;
    }

    public void setPermissionId(String permissionId){
        this.permissionId = permissionId;
    }

    public String getPermissionName(){
        return permissionName;
    }

    public void setPermissionName(String permissionName){
        this.permissionName = permissionName;
    }

    public CommonMenu getMenuList(){
        return menuList;
    }

    public void setMenuList(CommonMenu menuList){
        this.menuList = menuList;
    }

}
