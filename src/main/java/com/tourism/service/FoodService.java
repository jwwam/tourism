package com.tourism.service;

import com.tourism.entity.Food;
import com.tourism.entity.Hotel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface FoodService {

    Food findById(String id);

    Page<Food> findAll(Pageable pageable);

    String[] save(Food s);

    void delete(Food s);

    int upload (HttpServletRequest request, HttpServletResponse response);

}
