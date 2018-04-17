package com.tourism.dao;

import com.tourism.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import javax.transaction.Transactional;

@Transactional
public interface UserDao extends PagingAndSortingRepository<User, Long>,JpaSpecificationExecutor<User>,JpaRepository<User,Long> {

    @Query("from User u where u.username = ?1")
    User findByName(String username);

    @Query("from User u where u.id = ?1")
    User findById(String id);

    @Modifying
    @Query("UPDATE User SET username=?1,password=?2 WHERE id = ?3")
    int updateUser(String username, String password, String id);

}
