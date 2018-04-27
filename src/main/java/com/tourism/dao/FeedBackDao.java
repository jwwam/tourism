package com.tourism.dao;

import com.tourism.entity.FeedBack;
import com.tourism.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import javax.transaction.Transactional;

@Transactional
public interface FeedBackDao extends PagingAndSortingRepository<FeedBack, Long>,JpaSpecificationExecutor<FeedBack>,JpaRepository<FeedBack,Long> {

}
