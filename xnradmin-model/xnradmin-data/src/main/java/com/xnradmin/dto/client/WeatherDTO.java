/**
 * 2014年2月26日 下午4:44:26
 */
package com.xnradmin.dto.client;

import com.cntinker.util.ReflectHelper;


/**
 * @author: bin_liu
 */
public class WeatherDTO{
    
    //洗车指数
    private String xicheZhishu = "";
    
    //洗车指数详细描述
    private String xicheZhishuDesc = "";
    
    //洗车指数数字
    private String xicheZhishuCode = "";
    
    //限行，暂留
    private String xianxing = "";
    
    //今天最高温度
    private String dayHight = "";
    //今天最低温度
    private String dayLow = "";
    //实时温度
    private String shishiWendu = "";
    
    //查询时间
    private String time = "";
    
    public String toString(){
        String res = "";
        try{
            res = ReflectHelper.makeToString(this);
        }catch(Exception e){
            e.printStackTrace();
        }
        return res;
    }

    public String getXicheZhishu(){
        return xicheZhishu;
    }

    public void setXicheZhishu(String xicheZhishu){
        this.xicheZhishu = xicheZhishu;
    }

    public String getXianxing(){
        return xianxing;
    }

    public void setXianxing(String xianxing){
        this.xianxing = xianxing;
    }

   
    public String getShishiWendu(){
        return shishiWendu;
    }

    public void setShishiWendu(String shishiWendu){
        this.shishiWendu = shishiWendu;
    }

    public String getDayHight(){
        return dayHight;
    }

    public void setDayHight(String dayHight){
        this.dayHight = dayHight;
    }

    public String getDayLow(){
        return dayLow;
    }

    public void setDayLow(String dayLow){
        this.dayLow = dayLow;
    }

    public String getTime(){
        return time;
    }

    public void setTime(String time){
        this.time = time;
    }

    public String getXicheZhishuCode(){
        return xicheZhishuCode;
    }

    public void setXicheZhishuCode(String xicheZhishuCode){
        this.xicheZhishuCode = xicheZhishuCode;
    }

    public String getXicheZhishuDesc(){
        return xicheZhishuDesc;
    }

    public void setXicheZhishuDesc(String xicheZhishuDesc){
        this.xicheZhishuDesc = xicheZhishuDesc;
    }
}
