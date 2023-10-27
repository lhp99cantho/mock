package com.example.service01.model.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@Entity
@Table(name = "users")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int userId;

    @NotEmpty(message = "Fullname is required!")
    @Column(name = "fullname")
    private String fullname;

    @NotEmpty(message = "Username is required!")
    @Column(name = "username", unique = true)
    private String username;

    @JsonIgnore
    @NotEmpty(message = "Password is required!")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    @Column(name = "password")
    private String password;

    @Column(name = "role")
    private String role;

    @Column(name = "status")
    private boolean status;

    @Column(name = "email")
    private String email;

    @Column(name = "image")
    private String image;

    @Column(name="phone")
    private String phone;

    @Column(name = "verification_code", length = 64)
    private String verificationCode;

    @Column(name = "reset_password_token")
    private String resetPasswordToken;

}
