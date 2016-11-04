/**
 * 2012-10-28 下午11:43:11
 */
package com.xnradmin.vo;


import java.io.Serializable;

/**
 * @author: bin_liu
 */
public class PhoneLocalVO implements Serializable{

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    // private PhoneLocal localvo;
    //
    // private PhoneLocal localcodevo;

    private String serno;

    private int isChecked;

    private String local;

    private String localcode;

    private String id;

    private String maxCount;

    public String getLocal(){
        return local;
    }

    public void setLocal(String local){
        this.local = local;
    }

    public int getIsChecked(){
        return isChecked;
    }

    public void setIsChecked(int isChecked){
        this.isChecked = isChecked;
    }

    // public PhoneLocal getLocalvo(){
    // return localvo;
    // }
    //
    // public void setLocalvo(PhoneLocal localvo){
    // this.localvo = localvo;
    // }
    //
    // public PhoneLocal getLocalcodevo(){
    // return localcodevo;
    // }
    //
    // public void setLocalcodevo(PhoneLocal localcodevo){
    // this.localcodevo = localcodevo;
    // }

    public String getSerno(){
        return serno;
    }

    public void setSerno(String serno){
        this.serno = serno;
    }

    public String getMaxCount(){
        return maxCount;
    }

    public void setMaxCount(String maxCount){
        this.maxCount = maxCount;
    }

    public String getLocalcode(){
        return localcode;
    }

    public void setLocalcode(String localcode){
        this.localcode = localcode;
    }

    public String getId(){
        return id;
    }

    public void setId(String id){
        this.id = id;
    }
}
