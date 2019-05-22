package com.tourism.service;

import com.tourism.entity.Scenery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface SceneryService {

    Scenery findById(String id);

    Page<Scenery> findAll(Pageable pageable);

    Page<Scenery> findAll(String address, String sPrice, String ePrice, String star, Pageable pageable);

    String[] save(Scenery s);

    void delete(Scenery s);
     //推荐一个景点的相似景点
    List<Scenery> recommend(String sceneryName);
    int upload (HttpServletRequest request, HttpServletResponse response);
}
