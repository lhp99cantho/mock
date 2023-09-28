package com.example.service01.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sv01")
public class IndexController {
    @Autowired
    private Environment evn;

    @GetMapping("/hello")
    public String hello() {
        return "Hello from service 01";
    }

    @GetMapping("")
    public ResponseEntity<String> index() {
        String res = "OK Service 01";
        return ResponseEntity.ok(res);
    }
}
