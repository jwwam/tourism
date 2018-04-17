package com.tourism.controller;

import com.tourism.controller.base.BaseController;
import com.tourism.entity.Desktop;
import com.tourism.entity.User;
import com.tourism.service.DesktopService;
import com.tourism.service.UserService;
import com.tourism.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping(value="/desktop")
public class Index extends BaseController {

    @Autowired
    DesktopService desktopService;

    @Autowired
    UserService userService;

    @RequestMapping(value="/view")
    public String view(HttpServletRequest request){
        List<Desktop> desktopList = desktopService.getAll();
        if(desktopList!=null||desktopList.size()!=0){
            request.setAttribute("desktop",desktopList.get(0));
        }else{
            Desktop desktop = new Desktop();
            request.setAttribute("desktop",desktop);
        }
        return "/admin/config";
    }

    @RequestMapping(value="/upload")
    public void upload (HttpServletRequest request,HttpServletResponse response) throws Exception{

        //String registID = (String) request.getSession().getAttribute("registID");
        String registID = "";
        int result = desktopService.upload(request, response,registID);

        if( result < 0 ){
            request.setAttribute("failMsg", "上传失败，请重新上传！");
            //return "/admin/config";
            response.sendRedirect(request.getContextPath() + "/desktop/view"); // 返回提示页面
        }else{
            //return "/gongshang/recordReview";
            response.sendRedirect(request.getContextPath() + "/desktop/view"); // 返回提示页面
            //return "/admin/config";
        }

    }

    @RequestMapping(value="/login", method = RequestMethod.POST)
    public void login (HttpServletRequest request,HttpServletResponse response, String username, String password) throws Exception{
        if(StringUtils.isNotEmpty(username)&&StringUtils.isNotEmpty(password)){
            if(username.equals("admin")){
                User user = userService.findByName(username);
                if(user!=null){
                    String respassword = user.getPassword();
                    if(respassword.equals(password)){
                        request.getSession().setAttribute("state",1);
                        response.sendRedirect(request.getContextPath() + "/admin/index"); // 返回
                    }else{
                        request.getSession().setAttribute("state",0);
                        request.getSession().setAttribute("error", "密码错误");
                        response.sendRedirect(request.getContextPath() + "/base/index"); // 返回
                    }
                }else{
                    request.getSession().setAttribute("state",0);
                    request.getSession().setAttribute("error", "用户不存在");
                    response.sendRedirect(request.getContextPath() + "/base/index"); // 返回
                }

            }else{
                User user = userService.findByName(username);
                if(user!=null){
                    String respassword = user.getPassword();
                    if(respassword.equals(password)){
                        request.getSession().setAttribute("username",user.getUsername() );
                        request.getSession().setAttribute("state",1);
                    }else{
                        request.getSession().setAttribute("state",0);
                        request.getSession().setAttribute("error", "密码错误");
                    }
                }else{
                    request.getSession().setAttribute("state",0);
                    request.getSession().setAttribute("error", "用户不存在");
                }
                response.sendRedirect(request.getContextPath() + "/base/index"); // 返回
            }
        }else{
            request.getSession().setAttribute("error", "用户名或密码为空");
            response.sendRedirect(request.getContextPath() + "/base/index");
        }
    }

    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public void logout (HttpServletRequest request,HttpServletResponse response) throws Exception{

        request.getSession().setAttribute("state",null);
        response.sendRedirect(request.getContextPath() + "/base/index"); // 返回
    }

    //注册
    @RequestMapping(value="/regist", method = RequestMethod.POST)
    @ResponseBody
    public ModelMap regist(HttpServletRequest request, String username, String password) {
        ModelMap map = new ModelMap();
        if(StringUtils.isNotEmpty(username)&&StringUtils.isNotEmpty(password)){
            User user = userService.findByName(username);
            if(user!=null){
                map.put("state", 0);
                map.put("msg", "用户已被注册");
            }else{
                User u = new User();
                u.setUsername(username);
                u.setPassword(password);
                String [] userResult = userService.save(u);
                if( userResult[0].equals("false") ){
                    //"操作失败";
                    logger.info("保存失败："+userResult.toString());
                }else{
                    //"操作成功";
                    logger.info("保存成功："+userResult.toString());
                }
                map.put("state", 1);
            }
        }else{
            map.put("state", 0);
            map.put("msg", "用户名或密码为空");
        }
        return map;
    }

}
