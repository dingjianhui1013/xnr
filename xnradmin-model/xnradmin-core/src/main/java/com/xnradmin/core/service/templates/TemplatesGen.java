/**
 * 2013-7-22 上午3:59:03
 */
package com.xnradmin.core.service.templates;


import com.cntinker.util.StringHelper;
import com.xnradmin.constant.templates.SysGenKeyword;

/**
 * @autohr: bin_liu
 */
public class TemplatesGen{

    private TemplatesClassService classService;

    private String command;

    public TemplatesGen(TemplatesClassService classService,String command){
        this.classService = classService;
        this.command = command;
    }

    public String getRes(){
        if(this.command.equals(SysGenKeyword.SYSTIME)){
            return StringHelper.getSystime();
        }
        return "";
    }

    private String genSetGet(){
        String className = classService.getTemplateClass().getSyscls()
                .getClassName();
        
        return "";
    }
    
    public static void main(String[] args)throws Exception{
        
    }

}
