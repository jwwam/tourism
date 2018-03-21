package com.tourism.controller;

import com.show.api.ShowApiRequest;
import com.tourism.controller.base.BaseController;
import com.tourism.entity.*;
import com.tourism.service.DesktopService;
import com.tourism.utils.StringUtils;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value="/search")
public class SearchController extends BaseController {

    @Autowired
    DesktopService desktopService;

    //public static String showapi_appid = "58346";
    //public static String showapi_sign = "aaf02e30d642409d8e0e82143373af62";

    @RequestMapping(value="/getData")
    public String view(HttpServletRequest request){
        String keyword = request.getParameter("keyword");
        String res=new ShowApiRequest("http://route.showapi.com/268-1","58346","aaf02e30d642409d8e0e82143373af62")
                .addTextPara("keyword",keyword)
                .addTextPara("proId","")
                .addTextPara("cityId","")
                .addTextPara("areaId","")
                .addTextPara("page","")
                .post();
        //System.out.println(res);

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
        /*try{
            String keyword = "贺兰山";
            //String keyword = request.getParameter("keyword");

            URL u = new URL("http://route.showapi.com/268-1?showapi_appid="+ showapi_appid +"&keyword="+ keyword +"&showapi_sign="+ showapi_sign);
            //http://route.showapi.com/268-1?showapi_appid=58346&keyword=贺兰山&showapi_sign=aaf02e30d642409d8e0e82143373af62
            InputStream in = u.openStream();
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            try {
                byte buf[] = new byte[1024];
                int read = 0;
                while ((read = in .read(buf)) > 0) {
                    out.write(buf, 0, read);
                }
            } finally {
                if ( in != null) {
                    in .close();
                }
            }
            byte b[] = out.toByteArray();
            String res = new String(b, "utf-8");
            //System.out.println(res);
            JSONObject json = JSONObject.fromObject(res);
            JSONArray jsarr = json.getJSONObject("showapi_res_body").getJSONObject("pagebean").getJSONArray("contentlist");
            System.out.println(jsarr);
            for (int i = 0; i < jsarr.size(); i++) {
                JSONObject ao = jsarr.getJSONObject(i);
                String name = ao.get("name").toString();
                System.out.println(name);
            }

        }catch (Exception e){
            e.printStackTrace();
        }*/


        return "/page/search";
    }

}
