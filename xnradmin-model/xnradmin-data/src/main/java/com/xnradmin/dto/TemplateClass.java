/**
 * 2013-7-22 上午4:47:27
 */
package com.xnradmin.dto;


import java.io.Serializable;
import java.util.List;

import com.xnradmin.po.system.SysClass;
import com.xnradmin.po.system.SysVar;

/**
 * @autohr: bin_liu
 */
public class TemplateClass implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private List<SysVar> vars;

    private SysClass syscls;

    public List<SysVar> getVars(){
        return vars;
    }

    public void setVars(List<SysVar> vars){
        this.vars = vars;
    }

    public SysClass getSyscls(){
        return syscls;
    }

    public void setSyscls(SysClass syscls){
        this.syscls = syscls;
    }

}
