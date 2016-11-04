/**
 *2014年3月29日 上午10:12:38
*/
package com.xnradmin.core.servlet;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;

import com.xnradmin.core.util.Log4jUtil;

/**
 * @author: bin_liu
 *
 */
public class UrlAndroidDown extends HttpServlet{

    private Log log = Log4jUtil.getLog("androiddown");    

    public void doPost(HttpServletRequest request,HttpServletResponse response)
            throws ServletException,IOException{
        doGet(request,response);
    }

    public void doGet(HttpServletRequest request,HttpServletResponse response)
            throws ServletException,IOException{
        request.setCharacterEncoding("UTF-8");

        Enumeration enu = request.getHeaderNames();
        StringBuffer sb = new StringBuffer();
        while(enu.hasMoreElements()){
            String key = enu.nextElement().toString();
            String value = request.getHeader(key);
            sb.append(key).append(":").append(value).append("\n");
        }
        log.info(sb.toString());                
        log.info("downflag");
        response.sendRedirect("/down/android.htm");
        
    }
}
