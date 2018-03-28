package com.tourism.dao;

import com.tourism.entity.Line;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import javax.transaction.Transactional;

@Transactional
public interface LineDao extends PagingAndSortingRepository<Line, Long>,JpaSpecificationExecutor<Line>,JpaRepository<Line,Long> {
    @Query("from Line l where l.id = ?1")
    Line findById(String id);
}
