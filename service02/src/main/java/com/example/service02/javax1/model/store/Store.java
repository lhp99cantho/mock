package com.example.service02.javax1.model.store;

import com.example.service02.javax1.model.store.cart.Voucher;
import com.example.service02.javax1.model.store.order.Order;
import com.example.service02.javax1.model.store.product.Category;
import com.example.service02.javax1.model.store.product.Product;
import com.example.service02.javax1.model.user.Comments;
import com.example.service02.javax1.model.user.UserDetail;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

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
    @Column(name = "id")
    protected long id;

    @OneToMany (mappedBy = "store", cascade = CascadeType.ALL)
    protected Order order;

    @OneToMany(mappedBy = "store",cascade = CascadeType.ALL)
    protected Product product;

    @OneToMany(mappedBy = "store")
    protected Category category;

    @OneToOne(mappedBy = "store")
    protected StoreDetail storeDetail;

    @OneToMany(mappedBy = "store")
    protected Voucher voucher;

    @OneToOne(mappedBy = "comments")
    protected Comments comments;

    @OneToOne(mappedBy = "userDetail")
    protected UserDetail userDetail;

    @NotEmpty (message = "Store name is required!")
    @NotBlank
    @Column (name = "storeName")
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

    public Store(Long id, Store storeName, int ownerID, Boolean active, Boolean open, String avatar, int point,
                 double rating, String createAt, String updateAt, String ownerName, int storeSumCost) {
        super();
        this.id = id;
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
        return "Store [storeId=" + id + ", storeName=" + storeName + ", ownerID=" + ownerID + ", active="
                + storeActive + ", open=" + storeOpen + ", avatar=" + storeAvatar + ", point=" + storePoint + ", rating=" + storeRating
                + ", createAt=" + storeCreateAt + ", updateAt=" + storeUpdateAt + "]";
    }

    public Store (Long id, Store storeName, int ownerID, String ownerName, int sumCost) {
        super();
        this.id = id;
        this.storeName = storeName;
        this.ownerID = ownerID;
        this.ownerName = ownerName;
        this.storeSumCost = sumCost;
    }

    public Store (Long id, Store storeName, Boolean open, String avatar, String updateAt) {

        this.id = id;
        this.storeName = storeName;
        this.storeOpen = open;
        this.storeAvatar = avatar;
    }
}
