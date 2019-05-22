package com.tourism.controller;

import com.tourism.controller.base.BaseController;
import com.tourism.entity.Food;
import com.tourism.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value="/resFood")
public class ResFood extends BaseController {
    @Autowired
    FoodService foodService;

    @RequestMapping(value="/view")
    public ModelAndView view(@RequestParam(value = "start", defaultValue = "0") Integer start,
                             @RequestParam(value = "limit", defaultValue = "9") Integer limit){
        start = start < 0 ? 0 : start;
        Sort sort = new Sort(Sort.DEFAULT_DIRECTION, "id");
        Pageable pageable = new PageRequest(start, limit, sort);
        Page<Food> page = foodService.findAll(pageable);
        ModelAndView mav = new ModelAndView("/page/food");
        mav.addObject("page", page);
        return mav;
    }
}
