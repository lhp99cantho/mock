package com.example.service02.javax1.model.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table (name = "cart")
public class Cart implements Serializable {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    @Column (name = "cartID", unique = true)
    private int cartID;

    @OneToOne (cascade = CascadeType.ALL)
    @JoinColumn (name = "userID", referencedColumnName = "userID")
    private User userID;
}
