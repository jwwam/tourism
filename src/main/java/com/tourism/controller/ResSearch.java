package com.tourism.controller;

import com.show.api.ShowApiRequest;
import com.tourism.common.ApiData;
import com.tourism.controller.base.BaseController;
import com.tourism.entity.*;
import com.tourism.service.DesktopService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * java原生写法调用api接口main方法有效, 普通方法无效, 所以使用api封装写法
 */

@Controller
@RequestMapping(value="/search")
public class ResSearch extends BaseController {

    @Autowired
    DesktopService desktopService;
    @RequestMapping(value="/getData")
    public String view(HttpServletRequest request){
        ApiData ad = new ApiData();
        String keyword = request.getParameter("keyword");
        String res = ad.getApiData(keyword);
        JSONObject json = JSONObject.fromObject(res);
        JSONArray jsarr = json.getJSONObject("showapi_res_body").getJSONObject("pagebean").getJSONArray("contentlist");
        System.out.println(jsarr);
        List<Apidata> apidataList = new ArrayList<Apidata>();
        for (int i = 0; i < jsarr.size(); i++) {
            Apidata apidata = new Apidata();
            JSONObject ao = jsarr.getJSONObject(i);
            String name = ao.get("name") + "";
            apidata.setName(name);
            String cityName = ao.get("cityName") + "";
            apidata.setCityName(cityName);
            String areaName = ao.get("areaName") + "";
            apidata.setAreaName(areaName);
            String proName = ao.get("proName") + "";
            apidata.setProName(proName);
            String summary = ao.get("summary") + "";
            apidata.setSummary(summary);
            String star = ao.get("star") + "";
            apidata.setStar(star);
            String content = ao.get("content") + "";
            apidata.setContent(content);
            String price = ao.get("price") + "";
            apidata.setPrice(price);
            String address = ao.get("address") + "";
            apidata.setAddress(address);
            String coupon = ao.get("coupon") + "";
            apidata.setCoupon(coupon);
            String attention = ao.get("attention") + "";
            apidata.setAttention(attention);
            String opentime = ao.get("opentime") + "";
            apidata.setOpentime(opentime);

            //String priceList = ao.get("priceList") + "";
            JSONArray pricelist = ao.getJSONArray("priceList");
            List<Price> priceList = new ArrayList<Price>();
            for (int j = 0; j < pricelist.size(); j++){
                Price prices = new Price();
                JSONObject eo = pricelist.getJSONObject(j);
                String type = eo.get("type") + "";
                prices.setType(type);
                System.out.println("type="+type);

                JSONArray entityList = eo.getJSONArray("entityList");
                List<PriceData> priceDataList = new ArrayList<PriceData>();
                for (int k = 0; k < entityList.size(); k++){
                    PriceData pd = new PriceData();
                    JSONObject vo = entityList.getJSONObject(k);
                    String TicketName = vo.get("TicketName") + "";
                    pd.setTicketName(TicketName);
                    String Amount = vo.get("Amount") + "";
                    pd.setAmount(Amount);
                    String AmountAdvice = vo.get("AmountAdvice") + "";
                    pd.setAmountAdvice(AmountAdvice);

                    System.out.println("TicketName="+TicketName);
                    System.out.println("Amount="+Amount);
                    System.out.println("AmountAdvice="+AmountAdvice);
                    priceDataList.add(pd);
                }
                prices.setPriceDataList(priceDataList);
                priceList.add(prices);
            }
            apidata.setPriceList(priceList);

            JSONArray piclist = ao.getJSONArray("picList");
            List<Pic> picList = new ArrayList<Pic>();
            for (int s = 0; s < piclist.size(); s++){
                Pic pic = new Pic();
                JSONObject po = piclist.getJSONObject(s);
                String picUrl = po.get("picUrl") + "";
                pic.setPicUrl(picUrl);
                System.out.println("picUrl="+picUrl);
                String picUrlSmall = po.get("picUrlSmall") + "";
                pic.setPicUrlSmall(picUrlSmall);
                System.out.println("picUrlSmall="+picUrlSmall);
                picList.add(pic);
            }
            apidata.setPicList(picList);
            System.out.println("name="+name);
            System.out.println("cityName="+cityName);
            System.out.println("areaName="+areaName);
            System.out.println("proName="+proName);
            System.out.println("summary="+summary);
            System.out.println("star="+star);
            System.out.println("content="+content);
            System.out.println("price="+price);
            System.out.println("address="+address);
            System.out.println("coupon="+coupon);
            System.out.println("attention="+attention);
            System.out.println("opentime="+opentime);
            /*if(StringUtils.isNotEmpty(ao.get("name") + "")){
            }else{

            }*/
            apidataList.add(apidata);
        }
        request.setAttribute("apidataList",apidataList);
        List<Desktop> desktopList = desktopService.getAll();
        if(desktopList!=null||desktopList.size()!=0){
            request.setAttribute("desktop",desktopList.get(0));
        }else{
            Desktop desktop = new Desktop();
            request.setAttribute("desktop",desktop);
        }
        return "/page/search";
    }




}
