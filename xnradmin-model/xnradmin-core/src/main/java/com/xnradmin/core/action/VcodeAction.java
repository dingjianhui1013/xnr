/**
 * 2012-5-21 下午3:16:43
 */
package com.xnradmin.core.action;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.cntinker.util.WebImageHelper;
import com.xnradmin.constant.StrutsResMSG;

/**
 * @autohr: bin_liu
 */
@Controller
@Scope("prototype")
@Namespace("/page/vcode")
public class VcodeAction{

    @Action(value = "vcode", results = {@Result(name = StrutsResMSG.SUCCESS, type = "stream", params = {
            "contentType","image/jpeg","Pragma","No-cache","Cache-Control",
            "no-cache","Expires","0L"})})
    public void vcode() throws Exception{
        HttpServletResponse resp = ServletActionContext.getResponse();
        HttpServletRequest req = ServletActionContext.getRequest();
        // session中的名字为:vcode
        WebImageHelper.createNumber2Jpg(req,resp,4);
        // log.debug(req.getSession().getAttribute("vcode"));
    }

}
