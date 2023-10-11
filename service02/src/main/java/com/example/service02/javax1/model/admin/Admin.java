package com.example.service02.javax1.model.admin;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;


import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table (name = "admin")
public class Admin implements Serializable {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    @Column (name = "adminID", unique = true)
    private int adminID;

    @NotEmpty(message = "Name is required!")
    @NotBlank
    @Column(name = "adminName")
    private String adminName;

    @NotEmpty (message = "Admin Password is required!")
    @NotBlank
    @Column(name = "adminPassword")
    private String adminPassword;

    @Column(name = "adminDateCreate")
    @CreationTimestamp
    @NotNull
    private LocalDateTime adminDateCreate;
}
