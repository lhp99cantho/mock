package com.example.service02.javax1.model.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import jdk.jfr.MemoryAddress;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table (name = "address")
public class Address {

    @Id
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn (name = "userID", referencedColumnName = "userID")
    private User userID;

    @NotEmpty(message = "Address is required!")
    @NotBlank
    @MemoryAddress
    @Column(name = "userAddress")
    @OneToOne (mappedBy = "userAddress")
    private Address userAddress;

    @CreationTimestamp
    @NotBlank
    @Column (name = "userDateUpdate")
    private LocalDateTime userDateUpdate;

    @Override
    public String toString() {
        return "Address [userID=" + userID + ", dress=" + userAddress + "]";
    }

}
