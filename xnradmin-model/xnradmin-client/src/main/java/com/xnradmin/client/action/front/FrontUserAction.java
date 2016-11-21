package com.xnradmin.client.action.front;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.xnradmin.client.service.front.FrontUserService;
import com.xnradmin.constant.StrutsResMSG;
import com.xnradmin.core.action.ParentAction;
import com.xnradmin.po.front.FrontUser;

@Controller
@Scope("prototype")
@Namespace("/front")
@ParentPackage("json-default")
public class FrontUserAction extends ParentAction {
	
    private final static Logger LOG = LoggerFactory.getLogger(FrontUserAction.class);
    
    @Autowired
    private FrontUserService frontUserService;
    
    private String status;//状态
    
    
    
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 登录
     * @return
     */
	@Action(value = "login",results = { @Result(name = StrutsResMSG.SUCCESS, location = "/front/login.jsp") })
    public String login() {
        return StrutsResMSG.SUCCESS;
    }
	
	/**
	 * 注册 注册成功跳到登录页面,失败则停留在原页面
	 * @return
	 */
	@Action(value = "register",results = { @Result(name = StrutsResMSG.SUCCESS, location = "/front/login.jsp"),@Result(name = StrutsResMSG.FAILED, location = "/front/register.jsp") })
    public String register(FrontUser frontUser) {
        return StrutsResMSG.SUCCESS;
    }
	
	/**
	 * 验证phone的唯一
	 * @param frontUser
	 * @return
	 */
	@Action(value = "validatePhone",results = {@Result(name = StrutsResMSG.SUCCESS, type = "json")})
    public String validatePhone(String phone) {
            LOG.info("验证phone的唯一");
            final boolean flag = frontUserService.isExistloginName(phone);
            //true 存在  false 不存在
            if (flag) {
                this.status ="-1";
            } else {
                this.status = "1";
            }
        return StrutsResMSG.SUCCESS;
    }

    @Override
    public boolean isPublic() {
        // TODO Auto-generated method stub
        return false;
    }
	
	
}
