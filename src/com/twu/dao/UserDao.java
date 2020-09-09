package com.twu.dao;

import com.twu.pojo.User;

import java.util.ArrayList;
import java.util.List;

public class UserDao {
    public static List<User> users = new ArrayList<>();

    static {
        users.add(new User("admin", "admin", 1));
        users.add(new User("fu", "fu", 2));
    }

    public User getUser(String username,String password) {
        User loginUser = null;
        for (User user : users) {
            if(user.getName().equals(username)&&user.getPassword().equals(password)){
                loginUser = user;
            }
        }
        return loginUser;
    }
}
