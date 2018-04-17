package com.tourism.service;

import com.tourism.entity.Line;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface LineService {

    Line findById(String id);

    Page<Line> findAll(Pageable pageable);

    String[] save(Line s);

    void delete(Line s);

    int upload (HttpServletRequest request, HttpServletResponse response);

}
