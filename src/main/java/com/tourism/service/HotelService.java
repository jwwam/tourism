package com.tourism.service;

import com.tourism.entity.Hotel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface HotelService {

    Hotel findById(String id);

    Page<Hotel> findAll(Pageable pageable);

    String[] save(Hotel s);

    void delete(Hotel s);

    int upload (HttpServletRequest request, HttpServletResponse response);

}
