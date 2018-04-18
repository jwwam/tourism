package com.tourism.dao;

import com.tourism.entity.Scenery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import javax.transaction.Transactional;

@Transactional
public interface SceneryDao extends PagingAndSortingRepository<Scenery, Long>,JpaSpecificationExecutor<Scenery>,JpaRepository<Scenery,Long> {

    @Query("from Scenery s where s.id = ?1")
    Scenery findById(String id);

    @Modifying
    @Query("UPDATE Scenery SET address=?1, star=?2, day=?3, detail=?4, img=?5, price=?6, title=?7  WHERE id = ?8")
    int updateScenery(String address, String star, String day, String detail, String img, String price, String title, String id);

}
