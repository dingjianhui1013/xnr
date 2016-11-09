/**
 * 2013-7-17 下午2:10:05
 */
package com.xnradmin.core.util;


import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.xml.DOMConfigurator;

import com.cntinker.util.FileHelper;
import com.cntinker.util.StringHelper;
import com.xnradmin.constant.EnvConstant;

/**
 * @autohr: bin_liu
 */
public class Log4jUtil{

    private static String log4jFile;

    static{
        init();
    }

    public static void reloadConfig(){
        reloadConfig(log4jFile);
    }

    public static void reloadConfig(String file){
        DOMConfigurator.configure(file);
    }

    public static void init(){

        if(log4jFile == null || log4jFile.trim().length() <= 0){
            if(StringHelper.isNull(System
                    .getProperty(EnvConstant.XICHEADMIN_HOME))){
                SpringBase.initSpring();
            }

            log4jFile = SpringBase.getCfg().getWorklassPath() + "log4j.xml";

        }
        // System.out.println("logpath: "
        // + System.getProperty("smsplatform_logpath"));
        DOMConfigurator.configureAndWatch(log4jFile,60 * 1000);
    }

    public static Log getLog(String logName){
        return LogFactory.getLog(logName);
    }

    public static String getLog4jFile(){
        return log4jFile;
    }

    public static void addLog(String loggerName,String fileName){

    }

    private static void loadLog4jContent() throws FileNotFoundException,
            IOException{
        String[] lines = FileHelper.getLine(log4jFile);
        for(String e : lines){
            System.out.println(e);
        }
    }

    public static void main(String[] args) throws Exception{
        System.out.println(" :::::::::: "
                + System.getProperty(EnvConstant.XICHEADMIN_HOME));

        loadLog4jContent();
    }

}
