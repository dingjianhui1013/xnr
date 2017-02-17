/**
 *2012-4-30 下午11:17:12
 */
package com.xnradmin.vo;


import java.io.Serializable;

/**
 * @autohr: bin_liu
 */
public class MenuVO implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private String id;

    private String pid;

    private String pmenuname;

    private String menuname;

    private String enmenuname;

    private String menulevel;

    private String link;

    private String status;
    
    private String sortOrder;

    public String getId(){
        return id;
    }

    public void setId(String id){
        this.id = id;
    }

    public String getPid(){
        return pid;
    }

    public void setPid(String pid){
        this.pid = pid;
    }

    public String getPmenuname(){
        return pmenuname;
    }

    public void setPmenuname(String pmenuname){
        this.pmenuname = pmenuname;
    }

    public String getMenuname(){
        return menuname;
    }

    public void setMenuname(String menuname){
        this.menuname = menuname;
    }

    public String getEnmenuname(){
        return enmenuname;
    }

    public void setEnmenuname(String enmenuname){
        this.enmenuname = enmenuname;
    }

    public String getMenulevel(){
        return menulevel;
    }

    public void setMenulevel(String menulevel){
        this.menulevel = menulevel;
    }

    public String getLink(){
        return link;
    }

    public void setLink(String link){
        this.link = link;
    }

    public String getStatus(){
        return status;
    }

    public void setStatus(String status){
        this.status = status;
    }

	public String getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}

}
