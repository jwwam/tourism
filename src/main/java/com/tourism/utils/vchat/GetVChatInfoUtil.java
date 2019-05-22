package com.tourism.utils.vchat;

import com.tourism.utils.StringUtils;
import net.sf.json.JSONObject;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import java.io.*;
import java.net.ConnectException;
import java.net.URL;
import java.security.SecureRandom;

/**
 * Created by ppm on 2017/8/31.
 */
@Component
public class GetVChatInfoUtil {
    @Value("${hq.vchat.appid}")
    public  String appid="wx868758436df48548";
    @Value("${hq.vchat.appSecret}")
    public  String secret="7abc256d537b41c811dc9f9909484c17";
    public  GetAccessTokenRsp getWeiXinAccessToken() {
        if (StringUtils.isEmpty(secret) || StringUtils.isEmpty(secret)) {
            //Logger.error("appid or secret is null");
            return null;
        }
        GetAccessTokenRsp getAccessTokenRsp = new GetAccessTokenRsp();
        try {
            String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + appid + "&secret=" + secret;
            HttpClient httpClient = new HttpClient();
            GetMethod getMethod = new GetMethod(url);
            int execute = httpClient.executeMethod(getMethod);
            System.out.println("execute:" + execute);
            String getResponse = getMethod.getResponseBodyAsString();
            com.alibaba.fastjson.JSONObject jsonObject= com.alibaba.fastjson.JSON.parseObject(getResponse);
            getAccessTokenRsp=jsonObject.toJavaObject(GetAccessTokenRsp.class);
        } catch (IOException e) {
            //DEBUG_LOGGER.error("getAccessToken failed,desc:::"+e);
            e.printStackTrace();
        }
        System.out.println("token:"+getAccessTokenRsp.getAccessToken());
        return getAccessTokenRsp;
    }
    public   String  getOpenId(){
        String accessToken=getWeiXinAccessToken().getAccessToken();
        String result = null;
        String requestUrl ="https://api.weixin.qq.com/cgi-bin/user/get?access_token=ACCESS_TOKEN";
        requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken);
        JSONObject jsonObject = httpRequest(requestUrl, "GET", null);
        if(null != jsonObject){
            System.out.println(jsonObject);
            System.out.println(jsonObject.get("total"));
            System.out.println(jsonObject.get("data"));
            result = jsonObject.get("data")+"";
        }
        System.out.print("openId:"+result);
        return result;
    }
    public static JSONObject httpRequest(String requestUrl, String requestMethod, String outputStr)
    {
        JSONObject jsonObject = null;
        StringBuffer buffer = new StringBuffer();
        try
        {
            TrustManager[] tm = { new MyX509TrustManager() };
            SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
            sslContext.init(null, tm, new SecureRandom());

            SSLSocketFactory ssf = sslContext.getSocketFactory();

            URL url = new URL(requestUrl);
            HttpsURLConnection httpUrlConn = (HttpsURLConnection)url.openConnection();
            httpUrlConn.setSSLSocketFactory(ssf);

            httpUrlConn.setDoOutput(true);
            httpUrlConn.setDoInput(true);
            httpUrlConn.setUseCaches(false);

            httpUrlConn.setRequestMethod(requestMethod);

            if ("GET".equalsIgnoreCase(requestMethod)) {
                httpUrlConn.connect();
            }

            if (outputStr != null) {
                OutputStream outputStream = httpUrlConn.getOutputStream();

                outputStream.write(outputStr.getBytes("UTF-8"));
                outputStream.close();
            }

            InputStream inputStream = httpUrlConn.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String str = null;
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }
            bufferedReader.close();
            inputStreamReader.close();

            inputStream.close();
            inputStream = null;
            httpUrlConn.disconnect();
            jsonObject = JSONObject.fromObject(buffer.toString());
        } catch (ConnectException ce) {
           // logger.error("Weixin server connection timed out.");
        } catch (Exception e) {
          //  logger.error("https request error:{}", e);
        }
        return jsonObject;
    }
}