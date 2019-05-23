package com.tourism.common;

import com.show.api.ShowApiRequest;
import org.springframework.stereotype.Component;

@Component
public class ApiData {


    public String getApiData(String keyword,String showApiUrl,String showApiAppid,String showApiAppSecret){
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
