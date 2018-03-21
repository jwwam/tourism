package com.tourism.controller.Article;

import com.tourism.controller.base.BaseController;
import com.tourism.entity.Article;
import com.tourism.service.ArticleService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping(value="/article")
public class ArticleController extends BaseController {

    @Resource
    ArticleService articleService;

    @RequestMapping(value="/index")
    public String index(HttpServletRequest request){
        List<Article> aList = articleService.getAllList();
        request.setAttribute("aList", aList);
        return "/lover/index";
    }


}
