package com.tourism.dao;

import com.tourism.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import javax.transaction.Transactional;

@Transactional
public interface HotelDao extends PagingAndSortingRepository<Hotel, Long>,JpaSpecificationExecutor<Hotel>,JpaRepository<Hotel,Long> {
    @Query("from Hotel h where h.id = ?1")
    Hotel findById(String id);
}
