/**
 * 2012-10-24 上午11:17:12
 */
package com.xnradmin.core.action;


import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.cntinker.util.StringHelper;
import com.xnradmin.core.service.PhoneLocalService;
import com.xnradmin.constant.StrutsResMSG;
import com.xnradmin.po.PhoneLocal;
import com.xnradmin.vo.PhoneLocalVO;

/**
 * @author: liubin
 */
@Controller
@Scope("prototype")
@Namespace("/page/phoneLocal")
@ParentPackage("json-default")
public class PhoneLocalAction extends ParentAction{
    @Override
    public boolean isPublic(){
        // TODO Auto-generated method stub
        return false;
    }

    static Log log = LogFactory.getLog(PhoneLocalAction.class);

    @Autowired
    private PhoneLocalService localService;

    @Action(value = "lookup", results = {@Result(name = StrutsResMSG.SUCCESS, location = "/local/lookup.jsp")})
    public String lookup(){
        // 设置页面信息
        List<String> l = localService.getAllLocalList();
        voList = new ArrayList<PhoneLocalVO>();
        String[] array = null;
        if(!StringHelper.isNull(check)){
            if(check.indexOf(",") > -1)
                array = StringHelper.splitStr(check,",");
            else
                array = new String[]{check};
        }
        for(String e : l){
            PhoneLocalVO v = new PhoneLocalVO();
            v.setLocal(e);
            if(!StringHelper.isNull(check)){
                for(String ef : array){
                    if(ef.equals(e))
                        v.setIsChecked(1);
                }
            }else{
                v.setIsChecked(0);
            }
            voList.add(v);
        }
        return StrutsResMSG.SUCCESS;
    }

    @Action(value = "localLookup", results = {@Result(name = StrutsResMSG.SUCCESS, location = "/local/localLookup.jsp")})
    public String localLookup(){
        List<PhoneLocal> l = localService.getAllLocalPO();
        voList = new ArrayList<PhoneLocalVO>();
        for(PhoneLocal e : l){
            PhoneLocalVO v = new PhoneLocalVO();
            v.setLocal(e.getLocal());
            v.setId(e.getId().toString());
            voList.add(v);
        }
        return StrutsResMSG.SUCCESS;
    }

    @Action(value = "citylookup", results = {@Result(name = StrutsResMSG.SUCCESS, location = "/local/citylookup.jsp")})
    public String citylookup(){
        // 设置页面信息
        List<String> l = localService.getAllLocalCodeByLocalId(local);
        voList = new ArrayList<PhoneLocalVO>();
        String[] array = null;
        if(!StringHelper.isNull(check)){
            if(check.indexOf(",") > -1)
                array = StringHelper.splitStr(check,",");
            else
                array = new String[]{check};
        }
        for(String e : l){
            PhoneLocalVO v = new PhoneLocalVO();
            v.setLocalcode(e);
            if(!StringHelper.isNull(check)){
                for(String ef : array){
                    if(ef.equals(e))
                        v.setIsChecked(1);
                }
            }else{
                v.setIsChecked(0);
            }
            voList.add(v);
        }
        return StrutsResMSG.SUCCESS;
    }

    @Action(value = "view", results = {@Result(name = StrutsResMSG.SUCCESS, location = "/local/view.jsp")})
    public String view(){
        voList = new ArrayList<PhoneLocalVO>();
        String[] array = null;
        if(!StringHelper.isNull(check)){
            if(check.indexOf(",") > -1)
                array = StringHelper.splitStr(check,",");
            else
                array = new String[]{check};
        }
        for(String e : array){
            PhoneLocalVO v = new PhoneLocalVO();
            v.setLocal(e);
            voList.add(v);
        }
        return StrutsResMSG.SUCCESS;
    }

    private String check;

    private List<PhoneLocalVO> voList;

    private String local;

    public List<PhoneLocalVO> getVoList(){
        return voList;
    }

    public void setVoList(List<PhoneLocalVO> voList){
        this.voList = voList;
    }

    public String getCheck(){
        return check;
    }

    public void setCheck(String check){
        this.check = check;
    }

    public String getLocal(){
        return local;
    }

    public void setLocal(String local){
        this.local = local;
    }

}
