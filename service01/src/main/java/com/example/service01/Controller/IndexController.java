package com.example.service01.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class IndexController {
    @Autowired
    private Environment evn;

    @RequestMapping("/service01")
    public String getMessage() {
        return "Successfully from service01";
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello from service 01";
    }
}
