package com.example.service02.javax1.model.guest;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jdk.jfr.MemoryAddress;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.NumberFormat;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table (name = "guest")
public class Guest {

    @Id
    @Column(name = "id", unique = true, length = 10)
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty (message = "Guest Name is required!")
    @NotBlank
    @Column (name = "guestName")
    private String guestName;

    @NotEmpty (message = "Email is required!")
    @NotBlank
    @Email
    @Column (name = "guestEmail")
    private String guestEmail;

    @NotEmpty (message = "Phone Number is required!")
    @NotBlank
    @NumberFormat
    @Column (name = "guestPhoneNumber")
    private String guestPhoneNumber;

    @NotEmpty (message = "Address is required!")
    @NotBlank
    @MemoryAddress
    @Column (name = "guestAddress")
    private String guestAddress;

    public String toString() {
        return "Guest [id " + id + ", name = " + guestName + ", email = " + guestEmail + ", sdt = " + guestPhoneNumber + ", address = " + guestAddress + "]";
    }

}
