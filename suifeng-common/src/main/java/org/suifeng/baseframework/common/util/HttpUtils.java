package org.suifeng.baseframework.common.util;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.net.ssl.SSLContext;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class HttpUtils {

	private static int timeout = 30000;

	public static String httpGet(String url, boolean isHttps) {
		return httpGet(url, timeout, isHttps);
	}

	public static String httpPostJson(String url, String pa, boolean isHttps) {
		return httpPostJson(url, pa, timeout, isHttps);
	}

	public static String httpAjaxPost(String url, HashMap<String, String> pa, boolean isHttps) {
		return httpAjaxPost(url, pa, timeout, isHttps);
	}

	@SuppressWarnings("rawtypes")
	public static String ConvertParams(HashMap<String, String> map) {
		String ret = "";
		List<String> list = new ArrayList<String>();

		if (map != null) {
			Iterator iter = map.entrySet().iterator();
			while (iter.hasNext()) {
				Map.Entry entry = (Map.Entry) iter.next();
				list.add(entry.getKey() + "=" + entry.getValue());
			}
		}

		ret = StringUtils.join(list.toArray(), "&");

		return ret;
	}

	/**
	 * get方法
	 */
	public static String httpGet(String url, int to, boolean isHttps) {
		String ret = "";

		CloseableHttpClient httpclient = null;
		if (!isHttps)
			httpclient = HttpClients.createDefault();
		else
			httpclient = createSSLClientDefault();

		try {
			// 创建httpget.
			HttpGet httpget = new HttpGet(url);
			RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(to).setConnectTimeout(to)
					.setConnectionRequestTimeout(to).setStaleConnectionCheckEnabled(true).build();
			httpget.setConfig(requestConfig);

			// 执行get请求.
			CloseableHttpResponse response = httpclient.execute(httpget);
			try {
				// 获取响应实体
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					ret = EntityUtils.toString(entity, "UTF-8");
				}
			} finally {
				response.close();
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// 关闭连接,释放资源
			try {
				httpclient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return ret;
	}

	@SuppressWarnings("rawtypes")
	public static String httpAjaxPost(String url, HashMap<String, String> map, int to, boolean isHttps) {

		CloseableHttpClient httpclient = null;
		String ret = "";

		if (!isHttps)
			httpclient = HttpClients.createDefault();
		else
			httpclient = createSSLClientDefault();

		try {
			HttpPost httpPost = new HttpPost(url);
			RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(to).setConnectTimeout(to)
					.setConnectionRequestTimeout(to).setStaleConnectionCheckEnabled(true).build();
			httpPost.setConfig(requestConfig);

			ResponseHandler<String> responseHandler = new BasicResponseHandler();

			List<BasicNameValuePair> nvps = new ArrayList<BasicNameValuePair>();
			if (map != null) {
				Iterator iter = map.entrySet().iterator();
				while (iter.hasNext()) {
					Map.Entry entry = (Map.Entry) iter.next();
					nvps.add(new BasicNameValuePair(entry.getKey().toString(), entry.getValue().toString()));
				}
			}

			// 转码 封装成请求实体
			HttpEntity reqEntity = new UrlEncodedFormEntity(nvps, "UTF-8");
			httpPost.setEntity(reqEntity);

			CloseableHttpResponse response = httpclient.execute(httpPost);
			try {
				// 获取响应实体
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					ret = EntityUtils.toString(entity, "UTF-8");
				}
			} finally {
				response.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				httpclient.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return ret;
	}

	public static String httpPostJson(String url, String pa, int to, boolean isHttps) {
		CloseableHttpClient httpclient = null;
		String ret = "";

		if (!isHttps)
			httpclient = HttpClients.createDefault();
		else
			httpclient = createSSLClientDefault();

		try {
			HttpPost httpPost = new HttpPost(url);
			RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(to).setConnectTimeout(to)
					.setConnectionRequestTimeout(to).setStaleConnectionCheckEnabled(true).build();
			httpPost.setConfig(requestConfig);

			StringEntity stringEntity = new StringEntity(pa, "UTF-8");
			stringEntity.setContentType("application/json");
			stringEntity.setContentEncoding("UTF-8");


			httpPost.setEntity(stringEntity);
			httpPost.setHeader("Content-type", "application/json;charset=utf-8");
			CloseableHttpResponse response = httpclient.execute(httpPost);
			try {
				// 获取响应实体
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					ret = EntityUtils.toString(entity, "UTF-8");
				}
			} finally {
				response.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				httpclient.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return ret;
	}

	private static CloseableHttpClient createSSLClientDefault() {
		try {
			SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
				// 信任所有
				public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
					return true;
				}
			}).build();
			SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext);
			return HttpClients.custom().setSSLSocketFactory(sslsf).build();
		} catch (KeyManagementException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (KeyStoreException e) {
			e.printStackTrace();
		}

		return HttpClients.createDefault();
	}
}
