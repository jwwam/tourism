package com.tourism.controller.admin;

import com.tourism.controller.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value="/line")
public class LineController extends BaseController {
    @RequestMapping(value="/add")
    public String view(HttpServletRequest request){
        return "/admin/addLine";
    }

    @RequestMapping(value="/getList")
    public String getList(HttpServletRequest request){
        return "/admin/lineList";
    }
}
