package com.tourism.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethodBase;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.DeleteMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.PutMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;

/**
 * <p>
 * <Strong>Description：</Strong>rest请求工具类
 * </p>
 * 
 * <p>
 * <Strong>Date：</Strong> 2015-5-6
 * </p>
 * 
 * @author LiRixin
 * @since JDK 1.7
 * 
 */
public class RestClientUtil {

	/**
	 * @param url
	 *            请求路径
	 * @param headers
	 *            其他的头信息
	 * @return
	 * @throws IOException
	 */
	public static HttpMethodBase doDelete(final String url) throws IOException {
		final HttpMethodBase delete = new DeleteMethod(url);
		delete.addRequestHeader("Accept", "application/json");
		final HttpClient httpClient = new HttpClient();
		int status = 0;
		try {
			status = httpClient.executeMethod(delete);
			if ((status >= 200) && (status < 300)) {
				return delete;
			}
		} catch (final IOException e) {
			e.printStackTrace();
		}
		throw new IOException("status:" + status);
	}

	/**
	 * @param url
	 *            请求路径
	 * @param headers
	 *            其他的头信息
	 * @return
	 * @throws IOException
	 */
	public static HttpMethodBase doGet(final String url,String userId) throws IOException {
		final GetMethod get = new GetMethod(url);
		get.addRequestHeader("userId", userId); 
		final HttpClient httpClient = new HttpClient();
		int status = 0;
		try {
			status = httpClient.executeMethod(get);
			if ((status >= 200) && (status < 300)) {
				return get;
			}
		} catch (final IOException e) {
			e.printStackTrace();
		}
		throw new IOException("status:" + status);
	}

	/**
	 * <p>
	 * <Strong>Description：</Strong>
	 * </p>
	 * 
	 * <p>
	 * <Strong>Date：</Strong> 2015-6-30
	 * </p>
	 * 
	 * @param url
	 *            请求url
	 * @param headers
	 *            请求头信息
	 * @return
	 * @throws IOException
	 * @author LiRixin
	 * @since JDK 1.7
	 * 
	 */
	public static String doGetForString(final String url,String userId) throws IOException {
		System.out.println(url);
		try {
			final HttpMethodBase httpMethodBase = RestClientUtil.doGet(url,userId);
			return new String(httpMethodBase.getResponseBody(), "UTF-8");
		} catch (final UnsupportedEncodingException e) {
			throw new UnsupportedEncodingException("字符串编码错误");
		}
	}
	
	public static String doGetForString(final String url) throws IOException {
		try {
			final HttpMethodBase httpMethodBase = RestClientUtil.doGet(url,null);
			return new String(httpMethodBase.getResponseBody(), "UTF-8");
		} catch (final UnsupportedEncodingException e) {
			throw new UnsupportedEncodingException("字符串编码错误");
		}
	}

	/**
	 * @param url
	 *            请求路径
	 * @param sendData
	 *            发送的数据
	 * @param headers
	 *            其他的头信息
	 * @return
	 * @throws IOException
	 */
	public static HttpMethodBase doPost(final String url,
			final List<NameValuePair> nameValuePairs)
			throws IOException {
		return RestClientUtil.doPost(url, null, nameValuePairs);
	}

	/**
	 * @param url
	 *            请求路径
	 * @param sendData
	 *            发送的数据
	 * @param headers
	 *            其他的头信息
	 * @return
	 * @throws IOException
	 */
	public static HttpMethodBase doPost(final String url,
			final String sendData) throws IOException {
		return RestClientUtil.doPost(url, sendData, null);
	}

	/**
	 * @param url
	 *            请求路径
	 * @param sendData
	 *            发送的数据
	 * @param nameValuePairs
	 *            表单参数
	 * @param headers
	 *            其他的头信息
	 * @return
	 * @throws IOException
	 */
	public static HttpMethodBase doPost(final String url,
			final String sendData, final List<NameValuePair> nameValuePairs) throws IOException {
		final PostMethod post = new PostMethod(url);
		if ((sendData != null) && !"".equals(sendData)) {
			RequestEntity entity = null;
			try {
				entity = new StringRequestEntity(sendData, "application/json",
						"UTF-8");
			} catch (final UnsupportedEncodingException e1) {
				e1.printStackTrace();
				throw new IOException("无法设置:" + entity);
			}
			post.setRequestEntity(entity);
		}
		if (nameValuePairs != null) {
			post.setRequestBody(nameValuePairs
					.toArray(new NameValuePair[nameValuePairs.size()]));
		}
		final HttpClient httpClient = new HttpClient();
		int status = 0;
		try {
			status = httpClient.executeMethod(post);
			if ((status >= 200) && (status < 300)) {
				return post;
			}
		} catch (final IOException e) {
			e.printStackTrace();
		}
		throw new IOException("status:" + status);
	}

	public static String doPostForString(final String url,
			final String sendData) throws IOException {
		try {
			final HttpMethodBase httpMethodBase = RestClientUtil.doPost(url,
					sendData);
			return new String(httpMethodBase.getResponseBody(), "utf-8");
		} catch (final IOException e) {
			e.printStackTrace();
			throw new UnsupportedEncodingException("字符串编码错误");
		}
	}

	/**
	 * @param url
	 *            请求路径
	 * @param sendData
	 *            发送的数据
	 * @param headers
	 *            其他的头信息
	 * @return
	 * @throws IOException
	 */
	public static HttpMethodBase doPut(final String url, final String sendData,
			final Header... headers) throws IOException {
		final PutMethod put = new PutMethod(url);
		put.addRequestHeader("Accept", "application/json");
		for (final Header header : headers) {
			put.addRequestHeader(header.getName(), header.getValue().toString());
		}
		RequestEntity entity = null;
		try {
			entity = new StringRequestEntity(sendData, "application/json",
					"UTF-8");
		} catch (final UnsupportedEncodingException e1) {
			e1.printStackTrace();
			throw new IOException("无法设置:" + entity);
		}
		put.setRequestEntity(entity);
		final HttpClient httpClient = new HttpClient();
		int status = 0;
		try {
			status = httpClient.executeMethod(put);
			if ((status >= 200) && (status < 300)) {
				return put;
			}
		} catch (final IOException e) {
			e.printStackTrace();
		}
		throw new IOException("status:" + status);
	}


	public class ResponseBody {
		public Header[] headers;
		public String entry;

		public ResponseBody() {
		}

		public ResponseBody(final Header[] headers, final String entry) {
			this.headers = headers;
			this.entry = entry;
		}
	}
}
