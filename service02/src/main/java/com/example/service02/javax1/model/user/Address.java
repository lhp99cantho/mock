package com.example.service02.javax1.model.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import jdk.jfr.MemoryAddress;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table (name = "address")
public class Address implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    protected long id;

    @ManyToOne
    @JoinColumn (name = "user_id", referencedColumnName = "id")
    protected User user;

    @NotEmpty(message = "Address is required!")
    @NotBlank
    @MemoryAddress
    @Column(name = "userAddress")
    private String userAddress;

    @Temporal(TemporalType.DATE)
    @NotBlank
    @Column (name = "userDateUpdate")
    private LocalDateTime userDateUpdate;

    @Override
    public String toString() {
        return "Address [userID=" + id + ", dress=" + userAddress + "]";
    }

}
