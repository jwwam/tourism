package com.tourism.controller;

import com.tourism.common.DataTablesParam;
import com.tourism.controller.base.BaseController;
import com.tourism.dao.FeedBackDao;
import com.tourism.entity.FeedBack;
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
import java.util.UUID;

@Controller
@RequestMapping(value="/resFeedback")
public class ResFeedback extends BaseController {

    @Autowired
    FeedBackDao feedBackDao;

    @RequestMapping(value="/getList")
    public String getList(HttpServletRequest request){
        return "/admin/feedBackList";
    }

    //注册
    @RequestMapping(value="/addFeedBack", method = RequestMethod.POST)
    public String addUser(HttpServletRequest request, String name, String content, String email) {
        FeedBack fb = new FeedBack();
        String uuid = UUID.randomUUID().toString(); //获取UUID并转化为String对象
        uuid = uuid.replace("-", "");
        fb.setId(uuid);
        fb.setContent(content);
        fb.setEmail(email);
        fb.setName(name);
        feedBackDao.save(fb);
        return "/page/contacts_success";
    }

    @RequestMapping(value="/getFeedBackList",method = RequestMethod.POST)
    @ResponseBody
    public ModelMap getUserList(DataTablesParam dataTablesParam){
        ModelMap map = new ModelMap();
        Page<FeedBack> pageRe = null;
        pageRe = feedBackDao.findAll(BuildPageRequest.getPageRequest(dataTablesParam.getPageNum(), dataTablesParam.getiDisplayLength(), Sort.Direction.DESC, "id"));
        map.put("sEcho", dataTablesParam.getsEcho());
        map.put("iTotalRecords", pageRe.getTotalElements());
        map.put("iTotalDisplayRecords",pageRe.getTotalElements());
        map.put("aaData", pageRe.getContent());
        return  map;
    }

}
