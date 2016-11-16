/**
 *2014年8月23日 下午4:23:14
 */
package com.xnradmin.client.test;

import java.io.IOException;
import java.net.MalformedURLException;

import org.xml.sax.SAXException;

import com.cntinker.util.HttpHelper;
import com.cntinker.util.HttpunitHelper;
import com.cntinker.util.StringHelper;
import com.meterware.httpunit.WebConversation;
import com.meterware.httpunit.WebResponse;

/**
 * @author: liubin
 * 
 */
public class TestWXClientUser {

	private static void test(String openid, String accessToken)
			throws MalformedURLException, IOException, SAXException {
		if (StringHelper.isNull(openid)) {
			openid = "ohmUCj1104_W4N7h3c1EcJ3Qlsf0";
		}
		if(StringHelper.isNull(accessToken)){
			accessToken = "1VdGNsismecKiQHkUtLBwFYYWGI3MV3LQKWHNJCjQxCn3Ht1NZWtoLByLNJW4BaOd_AVvMBvWsQ9RrtSg-f8qw";
		}
		String url = "https://api.weixin.qq.com/cgi-bin/user/info?access_token="
				+ accessToken + "&openid=" + openid + "&lang=zh_CN";
		System.out.println("测试utf8: "+HttpHelper.sendGet(url, "UTF-8"));

		System.out.println("测试gbk: "+HttpHelper.sendGet(url, "GBK"));

		HttpunitHelper webLogin = new HttpunitHelper();
		WebConversation wc = webLogin.init("", true);

		WebResponse r = wc.getResponse(url);
		System.out.println("测试source : "+r.getText());
		
		System.out.println("测试utf-8 : "+new String(r.getText().getBytes("GBK"),"UTF-8"));
	}
	
	
	private static void test2() throws IOException{
		String openid = "ohmUCj4TInEzxJ7ScS5V5iP5r33g";
		String accessToken = "LdQGle1NF7DuOJ-19KTfV6_mvMoQWOtyY6i-m0lLURjUrjiDLZVSul2UJxH3FaIb6RdV7u69p63945iiHYpS7wZTIvdCmqyFAlqDPAB5FPA";
		String url = "https://api.weixin.qq.com/cgi-bin/user/info?access_token="
				+ accessToken + "&openid=" + openid + "&lang=zh_CN";
		String[] res = HttpHelper.sendGetReturnHeaderRes(url,"utf-8");
		System.out.println(res[0]);
		System.out.println(res[1]);
	}
	
	private static void test3() throws IOException{
		String url = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=dTDVRp7qIWG-IOhw-mUzk3EgZqw2HWXlHv-lnFkjxJJxN-h-8rXN6E2pu8o7IOR04riBwwX6CusWT2aJPqroPw&openid=ohmUCj1104_W4N7h3c1EcJ3Qlsf0&lang=zh_CN";
		String[] res = HttpHelper.sendGetReturnHeaderRes(url,"utf-8");
		System.out.println(res[0]);
		System.out.println(res[1]);
	}

	public static void main(String[] args) throws Exception {
		//System.out.println("args[0]=openid args[1]=accessToken");
		//test(args[0], args[1]);
		//test(null,null);
		test2();
		//test3();
	}
}
