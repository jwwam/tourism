package com.tourism.common;

import com.show.api.ShowApiRequest;

public class ApiData {

    public String getApiData(String keyword){
        String res = new ShowApiRequest("http://route.showapi.com/268-1","92031","d1ddc4dfb8514260aa13efdba71f9e8f")
                .addTextPara("keyword",keyword)
                .addTextPara("proId","")
                .addTextPara("cityId","")
                .addTextPara("areaId","")
                .addTextPara("page","")
                .post();
        return res;
    }
}
