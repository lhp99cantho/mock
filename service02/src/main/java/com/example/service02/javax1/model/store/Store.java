package com.example.service02.javax1.model.store;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table (name = "store")
public class Store implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    @Column (name = "storeID", unique = true, length = 16)
    @OneToOne (mappedBy = "storeID")
    protected Store storeID;

    @NotEmpty (message = "Store name is required!")
    @NotBlank
    @Column (name = "storeName")
    @OneToOne (mappedBy = "storeName")
    protected Store storeName;

    @NotEmpty (message = "Owner ID is required!")
    @NotBlank
    @Column (name = "ownerID")
    protected int ownerID;

    @NotNull
    @NotBlank
    @Column (name = "storeActive")
    protected Boolean storeActive;

    @NotNull
    @NotBlank
    @Column (name = "storeOpen")
    protected Boolean storeOpen;

    @NotNull
    @NotBlank
    @Column (name = "storeAvatar")
    protected String storeAvatar;

    @NotNull (message = "Store email contact is required!")
    @NotBlank
    @Column (name = "storeEmailContact", unique = true)
    protected String storeEmailContact;

    @NotNull (message = "Store point is required!")
    @NotBlank
    @Column (name = "storePoint")
    protected int storePoint;

    @NotNull
    @NotBlank
    @Column (name = "storeRating")
    protected double storeRating;

    @NotNull
    @NotBlank
    @PastOrPresent
    @Temporal(TemporalType.DATE)
    @Column (name = "storeCreateAt")
    protected LocalDateTime storeCreateAt;

    @NotNull
    @NotBlank
    @PastOrPresent
    @Temporal(TemporalType.DATE)
    @Column (name = "storeUpdateAt")
    protected LocalDateTime storeUpdateAt;

    @NotEmpty (message = "Owner Name is required!")
    @NotBlank
    @Column (name = "ownerName")
    protected String ownerName;

    @NotEmpty
    @NotBlank
    @Column (name = "storeSumCost")
    protected int storeSumCost;

    public Store(Store storeID, Store storeName, int ownerID, Boolean active, Boolean open, String avatar, int point,
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

    public Store (Store storeID, Store storeName, int ownerID, String ownerName, int sumCost) {
        super();
        this.storeID = storeID;
        this.storeName = storeName;
        this.ownerID = ownerID;
        this.ownerName = ownerName;
        this.storeSumCost = sumCost;
    }

    public Store (Store storeID, Store storeName, Boolean open, String avatar, String updateAt) {

        this.storeID = storeID;
        this.storeName = storeName;
        this.storeOpen = open;
        this.storeAvatar = avatar;
    }
}
