package com.tourism.controller;

import com.tourism.common.DataTablesParam;
import com.tourism.controller.base.BaseController;
import com.tourism.entity.Gallery;
import com.tourism.service.GalleryService;
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

@Controller
@RequestMapping(value="/resGallery")
public class ResGallery extends BaseController {

    @Autowired
    GalleryService galleryService;

    @RequestMapping(value="/view")
    public ModelAndView view(@RequestParam(value = "start", defaultValue = "0") Integer start,
                             @RequestParam(value = "limit", defaultValue = "9") Integer limit){
        start = start < 0 ? 0 : start;
        Sort sort = new Sort(Sort.DEFAULT_DIRECTION, "id");
        Pageable pageable = new PageRequest(start, limit, sort);
        Page<Gallery> page = galleryService.findAll(pageable);
        ModelAndView mav = new ModelAndView("/page/gallery");
        //request.setAttribute("labelList",contentFlowService.getAllLabel());
        //request.setAttribute("clickId","label");
        //return "/page/scenery";
        mav.addObject("page", page);
        return mav;
    }



}
