/**
 * 2014年2月3日 下午2:16:52
 */
package com.xnradmin.core.action;


import java.io.IOException;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.codehaus.commons.compiler.CompileException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.cntinker.util.StringHelper;
import com.xnradmin.core.service.ScriptService;
import com.xnradmin.core.service.common.status.StatusService;
import com.xnradmin.core.util.SpringBase;
import com.xnradmin.constant.AjaxResult;
import com.xnradmin.constant.StrutsResMSG;
import com.xnradmin.dto.ScriptDTO;
import com.xnradmin.po.CommonScript;
import com.xnradmin.po.common.status.Status;
import com.xnradmin.vo.client.script.ScriptVO;

/**
 * @author: bin_liu
 */
@Controller
@Scope("prototype")
@Namespace("/page/script")
public class ScriptAction extends ParentAction{

    @Autowired
    private ScriptService service;

    @Autowired
    private StatusService statusService;

    static Log log = LogFactory.getLog(ScriptAction.class);

    @Action(value = "info", results = {@Result(name = StrutsResMSG.SUCCESS, location = "/client/script/info.jsp")})
    public String info() throws IOException{
        setPageInfo();

        return StrutsResMSG.SUCCESS;
    }

    @Action(value = "addinfo", results = {@Result(name = StrutsResMSG.SUCCESS, location = "/client/script/add_dialog.jsp")})
    public String addInfo(){
        this.statusList = statusService.find(CommonScript.class);
        this.scriptdto = SpringBase.getScriptDTO();
        return StrutsResMSG.SUCCESS;
    }

    @Action(value = "modifyInfo", results = {@Result(name = StrutsResMSG.SUCCESS, location = "/client/script/modify.jsp")})
    public String modifyInfo() throws CompileException,ClassNotFoundException,
            IOException{
        this.statusList = statusService.find(CommonScript.class);
        this.scriptdto = SpringBase.getScriptDTO();
        CommonScript po = service.findByIDfromDB(query.getScriptpo().getId());
        this.query = service.findByClassName(po.getClassName());
        return StrutsResMSG.SUCCESS;
    }

    @Action(value = "add", results = {@Result(name = StrutsResMSG.SUCCESS, type = "plainText")})
    public String add() throws IOException{
        int res = -99;
        query.getScriptpo().setStatusDesc(
                statusService.findByid(
                        query.getScriptpo().getStatus().toString())
                        .getStatusName());
        try{
            res = service.save(query.getScriptpo(),super.getCurrentStaff());
        }catch(Exception e){
            super.error("其他错误: " + e.getMessage());
            return null;
        }
        if(res == -1)
            super.error("相同的类名已存在");
        else if(res == -2)
            super.error("编译异常");
        else if(res == -3)
            super.error("class未找到异常");
        else if(res == -4)
            super.error("文件IO异常");
        else if(res == -5)
            super.error("脚本缓存异常");
        else
            super.success(null,AjaxResult.CALL_BACK_TYPE_CLOSECURRENT,
                    "scriptInfo",null);
        return null;
    }

    @Action(value = "modify", results = {@Result(name = StrutsResMSG.SUCCESS, type = "plainText")})
    public String modify() throws IOException{
        int res = -99;
        try{
            query.getScriptpo().setStatusDesc(
                    statusService.findByid(
                            query.getScriptpo().getStatus().toString())
                            .getStatusName());
            res = service.modify(query.getScriptpo(),super.getCurrentStaff());
        }catch(Exception e){
            super.error("其他错误: " + e.getMessage());
            return null;
        }
        if(res == -1)
            super.error("记录不存在");
        else if(res == -2)
            super.error("编译异常");
        else if(res == -3)
            super.error("class未找到异常");
        else if(res == -4)
            super.error("文件IO异常");
        else if(res == -5)
            super.error("已有相同的类名:" + query.getScriptpo().getClassName());
        else
            super.success(null,AjaxResult.CALL_BACK_TYPE_CLOSECURRENT,
                    "scriptInfo",null);
        return null;
    }

    @Action(value = "del", results = {@Result(name = StrutsResMSG.SUCCESS, type = "plainText")})
    public String del() throws IOException{
        try{
            service.del(query.getScriptpo().getId(),super.getCurrentStaff());
        }catch(Exception e){
            super.error("其他错误: " + e.getMessage());
            return null;
        }
        super.success(null,null,"scriptInfo",null);
        return null;
    }

    @Action(value = "ref", results = {@Result(name = StrutsResMSG.SUCCESS, type = "plainText")})
    public String ref() throws IOException{
        try{
            service.ref(query.getScriptpo().getId());
        }catch(Exception e){
            super.error("其他错误: " + e.getMessage());
            return null;
        }
        super.success("重载成功",null,"scriptInfo",null);
        return null;
    }

    @Action(value = "refAll", results = {@Result(name = StrutsResMSG.SUCCESS, type = "plainText")})
    public String refAll() throws IOException{
        try{
            service.refAll();
        }catch(Exception e){
            super.error("其他错误: " + e.getMessage());
            return null;
        }
        super.success("重载成功",null,"scriptInfo",null);
        return null;
    }

    @Action(value = "lookup", results = {@Result(name = StrutsResMSG.SUCCESS, location = "/client/script/lookup.jsp")})
    public String lookup() throws IOException{
        // setPageInfo();
        ScriptVO vo = new ScriptVO();
        vo.setScriptClassName(className);
        CommonScript po = new CommonScript();
        po.setClassName(className);

        try{
            this.voList = service.listVO(vo,getPageNum(),getNumPerPage());
        }catch(CompileException | ClassNotFoundException | IOException e){
            super.error("error: " + e.getMessage());
            e.printStackTrace();
        }
        this.totalCount = service.getCount(vo);
        return StrutsResMSG.SUCCESS;
    }

    private void setPageInfo() throws IOException{
        try{
            if(query != null && query.getScriptpo() != null
                    && !StringHelper.isNull(query.getScriptpo().getClassName()))
                query.setScriptClassName(query.getScriptpo().getClassName());
            this.voList = service.listVO(query,getPageNum(),getNumPerPage());
        }catch(CompileException | ClassNotFoundException | IOException e){
            super.error("error: " + e.getMessage());
            e.printStackTrace();
        }
        this.totalCount = service.getCount(query);
    }

    @Override
    public boolean isPublic(){
        // TODO Auto-generated method stub
        return false;
    }

    private ScriptVO query;

    private List<ScriptVO> voList;

    private ScriptDTO scriptdto;

    private List<Status> statusList;

    private String className;

    public String getClassName(){
        return className;
    }

    public void setClassName(String className){
        this.className = className;
    }

    public ScriptDTO getScriptdto(){
        return scriptdto;
    }

    public void setScriptdto(ScriptDTO scriptdto){
        this.scriptdto = scriptdto;
    }

    public List<ScriptVO> getVoList(){
        return voList;
    }

    public void setVoList(List<ScriptVO> voList){
        this.voList = voList;
    }

    public ScriptVO getQuery(){
        return query;
    }

    public void setQuery(ScriptVO query){
        this.query = query;
    }

    public List<Status> getStatusList(){
        return statusList;
    }

    public void setStatusList(List<Status> statusList){
        this.statusList = statusList;
    }

}
