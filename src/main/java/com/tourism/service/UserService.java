package com.tourism.service;

import com.tourism.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    User findByName (String name);

    String[] save(User user);

    Page<User> findAll(Pageable pageable);

    User findById (String id);

    void delete(User user);

}
