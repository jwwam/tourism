package com.tourism.webservice;

import java.io.IOException;

public interface SmsWebService {
	
	int sendSMS(String iphone,String content )throws IOException;
     
     
    /* String[]  getStatus(String gid)throws IOException;*/
     
	
     

}
