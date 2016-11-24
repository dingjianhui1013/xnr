package com.xnradmin.po.wx.connect;

public final class WXActionUrl {
	public static final String OUTPLAN_SAVEF = WXurl.WX_CLICK_URL.replace("APPID", WXfInit.APPID).replace("REDIRECT_URI","http%3a%2f%2fweixin.robustsoft.cn%2fxnr%2fpage%2fwx%2fpersonalCenter%2flistF.action").replace("SCOPE", "snsapi_base")+"";
}
