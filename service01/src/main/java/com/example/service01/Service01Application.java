package com.example.service01;

import com.example.service01.model.Entity.User;
import com.example.service01.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@SpringBootApplication
@EnableDiscoveryClient
public class Service01Application implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(Service01Application.class, args);
    }

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        String pass = passwordEncoder.encode("123456");
        System.out.println("PASS: " + pass);

        Optional<User> userAdmin = userRepository.findByUsername("admin");
        if (!userAdmin.isPresent()) {
            User user = new User();
            user.setFullname("admin");
            user.setUsername("admin");
            user.setPassword(pass);
            user.setRole("ADMIN");
            user.setStatus(true);
            userRepository.save(user);
        }
    }
}
