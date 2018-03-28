package com.tourism.dao;

import com.tourism.entity.Gallery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import javax.transaction.Transactional;

@Transactional
public interface GalleryDao extends PagingAndSortingRepository<Gallery, Long>,JpaSpecificationExecutor<Gallery>,JpaRepository<Gallery,Long> {
    @Query("from Gallery g where g.id = ?1")
    Gallery findById(String id);
}
