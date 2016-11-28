package com.xnradmin.client.action.front;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.cntinker.security.MD5Encoder;
import com.cntinker.util.CookieHelper;
import com.cntinker.util.StringHelper;
import com.xnradmin.client.service.front.FrontUserService;
import com.xnradmin.constant.AjaxResult;
import com.xnradmin.constant.StrutsResMSG;
import com.xnradmin.core.action.ParentAction;
import com.xnradmin.po.business.BusinessWeight;
import com.xnradmin.po.front.FrontUser;
import com.xnradmin.vo.business.BusinessGoodsVO;

@Controller
@Scope("prototype")
@Namespace("/front")
@ParentPackage("json-default")
public class FrontUserAction {
	
    private final static Logger LOG = LoggerFactory.getLogger(FrontUserAction.class);
    
    @Autowired
    private FrontUserService frontUserService;
    
    private String status;//状态
    private String phone;//注册手机验证
    private FrontUser frontUser;//注册信息
    private String userName;//登陆
    private String password;//登陆
    private String message;//登陆信息
    private FrontUser user;//修改用户信息
    private String yuanshimima;//验证原始密码
    
	public FrontUser getUser() {
		return user;
	}

	public void setUser(FrontUser user) {
		this.user = user;
	}

	public String getYuanshimima() {
		return yuanshimima;
	}

	public void setYuanshimima(String yuanshimima) {
		this.yuanshimima = yuanshimima;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public FrontUser getFrontUser() {
		return frontUser;
	}

	public void setFrontUser(FrontUser frontUser) {
		this.frontUser = frontUser;
	}

	/**
     * 登录
     * @return
     */
	@Action(value = "login",results = { @Result(name = StrutsResMSG.SUCCESS, type="redirect",location = "/front/index.action"),@Result(name = StrutsResMSG.FAILED, location = "/front/login.jsp") })
    public String login() {
		this.password = (MD5Encoder.encode32(this.password));
		FrontUser frontUser = frontUserService.getByPhoneOrEmail(this.userName,this.password);
		if(null== frontUser){
			this.message = "用户名密码错误";
			return StrutsResMSG.FAILED;
		}else if(!"1".equals(frontUser.getType())){
			this.message = "您的账号未审核通过，请联系管理员";
			return StrutsResMSG.FAILED;
		}else{
			HttpSession session = ServletActionContext.getRequest().getSession();
			session.setAttribute("user", frontUser);
//			CookieHelper.addCookie(ServletActionContext.getResponse(), "user", frontUser.getUserName(), 365 * 24 * 60 * 60);
			return StrutsResMSG.SUCCESS;
		}
    }
	/**
     * 退出
     * @return
     */
	@Action(value = "exit",results = { @Result(name = StrutsResMSG.SUCCESS, type="redirect",location = "/front/index.action")})
    public String exit() {
			ServletActionContext.getRequest().getSession().setAttribute("user", null);
//			CookieHelper.addCookie(ServletActionContext.getResponse(), "user", frontUser.getUserName(), 365 * 24 * 60 * 60);
			return StrutsResMSG.SUCCESS;
	}
	/**
	 * 注册 注册成功跳到登录页面,失败则停留在原页面
	 * @return
	 */
	@Action(value = "register",results = { @Result(name = StrutsResMSG.SUCCESS, location = "/front/regSuccess.jsp"),@Result(name = StrutsResMSG.FAILED, location = "/front/register.jsp") })
    public String register() {
		boolean save = frontUserService.save(frontUser);
		if(save){
			return StrutsResMSG.SUCCESS;
		}else{
			return StrutsResMSG.FAILED;
		}
    }
	
	/**
	 * 验证phone的唯一
	 * @param frontUser
	 * @return
	 */
	@Action(value = "validatePhone",results = {@Result(name = StrutsResMSG.SUCCESS, type = "json")})
    public String validatePhone() {
            LOG.info("验证phone的唯一");
            final boolean flag = frontUserService.isExistloginName(this.phone);
            //true 存在  false 不存在
            if (flag) {
                this.status ="-1";
            } else {
                this.status = "1";
            }
        return StrutsResMSG.SUCCESS;
    }
	
	/**
	 * 保存用户信息
	 */
	@Action(value = "saveForm",results = {@Result(name = StrutsResMSG.SUCCESS, type = "redirect",location="/front/personalCenter.action")})
    public String saveForm() {
		
		boolean a = frontUserService.modifyName(user);
		if(a){
			((FrontUser)(ServletActionContext.getRequest().getSession().getAttribute("user"))).setUserName(user.getUserName());
		}
        return StrutsResMSG.SUCCESS;
    }
	/**
	 * 验证原始密码
	 */
	@Action(value = "validateYuanshimima",results = {@Result(name = StrutsResMSG.SUCCESS, type = "json")})
	public String validateYuanshimima(){
		FrontUser u = (FrontUser)(ServletActionContext.getRequest().getSession().getAttribute("user"));
		this.yuanshimima = MD5Encoder.encode32(this.yuanshimima);
		final boolean flag = u.getPassword().equals(this.yuanshimima);
        //true 存在  false 不存在
        if (!flag) {
            this.status ="-1";
        } else {
            this.status = "1";
        }
    return StrutsResMSG.SUCCESS;
	}
	/**
	 *修改密码
	 */
	@Action(value = "savePassword",results = {@Result(name = StrutsResMSG.SUCCESS, type = "redirect",location="/front/personalCenter.action")})
    public String savePassword() {
		frontUserService.modifyPassword(user);
        return StrutsResMSG.SUCCESS;
    }
}
