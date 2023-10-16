package com.example.service02.javax1.model.user;

import com.example.service02.javax1.model.store.product.Product;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table (name = "Favorites")
public class Favorite implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long favoriteID;

    @ManyToOne @JoinColumn(name="productID")
    private Product productID;

    @ManyToOne @JoinColumn(name = "userID")
    private User userID;

    @Temporal(TemporalType.DATE)
    private Date likedate = new Date();
}
