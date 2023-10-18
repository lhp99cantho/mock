package com.example.service01.Service;


import com.example.service01.model.Entity.User;

public interface UserService {


    User findByUsername(String username);

    User save(User user);


}
