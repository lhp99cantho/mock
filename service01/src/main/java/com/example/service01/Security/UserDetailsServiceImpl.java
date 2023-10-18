package com.example.service01.Security;

import com.example.service01.Service.UserService;
import com.example.service01.model.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Kiểm tra xem user có tồn tại trong CSDL không?
        User user = userService.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User Not Found!");
        }
        UserSecurity userSecurity = new UserSecurity(user);
        return userSecurity;
    }

}