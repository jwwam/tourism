package com.tourism.controller.admin;

import com.tourism.common.DataTablesParam;
import com.tourism.controller.base.BaseController;
import com.tourism.entity.Hotel;
import com.tourism.entity.Scenery;
import com.tourism.service.HotelService;
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
@RequestMapping(value="/hotel")
public class HotelController extends BaseController {

    @Autowired
    HotelService hotelService;

    @RequestMapping(value="/add")
    public String view(HttpServletRequest request){
        return "/admin/addHotel";
    }

    @RequestMapping(value="/getList")
    public String getList(HttpServletRequest request){
        return "/admin/hotelList";
    }

    @RequestMapping(value="/getHotelList",method = RequestMethod.POST)
    @ResponseBody
    public ModelMap getHotelList(DataTablesParam dataTablesParam){
        ModelMap map = new ModelMap();
        Page<Hotel> pageRe = null;
        pageRe = hotelService.findAll(BuildPageRequest.getPageRequest(dataTablesParam.getPageNum(), dataTablesParam.getiDisplayLength(), Sort.Direction.DESC, "id"));
        map.put("sEcho", dataTablesParam.getsEcho());
        map.put("iTotalRecords", pageRe.getTotalElements());
        map.put("iTotalDisplayRecords",pageRe.getTotalElements());
        map.put("aaData", pageRe.getContent());
        return  map;
    }

    @RequestMapping(value="/upload")
    public String upload (HttpServletRequest request,HttpServletResponse response){
        int result = hotelService.upload(request, response);
        if( result < 0 ){
            request.setAttribute("failMsg", "上传失败，请重新上传！");
            return "/admin/addHotel";
        }else{
            return "/admin/hotelList";
        }

    }

    @RequestMapping(value="/update")
    public String update(HttpServletRequest request, String id){
        Hotel hotel = hotelService.findById(id);
        request.setAttribute("hotel", hotel);
        return "/admin/addHotel";
    }

    //删除
    @RequestMapping(value="/deleteHotel", method = RequestMethod.POST)
    @ResponseBody
    public ModelMap deleteHotel(String id) {
        ModelMap map = new ModelMap();
        hotelService.delete(hotelService.findById(id));
        map.put("msg", "删除成功");
        return map;
    }

}
