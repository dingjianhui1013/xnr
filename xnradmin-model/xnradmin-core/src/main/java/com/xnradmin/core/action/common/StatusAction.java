/**
 * 2014年2月8日 下午3:05:33
 */
package com.xnradmin.core.action.common;


import java.io.IOException;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.xnradmin.core.action.ParentAction;
import com.xnradmin.core.action.ScriptAction;
import com.xnradmin.core.service.common.status.StatusService;
import com.xnradmin.constant.AjaxResult;
import com.xnradmin.constant.StrutsResMSG;
import com.xnradmin.po.common.status.Status;

/**
 * @author: bin_liu
 */
@Controller
@Scope("prototype")
@Namespace("/page/status")
public class StatusAction extends ParentAction{

    @Autowired
    private StatusService service;

    static Log log = LogFactory.getLog(StatusAction.class);

    @Action(value = "info", results = {@Result(name = StrutsResMSG.SUCCESS, location = "/common/status/info.jsp")})
    public String info() throws IOException{
        setPageInfo();
        return StrutsResMSG.SUCCESS;
    }

    @Action(value = "modifyInfo", results = {@Result(name = StrutsResMSG.SUCCESS, location = "/common/status/modify.jsp")})
    public String modifyInfo(){
        this.query = service.findByid(query.getId().toString());
        return StrutsResMSG.SUCCESS;
    }
    
    @Action(value = "addInfo", results = {@Result(name = StrutsResMSG.SUCCESS, location = "/common/status/add.jsp")})
    public String addInfo(){
        return StrutsResMSG.SUCCESS;
    }

    @Action(value = "add", results = {@Result(name = StrutsResMSG.SUCCESS, type = "plainText")})
    public String add() throws IOException{
        int res = service.save(query);        
        super.success(null,AjaxResult.CALL_BACK_TYPE_CLOSECURRENT,"statusInfo",
                null);
        return null;
    }
    
    @Action(value = "modify", results = {@Result(name = StrutsResMSG.SUCCESS, type = "plainText")})
    public String modify() throws IOException{
        int res = service.modify(query);        
        super.success(null,AjaxResult.CALL_BACK_TYPE_CLOSECURRENT,"statusInfo",
                null);
        return null;
    }
    
    @Action(value = "del", results = {@Result(name = StrutsResMSG.SUCCESS, type = "plainText")})
    public String del() throws IOException{
        try{
            service.del(query);
        }catch(Exception e){
            super.error("其他错误: " + e.getMessage());
            return null;
        }
        super.success(null,null,"statusInfo",null);
        return null;
    }

    private void setPageInfo(){
        this.voList = service.listVO(query,getPageNum(),getNumPerPage());
        this.totalCount = service.getCount(query);
    }

    private List<Status> voList;

    private Status query;

    @Override
    public boolean isPublic(){
        // TODO Auto-generated method stub
        return false;
    }

    public Status getQuery(){
        return query;
    }

    public void setQuery(Status query){
        this.query = query;
    }

    public List<Status> getVoList(){
        return voList;
    }

    public void setVoList(List<Status> voList){
        this.voList = voList;
    }

}
