package com.example.service02.javax1.model.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jdk.jfr.MemoryAddress;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.NumberFormat;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table (name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "userID", unique = true, length = 10)
    private int userID;

    @OneToOne (mappedBy = "userID")
    private User user;

    @NotEmpty(message = "Name is required!")
    @NotBlank
    @Column(name = "userName")
    private String userName;

    @NotEmpty(message = "Email is required!")
    @NotBlank
    @Email
    @Column(name = "userEmail")
    private String userEmail;

    @NotEmpty(message = "Phone number is required!")
    @NotBlank
    @NumberFormat
    @Column(name = "userPhoneNumber", unique = true, length = 10)
    private String userPhoneNumber;

    @NotEmpty(message = "Username is required!")
    @NotBlank
    @Column(name = "userUsername", unique = true, length = 16)
    private String userUsername;

    @NotEmpty(message = "Password is required!")
    @NotBlank
    @Column(name = "userPassword", unique = true, length = 20)
    private String userPassword;

    @NotEmpty(message = "Avatar is required!")
    @NotBlank
    @Column(name = "userAvatar")
    private String userAvatar;

    @NotEmpty
    @NotBlank
    @Column(name = "userPoint")
    private float userPoint;

    @NotEmpty
    @NotBlank
    @Column(name = "userRole")
    private int userRole;

    @CreationTimestamp
    @NotBlank
    @Column (name = "userDateCreate")
    private LocalDateTime userDateCreate;

    public String toString() {
        return "UserModels [userID=" + userID + ", name=" + userName + ", email=" + userEmail + ", phone="
                + userPhoneNumber + ", username=" + userUsername + ", password=" + userPassword + ", avatar=" + userAvatar + ", point=" + userPoint
                + "]";
    }

}
