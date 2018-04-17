package com.tourism.controller.admin;

import com.tourism.common.DataTablesParam;
import com.tourism.controller.base.BaseController;
import com.tourism.entity.Line;
import com.tourism.service.LineService;
import com.tourism.utils.BuildPageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value="/line")
public class LineController extends BaseController {


    @Autowired
    LineService lineService;

    @RequestMapping(value="/add")
    public String view(HttpServletRequest request){
        return "/admin/addLine";
    }

    @RequestMapping(value="/getList")
    public String getList(HttpServletRequest request){
        return "/admin/lineList";
    }

    @RequestMapping(value="/getLineList",method = RequestMethod.POST)
    @ResponseBody
    public ModelMap getLineList(DataTablesParam dataTablesParam){
        ModelMap map = new ModelMap();
        Page<Line> pageRe = null;
        pageRe = lineService.findAll(BuildPageRequest.getPageRequest(dataTablesParam.getPageNum(), dataTablesParam.getiDisplayLength(), Sort.Direction.DESC, "id"));
        map.put("sEcho", dataTablesParam.getsEcho());
        map.put("iTotalRecords", pageRe.getTotalElements());
        map.put("iTotalDisplayRecords",pageRe.getTotalElements());
        map.put("aaData", pageRe.getContent());
        return  map;
    }

    @RequestMapping(value="/upload")
    public String upload (HttpServletRequest request,HttpServletResponse response) {

        //String registID = (String) request.getSession().getAttribute("registID");
        String registID = "";
        int result = lineService.upload(request, response);

        if (result < 0) {
            request.setAttribute("failMsg", "上传失败，请重新上传！");
            //return "/gongshang/recordUpload";
            return "/admin/addLine";
        } else {
            //return "/gongshang/recordReview";
            return "/admin/lineList";
        }
    }

}
