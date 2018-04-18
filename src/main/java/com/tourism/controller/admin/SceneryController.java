package com.tourism.controller.admin;

import com.tourism.common.DataTablesParam;
import com.tourism.controller.base.BaseController;
import com.tourism.entity.Scenery;
import com.tourism.service.SceneryService;
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
@RequestMapping(value="/scenery")
public class SceneryController  extends BaseController {

    @Autowired
    SceneryService sceneryService;

    @RequestMapping(value="/add")
    public String view(HttpServletRequest request){
        return "/admin/addScenery";
    }

    @RequestMapping(value="/getList")
    public String getList(HttpServletRequest request){
        return "/admin/sceneryList";
    }

    @RequestMapping(value="/getSceneryList",method = RequestMethod.POST)
    @ResponseBody
    public ModelMap getSceneryList(DataTablesParam dataTablesParam){
        ModelMap map = new ModelMap();
        Page<Scenery> pageRe = null;
        pageRe = sceneryService.findAll(BuildPageRequest.getPageRequest(dataTablesParam.getPageNum(), dataTablesParam.getiDisplayLength(), Sort.Direction.DESC, "id"));
        map.put("sEcho", dataTablesParam.getsEcho());
        map.put("iTotalRecords", pageRe.getTotalElements());
        map.put("iTotalDisplayRecords",pageRe.getTotalElements());
        map.put("aaData", pageRe.getContent());
        return  map;
    }

    @RequestMapping(value="/upload")
    public String upload (HttpServletRequest request,HttpServletResponse response){

        //String registID = (String) request.getSession().getAttribute("registID");
        String registID = "";
        int result = sceneryService.upload(request, response);

        if( result < 0 ){
            request.setAttribute("failMsg", "上传失败，请重新上传！");
            //return "/gongshang/recordUpload";
            return "/admin/addScenery";
        }else{
            //return "/gongshang/recordReview";
            return "/admin/sceneryList";
        }
    }

    //删除
    @RequestMapping(value="/deleteScenery", method = RequestMethod.POST)
    @ResponseBody
    public ModelMap deleteScenery(String id) {
        ModelMap map = new ModelMap();
        sceneryService.delete(sceneryService.findById(id));
        map.put("msg", "删除成功");
        return map;
    }

    @RequestMapping(value="/update")
    public String update(HttpServletRequest request, String id){
        Scenery scenery = sceneryService.findById(id);
        request.setAttribute("scenery", scenery);
        return "/admin/addScenery";
    }

}
