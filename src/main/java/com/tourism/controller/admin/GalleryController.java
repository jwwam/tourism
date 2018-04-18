package com.tourism.controller.admin;

import com.tourism.common.DataTablesParam;
import com.tourism.controller.base.BaseController;
import com.tourism.entity.Gallery;
import com.tourism.entity.Hotel;
import com.tourism.service.GalleryService;
import com.tourism.utils.BuildPageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value="/gallery")
public class GalleryController extends BaseController {

    @Autowired
    GalleryService galleryService;

    @RequestMapping(value="/add")
    public String view(HttpServletRequest request){
        return "/admin/addGallery";
    }

    @RequestMapping(value="/getList")
    public String getList(HttpServletRequest request){
        return "/admin/galleryList";
    }

    @RequestMapping(value="/getGalleryList",method = RequestMethod.POST)
    @ResponseBody
    public ModelMap getGalleryList(DataTablesParam dataTablesParam){
        ModelMap map = new ModelMap();
        Page<Gallery> pageRe = null;
        pageRe = galleryService.findAll(BuildPageRequest.getPageRequest(dataTablesParam.getPageNum(), dataTablesParam.getiDisplayLength(), Sort.Direction.DESC, "id"));
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
        int result = galleryService.upload(request, response);

        if( result < 0 ){
            request.setAttribute("failMsg", "上传失败，请重新上传！");
            //return "/gongshang/recordUpload";
            return "/admin/addGallery";
        }else{
            //return "/gongshang/recordReview";
            return "/admin/galleryList";
        }
    }

    //删除
    @RequestMapping(value="/deleteGallery", method = RequestMethod.POST)
    @ResponseBody
    public ModelMap deleteGallery(String id) {
        ModelMap map = new ModelMap();
        galleryService.delete(galleryService.findById(id));
        map.put("msg", "删除成功");
        return map;
    }


    @RequestMapping(value="/update")
    public String update(HttpServletRequest request, String id){
        Gallery gallery = galleryService.findById(id);
        request.setAttribute("gallery", gallery);
        return "/admin/addGallery";
    }


}
