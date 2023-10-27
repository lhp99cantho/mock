package com.example.service01.validator;


import com.example.service01.Service.UserService;
import com.example.service01.model.request.RegisterRequest;
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
    public boolean supports(Class<?> clazz) {
        return RegisterRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        RegisterRequest user = (RegisterRequest) target;

        if (!StringUtils.isEmpty(user.getUsername()) && user.getUsername().equalsIgnoreCase("admin")) {
            errors.rejectValue("username", "error.username", "Username 'admin' is not allowed!");
        } else {
            if (userService.findByUsername(user.getUsername()) != null) {
                errors.rejectValue("username", "error.username", "Username already exists!");
            }
            if (userService.findByEmail(user.getEmail()) != null) {
                errors.rejectValue("email", "error.email", "Email already exists!");
            }
        }
    }

}
