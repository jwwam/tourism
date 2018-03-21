package com.tourism.dao;

import com.tourism.entity.Desktop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import javax.transaction.Transactional;

@Transactional
public interface DesktopDao extends PagingAndSortingRepository<Desktop, Long>,JpaSpecificationExecutor<Desktop>,JpaRepository<Desktop,Long> {

    @Modifying
    @Query("UPDATE Desktop SET email=?1,iphone=?2 WHERE id = ?3")
    int updateDesktop(String email, String iphone, String id);

}
