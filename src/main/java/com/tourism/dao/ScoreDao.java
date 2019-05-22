package com.tourism.dao;
import com.tourism.entity.Score;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface ScoreDao extends PagingAndSortingRepository<Score, Long>,JpaSpecificationExecutor<Score>,JpaRepository<Score,Long> {
    @Query(value ="from Score ")
    List<Score> findAll();
}
