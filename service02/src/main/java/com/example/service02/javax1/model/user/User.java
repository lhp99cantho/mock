package com.example.service02.javax1.model.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.format.annotation.NumberFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table (name = "user")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "userID", unique = true, length = 10)
    @OneToOne (mappedBy = "userID")
    private User userID;

    @Id
    @NotEmpty(message = "Name is required!")
    @NotBlank
    @Column(name = "userName")
    @OneToOne (mappedBy = "userName")
    private User userName;

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

    @Column(name = "userPoint")
    private int userPoint;

    @NotEmpty(message = "User Active is required!")
    @Column(name = "userActive")
    private boolean userActive;

    @Column(name = "userRole")
    private int userRole;

    @Temporal(TemporalType.DATE)
    @NotBlank
    @Column (name = "userDateCreate")
    private LocalDateTime userDateCreate;

    public User(String userEmail, String userPassword, String fullname, String email, String phonenumber, boolean b, boolean b1, String image) {

    }

    public String toString() {
        return "UserModels [userID=" + userID + ", name=" + userName + ", email=" + userEmail + ", phone="
                + userPhoneNumber + ", username=" + userUsername + ", password=" + userPassword + ", avatar=" + userAvatar + ", point=" + userPoint
                + "]";
    }

}
