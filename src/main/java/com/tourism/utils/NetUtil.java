package com.tourism.utils;

 

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

import org.apache.commons.httpclient.Credentials;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;

public class NetUtil {
	
	public static InputStream callURL(String urlStr, String account, byte[] b) throws IOException {
		URL url = new URL(urlStr);
		URLConnection connection = url.openConnection();

		connection.setDoOutput(true);
		connection.setDoInput(true);
		connection.setRequestProperty("Accept", "*/*");
		connection.setRequestProperty("Accept-Charset", "UTF-8");
		connection.setRequestProperty("authorization", account);
		// connection.setConnectTimeout(timeout*1000); // 1분

		OutputStream outStream = connection.getOutputStream();
		outStream.write(b);
		outStream.flush();
		outStream.close();
		outStream = null;

		return connection.getInputStream();
	}

	public static InputStream callURL(String urlStr, byte[] b) throws IOException {
		URL url = new URL(urlStr);
		URLConnection connection = url.openConnection();

		connection.setDoOutput(true);
		connection.setDoInput(true);
		connection.setRequestProperty("Accept", "*/*");
		connection.setRequestProperty("Accept-Charset", "UTF-8");
		connection.setConnectTimeout(40000);

		OutputStream outStream = connection.getOutputStream();
		outStream.write(b);
		outStream.flush();
		outStream.close();
		outStream = null;

		return connection.getInputStream();
	}

	public static InputStream callURL(String urlStr) throws IOException {
		URL url = new URL(urlStr);
		InputStream input = url.openStream();

		return input;
	}
	
	/**
	 * for Whois
	 * 
	 * @param url
	 * @param requestXML
	 * @return InputStream
	 * @throws HttpException
	 * @throws IOException
	 */
	public static InputStream callURL(String url, String requestXML) throws HttpException, IOException {
		HttpClient client = new HttpClient();
		PostMethod method = new PostMethod(url);
		StringRequestEntity entity = new StringRequestEntity(requestXML, "text/xml", "utf-8");
		method.setRequestEntity(entity);
		client.executeMethod(method);
		InputStream is = method.getResponseBodyAsStream();
		return is;
	}
	
	/**
	 * for Whois
	 * 
	 * @param url
	 * @param json
	 * @return InputStream
	 * @throws HttpException
	 * @throws IOException
	 */
	public static InputStream callURLForPost(String url, String json) throws HttpException, IOException {
		HttpClient client = new HttpClient();
		PostMethod method = new PostMethod(url);
		StringRequestEntity entity = new StringRequestEntity(json, "application/json", "utf-8");
		method.setRequestEntity(entity);
		client.executeMethod(method);
		InputStream is = method.getResponseBodyAsStream();
		return is;
	}
	
	public static InputStream callURLForGet(String url) throws HttpException, IOException {
		HttpClient client = new HttpClient();
		GetMethod method = new GetMethod(url);
		client.executeMethod(method);

		InputStream is = method.getResponseBodyAsStream();

		return is;
	}
	
	public static InputStream callURLWithAuthentication(String host, String port, String url, String userId, String password) throws HttpException, IOException {
		HttpClient client = new HttpClient();
		GetMethod method = new GetMethod(url);
		
		client.getParams().setAuthenticationPreemptive(true);
		client.getParams().setContentCharset("utf-8");
		Credentials defaultcreds = new UsernamePasswordCredentials(userId, password);
		client.getState().setCredentials(new AuthScope(host, Integer.parseInt(port), AuthScope.ANY_REALM), defaultcreds);

		client.executeMethod(method);
		
		InputStream is = method.getResponseBodyAsStream();
		
		return is;
	}
	
	public static InputStream callURLWithAuthentication(String host, String port, String url, String userId, String password, String requestXML) throws HttpException, IOException {
		HttpClient client = new HttpClient();
		PostMethod method = new PostMethod(url);
		
		client.getParams().setAuthenticationPreemptive(true);
		client.getParams().setContentCharset("utf-8");
		Credentials defaultcreds = new UsernamePasswordCredentials(userId, password);
		client.getState().setCredentials(new AuthScope(host, Integer.parseInt(port), AuthScope.ANY_REALM), defaultcreds);
		
		StringRequestEntity entity = new StringRequestEntity(requestXML, "text/xml", "utf-8");
		method.setRequestEntity(entity);
		client.executeMethod(method);
		
		InputStream is = method.getResponseBodyAsStream();
		
		return is;
	}
}
