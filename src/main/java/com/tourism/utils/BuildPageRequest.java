package com.tourism.utils;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

public class BuildPageRequest {
  public   static PageRequest getPageRequest(int pageNum,int pageSize,Sort.Direction direction,String sortType){
        PageRequest requestPage=new PageRequest(pageNum, pageSize, direction,sortType);
        return  requestPage;
    }
}
