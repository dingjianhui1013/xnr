/**
 * 2012-5-10 下午11:09:51
 */
package com.xnradmin.core.action;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import com.cntinker.util.StringHelper;
import com.xnradmin.core.service.StaffService;
import com.xnradmin.core.util.AjaxUtil;
import com.xnradmin.constant.SessionConstant;
import com.xnradmin.constant.ViewConstant;
import com.xnradmin.po.CommonStaff;

/**
 * @autohr: bin_liu
 */
@ParentPackage("struts-default")
public abstract class ParentAction{

    public abstract boolean isPublic();

    static Log log = LogFactory.getLog(ParentAction.class);

    @Autowired
    private StaffService staffService;

    public ParentAction(){
        if(!isPublic()){
            try{
                processAccess();
            }catch(IOException e){
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    /**
     * 控制访问权限，对于要公开的ACTION，不必调用此方法
     * 
     * @throws IOException
     */
    protected void processAccess() throws IOException{
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();
        HttpSession session = request.getSession();

        Object obj = session.getAttribute(SessionConstant.SESSION_LOGIN_STAFF);

        if(isPublic() == false){

            if(obj == null){
                processTimeout();
                // String path = request.getContextPath();
                // String basePath = request.getScheme() + "://"
                // + request.getServerName() + ":"
                // + request.getServerPort() + path + "/";
                // response.sendRedirect(basePath + "login.jsp");
                // return;
                return;
            }
        }
    }

    private void processTimeout(){
        try{

            ServletActionContext.getResponse().setContentType(
                    "text/html;charset=utf-8");
            PrintWriter out = ServletActionContext.getResponse().getWriter();

            out.print(AjaxUtil.getTimeout("登陆超时，请重新登陆"));
            out.flush();
            out.close();
        }catch(FileNotFoundException e){
            // TODO Auto-generated catch block
            e.printStackTrace();
        }catch(IOException e){
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * JSON格式输出独立对象
     * 
     * @param bean
     * @throws IOException
     */
    protected void toJsonObject(Object bean) throws IOException{
        ServletActionContext.getResponse().setContentType(
                "text/html;charset=utf-8");
        JSONObject obj = new JSONObject(bean);
        PrintWriter out = ServletActionContext.getResponse().getWriter();
        out.print(obj.toString());
        out.flush();
        out.close();
    }

    /**
     * JSON格式输出一个对象集合
     * 
     * @param bean
     * @throws IOException
     */
    protected void toJsonArray(Collection bean) throws IOException{
        ServletActionContext.getResponse().setContentType(
                "text/html;charset=utf-8");
        JSONArray obj = new JSONArray(bean);
        PrintWriter out = ServletActionContext.getResponse().getWriter();
        out.print(obj.toString());
        out.flush();
        out.close();
    }

    /**
     * 返回成功
     * 
     * @param msg
     *            返回消息<br>
     * @param callbackType
     *            指定动作:closeCurrent-关闭当前TAB或dialog <br>
     *            只有callbackType="forward"时需要forwardUrl值<br>
     * @param navTabId
     *            刷新指定的tab框<br>
     * @param forwardUrl
     * @throws IOException
     */
    protected void success(String msg,String callbackType,String navTabId,
            String forwardUrl) throws IOException{
        ServletActionContext.getResponse().setContentType(
                "text/html;charset=utf-8");
        PrintWriter out = ServletActionContext.getResponse().getWriter();
        out.print(AjaxUtil.getSuccess(msg,callbackType,navTabId,forwardUrl));
        out.flush();
        out.close();
    }

    /**
     * 得到当前登录人
     * 
     * @return String
     */
    protected CommonStaff getCurrentStaff(){

        String loginid = null;
        HttpSession session = ServletActionContext.getRequest().getSession();
        if(session == null){
            return null;
        }else{
            Object obj = session
                    .getAttribute(SessionConstant.SESSION_LOGIN_STAFF);
            if(obj == null)
                return null;
            else
                loginid = obj.toString();
        }

        if(StringHelper.isNull(loginid))
            return null;

        return this.staffService.findByLoginId(loginid);
    }

    /**
     * 提示错误
     * 
     * @param msg
     * @throws IOException
     */
    protected void error(String msg) throws IOException{
        ServletActionContext.getResponse().setContentType(
                "text/html;charset=utf-8");
        PrintWriter out = ServletActionContext.getResponse().getWriter();
        out.print(AjaxUtil.getError(msg));
        out.flush();
        out.close();
    }

    /**
     * 提示会话超时，timeout表示session超时，下次点击时跳转到DWZ.loginUrl
     * 
     * @param msg
     * @throws IOException
     */
    protected void timeout(String msg) throws IOException{
        ServletActionContext.getResponse().setContentType(
                "text/html;charset=utf-8");
        PrintWriter out = ServletActionContext.getResponse().getWriter();
        out.print(AjaxUtil.getError(msg));
        out.flush();
        out.close();
    }
    
    private String remoteIp;

    protected int pageNum;

    protected int numPerPage;

    protected String orderField;

    protected String orderDirection;

    protected int totalCount;

    public int getPageNum(){
        if(this.pageNum <= 0)
            return 1;
        else
            return pageNum;
    }

    public void setPageNum(int pageNum){
        if(pageNum <= 0)
            this.pageNum = 1;
        else
            this.pageNum = pageNum;
    }

    public int getNumPerPage(){
        if(this.numPerPage <= 0)
            return ViewConstant.PAGE_DATA_DEFAULT_NUM;
        return numPerPage;
    }

    public void setNumPerPage(int numPerPage){
        if(numPerPage <= 0){
            this.numPerPage = ViewConstant.PAGE_DATA_DEFAULT_NUM;
        }else
            this.numPerPage = numPerPage;
    }

    public String getOrderField(){
        return orderField;
    }

    public void setOrderField(String orderField){
        this.orderField = orderField;
    }

    public String getOrderDirection(){
        return orderDirection;
    }

    public void setOrderDirection(String orderDirection){
        this.orderDirection = orderDirection;
    }

    public int getTotalCount(){
        return totalCount;
    }

    public void setTotalCount(int totalCount){
        this.totalCount = totalCount;
    }

    public String getRemoteIp(){
        HttpServletRequest request = ServletActionContext.getRequest();
        //this.remoteIp = request.getRemoteAddr();
        remoteIp = request.getHeader("x-real-ip");
        if(StringHelper.isNull(remoteIp)){
        	remoteIp = request.getHeader("x-forwarded-for");
        }
        /**
        if(StringHelper.isNull(remoteIp) || remoteIp.equals("127.0.0.1")){
            remoteIp = request.getHeader("x-real-ip");
            if(StringHelper.isNull(remoteIp) || remoteIp.equals("127.0.0.1")){
                remoteIp = request.getHeader("x-forwarded-for");
            }
        }
        */
        return remoteIp;
    }

   

}