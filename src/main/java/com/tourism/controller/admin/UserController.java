package com.tourism.controller.admin;

import com.tourism.common.DataTablesParam;
import com.tourism.controller.base.BaseController;
import com.tourism.entity.User;
import com.tourism.service.UserService;
import com.tourism.utils.BuildPageRequest;
import com.tourism.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value="/user")
public class UserController extends BaseController {

    @Autowired
    UserService userService;

    @RequestMapping(value="/add")
    public String view(HttpServletRequest request){
        return "/admin/addUser";
    }

    @RequestMapping(value="/getList")
    public String getList(HttpServletRequest request){
        return "/admin/userList";
    }

    @RequestMapping(value="/getUserList",method = RequestMethod.POST)
    @ResponseBody
    public ModelMap getUserList(DataTablesParam dataTablesParam){
        ModelMap map = new ModelMap();
        Page<User> pageRe = null;
        pageRe = userService.findAll(BuildPageRequest.getPageRequest(dataTablesParam.getPageNum(), dataTablesParam.getiDisplayLength(), Sort.Direction.DESC, "id"));
        map.put("sEcho", dataTablesParam.getsEcho());
        map.put("iTotalRecords", pageRe.getTotalElements());
        map.put("iTotalDisplayRecords",pageRe.getTotalElements());
        map.put("aaData", pageRe.getContent());
        return  map;
    }

    //删除用户
    @RequestMapping(value="/deleteUser", method = RequestMethod.POST)
    @ResponseBody
    public ModelMap deleteUser(String id) {
        ModelMap map = new ModelMap();
        userService.delete(userService.findById(id));
        map.put("msg", "删除成功");
        return map;
    }

    //获取用户信息
    @RequestMapping(value="/getUserInfo", method = RequestMethod.POST)
    @ResponseBody
    public ModelMap getUserInfo(String id) {
        ModelMap map = new ModelMap();
        User u = userService.findById(id);
        map.put("user", u);
        return map;
    }

    //注册
    @RequestMapping(value="/addUser", method = RequestMethod.POST)
    public String addUser(HttpServletRequest request, String username, String password, String id) {
        User us = userService.findById(id);
        if(us==null) {
            us = new User();
        }
        us.setPassword(password);
        us.setUsername(username);
        String [] userResult = userService.save(us);
        if( userResult[0].equals("false") ){
            //"操作失败";
            logger.info("保存失败："+userResult.toString());
        }else{
            //"操作成功";
            logger.info("保存成功："+userResult.toString());
        }
        return "/admin/userList";
    }
}
