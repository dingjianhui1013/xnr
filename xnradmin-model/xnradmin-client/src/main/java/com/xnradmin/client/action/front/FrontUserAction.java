package com.xnradmin.client.action.front;

import java.net.URLDecoder;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.cntinker.security.MD5Encoder;
import com.cntinker.util.CookieHelper;
import com.xnradmin.client.service.front.FrontUserService;
import com.xnradmin.constant.StrutsResMSG;
import com.xnradmin.core.service.mall.order.ShoppingCartService;
import com.xnradmin.po.front.FrontUser;

@Controller
@Scope("prototype")
@Namespace("/front")
@ParentPackage("json-default")
public class FrontUserAction {
	
    private final static Logger LOG = LoggerFactory.getLogger(FrontUserAction.class);
    
    @Autowired
    private FrontUserService frontUserService;
    @Autowired
	private ShoppingCartService shoppingCartService;
    
    private String status;//状态
    private String phone;//注册手机验证
    private FrontUser frontUser;//注册信息
    private String userName;//登陆
    private String password;//登陆
    private String message;//登陆信息
    private FrontUser user;//修改用户信息
    private String yuanshimima;//验证原始密码
    private String valideCode;//验证验证码
    
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

	public String getValideCode() {
		return valideCode;
	}

	public void setValideCode(String valideCode) {
		this.valideCode = valideCode;
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
			//cookie购物车入库
			Cookie cookieByName = CookieHelper.getCookieByName(ServletActionContext.getRequest(), "cart");
			if(null != cookieByName&&!cookieByName.getValue().toString().equals("null")){
				String cookieCart = cookieByName.getValue();
				if(null!=cookieCart&&!"".equals(cookieCart)){
					cookieCart = URLDecoder.decode(cookieCart);
//					JSONArray json = JSONArray.fromObject(cookieCart);
//					Set<String> idSet = new HashSet<String>();
//					for (int i = 0; i < json.size(); i++) {
//						JSONObject job = json.getJSONObject(i);
//						if(!job.get("goodsId").toString().equals("null"))
//						{
//							idSet.add(job.get("goodsId").toString());
//						}
//						if(!job.get("comboId").toString().equals("null"))
//						{
//							idSet.add(job.get("comboId").toString());
//						}
//					}
//					JSONArray carsaArray = new JSONArray();
//					JSONObject carts = new JSONObject();
//					Object[] idsObject = idSet.toArray();
//					for (int i = 0; i < idsObject.length; i++) {
//						int goodsCount = 0;
//						String cookieId = "";
//						String goodsId = "";
//						String price = "";
//						String comboId = "";
//						for (int j = 0; j < json.size(); j++) {
//							JSONObject job = json.getJSONObject(j);
//							if(!job.get("goodsId").toString().equals("null"))
//							{
//								if(idsObject[i].toString().equals(job.get("goodsId").toString())){
//									goodsCount++;
//									cookieId=job.get("cookieId").toString();
//									goodsId = idsObject[i].toString();
//									price = job.get("price").toString();
//								}
//							}else {
//								goodsId="null";
//							}
//							if(!job.get("comboId").toString().equals("null"))
//							{
//								if(idsObject[i].toString().equals(job.get("comboId").toString())){
//									goodsCount++;
//									cookieId=job.get("cookieId").toString();
//									comboId = idsObject[i].toString();
//									price = job.get("price").toString();
//								}
//							}else {
//								comboId ="null";
//							}
//						}
//						carts.put("cookieId",cookieId);
//						carts.put("goodsId", goodsId);
//						carts.put("comboId", comboId);
//						carts.put("goodsCount", goodsCount);
//						carts.put("price", price);
//						carsaArray.add(carts);
//					}
					shoppingCartService.saveCookieCart(cookieCart,frontUser);
				}
			}
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
		frontUser.setType("1");//暂时不需要审核。
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
	//验证图型验证码
	@Action(value = "validateCode" ,results={@Result(name = StrutsResMSG.SUCCESS, type="json")})
	public String validateCode()
	{
        HttpServletResponse resp = ServletActionContext.getResponse();
        HttpServletRequest req = ServletActionContext.getRequest();
        // session中的名字为:validateCode
        try {
			Object attribute = req.getSession().getAttribute("validateCode");
			if(attribute.toString()==null||"".equals(attribute.toString()))
	        {
	        	status="0";
	        }else
	        {
	        	  if(valideCode.equals(attribute.toString()))
	        	  {
	        		  status="1";
	        	  }else
	        	  {
	        		  status ="0";
	        	  }
	        }
			return StrutsResMSG.SUCCESS;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			status="0";
			return StrutsResMSG.SUCCESS;
		}
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
