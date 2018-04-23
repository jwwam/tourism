package com.tourism.controller;

import com.tourism.controller.base.BaseController;
import com.tourism.entity.Hotel;
import com.tourism.entity.Scenery;
import com.tourism.service.HotelService;
import com.tourism.service.SceneryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping(value="/resHotel")
public class ResHotel extends BaseController {


    @Autowired
    HotelService hotelService;

    @RequestMapping(value="/view")
    public ModelAndView view(@RequestParam(value = "start", defaultValue = "0") Integer start,
                             @RequestParam(value = "limit", defaultValue = "9") Integer limit){
        start = start < 0 ? 0 : start;
        Sort sort = new Sort(Sort.DEFAULT_DIRECTION, "id");
        Pageable pageable = new PageRequest(start, limit, sort);
        Page<Hotel> page = hotelService.findAll(pageable);
        ModelAndView mav = new ModelAndView("/page/hotel");
        //request.setAttribute("labelList",contentFlowService.getAllLabel());
        //request.setAttribute("clickId","label");
        //return "/page/scenery";
        mav.addObject("page", page);
        return mav;
    }

    @RequestMapping(value="/search")
    public ModelAndView search(@RequestParam(value = "start", defaultValue = "0") Integer start,
                               @RequestParam(value = "limit", defaultValue = "9") Integer limit,
                               @RequestParam String day, @RequestParam String sPrice,
                               @RequestParam String ePrice, @RequestParam String star){
        start = start < 0 ? 0 : start;
        Sort sort = new Sort(Sort.DEFAULT_DIRECTION, "id");
        Pageable pageable = new PageRequest(start, limit, sort);
        Page<Hotel> page = hotelService.findAll(day, sPrice, ePrice, star, pageable);
        ModelAndView mav = new ModelAndView("/page/hotel_search");
        mav.addObject("page", page);
        return mav;
    }


}
