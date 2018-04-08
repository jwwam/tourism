package com.tourism.service;

import com.tourism.entity.User;

public interface UserService {

    User findByName (String name);

    String[] save(User user);
}
