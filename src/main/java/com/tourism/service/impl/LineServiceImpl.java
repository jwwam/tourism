package com.tourism.service.impl;

import com.tourism.dao.LineDao;
import com.tourism.entity.Line;
import com.tourism.service.LineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service("lineService")
public class LineServiceImpl implements LineService {
    @Value("#{configProperties['icb.uploadPath']}")
    private String uploadPath;

    @Value("#{configProperties['icb.tempPath']}")
    private String tempPath;

    @Value("#{configProperties['icb.uploadFilsPath']}")
    private String uploadFilsPath;

    @Autowired
    LineDao lineDao;

    public Line findById(String id) {
        return lineDao.findById(id);
    }

    public Page<Line> findAll(Pageable pageable) {
        return lineDao.findAll(pageable);
    }

    public String[] save(Line l) {
        return new String[0];
    }

    public void delete(Line l) {
        lineDao.delete(l);
    }
}
