package com.tourism.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value="/admin")
public class AdminController {

    @RequestMapping(value="/index")
    public String view(HttpServletRequest request){
        // iCBFileService.getICBImageUrl("http://10.4.3.13:6888/dacx/temp/10.4.199.174/440003/00004413000000000000064001/2.tif ","test" ,"00004413000000000000064001", 2);
        return "/admin/index";
    }

    @RequestMapping(value="/flot")
    public String flot(HttpServletRequest request){
        // iCBFileService.getICBImageUrl("http://10.4.3.13:6888/dacx/temp/10.4.199.174/440003/00004413000000000000064001/2.tif ","test" ,"00004413000000000000064001", 2);
        return "/admin/flot";
    }

    @RequestMapping(value="/content")
    public String content(HttpServletRequest request){
        // iCBFileService.getICBImageUrl("http://10.4.3.13:6888/dacx/temp/10.4.199.174/440003/00004413000000000000064001/2.tif ","test" ,"00004413000000000000064001", 2);
        return "/admin/content";
    }

}
