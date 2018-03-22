package com.tourism.service;

import com.tourism.entity.Scenery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SceneryService {

    Scenery findById(String id);

    Page<Scenery> findAll(Pageable pageable);

    String[] save(Scenery s);

    void delete(Scenery s);

}
