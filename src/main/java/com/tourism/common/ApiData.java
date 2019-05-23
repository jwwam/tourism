package com.tourism.common;

import com.show.api.ShowApiRequest;
import org.springframework.beans.factory.annotation.Value;

public class ApiData {

    @Value("#{configProperties['showApi.url']}")
    private String showApiUrl;
    @Value("#{configProperties['showApi.appid']}")
    private String showApiAppid;
    @Value("#{configProperties['showApi.appSecret']}")
    private String showApiAppSecret;

    public String getApiData(String keyword){
        String res = new ShowApiRequest(showApiUrl, showApiAppid, showApiAppSecret)
                .addTextPara("keyword",keyword)
                .addTextPara("proId","")
                .addTextPara("cityId","")
                .addTextPara("areaId","")
                .addTextPara("page","")
                .post();
        return res;
    }
}
