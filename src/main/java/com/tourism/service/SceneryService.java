package com.tourism.service;

import com.tourism.entity.Scenery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface SceneryService {

    Scenery findById(String id);

    Page<Scenery> findAll(Pageable pageable);

    String[] save(Scenery s);

    void delete(Scenery s);

    int upload (HttpServletRequest request, HttpServletResponse response);

}
