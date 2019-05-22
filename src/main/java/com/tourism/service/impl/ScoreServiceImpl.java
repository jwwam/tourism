package com.tourism.service.impl;

import com.tourism.dao.ScoreDao;
import com.tourism.entity.Score;
import com.tourism.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("scoreService")
public class ScoreServiceImpl implements ScoreService {
    @Autowired
    ScoreDao scoreDao;
    @Override
    public List<Score> findAll() {
        return scoreDao.findAll();
    }
}
