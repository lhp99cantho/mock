package com.example.service01.repository;



import com.example.service01.model.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByUsername(String username);

    Optional<User> findByVerificationCode(String code);

    Optional<User> findByEmail(String email);

    Optional<User> findByResetPasswordToken(String token);

}
