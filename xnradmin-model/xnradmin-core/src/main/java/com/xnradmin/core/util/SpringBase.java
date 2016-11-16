/**
 * 2013-3-20 上午1:45:51
 */
package com.xnradmin.core.util;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.cntinker.util.ConfigHelper;
import com.cntinker.util.HttpHelper;
import com.cntinker.util.StringHelper;
import com.xnradmin.constant.EnvConstant;
import com.xnradmin.dto.ScriptDTO;

/**
 * @autohr: bin_liu
 */
public class SpringBase{

    private static ApplicationContext ctx;
    
    private static ConfigHelper c;
    
    private static ScriptDTO scriptDto;

    public static ApplicationContext getCtx(){
        return ctx;
    }

    public static void setCtx(ApplicationContext ctx){
        ctx = ctx;
    }

    static{
        initSpring();
        initScriptDir();
    }

    private static void initEnv(){

        c = new ConfigHelper(new HttpHelper());
        String xnradmin_home = c.getCfgPath().substring(0,c.getCfgPath().indexOf("WEB-INF")-1);
        System.setProperty(EnvConstant.XICHEADMIN_HOME,xnradmin_home);
        System.out.println("xnradmin home: "+xnradmin_home);
        // String logPath = smsplattform_home + SystemHelper.getFileSeparator()
        // + "logs" + SystemHelper.getFileSeparator()
        // + StringHelper.getSystime("yyyy")
        // + SystemHelper.getFileSeparator()
        // + StringHelper.getSystime("MM")
        // + SystemHelper.getFileSeparator()
        // + StringHelper.getSystime("dd");
        // FileHelper.mkdir(logPath);
        // System.setProperty("smsplatform_logpath",logPath);
    }

    public static void initSpring(){
        try{
            String osName = System.getProperty("os.name");

            String file = "";

            System.out.println("osName: " + osName);
            initEnv();
            file = c.getCfgPath().substring(0,c.getCfgPath().indexOf("/conf"))
                    + "/spring/applicationContext-main.xml";
            
            System.out.println("path:"+file);
            if(osName.indexOf("Linux") > -1 || osName.indexOf("Mac") > -1)
                file = "/" + file;

            System.out.println("[Server start Load Spring Config]: " + file);
            ctx = new FileSystemXmlApplicationContext(file);            
            scriptDto = (ScriptDTO) SpringBase.getCtx().getBean("ScriptDTO");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public static void initScriptDir(){
        
        if(scriptDto == null || StringHelper.isNull(scriptDto.getScriptDIR())
                || scriptDto.getScriptDIR().indexOf("{") > -1){
            String p = SpringBase.getCfg().getCfgPath();
            p = p.substring(0,p.indexOf("/conf"))
                    + "/script";
            String osName = System.getProperty("os.name");
            if(osName.indexOf("Linux") > -1 || osName.indexOf("Mac") > -1)
                p = "/" + p;
            scriptDto.setScriptDIR(p);
            
        }        
    }
    
    public static ScriptDTO getScriptDTO(){
        return scriptDto;
    }
    
    public static ConfigHelper getCfg(){
        return c;
    }
}
