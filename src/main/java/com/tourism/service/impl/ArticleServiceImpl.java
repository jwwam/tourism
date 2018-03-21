package com.tourism.service.impl;

import com.tourism.common.BaseLogger;
import com.tourism.dao.ArticleDao;
import com.tourism.entity.Article;
import com.tourism.service.ArticleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class ArticleServiceImpl extends BaseLogger implements ArticleService {

    @Resource
    ArticleDao articleDao;

    @Override
    public List<Article> getAllList() {
        return articleDao.findAll();
    }

    @Override
    public List<Article> getList(String userId) {
        return articleDao.findByUserId(userId);
    }


}
