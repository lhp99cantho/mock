package com.example.service02.javax1.service;

import com.example.service02.javax1.model.user.User;

import java.util.List;

public interface UserService {
    List<User> findAll();

    User findById(int id);

    User findByUsername(String username);

    User register(User user);
}
