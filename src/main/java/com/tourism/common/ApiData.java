package com.tourism.common;

import com.show.api.ShowApiRequest;

public class ApiData {

    public String getApiData(String keyword){
        String res = new ShowApiRequest("http://route.showapi.com/268-1","58346","aaf02e30d642409d8e0e82143373af62")
                .addTextPara("keyword",keyword)
                .addTextPara("proId","")
                .addTextPara("cityId","")
                .addTextPara("areaId","")
                .addTextPara("page","")
                .post();

        return res;
    }

}
