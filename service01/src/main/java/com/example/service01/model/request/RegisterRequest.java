package com.example.service01.model.request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    @Size(min = 8, message = "Username must be at least 8 characters long")
    @Column(name = "username", unique = true)
    private String username;

    private String email;

    @NotEmpty(message = "Fullname is required!")
    @Column(name = "fullname")
    private String fullname;

    @Size(min = 8, message = "Password must be at least 8 characters long")
    private String password;

}
