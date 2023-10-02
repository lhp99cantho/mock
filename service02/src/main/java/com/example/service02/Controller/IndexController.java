package com.example.service02.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/sv02")
public class IndexController {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private Environment env;

    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        String res = restTemplate.getForObject("http://service01/sv01/hello", String.class);
        return ResponseEntity.ok(res);
    }
    //Đũy Phong bớt bán cơm chó lại nha mày

    @GetMapping("")
    public ResponseEntity<String> index() {
        String res = "OK Service 02";
        return ResponseEntity.ok(res);
    }
}
