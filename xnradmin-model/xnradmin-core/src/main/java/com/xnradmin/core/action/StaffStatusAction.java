/**
 * 2012-5-14 上午6:42:08
 */
package com.xnradmin.core.action;


import java.io.IOException;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.cntinker.util.StringHelper;
import com.xnradmin.core.service.StaffStatusService;
import com.xnradmin.constant.AjaxResult;
import com.xnradmin.constant.StrutsResMSG;
import com.xnradmin.po.CommonStaffStatus;

/**
 * @autohr: bin_liu
 */
@Controller
@Scope("prototype")
@Namespace("/page/staffStatus")
@ParentPackage("json-default")
public class StaffStatusAction extends ParentAction{

    @Autowired
    private StaffStatusService service;

    @Override
    public boolean isPublic(){
        return false;
    };

    static Log log = LogFactory.getLog(StaffStatusAction.class);

    /**
     * 跳转到信息页
     * 
     * @return
     */
    @Action(value = "info", results = {
            @Result(name = StrutsResMSG.SUCCESS, location = "/staffstatus/info.jsp"),
            @Result(name = StrutsResMSG.FAILED, location = "/staffstatus/info.jsp")})
    public String info(){

        // 设置排序
        setSort();

        this.voList = service.listVO(searchName,pageNum,numPerPage,orderField,
                orderDirection);
        this.totalCount = service.getCountByPorgid(searchName);

        return StrutsResMSG.SUCCESS;
    }

    /**
     * 设置排序相关项
     */
    private void setSort(){
        if(!StringHelper.isNull(orderField)){
            if(orderField.equals("id")){
                this.idsort = orderDirection;
            }
        }else{
            this.idsort = "asc";
        }
    }

    /**
     * 带信息到修改页
     * 
     * @return String
     */
    @Action(value = "modifyinfo", results = {
            @Result(name = StrutsResMSG.SUCCESS, location = "/staffstatus/modify.jsp"),
            @Result(name = StrutsResMSG.FAILED, location = "/staffstatus/modify.jsp")})
    public String modifyinfo(){

        CommonStaffStatus po = service.findByid(queryid);
        this.queryname = po.getStatusName();

        return StrutsResMSG.SUCCESS;
    }

    /**
     * 更新对象接口
     * 
     * @return String
     * @throws JSONException
     * @throws IOException
     */
    @Action(value = "modify", results = {@Result(name = StrutsResMSG.SUCCESS, type = "plainText")})
    public String modify() throws JSONException,IOException{

        CommonStaffStatus po = new CommonStaffStatus();
        po.setId(new Integer(queryid));

        po.setStatusName(queryname);

        int res = this.service.modify(po);
        if(res == 1){
            super.error("已存在相同的状态名称");
        }else{
            super.success(null,AjaxResult.CALL_BACK_TYPE_CLOSECURRENT,
                    "staffStatusInfo",null);
        }

        return null;
    }

    /**
     * 保存对象接口
     * 
     * @return String
     * @throws IOException
     * @throws JSONException
     */
    @Action(value = "add", results = {@Result(name = StrutsResMSG.SUCCESS, type = "plainText")})
    public String save() throws IOException,JSONException{

        CommonStaffStatus po = new CommonStaffStatus();
        po.setStatusName(queryname);

        int res = service.save(po);
        if(res == 1)
            super.error("员工状态名称已存在");
        else
            super.success(null,AjaxResult.CALL_BACK_TYPE_CLOSECURRENT,
                    "staffStatusInfo",null);

        return null;
    }

    /**
     * 外部调用，获取所有部门信息
     * 
     * @return String
     * @throws IOException
     */
    @Action(value = "all", results = {@Result(name = StrutsResMSG.SUCCESS, type = "plainText")})
    public String all() throws IOException{
        super.toJsonArray(service.listAll());
        return null;
    }

    private String searchName;

    private String idsort;

    private String queryid;

    private String queryname;

    private List<CommonStaffStatus> voList;

    public String getSearchName(){
        return searchName;
    }

    public void setSearchName(String searchName){
        this.searchName = searchName;
    }

    public String getIdsort(){
        return idsort;
    }

    public void setIdsort(String idsort){
        this.idsort = idsort;
    }

    public String getQueryid(){
        return queryid;
    }

    public void setQueryid(String queryid){
        this.queryid = queryid;
    }

    public String getQueryname(){
        return queryname;
    }

    public void setQueryname(String queryname){
        this.queryname = queryname;
    }

    public List<CommonStaffStatus> getVoList(){
        return voList;
    }

    public void setVoList(List<CommonStaffStatus> voList){
        this.voList = voList;
    }
}
