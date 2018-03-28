package com.tourism.service;

import com.tourism.entity.Gallery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GalleryService {

    Gallery findById(String id);

    Page<Gallery> findAll(Pageable pageable);

    String[] save(Gallery s);

    void delete(Gallery s);

}
