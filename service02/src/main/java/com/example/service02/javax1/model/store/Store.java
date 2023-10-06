package com.example.service02.javax1.model.store;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table (name = "store")
public class Store {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    @Column (name = "storeID", unique = true, length = 16)
    private int storeID;

    @OneToOne (mappedBy = "storeID")
    private Store store;

    @NotEmpty (message = "Store name is required!")
    @NotBlank
    @Column (name = "storeName")
    @OneToOne(mappedBy = "storeName")
    private Store storeName;

    @NotEmpty (message = "Owner ID is required!")
    @NotBlank
    @Column (name = "ownerID")
    private int ownerID;

    @NotNull
    @NotBlank
    @Column (name = "storeActive")
    private Boolean storeActive;

    @NotNull
    @NotBlank
    @Column (name = "storeOpen")
    private Boolean storeOpen;

    @NotNull
    @NotBlank
    @Column (name = "storeAvatar")
    private String storeAvatar;

    @NotNull (message = "Store email contact is required!")
    @NotBlank
    @Column (name = "storeEmailContact", unique = true)
    private String storeEmailContact;

    @NotNull (message = "Store point is required!")
    @NotBlank
    @Column (name = "storePoint")
    private int storePoint;

    @NotNull
    @NotBlank
    @Column (name = "storeRating")
    private double storeRating;

    @NotNull
    @NotBlank
    @PastOrPresent
    @CreationTimestamp
    @Column (name = "storeCreateAt")
    private LocalDateTime storeCreateAt;

    @NotNull
    @NotBlank
    @PastOrPresent
    @CreationTimestamp
    @Column (name = "storeUpdateAt")
    private LocalDateTime storeUpdateAt;

    @NotEmpty (message = "Owner Name is required!")
    @NotBlank
    @Column (name = "ownerName")
    private String ownerName;

    @NotEmpty
    @NotBlank
    @Column (name = "storeSumCost")
    private int storeSumCost;

    public Store(int storeID, Store storeName, int ownerID, Boolean active, Boolean open, String avatar, int point,
                 double rating, String createAt, String updateAt, String ownerName, int storeSumCost) {
        super();
        this.storeID = storeID;
        this.storeName = storeName;
        this.ownerID = ownerID;
        this.storeActive = active;
        this.storeOpen = open;
        this.storeAvatar = avatar;
        this.storePoint = point;
        this.storeRating = rating;
        this.storeCreateAt = LocalDateTime.parse(createAt);
        this.storeUpdateAt = LocalDateTime.parse(updateAt);
        this.ownerName=ownerName;
        this.storeSumCost = storeSumCost;
    }

    @Override
    public String toString() {
        return "Store [storeId=" + storeID + ", storeName=" + storeName + ", ownerID=" + ownerID + ", active="
                + storeActive + ", open=" + storeOpen + ", avatar=" + storeAvatar + ", point=" + storePoint + ", rating=" + storeRating
                + ", createAt=" + storeCreateAt + ", updateAt=" + storeUpdateAt + "]";
    }

    public Store (int storeID, Store storeName, int ownerID, String ownerName, int sumCost) {
        super();
        this.storeID = storeID;
        this.storeName = storeName;
        this.ownerID = ownerID;
        this.ownerName = ownerName;
        this.storeSumCost = sumCost;
    }

    public Store (int storeID, Store storeName, Boolean open, String avatar, String updateAt) {

        this.storeID = storeID;
        this.storeName = storeName;
        this.storeOpen = open;
        this.storeAvatar = avatar;
    }
}
