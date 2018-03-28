package com.tourism.service;

import com.tourism.entity.Line;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LineService {

    Line findById(String id);

    Page<Line> findAll(Pageable pageable);

    String[] save(Line s);

    void delete(Line s);

}
