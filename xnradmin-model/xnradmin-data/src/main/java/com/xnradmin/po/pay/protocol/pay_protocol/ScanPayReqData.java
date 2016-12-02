package com.xnradmin.po.pay.protocol.pay_protocol;



import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import com.xnradmin.core.pay.wxpay.util.RandomStringGenerator;
import com.xnradmin.core.pay.wxpay.util.Signature;
import com.xnradmin.po.wx.connect.WXfInit;

/**
 * User: rizenguo Date: 2014/10/25 Time: 13:54
 */
public class ScanPayReqData {

    private String appid = "";//公众账号ID
    private String mch_id = "";//商户号
    private String nonce_str = "";//随机字符串
    private String sign = "";//签名
    private String body = "";//商品描述
    private String detail="";//商品详情
    private String out_trade_no = ""; //商户订单号  
    private  int total_fee =  0;//总金额
    private String spbill_create_ip = "";//终端IP   
    private String fee_type = "CNY";//货币类型
    private String notify_url="";//通知地址
    private String trade_type = "";//交易类型 JSAPI，NATIVE，APP， 
    private String openid="";//用户标识
    private String device_info="WEB";//设备号  PC网页或公众号内支付请传"WEB"
    private String time_start;//交易开始时间
    private String time_expire;//交易结束时间
    
    
    public ScanPayReqData(String body, String detail, String out_trade_no,  int total_fee,
            String spbill_create_ip,String trade_type,String openid,String time_start,String time_expire) {
        super();
        this.appid = WXfInit.APPID;
        this.mch_id = WXfInit.getMchid(); 
        this.notify_url = WXfInit.NOTIFY_URL;
        this.nonce_str = RandomStringGenerator.getRandomStringByLength(32);     
        this.body = body;
        this.detail = detail;
        this.out_trade_no = out_trade_no;       
        this.total_fee = total_fee;
        this.spbill_create_ip = spbill_create_ip;
        this.openid=openid;
        this.time_start=time_start;
        this.time_expire=time_expire;
        this.trade_type=trade_type;
         //根据API给的签名规则进行签名
         //把签名数据设置到Sign这个属性中
        this.sign = Signature.getSign(toMap());
        
    }
    

    public String getDevice_info() {
        return device_info;
    }


    public void setDevice_info(String device_info) {
        this.device_info = device_info;
    }


    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getMch_id() {
        return mch_id;
    }

    public void setMch_id(String mch_id) {
        this.mch_id = mch_id;
    }

    public String getNonce_str() {
        return nonce_str;
    }

    public void setNonce_str(String nonce_str) {
        this.nonce_str = nonce_str;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

   

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

   
    public int getTotal_fee() {
        return total_fee;
    }

    public void setTotal_fee(int total_fee) {
        this.total_fee = total_fee;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getSpbill_create_ip() {
        return spbill_create_ip;
    }

    public void setSpbill_create_ip(String spbill_create_ip) {
        this.spbill_create_ip = spbill_create_ip;
    }

    public String getFee_type() {
        return fee_type;
    }

    public void setFee_type(String fee_type) {
        this.fee_type = fee_type;
    }

    public String getNotify_url() {
        return notify_url;
    }

    public void setNotify_url(String notify_url) {
        this.notify_url = notify_url;
    }

    public String getTrade_type() {
        return trade_type;
    }

    public void setTrade_type(String trade_type) {
        this.trade_type = trade_type;
    }

    public String getTime_start() {
        return time_start;
    }

    public void setTime_start(String time_start) {
        this.time_start = time_start;
    }


    public String getTime_expire() {
        return time_expire;
    }


    public void setTime_expire(String time_expire) {
        this.time_expire = time_expire;
    }


    public Map<String,Object> toMap(){
        Map<String,Object> map = new HashMap<String, Object>();
        Field[] fields = this.getClass().getDeclaredFields();
        for (Field field : fields) {
            Object obj;
            try {
                obj = field.get(this);
                if(obj!=null){
                    map.put(field.getName(), obj);
                }
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return map;
    }

}
