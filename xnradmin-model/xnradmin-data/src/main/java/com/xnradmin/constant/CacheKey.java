/**
 * 2013-7-13 上午3:26:54
 */
package com.xnradmin.constant;


/**
 * @autohr: bin_liu
 */
public class CacheKey{

    /**
     * 订单信息缓存<br>
     * value:Map<Long,com.xnradmin.po.Order>
     */
    public static final String ORDER_INFO = "ORDER_INFO_";

    /**
     * 号段缓存<br>
     * key: key+号段7位识别<br>
     * value:com.xnradmin.po.PhoneLocal<br>
     */
    public static final String PHONE_LOCAL = "PHONE_LOCAL_";

    /**
     * 脚本缓存KEY:className<br>
     * value:long(lastModifyTimestamp)
     */
    public static final String SCRIPT_PLATFORM = "SCRIPT_PLATFORM_";

    /**
     * 所有已缓存的脚本KEY索引<br>
     * value:List<String>
     */
    public static final String SCRIPT_KEY_LIST = "SCRIPT_KEY_LIST";
    
    /**
     * 页面，逻辑用脚本缓存<br>
     * KEY:className<br>
     * value:com.xnradmin.vo.client.script.ScriptVO<br>
     * 
     */
    public static final String SCRIPT_VO_CLASSNAME = "SCRIPT_VO_CLASSNAME_";
    /**
     * 页面，下发短信缓存<br>
     * KEY:msgID<br>
     * value:com.xnradmin.po.sms.SmsRecord<br>
     * 
     */
    public static final String SMS_INFO_KEY = "SMS_INFO_KEY_";
    
}
