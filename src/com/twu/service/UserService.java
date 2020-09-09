package com.twu.service;

import com.twu.dao.UserDao;
import com.twu.pojo.User;

public class UserService {

    UserDao userDao = new UserDao();

    public User loginCheck(String name, String password){
        return userDao.getUser(name,password);
    }
}
