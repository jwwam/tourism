package com.tourism.dao;

import com.tourism.entity.Article;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ArticleDao extends PagingAndSortingRepository<Article, Long> {

    @Query(value ="from Article  where blogid = ?1")
    List<Article> findByUserId(String id);

    @Query(value ="from Article")
    List<Article> findAll();

}
