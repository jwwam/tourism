package com.tourism.service;

import com.tourism.entity.ContentFlow;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface ContentFlowService {

    int upload (HttpServletRequest request, HttpServletResponse response, String registID);

    List<ContentFlow> getAllLabel();

    void delete(ContentFlow label);

    Page<ContentFlow> findByLabelName(String labelName, Pageable pageable);

    Page<ContentFlow> findAll(Pageable pageable);

    ContentFlow findById(String id);

}
