package com.tourism.controller;

import com.tourism.controller.base.BaseController;
import com.tourism.entity.Desktop;
import com.tourism.entity.User;
import com.tourism.service.DesktopService;
import com.tourism.service.UserService;
import com.tourism.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @RequestMapping(value="/login")
    public void login (HttpServletRequest request,HttpServletResponse response) throws Exception{
        String username = (String) request.getAttribute("username");
        String password = (String) request.getAttribute("password");

        if(StringUtils.isNotEmpty(username)&&StringUtils.isNotEmpty(password)){
            if(username.equals("admin")){
                User user = userService.findByName(username);
                if(user!=null){
                    String respassword = user.getPassword();
                    if(respassword.equals(password)){
                        request.getSession().setAttribute("state",1);
                    }else{
                        request.getSession().setAttribute("state",0);
                        request.setAttribute("error", "密码错误");
                    }
                }else{
                    request.setAttribute("error", "用户不存在");
                }
                response.sendRedirect(request.getContextPath() + "/admin/index"); // 返回
            }else{
                User user = userService.findByName(username);
                if(user!=null){
                    String respassword = user.getPassword();
                    if(respassword.equals(password)){
                        request.setAttribute("username",user.getUsername() );
                        request.getSession().setAttribute("state",1);
                    }else{
                        request.getSession().setAttribute("state",0);
                        request.setAttribute("error", "密码错误");
                    }
                }else{
                    request.setAttribute("error", "用户不存在");
                }
                response.sendRedirect(request.getContextPath() + "/base/index"); // 返回
            }
        }else{
            request.setAttribute("error", "用户名或密码为空");
            response.sendRedirect(request.getContextPath() + "/base/index");
        }
    }


    @RequestMapping(value="/regist")
    public void regist (HttpServletRequest request,HttpServletResponse response) throws Exception{

        String username = (String) request.getAttribute("username");
        String password = (String) request.getAttribute("password");

        if(StringUtils.isNotEmpty(username)&&StringUtils.isNotEmpty(password)){
            User user = new User();
            user.setUsername(username);
            user.setPassword(password);
            String [] userResult = userService.save(user);
            if( userResult[0].equals("false") ){
                //"操作失败";
                logger.info("保存失败："+userResult.toString());
            }else{
                //"操作成功";
                logger.info("保存成功："+userResult.toString());
            }
        }else{
            request.setAttribute("error", "用户名或密码为空");
        }
        response.sendRedirect(request.getContextPath() + "/base/index");
    }

}
