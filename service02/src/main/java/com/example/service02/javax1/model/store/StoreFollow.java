package com.example.service02.javax1.model.store;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table (name = "storeFollow")
public class StoreFollow implements Serializable {

    @Id
    @OneToOne (cascade = CascadeType.ALL)
    @JoinColumn (name = "storeID", referencedColumnName = "storeID")
    private Store storeID;

    @NotEmpty
    @JoinColumn(name = "storeName", referencedColumnName = "storeName")
    protected String storeName;

    @Column (name = "storesIsDeleted")
    private boolean storesIsDeleted;

    @Column (name = "storeAvatar")
    protected String storeAvatar;

    public String toString () {
        return "StoreFollow [storeID " + storeID + ", storeName " + storeName + ", storeIsDelete " + storesIsDeleted + ", storeAvatar " + storeAvatar + "]";
    }
}
