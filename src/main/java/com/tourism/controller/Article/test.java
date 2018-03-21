package com.tourism.controller.Article;

import com.show.api.ShowApiRequest;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;

public class test {

    public static String showapi_appid = "58346";
    public static String showapi_sign = "aaf02e30d642409d8e0e82143373af62";

    public static void main(String path[]) throws Exception {

        String res=new ShowApiRequest("http://route.showapi.com/268-1","58346","aaf02e30d642409d8e0e82143373af62")
                .addTextPara("keyword","泰山")
                .addTextPara("proId","")
                .addTextPara("cityId","")
                .addTextPara("areaId","")
                .addTextPara("page","")
                .post();
        System.out.println(res);

        /*//aaf02e30d642409d8e0e82143373af62
        String keyword = "贺兰山";

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
        }*/

    }

}
