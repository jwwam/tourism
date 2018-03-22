package com.tourism.dao;

import com.tourism.entity.Scenery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import javax.transaction.Transactional;

@Transactional
public interface SceneryDao extends PagingAndSortingRepository<Scenery, Long>,JpaSpecificationExecutor<Scenery>,JpaRepository<Scenery,Long> {

    @Query("from Scenery s where s.id = ?1")
    Scenery findById(String id);

}
