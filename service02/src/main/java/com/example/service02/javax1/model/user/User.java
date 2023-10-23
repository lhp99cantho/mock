package com.example.service02.javax1.model.user;

import com.example.service02.javax1.model.store.order.Order;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.format.annotation.NumberFormat;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table (name = "user")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    protected int id;

    @NotEmpty(message = "Name is required!")
    @NotBlank
    @Column(name = "Name")
    protected String name;

    @NotEmpty(message = "Email is required!")
    @NotBlank
    @Email
    @Column(name = "Email")
    protected String email;

    @NotEmpty(message = "Phone number is required!")
    @NotBlank
    @NumberFormat
    @Column(name = "PhoneNumber", unique = true, length = 10)
    protected String phoneNumber;

    @NotEmpty(message = "Username is required!")
    @NotBlank
    @Column(name = "Username", unique = true, length = 16)
    private String userName;

    @NotEmpty(message = "Password is required!")
    @NotBlank
    @Column(name = "Password", unique = true, length = 20)
    private String password;

    @NotEmpty(message = "Avatar is required!")
    @NotBlank
    @Column(name = "userAvatar")
    protected String userAvatar;

    @Column(name = "userPoint")
    protected int userPoint;

    @NotEmpty(message = "User Active is required!")
    @Column(name = "userActive")
    protected boolean userActive;

    @Column(name = "userRole")
    protected int userRole;

    @Temporal(TemporalType.DATE)
    @NotBlank
    @Column (name = "userDateCreate")
    protected LocalDateTime userDateCreate;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    protected List<Order> order;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    protected Address address;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    protected Favorite favorite;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    protected Comments comments;

    @OneToOne(mappedBy = "user")
    protected UserDetail userDetail;

    public String toString() {
        return "UserModels [ID=" + id + ", name=" + name + ", email=" + email + ", phone="
                + phoneNumber + ", username=" + userName + ", password=" + password + ", avatar=" + userAvatar + ", point=" + userPoint
                + "]";
    }

}
