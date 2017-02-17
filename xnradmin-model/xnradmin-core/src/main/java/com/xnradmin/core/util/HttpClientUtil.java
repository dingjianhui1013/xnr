package com.xnradmin.core.util;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.log4j.Logger;

public class HttpClientUtil {
	private static Logger log = Logger.getLogger(HttpClientUtil.class);
	private static MultiThreadedHttpConnectionManager conmgr = null;
	private static HttpClient client = null;

	static {
		conmgr = new MultiThreadedHttpConnectionManager();
		conmgr.getParams().setDefaultMaxConnectionsPerHost(200);
		conmgr.getParams().setMaxTotalConnections(350);
		client = new HttpClient(conmgr);
	}

	public static HttpClient getClient() {
		return client;
	}

	public static String post(String url, Map<String, String> params) {
		PostMethod postMethod = new PostMethod(url);
		if (params != null) {
			Iterator<Entry<String, String>> p = params.entrySet().iterator();
			Entry<String, String> e;
			while (p.hasNext()) {
				e = p.next();
				postMethod.addParameter(e.getKey(), e.getValue());
			}
		}
		String result = "";
		try {
			int status = client.executeMethod(postMethod);
			if (status == HttpStatus.SC_OK) {
				result = postMethod.getResponseBodyAsString();
			} else {
				log.debug(url + ",POST," + status + ","
						+ postMethod.getStatusLine().getReasonPhrase());
			}
		} catch (HttpException e) {
			log.debug(url + ",POST " + e);
		} catch (IOException e) {
			log.debug(url + ",POST " + e);
		} finally {
			postMethod.releaseConnection();
		}
		return result;
	}

	public static String postXml(String url, String xmlInfo,
			boolean hashReturnXml) {
		PostMethod postMethod = new PostMethod(url);
		String result = "";
		if (xmlInfo != null) {
			try {
				postMethod.setRequestEntity(new StringRequestEntity(xmlInfo,
						"text/xml", "utf-8"));
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			postMethod.setRequestBody(xmlInfo);// 这里添加xml字符串

		}
		try {
			int status = client.executeMethod(postMethod);
			if (status == HttpStatus.SC_OK) {
				if (!hashReturnXml) {
					result = postMethod.getResponseBodyAsString();
				} else {
					BufferedInputStream bis = new BufferedInputStream(
							postMethod.getResponseBodyAsStream());
					byte[] bytes = new byte[1024];
					ByteArrayOutputStream bos = new ByteArrayOutputStream();
					int count = 0;
					while ((count = bis.read(bytes)) != -1) {
						bos.write(bytes, 0, count);
					}
					byte[] strByte = bos.toByteArray();
					result = new String(strByte, 0, strByte.length, "utf-8");
					bos.close();
					bis.close();
				}

			} else {
				log.debug(url + ",POST," + status + ","
						+ postMethod.getStatusLine().getReasonPhrase());
			}
		} catch (HttpException e) {
			log.debug(url + ",POST " + e);
		} catch (IOException e) {
			log.debug(url + ",POST " + e);
		} finally {
			postMethod.releaseConnection();
		}
		return result;
	}

	public static String get(String url,boolean hashReturnXml) {
		GetMethod getMethod = new GetMethod(url);
		String result = "";
		try {
			int status = client.executeMethod(getMethod);
			if (status == HttpStatus.SC_OK) {
				if (!hashReturnXml) {
					result = getMethod.getResponseBodyAsString();
				} else {
					BufferedInputStream bis = new BufferedInputStream(
							getMethod.getResponseBodyAsStream());
					byte[] bytes = new byte[1024];
					ByteArrayOutputStream bos = new ByteArrayOutputStream();
					int count = 0;
					while ((count = bis.read(bytes)) != -1) {
						bos.write(bytes, 0, count);
					}
					byte[] strByte = bos.toByteArray();
					result = new String(strByte, 0, strByte.length, "utf-8");
					bos.close();
					bis.close();
				}
				
			} else {
				log.debug(url + ",GET," + status + ","
						+ getMethod.getStatusLine().getReasonPhrase());
			}
		} catch (HttpException e) {
			log.debug(url + ",GET " + e);
		} catch (IOException e) {
			log.debug(url + ",GET " + e);
		} finally {
			getMethod.releaseConnection();
		}
		return result;
	}

}
