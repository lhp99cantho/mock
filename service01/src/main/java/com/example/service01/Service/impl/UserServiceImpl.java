package com.example.service01.Service.impl;


import com.example.service01.Security.UserSecurity;
import com.example.service01.Service.UserService;
import com.example.service01.model.Entity.User;
import com.example.service01.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    @Override
    public User save(User user) {
        return userRepository.saveAndFlush(user);
    }

}


