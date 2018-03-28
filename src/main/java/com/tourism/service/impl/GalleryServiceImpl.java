package com.tourism.service.impl;

import com.tourism.dao.GalleryDao;
import com.tourism.entity.Gallery;
import com.tourism.service.GalleryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service("galleryService")
public class GalleryServiceImpl implements GalleryService{

    @Value("#{configProperties['icb.uploadPath']}")
    private String uploadPath;

    @Value("#{configProperties['icb.tempPath']}")
    private String tempPath;

    @Value("#{configProperties['icb.uploadFilsPath']}")
    private String uploadFilsPath;

    @Autowired
    GalleryDao galleryDao;

    public Gallery findById(String id) {
        return galleryDao.findById(id);
    }

    public Page<Gallery> findAll(Pageable pageable) {
        return galleryDao.findAll(pageable);
    }

    public String[] save(Gallery g) {
        return new String[0];
    }

    public void delete(Gallery g) {
        galleryDao.delete(g);
    }

}
