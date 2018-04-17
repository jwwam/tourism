package com.tourism.service.impl;

import com.tourism.dao.UserDao;
import com.tourism.entity.User;
import com.tourism.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;


    public User findByName(String name) {
        return userDao.findByName(name);
    }

    public User findById(String id) {
        return userDao.findById(id);
    }

    public String[] save(User user) {
        String [] array = new String[2];
        System.out.println("aaaaaaaa:"+user.getId());
        if( user.getId() == null || user.getId().equals("")){
            String uuid = UUID.randomUUID().toString(); //获取UUID并转化为String对象
            uuid = uuid.replace("-", "");
            user.setId(uuid);
            array[1] = uuid;
            User userResult = userDao.save(user);
            if(userResult == null ){
                array[0] = "false";
            }else{
                array[0] = "true";
            }
        }else{
            int  result = userDao.updateUser(user.getUsername(), user.getPassword(), user.getId());
            if(result < 0) {
                array[0] = "false";
            }else{
                array[0] = "true";
            }
            array[1] = user.getId();
        }

        return array;
    }

    public Page<User> findAll(Pageable pageable) {
        return userDao.findAll(pageable);
    }

    public void delete(User u){
        userDao.delete(u);
    }

}
