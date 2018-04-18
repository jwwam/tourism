package com.tourism.dao;

import com.tourism.entity.Line;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import javax.transaction.Transactional;

@Transactional
public interface LineDao extends PagingAndSortingRepository<Line, Long>,JpaSpecificationExecutor<Line>,JpaRepository<Line,Long> {
    @Query("from Line l where l.id = ?1")
    Line findById(String id);

    @Modifying
    @Query("UPDATE Line SET address=?1, day=?2, detail=?3, img=?4, price=?5, title=?6 WHERE id = ?7")
    int updateLine(String address, String day, String detail, String img, String price, String title, String id);

}
