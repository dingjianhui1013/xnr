package com.xnradmin.po.wx.connect;


public class WXfInit {
	public static final String TOKEN = "WHWWEIXINTOKEN";
	public static final String APPID = "wx89fafeca214c267b";
	public static final String ENCODINGAESKEY = "bjR366VQla3cpPEnDG9icjMW2h4XEUyeFvsW6x3Pvc1";
	public static final String APPSECRET = "9b7f4e8d331f1ecdc6ca740cabc73a09";
	public static final String SERVICEURL = "http%3a%2f%2fweixin.robustsoft.cn";
	public static final String SERVICEURLW = "http://weixin.robustsoft.cn";
	
	
	private static String key = "8gqOoPuyk1a9X9EAS33MzLC4mbTCNQOn";

    // 微信支付分配的商户号ID（开通公众号的微信支付功能之后可以获取到）
    private static String mchID = "1419647002";

    // 受理模式下给子商户分配的子商户号
    private static String subMchID = "";

    // HTTPS证书的本地路径
    private static String certLocalPath = "";

    // HTTPS证书密码，默认密码等于商户号MCHID
    private static String certPassword = "";

    // 是否使用异步线程的方式来上报API测速，默认为异步模式
    private static boolean useThreadToDoReport = true;

    // 机器IP
    private static String ip = "120.76.72.195";
    
    //类型
    private static String tradeType = "NATIVE";

    //  统一下单 for APP
    public static String UNIFIED_ORDER = "https://api.mch.weixin.qq.com/pay/unifiedorder";
    
    //通知 路径
    public static String NOTIFY_URL = "http://weixin.robustsoft.cn/xnr/front/weixinPay/wechatNotifyURL.action";
    
    //查询 订单
    public static String QUERY_URL =  "https://api.mch.weixin.qq.com/pay/orderquery";
    
    public static String HttpsRequestClassName = "com.tencent.common.HttpsRequest";
    
    public static boolean isUseThreadToDoReport() {
        return useThreadToDoReport;
    }

    public static void setUseThreadToDoReport(boolean useThreadToDoReport) {
        WXfInit.useThreadToDoReport = useThreadToDoReport;
    }

    public static void setKey(String key) {
        WXfInit.key = key;
    }

    public static void setMchID(String mchID) {
        WXfInit.mchID = mchID;
    }

    public static void setSubMchID(String subMchID) {
        WXfInit.subMchID = subMchID;
    }

    public static void setCertLocalPath(String certLocalPath) {
        WXfInit.certLocalPath = certLocalPath;
    }

    public static void setCertPassword(String certPassword) {
        WXfInit.certPassword = certPassword;
    }

    public static void setIp(String ip) {
        WXfInit.ip = ip;
    }

    public static String getKey() {
        return key;
    }

    public static String getMchid() {
        return mchID;
    }

    public static String getSubMchid() {
        return subMchID;
    }

    public static String getCertLocalPath() {
        return certLocalPath;
    }

    public static String getCertPassword() {
        return certPassword;
    }

    public static String getIP() {
        return ip;
    }

    public static void setHttpsRequestClassName(String name) {
        HttpsRequestClassName = name;
    }

    public static String getTradeType() {
        return tradeType;
    }

    public static void setTradeType(String tradeType) {
        WXfInit.tradeType = tradeType;
    }
}
