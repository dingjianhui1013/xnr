/**
 * 2014年2月2日 下午5:06:34
 */
package com.xnradmin.core.filter;


import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cntinker.util.StringHelper;
import com.xnradmin.core.service.StaffService;
import com.xnradmin.core.util.SpringBase;
import com.xnradmin.constant.SessionConstant;
import com.xnradmin.po.CommonStaff;

/**
 * @author: bin_liu
 */
public class DirFileter implements Filter{
    private static String c;

    static{
        try{
            c = SpringBase.getCfg().getValueByName("server.properties",
                    "http.disable.dir");
        }catch(IOException e){
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException{

    }

    @Override
    public void destroy(){
        // TODO Auto-generated method stub

    }

    @Override
    public void doFilter(ServletRequest req,ServletResponse res,
            FilterChain chain) throws IOException,ServletException{
        HttpServletRequest hreq = (HttpServletRequest) req;
        HttpServletResponse hres = (HttpServletResponse) res;

        String uri = hreq.getRequestURI().trim().toLowerCase();

        String[] dirs = StringHelper.splitStr(c,",");
        for(String e : dirs){
            if(uri.startsWith(e.toLowerCase())){
                if(!isLogin(hreq)){
                    //hres.getWriter().print("error");
                    hres.sendRedirect("/");
                    return;
                    
                }
            }
        }


        chain.doFilter(req,res);
    }

    private boolean isLogin(HttpServletRequest request){

        HttpSession session = request.getSession();
        String loginid = null;
        if(session == null){
            return false;
        }else{
            Object obj = session
                    .getAttribute(SessionConstant.SESSION_LOGIN_STAFF);
            if(obj == null)
                return false;
            else
                loginid = obj.toString();
        }

        if(StringHelper.isNull(loginid))
            return false;

        StaffService service = (StaffService) SpringBase.getCtx().getBean(
                "StaffService");

        CommonStaff staff = service.findByLoginId(loginid);
        
//        if(staff == null || staff.getOrganizationId() == null
//                || staff.getOrganizationId().intValue() != 3)
        //账号为正常状态
        if(staff==null || staff.getStatusId().intValue()!=1)
            return false;

        return true;
    }
}
