package com.tourism.controller;

import com.tourism.controller.base.BaseController;
import com.tourism.entity.Desktop;
import com.tourism.service.DesktopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping(value="/desktop")
public class Index extends BaseController {

    @Autowired
    DesktopService desktopService;

    @RequestMapping(value="/view")
    public String view(HttpServletRequest request){
        List<Desktop> desktopList = desktopService.getAll();
        if(desktopList!=null||desktopList.size()!=0){
            request.setAttribute("desktop",desktopList.get(0));
        }else{
            Desktop desktop = new Desktop();
            request.setAttribute("desktop",desktop);
        }
        return "/admin/config";
    }

    @RequestMapping(value="/upload")
    public void upload (HttpServletRequest request,HttpServletResponse response) throws Exception{

        //String registID = (String) request.getSession().getAttribute("registID");
        String registID = "";
        int result = desktopService.upload(request, response,registID);

        if( result < 0 ){
            request.setAttribute("failMsg", "上传失败，请重新上传！");
            //return "/admin/config";
            response.sendRedirect(request.getContextPath() + "/desktop/view"); // 返回提示页面
        }else{
            //return "/gongshang/recordReview";
            response.sendRedirect(request.getContextPath() + "/desktop/view"); // 返回提示页面
            //return "/admin/config";
        }

    }

}
