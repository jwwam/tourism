package com.tourism.controller.base;

import com.tourism.entity.Score;
import com.tourism.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ScoreServiceTest {
    @Autowired
    ScoreService scoreService;
        List<Score> list = scoreService.findAll();
}
