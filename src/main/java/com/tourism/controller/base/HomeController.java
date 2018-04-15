package com.tourism.controller.base;

import javax.servlet.http.HttpServletRequest;

import com.tourism.entity.ContentFlow;
import com.tourism.entity.Desktop;
import com.tourism.entity.TopFlow;
import com.tourism.service.ContentFlowService;
import com.tourism.service.DesktopService;
import com.tourism.service.TopFlowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by zyq on 2017/7/13.
 */
@Controller
@RequestMapping(value="/base")
public class HomeController {

    @Autowired
    ContentFlowService contentFlowService;

    @Autowired
    TopFlowService topFlowService;

    @Autowired
    DesktopService desktopService;


    @RequestMapping(value="/home")
    public String view(HttpServletRequest request){
        // iCBFileService.getICBImageUrl("http://10.4.3.13:6888/dacx/temp/10.4.199.174/440003/00004413000000000000064001/2.tif ","test" ,"00004413000000000000064001", 2);
        return "/base/home";
    }
    @RequestMapping(value="/test")
    public String test(HttpServletRequest request){
        // iCBFileService.getICBImageUrl("http://10.4.3.13:6888/dacx/temp/10.4.199.174/440003/00004413000000000000064001/2.tif ","test" ,"00004413000000000000064001", 2);
        return "/base/print";
    }

    /*@RequestMapping(value="/index")
    public String index(HttpServletRequest request){

        return "/lover/index";
    }*/

    @RequestMapping(value="/index")
    public String index(HttpServletRequest request){
        List<ContentFlow> contentFlowList = contentFlowService.getAllLabel();
        List<TopFlow> topFlowList = topFlowService.getAll();
        request.setAttribute("contentFlowList",contentFlowList);
        request.setAttribute("topFlowList",topFlowList);
        List<Desktop> desktopList = desktopService.getAll();
        if(desktopList!=null||desktopList.size()!=0){
            request.getSession().setAttribute("desktop",desktopList.get(0));
        }else{
            Desktop desktop = new Desktop();
            request.getSession().setAttribute("desktop",desktop);
        }
        return "/page/index";
    }

}
