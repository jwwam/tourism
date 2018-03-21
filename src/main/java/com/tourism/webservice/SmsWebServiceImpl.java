package com.tourism.webservice;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.tourism.utils.DateUtils;
import com.tourism.utils.RestClientUtil;
import com.tourism.utils.XmlToJson;

@Service("smsWebService")
public class SmsWebServiceImpl implements SmsWebService {
	
	private static String loginName = "hqzf";
	private static String pwd = "hqzf1234";
	
	public static void main(String[] args) {
		
			//sendSMS("中文测试","18565510628");
		
	}

	public  int sendSMS(String contents,String sendNums)throws IOException{
		
		Date date = new Date();
		String  timestamp= DateUtils.yyyyMMddHHmmssExtend(date);
		String str = loginName+pwd+timestamp;
		String sign = getMD5(str).toLowerCase();
		String content = URLEncoder.encode(contents, "UTF-8");//编码
		String url= "http://61.142.68.241:8088/v2sms.aspx?action=send&userid=16&timestamp="+timestamp+"&sign="+sign+"&mobile="+sendNums+"&content="+content+"&sendTime=&extno=";
		String result = RestClientUtil.doGetForString(url);
	    //将xml转为json  
        String xmlJSONObj = XmlToJson.xml2JSON(result);
        String  returnsms = net.sf.json.JSONObject.fromObject(xmlJSONObj).get("returnsms")+"";
        System.out.println(returnsms);
        String successCounts = net.sf.json.JSONObject.fromObject(returnsms).get("successCounts")+"";
        System.out.println(successCounts);
        String endResult = successCounts.replace("[\"", "").replace("\"]", "");
        System.out.println(endResult);
		return Integer.parseInt(endResult);
	
	}
	
	
	
	 /** 
     * 生成md5 
     *  
     * @param message 
     * @return 
	 * @throws UnsupportedEncodingException 
	 * @throws NoSuchAlgorithmException 
     */  
    public String getMD5(String str){  
    	 String result="";
    	 try {
    		 MessageDigest md5 = MessageDigest.getInstance("MD5");  
			 md5.update((str).getBytes("UTF-8"));
			 byte b[] = md5.digest();  
	    	 int i;  
	    	 StringBuffer buf = new StringBuffer("");  
	    	 for(int offset=0; offset<b.length; offset++){  
	    	     i = b[offset];  
	    	     if(i<0){  
	    	         i+=256;  
	    	     }  
	    	     if(i<16){  
	    	         buf.append("0");  
	    	     }  
	    	     buf.append(Integer.toHexString(i));  
	    	 }  
	    	 result = buf.toString();  
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
    	 return result;
    	 
    }  
  
	
}
