package com.tourism.controller.admin;

import com.tourism.common.DataTablesParam;
import com.tourism.controller.base.BaseController;
import com.tourism.entity.ContentFlow;
import com.tourism.service.ContentFlowService;
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
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value="/content")
public class ContentController extends BaseController{

    @Autowired
    ContentFlowService contentFlowService;

    @RequestMapping(value="/view")
    public String view(HttpServletRequest request){
        request.setAttribute("labelList",contentFlowService.getAllLabel());
        request.setAttribute("clickId","label");
        return "/admin/content";
    }

    @RequestMapping(value="/getContentList",method = RequestMethod.POST)
    @ResponseBody
    public ModelMap getContentList(DataTablesParam dataTablesParam, String labelName){
        ModelMap map = new ModelMap();
        Page<ContentFlow> pageRe = null;
        if(StringUtils.isNotEmpty(labelName)){
            pageRe = contentFlowService.findByLabelName(labelName, BuildPageRequest.getPageRequest(dataTablesParam.getPageNum(), dataTablesParam.getiDisplayLength(), Sort.Direction.DESC, "id"));
        }else {
            pageRe = contentFlowService.findAll(BuildPageRequest.getPageRequest(dataTablesParam.getPageNum(), dataTablesParam.getiDisplayLength(), Sort.Direction.DESC, "id"));
        }
        map.put("sEcho", dataTablesParam.getsEcho());
        map.put("iTotalRecords", pageRe.getTotalElements());
        map.put("iTotalDisplayRecords",pageRe.getTotalElements());
        map.put("aaData", pageRe.getContent());
        return  map;
        //return  getModelMap(StateParameter.SUCCESS,"","加载数据成功");
    }

    //删除信息流
    @RequestMapping(value="/deleteContent", method = RequestMethod.POST)
    @ResponseBody
    public ModelMap deleteContent(String id) {
        ModelMap map = new ModelMap();
        contentFlowService.delete(contentFlowService.findById(id));
        map.put("msg", "删除成功");
        return map;
    }

    @RequestMapping(value="/upload")
    public String upload (HttpServletRequest request,HttpServletResponse response){

        //String registID = (String) request.getSession().getAttribute("registID");
        String registID = "";
        int result = contentFlowService.upload(request, response,registID);

        if( result < 0 ){
            request.setAttribute("failMsg", "上传失败，请重新上传！");
            //return "/gongshang/recordUpload";
            return "/admin/content";
        }else{
            //return "/gongshang/recordReview";
            return "/admin/content";
        }

    }


}
