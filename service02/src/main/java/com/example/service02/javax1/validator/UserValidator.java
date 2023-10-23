package com.example.service02.javax1.validator;

import com.example.service02.javax1.model.user.User;
import com.example.service02.javax1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {
    @Autowired
    private UserService userService;

    @Override
    public boolean supports (Class<?> clazz){
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors){
        User user = (User) target;
        if(!StringUtils.isEmpty(user.getUserName()) && user.equals("admin")){
            errors.rejectValue("username, error.username", "Username 'admin' is not allowed!");
        } else {
            if(userService.findByUsername(user.getUserName()) != null){
                errors.rejectValue("username", "error.username", "Username already exists!");
            }
        }
    }

}
