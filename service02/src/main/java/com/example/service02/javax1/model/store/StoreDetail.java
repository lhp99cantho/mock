package com.example.service02.javax1.model.store;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table (name = "storeFollow")
public class StoreDetail implements Serializable {

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
        return "StoreDetail [storeID " + storeID + ", storeName " + storeName + ", storeIsDelete " + storesIsDeleted + ", storeAvatar " + storeAvatar + "]";
    }
}
